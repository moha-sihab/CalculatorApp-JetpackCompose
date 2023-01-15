package com.sihabudin.calculatorapp.screen

data class CalculatorState(
    val input1: String = "",
    val input2: String = "",
    val totalNumber : String = "",
    val splitNumber : String = "",
    val resultDisplay : String = "",
    val operationDisplay : String= "",
    val operation: OperationState? = null
)