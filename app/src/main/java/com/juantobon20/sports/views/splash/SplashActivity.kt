package com.juantobon20.sports.views.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.juantobon20.sports.databinding.ActivitySplashBinding
import com.juantobon20.sports.views.main.MainActivity
import com.juantobon20.sports.views.splash.viewModel.SplashVM

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private lateinit var viewModel: SplashVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(this._binding?.root)

        this.viewModel = ViewModelProvider(this)[SplashVM::class.java]
        this.viewModel.onSuccess().observe(this, {
            _binding?.animationView?.pauseAnimation()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}