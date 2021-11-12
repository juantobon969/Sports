package com.juantobon20.sports.models.request

import com.google.gson.annotations.SerializedName
import com.juantobon20.sports.models.Event
import com.juantobon20.sports.models.League
import com.juantobon20.sports.models.Team

class TeamResponse {
    //Lista Equipos
    @SerializedName("teams")
    var teams: List<Team> = arrayListOf()
}

class EventResponse {
    //Lista Eventos de los equipos
    @SerializedName("results")
    var events: List<Event> = arrayListOf()
}

class LeagueResponse {
    //Lista Ligas
    @SerializedName("leagues")
    var leagues: List<League> = arrayListOf()
}