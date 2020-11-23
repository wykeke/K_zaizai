package com.example.k_zaizai.bean

class T_bean {
    private var reason: String? = null
    private var result: ResultBean? = null
    private var error_code = 0

    fun getReason(): String? {
        return reason
    }

    fun setReason(reason: String?) {
        this.reason = reason
    }

    fun getResult(): ResultBean? {
        return result
    }

    fun setResult(result: ResultBean?) {
        this.result = result
    }

    fun getError_code(): Int {
        return error_code
    }

    fun setError_code(error_code: Int) {
        this.error_code = error_code
    }

    class ResultBean {
        /**
         * id : 3494
         * yangli : 2019-11-14
         * yinli : 己亥(猪)年十月十八
         * wuxing : 大溪水 定执位
         * chongsha : 冲鸡(己酉)煞西
         * baiji : 乙不栽植千株不长 卯不穿井水泉不香
         * jishen : 时德 天德 月德 四相 阴德 民日 三合 时阴 鸣犬
         * yi : 订盟 纳采 纳财 开市 立券 祭祀 祈福 移徙 入宅 出行 盖屋 起基 修造 动土 竖柱 上梁 安门 安香 出火 教牛马 会亲友 破土
         * xiongshen : 元武
         * ji : 嫁娶 安葬 掘井 置产 造船
         */
        var id: String? = null
        var yangli: String? = null
        var yinli: String? = null
        var wuxing: String? = null
        var chongsha: String? = null
        var baiji: String? = null
        var jishen: String? = null
        var yi: String? = null
        var xiongshen: String? = null
        var ji: String? = null

    }
}