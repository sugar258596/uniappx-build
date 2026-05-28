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
open class GenPagesApplyTabbarHomeComponentsGroupBannerIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarHomeComponentsGroupBannerIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarHomeComponentsGroupBannerIndex
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleJoin = fun(){
                emit("join")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "group-banner flex flex-row items-center justify-between px-1 py-1 mx-1 rounded-lg"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center gap-2 flex-1"), _uA(
                        _cE("view", _uM("class" to "flex justify-center items-center bg-teal-600 rounded-full p-2"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-weixinhaoyou", "size" to 40, "color" to "#fff"))
                        )),
                        _cE("view", _uM("class" to "flex flex-col"), _uA(
                            _cE("text", _uM("class" to "text-sm text-white font-medium"), "附近工友正在群内抢"),
                            _cE("text", _uM("class" to "text-sm text-yellow-300 font-bold"), "【高薪日结】")
                        ))
                    )),
                    _cE("view", _uM("class" to "join-btn flex items-center justify-center px-4 py-2 rounded-full", "onClick" to handleJoin), _uA(
                        _cE("text", _uM("class" to "text-sm text-green-600 font-medium"), "进群")
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
                return _uM("group-banner" to _pS(_uM("backgroundImage" to "linear-gradient(90deg, #16a34a 0%, #22c55e 100%)", "backgroundColor" to "rgba(0,0,0,0)", "boxShadow" to "0 4rpx 12rpx rgba(22, 163, 74, 0.3)")), "join-btn" to _pS(_uM("backgroundColor" to "#ffffff", "boxShadow" to "0 2rpx 8rpx rgba(0, 0, 0, 0.1)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("join" to null)
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
