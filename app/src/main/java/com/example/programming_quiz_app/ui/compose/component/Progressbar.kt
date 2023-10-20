import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DottedComponent(n: Int, y: Int) {
    Row {
        for (i in 0 until n) {
            val color = if (i < y) Color.Green else Color.White
            DottedCircle(color)
            if (i < n - 1) {
                // Apply horizontal spacing to all circles except the last one
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun DottedCircle(color: Color) {
    val circleSize = 12.dp

    Box(
        modifier = Modifier
            .size(circleSize)
            .clip(shape = CircleShape)
            .background(color)

    )
}
