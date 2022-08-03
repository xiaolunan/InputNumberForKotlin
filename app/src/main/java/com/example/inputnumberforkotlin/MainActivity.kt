package com.example.inputnumberforkotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.inputnumberforkotlin.databinding.ActivityMainBinding
import com.example.inputnumberforkotlin.lintener.InputNumListener

class MainActivity : AppCompatActivity(), InputNumListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.mInputNumberView.inputNumListener = this
        binding.mInputNumberView.setNumberMin(-5)
        binding.mInputNumberView.setNumberMax(15)
        binding.mInputNumberView.setDefaultNumber(10)
        binding.mInputNumberView.setStep(1)
    }

    override fun inputNumber(number: Int) {
        Log.d("chen", "inputNumber: $number")
    }
}