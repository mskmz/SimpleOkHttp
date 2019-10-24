package com.mskmz.okhttp

import android.provider.Telephony
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Dispatcher constructor(
        var mMaxRequest: Int = 64,
        var mMaxHostRun: Int = 5,
        var mRunList: ArrayDeque<RealCall.AsyncCall> = ArrayDeque(),
        var mReadyList: ArrayDeque<RealCall.AsyncCall> = ArrayDeque()
) {

    val exception: ExecutorService by lazy {
        Executors.newCachedThreadPool()
    }

    fun enqueue(call: RealCall.AsyncCall): Boolean {
        //check 数量
        if (mRunList.size >= mMaxRequest || getCount(call) >= mMaxHostRun) {
            mReadyList.add(call)
            return false
        } else {
            mRunList.add(call)

            return true
        }
    }

    fun execute(call: RealCall.AsyncCall): ExecutorService {
        exception.execute(call)
        return exception
    }

    fun finished(call: RealCall.AsyncCall) {
        mRunList.remove(call)
        if (enqueue(mReadyList.first)) {
            mReadyList.removeFirst()
        }
    }

    fun getCount(call: RealCall.AsyncCall): Int {
        var count = 0
        mRunList.forEach {
            if (call.request.host.equals(it.request.host)) {
                count++
            }
        }
        return count
    }
}


