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
import io.dcloud.uniapp.extapi.chooseImage as uni_chooseImage
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyResumeOnlineProfile : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineProfile) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineProfile
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            val authStore = useAuthStore()
            val asText = fun(value: Any?): String {
                return if (value != null) {
                    (value as String)
                } else {
                    ""
                }
            }
            val monthText = fun(value: Any?): String {
                val text = if (value != null) {
                    (value as String)
                } else {
                    ""
                }
                if (text.length >= 7) {
                    return text.substring(0, 7)
                }
                return text
            }
            val formData = reactive<ProfileFormState>(ProfileFormState(avatar = asText(resumeStore.resumeData.Avatar), name = asText(resumeStore.resumeData.UserNick), gender = resumeStore.genderText, nation = asText(resumeStore.resumeData.Nation), workStartTime = monthText(resumeStore.resumeData.StartWorking), phone = asText(resumeStore.resumeData.Mobile), wechat = asText(resumeStore.resumeData.WeChat), birthday = monthText(resumeStore.resumeData.Birthday), email = asText(resumeStore.resumeData.Email), status = asText(resumeStore.resumeData.HelpType), education = asText(resumeStore.resumeData.Education), registerTime = monthText(resumeStore.resumeData.AddTime)))
            val saving = ref(false)
            val showGenderSheet = ref(false)
            val showNationSheet = ref(false)
            val showEducationSheet = ref(false)
            val showDatePicker = ref(false)
            val dateField = ref<DateField>("birthday")
            val nationalityLoading = ref(false)
            val nationalityActions = ref(_uA<ActionItem>())
            val genderActions = _uA<ActionItem>(ActionItem(name = "男", value = "男"), ActionItem(name = "女", value = "女"), ActionItem(name = "保密", value = "保密"))
            val educationActions = EDUCATION_OPTIONS_CN.map(fun(item): UTSJSONObject {
                val action = UTSJSONObject()
                action["name"] = item.label
                action["value"] = item.value
                return action
            }
            )
            val datePickerTitle = computed(fun(): String {
                return if (dateField.value === "workStartTime") {
                    "参加工作时间"
                } else {
                    "出生年月"
                }
            }
            )
            val inputTitle = fun(field: InputField): String {
                if (field === "name") {
                    return "姓名"
                }
                if (field === "phone") {
                    return "手机号"
                }
                if (field === "wechat") {
                    return "微信号"
                }
                return "邮箱"
            }
            val getInputValue = fun(field: InputField): String {
                if (field === "name") {
                    return formData.name
                }
                if (field === "phone") {
                    return formData.phone
                }
                if (field === "wechat") {
                    return formData.wechat
                }
                return formData.email
            }
            val setInputValue = fun(field: InputField, value: String){
                if (field === "name") {
                    formData.name = value
                } else if (field === "phone") {
                    formData.phone = value
                } else if (field === "wechat") {
                    formData.wechat = value
                } else {
                    formData.email = value
                }
            }
            val handleChooseAvatar = fun(){
                uni_chooseImage(ChooseImageOptions(count = 1, success = fun(res){
                    formData.avatar = res.tempFilePaths[0]
                }
                ))
            }
            val handleInputClick = fun(field: InputField){
                uni_showModal(ShowModalOptions(title = inputTitle(field), editable = true, placeholderText = "请输入" + inputTitle(field), content = getInputValue(field), success = fun(res){
                    if (res.confirm) {
                        val content = (res as UTSJSONObject)["content"] as String?
                        if (content != null) {
                            setInputValue(field, content)
                        }
                    }
                }
                ))
            }
            val handleGenderSelect = fun(action: ActionItem){
                formData.gender = action.value
            }
            val fetchNationalityActions = fun(): UTSPromise<Boolean> {
                return wrapUTSPromise(suspend w1@{
                        if (nationalityActions.value.length > 0) {
                            return@w1 true
                        }
                        if (nationalityLoading.value) {
                            return@w1 false
                        }
                        nationalityLoading.value = true
                        try {
                            val res = await(getNationality(GetNationalityParams(Page = 1, PageSize = 100)))
                            val rawData = if (res != null) {
                                ((res as UTSJSONObject)["data"] as UTSArray<GetNationalityResult>?)
                            } else {
                                null
                            }
                            val data = if (rawData != null) {
                                rawData
                            } else {
                                _uA<GetNationalityResult>()
                            }
                            nationalityActions.value = data.map(fun(item: GetNationalityResult): ActionItem {
                                val name = item.ChineseName
                                val action = ActionItem(name = name, value = name)
                                return action
                            }
                            )
                            return@w1 nationalityActions.value.length > 0
                        }
                         catch (err: Throwable) {
                            console.error("获取民族列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取民族失败", icon = "none"))
                            return@w1 false
                        }
                         finally {
                            nationalityLoading.value = false
                        }
                })
            }
            val handleNationClick = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        val ok = await(fetchNationalityActions())
                        if (ok) {
                            showNationSheet.value = true
                        } else if (!nationalityLoading.value) {
                            uni_showToast(ShowToastOptions(title = "暂无民族数据", icon = "none"))
                        }
                })
            }
            val handleNationSelect = fun(action: ActionItem){
                formData.nation = action.value
            }
            val handleEducationSelect = fun(action: ActionItem){
                formData.education = action.value
            }
            val handleDateClick = fun(field: DateField){
                dateField.value = field
                showDatePicker.value = true
            }
            val handleDateConfirm = fun(_value: Number, formatted: String){
                val value = monthText(formatted)
                if (dateField.value === "workStartTime") {
                    formData.workStartTime = value
                } else {
                    formData.birthday = value
                }
                showDatePicker.value = false
            }
            val isLocalAvatar = fun(value: String): Boolean {
                return value.startsWith("blob:") || value.startsWith("file:") || value.includes("_doc/") || value.startsWith("http://tmp") || value.startsWith("wxfile://")
            }
            val handleSave = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (formData.name == "") {
                            uni_showToast(ShowToastOptions(title = "请输入姓名", icon = "none"))
                            return@w1
                        }
                        if (formData.phone == "") {
                            uni_showToast(ShowToastOptions(title = "请输入手机号", icon = "none"))
                            return@w1
                        }
                        saving.value = true
                        uni_showLoading(ShowLoadingOptions(title = "保存中..."))
                        try {
                            var avatar = formData.avatar
                            if (avatar != "" && avatar != (resumeStore.resumeData.Avatar ?: "") && isLocalAvatar(avatar)) {
                                avatar = await(pathToBase64(avatar, null))
                            }
                            resumeStore.updateBasicInfo(ProfileFormData(avatar = avatar, name = formData.name, gender = formData.gender, nation = formData.nation, workStartTime = formData.workStartTime, phone = formData.phone, wechat = formData.wechat, birthday = formData.birthday, email = formData.email, status = formData.status, education = formData.education))
                            val success = await(resumeStore.saveBasicInfo())
                            uni_hideLoading(null)
                            if (success) {
                                val onlineId = authStore.userInfo?.OnlineId ?: 0
                                if (onlineId > 0) {
                                    await(resumeStore.fetchResume(onlineId))
                                }
                                setTimeout(fun(){
                                    uni_navigateBack(null)
                                }
                                , 800)
                            }
                        }
                         catch (err: Throwable) {
                            uni_hideLoading(null)
                            val msg = if (err is UTSError) {
                                (err as UTSError).message
                            } else {
                                "保存失败"
                            }
                            uni_showToast(ShowToastOptions(title = msg, icon = "none"))
                            console.error("保存在线简历个人信息失败:", err)
                        }
                         finally {
                            saving.value = false
                        }
                })
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "profile-page flex flex-col h-full bg-white"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "个人信息")),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to ""), _uA(
                        _cE("view", _uM("class" to "profile-list"), _uA(
                            _cE("view", _uM("class" to "profile-cell profile-cell--avatar", "onClick" to handleChooseAvatar), _uA(
                                _cE("view", null, _uA(
                                    _cE("text", _uM("class" to "profile-label"), "头像"),
                                    _cE("view", _uM("class" to "audit-tip flex flex-row items-center mt-2"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-tishi", "size" to 24, "color" to "#ff5a1f")),
                                        _cE("text", _uM("class" to "audit-tip__text"), "头像上传需审核，禁止上传带电话的图片")
                                    ))
                                )),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to formData.avatar, "size" to "large"), null, 8, _uA(
                                        "src"
                                    ))
                                ))
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                handleInputClick("name")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "姓名"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(if (formData.name != "") {
                                        formData.name
                                    } else {
                                        "请填写姓名"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                showGenderSheet.value = true
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "性别"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(formData.gender), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to handleNationClick), _uA(
                                _cE("text", _uM("class" to "profile-label"), "民族"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(if (formData.nation != "") {
                                        formData.nation
                                    } else {
                                        if (nationalityLoading.value) {
                                            "加载中..."
                                        } else {
                                            "请选择民族"
                                        }
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                handleDateClick("workStartTime")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "参加工作时间"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(if (formData.workStartTime != "") {
                                        formData.workStartTime
                                    } else {
                                        "请选择"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                showEducationSheet.value = true
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "学历"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(if (formData.education != "") {
                                        formData.education
                                    } else {
                                        "请选择学历"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                handleInputClick("phone")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "手机号"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(if (formData.phone != "") {
                                        formData.phone
                                    } else {
                                        "请填写手机号"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                handleInputClick("wechat")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "微信号"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value profile-placeholder"), _tD(if (formData.wechat != "") {
                                        formData.wechat
                                    } else {
                                        "请填写微信号"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                handleDateClick("birthday")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "出生年月"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value profile-placeholder"), _tD(if (formData.birthday != "") {
                                        formData.birthday
                                    } else {
                                        "请选择"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell", "onClick" to fun(){
                                handleInputClick("email")
                            }
                            ), _uA(
                                _cE("text", _uM("class" to "profile-label"), "邮箱"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(if (formData.email != "") {
                                        formData.email
                                    } else {
                                        "请填写邮箱"
                                    }
                                    ), 1),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 24, "color" to "#111111"))
                                ))
                            ), 8, _uA(
                                "onClick"
                            )),
                            _cE("view", _uM("class" to "profile-cell"), _uA(
                                _cE("text", _uM("class" to "profile-label"), "注册时间"),
                                _cE("view", _uM("class" to "profile-right"), _uA(
                                    _cE("text", _uM("class" to "profile-value"), _tD(formData.registerTime), 1)
                                ))
                            ))
                        )),
                        _cE("view", _uM("class" to "save-wrap"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("text" to "保 存", "type" to "primary", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "loading" to saving.value, "onClick" to handleSave), null, 8, _uA(
                                "loading"
                            ))
                        ))
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTActionSheetIndexClass), _uM("modelValue" to showGenderSheet.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showGenderSheet.value = `$event`
                    }
                    , "title" to "请选择性别", "actions" to genderActions, "onSelect" to handleGenderSelect), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTActionSheetIndexClass), _uM("modelValue" to showNationSheet.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showNationSheet.value = `$event`
                    }
                    , "title" to "请选择民族", "actions" to nationalityActions.value, "onSelect" to handleNationSelect), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "actions"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTActionSheetIndexClass), _uM("modelValue" to showEducationSheet.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showEducationSheet.value = `$event`
                    }
                    , "title" to "请选择学历", "actions" to unref(educationActions), "onSelect" to handleEducationSelect), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "actions"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTDateTimePickerIndexClass), _uM("modelValue" to showDatePicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showDatePicker.value = `$event`
                    }
                    , "mode" to "date", "title" to datePickerTitle.value, "active-color" to "#00897b", "confirm-color" to "#00897b", "onConfirm" to handleDateConfirm, "onCancel" to fun(){
                        showDatePicker.value = false
                    }
                    ), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "title",
                        "onCancel"
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
                return _uM("profile-list" to _pS(_uM("paddingTop" to "16rpx", "paddingRight" to "28rpx", "paddingBottom" to 0, "paddingLeft" to "28rpx")), "profile-cell" to _pS(_uM("minHeight" to "96rpx", "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between")), "profile-cell--avatar" to _pS(_uM("minHeight" to "170rpx")), "profile-label" to _pS(_uM("fontSize" to "30rpx", "color" to "#777777")), "profile-right" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "marginLeft" to "24rpx", "display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "flex-end", "gap" to "2px")), "profile-value" to _pS(_uM("maxWidth" to "420rpx", "fontSize" to "30rpx", "color" to "#333333", "textAlign" to "right", "lines" to 1, "textOverflow" to "ellipsis", "marginRight" to 2)), "profile-placeholder" to _pS(_uM("color" to "#777777")), "audit-tip" to _pS(_uM("gap" to "8rpx")), "audit-tip__text" to _pS(_uM("color" to "#ff5a1f", "fontSize" to "24rpx")), "save-wrap" to _pS(_uM("paddingTop" to "64rpx", "paddingRight" to "48rpx", "paddingBottom" to "48rpx", "paddingLeft" to "48rpx")), "avatar-btn" to _pS(_uM("backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0, "marginTop" to 0, "marginRight" to 0, "marginBottom" to 0, "marginLeft" to 0, "lineHeight" to "normal", "borderTopLeftRadius" to 0, "borderTopRightRadius" to 0, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0, "width" to "100%", "borderTopWidth::after" to "medium", "borderRightWidth::after" to "medium", "borderBottomWidth::after" to "medium", "borderLeftWidth::after" to "medium", "borderTopStyle::after" to "none", "borderRightStyle::after" to "none", "borderBottomStyle::after" to "none", "borderLeftStyle::after" to "none", "borderTopColor::after" to "#000000", "borderRightColor::after" to "#000000", "borderBottomColor::after" to "#000000", "borderLeftColor::after" to "#000000")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
