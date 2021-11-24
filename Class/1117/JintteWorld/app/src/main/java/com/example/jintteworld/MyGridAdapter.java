package com.example.jintteworld;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MyGridAdapter extends BaseAdapter {
    Context context;
    Integer[] instruments = {R.drawable.img01,R.drawable.img02,R.drawable.img03,R.drawable.img04,R.drawable.img05};
    Integer[] map_figure = {R.drawable.map01,R.drawable.map02,R.drawable.map03,R.drawable.map04,R.drawable.map05};
    Integer[] name = {R.string.name01,R.string.name02,R.string.name03,R.string.name04,R.string.name05};
    Integer[] frame = {R.layout.summary01,R.layout.summary02,R.layout.summary03,R.layout.summary04,R.layout.summary05};
    Integer[] map_name = {R.id.map01,R.id.map02,R.id.map03,R.id.map04,R.id.map05};
    public MyGridAdapter(Context c){
        context=c;
    }

    public int getCount(){
        return instruments.length;
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageview = new ImageView(context);
        imageview.setLayoutParams(new ViewGroup.LayoutParams(1000,450));
        imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageview.setPadding(5,5,5,5);
        imageview.setImageResource(instruments[position]);

        final int pos=position;
        imageview.setOnClickListener(new View.OnClickListener() {
            private int count = 0;
            @Override
            public void onClick(View view) {
                View dialogView=(View) View.inflate(
                        context,R.layout.dialog,null);
                //시간 선택 spinner
                final Spinner spinner=(Spinner)dialogView.findViewById(R.id.spinner);
                ArrayAdapter timeAdapter=ArrayAdapter.createFromResource(context,R.array.time,R.layout.spinner_layout);
                spinner.setAdapter(timeAdapter);

                //명수 선택 & 버튼 이벤트
                final TextView person=(TextView)dialogView.findViewById(R.id.count_people);
                person.setText(count+"명");

                //framelayout 넣기
                final FrameLayout map=(FrameLayout)dialogView.findViewById(R.id.map);
                final LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(frame[pos],map,true);

                final ImageButton btn_add=(ImageButton)dialogView.findViewById(R.id.add_btn);
                final ImageButton btn_remove=(ImageButton)dialogView.findViewById(R.id.remove_btn);
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count++;
                        person.setText(count+"명");
                    }
                });
                btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (count <= 0){
                            count=0;
                        }else{
                            count--;
                        }
                        person.setText(count+"명");
                    }
                });
                //지도 imagebutton
                final ImageButton btn_map=(ImageButton)map.findViewById(map_name[pos]);
                btn_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View dialogView2=(View) View.inflate(context,R.layout.dialog3,null);
                        AlertDialog.Builder dlg2=new AlertDialog.Builder(context);
                        final ImageView rev_img=(ImageView)dialogView2.findViewById(R.id.rev_img);
                        rev_img.setImageResource(map_figure[pos]);
                        dlg2.setTitle(name[pos]);
                        dlg2.setNegativeButton(" X ",null);
                        dlg2.setView(dialogView2);
                        dlg2.show();
                    }
                });
                final Button btn_reserve=(Button)dialogView.findViewById(R.id.reserve_btn);
                btn_reserve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View dialogView2=(View) View.inflate(context,R.layout.dialog2,null);
                        AlertDialog.Builder dlg2=new AlertDialog.Builder(context);
                        final ImageView rev_img=(ImageView)dialogView2.findViewById(R.id.rev_img);
                        rev_img.setImageResource(instruments[pos]);
                        final TextView rev_name=(TextView)dialogView2.findViewById(R.id.rev_name);
                        rev_name.setText(name[pos]);
                        final TextView rev_time=(TextView)dialogView2.findViewById(R.id.rev_time);
                        rev_time.setText(spinner.getSelectedItem().toString());
                        final TextView rev_people=(TextView)dialogView2.findViewById(R.id.rev_people);
                        rev_people.setText(count+"명");
                        dlg2.setTitle("예약 완료!");
                        dlg2.setNegativeButton(" X ",null);
                        dlg2.setView(dialogView2);
                        dlg2.show();
                    }
                });
                AlertDialog.Builder dlg=new AlertDialog.Builder(context);
                dlg.setTitle(name[pos]);
                dlg.setNegativeButton(" X ",null);
                dlg.setView(dialogView);
                dlg.show();
            }
        });
        return imageview;
    }
}
