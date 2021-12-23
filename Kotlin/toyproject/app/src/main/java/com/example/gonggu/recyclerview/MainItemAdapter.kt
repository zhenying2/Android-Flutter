package com.example.gonggu.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gonggu.R

class MainItemAdapter(val MainItemList:ArrayList<MainItem>) : RecyclerView.Adapter<MainItemAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MainItemAdapter.CustomViewHolder{
        val view=LayoutInflater.from(parent.context).inflate(R.layout.main_items,parent,false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos:Int=adapterPosition
                val mainitem:MainItem=MainItemList.get(curPos)
                Toast.makeText(parent.context,"제목 : ${mainitem.title}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: MainItemAdapter.CustomViewHolder, position:Int){
        //현재 클릭한 위치와 연동
        holder.title.text=MainItemList.get(position).title
        holder.author.text=MainItemList.get(position).author
        holder.link.text=MainItemList.get(position).link
        holder.price.text=MainItemList.get(position).price
        holder.period.text=MainItemList.get(position).period
    }

    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
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