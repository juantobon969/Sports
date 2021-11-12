package com.juantobon20.sports.views.main.adapter

import com.juantobon20.sports.R
import com.juantobon20.sports.databinding.ItemTeamsBinding
import com.juantobon20.sports.models.Team
import com.juantobon20.sports.utils.BaseAdapter

class MainAdapter(list: List<Team>,
private val listener: TeamListener
) : BaseAdapter<ItemTeamsBinding, Team>(list) {

    override val layoutId: Int get() = R.layout.item_teams

    override fun bind(binding: ItemTeamsBinding, item: Team) {
        binding.apply {
            team = item
            listener = this@MainAdapter.listener
            executePendingBindings()
        }
    }
}

interface TeamListener {
    fun onClick(team: Team)
}