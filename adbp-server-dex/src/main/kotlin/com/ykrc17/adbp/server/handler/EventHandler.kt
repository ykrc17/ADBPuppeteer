package com.ykrc17.adbp.server.handler

import com.ykrc17.adbp.entity.ADBEvent

abstract class EventHandler<E : ADBEvent> {

    abstract fun handle(event: E)
}