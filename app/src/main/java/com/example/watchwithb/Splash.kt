package com.example.watchwithb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

val TIMEOUT : Long = 3000

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent: Intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }, TIMEOUT)
    }
}
