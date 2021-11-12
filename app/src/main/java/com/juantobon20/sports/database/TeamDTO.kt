package com.juantobon20.sports.database

import android.content.ContentValues
import android.database.Cursor
import com.juantobon20.sports.App
import com.juantobon20.sports.models.Team

class TeamDTO {
    private val db = App.dbHelper

    @Throws(Exception::class)
    fun getTeam(id: String?): Team? {
        val cursor =
            this.db.readableDatabase.rawQuery(
                "Select * FROM Team WHERE Id = '$id'",
                null
            )
        if (cursor == null || !cursor.moveToFirst()) {
            cursor?.close(); return null
        }

        val team = this.onLoadData(cursor)
        cursor.close()
        return team
    }

    private fun onLoadData(cursor: Cursor) =
        Team(
            cursor.getString(0), cursor.getString(1), cursor.getString(2),
            cursor.getString(3), cursor.getString(4), cursor.getString(5),
            cursor.getString(6), cursor.getString(7), cursor.getString(8),
            cursor.getString(9), cursor.getString(10)
        )

    @Throws(Exception::class)
    fun upsertTeam(team: Team): Boolean {
        val rpta = this.getTeam(team.id)

        return if (rpta != null && !rpta.name.isNullOrEmpty()) this.updateTeam(team)
        else this.insertTeam(team)
    }

    @Throws(Exception::class)
    fun insertTeam(team: Team): Boolean {
        return this.db.writableDatabase.insertOrThrow(
            "Team",
            null,
            setContentValues(team)
        ) > 0
    }

    @Throws(Exception::class)
    fun updateTeam(team: Team): Boolean {

        return this.db.writableDatabase.update(
            "Team",
            setContentValues(team, true),
            "Id = '${team.id}'",
            null
        ) > 0
    }

    private fun setContentValues(team: Team, isModify: Boolean = false) =
        ContentValues().apply {
            if (!isModify) put("Id", team.id)
            put("Name", team.name)
            put("NameAlt", team.nameAlt)
            put("Description", team.description)
            put("Badge", team.badge)
            put("Jersey", team.jersey)
            put("Stadium", team.stadium)
            put("FoundationYear", team.foundationYear)
            put("WebFacebook", team.webFacebook)
            put("WebInstagram", team.webInstagram)
            put("WebTwitter", team.webTwitter)
        }
}