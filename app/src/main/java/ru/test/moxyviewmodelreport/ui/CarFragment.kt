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
import ru.test.moxyviewmodelreport.presentation.CarViewModel

class CarFragment : Fragment() {

    companion object {
        fun newInstance(): CarFragment = CarFragment()
    }

    private lateinit var viewModel: CarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = CustomViewModelFactory(GetCarUseCaseImpl())

        viewModel = ViewModelProviders.of(this, factory).get(CarViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewmodel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.text
            .observe(
                this, Observer<String> { text ->
                    view_model_title.text = text
                })

        download_button.setOnClickListener {
            viewModel.loadCar()
        }
    }
}