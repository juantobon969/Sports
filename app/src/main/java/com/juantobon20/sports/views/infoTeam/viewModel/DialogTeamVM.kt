package com.juantobon20.sports.views.infoTeam.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juantobon20.sports.App
import com.juantobon20.sports.database.TeamDTO
import com.juantobon20.sports.models.Event
import com.juantobon20.sports.models.Team
import com.juantobon20.sports.utils.BaseVM
import kotlinx.coroutines.launch

class DialogTeamVM : BaseVM() {

    private val _team = MutableLiveData<Team>()
    val team: LiveData<Team> get() = _team

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    private val _urlFanPage = MutableLiveData<String>()
    val urlFanPage: LiveData<String> get() = _urlFanPage

    private val _nameEvents = MutableLiveData<String>("No hay eventos")
    val nameEvents: LiveData<String> get() = _nameEvents

    fun onLoadTeam(idTeam: String) {
        viewModelScope.launch {
            val rpta = try {
                TeamDTO().getTeam(idTeam)
                    ?: throw Exception("No se encontraron datos del equipo $idTeam")
            } catch (ex: Exception) {
                warning.value = ex.message; null
            } ?: return@launch

            _team.value = rpta

            val events = try {
                App.iApiRest.getEventsByTeamId(idTeam)
            } catch (ex: Exception) {
                warning.value = ex.message; null
            }

            if (!events?.events.isNullOrEmpty()) _nameEvents.value = "Eventos"
            _events.value = events?.events
        }
    }

    fun loadUrl(url: String?) {
        if (url.isNullOrEmpty()) return

        this._urlFanPage.value = url
    }
}