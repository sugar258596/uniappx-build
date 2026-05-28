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
open class GenPagesHireTabbarHomeIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarHomeIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarHomeIndex
            val _cache = __ins.renderCache
            val locationStore = useLocationStore()
            val addressStore = useAddressStore()
            val rightVisible = ref(false)
            val keywords = ref("")
            val resumeList = ref(_uA<GetOnlineResumeResult>())
            val loading = ref(false)
            val refresherTriggered = ref(false)
            val total = ref(0)
            val pageParams = reactive<PageParams>(PageParams(Page = 1, PageSize = 10, myType = 1, Keywords = "", Education = "", HelpType = "", Sex = 0, MinAge = 0, MaxAge = 100, MinExperience = 0, MaxExperience = 50))
            val regionVisible = ref(false)
            val activeProvinceCode = ref("")
            val activeProvinceName = ref("")
            val activeCityCode = ref("")
            val activeCityName = ref("")
            val selectedDistrictCodes = ref(_uA<String>())
            val provinceLoading = ref(false)
            val cityLoading = ref(false)
            val districtLoading = ref(false)
            val provinceList = ref(_uA<AddressOption>())
            val cityList = ref(_uA<AddressOption>())
            val districtList = ref(_uA<AddressOption>())
            val hasText = fun(value: Any?): Boolean {
                return value != null && value != ""
            }
            val getTextOrFallback = fun(value: Any?, fallback: String): String {
                return if (hasText(value)) {
                    value!! as String
                } else {
                    fallback
                }
            }
            val selectedCount = computed(fun(): Number {
                return selectedDistrictCodes.value.length
            }
            )
            val isAllSelected = computed(fun(): Boolean {
                if (districtList.value.length === 0) {
                    return false
                }
                return selectedDistrictCodes.value.length === districtList.value.length
            }
            )
            val selectedDistricts = computed(fun(): UTSArray<AddressOption> {
                val list: UTSArray<AddressOption> = districtList.value
                return list.filter(fun(item: AddressOption): Boolean {
                    return selectedDistrictCodes.value.includes(item.value)
                }
                )
            }
            )
            fun gen_loadDistrictList_fn(cityCode: String): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (cityCode == "") {
                            return@w1
                        }
                        try {
                            districtLoading.value = true
                            val res = await(addressStore.getDistrictOptions("District", cityCode))
                            districtList.value = res
                        }
                         catch (err: Throwable) {
                            console.error("加载区域列表失败:", err)
                        }
                         finally {
                            districtLoading.value = false
                        }
                })
            }
            val loadDistrictList = ::gen_loadDistrictList_fn
            fun gen_loadCityList_fn(provinceCode: String): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (provinceCode == "") {
                            return@w1
                        }
                        try {
                            cityLoading.value = true
                            val res = await(addressStore.getDistrictOptions("City", provinceCode))
                            cityList.value = res
                            if (cityList.value.length > 0) {
                                val firstCity = cityList.value[0]
                                activeCityCode.value = firstCity.value
                                activeCityName.value = firstCity.label
                                await(loadDistrictList(firstCity.value))
                            } else {
                                activeCityCode.value = ""
                                activeCityName.value = ""
                                districtList.value = _uA()
                            }
                        }
                         catch (err: Throwable) {
                            console.error("加载城市列表失败:", err)
                        }
                         finally {
                            cityLoading.value = false
                        }
                })
            }
            val loadCityList = ::gen_loadCityList_fn
            fun gen_loadProvinceList_fn(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            provinceLoading.value = true
                            val res = await(addressStore.getDistrictOptions("Province", null))
                            provinceList.value = res
                            if (provinceList.value.length > 0 && activeProvinceCode.value == "") {
                                val firstProvince = provinceList.value[0]
                                activeProvinceCode.value = firstProvince.value
                                activeProvinceName.value = firstProvince.label
                                await(loadCityList(firstProvince.value))
                            }
                        }
                         catch (err: Throwable) {
                            console.error("加载省份列表失败:", err)
                        }
                         finally {
                            provinceLoading.value = false
                        }
                })
            }
            val loadProvinceList = ::gen_loadProvinceList_fn
            val selectProvince = fun(province: AddressOption){
                if (activeProvinceCode.value === province.value) {
                    return
                }
                activeProvinceCode.value = province.value
                activeProvinceName.value = province.label
                cityList.value = _uA()
                districtList.value = _uA()
                activeCityCode.value = ""
                activeCityName.value = ""
                selectedDistrictCodes.value = _uA()
                loadCityList(province.value)
            }
            val selectCity = fun(city: AddressOption){
                if (activeCityCode.value === city.value) {
                    return
                }
                activeCityCode.value = city.value
                activeCityName.value = city.label
                selectedDistrictCodes.value = _uA()
                loadDistrictList(city.value)
            }
            val toggleDistrict = fun(district: AddressOption){
                val index = selectedDistrictCodes.value.indexOf(district.value)
                if (index > -1) {
                    selectedDistrictCodes.value.splice(index, 1)
                } else {
                    selectedDistrictCodes.value.push(district.value)
                }
            }
            val toggleSelectAll = fun(){
                if (isAllSelected.value) {
                    selectedDistrictCodes.value = _uA()
                } else {
                    selectedDistrictCodes.value = districtList.value.map(fun(item: AddressOption): String {
                        return item.value
                    }
                    )
                }
            }
            val openRegion = fun(){
                regionVisible.value = true
                if (provinceList.value.length === 0) {
                    loadProvinceList()
                }
            }
            watch(activeProvinceCode, fun(newCode: String){
                if (hasText(newCode) && cityList.value.length === 0) {
                    loadCityList(newCode)
                }
            }
            )
            watch(activeCityCode, fun(newCode: String){
                if (hasText(newCode) && districtList.value.length === 0) {
                    loadDistrictList(newCode)
                }
            }
            )
            val filterList = ref(_uA<FilterItem__1>(FilterItem__1(key = "HelpType", title = "求职状态", value = "", options = _uA<RadioOption>()), FilterItem__1(key = "Sex", title = "性别要求", value = 0, options = _uA<RadioOption>(RadioOption(label = "不限", value = 0), RadioOption(label = "男", value = 1), RadioOption(label = "女", value = 2))), FilterItem__1(key = "Experience", title = "工作年限", value = "", options = _uA<RadioOption>()), FilterItem__1(key = "Education", title = "学历要求", value = "", options = _uA<RadioOption>())))
            val fetchResumeList = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (loading.value) {
                            return@w1
                        }
                        loading.value = true
                        try {
                            pageParams.Keywords = keywords.value
                            val res = await(getResumeList(pageParams as GetResumeListParams))
                            if (res != null) {
                                val resObj = res as UTSJSONObject
                                val dataArr = if (resObj["data"] != null) {
                                    resObj["data"] as UTSArray<GetOnlineResumeResult>
                                } else {
                                    _uA<GetOnlineResumeResult>()
                                }
                                val totalVal = (resObj["total"] ?: 0) as Number
                                if (pageParams.Page === 1) {
                                    resumeList.value = dataArr
                                } else {
                                    resumeList.value = resumeList.value.concat(dataArr)
                                }
                                total.value = totalVal
                            }
                        }
                         catch (e: Throwable) {
                            console.error("获取简历列表失败:", e)
                        }
                         finally {
                            loading.value = false
                            refresherTriggered.value = false
                        }
                })
            }
            val onLoadMore = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (loading.value || resumeList.value.length >= total.value) {
                            return@w1
                        }
                        pageParams.Page = pageParams.Page + 1
                        await(fetchResumeList())
                })
            }
            fun gen_onRefresh_fn(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        pageParams.Page = 1
                        refresherTriggered.value = true
                        await(fetchResumeList())
                })
            }
            val onRefresh = ::gen_onRefresh_fn
            val handleConfirmRegion = fun(){
                locationStore.setLocation(LocationState(provinceCode = activeProvinceCode.value, provinceName = activeProvinceName.value, cityCode = activeCityCode.value, cityName = activeCityName.value, districts = selectedDistricts.value.map(fun(d: AddressOption): DistrictItem {
                    return (DistrictItem(code = d.value, name = d.label, selected = true))
                }
                ) as UTSArray<DistrictItem>, latitude = 0, longitude = 0))
                regionVisible.value = false
                onRefresh()
            }
            val fetchFilterOptions = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val expRes = await(getAgeOrExp(GetAgeOrExpParams(DemandType = 1))) as UTSArray<GetAgeOrExpResult>?
                            val statusRes = await(getJobStatus()) as UTSArray<DictItem>?
                            if (expRes != null) {
                                val expFilter = filterList.value.find(fun(f: FilterItem__1): Boolean {
                                    return f.key === "Experience"
                                }
                                )
                                if (expFilter != null) {
                                    val defaultExp = _uA<RadioOption>(RadioOption(label = "不限", value = ""))
                                    val expItems = expRes!!.map(fun(item: GetAgeOrExpResult): RadioOption {
                                        return (RadioOption(label = item.Name, value = item.Id))
                                    }
                                    )
                                    expFilter.options = defaultExp.concat(expItems)
                                }
                            }
                            val eduFilter = filterList.value.find(fun(f: FilterItem__1): Boolean {
                                return f.key === "Education"
                            }
                            )
                            if (eduFilter != null) {
                                val defaultEdu = _uA<RadioOption>(RadioOption(label = "不限", value = ""))
                                eduFilter.options = defaultEdu.concat(EDUCATION_OPTIONS)
                            }
                            if (statusRes != null) {
                                val statusFilter = filterList.value.find(fun(f: FilterItem__1): Boolean {
                                    return f.key === "HelpType"
                                }
                                )
                                if (statusFilter != null) {
                                    val defaultStatus = _uA<RadioOption>(RadioOption(label = "不限", value = ""))
                                    val statusItems = statusRes!!.map(fun(item: DictItem): RadioOption {
                                        return (RadioOption(label = item.message, value = item.code))
                                    }
                                    )
                                    statusFilter.options = defaultStatus.concat(statusItems)
                                }
                            }
                        }
                         catch (e: Throwable) {
                            console.error("获取筛选选项失败:", e)
                        }
                })
            }
            val selectFilterOption = fun(filterIndex: Number, optionValue: Any){
                filterList.value[filterIndex].value = optionValue
            }
            val closeFilter = fun(){
                rightVisible.value = false
            }
            val resetFilter = fun(){
                filterList.value.forEach(fun(filter: FilterItem__1){
                    if (filter.key === "Sex") {
                        filter.value = 0
                    } else {
                        filter.value = ""
                    }
                }
                )
            }
            val confirmFilter = fun(){
                filterList.value.forEach(fun(filter: FilterItem__1){
                    if (filter.key === "HelpType") {
                        pageParams.HelpType = filter.value as String
                    }
                    if (filter.key === "Education") {
                        pageParams.Education = filter.value as String
                    }
                    if (filter.key === "Sex") {
                        pageParams.Sex = filter.value as Number
                    }
                }
                )
                onRefresh()
                rightVisible.value = false
            }
            val goToProfile = fun(resumeId: Number){
                uni_navigateTo(NavigateToOptions(url = "/pages/hire/enterprise/profile/index?id=" + resumeId))
            }
            onMounted(fun(){
                fetchFilterOptions()
                fetchResumeList()
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-tab flex flex-col"), _uA(
                    _cE("view", _uM("class" to "h-36 bg-gradient-to-b from-green-200 overflow-visible justify-between"), _uA(
                        _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "首页"), _uM("left" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-1 pl-2 min-w-80", "onClick" to openRegion), _uA(
                                    _cE("text", _uM("class" to "text-base text-gray-800 font-medium line-clamp-1 min-w-70"), _tD(getTextOrFallback(unref(locationStore).cityName, "选择城市")), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiala", "size" to 20, "color" to "#374151"))
                                ))
                            )
                        }
                        ), "_" to 1)),
                        _cE("view", _uM("class" to "px-4"), _uA(
                            _cE("view", _uM("class" to "bg-white rounded-full flex flex-row w-full"), _uA(
                                _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to keywords.value, "onUpdate:modelValue" to fun(`$event`: String){
                                    keywords.value = `$event`
                                }
                                , "onSearch" to onRefresh), null, 8, _uA(
                                    "modelValue",
                                    "onUpdate:modelValue"
                                ))
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "flex-1 py-2 gap-2 overflow-hidden"), _uA(
                        _cE("view", _uM("class" to "flex flex-row justify-between px-4 py-1"), _uA(
                            _cE("view", _uM("class" to "text-base text-gray-700 font-bold"), "推荐列表"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-1", "onClick" to fun(){
                                rightVisible.value = true
                            }
                            ), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shaixuan", "size" to 28, "color" to "#000")),
                                _cE("text", _uM("class" to "text-gray-600 text-base"), "推荐")
                            ), 8, _uA(
                                "onClick"
                            ))
                        )),
                        _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to true, "refresher-triggered" to refresherTriggered.value, "onScrolltolower" to onLoadMore, "onRefresherrefresh" to onRefresh), _uA(
                            _cE("view", _uM("class" to "mt-2 px-3 pb-5"), _uA(
                                if (resumeList.value.length > 0) {
                                    _cE("view", _uM("key" to 0), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(resumeList.value, fun(item, index, __index, _cached): Any {
                                            return _cE("view", _uM("key" to index, "class" to "p-1 mb-1 bg-white rounded-lg"), _uA(
                                                _cV(unref(GenPagesHireTabbarHomeComponentsPositionCardClass), _uM("item" to item, "onClick" to goToProfile), null, 8, _uA(
                                                    "item"
                                                ))
                                            ))
                                        }), 128),
                                        if (isTrue(loading.value && resumeList.value.length > 0)) {
                                            _cE("view", _uM("key" to 0, "class" to "text-center py-2"), _uA(
                                                _cE("text", _uM("class" to "text-gray-400 text-sm"), "加载中...")
                                            ))
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        if (isTrue(resumeList.value.length >= total.value && total.value > 0)) {
                                            _cE("view", _uM("key" to 1, "class" to "text-center py-2"), _uA(
                                                _cE("text", _uM("class" to "text-gray-400 text-sm text-center"), "没有更多了")
                                            ))
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    ))
                                } else {
                                    if (isTrue(!loading.value)) {
                                        _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 1, "title" to "暂无相关简历", "description" to "可以尝试更换搜索关键词或筛选条件"))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                }
                            ))
                        ), 40, _uA(
                            "refresher-triggered"
                        ))
                    )),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 0)),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to rightVisible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        rightVisible.value = `$event`
                    }
                    , "show-title" to false, "position" to "right", "width" to "80%", "z-index" to 99999), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-white"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center justify-between py-4 px-3 border-b border-gray-100"), _uA(
                                    _cE("view", _uM("class" to "w-20", "onClick" to closeFilter), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-guanbi", "size" to 40, "color" to "#333"))
                                    )),
                                    _cE("text", _uM("class" to "text-lg font-semibold text-gray-800"), "全部筛选"),
                                    _cE("view", _uM("class" to "w-10"))
                                )),
                                _cE("scroll-view", _uM("class" to "flex-1 py-4", "scroll-y" to ""), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(filterList.value, fun(filter, filterIndex, __index, _cached): Any {
                                        return _cE("view", _uM("key" to filter.key, "class" to "mb-6"), _uA(
                                            _cE("text", _uM("class" to "text-base font-medium text-gray-800 mb-3"), _tD(filter.title), 1),
                                            _cE("view", _uM("class" to "flex flex-row flex-wrap gap-2 mt-3"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(filter.options, fun(item, __key, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to item.value, "class" to _nC(_uA(
                                                        "py-2 px-4 rounded-md",
                                                        if (filter.value === item.value) {
                                                            "bg-teal-700 border border-green-500"
                                                        } else {
                                                            "bg-gray-50"
                                                        }
                                                    )), "onClick" to fun(){
                                                        selectFilterOption(filterIndex, item.value)
                                                    }
                                                    ), _uA(
                                                        _cE("text", _uM("class" to _nC(_uA(
                                                            "text-sm",
                                                            if (filter.value === item.value) {
                                                                "text-green-200 font-medium"
                                                            } else {
                                                                "text-gray-600"
                                                            }
                                                        ))), _tD(item.label), 3)
                                                    ), 10, _uA(
                                                        "onClick"
                                                    ))
                                                }
                                                ), 128)
                                            ))
                                        ))
                                    }
                                    ), 128)
                                )),
                                _cE("view", _uM("class" to "flex flex-row p-3 border-t border-gray-100 gap-3"), _uA(
                                    _cE("view", _uM("class" to "flex-1"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "default", "size" to "large", "shape" to "round", "text" to "重置", "onClick" to resetFilter))
                                    )),
                                    _cE("view", _uM("class" to "flex-1"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "success", "size" to "large", "shape" to "round", "text" to "确定", "custom-class" to "t-default", "onClick" to confirmFilter))
                                    ))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to regionVisible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        regionVisible.value = `$event`
                    }
                    , "title" to "选择地区", "z-index" to 99999, "height" to "90%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-white overflow-hidden", "style" to _nS(_uM("flex" to "1"))), _uA(
                                _cE("view", _uM("class" to "flex flex-row flex-1 overflow-hidden", "style" to _nS(_uM("flex" to "1"))), _uA(
                                    _cE("scroll-view", _uM("class" to "region-column bg-gray-50", "scroll-y" to "", "show-scrollbar" to false), _uA(
                                        if (isTrue(provinceLoading.value)) {
                                            _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-20"), _uA(
                                                _cE("text", _uM("class" to "text-xs text-gray-400"), "加载中...")
                                            ))
                                        } else {
                                            _cE("view", _uM("key" to 1), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(provinceList.value, fun(province, __key, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to province.value, "class" to _nC(_uA(
                                                        "region-item flex items-center px-4 py-6",
                                                        if (activeProvinceCode.value === province.value) {
                                                            "active"
                                                        } else {
                                                            ""
                                                        }
                                                    )), "onClick" to fun(){
                                                        selectProvince(province)
                                                    }
                                                    ), _uA(
                                                        _cE("text", _uM("class" to _nC(_uA(
                                                            "text-xs line-clamp-1",
                                                            if (activeProvinceCode.value === province.value) {
                                                                "text-teal-600 font-medium"
                                                            } else {
                                                                "text-gray-600"
                                                            }
                                                        ))), _tD(province.label), 3)
                                                    ), 10, _uA(
                                                        "onClick"
                                                    ))
                                                }
                                                ), 128)
                                            ))
                                        }
                                    )),
                                    _cE("scroll-view", _uM("class" to "region-column bg-gray-50", "scroll-y" to "", "show-scrollbar" to false), _uA(
                                        if (isTrue(cityLoading.value)) {
                                            _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-20"), _uA(
                                                _cE("text", _uM("class" to "text-xs text-gray-400"), "加载中...")
                                            ))
                                        } else {
                                            if (cityList.value.length === 0) {
                                                _cE("view", _uM("key" to 1, "class" to "flex items-center justify-center py-20"), _uA(
                                                    _cE("text", _uM("class" to "text-xs text-gray-400"), "请选择省份")
                                                ))
                                            } else {
                                                _cE("view", _uM("key" to 2), _uA(
                                                    _cE(Fragment, null, RenderHelpers.renderList(cityList.value, fun(city, __key, __index, _cached): Any {
                                                        return _cE("view", _uM("key" to city.value, "class" to _nC(_uA(
                                                            "region-item flex items-center px-4 py-6",
                                                            if (activeCityCode.value === city.value) {
                                                                "active"
                                                            } else {
                                                                ""
                                                            }
                                                        )), "onClick" to fun(){
                                                            selectCity(city)
                                                        }
                                                        ), _uA(
                                                            _cE("text", _uM("class" to _nC(_uA(
                                                                "text-xs line-clamp-1",
                                                                if (activeCityCode.value === city.value) {
                                                                    "text-teal-600 font-medium"
                                                                } else {
                                                                    "text-gray-600"
                                                                }
                                                            ))), _tD(city.label), 3)
                                                        ), 10, _uA(
                                                            "onClick"
                                                        ))
                                                    }
                                                    ), 128)
                                                ))
                                            }
                                        }
                                    )),
                                    _cE("view", _uM("class" to "flex flex-col flex-1 bg-white"), _uA(
                                        _cE("view", _uM("class" to "flex flex-row items-center justify-between px-4 py-4 border-b border-gray-100"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row items-center gap-2", "onClick" to toggleSelectAll), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("model-value" to isAllSelected.value, "size" to "small"), null, 8, _uA(
                                                    "model-value"
                                                )),
                                                _cE("text", _uM("class" to "text-xs text-gray-600"), "全选")
                                            )),
                                            _cE("text", _uM("class" to "text-xs text-gray-400"), "已选 " + _tD(selectedCount.value) + " 个", 1)
                                        )),
                                        _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "show-scrollbar" to false), _uA(
                                            if (isTrue(districtLoading.value)) {
                                                _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-20"), _uA(
                                                    _cE("text", _uM("class" to "text-xs text-gray-400"), "加载中...")
                                                ))
                                            } else {
                                                if (districtList.value.length === 0) {
                                                    _cE("view", _uM("key" to 1, "class" to "flex items-center justify-center py-20"), _uA(
                                                        _cE("text", _uM("class" to "text-xs text-gray-400"), "请选择城市")
                                                    ))
                                                } else {
                                                    _cE("view", _uM("key" to 2, "class" to "flex flex-row flex-wrap px-4 py-4 gap-4"), _uA(
                                                        _cE(Fragment, null, RenderHelpers.renderList(districtList.value, fun(district, __key, __index, _cached): Any {
                                                            return _cE("view", _uM("key" to district.value, "class" to _nC(_uA(
                                                                "district-item flex items-center justify-center px-4 py-4 rounded-md",
                                                                if (selectedDistrictCodes.value.includes(district.value)) {
                                                                    "bg-teal-50 border border-teal-500"
                                                                } else {
                                                                    "bg-gray-50"
                                                                }
                                                            )), "onClick" to fun(){
                                                                toggleDistrict(district)
                                                            }
                                                            ), _uA(
                                                                _cE("text", _uM("class" to _nC(_uA(
                                                                    "text-xs",
                                                                    if (selectedDistrictCodes.value.includes(district.value)) {
                                                                        "text-teal-600 font-medium"
                                                                    } else {
                                                                        "text-gray-600"
                                                                    }
                                                                ))), _tD(district.label), 3)
                                                            ), 10, _uA(
                                                                "onClick"
                                                            ))
                                                        }
                                                        ), 128)
                                                    ))
                                                }
                                            }
                                        ))
                                    ))
                                ), 4),
                                _cE("view", _uM("class" to "border-t border-gray-100"), _uA(
                                    if (selectedCount.value > 0) {
                                        _cE("view", _uM("key" to 0, "class" to "px-4 py-4 bg-gray-50"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row flex-wrap gap-6"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(selectedDistricts.value, fun(district, __key, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to district.value, "class" to "selected-tag flex flex-row items-center gap-2 px-4 py-2 bg-teal-50 rounded-full", "onClick" to fun(){
                                                        toggleDistrict(district)
                                                    }), _uA(
                                                        _cE("text", _uM("class" to "text-xs text-teal-600"), _tD(district.label), 1),
                                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-cuowu", "size" to 24, "color" to "#0d9488"))
                                                    ), 8, _uA(
                                                        "onClick"
                                                    ))
                                                }), 128)
                                            ))
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                    ,
                                    _cE("view", _uM("class" to "flex flex-row px-6 py-6 safe-area-inset-bottom pb-8"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "text" to ("确定 " + (if (selectedCount.value > 0) {
                                            "（已选" + selectedCount.value + "个）"
                                        } else {
                                            ""
                                        }
                                        )), "onClick" to handleConfirmRegion), null, 8, _uA(
                                            "text"
                                        ))
                                    ))
                                ))
                            ), 4)
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
                return _uM("region-column" to _pS(_uM("width" to "180rpx", "borderRightWidth" to 1, "borderRightStyle" to "solid", "borderRightColor" to "#f0f0f0")), "region-item" to _uM("" to _uM("position" to "relative", "transitionProperty" to "backgroundColor", "transitionDuration" to "0.2s"), ".active" to _uM("backgroundColor" to "#ffffff"), ".active::before" to _uM("content" to "\"\"", "position" to "absolute", "left" to 0, "top" to "50%", "transform" to "translateY(-50%)", "width" to "6rpx", "height" to "32rpx", "backgroundColor" to "#0d9488", "borderTopLeftRadius" to 0, "borderTopRightRadius" to "3rpx", "borderBottomRightRadius" to "3rpx", "borderBottomLeftRadius" to 0)), "district-item" to _pS(_uM("minWidth" to "120rpx", "transitionProperty" to "all", "transitionDuration" to "0.2s")), "selected-tag" to _pS(_uM("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#14b8a6", "borderRightColor" to "#14b8a6", "borderBottomColor" to "#14b8a6", "borderLeftColor" to "#14b8a6")), "@TRANSITION" to _uM("region-item" to _uM("property" to "backgroundColor", "duration" to "0.2s"), "district-item" to _uM("property" to "all", "duration" to "0.2s")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
