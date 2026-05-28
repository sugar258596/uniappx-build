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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyUserReport : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyUserReport) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyUserReport
            val _cache = __ins.renderCache
            val reportList = ref(_uA<GetReportListResult>())
            val isRefreshing = ref(false)
            val isLoading = ref(false)
            val hasMore = ref(true)
            val total = ref(0)
            val params = reactive<Pagination>(Pagination(Page = 1, PageSize = 10))
            fun gen_fetchList_fn(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getReportList(params))
                            if (res != null) {
                                val data = (res as UTSJSONObject)["data"] as UTSArray<GetReportListResult>
                                val total_count = (res as UTSJSONObject)["total"] as Number
                                if (isRefresh) {
                                    reportList.value = data
                                } else {
                                    reportList.value = reportList.value.concat(data)
                                }
                                total.value = total_count
                                hasMore.value = reportList.value.length < total_count
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取举报列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            val fetchList = ::gen_fetchList_fn
            onLoad(fun(_options){
                fetchList(true)
            }
            )
            val onRefresh = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        isRefreshing.value = true
                        await(fetchList(true))
                        isRefreshing.value = false
                })
            }
            val onLoadMore = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoading.value || !hasMore.value) {
                            return@w1
                        }
                        isLoading.value = true
                        params.Page = params.Page + 1
                        await(fetchList(false))
                        isLoading.value = false
                })
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "举报中心")),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to onRefresh, "onScrolltolower" to onLoadMore), _uA(
                        if (isTrue(reportList.value.length === 0 && !isRefreshing.value)) {
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "title" to "暂无举报记录", "description" to "您可以继续浏览其他内容"))
                        } else {
                            _cE("view", _uM("key" to 1, "class" to "px-4 py-4 flex flex-col gap-4"), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(reportList.value, fun(item, index, __index, _cached): Any {
                                    return _cE("view", _uM("key" to index, "class" to "bg-white rounded-xl p-4 flex flex-row gap-4 shadow-sm"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to (item.Avatar ?: ""), "width" to "48px", "height" to "48px", "radius" to "24px"), null, 8, _uA(
                                            "src"
                                        )),
                                        _cE("view", _uM("class" to "flex-1 flex flex-col justify-between"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                                _cE("text", _uM("class" to "text-base font-bold text-gray-800"), _tD(item.NickName), 1),
                                                _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.Addtime), 1)
                                            )),
                                            _cE("view", _uM("class" to "flex flex-row justify-between items-center mt-2"), _uA(
                                                _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-cuowu", "size" to 24, "color" to "#ef4444")),
                                                    _cE("text", _uM("class" to "text-xs text-red-500"), "已举报")
                                                ))
                                            ))
                                        ))
                                    ))
                                }
                                ), 128),
                                _cE("view", _uM("class" to "flex justify-center items-center py-4"), _uA(
                                    _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(if (isLoading.value) {
                                        "加载中..."
                                    } else {
                                        if (hasMore.value) {
                                            "上拉加载更多"
                                        } else {
                                            "没有更多了"
                                        }
                                    }
                                    ), 1)
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
