package com.tcoding.odemetakip.View

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.R

class TipViewHolder(itemView: View, itemOnClick: (position : Int) -> Unit, odemeEkleClick: (position : Int)-> Unit) : RecyclerView.ViewHolder(itemView) {

    var tv_tipBaslik : TextView
    var tv_tipOdemePeriyodu : TextView
    var tv_tipPeriyodOdemeGunu : TextView
    var btn_odemeEkle : Button

    init {
        tv_tipBaslik = itemView.findViewById(R.id.tv_tip_baslik)
        tv_tipOdemePeriyodu = itemView.findViewById(R.id.tv_tipOdemePeriyodu)
        tv_tipPeriyodOdemeGunu = itemView.findViewById(R.id.tv_tipPeriyodOdemeGunu)
        btn_odemeEkle  = itemView.findViewById(R.id.btn_odeme_ekle)

        itemView.setOnClickListener {
            itemOnClick(adapterPosition)
        }

        btn_odemeEkle.setOnClickListener {
            odemeEkleClick(adapterPosition)
        }

    }

    fun bindData(context : Context, odemeTipi : OdemeTipi){
        tv_tipBaslik.text = odemeTipi.Baslik
        tv_tipOdemePeriyodu.text = odemeTipi.Periyod
        tv_tipPeriyodOdemeGunu.text = odemeTipi.PeriyodGunu
    }

}