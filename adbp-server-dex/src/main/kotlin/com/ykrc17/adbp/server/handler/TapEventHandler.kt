package com.ykrc17.adbp.server.handler

import com.ykrc17.adbp.entity.InputTapEvent
import java.io.OutputStream

object TapEventHandler : EventHandler<InputTapEvent>() {
    override fun handle(event: InputTapEvent, out: OutputStream) {
        tapUsingShell(event)
    }

    private fun tapUsingShell(event: InputTapEvent) {
        Runtime.getRuntime().exec("input tap ${event.x} ${event.y}")
    }
}