package com.juantobon20.sports.views.leagues.adapter

import com.juantobon20.sports.R
import com.juantobon20.sports.databinding.ItemLeagueBinding
import com.juantobon20.sports.models.League
import com.juantobon20.sports.utils.BaseAdapter

class DialogLeagueAdapter(
    list: List<League>, private val listener: LeagueListener
) : BaseAdapter<ItemLeagueBinding, League>(list) {

    override val layoutId: Int get() = R.layout.item_league

    override fun bind(binding: ItemLeagueBinding, item: League) {
        binding.apply {
            league = item
            listener = this@DialogLeagueAdapter.listener
            executePendingBindings()
        }
    }
}

interface LeagueListener {
    fun onClick(league: League)
}