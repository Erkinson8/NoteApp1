package com.example.noteapp1.NoteApp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp1.R
import com.example.noteapp1.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startTxt.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
        initialize()
    }
    private fun initialize() = with(binding){
        when (requireArguments().getInt(ARG_ON_BOARD_POSITION)){
            0->{
                onBoardTxt.text = "Очень удобный функционал"
                binding.animationOne.setAnimation(R.raw.animation_f)
                binding.animationOne.repeatCount = -1
                binding.animationOne.playAnimation()
            }
            1->{
                onBoardTxt.text = "Быстрый, качественный продукт"
                binding.animationOne.setAnimation(R.raw.animation_s)
                binding.animationOne.repeatCount = -1
                binding.animationOne.playAnimation()
            }
            2->{
                onBoardTxt.text = "Куча функций и интересных фишек"
                startTxt.text = "Начать работу"
                binding.animationOne.setAnimation(R.raw.animation_t)
                binding.animationOne.repeatCount = -1
                binding.animationOne.playAnimation()
            }

            else -> {}
        }
    }
    companion object {
        const val ARG_ON_BOARD_POSITION = "onBoard"
    }
}