package com.example.calculator

class Calculator {
    fun start(firstCount: Number, secondCount: Number, signIn: String): String {
        val first = firstCount.toDouble()
        val second = secondCount.toDouble()

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