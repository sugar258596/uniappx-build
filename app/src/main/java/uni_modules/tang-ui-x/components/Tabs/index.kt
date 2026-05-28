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
open class GenUniModulesTangUiXComponentsTabsIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var items: UTSArray<TabItem> by `$props`
    open var activeKey: Any? by `$props`
    open var defaultActiveKey: Any? by `$props`
    open var type: String by `$props`
    open var tabPosition: String by `$props`
    open var centered: Boolean by `$props`
    open var scrollable: Boolean by `$props`
    open var activeColor: String by `$props`
    open var inactiveColor: String by `$props`
    open var size: String by `$props`
    open var animated: Boolean by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTabsIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTabsIndex
            val _cache = __ins.renderCache
            val model = useModel<Any>(__ins.props, "modelValue") as Ref<TabsModelValue>
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val hasItemIcon = fun(item: TabItem): Boolean {
                return hasTextValue(item.icon)
            }
            val hasItemBadge = fun(item: TabItem): Boolean {
                return item.badge != null && item.badge !== ""
            }
            val getDefaultKey = fun(): Any {
                val defaultActiveKey = props.defaultActiveKey
                if (defaultActiveKey != null) {
                    return defaultActiveKey
                }
                if (props.items.length > 0) {
                    run {
                        var i: Number = 0
                        while(i < props.items.length){
                            val item = props.items[i]
                            if (item.disabled != true) {
                                return item.key
                            }
                            i++
                        }
                    }
                    return props.items[0].key
                }
                return ""
            }
            onMounted(fun(){
                if (model.value == null || model.value === "") {
                    model.value = getDefaultKey()
                }
            }
            )
            val isItemActive = fun(item: TabItem): Boolean {
                val currentKey = model.value
                if (currentKey == null || currentKey === "") {
                    return false
                }
                if (item.key == null || item.key === "") {
                    return false
                }
                return item.key == currentKey
            }
            val navListRef = ref<UniElement?>(null)
            val hexToRgb = fun(reassignedHex: String): String {
                var hex = reassignedHex
                hex = hex.replace("#", "")
                if (hex.length == 3) {
                    hex = hex.split("").map(fun(char): String {
                        return char + char
                    }
                    ).join("")
                }
                val r = parseInt(hex.substring(0, 2), 16)
                val g = parseInt(hex.substring(2, 4), 16)
                val b = parseInt(hex.substring(4, 6), 16)
                return "" + r + ", " + g + ", " + b
            }
            val activeColorRgb = computed(fun(): String {
                if (props.activeColor.startsWith("#")) {
                    return hexToRgb(props.activeColor)
                }
                return "0, 187, 167"
            }
            )
            val containerClass = computed(fun(): String {
                return "w-full overflow-hidden bg-white"
            }
            )
            val getNavListClass = fun(isBottom: Boolean): String {
                val classes = _uA(
                    "relative",
                    "flex",
                    "flex-row",
                    "items-center",
                    "overflow-x-auto",
                    "overflow-y-hidden",
                    "whitespace-nowrap"
                ) as UTSArray<String>
                if (props.type == "card") {
                    classes.push("rounded-lg", "bg-action-divider", "px-2", "py-2")
                } else {
                    classes.push("py-2")
                }
                if (props.centered) {
                    classes.push("justify-center")
                }
                if (isBottom) {
                    classes.push("border-t", "border-divider")
                }
                return classes.join(" ")
            }
            val navLabelClass = computed(fun(): String {
                return "transition-all duration-300"
            }
            )
            val getNavItemClass = fun(item: TabItem): String {
                val classes = _uA(
                    "relative",
                    "inline-flex",
                    "items-center",
                    "justify-center",
                    "origin-center",
                    "transition-all",
                    "duration-300"
                ) as UTSArray<String>
                if (props.type === "card") {
                    classes.push("mx-1", "rounded", "border", "border-solid", "border-border-soft", "bg-white")
                }
                if (isItemActive(item) && props.type == "basic") {
                    classes.push("scale-105")
                }
                if (item.disabled == true) {
                    classes.push("cursor-not-allowed", "opacity-50")
                } else {
                    classes.push("cursor-pointer")
                }
                return classes.join(" ")
            }
            val getNavItemStyle = fun(item: TabItem): String {
                val styles: UTSArray<String> = _uA()
                val active = isItemActive(item)
                if (props.size == "small") {
                    styles.push("min-height: 64rpx")
                    styles.push(if (props.type == "card") {
                        "padding: 10rpx 20rpx"
                    } else {
                        "padding: 12rpx 22rpx"
                    })
                } else if (props.size == "large") {
                    styles.push("min-height: 88rpx")
                    styles.push(if (props.type == "card") {
                        "padding: 18rpx 32rpx"
                    } else {
                        "padding: 20rpx 36rpx"
                    })
                } else {
                    styles.push("min-height: 76rpx")
                    styles.push(if (props.type == "card") {
                        "padding: 14rpx 28rpx"
                    } else {
                        "padding: 16rpx 30rpx"
                    }
                    )
                }
                if (props.type == "card") {
                    styles.push("border-width: 1px")
                    styles.push("border-style: solid")
                    styles.push(if (active) {
                        "border-color: " + props.activeColor
                    } else {
                        "border-color: #ebeef5"
                    }
                    )
                }
                if (props.type == "card" && active) {
                    styles.push("background-color: rgba(" + activeColorRgb.value + ", 0.1)")
                }
                return styles.join("; ")
            }
            val handleClick = fun(item: TabItem, _index: Number, _e: UniPointerEvent){
                if (item.disabled == true) {
                    return
                }
                model.value = item.key
                emit("update:activeKey", item.key)
                emit("change", item.key)
                emit("tabClick", item.key, item)
                if (props.scrollable) {
                    nextTick(fun(){})
                }
            }
            val getNavLabelStyle = fun(item: TabItem): String {
                val styles: UTSArray<String> = _uA()
                val active = isItemActive(item)
                styles.push("color: " + (if (active) {
                    props.activeColor
                } else {
                    props.inactiveColor
                }
                ))
                styles.push(if (active) {
                    "font-weight: 600"
                } else {
                    "font-weight: 400"
                }
                )
                if (props.size === "small") {
                    styles.push("font-size: 24rpx")
                    styles.push("line-height: 32rpx")
                } else if (props.size === "large") {
                    styles.push("font-size: 32rpx")
                    styles.push("line-height: 44rpx")
                } else {
                    styles.push("font-size: 28rpx")
                    styles.push("line-height: 38rpx")
                }
                return styles.join("; ")
            }
            val getActiveLineStyle = fun(item: TabItem): String {
                val opacity = if (isItemActive(item)) {
                    "1"
                } else {
                    "0"
                }
                return "position: absolute; left: 24rpx; right: 24rpx; bottom: 0; height: 4rpx; border-radius: 999rpx; background-color: " + props.activeColor + "; opacity: " + opacity
            }
            val badgeClass = computed(fun(): String {
                return "absolute flex flex-row items-center justify-center rounded-pill bg-danger px-1 text-center text-white"
            }
            )
            val badgeStyle = computed(fun(): String {
                val styles = _uA(
                    "right: 8rpx",
                    "min-width: 28rpx",
                    "height: 28rpx",
                    "border-radius: 14rpx",
                    "font-size: 18rpx",
                    "line-height: 28rpx",
                    "padding-left: 6rpx",
                    "padding-right: 6rpx"
                ) as UTSArray<String>
                if (props.size === "small") {
                    styles.push("top: 4rpx")
                } else if (props.size === "large") {
                    styles.push("top: 8rpx")
                } else {
                    styles.push("top: 6rpx")
                }
                return styles.join("; ")
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(containerClass.value)), _uA(
                    if (_ctx.tabPosition === "top") {
                        _cE("scroll-view", _uM("key" to 0, "ref_key" to "navListRef", "ref" to navListRef, "class" to "w-full whitespace-nowrap", "scroll-x" to true, "show-scrollbar" to false), _uA(
                            _cE("view", _uM("class" to _nC(getNavListClass(false))), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, index, __index, _cached): Any {
                                    return _cE("view", _uM("key" to item.key, "class" to _nC(getNavItemClass(item)), "style" to _nS(getNavItemStyle(item)), "ref_for" to true, "ref" to ("navItem" + index), "onClick" to fun(`$event`: Any){
                                        handleClick(item, index, `$event` as UniPointerEvent)
                                    }), _uA(
                                        if (isTrue(hasItemIcon(item))) {
                                            _cE("image", _uM("key" to 0, "class" to "mr-sm h-9 w-9 transition-all duration-300", "src" to item.icon, "mode" to "aspectFit"), null, 8, _uA(
                                                "src"
                                            ))
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        _cE("text", _uM("class" to _nC(navLabelClass.value), "style" to _nS(getNavLabelStyle(item))), _tD(item.label), 7),
                                        if (isTrue(hasItemBadge(item))) {
                                            _cE("view", _uM("key" to 1, "class" to _nC(badgeClass.value), "style" to _nS(badgeStyle.value)), _tD(item.badge), 7)
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        if (_ctx.type === "line") {
                                            _cE("view", _uM("key" to 2, "style" to _nS(getActiveLineStyle(item))), null, 4)
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    ), 14, _uA(
                                        "onClick"
                                    ))
                                }), 128)
                            ), 2)
                        ), 512)
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    _cE("view", _uM("class" to "bg-white"), _uA(
                        renderSlot(_ctx.`$slots`, "default", _uM("activeKey" to model.value))
                    )),
                    if (_ctx.tabPosition === "bottom") {
                        _cE("scroll-view", _uM("key" to 1, "ref_key" to "navListRef", "ref" to navListRef, "class" to "w-full whitespace-nowrap", "scroll-x" to true, "show-scrollbar" to false), _uA(
                            _cE("view", _uM("class" to _nC(getNavListClass(true))), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, index, __index, _cached): Any {
                                    return _cE("view", _uM("key" to item.key, "class" to _nC(getNavItemClass(item)), "style" to _nS(getNavItemStyle(item)), "onClick" to fun(`$event`: Any){
                                        handleClick(item, index, `$event` as UniPointerEvent)
                                    }), _uA(
                                        if (isTrue(hasItemIcon(item))) {
                                            _cE("image", _uM("key" to 0, "class" to "mr-sm h-9 w-9 transition-all duration-300", "src" to item.icon, "mode" to "aspectFit"), null, 8, _uA(
                                                "src"
                                            ))
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        _cE("text", _uM("class" to _nC(navLabelClass.value), "style" to _nS(getNavLabelStyle(item))), _tD(item.label), 7),
                                        if (isTrue(hasItemBadge(item))) {
                                            _cE("view", _uM("key" to 1, "class" to _nC(badgeClass.value), "style" to _nS(badgeStyle.value)), _tD(item.badge), 7)
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        if (_ctx.type === "line") {
                                            _cE("view", _uM("key" to 2, "style" to _nS(getActiveLineStyle(item))), null, 4)
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    ), 14, _uA(
                                        "onClick"
                                    ))
                                }), 128)
                            ), 2)
                        ), 512)
                    } else {
                        _cC("v-if", true)
                    }
                ), 2)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("change" to null, "update:activeKey" to null, "tabClick" to null, "update:modelValue" to null)
        var props = _nP(_uM("items" to _uM("type" to "Array", "required" to true, "default" to fun(): UTSArray<TabItem> {
            return _uA<TabItem>()
        }
        ), "activeKey" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false), "defaultActiveKey" to _uM("type" to _uA(
            "String",
            "Number"
        ), "required" to false), "type" to _uM("type" to "String", "required" to false, "default" to "basic"), "tabPosition" to _uM("type" to "String", "required" to false, "default" to "top"), "centered" to _uM("type" to "Boolean", "required" to false, "default" to false), "scrollable" to _uM("type" to "Boolean", "required" to false, "default" to true), "activeColor" to _uM("type" to "String", "required" to false, "default" to "#00bba7"), "inactiveColor" to _uM("type" to "String", "required" to false, "default" to "#666666"), "size" to _uM("type" to "String", "required" to false, "default" to "medium"), "animated" to _uM("type" to "Boolean", "required" to false, "default" to true), "modelValue" to _uM("default" to null)))
        var propsNeedCastKeys = _uA(
            "items",
            "type",
            "tabPosition",
            "centered",
            "scrollable",
            "activeColor",
            "inactiveColor",
            "size",
            "animated",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
