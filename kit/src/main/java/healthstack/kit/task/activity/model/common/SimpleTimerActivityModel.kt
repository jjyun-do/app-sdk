package healthstack.kit.task.activity.model.common

import healthstack.kit.task.base.StepModel
import healthstack.kit.ui.TextType
import healthstack.kit.ui.TextType.PARAGRAPH

abstract class SimpleTimerActivityModel(
    id: String,
    title: String,
    val header: String,
    val body: List<String>? = null,
    val timeSeconds: Int = 10,
    val textType: TextType = PARAGRAPH,
) : StepModel(id, title, null)
