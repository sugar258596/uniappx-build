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
open class GenPagesApplyCommunityMyPosts : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyCommunityMyPosts) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyCommunityMyPosts
            val _cache = __ins.renderCache
            val keyword = ref("")
            val postList = ref(_uA<FindBaseResponse>())
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val isEditMode = ref(false)
            val selectedIds = ref(_uA<Number>())
            val isAllSelected = computed(fun(): Boolean {
                return postList.value.length > 0 && selectedIds.value.length === postList.value.length
            }
            )
            val params = reactive<GetFindListParams>(GetFindListParams(Page = 1, PageSize = 10, IsFollow = 0, Keywords = "", myType = 0))
            val fetchList = fun(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getFindList(params))
                            if (res != null) {
                                val rawData = (res as UTSJSONObject)["data"] as UTSArray<FindBaseResponse>?
                                val rawTotal = (res as UTSJSONObject)["total"] as Number?
                                val resData = if (rawData != null) {
                                    rawData
                                } else {
                                    _uA<FindBaseResponse>()
                                }
                                val resTotal = if (rawTotal != null) {
                                    rawTotal
                                } else {
                                    0
                                }
                                if (isRefresh) {
                                    postList.value = resData
                                } else {
                                    postList.value = postList.value.concat(resData)
                                }
                                hasMore.value = postList.value.length < resTotal
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取我的发布列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            val handleSearch = fun(){
                params.Keywords = keyword.value
                fetchList(true)
            }
            val toggleEditMode = fun(){
                isEditMode.value = !isEditMode.value
                if (!isEditMode.value) {
                    selectedIds.value = _uA()
                }
            }
            val toggleSelect = fun(id: Number){
                val index = selectedIds.value.indexOf(id)
                if (index === -1) {
                    selectedIds.value.push(id)
                } else {
                    selectedIds.value.splice(index, 1)
                }
            }
            val toggleSelectAll = fun(){
                if (isAllSelected.value) {
                    selectedIds.value = _uA()
                } else {
                    selectedIds.value = postList.value.map(fun(item): Number {
                        return item.Id
                    }
                    )
                }
            }
            val confirmDeleteSelected = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(deleteFind(DeleteFindParams(IdArr = selectedIds.value.join(","), IsClear = 0)))
                            uni_showToast(ShowToastOptions(title = "删除成功", icon = "success"))
                            isEditMode.value = false
                            selectedIds.value = _uA()
                            fetchList(true)
                        }
                         catch (e: Throwable) {
                            uni_showToast(ShowToastOptions(title = "删除失败", icon = "none"))
                        }
                })
            }
            val handleDelete = fun(){
                if (selectedIds.value.length === 0) {
                    uni_showToast(ShowToastOptions(title = "请选择要删除的动态", icon = "none"))
                    return
                }
                uni_showModal(ShowModalOptions(title = "提示", content = "确定删除选中的 " + selectedIds.value.length + " 条动态吗？", success = fun(modalRes){
                    if (modalRes.confirm) {
                        confirmDeleteSelected()
                    }
                }
                ))
            }
            val handleClickPost = fun(post: FindBaseResponse){
                if (isEditMode.value) {
                    toggleSelect(post.Id)
                    return
                }
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/community/detail?id=" + post.Id))
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
            onShow(fun(){
                fetchList(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "我的发布")),
                    _cE("view", _uM("class" to "bg-white m-4"), _uA(
                        _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to keyword.value, "onUpdate:modelValue" to fun(`$event`: String){
                            keyword.value = `$event`
                        }
                        , "placeholder" to "请输入关键词搜索", "onSearch" to handleSearch), null, 8, _uA(
                            "modelValue",
                            "onUpdate:modelValue"
                        ))
                    )),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "show-scrollbar" to false, "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to handleRefresh, "onScrolltolower" to handleLoadMore), _uA(
                        if (isTrue(postList.value.length === 0 && !isRefreshing.value)) {
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "title" to "暂无发布", "description" to "快来发布你的第一条动态吧"))
                        } else {
                            _cE(Fragment, _uM("key" to 1), _uA(
                                _cE("view", _uM("class" to "px-4 pb-4"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row justify-between items-center py-4"), _uA(
                                        _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "发布列表"),
                                        _cE("text", _uM("class" to _nC(_uA(
                                            "text-sm",
                                            if (isEditMode.value) {
                                                "text-green-700"
                                            } else {
                                                "text-gray-500"
                                            }
                                        )), "onClick" to toggleEditMode), _tD(if (isEditMode.value) {
                                            "取消"
                                        } else {
                                            "编辑"
                                        }
                                        ), 3)
                                    )),
                                    _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(postList.value, fun(item, __key, __index, _cached): Any {
                                            return _cE("view", _uM("key" to item.Id, "class" to "bg-white rounded-lg p-4 shadow-sm flex flex-row items-center gap-4", "onClick" to fun(){
                                                handleClickPost(item)
                                            }
                                            ), _uA(
                                                if (isTrue(isEditMode.value)) {
                                                    _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("key" to 0, "model-value" to selectedIds.value.includes(item.Id), "active-color" to "#15803d", "onClick" to withModifiers(fun(){
                                                        toggleSelect(item.Id)
                                                    }, _uA(
                                                        "stop"
                                                    ))), null, 8, _uA(
                                                        "model-value",
                                                        "onClick"
                                                    ))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                                ,
                                                _cE("view", _uM("class" to "flex-1"), _uA(
                                                    if (item.ImgList.length > 0) {
                                                        _cE("view", _uM("key" to 0, "class" to "flex flex-row gap-4"), _uA(
                                                            _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to item.ImgList[0], "width" to "100px", "height" to "64px", "radius" to "10px"), null, 8, _uA(
                                                                "src"
                                                            )),
                                                            _cE("view", _uM("class" to "flex-1 flex flex-col justify-between"), _uA(
                                                                _cE("text", _uM("class" to "text-sm text-gray-800 leading-snug lines-3 text-ellipsis overflow-hidden"), _tD(item.ContentDetails), 1),
                                                                _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.AddTime), 1)
                                                            ))
                                                        ))
                                                    } else {
                                                        _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-2"), _uA(
                                                            _cE("text", _uM("class" to "text-sm text-gray-800 leading-snug"), _tD(item.ContentDetails), 1),
                                                            _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.AddTime), 1)
                                                        ))
                                                    }
                                                    ,
                                                    if (item.Status === 1) {
                                                        _cE("view", _uM("key" to 2, "class" to "mt-2"), _uA(
                                                            _cE("text", _uM("class" to "text-xs text-yellow-500"), "审核中")
                                                        ))
                                                    } else {
                                                        if (item.Status === 2) {
                                                            _cE("view", _uM("key" to 3, "class" to "mt-2"), _uA(
                                                                _cE("text", _uM("class" to "text-xs text-red-500"), "审核失败：" + _tD(item.Reason), 1)
                                                            ))
                                                        } else {
                                                            _cC("v-if", true)
                                                        }
                                                    }
                                                ))
                                            ), 8, _uA(
                                                "onClick"
                                            ))
                                        }
                                        ), 128)
                                    ))
                                )),
                                if (postList.value.length > 0) {
                                    _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center py-4"), _uA(
                                        if (isTrue(isLoadingMore.value)) {
                                            _cE("text", _uM("key" to 0, "class" to "text-xs text-gray-400"), "加载中...")
                                        } else {
                                            if (isTrue(!hasMore.value)) {
                                                _cE("text", _uM("key" to 1, "class" to "text-xs text-gray-400"), "没有更多了")
                                            } else {
                                                _cE("text", _uM("key" to 2, "class" to "text-xs text-gray-400"), "上拉加载更多")
                                            }
                                        }
                                    ))
                                } else {
                                    _cC("v-if", true)
                                }
                            ), 64)
                        }
                    ), 40, _uA(
                        "refresher-triggered"
                    )),
                    if (isTrue(isEditMode.value)) {
                        _cE("view", _uM("key" to 0, "class" to "bottom-bar"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2", "onClick" to toggleSelectAll), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("model-value" to isAllSelected.value, "active-color" to "#15803d"), null, 8, _uA(
                                    "model-value"
                                )),
                                _cE("text", _uM("class" to "text-sm text-gray-600"), "全选")
                            )),
                            _cE("view", _uM("class" to _nC(_uA(
                                "delete-btn",
                                if (selectedIds.value.length === 0) {
                                    "delete-btn-disabled"
                                } else {
                                    ""
                                }
                            )), "onClick" to handleDelete), _uA(
                                _cE("text", _uM("class" to "text-white text-sm"), "删除(" + _tD(selectedIds.value.length) + ")", 1)
                            ), 2)
                        ))
                    } else {
                        _cC("v-if", true)
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
                return _uM("bottom-bar" to _pS(_uM("display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between", "alignItems" to "center", "paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "backgroundColor" to "#ffffff", "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#f3f4f6")), "delete-btn" to _pS(_uM("paddingTop" to 8, "paddingRight" to 24, "paddingBottom" to 8, "paddingLeft" to 24, "backgroundColor" to "#ef4444", "borderTopLeftRadius" to 20, "borderTopRightRadius" to 20, "borderBottomRightRadius" to 20, "borderBottomLeftRadius" to 20)), "delete-btn-disabled" to _pS(_uM("backgroundColor" to "#d1d5db")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
