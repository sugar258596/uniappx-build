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
open class GenPagesApplyResumeOnlineComponentsSkillCertificate : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineComponentsSkillCertificate) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineComponentsSkillCertificate
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val showPopup = ref(false)
            watch(showPopup, fun(kVal: Boolean){
                emit("popup-change", kVal)
            }
            )
            val loading = ref(false)
            val loadingMore = ref(false)
            val isFinished = ref(false)
            val isRefreshing = ref(false)
            val certificateOptions = ref(_uA<GetIndustryInfoResult>())
            val queryParams = reactive<GetQualificationCertificateParams>(GetQualificationCertificateParams(Page = 1, PageSize = 20, Id = 0, IsRecommend = 0))
            val selectedIds = ref(_uA<Number>())
            val displayCertificates = computed(fun(): UTSArray<String> {
                if (resumeStore.certificates.length > 0) {
                    return resumeStore.certificates
                }
                return _uA()
            }
            )
            val initSelectionFromStore = fun(){
                val idStr = resumeStore.resumeData.CertificateId ?: ""
                if (idStr != "") {
                    selectedIds.value = idStr.split(",").map(fun(id: String): Number {
                        return parseInt(id)
                    }).filter(fun(id: Number): Boolean {
                        return !isNaN(id)
                    })
                } else {
                    selectedIds.value = _uA()
                }
            }
            val fetchCertificateData = fun(refresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (loading.value || loadingMore.value) {
                            return@w1
                        }
                        if (refresh) {
                            queryParams.Page = 1
                            isFinished.value = false
                            loading.value = !isRefreshing.value
                        } else {
                            if (isFinished.value) {
                                return@w1
                            }
                            loadingMore.value = true
                        }
                        try {
                            val res = await(getQualificationCertificate(queryParams))
                            val rawList = if (res != null) {
                                ((res as UTSJSONObject)["data"] as UTSArray<GetIndustryInfoResult>?)
                            } else {
                                null
                            }
                            val list = if (rawList != null) {
                                rawList
                            } else {
                                _uA<GetIndustryInfoResult>()
                            }
                            if (refresh) {
                                certificateOptions.value = list
                            } else {
                                certificateOptions.value = certificateOptions.value.concat(list)
                            }
                            if (list.length < queryParams.PageSize) {
                                isFinished.value = true
                            } else {
                                queryParams.Page = queryParams.Page + 1
                            }
                        }
                         catch (err: Throwable) {
                            console.error("资格证书加载异常:", err)
                        }
                         finally {
                            loading.value = false
                            loadingMore.value = false
                            isRefreshing.value = false
                        }
                })
            }
            val handleEditClick = fun(){
                showPopup.value = true
                initSelectionFromStore()
                if (certificateOptions.value.length === 0) {
                    fetchCertificateData(true)
                }
            }
            val onRefresherRefresh = fun(){
                isRefreshing.value = true
                fetchCertificateData(true)
            }
            val onScrollToLower = fun(){
                if (!isFinished.value && !loadingMore.value) {
                    fetchCertificateData(false)
                }
            }
            val onToggleItem = fun(item: GetIndustryInfoResult){
                val index = selectedIds.value.indexOf(item.Id)
                if (index > -1) {
                    selectedIds.value.splice(index, 1)
                } else {
                    selectedIds.value.push(item.Id)
                }
            }
            val onConfirmSelection = fun(){
                val selectedNames: UTSArray<String> = _uA()
                certificateOptions.value.forEach(fun(opt){
                    if (selectedIds.value.includes(opt.Id)) {
                        selectedNames.push(opt.Name)
                    }
                }
                )
                val idStr = selectedIds.value.join(",")
                resumeStore.updateCertificates(idStr, selectedNames)
                resumeStore.saveBasicInfo()
                showPopup.value = false
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg p-6"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between mb-4"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                            _cE("view", _uM("class" to "w-1 h-4 bg-teal-600 rounded-full")),
                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "资格证书")
                        )),
                        _cE("view", _uM("class" to "flex items-center justify-center p-1 active:opacity-60", "onClick" to handleEditClick), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-bianji", "size" to 40, "color" to "#999"))
                        ))
                    )),
                    if (displayCertificates.value.length > 0) {
                        _cE("view", _uM("key" to 0, "class" to "flex flex-col"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(displayCertificates.value, fun(cert, index, __index, _cached): Any {
                                return _cE("view", _uM("key" to index, "class" to "py-4 border-b border-gray-50 last:border-0"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-zhengshu", "size" to 32, "color" to "#0d9488")),
                                        _cE("text", _uM("class" to "text-sm text-gray-700"), _tD(cert), 1)
                                    ))
                                ))
                            }), 128)
                        ))
                    } else {
                        _cE("view", _uM("key" to 1, "class" to "py-8 flex flex-col items-center justify-center bg-gray-50 rounded-lg border border-dashed border-gray-200"), _uA(
                            _cE("text", _uM("class" to "text-xs text-gray-400"), "目前还没有添加任何资格证书"),
                            _cE("text", _uM("class" to "text-[10px] text-gray-300 mt-1"), "请点击右上方图标进行添加")
                        ))
                    }
                    ,
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showPopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showPopup.value = `$event`
                    }
                    , "mode" to "bottom", "title" to "资格证书库", "round" to "", "height" to "75%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-slate-50"), _uA(
                                _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to onRefresherRefresh, "onScrolltolower" to onScrollToLower), _uA(
                                    _cE("view", _uM("class" to "px-4 py-4 flex flex-col gap-4"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(certificateOptions.value, fun(item, __key, __index, _cached): Any {
                                            return _cE("view", _uM("key" to item.Id, "class" to "flex flex-row items-center justify-between p-4 bg-white rounded-xl shadow-sm active:bg-teal-50", "onClick" to fun(){
                                                onToggleItem(item)
                                            }
                                            ), _uA(
                                                _cE("view", _uM("class" to "flex-1 flex flex-row items-center gap-4"), _uA(
                                                    _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("model-value" to selectedIds.value.includes(item.Id), "active-color" to "#0d9488"), null, 8, _uA(
                                                        "model-value"
                                                    )),
                                                    _cE("text", _uM("class" to "text-base text-gray-800 font-medium"), _tD(item.Name), 1)
                                                ))
                                            ), 8, _uA(
                                                "onClick"
                                            ))
                                        }
                                        ), 128),
                                        _cE("view", _uM("class" to "py-6 flex flex-row justify-center items-center"), _uA(
                                            if (isTrue(loadingMore.value)) {
                                                _cE("text", _uM("key" to 0, "class" to "text-xs text-slate-400"), "正在努力加载更多...")
                                            } else {
                                                if (isTrue(isFinished.value && certificateOptions.value.length > 0)) {
                                                    _cE("text", _uM("key" to 1, "class" to "text-xs text-slate-300"), "— 已经到底啦 —")
                                                } else {
                                                    if (isTrue(!loading.value && certificateOptions.value.length === 0)) {
                                                        _cE("text", _uM("key" to 2, "class" to "text-xs text-slate-400"), "暂无证书数据")
                                                    } else {
                                                        _cC("v-if", true)
                                                    }
                                                }
                                            }
                                        ))
                                    ))
                                ), 40, _uA(
                                    "refresher-triggered"
                                )),
                                _cE("view", _uM("class" to "p-6 bg-white border-t border-slate-100"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "class" to "confirm-btn", "text" to "开始同步到简历", "size" to "large", "shape" to "round", "block" to "", "onClick" to onConfirmSelection))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
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
                return _uM("confirm-btn" to _pS(_uM("!backgroundColor" to "#0d9488", "!borderTopColor" to "#0d9488", "!borderRightColor" to "#0d9488", "!borderBottomColor" to "#0d9488", "!borderLeftColor" to "#0d9488")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("popup-change" to null)
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
