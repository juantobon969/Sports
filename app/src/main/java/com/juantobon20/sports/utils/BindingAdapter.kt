package com.juantobon20.sports.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.juantobon20.sports.R

@BindingAdapter("onLoadUrl")
fun ImageView.onLoadUrl(url: String?) {
    if (url.isNullOrEmpty()) return

    val circular = CircularProgressDrawable(context).apply {
        strokeWidth = 5F; centerRadius = 30F; start()
    }
    Glide.with(this)
        .load(url)
        .placeholder(circular).into(this)
}

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("newText")
fun TextView.newText(value: String?) {
    if (value.isNullOrEmpty()) this.text = "Sin año de fundacion"
    else this.text = context.getString(R.string.foundationYear_, value)
}