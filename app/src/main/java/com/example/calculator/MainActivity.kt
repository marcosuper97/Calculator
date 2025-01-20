package com.example.calculator

import android.os.Bundle
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

        var count: String = ""

        fun appendNumber(number: String) {
            if (count.length < 10) { // Ограничение длины
                count += number
                binding.count.text = count
            } else {
                Toast.makeText(this, "Maximum length reached", Toast.LENGTH_SHORT).show()
            }
        }

        fun cleanCount(){
            count = ""
            binding.count.text = count
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
        binding.ac.setOnClickListener(){cleanCount()}
    }

}