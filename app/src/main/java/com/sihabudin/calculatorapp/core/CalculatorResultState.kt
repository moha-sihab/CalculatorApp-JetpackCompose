package com.sihabudin.calculatorapp.core

sealed class CalculatorResultState<T>(val data: T? = null){
    class SingleResult<Double>(result:Double) : CalculatorResultState<Double>(result)
    class ListResult<List>(result:List) : CalculatorResultState<List>(result)
    class EmptyResult<Double>(result:Double) : CalculatorResultState<Double>(result)
}
