package com.greengift.participant.data.util

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.greengift.participant.presentation.view.festival.MY_IMAGE
import java.util.Base64

class ImageConverter {
    fun getImageBitmap(image: String): ImageBitmap {
        return try {
            val decoded = Base64.getDecoder().decode(image)
            BitmapFactory.decodeByteArray(decoded, 0, decoded.size).asImageBitmap()
        } catch(e: Exception){
            val decoded = Base64.getDecoder().decode(MY_IMAGE)
            BitmapFactory.decodeByteArray(decoded, 0, decoded.size).asImageBitmap()
        }
    }
}