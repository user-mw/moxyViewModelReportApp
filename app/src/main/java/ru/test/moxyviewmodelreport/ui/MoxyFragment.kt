package ru.test.moxyviewmodelreport.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_moxy.*
import ru.test.moxyviewmodelreport.R
import ru.test.moxyviewmodelreport.presentation.IMoxyView
import ru.test.moxyviewmodelreport.presentation.MoxyPresenter

class MoxyFragment : Fragment(), IMoxyView {

    private lateinit var presenter: MoxyPresenter

    companion object {
        fun newInstance(): MoxyFragment = MoxyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_moxy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = MoxyPresenter(this)

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
}