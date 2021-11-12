package com.juantobon20.sports.connect

import com.juantobon20.sports.models.request.EventResponse
import com.juantobon20.sports.models.request.LeagueResponse
import com.juantobon20.sports.models.request.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiRest {
    @GET("lookup_all_teams.php")
    suspend fun getAllTeamsByLeagueId(@Query("id") leagueId: String): TeamResponse?

    @GET("lookupteam.php")
    suspend fun getTeamDetailsById(@Query("id") teamId: String): TeamResponse?

    @GET("eventslast.php")
    suspend fun getEventsByTeamId(@Query("id") teamId: String): EventResponse?

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse?
}