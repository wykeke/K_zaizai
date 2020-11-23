package com.example.k_zaizai.base

import androidx.appcompat.app.AppCompatActivity
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x


class BaseActivity : AppCompatActivity(),Callback.CommonCallback<String>{


    fun loadData(url: String?) {
        val params = RequestParams(url)
        x.http().get(params, this)
    }

    override fun onFinished() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(result: String?) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(cex: Callback.CancelledException?) {
        TODO("Not yet implemented")
    }

    override fun onError(ex: Throwable?, isOnCallback: Boolean) {
        TODO("Not yet implemented")
    }


}