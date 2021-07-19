package com.example.user.inventaris

class Model (var id:Long?,
             var nama:String?,
             var penulis:String?,
             var penerbit:String?,
             var tahun:String?,
             var kategori:String?) {

    companion object {
        val TABLE_NAME:String = "TABLE_NAME"
        val ID:String = "ID"
        val NAMA:String = "NAMA"
        val PENULIS:String = "PENULIS"
        val PENERBIT:String = "PENERBIT"
        val TAHUN:String = "TAHUN"
        val KATEGORI:String = "KATEGORI"

    }
}