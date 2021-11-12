package com.juantobon20.sports.views.splash.viewModel

import androidx.lifecycle.viewModelScope
import com.juantobon20.sports.utils.BaseVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashVM : BaseVM() {

    init {
        this.loadSplash()
    }

    private fun loadSplash() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
            }

            this@SplashVM.success.value = true
        }
    }
}