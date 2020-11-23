package com.example.k_zaizai.fragment

import org.litepal.crud.LitePalSupport

class Records(val content: String): LitePalSupport(){
    val id: Long = 0
    var time: String? = null
    val mode: Int = 0
}