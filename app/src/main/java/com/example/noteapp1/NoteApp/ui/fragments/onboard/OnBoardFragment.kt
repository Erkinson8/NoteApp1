package com.example.noteapp1.NoteApp.ui.fragments.onboard

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp1.NoteApp.ui.adapter.OnBoardViewpagerAdapter
import com.example.noteapp1.R
import com.example.noteapp1.databinding.FragmentOnBoardBinding


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var dotsLayout: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext()
            .getSharedPreferences("onBoardPrefs", Context.MODE_PRIVATE)
        val onBoardShown = sharedPreferences.getBoolean("onBoardShown", false)
        if (onBoardShown) {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
            return
        }
        initialize()
        setupListener()
        dotsLayout = binding.point
        createDots()
    }
    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewpagerAdapter(this@OnBoardFragment)
    }

    private fun setupListener() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.nextTxt.visibility = View.GONE
                } else {
                    binding.nextTxt.visibility = View.VISIBLE
                }
                updateDots(position)
            }
        })
        binding.nextTxt.setOnClickListener {
            val currentPage = binding.viewPager.currentItem
            if (currentPage == 0) {
                binding.viewPager.setCurrentItem(2, true)
            } else {
                val nextPage = currentPage + 1
                binding.viewPager.setCurrentItem(nextPage, true)
            }
        }
    }
    private fun createDots() {
        val dotsCount = 3
        val dots = arrayOfNulls<ImageView>(dotsCount)

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(requireContext())
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_point
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dots[i], params)
        }
    }
    private fun updateDots(position: Int) {
        val dotsCount = dotsLayout.childCount

        for (i in 0 until dotsCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (i == position) R.drawable.ic_point else R.drawable.ic_point2
                )
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        sharedPreferences.edit().putBoolean("onBoardShown", true).apply()
    }
}