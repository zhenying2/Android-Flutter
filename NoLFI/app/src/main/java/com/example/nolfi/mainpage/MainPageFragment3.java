package com.example.nolfi.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;
import com.example.nolfi.mainpage.storeclick.frag_adapter;
import com.example.nolfi.mainpage.storeclick.frag_all;
import com.example.nolfi.mainpage.storeclick.frag_soldout;
import com.example.nolfi.mainpage.storeclick.frag_trading;
import com.google.android.material.tabs.TabLayout;

public class MainPageFragment3 extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;
    TabLayout tabLayout;
    ViewPager viewPager;
    frag_adapter adapter;
    ImageView backarrow;

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //현재 소속된 액티비티를 메인 액티비티로 한다.
        activity = (MainActivity) getActivity();
    }
    @Override public void onDetach() {
        super.onDetach();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mainpage3,container,false);

        tabLayout=v.findViewById(R.id.tabs);
        viewPager=v.findViewById(R.id.view_pager);
        adapter=new frag_adapter(activity.getSupportFragmentManager(),1);
        backarrow=v.findViewById(R.id.arrow_back3);
        backarrow.setOnClickListener(this);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new frag_all());
        adapter.addFragment(new frag_trading());
        adapter.addFragment(new frag_soldout());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("sell");
        tabLayout.getTabAt(1).setText("group buying");
        tabLayout.getTabAt(2).setText("donate");

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arrow_back3:
                //activity.onFragmentChange(1);
                break;
        }
    }
}
