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
open class GenPagesApplyHistoryViewComponentsViewedList : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var list: UTSArray<GetHaveSeenResult> by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyHistoryViewComponentsViewedList) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyHistoryViewComponentsViewedList
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleViewJob = fun(item: GetHaveSeenResult){
                emit("viewJob", item)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-col"), _uA(
                    _cE("view", _uM("class" to "flex flex-col gap-8"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(props.list, fun(item, index, __index, _cached): Any {
                            return _cE("view", _uM("key" to index, "class" to "flex flex-row items-start bg-white border-b border-gray-100 tap-active", "onClick" to fun(){
                                handleViewJob(item)
                            }
                            ), _uA(
                                _cE("view", _uM("class" to "flex-1 flex flex-col gap-2"), _uA(
                                    _cE("text", _uM("class" to "text-base font-bold text-gray-800"), _tD(item.JobName), 1),
                                    _cE("text", _uM("class" to "text-sm text-gray-500"), _tD(item.CompanyName), 1),
                                    _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.AreaCodeName) + " " + _tD(item.HireAddress) + " " + _tD(item.distanceCile) + "km ", 1)
                                )),
                                _cE("view", _uM("class" to "flex flex-col items-end gap-2"), _uA(
                                    _cE("text", _uM("class" to "text-base font-bold text-green-600"), _tD(item.SalaryName), 1),
                                    _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.AddTime) + " 查看过", 1)
                                ))
                            ), 8, _uA(
                                "onClick"
                            ))
                        }
                        ), 128)
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("viewJob" to null)
        var props = _nP(_uM("list" to _uM("type" to "Array", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
