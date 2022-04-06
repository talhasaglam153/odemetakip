package com.tcoding.odemetakip.BLL

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import android.widget.DatePicker
import java.util.*

class GuncelTarihAl(var context : Context) : DatePickerDialog.OnDateSetListener {

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    var dateString : String = "bos"

    fun guncelYil() : Int{
        return calendar.get(Calendar.YEAR)
    }


    fun guncelAy() : Int {
        return calendar.get(Calendar.MONTH) + 1
    }

    fun guncelGun() : Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }


    override fun onDateSet(p0: DatePicker?, yil: Int, ay: Int, gun: Int)  {
      dateString   = gun.toString() + "/" + ay.toString() + "/" + yil.toString()
    }

}