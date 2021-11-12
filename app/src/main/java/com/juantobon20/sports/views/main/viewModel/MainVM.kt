package com.juantobon20.sports.views.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juantobon20.sports.App
import com.juantobon20.sports.database.TeamDTO
import com.juantobon20.sports.models.Team
import com.juantobon20.sports.utils.BaseVM
import com.juantobon20.sports.utils.CODE_DEFAULT_LEAGUE
import com.juantobon20.sports.views.main.adapter.TeamListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainVM : BaseVM(), TeamListener {

    private var teamsAll: List<Team> = arrayListOf()

    private val _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>> get() = _teams

    private val _team = MutableLiveData<Team>()
    val team: LiveData<Team> get() = _team

    fun onLoadTeams(idLeague: String = CODE_DEFAULT_LEAGUE) {
        viewModelScope.launch {
            val rpta = try {
                App.iApiRest.getAllTeamsByLeagueId(idLeague)
            } catch (ex: Exception) {
                warning.value = ex.message; null
            } ?: return@launch


            rpta.teams.forEach {
                try {
                    withContext(Dispatchers.IO) {
                        TeamDTO().upsertTeam(it)
                    }
                } catch (ex: Exception) {
                    warning.value = ex.message; return@forEach
                }
            }

            this@MainVM._teams.value = rpta.teams
            this@MainVM.teamsAll = rpta.teams
        }
    }

    override fun onClick(team: Team) {
        this._team.value = team
    }

    fun filter(newText: String?) {
        if (newText.isNullOrEmpty()) {
            _teams.value = teamsAll; return
        }

        _teams.value =
            teamsAll.filter {
                it.name.toString().lowercase().contains(newText.lowercase())
                        || it.nameAlt.toString().contains(newText.lowercase())
            }
    }
}