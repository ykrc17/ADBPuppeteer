package com.ykrc17.adbp.server.handler

import android.os.SystemClock
import android.view.InputDevice
import android.view.KeyCharacterMap
import android.view.KeyEvent
import com.ykrc17.adbp.entity.ADBKeyEvent
import com.ykrc17.adbp.server.ServiceManagerWrapper

object KeyEventHandler : EventHandler<ADBKeyEvent>() {

    override fun handle(event: ADBKeyEvent) {
        println(event.keyCode)
        injectKeyEvent(KeyEvent.ACTION_DOWN, event.keyCode, event.metaState)
        injectKeyEvent(KeyEvent.ACTION_UP, event.keyCode, event.metaState)
    }

    private fun injectKeyEvent(action: Int, keyCode: Int, metaState: Int) {
        val time = SystemClock.uptimeMillis()
        val injectEvent = KeyEvent(time, time, action, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0, InputDevice.SOURCE_KEYBOARD)
        ServiceManagerWrapper.inputManager.injectInputEvent(injectEvent, 0)
    }
}