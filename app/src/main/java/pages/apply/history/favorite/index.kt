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
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyHistoryFavoriteIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyHistoryFavoriteIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyHistoryFavoriteIndex
            val _cache = __ins.renderCache
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val total = ref(0)
            val params = reactive<GetUserCollectParams>(GetUserCollectParams(Type = 10, Lng = 0, Lat = 0, Page = 1, PageSize = 10))
            val list = ref(_uA<GetUserCollectResult>())
            val parseTags = fun(welfareName: String?): UTSArray<String> {
                val source = if (welfareName != null && welfareName != "") {
                    welfareName
                } else {
                    ""
                }
                return source.split(",").filter(fun(t): Boolean {
                    return t !== ""
                }
                ) as UTSArray<String>
            }
            val fetchList = fun(isRefresh: Boolean = false): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getUserCollect(params))
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
                            console.error("获取收藏列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            onLoad(fun(_options){
                fetchList(true)
            }
            )
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
            val handleJobClick = fun(job: GetUserCollectResult){
                if (job.HireJobId != null) {
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/job-detail/index?id=" + job.HireJobId!!))
                }
            }
            val handleCancelFavorite = fun(job: GetUserCollectResult){
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要取消收藏该职位吗？", success = fun(res): UTSPromise<Unit> {
                    return wrapUTSPromise(suspend {
                            if (res.confirm && job.HireJobId != null) {
                                try {
                                    await(deleteFavoritePosition(AddFavoriteParams(Id = job.HireJobId!!, Type = "10")))
                                    list.value = list.value.filter(fun(item): Boolean {
                                        return item.Id !== job.Id
                                    }
                                    )
                                    total.value--
                                    uni_showToast(ShowToastOptions(title = "已取消收藏", icon = "success"))
                                }
                                 catch (err: Throwable) {
                                    console.error("取消收藏失败:", err)
                                    uni_showToast(ShowToastOptions(title = "取消收藏失败", icon = "none"))
                                }
                            }
                    })
                }
                ))
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "收藏职位", "background" to "#ffffff")),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to handleRefresh, "onScrolltolower" to handleLoadMore), _uA(
                        if (isTrue(list.value.length === 0 && !isRefreshing.value)) {
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "description" to "暂无收藏职位"))
                        } else {
                            _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-4 px-6 pb-16"), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(list.value, fun(job, index, __index, _cached): Any {
                                    return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("key" to index, "onClick" to fun(){
                                        handleJobClick(job)
                                    }
                                    ), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return _uA(
                                            _cE("view", _uM("class" to "flex flex-col gap-8"), _uA(
                                                _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                                    _cE("text", _uM("class" to "text-base font-bold text-zice-800"), _tD(job.JobName), 1),
                                                    _cE("text", _uM("class" to "text-base font-bold text-green-600"), _tD(job.SalaryName), 1)
                                                )),
                                                _cE("text", _uM("class" to "text-sm text-zice-500"), _tD(job.HireCompanyName), 1),
                                                _cE("view", _uM("class" to "flex flex-col justify-between gap-4"), _uA(
                                                    if (parseTags(job.HireWelfareName).length > 0) {
                                                        _cE("view", _uM("key" to 0, "class" to "flex flex-row flex-wrap gap-2"), _uA(
                                                            _cE(Fragment, null, RenderHelpers.renderList(parseTags(job.HireWelfareName), fun(tag, tagIndex, __index, _cached): Any {
                                                                return _cE("view", _uM("key" to tagIndex, "class" to "px-4 py-2 bg-green-100 rounded-base"), _uA(
                                                                    _cE("text", _uM("class" to "text-xs text-green-600"), _tD(tag), 1)
                                                                ))
                                                            }), 128)
                                                        ))
                                                    } else {
                                                        _cC("v-if", true)
                                                    }
                                                    ,
                                                    _cE("view", _uM("class" to "flex flex-row gap-4 text-zice-400"), _uA(
                                                        _cE("text", _uM("class" to "text-sm"), _tD(job.HireCityCodeName) + " " + _tD(job.HireAreaCodeName), 1),
                                                        if (job.distanceCile != null) {
                                                            _cE("text", _uM("key" to 0, "class" to "text-sm"), _tD(job.distanceCile) + "km", 1)
                                                        } else {
                                                            _cC("v-if", true)
                                                        }
                                                    )),
                                                    _cE("view", _uM("class" to "flex items-center gap-4", "onClick" to withModifiers(fun(){
                                                        handleCancelFavorite(job)
                                                    }
                                                    , _uA(
                                                        "stop"
                                                    ))), _uA(
                                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-quxiaoshoucang", "color" to "#00897b")),
                                                        _cE("text", _uM("class" to "text-sm text-teal-600"), "取消收藏")
                                                    ), 8, _uA(
                                                        "onClick"
                                                    ))
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
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
