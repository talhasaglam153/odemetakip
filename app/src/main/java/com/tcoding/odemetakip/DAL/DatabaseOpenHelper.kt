package com.tcoding.odemetakip.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(context : Context, name : String, factory : SQLiteDatabase.CursorFactory?, version : Int) : SQLiteOpenHelper(context, name , factory, version) {
    override fun onCreate(p0: SQLiteDatabase) {



       val sorgu = "CREATE TABLE OdemeTipi(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Baslik TEXT, Periyod TEXT, PeriyodGunu TEXT)"

       val sorgu2 = "CREATE TABLE Odeme(Id integer primary key autoincrement, DayOfMonth integer , Month integer, Year integer , Tutar integer, OdemeTipId integer, FOREIGN KEY (OdemeTipId) REFERENCES OdemeTipi (Id));";

        p0.execSQL(sorgu)
        p0.execSQL(sorgu2)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}