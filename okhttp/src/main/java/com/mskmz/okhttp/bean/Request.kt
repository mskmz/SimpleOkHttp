package com.mskmz.okhttp.bean

import org.json.JSONArray
import java.net.URL

//构建request
//获取到关键信息
class Request constructor(builder: Builder? = null) {
    var url: String? = null
    var host: String? = null
    var port: Int? = null
    lateinit var connectType: ConnectType
    val pamaList by lazy {
        HashMap<String, String>()
    }

    fun buildRequest(): String {
        return url!!
    }

    init {
        url = builder?.url
        if (url!!.contains("HTTPS")) {
            connectType = ConnectType.HTTPS
        } else {
            connectType = ConnectType.HTTP
        }
        val url = URL(url)
        host = url.host
        port = url.port

    }


    enum class ConnectType {
        HTTP, HTTPS
    }

    class Builder {
        var url: String? = null
        fun sertUrl(path: String) {
            url = path
        }

        fun builder(): Request {
            return Request(this)
        }
    }
}