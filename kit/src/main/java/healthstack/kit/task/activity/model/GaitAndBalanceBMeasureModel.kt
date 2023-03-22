package healthstack.kit.task.activity.model

import healthstack.kit.task.activity.model.common.SimpleTimerActivityModel
import healthstack.kit.ui.TextType
import healthstack.kit.ui.TextType.PARAGRAPH
import healthstack.kit.ui.util.InteractionType
import healthstack.kit.ui.util.InteractionType.NOTHING

class GaitAndBalanceBMeasureModel(
    id: String,
    title: String = "Gait & Balance",
    header: String,
    body: List<String>? = null,
    timeSeconds: Int,
    textType: TextType = PARAGRAPH,
    interactionType: InteractionType = NOTHING,
    autoFlip: Boolean = true,
) :
    SimpleTimerActivityModel(
        id, title, header, body, timeSeconds, textType, interactionType, autoFlip
    )
