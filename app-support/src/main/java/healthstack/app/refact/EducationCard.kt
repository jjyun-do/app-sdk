package healthstack.app.refact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.app.R
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.theme.AppTheme

@Composable
fun EducationCard(
    type: String,
    title: String,
    description: String,
    drawableId: Int,
    onClick: () -> Unit = { },
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(4.dp),
        elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = null,
                    modifier = Modifier.height(98.dp).width(116.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier.fillMaxWidth().height(98.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                EducationCardLayout(type, title, description)
            }
        }
    }
}

@Composable
fun EducationCardWithIcon(
    type: String,
    title: String,
    description: String,
    icon: ImageVector,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(98.dp).width(116.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(AppTheme.colors.primary.copy(0.3F))
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = AppTheme.colors.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier.fillMaxWidth().height(98.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                EducationCardLayout(type, title, description)
            }
        }
    }
}

@Composable
private fun EducationCardLayout(
    type: String,
    title: String,
    description: String,
) {
    Row {
        Column(Modifier.wrapContentHeight().fillMaxWidth()) {
            Text(
                text = type,
                style = AppTheme.typography.body3,
                color = AppTheme.colors.onBackground,
                modifier = Modifier.padding(top = 1.dp, bottom = 4.dp)
            )
            Text(
                text = title,
                style = AppTheme.typography.title3,
                color = AppTheme.colors.onBackground,
                maxLines = 3
            )
        }
    }
    Row {
        Text(
            text = description,
            style = AppTheme.typography.body3,
            color = AppTheme.colors.onBackground.copy(0.6F),
            modifier = Modifier.padding(bottom = 1.dp),
        )
    }
}

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun EducationCardPreview() =
    EducationCard(
        "Heart Health",
        "How to Take an ECG",
        "5:35 min",
        R.drawable.thum_1,
    )

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun EducationCardPreview2() =
    EducationCard(
        "Heart Health",
        "How to Prevent a Heart Stroke? Expert Tips and Tricks",
        "10 min read",
        R.drawable.thum_3,
    )

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun EducationCardPreview3() =
    EducationCard(
        "Heart Health",
        "Heart Stroke Prevention",
        "10 min read",
        R.drawable.thum_2,
    )

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun EducationCardPreview4() =
    EducationCardWithIcon(
        "Heart Health",
        "Heart Stroke Prevention",
        "10 min read",
        Icons.Default.PictureAsPdf
    )
