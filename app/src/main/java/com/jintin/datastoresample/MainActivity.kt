package com.jintin.datastoresample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.jintin.datastoresample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.increaseCounter()
            viewModel.updateCounter()
        }
        viewModel.counter.asLiveData().observe(this) {
            binding.result.text = "Click count: $it"
        }
        viewModel.counterProto.asLiveData().observe(this) {
            println(it)
        }

    }
}