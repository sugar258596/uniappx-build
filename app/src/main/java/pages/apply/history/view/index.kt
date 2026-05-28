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
open class GenPagesApplyHistoryViewIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyHistoryViewIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyHistoryViewIndex
            val _cache = __ins.renderCache
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val total = ref(0)
            val params = reactive<GetCommunicateListParams>(GetCommunicateListParams(Type = 0, Lat = 0, Lng = 0, Page = 1, PageSize = 10))
            val tabItems = _uA(
                TabItem(key = 0, label = "谁看过我"),
                TabItem(key = 1, label = "我看过谁")
            ) as UTSArray<TabItem>
            val list = ref(_uA<GetHaveSeenResult>())
            val fetchList = fun(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getHaveSeen(params))
                            if (res != null) {
                                val rawData = (res as UTSJSONObject)["data"] as UTSArray<GetHaveSeenResult>?
                                val rawTotal = (res as UTSJSONObject)["total"] as Number?
                                val data = if (rawData != null) {
                                    rawData
                                } else {
                                    _uA<GetHaveSeenResult>()
                                }
                                val nextTotal = if (rawTotal != null) {
                                    rawTotal
                                } else {
                                    0
                                }
                                if (isRefresh) {
                                    list.value = data
                                } else {
                                    list.value = list.value.concat(data)
                                }
                                total.value = nextTotal
                                hasMore.value = list.value.length < nextTotal
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取查看记录失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            onLoad(fun(_options){
                fetchList(true)
            }
            )
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
                        params.Page = params.Page + 1
                        await(fetchList(false))
                        isLoadingMore.value = false
                })
            }
            val handleViewRecruiter = fun(item: GetHaveSeenResult){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/recruiter-info/index?id=" + item.NickName))
            }
            val handleViewJob = fun(item: GetHaveSeenResult){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/job-detail/index?id=" + item.JobName))
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "查看记录", "background" to "#ffffff")),
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
                        _cE("view", _uM("class" to "px-8 py-4"), _uA(
                            if (isTrue(list.value.length === 0 && !isRefreshing.value)) {
                                _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "description" to "暂无查看记录"))
                            } else {
                                _cE(Fragment, _uM("key" to 1), _uA(
                                    if (params.Type === 0) {
                                        _cV(unref(GenPagesApplyHistoryViewComponentsViewerListClass), _uM("key" to 0, "list" to list.value, "onViewRecruiter" to handleViewRecruiter), null, 8, _uA(
                                            "list"
                                        ))
                                    } else {
                                        _cV(unref(GenPagesApplyHistoryViewComponentsViewedListClass), _uM("key" to 1, "list" to list.value, "onViewJob" to handleViewJob), null, 8, _uA(
                                            "list"
                                        ))
                                    }
                                ), 64)
                            }
                            ,
                            _cE("view", _uM("class" to "py-16 flex items-center justify-center"), _uA(
                                if (isTrue(isLoadingMore.value)) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "加载中...")
                                } else {
                                    if (isTrue(!hasMore.value && list.value.length > 0)) {
                                        _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-400"), "没有更多了")
                                    } else {
                                        _cC("v-if", true)
                                    }
                                }
                            ))
                        ))
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
