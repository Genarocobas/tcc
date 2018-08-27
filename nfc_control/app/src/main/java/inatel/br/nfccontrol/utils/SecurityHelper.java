package inatel.br.nfccontrol.utils;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import inatel.br.nfccontrol.di.preference.BooleanPreference;
import inatel.br.nfccontrol.di.preference.StringPreference;
import inatel.br.nfccontrol.di.qualifier.ApiTokenPreference;
import inatel.br.nfccontrol.di.qualifier.IsAuthenticatedPreference;
import inatel.br.nfccontrol.di.qualifier.RefreshTokenPreference;
import inatel.br.nfccontrol.di.qualifier.SecretKeyPreference;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.security.auth.x500.X500Principal;

/**
 * SecretKey generation and encryption and decryption of objects.
 * Based on
 * {@link "https://medium.com/@ericfu/securely-storing-secrets-in-an-android-application-501f030ae5a3"}
 * SecretKey store method:
 * - Generate a pair of RSA keys;
 * - Generate a random AES key;
 * - Encrypt the AES key with the RSA public key;
 * - Store the encrypted AES key;
 */
public class SecurityHelper {

  private static final String TAG = Logger.getTag();
  private KeyStore mKeyStore;
  private static final String KEY_STORE_ANDROID = "AndroidKeyStore";
  private static final String KEYSTORE_KEY_ALIAS = "KeyStoreKeyAlias2";
  private static final String RSA_MODE = "RSA/ECB/PKCS1Padding";
  private static final String AES_MODE = "AES/ECB/PKCS7Padding";
  private static final String CIPHER_PROVIDER = "BC";

  @Inject
  @SecretKeyPreference
  StringPreference mSecretKey;

  @Inject
  @ApiTokenPreference
  StringPreference mApiTokenPreference;

  @Inject
  @RefreshTokenPreference
  StringPreference mRefreshTokenPreference;

  @Inject
  @IsAuthenticatedPreference
  BooleanPreference mIsAuthenticatedPreference;

  @Inject
  Context mContext;

  @Inject
  SecurityHelper() {
    if (Logger.DEBUG) Log.d(TAG, "constructor");
    try {
      mKeyStore = KeyStore.getInstance(KEY_STORE_ANDROID);
      mKeyStore.load(null);
    } catch (Exception e) {
      Log.e(TAG, "SecurityHelper: ", e);
      e.printStackTrace();
    }
  }

  public void persistApplicationApiToken(String token) throws Exception {
    mApiTokenPreference.put(encrypt(token
        .getBytes(Config.ENCRYPTION_CHAR_ENCODING)));
  }

  public void persistApplicationRefreshToken(String token) throws Exception {
    mRefreshTokenPreference.put(encrypt(token
        .getBytes(Config.ENCRYPTION_CHAR_ENCODING)));
  }

  public void deleteTokens() {
    mApiTokenPreference.put(null);
    mRefreshTokenPreference.put(null);
  }

  public String getApplicationApiToken() {
    try {
      final String token = mApiTokenPreference.get();

      if (token != null) {
        byte[] decryptedToken = new byte[0];
        decryptedToken = decrypt(token
            .getBytes(Config.ENCRYPTION_CHAR_ENCODING));
        return new String(decryptedToken, Config.ENCRYPTION_CHAR_ENCODING);
      }
    } catch (Exception e) {
      Log.e(TAG, "getApplicationApiToken exception: ", e);
      mIsAuthenticatedPreference.put(false);
    }

    return null;
  }

  /**
   * Setup this class with all needed key pairs.
   */
  public void setupSecurityHelper() {
    try {
      generateRSAPairs();
      generateAESKey();
    } catch (Exception e) {
      Log.e(TAG, "setupSecurityHelper error: " + e.getMessage());
    }
  }

