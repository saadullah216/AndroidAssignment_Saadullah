package com.example.androidassignment_saadullah.utills

object FactorCalculator {

    /**
     * calculates factor to scale image
     */
    fun findFactor(height: Float, width: Float): Float {
            return height / width
    }

    /**
     * validation method for division
     */
    fun validateDivision( width: Float) : Boolean
    {
        return width > 0
    }
}