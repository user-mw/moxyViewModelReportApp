package ru.test.moxyviewmodelreport.presentation

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.test.moxyviewmodelreport.data.Car
import ru.test.moxyviewmodelreport.domain.GetCarUseCaseImpl
import ru.test.moxyviewmodelreport.domain.IGetCarUseCase

class MoxyPresenter(private val view: IMoxyView) {

    private val getCarUseCase: IGetCarUseCase = GetCarUseCaseImpl()

    fun onClick(id: Int) {
        getCarUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Car?> {
                override fun onSuccess(car: Car) {
                    view.hideProgress()

                    val textResult = """
                        Brand: ${car.brand}
                        Model: ${car.model}
                        Color: ${car.color}
                        Year: ${car.year}
                        Cost: ${car.cost}
                    """.trimIndent()

                    view.changeText(textResult)
                }

                override fun onSubscribe(d: Disposable) {
                    view.hideError()
                    view.showProgress()
                }

                override fun onError(e: Throwable) {
                    view.hideProgress()
                    view.showError()
                }
            })
    }
}