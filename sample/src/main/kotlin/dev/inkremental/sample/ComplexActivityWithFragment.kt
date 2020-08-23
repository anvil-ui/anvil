package dev.inkremental.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ComplexActivityWithFragment : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, ComplextFragment())
                .commit()
    }
}