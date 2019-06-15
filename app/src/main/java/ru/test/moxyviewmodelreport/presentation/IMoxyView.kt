package ru.test.moxyviewmodelreport.presentation

interface IMoxyView {

    fun changeText(newText: String)
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
}