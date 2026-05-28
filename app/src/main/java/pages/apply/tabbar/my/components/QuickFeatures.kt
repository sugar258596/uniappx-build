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
open class GenPagesApplyTabbarMyComponentsQuickFeatures : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarMyComponentsQuickFeatures) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarMyComponentsQuickFeatures
            val _cache = __ins.renderCache
            val menuItems = _uA(
                FeatureMenuItem(icon = "icon-zaixianjianli", label = "在线简历", path = "/pages/apply/resume/online/index"),
                FeatureMenuItem(icon = "icon-fujianjianli", label = "附件简历", path = "/pages/apply/resume/attachment"),
                FeatureMenuItem(icon = "icon-qiuzhiyixiang", label = "求职意向", path = "/pages/apply/resume/expectation"),
                FeatureMenuItem(icon = "icon-toudijilu2", label = "投递记录", path = "/pages/apply/history/delivery")
            ) as UTSArray<FeatureMenuItem>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleMenuClick = fun(item: FeatureMenuItem){
                emit("menuClick", item)
            }
            return fun(): Any? {
                return _cV(unref(GenComponentsCommonFeatureGridIndexClass), _uM("title" to "常用功能", "items" to menuItems, "icon-color" to "#22c55e", "icon-bg-color" to "#dcfce7", "onMenuClick" to handleMenuClick))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("menuClick" to null)
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
