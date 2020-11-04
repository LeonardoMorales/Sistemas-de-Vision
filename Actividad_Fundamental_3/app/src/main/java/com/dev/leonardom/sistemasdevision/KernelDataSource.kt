package com.dev.leonardom.sistemasdevision

class KernelDataSource {
    companion object {
        fun get(num: Int): Array<IntArray>{

            when(num){
                0 -> {
                    val kernelRow1 = intArrayOf(0, 1, 0)
                    val kernelRow2 = intArrayOf(1, -4, 1)
                    val kernelRow3 = intArrayOf(0, 1, 0)
                    return arrayOf(kernelRow1, kernelRow2, kernelRow3)
                }

                1 -> {
                    val kernel2Row1 = intArrayOf(0, -1, 0)
                    val kernel2Row2 = intArrayOf(-1, 4, -1)
                    val kernel2Row3 = intArrayOf(0, -1, 0)
                    return arrayOf(kernel2Row1, kernel2Row2, kernel2Row3)
                }

                2 -> {
                    val kernel3Row1 = intArrayOf(1, 1, 1)
                    val kernel3Row2 = intArrayOf(1, -8, 1)
                    val kernel3Row3 = intArrayOf(1, 1, 1)
                    return arrayOf(kernel3Row1, kernel3Row2, kernel3Row3)
                }

                3 -> {
                    // KERNEL 4 (Outline)
                    val kernel4Row1 = intArrayOf(-1, -1, -1)
                    val kernel4Row2 = intArrayOf(-1, 8, -1)
                    val kernel4Row3 = intArrayOf(-1, -1, -1)
                    return arrayOf(kernel4Row1, kernel4Row2, kernel4Row3)
                }

                4 -> {
                    // KERNEL 5 (sharpen)
                    val kernel5Row1 = intArrayOf(0, -1, 0)
                    val kernel5Row2 = intArrayOf(-1, 5, -1)
                    val kernel5Row3 = intArrayOf(0, -1, 0)
                    return arrayOf(kernel5Row1, kernel5Row2, kernel5Row3)
                }

                5 -> {
                    // KERNEL 6 (identity)
                    val kernel6Row1 = intArrayOf(0, 0, 0)
                    val kernel6Row2 = intArrayOf(0, 1, 0)
                    val kernel6Row3 = intArrayOf(0, 0, 0)
                    return arrayOf(kernel6Row1, kernel6Row2, kernel6Row3)
                }

                6 -> {
                    // KERNEL 7 (bottom sobel)
                    val kernel7Row1 = intArrayOf(-1, -2, -1)
                    val kernel7Row2 = intArrayOf(0, 0, 0)
                    val kernel7Row3 = intArrayOf(1, 2, 1)
                    return arrayOf(kernel7Row1, kernel7Row2, kernel7Row3)
                }

                7 -> {
                    // KERNEL 8
                    val kernel8Row1 = intArrayOf(-1, -1, -1)
                    val kernel8Row2 = intArrayOf(-1, 12, -1)
                    val kernel8Row3 = intArrayOf(-1, -1, -1)
                    return arrayOf(kernel8Row1, kernel8Row2, kernel8Row3)
                }

                else -> {
                    val kernelRow1 = intArrayOf(0, 0, 0)
                    val kernelRow2 = intArrayOf(0, 0, 0)
                    val kernelRow3 = intArrayOf(0, 0, 0)
                    return arrayOf(kernelRow1, kernelRow2, kernelRow3)
                }
            }
        }
    }

}