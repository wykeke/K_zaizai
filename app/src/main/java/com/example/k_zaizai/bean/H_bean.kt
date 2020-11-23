package com.example.k_zaizai.bean

class H_bean {
    private var reason: String? = null
    private var error_code = 0
    private var result: List<ResultBean?>? = null

    fun getReason(): String? {
        return reason
    }

    fun setReason(reason: String?) {
        this.reason = reason
    }

    fun getError_code(): Int {
        return error_code
    }

    fun setError_code(error_code: Int) {
        this.error_code = error_code
    }

    fun getResult(): List<ResultBean?>? {
        return result
    }

    fun setResult(result: List<ResultBean?>?) {
        this.result = result
    }

    class ResultBean  {
        /**
         * _id : 17161114
         * title : 德国数学家莱布尼茨逝世
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201111/14/B6125711808.jpg
         * year : 1716
         * month : 11
         * day : 14
         * des : 在303年前的今天，1716年11月14日 (农历十月初一)，德国数学家莱布尼茨逝世。
         * lunar : 丙申年十月初一
         */
        private var _id: String? = null
        var title: String? = null
        var pic: String? = null
        var year = 0
        var month = 0
        var day = 0
        var des: String? = null
        var lunar: String? = null

        fun get_id(): String? {
            return _id
        }

        fun set_id(_id: String?) {
            this._id = _id
        }

    }
}