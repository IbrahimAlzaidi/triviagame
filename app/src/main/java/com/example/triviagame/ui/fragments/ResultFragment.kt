package com.example.triviagame.ui.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.triviagame.databinding.ResultFragmentBinding

class ResultFragment : BaseFragment<ResultFragmentBinding>() {
    override val bindingInflater: (LayoutInflater) -> ResultFragmentBinding
        get() = ResultFragmentBinding :: inflate

    override fun addCallBack() {
    }

    override fun setup() {

    }

}