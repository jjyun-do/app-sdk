package com.samsung.healthcare.research.view.eligibility

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.SdkCard

@Composable
fun EligibilityCard(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    imageId: Int = R.drawable.card_sample_image,
    cardTitle: String,
    cardSubTitles: List<String>,
) {
    SdkCard(
        modifier = modifier
            .size(
                width = 255.dp,
                height = 375.dp,
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
                    .height(240.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.align(alignment = Alignment.Center)
                ) {
                    Text(
                        text = cardTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h6,
                        color = AppTheme.colors.textPrimaryAccent,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    cardSubTitles.forEachIndexed() { index, subTitle ->
                        if (index < 3) {
                            Text(
                                text = "• $subTitle",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.body1,
                                color = AppTheme.colors.textHint,
                                modifier = Modifier.padding(horizontal = 22.dp)
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

@Preview(showBackground = true)
@Composable
fun EligibilityCardPreview() {
    EligibilityCard(
        cardTitle = "Test Title",
        cardSubTitles = listOf("One", "Two", "Three", "Four", "Five"),
    )
}
