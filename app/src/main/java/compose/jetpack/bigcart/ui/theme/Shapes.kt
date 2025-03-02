package compose.jetpack.bigcart.ui.theme

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ArchShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            // Draw the path for the arch shape
            Path().apply {
                val radius = size.width / 2f // Radius for semicircle

                // Start from bottom-left
                moveTo(0f, size.height)

                // Line to start of the arch (top-left)
                lineTo(0f, radius)

                // Draw semicircular arch from left to right
                arcTo(
                    rect = Rect(0f, 0f, size.width, 0.75f* radius),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false
                )

                // Line to bottom-right
                lineTo(size.width, size.height)

                // Close the path
                close()
            }
        )
    }
}