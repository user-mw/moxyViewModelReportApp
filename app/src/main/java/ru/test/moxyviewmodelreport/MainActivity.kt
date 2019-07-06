package ru.test.moxyviewmodelreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.test.moxyviewmodelreport.ui.CarFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showScreen(CarFragment.newInstance())
        }
    }

    private fun showScreen(fragment: Fragment) {
        val stacked = supportFragmentManager.findFragmentById(R.id.fragment_container) != null

        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)

        if (stacked) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }

        transaction.commit()
    }
}
