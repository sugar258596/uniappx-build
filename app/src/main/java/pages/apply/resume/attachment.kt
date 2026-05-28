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
import io.dcloud.uniapp.extapi.showActionSheet as uni_showActionSheet
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyResumeAttachment : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeAttachment) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeAttachment
            val _cache = __ins.renderCache
            val resumeList = ref(_uA<NearbyListRults>())
            val params = reactive<Pagination>(Pagination(Page = 1, PageSize = 10))
            val isRefreshing = ref(false)
            val isLoading = ref(false)
            val hasMore = ref(true)
            val fetchData = fun(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoading.value) {
                            return@w1
                        }
                        if (!isRefresh && !hasMore.value) {
                            return@w1
                        }
                        if (isRefresh) {
                            params.Page = 1
                            hasMore.value = true
                        } else {
                            params.Page = params.Page + 1
                        }
                        isLoading.value = true
                        try {
                            val res = await(getNearbyList(params))
                            val rawData = if (res != null) {
                                ((res as UTSJSONObject)["data"] as UTSArray<NearbyListRults>?)
                            } else {
                                null
                            }
                            val rawTotal = if (res != null) {
                                ((res as UTSJSONObject)["total"] as Number?)
                            } else {
                                null
                            }
                            val data = if (rawData != null) {
                                rawData
                            } else {
                                _uA<NearbyListRults>()
                            }
                            val total = if (rawTotal != null) {
                                rawTotal
                            } else {
                                0
                            }
                            if (data != null) {
                                if (isRefresh) {
                                    resumeList.value = data
                                } else {
                                    resumeList.value = resumeList.value.concat(data)
                                }
                                hasMore.value = resumeList.value.length < total
                            } else {
                                hasMore.value = false
                            }
                        }
                         catch (error: Throwable) {
                            console.error("获取附件失败", error)
                            if (!isRefresh && params.Page > 1) {
                                params.Page = params.Page - 1
                            }
                        }
                         finally {
                            isLoading.value = false
                            if (isRefresh) {
                                isRefreshing.value = false
                            }
                        }
                })
            }
            val onRefresh = fun(){
                isRefreshing.value = true
                fetchData(true)
            }
            val onLoadMore = fun(){
                if (isLoading.value || !hasMore.value) {
                    return
                }
                fetchData(false)
            }
            val confirmRenameFile = fun(item: NearbyListRults, newName: String): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (newName == "" || item.Id == null) {
                            return@w1
                        }
                        try {
                            uni_showLoading(ShowLoadingOptions(title = "修改中..."))
                            await(renameNearby(RenameNearbyParams(Id = item.Id as Number, FileName = newName)))
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "修改成功", icon = "success"))
                            fetchData(true)
                        }
                         catch (_error: Throwable) {
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "修改失败", icon = "none"))
                        }
                })
            }
            val renameFile = fun(item: NearbyListRults){
                uni_showModal(ShowModalOptions(title = "文件重命名", editable = true, placeholderText = "请输入新文件名", content = item.PicName ?: "", success = fun(res){
                    if (res.confirm) {
                        val content = (res as UTSJSONObject)["content"] as String?
                        if (content != null) {
                            confirmRenameFile(item, content)
                        }
                    }
                }
                ))
            }
            val deleteFile = fun(item: NearbyListRults): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (item.Id == null) {
                            return@w1
                        }
                        try {
                            uni_showLoading(ShowLoadingOptions(title = "删除中..."))
                            await(deleteNearby(DeleteNearbyParams(IdArr = item.Id!!.toString(10), IsClear = 0)))
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "已删除", icon = "success"))
                            fetchData(true)
                        }
                         catch (_error: Throwable) {
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "删除失败", icon = "none"))
                        }
                })
            }
            val handleMoreClick = fun(item: NearbyListRults){
                uni_showActionSheet(ShowActionSheetOptions(itemList = _uA(
                    "重命名",
                    "发送至邮箱",
                    "删除"
                ), itemColor = "#000000", success = fun(res){
                    if (res.tapIndex === 0) {
                        renameFile(item)
                    } else if (res.tapIndex === 1) {
                        uni_showToast(ShowToastOptions(title = "发送至邮箱功能开发中", icon = "none"))
                    } else if (res.tapIndex === 2) {
                        uni_showModal(ShowModalOptions(title = "确认删除", content = "确定要删除该附件吗？", success = fun(modalRes){
                            if (modalRes.confirm) {
                                deleteFile(item)
                            }
                        }
                        ))
                    }
                }
                ))
            }
            val handleAddClick = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/resume/upload-attachment"))
            }
            val handleItemClick = fun(item: NearbyListRults){
                uni_showToast(ShowToastOptions(title = "打开文件: " + (item.PicName ?: "未知文件"), icon = "none"))
            }
            val isPdf = fun(name: Any?): Boolean {
                if (name == null || name == "") {
                    return false
                }
                val fileName = name as String
                val length = fileName.length
                if (length < 4) {
                    return false
                }
                val suffix = fileName.substring(length - 4, length)
                return suffix == ".pdf" || suffix == ".PDF"
            }
            onShow(fun(){
                fetchData(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "附件简历")),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to true, "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to onRefresh, "onScrolltolower" to onLoadMore), _uA(
                        _cE("view", _uM("class" to "flex flex-col mt-2 px-4"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(resumeList.value, fun(item, index, __index, _cached): Any {
                                return _cE("view", _uM("key" to index, "class" to "bg-white p-4 border-b border-gray-100 flex flex-row items-center gap-4 rounded-lg", "onClick" to fun(){
                                    handleItemClick(item)
                                }
                                ), _uA(
                                    _cE("view", _uM("class" to "w-12 h-12 flex items-center justify-center bg-gray-50 rounded"), _uA(
                                        if (isTrue(isPdf(item.PicName))) {
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("key" to 0, "name" to "icon-pdf", "color" to "#ef4444"))
                                        } else {
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("key" to 1, "name" to "icon-cuowuwenjian", "color" to "#9ca3af"))
                                        }
                                    )),
                                    _cE("view", _uM("class" to "flex-1 flex flex-col gap-2"), _uA(
                                        _cE("text", _uM("class" to "text-base font-medium text-gray-800"), _tD(item.PicName ?: "未命名附件"), 1),
                                        _cE("text", _uM("class" to "text-xs text-gray-500"), "更新于 " + _tD(item.AddTime ?: ""), 1)
                                    )),
                                    _cE("view", _uM("class" to "p-2", "onClick" to withModifiers(fun(){
                                        handleMoreClick(item)
                                    }
                                    , _uA(
                                        "stop"
                                    ))), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-gengduo", "size" to 24, "color" to "#6b7280"))
                                    ), 8, _uA(
                                        "onClick"
                                    ))
                                ), 8, _uA(
                                    "onClick"
                                ))
                            }
                            ), 128),
                            if (isTrue(resumeList.value.length === 0 && !isLoading.value)) {
                                _cE("view", _uM("key" to 0, "class" to "py-20"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("title" to "暂无附件", "description" to "可以点击下方添加附件"))
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                            ,
                            if (resumeList.value.length > 0) {
                                _cE("view", _uM("key" to 1, "class" to "flex justify-center items-center py-4"), _uA(
                                    _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(if (isLoading.value) {
                                        "加载中..."
                                    } else {
                                        if (hasMore.value) {
                                            "上拉加载更多"
                                        } else {
                                            "没有更多了"
                                        }
                                    }), 1)
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                        ))
                    ), 40, _uA(
                        "refresher-triggered"
                    )),
                    _cE("view", _uM("class" to "bg-white p-4 pb-8 shadow-top"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "shape" to "round", "size" to "large", "block" to "", "custom-class" to "t-default ", "text" to "添加附件", "onClick" to handleAddClick))
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
                return _uM("shadow-top" to _pS(_uM("boxShadow" to "0 -2px 10px rgba(0, 0, 0, 0.05)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
