package com.example.part_5_3_2_chartgraphdraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v=inflater.inflate(R.layout.frag3,container,false);

        barChart=v.findViewById(R.id.barchart);

        ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new BarEntry(0, 2482f));
        NoOfEmp.add(new BarEntry(1, 2343f));
        NoOfEmp.add(new BarEntry(2, 2247f));
        NoOfEmp.add(new BarEntry(3, 2224f));
        NoOfEmp.add(new BarEntry(4, 1758f));
        NoOfEmp.add(new BarEntry(5, 2425f));

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "# 11.03 ~ 11. 09");
        barChart.animateY(1000);

        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.JOYFUL_COLORS);
        barChart.setData(data);

        Description description = new Description();
        description.setText("코로나 신규 확진자 수"); //라벨
        description.setTextSize(15);
        barChart.setDescription(description);

        return v;
    }
}