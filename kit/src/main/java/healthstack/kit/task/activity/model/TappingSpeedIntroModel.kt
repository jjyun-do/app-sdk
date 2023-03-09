package healthstack.kit.task.activity.model

import healthstack.kit.task.base.StepModel

class TappingSpeedIntroModel(
    id: String,
    title: String,
    drawableId: Int? = null,
    val rightHand: Boolean = true,
) : StepModel(id, title, drawableId) {
    val handType: String = when (rightHand) {
        true -> "right"
        else -> "left"
    }
}
