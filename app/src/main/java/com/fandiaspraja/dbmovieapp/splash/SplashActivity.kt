package com.fandiaspraja.dbmovieapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fandiaspraja.dbmovieapp.MovieMainActivity
import com.fandiaspraja.dbmovieapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, MovieMainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}