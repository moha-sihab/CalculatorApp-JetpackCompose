package com.sihabudin.calculatorapp.core

class Calculator() {

    private var listResult: ArrayList<Double> = ArrayList()
    var resultState: Any = CalculatorResultState.EmptyResult(null)

    fun add(input1: Double, input2: Double): Double {
        return input1.plus(input2)
    }

    fun substract(input1: Double, input2: Double): Double {
        return input1.minus(input2)
    }

    fun multiply(input1: Double, input2: Double): Double {
        return input1.times(input2)
    }

    fun divide(input1: Double, input2: Double): Double {
        return input1.div(input2)
    }

    fun splitEqual(input1: Double, input2: Double) {
        if (input1 != null && input2 != null && input1 != 0.0 && input2 != 0.0) {
            val divResult = input1.div(input2)
            val equalDiv = Array(input2.toInt()) { divResult }
            listResult.addAll(equalDiv)
            resultState = CalculatorResultState.ListResult(listResult)

        } else {
            resultState = CalculatorResultState.EmptyResult(null)
        }
    }

    fun splitNumRemainder(totalAmount: Int, splitNum: IntArray) : Int {
        if (totalAmount != 0 && splitNum.isNotEmpty()) {
            val splitNumSum = splitNum.sum()
            if (totalAmount > splitNumSum) {
                return totalAmount.minus(splitNumSum)
            }
            else
            {
                return 0
            }
        }
        else
        {
            return 0
        }
    }
}