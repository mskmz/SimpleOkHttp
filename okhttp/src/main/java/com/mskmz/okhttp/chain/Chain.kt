package com.mskmz.okhttp.chain

import com.mskmz.okhttp.bean.Request
import com.mskmz.okhttp.bean.Response

interface Chain {
    fun getRequest(): Request
    fun getResponse(): Response
}