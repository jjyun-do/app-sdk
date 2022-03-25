package com.samsung.healthcare.research.view.eligibility

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.model.eligibility.EligibilityOverviewContent
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.SdkCard
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EligibilityOverviewCards(
    modifier: Modifier = Modifier,
    contents: List<EligibilityOverviewContent>,
    contentPadding: PaddingValues = PaddingValues(horizontal = 50.dp),
    startScale: Float = 1f,
    startAlpha: Float = 1f,
) {
    HorizontalPager(
        count = contents.size,
        contentPadding = contentPadding,
        modifier = modifier.fillMaxSize()
    ) { page ->
        EligibilityOverviewCard(
            modifier = Modifier.graphicsLayer {
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                lerp(
                    start = startScale,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                alpha = lerp(
                    start = startAlpha,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
            content = contents[page],
        )
    }
}

@Composable
fun EligibilityOverviewCard(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    imageId: Int = R.drawable.card_sample_image,
    content: EligibilityOverviewContent,
) {
    SdkCard(
        modifier = modifier
            .size(
                width = 280.dp,
                height = 445.dp,
            )
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onClick() })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = content.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h6,
                        color = AppTheme.colors.textPrimaryAccent,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    content.subTitles.forEachIndexed() { index, subTitle ->
                        if (index < 3) {
                            Text(
                                text = "• $subTitle",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.body1,
                                color = AppTheme.colors.textHint,
                                modifier = Modifier.padding(horizontal = 30.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.MoreHoriz,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                contentDescription = "",
                            )
                            return@forEachIndexed
                        }
                    }
                }
            }
        }
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float =
    (1 - fraction) * start + fraction * stop

@Preview(showBackground = true)
@Composable
fun EligibilityCardPreview() {
    EligibilityOverviewCard(
        content = EligibilityOverviewContent(
            title = "Test Title",
            subTitles = listOf("One", "Two", "Three", "Four", "Five"),
        )
    )
}

@Preview(showBackground = true)
@Composable
fun EligibilityCardsPreview() {
    EligibilityOverviewCards(
        contents = listOf(
            EligibilityOverviewContent(
                title = "Medical Eligibilities",
                subTitles = listOf(
                    "Pre-existing condition(s)",
                    "Prescription(s)",
                    "Living in the United States"
                ),
            ),
            EligibilityOverviewContent(
                title = "Medical Eligibilities",
                subTitles = listOf(
                    "Pre-existing condition(s)",
                    "Prescription(s)",
                    "Living in the United States"
                ),
            ),
            EligibilityOverviewContent(
                title = "Medical Eligibilities",
                subTitles = listOf(
                    "Pre-existing condition(s)",
                    "Prescription(s)",
                    "Living in the United States"
                ),
            ),
        ),
        contentPadding = PaddingValues(horizontal = 60.dp),
        startScale = 0.85f,
        startAlpha = 0.5f,
    )
}
