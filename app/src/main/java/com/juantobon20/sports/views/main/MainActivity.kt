package com.juantobon20.sports.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.juantobon20.sports.R
import com.juantobon20.sports.databinding.ActivityMainBinding
import com.juantobon20.sports.models.League
import com.juantobon20.sports.utils.alertDialog
import com.juantobon20.sports.views.infoTeam.DialogTeam
import com.juantobon20.sports.views.leagues.LeaguesDialog
import com.juantobon20.sports.views.main.adapter.MainAdapter
import com.juantobon20.sports.views.main.viewModel.MainVM

class MainActivity : AppCompatActivity(), MainListener {

    private var _binding: ActivityMainBinding? = null
    private lateinit var viewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this._binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this._binding?.root)

        this.viewModel = ViewModelProvider(this)[MainVM::class.java]
        this._binding?.apply {
            this.viewModel = this@MainActivity.viewModel
            this.lifecycleOwner = this@MainActivity
            this.adapter = MainAdapter(arrayListOf(), this@MainActivity.viewModel)
            this.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    this@MainActivity.viewModel.filter(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    this@MainActivity.viewModel.filter(newText)
                    return true
                }
            })
            this.btnFilter.setOnClickListener {
                LeaguesDialog.newInstance(this@MainActivity)
                    .show(supportFragmentManager, "DialogLeagues")
            }
        }

        this.viewModel.team.observe(this, {
            DialogTeam.newInstance(it.id).show(supportFragmentManager, "DialogTeam")
        })
        this.viewModel.warning.observe(this, {
            alertDialog(this, null, it)
        })

        this.viewModel.onLoadTeams()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(league: League) {
        this.viewModel.onLoadTeams(league.id)
    }
}