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
open class GenPagesApplyTabbarCommunityComponentsPublish : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarCommunityComponentsPublish) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarCommunityComponentsPublish
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleClick = fun(){
                emit("click")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "publish-button fixed flex flex-col items-center justify-center gap-2 rounded-full shadow-lg", "onClick" to handleClick), _uA(
                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-fasong", "size" to 40, "color" to "#ffffff")),
                    _cE("text", _uM("class" to "text-white text-xs"), "发布")
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
                return _uM("publish-button" to _pS(_uM("right" to "40rpx", "bottom" to "200rpx", "width" to "120rpx", "height" to "120rpx", "zIndex" to 100, "flexDirection" to "column", "backgroundImage" to "linear-gradient(180deg, #4ade80 0%, #0d9488 100%)", "backgroundColor" to "rgba(0,0,0,0)", "boxShadow" to "0 8rpx 24rpx rgba(16, 185, 129, 0.3)", "opacity:active" to 0.8, "transform:active" to "scale(0.95)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null)
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
