package com.tcoding.odemetakip.View

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.odemetakip.OdemeTipi.Odeme
import com.tcoding.odemetakip.R

class OdemeViewHolder(itemView: View, itemClick:(position: Int)-> Unit) : RecyclerView.ViewHolder(itemView) {

    var tv_tarih : TextView
    var tv_tutar : TextView

    init {
        tv_tarih = itemView.findViewById<TextView>(R.id.tv_tarih)
        tv_tutar = itemView.findViewById<TextView>(R.id.tv_tutar)
        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }
    }

    fun bindData(context : Context, odeme: Odeme){
        tv_tarih.setText(odeme.DayOfMonth.toString() + "/"+ odeme.Month.toString() + "/" + odeme.Year)
        tv_tutar.setText(odeme.Tutar.toString())
    }


}