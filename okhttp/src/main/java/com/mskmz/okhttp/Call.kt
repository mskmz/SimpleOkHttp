package com.mskmz.okhttp

import com.mskmz.okhttp.bean.Request
import com.mskmz.okhttp.bean.Response

interface Call {
    fun request(): Request

    fun execute(): Response

    fun enqueue(responseCallback: Callback)

    fun cancel()

    fun isExecuted(): Boolean

    fun isCanceled(): Boolean

    interface Factory {
        fun newCall(request: Request): Call
    }

}