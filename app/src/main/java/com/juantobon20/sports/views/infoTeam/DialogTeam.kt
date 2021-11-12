package com.juantobon20.sports.views.infoTeam

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.juantobon20.sports.R
import com.juantobon20.sports.databinding.DialogTeamBinding
import com.juantobon20.sports.utils.alertDialog
import com.juantobon20.sports.views.infoTeam.adapter.DialogAdapter
import com.juantobon20.sports.views.infoTeam.viewModel.DialogTeamVM

class DialogTeam : DialogFragment() {

    private var idTeam: String? = ""
    private var _binding: DialogTeamBinding? = null
    private lateinit var viewModel: DialogTeamVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = DataBindingUtil.inflate(inflater, R.layout.dialog_team, container, true)
        this.viewModel = ViewModelProvider(this)[DialogTeamVM::class.java]
        this._binding?.apply {
            this.viewModel = this@DialogTeam.viewModel
            this.lifecycleOwner = this@DialogTeam
            this.adapter = DialogAdapter(arrayListOf())
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (idTeam.isNullOrEmpty()) {
            dismiss(); return
        }

        _binding?.btnCerrar?.setOnClickListener { dismiss() }
        this.viewModel.warning.observe(this, {
            alertDialog(context, null, it)
        })
        this.viewModel.urlFanPage.observe(this, {
            try {
                val uri = Uri.parse("http://$it")
                startActivity(Intent(Intent.ACTION_VIEW).setData(uri))
            } catch (ex: Exception) {
                alertDialog(context, null, ex.message.toString())
            }
        })
        this.viewModel.onLoadTeam(idTeam!!)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(idTeam: String?): DialogTeam {
            return DialogTeam().apply { this.idTeam = idTeam }
        }
    }
}