package com.example.calculator
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = DEF_COUNT
    private val calculator = Calculator()
    private var firstCount = 0.0
    private var secondCount = 0.0
    private var sign = DEF_STR
    private val spaceController = SpaceController()
    private val buttonsWithNumbers = mapOf(
        binding.zero to { appendNumber("0") },
        binding.one to { appendNumber("1") },
        binding.two to { appendNumber("2") },
        binding.three to { appendNumber("3") },
        binding.four to {appendNumber("4")},
        binding.five to {appendNumber("5")},
        binding.six to {appendNumber("6")},
        binding.seven to {appendNumber("7")},
        binding.eight to {appendNumber("8")},
        binding.nine to {appendNumber("9")},
        binding.point to {appendNumber(".")},
        binding.ac to {cleanCount()},
        binding.percent to {},
        binding.divide to {},
        binding.multiply to {},
        binding.plus to {},
        binding.minus to {},
        binding.equal to {},
        binding.plusOrMinus to {}
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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
        count = "0"
        binding.count.text = count
        binding.scrollCount.viewTreeObserver.addOnGlobalLayoutListener {
            binding.scrollCount.post {
                binding.scrollCount.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            }
        }

        fun appendNumber(number: String) {
            if (number.contains(POINT)) {
                if (count == "0") {
                    count += number
                    binding.count.text = count
                } else if (!count.contains(POINT)) {
                    count += number
                    binding.count.text = count
                }
            } else {
                if (count == "0") {
                    count = number
                    binding.count.text = count
                } else if (count.length <= 14 && !count.contains(POINT)) {
                    count += number
                    binding.count.text = spaceController.start(count)
                    textSizeFormating(count)
                } else if (count.length <= 25 && count.contains(POINT)) {
                    count += number
                    binding.count.text = count
                    textSizeFormating(count)
                }
            }
        }

        binding.one.setOnClickListener(this::onButtonClicked)
        binding.two.setOnClickListener {
            appendNumber("2")
        }
        binding.three.setOnClickListener {
            appendNumber("3")
        }
        binding.four.setOnClickListener {
            appendNumber("4")
        }
        binding.five.setOnClickListener {
            appendNumber("5")
        }
        binding.six.setOnClickListener {
            appendNumber("6")
        }
        binding.seven.setOnClickListener {
            appendNumber("7")
        }
        binding.eight.setOnClickListener {
            appendNumber("8")
        }
        binding.nine.setOnClickListener {
            appendNumber("9")
        }
        binding.zero.setOnClickListener {
            appendNumber("0")
        }
        binding.point.setOnClickListener {
            appendNumber(".")
        }

        binding.ac.setOnClickListener {
                exceptionButton(binding.ac)
        }
        binding.percent.setOnClickListener {
                exceptionButton(binding.percent)
        }
        binding.divide.setOnClickListener {
                exceptionButton(binding.divide)
        }
        binding.multiply.setOnClickListener {
                exceptionButton(binding.multiply)
        }
        binding.minus.setOnClickListener {
                exceptionButton(binding.minus)
        }
        binding.plus.setOnClickListener {
                exceptionButton(binding.plus)
        }
        binding.plusOrMinus.setOnClickListener {
                exceptionButton(binding.plusOrMinus)
        }
        binding.equal.setOnClickListener {
                exceptionButton(binding.equal)
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
                signChoosing(PLUS_OR_MINUS)
            }

            binding.percent -> {
                signChoosing(PERCENT)
            }

            binding.divide -> {
                signChoosing(DIVIDE)
            }

            binding.multiply -> {
                signChoosing(MULTIPLY)
            }

            binding.minus -> {
                signChoosing(MINUS)
            }

            binding.plus -> {
                signChoosing(PLUS)
            }

            binding.equal -> {
                signChoosing(EQUAL)
            }
        }
    }

    private fun appendNumber(number: String) {
        if (number.contains(POINT)) {
            if (count == "0") {
                count += number
                binding.count.text = count
            } else if (!count.contains(POINT)) {
                count += number
                binding.count.text = count
            }
        } else {
            if (count == "0") {
                count = number
                binding.count.text = count
            } else if (count.length <= 14 && !count.contains(POINT)) {
                count += number
                binding.count.text = spaceController.start(count)
                textSizeFormating(count)
            } else if (count.length <= 25 && count.contains(POINT)) {
                count += number
                binding.count.text = count
                textSizeFormating(count)
            }
        }
    }

    private fun onButtonClicked(view: View) {
        val action = buttonsWithNumbers[view]
        action?.invoke()
    }

    private fun signChoosing(signIn: String) {
        if (signIn in listOf(MULTIPLY, DIVIDE, PLUS, MINUS)) {
            if (firstCount != DEF_COUNT_DOUBLE && count != DEF_COUNT && sign != DEF_STR && !count.endsWith(
                    POINT
                )
            ) {
                secondCount = count.toDouble()
                count = calculator.start(firstCount, secondCount, sign)
                binding.count.text = count
                textSizeFormating(count)
                firstCount = count.toDouble()
                secondCount = DEF_COUNT_DOUBLE
                sign = signIn
            } else if (firstCount == DEF_COUNT_DOUBLE && count != DEF_COUNT && !count.endsWith(
                    POINT
                )
            ) {
                sign = signIn
                firstCount = count.toDouble()
                count = DEF_COUNT
                textSizeFormating(count)
                binding.count.text = count
            }
        } else if (signIn == PERCENT) {
            if (firstCount != DEF_COUNT_DOUBLE && !(firstCount < DEF_COUNT_DOUBLE) && count != DEF_COUNT && !count.endsWith(
                    POINT
                )
            ) {
                secondCount = count.toDouble()
                count = calculator.start(firstCount, secondCount, sign)
                binding.count.text = count
                textSizeFormating(count)
                firstCount = count.toDouble()
                secondCount = DEF_COUNT_DOUBLE
                sign = signIn
            } else if (firstCount == DEF_COUNT_DOUBLE && count != DEF_COUNT && !count.endsWith(
                    POINT
                )
            ) {
                sign = signIn
                firstCount = count.toDouble()
                count = DEF_COUNT
                textSizeFormating(count)
                binding.count.text = count
            }
        } else if (signIn == EQUAL) {
            if (firstCount != DEF_COUNT_DOUBLE && sign != DEF_STR && !count.endsWith(
                    POINT
                )
            ) {
                secondCount = count.toDouble()
                count = calculator.start(firstCount, secondCount, sign)
                binding.count.text = count
                textSizeFormating(count)
                firstCount = DEF_COUNT_DOUBLE
                secondCount = DEF_COUNT_DOUBLE
                sign = DEF_STR
            }
        } else if (signIn == PLUS_OR_MINUS) {
            if (!count.startsWith("-") && count != "0") {
                count = "-$count"
            } else if (count.startsWith("-")) {
                count = count.trimStart('-')
            }
            binding.count.text = count
        }
    }

    companion object {
        private const val DEF_COUNT = "0"
        private const val DEF_STR = ""
        private const val DEF_COUNT_DOUBLE = 0.0
        private const val PLUS = "+"
        private const val MINUS = "-"
        private const val MULTIPLY = "*"
        private const val DIVIDE = "/"
        private const val PERCENT = "%"
        private const val POINT = "."
        private const val EQUAL = "="
        private const val PLUS_OR_MINUS = "+-"
    }
}
