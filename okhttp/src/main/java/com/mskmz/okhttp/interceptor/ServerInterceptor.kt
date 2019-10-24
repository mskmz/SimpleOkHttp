package com.mskmz.okhttp.interceptor

import com.mskmz.okhttp.bean.Response
import com.mskmz.okhttp.chain.Chain
import com.mskmz.okhttp.chain.ChainManager
import java.net.Socket

//在这里完成实际的请求创建
class ServerInterceptor : Interceptor {

    override fun doNext(chain: Chain): Response {
        var chainManager = chain as ChainManager
        var realCall = chainManager.realCall
        //使用Socket 实现双端通信
        var socket = Socket()
        
        return chainManager.getResponse()
    }
}