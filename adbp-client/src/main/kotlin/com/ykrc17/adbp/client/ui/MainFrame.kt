package com.ykrc17.adbp.client.ui

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JSplitPane

class MainFrame : JFrame("TraceOn") {
    init {
        setSize(800, 600)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val imagePane = ImagePanel()
        val pane = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePane, JLabel("Hello World"))
        contentPane.add(pane)

        pack()
    }
}