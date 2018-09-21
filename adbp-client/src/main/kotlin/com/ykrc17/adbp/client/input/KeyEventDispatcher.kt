package com.ykrc17.adbp.client.input

import com.ykrc17.adbp.client.SocketClient
import com.ykrc17.adbp.entity.InputKeyEvent
import java.awt.event.KeyEvent

object KeyEventDispatcher {
    private val blackList = hashSetOf(KeyEvent.VK_SHIFT)
    private val keyCodeMap = hashMapOf<Int, Int>()

    init {
        for (i in KeyEvent.VK_0..KeyEvent.VK_9) {
            keyCodeMap[i] = i - (KeyEvent.VK_0 - AndroidKeyEvent.KEYCODE_0)
        }
        for (i in KeyEvent.VK_A..KeyEvent.VK_Z) {
            keyCodeMap[i] = i - (KeyEvent.VK_A - AndroidKeyEvent.KEYCODE_A)
        }
        for (i in KeyEvent.VK_NUMPAD0..KeyEvent.VK_NUMPAD9) {
            keyCodeMap[i] = i - (KeyEvent.VK_NUMPAD0 - AndroidKeyEvent.KEYCODE_0)
        }
        keyCodeMap[KeyEvent.VK_BACK_SPACE] = AndroidKeyEvent.KEYCODE_DEL
        keyCodeMap[KeyEvent.VK_ENTER] = AndroidKeyEvent.KEYCODE_ENTER
        keyCodeMap[KeyEvent.VK_SPACE] = AndroidKeyEvent.KEYCODE_SPACE

        keyCodeMap[KeyEvent.VK_LEFT] = AndroidKeyEvent.KEYCODE_DPAD_LEFT
        keyCodeMap[KeyEvent.VK_UP] = AndroidKeyEvent.KEYCODE_DPAD_UP
        keyCodeMap[KeyEvent.VK_RIGHT] = AndroidKeyEvent.KEYCODE_DPAD_RIGHT
        keyCodeMap[KeyEvent.VK_DOWN] = AndroidKeyEvent.KEYCODE_DPAD_DOWN

        keyCodeMap[KeyEvent.VK_PERIOD] = AndroidKeyEvent.KEYCODE_PERIOD
        keyCodeMap[KeyEvent.VK_SLASH] = AndroidKeyEvent.KEYCODE_SLASH

        keyCodeMap[KeyEvent.VK_DECIMAL] = AndroidKeyEvent.KEYCODE_PERIOD
    }

    fun down(event: KeyEvent) {
        handleEvent(AndroidKeyEvent.ACTION_DOWN, event)
    }

    private fun handleEvent(action: Int, event: KeyEvent) {
        if (blackList.contains(event.keyCode)) {
            return
        }
        var metaState = 0
        if (event.isShiftDown) {
            metaState += AndroidKeyEvent.META_SHIFT_ON
        }
        SocketClient.newSocket(InputKeyEvent(convertKeyCode(event.keyCode), metaState)) {}
    }

    private fun convertKeyCode(javaKeyCode: Int): Int {
        var keyCode = keyCodeMap[javaKeyCode]
        if (keyCode == null) {
            keyCode = javaKeyCode
            System.err.println("unknown key code: $javaKeyCode")
        }
        return keyCode
    }
}