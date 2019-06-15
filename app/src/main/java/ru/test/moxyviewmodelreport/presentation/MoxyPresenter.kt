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
                    view.changeText(car.brand)
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