package com.sihabudin.calculatorapp.screen

data class CalculatorState(
    val input1: String = "",
    val input2: String = "",
    val resultDisplay : String = "",
    val operationDisplay : String= "",
    val splitNumber : String= "",
    val hintInformation : String = "",
    val operation: OperationState? = null
)