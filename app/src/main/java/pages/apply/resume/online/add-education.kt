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
open class GenPagesApplyResumeOnlineAddEducation : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineAddEducation) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineAddEducation
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            val authStore = useAuthStore()
            val hasText = fun(value: Any?): Boolean {
                return value != null && value != ""
            }
            val isEditMode = ref(false)
            val submitting = ref(false)
            val formData = reactive<AddEditEducationParams>(AddEditEducationParams(Degree = "", SchoolMajor = "", SchoolId = 0, SchoolBeginTime = "", SchoolEndTime = ""))
            val schoolName = ref("")
            val degreeOptions = EDUCATION_OPTIONS
            val schoolList = ref(_uA<GetUniversityResult>())
            val schoolTotal = ref(0)
            val schoolLoading = ref(false)
            val schoolParams = reactive<GetUniversityParams>(GetUniversityParams(Page = 1, PageSize = 20, KeyWord = ""))
            val showDegreePicker = ref(false)
            val showSchoolPicker = ref(false)
            val schoolKeyword = ref("")
            val tempSchool = ref("")
            val tempSchoolId = ref(0)
            val showTimePicker = ref(false)
            val showInputPicker = ref(false)
            val inputType = ref("")
            val tempInputValue = ref("")
            val pageTitle = computed(fun(): String {
                return if (isEditMode.value) {
                    "修改教育经历"
                } else {
                    "添加教育经历"
                }
            }
            )
            val degreeLabel = computed(fun(): String {
                val item = degreeOptions.find(fun(opt): Boolean {
                    return opt.value === formData.Degree
                }
                )
                return if (item != null) {
                    item.label
                } else {
                    ""
                }
            }
            )
            val inputPopupTitle = computed(fun(): String {
                return if (inputType.value === "SchoolMajor") {
                    "专业"
                } else {
                    "在校经历"
                }
            }
            )
            val inputPopupPlaceholder = computed(fun(): String {
                return if (inputType.value === "SchoolMajor") {
                    "请输入专业名称"
                } else {
                    "请输入在校经历"
                }
            }
            )
            val isTextarea = computed(fun(): Boolean {
                return inputType.value === "ContentDetail"
            }
            )
            val loadSchoolList = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (schoolLoading.value) {
                            return@w1
                        }
                        schoolLoading.value = true
                        try {
                            val res = await(getUniversity(schoolParams))
                            val rawData = if (res != null) {
                                ((res as UTSJSONObject)["data"] as UTSArray<GetUniversityResult>?)
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
                                _uA<GetUniversityResult>()
                            }
                            val total = if (rawTotal != null) {
                                rawTotal
                            } else {
                                0
                            }
                            if (data.length > 0) {
                                schoolTotal.value = total
                                if (schoolParams.Page === 1) {
                                    schoolList.value = data
                                } else {
                                    schoolList.value = schoolList.value.concat(data)
                                }
                            }
                        }
                         catch (_: Throwable) {
                            uni_showToast(ShowToastOptions(title = "加载学校失败", icon = "none"))
                        }
                         finally {
                            schoolLoading.value = false
                        }
                })
            }
            val handleSchoolClick = fun(){
                schoolKeyword.value = ""
                tempSchool.value = schoolName.value
                tempSchoolId.value = formData.SchoolId
                showSchoolPicker.value = true
                schoolParams.Page = 1
                schoolParams.KeyWord = ""
                schoolList.value = _uA()
                loadSchoolList()
            }
            val handleSchoolSearch = fun(){
                schoolParams.Page = 1
                schoolParams.KeyWord = schoolKeyword.value
                schoolList.value = _uA()
                loadSchoolList()
            }
            val handleSchoolLoadMore = fun(){
                if (schoolLoading.value) {
                    return
                }
                if (schoolList.value.length >= schoolTotal.value) {
                    return
                }
                schoolParams.Page = schoolParams.Page + 1
                loadSchoolList()
            }
            val handleSchoolSelect = fun(item: GetUniversityResult){
                tempSchool.value = item.SchoolName
                tempSchoolId.value = item.Id
            }
            val confirmSchoolSelect = fun(){
                if (hasText(tempSchool.value)) {
                    schoolName.value = tempSchool.value
                    formData.SchoolId = tempSchoolId.value
                }
                showSchoolPicker.value = false
            }
            val handleDegreeClick = fun(){
                showDegreePicker.value = true
            }
            val handleDegreeSelect = fun(value: String){
                formData.Degree = value
                showDegreePicker.value = false
            }
            val handleMajorClick = fun(){
                inputType.value = "SchoolMajor"
                tempInputValue.value = formData.SchoolMajor
                showInputPicker.value = true
            }
            val handleExperienceClick = fun(){
                inputType.value = "ContentDetail"
                tempInputValue.value = formData.ContentDetail ?: ""
                showInputPicker.value = true
            }
            val confirmInput = fun(){
                if (inputType.value === "SchoolMajor") {
                    formData.SchoolMajor = tempInputValue.value
                } else if (inputType.value === "ContentDetail") {
                    formData.ContentDetail = tempInputValue.value
                }
                showInputPicker.value = false
            }
            val handleTimeClick = fun(){
                showTimePicker.value = true
            }
            val handleTimeRangeConfirm = fun(_value: Any, formatted: UTSArray<String>){
                if (formatted.length >= 2) {
                    formData.SchoolBeginTime = formatted[0]
                    formData.SchoolEndTime = formatted[1]
                }
                showTimePicker.value = false
            }
            val handleTimeCancel = fun(){
                showTimePicker.value = false
            }
            val handleSubmit = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (!hasText(schoolName.value)) {
                            uni_showToast(ShowToastOptions(title = "请输入学校", icon = "none"))
                            return@w1
                        }
                        if (!hasText(formData.Degree)) {
                            uni_showToast(ShowToastOptions(title = "请选择学历", icon = "none"))
                            return@w1
                        }
                        if (!hasText(formData.SchoolMajor)) {
                            uni_showToast(ShowToastOptions(title = "请输入专业", icon = "none"))
                            return@w1
                        }
                        submitting.value = true
                        try {
                            await(addEditEducation(formData))
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
                            await(deleteEducation(DeleteEducationParams(EducationalId = formData.EducationalId!!)))
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
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要删除这条教育经历吗？", confirmColor = "#0d9488", success = fun(res){
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
                        formData.EducationalId = id
                        val editing = resumeStore.editingEducation
                        schoolName.value = editing.SchoolName
                        formData.SchoolId = editing.SchoolId
                        formData.Degree = editing.DegreeId.toString(10)
                        formData.SchoolMajor = editing.SchoolMajor
                        formData.SchoolBeginTime = editing.SchoolBeginTime
                        formData.SchoolEndTime = editing.SchoolEndTime
                        formData.ContentDetail = editing.ContentDetail
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
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to handleSchoolClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "学校"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(schoolName.value))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请输入")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(schoolName.value), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to handleDegreeClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "学历"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.Degree))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(degreeLabel.value), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to handleMajorClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "专业"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.SchoolMajor))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请输入")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.SchoolMajor), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to handleTimeClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "时间段"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.SchoolBeginTime))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请选择")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.SchoolBeginTime) + " - " + _tD(formData.SchoolEndTime), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-between p-4 border-b border-gray-100", "onClick" to handleExperienceClick), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-800"), "在校经历"),
                            _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                if (isTrue(!hasText(formData.ContentDetail))) {
                                    _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-400"), "请输入")
                                } else {
                                    _cE("text", _uM("key" to 1, "class" to "text-sm text-gray-800"), _tD(formData.ContentDetail), 1)
                                }
                                ,
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 32, "color" to "#999"))
                            ))
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
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showSchoolPicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showSchoolPicker.value = `$event`
                    }
                    , "mode" to "bottom", "round" to "", "show-title" to false, "height" to "70%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full"), _uA(
                                _cE("view", _uM("class" to "flex flex-row px-4 py-4 border-b border-gray-100 items-center"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row flex-1 rounded-full"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTSearchBarIndexClass), _uM("modelValue" to schoolKeyword.value, "onUpdate:modelValue" to fun(`$event`: String){
                                            schoolKeyword.value = `$event`
                                        }
                                        , "background" to "transparent", "custom-class" to "flex-1", "placeholder" to "请输入学校名称", "onSearch" to handleSchoolSearch), null, 8, _uA(
                                            "modelValue",
                                            "onUpdate:modelValue"
                                        )),
                                        _cE("view", _uM("class" to "flex justify-center items-center w-40 bg-teal-700 text-white rounded-full h-20", "onClick" to handleSchoolSearch), "搜索")
                                    ))
                                )),
                                _cE("scroll-view", _uM("class" to "flex-1 overflow-hidden", "scroll-y" to "", "onScrolltolower" to handleSchoolLoadMore), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(schoolList.value, fun(item, index, __index, _cached): Any {
                                        return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                            "p-4 border-b border-gray-100",
                                            _uM("bg-gray-200" to (tempSchool.value === item.SchoolName))
                                        )), "onClick" to fun(){
                                            handleSchoolSelect(item)
                                        }
                                        ), _uA(
                                            _cE("text", _uM("class" to "text-sm text-gray-800"), _tD(item.SchoolName), 1)
                                        ), 10, _uA(
                                            "onClick"
                                        ))
                                    }
                                    ), 128),
                                    if (isTrue(schoolLoading.value)) {
                                        _cE("view", _uM("key" to 0, "class" to "flex items-center justify-center py-4"), _uA(
                                            _cE("text", _uM("class" to "text-sm text-gray-400"), "加载中...")
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                    ,
                                    if (isTrue(!schoolLoading.value && schoolList.value.length > 0 && schoolList.value.length >= schoolTotal.value)) {
                                        _cE("view", _uM("key" to 1, "class" to "flex items-center justify-center py-4"), _uA(
                                            _cE("text", _uM("class" to "text-sm text-gray-400"), "没有更多了")
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                    ,
                                    if (isTrue(!schoolLoading.value && schoolList.value.length === 0)) {
                                        _cE("view", _uM("key" to 2, "class" to "py-8 px-4 flex items-center justify-center"), _uA(
                                            _cE("text", _uM("class" to "text-sm text-gray-400"), "未找到匹配的学校")
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ), 32),
                                _cE("view", _uM("class" to "p-6"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to confirmSchoolSelect))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showDegreePicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showDegreePicker.value = `$event`
                    }
                    , "mode" to "bottom", "title" to "选择学历", "round" to ""), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTRadioGroupIndexClass), _uM("modelValue" to formData.Degree, "onUpdate:modelValue" to fun(`$event`: String){
                                    formData.Degree = `$event`
                                }
                                , "options" to unref(degreeOptions), "direction" to "vertical", "active-color" to "#0d9488", "onChange" to handleDegreeSelect), null, 8, _uA(
                                    "modelValue",
                                    "onUpdate:modelValue",
                                    "options"
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
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
                                        500
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
                    , "mode" to "date", "range" to true, "title" to "选择时间段", "start-text" to "开始时间", "end-text" to "结束时间", "active-color" to "#00897b", "confirm-color" to "#00897b", "onConfirmRange" to handleTimeRangeConfirm, "onCancel" to handleTimeCancel), null, 8, _uA(
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
                return _uM("t-radio-group__item" to _pS(_uM("flexDirection" to "row-reverse", "justifyContent" to "space-between")), "t-search-bar" to _pS(_uM("paddingTop" to 4, "paddingRight" to 12, "paddingBottom" to 4, "paddingLeft" to 12)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
