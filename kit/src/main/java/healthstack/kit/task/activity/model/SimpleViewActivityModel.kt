package healthstack.kit.task.activity.model

import healthstack.kit.task.base.StepModel

abstract class SimpleViewActivityModel(
    id: String,
    title: String,
    val header: String,
    val body: String? = null,
    drawableId: Int? = null,
    val buttonText: String? = null, // If null, do not render bottom button
) : StepModel(id, title, drawableId)
