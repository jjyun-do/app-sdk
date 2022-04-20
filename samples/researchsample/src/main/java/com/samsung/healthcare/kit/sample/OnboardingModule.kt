package com.samsung.healthcare.kit.sample

import android.content.Context
import com.samsung.healthcare.kit.model.EligibilityCheckerModel
import com.samsung.healthcare.kit.model.EligibilityIntroModel
import com.samsung.healthcare.kit.model.EligibilityResultModel
import com.samsung.healthcare.kit.model.ImageArticleModel
import com.samsung.healthcare.kit.model.IntroModel
import com.samsung.healthcare.kit.model.question.ChoiceQuestionModel
import com.samsung.healthcare.kit.step.EligibilityCheckerStep
import com.samsung.healthcare.kit.step.EligibilityIntroStep
import com.samsung.healthcare.kit.step.EligibilityResultStep
import com.samsung.healthcare.kit.step.IntroStep
import com.samsung.healthcare.kit.step.sub.QuestionSubStep
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.task.OnboardingTask
import com.samsung.healthcare.kit.theme.AppColors
import com.samsung.healthcare.kit.theme.darkColors
import com.samsung.healthcare.kit.view.EligibilityCheckerView
import com.samsung.healthcare.kit.view.EligibilityIntroView
import com.samsung.healthcare.kit.view.EligibilityResultView
import com.samsung.healthcare.kit.view.IntroView
import com.samsung.healthcare.kit.view.component.ChoiceQuestionComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Singleton
    @Provides
    fun providesAppColors(): AppColors = darkColors()

    @Singleton
    @Provides
    fun providesIntroStep(@ApplicationContext context: Context): IntroStep =
        IntroStep(
            "intro-step",
            "intro-step",
            intro(context),
            IntroView(),
        )

    @Singleton
    @Provides
    fun providesEligibilityIntroStep(@ApplicationContext context: Context): EligibilityIntroStep =
        EligibilityIntroStep(
            "eligibility-intro-step",
            "eligibility-intro-step",
            eligibilityIntro(context),
            EligibilityIntroView(),
        )

    @Singleton
    @Provides
    fun providesEligibilityCheckerStep(@ApplicationContext context: Context): EligibilityCheckerStep =
        EligibilityCheckerStep(
            "eligibility-checker-step",
            "eligibility-checker-step",
            eligibilityChecker(context),
            EligibilityCheckerView(),
            questionnaireHolder
        )

    @Singleton
    @Provides
    fun providesOnboardingTask(
        introStep: IntroStep,
        eligibilityIntroStep: EligibilityIntroStep,
        eligibilityCheckerStep: EligibilityCheckerStep,
        eligibilityResultStep: EligibilityResultStep,
    ): OnboardingTask =
        OnboardingTask(
            "81b36273-42c0-4fdc-a2d2-7ffff2cda61a",
            "Sample-App-On-Boarding",
            "Introduce the project and determine whether the user is able to participate in this project or not.",
            {},
            introStep,
            eligibilityIntroStep,
            eligibilityCheckerStep,
            eligibilityResultStep
        )

    @Singleton
    @Provides
    fun providesEligibilityResultStep(@ApplicationContext context: Context): EligibilityResultStep =
        EligibilityResultStep(
            "eligibility-result-step",
            "eligibility-result-step",
            eligibilityResult(context),
            EligibilityResultView(),
        )

    private val summaries: List<Pair<Int, String>> = listOf(
        R.drawable.ic_watch to "Wear your watch",
        R.drawable.ic_clock to "10 min a day",
        R.drawable.ic_alarm to "2 surveys a week"
    )

    private fun intro(@ApplicationContext context: Context) = IntroModel(
        id = "IntroModel",
        title = "IntroTitle",
        R.drawable.ic_sample_icon,
        summaries = summaries,
        descriptions = listOf(
            "Description" to context.getString(R.string.lorem_ipsum),
            "Description2" to context.getString(R.string.lorem_ipsum)
        )
    )

    private fun eligibilityIntro(@ApplicationContext context: Context) = EligibilityIntroModel(
        id = "EligibilityIntroModel",
        title = "EligibilityIntroTitle",
        description = context.getString(R.string.lorem_ipsum_short),
        conditions = eligibilitySections(),
        drawableId = R.drawable.sample_image1,
        viewType = EligibilityIntroModel.ViewType.Card
    )

    private fun eligibilityChecker(@ApplicationContext context: Context) = EligibilityCheckerModel(
        id = "EligibilityCheckerModel",
        title = "EligibilityCheckerTitle",
        drawableId = R.drawable.sample_image1,
    )

    private fun eligibilityResult(@ApplicationContext context: Context) = EligibilityResultModel(
        id = "EligibilityResultModel",
        title = "EligibilityResultTitle",
        drawableId = R.drawable.sample_image1,

        successModel = successMessage,
        failModel = failMessage,
    )

    private val questionMiniSteps = listOf(
        QuestionSubStep(
            "question1_id",
            "question1_name",
            ChoiceQuestionModel(
                "1",
                "Do you have any existing cardiac conditions?",
                "Examples of cardiac conditions include abnormal heart rhythms, or arrhythmias",
                candidates = listOf("Yes", "No"),
                answer = "Yes"
            ),
            ChoiceQuestionComponent(),
        ),
        QuestionSubStep(
            "question2_id",
            "question2_name",
            ChoiceQuestionModel(
                "2",
                "Do you currently own a wearable device?",
                "Examples of wearable devices include Samsung Galaxy Watch 4, Fitbit, OuraRing, etc.",
                candidates = listOf("Yes", "No"),
                answer = "Yes"
            ),
            ChoiceQuestionComponent(),
        ),
        QuestionSubStep(
            "question3_id",
            "question3_name",
            ChoiceQuestionModel(
                "3",
                "test page?",
                "test page",
                candidates = listOf("Yes", "No"),
                answer = "Yes"
            ),
            ChoiceQuestionComponent(),
        )
    )

    private val questionnaireHolder = SubStepHolder(
        "substep_holder_id",
        "substep_holder_name",
        subSteps = questionMiniSteps
    )

    private fun eligibilitySections(): List<EligibilityIntroModel.EligibilityCondition> = listOf(
        EligibilityIntroModel.EligibilityCondition(
            "Medical eligibility",
            listOf("Condition(s)", "Prescription(s)", "Living in Europe")
        ),
        EligibilityIntroModel.EligibilityCondition(
            "Basic Profile",
            listOf("18 and over", "Living in Europe")
        ),
    )

    private val successMessage: ImageArticleModel = ImageArticleModel(
        "successMessage",
        "Great! You’re in!",
        "Congratulations! You are eligible for the study.",
        drawableId = R.drawable.sample_image2
    )

    private val failMessage: ImageArticleModel = ImageArticleModel(
        "failMessage",
        "You are not eligible for the study.",
        "Check back later and stay tuned for more studies coming soon!",
        drawableId = R.drawable.sample_image3
    )
}
