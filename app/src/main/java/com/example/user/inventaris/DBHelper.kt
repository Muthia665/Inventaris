package com.example.user.inventaris

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper (ctx: Context) : ManagedSQLiteOpenHelper (ctx, "Pembaca.db", null, 1) {
    companion object {
        private var instances:DBHelper? = null
        @Synchronized
        fun getInstance (ctx: Context) : DBHelper{
            if (instances == null){
                instances = DBHelper(ctx.applicationContext)
            }
            return instances as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Model.TABLE_NAME,true,
            Model.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Model.NAMA to TEXT,
            Model.PENULIS to TEXT,
            Model.PENERBIT to TEXT,
            Model.TAHUN to TEXT,
            Model.KATEGORI to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Model.TABLE_NAME, true)
    }
}

// biar databasenya bisa dipake
val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)