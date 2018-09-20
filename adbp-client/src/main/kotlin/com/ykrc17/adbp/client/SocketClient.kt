package com.ykrc17.adbp.client

import java.io.InputStream
import java.net.Socket

object SocketClient {

    fun newSocket(callback: (InputStream) -> Unit) {
        val socket = Socket("127.0.0.1", 8000)
        val sin = socket.getInputStream()
        val sout = socket.getOutputStream()
        callback(sin)
        socket.close()
    }
}