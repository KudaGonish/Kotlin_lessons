package com.example.learnkotlin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkotlin.databinding.ActivityMainBinding
import com.example.learnkotlin.presenters.MainPresenter
import com.example.learnkotlin.ui.views.MainView

class MainActivity : AppCompatActivity(), MainView {


    private lateinit var binding: ActivityMainBinding
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            presenter.counterButton1Click()
            presenter.counterButton2Click()
            presenter.counterButton3Click()
        }

        binding.btnCounter1.setOnClickListener(listener)
        binding.btnCounter2.setOnClickListener(listener)
        binding.btnCounter3.setOnClickListener(listener)

    }

    override fun setButton1Text(index: Int, text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButton2Text(index: Int, text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButton3Text(index: Int, text: String) {
        binding.btnCounter3.text = text
    }
}
