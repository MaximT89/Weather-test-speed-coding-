package com.secondworld.buenas.weathermaxtest.core.remote

import com.secondworld.buenas.weathermaxtest.core.bases.BaseResult
import com.secondworld.buenas.weathermaxtest.core.bases.Mapper
import retrofit2.Response
import javax.inject.Inject

/**
 * Класс для приема запроса с сервера и разбора его, на выходе мы получаем [BaseResult] в который
 * передаем тип данных соответствующий запросу или ошибку которую получаем в ходе разбора запроса
 */
interface ResponseWrapper {

    suspend fun <T, R> handleResponse(
        mapper: Mapper<T, R>,
        apiRequest: suspend () -> Response<T>
    ): BaseResult<R, Failure>

    class Base @Inject constructor() : ResponseWrapper {

        override suspend fun <T, R> handleResponse(
            mapper: Mapper<T, R>,
            apiRequest: suspend () -> Response<T>
        ): BaseResult<R, Failure> {

            return try {
                val response = apiRequest.invoke()

                if (response.isSuccessful) {
                    val body = response.body()
                    BaseResult.Success(mapper.map(body!!))
                } else {
                    BaseResult.Error(Failure(response.code(), response.message()))
                }
            } catch (e: NoInternetConnectionException) {
                BaseResult.Error(Failure(0, e.message))
            } catch (e: Exception) {
                e.printStackTrace()
                BaseResult.Error(Failure(-1, e.message.toString()))
            }
        }
    }
}