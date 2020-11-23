package com.example.k_zaizai.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_zaizai.R
import com.example.k_zaizai.activity.addActivity
import com.example.k_zaizai.adapter.ItemTouchHelperCallback
import com.example.k_zaizai.adapter.ViewAdapter
import kotlinx.android.synthetic.main.first_fragment.*
import org.litepal.LitePal
import kotlin.concurrent.thread


class First_Fragment : Fragment(){

    private val mRecordList = ArrayList<Records>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.first_fragment, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = GridLayoutManager(context,1)
        //recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =
            ViewAdapter(this, initRecord() as ArrayList<Records>)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //点击添加按钮跳转到编辑页面
        add.setOnClickListener {
            val intent = Intent(activity,addActivity::class.java)
            intent.putExtra("mode",1)
            startActivityForResult(intent,1)
        }

//        //删除所有数据
//        deleteAll.setOnClickListener {
//            LitePal.deleteAll(Records::class.java)
//            recyclerView.adapter =
//                ViewAdapter(initRecord() as ArrayList<Records>)
//        }

        //下拉刷新
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refresh(recyclerView.adapter as ViewAdapter)
        }

        //侧滑删除
        val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(recyclerView.adapter as ViewAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

    }

    //读取数据库内容
    private fun initRecord(): MutableList<Records>? {
        return LitePal.select("content","time").find(Records::class.java)
    }

    //接受startActivityForResult的结果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val returnMode = data?.extras?.getInt("mode")

        if (returnMode == 1){
            //新建笔记
            val db = Records(data.getStringExtra("content"))
            db.time = data.getStringExtra("time")
            db.save()
            Log.d("tag","新建成功")
        }else if (returnMode == 2){
            val id = data?.extras?.getLong("id")
            val db = ContentValues()
            db.put("content",data?.extras?.getString("content"))
            db.put("time",data?.extras?.getString("time"))
            LitePal.update(Records::class.java, db, id!!)
            Log.d("tag","修改成功")
        }
        recyclerView.adapter = ViewAdapter(this, initRecord() as ArrayList<Records>)
    }


    //下拉刷新
    private fun refresh(adapter: ViewAdapter){
        thread {
            Thread.sleep(2000)
            activity?.runOnUiThread{
                initRecord()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
                Log.d("tag","刷新成功")
            }
        }
    }

}
