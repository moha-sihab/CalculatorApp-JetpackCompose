package com.sihabudin.calculatorapp.screen


sealed class OperationState (val operation: String) {
    object Add: OperationState("+")
    object Subtract: OperationState("-")
    object Multiply: OperationState("x")
    object Divide: OperationState("/")
    object SplitEqual : OperationState("Split Equal")
    object SplitRemainder : OperationState("Split - Remainder")
}