package healthstack.kit.task.activity.model

import healthstack.kit.task.activity.model.common.SimpleTimerActivityModel
import healthstack.kit.ui.TextType
import healthstack.kit.ui.TextType.PARAGRAPH

class GaitAndBalanceBMeasureModel(
    id: String,
    title: String = "Gait & Balance",
    header: String,
    body: String? = null,
    timeSeconds: Int,
    textType: TextType = PARAGRAPH,
) :
    SimpleTimerActivityModel(
        id, title, header, body?.split("\n"), timeSeconds, textType
    )
