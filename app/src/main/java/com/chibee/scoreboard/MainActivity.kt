package com.chibee.scoreboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chibee.scoreboard.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ScoreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        viewModel =  ViewModelProvider(this).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
    }

}