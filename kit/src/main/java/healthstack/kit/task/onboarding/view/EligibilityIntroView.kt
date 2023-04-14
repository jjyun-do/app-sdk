package healthstack.kit.task.onboarding.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import healthstack.kit.R
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.base.View
import healthstack.kit.task.onboarding.model.EligibilityIntroModel
import healthstack.kit.task.onboarding.model.EligibilityIntroModel.ViewType.Card
import healthstack.kit.task.survey.question.SubStepHolder
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomRoundButton
import healthstack.kit.ui.SdkCard
import healthstack.kit.ui.TopBar
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class EligibilityIntroView : View<EligibilityIntroModel>() {
    @Composable
    override fun Render(
        model: EligibilityIntroModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        val scrollState = rememberScrollState()

        Scaffold(
            topBar = {
                TopBar(title = model.title) {
                    callbackCollection.prev()
                }
            },
            bottomBar = {
                BottomRoundButton(text = LocalContext.current.getString(R.string.eligibility_intro_button_text)) {
                    callbackCollection.next()
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
                    .background(AppTheme.colors.background)
                    .verticalScroll(scrollState)
            ) {
                when (model.viewType) {
                    EligibilityIntroModel.ViewType.Paragraph -> EligibilityIntroParagraphView(model)
                    EligibilityIntroModel.ViewType.Card -> EligibilityIntroCardView(model)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(AppTheme.colors.background)
                        .fillMaxHeight(),
                )
            }
        }
    }

    @Composable
    private fun EligibilityIntroParagraphView(model: EligibilityIntroModel) {
        model.drawableId?.let { drawableId ->
            Image(
                painter = painterResource(drawableId),
                contentDescription = "Eligibility Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                model.description,
                color = AppTheme.colors.onSurface,
                modifier = Modifier
                    .padding(24.dp),
                style = AppTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(12.dp))
            ConditionForm(model)
        }
    }

    @Composable
    private fun ConditionForm(model: EligibilityIntroModel) {
        model.conditions.forEach { (title, constraints) ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                title,
                style = AppTheme.typography.headline3,
                color = AppTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                constraints.forEach { constraint ->
                    Text(
                        "- $constraint",
                        color = AppTheme.colors.onSurface,
                        style = AppTheme.typography.subtitle2
                    )
                }
            }
        }
    }

    @Composable
    private fun EligibilityIntroCardView(model: EligibilityIntroModel) {
        OverviewTab(
            modifier = Modifier,
            conditions = model.conditions,
        )

        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                model.description,
                color = AppTheme.colors.onSurface,
                style = AppTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun OverviewTab(
        modifier: Modifier = Modifier,
        unselectedTabIcon: Int = R.drawable.tab_icon_unselected,
        selectedTabIcon: Int = R.drawable.tab_icon_selected,
        conditions: List<EligibilityIntroModel.EligibilityCondition>,
        contentPadding: PaddingValues = PaddingValues(horizontal = 50.dp),
        startScale: Float = 1f,
        startAlpha: Float = 1f,
        backgroundColor: Color = AppTheme.colors.background,
    ) {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = modifier,
        ) {
            EligibilityOverviewCards(
                pagerState = pagerState,
                contents = conditions,
                contentPadding = contentPadding,
                startScale = startScale,
                startAlpha = startAlpha,
                backgroundColor = backgroundColor,
            )
            Spacer(Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .height(10.dp)
                    .wrapContentWidth()
                    .background(AppTheme.colors.background)
                    .align(Alignment.CenterHorizontally),
            ) {
                conditions.forEachIndexed { index, _ ->
                    Surface(
                        modifier = Modifier.width(20.dp)
                            .background(AppTheme.colors.background),
                    ) {
                        val selected = pagerState.currentPage == index
                        Tab(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(AppTheme.colors.background),
                            selected = selected,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(
                                        page = index,
                                    )
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        id =
                                        if (selected) {
                                            selectedTabIcon
                                        } else {
                                            unselectedTabIcon
                                        }
                                    ),
                                    modifier = Modifier.width(8.dp).height(8.dp)
                                        .background(AppTheme.colors.background),
                                    contentDescription = null,
                                    tint = AppTheme.colors.primary
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun EligibilityOverviewCards(
        modifier: Modifier = Modifier,
        pagerState: PagerState = rememberPagerState(),
        contents: List<EligibilityIntroModel.EligibilityCondition>,
        contentPadding: PaddingValues = PaddingValues(horizontal = 50.dp),
        startScale: Float = 1f,
        startAlpha: Float = 1f,
        backgroundColor: Color = AppTheme.colors.background,
    ) {
        HorizontalPager(
            count = contents.size,
            state = pagerState,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxWidth()
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
                backgroundColor = backgroundColor,
            )
        }
    }

    @Composable
    fun EligibilityOverviewCard(
        onClick: () -> Unit = {},
        modifier: Modifier = Modifier,
        imageId: Int = R.drawable.card_sample_image_alpha,
        content: EligibilityIntroModel.EligibilityCondition,
        backgroundColor: Color = AppTheme.colors.background,
    ) {
        SdkCard(
            modifier = modifier
                .size(
                    width = 280.dp,
                    height = 430.dp,
                )
                .padding(horizontal = 10.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = 1.dp,
            color = backgroundColor,
        ) {
            Column(
                modifier = Modifier
                    .clickable(onClick = { onClick() })
                    .background(backgroundColor)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(AppTheme.colors.background),
                ) {
                    Image(
                        painter = painterResource(imageId),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = content.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = AppTheme.typography.headline3,
                            color = AppTheme.colors.primary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        content.constraints.forEachIndexed { index, subTitle ->
                            if (index < 3) {
                                Text(
                                    text = "• $subTitle",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = AppTheme.typography.subtitle2,
                                    color = AppTheme.colors.onSurface,
                                    modifier = Modifier.padding(horizontal = 20.dp)
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
}

@PreviewGenerated
@Preview(showBackground = true, device = Devices.NEXUS_6)
@Composable
fun EligibilityIntroViewPreview() {
    val eligibilityIntroView = EligibilityIntroView()
    val eligibilitySections: List<EligibilityIntroModel.EligibilityCondition> = listOf(
        EligibilityIntroModel.EligibilityCondition(
            "Medical eligibility",
            listOf("Pre-existing condition(s)", "Prescription(s)", "Living in the United States")
        ),
        EligibilityIntroModel.EligibilityCondition(
            "Basic Profile",
            listOf("Age", "Geographical location", "Devices")
        ),
    )

    return eligibilityIntroView.Render(
        EligibilityIntroModel(
            id = "eligibility",
            title = "Eligibility",
            description = "To begin with, we will ask a few questions " +
                "to make sure that you are eligible to join this study.",
            conditions = eligibilitySections,
            viewType = Card
        ),
        CallbackCollection(),
        null
    )
}
