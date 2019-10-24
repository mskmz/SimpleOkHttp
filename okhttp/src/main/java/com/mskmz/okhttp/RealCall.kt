package com.mskmz.okhttp

import com.mskmz.okhttp.bean.Request
import com.mskmz.okhttp.bean.Response
import com.mskmz.okhttp.chain.Chain
import com.mskmz.okhttp.chain.ChainManager
import com.mskmz.okhttp.interceptor.HeadInterceptor
import com.mskmz.okhttp.interceptor.Interceptor
import java.lang.Exception
import java.util.ArrayList
import kotlin.properties.Delegates

class RealCall constructor(
        var client: OkHttpClient,
        var originalRequest: Request
) : Call {
    var isRun = false
    var isCancel = true
    override fun request(): Request {
        return originalRequest
    }

    override fun execute(): Response {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enqueue(responseCallback: Callback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel() {
        isCancel = true
    }

    override fun isExecuted(): Boolean {
        return isRun
    }

    override fun isCanceled(): Boolean {
        return isCancel
    }

    //异步调用？？？ 真正的函数请求 在这里实现真实的代码调用
    class AsyncCall constructor(
            var client: OkHttpClient,
            var request: Request,
            var callBack: Callback,
            var call: RealCall
    ) : Runnable {
        var hasResponse by Delegates.notNull<Boolean>()
        override fun run() {
            hasResponse = false
            try {
                getResponse()
            } catch (e: Exception) {

            }
        }

        fun getResponse(): Response {
            val list = ArrayList<Interceptor>()
            list.add(HeadInterceptor())

            return ChainManager(request, list, call).getResponse()
        }
    }


    companion object {
        fun newRealCall(
                client: OkHttpClient,
                originalRequest: Request
        ): RealCall {
            return RealCall(client, originalRequest)
        }
    }
}