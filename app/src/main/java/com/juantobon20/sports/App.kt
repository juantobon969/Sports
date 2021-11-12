package com.juantobon20.sports

import android.app.Application
import com.juantobon20.sports.connect.ApiRest
import com.juantobon20.sports.connect.IApiRest
import com.juantobon20.sports.database.DatabaseHelper

class App : Application() {

    companion object {
        lateinit var iApiRest: IApiRest
        lateinit var dbHelper: DatabaseHelper
    }

    override fun onCreate() {
        super.onCreate()

        iApiRest = ApiRest()
        dbHelper = DatabaseHelper(this)
    }
}