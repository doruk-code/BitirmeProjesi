package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.Sepet
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject



@HiltViewModel
class AnasayfaViewModel @Inject constructor(var krepo:YemeklerRepository) : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private val _aramaMetni = MutableLiveData<String>()
    private val _yemekler = MutableLiveData<List<Yemekler>>()
    val aramaMetni: LiveData<String>
        get() = _aramaMetni
    val yemekler: LiveData<List<Yemekler>>
        get() = _yemekler


    init {
        yemekleriYukle()
    }

    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int){
        val kullanici_adi = "Doruk"
        CoroutineScope(Dispatchers.Main).launch {
            krepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value = krepo.yemekleriYukle()
        }
    }








}