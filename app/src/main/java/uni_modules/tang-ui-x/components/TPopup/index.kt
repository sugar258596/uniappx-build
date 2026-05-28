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
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
open class GenUniModulesTangUiXComponentsTPopupIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var height: Any by `$props`
    open var width: Any by `$props`
    open var position: String by `$props`
    open var showTitle: Boolean by `$props`
    open var title: String by `$props`
    open var showClose: Boolean by `$props`
    open var safeAreaInsetTop: Boolean by `$props`
    open var round: Boolean by `$props`
    open var borderRadius: Any by `$props`
    open var closeOnClickOverlay: Boolean by `$props`
    open var zIndex: Number by `$props`
    open var background: String by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTPopupIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTPopupIndex
            val _cache = __ins.renderCache
            val model = useModel<Any>(__ins.props, "modelValue")
            val rendered = ref(false)
            val showOverlay = ref(false)
            val props = __props
            val titleBarHeight: Number = 48
            val getSystemTopInset = fun(): Number {
                val systemInfo = uni_getSystemInfoSync() as UTSJSONObject
                if (resolveInOperator(systemInfo, "safeAreaInsets")) {
                    val safeAreaInsets = systemInfo["safeAreaInsets"] as UTSJSONObject
                    if (resolveInOperator(safeAreaInsets, "top")) {
                        val safeTop = safeAreaInsets["top"]
                        if (UTSAndroid.`typeof`(safeTop) === "number") {
                            return safeTop as Number
                        }
                    }
                }
                if (resolveInOperator(systemInfo, "statusBarHeight")) {
                    val statusBarHeight = systemInfo["statusBarHeight"]
                    if (UTSAndroid.`typeof`(statusBarHeight) === "number") {
                        return statusBarHeight as Number
                    }
                }
                return 0
            }
            val topSafeArea = computed(fun(): Number {
                if (props.safeAreaInsetTop != true) {
                    return 0
                }
                if (props.position === "bottom" || props.position === "center") {
                    return 0
                }
                return getSystemTopInset()
            }
            )
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val popupHeight = computed(fun(): String {
                if (UTSAndroid.`typeof`(props.height) === "number") {
                    val heightValue = props.height as Number
                    return heightValue.toString(10) + "rpx"
                }
                return props.height as String
            }
            )
            val popupWidth = computed(fun(): String {
                if (UTSAndroid.`typeof`(props.width) === "number") {
                    val widthValue = props.width as Number
                    return widthValue.toString(10) + "rpx"
                }
                return props.width as String
            }
            )
            val popupBorderRadius = computed(fun(): String {
                if (UTSAndroid.`typeof`(props.borderRadius) === "number") {
                    val borderRadiusValue = props.borderRadius as Number
                    return borderRadiusValue.toString(10) + "rpx"
                }
                return props.borderRadius as String
            }
            )
            val popupStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                if (props.position === "left" || props.position === "right") {
                    styles.push("width: " + popupWidth.value)
                    styles.push("height: 100%")
                } else if (props.position === "center") {
                    styles.push("width: " + popupWidth.value)
                    styles.push("height: " + popupHeight.value)
                } else {
                    styles.push("height: " + popupHeight.value)
                    styles.push("width: 100%")
                }
                styles.push("z-index: " + props.zIndex)
                styles.push("background-color: " + props.background)
                if (props.round) {
                    when (props.position) {
                        "bottom" -> 
                            {
                                styles.push("border-top-left-radius: " + popupBorderRadius.value)
                                styles.push("border-top-right-radius: " + popupBorderRadius.value)
                            }
                        "top" -> 
                            {
                                styles.push("border-bottom-left-radius: " + popupBorderRadius.value)
                                styles.push("border-bottom-right-radius: " + popupBorderRadius.value)
                            }
                        "left" -> 
                            {
                                styles.push("border-top-right-radius: " + popupBorderRadius.value)
                                styles.push("border-bottom-right-radius: " + popupBorderRadius.value)
                            }
                        "right" -> 
                            {
                                styles.push("border-top-left-radius: " + popupBorderRadius.value)
                                styles.push("border-bottom-left-radius: " + popupBorderRadius.value)
                            }
                        "center" -> 
                            styles.push("border-radius: " + popupBorderRadius.value)
                    }
                }
                return styles.join("; ")
            }
            )
            val popupMotionStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                when (props.position) {
                    "top" -> 
                        {
                            styles.push("transform: " + (if (showOverlay.value) {
                                "translateY(0)"
                            } else {
                                "translateY(-100%)"
                            }
                            ))
                            styles.push("transition: transform 0.35s cubic-bezier(0.25, 0.46, 0.45, 0.94)")
                        }
                    "left" -> 
                        {
                            styles.push("transform: " + (if (showOverlay.value) {
                                "translateX(0)"
                            } else {
                                "translateX(-100%)"
                            }
                            ))
                            styles.push("transition: transform 0.35s cubic-bezier(0.25, 0.46, 0.45, 0.94)")
                        }
                    "right" -> 
                        {
                            styles.push("transform: " + (if (showOverlay.value) {
                                "translateX(0)"
                            } else {
                                "translateX(100%)"
                            }
                            ))
                            styles.push("transition: transform 0.35s cubic-bezier(0.25, 0.46, 0.45, 0.94)")
                        }
                    "center" -> 
                        {
                            styles.push("transform: translate(-50%, -50%)")
                            styles.push("opacity: " + (if (showOverlay.value) {
                                "1"
                            } else {
                                "0"
                            }
                            ))
                            styles.push("transition: opacity 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94)")
                        }
                    else -> 
                        {
                            styles.push("transform: " + (if (showOverlay.value) {
                                "translateY(0)"
                            } else {
                                "translateY(100%)"
                            }
                            ))
                            styles.push("transition: transform 0.35s cubic-bezier(0.25, 0.46, 0.45, 0.94)")
                        }
                }
                return styles.join("; ")
            }
            )
            val contentStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                if (popupStyle.value != "") {
                    styles.push(popupStyle.value)
                }
                if (popupMotionStyle.value != "") {
                    styles.push(popupMotionStyle.value)
                }
                return styles.join("; ")
            }
            )
            val titleBarStyle = computed(fun(): String {
                val safeTop = topSafeArea.value
                val styles = _uA(
                    "height: " + (titleBarHeight + safeTop) + "px"
                ) as UTSArray<String>
                if (safeTop > 0) {
                    styles.push("padding-top: " + safeTop + "px")
                    styles.push("box-sizing: border-box")
                }
                return styles.join("; ")
            }
            )
            val closeStyle = computed(fun(): String {
                return "top: " + (topSafeArea.value + titleBarHeight / 2) + "px; transform: translateY(-50%)"
            }
            )
            val overlayStyle = computed(fun(): String {
                return "z-index: " + (props.zIndex - 1) + "; transition: opacity 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94)"
            }
            )
            val contentClass = computed(fun(): String {
                val classes = _uA(
                    "fixed flex flex-col overflow-hidden bg-white"
                ) as UTSArray<String>
                when (props.position) {
                    "top" -> 
                        classes.push("left-0 right-0 top-0")
                    "left" -> 
                        classes.push("bottom-0 left-0 top-0")
                    "right" -> 
                        classes.push("bottom-0 right-0 top-0")
                    "center" -> 
                        classes.push("left-1/2 top-1/2")
                    else -> 
                        classes.push("bottom-0 left-0 right-0")
                }
                return classes.join(" ")
            }
            )
            val wrapperClass = computed(fun(): String {
                val classes = _uA(
                    "flex"
                ) as UTSArray<String>
                when (props.position) {
                    "top" -> 
                        classes.push("items-start")
                    "left" -> 
                        classes.push("justify-start")
                    "right" -> 
                        classes.push("justify-end")
                    "center" -> 
                        classes.push("items-center justify-center")
                    else -> 
                        classes.push("items-end")
                }
                return classes.join(" ")
            }
            )
            val disableBodyScroll = fun(){}
            val enableBodyScroll = fun(){}
            val handleClose = fun(){
                showOverlay.value = false
                setTimeout(fun(){
                    rendered.value = false
                    enableBodyScroll()
                    model.value = false
                    emit("close")
                }
                , 350)
            }
            val handleOverlayClick = fun(){
                if (props.closeOnClickOverlay) {
                    handleClose()
                }
            }
            val stopPropagation = fun(_e: Any){}
            watch(model, fun(newVal: Boolean){
                if (newVal) {
                    rendered.value = true
                    showOverlay.value = false
                    nextTick(fun(){
                        setTimeout(fun(){
                            showOverlay.value = true
                            emit("open")
                            disableBodyScroll()
                        }, 10)
                    })
                } else {
                    if (rendered.value) {
                        handleClose()
                    }
                }
            }
            )
            return fun(): Any? {
                return if (isTrue(rendered.value)) {
                    _cE("view", _uM("key" to 0, "class" to _nC(wrapperClass.value)), _uA(
                        _cE("view", _uM("class" to _nC(_uA(
                            "fixed inset-0 overflow-hidden bg-black/50 touch-none select-none",
                            _uM("opacity-0" to !showOverlay.value, "opacity-100" to showOverlay.value)
                        )), "style" to _nS(overlayStyle.value), "onClick" to handleOverlayClick), null, 6),
                        _cE("view", _uM("class" to _nC(contentClass.value), "style" to _nS(contentStyle.value), "onClick" to stopPropagation), _uA(
                            if (isTrue(_ctx.showTitle)) {
                                _cE("view", _uM("key" to 0, "class" to "relative flex shrink-0 items-center justify-center border-b border-border px-4", "style" to _nS(titleBarStyle.value)), _uA(
                                    _cE("view", _uM("class" to "flex flex-1 items-center justify-center"), _uA(
                                        renderSlot(_ctx.`$slots`, "title", _uO(), fun(): UTSArray<Any> {
                                            return _uA(
                                                _cE("text", _uM("class" to "text-2 font-semibold text-primary"), _tD(_ctx.title), 1)
                                            )
                                        })
                                    )),
                                    if (isTrue(_ctx.showClose)) {
                                        _cE("view", _uM("key" to 0, "class" to "absolute right-2 flex h-14 w-14 items-center justify-center rounded-full transition-colors duration-300 active:bg-black/5", "style" to _nS(closeStyle.value), "onClick" to handleClose), _uA(
                                            _cE("text", _uM("class" to "text-5 leading-none text-tertiary"), "✕")
                                        ), 4)
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ), 4)
                            } else {
                                _cC("v-if", true)
                            },
                            _cE("view", _uM("class" to "flex-1 overflow-y-auto p-2"), _uA(
                                renderSlot(_ctx.`$slots`, "default")
                            ))
                        ), 6)
                    ), 2)
                } else {
                    _cC("v-if", true)
                }
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("close" to null, "open" to null, "update:modelValue" to null)
        var props = _nP(_uM("height" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to "50%"), "width" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to "100%"), "position" to _uM("type" to "String", "required" to false, "default" to "bottom"), "showTitle" to _uM("type" to "Boolean", "required" to false, "default" to true), "title" to _uM("type" to "String", "required" to false, "default" to ""), "showClose" to _uM("type" to "Boolean", "required" to false, "default" to true), "safeAreaInsetTop" to _uM("type" to "Boolean", "required" to false, "default" to true), "round" to _uM("type" to "Boolean", "required" to false, "default" to true), "borderRadius" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to 32), "closeOnClickOverlay" to _uM("type" to "Boolean", "required" to false, "default" to true), "zIndex" to _uM("type" to "Number", "required" to false, "default" to 999), "background" to _uM("type" to "String", "required" to false, "default" to "var(--tui-surface)"), "modelValue" to _uM("default" to false)))
        var propsNeedCastKeys = _uA(
            "height",
            "width",
            "position",
            "showTitle",
            "title",
            "showClose",
            "safeAreaInsetTop",
            "round",
            "borderRadius",
            "closeOnClickOverlay",
            "zIndex",
            "background",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
