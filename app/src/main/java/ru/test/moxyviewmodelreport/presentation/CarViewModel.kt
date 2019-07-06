package ru.test.moxyviewmodelreport.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.test.moxyviewmodelreport.domain.IGetCarUseCase

class CarViewModel(private val getCarUseCase: IGetCarUseCase) : ViewModel() {

    val text: MutableLiveData<String> = MutableLiveData()

    fun loadCar() {
        text.postValue("It is a car")
    }
}