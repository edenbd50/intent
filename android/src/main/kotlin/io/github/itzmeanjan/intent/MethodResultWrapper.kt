package io.github.itzmeanjan.intent

import android.os.Handler
import android.os.Looper

import io.flutter.plugin.common.MethodChannel

///Solution from:
//  https://stackoverflow.com/questions/55846479/is-there-an-solution-for-java-lang-illegalstateexception-reply-already-submitte
// var result = MethodResultWrapper(rawResult);
class MethodResultWrapper internal constructor(private val methodResult: MethodChannel.Result) : MethodChannel.Result {
    private val handler: Handler
    override fun success(result: Any?) {
        handler.post(
                Runnable { methodResult.success(result) })
    }

    override fun error(
            errorCode: String, errorMessage: String?, errorDetails: Any?) {
        handler.post(
                Runnable { methodResult.error(errorCode, errorMessage, errorDetails) })
    }

    override fun notImplemented() {
        handler.post(
                Runnable { methodResult.notImplemented() })
    }

    init {
        handler = Handler(Looper.getMainLooper())
    }
}