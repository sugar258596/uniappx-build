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
open class GenComponentsPointsPointsHeader : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var totalPoints: Number by `$props`
    open var showNavbar: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsPointsHeader) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsPointsHeader
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleDetailClick = fun(){
                emit("detail-click")
            }
            val handleMallClick = fun(){
                emit("mall-click")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "header-bg"), _uA(
                    if (isTrue(_ctx.showNavbar)) {
                        _cV(unref(GenComponentsNavbarIndexClass), _uM("key" to 0, "title" to "我的积分", "background" to "transparent"))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    _cE("view", _uM("class" to _nC(_uA(
                        "flex flex-row items-center justify-between px-4 pb-12",
                        if (_ctx.showNavbar) {
                            ""
                        } else {
                            "pt-10"
                        }
                    ))), _uA(
                        _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-ziyuan", "size" to 30, "color" to "#ff9635")),
                                _cE("text", _uM("class" to "text-sm"), "我的积分")
                            )),
                            _cE("text", _uM("class" to "text-3xl font-bold"), _tD(_ctx.totalPoints), 1),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-1", "onClick" to fun(){
                                if (_ctx.showNavbar) {
                                    handleDetailClick()
                                } else {
                                    handleMallClick()
                                }
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "text-sm"), _tD(if (_ctx.showNavbar) {
                                    "积分明细"
                                } else {
                                    "积分商城"
                                }
                                ), 1),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 14))
                            ), 8, _uA(
                                "onClick"
                            ))
                        ))
                    ), 2)
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("detail-click" to null, "mall-click" to null)
        var props = _nP(_uM("totalPoints" to _uM("type" to "Number", "required" to true), "showNavbar" to _uM("type" to "Boolean", "required" to false, "default" to true)))
        var propsNeedCastKeys = _uA(
            "showNavbar"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
