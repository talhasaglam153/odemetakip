package com.tcoding.odemetakip

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi

class TipAdapter(var context: Context,var tipList : ArrayList<OdemeTipi>, var itemOnClick :(position : Int) -> Unit) : RecyclerView.Adapter<TipViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.odeme_tipi_cardview, parent, false)
        return TipViewHolder(view, itemOnClick)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bindData(context, tipList.get(position))
    }

    override fun getItemCount(): Int {
        return tipList.size
    }
}