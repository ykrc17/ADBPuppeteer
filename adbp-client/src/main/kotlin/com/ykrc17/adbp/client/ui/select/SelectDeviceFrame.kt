package com.ykrc17.adbp.client.ui.select

import com.ykrc17.adbp.client.adb.ADB
import com.ykrc17.adbp.client.entity.DeviceEntity
import com.ykrc17.adbp.client.jbutton
import com.ykrc17.adbp.client.ui.main.MainFrame
import java.awt.BorderLayout
import javax.swing.*

class SelectDeviceFrame(var devices: List<DeviceEntity>) : JFrame() {
    private val list = JList<String>()
    private val listModel = DefaultListModel<String>()

    init {
        setSize(320, 480)
        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        title = "请选择设备"
        layout = BorderLayout()

        devices.forEach { listModel.addElement(it.model) }

        list.also {
            it.selectionMode = ListSelectionModel.SINGLE_SELECTION
            it.model = listModel
            add(it)
        }

        JPanel().also {
            it.layout = BoxLayout(it, BoxLayout.X_AXIS)
            it.add(Box.createHorizontalGlue())
            it.jbutton("刷新") {
                devices = ADB.getDevices()
                listModel.removeAllElements()
                devices.forEach { listModel.addElement(it.model) }
            }
            it.jbutton("取消") { _ ->
                dispose()
            }
            it.jbutton("确认") { _ ->
                val index = list.selectedIndex
                if (index >= 0) {
                    ADB.pushServerDex(devices[index].serial)
                    MainFrame().isVisible = true
                    dispose()
                } else {
                    JOptionPane.showMessageDialog(this, "请先选择设备", "", JOptionPane.PLAIN_MESSAGE)
                }
            }
            add(it, BorderLayout.SOUTH)
        }
    }
}
