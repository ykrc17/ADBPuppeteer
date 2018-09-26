package com.ykrc17.adbp.client.ui.main

import com.ykrc17.adbp.client.input.KeyEventDispatcher
import java.awt.BorderLayout
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel

class MainFrame : JFrame("ADBPuppeteer") {
    private val keyListener = object : KeyListener {
        override fun keyTyped(e: KeyEvent) {
        }

        override fun keyPressed(e: KeyEvent) {
            println("keyPressed: ${e.keyCode}")
            KeyEventDispatcher.sendJvmKeyEvent(e)
        }

        override fun keyReleased(e: KeyEvent) {
        }
    }

    init {
        setSize(800, 600)
        isLocationByPlatform = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = false
        layout = BorderLayout()
        addKeyListener(keyListener)

        JPanel(BorderLayout()).also {
            it.add(ScreenPanel())
            it.add(NavigationPanel(), BorderLayout.SOUTH)
            add(it)
        }

        add(ControlPanel(), BorderLayout.EAST)

        pack()
    }
}