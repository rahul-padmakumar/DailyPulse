package com.petros.efthymiou.dailypulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

actual open class BaseViewModel{
    actual val scope: CoroutineScope
        get() = CoroutineScope(Job())

    fun clear(){
        scope.cancel()
    }
}