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
open class GenUniModulesTangUiXComponentsTCellIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String? by `$props`
    open var content: String? by `$props`
    open var showIcon: Boolean by `$props`
    open var icon: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTCellIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTCellIndex
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleClick = fun(){
                emit("cell")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-col gap-4", "onClick" to handleClick), _uA(
                    _cE("view", _uM("class" to "flex-row justify-between gap-4 p-2 m-2"), _uA(
                        renderSlot(_ctx.`$slots`, "title", _uO(), fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "text-zice-900 font-bold"), _tD(_ctx.title), 1)
                            )
                        }
                        ),
                        renderSlot(_ctx.`$slots`, "right", _uO(), fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex-row gap-4"), _uA(
                                    renderSlot(_ctx.`$slots`, "content", _uO(), fun(): UTSArray<Any> {
                                        return _uA(
                                            _cE("text", _uM("class" to "text-zice-600"), _tD(_ctx.content), 1)
                                        )
                                    }
                                    ),
                                    if (isTrue(_ctx.showIcon)) {
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("key" to 0, "name" to _ctx.icon, "color" to "#a1a1aa", "size" to 24), null, 8, _uA(
                                            "name"
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            )
                        }
                        )
                    )),
                    renderSlot(_ctx.`$slots`, "footer")
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
                return _uM("text-zice-900" to _pS(_uM("color" to "#18181b")), "text-zice-600" to _pS(_uM("color" to "#52525b")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("cell" to null)
        var props = _nP(_uM("title" to _uM("type" to "String", "required" to false), "content" to _uM("type" to "String", "required" to false), "showIcon" to _uM("type" to "Boolean", "required" to false, "default" to true), "icon" to _uM("type" to "String", "required" to false, "default" to "icon-youjiantou")))
        var propsNeedCastKeys = _uA(
            "showIcon",
            "icon"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
