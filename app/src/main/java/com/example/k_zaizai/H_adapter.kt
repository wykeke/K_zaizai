package com.example.k_zaizai

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k_zaizai.bean.H_bean
import com.squareup.picasso.Picasso

class H_adapter(val context: H_Fragment, val mDatas: List<H_bean.ResultBean>) :
    RecyclerView.Adapter<H_adapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTv: TextView = view.findViewById(R.id.h_title)
        val picIv: ImageView = view.findViewById(R.id.h_pic)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.h_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mDatas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultBean: H_bean.ResultBean = mDatas[position]
//        //判断当前位置的年份和上一个位置是否相同
//        if (position != 0){
//            val lastBean: H_bean.ResultBean = mDatas[position - 1]
//            if (resultBean.year == lastBean.year){
//
//            }
//        }
        holder.titleTv.text = resultBean.title
        val picURL = resultBean.pic
        if (TextUtils.isEmpty(picURL)){
            holder.picIv.visibility = View.GONE
        }else{
            holder.picIv.visibility = View.VISIBLE
            //加载图片
            Picasso.with(context).load(picURL).into(holder.picIv)
        }

    }
}