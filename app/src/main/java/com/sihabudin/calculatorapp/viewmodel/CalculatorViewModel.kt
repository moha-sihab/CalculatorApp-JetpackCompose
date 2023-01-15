package com.sihabudin.calculatorapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sihabudin.calculatorapp.core.Calculator
import com.sihabudin.calculatorapp.screen.CalculatorState
import com.sihabudin.calculatorapp.screen.OperationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val calculator: Calculator) : ViewModel() {
    var calculatorState by mutableStateOf(CalculatorState())

    fun inputNumber(input: String) {
        if (calculatorState.operation == null) {
            calculatorState = calculatorState.copy(
                input1 = calculatorState.input1 + input,
                operationDisplay = calculatorState.operationDisplay + input
            )
            return
        }
        calculatorState = calculatorState.copy(
            input2 = calculatorState.input2 + input,
            operationDisplay = calculatorState.operationDisplay + input
        )
    }

    fun setOperation(operation: OperationState) {
        if (calculatorState.input1.isNotBlank()) {
            if (calculatorState.operation == null && calculatorState.resultDisplay.isNotBlank()) {
                calculatorState = calculatorState.copy(
                    operation = operation,
                    operationDisplay = calculatorState.resultDisplay + operation.operation
                )
            } else {
                calculatorState = calculatorState.copy(
                    operation = operation,
                    operationDisplay = calculatorState.operationDisplay + operation.operation
                )
            }

        }
    }

    fun inputDecimal() {
        if (calculatorState.operation == null && !calculatorState.input1.contains(".") && calculatorState.input1.isNotBlank()) {
            calculatorState = calculatorState.copy(
                input1 = calculatorState.input1 + ".",
                operationDisplay = calculatorState.operationDisplay + ".",
            )
            return
        } else if (!calculatorState.input2.contains(".") && calculatorState.input2.isNotBlank()) {
            calculatorState = calculatorState.copy(
                input2 = calculatorState.input2 + ".",
                operationDisplay = calculatorState.operationDisplay + ".",
            )
        }
    }

    fun clearState(){
        calculatorState = calculatorState.copy(
            input1 = "",
            input2 = "",
            operation = null,
            resultDisplay = "",
            operationDisplay = ""
        )
    }

    fun deleteInput(){
        when {
            calculatorState.input2.isNotBlank() -> {
                calculatorState = calculatorState.copy(
                    input2 = calculatorState.input2.dropLast(1),
                    operationDisplay =  calculatorState.operationDisplay.dropLast(1)
                )
            }
            calculatorState.operation != null -> {
                calculatorState = calculatorState.copy(
                    operation = null,
                    operationDisplay =  calculatorState.operationDisplay.dropLast(1)
                )
            }
            calculatorState.input1.isNotBlank() -> {
                calculatorState = calculatorState.copy(
                    input1 = calculatorState.input1.dropLast(1),
                    operationDisplay =  calculatorState.operationDisplay.dropLast(1)
                )
            }
        }
    }

    fun calculate() {
        val input1 = calculatorState.input1.toDoubleOrNull()
        val input2 = calculatorState.input2.toDoubleOrNull()
        val totalNumber = calculatorState.totalNumber.toIntOrNull()
        val splitNumber = calculatorState.splitNumber
        val operation = calculatorState.operation

        if (input1 != null && input2 != null && operation != null) {
            var result = ""
            result = when (operation) {
                is OperationState.Add -> calculator.add(input1, input2).toString()
                is OperationState.Divide -> calculator.divide(input1, input2).toString()
                is OperationState.Multiply -> calculator.multiply(input1, input2).toString()
                is OperationState.Subtract -> calculator.substract(input1, input2).toString()
                is OperationState.SplitRemainder -> ({
                    if (totalNumber != null && splitNumber.isNotBlank()) {
                        calculator.splitNumRemainder(
                            totalNumber,
                            splitNumber.split(',').map { it.toInt() }.toIntArray()
                        )
                    } else {
                        0
                    }

                }).toString()
                /*   is OperationState.SplitEqual -> calculator.splitEqual(input1, input2)
                   */

                else -> {
                    ""
                }
            }

            calculatorState = calculatorState.copy(
                input1 = result.removeSuffix(".0"),
                input2 = "",
                operation = null,
                resultDisplay = result.removeSuffix(".0"),
                operationDisplay = ""
            )
        }
    }

}