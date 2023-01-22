package com.example.converter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class ConverterJpgToPng(val currentContext: Context) {


    fun convert(uri: Uri): Uri{
        val tempConvertedFile = File.createTempFile("tmpConvert", ".png")

        uri.let {

            val fos = FileOutputStream(tempConvertedFile)
            val bos = BufferedOutputStream(fos)
            val mim =
                MediaStore.Images.Media.getBitmap(currentContext.contentResolver, uri)
            mim.compress(Bitmap.CompressFormat.PNG, 100, bos)
            bos.close()
            fos.close()

        }
        return tempConvertedFile.toUri()
    }

    fun stopConverting() {

    }

}