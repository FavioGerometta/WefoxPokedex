package ar.com.favio.wefoxpokedex.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object Utilities{
    suspend fun hasInternetConnection() : Boolean{
        val result = withContext(Dispatchers.Default) {
            try {
                // Connect to Google DNS to check for connection
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)

                socket.connect(socketAddress, timeoutMs)
                socket.close()
                true
            } catch (e: IOException) {
                false
            }
        }
        return result
    }





}