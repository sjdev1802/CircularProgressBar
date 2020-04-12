package com.example.circularprogressbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.circularprogressbar.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val animation = CircleAngleAnimation(binding.circle)

        binding.animateBtn.setOnClickListener {
            animation.apply {
                setAngle(getAngle())
                duration = 1000
                circle.startAnimation(this)
            }
        }
    }

    private fun getAngle() = try {
        var value: Int = binding.userInput.text.toString().toInt()
        if (value > 100) {
            if (value % 100 == 0) value = 100;
            else value %= 100
        }
        value * 3.6f
    } catch (e: Exception) {
        0f
    }
}