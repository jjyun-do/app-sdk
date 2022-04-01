package com.samsung.healthcare.research.sample

import android.content.Context
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.eligibility.Eligibility
import com.samsung.healthcare.research.eligibility.EligibilityChecker
import com.samsung.healthcare.research.eligibility.EligibilityResult
import com.samsung.healthcare.research.eligibility.EligibilitySection
import com.samsung.healthcare.research.intro.Intro
import com.samsung.healthcare.research.model.ImageArticle
import com.samsung.healthcare.research.step.ConsentTextStep
import com.samsung.healthcare.research.step.EligibilityStep
import com.samsung.healthcare.research.step.IntroStep
import com.samsung.healthcare.research.step.RegistrationCompletedStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.survey.Question
import com.samsung.healthcare.research.theme.AppColors
import com.samsung.healthcare.research.theme.darkColors
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
            intro(context)
        )

    @Singleton
    @Provides
    fun providesEligibilityStep(@ApplicationContext context: Context): EligibilityStep =
        EligibilityStep(
            "eligibility-step",
            eligibility(context)
        )

    @Singleton
    @Provides
    fun providesConsentTextStep(@ApplicationContext context: Context): ConsentTextStep =
        ConsentTextStep(
            id = "context-step",
            title = "Consent",
            subTitle = "Privacy header",
            description = context.getString(R.string.lorem_ipsum_short),
            checkBoxTexts = listOf("I agree", "I agree to share my data.", "Some Message"),
            onCompleted = {}
        )

    @Singleton
    @Provides
    fun providesRegistrationCompletedStep(@ApplicationContext context: Context): RegistrationCompletedStep =
        RegistrationCompletedStep(
            id = "registration-completed-step",
            title = "Registration Completed",
            message = registrationCompletedMessage(context),
            buttonText = "Continue",
            onCompleted = {}
        )

    private fun intro(@ApplicationContext context: Context) = Intro()

    private fun eligibility(@ApplicationContext context: Context) = Eligibility(
        viewType = Eligibility.ViewType.Card,
        description = context.getString(R.string.lorem_ipsum_short),
        sections = eligibilitySections(),
        drawableId = R.drawable.sample_image1,
        eligibilityChecker = EligibilityChecker(
            questions = questions(),
            predicate = checker(),
            eligibilityResult = EligibilityResult(
                "Eligibility result",
                successMessage = successMessage(),
                failMessage = failMessage(),
            )
        )
    )

    private fun eligibilitySections(): List<EligibilitySection> = listOf(
        EligibilitySection(
            "Medical eligibility",
            listOf("Condition(s)", "Prescription(s)", "Living in Europe")
        ),
        EligibilitySection(
            "Basic Profile",
            listOf("18 and over", "Living in Europe")
        ),
    )

    private fun questions() = listOf(
        ChoiceQuestion(
            "1",
            "Do you have any existing cardiac conditions?",
            "Examples of cardiac conditions include abnormal heart rhythms, or arrhythmias",
            listOf("Yes", "No")
        ),
        ChoiceQuestion(
            "2",
            "Do you currently own a wearable device?",
            "Examples of wearable devices include Samsung Galaxy Watch 4, Fitbit, OuraRing, etc.",
            listOf("Yes", "No")
        ),
    )

    private fun successMessage() = ImageArticle(
        "Great! You’re in!",
        "Congratulations! You are eligible for the study.",
        drawableId = R.drawable.sample_image2
    )

    private fun failMessage() = ImageArticle(
        "You are not eligible for the study.",
        "Check back later and stay tuned for more studies coming soon!",
        drawableId = R.drawable.sample_image3
    )

    private fun registrationCompletedMessage(@ApplicationContext context: Context) = ImageArticle(
        "You are done!",
        "Congratulations! Everything is all set for you. Now please tap on the button below to start your SleepCare journey!",
        drawableId = R.drawable.sample_image4
    )

    private fun checker() = { questions: List<Question<*>> ->
        questions.all { q ->
            (q as ChoiceQuestion).selection == 0
        }
    }
}
