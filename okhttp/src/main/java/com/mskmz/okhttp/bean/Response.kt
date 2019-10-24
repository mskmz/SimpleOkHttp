package com.mskmz.okhttp.bean

//构建response
//获取到关键信息
class Response {
    open class Bulder {
        lateinit var str: String
        fun builder(): Response {
            return Response()
        }
    }
}