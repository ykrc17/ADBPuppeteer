package com.ykrc17.adbp.client

import com.ykrc17.adbp.entity.ADBEvent
import java.io.InputStream
import java.io.ObjectOutputStream
import java.net.Socket

object SocketClient {
    private val oos by lazy { ObjectOutputStream(createSocket().getOutputStream()) }

    fun send(event: ADBEvent) {
        oos.writeObject(event)
        oos.flush()
    }

    fun call(event: ADBEvent, callback: (InputStream) -> Unit) {
        val socket = createSocket()
        val sin = socket.getInputStream()
        val sout = ObjectOutputStream(socket.getOutputStream())
        sout.writeObject(event)
        sout.flush()

        callback(sin)
        socket.close()
    }

    private fun createSocket(): Socket {
        return Socket("127.0.0.1", 8000)
    }
}