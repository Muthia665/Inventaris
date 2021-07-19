package com.example.user.inventaris

import android.app.Activity
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var id = intent.getLongExtra("id", 0)
        var nama = intent.getStringExtra("nama")
        var penulis = intent.getStringExtra("penulis")
        var penerbit = intent.getStringExtra("penerbit")
        var tahun = intent.getStringExtra("tahun")
        var kategori = intent.getStringExtra("kategori")
        var Pilih = intent.getStringExtra("pilih")

        dlId.text = id.toString()
        dlNama.text = nama
        dlPenulis.text = penulis
        dlPenerbit.text = penerbit
        dlTahun.text = tahun
        dlKategori.text = kategori

            btnEdit.setOnClickListener {
            startActivity<MainActivity>(
                "ID" to id,
                "Nama" to nama,
                "Penulis" to penulis,
                "Penerbit" to penerbit,
                "Tahun" to tahun,
                "Kategori" to kategori
            )
        }

        btnHapus.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("PERINGATAN !!!")
            dialog.setMessage("Apakah anda ingin menghapus data ini ?")
            dialog.setPositiveButton("IYA"){ dialog, which ->
                database.use {
                    delete(Model.TABLE_NAME, "(${Model.NAMA} = {nama})", "nama" to nama.toString())
                    startActivity<activity_lihat_data>()
                    finish()
                }
            }

            dialog.setNegativeButton("TIDAK") { dialog, which ->

            }

            val a = dialog.create()
            a.show()
        }

    }
}