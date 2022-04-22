package com.samsung.healthcare.kit.task

import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.Model
import com.samsung.healthcare.kit.step.ConsentTextStep
import com.samsung.healthcare.kit.step.EligibilityCheckerStep
import com.samsung.healthcare.kit.step.EligibilityIntroStep
import com.samsung.healthcare.kit.step.EligibilityResultStep
import com.samsung.healthcare.kit.step.IntroStep
import com.samsung.healthcare.kit.step.RegistrationCompletedStep
import com.samsung.healthcare.kit.step.SignUpStep
import com.samsung.healthcare.kit.step.Step

class OnboardingTask private constructor(
    id: String,
    name: String,
    description: String,
    callback: () -> Unit,
    steps: List<Step<out Model, *>>,
) : OrderedTask(
    id,
    name,
    description,
    callback,
    steps
) {
    constructor(
        id: String,
        name: String,
        description: String,
        callback: () -> Unit,
        introStep: IntroStep,
        signUpStep: SignUpStep,
        registrationCompletedStep: RegistrationCompletedStep,
    ) : this(id, name, description, callback, listOf(introStep, signUpStep, registrationCompletedStep))

    constructor(
        id: String,
        name: String,
        description: String,
        callback: () -> Unit,
        introStep: IntroStep,
        eligibilityIntroStep: EligibilityIntroStep,
        eligibilityCheckerStep: EligibilityCheckerStep,
        eligibilityResultStep: EligibilityResultStep,
        consentTextStep: ConsentTextStep,
        signUpStep: SignUpStep,
        registrationCompletedStep: RegistrationCompletedStep,
    ) : this(
        id, name, description, callback,
        listOf(
            introStep,
            eligibilityIntroStep,
            eligibilityCheckerStep,
            eligibilityResultStep,
            consentTextStep,
            signUpStep,
            registrationCompletedStep
        )
    )

    var eligibility: Boolean = false

    override val pageCallbacks =
        object : CallbackCollection() {
            override fun next() {
                if (hasNext()) pageableStream.postValue(++progress.current)
                else callback()
            }

            override fun prev() {
                if (hasPrev()) pageableStream.postValue(--progress.current)
            }

            override fun setEligibility(value: Boolean) {
                eligibility = value
            }

            override fun getEligibility(): Boolean = eligibility
        }
}
