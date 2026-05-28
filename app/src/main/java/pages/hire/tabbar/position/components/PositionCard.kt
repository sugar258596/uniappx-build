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
open class GenPagesHireTabbarPositionComponentsPositionCard : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var item: GetPositionListResult by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarPositionComponentsPositionCard) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarPositionComponentsPositionCard
            val _cache = __ins.renderCache
            val props = __props
            val isOffline = computed(fun(): Boolean {
                return props.item.SalesStatus === 1
            }
            )
            val offlineActionText = computed(fun(): String {
                return if (isOffline.value) {
                    "上架"
                } else {
                    "下架"
                }
            }
            )
            val offlineActionIcon = computed(fun(): String {
                return if (isOffline.value) {
                    "icon-shangjia"
                } else {
                    "icon-xiajia"
                }
            }
            )
            val asText = fun(value: Any?): String {
                return if (value != null) {
                    (value as String)
                } else {
                    ""
                }
            }
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleClick = fun(){
                emit("click")
            }
            val handleTop = fun(e: Event){
                e.stopPropagation()
                emit("top")
            }
            val handleEdit = fun(e: Event){
                e.stopPropagation()
                emit("edit")
            }
            val handleOffline = fun(e: Event){
                e.stopPropagation()
                emit("offline")
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "w-full gap-2", "padding" to "small", "custom-class" to "shadow-lg tap-active", "onClick" to handleClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "flex flex-row justify-between"), _uA(
                            _cE("view", _uM("class" to "text-base font-bold text-zice-800"), _tD(asText(_ctx.item.JobName)), 1),
                            _cE("view", _uM("class" to "text-sm text-teal-700"), _tD(asText(_ctx.item.SalaryName)), 1)
                        )),
                        _cE("view", _uM("class" to "flex flex-col gap-1 mt-1"), _uA(
                            _cE("view", _uM("class" to "flex flex-row gap-1"), _uA(
                                _cE("view", _uM("class" to "bg-gray-200 rounded-md text-zice-500 text-xs px-2 py-1"), _tD(asText(_ctx.item.JobPositionName)), 1)
                            )),
                            _cE("view", _uM("class" to "text-sm text-zice-600 line-clamp-3"), _tD(asText(_ctx.item.HireDescription)), 1)
                        )),
                        _cE("view", _uM("class" to "flex flex-row gap-1 mt-1 justify-around"), _uA(
                            if (_ctx.item.IsPinned !== 1) {
                                _cE("view", _uM("key" to 0, "class" to "flex flex-row gap-2 justify-center items-center", "onClick" to handleTop), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-dingzhi", "size" to 30, "color" to "#000")),
                                    _cE("text", _uM("class" to "text-sm text-zice-600"), "置顶")
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                            ,
                            _cE("view", _uM("class" to "flex flex-row gap-2 justify-center items-center", "onClick" to handleEdit), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-bianji", "size" to 28, "color" to "#000")),
                                _cE("text", _uM("class" to "text-sm text-zice-600"), "编辑")
                            )),
                            _cE("view", _uM("class" to "flex flex-row gap-2 justify-center items-center", "onClick" to handleOffline), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to offlineActionIcon.value, "size" to 30, "color" to "#000"), null, 8, _uA(
                                    "name"
                                )),
                                _cE("text", _uM("class" to "text-sm text-zice-600"), _tD(offlineActionText.value), 1)
                            ))
                        ))
                    )
                }
                ), "_" to 1))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("shadow-lg" to _pS(_uM("!boxShadow" to "0 2px 12px 3px rgba(0, 0, 0, 0.1)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null, "top" to null, "edit" to null, "offline" to null)
        var props = _nP(_uM("item" to _uM("type" to "Object", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
