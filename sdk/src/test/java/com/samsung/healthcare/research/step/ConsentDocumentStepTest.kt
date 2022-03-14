package com.samsung.healthcare.research.step

import com.samsung.healthcare.research.consent.ConsentDocument
import com.samsung.healthcare.research.consent.ConsentDocument.ConsentResult
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class ConsentDocumentStepTest {

    @Test
    fun `should invoke callback function when completed id called`() {
        val onComplete = mockk<(ConsentResult) -> Unit>(relaxed = true)

        val step = ConsentDocumentStep(
            "step-id",
            ConsentDocument("title", emptyList())
        ) { r -> onComplete(r) }
        step.completed()

        verify(exactly = 1) { onComplete(any()) }
    }
}
