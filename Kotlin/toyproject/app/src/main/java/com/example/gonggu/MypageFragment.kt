package com.example.gonggu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gonggu.DTOs.userDTO
import com.example.gonggu.databinding.ActivityMypageBinding
import com.example.gonggu.retrofit.RetrofitManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MypageFragment : Fragment() {
    //뷰 바인딩 사용
    private var mBinding: ActivityMypageBinding? = null
    private val binding get() = mBinding!!
    lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= ActivityMypageBinding.inflate(inflater, container, false)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        var mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)

        googleSignInClient = GoogleSignIn.getClient(activity,gso)

        binding.logoutBtn.setOnClickListener {
            mGoogleSignInClient.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding=null
    }
}