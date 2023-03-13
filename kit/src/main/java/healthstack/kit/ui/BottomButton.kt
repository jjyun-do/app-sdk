package healthstack.kit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.kit.annotation.PreviewGenerated

@Composable
fun BottomSquareButton(
    text: String = "Dummy name",
    onClick: () -> Unit,
) {
    BottomButton(text, RoundedCornerShape(0), onClick)
}

@Composable
fun BottomRoundButton(
    text: String = "Dummy name",
    onClick: () -> Unit,
) {
    BottomButton(text, RoundedCornerShape(4.dp), onClick)
}

@Composable
private fun BottomButton(text: String, shape: RoundedCornerShape, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(horizontal = 20.dp)
    ) {
        KitButton(text = text, shape = shape) {
            onClick()
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun BottomSquareButtonPreview() {
    BottomSquareButton("hello", nothing)
}

@PreviewGenerated
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun BottomRoundButtonPreview() {
    BottomRoundButton("hello", nothing)
}

private val nothing: () -> Unit = { }
