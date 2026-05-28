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
import io.dcloud.uniapp.extapi.stopPullDownRefresh as uni_stopPullDownRefresh
open class GenPagesApplyTabbarHomeIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarHomeIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarHomeIndex
            val _cache = __ins.renderCache
            val locationStore = useLocationStore()
            val addressStore = useAddressStore()
            val asText = fun(value: Any?): String {
                return (value ?: "") as String
            }
            val hasText = fun(value: Any?): Boolean {
                return value != null && value != ""
            }
            val splitTags = fun(value: Any?): UTSArray<String> {
                val str = asText(value)
                if (str == "") {
                    return _uA<String>()
                }
                val parts: UTSArray<String> = str.split(",")
                return parts.filter(fun(t: String): Boolean {
                    return t !== ""
                }
                )
            }
            val getJobImage = fun(item: GetPositionListResult): String {
                if (hasText(item.PicNo)) {
                    return item.PicNo as String
                }
                return asText(item.Avatar)
            }
            val parseIntegerOrZero = fun(value: String): Number {
                val parsedValue = parseInt(value)
                return if (isNaN(parsedValue)) {
                    0
                } else {
                    parsedValue
                }
            }
            val rightVisible = ref(false)
            val regionVisible = ref(false)
            val activeProvinceCode = ref("")
            val activeProvinceName = ref("")
            val activeCityCode = ref("")
            val activeCityName = ref("")
            val selectedDistrictCodes = ref(_uA<String>())
            val provinceLoading = ref(false)
            val cityLoading = ref(false)
            val districtLoading = ref(false)
            val loading = ref(false)
            val total = ref(0)
            val positionList = ref(_uA<JobInfo>())
            val provinceList = ref(_uA<AddressOption>())
            val cityList = ref(_uA<AddressOption>())
            val districtList = ref(_uA<AddressOption>())
            val groupQrPopupVisible = ref(false)
            val groupQrCodeUrl = ref("")
            val groupQrLoading = ref(false)
            val filterList = ref(_uA<FilterItem>(FilterItem(key = "salary", title = "期望薪资", value = "", options = _uA<FilterOption>()), FilterItem(key = "distance", title = "距离范围", value = "", options = _uA<FilterOption>(FilterOption(label = "不限", value = ""), FilterOption(label = "3km以内", value = 3), FilterOption(label = "5km以内", value = 5), FilterOption(label = "10km以内", value = 10), FilterOption(label = "20km以内", value = 20))), FilterItem(key = "benefits", title = "福利筛选", value = "", options = _uA<FilterOption>())))
            val param = reactive<GetPositionListParams>(GetPositionListParams(IsOneself = 0, Lat = 0, Lng = 0, HireJobStatue = 0, SettleWay = "", HireJobCode = (locationStore.cityCode ?: "") as String, SalaryId = 0, Keywords = "", HireCompanyId = "", ToMemberId = "", Page = 1, PageSize = 10))
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
            val filteredJobList = computed(fun(): UTSArray<JobInfo> {
                return positionList.value
            }
            )
            val activeSettleType = computed<String>(_uO("get" to fun(): String {
                return if (param.SettleWay === "") {
                    "全职"
                } else {
                    param.SettleWay
                }
            }
            , "set" to fun(kVal: String){
                param.SettleWay = if (kVal === "全职") {
                    ""
                } else {
                    kVal
                }
                param.Page = 1
            }
            ))
            val workTypeOptions = computed(fun(): UTSArray<TabItem> {
                val toTabItem = fun(opt: TabOption): TabItem {
                    return (TabItem(key = opt.key, label = opt.label, badge = opt.badge))
                }
                val a = WORK_TYPE_OPTIONS.map(fun(item: TabOption): TabItem {
                    return toTabItem(item)
                }
                )
                val b = SETTLE_TYPE_OPTIONS.map(fun(item: TabOption): TabItem {
                    return toTabItem(item)
                }
                )
                return a.concat(b)
            }
            )
            val bannerPosition = computed(fun(): Number {
                val len = filteredJobList.value.length
                if (len < 2) {
                    return -1
                }
                return 1
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
            val loadProvinceList = fun(): UTSPromise<Unit> {
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
            val handleConfirmRegion = fun(){
                locationStore.setLocation(LocationState(provinceCode = activeProvinceCode.value, provinceName = activeProvinceName.value, cityCode = activeCityCode.value, cityName = activeCityName.value, districts = selectedDistricts.value.map(fun(d: AddressOption): DistrictItem {
                    return (DistrictItem(code = d.value, name = d.label, selected = true))
                }
                ), latitude = 0, longitude = 0))
                regionVisible.value = false
            }
            val openRegion = fun(){
                regionVisible.value = true
                if (provinceList.value.length === 0) {
                    loadProvinceList()
                }
            }
            watch(activeProvinceCode, fun(newCode: String){
                if (newCode != "" && cityList.value.length === 0) {
                    loadCityList(newCode)
                }
            }
            )
            watch(activeCityCode, fun(newCode: String){
                if (newCode != "" && districtList.value.length === 0) {
                    loadDistrictList(newCode)
                }
            }
            )
            val fetchFilterOptions = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val salaryRes = await(getSalary())
                            val benefitsRes = await(getBenefits())
                            if (salaryRes != null) {
                                val salaryFilter = filterList.value.find(fun(f: FilterItem): Boolean {
                                    return f.key === "salary"
                                }
                                )
                                if (salaryFilter != null) {
                                    val salaryItems = (salaryRes as UTSArray<GetSalaryResult>).map(fun(item: GetSalaryResult): FilterOption {
                                        return (FilterOption(label = item.Name, value = item.Id))
                                    }
                                    )
                                    val defaultSalary = _uA<FilterOption>(FilterOption(label = "不限", value = ""))
                                    salaryFilter.options = defaultSalary.concat(salaryItems)
                                }
                            }
                            if (benefitsRes != null) {
                                val benefitsFilter = filterList.value.find(fun(f: FilterItem): Boolean {
                                    return f.key === "benefits"
                                }
                                )
                                if (benefitsFilter != null) {
                                    val benefitsItems = (benefitsRes as UTSArray<GetBenefitsParamsn>).map(fun(item: GetBenefitsParamsn): FilterOption {
                                        return (FilterOption(label = item.Name, value = item.Id))
                                    }
                                    )
                                    val defaultBenefit = _uA<FilterOption>(FilterOption(label = "不限", value = ""))
                                    benefitsFilter.options = defaultBenefit.concat(benefitsItems)
                                }
                            }
                        }
                         catch (e: Throwable) {
                            console.error("获取筛选选项失败:", e)
                        }
                })
            }
            val fetchPositionList = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (loading.value) {
                            return@w1
                        }
                        val isRefresh = param.Page === 1
                        loading.value = true
                        try {
                            val res = await(getPositionList(param))
                            if (res != null) {
                                val resObj = res as UTSJSONObject
                                val dataArr = if (resObj["data"] != null) {
                                    resObj["data"] as UTSArray<GetPositionListResult>
                                } else {
                                    _uA<GetPositionListResult>()
                                }
                                val totalVal = (resObj["total"] ?: 0) as Number
                                val newList = dataArr.map(fun(item: GetPositionListResult): JobInfo {
                                    return JobInfo(id = (item.Id ?: 0), title = asText(item.JobName), company = asText(item.HireCompanyName), area = asText(item.HireAreaCodeName), address = asText(item.HireCityCodeName), benefitTags = splitTags(item.HireWelfareName), salaryName = asText(item.SalaryName), distance = (item.Distance ?: 0) as Number, settleType = asText(item.SettleWay), image = getJobImage(item))
                                }
                                )
                                if (isRefresh) {
                                    positionList.value = newList
                                } else {
                                    positionList.value = positionList.value.concat(newList)
                                }
                                total.value = totalVal
                            }
                        }
                         catch (e: Throwable) {
                            console.error("获取职位列表失败:", e)
                        }
                         finally {
                            loading.value = false
                            if (isRefresh) {
                                uni_stopPullDownRefresh()
                            }
                        }
                })
            }
            val handleSearch = fun(value: String){
                param.Keywords = value
                param.Page = 1
            }
            val selectFilterOption = fun(filterIndex: Number, optionValue: Any){
                filterList.value[filterIndex].value = optionValue
            }
            val resetFilter = fun(){
                filterList.value.forEach(fun(filter: FilterItem){
                    filter.value = ""
                }
                )
                param.SalaryId = 0
                param.Page = 1
            }
            val confirmFilter = fun(){
                val conditions = UTSJSONObject()
                filterList.value.forEach(fun(filter: FilterItem){
                    conditions[filter.key] = "" + filter.value
                }
                )
                val salaryFilter = filterList.value.find(fun(f: FilterItem): Boolean {
                    return f.key === "salary"
                }
                )
                if (salaryFilter != null) {
                    param.SalaryId = if (salaryFilter.value != "") {
                        parseIntegerOrZero("" + salaryFilter.value)
                    } else {
                        0
                    }
                }
                rightVisible.value = false
                param.Page = 1
            }
            val goToJobDetail = fun(jobInfo: JobInfo){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/job-detail/index?id=" + jobInfo.id))
            }
            val handleJoinGroup = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (groupQrLoading.value) {
                            return@w1
                        }
                        groupQrLoading.value = true
                        try {
                            val res = await(getGroupQrCode(GetGroupQrCodeParams(Lat = (locationStore.latitude as Number) ?: 0, Lng = (locationStore.longitude as Number) ?: 0)))
                            if (res == null || (res as String) == "") {
                                uni_showToast(ShowToastOptions(title = "暂无群二维码", icon = "none"))
                                return@w1
                            }
                            groupQrCodeUrl.value = (res as String) ?: ""
                            groupQrPopupVisible.value = true
                        }
                         catch (err: Throwable) {
                            console.error("获取群二维码失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取二维码失败", icon = "none"))
                        }
                         finally {
                            groupQrLoading.value = false
                        }
                })
            }
            watch(param, fun(){
                fetchPositionList()
            }
            , WatchOptions(deep = true))
            watch(fun(): String {
                return (locationStore.cityCode ?: "") as String
            }
            , fun(newCode: String){
                param.HireJobCode = newCode
                param.Page = 1
            }
            )
            val openFilter = fun(){
                rightVisible.value = true
            }
            onLoad(fun(_options){
                fetchFilterOptions()
                fetchPositionList()
            }
            )
            onPullDownRefresh(fun(){
                if (param.Page === 1) {
                    fetchPositionList()
                } else {
                    param.Page = 1
                }
            }
            )
            onReachBottom(fun(){
                if (positionList.value.length < total.value) {
                    param.Page++
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-tab flex flex-col bg-gray-50"), _uA(
                    _cE("view", _uM("class" to "h-36 bg-gradient-to-b from-green-200 overflow-visible justify-end"), _uA(
                        _cE("view", _uM("class" to "flex flex-row px-4 items-center overflow-visible"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2 mr-4", "onClick" to openRegion), _uA(
                                _cE("text", _uM("class" to "text-base text-gray-800 font-medium"), _tD(if (hasText(unref(locationStore).cityName)) {
                                    unref(locationStore).cityName
                                } else {
                                    "选择城市"
                                }
                                ), 1),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiala", "size" to 24, "color" to "#374151"))
                            )),
                            _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to param.Keywords, "onUpdate:modelValue" to fun(`$event`: String){
                                param.Keywords = `$event`
                            }
                            , "onSearch" to handleSearch), null, 8, _uA(
                                "modelValue",
                                "onUpdate:modelValue"
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "bg-white border-b border-gray-100"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center"), _uA(
                            _cE("view", _uM("class" to "flex-1"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTabsIndexClass), _uM("active-key" to activeSettleType.value, "onUpdate:activeKey" to fun(`$event`: String){
                                    activeSettleType.value = `$event`
                                }
                                , "items" to workTypeOptions.value, "type" to "line", "active-color" to "#0d9488", "inactive-color" to "#6b7280", "animated" to true), null, 8, _uA(
                                    "active-key",
                                    "onUpdate:activeKey",
                                    "items"
                                ))
                            )),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2 px-4 border-l border-gray-200", "onClick" to openFilter), _uA(
                                _cE("text", _uM("class" to "text-sm text-gray-600"), "筛选"),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shaixuan", "size" to 32, "color" to "#6b7280"))
                            ))
                        ))
                    )),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "show-scrollbar" to false), _uA(
                        _cE("view", _uM("class" to "px-2 py-2"), _uA(
                            if (filteredJobList.value.length > 0) {
                                _cV(unref(GenComponentsJobCardIndexClass), _uM("key" to 0, "jobs" to filteredJobList.value, "banner-index" to bannerPosition.value, "onClick" to goToJobDetail), _uM("banner" to withSlotCtx(fun(): UTSArray<Any> {
                                    return _uA(
                                        _cV(unref(GenPagesApplyTabbarHomeComponentsGroupBannerIndexClass), _uM("onJoin" to handleJoinGroup))
                                    )
                                }), "_" to 1), 8, _uA(
                                    "jobs",
                                    "banner-index"
                                ))
                            } else {
                                _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 1, "title" to "暂无职位", "description" to "换个条件试试吧"))
                            }
                        )),
                        _cE("view", _uM("class" to "h-28"))
                    )),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 0)),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to groupQrPopupVisible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        groupQrPopupVisible.value = `$event`
                    }
                    , "position" to "center", "width" to "84%", "show-title" to false, "z-index" to 99999), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col items-center justify-center px-8 py-10 bg-white rounded-2xl"), _uA(
                                _cE("text", _uM("class" to "text-lg font-semibold text-gray-800 mb-3"), "加入工友群"),
                                _cE("text", _uM("class" to "text-sm text-gray-500 mb-8"), "长按识别二维码加入群聊"),
                                _cE("view", _uM("class" to "w-72 h-72 bg-gray-50 rounded-xl overflow-hidden flex items-center justify-center"), _uA(
                                    if (isTrue(groupQrLoading.value)) {
                                        _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "二维码加载中...")
                                    } else {
                                        if (groupQrCodeUrl.value != "") {
                                            _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("key" to 1, "src" to groupQrCodeUrl.value, "width" to "100%", "height" to "100%", "mode" to "aspectFit", "error-text" to ""), null, 8, _uA(
                                                "src"
                                            ))
                                        } else {
                                            _cE("text", _uM("key" to 2, "class" to "text-sm text-gray-400"), "暂无二维码")
                                        }
                                    }
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to rightVisible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        rightVisible.value = `$event`
                    }
                    , "title" to "全部筛选", "z-index" to 99999, "height" to "80%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-white"), _uA(
                                _cE("scroll-view", _uM("class" to "flex-1 py-8", "scroll-y" to ""), _uA(
                                    _cE("view", _uM("class" to "px-6"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(filterList.value, fun(filter, filterIndex, __index, _cached): Any {
                                            return _cE("view", _uM("key" to filter.key, "class" to "mb-12"), _uA(
                                                _cE("text", _uM("class" to "text-base font-medium text-gray-800 mb-6"), _tD(filter.title), 1),
                                                _cE("view", _uM("class" to "flex flex-row flex-wrap gap-4 mt-6"), _uA(
                                                    _cE(Fragment, null, RenderHelpers.renderList(filter.options, fun(item, __key, __index, _cached): Any {
                                                        return _cE("view", _uM("key" to item.value, "class" to _nC(_uA(
                                                            "py-4 px-8 rounded-md",
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
                                    ))
                                )),
                                _cE("view", _uM("class" to "flex flex-row py-6 px-6 border-t border-gray-100 gap-6"), _uA(
                                    _cE("view", _uM("class" to "flex-1", "onClick" to resetFilter), _uA(
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
                    , "title" to "选择地区", "z-index" to 99999, "height" to "80%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
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
                return _uM("select-box" to _pS(_uM("borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000")), "region-column" to _pS(_uM("width" to "180rpx", "borderRightWidth" to 1, "borderRightStyle" to "solid", "borderRightColor" to "#f0f0f0")), "region-item" to _uM("" to _uM("position" to "relative", "transitionProperty" to "backgroundColor", "transitionDuration" to "0.2s"), ".active" to _uM("backgroundColor" to "#ffffff"), ".active::before" to _uM("content" to "\"\"", "position" to "absolute", "left" to 0, "top" to "50%", "transform" to "translateY(-50%)", "width" to "6rpx", "height" to "32rpx", "backgroundColor" to "#0d9488", "borderTopLeftRadius" to 0, "borderTopRightRadius" to "3rpx", "borderBottomRightRadius" to "3rpx", "borderBottomLeftRadius" to 0)), "district-item" to _pS(_uM("minWidth" to "120rpx", "transitionProperty" to "all", "transitionDuration" to "0.2s")), "selected-tag" to _pS(_uM("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#14b8a6", "borderRightColor" to "#14b8a6", "borderBottomColor" to "#14b8a6", "borderLeftColor" to "#14b8a6")), "@TRANSITION" to _uM("region-item" to _uM("property" to "backgroundColor", "duration" to "0.2s"), "district-item" to _uM("property" to "all", "duration" to "0.2s")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
