package com.ykrc17.adbp.client.ui

import com.ykrc17.adbp.client.input.AndroidKeyEvent
import com.ykrc17.adbp.client.input.KeyEventDispatcher
import com.ykrc17.adbp.client.jbutton
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JPanel

class NavigationPanel : JPanel {
    constructor() {
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        jbutton("◁") { _ ->
            KeyEventDispatcher.sendADBKeyEvent(AndroidKeyEvent.KEYCODE_BACK)
        }
        add(Box.createHorizontalGlue())
        jbutton("○") { _ ->
            KeyEventDispatcher.sendADBKeyEvent(AndroidKeyEvent.KEYCODE_HOME)
        }
        add(Box.createHorizontalGlue())
        jbutton("□") { _ ->
            KeyEventDispatcher.sendADBKeyEvent(AndroidKeyEvent.KEYCODE_APP_SWITCH)
        }
    }
}
