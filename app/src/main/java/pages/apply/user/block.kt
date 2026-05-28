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
open class GenPagesApplyUserBlock : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyUserBlock) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyUserBlock
            val _cache = __ins.renderCache
            val keyword = ref("")
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val total = ref(0)
            val params = reactive<Pagination>(Pagination(Page = 1, PageSize = 10))
            val list = ref(_uA<GetBlockListResult>())
            fun gen_fetchList_fn(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getBlockList(params))
                            if (res != null) {
                                val data = (res as UTSJSONObject)["data"] as UTSArray<GetBlockListResult>
                                val total_count = (res as UTSJSONObject)["total"] as Number
                                if (isRefresh) {
                                    list.value = data
                                } else {
                                    list.value = list.value.concat(data)
                                }
                                total.value = total_count
                                hasMore.value = list.value.length < total_count
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取屏蔽列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            val fetchList = ::gen_fetchList_fn
            val handleSearch = fun(){
                fetchList(true)
            }
            val handleAddCompany = fun(){
                uni_showToast(ShowToastOptions(title = "点击添加屏蔽公司", icon = "none"))
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
            onLoad(fun(_options){
                fetchList(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-white"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "屏蔽公司")),
                    _cE("view", _uM("class" to "px-4 py-4"), _uA(
                        _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to keyword.value, "onUpdate:modelValue" to fun(`$event`: String){
                            keyword.value = `$event`
                        }
                        , "placeholder" to "请输入关键词搜索", "onSearch" to handleSearch), null, 8, _uA(
                            "modelValue",
                            "onUpdate:modelValue"
                        ))
                    )),
                    _cE("view", _uM("class" to "px-4 py-2"), _uA(
                        _cE("text", _uM("class" to "text-base text-gray-800 font-bold"), "已屏蔽" + _tD(total.value) + "家公司", 1)
                    )),
                    _cE("scroll-view", _uM("class" to "flex-1 px-4", "scroll-y" to "", "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to handleRefresh, "onScrolltolower" to handleLoadMore), _uA(
                        if (isTrue(list.value.length === 0 && !isRefreshing.value)) {
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "title" to "暂无屏蔽公司", "description" to "您可以继续浏览其他内容"))
                        } else {
                            _cE(Fragment, _uM("key" to 1), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(list.value, fun(item, index, __index, _cached): Any {
                                    return _cE("view", _uM("key" to index, "class" to "py-6 border-b border-gray-100"), _uA(
                                        _cE("text", _uM("class" to "text-base text-gray-800"), _tD(item.ScreenName), 1)
                                    ))
                                }
                                ), 128),
                                _cE("view", _uM("class" to "flex justify-center items-center py-4"), _uA(
                                    if (isTrue(isLoadingMore.value)) {
                                        _cE("text", _uM("key" to 0, "class" to "text-xs text-gray-400"), "加载中...")
                                    } else {
                                        if (isTrue(!hasMore.value && list.value.length > 0)) {
                                            _cE("text", _uM("key" to 1, "class" to "text-xs text-gray-400"), "没有更多了")
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    }
                                ))
                            ), 64)
                        }
                    ), 40, _uA(
                        "refresher-triggered"
                    )),
                    _cE("view", _uM("class" to "p-6 pb-12"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "success", "block" to "", "size" to "large", "custom-style" to "background-color: #065f46; border: none; border-radius: 100px;", "onClick" to handleAddCompany), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                " 添加屏蔽公司 "
                            )
                        }
                        ), "_" to 1))
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
