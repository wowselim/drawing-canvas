package co.selim.canvas

import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JComponent

class DrawingCanvas : JComponent() {
    val strokes = mapOf(
        BrushSize.SMALL to BasicStroke(BrushSize.SMALL.size.toFloat(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND),
        BrushSize.MEDIUM to BasicStroke(BrushSize.MEDIUM.size.toFloat(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND),
        BrushSize.LARGE to BasicStroke(BrushSize.LARGE.size.toFloat(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
    )
    var brushSize = BrushSize.SMALL
        set(value) {
            field = value
            graphics.stroke = strokes[value]
        }
    private val image by lazy { createVolatileImage(1280, 1280) }
    private val graphics by lazy {
        image.createGraphics()
            .also {
                it.stroke = strokes[brushSize]
                it.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            }
    }
    private var lastPoint: Point? = null

    init {
        val listener = object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                getCoordinatesOnCanvas(e).let { point ->
                    val x = point.x - brushSize.size / 2
                    val y = point.y - brushSize.size / 2
                    graphics.fillOval(x, y, brushSize.size, brushSize.size)
                    lastPoint = point
                }
                repaint()
            }

            override fun mouseDragged(e: MouseEvent) {
                getCoordinatesOnCanvas(e).let { point ->
                    lastPoint?.let { lastPoint ->
                        graphics.drawLine(lastPoint.x, lastPoint.y, point.x, point.y)
                    }
                    lastPoint = point
                }
                repaint()
            }
        }
        addMouseListener(listener)
        addMouseMotionListener(listener)
        border = BorderFactory.createLoweredBevelBorder()
        cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)
        preferredSize = Dimension(640, 480)
    }

    override fun paint(g: Graphics) {
        g.drawImage(image, 0, 0, width, height, null)
    }

    private fun getCoordinatesOnCanvas(e: MouseEvent): Point {
        return Point(
            (e.x.toDouble() / width * image.width).toInt(),
            (e.y.toDouble() / height * image.height).toInt()
        )
    }
}