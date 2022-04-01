package com.example.nolfi.writepage;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nolfi.R;

public class WritePageFragment_my extends Fragment {

    private WritePageFragmentMyViewModel mViewModel;

    public static WritePageFragment_my newInstance() {
        return new WritePageFragment_my();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write_mypage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WritePageFragmentMyViewModel.class);
        // TODO: Use the ViewModel
    }

}