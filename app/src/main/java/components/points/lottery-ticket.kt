@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "SENSELESS_COMPARISON", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNI4828FFC
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlin.properties.Delegates
open class GenComponentsPointsLotteryTicket : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var defmodel: UTSArray<String> by `$props`
    open var showTitle: Boolean by `$props`
    open var title: String by `$props`
    open var ruleBtnText: String by `$props`
    open var rulesContent: UTSArray<String> by `$props`
    open var mainNumberCount: Number by `$props`
    open var subNumberCount: Number by `$props`
    open var play: (targetNumbers: UTSArray<String>, isWin: Boolean) -> Unit
        get() {
            return unref(this.`$exposed`["play"]) as (targetNumbers: UTSArray<String>, isWin: Boolean) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "play", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsLotteryTicket, __setupCtx: SetupContext) -> Any? = fun(__props, __setupCtx): Any? {
            val __expose = __setupCtx.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsLotteryTicket
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val displayNumbersInner = ref(_uA<String>())
            val isDrawing = ref(false)
            val resultPopupVisible = ref(false)
            val isWinResult = ref(false)
            watch(fun(): UTSArray<String> {
                return props.defmodel
            }
            , fun(newVal: UTSArray<String>){
                if (!isDrawing.value) {
                    displayNumbersInner.value = newVal
                }
            }
            , WatchOptions(immediate = true))
            val play = fun(targetNumbers: UTSArray<String>, isWin: Boolean){
                if (isDrawing.value) {
                    return
                }
                isDrawing.value = true
                isWinResult.value = isWin
                val mPool: UTSArray<String> = _uA()
                run {
                    var i: Number = 1
                    while(i <= 35){
                        mPool.push(if (i < 10) {
                            "0" + i
                        } else {
                            "" + i
                        }
                        )
                        i++
                    }
                }
                val sPool: UTSArray<String> = _uA()
                run {
                    var i: Number = 1
                    while(i <= 12){
                        sPool.push(if (i < 10) {
                            "0" + i
                        } else {
                            "" + i
                        }
                        )
                        i++
                    }
                }
                val timer = setInterval(fun(){
                    val tempNums: UTSArray<String> = _uA()
                    run {
                        var i: Number = 0
                        while(i < props.mainNumberCount){
                            tempNums.push(mPool[Math.floor(Math.random() * mPool.length)])
                            i++
                        }
                    }
                    run {
                        var i: Number = 0
                        while(i < props.subNumberCount){
                            tempNums.push(sPool[Math.floor(Math.random() * sPool.length)])
                            i++
                        }
                    }
                    displayNumbersInner.value = tempNums
                }
                , 50)
                setTimeout(fun(){
                    clearInterval(timer)
                    isDrawing.value = false
                    displayNumbersInner.value = targetNumbers
                    emit("update:defmodel", targetNumbers)
                    emit("lottery-complete", targetNumbers)
                    resultPopupVisible.value = true
                }
                , 2000)
            }
            val handleDrawClick = fun(){
                if (isDrawing.value) {
                    return
                }
                emit("click-draw")
            }
            __expose(_uM("play" to play))
            return fun(): Any? {
                return _cE(Fragment, null, _uA(
                    _cE("view", _uM("class" to "w-full p-4"), _uA(
                        _cE("view", _uM("class" to "flex flex-row justify-between items-center w-full mb-4 overflow-visible"), _uA(
                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), _tD(_ctx.title), 1),
                            _cV(unref(GenComponentsPointsRuleTitleClass), _uM("rule-btn-text" to _ctx.ruleBtnText, "rules-content" to _ctx.rulesContent), null, 8, _uA(
                                "rule-btn-text",
                                "rules-content"
                            ))
                        )),
                        _cE("view", _uM("class" to "bg ticket-body rounded-xl p-6 shadow"), _uA(
                            _cE("image", _uM("class" to "ticket-bg-image", "src" to "/static/images/gift-box.png", "mode" to "aspectFill")),
                            _cE("view", _uM("class" to "ticket-content"), _uA(
                                _cE("view", _uM("class" to "rounded-lg px-2 py-8 mt-10 flex flex-row items-center justify-center gap-x-1 gap-y-4 border border-orange-200"), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(_ctx.mainNumberCount, fun(n, i, __index, _cached): Any {
                                        return _cE("view", _uM("key" to ("main-" + i), "class" to _nC(_uA(
                                            "number-ball w-8 h-8 rounded-full flex justify-center items-center shadow-inner main-ball-bg",
                                            _uM("is-shaking" to isDrawing.value)
                                        ))), _uA(
                                            _cE("text", _uM("class" to "text-white text-sm font-bold"), _tD(if ((i < displayNumbersInner.value.length)) {
                                                displayNumbersInner.value[i]
                                            } else {
                                                "?"
                                            }
                                            ), 1)
                                        ), 2)
                                    }
                                    ), 128),
                                    _cE(Fragment, null, RenderHelpers.renderList(_ctx.subNumberCount, fun(n, i, __index, _cached): Any {
                                        return _cE("view", _uM("key" to ("sub-" + i), "class" to _nC(_uA(
                                            "number-ball w-8 h-8 rounded-full flex justify-center items-center shadow-inner sub-ball-bg",
                                            _uM("is-shaking" to isDrawing.value)
                                        ))), _uA(
                                            _cE("text", _uM("class" to "text-white text-sm font-bold"), _tD(displayNumbersInner.value[i + _ctx.mainNumberCount] ?: "?"), 1)
                                        ), 2)
                                    }
                                    ), 128)
                                )),
                                _cE("view", _uM("class" to "mt-3"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "warning", "block" to "", "shape" to "round", "disabled" to isDrawing.value, "onClick" to handleDrawClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return _uA(
                                            _tD(if (isDrawing.value) {
                                                "开奖中..."
                                            } else {
                                                "立即抽奖"
                                            }
                                            )
                                        )
                                    }
                                    ), "_" to 1), 8, _uA(
                                        "disabled"
                                    ))
                                ))
                            ))
                        ))
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to resultPopupVisible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        resultPopupVisible.value = `$event`
                    }
                    , "position" to "center", "show-title" to false, "width" to "90%", "height" to "50%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col items-center px-4 py-6"), _uA(
                                _cE("view", _uM("class" to "w-20 h-20 rounded-full bg-orange-50 flex justify-center items-center mb-4"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to if (isWinResult.value) {
                                        "icon-earn-active"
                                    } else {
                                        "icon-guanyu"
                                    }
                                    , "size" to 80, "color" to if (isWinResult.value) {
                                        "#f97316"
                                    } else {
                                        "#9ca3af"
                                    }
                                    ), null, 8, _uA(
                                        "name",
                                        "color"
                                    ))
                                )),
                                _cE("text", _uM("class" to "text-lg font-bold text-gray-800 mb-2"), _tD(if (isWinResult.value) {
                                    "恭喜中奖！"
                                } else {
                                    "很遗憾"
                                }
                                ), 1),
                                _cE("text", _uM("class" to "text-base text-gray-600 mb-6 text-center"), _tD(if (isWinResult.value) {
                                    "您的抽奖券号码已中奖，奖金已入账"
                                } else {
                                    "本次未中奖，换个姿势再来一次吧~"
                                }
                                ), 1),
                                _cE("view", _uM("class" to "flex flex-row gap-2 mb-6"), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(displayNumbersInner.value, fun(num, __key, __index, _cached): Any {
                                        return _cE("view", _uM("key" to num, "class" to "w-8 h-8 bg-orange-500 rounded-full flex items-center justify-center"), _uA(
                                            _cE("text", _uM("class" to "text-white text-xs font-bold"), _tD(num), 1)
                                        ))
                                    }
                                    ), 128)
                                )),
                                _cE("view", _uM("class" to "w-full bg-orange-500 rounded-full h-22 flex justify-center items-center tap-active", "onClick" to fun(){
                                    resultPopupVisible.value = false
                                }
                                ), _uA(
                                    _cE("text", _uM("class" to "text-white text-base font-semibold"), "确 认")
                                ), 8, _uA(
                                    "onClick"
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    ))
                ), 64)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("bg" to _pS(_uM("height" to 230, "position" to "relative", "overflow" to "hidden")), "ticket-bg-image" to _pS(_uM("position" to "absolute", "inset" to 0, "width" to "100%", "height" to "100%")), "ticket-content" to _pS(_uM("position" to "relative", "zIndex" to 1)), "banner-gift" to _pS(_uM("position" to "absolute", "right" to 0, "top" to "-20rpx", "width" to "80rpx", "height" to "80rpx")), "reel-window" to _pS(_uM("boxShadow" to "inset 0 2px 8px rgba(0, 0, 0, 0.2)", "position" to "relative")), "main-ball-bg" to _pS(_uM("backgroundImage" to "linear-gradient(135deg, #ff8a65 0%, #ff5722 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "sub-ball-bg" to _pS(_uM("backgroundImage" to "linear-gradient(135deg, #42a5f5 0%, #1e88e5 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "reel-container" to _pS(_uM("display" to "flex", "flexDirection" to "column")), "reel-item" to _pS(_uM("height" to "64rpx")), "close-icon" to _pS(_uM("position" to "absolute", "bottom" to "-100rpx", "left" to "50%", "transform" to "translateX(-50%)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("update:defmodel" to null, "click-draw" to null, "lottery-complete" to null)
        var props = _nP(_uM("defmodel" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<String> {
            return _uA<String>()
        }
        ), "showTitle" to _uM("type" to "Boolean", "required" to false, "default" to true), "title" to _uM("type" to "String", "required" to false, "default" to "抽奖券"), "ruleBtnText" to _uM("type" to "String", "required" to false, "default" to "抽奖券规则"), "rulesContent" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<String> {
            return _uA(
                "1. 每天可免费获取1张抽奖券",
                "2. 完成任务可额外获得抽奖券",
                "3. 中奖后奖金将自动发放到账户",
                "4. 每期开奖时间为每天20:00"
            )
        }
        ), "mainNumberCount" to _uM("type" to "Number", "required" to false, "default" to 5), "subNumberCount" to _uM("type" to "Number", "required" to false, "default" to 2)))
        var propsNeedCastKeys = _uA(
            "defmodel",
            "showTitle",
            "title",
            "ruleBtnText",
            "rulesContent",
            "mainNumberCount",
            "subNumberCount"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
