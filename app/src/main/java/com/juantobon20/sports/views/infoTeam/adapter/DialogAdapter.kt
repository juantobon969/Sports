package com.juantobon20.sports.views.infoTeam.adapter

import com.juantobon20.sports.R
import com.juantobon20.sports.databinding.ItemEventBinding
import com.juantobon20.sports.models.Event
import com.juantobon20.sports.utils.BaseAdapter

class DialogAdapter(list: List<Event>
) : BaseAdapter<ItemEventBinding, Event>(list) {

    override val layoutId: Int get() = R.layout.item_event

    override fun bind(binding: ItemEventBinding, item: Event) {
        binding.apply {
            event = item
            executePendingBindings()
        }
    }
}