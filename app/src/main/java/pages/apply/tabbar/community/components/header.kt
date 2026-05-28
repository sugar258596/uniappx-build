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
open class GenPagesApplyTabbarCommunityComponentsHeader : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var activeTab: String by `$props`
    open var keyword: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarCommunityComponentsHeader) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarCommunityComponentsHeader
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val tabItems = _uA(
                TabItem(key = "hot", label = "热门推荐", badge = null),
                TabItem(key = "follow", label = "我的关注", badge = null)
            ) as UTSArray<TabItem>
            val localKeyword = computed<String>(_uO("get" to fun(): String {
                return props.keyword
            }
            , "set" to fun(value: String){
                emit("update:keyword", value)
            }
            ))
            val localActiveTab = computed<String>(_uO("get" to fun(): String {
                return props.activeTab
            }
            , "set" to fun(value: String){
                emit("update:activeTab", value as TabKey)
            }
            ))
            val handleSearch = fun(value: String){
                emit("search", value)
            }
            val handleTabChange = fun(key: String){
                emit("tabChange", key as TabKey)
                emit("update:activeTab", key as TabKey)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "community-header bg-white"), _uA(
                    _cE("view", _uM("class" to "search-bar px-2 py-2"), _uA(
                        _cE("view", _uM("class" to "flex flex-row flex-1 rounded-full"), _uA(
                            _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to localKeyword.value, "onUpdate:modelValue" to fun(`$event`: String){
                                localKeyword.value = `$event`
                            }
                            , "onSearch" to handleSearch), null, 8, _uA(
                                "modelValue",
                                "onUpdate:modelValue"
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "tabs-bar border-b border-gray-100"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTabsIndexClass), _uM("modelValue" to localActiveTab.value, "onUpdate:modelValue" to fun(`$event`: String){
                            localActiveTab.value = `$event`
                        }
                        , "items" to tabItems, "active-color" to "#10b981", "inactive-color" to "#6b7280", "centered" to "", "animated" to true, "onChange" to handleTabChange), null, 8, _uA(
                            "modelValue",
                            "onUpdate:modelValue"
                        ))
                    ))
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
                return _uM("header-title" to _uM(".community-header " to _uM("height" to "88rpx")), "nav-label" to _uM(".community-header .tabs-bar .nav-tabs " to _uM("paddingBottom" to "16rpx", "fontSize" to "30rpx")), "nav-item" to _uM(".community-header .tabs-bar .nav-tabs " to _uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "content::after" to "\"\"", "height::after" to "4rpx", "width::after" to 0, "backgroundColor::after" to "#10b981", "transitionProperty::after" to "width", "transitionDuration::after" to "0.3s", "transitionTimingFunction::after" to "ease"), ".community-header .tabs-bar .nav-tabs .active::after" to _uM("width" to "40%")), "@TRANSITION" to _uM("nav-item" to _uM("property::after" to "width", "duration::after" to "0.3s", "timingFunction::after" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("search" to null, "tabChange" to null, "update:keyword" to null, "update:activeTab" to null)
        var props = _nP(_uM("activeTab" to _uM("type" to "String", "default" to "hot"), "keyword" to _uM("type" to "String", "default" to "")))
        var propsNeedCastKeys = _uA(
            "activeTab",
            "keyword"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
