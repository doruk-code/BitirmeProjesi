package com.example.bitirmeprojesi.data.repo


import com.example.bitirmeprojesi.data.datasource.YemeklerDataSource
import com.example.bitirmeprojesi.data.entity.Sepet
import com.example.bitirmeprojesi.data.entity.Yemekler

class YemeklerRepository(var kds:YemeklerDataSource) {


    suspend fun sepetYukle(kullanici_adi:String) : List<Sepet> = kds.sepetYukle(kullanici_adi)

    suspend fun sepettenYemekSil(sepet_yemek_id:Int,kullanici_adi:String) = kds.sepettenYemekSil(sepet_yemek_id,kullanici_adi)


    suspend fun yemekleriYukle() : List<Yemekler> = kds.yemekleriYukle()


    suspend fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) = kds.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
}