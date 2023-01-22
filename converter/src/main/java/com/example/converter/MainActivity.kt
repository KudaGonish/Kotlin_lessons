package com.example.converter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import com.example.converter.databinding.ActivityImageConverterBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity :  MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    private var vb: ActivityImageConverterBinding? = null
    private var imageUri: Uri? = null
    private val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(ConverterJpgToPng(requireContext()), App.instance.router)
    }
    //val tempConvertedFile = File.createTempFile("tmpConvert", ".png")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ActivityImageConverterBinding.inflate(inflater, container, false).also { vb = it }.root

    // методы интерфейсов
    override fun backPressed(): Boolean = presenter.backPressed()



    override fun init() {
        vb?.btnImageSelection?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/jpg"
            startActivityForResult(intent, 1000)

        }
        vb?.btnStartConverting?.setOnClickListener {
            imageUri?.let(presenter::btnStartConvertingPressed)
        }
        vb?.btnAbort?.setOnClickListener {
            presenter.abortConvertImage()
        }
    }

    override fun showOriginImage(uri: Uri) {
    }

    override fun showConvertedImage(uri: Uri) {
    }

    override fun showMessage(text: String) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            imageUri = data?.data
            imageUri?.let { presenter.originalImageSelected(it) }
        }
    }

}
