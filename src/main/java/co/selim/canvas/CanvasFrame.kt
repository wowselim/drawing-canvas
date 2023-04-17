package co.selim.canvas

import java.awt.BorderLayout
import javax.swing.JFrame

class CanvasFrame(title: String) : JFrame(title) {
    init {
        layout = BorderLayout()
        val drawingCanvas = DrawingCanvas()
        add(Toolbar {
            drawingCanvas.brushSize = it
        }, BorderLayout.NORTH)
        add(drawingCanvas, BorderLayout.CENTER)
        add(Chat(), BorderLayout.EAST)
    }
}