package com.example.calculator

class Calculator {
    fun start(firstCount:Double, secondCount: Double, signIn: String): String {
        val first = firstCount
        val second = secondCount

        return when (signIn) {
            "%" -> (second / first * 100).toString()
            "*" -> (second * first).toString()
            "+" -> (first + second).toString()
            "-" -> (first - second).toString()
            "/" -> {
                if (second == 0.0) {
                    "ОШИБКА: деление на ноль"
                } else {
                    (first / second).toString()
                }
            }
            else -> "eRrOrr :)"
        }
    }
}