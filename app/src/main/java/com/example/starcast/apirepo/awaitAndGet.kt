package com.example.starcast.apirepo

import com.example.starcast.network.NetworkResult

import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException



suspend inline fun <T> Deferred<Response<T>>.awaitAndGet(): NetworkResult<T> {
    return try {
        val response = await()
        if (response.isSuccessful)
        {
            if (response.body() is ServerResponse<*>) {
                val result = response.body() as ServerResponse<*>
                if (result.count>=1)
                {
                    NetworkResult.Success(response.body(), response.code()) as NetworkResult<T>
                }
                else if (!result.opening_crawl.isNullOrEmpty())
                {
                    NetworkResult.Success(response.body(), response.code()) as NetworkResult<T>
                }
               else if (result.count==0)
                {
                    NetworkResult.Failure("No Data Found", response.code())
                }

                else{
                    NetworkResult.Failure("", response.code())
                }

            } else {
                NetworkResult.Success(response.body(), response.code()) as NetworkResult<T>

            }
        }
        else {
            NetworkResult.Failure("No Data Found", response.code())
        }
    } catch (e: UnknownHostException) {
        NetworkResult.Failure("Failed to connect to server", 101)
    } catch (e: SocketTimeoutException) {
        NetworkResult.Failure("Timeout", HttpURLConnection.HTTP_INTERNAL_ERROR)
    } catch (e: Exception) {
        NetworkResult.Failure(e.message.toString(), HttpURLConnection.HTTP_INTERNAL_ERROR)
    }
}
