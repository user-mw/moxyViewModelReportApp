package ru.test.moxyviewmodelreport.data

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.moxyviewmodelreport.BuildConfig.MAIN_URL

class ApiWorker {

    companion object {
        private val gsonInstance: Gson = Gson()

        fun getApiInstance(): IApi =
            getRetrofitInstance().create(IApi::class.java)

        private fun getOkHttp(): OkHttpClient =
                OkHttpClient()
                    .newBuilder()
                    .build()

        private fun getRetrofitInstance(): Retrofit =
                Retrofit.Builder()
                    .baseUrl(MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonInstance))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
    }
}