package com.example.calculator

class Calculator {
    fun start(firstCount: Double, secondCount: Double, signIn: String): String {
        val first = firstCount
        val second = secondCount

        val result = when (signIn) {
            "%" -> if (second == 0.0 || first == 0.0) {
                return "404.404"
            } else {
                second / first * 100
            }

            "*" -> second * first
            "+" -> first + second
            "-" -> first - second
            "/" -> {
                if (second == 0.0 || first == 0.0) {
                    return "404.404"
                } else {
                    first / second
                }
            }

            else -> return "0.0"
        }
        return "%.6f".format(result).trimEnd('0').trimEnd('.').trimEnd(',')
    }
}