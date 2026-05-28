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
open class GenUniModulesTangUiXComponentsTAvatarIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var src: String by `$props`
    open var errorImage: String by `$props`
    open var size: Any by `$props`
    open var shape: String by `$props`
    open var alt: String by `$props`
    open var bgColor: String by `$props`
    open var color: String by `$props`
    open var fit: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTAvatarIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTAvatarIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val hasLoadError = ref(false)
            val hasErrorImageLoadError = ref(false)
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val hasSrc = computed(fun(): Boolean {
                return hasTextValue(props.src)
            }
            )
            val hasErrorImage = computed(fun(): Boolean {
                return hasTextValue(props.errorImage)
            }
            )
            val hasAlt = computed(fun(): Boolean {
                return hasTextValue(props.alt)
            }
            )
            val displaySrc = computed(fun(): String {
                if (hasSrc.value && !hasLoadError.value) {
                    return props.src
                }
                if (hasErrorImage.value && !hasErrorImageLoadError.value) {
                    return props.errorImage
                }
                return ""
            }
            )
            val hasDisplaySrc = computed(fun(): Boolean {
                return hasTextValue(displaySrc.value)
            }
            )
            val avatarSize = computed(fun(): Number {
                val size = props.size
                if (UTSAndroid.`typeof`(size) === "number") {
                    return size as Number
                }
                if (size as String == "small") {
                    return 32
                }
                if (size as String == "large") {
                    return 56
                }
                return 40
            }
            )
            val avatarClass = computed(fun(): String {
                val classes = _uA(
                    "t-avatar flex items-center justify-center overflow-hidden relative"
                ) as UTSArray<String>
                if (props.shape == "square") {
                    classes.push("rounded")
                } else {
                    classes.push("rounded-full")
                }
                return classes.join(" ")
            }
            )
            val avatarStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                val size = avatarSize.value
                styles.push("width: " + size + "px")
                styles.push("height: " + size + "px")
                if (!hasDisplaySrc.value) {
                    styles.push("background-color: " + props.bgColor)
                }
                return styles.join("; ")
            }
            )
            val textStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                styles.push("color: " + props.color)
                styles.push("font-size: " + avatarSize.value / 2 + "px")
                return styles.join("; ")
            }
            )
            val handleClick = fun(): Unit {
                emit("click")
            }
            val handleError = fun(): Unit {
                hasLoadError.value = true
                emit("error")
            }
            val handleErrorImageError = fun(): Unit {
                hasErrorImageLoadError.value = true
            }
            val handleImageError = fun(): Unit {
                if (hasLoadError.value) {
                    handleErrorImageError()
                } else {
                    handleError()
                }
            }
            val displayText = computed(fun(): String {
                if (hasAlt.value) {
                    return props.alt.charAt(0).toUpperCase()
                }
                return ""
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(avatarClass.value), "style" to _nS(avatarStyle.value), "onClick" to handleClick), _uA(
                    if (isTrue(hasDisplaySrc.value)) {
                        _cE("image", _uM("key" to 0, "class" to "t-avatar__image h-full w-full", "src" to displaySrc.value, "mode" to _ctx.fit, "onError" to handleImageError), null, 40, _uA(
                            "src",
                            "mode"
                        ))
                    } else {
                        if (isTrue(hasAlt.value)) {
                            _cE("text", _uM("key" to 1, "class" to "t-avatar__text text-center font-medium", "style" to _nS(textStyle.value)), _tD(displayText.value), 5)
                        } else {
                            renderSlot(_ctx.`$slots`, "default", _uM("key" to 2))
                        }
                    }
                ), 6)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null, "error" to null)
        var props = _nP(_uM("src" to _uM("type" to "String", "required" to false, "default" to ""), "errorImage" to _uM("type" to "String", "required" to false, "default" to ""), "size" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to "medium"), "shape" to _uM("type" to "String", "required" to false, "default" to "circle"), "alt" to _uM("type" to "String", "required" to false, "default" to ""), "bgColor" to _uM("type" to "String", "required" to false, "default" to "var(--tui-border-strong)"), "color" to _uM("type" to "String", "required" to false, "default" to "var(--tui-text-inverse)"), "fit" to _uM("type" to "String", "required" to false, "default" to "cover")))
        var propsNeedCastKeys = _uA(
            "src",
            "errorImage",
            "size",
            "shape",
            "alt",
            "bgColor",
            "color",
            "fit"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
