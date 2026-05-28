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
open class GenUniModulesTangUiXComponentsTInputIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var type: String by `$props`
    open var size: String by `$props`
    open var placeholder: String by `$props`
    open var disabled: Boolean by `$props`
    open var readonly: Boolean by `$props`
    open var clearable: Boolean by `$props`
    open var showCount: Boolean by `$props`
    open var maxlength: Number by `$props`
    open var prefix: String by `$props`
    open var suffix: String by `$props`
    open var prefixIcon: String by `$props`
    open var suffixIcon: String by `$props`
    open var rows: Number by `$props`
    open var autoHeight: Boolean by `$props`
    open var autoFocus: Boolean by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    open var focusBorderColor: String by `$props`
    open var focusBackgroundColor: String by `$props`
    open var focusBoxShadow: String by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTInputIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTInputIndex
            val _cache = __ins.renderCache
            val props = __props
            val model = useModel<Any>(__ins.props, "modelValue") as Ref<InputModelValue>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val isFocused = ref(false)
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val hasModelValue = computed(fun(): Boolean {
                return model.value != null && model.value.toString() !== ""
            }
            )
            val hasPrefixText = computed(fun(): Boolean {
                return hasTextValue(props.prefix)
            }
            )
            val hasPrefixIcon = computed(fun(): Boolean {
                return hasTextValue(props.prefixIcon)
            }
            )
            val hasSuffixText = computed(fun(): Boolean {
                return hasTextValue(props.suffix)
            }
            )
            val hasSuffixIcon = computed(fun(): Boolean {
                return hasTextValue(props.suffixIcon)
            }
            )
            val canShowClearButton = computed(fun(): Boolean {
                return props.clearable && hasModelValue.value && !props.disabled && !props.readonly
            }
            )
            val inputWrapperClasses = computed(fun(): String {
                val classes = _uA(
                    "relative flex w-full shrink-0 grow-0 basis-auto flex-row items-center box-border min-h-0 min-w-0 border border-solid border-border bg-white transition-colors duration-300"
                ) as UTSArray<String>
                when (props.size) {
                    "large" -> 
                        classes.push("h-11 rounded text-base")
                    "small" -> 
                        classes.push("h-7 rounded text-xs")
                    "mini" -> 
                        classes.push("h-6 rounded text-xs")
                    else -> 
                        classes.push("h-9 rounded text-sm")
                }
                if (props.type === "textarea") {
                    classes.push("h-auto p-0")
                }
                if (props.disabled) {
                    classes.push("cursor-not-allowed border-border-soft bg-page text-disabled")
                }
                if (props.readonly) {
                    classes.push("cursor-default")
                }
                if (isFocused.value) {
                    classes.push("twx-border-focus")
                }
                if (hasTextValue(props.customClass)) {
                    classes.push(props.customClass)
                }
                return classes.join(" ")
            }
            )
            val affixClasses = computed(fun(): String {
                when (props.size) {
                    "small", "mini" -> 
                        return "flex items-center justify-center px-1 text-tertiary"
                    else -> 
                        return "flex items-center justify-center px-2 text-tertiary"
                }
            }
            )
            val inputInnerClasses = computed(fun(): String {
                val classes = _uA(
                    "w-full flex-1 border-none bg-transparent text-secondary outline-none box-border"
                ) as UTSArray<String>
                when (props.size) {
                    "large" -> 
                        classes.push("h-full px-3 text-base")
                    "small", "mini" -> 
                        classes.push("h-full px-2 text-xs")
                    else -> 
                        classes.push("h-full px-3 text-sm")
                }
                if (hasPrefixText.value || hasPrefixIcon.value) {
                    classes.push("pl-0")
                }
                if (hasSuffixText.value || hasSuffixIcon.value || props.clearable) {
                    classes.push("pr-0")
                }
                if (props.disabled) {
                    classes.push("cursor-not-allowed text-disabled")
                } else if (props.readonly) {
                    classes.push("cursor-default")
                }
                return classes.join(" ")
            }
            )
            val textareaClasses = computed(fun(): String {
                val classes = _uA(
                    "min-h-15 w-full flex-1 resize-none border-none bg-transparent px-3 py-2 text-sm leading-normal text-secondary outline-none box-border"
                ) as UTSArray<String>
                if (props.disabled) {
                    classes.push("cursor-not-allowed text-disabled")
                } else if (props.readonly) {
                    classes.push("cursor-default")
                }
                return classes.join(" ")
            }
            )
            val currentLength = computed(fun(): Number {
                val value = if (model.value != null) {
                    model.value.toString()
                } else {
                    ""
                }
                return value.length
            }
            )
            val iconSize = computed(fun(): Number {
                when (props.size) {
                    "large" -> 
                        return 36
                    "medium" -> 
                        return 32
                    "small" -> 
                        return 28
                    "mini" -> 
                        return 24
                    else -> 
                        return 32
                }
            }
            )
            val focusStyle = computed(fun(): String {
                if (!isFocused.value) {
                    return ""
                }
                val styles: UTSArray<String> = _uA()
                if (hasTextValue(props.focusBorderColor)) {
                    styles.push("border-color: " + props.focusBorderColor)
                }
                if (hasTextValue(props.focusBackgroundColor)) {
                    styles.push("background-color: " + props.focusBackgroundColor)
                }
                if (hasTextValue(props.focusBoxShadow)) {
                    styles.push("box-shadow: " + props.focusBoxShadow)
                }
                return styles.join("; ")
            }
            )
            val computedStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                if (hasTextValue(props.customStyle)) {
                    styles.push(props.customStyle)
                }
                if (focusStyle.value !== "") {
                    styles.push(focusStyle.value)
                }
                return styles.join("; ")
            }
            )
            val handleInput = fun(e: InputChangeEvent): Unit {
                val value = e.detail.value
                model.value = value
                emit("input", value)
            }
            val handleFocus = fun(){
                isFocused.value = true
                emit("focus")
            }
            val handleBlur = fun(){
                isFocused.value = false
                emit("blur")
                emit("change", model.value)
            }
            val handleClear = fun(){
                model.value = ""
                emit("input", "")
                emit("change", "")
                emit("clear")
            }
            val handleConfirm = fun(){
                emit("confirm")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(_uA(
                    "t-input",
                    inputWrapperClasses.value
                )), "style" to _nS(computedStyle.value)), _uA(
                    if (isTrue(hasPrefixText.value)) {
                        _cE("view", _uM("key" to 0, "class" to _nC(_uA(
                            "t-input__prefix",
                            affixClasses.value
                        ))), _uA(
                            _cE("text", _uM("class" to "t-input__prefix-text whitespace-nowrap text-sm text-secondary"), _tD(_ctx.prefix), 1)
                        ), 2)
                    } else {
                        if (isTrue(hasPrefixIcon.value)) {
                            _cE("view", _uM("key" to 1, "class" to _nC(_uA(
                                "t-input__prefix",
                                affixClasses.value
                            ))), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to _ctx.prefixIcon, "size" to iconSize.value, "color" to "#909399"), null, 8, _uA(
                                    "name",
                                    "size"
                                ))
                            ), 2)
                        } else {
                            _cC("v-if", true)
                        }
                    }
                    ,
                    if (_ctx.type !== "textarea") {
                        _cE("input", _uM("key" to 2, "class" to _nC(_uA(
                            "t-input__inner",
                            inputInnerClasses.value
                        )), "type" to _ctx.type, "value" to model.value, "placeholder" to _ctx.placeholder, "disabled" to _ctx.disabled, "readonly" to _ctx.readonly, "maxlength" to _ctx.maxlength, "focus" to _ctx.autoFocus, "onInput" to handleInput, "onFocus" to handleFocus, "onBlur" to handleBlur, "onConfirm" to handleConfirm), null, 42, _uA(
                            "type",
                            "value",
                            "placeholder",
                            "disabled",
                            "readonly",
                            "maxlength",
                            "focus"
                        ))
                    } else {
                        _cE("textarea", _uM("key" to 3, "class" to _nC(_uA(
                            "t-input__textarea",
                            textareaClasses.value
                        )), "value" to model.value, "placeholder" to _ctx.placeholder, "disabled" to _ctx.disabled, "readonly" to _ctx.readonly, "maxlength" to _ctx.maxlength, "auto-height" to _ctx.autoHeight, "focus" to _ctx.autoFocus, "onInput" to handleInput, "onFocus" to handleFocus, "onBlur" to handleBlur), null, 42, _uA(
                            "value",
                            "placeholder",
                            "disabled",
                            "readonly",
                            "maxlength",
                            "auto-height",
                            "focus"
                        ))
                    }
                    ,
                    if (isTrue(canShowClearButton.value)) {
                        _cE("view", _uM("key" to 4, "class" to _nC(_uA(
                            "t-input__suffix t-input__clear cursor-pointer transition-opacity duration-300 active:opacity-70",
                            affixClasses.value
                        )), "onClick" to handleClear), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shanchu", "size" to iconSize.value, "color" to "#909399"), null, 8, _uA(
                                "size"
                            ))
                        ), 2)
                    } else {
                        if (isTrue(hasSuffixText.value)) {
                            _cE("view", _uM("key" to 5, "class" to _nC(_uA(
                                "t-input__suffix",
                                affixClasses.value
                            ))), _uA(
                                _cE("text", _uM("class" to "t-input__suffix-text whitespace-nowrap text-sm text-secondary"), _tD(_ctx.suffix), 1)
                            ), 2)
                        } else {
                            if (isTrue(hasSuffixIcon.value)) {
                                _cE("view", _uM("key" to 6, "class" to _nC(_uA(
                                    "t-input__suffix",
                                    affixClasses.value
                                ))), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to _ctx.suffixIcon, "size" to iconSize.value, "color" to "#909399"), null, 8, _uA(
                                        "name",
                                        "size"
                                    ))
                                ), 2)
                            } else {
                                _cC("v-if", true)
                            }
                        }
                    }
                    ,
                    if (isTrue(_ctx.showCount && _ctx.maxlength > 0)) {
                        _cE("view", _uM("key" to 7, "class" to "t-input__count absolute bottom-1 right-2"), _uA(
                            _cE("text", _uM("class" to "t-input__count-text text-xs text-tertiary"), _tD(currentLength.value) + "/" + _tD(_ctx.maxlength), 1)
                        ))
                    } else {
                        _cC("v-if", true)
                    }
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
                return _uM("twx-text-link" to _pS(_uM("color" to "#1890ff")), "twx-border-soft" to _pS(_uM("borderTopColor" to "#ebedf0", "borderRightColor" to "#ebedf0", "borderBottomColor" to "#ebedf0", "borderLeftColor" to "#ebedf0")), "twx-min-w-15" to _pS(_uM("minWidth" to 60)), "twx-border-focus" to _pS(_uM("borderTopColor" to "#409eff", "borderRightColor" to "#409eff", "borderBottomColor" to "#409eff", "borderLeftColor" to "#409eff")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("input" to null, "change" to null, "focus" to null, "blur" to null, "clear" to null, "confirm" to null, "update:modelValue" to null)
        var props = _nP(_uM("type" to _uM("type" to "String", "required" to false, "default" to "text"), "size" to _uM("type" to "String", "required" to false, "default" to "medium"), "placeholder" to _uM("type" to "String", "required" to false, "default" to ""), "disabled" to _uM("type" to "Boolean", "required" to false, "default" to false), "readonly" to _uM("type" to "Boolean", "required" to false, "default" to false), "clearable" to _uM("type" to "Boolean", "required" to false, "default" to false), "showCount" to _uM("type" to "Boolean", "required" to false, "default" to false), "maxlength" to _uM("type" to "Number", "required" to false, "default" to -1), "prefix" to _uM("type" to "String", "required" to false, "default" to ""), "suffix" to _uM("type" to "String", "required" to false, "default" to ""), "prefixIcon" to _uM("type" to "String", "required" to false, "default" to ""), "suffixIcon" to _uM("type" to "String", "required" to false, "default" to ""), "rows" to _uM("type" to "Number", "required" to false, "default" to 3), "autoHeight" to _uM("type" to "Boolean", "required" to false, "default" to false), "autoFocus" to _uM("type" to "Boolean", "required" to false, "default" to false), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to ""), "focusBorderColor" to _uM("type" to "String", "required" to false, "default" to "#409eff"), "focusBackgroundColor" to _uM("type" to "String", "required" to false, "default" to ""), "focusBoxShadow" to _uM("type" to "String", "required" to false, "default" to ""), "modelValue" to _uM("default" to "")))
        var propsNeedCastKeys = _uA(
            "type",
            "size",
            "placeholder",
            "disabled",
            "readonly",
            "clearable",
            "showCount",
            "maxlength",
            "prefix",
            "suffix",
            "prefixIcon",
            "suffixIcon",
            "rows",
            "autoHeight",
            "autoFocus",
            "customClass",
            "customStyle",
            "focusBorderColor",
            "focusBackgroundColor",
            "focusBoxShadow",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
