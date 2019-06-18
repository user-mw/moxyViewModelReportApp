package ru.test.moxyviewmodelreport.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

interface IMoxyView : MvpView {

    @StateStrategyType(SingleStateStrategy::class)
    fun changeText(newText: String)
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
    fun clearSearch()
}