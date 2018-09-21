package com.ykrc17.adbp.server.handler

import com.ykrc17.adbp.entity.Event
import com.ykrc17.adbp.server.threadPool
import java.io.OutputStream
import java.net.Socket

abstract class EventHandler<E : Event> {
    fun dispatch(event: E, socket: Socket) {
        threadPool.execute {
            handle(event, socket.getOutputStream())
            socket.close()
        }
    }

    protected abstract fun handle(event: E, out: OutputStream)
}