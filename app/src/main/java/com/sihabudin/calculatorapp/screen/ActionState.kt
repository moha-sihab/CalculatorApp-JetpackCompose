package com.sihabudin.calculatorapp.screen

sealed class ActionState {
    data class Number(val number: Int) : ActionState()
}
