package inatel.br.nfccontrol.service

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import inatel.br.nfccontrol.TccApplication
import inatel.br.nfccontrol.utils.NFCUtils

class CardService : HostApduService() {

  companion object {
    const val TAG = "Host Card Emulator"
    const val STATUS_FAILED = "6F00"
    const val CLA_NOT_SUPPORTED = "6E00"
    const val INS_NOT_SUPPORTED = "6D00"
    const val AID = "F0010203040506"
    const val SELECT_INS = "A4"
    const val DEFAULT_CLA = "00"
    const val MIN_APDU_LENGTH = 12
  }

  override fun onDeactivated(reason: Int) {
    Log.d(TAG, "Card - Deactivated: $reason")
  }

  override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {

    Log.d(TAG, "processCommandApdu: ${commandApdu.toString()}")

    if (TccApplication.prefs.canRegister) {
      if (commandApdu == null) {
        return NFCUtils.hexStringToByteArray(STATUS_FAILED)
      }

      val hexCommandApdu = NFCUtils.toHex(commandApdu)

      if (hexCommandApdu.substring(10, 24) == AID) {
        return byteArrayOf(0x1, 0x2, 0x3, 0x4, 0x5)
      } else {
        return NFCUtils.hexStringToByteArray(STATUS_FAILED)
      }
    } else {
      return NFCUtils.hexStringToByteArray(STATUS_FAILED)
    }
  }

}