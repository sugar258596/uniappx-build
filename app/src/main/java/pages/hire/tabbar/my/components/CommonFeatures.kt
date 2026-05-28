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
open class GenPagesHireTabbarMyComponentsCommonFeatures : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var data: CommonFeatureData by `$props`
    open var isLogin: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarMyComponentsCommonFeatures) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarMyComponentsCommonFeatures
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleOrderClick = fun(){
                emit("orderClick")
            }
            val handlePointsClick = fun(){
                emit("pointsClick")
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "mx-4", "padding" to "medium"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "常用功能"),
                            _cE("view", _uM("class" to "flex flex-row"), _uA(
                                _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 py-2 tap-active", "onClick" to handleOrderClick), _uA(
                                    _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                        props.data.orderCount
                                    } else {
                                        "-"
                                    }
                                    ), 1),
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "我的订单"),
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "right", "size" to 24, "color" to "#9ca3af"))
                                    ))
                                )),
                                _cE("view", _uM("class" to "w-px bg-gray-200 my-2")),
                                _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 py-2 tap-active", "onClick" to handlePointsClick), _uA(
                                    _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                        props.data.pointsCount
                                    } else {
                                        "-"
                                    }
                                    ), 1),
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "我的积分"),
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "right", "size" to 24, "color" to "#9ca3af"))
                                    ))
                                ))
                            ))
                        ))
                    )
                }
                ), "_" to 1))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("orderClick" to null, "pointsClick" to null)
        var props = _nP(_uM("data" to _uM("type" to "Object", "required" to true), "isLogin" to _uM("type" to "Boolean", "required" to true)))
        var propsNeedCastKeys = _uA(
            "isLogin"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
