package com.amoferreira.dictionary.utils

interface ResourceProvider {
    fun getString(resId: Int): String
    fun getString(resId: Int, arg: String): String
}