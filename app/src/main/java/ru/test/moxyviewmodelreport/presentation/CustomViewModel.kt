package ru.test.moxyviewmodelreport.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.test.moxyviewmodelreport.data.Car
import ru.test.moxyviewmodelreport.domain.IGetCarUseCase

class CustomViewModel(private val getCarUseCase: IGetCarUseCase) : ViewModel() {

    private val text: MutableLiveData<String> = MutableLiveData()
    private val error: MutableLiveData<Boolean> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    val textTitle: MutableLiveData<String>
        get() = text

    val errorVisible: MutableLiveData<Boolean>
        get() = error

    val loadingVisible: MutableLiveData<Boolean>
        get() = loading

    fun loadCar(id: Int) {
        getCarUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Car?> {
                override fun onSuccess(car: Car) {
                    loading.postValue(false)

                    val textResult = """
                        Brand: ${car.brand}
                        Model: ${car.model}
                        Color: ${car.color}
                        Year: ${car.year}
                        Cost: ${car.cost}
                    """.trimIndent()

                    text.postValue(textResult)
                }

                override fun onSubscribe(d: Disposable) {
                    error.postValue(false)
                    loading.postValue(true)
                }

                override fun onError(e: Throwable) {
                    loading.postValue(false)
                    error.postValue(true)
                }
            })
    }
}