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
open class GenUniModulesTangUiXComponentsTCheckboxIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var disabled: Boolean by `$props`
    open var size: String by `$props`
    open var activeColor: String by `$props`
    open var inactiveColor: String by `$props`
    open var square: Boolean by `$props`
    open var borderWidth: Number by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTCheckboxIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTCheckboxIndex
            val _cache = __ins.renderCache
            val props = __props
            val model = useModel<Any>(__ins.props, "modelValue") as Ref<Boolean>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val isChecked = computed(fun(): Boolean {
                return model.value === true
            }
            )
            val isCheckedValue = fun(): Boolean {
                return isChecked.value
            }
            val getCheckboxSize = fun(): Number {
                when (props.size) {
                    "small" -> 
                        return 32
                    "large" -> 
                        return 48
                    else -> 
                        return 40
                }
            }
            val checkboxSize = computed(fun(): Number {
                return getCheckboxSize()
            }
            )
            val iconSize = computed(fun(): Number {
                val baseSize = getCheckboxSize()
                return Math.floor(baseSize * 0.6)
            }
            )
            val cssVars = computed(fun(): UTSJSONObject {
                return (_uO("--checkbox-size" to ("" + checkboxSize.value + "rpx"), "--checkbox-icon-size" to ("" + iconSize.value + "rpx"), "--checkbox-active-color" to props.activeColor, "--checkbox-inactive-color" to props.inactiveColor, "--checkbox-border-width" to ("" + props.borderWidth + "rpx")))
            }
            )
            val checkboxClass = computed(fun(): String {
                val classes = _uA(
                    "t-checkbox relative flex flex-row items-center transition-all duration-200"
                ) as UTSArray<String>
                if (props.disabled == true) {
                    classes.push("opacity-50")
                } else {
                    classes.push("active:scale-95 cursor-pointer")
                }
                return classes.join(" ")
            }
            )
            val iconClass = computed(fun(): String {
                val classes = _uA(
                    "t-checkbox__icon flex items-center justify-center bg-white transition-all duration-200"
                ) as UTSArray<String>
                classes.push(if (props.square == true) {
                    "t-checkbox__icon--square"
                } else {
                    "t-checkbox__icon--round"
                }
                )
                return classes.join(" ")
            }
            )
            val checkboxStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                styles.push("width: var(--checkbox-size)")
                styles.push("height: var(--checkbox-size)")
                styles.push("border-width: var(--checkbox-border-width)")
                styles.push("border-style: solid")
                styles.push("border-color: " + (if (isChecked.value) {
                    props.activeColor
                } else {
                    props.inactiveColor
                }
                ))
                if (isChecked.value) {
                    styles.push("background-color: " + props.activeColor)
                }
                return styles.join("; ")
            }
            )
            val checkmarkStyle = computed(fun(): String {
                val sizeMap: UTSJSONObject = _uO("small" to 20, "medium" to 24, "large" to 28)
                return "font-size: " + sizeMap[props.size] + "rpx; line-height: 1;"
            }
            )
            val handleClick = fun(event: Any): Unit {
                if (props.disabled == true) {
                    return
                }
                val newValue = isChecked.value !== true
                model.value = newValue
                emit("change", newValue)
                emit("click", event)
            }
            val handleTouchStart = fun(){
                if (props.disabled == true) {
                    return
                }
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(checkboxClass.value), "style" to _nS(cssVars.value), "onClick" to handleClick, "onTouchstart" to handleTouchStart), _uA(
                    _cE("view", _uM("class" to _nC(iconClass.value), "style" to _nS(checkboxStyle.value)), _uA(
                        if (isTrue(isCheckedValue())) {
                            _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center", "style" to _nS("width: var(--checkbox-icon-size); height: var(--checkbox-icon-size);")), _uA(
                                _cE("text", _uM("class" to "font-bold text-white", "style" to _nS(checkmarkStyle.value)), "✓", 4)
                            ), 4)
                        } else {
                            renderSlot(_ctx.`$slots`, "unchecked", _uM("key" to 1))
                        }
                    ), 6),
                    _cE("view", _uM("class" to "t-checkbox__content min-w-0 flex-1"), _uA(
                        renderSlot(_ctx.`$slots`, "default")
                    ))
                ), 38)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("t-checkbox__icon--square" to _pS(_uM("borderTopLeftRadius" to "8rpx", "borderTopRightRadius" to "8rpx", "borderBottomRightRadius" to "8rpx", "borderBottomLeftRadius" to "8rpx")), "t-checkbox__icon--round" to _pS(_uM("borderTopLeftRadius" to "9999rpx", "borderTopRightRadius" to "9999rpx", "borderBottomRightRadius" to "9999rpx", "borderBottomLeftRadius" to "9999rpx")), "t-checkbox__content" to _pS(_uM("marginLeft" to "12rpx")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("change" to null, "click" to null, "update:modelValue" to null)
        var props = _nP(_uM("disabled" to _uM("type" to "Boolean", "required" to false, "default" to false), "size" to _uM("type" to "String", "required" to false, "default" to "medium"), "activeColor" to _uM("type" to "String", "required" to false, "default" to "#00bba7"), "inactiveColor" to _uM("type" to "String", "required" to false, "default" to "var(--tui-border-strong)"), "square" to _uM("type" to "Boolean", "required" to false, "default" to false), "borderWidth" to _uM("type" to "Number", "required" to false, "default" to 2), "modelValue" to _uM("default" to false)))
        var propsNeedCastKeys = _uA(
            "disabled",
            "size",
            "activeColor",
            "inactiveColor",
            "square",
            "borderWidth",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
