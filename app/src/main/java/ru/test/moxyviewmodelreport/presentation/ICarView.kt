package ru.test.moxyviewmodelreport.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

@StateStrategyType(SingleStateStrategy::class)
interface ICarView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showText(newText: String)

    fun showProgress()

    @StateStrategyType(SingleStateStrategy::class)
    fun hideProgress()

    fun showError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideError()

    @StateStrategyType(SkipStrategy::class)
    fun clearSearch()

    // Other code
}