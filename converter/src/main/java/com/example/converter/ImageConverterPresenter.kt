package com.example.converter

import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter


class ImageConverterPresenter(
    private val converter: ConverterJpgToPng,
    val router: Router
) : MvpPresenter<ImageConverterView>() {
    val disposables = CompositeDisposable()
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onDestroy() {
        disposables.clear()
    }

    fun selectImage(){


    }
    fun btnStartConvertingPressed(imageUri: Uri){
        val res = converter.convert(imageUri)
        viewState.showConvertedImage(res)
    }

    fun abortConvertImage(){
        converter.stopConverting()
    }
    fun originalImageSelected(imageUri: Uri) {
        viewState.showOriginImage(imageUri)
    }
}