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
open class GenPagesApplyResumeOnlineComponentsCustomModuleList : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var list: UTSArray<CustomModule__1> by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineComponentsCustomModuleList) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineComponentsCustomModuleList
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleAdd = fun(mod: CustomModule__1){
                emit("add", mod)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg p-6"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center mb-4"), _uA(
                        _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "自定义添加")
                    )),
                    _cE("view", _uM("class" to "flex flex-col"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(mod, index, __index, _cached): Any {
                            return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                "flex flex-row items-center justify-between py-6 border-b border-gray-100",
                                _uM("border-b-0" to (index === _ctx.list.length - 1))
                            )), "onClick" to fun(){
                                handleAdd(mod)
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "text-base text-gray-800"), _tD(mod.name), 1),
                                _cE("view", _uM("class" to "flex items-center justify-center"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-tianjia", "size" to 40, "color" to "#999"))
                                ))
                            ), 10, _uA(
                                "onClick"
                            ))
                        }
                        ), 128)
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
                return _uM("border-b-0" to _pS(_uM("borderBottomWidth" to 0)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("add" to null)
        var props = _nP(_uM("list" to _uM("type" to "Array", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
