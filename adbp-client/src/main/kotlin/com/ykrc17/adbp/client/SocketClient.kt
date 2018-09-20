package com.ykrc17.adbp.client

import com.ykrc17.adbp.entity.Event
import java.io.InputStream
import java.io.ObjectOutputStream
import java.net.Socket

object SocketClient {

    fun newSocket(event: Event, callback: (InputStream) -> Unit) {
        val socket = Socket("127.0.0.1", 8000)
        val sin = socket.getInputStream()
        val sout = ObjectOutputStream(socket.getOutputStream())
        sout.writeObject(event)
        sout.flush()

        callback(sin)
        socket.close()
    }
}