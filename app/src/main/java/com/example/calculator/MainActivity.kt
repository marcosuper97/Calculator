package com.example.calculator

import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count: String = "0"
        binding.count.text = count
        binding.scrollCount.viewTreeObserver.addOnGlobalLayoutListener {
            binding.scrollCount.post {
                binding.scrollCount.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            }
        }

        fun stringFormating(count: String): String {
            if (count.length == 4 && !count.contains(".")) {
                val index = 1
                val reformatCount = StringBuilder(count)
                reformatCount.insert(index, " ")
                return reformatCount.toString()
            } else if (count.length == 5 && !count.contains(".")) {
                val index = 2
                count.replace(" ", "")
                val reformatCount = StringBuilder(count)
                reformatCount.insert(index, " ")
                return reformatCount.toString()
            } else if (count.length == 6 && !count.contains(".")) {
                val index = 3
                count.replace(" ", "")
                val reformatCount = StringBuilder(count)
                reformatCount.insert(index, " ")
                return reformatCount.toString()
            } else if (count.length == 7 && !count.contains(".")) {
                val indices = listOf(1, 4)
                count.replace(" ", "")
                val reformatCount = StringBuilder(count)
                for (index in indices.sortedDescending()) {
                    reformatCount.insert(index, " ") // Вставляем пробел
                }
                return reformatCount.toString()
            } else if (count.length == 8 && !count.contains(".")) {
                val indices = listOf(2, 5)
                count.replace(" ", "")
                val reformatCount = StringBuilder(count)
                for (index in indices.sortedDescending()) {
                    reformatCount.insert(index, " ") // Вставляем пробел
                }
                return reformatCount.toString()
            } else if (count.length == 9 && !count.contains(".")) {
                val indices = listOf(3, 6)
                count.replace(" ", "")
                val reformatCount = StringBuilder(count)
                for (index in indices.sortedDescending()) {
                    reformatCount.insert(index, " ") // Вставляем пробел
                }
                return reformatCount.toString()
            } else return count
        }

        fun textSizeFormating(count: String) {
            if (count.length >= 7) {
                binding.count.textSize = 65.0F
            } else binding.count.textSize = 90.0F
        }


        fun appendNumber(number: String) {
            if (count == "0") {
                count = number
                binding.count.text = stringFormating(count)
            } else if (count.length < 100) { // Ограничение длины
                count += number
                binding.count.text = stringFormating(count)
                textSizeFormating(count)
            } else {
                Toast.makeText(this, "Maximum length reached", Toast.LENGTH_SHORT).show()
            }
        }


        fun cleanCount() {
            count = "0"
            binding.count.text = count
            textSizeFormating(count)
        }

        binding.one.setOnClickListener() { appendNumber("1") }
        binding.two.setOnClickListener() { appendNumber("2") }
        binding.three.setOnClickListener() { appendNumber("3") }
        binding.four.setOnClickListener() { appendNumber("4") }
        binding.five.setOnClickListener() { appendNumber("5") }
        binding.six.setOnClickListener() { appendNumber("6") }
        binding.seven.setOnClickListener() { appendNumber("7") }
        binding.eight.setOnClickListener() { appendNumber("8") }
        binding.nine.setOnClickListener() { appendNumber("9") }
        binding.zero.setOnClickListener() { appendNumber("0") }
        binding.point.setOnClickListener() { appendNumber(".") }
        binding.ac.setOnClickListener() { cleanCount() }
    }
}