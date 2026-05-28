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
import io.dcloud.uniapp.extapi.stopPullDownRefresh as uni_stopPullDownRefresh
open class GenPagesApplyHistoryDelivery : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyHistoryDelivery) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyHistoryDelivery
            val _cache = __ins.renderCache
            val asText = fun(value: Any?): String {
                return if (value != null) {
                    value
                } else {
                    ""
                }
            }
            val hasText = fun(value: Any?): Boolean {
                return value != null && value != ""
            }
            val getDatePart = fun(dateTime: String): String {
                if (dateTime == "") {
                    return ""
                }
                return if (dateTime.length >= 10) {
                    dateTime.substring(0, 10)
                } else {
                    dateTime
                }
            }
            val getDisplayDate = fun(dateTime: String): String {
                val datePart = getDatePart(dateTime)
                if (datePart == "" || datePart.length < 10) {
                    return ""
                }
                return datePart.substring(5, 10)
            }
            val parseTags = fun(welfareName: String): UTSArray<String> {
                return asText(welfareName).split(",").filter(fun(tag): Boolean {
                    return tag !== ""
                }
                ) as UTSArray<String>
            }
            val mapRecord = fun(item: GetDeliveryRecordResult): DeliveryRecord {
                val areaText = ("" + asText(item.AreaCodeName) + " " + asText(item.HireAddress)).trim()
                val deliverTime = asText(item.DeliverTime)
                return DeliveryRecord(id = String(item.Id), jobTitle = asText(item.JobName), salary = asText(item.SalaryName), company = asText(item.HireCompanyName), tags = parseTags(asText(item.HireWelfareName)), areaText = areaText, deliverTime = deliverTime, deliverDate = getDatePart(deliverTime), displayDate = getDisplayDate(deliverTime))
            }
            val mergeGroups = fun(target: UTSArray<DeliveryGroup>, incoming: UTSArray<DeliveryGroup>){
                incoming.forEach(fun(group){
                    val lastGroup = target[target.length - 1]
                    if (lastGroup != null && lastGroup.date === group.date) {
                        lastGroup.items.push(*group.items.toTypedArray())
                    } else {
                        target.push(DeliveryGroup(date = group.date, items = group.items.slice()))
                    }
                }
                )
            }
            val buildGroups = fun(list: UTSArray<DeliveryRecord>): UTSArray<DeliveryGroup> {
                val result: UTSArray<DeliveryGroup> = _uA()
                val map = Map<String, UTSArray<DeliveryRecord>>()
                list.forEach(fun(item){
                    val key = if (item.displayDate != "") {
                        item.displayDate
                    } else {
                        "未知日期"
                    }
                    if (!map.has(key)) {
                        map.set(key, _uA())
                    }
                    map.get(key)?.push(item)
                }
                )
                map.forEach(fun(items, key){
                    result.push(DeliveryGroup(date = key, items = items))
                }
                )
                return result
            }
            val params = reactive<GetDeliveryRecordParams>(GetDeliveryRecordParams(Type = 1, StarTime = "", EndTime = "", Page = 1, PageSize = 10))
            val groups = ref(_uA<DeliveryGroup>())
            val filterStartDate = ref("")
            val filterEndDate = ref("")
            val showTimePicker = ref(false)
            val currentTimeRange = ref(_uA<Number>())
            val isRefreshing = ref(false)
            val isLoading = ref(false)
            val hasMore = ref(true)
            val total = ref(0)
            val loadData = fun(reset: Boolean = false): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoading.value) {
                            return@w1
                        }
                        if (reset) {
                            params.Page = 1
                            hasMore.value = true
                            isRefreshing.value = true
                        } else {
                            if (!hasMore.value) {
                                return@w1
                            }
                            isLoading.value = true
                            params.Page += 1
                        }
                        params.StarTime = filterStartDate.value
                        params.EndTime = filterEndDate.value
                        try {
                            val res = await(getDeliveryRecord(params))
                            val responseData = res?.data
                            val responseTotal = res?.total
                            val records = if (responseData != null) {
                                responseData.map(fun(item): DeliveryRecord {
                                    return mapRecord(item)
                                })
                            } else {
                                _uA()
                            }
                            val groupedList = buildGroups(records)
                            if (reset) {
                                groups.value = groupedList
                            } else if (groupedList.length > 0) {
                                val merged = groups.value.slice()
                                mergeGroups(merged, groupedList)
                                groups.value = merged
                            }
                            total.value = if (responseTotal != null) {
                                responseTotal
                            } else {
                                0
                            }
                            var currentCount: Number = 0
                            groups.value.forEach(fun(group){
                                currentCount += group.items.length
                            }
                            )
                            hasMore.value = currentCount < total.value
                        }
                         catch (err: Throwable) {
                            if (!reset) {
                                params.Page -= 1
                            }
                            console.error("获取投递记录失败:", err)
                            uni_showToast(ShowToastOptions(title = "加载失败", icon = "none"))
                        }
                         finally {
                            isRefreshing.value = false
                            isLoading.value = false
                            uni_stopPullDownRefresh()
                        }
                })
            }
            val handleHeaderClick = fun(){
                showTimePicker.value = true
            }
            val handleTimeRangeConfirm = fun(_value: Any, formatted: UTSArray<String>){
                showTimePicker.value = false
                if (formatted.length >= 2) {
                    filterStartDate.value = formatted[0]
                    filterEndDate.value = formatted[1]
                }
                loadData(true)
            }
            val handleTimeCancel = fun(){
                showTimePicker.value = false
            }
            val clearFilter = fun(){
                filterStartDate.value = ""
                filterEndDate.value = ""
                currentTimeRange.value = _uA()
                loadData(true)
                uni_showToast(ShowToastOptions(title = "已显示全部", icon = "none"))
            }
            val onRefresh = fun(){
                loadData(true)
            }
            val onLoadMore = fun(){
                loadData(false)
            }
            onLoad(fun(_options){
                loadData(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "投递记录")),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "refresher-enabled" to true, "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to onRefresh, "onScrolltolower" to onLoadMore), _uA(
                        _cE("view", _uM("class" to "px-4 pb-4"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between py-4"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-2", "onClick" to handleHeaderClick), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-yuyue", "size" to 30, "color" to "#374151")),
                                    _cE("text", _uM("class" to "text-lg font-bold text-gray-800"), _tD(if (hasText(filterStartDate.value) && hasText(filterEndDate.value)) {
                                        "" + filterStartDate.value + " - " + filterEndDate.value
                                    } else {
                                        "全部时间"
                                    }
                                    ), 1)
                                )),
                                if (isTrue(hasText(filterStartDate.value) || hasText(filterEndDate.value))) {
                                    _cE("view", _uM("key" to 0, "class" to "bg-gray-200 px-4 py-2 rounded-full", "onClick" to clearFilter), _uA(
                                        _cE("text", _uM("class" to "text-xs text-gray-500"), "清除筛选")
                                    ))
                                } else {
                                    _cC("v-if", true)
                                }
                            )),
                            if (isTrue(groups.value.length === 0 && !isRefreshing.value)) {
                                _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "description" to "暂无投递记录"))
                            } else {
                                _cE(Fragment, _uM("key" to 1), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(groups.value, fun(group, __key, __index, _cached): Any {
                                        return _cE(Fragment, _uM("key" to group.date), _uA(
                                            _cE("view", _uM("class" to "pb-4 pt-1"), _uA(
                                                _cE("text", _uM("class" to "text-sm text-gray-500"), _tD(group.date), 1)
                                            )),
                                            _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(group.items, fun(item, __key, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to item.id, "class" to "bg-white rounded-lg p-4 flex flex-col gap-4 shadow-sm"), _uA(
                                                        _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), _tD(item.jobTitle), 1),
                                                            _cE("text", _uM("class" to "text-base font-bold text-green-600"), _tD(item.salary), 1)
                                                        )),
                                                        _cE("text", _uM("class" to "text-xs text-gray-500"), _tD(item.company), 1),
                                                        if (item.tags.length > 0) {
                                                            _cE("view", _uM("key" to 0, "class" to "flex flex-row flex-wrap gap-2 items-center pt-1"), _uA(
                                                                _cE(Fragment, null, RenderHelpers.renderList(item.tags, fun(tag, tIndex, __index, _cached): Any {
                                                                    return _cE("view", _uM("key" to tIndex, "class" to "bg-green-50 px-4 py-2 rounded-sm"), _uA(
                                                                        _cE("text", _uM("class" to "text-xs text-green-500"), _tD(tag), 1)
                                                                    ))
                                                                }), 128)
                                                            ))
                                                        } else {
                                                            _cC("v-if", true)
                                                        }
                                                        ,
                                                        _cE("view", _uM("class" to "flex flex-row justify-between items-center pt-1"), _uA(
                                                            _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.areaText), 1),
                                                            _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(item.deliverTime), 1)
                                                        ))
                                                    ))
                                                }
                                                ), 128)
                                            ))
                                        ), 64)
                                    }
                                    ), 128),
                                    if (groups.value.length > 0) {
                                        _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center py-4"), _uA(
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
                                ), 64)
                            }
                        ))
                    ), 40, _uA(
                        "refresher-triggered"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTDateTimePickerIndexClass), _uM("modelValue" to showTimePicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showTimePicker.value = `$event`
                    }
                    , "range-value" to currentTimeRange.value, "onUpdate:rangeValue" to fun(`$event`: UTSArray<Number>){
                        currentTimeRange.value = `$event`
                    }
                    , "mode" to "date", "range" to true, "title" to "选择时间段", "start-text" to "开始时间", "end-text" to "结束时间", "active-color" to "#00897b", "confirm-color" to "#00897b", "onConfirmRange" to handleTimeRangeConfirm, "onCancel" to handleTimeCancel), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "range-value",
                        "onUpdate:rangeValue"
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
