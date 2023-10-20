import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Timer(
    initialTime: Int,
    onFinish: () -> Unit
) {
    var currentTime by remember { mutableStateOf(initialTime)}
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current.density

    // Use LaunchedEffect to start the timer automatically
    LaunchedEffect(currentTime) {
        while (currentTime > 0) {
            delay(1000) // Delay for 1 second
            currentTime--
        }
        onFinish()
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = currentTime.toString(),
            onValueChange = {
                currentTime = it.toIntOrNull() ?: 0
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
    }

    Text(
        text = "Time Remains: $currentTime seconds",
        style = MaterialTheme.typography.body2,
        color = androidx.compose.ui.graphics.Color.Gray,
        modifier = Modifier.padding(top = 16.dp)
    )
}

