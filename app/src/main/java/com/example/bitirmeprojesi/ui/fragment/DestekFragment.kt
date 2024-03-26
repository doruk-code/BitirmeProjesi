package com.example.bitirmeprojesi.ui.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.example.bitirmeprojesi.databinding.FragmentDestekBinding
import com.example.bitirmeprojesi.ui.viewmodel.DestekViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DestekFragment : Fragment() {
    private lateinit var binding: FragmentDestekBinding
    private lateinit var viewModel: DestekViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDestekBinding.inflate(inflater, container, false)




        binding.btngonder.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "destek@sepette.com", null))
            startActivity(Intent.createChooser(mailIntent, "Mail Gönder..."))
        }


        binding.btngondercall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0850 000 00 00")
            startActivity(intent)
        }





        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.bitirmeprojesi.R.id.fab -> {
                    // FAB tıklandığında yapılacak işlemler
                    view?.let { Navigation.findNavController(it).navigate(com.example.bitirmeprojesi.R.id.gecisSepete) }
                }
                com.example.bitirmeprojesi.R.id.anasayfa -> {
                    view?.let { Navigation.findNavController(it).navigate(com.example.bitirmeprojesi.R.id.gectimanasayfa) }
                }
                else -> {

                }
            }
            true
        }






        return binding.root
    }


}