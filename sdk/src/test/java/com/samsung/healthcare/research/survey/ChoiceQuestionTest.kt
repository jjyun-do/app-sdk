package com.samsung.healthcare.research.survey

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ChoiceQuestionTest {

    @Test
    fun `constructor should throw IllegalArgumentException when candidate is empty`() {
        assertThrows<IllegalArgumentException> {
            choiceOf(emptyList<String>())
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4])
    fun `getResult should return selected value`(selection: Int) {
        val candidates = listOf("jlego", "jjyun", "cubiest", "yur2", "culater")
        val question = choiceOf(candidates)
        question.selection = selection
        assertEquals(candidates[selection], question.getResult())
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 2])
    fun `selection should throw IndexOutOfBoundsException when selection is out of range`(selection: Int) {
        val question = choiceOf(listOf("test"))
        assertThrows<IndexOutOfBoundsException> {
            question.selection = selection
        }
    }

    fun <T> choiceOf(candidates: List<T>): ChoiceQuestion<T> =
        ChoiceQuestion("title", "description", candidates)
}
