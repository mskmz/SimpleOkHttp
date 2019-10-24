package com.netease.custom_okhttp.okhttp.connect

import com.mskmz.okhttp.bean.Request
import com.mskmz.okhttp.bean.Response
import java.io.*
import java.net.Socket
import javax.net.ssl.SSLSocketFactory

class SocketExchangeCodec constructor(
        var request: Request
) : ExchangeCodec {
    lateinit var socket: Socket
    var os: OutputStream? = null
    var bufferedWriter: BufferedWriter? = null
    override fun writeRequestHeaders(request: Request) {
        if (bufferedWriter == null) {
            os = socket.getOutputStream()
            bufferedWriter = BufferedWriter(OutputStreamWriter(os))
        }
        bufferedWriter!!.write(request.buildRequest())
    }

    override fun flushRequest() {
        bufferedWriter!!.flush()
    }

    override fun finishRequest() {
        flushRequest()
        bufferedWriter!!.close()
        bufferedWriter = null
        os!!.close()
        os = null
    }

    override fun readResponseHeaders(expectContinue: Boolean): Response.Bulder? {
        val str = StringBuffer()
        BufferedReader(InputStreamReader(socket.getInputStream())).use {
            var readerLine: String?
            while (true) {
                readerLine = it.readLine()
                if (readerLine == null) {
                    break
                }
                str.append(readerLine)
            }
        }
        val ret = Response.Bulder()
        ret.str = str.toString()
        return ret
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connection() {
        socket = when (request.connectType) {
            Request.ConnectType.HTTP -> Socket(request.host!!, request.port!!)
            Request.ConnectType.HTTPS -> SSLSocketFactory.getDefault().createSocket(request.host!!, request.port!!)
        }


    }
}