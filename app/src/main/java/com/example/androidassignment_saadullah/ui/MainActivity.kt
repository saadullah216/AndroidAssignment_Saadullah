package com.example.androidassignment_saadullah.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidassignment_saadullah.R
import com.example.androidassignment_saadullah.constants.Constants
import com.example.androidassignment_saadullah.utills.FactorCalculator
import com.example.androidassignment_saadullah.utills.isOnline
import com.example.androidassignment_saadullah.utills.showToast
import com.jsibbold.zoomage.ZoomageView
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt


/**
 * main entry point for hilt to inject view model
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // injected by hilt
    private val mainViewModel: MainViewModel by viewModels()

    // view to hold translated image
    private lateinit var img: ZoomageView

    // view for progress indication
    private lateinit var progress: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.img)
        progress = findViewById(R.id.progress_circular)


        //extension to check internet connectivity
        if(isOnline())
        {
            // view interaction with view model
            mainViewModel.getTranslatedValesState()
            // view observing data state
            mainViewModel.translatedValues.observe(this){
                when(it) {
                    is MainViewModel.TranslatedValuesEvent.Success -> {
                        progress.visibility = View.GONE
                        setUPImage(it.translatedValues.translated_height , it.translatedValues.translated_width)
                    }
                    is MainViewModel.TranslatedValuesEvent.Failure -> {
                        progress.visibility = View.GONE
                    }
                    is MainViewModel.TranslatedValuesEvent.Loading -> {
                        progress.visibility = View.VISIBLE
                    }
                    else -> Unit
                }

            }
            //......
        }
        // in case of no internet
        else
        {
            showToast(Constants.NO_INTERNET)
        }

    }

    /**
     * @param translatedHeight form view model
     * @param translatedWidth form view model
     * calculates factor which is used to scale image
     */
    private fun setUPImage(translatedHeight: Float, translatedWidth: Float)
    {
        if(FactorCalculator.validateDivision(translatedWidth))
        {
            //img.setImageDrawable(getDrawable(R.drawable.tree))
            val bitmapResizeImage = BitmapFactory.decodeResource(
                resources,
                R.drawable.activity_life_cycle
            )

            if (bitmapResizeImage != null) {

                val originalWidth: Int = bitmapResizeImage.width

                val heightFactor = FactorCalculator.findFactor(translatedHeight, translatedWidth)

                Log.e("aspect_factor ", " = $heightFactor")

                var newHeight = heightFactor * originalWidth

                img.setImageBitmap(
                    resizeBitmap(
                        bitmapResizeImage, originalWidth,
                        newHeight.roundToInt()
                    )
                )
            }
        }
        else
        {
            showToast(Constants.INVALID_TRANSLATED_WIDTH)
        }

    }


    /**
     * resize bitmap according to provided values
     */
    private fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(
                bitmap,
                width,
                height,
                false
        )
    }

}



