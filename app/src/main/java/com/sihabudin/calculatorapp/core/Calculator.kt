package com.sihabudin.calculatorapp.core

class Calculator() {
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

    fun splitEqual(input1: Double, input2: Double): String {
        val listResult: ArrayList<String> = ArrayList()
        val divResult = input1.div(input2)
        val equalDiv = Array(input2.toInt()) { divResult.toString().removeSuffix(".0") }
        listResult.addAll(equalDiv)
        return listResult.toList().toString()

    }

    fun splitNumRemainder(totalAmount: Double ,collectiveNumber: String): String {
        return if (totalAmount != 0.0 && collectiveNumber.isNotEmpty()) {
            val splitNum = collectiveNumber.split('.').map { it.toInt() }
            val splitNumSum = splitNum.sum()
            if (totalAmount > splitNumSum) {
                totalAmount.minus(splitNumSum).toString()
            } else {
                "0"
            }
        } else {
            "0"
        }
    }
}