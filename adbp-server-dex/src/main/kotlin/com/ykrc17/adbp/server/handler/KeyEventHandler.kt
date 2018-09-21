package com.ykrc17.adbp.server.handler

import android.os.SystemClock
import android.view.InputDevice
import android.view.KeyCharacterMap
import android.view.KeyEvent
import com.ykrc17.adbp.entity.InputKeyEvent
import com.ykrc17.adbp.server.ServiceManagerWrapper
import java.net.Socket

object KeyEventHandler : EventHandler<InputKeyEvent> {
    override fun handle(event: InputKeyEvent, socket: Socket) {
        println(event.keyCode)
        injectKeyEvent(KeyEvent.ACTION_DOWN, event.keyCode)
        injectKeyEvent(KeyEvent.ACTION_UP, event.keyCode)
        //        injectEvent = KeyEvent(KeyEvent.ACTION_UP, event.keyCode)
//        ServiceManagerWrapper.getInputManager().injectInputEvent(injectEvent, 0)
    }

    private fun injectKeyEvent(action: Int, keyCode: Int) {
        val time = SystemClock.uptimeMillis()
        val injectEvent = KeyEvent(time, time, action, keyCode, 0, 0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0, InputDevice.SOURCE_KEYBOARD)
        ServiceManagerWrapper.getInputManager().injectInputEvent(injectEvent, 0)

    }
}