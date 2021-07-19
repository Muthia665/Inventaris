package com.example.user.inventaris

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.ColorSpace
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity

class RVAdapter (val context: Context, val items: ArrayList<Model>):RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(items[p1])
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(model: Model) {
            itemView.txtNama.text = model.nama
            itemView.txtPenerbit.text = model.penerbit

            itemView.setOnClickListener {
                itemView.context.startActivity<Detail>(
                    "id" to model.id,
                    "nama" to model.nama,
                    "penulis" to model.penulis,
                    "penerbit" to model.penerbit,
                    "tahun" to model.tahun,
                    "kategori" to model.kategori
                )
                (itemView.context as Activity).finish()
            }
        }
    }
}
