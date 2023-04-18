package com.example.observeflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.observeflow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private val viewModel = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.myEventFlow.onEach { event ->
            when (event) {
                is MyEvent.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MyEvent.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, event.data, Toast.LENGTH_SHORT).show()
                    binding.loadDataButton.visibility = View.VISIBLE
                }
                is MyEvent.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                    binding.loadDataButton.visibility = View.VISIBLE
                }
            }
        }.launchIn(lifecycleScope)

        binding.loadDataButton.setOnClickListener {
            binding.loadDataButton.visibility = View.INVISIBLE // ou View.GONE
            viewModel.loadData()
        }

    }
}