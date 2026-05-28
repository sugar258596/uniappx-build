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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch
import io.dcloud.uniapp.extapi.switchTab as uni_switchTab
open class GenComponentsCustomTabbarIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var activeIndex: Number by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsCustomTabbarIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsCustomTabbarIndex
            val _cache = __ins.renderCache
            val props = __props
            val roleStore = useRoleStore()
            val messageStore = useMessageStore()
            val applicantTabs = _uA(
                TabBarItem(pagePath = "/pages/apply/tabbar/home/index", text = "首页", iconPath = "/static/tabbar/home.png", selectedIconPath = "/static/tabbar/home-active.png"),
                TabBarItem(pagePath = "/pages/apply/tabbar/community/index", text = "社区", iconPath = "/static/tabbar/community.png", selectedIconPath = "/static/tabbar/community-active.png"),
                TabBarItem(pagePath = "/pages/apply/tabbar/make/index", text = "赚钱", iconPath = "/static/tabbar/earn.png", selectedIconPath = "/static/tabbar/earn-active.png", isRaised = true),
                TabBarItem(pagePath = "/pages/common/message/index", text = "消息", iconPath = "/static/tabbar/message.png", selectedIconPath = "/static/tabbar/message-active.png"),
                TabBarItem(pagePath = "/pages/apply/tabbar/my/index", text = "我的", iconPath = "/static/tabbar/profile.png", selectedIconPath = "/static/tabbar/profile-active.png")
            ) as UTSArray<TabBarItem>
            val recruiterTabs = _uA(
                TabBarItem(pagePath = "/pages/hire/tabbar/home/index", text = "首页", iconPath = "/static/tabbar/home.png", selectedIconPath = "/static/tabbar/home-active.png"),
                TabBarItem(pagePath = "/pages/hire/tabbar/position/index", text = "职位", iconPath = "/static/tabbar/jobs.png", selectedIconPath = "/static/tabbar/jobs-active.png"),
                TabBarItem(pagePath = "/pages/hire/position/publish/index", text = "发布职位", iconPath = "/static/tabbar/publish.png", selectedIconPath = "/static/tabbar/publish-active.png", isRaised = true),
                TabBarItem(pagePath = "/pages/common/message/index", text = "消息", iconPath = "/static/tabbar/message.png", selectedIconPath = "/static/tabbar/message-active.png"),
                TabBarItem(pagePath = "/pages/hire/tabbar/my/index", text = "我的", iconPath = "/static/tabbar/profile.png", selectedIconPath = "/static/tabbar/profile-active.png")
            ) as UTSArray<TabBarItem>
            val currentTabs = computed(fun(): UTSArray<TabBarItem> {
                return if (roleStore.roleCheck === true) {
                    recruiterTabs
                } else {
                    applicantTabs
                }
            }
            )
            val unreadCount = computed(fun(): Number {
                return messageStore.unreadCount as Number
            }
            )
            val hasRaisedTab = computed(fun(): Boolean {
                return currentTabs.value.some(fun(tab: TabBarItem): Boolean {
                    return tab.isRaised === true
                }
                )
            }
            )
            val tabBarPages = _uA(
                "/pages/apply/tabbar/home/index",
                "/pages/apply/tabbar/community/index",
                "/pages/apply/tabbar/make/index",
                "/pages/common/message/index",
                "/pages/apply/tabbar/my/index",
                "/pages/hire/tabbar/home/index",
                "/pages/hire/tabbar/position/index",
                "/pages/hire/tabbar/my/index"
            ) as UTSArray<String>
            val isTabBarPage = fun(pagePath: String): Boolean {
                return tabBarPages.includes(pagePath)
            }
            val switchTab = fun(index: Number){
                if (index === props.activeIndex) {
                    return
                }
                val tab = currentTabs.value[index]
                if (!isTabBarPage(tab.pagePath)) {
                    uni_navigateTo(NavigateToOptions(url = tab.pagePath, fail = fun(err){
                        console.error("打开页面失败:", err)
                    }
                    ))
                    return
                }
                uni_switchTab(SwitchTabOptions(url = tab.pagePath, fail = fun(_){
                    uni_reLaunch(ReLaunchOptions(url = tab.pagePath))
                }
                ))
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "custom-tabbar bg-white border-t border-gray-200 overflow-visible"), _uA(
                    _cE("view", _uM("class" to _nC(_uA(
                        "overflow-visible",
                        _uA(
                            "flex flex-row justify-around h-full",
                            if (hasRaisedTab.value) {
                                "items-end pb-1 overflow-visible"
                            } else {
                                "items-center"
                            }
                        )
                    ))), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(currentTabs.value, fun(item, index, __index, _cached): Any {
                            return _cE("view", _uM("key" to index, "class" to "flex-1 flex flex-col items-center justify-center overflow-visible", "onClick" to fun(){
                                switchTab(index)
                            }
                            ), _uA(
                                if (isTrue(item.isRaised)) {
                                    _cE("view", _uM("key" to 0, "class" to "flex flex-col items-center justify-end h-full pb-1 w-full"), _uA(
                                        _cE("view", _uM("class" to "center-raised-btn"), _uA(
                                            _cE("image", _uM("src" to if (props.activeIndex === index) {
                                                item.selectedIconPath
                                            } else {
                                                item.iconPath
                                            }, "class" to "w-6 h-6", "mode" to "aspectFit"), null, 8, _uA(
                                                "src"
                                            ))
                                        )),
                                        _cE("text", _uM("class" to _nC(_uA(
                                            "text-xs mt-auto",
                                            if (props.activeIndex === index) {
                                                "text-teal-800 font-medium"
                                            } else {
                                                "text-gray-600"
                                            }
                                        ))), _tD(item.text), 3)
                                    ))
                                } else {
                                    _cE("view", _uM("key" to 1, "class" to "flex flex-col items-center justify-center py-1 w-full h-full overflow-visible"), _uA(
                                        _cE("view", _uM("class" to "relative overflow-visible"), _uA(
                                            _cE("image", _uM("src" to if (props.activeIndex === index) {
                                                item.selectedIconPath
                                            } else {
                                                item.iconPath
                                            }
                                            , "class" to "w-6 h-6 mb-1", "mode" to "aspectFit"), null, 8, _uA(
                                                "src"
                                            )),
                                            if (isTrue(item.text === "消息" && unreadCount.value > 0)) {
                                                _cE("view", _uM("key" to 0, "class" to "absolute -top-1 -right-1 bg-red-500 rounded-full min-w-2 h-4 flex items-center justify-center px-1 overflow-visible"), _uA(
                                                    _cE("text", _uM("class" to "text-white text-xs font-medium"), _tD(if (unreadCount.value > 99) {
                                                        "99+"
                                                    } else {
                                                        unreadCount.value
                                                    }), 1)
                                                ))
                                            } else {
                                                _cC("v-if", true)
                                            }
                                        )),
                                        _cE("text", _uM("class" to _nC(_uA(
                                            "text-xs",
                                            if (props.activeIndex === index) {
                                                "text-teal-800 font-medium"
                                            } else {
                                                "text-gray-600"
                                            }
                                        ))), _tD(item.text), 3)
                                    ))
                                }
                            ), 8, _uA(
                                "onClick"
                            ))
                        }
                        ), 128)
                    ), 2)
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
                return _uM("custom-tabbar" to _pS(_uM("position" to "fixed", "bottom" to 0, "left" to 0, "right" to 0, "height" to 50, "zIndex" to 9999, "boxShadow" to "0 -2px 8px rgba(0, 0, 0, 0.05)", "paddingBottom" to "env(safe-area-inset-bottom)")), "center-raised-btn" to _pS(_uM("position" to "fixed", "left" to "50%", "transform" to "translateX(-50%)", "zIndex" to 10000, "width" to 56, "height" to 56, "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "backgroundImage" to "linear-gradient(180deg, #4ade80 0%, #0d9488 100%)", "backgroundColor" to "rgba(0,0,0,0)", "boxShadow" to "0 4px 12px rgba(22, 163, 74, 0.4)")), "-top-2" to _pS(_uM("top" to -8)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM("activeIndex" to _uM("type" to "Number", "default" to 0)))
        var propsNeedCastKeys = _uA(
            "activeIndex"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
