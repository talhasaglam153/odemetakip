package com.tcoding.odemetakip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.databinding.ActivityTipDetayBinding

class TipDetayActivity : AppCompatActivity() {
    lateinit var binding : ActivityTipDetayBinding
    lateinit var odemeTipi: OdemeTipi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        odemeTipi = intent.getSerializableExtra("odemeTipi") as OdemeTipi

        binding.etTipBaslik.text = odemeTipi.Baslik
        binding.etTipOdemePeriyodu.text = odemeTipi.Periyod
        binding.etTipPeriyodOdemeGunu.text = odemeTipi.PeriyodGunu


    }



}