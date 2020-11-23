package com.example.k_zaizai.fragment

import org.litepal.crud.LitePalSupport

class User(): LitePalSupport(){
    var userID: Int = 0
    var ID: Int = 0
    var password: String = ""
    var name: String = ""
}