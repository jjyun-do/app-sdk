package com.samsung.healthcare.research.sample

import android.content.Context
import com.samsung.healthcare.research.consent.ConsentDocument
import com.samsung.healthcare.research.consent.ConsentSection
import com.samsung.healthcare.research.step.ConsentDocumentStep
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConsentDocumentModule {

    private val consentMessages = listOf(
        R.string.consent_overview,
        R.string.consent_data_gathering,
        R.string.consent_privacy,
        R.string.consent_data_use,
        R.string.consent_time_commitment,
        R.string.consent_study_tasks,
        R.string.consent_study_survey,
        R.string.consent_withdrawing,
    )

    @Singleton
    @Provides
    fun providesConsentDocumentStep(@ApplicationContext context: Context): ConsentDocumentStep =
        ConsentDocumentStep(
            id = "consent-document-step-id",
            consentDocument = consentDocument(context)
        ) { }

    private fun consentDocument(@ApplicationContext context: Context): ConsentDocument {
        val types = ConsentSection.Type.values()
        return ConsentDocument(
            title = "Consent Document for Sample App",
            sections = consentMessages.map { resId ->
                context.getString(resId)
            }.mapIndexed { index, content ->
                ConsentSection(
                    type = types[index],
                    title = types[index].title,
                    summary = content.take(content.indexOf('.') + 1),
                    content = content
                )
            }
        )
    }
}
