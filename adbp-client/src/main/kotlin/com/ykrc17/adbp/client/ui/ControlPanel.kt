package com.ykrc17.adbp.client.ui

import com.ykrc17.adbp.client.input.AndroidKeyEvent
import com.ykrc17.adbp.client.input.KeyEventDispatcher
import com.ykrc17.adbp.client.jbutton
import javax.swing.BoxLayout
import javax.swing.JPanel

class ControlPanel : JPanel {
    constructor() : super() {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        jbutton("电源") { _ ->
            KeyEventDispatcher.sendADBKeyEvent(AndroidKeyEvent.KEYCODE_POWER)
        }
    }
}
