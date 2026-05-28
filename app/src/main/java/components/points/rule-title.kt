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
open class GenComponentsPointsRuleTitle : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var ruleBtnText: String by `$props`
    open var rulesContent: UTSArray<String> by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsRuleTitle) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsRuleTitle
            val _cache = __ins.renderCache
            val rulesVisible = ref(false)
            val handleShowRules = fun(){
                rulesVisible.value = !rulesVisible.value
            }
            val closeRules = fun(){
                rulesVisible.value = false
            }
            return fun(): Any? {
                return _cE(Fragment, null, _uA(
                    _cE("view", _uM("class" to "relative overflow-visible", "onClick" to handleShowRules), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-guanyu", "size" to 28, "color" to "#999")),
                            _cE("text", _uM("class" to "text-sm text-gray-500"), _tD(_ctx.ruleBtnText), 1)
                        )),
                        if (isTrue(rulesVisible.value)) {
                            _cE("view", _uM("key" to 0, "class" to "absolute right-0 mt-2 z-50 overflow-visible", "style" to _nS(_uM("top" to "100%"))), _uA(
                                _cE("view", _uM("class" to "rules-tooltip-arrow")),
                                _cE("view", _uM("class" to "rules-tooltip-content"), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(_ctx.rulesContent, fun(rule, index, __index, _cached): Any {
                                        return _cE("text", _uM("key" to index, "class" to "text-xs text-white"), _tD(rule), 1)
                                    }), 128)
                                ))
                            ), 4)
                        } else {
                            _cC("v-if", true)
                        }
                    )),
                    if (isTrue(rulesVisible.value)) {
                        _cE("view", _uM("key" to 0, "class" to "fixed inset-0 z-40", "onClick" to closeRules))
                    } else {
                        _cC("v-if", true)
                    }
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
                return _uM("rules-tooltip-arrow" to _pS(_uM("position" to "absolute", "top" to -6, "right" to 16, "width" to 0, "height" to 0, "borderLeftWidth" to 6, "borderLeftStyle" to "solid", "borderLeftColor" to "rgba(0,0,0,0)", "borderRightWidth" to 6, "borderRightStyle" to "solid", "borderRightColor" to "rgba(0,0,0,0)", "borderBottomWidth" to 6, "borderBottomStyle" to "solid", "borderBottomColor" to "rgba(0,0,0,0.75)")), "rules-tooltip-content" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.75)", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8, "paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "minWidth" to 200, "maxWidth" to 280, "display" to "flex", "flexDirection" to "column", "gap" to "8px")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM("ruleBtnText" to _uM("type" to "String", "required" to false, "default" to "规则说明"), "rulesContent" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<String> {
            return _uA(
                "1. 规则说明一",
                "2. 规则说明二"
            )
        }
        )))
        var propsNeedCastKeys = _uA(
            "ruleBtnText",
            "rulesContent"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
