package com.juantobon20.sports.views.leagues.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juantobon20.sports.App
import com.juantobon20.sports.models.League
import com.juantobon20.sports.utils.BaseVM
import com.juantobon20.sports.views.leagues.adapter.LeagueListener
import kotlinx.coroutines.launch

class DialogLeagueVM : BaseVM(), LeagueListener {

    private val _leagues = MutableLiveData<List<League>>()
    val leagues: LiveData<List<League>> get() = _leagues

    private val _league = MutableLiveData<League>()
    val league: LiveData<League> get() = _league

    init {
        this.onLoadData()
    }

    private fun onLoadData() {
        viewModelScope.launch {
            val rpta = try {
                App.iApiRest.getAllLeagues()
            } catch (ex: Exception) {
                warning.value = ex.message; null
            } ?: return@launch

            val onlySoccer = rpta.leagues.filter { it.sport == "Soccer" }
            if (onlySoccer.isNullOrEmpty()) {
                warning.value = "No hay ligas de futbol"; return@launch
            }

            _leagues.value = onlySoccer
        }
    }

    override fun onClick(league: League) {
        this._league.value = league
    }
}