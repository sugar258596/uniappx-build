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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyHistoryCommunicatedIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyHistoryCommunicatedIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyHistoryCommunicatedIndex
            val _cache = __ins.renderCache
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val total = ref(0)
            val params = reactive<GetCommunicateListParams>(GetCommunicateListParams(Type = 0, Lat = 0, Lng = 0, Page = 1, PageSize = 10))
            val tabItems = _uA(
                TabItem(key = 0, label = "谁沟通过我"),
                TabItem(key = 1, label = "我沟通过的")
            ) as UTSArray<TabItem>
            val list = ref(_uA<GetCommunicateListResult>())
            val fetchList = fun(isRefresh: Boolean = false): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getCommunicateList(params))
                            if (res != null) {
                                if (isRefresh) {
                                    list.value = res.data
                                } else {
                                    list.value = list.value.concat(res.data)
                                }
                                total.value = res.total
                                hasMore.value = list.value.length < res.total
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取沟通列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            val parseTags = fun(welfareName: String): UTSArray<String> {
                val source = if (welfareName != "") {
                    welfareName
                } else {
                    ""
                }
                return source.split(",").filter(fun(t): Boolean {
                    return t !== ""
                }
                ) as UTSArray<String>
            }
            val handleTabChange = fun(_key: Any){
                params.Type = _key as Number
                fetchList(true)
            }
            val handleRefresh = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        isRefreshing.value = true
                        await(fetchList(true))
                        isRefreshing.value = false
                })
            }
            val handleLoadMore = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoadingMore.value || !hasMore.value) {
                            return@w1
                        }
                        isLoadingMore.value = true
                        params.Page++
                        await(fetchList(false))
                        isLoadingMore.value = false
                })
            }
            val handleItemClick = fun(item: GetCommunicateListResult){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/job-detail/index?id=" + item.MemberId))
            }
            onLoad(fun(_options){
                fetchList(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "沟通过", "background" to "#ffffff")),
                    _cE("view", _uM("class" to "tabs bg-white"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTabsIndexClass), _uM("modelValue" to params.Type, "onUpdate:modelValue" to fun(`$event`: Number){
                            params.Type = `$event`
                        }
                        , "items" to tabItems, "centered" to "", "active-color" to "#00897b", "onChange" to handleTabChange), null, 8, _uA(
                            "modelValue",
                            "onUpdate:modelValue"
                        ))
                    )),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to handleRefresh, "onScrolltolower" to handleLoadMore), _uA(
                        if (isTrue(list.value.length === 0 && !isRefreshing.value)) {
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "description" to "暂无沟通记录"))
                        } else {
                            _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-4 px-6"), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(list.value, fun(item, index, __index, _cached): Any {
                                    return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("key" to index, "onClick" to fun(){
                                        handleItemClick(item)
                                    }
                                    ), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return _uA(
                                            _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                                _cE("view", _uM("class" to "flex flex-row items-center"), _uA(
                                                    _cE("view", _uM("class" to "w-40 h-40"), _uA(
                                                        _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to item.Avatar, "size" to 40), null, 8, _uA(
                                                            "src"
                                                        ))
                                                    )),
                                                    _cE("view", _uM("class" to "flex-1"), _uA(
                                                        _cE("text", _uM("class" to "text-base font-medium text-zice-800"), _tD(item.NickName), 1),
                                                        _cE("text", _uM("class" to "text-sm text-zice-500 mt-2"), _tD(item.HireCompanyName), 1)
                                                    ))
                                                )),
                                                _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                                    _cE("text", _uM("class" to "text-base font-bold text-zice-800"), _tD(item.JobName), 1),
                                                    _cE("text", _uM("class" to "text-base font-bold text-green-600"), _tD(item.SalaryName), 1)
                                                )),
                                                if (parseTags(item.HireWelfareName).length > 0) {
                                                    _cE("view", _uM("key" to 0, "class" to "flex flex-row flex-wrap gap-6"), _uA(
                                                        _cE(Fragment, null, RenderHelpers.renderList(parseTags(item.HireWelfareName), fun(tag, tagIndex, __index, _cached): Any {
                                                            return _cE("view", _uM("key" to tagIndex, "class" to "px-4 py-2 bg-green-100 rounded-base"), _uA(
                                                                _cE("text", _uM("class" to "text-xs text-green-600"), _tD(tag), 1)
                                                            ))
                                                        }), 128)
                                                    ))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                                ,
                                                _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                                    _cE("text", _uM("class" to "text-sm text-zice-500"), _tD(item.AreaCodeName) + " " + _tD(item.HireAddress), 1),
                                                    _cE("text", _uM("class" to "text-sm text-zice-400"), _tD(item.distanceCile) + "km", 1)
                                                ))
                                            ))
                                        )
                                    }
                                    ), "_" to 2), 1032, _uA(
                                        "onClick"
                                    ))
                                }
                                ), 128),
                                _cE("view", _uM("class" to "py-16 flex items-center justify-center"), _uA(
                                    if (isTrue(isLoadingMore.value)) {
                                        _cE("text", _uM("key" to 0, "class" to "text-sm text-zice-400"), "加载中...")
                                    } else {
                                        if (isTrue(!hasMore.value && list.value.length > 0)) {
                                            _cE("text", _uM("key" to 1, "class" to "text-sm text-zice-400"), "没有更多了")
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    }
                                ))
                            ))
                        }
                    ), 40, _uA(
                        "refresher-triggered"
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
                return _uM("nav-label" to _uM(".tabs .nav-tabs " to _uM("paddingBottom" to "8rpx")), "nav-item" to _uM(".tabs .nav-tabs " to _uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "content::after" to "\"\"", "height::after" to 2, "width::after" to 0, "backgroundColor::after" to "#00897b", "transitionProperty::after" to "width", "transitionDuration::after" to "0.3s", "transitionTimingFunction::after" to "ease")), "active" to _uM(".tabs .nav-tabs " to _uM("width::after" to "20%")), "@TRANSITION" to _uM("nav-item" to _uM("property::after" to "width", "duration::after" to "0.3s", "timingFunction::after" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
