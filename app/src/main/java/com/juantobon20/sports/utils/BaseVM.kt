package com.juantobon20.sports.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseVM : ViewModel() {

    val warning: MutableLiveData<String> = MutableLiveData()
    val success: MutableLiveData<Boolean> = MutableLiveData()

    fun getWarning(): LiveData<String> = this.warning
    fun onSuccess(): LiveData<Boolean> = this.success
}