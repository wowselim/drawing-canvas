package co.selim.canvas

import javax.swing.ButtonGroup
import javax.swing.JLabel
import javax.swing.JToggleButton
import javax.swing.JToolBar

class Toolbar(onBrushSizeChanged: (BrushSize) -> Unit) : JToolBar() {
    init {
        add(JLabel("Brush size"))
        val buttonGroup = ButtonGroup()
        BrushSize.values().forEachIndexed { i, brushSize ->
            val button = JToggleButton(brushSize.label)
            buttonGroup.add(button)
            button.addActionListener {
                onBrushSizeChanged(brushSize)
            }
            addSeparator()
            add(button)
            if (i == 0) button.isSelected = true
        }
    }
}