package inatel.br.nfccontrol.service

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import inatel.br.nfccontrol.utils.NFCUtils

class CardService : HostApduService() {
  
  companion object {
    const val TAG = "Host Card Emulator"
    const val STATUS_SUCCESS = "9000"
    const val STATUS_FAILED = "6F00"
    const val CLA_NOT_SUPPORTED = "6E00"
    const val INS_NOT_SUPPORTED = "6D00"
    const val AID = "A0000002471001"
    const val SELECT_INS = "A4"
    const val DEFAULT_CLA = "00"
    const val MIN_APDU_LENGTH = 12
  }  
  
  override fun onDeactivated(reason: Int) {
    Log.d(TAG, "Deactivated: $reason")
  }
  
  override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
    if (commandApdu == null) {
      return NFCUtils.hexStringToByteArray(STATUS_FAILED)
    }
  
    val hexCommandApdu = NFCUtils.toHex(commandApdu)
    if (hexCommandApdu.length < MIN_APDU_LENGTH) {
      return NFCUtils.hexStringToByteArray(STATUS_FAILED)
    }
  
    if (hexCommandApdu.substring(0, 2) != DEFAULT_CLA) {
      return NFCUtils.hexStringToByteArray(CLA_NOT_SUPPORTED)
    }
  
    if (hexCommandApdu.substring(2, 4) != SELECT_INS) {
      return NFCUtils.hexStringToByteArray(INS_NOT_SUPPORTED)
    }
  
    if (hexCommandApdu.substring(10, 24) == AID)  {
      return NFCUtils.hexStringToByteArray(STATUS_SUCCESS)
    } else {
      return NFCUtils.hexStringToByteArray(STATUS_FAILED)
    }
  }
  
}