package com.example.gonggu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gonggu.recyclerview.MainItem
import com.example.gonggu.recyclerview.MainItemAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var bundle2=Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data = intent.getStringExtra("emailKey")
        Log.d("*****성공 success*****", "value:" + data)

        var bundle = Bundle()
        bundle.putString("email", "")

        //user name
        var myname=intent.getStringExtra("nameKey")


        bundle2.putString("author",myname)

        //bottom navigation
        bottom_navigationbar.setOnNavigationItemSelectedListener(this)
        //set default screen
        bottom_navigationbar.selectedItemId=R.id.action_home

    }

    //bottom navigation 화면 이동
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.action_home->{
                var MainDetailFragment = MainDetailFragment()
                MainDetailFragment.arguments=bundle2
                supportFragmentManager.beginTransaction().replace(R.id.main_content,MainDetailFragment).commit()
                return true
            }
            R.id.action_favorite->{
                var favoriteFragment = FavoriteFragment()
                favoriteFragment.arguments=bundle2
                supportFragmentManager.beginTransaction().replace(R.id.main_content,favoriteFragment).commit()
                return true
            }
            R.id.action_add->{
                var AddFragment = AddFragment()
                AddFragment.arguments=bundle2
                supportFragmentManager.beginTransaction().replace(R.id.main_content,AddFragment).commit()
                return true
            }
            R.id.action_mypage->{
                var MypageFragment = MypageFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,MypageFragment).commit()
                return true
            }
        }
        return false

    }
}