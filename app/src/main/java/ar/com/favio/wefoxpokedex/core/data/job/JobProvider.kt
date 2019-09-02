package ar.com.favio.wefoxpokedex.core.data.job

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

interface JobProvider {
    private val parentJob: Job
        get() = Job()
    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
     val scope: CoroutineScope
         get() = CoroutineScope(coroutineContext)

     fun cancelAllRequests() = coroutineContext.cancel()
}