package com.mskmz.okhttp.chain

import com.mskmz.okhttp.RealCall
import com.mskmz.okhttp.bean.Request
import com.mskmz.okhttp.bean.Response
import com.mskmz.okhttp.interceptor.Interceptor
import java.lang.RuntimeException

class ChainManager constructor(
        var srcRequest: Request,
        var chainList: List<Interceptor>,
        var realCall: RealCall,
        var index: Int = 0
) : Chain {
    override fun getRequest(): Request {
        return srcRequest
    }

    override fun getResponse(): Response {
        //先过滤所有的拦截器
        if (index >= chainList.size) {
            throw RuntimeException("下标越界")
        }
        if (chainList.isEmpty()) {
            throw RuntimeException("interceptors is empty")
        }
        return chainList.get(index).doNext(ChainManager(srcRequest, chainList, realCall, index + 1))
    }
}