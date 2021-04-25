package com.example.androidassignment_saadullah.utills
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast


/**
 * extension for network connectivity
 */
fun Context.isOnline(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
    }
    return false
}

/**
 * extension for displaying toast
 */
fun Context.showToast( string: String)
{
    if(string.isNotEmpty())
    {
        Toast.makeText(this,string, Toast.LENGTH_LONG).show()
    }

}