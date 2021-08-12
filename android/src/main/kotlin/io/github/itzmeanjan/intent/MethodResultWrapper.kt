package io.github.itzmeanjan.intent

import android.app.Activity
import android.os.Handler
import android.os.Looper

import io.flutter.plugin.common.MethodChannel

///Solution from:
//  https://stackoverflow.com/questions/55846479/is-there-an-solution-for-java-lang-illegalstateexception-reply-already-submitte
// var result = MethodResultWrapper(rawResult);
class MethodResultWrapper internal constructor(private val methodResult: MethodChannel.Result,private val activity: Activity) : MethodChannel.Result {
    override fun success(result: Any?) {
        activity.runOnUiThread(Runnable { methodResult.success(result) })
        
    }

    override fun error(
            errorCode: String, errorMessage: String?, errorDetails: Any?) {
        activity.runOnUiThread(Runnable { methodResult.error(errorCode, errorMessage, errorDetails) })
    }

    override fun notImplemented() {
        activity.runOnUiThread(Runnable { methodResult.notImplemented() })
    }

}