package com.eerick.dogos.objects

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * RequestManager
 *
 * We use a singleton pattern here, because to process data is
 * mandatory use a requestQueue, so to avoid make multiple request
 * queue we can use just one for all the requests.
 *
 * @param context
 */
class RequestManager constructor(private val context: Context){
    companion object {
        @Volatile
        private var INSTANCE: RequestManager? = null
        /**
         * [getInstance] gets the only existing RequestManager instance
         * @param context
         * @return synchronized
         */
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RequestManager(context).also {
                INSTANCE = it
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }


    /**
     * [addToRequestQueue] Receives a request and it starts processing it
     * @param request
     */
    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }
}