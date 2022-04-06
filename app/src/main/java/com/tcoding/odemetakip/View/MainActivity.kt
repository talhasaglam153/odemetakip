package com.tcoding.odemetakip.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcoding.odemetakip.BLL.YapilacakLogic
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

        tipListe = YapilacakLogic.tumOdemeTipleriniGetir(this)
        listControl(tipListe)

        binding.rv.adapter = TipAdapter(this, tipListe, ::itemClick, ::odemeEkleClick)

        binding.btnYeniOdemeTipi.setOnClickListener {
            toYeniOdemeTipi()
        }


    }

    fun odemeEkleClick(position: Int) {
        val intent = Intent(this, TipDetayActivity::class.java)
        intent.putExtra("odemeTipi",tipListe.get(position))
        startActivity(intent)
    }


    fun toYeniOdemeTipi(){
        val intent = Intent(this, YeniOdemeTipi::class.java)
        resultLauncher.launch(intent)

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::duzenleResult )

    @SuppressLint("NotifyDataSetChanged")
    fun duzenleResult(result: ActivityResult) {

        if (result.resultCode == RESULT_OK)
        {
            val tip = result.data!!.getSerializableExtra("odemeTipi") as OdemeTipi
            YapilacakLogic.ekle(this, tip)

            tipListe = YapilacakLogic.tumOdemeTipleriniGetir(this)
            binding.rv.adapter = TipAdapter(this, tipListe, ::itemClick, ::odemeEkleClick)

            listControl(tipListe)
            binding.rv.adapter!!.notifyDataSetChanged()

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
        val intent = Intent(this, TipDetayActivity::class.java)
        intent.putExtra("odemeTipi",tipListe.get(position))
        startActivity(intent)
    }




}