package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekDetayViewModel @Inject constructor(var krepo:YemeklerRepository) : ViewModel() {


    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int){
        val kullanici_adi = "Doruk"
        CoroutineScope(Dispatchers.Main).launch {
            krepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
        Log.e("Sepete Ekle","$yemek_adi $yemek_resim_adi $yemek_fiyat $yemek_siparis_adet")
    }

    //



}