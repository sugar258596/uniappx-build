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
open class GenPagesApplyResumeOnlineAddWorkExperience : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineAddWorkExperience) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineAddWorkExperience
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            val authStore = useAuthStore()
            val hasText = fun(value: Any?): Boolean {
                return value != null && value != ""
            }
            val isEditMode = ref(false)
            val submitting = ref(false)
            val formData = reactive<AddEditWorkExperienceParams>(AddEditWorkExperienceParams(CompanyName = "", JobName = "", ContentDetail = "", JobBeginTime = "", JobEndTime = ""))
            val showInputPicker = ref(false)
            val inputType = ref<InputType>("CompanyName")
            val tempInputValue = ref("")
            val showTimePicker = ref(false)
            val timeRangeValues = ref(_uA<Number>())
            val pageTitle = computed(fun(): String {
                return if (isEditMode.value) {
                    "修改工作经历"
                } else {
                    "添加工作经历"
                }
            }
            )
            val inputPopupTitle = computed(fun(): String {
                if (inputType.value === "CompanyName") {
                    return "公司名称"
                }
                if (inputType.value === "JobName") {
                    return "职位名称"
                }
                return "工作内容"
            }
            )
            val inputPopupPlaceholder = computed(fun(): String {
                if (inputType.value === "CompanyName") {
                    return "请输入公司名称"
                }
                if (inputType.value === "JobName") {
                    return "请输入职位名称"
                }
                return "请输入工作内容"
            }
            )
            val isTextarea = computed(fun(): Boolean {
                return inputType.value === "ContentDetail"
            }
            )
            val getInputValue = fun(type: InputType): String {
                if (type === "CompanyName") {
                    return formData.CompanyName
                }
                if (type === "JobName") {
                    return formData.JobName
                }
                return formData.ContentDetail
            }
            val setInputValue = fun(type: InputType, value: String){
                if (type === "CompanyName") {
                    formData.CompanyName = value
                } else if (type === "JobName") {
                    formData.JobName = value
                } else {
                    formData.ContentDetail = value
                }
            }
            val updateTimeRangeValues = fun(){
                if (hasText(formData.JobBeginTime) && hasText(formData.JobEndTime)) {
                    var startStr = formData.JobBeginTime.replace(UTSRegExp("-", "g"), "/")
                    if (startStr.split("/").length === 2) {
                        startStr = startStr + "/01"
                    }
                    val start = Date(startStr).getTime()
                    var endStr = formData.JobEndTime.replace(UTSRegExp("-", "g"), "/")
                    if (endStr.split("/").length === 2) {
                        endStr = endStr + "/01"
                    }
                    val end = Date(endStr).getTime()
                    if (!UTSNumber.isNaN(start) && !UTSNumber.isNaN(end)) {
                        timeRangeValues.value = _uA(
                            start,
                            end
                        )
                        return
                    }
                }
                timeRangeValues.value = _uA()
            }
            val handleInputClick = fun(type: InputType){
                inputType.value = type
                tempInputValue.value = getInputValue(type)
                showInputPicker.value = true
            }
            val confirmInput = fun(){
                setInputValue(inputType.value, tempInputValue.value)
                showInputPicker.value = false
            }
            val handleTimeClick = fun(){
                showTimePicker.value = true
            }
            val handleTimeRangeConfirm = fun(_value: Any, formatted: UTSArray<String>){
                if (formatted.length >= 2) {
                    formData.JobBeginTime = formatted[0]
                    formData.JobEndTime = formatted[1]
                    updateTimeRangeValues()
                }
                showTimePicker.value = false
            }
            val handleTimeCancel = fun(){
                showTimePicker.value = false
            }
            val handleSubmit = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (!hasText(formData.CompanyName)) {
                            uni_showToast(ShowToastOptions(title = "请输入公司名称", icon = "none"))
                            return@w1
                        }
                        if (!hasText(formData.JobBeginTime)) {
                            uni_showToast(ShowToastOptions(title = "请选择在职时间", icon = "none"))
                            return@w1
                        }
                        if (!hasText(formData.JobName)) {
                            uni_showToast(ShowToastOptions(title = "请输入职位名称", icon = "none"))
                            return@w1
                        }
                        if (!hasText(formData.ContentDetail)) {
                            uni_showToast(ShowToastOptions(title = "请输入工作内容", icon = "none"))
                            return@w1
                        }
                        submitting.value = true
                        try {
                            await(addEditWorkExperience(formData))
                            uni_showToast(ShowToastOptions(title = "保存成功", icon = "success"))
                            val onlineId = authStore.userInfo?.OnlineId ?: 0
                            if (onlineId > 0) {
                                await(resumeStore.fetchResume(onlineId))
                            }
                            setTimeout(fun(){
                                uni_navigateBack(null)
                            }
                            , 1500)
                        }
                         catch (err: Throwable) {
                            val msg = if (err is UTSError) {
                                (err as UTSError).message
                            } else {
                                "保存失败"
                            }
                            uni_showToast(ShowToastOptions(title = msg, icon = "none"))
                        }
                         finally {
                            submitting.value = false
                        }
                })
            }
            val confirmDelete = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(deleteWorkExperience(DeleteWorkExperienceParams(WorkExperienceId = formData.WorkExperienceId!!)))
                            uni_showToast(ShowToastOptions(title = "删除成功", icon = "success"))
                            val onlineId = authStore.userInfo?.OnlineId ?: 0
                            if (onlineId > 0) {
                                await(resumeStore.fetchResume(onlineId))
                            }
                            setTimeout(fun(){
                                uni_navigateBack(null)
                            }
                            , 1500)
                        }
                         catch (err: Throwable) {
                            val msg = if (err is UTSError) {
                                (err as UTSError).message
                            } else {
                                "删除失败"
                            }
                            uni_showToast(ShowToastOptions(title = msg, icon = "none"))
                        }
                })
            }
            val handleDelete = fun(){
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要删除这条工作经历吗？", confirmColor = "#0d9488", success = fun(res){
                    if (res.confirm) {
                        confirmDelete()
                    }
                }
                ))
            }
            onLoad(fun(options: UTSJSONObject){
                val idStr = options.get("id") as String?
                if (idStr != null) {
                    val id = parseInt(idStr)
                    if (id > 0) {
                        isEditMode.value = true
                        formData.WorkExperienceId = id
                        val editing = resumeStore.editingWorkExperience
                        formData.CompanyName = editing.CompanyName ?: ""
                        formData.JobName = editing.JobName ?: ""
                        formData.ContentDetail = editing.ContentDetail ?: ""
                        formData.JobBeginTime = editing.JobBeginTime ?: ""
                        formData.JobEndTime = editing.JobEndTime ?: ""
                        updateTimeRangeValues()
                    }
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-col h-full bg-white"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to pageTitle.value), null, 8, _uA(
                        "title"
                    )),
                    _cE("view", _uM("class" to "flex-1 flex flex-col gap-4 px-4"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to fun(){
                            handleInputClick("CompanyName")
                        }
                        ), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "公司名称"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.CompanyName))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请输入")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.CompanyName), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        ), 8, _uA(
                            "onClick"
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to handleTimeClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "在职时间"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.JobBeginTime))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.JobBeginTime) + " - " + _tD(formData.JobEndTime), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to fun(){
                            handleInputClick("JobName")
                        }
                        ), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "职位名称"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.JobName))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.JobName), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        ), 8, _uA(
                            "onClick"
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to fun(){
                            handleInputClick("ContentDetail")
                        }
                        ), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "工作内容"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.ContentDetail))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请输入")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.ContentDetail), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        ), 8, _uA(
                            "onClick"
                        ))
                    )),
                    _cE("view", _uM("class" to "p-6"), _uA(
                        if (isTrue(isEditMode.value)) {
                            _cE("view", _uM("key" to 0, "class" to "flex flex-row gap-4"), _uA(
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "default", "text" to "删除", "size" to "large", "shape" to "round", "onClick" to handleDelete))
                                )),
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "loading" to submitting.value, "custom-class" to "t-default", "onClick" to handleSubmit), null, 8, _uA(
                                        "loading"
                                    ))
                                ))
                            ))
                        } else {
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 1, "type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "loading" to submitting.value, "custom-class" to "t-default", "onClick" to handleSubmit), null, 8, _uA(
                                "loading"
                            ))
                        }
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showInputPicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showInputPicker.value = `$event`
                    }
                    , "mode" to "bottom", "title" to inputPopupTitle.value, "round" to ""), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col p-4 h-full"), _uA(
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to tempInputValue.value, "onUpdate:modelValue" to fun(`$event`: String){
                                        tempInputValue.value = `$event`
                                    }
                                    , "type" to if (isTextarea.value) {
                                        "textarea"
                                    } else {
                                        "text"
                                    }
                                    , "placeholder" to inputPopupPlaceholder.value, "maxlength" to if (isTextarea.value) {
                                        1000
                                    } else {
                                        100
                                    }
                                    , "border" to "surround", "auto-height" to "", "clearable" to !isTextarea.value, "focus-border-color" to "#0f766e"), null, 8, _uA(
                                        "modelValue",
                                        "onUpdate:modelValue",
                                        "type",
                                        "placeholder",
                                        "maxlength",
                                        "clearable"
                                    ))
                                )),
                                _cE("view", _uM("class" to "mt-4"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to confirmInput))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "title"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTDateTimePickerIndexClass), _uM("modelValue" to showTimePicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showTimePicker.value = `$event`
                    }
                    , "range-value" to timeRangeValues.value, "onUpdate:rangeValue" to fun(`$event`: UTSArray<Number>){
                        timeRangeValues.value = `$event`
                    }
                    , "mode" to "date", "range" to true, "title" to "选择时间段", "start-text" to "入职时间", "end-text" to "离职时间", "active-color" to "#00897b", "confirm-color" to "#00897b", "onConfirmRange" to handleTimeRangeConfirm, "onCancel" to handleTimeCancel), null, 8, _uA(
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
