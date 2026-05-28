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
open class GenUniModulesTangUiXComponentsTSearchBarIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var placeholder: String? by `$props`
    open var shape: String by `$props`
    open var background: String by `$props`
    open var maxlength: Number by `$props`
    open var clearable: Boolean by `$props`
    open var showCancel: Boolean by `$props`
    open var cancelText: String? by `$props`
    open var disabled: Boolean by `$props`
    open var readonly: Boolean by `$props`
    open var autoFocus: Boolean by `$props`
    open var showLeftIcon: Boolean by `$props`
    open var icon: String? by `$props`
    open var debounce: Number by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTSearchBarIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTSearchBarIndex
            val _cache = __ins.renderCache
            val props = __props
            val modelValue = useModel<Any>(__ins.props, "modelValue") as Ref<String>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val t = useI18n().t
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val displayPlaceholder = computed(fun(): String {
                val placeholder = props.placeholder
                if (hasTextValue(placeholder)) {
                    return placeholder as String
                }
                return t("searchBar.placeholder")
            }
            )
            val displayCancelText = computed(fun(): String {
                val cancelText = props.cancelText
                if (hasTextValue(cancelText)) {
                    return cancelText as String
                }
                return t("searchBar.cancelText")
            }
            )
            var debounceTimer: Number? = null
            val containerClasses = computed(fun(): String {
                val classes = _uA(
                    "t-search-bar flex flex-row items-center px-3 py-2"
                ) as UTSArray<String>
                if (props.shape === "round") {
                    classes.push("t-search-bar--round")
                }
                if (props.disabled) {
                    classes.push("opacity-60")
                }
                if (props.customClass !== "") {
                    classes.push(props.customClass)
                }
                return classes.join(" ")
            }
            )
            val inputStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                styles.push("background-color: " + props.background)
                if (props.customStyle !== "") {
                    styles.push(props.customStyle)
                }
                return styles.join("; ")
            }
            )
            val handleInput = fun(event: SearchBarInputEvent): Unit {
                val value = event.detail.value
                modelValue.value = value
                emit("input", value)
                val timerId = debounceTimer
                if (timerId != null) {
                    clearTimeout(timerId)
                }
                debounceTimer = setTimeout(fun(){
                    emit("search", value)
                }
                , props.debounce)
            }
            val handleFocus = fun(event: Any): Unit {
                emit("focus", event)
            }
            val handleBlur = fun(event: Any): Unit {
                emit("blur", event)
            }
            val handleClear = fun(): Unit {
                modelValue.value = ""
                emit("clear")
                emit("search", "")
            }
            val handleSearch = fun(): Unit {
                emit("search", modelValue.value)
            }
            val handleCancel = fun(): Unit {
                modelValue.value = ""
                emit("cancel")
            }
            val hasInputValue = computed(fun(): Boolean {
                return modelValue.value.length > 0
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(containerClasses.value)), _uA(
                    _cE("view", _uM("class" to _nC(_uA(
                        "t-search-bar__content relative flex flex-1 flex-row items-center px-3 py-2",
                        if (_ctx.shape === "round") {
                            "twx-rounded-18px"
                        } else {
                            "rounded"
                        }
                    )), "style" to _nS(inputStyle.value)), _uA(
                        if (isTrue(_ctx.showLeftIcon)) {
                            _cE("view", _uM("key" to 0, "class" to "t-search-bar__icon-wrapper mr-2 flex items-center justify-center"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-sousuo", "size" to 32, "color" to "#969799"))
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                        ,
                        _cE("input", _uM("class" to "t-search-bar__input flex-1 border-none bg-transparent text-sm text-primary outline-none", "type" to "text", "value" to modelValue.value, "placeholder" to displayPlaceholder.value, "maxlength" to _ctx.maxlength, "disabled" to _ctx.disabled, "focus" to _ctx.autoFocus, "onInput" to handleInput, "onFocus" to handleFocus, "onBlur" to handleBlur, "onConfirm" to handleSearch), null, 40, _uA(
                            "value",
                            "placeholder",
                            "maxlength",
                            "disabled",
                            "focus"
                        )),
                        withDirectives(_cE("view", _uM("class" to "t-search-bar__clear ml-2 flex twx-h-18px twx-w-18px items-center justify-center rounded-full twx-bg-gray-ccc", "onClick" to handleClear), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shanchu", "color" to "#ffffff"))
                        ), 512), _uA(
                            _uA(
                                vShow,
                                _ctx.clearable && hasInputValue.value
                            )
                        ))
                    ), 6),
                    if (isTrue(_ctx.showCancel)) {
                        _cE("view", _uM("key" to 0, "class" to "t-search-bar__cancel cursor-pointer px-3 active:opacity-70", "onClick" to handleCancel), _uA(
                            renderSlot(_ctx.`$slots`, "cancel", _uO(), fun(): UTSArray<Any> {
                                return _uA(
                                    _cE("text", _uM("class" to "t-search-bar__cancel-text whitespace-nowrap text-sm text-primary"), _tD(displayCancelText.value), 1)
                                )
                            })
                        ))
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
                return _uM("twx-rounded-18px" to _pS(_uM("borderTopLeftRadius" to 18, "borderTopRightRadius" to 18, "borderBottomRightRadius" to 18, "borderBottomLeftRadius" to 18)), "twx-h-18px" to _pS(_uM("height" to 18)), "twx-w-18px" to _pS(_uM("width" to 18)), "twx-bg-gray-ccc" to _pS(_uM("backgroundColor" to "#c8c9cc")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("search" to null, "input" to null, "cancel" to null, "clear" to null, "focus" to null, "blur" to null, "update:modelValue" to null)
        var props = _nP(_uM("placeholder" to _uM("type" to "String", "required" to false), "shape" to _uM("type" to "String", "required" to false, "default" to "square"), "background" to _uM("type" to "String", "required" to false, "default" to "var(--tui-surface-muted)"), "maxlength" to _uM("type" to "Number", "required" to false, "default" to -1), "clearable" to _uM("type" to "Boolean", "required" to false, "default" to true), "showCancel" to _uM("type" to "Boolean", "required" to false, "default" to false), "cancelText" to _uM("type" to "String", "required" to false), "disabled" to _uM("type" to "Boolean", "required" to false, "default" to false), "readonly" to _uM("type" to "Boolean", "required" to false, "default" to false), "autoFocus" to _uM("type" to "Boolean", "required" to false, "default" to false), "showLeftIcon" to _uM("type" to "Boolean", "required" to false, "default" to true), "icon" to _uM("type" to "String", "required" to false), "debounce" to _uM("type" to "Number", "required" to false, "default" to 300), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to ""), "modelValue" to _uM("default" to "")))
        var propsNeedCastKeys = _uA(
            "shape",
            "background",
            "maxlength",
            "clearable",
            "showCancel",
            "disabled",
            "readonly",
            "autoFocus",
            "showLeftIcon",
            "debounce",
            "customClass",
            "customStyle",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
