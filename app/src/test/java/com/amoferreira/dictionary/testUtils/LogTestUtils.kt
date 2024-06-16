package com.amoferreira.dictionary.testUtils

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic

fun mockLog() {
    mockkStatic(Log::class)
    every { Log.i(any(), any()) } returns 0
    every { Log.v(any(), any()) } returns 0
    every { Log.d(any(), any()) } returns 0
    every { Log.e(any(), any()) } returns 0
    every { Log.e(any(), any(), any()) } returns 0
}