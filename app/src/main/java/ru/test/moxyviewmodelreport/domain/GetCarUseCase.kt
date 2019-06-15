package ru.test.moxyviewmodelreport.domain

import io.reactivex.Single
import ru.test.moxyviewmodelreport.data.ApiWorker
import ru.test.moxyviewmodelreport.data.Car
import ru.test.moxyviewmodelreport.data.IApi

interface IGetCarUseCase {
    operator fun invoke(id: Int): Single<Car?>
}

class GetCarUseCaseImpl : IGetCarUseCase {

    private val api: IApi = ApiWorker.getApiInstance()

    override fun invoke(id: Int): Single<Car?> =
        api.getSingleCar(id)
}