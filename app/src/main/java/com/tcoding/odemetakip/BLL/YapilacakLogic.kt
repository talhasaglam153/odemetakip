package com.tcoding.odemetakip.BLL

import android.content.Context
import com.tcoding.odemetakip.DAL.YapilacakIslemler
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi

class YapilacakLogic {

    companion object {

        fun ekle(context : Context, odemeTipi : OdemeTipi){
            val yapilacakIslemler = YapilacakIslemler(context)
            yapilacakIslemler.tipEkle(odemeTipi)
        }

        fun tumOdemeTipleriniGetir(context: Context) : ArrayList<OdemeTipi> {

            return YapilacakIslemler(context).odemeTipleriniGetir()
        }

    }


}