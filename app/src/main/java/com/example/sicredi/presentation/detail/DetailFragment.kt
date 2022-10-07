package com.example.sicredi.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sicredi.databinding.FragmentDetailBinding
import com.example.sicredi.framework.imageLoader.ImageLoader
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val imageLoader: ImageLoader by inject()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewArg = args.detailViewArg
        binding.tvTitle.text = args.detailViewArg.name
        binding.tvDescription.text = args.detailViewArg.description

        binding.imageEvent.run {
            imageLoader.load(this, detailViewArg.imageUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}