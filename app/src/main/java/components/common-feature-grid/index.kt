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
open class GenComponentsCommonFeatureGridIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String by `$props`
    open var items: UTSArray<FeatureMenuItem> by `$props`
    open var iconColor: String by `$props`
    open var iconBgColor: String by `$props`
    open var columns: Number by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsCommonFeatureGridIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsCommonFeatureGridIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleMenuClick = fun(item: FeatureMenuItem){
                emit("menuClick", item)
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "mx-4 mt-2", "padding" to "medium"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), _tD(props.title), 1),
                            _cE("view", _uM("class" to "flex flex-row flex-wrap"), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(props.items, fun(item, index, __index, _cached): Any {
                                    return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                        "flex flex-col items-center gap-1 py-1 tap-active",
                                        if (props.columns > 0) {
                                            "w-1-" + props.columns
                                        } else {
                                            "flex-1"
                                        }
                                    )), "onClick" to fun(){
                                        handleMenuClick(item)
                                    }
                                    ), _uA(
                                        _cE("view", _uM("class" to "w-10 h-10 rounded-full flex items-center justify-center", "style" to _nS(_uM("backgroundColor" to props.iconBgColor))), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to item.icon, "size" to 40, "color" to props.iconColor), null, 8, _uA(
                                                "name",
                                                "color"
                                            ))
                                        ), 4),
                                        _cE("text", _uM("class" to "text-xs text-gray-600"), _tD(item.label), 1)
                                    ), 10, _uA(
                                        "onClick"
                                    ))
                                }
                                ), 128)
                            ))
                        ))
                    )
                }
                ), "_" to 1))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("w-1-3" to _pS(_uM("width" to "33.333333%")), "w-1-4" to _pS(_uM("width" to "25%")), "w-1-5" to _pS(_uM("width" to "20%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("menuClick" to null)
        var props = _nP(_uM("title" to _uM("type" to "String", "required" to true), "items" to _uM("type" to "Array", "required" to true), "iconColor" to _uM("type" to "String", "required" to false, "default" to "#fe8d00"), "iconBgColor" to _uM("type" to "String", "required" to false, "default" to "#f3f4f6"), "columns" to _uM("type" to "Number", "required" to false, "default" to 0)))
        var propsNeedCastKeys = _uA(
            "iconColor",
            "iconBgColor",
            "columns"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
