package com.example.gonggu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gonggu.databinding.FragmentMainDetailBinding
import com.example.gonggu.recyclerview.MainItem
import com.example.gonggu.recyclerview.MainItemAdapter
import kotlinx.android.synthetic.main.fragment_main_detail.*

class MainDetailFragment : Fragment(){
    //뷰 바인딩 사용
    private var mBinding: FragmentMainDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentMainDetailBinding.inflate(inflater, container, false)

        //test recycler view
        val MainItemList= arrayListOf(
            MainItem("글제목1", "저자", "공구링크", "공구가격", "공구기간"),
            MainItem("글제목2", "저자", "공구링크", "공구가격", "공구기간"),
            MainItem("글제목3", "저자", "공구링크", "공구가격", "공구기간"),
            MainItem("글제목4", "저자", "공구링크", "공구가격", "공구기간")
        )

        mBinding!!.rvItems.layoutManager= LinearLayoutManager(activity)
        mBinding!!.rvItems.setHasFixedSize(true)

        mBinding!!.rvItems.adapter= MainItemAdapter(MainItemList)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding=null
    }
}