package healthstack.kit.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.theme.AppTheme

data class BottomNavItem(val title: String, val icon: ImageVector, val onClick: () -> Unit)

@Composable
fun BottomBarNavigation(
    items: List<BottomNavItem>,
) {
    val curItem = remember { mutableStateOf(items.first().title) }

    BottomNavigation(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        backgroundColor = AppTheme.colors.background,
    ) {
        items.forEach {
            BottomNavigationItem(
                selected = (curItem.value == it.title),
                onClick = {
                    curItem.value = it.title
                    it.onClick()
                },
                selectedContentColor = AppTheme.colors.primary,
                unselectedContentColor = AppTheme.colors.onBackground.copy(0.6F),
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = "${it.title}_icon",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = it.title,
                        style = AppTheme.typography.body3,
                    )
                }
            )
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun BottomBarNavigationPreview() =
    BottomBarNavigation(
        listOf(
            BottomNavItem("Home", Icons.Default.Home) {},
            BottomNavItem("Insights", Icons.Default.InsertChart) {},
            BottomNavItem("Education", Icons.Default.MenuBook) {}
        )
    )
