package com.juantobon20.sports.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, "Sports.db",
    null, 1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.apply {
            execSQL(SCRIPTEAM)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}