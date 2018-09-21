package com.ykrc17.adbp.server.handler

import com.ykrc17.adbp.entity.Event
import java.net.Socket

interface EventHandler<E : Event> {
    fun handle(event: E, socket: Socket)
}