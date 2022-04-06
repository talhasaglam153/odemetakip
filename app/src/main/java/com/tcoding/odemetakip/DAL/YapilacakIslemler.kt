package com.tcoding.odemetakip.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.tcoding.odemetakip.OdemeTipi.Odeme
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi

class YapilacakIslemler(context : Context) {

    var yapilacakDatabase :SQLiteDatabase? = null
    var dbOpenHelper : DatabaseOpenHelper

    init{
        dbOpenHelper = DatabaseOpenHelper(context, "OdemeTakipDB", null, 1)
    }


    fun open() {
        yapilacakDatabase = dbOpenHelper.writableDatabase
    }

    fun close() {
        if(yapilacakDatabase != null && yapilacakDatabase!!.isOpen) {
            yapilacakDatabase!!.close()
        }
    }

    fun odemeEkle(odeme: Odeme) : Long{

        val cv = ContentValues()
        cv.put("DayOfMonth", odeme.DayOfMonth)
        cv.put("Month", odeme.Month)
        cv.put("Year", odeme.Year)
        cv.put("Tutar", odeme.Tutar)
        cv.put("OdemeTipId", odeme.OdemeTipId)
        open()
        val eklenenKayit = yapilacakDatabase!!.insert("Odeme", null, cv)
        close()
        return eklenenKayit
    }

    fun yapilacakSil(id : Int) {
        open()
        yapilacakDatabase!!.delete("Odeme", "Id = ?", arrayOf(id.toString()))
        close()
    }

    fun tipEkle(odemeTipi: OdemeTipi) : Long{

        val cv = ContentValues()
        cv.put("Baslik", odemeTipi.Baslik)
        cv.put("Periyod", odemeTipi.Periyod)
        cv.put("PeriyodGunu", odemeTipi.PeriyodGunu)
        open()
        val eklenenKayit = yapilacakDatabase!!.insert("OdemeTipi", null, cv)

        close()
        return eklenenKayit
    }

    private fun odemeGetir(odemeTipId: Int) : Cursor {

        val sorgu = "Select * from Odeme Where OdemeTipId = $odemeTipId "
        return yapilacakDatabase!!.rawQuery(sorgu, null)

    }

    @SuppressLint("Range")
    fun odemeListeGetir(odemeTipId : Int) : ArrayList<Odeme>{

        val odemeList = ArrayList<Odeme>()
        var odeme : Odeme
        open()

        var c : Cursor = odemeGetir(odemeTipId)

        if(c.moveToFirst()) {

            do {
                odeme = Odeme()

                // yapilacak.Baslik = c.getString(1)
                // 82. satırda ki gibi yapmamama sebebimiz
                odeme.Id = c.getInt(0)
                odeme.DayOfMonth = c.getInt(1)
                odeme.Month = c.getInt(2)
                odeme.Year = c.getInt(3)
                odeme.Tutar = c.getInt(4)
                odeme.OdemeTipId = c.getInt(5)


                odemeList.add(odeme)
            }while (c.moveToNext())
        }


        close()
        return odemeList
    }

    private fun odemeTipiGetir() : Cursor {

        val sorgu = "Select * from OdemeTipi"
        return yapilacakDatabase!!.rawQuery(sorgu, null)

    }

    @SuppressLint("Range")
    fun odemeTipleriniGetir() : ArrayList<OdemeTipi>{

        val odemeList = ArrayList<OdemeTipi>()
        var odemeTipi : OdemeTipi
        open()

        var c : Cursor = odemeTipiGetir()

        if(c.moveToFirst()) {

            do {
                odemeTipi = OdemeTipi()

                // yapilacak.Baslik = c.getString(1)
                // 82. satırda ki gibi yapmamama sebebimiz
                odemeTipi.Id = c.getInt(0)
                odemeTipi.Baslik = c.getString(c.getColumnIndex("Baslik"))
                odemeTipi.Periyod = c.getString(c.getColumnIndex("Periyod"))
                odemeTipi.PeriyodGunu = c.getString(c.getColumnIndex("PeriyodGunu"))


                odemeList.add(odemeTipi)
            }while (c.moveToNext())
        }


        close()
        return odemeList
    }




}