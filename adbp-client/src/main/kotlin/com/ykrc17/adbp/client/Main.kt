package com.ykrc17.adbp.client

import com.ykrc17.adbp.client.ui.MainFrame
import java.awt.EventQueue
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel


fun main(args: Array<String>) {
    EventQueue.invokeLater {
        MainFrame().isVisible = true
    }
}

fun JPanel.jbutton(text: String, listener: (ActionEvent) -> Unit) {
    val button = JButton(text)
    button.isFocusable = false
    button.addActionListener(listener)
    add(button)
}