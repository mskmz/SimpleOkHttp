package com.mskmz.okhttp.interceptor

import com.mskmz.okhttp.bean.Response
import com.mskmz.okhttp.chain.Chain
import com.mskmz.okhttp.chain.ChainManager

class HeadInterceptor : Interceptor {

    override fun doNext(chain: Chain): Response {
        var chainManager = chain as ChainManager
        var request = chainManager.srcRequest
        request.pamaList["test"] = "test"
        return chainManager.getResponse()
    }
}