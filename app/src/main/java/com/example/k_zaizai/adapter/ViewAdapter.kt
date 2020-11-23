package com.example.k_zaizai.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_zaizai.R
import com.example.k_zaizai.activity.addActivity
import com.example.k_zaizai.fragment.Records
import org.litepal.LitePal
import java.util.*
import kotlin.collections.ArrayList


class ViewAdapter(val fragment: Fragment,val recordList: ArrayList<Records>) :
    RecyclerView.Adapter<ViewAdapter.ViewHolder>(),ItemTouchHelperAdapter {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val RecordContent: TextView = view.findViewById(R.id.list_content)
        val RecordTime: TextView = view.findViewById(R.id.list_time)
        val delete: ImageView = view.findViewById(R.id.delete)
        val layout: RelativeLayout = view.findViewById(R.id.layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val viewHolder = ViewHolder(view)

        return viewHolder

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //对子项数据进行赋值
        val record = recordList[position]
        holder.apply {
            RecordContent.text = record.content
            RecordTime.text = record.time
        }

        //点击删除按钮删除该条记录
        holder.delete.setOnClickListener {
            onItemDelete(position)
            LitePal.delete(Records::class.java, record.id)
        }

        //点击该条记录进入编辑页面
        holder.layout.setOnClickListener {
            val intent = Intent(fragment.activity,addActivity::class.java).apply {
                putExtra("content",record.content)
                putExtra("id", record.id)
                putExtra("mode",2) //再次点击笔记进行编辑设为2

            }
            fragment.startActivityForResult(intent, 1)
        }



    }

    override fun getItemCount() = recordList.size

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
    }


    //交换位置
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(recordList,fromPosition,toPosition)
        notifyItemMoved(fromPosition,toPosition)
    }

    //移除数据
     override fun onItemDelete(position: Int) {
        recordList.removeAt(position)
        notifyItemRemoved(position)
        //LitePal.delete(Records::class.java, recordList[position].id)
    }
}