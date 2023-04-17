package co.selim.canvas

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

class Chat : JComponent() {
    init {
        layout = BorderLayout()
        val inputField = JTextField()
        val chatLog = JTextArea()
        val chatLogScrollPane = JScrollPane(chatLog)
        chatLog.isEditable = false
        add(chatLogScrollPane, BorderLayout.CENTER)
        add(inputField, BorderLayout.SOUTH)
        preferredSize = Dimension(320, -1)
        border = BorderFactory.createLoweredBevelBorder()
        inputField.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ENTER) {
                    if (inputField.text.isNotBlank()) {
                        chatLog.append("${inputField.text}\n")
                    }
                    inputField.text = ""
                }
            }
        })
    }
}