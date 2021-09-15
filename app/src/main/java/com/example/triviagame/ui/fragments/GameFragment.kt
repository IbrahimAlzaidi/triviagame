package com.example.triviagame.ui.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.triviagame.R
import com.example.triviagame.databinding.GameFragmentBinding

class GameFragment : BaseFragment<GameFragmentBinding>() {
    override val bindingInflater: (LayoutInflater) -> GameFragmentBinding
        get() = GameFragmentBinding :: inflate

    override fun addCallBack() {

    }

    override fun setup() {

    }

}