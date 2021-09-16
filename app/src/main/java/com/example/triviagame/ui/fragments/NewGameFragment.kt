package com.example.triviagame.ui.fragments

import android.view.LayoutInflater
import com.example.triviagame.R
import com.example.triviagame.databinding.NewGameFragmentBinding
import com.example.triviagame.ui.fragments.BaseFragment

class NewGameFragment : BaseFragment<NewGameFragmentBinding>() {
    val gameFragment = GameFragment()
    override val bindingInflater: (LayoutInflater) -> NewGameFragmentBinding
        get() = NewGameFragmentBinding::inflate

    override fun addCallBack() {
        binding?.NewGameButton?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, gameFragment)
            ?.addToBackStack(null)
            ?.commit()
        }
    }

    override fun setup() {

    }

}