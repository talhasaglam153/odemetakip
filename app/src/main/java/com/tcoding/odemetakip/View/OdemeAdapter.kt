package com.tcoding.odemetakip.View

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.odemetakip.OdemeTipi.Odeme
import com.tcoding.odemetakip.R

class OdemeAdapter(var context : Context,var odemeList: ArrayList<Odeme>, var itemClick : (position : Int)-> Unit) : RecyclerView.Adapter<OdemeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.detay_cardview, parent, false)
        return OdemeViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: OdemeViewHolder, position: Int) {

        holder.bindData(context, odemeList.get(position))

    }

    override fun getItemCount(): Int {
        return odemeList.size
    }
}