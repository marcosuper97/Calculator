package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var count = DEF_COUNT
    private val calculator = Calculator()
    private var firstCount = 0.0
    private var secondCount = 0.0
    private var sign = DEF_STR
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
        val spaceController = SpaceController()
        count = "0"
        binding.count.text = count
        binding.scrollCount.viewTreeObserver.addOnGlobalLayoutListener {
            binding.scrollCount.post {
                binding.scrollCount.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            }
        }

        fun textSizeFormating(count: String) {
            if (count.length >= 7) {
                binding.count.textSize = 65.0F
            } else binding.count.textSize = 90.0F
        }

        fun appendNumber(number: String) {
            if (number.contains(".")) {
                if (count == "0") {
                    count += number
                    binding.count.text = count
                } else if (!count.contains(".")) {
                    count += number
                    binding.count.text = count
                }
            } else {
                if (count == "0") {
                    count = number
                    binding.count.text = count
                } else if (count.length <= 14 && !count.contains(".")) {
                    count += number
                    binding.count.text = spaceController.start(count)
                    textSizeFormating(count)
                } else if (count.length <= 25 && count.contains(".")) {
                    count += number
                    binding.count.text = count
                    textSizeFormating(count)
                }
            }
        }

        binding.one.setOnClickListener() {
            appendNumber("1")
        }
        binding.two.setOnClickListener() {
            appendNumber("2")
        }
        binding.three.setOnClickListener() {
            appendNumber("3")
        }
        binding.four.setOnClickListener() {
            appendNumber("4")
        }
        binding.five.setOnClickListener() {
            appendNumber("5")
        }
        binding.six.setOnClickListener() {
            appendNumber("6")
        }
        binding.seven.setOnClickListener() {
            appendNumber("7")
        }
        binding.eight.setOnClickListener() {
            appendNumber("8")
        }
        binding.nine.setOnClickListener() {
            appendNumber("9")
        }
        binding.zero.setOnClickListener() {
            appendNumber("0")
        }
        binding.point.setOnClickListener() {
            appendNumber(".")
        }

        binding.ac.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.ac)
            }
            handler.post { runnable.run() }
        }
        binding.percent.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.percent)
            }
            handler.post { runnable.run() }
        }
        binding.divide.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.divide)
            }
            handler.post { runnable.run() }
        }
        binding.multiply.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.multiply)
            }
            handler.post { runnable.run() }
        }
        binding.minus.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.minus)
            }
            handler.post { runnable.run() }
        }
        binding.plus.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.plus)
            }
            handler.post { runnable.run() }
        }
        binding.equal.setOnClickListener() {
            val runnable = Runnable {
                exceptionButton(binding.equal)
            }
            handler.post { runnable.run() }
        }
    }

    private fun cleanCount() {
        count = DEF_COUNT
        firstCount = DEF_COUNT_DOUBLE
        secondCount = DEF_COUNT_DOUBLE
        binding.count.text = count
        textSizeFormating(count)
    }

    private fun textSizeFormating(count: String) {
        if (count.length >= 7) {
            binding.count.textSize = 65.0F
        } else binding.count.textSize = 90.0F
    }

    private fun exceptionButton(view: View) {

        when (view) {
            binding.ac -> {
                cleanCount()
            }

            binding.plusOrMinus -> {
                if (!count.contains("-") && count != "0") {
                    count = "-".plus(count)
                } else count.replace("-", "")
            }

            binding.percent -> {
                if (firstCount != 0.0 && !(firstCount < 0.0) && count != "0") {
                    secondCount = count.toDouble()
                    count = calculator.start(firstCount, secondCount, sign)
                    binding.count.text = count
                    textSizeFormating(count)
                    firstCount = count.toDouble()
                    secondCount = DEF_COUNT_DOUBLE
                    sign = "%"
                } else if (firstCount == 0.0 && count != "0") {
                    sign = "%"
                    firstCount = count.toDouble()
                    count = DEF_COUNT
                    binding.count.text = count
                }
            }

            binding.divide -> {
                if (firstCount != 0.0 && !(firstCount < 0.0) && count != "0") {
                    secondCount = count.toDouble()
                    count = calculator.start(firstCount, secondCount, sign)
                    binding.count.text = count
                    textSizeFormating(count)
                    firstCount = count.toDouble()
                    secondCount = DEF_COUNT_DOUBLE
                    sign = "/"
                } else if (firstCount == 0.0 && count != "0") {
                    sign = "/"
                    firstCount = count.toDouble()
                    count = DEF_COUNT
                    binding.count.text = count
                }
            }

            binding.multiply -> {
                if (firstCount != 0.0 && !(firstCount < 0.0) && count != "0") {
                    secondCount = count.toDouble()
                    count = calculator.start(firstCount, secondCount, sign)
                    binding.count.text = count
                    textSizeFormating(count)
                    firstCount = count.toDouble()
                    secondCount = DEF_COUNT_DOUBLE
                    sign = "*"
                } else if (firstCount == 0.0 && count != "0") {
                    sign = "*"
                    firstCount = count.toDouble()
                    count = DEF_COUNT
                    binding.count.text = count
                }
            }

            binding.minus -> {
                if (firstCount != 0.0 && !(firstCount < 0.0) && count != "0") {
                    secondCount = count.toDouble()
                    count = calculator.start(firstCount, secondCount, sign)
                    binding.count.text = count
                    textSizeFormating(count)
                    firstCount = count.toDouble()
                    secondCount = DEF_COUNT_DOUBLE
                    sign = "-"
                } else if (firstCount == 0.0 && count != "0") {
                    sign = "-"
                    firstCount = count.toDouble()
                    count = DEF_COUNT
                    binding.count.text = count
                }
            }

            binding.plus -> {
                if (firstCount != 0.0 && !(firstCount < 0.0) && count != "0") {
                    secondCount = count.toDouble()
                    count = calculator.start(firstCount, secondCount, sign)
                    binding.count.text = count
                    textSizeFormating(count)
                    firstCount = count.toDouble()
                    secondCount = DEF_COUNT_DOUBLE
                    sign = "+"
                } else if (firstCount != 0.0 && !(firstCount < 0.0) && count != "0") {

                }

//                else if(firstCount != 0.0 && secondCount == 0.0 && sign == DEF_STR){
//                    secondCount = count.toDouble()
//                    sign = "+"
//                    count = calculator.start(firstCount, secondCount, sign)
//                    binding.count.text = count
//                    textSizeFormating(count)
//                    firstCount = count.toDouble()
//                    secondCount = DEF_COUNT_DOUBLE
//                    sign = DEF_STR
//                }
                else if (firstCount == 0.0 && count != "0") {
                    sign = "+"
                    firstCount = count.toDouble()
                    count = DEF_COUNT
                    binding.count.text = count
                }
            }

            binding.equal -> {
                if (firstCount != 0.0 && sign != DEF_STR) {
                    secondCount = count.toDouble()
                    count = calculator.start(firstCount, secondCount, sign)
                    binding.count.text = count
                    textSizeFormating(count)
                    firstCount = DEF_COUNT_DOUBLE
                    secondCount = DEF_COUNT_DOUBLE
                    sign = DEF_STR
                }
            }
        }
    }

    companion object {
        private const val DEF_COUNT = "0"
        private const val DEF_STR = ""
        private const val DEF_COUNT_DOUBLE = 0.0
    }
}