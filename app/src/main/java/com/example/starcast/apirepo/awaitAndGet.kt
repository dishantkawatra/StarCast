package com.example.starcast.apirepo

import android.util.Log
import com.example.starcast.network.NetworkResult
import com.example.starcast.network.error.DefaultErrorResponse
import com.example.starcast.network.error.DynamicErrorResponse
import com.example.starcast.network.error.JsonApiErrorResponse
import com.example.starcast.network.error.SingleErrorResponse
import com.squareup.moshi.Moshi

import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException



suspend inline fun <T> Deferred<Response<T>>.awaitAndGet(): NetworkResult<T> {
    return try {
        val response = await()
        if (response.isSuccessful) {
            Log.e("message","isSuccessful")
            if (response.body() is ServerResponse<*>) {
                val result = response.body() as ServerResponse<*>
                if (result.count>=1)
                {
                    NetworkResult.Success(response.body(), response.code()) as NetworkResult<T>
                }
              else  if (result.count==0)
                {
                    NetworkResult.Failure("No Data Found", response.code())
                }
                else{
                    NetworkResult.Failure("", response.code())
                }


            } else {
                NetworkResult.Success(response.body(), response.code()) as NetworkResult<T>

            }
        } else {
            val errorBody = response.errorBody()?.source()?.readUtf8()
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(DefaultErrorResponse::class.java)
            val errorResponse = try { jsonAdapter.fromJson(errorBody)?.message
            } catch (e: Exception) {
                null
            }
            val jsonDynamicAdapter = moshi.adapter(DynamicErrorResponse::class.java)
            val jsonDynamicErrorResponse = try {
                jsonDynamicAdapter.fromJson(errorBody)?.errors?.values?.flatten()?.joinToString(", ")
            } catch (e: Exception) {
                null
            }
            val jsonSingleAdapter = moshi.adapter(SingleErrorResponse::class.java)
            val jsonSingleErrorResponse = try {
                jsonSingleAdapter.fromJson(errorBody)?.errorMessage
            } catch (e: Exception) {
                null
            }
            val jsonApiAdapter = moshi.adapter(JsonApiErrorResponse::class.java)
            val jsonApiErrorResponse = try {
                jsonApiAdapter.fromJson(errorBody)?.errorList?.get(0)?.message
            } catch (e: Exception) {
                null
            }


            val jsonErrorAdapter = moshi.adapter(ErrorMessageResponse::class.java)
            val errorMessageResponse = try {
                jsonErrorAdapter.fromJson(errorBody)?.errorMessage
            } catch (e: Exception) {
                null
            }
            val errorCode:Int = try {
                jsonErrorAdapter.fromJson(errorBody)?.errorCode!!
            }catch (e:java.lang.Exception)
            {
                jsonErrorAdapter.fromJson(errorBody)?.errorCode!!
            }


            NetworkResult.Failure(
                try {
                    errorResponse ?: jsonDynamicErrorResponse ?: jsonSingleErrorResponse
                    ?: jsonApiErrorResponse ?: errorMessageResponse ?: "Internal Server Error"
                } catch (e: SocketTimeoutException) {
                    "Timeout"
                }, errorCode
            )
        }
    } catch (e: UnknownHostException) {
        Log.e("message","catch")
        NetworkResult.Failure("Failed to connect to server", 101)
    } catch (e: SocketTimeoutException) {
        Log.e("message","catch")
        NetworkResult.Failure("Timeout", HttpURLConnection.HTTP_INTERNAL_ERROR)
    } catch (e: Exception) {
        Log.e("message","catch")
        NetworkResult.Failure(e.message.toString(), HttpURLConnection.HTTP_INTERNAL_ERROR)
    }
}