  /**
   * Generator the RSA pairs that will be used to encode the AES created key.
   *
   * @throws Exception exceptions.
   */
  private void generateRSAPairs() throws Exception {
    if (Logger.DEBUG) Log.d(TAG, "generateRSAPairs");
    if (!mKeyStore.containsAlias(KEYSTORE_KEY_ALIAS)) {
      Calendar start = Calendar.getInstance();
      Calendar end = Calendar.getInstance();
      end.add(Calendar.YEAR, 30);
      KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(mContext)
          .setAlias(KEYSTORE_KEY_ALIAS)
          .setSubject(new X500Principal("CN=" + KEYSTORE_KEY_ALIAS))
          .setSerialNumber(BigInteger.TEN)
          .setStartDate(start.getTime())
          .setEndDate(end.getTime())
          .build();
      KeyPairGenerator kpg = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA,
          KEY_STORE_ANDROID);
      kpg.initialize(spec);
      kpg.generateKeyPair();
    }
  }

  /**
   * Generate the AES key that will be used as secret key of the application to encrypt everything.
   *
   * @throws Exception exceptions.
   */
  private void generateAESKey() throws Exception {
    if (Logger.DEBUG) Log.d(TAG, "generateAESKey");
    String enryptedKeyB64 = mSecretKey.get();

    if (enryptedKeyB64 == null) {
      byte[] key = new byte[16];
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(key);
      byte[] encryptedKey = rsaEncrypt(key);
      enryptedKeyB64 = Base64.encodeToString(encryptedKey, Base64.DEFAULT);
      mSecretKey.put(enryptedKeyB64);
    }
  }

  /**
   * Encrypt bytes using the RSA key pair.
   *
   * @param secret the secret to be encrypted.
   * @return the encrypted value.
   * @throws Exception exceptions.
   */
  private byte[] rsaEncrypt(byte[] secret) throws Exception {
    KeyStore.PrivateKeyEntry privateKeyEntry = null;
    privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry
        (KEYSTORE_KEY_ALIAS, null);

    Cipher inputCipher = getCipherInstance();
    inputCipher.init(Cipher.ENCRYPT_MODE, privateKeyEntry.getCertificate().getPublicKey());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, inputCipher);
    cipherOutputStream.write(secret);
    cipherOutputStream.close();
    byte[] vals = outputStream.toByteArray();

    return vals;
  }

  /**
   * Decrypts the byte array with the rsa key pair.
   *
   * @param encrypted the encrypted value.
   * @return decrypted bytes.
   * @throws Exception exceptions.
   */
  private byte[] rsaDecrypt(byte[] encrypted) throws Exception {
    KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry
        (KEYSTORE_KEY_ALIAS, null);

    Cipher output = getCipherInstance();
    output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
    CipherInputStream cipherInputStream = new CipherInputStream(
        new ByteArrayInputStream(encrypted), output);
    ArrayList<Byte> values = new ArrayList<>();
    int nextByte;

    while ((nextByte = cipherInputStream.read()) != -1) {
      values.add((byte) nextByte);
    }

    byte[] bytes = new byte[values.size()];

    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = values.get(i).byteValue();
    }

    return bytes;
  }

  /**
   * Helper to get the Cipher instance, this depends on the version of Android.
   *
   * @return the Cipher instance.
   * @throws Exception exceptions.
   */
  private Cipher getCipherInstance() throws Exception {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      return Cipher.getInstance(RSA_MODE, "AndroidOpenSSL");
    } else {
      return Cipher.getInstance(RSA_MODE, "AndroidKeyStoreBCWorkaround");
    }
  }

  /**
   * Get decrypted secret key.
   *
   * @return decrypted secret key.
   * @throws Exception exceptions.
   */
  private Key getSecretKey() throws Exception {
    String enryptedKeyB64 = mSecretKey.get();
    if (enryptedKeyB64.length() > 0) {
      byte[] encryptedKey = Base64.decode(enryptedKeyB64, Base64.DEFAULT);
      byte[] key = rsaDecrypt(encryptedKey);
      return new SecretKeySpec(key, "AES");
    } else {
      return null;
    }
  }

  /**
   * Ecrypt any byte array.
   *
   * @param input the byte array to be encrypted.
   * @return encrypted value.
   * @throws Exception exceptions.
   */
  public String encrypt(byte[] input) throws Exception {
    Cipher cipher = Cipher.getInstance(AES_MODE, CIPHER_PROVIDER);
    cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
    byte[] encodedBytes = cipher.doFinal(input);
    return Base64.encodeToString(encodedBytes, Base64.DEFAULT);
  }

  /**
   * Decrypt encrypted bytes.
   *
   * @param encrypted the encrypted byte array.
   * @return decrypted byte array.
   * @throws Exception exceptions.
   */
  public byte[] decrypt(byte[] encrypted) throws Exception {
    Cipher cipher = Cipher.getInstance(AES_MODE, CIPHER_PROVIDER);
    cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
    byte[] encryptedBase64Decoded = Base64.decode(encrypted, Base64.DEFAULT);
    return cipher.doFinal(encryptedBase64Decoded);
  }
}
