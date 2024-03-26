package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter

import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater, container, false)

        viewModel.sepetListesi.observe(viewLifecycleOwner){
            val sepetAdapter = SepetAdapter(requireContext(),it,viewModel)
            binding.SepetRv.adapter = sepetAdapter
            binding.SepetRv.layoutManager = GridLayoutManager(requireContext(),2)
            binding.textViewToplamFiyat.text = "Ödenecek Tutar : ${viewModel.hesaplaToplamTutar()} TL"
        }




        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.destek -> {
                    // FAB tıklandığında yapılacak işlemler
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisDestek2) }
                }
                R.id.anasayfa -> {
                    view?.let { Navigation.findNavController(it).navigate(R.id.gecisAnasayfa) }
                }

                else -> {

                }
            }
            true
        }

        binding.buttonSiparis.setOnClickListener {
            //SepetRv görünmez yap


            //eğer sepet boş ise textViewSepetBos görünür yap
            if(viewModel.sepetListesi.value?.isEmpty() == true){
                Snackbar.make(it,"Sepetiniz Boş",Snackbar.LENGTH_SHORT).show()
            }
            else {
                binding.SepetRv.visibility = View.GONE
                //animation_moto görünür yap
                binding.animationMoto.visibility = View.VISIBLE
                //textViewSepetBos görünmez yap
                binding.buttonSiparis.visibility = View.GONE
                binding.textViewToplamFiyat.visibility = View.GONE
                binding.textViewYolda.visibility = View.VISIBLE
            }

        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetViewModel by viewModels()

        viewModel = tempViewModel


    }




    override fun onResume() {
        super.onResume()
        viewModel.sepetYukle()
        binding.textViewToplamFiyat.text = viewModel.hesaplaToplamTutar().toString() + " TL"
        viewModel.sepetListesi.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.textViewSepetBos.text = "Sepetiniz Boş"
            }
        }




    }


}

