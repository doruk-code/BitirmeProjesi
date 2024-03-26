package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Sepet
import com.example.bitirmeprojesi.databinding.CardTasarimSepetBinding
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.example.bitirmeprojesi.utils.gecis
import com.google.android.material.snackbar.Snackbar


class SepetAdapter(
    var mContext: Context,
    var sepetListe: List<Sepet>,
    var viewModel: SepetViewModel) : RecyclerView.Adapter<SepetAdapter.CardTasarimSepet>() {

    inner class CardTasarimSepet(var tasarimSepet: CardTasarimSepetBinding) :
        RecyclerView.ViewHolder(tasarimSepet.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimSepet {
        val binding = CardTasarimSepetBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimSepet(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimSepet, position: Int) {
        val sepet = sepetListe.get(position)
        val b = holder.tasarimSepet

        b.textViewSepetAd.text = sepet.yemek_adi
        b.textViewAdet.text = sepet.yemek_siparis_adet.toString() + " Adet"
        b.textViewSepetFiyat.text = sepet.yemek_fiyat.toString() + " TL"

        b.imageViewSil.setOnClickListener {
            viewModel.sepettenYemekSil(sepet.sepet_yemek_id, "Doruk")



            Snackbar.make(it, "${sepet.yemek_adi} Sepetten Silindi", Snackbar.LENGTH_SHORT).show()

        }





        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(150, 150).into(b.igSepet)



    }

    override fun getItemCount(): Int {
        return sepetListe.size
    }

}