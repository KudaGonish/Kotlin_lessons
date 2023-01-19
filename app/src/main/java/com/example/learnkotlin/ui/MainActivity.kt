package com.example.learnkotlin.ui

import android.os.Bundle
import com.example.learnkotlin.App
import com.example.learnkotlin.R
import com.example.learnkotlin.databinding.ActivityMainBinding
import com.example.learnkotlin.presenters.MainPresenter
import com.example.learnkotlin.ui.views.MainView
import com.example.learnkotlin.utils.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView {
    val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MainPresenter(
        App.instance.router,
        AndroidScreens()) }
    private var vb: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }
    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }
    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}
