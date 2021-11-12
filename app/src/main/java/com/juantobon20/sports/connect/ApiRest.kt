package com.juantobon20.sports.connect

import com.juantobon20.sports.models.request.EventResponse
import com.juantobon20.sports.models.request.LeagueResponse
import com.juantobon20.sports.models.request.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiRest : IApiRest {

    @Throws(Exception::class)
    override suspend fun getAllTeamsByLeagueId(leagueId: String): TeamResponse {
        val rpta = withContext(Dispatchers.IO) {
            Connection().iApiRest.getAllTeamsByLeagueId(leagueId)
        } ?: throw Exception("Error al obtener datos de la liga $leagueId")

        return rpta
    }

    override suspend fun getTeamDetailsById(teamId: String): TeamResponse {
        val rpta = withContext(Dispatchers.IO) {
            Connection().iApiRest.getTeamDetailsById(teamId)
        } ?: throw Exception("Error al obtener datos del equipo $teamId")

        return rpta
    }

    override suspend fun getEventsByTeamId(teamId: String): EventResponse {
        val rpta = withContext(Dispatchers.IO) {
            Connection().iApiRest.getEventsByTeamId(teamId)
        } ?: throw Exception("Error al obtener datos del equipo $teamId")

        return rpta
    }

    override suspend fun getAllLeagues(): LeagueResponse {
        val rpta = withContext(Dispatchers.IO) {
            Connection().iApiRest.getAllLeagues()
        } ?: throw Exception("Error al obtener datos de las ligas")

        return rpta
    }
}