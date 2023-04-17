package co.selim.canvas

import javax.swing.JFrame

fun main() {
    CanvasFrame("Canvas")
        .also {
            it.pack()
            it.setLocationRelativeTo(null)
            it.isVisible = true
            it.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        }
}