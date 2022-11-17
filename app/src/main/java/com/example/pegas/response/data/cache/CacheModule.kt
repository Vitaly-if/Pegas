package com.example.pegas.response.data.cache

import android.content.Context

interface CacheModule {

    class Base(private val context: Context) : CacheModule {

    }

    class Mock(private val context: Context) : CacheModule {

    }
}