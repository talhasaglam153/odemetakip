package com.tcoding.odemetakip.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.R
import com.tcoding.odemetakip.databinding.ActivityYeniOdemeTipiBinding

class YeniOdemeTipi : AppCompatActivity() {
    lateinit var  binding : ActivityYeniOdemeTipiBinding
    lateinit var odemePeriyod : ArrayList<String>
    var odemeTipi: OdemeTipi? = null
    var odemePeriyodPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYeniOdemeTipiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        odemeTipi =intent.getSerializableExtra("odemeTipi") as OdemeTipi?

        odemeTipiKontrol(odemeTipi)
        spinner()

        binding.btnKaydet.setOnClickListener {
            odemeTipi = OdemeTipi()
            if(binding.tvOdemeTipBaslik.text.toString().isEmpty()){
                Toast.makeText(this,"Lütfen Bir Ödeme Tipi Girdiğinizden Emin Olun", Toast.LENGTH_SHORT).show()
            }else{




            odemeTipi!!.Baslik = binding.tvOdemeTipBaslik.text.toString()
            odemeTipi?.PeriyodGunu = binding.tvOdemeTipPeriyodGunu.text.toString()
            if(odemePeriyodPosition == 0){
                odemeTipi?.PeriyodGunu = "Belirlenmedi"
            }

            odemeTipi?.Periyod = odemePeriyod.get(odemePeriyodPosition)

            val intent = Intent()

            intent.putExtra("odemeTipi", odemeTipi)

            setResult(RESULT_OK, intent)
            finish()
        }

        }



    }



    fun odemeTipiKontrol(odemeTipi : OdemeTipi?){
        if(odemeTipi == null){
            binding.btnSil.visibility = View.GONE
        }
    }



    fun spinner(){

        odemePeriyod = arrayListOf("Belirlenmedi", "Yıllık", "Aylık", "Haftalık")

        binding.spinner.adapter = ArrayAdapter<String>(this,
            R.layout.support_simple_spinner_dropdown_item, odemePeriyod)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.textViewDd.text = "Ödeme Periyodu : ${odemePeriyod.get(position)}"
                odemePeriyodPosition = position
                binding.tvOdemeTipPeriyodGunu.isEnabled = position != 0
                if(position != 0){
                    binding.tvUyari.visibility = View.GONE
                }else {
                    binding.tvUyari.visibility = View.VISIBLE
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

}