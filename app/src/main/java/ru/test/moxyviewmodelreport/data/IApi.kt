package ru.test.moxyviewmodelreport.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IApi {

    @GET("cars/{id}")
    fun getSingleCar(@Path("id") id: Int): Single<Car?>
}