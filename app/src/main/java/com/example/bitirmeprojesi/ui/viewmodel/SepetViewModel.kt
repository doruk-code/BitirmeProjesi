package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.Sepet
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var krepo:YemeklerRepository): ViewModel() {
    var sepetListesi = MutableLiveData<List<Sepet>>()

    init {
        sepetYukle()
    }

    fun sepetYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepetListesi.postValue(krepo.sepetYukle("Doruk"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun hesaplaToplamTutar(): Int {
        var toplamTutar = 0
        sepetListesi.value?.forEach { sepetYemek ->
            toplamTutar += sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet
            Log.e("Toplam Tutar", sepetYemek.yemek_fiyat.toString() + sepetYemek.yemek_siparis_adet)
        }
        return toplamTutar

    }

    fun sepettenYemekSil(sepet_yemek_id:Int, kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            if(sepetListesi.value?.size == 1){
                krepo.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
                sepetListesi.value = emptyList()





            }else{
                krepo.sepettenYemekSil(sepet_yemek_id, kullanici_adi)

            }
            sepetYukle()
        }

    }
}

