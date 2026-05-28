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
open class GenUniModulesTangUiXComponentsTIconIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var name: String by `$props`
    open var size: Any by `$props`
    open var color: String by `$props`
    open var bold: Boolean by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTIconIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTIconIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val iconSize = computed(fun(): String {
                if (UTSAndroid.`typeof`(props.size) === "number") {
                    return (props.size as Number) + "rpx"
                }
                return props.size as String
            }
            )
            val iconStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                styles.push("font-size: " + iconSize.value)
                styles.push("color: " + props.color)
                if (props.bold) {
                    styles.push("font-weight: bold")
                }
                if (props.customStyle !== "") {
                    styles.push(props.customStyle)
                }
                return styles.join("; ")
            }
            )
            val iconClass = computed(fun(): String {
                val classes = _uA(
                    "inline-block",
                    "leading-none",
                    "iconfont"
                ) as UTSArray<String>
                if (props.name.length > 0) {
                    if (props.name.indexOf("icon-") == 0) {
                        classes.push(props.name)
                    } else {
                        classes.push("icon-" + props.name)
                    }
                }
                if (props.customClass !== "") {
                    classes.push(props.customClass)
                }
                return classes.join(" ")
            }
            )
            val handleClick = fun(){
                emit("click")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex justify-center items-center"), _uA(
                    _cE("text", _uM("class" to _nC(unref(iconClass)), "style" to _nS(unref(iconStyle)), "onClick" to handleClick), null, 6)
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
                return _uM("iconfont" to _pS(_uM("!fontFamily" to "iconfont", "fontSize" to 16, "fontStyle" to "normal", "WebkitFontSmoothing" to "antialiased", "MozOsxFontSmoothing" to "grayscale")), "icon-xiajiantou" to _pS(_uM("content:before" to "\"\\e656\"")), "icon-tianjia" to _pS(_uM("content:before" to "\"\\e62d\"")), "icon-shanchu" to _pS(_uM("content:before" to "\"\\e61a\"")), "icon-sousuo" to _pS(_uM("content:before" to "\"\\e602\"")), "t-icon" to _pS(_uM("lineHeight" to 1)), "@FONT-FACE" to _uM("0" to _uM("fontFamily" to "iconfont", "src" to "url(\"/uni_modules/tang-ui-x/static/font/iconfont.woff2?t=1772268917050\") format(\"woff2\"),\n    url(\"/uni_modules/tang-ui-x/static/font/iconfont.woff?t=1772268917050\") format(\"woff\"),\n    url(\"/uni_modules/tang-ui-x/static/font/iconfont.ttf?t=1772268917050\") format(\"truetype\")")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null)
        var props = _nP(_uM("name" to _uM("type" to "String", "required" to true, "default" to ""), "size" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false, "default" to 48), "color" to _uM("type" to "String", "required" to false, "default" to "var(--tui-text-primary)"), "bold" to _uM("type" to "Boolean", "required" to false, "default" to false), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to "")))
        var propsNeedCastKeys = _uA(
            "name",
            "size",
            "color",
            "bold",
            "customClass",
            "customStyle"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
