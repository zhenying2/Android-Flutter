package com.example.gridview1;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class MyGridAdapter extends BaseAdapter {

    Context context;
    Integer[] posterID={R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
            R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10};
    Integer[] posterTitle={R.string.mov01, R.string.mov02, R.string.mov03,R.string.mov04,R.string.mov05,R.string.mov06,
            R.string.mov07,R.string.mov08,R.string.mov09,R.string.mov10};

    public MyGridAdapter(Context c){
        context=c;
    }

    public int getCount(){
        return posterID.length;
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(400,650));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5,5,10,10);

        imageView.setImageResource(posterID[position]);

        final int pos=position;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = (View) View.inflate(
                        context,R.layout.dialog,null);
                AlertDialog.Builder dlg=new AlertDialog.Builder(context);
                ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.imageViewForPoster);
                ivPoster.setImageResource(posterID[pos]);

                //dlg.setTitle("큰 포스터");
                //dlg.setIcon(R.drawable.ic_launcher_foreground);
                dlg.setTitle(posterTitle[pos]);
                dlg.setIcon(posterID[pos]);
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기",null);
                dlg.show();
            }
        });
        return imageView;
    }
}
