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
open class GenComponentsPointsSignInCard : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var signInDays: Number by `$props`
    open var signInReminder: Boolean by `$props`
    open var isTodaySigned: Boolean by `$props`
    open var signInCalendar: UTSArray<SignInDay> by `$props`
    open var manual: Boolean? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsSignInCard) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsSignInCard
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleSignIn = fun(){
                emit("sign-in")
            }
            val handleReminderChange = fun(value: Boolean){
                emit("reminder-change", value)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between mb-8"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center"), _uA(
                            _cE("text", _uM("class" to "text-base text-zice-800"), "已连续签到 "),
                            _cE("text", _uM("class" to "text-base text-orange-500 font-bold"), _tD(_ctx.signInDays), 1),
                            _cE("text", _uM("class" to "text-base text-zice-800"), " 天")
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center"), _uA(
                            _cE("text", _uM("class" to "text-sm text-zice-500 mr-4"), "签到提醒"),
                            _cV(unref(GenUniModulesTangUiXComponentsTSwitchIndexClass), _uM("model-value" to _ctx.signInReminder, "size" to "small", "active-color" to "#f97316", "manual" to _ctx.manual, "onChange" to handleReminderChange), null, 8, _uA(
                                "model-value",
                                "manual"
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "flex flex-row justify-between mb-10"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(_ctx.signInCalendar, fun(item, __key, __index, _cached): Any {
                            return _cE("view", _uM("key" to item.day, "class" to _nC(_uA(
                                "flex flex-col gap-2 rounded-md",
                                if (item.status === "signed") {
                                    "bg-orange-300"
                                } else {
                                    if (item.status === "today") {
                                        "today-box"
                                    } else {
                                        if (item.status === "missed") {
                                            "missed-box"
                                        } else {
                                            "future-box"
                                        }
                                    }
                                }
                            ))), _uA(
                                _cE("view", _uM("class" to "sign-day-box flex flex-col items-center justify-center p-2 rounded-lg"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-ziyuan", "color" to if (item.status === "signed") {
                                        "#ffffff"
                                    } else {
                                        if (item.status === "today") {
                                            "#f97316"
                                        } else {
                                            if (item.status === "missed") {
                                                "#fb7185"
                                            } else {
                                                "#d1d5db"
                                            }
                                        }
                                    }
                                    ), null, 8, _uA(
                                        "color"
                                    )),
                                    _cE("text", _uM("class" to _nC(_uA(
                                        "text-xs",
                                        if (item.status === "signed") {
                                            "text-white"
                                        } else {
                                            if (item.status === "today") {
                                                "text-orange-500"
                                            } else {
                                                if (item.status === "missed") {
                                                    "text-rose-400"
                                                } else {
                                                    "text-zice-400"
                                                }
                                            }
                                        }
                                    ))), _tD(item.points), 3)
                                )),
                                _cE("text", _uM("class" to _nC(_uA(
                                    "text-xs text-center rounded-md",
                                    if (item.status === "signed") {
                                        "bg-orange-400 text-zice-300"
                                    } else {
                                        if (item.status === "today") {
                                            "text-zice-400 bg-orange-200"
                                        } else {
                                            if (item.status === "missed") {
                                                "text-rose-500 bg-rose-100"
                                            } else {
                                                "text-zice-400 bg-gray-50"
                                            }
                                        }
                                    }
                                ))), _tD(item.label), 3)
                            ), 2)
                        }
                        ), 128)
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "warning", "block" to "", "shape" to "round", "disabled" to _ctx.isTodaySigned, "onClick" to handleSignIn), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _tD(if (_ctx.isTodaySigned) {
                                "今日已签到"
                            } else {
                                "签到领积分"
                            }
                            )
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "disabled"
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("sign-day-box" to _pS(_uM("transitionProperty" to "all", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease")), "today-box" to _pS(_uM("backgroundColor" to "#fff7ed", "borderTopWidth" to "2rpx", "borderRightWidth" to "2rpx", "borderBottomWidth" to "2rpx", "borderLeftWidth" to "2rpx", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#f97316", "borderRightColor" to "#f97316", "borderBottomColor" to "#f97316", "borderLeftColor" to "#f97316")), "missed-box" to _pS(_uM("backgroundColor" to "#fff1f2", "borderTopWidth" to "2rpx", "borderRightWidth" to "2rpx", "borderBottomWidth" to "2rpx", "borderLeftWidth" to "2rpx", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#fb7185", "borderRightColor" to "#fb7185", "borderBottomColor" to "#fb7185", "borderLeftColor" to "#fb7185")), "future-box" to _pS(_uM("backgroundColor" to "#f3f4f6", "borderTopWidth" to "2rpx", "borderRightWidth" to "2rpx", "borderBottomWidth" to "2rpx", "borderLeftWidth" to "2rpx", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0)", "borderRightColor" to "rgba(0,0,0,0)", "borderBottomColor" to "rgba(0,0,0,0)", "borderLeftColor" to "rgba(0,0,0,0)")), "@TRANSITION" to _uM("sign-day-box" to _uM("property" to "all", "duration" to "0.3s", "timingFunction" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("sign-in" to null, "reminder-change" to null)
        var props = _nP(_uM("signInDays" to _uM("type" to "Number", "required" to true), "signInReminder" to _uM("type" to "Boolean", "required" to true), "isTodaySigned" to _uM("type" to "Boolean", "required" to true), "signInCalendar" to _uM("type" to "Array", "required" to true), "manual" to _uM("type" to "Boolean", "required" to false)))
        var propsNeedCastKeys = _uA(
            "signInReminder",
            "isTodaySigned",
            "manual"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
