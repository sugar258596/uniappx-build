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
open class GenUniModulesTangUiXComponentsTSwitchIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var disabled: Boolean by `$props`
    open var size: String by `$props`
    open var activeColor: String by `$props`
    open var inactiveColor: String by `$props`
    open var activeText: String by `$props`
    open var inactiveText: String by `$props`
    open var showText: Boolean by `$props`
    open var manual: Boolean by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTSwitchIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTSwitchIndex
            val _cache = __ins.renderCache
            val props = __props
            val model = useModel<Any>(__ins.props, "modelValue") as Ref<Boolean>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val checked = ref<Boolean>(model.value)
            watch(model, fun(newVal: Boolean){
                val value = newVal
                checked.value = value
                return
            }
            )
            val switchClass = computed(fun(): String {
                val classes = _uA(
                    "t-switch relative twx-rounded-100px transition-colors duration-300"
                ) as UTSArray<String>
                when (props.size) {
                    "small" -> 
                        classes.push("twx-h-18px w-8")
                    "large" -> 
                        classes.push("h-6 twx-w-50px")
                    else -> 
                        classes.push("h-5 w-10")
                }
                return classes.join(" ")
            }
            )
            val wrapperClass = computed(fun(): String {
                val classes = _uA(
                    "t-switch-wrapper inline-flex flex-row items-center"
                ) as UTSArray<String>
                if (props.disabled) {
                    classes.push("cursor-not-allowed opacity-60")
                } else {
                    classes.push("cursor-pointer")
                }
                return classes.join(" ")
            }
            )
            val buttonClass = computed(fun(): String {
                val classes = _uA(
                    "t-switch__button absolute left-0.5 top-0.5 rounded-full bg-white twx-shadow-sm"
                ) as UTSArray<String>
                when (props.size) {
                    "small" -> 
                        classes.push("twx-h-14px twx-w-14px")
                    "large" -> 
                        classes.push("h-5 w-5")
                    else -> 
                        classes.push("h-4 w-4")
                }
                return classes.join(" ")
            }
            )
            val buttonStyle = computed(fun(): String {
                var translateX = "0px"
                if (checked.value) {
                    when (props.size) {
                        "small" -> 
                            translateX = "14px"
                        "large" -> 
                            translateX = "26px"
                        else -> 
                            translateX = "20px"
                    }
                }
                return "transform: translateX(" + translateX + "); transition: transform 0.3s;"
            }
            )
            val switchStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                val bgColor = if (checked.value) {
                    props.activeColor
                } else {
                    props.inactiveColor
                }
                if (bgColor != "") {
                    styles.push("background-color: " + bgColor)
                }
                return styles.join("; ")
            }
            )
            val handleClick = fun(): Unit {
                if (props.disabled) {
                    return
                }
                val newValue = checked.value !== true
                if (props.manual) {
                    emit("change", newValue)
                    return
                }
                checked.value = newValue
                model.value = newValue
                emit("change", newValue)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(wrapperClass.value), "onClick" to handleClick), _uA(
                    if (isTrue(_ctx.showText && _ctx.inactiveText !== "")) {
                        _cE("text", _uM("key" to 0, "class" to "t-switch__text t-switch__text--inactive mr-2 text-sm text-secondary"), _tD(_ctx.inactiveText), 1)
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    _cE("view", _uM("class" to _nC(switchClass.value), "style" to _nS(switchStyle.value)), _uA(
                        _cE("view", _uM("class" to _nC(buttonClass.value), "style" to _nS(buttonStyle.value)), null, 6)
                    ), 6),
                    if (isTrue(_ctx.showText && _ctx.activeText !== "")) {
                        _cE("text", _uM("key" to 1, "class" to "t-switch__text t-switch__text--active ml-2 text-sm text-secondary"), _tD(_ctx.activeText), 1)
                    } else {
                        _cC("v-if", true)
                    }
                ), 2)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("twx-rounded-100px" to _pS(_uM("borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100)), "twx-h-18px" to _pS(_uM("height" to 18)), "twx-w-50px" to _pS(_uM("width" to 50)), "twx-h-14px" to _pS(_uM("height" to 14)), "twx-w-14px" to _pS(_uM("width" to 14)), "twx-shadow-sm" to _pS(_uM("boxShadow" to "0 2px 4px rgba(0, 0, 0, 0.2)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("change" to null, "update:modelValue" to null)
        var props = _nP(_uM("disabled" to _uM("type" to "Boolean", "required" to false, "default" to false), "size" to _uM("type" to "String", "required" to false, "default" to "medium"), "activeColor" to _uM("type" to "String", "required" to false, "default" to "#409eff"), "inactiveColor" to _uM("type" to "String", "required" to false, "default" to "var(--tui-border)"), "activeText" to _uM("type" to "String", "required" to false, "default" to ""), "inactiveText" to _uM("type" to "String", "required" to false, "default" to ""), "showText" to _uM("type" to "Boolean", "required" to false, "default" to false), "manual" to _uM("type" to "Boolean", "required" to false, "default" to false), "modelValue" to _uM("default" to false)))
        var propsNeedCastKeys = _uA(
            "disabled",
            "size",
            "activeColor",
            "inactiveColor",
            "activeText",
            "inactiveText",
            "showText",
            "manual",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
