package ru.test.moxyviewmodelreport.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_moxy.*
import ru.test.moxyviewmodelreport.R
import ru.test.moxyviewmodelreport.presentation.IMoxyView
import ru.test.moxyviewmodelreport.presentation.MoxyPresenter

class MoxyFragment : MvpAppCompatFragment(), IMoxyView {

    companion object {
        fun newInstance(): MoxyFragment = MoxyFragment()
    }

    @InjectPresenter
    lateinit var presenter: MoxyPresenter

    @ProvidePresenter
    fun providePresenter(): MoxyPresenter = MoxyPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_moxy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        download_button.setOnClickListener {
            presenter.onClick(Integer.valueOf(enter_id_field.text.toString()))
        }
    }

    override fun changeText(newText: String) {
        moxy_title.visibility = View.VISIBLE
        moxy_title.text = newText
    }

    override fun showProgress() {
        moxy_title.visibility = View.GONE
        error_message.visibility = View.GONE
        progress_title.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_title.visibility = View.GONE
    }

    override fun showError() {
        error_message.visibility = View.VISIBLE
    }

    override fun hideError() {
        error_message.visibility = View.GONE
    }

    override fun clearSearch() {
        enter_id_field.text.clear()
    }
}