package com.ykrc17.adbp.client

import com.ykrc17.adbp.client.adb.ADB
import com.ykrc17.adbp.client.ui.select.SelectDeviceFrame
import java.awt.EventQueue
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JPanel


fun main(args: Array<String>) {
    val devices = ADB.getDevices()
    EventQueue.invokeLater {
        SelectDeviceFrame(devices).isVisible = true
    }
//    if (devices.size == 1) {
//        EventQueue.invokeLater {
//            MainFrame().isVisible = true
//        }
//    }
}

fun JPanel.jbutton(text: String, listener: (ActionEvent) -> Unit) {
    val button = JButton(text)
    button.isFocusable = false
    button.addActionListener(listener)
    add(button)
}