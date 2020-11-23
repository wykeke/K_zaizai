package com.example.k_zaizai.adapter

interface ItemTouchHelperAdapter {
    //数据交换
    fun onItemMove(fromPosition: Int, toPosition: Int)

    //数据删除
    fun onItemDelete(position: Int)
}