package ai.akun.nukasdk.chatbot.presentation.shared

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

@Suppress("DEPRECATION")
class ConnectivityReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        connectivityReceiverListener?.onNetworkConnectionChanged(ConnectivityVerifier.isConnectedOrConnecting(context!!))
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}