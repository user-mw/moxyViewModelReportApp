package ru.test.moxyviewmodelreport.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_viewmodel.*
import ru.test.moxyviewmodelreport.CustomViewModelFactory
import ru.test.moxyviewmodelreport.R
import ru.test.moxyviewmodelreport.domain.GetCarUseCaseImpl
import ru.test.moxyviewmodelreport.presentation.CustomViewModel

class ViewModelFragment : Fragment() {

    companion object {
        fun newInstance(): ViewModelFragment = ViewModelFragment()
    }

    private val userName = "Test"
    private lateinit var viewModel: CustomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = CustomViewModelFactory(GetCarUseCaseImpl())

        viewModel = ViewModelProviders.of(this, factory).get(CustomViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewmodel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        download_button.setOnClickListener {
            viewModel.loadCar(Integer.parseInt(enter_id_field.text.toString()))
        }

        viewModel.textTitle
            .observe(
                this, Observer<String> { text ->
                    view_model_title.visibility = View.VISIBLE
                    view_model_title.text = text
                })

        viewModel.loadingVisible
            .observe(
                this, Observer<Boolean> { visible ->
                    if (visible) {
                        view_model_title.visibility = View.GONE
                        progress_title.visibility = View.VISIBLE
                    } else {
                        progress_title.visibility = View.GONE
                    }
                })

        viewModel.errorVisible
            .observe(
                this, Observer<Boolean> { visible ->
                    if (visible) {
                        view_model_title.visibility = View.GONE
                        error_message.visibility = View.VISIBLE
                    } else {
                        error_message.visibility = View.GONE
                    }
                })
    }
}