package com.example.k_zaizai.base

class CententURL {
    //获取指定日期对应的历史上的今天网址
    fun getTodayHistoryURL(version: String, month: Int, day: Int): String? {
        return "http://api.juheapi.com/japi/toh?v=$version&month=$month&day=$day&key=6a877b186ccd134296d131183b74c9f4"
    }

    //    获取指定日期的老黄历的网址
    fun getLaohuangliURL(time: String): String? {
        return "http://v.juhe.cn/laohuangli/d?date=$time&key=c7c6d7da1062f007a33609571cdb17f2"
    }

//    http://api.juheapi.com/japi/tohdet?key=6a877b186ccd134296d131183b74c9f4&v=1.0&id=18401114

    //    http://api.juheapi.com/japi/tohdet?key=6a877b186ccd134296d131183b74c9f4&v=1.0&id=18401114
    //    根据指定事件id获取指定事件详情信息
    fun getHistoryDescURL(version: String, id: String): String? {
        return "http://api.juheapi.com/japi/tohdet?key=6a877b186ccd134296d131183b74c9f4&v=$version&id=$id"
    }

}