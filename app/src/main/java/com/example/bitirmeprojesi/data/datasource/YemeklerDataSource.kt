package com.example.bitirmeprojesi.data.datasource

import com.example.bitirmeprojesi.data.entity.Sepet
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource (var ydo:YemeklerDao) {


    suspend fun sepetYukle(kullanici_adi: String) : List<Sepet> =
        withContext(Dispatchers.IO) {
            return@withContext ydo.sepetYukle(kullanici_adi).sepet_yemekler
        }

    suspend fun yemekleriYukle(): List<Yemekler> =
        withContext(Dispatchers.IO) {
            return@withContext ydo.yemekleriYukle().yemekler
        }


    suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String) = ydo.sepettenYemekSil(sepet_yemek_id, "Doruk")








    suspend fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = ydo.sepeteYemekEkle(
        yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi
    )
}

