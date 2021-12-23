package com.example.gonggu.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gonggu.DetailPostView
import com.example.gonggu.R

class MyItemAdapter(val MainItemList:ArrayList<MainItem>) : RecyclerView.Adapter<MyItemAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CustomViewHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.main_items,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos:Int=adapterPosition
                val mainitem:MainItem=MainItemList.get(curPos)
                Toast.makeText(parent.context,"제목 : ${mainitem.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position:Int){
        //현재 클릭한 위치와 연동
        holder.title.text=MainItemList.get(position).title
        holder.author.text=MainItemList.get(position).author
        holder.link.text=MainItemList.get(position).link
        holder.price.text=MainItemList.get(position).price
        holder.period.text=MainItemList.get(position).period

        //해당 recyclerview 클릭시 activity 화면 이동
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView?.context, DetailPostView::class.java).putExtra("id_extra",position)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
        }
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title=itemView.findViewById<TextView>(R.id.tv_title) //글제목
        var author=itemView.findViewById<TextView>(R.id.tv_id) //글쓴이
        var link=itemView.findViewById<TextView>(R.id.tv_item_titlename) //공구링크
        var price=itemView.findViewById<TextView>(R.id.tv_item_pricename) //공구가격
        var period=itemView.findViewById<TextView>(R.id.tv_item_periodname) //공구 날짜

    }

    override fun getItemCount(): Int {
        return MainItemList.size
    }

}