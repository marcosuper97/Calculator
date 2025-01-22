package com.example.calculator

class SpaceController {
    fun start(count: String, length: Int): String {
        if (!count.contains(".")) {
            val trimCount = count.replace(" ", "")
            when (length) {
                4 -> {
                    val index = 1
                    val reformatCount = StringBuilder(trimCount)
                    reformatCount.insert(index, " ")
                    return reformatCount.toString()
                }

                5 -> {
                    val index = 2
                    val reformatCount = StringBuilder(trimCount)
                    reformatCount.insert(index, " ")
                    return reformatCount.toString()
                }

                6 -> {
                    val index = 3
                    val reformatCount = StringBuilder(trimCount)
                    reformatCount.insert(index, " ")
                    return reformatCount.toString()
                }

                7 -> {
                    val indices = listOf(1, 4)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                8 -> {
                    val indices = listOf(2, 5)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                9 -> {
                    val indices = listOf(3, 6)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                10 -> {
                    val indices = listOf(1, 4, 7)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                11 -> {
                    val indices = listOf(2, 5, 8)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                12 -> {
                    val indices = listOf(3, 6, 9)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                13 -> {
                    val indices = listOf(1, 4, 7, 10)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                14 -> {
                    val indices = listOf(2, 5, 8, 11)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                15 -> {
                    val indices = listOf(3, 6, 9, 12)
                    val reformatCount = StringBuilder(count)
                    for (index in indices.sortedDescending()) {
                        reformatCount.insert(index, " ")
                    }
                    return reformatCount.toString()
                }

                else -> return count
            }
        }
        return count
    }
}