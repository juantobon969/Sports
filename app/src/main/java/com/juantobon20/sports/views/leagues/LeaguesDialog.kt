package com.juantobon20.sports.views.leagues

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.juantobon20.sports.R
import com.juantobon20.sports.databinding.DialogLeagueBinding
import com.juantobon20.sports.views.leagues.adapter.DialogLeagueAdapter
import com.juantobon20.sports.views.leagues.viewModel.DialogLeagueVM
import com.juantobon20.sports.views.main.MainListener

class LeaguesDialog : DialogFragment() {

    private lateinit var listener: MainListener

    private var _binding: DialogLeagueBinding? = null
    private lateinit var viewModel: DialogLeagueVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = DataBindingUtil.inflate(inflater, R.layout.dialog_league, container, true)
        this.viewModel = ViewModelProvider(this)[DialogLeagueVM::class.java]
        this._binding?.apply {
            this.viewModel = this@LeaguesDialog.viewModel
            this.lifecycleOwner = this@LeaguesDialog
            this.adapter = DialogLeagueAdapter(arrayListOf(), this@LeaguesDialog.viewModel)
        }

        this.viewModel.league.observe(this, {
            if (it == null) return@observe

            dismiss()
            listener.onClick(it)
        })
        return _binding?.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.apply {
            this.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    companion object {
        fun newInstance(listener: MainListener): LeaguesDialog = LeaguesDialog().apply {
            this.listener = listener
        }
    }
}