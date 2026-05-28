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
open class GenUniModulesTangUiXComponentsTRadioGroupIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var options: UTSArray<RadioOption__1> by `$props`
    open var direction: String by `$props`
    open var size: String by `$props`
    open var activeColor: String by `$props`
    open var inactiveColor: String by `$props`
    open var disabled: Boolean by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTRadioGroupIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTRadioGroupIndex
            val _cache = __ins.renderCache
            val props = __props
            val modelValue = useModel<Any>(__ins.props, "modelValue") as Ref<RadioGroupModelValue>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleChange = fun(option: RadioOption__1): Unit {
                if (props.disabled || option.disabled === true) {
                    return
                }
                modelValue.value = option.value
                emit("change", option.value)
            }
            val isChecked = fun(option: RadioOption__1): Boolean {
                return modelValue.value === option.value
            }
            val getSizeConfig = fun(): RadioSizeConfig {
                when (props.size) {
                    "small" -> 
                        return RadioSizeConfig(icon = 28, dot = 14, fontSize = 24)
                    "large" -> 
                        return RadioSizeConfig(icon = 44, dot = 26, fontSize = 32)
                    else -> 
                        return RadioSizeConfig(icon = 36, dot = 20, fontSize = 28)
                }
            }
            val cssVars = computed(fun(): UTSJSONObject {
                val size = getSizeConfig()
                return _uO("--icon-size" to ("" + size.icon + "rpx"), "--dot-size" to ("" + size.dot + "rpx"), "--font-size" to ("" + size.fontSize + "rpx"), "--active-color" to props.activeColor, "--inactive-color" to props.inactiveColor)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(_uA(
                    "t-radio-group",
                    _uA(
                        if (_ctx.direction === "horizontal") {
                            "flex flex-row flex-wrap"
                        } else {
                            "flex flex-col"
                        }
                        ,
                        _ctx.customClass
                    )
                )), "style" to _nS(_uA(
                    _ctx.customStyle,
                    cssVars.value
                ))), _uA(
                    _cE(Fragment, null, RenderHelpers.renderList(_ctx.options, fun(option, index, __index, _cached): Any {
                        return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                            "t-radio-group__item flex flex-row items-center py-3",
                            _uM("opacity-50 cursor-not-allowed" to (_ctx.disabled || option.disabled === true), "mr-6" to (_ctx.direction === "horizontal"))
                        )), "onClick" to fun(){
                            handleChange(option)
                        }
                        ), _uA(
                            _cE("view", _uM("class" to "t-radio-group__icon mr-4 flex items-center justify-center rounded-full border-solid transition-all duration-300", "style" to _nS("width: var(--icon-size); height: var(--icon-size); border-width: 2rpx; border-color: " + (if (isChecked(option)) {
                                "var(--active-color)"
                            } else {
                                "var(--inactive-color)"
                            }
                            ))), _uA(
                                if (isTrue(isChecked(option))) {
                                    _cE("view", _uM("key" to 0, "class" to "t-radio-group__dot rounded-full", "style" to _nS("width: var(--dot-size); height: var(--dot-size); background-color: var(--active-color)")), null, 4)
                                } else {
                                    _cC("v-if", true)
                                }
                            ), 4),
                            _cE("text", _uM("class" to "t-radio-group__label twx-text-323233", "style" to _nS("font-size: var(--font-size);")), _tD(option.label), 5)
                        ), 10, _uA(
                            "onClick"
                        ))
                    }
                    ), 128)
                ), 6)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("twx-text-323233" to _pS(_uM("color" to "#323233")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("change" to null, "update:modelValue" to null)
        var props = _nP(_uM("options" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<RadioOption__1> {
            return _uA<RadioOption__1>()
        }
        ), "direction" to _uM("type" to "String", "required" to false, "default" to "vertical"), "size" to _uM("type" to "String", "required" to false, "default" to "medium"), "activeColor" to _uM("type" to "String", "required" to false, "default" to "#00bba7"), "inactiveColor" to _uM("type" to "String", "required" to false, "default" to "var(--tui-border-strong)"), "disabled" to _uM("type" to "Boolean", "required" to false, "default" to false), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to ""), "modelValue" to _uM("default" to "")))
        var propsNeedCastKeys = _uA(
            "options",
            "direction",
            "size",
            "activeColor",
            "inactiveColor",
            "disabled",
            "customClass",
            "customStyle",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
