package com.example.k_zaizai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_zaizai.base.CententURL
import com.example.k_zaizai.bean.H_bean
import java.util.*

class H_Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var  mDatas: List<H_bean.ResultBean>



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.h_fragment, container, false)
        recyclerView = view.findViewById(R.id.h_rv)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = H_adapter(this,mDatas)

        //获取日历对象
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val month = calendar.get(Calendar.MONTH) + 1 //打印月份默认1月为0
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val todayURL = CententURL()
        todayURL.getTodayHistoryURL("1.0",month, day)

    }




}