package com.ykrc17.adbp.client.ui.toolbox

import com.ykrc17.adbp.client.SocketClient
import com.ykrc17.adbp.client.jbutton
import com.ykrc17.adbp.entity.ClipBoardPullEvent
import com.ykrc17.adbp.entity.ClipBoardPushEvent
import java.awt.BorderLayout
import java.io.ObjectInputStream
import javax.swing.*

class ClipboardFrame : JFrame {
    private val textArea = JTextArea()

    constructor(parent: JPanel) {
        setSize(400, 300)
        setLocationRelativeTo(parent)
        defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        isResizable = false

        layout = BorderLayout()
        title = "剪贴板"

        textArea.also {
            it.lineWrap = true
            add(it)
        }

        JPanel().also {
            it.layout = BoxLayout(it, BoxLayout.X_AXIS)
            it.add(Box.createHorizontalGlue())
            it.jbutton("Clear") { _ ->
                textArea.text = ""
            }
            it.jbutton("Push") { _ ->
                if (textArea.text.isNotEmpty()) {
                    SocketClient.send(ClipBoardPushEvent(textArea.text))
                }
            }
            it.jbutton("Pull") { _ ->
                SocketClient.call(ClipBoardPullEvent()) { result ->
                    val callback = ObjectInputStream(result).readObject()
                    if (callback is ClipBoardPullEvent.Callback) {
                        textArea.text = callback.text
                    }
                }
            }
            add(it, BorderLayout.SOUTH)
        }
    }
}
