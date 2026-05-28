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
open class GenUniModulesTangUiXComponentsTDateTimePickerIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var mode: String by `$props`
    open var range: Boolean by `$props`
    open var title: String? by `$props`
    open var minDate: Number? by `$props`
    open var maxDate: Number? by `$props`
    open var confirmText: String? by `$props`
    open var cancelText: String? by `$props`
    open var itemHeight: String by `$props`
    open var visibleItemCount: Number by `$props`
    open var showToolbar: Boolean by `$props`
    open var activeColor: String by `$props`
    open var activeTextColor: String by `$props`
    open var todayColor: String by `$props`
    open var confirmColor: String by `$props`
    open var rangeColor: String by `$props`
    open var startText: String by `$props`
    open var endText: String by `$props`
    open var modelValue: Any? by `$props`
    open var value: Any? by `$props`
    open var rangeValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTDateTimePickerIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTDateTimePickerIndex
            val _cache = __ins.renderCache
            val props = __props
            val visible = useModel<Any>(__ins.props, "modelValue") as Ref<Boolean>
            val selectedValue = useModel<Any>(__ins.props, "value") as Ref<Number>
            val selectedRangeValue = useModel<Any>(__ins.props, "rangeValue") as Ref<TDateTimePickerRangeValue?>
            val rendered = ref(false)
            val showOverlay = ref(false)
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val t = useI18n().t
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val getDefaultMinDate = fun(): Number {
                val now = Date()
                return Date(now.getFullYear() - 100, 0, 1).getTime()
            }
            val getDefaultMaxDate = fun(): Number {
                val now = Date()
                return Date(now.getFullYear() + 100, 11, 31, 23, 59).getTime()
            }
            val minDate = computed<Number>(fun(): Number {
                return props.minDate ?: getDefaultMinDate()
            }
            )
            val maxDate = computed<Number>(fun(): Number {
                return props.maxDate ?: getDefaultMaxDate()
            }
            )
            val displayTitle = computed<String>(fun(): String {
                val title = props.title
                if (hasTextValue(title)) {
                    return title as String
                }
                return t("datetimePicker.title")
            }
            )
            val displayConfirmText = computed<String>(fun(): String {
                val confirmText = props.confirmText
                if (hasTextValue(confirmText)) {
                    return confirmText as String
                }
                return t("datetimePicker.confirmText")
            }
            )
            val displayCancelText = computed<String>(fun(): String {
                val cancelText = props.cancelText
                if (hasTextValue(cancelText)) {
                    return cancelText as String
                }
                return t("datetimePicker.cancelText")
            }
            )
            val viewYear = ref<Number>(Date().getFullYear())
            val viewMonth = ref<Number>(Date().getMonth() + 1)
            val selectedYear = ref<Number>(Date().getFullYear())
            val selectedMonth = ref<Number>(Date().getMonth() + 1)
            val selectedDay = ref<Number>(Date().getDate())
            val selectedHour = ref<Number>(0)
            val selectedMinute = ref<Number>(0)
            val panelMode = ref<String>("date")
            val rangeStart = ref<Number?>(null)
            val rangeEnd = ref<Number?>(null)
            val selectingRange = ref<String>("start")
            val weekDays = computed<UTSArray<String>>(fun(): UTSArray<String> {
                return _uA(
                    "日",
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六"
                )
            }
            )
            val monthNames = _uA(
                "1月",
                "2月",
                "3月",
                "4月",
                "5月",
                "6月",
                "7月",
                "8月",
                "9月",
                "10月",
                "11月",
                "12月"
            )
            val minYear = computed<Number>(fun(): Number {
                return Date(minDate.value).getFullYear()
            }
            )
            val maxYear = computed<Number>(fun(): Number {
                return Date(maxDate.value).getFullYear()
            }
            )
            val yearList = computed<UTSArray<Number>>(fun(): UTSArray<Number> {
                val years: UTSArray<Number> = _uA()
                val startYear = Math.floor(viewYear.value / 10) * 10
                run {
                    var i = startYear
                    while(i < startYear + 12){
                        if (i >= minYear.value && i <= maxYear.value) {
                            years.push(i)
                        }
                        i++
                    }
                }
                return years
            }
            )
            val getDaysInMonth = fun(year: Number, month: Number): Number {
                return Date(year, month, 0).getDate()
            }
            val getFirstDayOfMonth = fun(year: Number, month: Number): Number {
                return Date(year, month - 1, 1).getDay()
            }
            val isNumberRangeArray = fun(value: TDateTimePickerRangeValue?): Boolean {
                if (!UTSArray.isArray(value)) {
                    return false
                }
                val arr = value as TDateTimePickerRangeNumberArray
                if (arr.length < 2) {
                    return false
                }
                return UTSAndroid.`typeof`(arr[0]) === "number" && UTSAndroid.`typeof`(arr[1]) === "number"
            }
            val isStringRangeArray = fun(value: TDateTimePickerRangeValue?): Boolean {
                if (!UTSArray.isArray(value)) {
                    return false
                }
                val arr = value as TDateTimePickerRangeStringArray
                if (arr.length < 2) {
                    return false
                }
                return UTSAndroid.`typeof`(arr[0]) === "string" && UTSAndroid.`typeof`(arr[1]) === "string"
            }
            val isNumberRangeObject = fun(value: TDateTimePickerRangeValue?): Boolean {
                if (value == null || UTSArray.isArray(value) || UTSAndroid.`typeof`(value) !== "object") {
                    return false
                }
                val rangeValue = value as TDateTimePickerRangeNumberObject
                return UTSAndroid.`typeof`(rangeValue.start) === "number" && UTSAndroid.`typeof`(rangeValue.end) === "number"
            }
            val isStringRangeObject = fun(value: TDateTimePickerRangeValue?): Boolean {
                if (value == null || UTSArray.isArray(value) || UTSAndroid.`typeof`(value) !== "object") {
                    return false
                }
                val rangeValue = value as TDateTimePickerRangeStringObject
                return UTSAndroid.`typeof`(rangeValue.start) === "string" && UTSAndroid.`typeof`(rangeValue.end) === "string"
            }
            val normalizeRangeTimestamp = fun(value: Any): Number {
                if (UTSAndroid.`typeof`(value) === "number") {
                    return value as Number
                }
                if (UTSAndroid.`typeof`(value) === "string" && value as String !== "") {
                    return Date(value as String).getTime()
                }
                return 0
            }
            val parseRangeValue = fun(value: TDateTimePickerRangeValue?): RangeParseResult? {
                if (isNumberRangeArray(value)) {
                    val arr = value as TDateTimePickerRangeNumberArray
                    val startValue = normalizeRangeTimestamp(arr[0])
                    val endValue = normalizeRangeTimestamp(arr[1])
                    if (startValue > 0 && endValue > 0) {
                        return RangeParseResult(start = startValue, end = endValue)
                    }
                    return null
                }
                if (isStringRangeArray(value)) {
                    val arr = value as TDateTimePickerRangeStringArray
                    val startValue = normalizeRangeTimestamp(arr[0])
                    val endValue = normalizeRangeTimestamp(arr[1])
                    if (startValue > 0 && endValue > 0) {
                        return RangeParseResult(start = startValue, end = endValue)
                    }
                    return null
                }
                if (isNumberRangeObject(value)) {
                    val rangeValue = value as TDateTimePickerRangeNumberObject
                    val startValue = normalizeRangeTimestamp(rangeValue.start)
                    val endValue = normalizeRangeTimestamp(rangeValue.end)
                    if (startValue > 0 && endValue > 0) {
                        return RangeParseResult(start = startValue, end = endValue)
                    }
                }
                if (isStringRangeObject(value)) {
                    val rangeValue = value as TDateTimePickerRangeStringObject
                    val startValue = normalizeRangeTimestamp(rangeValue.start)
                    val endValue = normalizeRangeTimestamp(rangeValue.end)
                    if (startValue > 0 && endValue > 0) {
                        return RangeParseResult(start = startValue, end = endValue)
                    }
                }
                return null
            }
            val dateToTimestamp = fun(year: Number, month: Number, day: Number): Number {
                return Date(year, month - 1, day).getTime()
            }
            val isRangeStartDate = fun(year: Number, month: Number, day: Number): Boolean {
                if (!props.range || rangeStart.value == null) {
                    return false
                }
                val startDate = Date(rangeStart.value as Number)
                return startDate.getFullYear() === year && startDate.getMonth() + 1 === month && startDate.getDate() === day
            }
            val isRangeEndDate = fun(year: Number, month: Number, day: Number): Boolean {
                if (!props.range || rangeEnd.value == null) {
                    return false
                }
                val endDate = Date(rangeEnd.value as Number)
                return endDate.getFullYear() === year && endDate.getMonth() + 1 === month && endDate.getDate() === day
            }
            val isInRangeDate = fun(year: Number, month: Number, day: Number): Boolean {
                if (!props.range || rangeStart.value == null || rangeEnd.value == null) {
                    return false
                }
                val timestamp = dateToTimestamp(year, month, day)
                val startTemp = Date(rangeStart.value as Number)
                val startTimestamp = Date(startTemp.getFullYear(), startTemp.getMonth(), startTemp.getDate()).getTime()
                val endTemp = Date(rangeEnd.value as Number)
                val endTimestamp = Date(endTemp.getFullYear(), endTemp.getMonth(), endTemp.getDate()).getTime()
                return timestamp > startTimestamp && timestamp < endTimestamp
            }
            val getDayStyle = fun(item: DayItem): String {
                if (props.range) {
                    if (item.isRangeStart === true || item.isRangeEnd === true) {
                        return "background-color: " + props.activeColor + "; color: " + props.activeTextColor + ";"
                    }
                    if (item.isInRange === true) {
                        return "background-color: " + props.rangeColor + "; color: " + props.activeColor + ";"
                    }
                } else {
                    if (item.isSelected) {
                        return "background-color: " + props.activeColor + "; color: " + props.activeTextColor + ";"
                    }
                }
                if (item.isToday) {
                    return "color: " + props.todayColor + "; font-weight: 500;"
                }
                return ""
            }
            val getDayClass = fun(item: DayItem): String {
                val classes: UTSArray<String> = _uA()
                if (!item.isCurrentMonth) {
                    classes.push("t-datetime-picker__day--other")
                }
                if (item.isDisabled) {
                    classes.push("t-datetime-picker__day--disabled")
                }
                if (item.isRangeStart === true) {
                    classes.push("t-datetime-picker__day--range-start")
                }
                if (item.isRangeEnd === true) {
                    classes.push("t-datetime-picker__day--range-end")
                }
                if (item.isInRange === true) {
                    classes.push("t-datetime-picker__day--in-range")
                }
                return classes.join(" ")
            }
            val getSelectedItemStyle = fun(isSelected: Boolean): String {
                if (isSelected) {
                    return "background-color: " + props.activeColor + "; color: " + props.activeTextColor + ";"
                }
                return ""
            }
            val confirmBtnStyle = computed<String>(fun(): String {
                return "color: " + props.confirmColor + ";"
            }
            )
            val getTimeItemStyle = fun(isSelected: Boolean): String {
                if (isSelected) {
                    return "background-color: " + props.activeColor + ";"
                }
                return ""
            }
            val getMaskClass = fun(): String {
                return if (!showOverlay.value) {
                    "t-datetime-picker__mask--hidden"
                } else {
                    ""
                }
            }
            val getContainerClass = fun(): String {
                return if (!showOverlay.value) {
                    "t-datetime-picker__container--hidden"
                } else {
                    ""
                }
            }
            val getRangeItemClass = fun(type: String): String {
                if (selectingRange.value === type) {
                    return "t-datetime-picker__range-item--active"
                }
                return ""
            }
            val getTimeItemClass = fun(isSelected: Boolean): String {
                return if (isSelected) {
                    "t-datetime-picker__time-item--selected"
                } else {
                    ""
                }
            }
            fun gen_isDateDisabled_fn(year: Number, month: Number, day: Number): Boolean {
                val date = Date(year, month - 1, day).getTime()
                return date < minDate.value || date > maxDate.value
            }
            val isDateDisabled = ::gen_isDateDisabled_fn
            fun gen_doClose_fn(): Unit {
                showOverlay.value = false
                setTimeout(fun(){
                    rendered.value = false
                }
                , 350)
            }
            val doClose = ::gen_doClose_fn
            fun gen_handleCancel_fn(): Unit {
                showOverlay.value = false
                setTimeout(fun(){
                    rendered.value = false
                    visible.value = false
                    emit("cancel")
                }
                , 350)
            }
            val handleCancel = ::gen_handleCancel_fn
            fun gen_resetRange_fn(): Unit {
                rangeStart.value = null
                rangeEnd.value = null
                selectingRange.value = "start"
            }
            val resetRange = ::gen_resetRange_fn
            val calendarDays = computed<UTSArray<DayItem>>(fun(): UTSArray<TDateTimePickerDayItem> {
                val days: UTSArray<DayItem> = _uA()
                val year = viewYear.value
                val month = viewMonth.value
                val daysInMonth = getDaysInMonth(year, month)
                val firstDayOfWeek = getFirstDayOfMonth(year, month)
                val prevMonth = if (month === 1) {
                    12
                } else {
                    month - 1
                }
                val prevYear = if (month === 1) {
                    year - 1
                } else {
                    year
                }
                val daysInPrevMonth = getDaysInMonth(prevYear, prevMonth)
                run {
                    var i = firstDayOfWeek - 1
                    while(i >= 0){
                        val day = daysInPrevMonth - i
                        days.push(TDateTimePickerDayItem(day = day, month = prevMonth, year = prevYear, isCurrentMonth = false, isToday = false, isSelected = false, isDisabled = isDateDisabled(prevYear, prevMonth, day), isRangeStart = isRangeStartDate(prevYear, prevMonth, day), isRangeEnd = isRangeEndDate(prevYear, prevMonth, day), isInRange = isInRangeDate(prevYear, prevMonth, day)))
                        i--
                    }
                }
                val today = Date()
                val todayYear = today.getFullYear()
                val todayMonth = today.getMonth() + 1
                val todayDay = today.getDate()
                run {
                    var day: Number = 1
                    while(day <= daysInMonth){
                        days.push(TDateTimePickerDayItem(day = day, month = month, year = year, isCurrentMonth = true, isToday = year === todayYear && month === todayMonth && day === todayDay, isSelected = !props.range && year === selectedYear.value && month === selectedMonth.value && day === selectedDay.value, isDisabled = isDateDisabled(year, month, day), isRangeStart = isRangeStartDate(year, month, day), isRangeEnd = isRangeEndDate(year, month, day), isInRange = isInRangeDate(year, month, day)))
                        day++
                    }
                }
                val nextMonth = if (month === 12) {
                    1
                } else {
                    month + 1
                }
                val nextYear = if (month === 12) {
                    year + 1
                } else {
                    year
                }
                val remainingDays = 42 - days.length
                run {
                    var day: Number = 1
                    while(day <= remainingDays){
                        days.push(TDateTimePickerDayItem(day = day, month = nextMonth, year = nextYear, isCurrentMonth = false, isToday = false, isSelected = false, isDisabled = isDateDisabled(nextYear, nextMonth, day), isRangeStart = isRangeStartDate(nextYear, nextMonth, day), isRangeEnd = isRangeEndDate(nextYear, nextMonth, day), isInRange = isInRangeDate(nextYear, nextMonth, day)))
                        day++
                    }
                }
                return days
            }
            )
            fun gen_isMonthDisabled_fn(month: Number): Boolean {
                val year = viewYear.value
                val minD = Date(minDate.value)
                val maxD = Date(maxDate.value)
                if (year < minD.getFullYear() || year > maxD.getFullYear()) {
                    return true
                }
                if (year === minD.getFullYear() && month < minD.getMonth() + 1) {
                    return true
                }
                if (year === maxD.getFullYear() && month > maxD.getMonth() + 1) {
                    return true
                }
                return false
            }
            val isMonthDisabled = ::gen_isMonthDisabled_fn
            fun gen_isYearDisabled_fn(year: Number): Boolean {
                return year < minYear.value || year > maxYear.value
            }
            val isYearDisabled = ::gen_isYearDisabled_fn
            fun gen_getYearItemClass_fn(year: Number): String {
                val classes: UTSArray<String> = _uA()
                if (year === selectedYear.value) {
                    classes.push("t-datetime-picker__year-item--selected")
                }
                if (isYearDisabled(year)) {
                    classes.push("t-datetime-picker__year-item--disabled")
                }
                return classes.join(" ")
            }
            val getYearItemClass = ::gen_getYearItemClass_fn
            fun gen_getMonthItemClass_fn(month: Number): String {
                val classes: UTSArray<String> = _uA()
                if (month === selectedMonth.value && viewYear.value === selectedYear.value) {
                    classes.push("t-datetime-picker__month-item--selected")
                }
                if (isMonthDisabled(month)) {
                    classes.push("t-datetime-picker__month-item--disabled")
                }
                return classes.join(" ")
            }
            val getMonthItemClass = ::gen_getMonthItemClass_fn
            val padZero = fun(num: Number): String {
                return if (num < 10) {
                    "0" + num
                } else {
                    "" + num
                }
            }
            val headerTitle = computed<String>(fun(): String {
                if (panelMode.value === "year") {
                    val startYear = Math.floor(viewYear.value / 10) * 10
                    return "" + startYear + " - " + (startYear + 11)
                }
                return "" + viewYear.value + "年" + padZero(viewMonth.value) + "月"
            }
            )
            fun gen_getCurrentTimestamp_fn(): Number {
                val year = selectedYear.value
                val month = selectedMonth.value
                val day = if (props.mode === "month") {
                    1
                } else {
                    selectedDay.value
                }
                val hour = if (props.mode === "datetime") {
                    selectedHour.value
                } else {
                    0
                }
                val minute = if (props.mode === "datetime") {
                    selectedMinute.value
                } else {
                    0
                }
                return Date(year, month - 1, day, hour, minute).getTime()
            }
            val getCurrentTimestamp = ::gen_getCurrentTimestamp_fn
            fun gen_emitChange_fn(): Unit {
                val timestamp = getCurrentTimestamp()
                emit("change", timestamp)
            }
            val emitChange = ::gen_emitChange_fn
            val handlePrev = fun(): Unit {
                if (panelMode.value === "year") {
                    viewYear.value -= 10
                } else if (panelMode.value === "month" || props.mode === "month") {
                    viewYear.value -= 1
                } else {
                    if (viewMonth.value === 1) {
                        viewMonth.value = 12
                        viewYear.value -= 1
                    } else {
                        viewMonth.value -= 1
                    }
                }
            }
            val handleNext = fun(): Unit {
                if (panelMode.value === "year") {
                    viewYear.value += 10
                } else if (panelMode.value === "month" || props.mode === "month") {
                    viewYear.value += 1
                } else {
                    if (viewMonth.value === 12) {
                        viewMonth.value = 1
                        viewYear.value += 1
                    } else {
                        viewMonth.value += 1
                    }
                }
            }
            val handleHeaderClick = fun(): Unit {
                if (props.mode === "month") {
                    panelMode.value = if (panelMode.value === "year") {
                        "month"
                    } else {
                        "year"
                    }
                } else {
                    if (panelMode.value === "date") {
                        panelMode.value = "month"
                    } else if (panelMode.value === "month") {
                        panelMode.value = "year"
                    } else {
                        panelMode.value = "date"
                    }
                }
            }
            val handleYearSelect = fun(year: Number): Unit {
                if (isYearDisabled(year)) {
                    return
                }
                viewYear.value = year
                selectedYear.value = year
                panelMode.value = "month"
            }
            val handleMonthSelect = fun(month: Number): Unit {
                if (isMonthDisabled(month)) {
                    return
                }
                viewMonth.value = month
                selectedMonth.value = month
                if (props.mode === "month") {
                    emitChange()
                } else {
                    panelMode.value = "date"
                }
            }
            val handleDaySelect = fun(item: DayItem): Unit {
                if (item.isDisabled) {
                    return
                }
                if (props.range) {
                    val timestamp = if (props.mode === "datetime") {
                        Date(item.year, item.month - 1, item.day, selectedHour.value, selectedMinute.value).getTime()
                    } else {
                        dateToTimestamp(item.year, item.month, item.day)
                    }
                    if (selectingRange.value === "start") {
                        rangeStart.value = timestamp
                        rangeEnd.value = null
                        selectingRange.value = "end"
                    } else {
                        val currentStartObj = Date(rangeStart.value as Number)
                        val currentStartTimestamp = Date(currentStartObj.getFullYear(), currentStartObj.getMonth(), currentStartObj.getDate()).getTime()
                        if (rangeStart.value != null && timestamp < currentStartTimestamp) {
                            rangeEnd.value = rangeStart.value
                            rangeStart.value = timestamp
                        } else {
                            rangeEnd.value = timestamp
                        }
                        selectingRange.value = "start"
                    }
                    viewYear.value = item.year
                    viewMonth.value = item.month
                } else {
                    selectedYear.value = item.year
                    selectedMonth.value = item.month
                    selectedDay.value = item.day
                    viewYear.value = item.year
                    viewMonth.value = item.month
                    emitChange()
                }
            }
            val hourList = computed<UTSArray<Number>>(fun(): UTSArray<Number> {
                val hours: UTSArray<Number> = _uA()
                run {
                    var i: Number = 0
                    while(i <= 23){
                        hours.push(i)
                        i++
                    }
                }
                return hours
            }
            )
            val minuteList = computed<UTSArray<Number>>(fun(): UTSArray<Number> {
                val minutes: UTSArray<Number> = _uA()
                run {
                    var i: Number = 0
                    while(i <= 59){
                        minutes.push(i)
                        i++
                    }
                }
                return minutes
            }
            )
            val hourScrollTop = ref<Number>(0)
            val minuteScrollTop = ref<Number>(0)
            val updateTimeScrollPosition = fun(): Unit {
                hourScrollTop.value = selectedHour.value * 40
                minuteScrollTop.value = selectedMinute.value * 40
            }
            val syncRangeTime = fun(): Unit {
                if (selectingRange.value === "end" && rangeStart.value != null) {
                    val d = Date(rangeStart.value as Number)
                    rangeStart.value = Date(d.getFullYear(), d.getMonth(), d.getDate(), selectedHour.value, selectedMinute.value).getTime()
                } else if (selectingRange.value === "start" && rangeEnd.value != null) {
                    val d = Date(rangeEnd.value as Number)
                    rangeEnd.value = Date(d.getFullYear(), d.getMonth(), d.getDate(), selectedHour.value, selectedMinute.value).getTime()
                }
            }
            val handleHourSelect = fun(hour: Number): Unit {
                selectedHour.value = hour
                nextTick(fun(){
                    hourScrollTop.value = hour * 40
                }
                )
                if (props.range && props.mode === "datetime") {
                    syncRangeTime()
                } else {
                    emitChange()
                }
            }
            val handleMinuteSelect = fun(minute: Number): Unit {
                selectedMinute.value = minute
                nextTick(fun(){
                    minuteScrollTop.value = minute * 40
                }
                )
                if (props.range && props.mode === "datetime") {
                    syncRangeTime()
                } else {
                    emitChange()
                }
            }
            val formatTimestamp = fun(timestamp: Number): String {
                val date = Date(timestamp)
                val year = date.getFullYear()
                val month = padZero(date.getMonth() + 1)
                val day = padZero(date.getDate())
                val hour = padZero(date.getHours())
                val minute = padZero(date.getMinutes())
                if (props.mode === "month") {
                    return "" + year + "-" + month
                } else if (props.mode === "date") {
                    return "" + year + "-" + month + "-" + day
                } else {
                    return "" + year + "-" + month + "-" + day + " " + hour + ":" + minute
                }
            }
            val setValueByTimestamp = fun(reassignedTimestamp: Number): Unit {
                var timestamp = reassignedTimestamp
                if (timestamp <= 0) {
                    timestamp = Date.now()
                }
                val date = Date(timestamp)
                selectedYear.value = date.getFullYear()
                selectedMonth.value = date.getMonth() + 1
                selectedDay.value = date.getDate()
                selectedHour.value = date.getHours()
                selectedMinute.value = date.getMinutes()
                viewYear.value = selectedYear.value
                viewMonth.value = selectedMonth.value
            }
            watch(visible, fun(newVal: Boolean){
                if (newVal) {
                    rendered.value = true
                    showOverlay.value = false
                    if (props.range) {
                        val parsedRange = parseRangeValue(selectedRangeValue.value)
                        if (parsedRange != null) {
                            rangeStart.value = parsedRange.start
                            rangeEnd.value = parsedRange.end
                            selectingRange.value = "start"
                            val startDate = Date(rangeStart.value as Number)
                            viewYear.value = startDate.getFullYear()
                            viewMonth.value = startDate.getMonth() + 1
                            if (props.mode === "datetime") {
                                val echoDate = Date(rangeEnd.value as Number)
                                selectedHour.value = echoDate.getHours()
                                selectedMinute.value = echoDate.getMinutes()
                            }
                        } else {
                            resetRange()
                            setValueByTimestamp(Date.now())
                        }
                    } else {
                        setValueByTimestamp(if (selectedValue.value !== 0) {
                            selectedValue.value
                        } else {
                            Date.now()
                        })
                    }
                    if (props.mode === "month") {
                        panelMode.value = "month"
                    } else {
                        panelMode.value = "date"
                    }
                    nextTick(fun(){
                        setTimeout(fun(){
                            showOverlay.value = true
                            if (props.mode === "datetime") {
                                updateTimeScrollPosition()
                            }
                        }, 10)
                    })
                } else {
                    if (rendered.value) {
                        doClose()
                    }
                }
            }
            )
            val handleMaskClick = fun(): Unit {
                handleCancel()
            }
            val handleConfirm = fun(): Unit {
                if (props.range) {
                    if (rangeStart.value != null && rangeEnd.value != null) {
                        val formatted = _uA(
                            formatTimestamp(rangeStart.value as Number),
                            formatTimestamp(rangeEnd.value as Number)
                        )
                        var emitValue: TDateTimePickerRangeValue? = null
                        if (isNumberRangeArray(selectedRangeValue.value) || isStringRangeArray(selectedRangeValue.value)) {
                            emitValue = formatted
                            selectedRangeValue.value = formatted
                        } else {
                            val rangeValue = TDateTimePickerRangeNumberObject(start = rangeStart.value!!, end = rangeEnd.value!!)
                            emitValue = rangeValue
                            selectedRangeValue.value = rangeValue
                        }
                        showOverlay.value = false
                        setTimeout(fun(){
                            rendered.value = false
                            visible.value = false
                            emit("confirmRange", emitValue as TDateTimePickerRangeValue, formatted)
                        }
                        , 350)
                    }
                } else {
                    val timestamp = getCurrentTimestamp()
                    val formatted = formatTimestamp(timestamp)
                    selectedValue.value = timestamp
                    showOverlay.value = false
                    setTimeout(fun(){
                        rendered.value = false
                        visible.value = false
                        emit("confirm", timestamp, formatted)
                    }
                    , 350)
                }
            }
            val handleToday = fun(): Unit {
                val now = Date()
                if (props.range) {
                    val todayTimestamp = dateToTimestamp(now.getFullYear(), now.getMonth() + 1, now.getDate())
                    rangeStart.value = todayTimestamp
                    rangeEnd.value = null
                    selectingRange.value = "end"
                } else {
                    selectedYear.value = now.getFullYear()
                    selectedMonth.value = now.getMonth() + 1
                    selectedDay.value = now.getDate()
                    emitChange()
                }
                viewYear.value = now.getFullYear()
                viewMonth.value = now.getMonth() + 1
            }
            return fun(): Any? {
                return if (isTrue(rendered.value)) {
                    _cE("view", _uM("key" to 0, "class" to "t-datetime-picker", "onTouchmove" to withModifiers(fun(){}, _uA(
                        "stop",
                        "prevent"
                    ))), _uA(
                        _cE("view", _uM("class" to _nC(_uA(
                            "t-datetime-picker__mask",
                            getMaskClass()
                        )), "onClick" to handleMaskClick, "onTouchmove" to withModifiers(fun(){}, _uA(
                            "stop",
                            "prevent"
                        ))), null, 34),
                        _cE("view", _uM("class" to _nC(_uA(
                            "t-datetime-picker__container",
                            getContainerClass()
                        )), "onTouchmove" to withModifiers(fun(){}, _uA(
                            "stop"
                        ))), _uA(
                            if (isTrue(_ctx.showToolbar)) {
                                _cE("view", _uM("key" to 0, "class" to "t-datetime-picker__toolbar"), _uA(
                                    _cE("view", _uM("class" to "t-datetime-picker__btn t-datetime-picker__btn--cancel", "onClick" to handleCancel), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__btn-text"), _tD(displayCancelText.value), 1)
                                    )),
                                    _cE("view", _uM("class" to "t-datetime-picker__title"), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__title-text"), _tD(displayTitle.value), 1)
                                    )),
                                    _cE("view", _uM("class" to "t-datetime-picker__btn t-datetime-picker__btn--confirm", "onClick" to handleConfirm), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__btn-text t-datetime-picker__btn-text--primary", "style" to _nS(confirmBtnStyle.value)), _tD(displayConfirmText.value), 5)
                                    ))
                                ))
                            } else {
                                _cC("v-if", true)
                            },
                            _cE("view", _uM("class" to "t-datetime-picker__header"), _uA(
                                _cE("view", _uM("class" to "t-datetime-picker__header-btn", "onClick" to handlePrev), _uA(
                                    _cE("text", _uM("class" to "t-datetime-picker__header-arrow"), "‹")
                                )),
                                _cE("view", _uM("class" to "t-datetime-picker__header-title", "onClick" to handleHeaderClick), _uA(
                                    _cE("text", _uM("class" to "t-datetime-picker__header-title-text"), _tD(headerTitle.value), 1)
                                )),
                                _cE("view", _uM("class" to "t-datetime-picker__header-btn", "onClick" to handleNext), _uA(
                                    _cE("text", _uM("class" to "t-datetime-picker__header-arrow"), "›")
                                ))
                            )),
                            if (isTrue(_ctx.range)) {
                                _cE("view", _uM("key" to 1, "class" to "t-datetime-picker__range-info"), _uA(
                                    _cE("view", _uM("class" to _nC(_uA(
                                        "t-datetime-picker__range-item",
                                        getRangeItemClass("start")
                                    ))), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__range-label"), _tD(_ctx.startText), 1),
                                        _cE("text", _uM("class" to "t-datetime-picker__range-value"), _tD(if (rangeStart.value != null) {
                                            formatTimestamp(rangeStart.value!!)
                                        } else {
                                            "请选择"
                                        }), 1)
                                    ), 2),
                                    _cE("text", _uM("class" to "t-datetime-picker__range-separator"), "~"),
                                    _cE("view", _uM("class" to _nC(_uA(
                                        "t-datetime-picker__range-item",
                                        getRangeItemClass("end")
                                    ))), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__range-label"), _tD(_ctx.endText), 1),
                                        _cE("text", _uM("class" to "t-datetime-picker__range-value"), _tD(if (rangeEnd.value != null) {
                                            formatTimestamp(rangeEnd.value!!)
                                        } else {
                                            "请选择"
                                        }), 1)
                                    ), 2)
                                ))
                            } else {
                                _cC("v-if", true)
                            },
                            if (panelMode.value === "year") {
                                _cE("view", _uM("key" to 2, "class" to "t-datetime-picker__panel"), _uA(
                                    _cE("view", _uM("class" to "t-datetime-picker__year-grid"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(yearList.value, fun(year, index, __index, _cached): Any {
                                            return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                                "t-datetime-picker__year-item",
                                                getYearItemClass(year)
                                            )), "onClick" to fun(){
                                                handleYearSelect(year)
                                            }), _uA(
                                                _cE("text", _uM("class" to "t-datetime-picker__year-text", "style" to _nS(getSelectedItemStyle(year === selectedYear.value))), _tD(year), 5)
                                            ), 10, _uA(
                                                "onClick"
                                            ))
                                        }), 128)
                                    ))
                                ))
                            } else {
                                if (isTrue(panelMode.value === "month" || _ctx.mode === "month")) {
                                    _cE("view", _uM("key" to 3, "class" to "t-datetime-picker__panel"), _uA(
                                        _cE("view", _uM("class" to "t-datetime-picker__month-grid"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(monthNames, fun(name, index, __index, _cached): Any {
                                                return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                                    "t-datetime-picker__month-item",
                                                    getMonthItemClass(index + 1)
                                                )), "onClick" to fun(){
                                                    handleMonthSelect(index + 1)
                                                }), _uA(
                                                    _cE("text", _uM("class" to "t-datetime-picker__month-text", "style" to _nS(getSelectedItemStyle((index + 1) === selectedMonth.value && viewYear.value === selectedYear.value))), _tD(name), 5)
                                                ), 10, _uA(
                                                    "onClick"
                                                ))
                                            }), 64)
                                        ))
                                    ))
                                } else {
                                    _cE("view", _uM("key" to 4, "class" to "t-datetime-picker__panel"), _uA(
                                        _cE("view", _uM("class" to "t-datetime-picker__weekdays"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(weekDays.value, fun(day, index, __index, _cached): Any {
                                                return _cE("view", _uM("key" to index, "class" to "t-datetime-picker__weekday"), _uA(
                                                    _cE("text", _uM("class" to "t-datetime-picker__weekday-text"), _tD(day), 1)
                                                ))
                                            }), 128)
                                        )),
                                        _cE("view", _uM("class" to "t-datetime-picker__days"), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(calendarDays.value, fun(item, index, __index, _cached): Any {
                                                return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                                    "t-datetime-picker__day",
                                                    getDayClass(item)
                                                )), "onClick" to fun(){
                                                    handleDaySelect(item)
                                                }), _uA(
                                                    renderSlot(_ctx.`$slots`, "day", _uM("item" to item), fun(): UTSArray<Any> {
                                                        return _uA(
                                                            _cE("text", _uM("class" to "t-datetime-picker__day-text", "style" to _nS(getDayStyle(item))), _tD(item.day), 5)
                                                        )
                                                    })
                                                ), 10, _uA(
                                                    "onClick"
                                                ))
                                            }), 128)
                                        ))
                                    ))
                                }
                            },
                            if (isTrue(_ctx.mode === "datetime" && panelMode.value === "date")) {
                                _cE("view", _uM("key" to 5, "class" to "t-datetime-picker__time"), _uA(
                                    _cE("view", _uM("class" to "t-datetime-picker__time-label"), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__time-label-text"), "选择时间")
                                    )),
                                    _cE("view", _uM("class" to "t-datetime-picker__time-picker"), _uA(
                                        _cE("scroll-view", _uM("class" to "t-datetime-picker__time-scroll", "scroll-y" to "", "scroll-top" to hourScrollTop.value, "scroll-with-animation" to true), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(hourList.value, fun(hour, __key, __index, _cached): Any {
                                                return _cE("view", _uM("key" to hour, "id" to ("hour-" + hour), "class" to _nC(_uA(
                                                    "t-datetime-picker__time-item",
                                                    getTimeItemClass(hour === selectedHour.value)
                                                )), "style" to _nS(getTimeItemStyle(hour === selectedHour.value)), "onClick" to fun(){
                                                    handleHourSelect(hour)
                                                }), _uA(
                                                    _cE("text", _uM("class" to "t-datetime-picker__time-item-text"), _tD(padZero(hour)), 1)
                                                ), 14, _uA(
                                                    "id",
                                                    "onClick"
                                                ))
                                            }), 128)
                                        ), 8, _uA(
                                            "scroll-top"
                                        )),
                                        _cE("text", _uM("class" to "t-datetime-picker__time-separator"), ":"),
                                        _cE("scroll-view", _uM("class" to "t-datetime-picker__time-scroll", "scroll-y" to "", "scroll-top" to minuteScrollTop.value, "scroll-with-animation" to true), _uA(
                                            _cE(Fragment, null, RenderHelpers.renderList(minuteList.value, fun(minute, __key, __index, _cached): Any {
                                                return _cE("view", _uM("key" to minute, "id" to ("minute-" + minute), "class" to _nC(_uA(
                                                    "t-datetime-picker__time-item",
                                                    getTimeItemClass(minute === selectedMinute.value)
                                                )), "style" to _nS(getTimeItemStyle(minute === selectedMinute.value)), "onClick" to fun(){
                                                    handleMinuteSelect(minute)
                                                }), _uA(
                                                    _cE("text", _uM("class" to "t-datetime-picker__time-item-text"), _tD(padZero(minute)), 1)
                                                ), 14, _uA(
                                                    "id",
                                                    "onClick"
                                                ))
                                            }), 128)
                                        ), 8, _uA(
                                            "scroll-top"
                                        ))
                                    ))
                                ))
                            } else {
                                _cC("v-if", true)
                            },
                            if (_ctx.mode !== "month") {
                                _cE("view", _uM("key" to 6, "class" to "t-datetime-picker__footer"), _uA(
                                    _cE("view", _uM("class" to "t-datetime-picker__footer-btn", "onClick" to handleToday), _uA(
                                        _cE("text", _uM("class" to "t-datetime-picker__footer-btn-text", "style" to _nS(confirmBtnStyle.value)), "今天", 4)
                                    ))
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                        ), 34)
                    ), 32)
                } else {
                    _cC("v-if", true)
                }
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("t-datetime-picker" to _pS(_uM("position" to "fixed", "top" to 0, "left" to 0, "right" to 0, "bottom" to 0, "zIndex" to 9999)), "t-datetime-picker__mask" to _pS(_uM("position" to "absolute", "top" to 0, "left" to 0, "right" to 0, "bottom" to 0, "backgroundColor" to "rgba(0,0,0,0.5)", "opacity" to 1, "transitionProperty" to "opacity", "transitionDuration" to "0.3s", "transitionTimingFunction" to "cubic-bezier(0.25,0.46,0.45,0.94)")), "t-datetime-picker__mask--hidden" to _pS(_uM("opacity" to 0)), "t-datetime-picker__container" to _pS(_uM("position" to "absolute", "left" to 0, "right" to 0, "bottom" to 0, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "zIndex" to 1, "transform" to "translateY(0)", "transitionProperty" to "transform", "transitionDuration" to "0.35s", "transitionTimingFunction" to "cubic-bezier(0.25,0.46,0.45,0.94)")), "t-datetime-picker__container--hidden" to _pS(_uM("transform" to "translateY(100%)")), "t-datetime-picker__toolbar" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#ebeef5")), "t-datetime-picker__btn" to _pS(_uM("paddingTop" to 8, "paddingRight" to 12, "paddingBottom" to 8, "paddingLeft" to 12, "cursor" to "pointer")), "t-datetime-picker__btn-text" to _pS(_uM("fontSize" to 16, "color" to "#606266")), "t-datetime-picker__btn-text--primary" to _pS(_uM("color" to "#409eff", "fontWeight" to "500")), "t-datetime-picker__title" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "t-datetime-picker__title-text" to _pS(_uM("fontSize" to 16, "fontWeight" to "500", "color" to "#303133")), "t-datetime-picker__header" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f0f0f0")), "t-datetime-picker__header-btn" to _pS(_uM("width" to 36, "height" to 36, "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6, "backgroundColor" to "#f5f7fa")), "t-datetime-picker__header-arrow" to _pS(_uM("fontSize" to 24, "color" to "#606266", "fontWeight" to "bold")), "t-datetime-picker__header-title" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "paddingTop" to 8, "paddingRight" to 16, "paddingBottom" to 8, "paddingLeft" to 16, "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6, "backgroundColor" to "#f5f7fa", "marginTop" to 0, "marginRight" to 12, "marginBottom" to 0, "marginLeft" to 12)), "t-datetime-picker__header-title-text" to _pS(_uM("fontSize" to 16, "fontWeight" to "500", "color" to "#303133")), "t-datetime-picker__panel" to _pS(_uM("paddingTop" to 8, "paddingRight" to 12, "paddingBottom" to 8, "paddingLeft" to 12)), "t-datetime-picker__weekdays" to _pS(_uM("display" to "flex", "flexDirection" to "row", "marginBottom" to 8)), "t-datetime-picker__weekday" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "height" to 36)), "t-datetime-picker__weekday-text" to _pS(_uM("fontSize" to 14, "color" to "#909399")), "t-datetime-picker__days" to _pS(_uM("display" to "flex", "flexDirection" to "row", "flexWrap" to "wrap")), "t-datetime-picker__day" to _pS(_uM("width" to "14.285%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "height" to 44, "cursor" to "pointer")), "t-datetime-picker__day-text" to _uM("" to _uM("width" to 36, "height" to 36, "lineHeight" to "36px", "textAlign" to "center", "fontSize" to 15, "color" to "#303133", "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%"), ".t-datetime-picker__day--other " to _uM("color" to "#c0c4cc"), ".t-datetime-picker__day--today " to _uM("color" to "#409eff", "fontWeight" to "500"), ".t-datetime-picker__day--selected " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__day--disabled " to _uM("color" to "#c0c4cc", "backgroundColor" to "rgba(0,0,0,0)"), ".t-datetime-picker__day--range-start " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__day--range-end " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__day--in-range " to _uM("color" to "#409eff")), "t-datetime-picker__day--disabled" to _pS(_uM("cursor" to "not-allowed")), "t-datetime-picker__day--range-start" to _uM("" to _uM("backgroundImage" to "linear-gradient(to right, transparent 50%, rgba(64, 158, 255, 0.1) 50%)", "backgroundColor" to "rgba(0,0,0,0)"), ".t-datetime-picker__day--range-end" to _uM("backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0)")), "t-datetime-picker__day--range-end" to _pS(_uM("backgroundImage" to "linear-gradient(to left, transparent 50%, rgba(64, 158, 255, 0.1) 50%)", "backgroundColor" to "rgba(0,0,0,0)")), "t-datetime-picker__day--in-range" to _pS(_uM("backgroundColor" to "rgba(64,158,255,0.1)")), "t-datetime-picker__year-grid" to _pS(_uM("display" to "flex", "flexDirection" to "row", "flexWrap" to "wrap", "paddingTop" to 8, "paddingRight" to 0, "paddingBottom" to 8, "paddingLeft" to 0)), "t-datetime-picker__month-grid" to _pS(_uM("display" to "flex", "flexDirection" to "row", "flexWrap" to "wrap", "paddingTop" to 8, "paddingRight" to 0, "paddingBottom" to 8, "paddingLeft" to 0)), "t-datetime-picker__year-item" to _pS(_uM("width" to "25%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "height" to 56, "cursor" to "pointer")), "t-datetime-picker__month-item" to _pS(_uM("width" to "25%", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "height" to 56, "cursor" to "pointer")), "t-datetime-picker__year-text" to _uM(".t-datetime-picker__year-item--selected " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__month-item--selected " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__year-item--disabled " to _uM("color" to "#c0c4cc"), ".t-datetime-picker__month-item--disabled " to _uM("color" to "#c0c4cc"), "" to _uM("fontSize" to 15, "color" to "#303133", "paddingTop" to 8, "paddingRight" to 16, "paddingBottom" to 8, "paddingLeft" to 16, "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "t-datetime-picker__month-text" to _uM(".t-datetime-picker__year-item--selected " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__month-item--selected " to _uM("backgroundColor" to "#409eff", "color" to "#ffffff"), ".t-datetime-picker__year-item--disabled " to _uM("color" to "#c0c4cc"), ".t-datetime-picker__month-item--disabled " to _uM("color" to "#c0c4cc"), "" to _uM("fontSize" to 15, "color" to "#303133", "paddingTop" to 8, "paddingRight" to 16, "paddingBottom" to 8, "paddingLeft" to 16, "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "t-datetime-picker__year-item--disabled" to _pS(_uM("cursor" to "not-allowed")), "t-datetime-picker__month-item--disabled" to _pS(_uM("cursor" to "not-allowed")), "t-datetime-picker__time" to _pS(_uM("paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#ebeef5")), "t-datetime-picker__time-label" to _pS(_uM("marginBottom" to 12)), "t-datetime-picker__time-label-text" to _pS(_uM("fontSize" to 14, "color" to "#606266")), "t-datetime-picker__time-picker" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center", "height" to 120)), "t-datetime-picker__time-scroll" to _pS(_uM("width" to 80, "height" to 120, "backgroundColor" to "#f5f7fa", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8)), "t-datetime-picker__time-item" to _pS(_uM("display" to "flex", "alignItems" to "center", "justifyContent" to "center", "height" to 40, "cursor" to "pointer")), "t-datetime-picker__time-item--selected" to _pS(_uM("backgroundColor" to "#409eff")), "t-datetime-picker__time-item-text" to _uM(".t-datetime-picker__time-item--selected " to _uM("color" to "#ffffff"), "" to _uM("fontSize" to 16, "color" to "#303133")), "t-datetime-picker__time-separator" to _pS(_uM("fontSize" to 24, "color" to "#303133", "marginTop" to 0, "marginRight" to 16, "marginBottom" to 0, "marginLeft" to 16)), "t-datetime-picker__footer" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center", "paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "borderTopWidth" to 1, "borderTopStyle" to "solid", "borderTopColor" to "#ebeef5")), "t-datetime-picker__footer-btn" to _pS(_uM("paddingTop" to 8, "paddingRight" to 24, "paddingBottom" to 8, "paddingLeft" to 24, "backgroundColor" to "#f5f7fa", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "t-datetime-picker__footer-btn-text" to _pS(_uM("fontSize" to 14, "color" to "#409eff")), "t-datetime-picker__range-info" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center", "paddingTop" to 12, "paddingRight" to 16, "paddingBottom" to 12, "paddingLeft" to 16, "backgroundColor" to "#f5f7fa", "marginTop" to 0, "marginRight" to 12, "marginBottom" to 0, "marginLeft" to 12, "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8)), "t-datetime-picker__range-item" to _pS(_uM("display" to "flex", "flexDirection" to "column", "alignItems" to "center", "paddingTop" to 8, "paddingRight" to 16, "paddingBottom" to 8, "paddingLeft" to 16, "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6, "minWidth" to 100)), "t-datetime-picker__range-item--active" to _pS(_uM("backgroundColor" to "rgba(64,158,255,0.1)")), "t-datetime-picker__range-label" to _pS(_uM("fontSize" to 12, "color" to "#909399", "marginBottom" to 4)), "t-datetime-picker__range-value" to _pS(_uM("fontSize" to 14, "color" to "#303133", "fontWeight" to "500")), "t-datetime-picker__range-separator" to _pS(_uM("fontSize" to 16, "color" to "#909399", "marginTop" to 0, "marginRight" to 12, "marginBottom" to 0, "marginLeft" to 12)), "@TRANSITION" to _uM("t-datetime-picker__mask" to _uM("property" to "opacity", "duration" to "0.3s", "timingFunction" to "cubic-bezier(0.25,0.46,0.45,0.94)"), "t-datetime-picker__container" to _uM("property" to "transform", "duration" to "0.35s", "timingFunction" to "cubic-bezier(0.25,0.46,0.45,0.94)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("confirm" to null, "confirmRange" to null, "cancel" to null, "change" to null, "update:modelValue" to null, "update:value" to null, "update:rangeValue" to null)
        var props = _nP(_uM("mode" to _uM("type" to "String", "required" to false, "default" to "date"), "range" to _uM("type" to "Boolean", "required" to false, "default" to false), "title" to _uM("type" to "String", "required" to false), "minDate" to _uM("type" to "Number", "required" to false), "maxDate" to _uM("type" to "Number", "required" to false), "confirmText" to _uM("type" to "String", "required" to false), "cancelText" to _uM("type" to "String", "required" to false), "itemHeight" to _uM("type" to "String", "required" to false, "default" to "44px"), "visibleItemCount" to _uM("type" to "Number", "required" to false, "default" to 5), "showToolbar" to _uM("type" to "Boolean", "required" to false, "default" to true), "activeColor" to _uM("type" to "String", "required" to false, "default" to "#409eff"), "activeTextColor" to _uM("type" to "String", "required" to false, "default" to "#ffffff"), "todayColor" to _uM("type" to "String", "required" to false, "default" to "#409eff"), "confirmColor" to _uM("type" to "String", "required" to false, "default" to "#409eff"), "rangeColor" to _uM("type" to "String", "required" to false, "default" to "rgba(64, 158, 255, 0.1)"), "startText" to _uM("type" to "String", "required" to false, "default" to "开始"), "endText" to _uM("type" to "String", "required" to false, "default" to "结束"), "modelValue" to _uM("default" to false), "value" to _uM("default" to 0), "rangeValue" to _uM("default" to null)))
        var propsNeedCastKeys = _uA(
            "mode",
            "range",
            "itemHeight",
            "visibleItemCount",
            "showToolbar",
            "activeColor",
            "activeTextColor",
            "todayColor",
            "confirmColor",
            "rangeColor",
            "startText",
            "endText",
            "modelValue",
            "value",
            "rangeValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
