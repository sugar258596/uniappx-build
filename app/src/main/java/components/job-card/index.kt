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
open class GenComponentsJobCardIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var jobs: UTSArray<JobInfo> by `$props`
    open var bannerIndex: Number by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsJobCardIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsJobCardIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleClick = fun(jobInfo: JobInfo){
                emit("click", jobInfo)
            }
            val showBannerAfter = fun(index: Number): Boolean {
                return props.bannerIndex >= 0 && index === props.bannerIndex
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-row flex-wrap gap-2 px-2"), _uA(
                    _cE(Fragment, null, RenderHelpers.renderList(_ctx.jobs, fun(item, index, __index, _cached): Any {
                        return _cE(Fragment, _uM("key" to item.id), _uA(
                            _cE("view", _uM("class" to "job-card-wrapper"), _uA(
                                _cE("view", _uM("class" to "job-card bg-white rounded-lg overflow-hidden shadow-sm tap-active", "onClick" to fun(){
                                    handleClick(item)
                                }
                                ), _uA(
                                    if (isTrue(item.image)) {
                                        _cE("view", _uM("key" to 0, "class" to "relative"), _uA(
                                            _cE("view", _uM("class" to "w-full h-30 bg-gray-200"), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to item.image, "height" to "100%"), null, 8, _uA(
                                                    "src"
                                                ))
                                            ))
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                    ,
                                    _cE("view", _uM("class" to "p-2 flex flex-col gap-2"), _uA(
                                        _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                                            _cE("text", _uM("class" to "text-base font-medium text-gray-800 line-clamp-1"), _tD(item.title), 1),
                                            _cE("view", _uM("class" to "flex flex-row justify-between"), _uA(
                                                _cE("view", _uM("class" to "text-xs text-gray-500 line-clamp-1 flex-1"), _tD(item.company), 1),
                                                _cE("view", _uM("class" to "text-xs text-gray-500 line-clamp-1 flex-1"), _tD(item.area) + " " + _tD(item.address), 1)
                                            )),
                                            _cE("view", _uM("class" to "flex flex-row flex-wrap gap-2"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(item.benefitTags.slice(0, 3), fun(tag, tagIndex, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to tagIndex, "class" to "px-2 py-2 border border-solid border-green-700 rounded-md"), _uA(
                                                        _cE("text", _uM("class" to "text-xs text-green-700"), _tD(tag), 1)
                                                    ))
                                                }
                                                ), 128)
                                            )),
                                            _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                                _cE("text", _uM("class" to "text-base font-bold text-green-700"), _tD(item.salaryName), 1),
                                                _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.distance.toFixed(1)) + "km", 1)
                                            ))
                                        ))
                                    ))
                                ), 8, _uA(
                                    "onClick"
                                ))
                            )),
                            if (isTrue(showBannerAfter(index))) {
                                _cE("view", _uM("key" to 0, "class" to "w-full"), _uA(
                                    renderSlot(_ctx.`$slots`, "banner")
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                        ), 64)
                    }
                    ), 128)
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
                return _uM("job-card" to _pS(_uM("width" to "100%")), "hot-tag" to _pS(_uM("backgroundImage" to "linear-gradient(90deg, #dc2626 0%, #ef4444 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopLeftRadius" to 0, "borderTopRightRadius" to "16rpx", "borderBottomRightRadius" to "16rpx", "borderBottomLeftRadius" to 0, "paddingTop" to "4rpx", "paddingRight" to "12rpx", "paddingBottom" to "4rpx", "paddingLeft" to "8rpx")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null)
        var props = _nP(_uM("jobs" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<JobInfo> {
            return _uA()
        }
        ), "bannerIndex" to _uM("type" to "Number", "required" to false, "default" to -1)))
        var propsNeedCastKeys = _uA(
            "jobs",
            "bannerIndex"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
