package com.amoferreira.dictionary.utils

import android.content.Context

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, arg: String): String {
        return context.getString(resId, arg)
    }
}