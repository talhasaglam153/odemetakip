package com.tcoding.odemetakip.View

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tcoding.odemetakip.OdemeTipi.Odeme
import com.tcoding.odemetakip.OdemeTipi.OdemeTipi
import com.tcoding.odemetakip.databinding.ActivityOdemeEkle2Binding
import java.util.*

class OdemeEkle2 : AppCompatActivity() {

    lateinit var binding : ActivityOdemeEkle2Binding
    var odeme : Odeme? = null
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    lateinit var odemeTipi: OdemeTipi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOdemeEkle2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        odeme = Odeme()
        guncelTarihAyarla()

        odemeTipi = intent.getSerializableExtra("odemeTipi") as OdemeTipi
        binding.etTipBaslik.setText(odemeTipi.Baslik)

        binding.btnDate.setOnClickListener {
           btnDateClick()
        }

        binding.btnOdemeKaydet.setOnClickListener {


            if(binding.etOdemeTutari.text.toString().isEmpty()) {
               binding.btnDate.isEnabled = false
            }else {
                val intent = Intent()

                intent.putExtra("odeme", odeme)

                setResult(RESULT_OK, intent)
                finish()
            }

        }

    }

    fun guncelTarihAyarla(){
        binding.btnDate.text = dateOfMonth.toString() + "/" + month.toString() + "/" + year.toString()
    }

    fun btnDateClick() {
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mAy, mGun ->
            binding.btnDate.setText("" + mGun + "/" + mAy + "/" + mYear)
            odeme?.Month = mAy
            odeme?.Year = mYear
            odeme?.DayOfMonth = mGun
            odeme?.Tutar = binding.etOdemeTutari.text.toString().toInt()
        },year, month, dateOfMonth)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}