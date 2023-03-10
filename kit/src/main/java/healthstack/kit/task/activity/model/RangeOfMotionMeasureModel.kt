package healthstack.kit.task.activity.model

import healthstack.kit.task.base.StepModel
import healthstack.kit.ui.TextType

class RangeOfMotionMeasureModel(
    id: String,
    title: String,
    val header: String = "Right Arm Circumduction",
    val body: List<String>? = listOf(
        "Place phone in your right hand.",
        "Straighten your right arm and move it in a full circle for 20 sec",
    ),
    val buttonText: String = "Continue",
    val textType: TextType = TextType.NUMBER,
    val timeSeconds: Long = 20,
) : StepModel(id, title, null)
