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
open class GenPagesApplyResumeOnlineComponentsAdvantage : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var content: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineComponentsAdvantage) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineComponentsAdvantage
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleEdit = fun(){
                emit("edit")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg p-6"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between mb-4"), _uA(
                        _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "个人优势"),
                        _cE("view", _uM("class" to "flex items-center justify-center", "onClick" to handleEdit), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-tianjia", "size" to 40, "color" to "#999"))
                        ))
                    )),
                    _cE("text", _uM("class" to "text-sm text-gray-600 leading-relaxed line-clamp-3"), _tD(_ctx.content), 1)
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
                return _uM("leading-relaxed" to _pS(_uM("lineHeight" to 1.6)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("edit" to null)
        var props = _nP(_uM("content" to _uM("type" to "String", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
