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
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
open class GenUniModulesTangUiXComponentsTImageIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var src: String by `$props`
    open var mode: String by `$props`
    open var width: Any by `$props`
    open var height: Any by `$props`
    open var shape: String by `$props`
    open var radius: Any by `$props`
    open var showLoading: Boolean by `$props`
    open var showError: Boolean by `$props`
    open var lazyLoad: Boolean by `$props`
    open var showText: Boolean by `$props`
    open var loadingText: String by `$props`
    open var errorText: String by `$props`
    open var errorImage: String by `$props`
    open var previewable: Boolean by `$props`
    open var previewList: UTSArray<String> by `$props`
    open var previewIndex: Number by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTImageIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTImageIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val loading = ref(true)
            val loadError = ref(false)
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val hasErrorImage = computed(fun(): Boolean {
                return hasTextValue(props.errorImage)
            }
            )
            val containerStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                if (UTSAndroid.`typeof`(props.width) === "number") {
                    styles.push("width: " + props.width as Number + "px")
                } else {
                    styles.push("width: " + props.width as String)
                }
                if (UTSAndroid.`typeof`(props.height) === "number") {
                    styles.push("height: " + props.height as Number + "px")
                } else {
                    styles.push("height: " + props.height as String)
                }
                if (props.shape === "round") {
                    styles.push("border-radius: 8px")
                } else if (props.shape === "circle") {
                    styles.push("border-radius: 50%")
                } else if (props.radius != 0 && props.radius != "0" && props.radius != "") {
                    if (UTSAndroid.`typeof`(props.radius) === "number") {
                        styles.push("border-radius: " + props.radius as Number + "px")
                    } else {
                        styles.push("border-radius: " + props.radius as String)
                    }
                }
                if (hasTextValue(props.customStyle)) {
                    styles.push(props.customStyle)
                }
                return styles.join("; ")
            }
            )
            val imageClasses = computed(fun(): String {
                val classes = _uA(
                    "t-image__img"
                ) as UTSArray<String>
                if (hasTextValue(props.customClass)) {
                    classes.push(props.customClass)
                }
                return classes.join(" ")
            }
            )
            val handleLoad = fun(event: Any): Unit {
                loading.value = false
                loadError.value = false
                emit("load", event)
            }
            val handleError = fun(event: Any): Unit {
                loading.value = false
                loadError.value = true
                emit("error", event)
            }
            val handlePreview = fun(): Unit {
                emit("preview")
                val urls = if (props.previewList.length > 0) {
                    props.previewList
                } else {
                    _uA(
                        props.src
                    )
                }
                val current = if (props.previewList.length > 0) {
                    props.previewIndex
                } else {
                    0
                }
                uni_previewImage(PreviewImageOptions(urls = urls, current = current))
            }
            val handleClick = fun(): Unit {
                emit("click")
                if (props.previewable) {
                    handlePreview()
                }
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(_uA(
                    "t-image relative inline-block overflow-hidden bg-gray-100",
                    _uM("cursor-pointer" to _ctx.previewable)
                )), "style" to _nS(containerStyle.value), "onClick" to handleClick), _uA(
                    if (isTrue(!loadError.value)) {
                        _cE("image", _uM("key" to 0, "src" to _ctx.src, "mode" to _ctx.mode, "lazy-load" to _ctx.lazyLoad, "class" to _nC("" + imageClasses.value + " h-full w-full block"), "onLoad" to handleLoad, "onError" to handleError), null, 42, _uA(
                            "src",
                            "mode",
                            "lazy-load"
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    if (isTrue(loading.value && _ctx.showLoading)) {
                        _cE("view", _uM("key" to 1, "class" to "t-image__loading absolute left-0 top-0 flex h-full w-full flex-col items-center justify-center bg-gray-100"), _uA(
                            _cE("view", _uM("class" to "t-image__loading-icon h-8 w-8 animate-spin rounded-full border-2 border-solid border-gray-200 border-t-blue-500")),
                            if (isTrue(_ctx.showText)) {
                                _cE("text", _uM("key" to 0, "class" to "t-image__loading-text mt-2 text-xs text-gray-400"), _tD(_ctx.loadingText), 1)
                            } else {
                                _cC("v-if", true)
                            }
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    if (isTrue(loadError.value && _ctx.showError)) {
                        _cE("view", _uM("key" to 2, "class" to "t-image__error absolute left-0 top-0 flex h-full w-full flex-col items-center justify-center bg-gray-100"), _uA(
                            if (isTrue(hasErrorImage.value)) {
                                _cE("image", _uM("key" to 0, "src" to _ctx.errorImage, "mode" to _ctx.mode, "class" to "h-full w-full block"), null, 8, _uA(
                                    "src",
                                    "mode"
                                ))
                            } else {
                                _cE("text", _uM("key" to 1, "class" to "t-image__error-icon text-3xl text-gray-300"), "✕")
                            },
                            if (isTrue(_ctx.showText)) {
                                _cE("text", _uM("key" to 2, "class" to "t-image__error-text mt-2 text-xs text-gray-400"), _tD(_ctx.errorText), 1)
                            } else {
                                _cC("v-if", true)
                            }
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    renderSlot(_ctx.`$slots`, "default")
                ), 6)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("load" to null, "error" to null, "click" to null, "preview" to null)
        var props = _nP(_uM("src" to _uM("type" to "String", "required" to true), "mode" to _uM("type" to "String", "required" to false, "default" to "aspectFill"), "width" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to "100%"), "height" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to "200rpx"), "shape" to _uM("type" to "String", "required" to false, "default" to "square"), "radius" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to "0"), "showLoading" to _uM("type" to "Boolean", "required" to false, "default" to true), "showError" to _uM("type" to "Boolean", "required" to false, "default" to true), "lazyLoad" to _uM("type" to "Boolean", "required" to false, "default" to false), "showText" to _uM("type" to "Boolean", "required" to false, "default" to false), "loadingText" to _uM("type" to "String", "required" to false, "default" to "加载中..."), "errorText" to _uM("type" to "String", "required" to false, "default" to "加载失败"), "errorImage" to _uM("type" to "String", "required" to false, "default" to ""), "previewable" to _uM("type" to "Boolean", "required" to false, "default" to false), "previewList" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<String> {
            return _uA<String>()
        }
        ), "previewIndex" to _uM("type" to "Number", "required" to false, "default" to 0), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to "")))
        var propsNeedCastKeys = _uA(
            "mode",
            "width",
            "height",
            "shape",
            "radius",
            "showLoading",
            "showError",
            "lazyLoad",
            "showText",
            "loadingText",
            "errorText",
            "errorImage",
            "previewable",
            "previewList",
            "previewIndex",
            "customClass",
            "customStyle"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
