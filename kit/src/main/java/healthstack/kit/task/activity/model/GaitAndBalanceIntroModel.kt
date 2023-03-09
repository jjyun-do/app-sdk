package healthstack.kit.task.activity.model

import healthstack.kit.R

class GaitAndBalanceIntroModel(
    id: String,
    title: String = "Gait & Balance",
    header: String = "Gait & Balance",
    body: String? = "1. Walk unassisted for 20 steps in a straight line.\n" +
        "2. Turn around and walk back to your starting point.\n" +
        "3. Stand still for 20 seconds.",
    drawableId: Int? = R.drawable.ic_activity_gait_and_balance,
    buttonText: String? = null, // If null, do not render bottom button
) :
    SimpleViewActivityModel(
        id, title, header, body, drawableId, buttonText
    )
