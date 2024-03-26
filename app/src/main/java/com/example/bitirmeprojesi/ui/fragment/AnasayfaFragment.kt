package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.ui.adapter.YemeklerAdapter
import com.example.bitirmeprojesi.utils.gecis
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)



        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val yemeklerAdapter = YemeklerAdapter(requireContext(),it,viewModel)
            binding.yemeklerRv.adapter = yemeklerAdapter
        }
        binding.yemeklerRv.layoutManager = GridLayoutManager(requireContext(),2)


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fab -> {
                    // FAB tıklandığında yapılacak işlemler
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisSepet) }
                }
                R.id.anasayfa -> {
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisAnasayfa) }
                }
                R.id.destek -> {
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisDestek) }
                }
                else -> {

                }
            }
            true
        }
       return binding.root




    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }
}