package com.example.user.inventaris

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var id = intent.getLongExtra("ID", 0)
        var nama = intent.getStringExtra("Nama")
        var penulis = intent.getStringExtra("Penulis")
        var penerbit = intent.getStringExtra("Penerbit")
        var tahun = intent.getStringExtra("Tahun")
        var kategori = intent.getStringExtra("Kategori")
        var Pilih = intent.getStringExtra("Pilih")

        btnPilih.setOnClickListener {
            val Buku = listOf("HOROR", "FANTASI", "ROMANCE")
            selector("Pilih Buku", Buku) { dialog, i ->
                edtKategori.setText(Buku[i])
            }
        }

        if (nama.isNullOrBlank()) {
            btnUpdate.visibility = View.GONE
        } else {
            btnTambah.visibility = View.GONE
            edtId.setText(id.toString())
            edtNama.setText(nama)
            edtPenulis.setText(penulis)
            edtPenerbit.setText(penerbit)
            edtTahun.setText(tahun)
            edtKategori.setText(kategori)
            btnPilih.setText(Pilih)
        }

        btnTambah.setOnClickListener {
            addData()
            clearData()
        }

        btnLihat.setOnClickListener {
            startActivity<activity_lihat_data>()
        }

        btnUpdate.setOnClickListener {
            database.use {
                update(
                    Model.TABLE_NAME,
                    Model.ID to edtId.text.toString(),
                    Model.NAMA to edtNama.text.toString(),
                    Model.PENULIS to edtPenulis.text.toString(),
                    Model.PENERBIT to edtPenerbit.text.toString(),
                    Model.TAHUN to edtTahun.text.toString(),
                    Model.KATEGORI to edtKategori.text.toString()
                )
                    .whereArgs("${Model.NAMA} = {nama}", "nama" to nama).exec()

                toast("Data berhasil DI TAMBAHKAN")
                clearData()
            }
        }

    }

    private fun addData() {
        database.use {
            insert(
                Model.TABLE_NAME,
                Model.ID to edtId.text.toString(),
                Model.NAMA to edtNama.text.toString(),
                Model.PENULIS to edtPenulis.text.toString(),
                Model.PENERBIT to edtPenerbit.text.toString(),
                Model.TAHUN to edtTahun.text.toString(),
                Model.KATEGORI to edtKategori.text.toString()
            )

            toast("Data berhasil DI TAMBAHKAN")
        }
    }

        private fun clearData() {
            edtId.text.clear()
            edtNama.text.clear()
            edtPenulis.text.clear()
            edtPenerbit.text.clear()
            edtTahun.text.clear()
            edtKategori.text.clear()
        }

    //panggil function onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.menuAbout ->{
                val i = Intent(applicationContext, AboutActivity::class.java)
                startActivity(i)
            }
        }
    }
}
