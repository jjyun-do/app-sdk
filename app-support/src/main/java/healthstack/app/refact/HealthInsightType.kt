package healthstack.app.refact

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import healthstack.app.R.drawable
import healthstack.kit.ui.InsightCardWithProgress
import healthstack.kit.ui.InsightUnit
import healthstack.kit.ui.ThinInsightCard

enum class HealthInsightType(
    val title: String,
    val today: @Composable () -> Unit,
    val week: @Composable () -> Unit,
    val all: @Composable () -> Unit,
) {

    STEP_COUNT(
        "Step Count",
        {
            Column(Modifier.fillMaxWidth().wrapContentSize()) {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.step_count_today),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                InsightCardWithProgress("Target Goal", 1521, 6000, "steps")
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("Stairs", InsightUnit("16", "floors"))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column(Modifier.fillMaxWidth().wrapContentSize()) {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.step_count_week),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                InsightCardWithProgress("Target Goal", 7, 7, "days")
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("Stairs", InsightUnit("32", "floors"))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column(Modifier.fillMaxWidth().wrapContentSize()) {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.step_count_all),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                InsightCardWithProgress("Target Goal", 7, 7, "days")
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("Stairs", InsightUnit("75", "floors"))
                Spacer(Modifier.height(5.dp))
            }
        },
    ),
    BLOOD_PRESSURE(
        "Blood Pressure",
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.blood_pressure_today),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.blood_pressure_week),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.blood_pressure_all),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        },
    ),
    HEART_RATE(
        "Heart Rate",
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.heart_rate_today),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            ThinInsightCard("74 bpm", InsightUnit("Daily Average", ""))
            Spacer(Modifier.height(16.dp))
            ThinInsightCard("64 - 78 bpm", InsightUnit("Average resting range", ""))
            Spacer(Modifier.height(5.dp))
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.heart_rate_week),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("60 - 82 bpm", InsightUnit("Average resting range", ""))
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("107 - 115 bpm", InsightUnit("Peak zones", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.heart_rate_all),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("62 - 86 bpm", InsightUnit("Average resting range", ""))
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("107 - 115 bpm", InsightUnit("Peak zones", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
    ),
    RESPIRATORY_RATE(
        "Respiratory Rate",
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.respiratory_today),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("11 - 15 bpm", InsightUnit("Average range", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.respiratory_week),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("12 - 16 bpm", InsightUnit("Average range", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.respiratory_all),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("12 - 16 bpm", InsightUnit("Average range", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
    ),
    SLEEP(
        "Sleep",
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.sleep_today),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("8 hours 45 minutes", InsightUnit("Time in bed", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.sleep_week),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("8 hours 20 minutes", InsightUnit("Average time in bed", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.sleep_all),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(16.dp))
                ThinInsightCard("8 hours 55 minutes", InsightUnit("Average time in bed", ""))
                Spacer(Modifier.height(5.dp))
            }
        },
    ),
    STRESS_LEVEL(
        "Stress Level",
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.stress_today),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.stress_week),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        },
        {
            Column {
                Card(
                    Modifier.fillMaxWidth(),
                    elevation = 1.dp
                ) {
                    Image(
                        painter = painterResource(drawable.stress_all),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        },
    )
}
