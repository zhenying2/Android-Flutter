package com.example.gonggu.utils

//세부 글 확인 (fragment -> activity ) 데이터 전달 위한 함수
public interface OnPositionSetListener {
    fun onPositionPickerSet(position:Int): Int {
        return position
    }
}