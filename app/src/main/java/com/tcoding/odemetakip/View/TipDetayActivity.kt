package com.tcoding.odemetakip.View

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcoding.odemetakip.BLL.YapilacakLogic
import com.tcoding.odemetakip.OdemeTipi.Odeme
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.databinding.ActivityTipDetayBinding

class TipDetayActivity : AppCompatActivity() {
    lateinit var binding : ActivityTipDetayBinding
    lateinit var odemeTipi: OdemeTipi
    var odemeTipId : Int = 0
    var odemeListe = ArrayList<Odeme>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        odemeTipi = intent.getSerializableExtra("odemeTipi") as OdemeTipi

        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.rvDetay.layoutManager = lm
        odemeListe = YapilacakLogic.tumOdemeleriGetir(this,odemeTipi.Id)

        listControl(odemeListe)

        binding.rvDetay.adapter = OdemeAdapter(this, odemeListe, ::itemClick)




        binding.etTipBaslik.text = odemeTipi.Baslik
        binding.etTipOdemePeriyodu.text = odemeTipi.Periyod
        binding.etTipPeriyodOdemeGunu.text = odemeTipi.PeriyodGunu
        odemeTipId = odemeTipi.Id
        binding.fab.setOnClickListener {
            val intent = Intent(this, OdemeEkle2::class.java)
            intent.putExtra("odemeTipi", odemeTipi)
            resultLauncher.launch(intent)
        }


    }

    fun itemClick(position : Int) {
        var alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Silmek istediğinize Emin misiniz ? ")
        alertDialogBuilder.setPositiveButton("Evet",DialogInterface.OnClickListener { dialogInterface, i ->
            odemeListe = YapilacakLogic.tumOdemeleriGetir(this, odemeTipi.Id)
            if(odemeListe.size > 0){
                YapilacakLogic.odemeSil(this, odemeListe.get(position))
            }else {
                binding.imageViewList.visibility = View.VISIBLE
            }
            binding.rvDetay.adapter = OdemeAdapter(this, odemeListe, ::itemClick)
            binding.rvDetay.adapter!!.notifyDataSetChanged()
        })

        alertDialogBuilder.setNegativeButton("Hayır", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        alertDialogBuilder.show()
    }



    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::duzenleResult )

    @SuppressLint("NotifyDataSetChanged")
    fun duzenleResult(result: ActivityResult) {

        if (result.resultCode == RESULT_OK)
        {
            val odeme = result.data!!.getSerializableExtra("odeme") as Odeme
            odeme.OdemeTipId = odemeTipId
            YapilacakLogic.odemeEkle(this, odeme, odemeTipId)

            odemeListe = YapilacakLogic.tumOdemeleriGetir(this, odeme.OdemeTipId)
            binding.rvDetay.adapter = OdemeAdapter(this, odemeListe, ::itemClick)

            listControl(odemeListe)
            binding.rvDetay.adapter!!.notifyDataSetChanged()

            Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()

        }
    }

    fun listControl(list : ArrayList<Odeme>){
        if(list.size == 0){
            binding.imageViewList.visibility = View.VISIBLE
        }else {
            binding.imageViewList.visibility = View.GONE
        }
    }



}