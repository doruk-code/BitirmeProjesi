package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentYemekDetayBinding
import com.example.bitirmeprojesi.ui.viewmodel.YemekDetayViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {

    private lateinit var binding: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYemekDetayBinding.inflate(inflater, container, false)
        val bundle: YemekDetayFragmentArgs by navArgs()
        val gelenUrun = bundle.yemek

        var urunAdet = 1




        binding.buttonSepetEkle.setOnClickListener {
            val gidenFiyat = binding.textViewUrunFiyat.text.toString().substring(0, binding.textViewUrunFiyat.text.length - 3).toInt()


            viewModel.sepeteEkle(gelenUrun.yemek_adi, gelenUrun.yemek_resim_adi, gidenFiyat, urunAdet)

            Log.e("Sepet", "Sepete Eklendi ${gelenUrun.yemek_adi} $urunAdet adet")

            Snackbar.make(it, "${gelenUrun.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_SHORT).show()

        }

        binding.buttonUrunAdetArttir.setOnClickListener {
            urunAdet++
            binding.textViewMiktar.text = urunAdet.toString() + " Adet"
            val toplamFiyat = urunAdet * gelenUrun.yemek_fiyat
            binding.textViewUrunFiyat.text = toplamFiyat.toString() + " TL"

        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fab -> {
                    // FAB tıklandığında yapılacak işlemler
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisSep) }
                }
                R.id.anasayfa -> {
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisAnasay) }
                }
                else -> {

                }
            }
            true
        }


        binding.buttonUrunAdetAzalt.setOnClickListener {
            if (urunAdet > 1) {
                urunAdet--
                binding.textViewMiktar.text = urunAdet.toString() + " adet"
                //eğer miktar azaltılırsa fiyatıda azalt
                val toplamFiyat = binding.textViewUrunFiyat.text
                val toplamFiyatInt = toplamFiyat.toString().substring(0, toplamFiyat.length - 3).toInt()
                val yeniFiyat = toplamFiyatInt - gelenUrun.yemek_fiyat
                binding.textViewUrunFiyat.text = yeniFiyat.toString() + " TL"


            }
            else {
                Snackbar.make(it, "En az 1 adet seçmelisiniz", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.textViewUrunAdi.text = gelenUrun.yemek_adi
        binding.textViewUrunFiyat.text = gelenUrun.yemek_fiyat.toString() + " TL"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenUrun.yemek_resim_adi}"
        Glide.with(requireContext()).load(url).into(binding.imageViewUrun)
        return binding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekDetayViewModel by viewModels()
        viewModel = tempViewModel





    }

}