package com.tcoding.odemetakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var tipListe = ArrayList<OdemeTipi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rv.layoutManager = lm

        var o1 = OdemeTipi()
        var o2 = OdemeTipi()
        var o3 = OdemeTipi()

        o1.baslik = "Elektrik Faturasi"
        o2.baslik = "Su Faturasi"
        o3.baslik = "Kira"

        tipListe.add(o1)
        tipListe.add(o2)
        tipListe.add(o3)
        var o4 = OdemeTipi()
        var o5 = OdemeTipi()
        var o6 = OdemeTipi()

        o4.baslik = "Elektrik Faturasi"
        o5.baslik = "Su Faturasi"
        o6.baslik = "Kira"

        tipListe.add(o4)
        tipListe.add(o5)
        tipListe.add(o6)

        var o7 = OdemeTipi()
        var o8 = OdemeTipi()
        var o9 = OdemeTipi()

        o7.baslik = "Elektrik Faturasi"
        o8.baslik = "Su Faturasi"
        o9.baslik = "Kira"

        tipListe.add(o7)
        tipListe.add(o8)
        tipListe.add(o9)

        listControl(tipListe)

        binding.rv.adapter = TipAdapter(this, tipListe, ::itemClick)

        binding.btnYeniOdemeTipi.setOnClickListener {
            toYeniOdemeTipi()
        }

    }

    fun toYeniOdemeTipi(){
        val intent = Intent(this, YeniOdemeTipi::class.java)
        resultLauncher.launch(intent)

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::duzenleResult )

    fun duzenleResult(result: ActivityResult) {

        if (result.resultCode == RESULT_OK)
        {

        }
    }


    fun listControl(list : ArrayList<OdemeTipi>){
        if(list.size == 0){
            binding.ivEmptyList.visibility = View.VISIBLE
        }else {
            binding.ivEmptyList.visibility = View.GONE
        }
    }

    fun itemClick(position : Int){

    }
}