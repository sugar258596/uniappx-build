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
open class GenPagesApplyResumeOnlineComponentsUserCard : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var info: UserCardInfo by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineComponentsUserCard) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineComponentsUserCard
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleEdit = fun(){
                emit("editClick")
            }
            val handleStatusClick = fun(){
                emit("statusClick")
            }
            val getAvatarStatusClass = fun(): String {
                when (props.info.avatarStatus) {
                    1 -> 
                        return "bg-teal-500 text-white"
                    2 -> 
                        return "bg-red-500 text-white"
                    0 -> 
                        return "bg-orange-500 text-white"
                    else -> 
                        return "bg-orange-500 text-white"
                }
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-col gap-4 bg-white rounded-lg p-4"), _uA(
                    _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-4"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to _ctx.info.avatar, "size" to "large"), null, 8, _uA(
                                "src"
                            )),
                            if (isTrue(_ctx.info.avatarStatusText)) {
                                _cE("view", _uM("key" to 0, "class" to _nC(_uA(
                                    "px-2 rounded-full ",
                                    getAvatarStatusClass()
                                ))), _uA(
                                    _cE("text", _uM("class" to "text-xs whitespace-nowrap"), _tD(_ctx.info.avatarStatusText), 1)
                                ), 2)
                            } else {
                                _cC("v-if", true)
                            }
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2 border border-solid border-teal-500 rounded-md px-6 py-4 tap-active", "onClick" to handleStatusClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-teal-600"), _tD(_ctx.info.status ?: "请选择"), 1),
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiajiantou", "size" to 24, "color" to "#0d9488"))
                        ))
                    )),
                    _cE("view", _uM("class" to "flex flex-col gap-2 tap-active", "onClick" to handleEdit), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                            _cE("text", _uM("class" to "text-lg font-bold text-gray-800"), _tD(_ctx.info.name), 1),
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-bianji", "size" to 32, "color" to "#9ca3af", "onClick" to handleEdit))
                        )),
                        _cE("text", _uM("class" to "text-sm text-gray-500"), _tD(_ctx.info.age) + "岁 | " + _tD(_ctx.info.education) + " | " + _tD(_ctx.info.location) + " | " + _tD(_ctx.info.salary), 1),
                        if (isTrue(_ctx.info.phone)) {
                            _cE("view", _uM("key" to 0, "class" to "flex flex-row items-center gap-2 mt-1"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-dianhua", "size" to 28, "color" to "#0d9488")),
                                _cE("text", _uM("class" to "text-sm text-gray-600"), _tD(_ctx.info.phone), 1)
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                        ,
                        if (isTrue(_ctx.info.email)) {
                            _cE("view", _uM("key" to 1, "class" to "flex flex-row items-center gap-2"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youxiang", "size" to 28, "color" to "#0d9488")),
                                _cE("text", _uM("class" to "text-sm text-gray-600"), _tD(_ctx.info.email), 1)
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("statusClick" to null, "editClick" to null)
        var props = _nP(_uM("info" to _uM("type" to "Object", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
