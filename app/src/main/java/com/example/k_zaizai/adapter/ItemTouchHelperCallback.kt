package com.example.k_zaizai.adapter

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kotlin.math.abs


class ItemTouchHelperCallback(private val mAdapter: ItemTouchHelperAdapter): ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView,
                                  viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags: Int = ItemTouchHelper.UP or DOWN  //允许上下拖动
        val swipeFlags: Int = ItemTouchHelper.LEFT //只允许从右向左侧滑
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    //实现拖动交换位置，可以重写该方法（前提是支持上下拖动）
    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mAdapter.onItemMove(viewHolder.bindingAdapterPosition,target.bindingAdapterPosition)
        return true
    }

    //当用户左右滑动Item达到删除条件时，会调用该方法
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mAdapter.onItemDelete(viewHolder.bindingAdapterPosition)
    }

    //如果用户触摸并左右滑动了View，那么可以执行滑动删除操作,即调用onSwiped()方法
    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
    }

    //表示支持长按拖动
    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }


//    //限制ImageView长度所能增加的最大值
//    private val ICON_MAX_SIZE = 50.0
//
//    //ImageView的初始长宽
//    private val fixedWidth = 150
//
//    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
//        super.clearView(recyclerView, viewHolder)
//        //重置改变，防止由于复用而导致的显示问题
//        viewHolder.itemView.scrollX = 0
//        (viewHolder as ViewAdapter.ViewHolder).layout.tv_text.text = "删除"
//        val params = viewHolder.layout.iv_img.layoutParams
//        params.width = 150
//        params.height = 150
//        viewHolder.layout.iv_img.layoutParams = params
//        viewHolder.layout.iv_img.visibility = View.INVISIBLE
//    }
//
//    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
//                             dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
//        //仅对侧滑状态下的效果做出改变
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//            //如果dX小于等于删除方块的宽度，将该方块滑出来
//            if (abs(dX) <= getSlideLimitation(viewHolder)){
//                viewHolder.itemView.scrollTo((-dX).toInt(), 0)
//            }
//        }else if (abs(dX) <= recyclerView.width/2){
//            val distance = (recyclerView.width / 2 - getSlideLimitation(viewHolder)).toDouble()
//            val factor = ICON_MAX_SIZE / distance
//            var diff = (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor
//            if (diff >= ICON_MAX_SIZE) diff = ICON_MAX_SIZE
//            (viewHolder as ViewAdapter.ViewHolder).layout.tv_text.text = ""
//            viewHolder.layout.iv_img.visibility = View.INVISIBLE //显示删除图标
//            val params = viewHolder.layout.iv_img.layoutParams
//            viewHolder.layout.iv_img.layoutParams = params
//        }else{
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//        }
//    }
//
//    //获取删除方块的宽度
//    fun getSlideLimitation(viewHolder: RecyclerView.ViewHolder): Int {
//        val viewGroup = viewHolder.itemView as ViewGroup
//        return viewGroup.getChildAt(1).layoutParams.width
//    }
}