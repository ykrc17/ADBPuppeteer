package com.ykrc17.adbp.client.ui.main

import com.ykrc17.adbp.client.input.AndroidKeyEvent
import com.ykrc17.adbp.client.input.KeyEventDispatcher
import com.ykrc17.adbp.client.jbutton
import com.ykrc17.adbp.client.ui.toolbox.ClipboardFrame
import javax.swing.BoxLayout
import javax.swing.JPanel

class ControlPanel : JPanel {
    private val clipboardFrame by lazy { ClipboardFrame(this) }

    constructor() : super() {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        jbutton("电源") { _ ->
            KeyEventDispatcher.sendADBKeyEvent(AndroidKeyEvent.KEYCODE_POWER)
        }

        jbutton(clipboardFrame.title) { _ ->
            clipboardFrame.isVisible = !clipboardFrame.isVisible
        }
    }
}
