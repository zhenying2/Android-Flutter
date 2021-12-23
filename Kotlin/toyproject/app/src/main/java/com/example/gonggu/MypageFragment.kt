package com.example.gonggu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gonggu.databinding.ActivityMypageBinding

class MypageFragment : Fragment() {
    //뷰 바인딩 사용
    private var mBinding: ActivityMypageBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= ActivityMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding=null
    }
}