package ru.test.moxyviewmodelreport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.test.moxyviewmodelreport.ui.MoxyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showScreen(MoxyFragment.newInstance())
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
