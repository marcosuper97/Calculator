package com.example.calculator

class SpaceController {
    fun start(number: String): String {
        val stringBuilder = StringBuilder()
        val length = number.length
        for (i in 0 until length) {
            if ((length - i) % 3 == 0 && i != 0) {
                stringBuilder.append(' ')
            }
            stringBuilder.append(number[i])
        }
        return stringBuilder.toString()
    }
}