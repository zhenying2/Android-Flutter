package com.example.gonggu

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gonggu.databinding.ActivityLoginBinding
import com.example.gonggu.retrofit.RetrofitManager
import com.example.gonggu.utils.Constants.TAG
import com.example.gonggu.utils.RESPONSE_STATE
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import android.R.attr.data
import com.example.gonggu.DTOs.userDTO


class LoginActivity : AppCompatActivity() {

    final val RC_SIGN_IN = 1

    private var mBinding: ActivityLoginBinding? = null
    private val binding get() = mBinding!!

    lateinit var googleSignInClient : GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("572171105595-3aiu6ju9amf6bdjd1pmcatbljlidinjv.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        binding.loginBtn.setOnClickListener {
            googleLogin()
        }

    }

    private fun googleLogin() {
        val signInIntent = googleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account : GoogleSignInAccount = completedTask.getResult(ApiException::class.java)

            if (account != null) {
                val email = account?.email.toString()
                val displayName = account?.displayName.toString()
                val photo = account?.photoUrl.toString()

                Log.d("*****성공 success*****", email)
                Log.d("*****성공 success*****", displayName)
                Log.d("*****성공 success*****", photo)

                // addUseres API 호출
                val user = userDTO(email, displayName, photo)
                RetrofitManager.instance.addUsers(user)

                // 사용자 이메일 넘기기
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("emailKey", email)
                intent.putExtra("nameKey", displayName)
                intent.putExtra("photoKey", photo)
                startActivity(intent)
                finish()
            }

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("failed", "signInResult:failed code=" + e.statusCode)
        }
    }
}