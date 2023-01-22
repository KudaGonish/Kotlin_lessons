package com.example.converter

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView: MvpView  {

    fun init()

    fun showOriginImage(uri: Uri)


    fun showConvertedImage(uri: Uri)


    fun showMessage(text: String)
}