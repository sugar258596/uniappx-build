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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyResumeExpectation : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeExpectation) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeExpectation
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            val authStore = useAuthStore()
            val addressStore = useAddressStore()
            val hasText = fun(value: Any?): Boolean {
                return value != null && value != ""
            }
            val asText = fun(value: Any?): String {
                return if (value != null) {
                    (value as String)
                } else {
                    ""
                }
            }
            val pageState = reactive<PageState__1>(PageState__1(isEditMode = false, submitting = false, showPositionPicker = false, showCityPicker = false, showIndustryPicker = false, positionName = "", industryName = "", selectedIndustryCode = ""))
            val formData = reactive<AddEditJobIntentionParams>(AddEditJobIntentionParams(JobPositionId = 0, CityCode = "", CityName = "", HighestSalary = "", MinimumSalary = "", JobPositionIndustryId = 0))
            val positionState = reactive<PositionState__1>(PositionState__1(categoryList = _uA<GetAllPositionResult>(), categoryLoading = false, currentCategoryIndex = 0, searchKeyword = "", searchResultList = _uA<GetPositionResult>(), searchPageNum = 1, searchHasMore = true, searchLoading = false, childLoading = false))
            val cityState = reactive<CityState__1>(CityState__1(provinceList = _uA<GetAddressesResult>(), cityList = _uA<GetAddressesResult>(), currentProvinceIndex = 0, provinceLoading = false, cityLoading = false))
            val industryState = reactive<IndustryState__1>(IndustryState__1(list = _uA<GetAllPositionResult>(), currentParentIndex = 0, loading = false))
            val isSearching = computed(fun(): Boolean {
                return positionState.searchKeyword.trim().length > 0
            }
            )
            val currentSubIndustryList = computed(fun(): UTSArray<GetAllPositionResult> {
                val parent = industryState.list[industryState.currentParentIndex]
                if (parent != null && parent.MidChildren != null) {
                    return parent.MidChildren as UTSArray<GetAllPositionResult>
                }
                return _uA()
            }
            )
            val currentCategoryChildren = computed(fun(): UTSArray<GetAllPositionResult> {
                val category = positionState.categoryList[positionState.currentCategoryIndex]
                if (category != null && category.MidChildren != null) {
                    return category.MidChildren as UTSArray<GetAllPositionResult>
                }
                return _uA()
            }
            )
            var searchTimer: Number? = null
            val loadPositionCategories = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (positionState.categoryLoading || positionState.categoryList.length > 0) {
                            return@w1
                        }
                        positionState.categoryLoading = true
                        try {
                            val Fatherdata = (await(getAllPosition(GetAllPositionParams(IndustryType = 0, Id = 0, Page = 1, PageSize = 100, TwoPage = 1, TwoPageSize = 100, ThreePage = 1, ThreePageSize = 100)))).Fatherdata
                            positionState.categoryList = if (Fatherdata != null) {
                                Fatherdata
                            } else {
                                _uA<GetAllPositionResult>()
                            }
                        }
                         catch (_: Throwable) {
                            uni_showToast(ShowToastOptions(title = "加载职位分类失败", icon = "none"))
                        }
                         finally {
                            positionState.categoryLoading = false
                        }
                })
            }
            val fetchPositionSearch = fun(isLoadMore: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        val keyword = positionState.searchKeyword.trim()
                        if (keyword == "") {
                            positionState.searchResultList = _uA()
                            return@w1
                        }
                        if (!isLoadMore) {
                            positionState.searchPageNum = 1
                            positionState.searchResultList = _uA()
                            positionState.searchHasMore = true
                        }
                        if (!positionState.searchHasMore || positionState.searchLoading) {
                            return@w1
                        }
                        positionState.searchLoading = true
                        try {
                            val res = await(getPosition(GetAllPositionParams(IndustryType = 0, Id = 0, Page = positionState.searchPageNum, PageSize = 20, TwoPage = 1, TwoPageSize = 100, ThreePage = 1, ThreePageSize = 100, Keywords = keyword)))
                            val rawList = if (res != null) {
                                ((res as UTSJSONObject)["data"] as UTSArray<GetPositionResult>?)
                            } else {
                                null
                            }
                            val rawTotal = if (res != null) {
                                ((res as UTSJSONObject)["total"] as Number?)
                            } else {
                                null
                            }
                            val list = if (rawList != null) {
                                rawList
                            } else {
                                _uA<GetPositionResult>()
                            }
                            val total = if (rawTotal != null) {
                                rawTotal
                            } else {
                                0
                            }
                            if (isLoadMore) {
                                positionState.searchResultList = positionState.searchResultList.concat(list)
                            } else {
                                positionState.searchResultList = list
                            }
                            positionState.searchHasMore = positionState.searchResultList.length < total && list.length > 0
                            if (positionState.searchHasMore) {
                                positionState.searchPageNum = positionState.searchPageNum + 1
                            }
                        }
                         catch (_: Throwable) {
                            uni_showToast(ShowToastOptions(title = "搜索失败", icon = "none"))
                        }
                         finally {
                            positionState.searchLoading = false
                        }
                })
            }
            val handlePositionSearch = fun(isLoadMore: Boolean){
                val timer = searchTimer
                if (timer != null) {
                    clearTimeout(timer)
                }
                if (isLoadMore) {
                    fetchPositionSearch(true)
                } else {
                    searchTimer = setTimeout(fun(){
                        fetchPositionSearch(false)
                    }
                    , 300)
                }
            }
            val handlePositionClick = fun(){
                pageState.showPositionPicker = true
                loadPositionCategories()
            }
            val handleCategoryClick = fun(index: Number){
                positionState.currentCategoryIndex = index
                positionState.searchKeyword = ""
            }
            val handlePositionSelect = fun(item: GetPositionResult){
                val id = item.Id
                val name = item.PositionName
                if (id > 0) {
                    pageState.positionName = name
                    formData.JobPositionId = id
                    pageState.showPositionPicker = false
                }
            }
            val loadCityList = fun(provinceCode: String): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        cityState.cityLoading = true
                        try {
                            val res = await(addressStore.getDistrictOptions("City", provinceCode))
                            if (res != null) {
                                cityState.cityList = res.map(fun(item: AddressOption): GetAddressesResult {
                                    return (GetAddressesResult(Name = item.label, Code = item.value))
                                }
                                )
                            }
                        }
                         catch (_: Throwable) {
                            uni_showToast(ShowToastOptions(title = "加载城市失败", icon = "none"))
                        }
                         finally {
                            cityState.cityLoading = false
                        }
                })
            }
            val loadProvinceList = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (cityState.provinceLoading || cityState.provinceList.length > 0) {
                            return@w1
                        }
                        cityState.provinceLoading = true
                        try {
                            val res = await(addressStore.getDistrictOptions("Province", null))
                            if (res != null && res.length > 0) {
                                cityState.provinceList = res.map(fun(item: AddressOption): GetAddressesResult {
                                    return (GetAddressesResult(Name = item.label, Code = item.value))
                                }
                                )
                                cityState.currentProvinceIndex = 0
                                loadCityList(res[0].value)
                            }
                        }
                         catch (_: Throwable) {
                            uni_showToast(ShowToastOptions(title = "加载省份失败", icon = "none"))
                        }
                         finally {
                            cityState.provinceLoading = false
                        }
                })
            }
            val handleCityClick = fun(){
                pageState.showCityPicker = true
                loadProvinceList()
            }
            val handleProvinceClick = fun(index: Number){
                cityState.currentProvinceIndex = index
                val province = cityState.provinceList[index]
                if (province != null) {
                    loadCityList(province.Code ?: "")
                }
            }
            val handleCitySelect = fun(item: GetAddressesResult){
                formData.CityCode = item.Code ?: ""
                formData.CityName = item.Name ?: ""
                pageState.showCityPicker = false
            }
            val loadIndustryList = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (industryState.loading || industryState.list.length > 0) {
                            return@w1
                        }
                        industryState.loading = true
                        try {
                            val Fatherdata = (await(getAllPosition(GetAllPositionParams(IndustryType = 1, Id = 0, Page = 1, PageSize = 100, TwoPage = 1, TwoPageSize = 100, ThreePage = 1, ThreePageSize = 100)))).Fatherdata
                            if (Fatherdata != null) {
                                industryState.list = Fatherdata
                                industryState.currentParentIndex = 0
                            }
                        }
                         catch (_: Throwable) {
                            uni_showToast(ShowToastOptions(title = "加载行业失败", icon = "none"))
                        }
                         finally {
                            industryState.loading = false
                        }
                })
            }
            val handleIndustryClick = fun(){
                pageState.showIndustryPicker = true
                loadIndustryList()
            }
            val handleParentIndustryClick = fun(index: Number){
                industryState.currentParentIndex = index
            }
            val handleIndustrySelect = fun(item: GetAllPositionResult){
                val targetId = item.Id
                val targetName = item.PositionName
                pageState.selectedIndustryCode = targetId.toString(10)
                val parent = industryState.list[industryState.currentParentIndex]
                val parentName = asText(parent.PositionName)
                pageState.industryName = if (hasText(parentName)) {
                    "" + parentName + "/" + targetName
                } else {
                    targetName
                }
                formData.JobPositionIndustryId = targetId
                pageState.showIndustryPicker = false
            }
            val getMinChildren = fun(item: GetAllPositionResult): UTSArray<GetPositionResult> {
                return if (item.MinChildren != null) {
                    (item.MinChildren as UTSArray<GetPositionResult>)
                } else {
                    _uA<GetPositionResult>()
                }
            }
            val handleOperationSuccess = fun(message: String): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        uni_showToast(ShowToastOptions(title = message, icon = "success"))
                        val onlineId = authStore.userInfo?.OnlineId ?: 0
                        if (onlineId > 0) {
                            await(resumeStore.fetchResume(onlineId))
                        }
                        setTimeout(fun(){
                            uni_navigateBack(null)
                        }
                        , 1500)
                })
            }
            val handleSubmit = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (!hasText(pageState.positionName)) {
                            return@w1 uni_showToast(ShowToastOptions(title = "请选择期望职位", icon = "none"))
                        }
                        if (!hasText(formData.CityName)) {
                            return@w1 uni_showToast(ShowToastOptions(title = "请选择工作城市", icon = "none"))
                        }
                        if (!hasText(formData.MinimumSalary) || !hasText(formData.HighestSalary)) {
                            return@w1 uni_showToast(ShowToastOptions(title = "请填写薪资要求", icon = "none"))
                        }
                        if (!hasText(pageState.industryName)) {
                            return@w1 uni_showToast(ShowToastOptions(title = "请选择期望行业", icon = "none"))
                        }
                        val minValue = parseInt(formData.MinimumSalary)
                        val maxValue = parseInt(formData.HighestSalary)
                        if (isNaN(minValue) || isNaN(maxValue)) {
                            return@w1 uni_showToast(ShowToastOptions(title = "请输入有效的薪资数值", icon = "none"))
                        }
                        if (minValue > maxValue) {
                            return@w1 uni_showToast(ShowToastOptions(title = "最低薪资不能大于最高薪资", icon = "none"))
                        }
                        pageState.submitting = true
                        try {
                            await(addEditJobIntention(formData))
                            await(handleOperationSuccess("保存成功"))
                        }
                         catch (err: Throwable) {
                            uni_showToast(ShowToastOptions(title = if (err is UTSError) {
                                (err as UTSError).message
                            } else {
                                "保存失败"
                            }
                            , icon = "none"))
                        }
                         finally {
                            pageState.submitting = false
                        }
                })
            }
            val confirmDelete = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(deleteJobIntention(DeleteJobIntentionParams(PurposeId = formData.PurposeId!!)))
                            await(handleOperationSuccess("删除成功"))
                        }
                         catch (err: Throwable) {
                            uni_showToast(ShowToastOptions(title = if (err is UTSError) {
                                (err as UTSError).message
                            } else {
                                "删除失败"
                            }
                            , icon = "none"))
                        }
                })
            }
            val handleDelete = fun(){
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要删除这条求职期望吗？", confirmColor = "#057a55", success = fun(res){
                    if (!res.confirm) {
                        return
                    }
                    confirmDelete()
                }
                ))
            }
            onLoad(fun(options: UTSJSONObject){
                val idStr = options.get("id") as String?
                if (idStr != null) {
                    val id = parseInt(idStr)
                    if (id > 0) {
                        pageState.isEditMode = true
                        formData.PurposeId = id
                        val editing = resumeStore.editingJobIntention
                        formData.JobPositionId = editing.JobPositionId ?: 0
                        formData.CityCode = editing.CityCode ?: ""
                        formData.CityName = editing.CityName ?: ""
                        formData.HighestSalary = editing.HighestSalary ?: ""
                        formData.MinimumSalary = editing.MinimumSalary ?: ""
                        formData.JobPositionIndustryId = editing.JobPositionIndustryId ?: 0
                        pageState.positionName = editing.JobPositionName ?: ""
                        pageState.industryName = editing.JobPositionIndustryName ?: ""
                        pageState.selectedIndustryCode = (editing.JobPositionIndustryId ?: 0).toString(10)
                    }
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-col h-full user-header"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to " ", "background-color" to "transparent", "color" to "#000")),
                    _cE("view", _uM("class" to "px-6 pt-2 pb-12"), _uA(
                        _cE("text", _uM("class" to "text-2xl font-bold text-gray-900 mb-2 block"), _tD(if (pageState.isEditMode) {
                            "修改求职期望"
                        } else {
                            "添加求职期望"
                        }
                        ), 1),
                        _cE("text", _uM("class" to "text-sm text-gray-500"), "推荐合适岗位，求职更高效")
                    )),
                    _cE("view", _uM("class" to "flex-1 px-4 -mt-6"), _uA(
                        _cE("view", _uM("class" to "flex flex-col bg-white rounded-lg p-4 shadow-sm"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between py-6 border-b border-gray-100", "onClick" to handlePositionClick), _uA(
                                _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                    _cE("text", _uM("class" to "text-base text-gray-800 font-medium"), "期望职位"),
                                    if (isTrue(!hasText(pageState.positionName))) {
                                        _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择期望职位")
                                    } else {
                                        _cE("text", _uM("key" to 1, "class" to "text-base text-gray-900 font-medium"), _tD(pageState.positionName), 1)
                                    }
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#999"))
                            )),
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between py-6 border-b border-gray-100", "onClick" to handleCityClick), _uA(
                                _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                    _cE("text", _uM("class" to "text-base text-gray-800 font-medium"), "工作城市"),
                                    if (isTrue(!hasText(formData.CityName))) {
                                        _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择工作城市")
                                    } else {
                                        _cE("text", _uM("key" to 1, "class" to "text-base text-gray-900 font-medium"), _tD(formData.CityName), 1)
                                    }
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#999"))
                            )),
                            _cE("view", _uM("class" to "flex flex-row items-center py-6 border-b border-gray-100"), _uA(
                                _cE("text", _uM("class" to "text-base text-gray-800 font-medium mr-4"), "薪资要求"),
                                _cE("view", _uM("class" to "flex-1 flex flex-row items-center gap-2"), _uA(
                                    _cE("input", _uM("modelValue" to formData.MinimumSalary, "onInput" to fun(`$event`: UniInputEvent){
                                        formData.MinimumSalary = `$event`.detail.value
                                    }
                                    , "type" to "number", "class" to "flex-1 text-center text-base text-gray-900 bg-gray-50 rounded-lg py-2", "placeholder" to "最低薪资", "placeholder-class" to "text-gray-400"), null, 40, _uA(
                                        "modelValue",
                                        "onInput"
                                    )),
                                    _cE("text", _uM("class" to "text-gray-400"), "-"),
                                    _cE("input", _uM("modelValue" to formData.HighestSalary, "onInput" to fun(`$event`: UniInputEvent){
                                        formData.HighestSalary = `$event`.detail.value
                                    }
                                    , "type" to "number", "class" to "flex-1 text-center text-base text-gray-900 bg-gray-50 rounded-lg py-2", "placeholder" to "最高薪资", "placeholder-class" to "text-gray-400"), null, 40, _uA(
                                        "modelValue",
                                        "onInput"
                                    ))
                                ))
                            )),
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between py-6", "onClick" to handleIndustryClick), _uA(
                                _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                    _cE("text", _uM("class" to "text-base text-gray-800 font-medium"), "期望行业"),
                                    if (isTrue(!hasText(pageState.industryName))) {
                                        _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择期望行业")
                                    } else {
                                        _cE("text", _uM("key" to 1, "class" to "text-base text-gray-900 font-medium"), _tD(pageState.industryName), 1)
                                    }
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#999"))
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "p-6 pb-10 bg-white"), _uA(
                        if (isTrue(pageState.isEditMode)) {
                            _cE("view", _uM("key" to 0, "class" to "flex flex-row gap-4"), _uA(
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "default", "text" to "删除", "size" to "large", "shape" to "round", "block" to "", "onClick" to handleDelete))
                                )),
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "loading" to pageState.submitting, "custom-class" to "t-default", "style" to _nS(_uM("backgroundColor" to "#057a55", "borderColor" to "#057a55")), "onClick" to handleSubmit), null, 8, _uA(
                                        "loading",
                                        "style"
                                    ))
                                ))
                            ))
                        } else {
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 1, "type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "loading" to pageState.submitting, "custom-class" to "t-default", "style" to _nS(_uM("backgroundColor" to "#057a55", "borderColor" to "#057a55")), "onClick" to handleSubmit), null, 8, _uA(
                                "loading",
                                "style"
                            ))
                        }
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to pageState.showPositionPicker, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        pageState.showPositionPicker = `$event`
                    }
                    , "mode" to "bottom", "height" to "85%", "round" to "", "show-title" to false), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-white"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center p-4 border-b border-gray-100 gap-4"), _uA(
                                    _cE("view", _uM("class" to "flex-1 flex flex-row items-center bg-gray-50 rounded-full px-4 py-2"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-sousuo", "size" to 20, "color" to "#999")),
                                        _cE("input", _uM("modelValue" to positionState.searchKeyword, "onInput" to _uA<Any?>(fun(`$event`: UniInputEvent){
                                            positionState.searchKeyword = `$event`.detail.value
                                        }
                                        , fun(){
                                            handlePositionSearch(false)
                                        }
                                        ), "class" to "flex-1 ml-2 text-sm text-gray-800", "placeholder" to "请输入关键词搜索", "placeholder-class" to "text-gray-400", "onConfirm" to fun(){
                                            handlePositionSearch(false)
                                        }
                                        ), null, 40, _uA(
                                            "modelValue",
                                            "onInput",
                                            "onConfirm"
                                        ))
                                    )),
                                    _cE("text", _uM("class" to "text-base text-teal-700 font-medium", "onClick" to fun(){
                                        handlePositionSearch(false)
                                    }
                                    ), "搜索", 8, _uA(
                                        "onClick"
                                    ))
                                )),
                                _cE("view", _uM("class" to "flex-1 flex flex-row overflow-hidden"), _uA(
                                    if (isTrue(!isSearching.value)) {
                                        _cE(Fragment, _uM("key" to 0), _uA(
                                            _cE("scroll-view", _uM("scroll-y" to "", "class" to "w-60 bg-gray-50 h-full"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(positionState.categoryList, fun(category, index, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                                        "py-4 px-4 flex justify-center relative",
                                                        _uM("bg-white" to (positionState.currentCategoryIndex === index))
                                                    )), "onClick" to fun(){
                                                        handleCategoryClick(index)
                                                    }), _uA(
                                                        if (positionState.currentCategoryIndex === index) {
                                                            _cE("view", _uM("key" to 0, "class" to "absolute left-0 top-4 bottom-4 w-1 bg-teal-600 rounded-r"))
                                                        } else {
                                                            _cC("v-if", true)
                                                        },
                                                        _cE("text", _uM("class" to _nC(_uA(
                                                            "text-sm",
                                                            if (positionState.currentCategoryIndex === index) {
                                                                "text-teal-700 font-medium"
                                                            } else {
                                                                "text-gray-600"
                                                            }
                                                        ))), _tD(category.PositionName), 3)
                                                    ), 10, _uA(
                                                        "onClick"
                                                    ))
                                                }), 128)
                                            )),
                                            _cE("scroll-view", _uM("scroll-y" to "", "class" to "flex-1 bg-white h-full p-4"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(currentCategoryChildren.value, fun(level2, index, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to index), _uA(
                                                        _cE("text", _uM("class" to "text-sm font-bold text-gray-800 mb-4 block mt-2"), _tD(level2.PositionName), 1),
                                                        _cE("view", _uM("class" to "flex flex-row flex-wrap gap-4 mb-4"), _uA(
                                                            _cE(Fragment, null, RenderHelpers.renderList(getMinChildren(level2), fun(level3, childIndex, __index, _cached): Any {
                                                                return _cE("view", _uM("key" to childIndex, "class" to _nC(_uA(
                                                                    "px-4 py-2 rounded-full mb-2",
                                                                    if (level3.Id === formData.JobPositionId) {
                                                                        "bg-teal-50"
                                                                    } else {
                                                                        "bg-gray-50"
                                                                    }
                                                                )), "onClick" to fun(){
                                                                    handlePositionSelect(level3)
                                                                }), _uA(
                                                                    _cE("text", _uM("class" to _nC(_uA(
                                                                        "text-sm",
                                                                        if (level3.Id === formData.JobPositionId) {
                                                                            "text-teal-700 font-medium"
                                                                        } else {
                                                                            "text-gray-600"
                                                                        }
                                                                    ))), _tD(level3.PositionName), 3)
                                                                ), 10, _uA(
                                                                    "onClick"
                                                                ))
                                                            }), 128)
                                                        ))
                                                    ))
                                                }), 128),
                                                if (currentCategoryChildren.value.length === 0) {
                                                    _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-10"), _uA(
                                                        _cE("text", _uM("class" to "text-gray-400 text-sm"), "暂无相关职位")
                                                    ))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                            ))
                                        ), 64)
                                    } else {
                                        _cE("scroll-view", _uM("key" to 1, "scroll-y" to "", "class" to "flex-1 bg-white h-full px-4", "onScrolltolower" to fun(){
                                            handlePositionSearch(true)
                                        }
                                        ), _uA(
                                            _cE("view", _uM("class" to "flex flex-col"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(positionState.searchResultList, fun(item, index, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to index, "class" to "py-4 border-b border-gray-100 flex flex-row items-center justify-between", "onClick" to fun(){
                                                        handlePositionSelect(item)
                                                    }
                                                    ), _uA(
                                                        _cE("view", _uM("class" to "flex flex-col flex-1 pr-4"), _uA(
                                                            _cE("text", _uM("class" to _nC(_uA(
                                                                "text-base",
                                                                if (item.Id === formData.JobPositionId) {
                                                                    "text-teal-700 font-medium"
                                                                } else {
                                                                    "text-gray-800"
                                                                }
                                                            ))), _tD(item.PositionName), 3),
                                                            if (item.RePositionName != "") {
                                                                _cE("text", _uM("key" to 0, "class" to "text-xs text-gray-400 mt-1"), _tD(item.RePositionName), 1)
                                                            } else {
                                                                _cC("v-if", true)
                                                            }
                                                        )),
                                                        if (item.Id === formData.JobPositionId) {
                                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("key" to 0, "name" to "icon-xuanze", "color" to "#0d9488", "size" to 20))
                                                        } else {
                                                            _cC("v-if", true)
                                                        }
                                                    ), 8, _uA(
                                                        "onClick"
                                                    ))
                                                }
                                                ), 128)
                                            )),
                                            if (isTrue(positionState.searchResultList.length === 0 && !positionState.searchLoading)) {
                                                _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-10"), _uA(
                                                    _cE("text", _uM("class" to "text-gray-400 text-sm"), "暂无搜索结果")
                                                ))
                                            } else {
                                                _cC("v-if", true)
                                            }
                                            ,
                                            if (isTrue(positionState.searchLoading)) {
                                                _cE("view", _uM("key" to 1, "class" to "flex items-center justify-center py-4"), _uA(
                                                    _cE("text", _uM("class" to "text-gray-400 text-xs"), "加载中...")
                                                ))
                                            } else {
                                                if (isTrue(!positionState.searchHasMore && positionState.searchResultList.length > 0)) {
                                                    _cE("view", _uM("key" to 2, "class" to "flex items-center justify-center py-4"), _uA(
                                                        _cE("text", _uM("class" to "text-gray-400 text-xs"), "没有更多数据了")
                                                    ))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                            }
                                        ), 40, _uA(
                                            "onScrolltolower"
                                        ))
                                    }
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to pageState.showCityPicker, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        pageState.showCityPicker = `$event`
                    }
                    , "mode" to "bottom", "height" to "70%", "round" to "", "show-title" to false), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-white"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center justify-center p-4 border-b border-gray-100"), _uA(
                                    _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "选择工作城市")
                                )),
                                _cE("view", _uM("class" to "flex-1 flex flex-row overflow-hidden"), _uA(
                                    _cE("scroll-view", _uM("scroll-y" to "", "class" to "w-60 bg-gray-50 h-full"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(cityState.provinceList, fun(province, index, __index, _cached): Any {
                                            return _cE("view", _uM("key" to province.Code, "class" to _nC(_uA(
                                                "py-4 px-4 flex justify-center relative",
                                                _uM("bg-white" to (cityState.currentProvinceIndex === index))
                                            )), "onClick" to fun(){
                                                handleProvinceClick(index)
                                            }
                                            ), _uA(
                                                if (cityState.currentProvinceIndex === index) {
                                                    _cE("view", _uM("key" to 0, "class" to "absolute left-0 top-4 bottom-4 w-1 bg-teal-600 rounded-r"))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                                ,
                                                _cE("text", _uM("class" to _nC(_uA(
                                                    "text-sm",
                                                    if (cityState.currentProvinceIndex === index) {
                                                        "text-teal-700 font-medium"
                                                    } else {
                                                        "text-gray-600"
                                                    }
                                                ))), _tD(province.Name), 3)
                                            ), 10, _uA(
                                                "onClick"
                                            ))
                                        }
                                        ), 128)
                                    )),
                                    _cE("scroll-view", _uM("scroll-y" to "", "class" to "flex-1 bg-white h-full p-4"), _uA(
                                        _cE("view", _uM("class" to "flex flex-row flex-wrap gap-4"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(cityState.cityList, fun(city, __key, __index, _cached): Any {
                                                return _cE("view", _uM("key" to city.Code, "class" to _nC(_uA(
                                                    "px-4 py-2 rounded-full mb-2",
                                                    if ((city.Code ?: "") === formData.CityCode) {
                                                        "bg-teal-50"
                                                    } else {
                                                        "bg-gray-50"
                                                    }
                                                )), "onClick" to fun(){
                                                    handleCitySelect(city)
                                                }
                                                ), _uA(
                                                    _cE("text", _uM("class" to _nC(_uA(
                                                        "text-sm",
                                                        if ((city.Code ?: "") === formData.CityCode) {
                                                            "text-teal-700 font-medium"
                                                        } else {
                                                            "text-gray-700"
                                                        }
                                                    ))), _tD(city.Name), 3)
                                                ), 10, _uA(
                                                    "onClick"
                                                ))
                                            }
                                            ), 128)
                                        )),
                                        if (cityState.cityList.length === 0) {
                                            _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-10"), _uA(
                                                _cE("text", _uM("class" to "text-gray-400 text-sm"), "暂无城市数据")
                                            ))
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    ))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to pageState.showIndustryPicker, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        pageState.showIndustryPicker = `$event`
                    }
                    , "mode" to "bottom", "height" to "70%", "round" to "", "show-title" to false), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-white"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center justify-center p-4 border-b border-gray-100"), _uA(
                                    _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "选择期望行业")
                                )),
                                _cE("view", _uM("class" to "flex-1 flex flex-row overflow-hidden"), _uA(
                                    _cE("scroll-view", _uM("scroll-y" to "", "class" to "w-60 bg-gray-50 h-full"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(industryState.list, fun(item, index, __index, _cached): Any {
                                            return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                                "py-4 px-4 flex justify-center relative",
                                                _uM("bg-white" to (industryState.currentParentIndex === index))
                                            )), "onClick" to fun(){
                                                handleParentIndustryClick(index)
                                            }
                                            ), _uA(
                                                if (industryState.currentParentIndex === index) {
                                                    _cE("view", _uM("key" to 0, "class" to "absolute left-0 top-4 bottom-4 w-1 bg-teal-600 rounded-r"))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                                ,
                                                _cE("text", _uM("class" to _nC(_uA(
                                                    "text-sm",
                                                    if (industryState.currentParentIndex === index) {
                                                        "text-teal-700 font-medium"
                                                    } else {
                                                        "text-gray-600"
                                                    }
                                                ))), _tD(item.PositionName), 3)
                                            ), 10, _uA(
                                                "onClick"
                                            ))
                                        }
                                        ), 128)
                                    )),
                                    _cE("scroll-view", _uM("scroll-y" to "", "class" to "flex-1 bg-white h-full p-4"), _uA(
                                        _cE("view", _uM("class" to "flex flex-row flex-wrap gap-4"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(currentSubIndustryList.value, fun(sub, __key, __index, _cached): Any {
                                                return _cE("view", _uM("key" to sub.Id, "class" to _nC(_uA(
                                                    "px-4 py-2 rounded-full mb-2",
                                                    if (sub.Id.toString(10) === pageState.selectedIndustryCode) {
                                                        "bg-teal-50"
                                                    } else {
                                                        "bg-gray-50"
                                                    }
                                                )), "onClick" to fun(){
                                                    handleIndustrySelect(sub)
                                                }
                                                ), _uA(
                                                    _cE("text", _uM("class" to _nC(_uA(
                                                        "text-sm",
                                                        if (sub.Id.toString(10) === pageState.selectedIndustryCode) {
                                                            "text-teal-700 font-medium"
                                                        } else {
                                                            "text-gray-700"
                                                        }
                                                    ))), _tD(sub.PositionName), 3)
                                                ), 10, _uA(
                                                    "onClick"
                                                ))
                                            }
                                            ), 128)
                                        ))
                                    ))
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
                return _uM("user-header" to _pS(_uM("backgroundImage" to "linear-gradient(to bottom, #bef3d0ff 0%, #fff 40%, #fff 100%)", "backgroundColor" to "rgba(0,0,0,0)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
