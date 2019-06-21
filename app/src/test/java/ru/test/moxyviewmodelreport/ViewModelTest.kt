package ru.test.moxyviewmodelreport

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.test.moxyviewmodelreport.domain.GetCarUseCaseImpl
import ru.test.moxyviewmodelreport.presentation.CustomViewModel

@RunWith(JUnit4::class)
class ViewModelTest {

    private val getCarUseCaseImpl: GetCarUseCaseImpl = mock()
    private lateinit var viewModel: CustomViewModel


    @Before
    fun setUp() {
        viewModel = CustomViewModel(getCarUseCaseImpl)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(viewModel, getCarUseCaseImpl)
    }

    @Test
    fun `WHEN loadCar performed EXPECT load car`() {
        val id = 1

        viewModel.loadCar(id)

        verify(getCarUseCaseImpl).invoke(id)
    }
}