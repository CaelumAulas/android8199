package br.com.caelum.twittelumapp.extensions

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.codificaParaBase64(): String? {
    val bytesOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, bytesOutputStream)
    val byteArray = bytesOutputStream.toByteArray()
    val fotoNaBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
    return fotoNaBase64
}