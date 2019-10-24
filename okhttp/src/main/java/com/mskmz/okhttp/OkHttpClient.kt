package com.mskmz.okhttp


//特点 - 使用Builder模式
//功能 -
class OkHttpClient constructor(builder: Builder? = null) {
    var maxReCount = 5
    //线程池
    var isCancel = false

    init {
        if (builder != null) {
            maxReCount = builder.maxReCount
        }
    }


    class Builder {
        var maxReCount = 5
        fun builder(): OkHttpClient {
            return OkHttpClient(this)
        }
    }

}