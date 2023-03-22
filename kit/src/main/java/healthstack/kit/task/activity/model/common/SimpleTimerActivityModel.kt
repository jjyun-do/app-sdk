package healthstack.kit.task.activity.model.common

import healthstack.kit.task.base.StepModel
import healthstack.kit.ui.TextType
import healthstack.kit.ui.TextType.PARAGRAPH
import healthstack.kit.ui.util.InteractionType
import healthstack.kit.ui.util.InteractionType.NOTHING

abstract class SimpleTimerActivityModel(
    id: String,
    title: String,
    val header: String,
    val body: List<String>? = null,
    val timeSeconds: Int = 10,
    val textType: TextType = PARAGRAPH,
    val interactionType: InteractionType = NOTHING,
    val autoFlip: Boolean = false,
) : StepModel(id, title, null)
