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
open class GenUniModulesTangUiXComponentsTButtonIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var type: String by `$props`
    open var size: String by `$props`
    open var shape: String by `$props`
    open var plain: Boolean by `$props`
    open var disabled: Boolean by `$props`
    open var loading: Boolean by `$props`
    open var block: Boolean by `$props`
    open var text: String by `$props`
    open var icon: String by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTButtonIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTButtonIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val buttonClasses = computed(fun(): String {
                val classes = _uA(
                    "relative inline-flex shrink-0 grow-0 basis-auto select-none items-center justify-center box-border min-h-0 min-w-0 border border-solid border-transparent bg-none text-center outline-none transition-all duration-300 appearance-none"
                ) as UTSArray<String>
                when (props.size) {
                    "large" -> 
                        classes.push("h-11 px-5 text-base")
                    "small" -> 
                        classes.push("h-7 px-3 text-xs")
                    "mini" -> 
                        classes.push("h-6 px-2 text-xs")
                    else -> 
                        classes.push("h-9 px-4 text-sm")
                }
                when (props.shape) {
                    "round" -> 
                        classes.push("rounded-full")
                    "circle" -> 
                        {
                            classes.push("rounded-full p-0")
                            when (props.size) {
                                "large" -> 
                                    classes.push("w-11")
                                "small" -> 
                                    classes.push("w-7")
                                "mini" -> 
                                    classes.push("w-6")
                                else -> 
                                    classes.push("w-9")
                            }
                        }
                    else -> 
                        classes.push("rounded")
                }
                if (props.block) {
                    classes.push("flex w-full")
                }
                if (props.disabled) {
                    classes.push("cursor-not-allowed opacity-60")
                }
                when (props.type) {
                    "primary" -> 
                        classes.push(if (props.plain) {
                            "border-blue-200 bg-blue-50 text-blue-500"
                        } else {
                            "border-blue-500 bg-blue-500 text-white"
                        }
                        )
                    "success" -> 
                        classes.push(if (props.plain) {
                            "border-green-200 bg-green-50 text-green-500"
                        } else {
                            "border-green-500 bg-green-500 text-white"
                        }
                        )
                    "warning" -> 
                        classes.push(if (props.plain) {
                            "border-orange-200 bg-orange-50 text-orange-400"
                        } else {
                            "border-orange-400 bg-orange-400 text-white"
                        }
                        )
                    "danger" -> 
                        classes.push(if (props.plain) {
                            "border-red-200 bg-red-50 text-red-400"
                        } else {
                            "border-red-400 bg-red-400 text-white"
                        }
                        )
                    "info" -> 
                        classes.push(if (props.plain) {
                            "border-zinc-300 bg-zinc-100 text-zinc-400"
                        } else {
                            "border-zinc-400 bg-zinc-400 text-white"
                        }
                        )
                    else -> 
                        classes.push("border-gray-300 bg-white text-gray-600")
                }
                if (props.customClass !== "") {
                    classes.push(props.customClass)
                }
                return classes.join(" ")
            }
            )
            val buttonStyles = computed(fun(): String {
                return props.customStyle
            }
            )
            val hoverClass = computed(fun(): String {
                if (props.disabled || props.loading) {
                    return "none"
                }
                return "opacity-90"
            }
            )
            val handleClick = fun(){
                if (props.disabled || props.loading) {
                    return
                }
                emit("click")
            }
            return fun(): Any? {
                return _cE("button", _uM("class" to _nC(_uA(
                    "t-button",
                    buttonClasses.value
                )), "style" to _nS(buttonStyles.value), "disabled" to (_ctx.disabled || _ctx.loading), "hover-class" to hoverClass.value, "onClick" to handleClick), _uA(
                    if (isTrue(_ctx.loading)) {
                        _cE("view", _uM("key" to 0, "class" to "t-button__loading mr-1 flex items-center"), _uA(
                            _cE("text", _uM("class" to "t-button__loading-icon inline-block animate-spin text-sm"), "⟳")
                        ))
                    } else {
                        if (_ctx.icon !== "") {
                            _cE("view", _uM("key" to 1, "class" to "t-button__icon mr-1 flex items-center"), _uA(
                                _cE("text", _uM("class" to "t-button__icon-text text-base"), _tD(_ctx.icon), 1)
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                    }
                    ,
                    _cE("view", _uM("class" to "t-button__text flex items-center"), _uA(
                        renderSlot(_ctx.`$slots`, "default", _uO(), fun(): UTSArray<Any> {
                            return _uA(
                                _tD(_ctx.text)
                            )
                        }
                        )
                    ))
                ), 14, _uA(
                    "disabled",
                    "hover-class"
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
                return _uM("transition-all" to _pS(_uM("transitionProperty" to "all", "transitionDuration" to "300ms")), "@TRANSITION" to _uM("transition-all" to _uM("property" to "all", "duration" to "300ms")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null)
        var props = _nP(_uM("type" to _uM("type" to "String", "required" to false, "default" to "default"), "size" to _uM("type" to "String", "required" to false, "default" to "medium"), "shape" to _uM("type" to "String", "required" to false, "default" to "square"), "plain" to _uM("type" to "Boolean", "required" to false, "default" to false), "disabled" to _uM("type" to "Boolean", "required" to false, "default" to false), "loading" to _uM("type" to "Boolean", "required" to false, "default" to false), "block" to _uM("type" to "Boolean", "required" to false, "default" to false), "text" to _uM("type" to "String", "required" to false, "default" to ""), "icon" to _uM("type" to "String", "required" to false, "default" to ""), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to "")))
        var propsNeedCastKeys = _uA(
            "type",
            "size",
            "shape",
            "plain",
            "disabled",
            "loading",
            "block",
            "text",
            "icon",
            "customClass",
            "customStyle"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
