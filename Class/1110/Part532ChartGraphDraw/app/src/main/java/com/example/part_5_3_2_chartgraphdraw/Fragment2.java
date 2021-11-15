package com.example.part_5_3_2_chartgraphdraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    LineChart lineChart;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v=inflater.inflate(R.layout.frag2,container,false);

        lineChart=v.findViewById(R.id.linechart);


        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 2482f));
        entries.add(new Entry(1, 2343f));
        entries.add(new Entry(2, 2247f));
        entries.add(new Entry(3, 2224f));
        entries.add(new Entry(4, 1758f));
        entries.add(new Entry(5, 2425f));

        LineDataSet dataset = new LineDataSet(entries, "# 11.03 ~ 11. 09");

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS); //
        /*dataset.setDrawCubic(true); //선 둥글게 만들기
        dataset.setDrawFilled(true); //그래프 밑부분 색칠*/

        lineChart.setData(data);
        lineChart.animateY(1000);

        Description description = new Description();
        description.setText("코로나 신규 확진자 수"); //라벨
        description.setTextSize(15);
        lineChart.setDescription(description);

        return v;
    }
}