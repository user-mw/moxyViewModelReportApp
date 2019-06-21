package ru.test.moxyviewmodelreport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.test.moxyviewmodelreport.domain.IGetCarUseCase
import ru.test.moxyviewmodelreport.presentation.CustomViewModel

class CustomViewModelFactory(private val getCarUseCase: IGetCarUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CustomViewModel(getCarUseCase) as T
    }
}