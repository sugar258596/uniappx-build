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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesHireTabbarPositionIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarPositionIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarPositionIndex
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val list = ref(_uA<GetPositionListResult>())
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val tabItems = _uA<UTSJSONObject>(_uO("label" to "全部职位", "key" to 0), _uO("label" to "已发布", "key" to 2), _uO("label" to "未发布", "key" to 1), _uO("label" to "已完成", "key" to 3), _uO("label" to "已下架", "key" to 4))
            val queryParams = reactive<GetPositionListParams>(GetPositionListParams(Page = 1, PageSize = 10, IsOneself = 1, Lat = 0, Lng = 0, HireJobStatue = 0, SettleWay = "", HireJobCode = "", SalaryId = 0, Keywords = "", HireCompanyId = "", ToMemberId = ""))
            val getPositionId = fun(item: GetPositionListResult): Number {
                return item.Id ?: 0
            }
            val fetchData = fun(): UTSPromise<PositionListPageResult> {
                return wrapUTSPromise(suspend w1@{
                        try {
                            val res = await(getPositionList(queryParams))
                            val resData = if (res != null) {
                                ((res as UTSJSONObject)["data"] as UTSArray<GetPositionListResult>?)
                            } else {
                                null
                            }
                            val resTotal = if (res != null) {
                                ((res as UTSJSONObject)["total"] as Number?)
                            } else {
                                null
                            }
                            return@w1 PositionListPageResult(data = if (resData != null) {
                                resData
                            } else {
                                _uA<GetPositionListResult>()
                            }
                            , total = if (resTotal != null) {
                                resTotal
                            } else {
                                0
                            }
                            )
                        }
                         catch (e: Throwable) {
                            console.error(e)
                            hasMore.value = false
                            return@w1 PositionListPageResult(data = _uA(), total = 0)
                        }
                })
            }
            val initData = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        queryParams.Page = 1
                        hasMore.value = true
                        val _ref = await(fetchData())
                        val data = _ref.data
                        val total = _ref.total
                        list.value = data
                        if (list.value.length >= total) {
                            hasMore.value = false
                        }
                })
            }
            val onRefresh = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isRefreshing.value) {
                            return@w1
                        }
                        isRefreshing.value = true
                        queryParams.Page = 1
                        hasMore.value = true
                        val _ref = await(fetchData())
                        val data = _ref.data
                        val total = _ref.total
                        list.value = data
                        if (list.value.length >= total) {
                            hasMore.value = false
                        }
                        isRefreshing.value = false
                })
            }
            val onLoadMore = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoadingMore.value || !hasMore.value) {
                            return@w1
                        }
                        isLoadingMore.value = true
                        queryParams.Page++
                        val _ref = await(fetchData())
                        val data = _ref.data
                        val total = _ref.total
                        list.value = list.value.concat(data)
                        if (list.value.length >= total) {
                            hasMore.value = false
                        }
                        isLoadingMore.value = false
                })
            }
            watch(fun(): Number {
                return queryParams.HireJobStatue
            }
            , fun(_value: Number){
                initData()
            }
            )
            val handlePositionClick = fun(id: Number){
                if (id <= 0) {
                    return
                }
                uni_navigateTo(NavigateToOptions(url = "/pages/common/job-detail/index?id=" + id))
            }
            val handleEditClick = fun(id: Number){
                if (id <= 0) {
                    return
                }
                uni_navigateTo(NavigateToOptions(url = "/pages/hire/position/publish/index?id=" + id))
            }
            val confirmFreePinned = fun(id: Number): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            uni_showLoading(ShowLoadingOptions(title = "处理中..."))
                            await(postFreePinned(BuyPinnedParams(HireJobId = id)))
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "置顶成功", icon = "success"))
                            authStore.fetchUserInfo(null)
                            onRefresh()
                        }
                         catch (e: Throwable) {
                            uni_hideLoading(null)
                            console.error(e)
                        }
                })
            }
            val handleTopClick = fun(id: Number){
                val freeCount = if (authStore.userInfo != null && authStore.userInfo!!.FreePinnedCount != null) {
                    authStore.userInfo!!.FreePinnedCount
                } else {
                    0
                }
                if (freeCount > 0) {
                    uni_showModal(ShowModalOptions(title = "提示", content = "您今天还有 " + freeCount + " 次免费置顶机会，是否立刻使用？", success = fun(res){
                        if (res.confirm) {
                            confirmFreePinned(id)
                        }
                    }))
                } else {
                    uni_navigateTo(NavigateToOptions(url = "/pages/hire/position/top?id=" + id))
                }
            }
            val confirmOffline = fun(id: Number, actionText: String): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            uni_showLoading(ShowLoadingOptions(title = "处理中..."))
                            await(salesHireJob(SalesHireJobParams(HireJobId = id)))
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "" + actionText + "成功", icon = "success"))
                            onRefresh()
                        }
                         catch (e: Throwable) {
                            uni_hideLoading(null)
                            console.error(e)
                            uni_showToast(ShowToastOptions(title = "" + actionText + "失败，请稍后重试", icon = "none"))
                        }
                })
            }
            val handleOfflineClick = fun(item: GetPositionListResult){
                val id = getPositionId(item)
                if (id <= 0) {
                    return
                }
                val isOffline = item.SalesStatus === 1
                val actionText = if (isOffline) {
                    "上架"
                } else {
                    "下架"
                }
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要" + actionText + "该职位吗？", success = fun(res){
                    if (!res.confirm) {
                        return
                    }
                    confirmOffline(id, actionText)
                }
                ))
            }
            onLoad(fun(_options){
                initData()
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-header-tab flex flex-col"), _uA(
                    _cE("view", _uM("class" to "px-4"), _uA(
                        _cE("view", _uM("class" to "bg-white rounded-full flex flex-row w-full"), _uA(
                            _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to queryParams.Keywords, "onUpdate:modelValue" to fun(`$event`: String){
                                queryParams.Keywords = `$event`
                            }
                            , "onSearch" to initData), null, 8, _uA(
                                "modelValue",
                                "onUpdate:modelValue"
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "flex-1 py-2 gap-2"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTabsIndexClass), _uM("modelValue" to queryParams.HireJobStatue, "onUpdate:modelValue" to fun(`$event`: Number){
                            queryParams.HireJobStatue = `$event`
                        }
                        , "items" to tabItems, "active-color" to "#006633"), null, 8, _uA(
                            "modelValue",
                            "onUpdate:modelValue"
                        )),
                        _cE("scroll-view", _uM("class" to "flex-1 scroll-y", "refresher-enabled" to true, "refresher-triggered" to isRefreshing.value, "lower-threshold" to 100, "onRefresherrefresh" to onRefresh, "onScrolltolower" to onLoadMore), _uA(
                            _cE("view", _uM("class" to "mt-2 px-4"), _uA(
                                if (list.value.length > 0) {
                                    _cE(Fragment, _uM("key" to 0), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(list.value, fun(item, __key, __index, _cached): Any {
                                            return _cE("view", _uM("key" to getPositionId(item), "class" to "p-1 mb-1 bg-white rounded-lg"), _uA(
                                                _cV(unref(GenPagesHireTabbarPositionComponentsPositionCardClass), _uM("item" to item, "onClick" to fun(){
                                                    handlePositionClick(getPositionId(item))
                                                }, "onTop" to fun(){
                                                    handleTopClick(getPositionId(item))
                                                }, "onEdit" to fun(){
                                                    handleEditClick(getPositionId(item))
                                                }, "onOffline" to fun(){
                                                    handleOfflineClick(item)
                                                }), null, 8, _uA(
                                                    "item",
                                                    "onClick",
                                                    "onTop",
                                                    "onEdit",
                                                    "onOffline"
                                                ))
                                            ))
                                        }), 128),
                                        _cE("view", _uM("class" to "py-4 flex justify-center items-center"), _uA(
                                            if (isTrue(isLoadingMore.value)) {
                                                _cE("text", _uM("key" to 0, "class" to "text-gray-400 text-sm"), "加载中...")
                                            } else {
                                                if (isTrue(!hasMore.value)) {
                                                    _cE("text", _uM("key" to 1, "class" to "text-gray-400 text-sm"), "没有更多数据了")
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                            }
                                        ))
                                    ), 64)
                                } else {
                                    _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 1, "title" to "暂无相关职位", "description" to "没有找到对应的职位数据"))
                                }
                            ))
                        ), 40, _uA(
                            "refresher-triggered"
                        ))
                    )),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 1))
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
                return _uM("select-box" to _pS(_uM("borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000")), "nav-label" to _uM(".nav-tabs " to _uM("paddingBottom" to "8rpx")), "nav-item" to _uM(".nav-tabs " to _uM("content::after" to "\"\"", "height::after" to 2, "width::after" to 0, "backgroundColor::after" to "#006633", "transitionProperty::after" to "width", "transitionDuration::after" to "0.3s", "transitionTimingFunction::after" to "ease")), "active" to _uM(".nav-tabs " to _uM("width::after" to "100%")), "@TRANSITION" to _uM("nav-item" to _uM("property::after" to "width", "duration::after" to "0.3s", "timingFunction::after" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
