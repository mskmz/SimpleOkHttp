package com.netease.custom_okhttp.okhttp.connect

import com.mskmz.okhttp.bean.Request
import com.mskmz.okhttp.bean.Response
import java.io.IOException

interface ExchangeCodec {
    fun connection()


    @Throws(IOException::class)
    fun writeRequestHeaders(request: Request)

    @Throws(IOException::class)
    fun flushRequest()

    @Throws(IOException::class)
    fun finishRequest()

    @Throws(IOException::class)
    fun readResponseHeaders(expectContinue: Boolean): Response.Bulder?


    fun cancel()

    companion object {
        const val DISCARD_STREAM_TIMEOUT_MILLIS = 100
    }

}