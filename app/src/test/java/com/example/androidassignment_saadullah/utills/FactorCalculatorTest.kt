package com.example.androidassignment_saadullah.utills


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FactorCalculatorTest{

    @Test
    fun `valid height and width`() {
        val result = FactorCalculator.findFactor(200F, 100F)
        assertThat(result).isEqualTo(2F)
    }

    @Test
    fun `height is zero`() {
        val result = FactorCalculator.findFactor(0F, 100F)
        assertThat(result).isEqualTo(0F)
    }

    @Test
    fun `invalid width`() {
        val result = FactorCalculator.validateDivision(0F)
        assertThat(result).isFalse()
    }

    @Test
    fun `valid width`() {
        val result = FactorCalculator.validateDivision(100F)
        assertThat(result).isTrue()
    }

}