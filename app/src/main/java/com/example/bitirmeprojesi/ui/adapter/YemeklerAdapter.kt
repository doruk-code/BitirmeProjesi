package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.CardTasarimBinding
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections

import com.example.bitirmeprojesi.utils.gecis
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext: Context,
                      var yemeklerListesi:List<Yemekler>,
                      var viewModel: AnasayfaViewModel

)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {//0,1,2
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.textViewYemekAd.text = yemek.yemek_adi
        t.textViewYemekFiyat.text = yemek.yemek_fiyat.toString() + " TL"

        t.cardViewSatir.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.gecisDetay(yemek = yemek)
            Navigation.gecis(it,gecis)
        }

        t.imageViewSepet.setOnClickListener {
            viewModel.sepeteEkle(yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat,1)
            Snackbar.make(it,"${yemek.yemek_adi} sepete eklendi",Snackbar.LENGTH_SHORT).show()
        }




        Glide.with(mContext).load("http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}").into(t.imageViewYemek)

    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}