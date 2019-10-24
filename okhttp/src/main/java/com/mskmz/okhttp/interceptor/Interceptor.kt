package com.mskmz.okhttp.interceptor

import com.mskmz.okhttp.bean.Response
import com.mskmz.okhttp.chain.Chain
import java.io.IOException

interface Interceptor {
    @Throws(IOException::class)
    fun doNext(chain: Chain): Response
}