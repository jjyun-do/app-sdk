package healthstack.kit.task.activity.model

import healthstack.kit.R

class GaitAndBalanceResultModel(
    id: String,
    title: String = "Gait & Balance",
    header: String = "Great Job!",
    body: String? = "Your task was successfully completed.",
    drawableId: Int? = R.drawable.ic_activity_result,
    buttonText: String? = "Back to Home", // If null, do not render bottom button
) :
    SimpleViewActivityModel(
        id, title, header, body?.split("\n"), drawableId, buttonText
    )
