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
open class GenPagesApplyTabbarMyComponentsOtherFeatures : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarMyComponentsOtherFeatures) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarMyComponentsOtherFeatures
            val _cache = __ins.renderCache
            val menuItems = _uA(
                FeatureMenuItem(icon = "icon-jiaohuan", label = "更换账号", path = "/pages/common/system/switch-account/index"),
                FeatureMenuItem(icon = "icon-fasong", label = "我的发布", path = "/pages/apply/community/my-posts"),
                FeatureMenuItem(icon = "icon-48", label = "充值中心", path = "/pages/common/recharge/index"),
                FeatureMenuItem(icon = "icon-yinsibaohu", label = "隐私保护", path = "/pages/apply/user/privacy"),
                FeatureMenuItem(icon = "icon-guanyu", label = "关于我们", path = "/pages/common/system/about/index"),
                FeatureMenuItem(icon = "icon-bangzhu", label = "帮助与反馈", path = "/pages/common/feedback/index"),
                FeatureMenuItem(icon = "icon-jubao", label = "举报中心", path = "/pages/apply/user/report"),
                FeatureMenuItem(icon = "icon-xitong", label = "系统设置", path = "/pages/common/system/settings/index")
            ) as UTSArray<FeatureMenuItem>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleMenuClick = fun(item: FeatureMenuItem){
                emit("menuClick", item)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "other-features"), _uA(
                    _cV(unref(GenComponentsCommonFeatureGridIndexClass), _uM("title" to "其他功能", "items" to menuItems, "columns" to 4, "onMenuClick" to handleMenuClick))
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
                return _uM("logout-btn" to _pS(_uM("!backgroundColor" to "#f3f4f6", "!color" to "#22c55e", "!borderTopWidth" to "medium", "!borderRightWidth" to "medium", "!borderBottomWidth" to "medium", "!borderLeftWidth" to "medium", "!borderTopStyle" to "none", "!borderRightStyle" to "none", "!borderBottomStyle" to "none", "!borderLeftStyle" to "none", "!borderTopColor" to "#000000", "!borderRightColor" to "#000000", "!borderBottomColor" to "#000000", "!borderLeftColor" to "#000000")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("menuClick" to null, "logoutClick" to null)
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
