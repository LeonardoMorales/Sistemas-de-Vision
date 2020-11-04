package com.dev.leonardom.sistemasdevision

import android.graphics.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.IntBuffer

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AppDebug"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dpi = this.resources.displayMetrics.density

        val originalBitmap = R.drawable.image_profile.createBitmap(this)
        imageView_original.setImageBitmap(originalBitmap)
        val originalHeight = originalBitmap.height * dpi
        val originalWidth = originalBitmap.width * dpi
        Log.d(TAG, "ORIGINAL: \nHeight = $originalHeight\nWidth = $originalWidth\n\n")

        val processedBitmap = Bitmap.createScaledBitmap(originalBitmap, (2400 / dpi).toInt(), (1920 / dpi).toInt(), false)
        imageView_processed.setImageBitmap(processedBitmap)
        val precessedHeight = processedBitmap.height * dpi
        val processedWidth = processedBitmap.width * dpi
        Log.d(TAG, "PROCESSED: \nHeight = $precessedHeight\nWidth = $processedWidth\n\n")

        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)

        imageView_processed.colorFilter = filter
        val newBitmap = toGrayscale(processedBitmap)

        newBitmap?.let { bitmap ->
            Log.d(TAG, "BITMAP WIDTH = ${bitmap.width} ")
            Log.d(TAG, "BITMAP HEIGHT = ${bitmap.height}")

            val imageMatrix = Array(bitmap.height){IntArray(bitmap.width)}

            for(m in 0 until bitmap.height){
                for (n in 0 until bitmap.width){
                    imageMatrix[m][n] = Color.red(bitmap.getPixel(n, m))
                }
            }

            // IMPRIMIR MATRIZ ORIGINAL
            /*for (array in imageMatrix) {
                for (value in array) {
                    when (value) {
                        in 0..9 -> {
                            print(" 00$value |")
                        }
                        in 10..99 -> {
                            print(" 0$value |")
                        }
                        else -> {
                            print(" $value |")
                        }
                    }
                }
                println()
            }*/

            val kernelMatrix = KernelDataSource.get(2)

            val kernelHeight = 3
            val kernelWidth = 3

            val h = 1
            val w = 1

            val imageProcessedMatrix = Array(bitmap.height){IntArray(bitmap.width)}

            for(i in h until (bitmap.height - h)){  // 1 a 11
                for(j in w until (bitmap.width - w)){  // 1 a 14
                    var sum = 0

                    for(k in 0 until kernelHeight){
                        for(l in 0 until kernelWidth){
                            sum += kernelMatrix[k][l] * imageMatrix[i - h + k][j - w + l]  // O(i,j) =  Sum[m]Sum[n](kernelMatrix(k, l) * imageMatrix(i + k - 1, k + l -1))
                        }
                    }

                    imageProcessedMatrix[i][j] = sum
                }
            }

            // IMPRIMIR MATRIZ PROCESADA
            /*for (array in imageProcessedMatrix) {
                for (value in array) {
                    print(" $value |")
                }
                println()
            }*/

            // 2D Array to Vector
            val vector = IntArray(bitmap.width * bitmap.height)
            var iterator = 0
            imageProcessedMatrix.forEachIndexed { _, ints ->
                ints.forEach {
                    vector[iterator] = it
                    iterator++
                }
            }

            val filteredBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            filteredBitmap.copyPixelsFromBuffer(IntBuffer.wrap(vector))

            imageView_original.setImageBitmap(filteredBitmap)
        }
    }

    private fun toGrayscale(bmpOriginal: Bitmap): Bitmap? {
        val height: Int = bmpOriginal.height
        val width: Int = bmpOriginal.width
        val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmpGrayscale)
        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        val f = ColorMatrixColorFilter(cm)
        paint.colorFilter = f
        c.drawBitmap(bmpOriginal, 0f, 0f, paint)
        return bmpGrayscale
    }
}