package com.example.nolfi.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;

public class MainPageFragment1  extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;

    boolean i=true;
    ImageView imageview;
    LinearLayout list_item;
    ImageView strawberry;

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
        View v=inflater.inflate(R.layout.fragment_mainpage1,container,false);
        list_item=v.findViewById(R.id.main_content);
        strawberry=v.findViewById(R.id.strawberry);
        imageview = v.findViewById(R.id.scrab);
        imageview.setOnClickListener(this);
        list_item.setOnClickListener(this);
        strawberry.setOnClickListener(this);
        return v;
    }

    public void onClick(View v){
        switch (v.getId()){
            //fragment 변환 (list 클릭시)
            case R.id.item_list:{
                activity.onFragmentChange(4);
            }
            case R.id.strawberry:{
                activity.onFragmentChange(4);
            }
            //scrab 클릭시 -> 별 바꿈
            case R.id.scrab:{
                if (i==true){
                    imageview.setImageResource(R.drawable.scrab);
                    i=false;
                }
                else{
                    imageview.setImageResource(R.drawable.cl_scrab);
                    i=true;
                }
            }
        }
    }
}