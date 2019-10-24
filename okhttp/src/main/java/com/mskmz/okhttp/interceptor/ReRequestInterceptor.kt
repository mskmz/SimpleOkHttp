package com.mskmz.okhttp.interceptor

import com.mskmz.okhttp.bean.Response
import com.mskmz.okhttp.chain.Chain
import com.mskmz.okhttp.chain.ChainManager
import java.lang.Exception
import java.lang.RuntimeException

//在这里完成实际的请求创建
class ReRequestInterceptor : Interceptor {

    //在这里实现请求重定向
    override fun doNext(chain: Chain): Response {
        var chainManager = chain as ChainManager
        var client = chainManager.realCall.client
        repeat(client.maxReCount) {
            try {
                return chainManager.getResponse()
            } catch (e: Exception) {

            }
        }
        throw RuntimeException("chong ")

    }
}