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
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var countToTokenizer: MutableList<String>
    private val handler = Handler(Looper.getMainLooper())
    lateinit var count: String

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
        countToTokenizer = mutableListOf()
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
                    countToTokenizer.add(number)
                    count += number
                    binding.count.text = count
                } else if (!count.contains(".")) {
                    countToTokenizer.add(number)
                    count += number
                    binding.count.text = count
                }
            } else {
                if (count == "0") {
                    countToTokenizer.add(number)
                    count = number
                    binding.count.text = count
                } else if (count.length <= 14 && !count.contains(".")) { // Ограничение длины
                    countToTokenizer.add(number)
                    count += number
                    binding.count.text = spaceController.start(count)
                    textSizeFormating(count)
                } else if (count.length <= 25 && count.contains(".")) {
                    countToTokenizer.add(number)
                    count += number
                    binding.count.text = count
                    textSizeFormating(count)
                } else Toast.makeText(this, countToTokenizer.toString(), LENGTH_LONG).show()
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
            countToTokenizer.add("%")
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

        }
    }

    private fun cleanCount() {
        countToTokenizer.clear()
        count = DEF_COUNT
        binding.count.text = count
        textSizeFormating(count)
    }

    private fun textSizeFormating(count: String) {
        if (count.length >= 7) {
            binding.count.textSize = 65.0F
        } else binding.count.textSize = 90.0F
    }

    private fun exceptionButton(view: View) {
        val listActions = mutableListOf(
            binding.percent,
            binding.divide,
            binding.multiply,
            binding.minus,
            binding.plus
        )

        if (listActions.contains(view)) {
            listActions.remove(view)
        }

        when (view) {
            binding.ac -> {
                listActions.forEach { view ->
                    view.alpha = 1F
                    view.isEnabled = true
                }
                cleanCount()
            }

            binding.plusOrMinus -> {
                if (!countToTokenizer.contains(R.id.plus_or_minus.toString())) {
                    listActions.forEach { view ->
                        view.alpha = 0.5F
                    }
                    countToTokenizer.add("+/-")
                    (TODO("ПОДУМАТЬ НАД РЕАЛИЩАЦИЕЙ"))
                }
            }

            binding.percent -> {
                if (!countToTokenizer.contains(R.id.percent.toString())) {
                    listActions.forEach { view ->
                        view.alpha = 0.5F
                        view.isEnabled = false
                    }
                    countToTokenizer.add("%")
                }
            }

            binding.divide -> {
                if (!countToTokenizer.contains(R.id.divide.toString())) {
                    listActions.forEach { view ->
                        view.alpha = 0.5F
                        view.isEnabled = false
                    }
                    countToTokenizer.add("/")
                }
            }

            binding.multiply -> {
                if (!countToTokenizer.contains(R.id.multiply.toString())) {
                    listActions.forEach { view ->
                        view.alpha = 0.5F
                        view.isEnabled = false
                    }
                    countToTokenizer.add("*")
                }
            }

            binding.minus -> {
                if (!countToTokenizer.contains(R.id.minus.toString())) {
                    listActions.forEach { view ->
                        view.alpha = 0.5F
                        view.isEnabled = false
                    }
                    countToTokenizer.add("-")
                }
            }

            binding.plus -> {
                if (!countToTokenizer.contains(R.id.plus.toString()) && countToTokenizer.isNotEmpty()) {
                    listActions.forEach { view ->
                        view.alpha = 0.5F
                        view.isEnabled = false
                    }
                    countToTokenizer.add("+")
                }
            }

            binding.equal -> {}
        }
    }

    companion object {
        private const val DEF_COUNT = "0"
    }
}