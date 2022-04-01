package com.example.nolfi.mainpage;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

public class MainPageFragment2 extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;

    int count; //dialog 띄우는 횟수 제한 기준 변수
    boolean i=true;
    ImageView imageview;
    LinearLayout layout_store;
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
        View v=inflater.inflate(R.layout.fragment_mainpage2,container,false);
        count=0;
        layout_store=v.findViewById(R.id.layout_store);
        layout_store.setOnClickListener(this);
        imageview=v.findViewById(R.id.icon_phone);
        imageview.setOnClickListener(this);

        backarrow=v.findViewById(R.id.arrow_back2);
        backarrow.setOnClickListener(this);
        return v;
    }
    @SuppressLint("ResourceType")
    public void onClick(View v){
        switch (v.getId()){
            //fragment 변환 (layout_store 클릭시)
            case R.id.layout_store:{
                activity.onFragmentChange(5);
            }
            //전화모양 클릭시 -> 다이얼로그창 띄움
            case R.id.icon_phone: {
                if (count % 2 == 0) {
                    count = 1;
                    // 데이터를 다이얼로그로 보내는 코드
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Contact").setMessage("02-123-4567")
                            .setPositiveButton("ok", (dialog, which) -> {
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
            case R.id.arrow_back2: {
                //activity.onFragmentChange(1);
            }
        }
    }
}