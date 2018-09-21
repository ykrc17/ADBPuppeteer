package com.ykrc17.adbp.client.input

import com.ykrc17.adbp.client.SocketClient
import com.ykrc17.adbp.entity.InputKeyEvent
import java.awt.event.KeyEvent

object KeyEventDispatcher {
    private val keyCodeMap = hashMapOf<Int, Int>()

    init {
        for (i in KeyEvent.VK_A..KeyEvent.VK_Z) {
            keyCodeMap[i] = i - (KeyEvent.VK_A - AndroidKeyEvent.KEYCODE_A)
        }
        for (i in KeyEvent.VK_0..KeyEvent.VK_9) {
            keyCodeMap[i] = i - (KeyEvent.VK_0 - AndroidKeyEvent.KEYCODE_0)
        }
        keyCodeMap[KeyEvent.VK_BACK_SPACE] = AndroidKeyEvent.KEYCODE_DEL
        keyCodeMap[KeyEvent.VK_ENTER] = AndroidKeyEvent.KEYCODE_ENTER
        keyCodeMap[KeyEvent.VK_SPACE] = AndroidKeyEvent.KEYCODE_SPACE
    }

    fun down(keyCode: Int) {
        SocketClient.newSocket(InputKeyEvent(convertKeyCode(keyCode))) {}
    }

    private fun convertKeyCode(javaKeyCode: Int): Int {
        var keyCode = keyCodeMap[javaKeyCode]
        if (keyCode == null) {
            keyCode = javaKeyCode
            println("unknown key code: $javaKeyCode")
        }
        return keyCode
    }
}