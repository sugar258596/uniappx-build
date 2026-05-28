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
open class GenComponentsPointsLuckyWheel : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var prizes: UTSArray<Prize> by `$props`
    open var mode: String by `$props`
    open var showTitle: Boolean by `$props`
    open var title: String by `$props`
    open var ruleBtnText: String by `$props`
    open var rulesContent: UTSArray<String> by `$props`
    open var play: (id: Number) -> Unit
        get() {
            return unref(this.`$exposed`["play"]) as (id: Number) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "play", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsLuckyWheel, __setupCtx: SetupContext) -> Any? = fun(__props, __setupCtx): Any? {
            val __expose = __setupCtx.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsLuckyWheel
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val rotateAngle = ref(0)
            val spinDuration = ref(0)
            val isSpinning = ref(false)
            val prizePopupVisible = ref(false)
            val currentPrize = ref<Prize?>(null)
            val getRandomPrizeByProbability = fun(): Number {
                val prizeList = props.prizes
                val totalProbability = prizeList.reduce(fun(sum, prize): Number {
                    return sum + (prize.probability ?: 0)
                }
                , 0)
                var random = Math.random() * totalProbability
                var cumulativeProbability: Number = 0
                run {
                    var i: Number = 0
                    while(i < prizeList.length){
                        cumulativeProbability += prizeList[i].probability ?: 0
                        if (random <= cumulativeProbability) {
                            return i
                        }
                        i++
                    }
                }
                return prizeList.length - 1
            }
            val startSpin = fun(prizeIndex: Number){
                if (isSpinning.value) {
                    return
                }
                isSpinning.value = true
                val prizeList = props.prizes
                val prizeAngle = (360 as Number) / prizeList.length
                val targetStopAngle = 360 - (prizeIndex * prizeAngle)
                val currentNormalizedAngle = rotateAngle.value % 360
                var deltaAngle = targetStopAngle - currentNormalizedAngle
                val baseRotation: Number = 1800
                while(deltaAngle < baseRotation){
                    deltaAngle += 360
                }
                spinDuration.value = 0
                setTimeout(fun(){
                    spinDuration.value = 4000
                    rotateAngle.value = rotateAngle.value + deltaAngle
                    setTimeout(fun(){
                        isSpinning.value = false
                        currentPrize.value = prizeList[prizeIndex]
                        prizePopupVisible.value = true
                        spinDuration.value = 0
                        emit("spin-complete", prizeList[prizeIndex])
                    }
                    , 4000)
                }
                , 30)
            }
            val handleSpin = fun(){
                if (isSpinning.value) {
                    return
                }
                if (props.mode === "server") {
                    emit("click-spin")
                    return
                }
                val prizeIndex = getRandomPrizeByProbability()
                startSpin(prizeIndex)
            }
            val play = fun(id: Number){
                val prizeIndex = props.prizes.findIndex(fun(item): Boolean {
                    return item.id === id
                }
                )
                if (prizeIndex >= 0) {
                    startSpin(prizeIndex)
                } else {
                    console.error("LuckyWheel: Prize ID not found")
                }
            }
            __expose(_uM("play" to play))
            val wheelStyle = computed(fun(): UTSJSONObject {
                return _uO("transform" to ("rotate(" + rotateAngle.value + "deg)"), "transition" to if (spinDuration.value > 0) {
                    "transform " + spinDuration.value + "ms cubic-bezier(0.17, 0.67, 0.12, 0.99)"
                } else {
                    "none"
                }
                )
            }
            )
            val prizeBgStyle = fun(index: Number): UTSJSONObject {
                val prizeCount = props.prizes.length
                val angle = (360 as Number) / prizeCount
                val rotate = index * angle - (angle / 2)
                return _uO("transform" to ("rotate(" + rotate + "deg) skewY(-" + (90 - angle) + "deg)"), "background-color" to if (index % 2 === 0) {
                    "#FEE4E4"
                } else {
                    "#FFF5F0"
                }
                )
            }
            val prizeContentStyle = fun(index: Number): UTSJSONObject {
                val prizeCount = props.prizes.length
                val angle = (360 as Number) / prizeCount
                val rotate = index * angle
                return _uO("transform" to ("rotate(" + rotate + "deg)"))
            }
            val closePrizePopup = fun(){
                prizePopupVisible.value = false
            }
            val hasPrizeImage = fun(prize: Prize): Boolean {
                val image = prize.image
                val icon = prize.icon
                return (image != null && image != "") || (icon != null && icon != "" && (icon.includes("/") || icon.includes("http")))
            }
            val getPrizeSrc = fun(prize: Prize): String {
                val image = prize.image
                val icon = prize.icon
                return if ((image != null && image != "")) {
                    image
                } else {
                    (icon ?: "")
                }
            }
            val hasCurrentPrizeImage = fun(): Boolean {
                val p = currentPrize.value
                if (p == null) {
                    return false
                }
                val image = p.image
                val icon = p.icon
                return (image != null && image != "") || (icon != null && icon != "" && (icon.includes("/") || icon.includes("http")))
            }
            val getCurrentPrizeSrc = fun(): String {
                val p = currentPrize.value
                if (p == null) {
                    return ""
                }
                val image = p.image
                val icon = p.icon
                return if ((image != null && image != "")) {
                    image
                } else {
                    (icon ?: "")
                }
            }
            return fun(): Any? {
                return _cE(Fragment, null, _uA(
                    _cE("view", _uM("class" to "flex flex-col items-center gap-2 p-4"), _uA(
                        _cE("view", _uM("class" to "flex flex-row justify-between overflow-visible w-full"), _uA(
                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), _tD(_ctx.title), 1),
                            _cV(unref(GenComponentsPointsRuleTitleClass), _uM("rule-btn-text" to _ctx.ruleBtnText, "rules-content" to _ctx.rulesContent), null, 8, _uA(
                                "rule-btn-text",
                                "rules-content"
                            ))
                        )),
                        _cE("view", _uM("class" to "wheel-outer flex justify-center items-center relative w-80 h-80 shadow rounded-full"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(16, fun(item, index, __index, _cached): Any {
                                return _cE("view", _uM("key" to ("light-" + index), "class" to "wheel-light", "style" to _nS(_uM("transform" to ("rotate(" + index * 22.5 + "deg) translateY(-148px)")))), null, 4)
                            }
                            ), 64),
                            _cE("view", _uM("class" to "wheel-container relative w-80 h-80 rounded-full"), _uA(
                                _cE("view", _uM("class" to "wheel-border"), _uA(
                                    _cE("view", _uM("class" to "wheel", "style" to _nS(wheelStyle.value)), _uA(
                                        _cE("view", _uM("class" to "absolute inset-0 w-full h-full pointer-events-none"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(_ctx.prizes, fun(prize, index, __index, _cached): Any {
                                                return _cE("view", _uM("key" to ("bg-" + index), "class" to "prize-bg-item", "style" to _nS(prizeBgStyle(index))), null, 4)
                                            }
                                            ), 128)
                                        )),
                                        _cE("view", _uM("class" to "absolute inset-0 w-full h-full pointer-events-none"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(_ctx.prizes, fun(prize, index, __index, _cached): Any {
                                                return _cE("view", _uM("key" to ("content-" + index), "class" to "prize-content-item", "style" to _nS(prizeContentStyle(index))), _uA(
                                                    _cE("view", _uM("class" to "flex flex-col items-center gap-2"), _uA(
                                                        if (isTrue(hasPrizeImage(prize))) {
                                                            _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("key" to 0, "src" to getPrizeSrc(prize), "width" to 32, "height" to 32, "mode" to "aspectFit"), null, 8, _uA(
                                                                "src"
                                                            ))
                                                        } else {
                                                            _cE("text", _uM("key" to 1, "class" to "prize-icon"), _tD(prize.icon), 1)
                                                        }
                                                        ,
                                                        _cE("text", _uM("class" to "prize-text"), _tD(prize.name), 1)
                                                    ))
                                                ), 4)
                                            }
                                            ), 128)
                                        )),
                                        _cE("view", _uM("class" to "absolute inset-0 w-full h-full pointer-events-none"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(_ctx.prizes, fun(prize, index, __index, _cached): Any {
                                                return _cE("view", _uM("key" to ("line-" + index), "class" to "prize-line", "style" to _nS(_uM("transform" to ("rotate(" + (index * 45 - 22.5) + "deg)")))), null, 4)
                                            }
                                            ), 128)
                                        ))
                                    ), 4)
                                )),
                                _cE("view", _uM("class" to "spin-button-wrapper", "onClick" to handleSpin), _uA(
                                    _cE("view", _uM("class" to "spin-button-pointer")),
                                    _cE("view", _uM("class" to "spin-button"), _uA(
                                        _cE("text", _uM("class" to "spin-text-line1"), "立即"),
                                        _cE("text", _uM("class" to "spin-text-line2"), "抽奖")
                                    ))
                                ))
                            ))
                        ))
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to prizePopupVisible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        prizePopupVisible.value = `$event`
                    }
                    , "position" to "center", "show-title" to false, "width" to "90%", "height" to "40%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "prize-popup-box flex flex-col items-center w-full"), _uA(
                                _cE("view", _uM("class" to "w-20 h-20 rounded-full bg-orange-50 flex justify-center items-center mb-4"), _uA(
                                    if (isTrue(hasCurrentPrizeImage())) {
                                        _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("key" to 0, "src" to getCurrentPrizeSrc(), "width" to 80, "height" to 80, "mode" to "aspectFit"), null, 8, _uA(
                                            "src"
                                        ))
                                    } else {
                                        _cE("text", _uM("key" to 1, "style" to _nS(_uM("font-size" to "80rpx"))), _tD(currentPrize.value?.icon ?: "🎁"), 5)
                                    }
                                )),
                                _cE("text", _uM("class" to "text-lg font-bold text-gray-800 mb-2"), "恭喜您！"),
                                _cE("text", _uM("class" to "text-base text-gray-600 mb-4"), "抽中 " + _tD(currentPrize.value?.name), 1),
                                _cE("view", _uM("class" to "w-full bg-gradient rounded-full h-10 flex justify-center items-center tap-active", "onClick" to closePrizePopup), _uA(
                                    _cE("text", _uM("class" to "text-white text-base font-semibold"), "确 认")
                                )),
                                _cE("view", _uM("class" to "close-icon tap-active", "onClick" to closePrizePopup), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-close-circle", "size" to 48, "color" to "#9ca3af"))
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
                return _uM("wheel-outer" to _pS(_uM("backgroundImage" to "linear-gradient(135deg, #ffd700 0%, #ffa500 50%, #ffd700 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "wheel-light" to _pS(_uM("position" to "absolute", "width" to 10, "height" to 10, "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%", "backgroundImage" to "none", "backgroundColor" to "#fff8dc", "boxShadow" to "0 0 6px 2px rgba(255, 255, 255, 0.8)", "top" to "50%", "left" to "50%", "marginLeft" to -5, "marginTop" to -5)), "wheel-border" to _pS(_uM("width" to "100%", "height" to "100%", "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%", "borderTopWidth" to 6, "borderRightWidth" to 6, "borderBottomWidth" to 6, "borderLeftWidth" to 6, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#e53935", "borderRightColor" to "#e53935", "borderBottomColor" to "#e53935", "borderLeftColor" to "#e53935", "overflow" to "hidden", "boxSizing" to "border-box")), "wheel" to _pS(_uM("width" to "100%", "height" to "100%", "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%", "position" to "relative", "overflow" to "hidden", "backgroundImage" to "none", "backgroundColor" to "#fff5f0")), "prize-bg-item" to _pS(_uM("position" to "absolute", "top" to 0, "right" to 0, "width" to "50%", "height" to "50%", "transformOrigin" to "0% 100%")), "prize-content-item" to _pS(_uM("position" to "absolute", "left" to "50%", "top" to 0, "width" to 100, "height" to "50%", "marginLeft" to -50, "transformOrigin" to "50% 100%", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "flex-start", "paddingTop" to 15, "pointerEvents" to "none")), "prize-line" to _pS(_uM("position" to "absolute", "top" to 0, "left" to "50%", "width" to 1, "height" to "50%", "backgroundImage" to "linear-gradient(to bottom, rgba(229, 57, 53, 0.2), transparent)", "backgroundColor" to "rgba(0,0,0,0)", "transformOrigin" to "50% 100%")), "prize-image" to _pS(_uM("width" to 32, "height" to 32, "marginBottom" to 4)), "prize-icon" to _pS(_uM("fontSize" to 24, "lineHeight" to 1.2, "marginBottom" to 4)), "prize-text" to _pS(_uM("fontSize" to 11, "color" to "#e53935", "fontWeight" to "600", "whiteSpace" to "nowrap", "textAlign" to "center", "lineHeight" to 1.2)), "spin-button-wrapper" to _pS(_uM("position" to "absolute", "top" to "50%", "left" to "50%", "transform" to "translate(-50%, -50%)", "zIndex" to 10, "display" to "flex", "flexDirection" to "column", "alignItems" to "center")), "spin-button-pointer" to _pS(_uM("width" to 0, "height" to 0, "borderLeftWidth" to 16, "borderLeftStyle" to "solid", "borderLeftColor" to "rgba(0,0,0,0)", "borderRightWidth" to 16, "borderRightStyle" to "solid", "borderRightColor" to "rgba(0,0,0,0)", "borderBottomWidth" to 24, "borderBottomStyle" to "solid", "borderBottomColor" to "#e53935", "marginBottom" to -8, "zIndex" to 11, "filter" to "drop-shadow(0 -2px 4px rgba(0, 0, 0, 0.2))")), "spin-button" to _pS(_uM("width" to 80, "height" to 80, "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%", "backgroundImage" to "linear-gradient(180deg, #e53935 0%, #c62828 100%)", "backgroundColor" to "rgba(0,0,0,0)", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "justifyContent" to "center", "boxShadow" to "0 4px 12px rgba(229, 57, 53, 0.5), inset 0 2px 4px rgba(255, 255, 255, 0.3)", "cursor" to "pointer")), "spin-text-line1" to _pS(_uM("color" to "#ffeb3b", "fontSize" to 14, "fontWeight" to "bold", "lineHeight" to 1.2)), "spin-text-line2" to _pS(_uM("color" to "#ffeb3b", "fontSize" to 14, "fontWeight" to "bold", "lineHeight" to 1.2)), "prize-popup-box" to _pS(_uM("width" to "600rpx", "borderTopLeftRadius" to "32rpx", "borderTopRightRadius" to "32rpx", "borderBottomRightRadius" to "32rpx", "borderBottomLeftRadius" to "32rpx", "paddingTop" to "48rpx", "paddingRight" to "32rpx", "paddingBottom" to "48rpx", "paddingLeft" to "32rpx")), "close-icon" to _pS(_uM("position" to "absolute", "bottom" to "-100rpx", "left" to "50%", "transform" to "translateX(-50%)")), "bg-gradient" to _pS(_uM("backgroundImage" to "linear-gradient(135deg, #f97316 0%, #fb923c 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "popup-position-center" to _pS(_uM("!backgroundImage" to "linear-gradient(to bottom, #ffedd5 0%, #fff 20%, #fff 100%)", "!backgroundColor" to "rgba(0,0,0,0)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("spin-complete" to null, "show-rules" to null, "click-spin" to null)
        var props = _nP(_uM("prizes" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<Any?> {
            return _uA()
        }
        ), "mode" to _uM("type" to "String", "required" to false, "default" to "probability"), "showTitle" to _uM("type" to "Boolean", "required" to false, "default" to true), "title" to _uM("type" to "String", "required" to false, "default" to "幸运大转盘"), "ruleBtnText" to _uM("type" to "String", "required" to false, "default" to "抽奖规则"), "rulesContent" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<String> {
            return _uA(
                "1. 每天可免费抽奖1次",
                "2. 中奖后奖品将自动发放到账户",
                "3. 积分奖励即时到账",
                "4. 实物奖品需填写收货地址"
            )
        }
        )))
        var propsNeedCastKeys = _uA(
            "prizes",
            "mode",
            "showTitle",
            "title",
            "ruleBtnText",
            "rulesContent"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
