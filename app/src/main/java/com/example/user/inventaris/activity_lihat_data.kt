package com.example.user.inventaris

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_lihat_data.*
import kotlinx.android.synthetic.main.content_activity_lihat_data.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class activity_lihat_data : AppCompatActivity() {

    private lateinit var adapter : RVAdapter
    private var ListPembaca = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data)
        setSupportActionBar(toolbar)

        adapter = RVAdapter(this, ListPembaca)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        getData()


        fab.setOnClickListener { view ->
            startActivity<MainActivity>()
                finish()
        }
    }

    private fun getData() {
        database.use {
            ListPembaca.clear()
            var result = select(Model.TABLE_NAME)
            var dataPembaca = result.parseList(classParser<Model>())
            ListPembaca.addAll(dataPembaca)
            adapter.notifyDataSetChanged()

            toast(""+dataPembaca.toString())
        }
    }

}
