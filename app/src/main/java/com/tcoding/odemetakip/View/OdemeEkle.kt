package com.tcoding.odemetakip.View


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tcoding.odemetakip.BLL.GuncelTarihAl

import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.databinding.ActivityOdemeEkleBinding

class OdemeEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeEkleBinding
    lateinit var odemeTipi : OdemeTipi



    var guncelTarihAl  = GuncelTarihAl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)



        guncelTarih()

        binding.btnDate.setOnClickListener {
           // tarihSec()

        }


    }

    fun guncelTarih(){
        val stringTarih = guncelTarihAl.guncelGun().toString() + "/" + guncelTarihAl.guncelAy().toString() + "/" + guncelTarihAl.guncelYil().toString()
        binding.btnDate.text = stringTarih
    }

    /*fun tarihSec(){
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mAy, mGun ->
            binding.btnDate.setText("" + mGun + "/" + mAy + "/" + mYear)
        },year, month, dateOfMonth)
        dpd.show()

    }*/

}