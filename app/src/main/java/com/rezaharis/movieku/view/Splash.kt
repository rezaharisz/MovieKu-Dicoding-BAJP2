package com.rezaharis.movieku.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.rezaharis.movieku.R

class Splash : AppCompatActivity() {
    private val displayLength = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        @Suppress("DEPRECATION")
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        showSplash()
    }

    private fun showSplash(){
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@Splash, MainActivity::class.java)
            this@Splash.startActivity(intent)
            finish()
        }, displayLength.toLong())
    }
}