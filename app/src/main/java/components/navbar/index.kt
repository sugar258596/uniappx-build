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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.switchTab as uni_switchTab
open class GenComponentsNavbarIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String by `$props`
    open var showBack: Boolean by `$props`
    open var backText: String by `$props`
    open var background: String by `$props`
    open var color: String by `$props`
    open var fixed: Boolean by `$props`
    open var bordered: Boolean by `$props`
    open var height: Number by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsNavbarIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsNavbarIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val roleStore = useRoleStore()
            val safeArea = useSafeArea()
            val navbarHeight = computed(fun(): Number {
                return safeArea.getNavbarContentHeight(props.height)
            }
            )
            val navbarStyle = computed(fun(): NavbarStyle {
                val info = safeArea.getSafeAreaInfo()
                val style = NavbarStyle(height = navbarHeight.value + "px", backgroundColor = props.background, color = props.color, borderBottomWidth = if (props.bordered) {
                    "1rpx"
                } else {
                    "0"
                }
                )
                val menuButton = info.menuButton
                if (info.isMP && menuButton != null) {
                    style.paddingRight = menuButton.width * 1.1 + "px"
                }
                return style
            }
            )
            val navbarLeftStyle = computed(fun(): NavbarBlockStyle {
                val info = safeArea.getSafeAreaInfo()
                val style = NavbarBlockStyle(height = navbarHeight.value + "px")
                val menuButton = info.menuButton
                if (info.isMP && menuButton != null) {
                    style.maxWidth = menuButton.left + "px"
                }
                return style
            }
            )
            val navbarCenterStyle = computed(fun(): UTSJSONObject {
                return _uO("height" to (navbarHeight.value + "px"), "lineHeight" to (navbarHeight.value + "px"))
            }
            )
            val navbarRightStyle = computed(fun(): UTSJSONObject {
                return _uO("height" to (navbarHeight.value + "px"))
            }
            )
            val statusBarHeight = computed(fun(): Number {
                val info = safeArea.getSafeAreaInfo()
                return info.statusBarHeight
            }
            )
            val getHomePathByRole = fun(): String {
                if (roleStore.roleCheck === true) {
                    return "/pages/hire/tabbar/home/index"
                } else {
                    return "/pages/apply/tabbar/home/index"
                }
            }
            val handleBack = fun(){
                emit("back")
                val pages = getCurrentPages()
                if (pages.length > 1) {
                    uni_navigateBack(NavigateBackOptions(delta = 1))
                } else {
                    val homePath = getHomePathByRole()
                    uni_switchTab(SwitchTabOptions(url = homePath))
                }
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "navbar-wrapper"), _uA(
                    _cE("view", _uM("class" to "status-bar", "style" to _nS(_uM("height" to (statusBarHeight.value + "px"), "backgroundColor" to _ctx.background))), null, 4),
                    renderSlot(_ctx.`$slots`, "default", _uO(), fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "navbar", "style" to _nS(navbarStyle.value)), _uA(
                                _cE("view", _uM("class" to "navbar-left", "style" to _nS(navbarLeftStyle.value)), _uA(
                                    renderSlot(_ctx.`$slots`, "left", _uO(), fun(): UTSArray<Any> {
                                        return _uA(
                                            if (isTrue(_ctx.showBack)) {
                                                _cE("view", _uM("key" to 0, "class" to "nav-back", "onClick" to handleBack), _uA(
                                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-arrow-left", "color" to _ctx.color, "size" to 30), null, 8, _uA(
                                                        "color"
                                                    )),
                                                    if (isTrue(_ctx.backText)) {
                                                        _cE("text", _uM("key" to 0, "class" to "nav-back-text"), _tD(_ctx.backText), 1)
                                                    } else {
                                                        _cC("v-if", true)
                                                    }
                                                ))
                                            } else {
                                                _cC("v-if", true)
                                            }
                                        )
                                    }
                                    )
                                ), 4),
                                _cE("view", _uM("class" to "navbar-center", "style" to _nS(navbarCenterStyle.value)), _uA(
                                    renderSlot(_ctx.`$slots`, "center", _uO(), fun(): UTSArray<Any> {
                                        return _uA(
                                            _cE("text", _uM("class" to "navbar-title"), _tD(_ctx.title), 1)
                                        )
                                    }
                                    )
                                ), 4),
                                _cE("view", _uM("class" to "navbar-right", "style" to _nS(navbarRightStyle.value)), _uA(
                                    renderSlot(_ctx.`$slots`, "right")
                                ), 4)
                            ), 4)
                        )
                    }
                    )
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
                return _uM("navbar-wrapper" to _pS(_uM("position" to "relative", "width" to "100%", "overflow" to "visible")), "status-bar" to _pS(_uM("width" to "100%")), "navbar" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 0, "paddingRight" to 18, "paddingBottom" to 0, "paddingLeft" to 18, "borderBottomStyle" to "solid", "borderBottomColor" to "#c8c7cc", "position" to "relative", "overflow" to "visible")), "navbar-content" to _pS(_uM("display" to "flex", "flexDirection" to "column", "width" to "100%")), "navbar-left" to _pS(_uM("display" to "flex", "justifyContent" to "center", "alignItems" to "center", "flexShrink" to 0, "zIndex" to 2, "overflow" to "visible")), "navbar-center" to _pS(_uM("position" to "absolute", "left" to "50%", "transform" to "translateX(-50%)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "zIndex" to 1)), "navbar-title" to _pS(_uM("fontSize" to 18, "fontWeight" to "600", "textAlign" to "center")), "navbar-right" to _pS(_uM("display" to "flex", "alignItems" to "center", "flexShrink" to 0, "zIndex" to 2)), "nav-back" to _pS(_uM("display" to "flex", "justifyContent" to "center", "alignItems" to "center", "borderTopLeftRadius" to 18, "borderTopRightRadius" to 18, "borderBottomRightRadius" to 18, "borderBottomLeftRadius" to 18, "transitionProperty" to "backgroundColor", "transitionDuration" to "0.3s", "backgroundColor:active" to "rgba(0,0,0,0.05)")), "nav-back-text" to _pS(_uM("fontSize" to 14)), "menu-placeholder" to _pS(_uM("flexShrink" to 0, "visibility" to "hidden")), "@TRANSITION" to _uM("nav-back" to _uM("property" to "backgroundColor", "duration" to "0.3s")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("back" to null, "clickLeft" to null, "clickRight" to null)
        var props = _nP(_uM("title" to _uM("type" to "String", "required" to false, "default" to ""), "showBack" to _uM("type" to "Boolean", "required" to false, "default" to true), "backText" to _uM("type" to "String", "required" to false, "default" to ""), "background" to _uM("type" to "String", "required" to false, "default" to "transparent"), "color" to _uM("type" to "String", "required" to false, "default" to "#000000"), "fixed" to _uM("type" to "Boolean", "required" to false, "default" to true), "bordered" to _uM("type" to "Boolean", "required" to false, "default" to false), "height" to _uM("type" to "Number", "required" to false, "default" to 0)))
        var propsNeedCastKeys = _uA(
            "title",
            "showBack",
            "backText",
            "background",
            "color",
            "fixed",
            "bordered",
            "height"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
