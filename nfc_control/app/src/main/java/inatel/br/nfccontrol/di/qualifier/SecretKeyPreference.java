package inatel.br.nfccontrol.di.qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

/**
 * Interface used to keep sharedpreference secret key to encrypt objects.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface SecretKeyPreference {
}