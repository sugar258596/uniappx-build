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
open class GenComponentsPointsRankList : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var rankList: UTSArray<RankUser> by `$props`
    open var showMonthPoints: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsRankList) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsRankList
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleViewMore = fun(){
                emit("view-more")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between mb-4"), _uA(
                        _cE("text", _uM("class" to "text-base font-bold text-zice-800"), "积分排名"),
                        _cE("view", _uM("class" to "flex flex-row items-center", "onClick" to handleViewMore), _uA(
                            _cE("text", _uM("class" to "text-sm text-zice-500"), "查看更多"),
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 20))
                        ))
                    )),
                    if (_ctx.rankList.length > 0) {
                        _cE("view", _uM("key" to 0, "class" to "flex flex-row items-center py-4 justify-around w-full"), _uA(
                            _cE("text", _uM("class" to "text-sm text-zice-400"), "排名"),
                            _cE("text", _uM("class" to "text-sm text-zice-400"), "用户"),
                            if (isTrue(_ctx.showMonthPoints)) {
                                _cE("text", _uM("key" to 0, "class" to "text-sm text-zice-400"), "月积分")
                            } else {
                                _cC("v-if", true)
                            },
                            _cE("text", _uM("class" to "text-sm text-zice-400"), "总积分")
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    if (_ctx.rankList.length > 0) {
                        _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-3"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(_ctx.rankList, fun(item, __key, __index, _cached): Any {
                                return _cE("view", _uM("key" to item.userId, "class" to "flex flex-row items-center w-full justify-around"), _uA(
                                    _cE("view", _uM("class" to "flex-1 flex items-center"), _uA(
                                        _cE("text", _uM("class" to "text-sm text-zice-600"), _tD(item.rank), 1)
                                    )),
                                    _cE("view", _uM("class" to "flex-1 flex flex-row items-center gap-4"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to item.avatar, "size" to "small"), null, 8, _uA(
                                            "src"
                                        )),
                                        _cE("text", _uM("class" to "text-xs text-zice-800 w-16 line-clamp-1"), _tD(item.userName), 1)
                                    )),
                                    if (isTrue(_ctx.showMonthPoints)) {
                                        _cE("view", _uM("key" to 0, "class" to "flex-1 flex items-center"), _uA(
                                            _cE("text", _uM("class" to "text-sm text-orange-500 text-right font-bold"), _tD(item.monthPoints ?: 0), 1)
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    },
                                    _cE("view", _uM("class" to "flex-1 flex items-center"), _uA(
                                        _cE("text", _uM("class" to "text-sm text-orange-500 text-right font-bold"), _tD(item.totalPoints), 1)
                                    ))
                                ))
                            }), 128)
                        ))
                    } else {
                        _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 2, "mode" to "data", "description" to "暂无排名"))
                    }
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
                return _uM("empty-description" to _pS(_uM("textAlign" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("view-more" to null)
        var props = _nP(_uM("rankList" to _uM("type" to "Array", "required" to true), "showMonthPoints" to _uM("type" to "Boolean", "required" to false, "default" to false)))
        var propsNeedCastKeys = _uA(
            "showMonthPoints"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
