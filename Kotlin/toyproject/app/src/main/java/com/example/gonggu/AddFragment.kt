package com.example.gonggu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gonggu.databinding.FragmentAddPostBinding
import com.example.gonggu.model.writeDTO
import com.example.gonggu.retrofit.RetrofitManager

class AddFragment : Fragment(){
    //뷰 바인딩 사용
    private var mBinding: FragmentAddPostBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentAddPostBinding.inflate(inflater, container, false)

        //공구 등록하기 버튼이 클릭되었을 때
        binding.createGoggu.setOnClickListener{
            var author="진영posttest"
            var title=binding.etWriteTitle.text.toString()
            var description=binding.etWriteContent.text.toString()
            var link=binding.etLink.text.toString()
            var contact=binding.etOpenchatlink.text.toString()
            var date=binding.etDate.text.toString()
            var price=binding.etPrice.text.toString()
            RetrofitManager.instance.write(writeDTO(title, description, link, contact, price, date, author))
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding=null
    }
}