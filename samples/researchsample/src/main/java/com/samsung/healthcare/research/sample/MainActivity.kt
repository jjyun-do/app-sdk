package com.samsung.healthcare.research.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.healthcare.research.step.ConsentDocumentStep
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var consentDocumentStep: ConsentDocumentStep

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface {
                consentDocumentStep.composable()
            }
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    /*val step = ConsentDocumentStep(
        "step-id",
        ConsentDocumentModule.consentDocument
    )

    step.composable()*/
}
