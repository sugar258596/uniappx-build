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
open class GenComponentsCommonSearchBarIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var placeholder: String? by `$props`
    open var modelValue: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsCommonSearchBarIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsCommonSearchBarIndex
            val _cache = __ins.renderCache
            val modelValue = useModel<String>(__ins.props, "modelValue")
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleSearch = fun(value: String){
                modelValue.value = value
                emit("search", value)
            }
            val triggerSearch = fun(){
                emit("search", modelValue.value as String)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-row flex-1 justify-center items-center"), _uA(
                    _cV(unref(GenUniModulesTangUiXComponentsTSearchBarIndexClass), _uM("modelValue" to modelValue.value, "onUpdate:modelValue" to fun(`$event`: String){
                        modelValue.value = `$event`
                    }
                    , "background" to "transparent", "custom-class" to "flex-1", "placeholder" to if (_ctx.placeholder != null && _ctx.placeholder != "") {
                        _ctx.placeholder
                    } else {
                        "请输入关键字搜索"
                    }
                    , "show-cancel" to "", "onSearch" to handleSearch), _uM("cancel" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex justify-center items-center w-20 bg-teal-700 text-white rounded-full h-8", "onClick" to triggerSearch), "搜索")
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "placeholder"
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
                return _uM("t-search-bar" to _pS(_uM("paddingTop" to 4, "paddingRight" to 12, "paddingBottom" to 4, "paddingLeft" to 12)), "t-search-bar__cancel" to _uM(".t-search-bar " to _uM("paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("search" to null, "update:modelValue" to null)
        var props = _nP(_uM("placeholder" to _uM("type" to "String", "required" to false), "modelValue" to _uM("type" to "String", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
