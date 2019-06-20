package ru.test.moxyviewmodelreport.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.test.moxyviewmodelreport.data.Car
import ru.test.moxyviewmodelreport.domain.GetCarUseCaseImpl
import ru.test.moxyviewmodelreport.domain.IGetCarUseCase

@InjectViewState
class CarPresenter : MvpPresenter<ICarView>() {

    private val getCarUseCase: IGetCarUseCase = GetCarUseCaseImpl()
    private val textResult: String = ""

    fun onClick(id: Int) {
        getCarUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Car?> {
                override fun onSuccess(car: Car) {
                    viewState.hideProgress()

                    val textResult = """
                        Brand: ${car.brand}
                        Model: ${car.model}
                        Color: ${car.color}
                        Year: ${car.year}
                        Cost: ${car.cost}
                    """.trimIndent()

                    viewState.showText(textResult)
                    viewState.clearSearch()
                }

                override fun onSubscribe(d: Disposable) {
                    viewState.hideError()
                    viewState.showProgress()
                }

                override fun onError(e: Throwable) {
                    viewState.hideProgress()
                    viewState.showError()
                }
            })
    }
}