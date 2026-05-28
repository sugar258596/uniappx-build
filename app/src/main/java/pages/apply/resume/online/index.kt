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
open class GenPagesApplyResumeOnlineIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineIndex
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            val authStore = useAuthStore()
            val showStatusPopup = ref(false)
            val selectedStatus = ref("")
            val statusOptions = STATUS_OPTIONS
            val showAdvantagePopup = ref(false)
            val tempAdvantage = ref("")
            val showCustomPopup = ref(false)
            val customPopupTitle = ref("")
            val currentModuleName = ref("")
            val tempCustomContent = ref("")
            val resumeData = computed(fun(): GetOnlineResumeResult {
                return resumeStore.resumeData
            }
            )
            val userCardInfo = computed(fun(): UTSJSONObject {
                var city = ""
                var salary = ""
                val purposeList = if (resumeData.value.PurposeCodeData != null) {
                    resumeData.value.PurposeCodeData as UTSArray<JobIntentionItem>
                } else {
                    _uA<JobIntentionItem>()
                }
                if (purposeList.length > 0) {
                    val first = purposeList[0]
                    city = if (first.CityName != null) {
                        first.CityName!!
                    } else {
                        ""
                    }
                    salary = "" + (if (first.MinimumSalary != null) {
                        first.MinimumSalary!!
                    } else {
                        ""
                    }
                    ) + "-" + (if (first.HighestSalary != null) {
                        first.HighestSalary!!
                    } else {
                        ""
                    }
                    )
                }
                var age: Number = 0
                val birthday = if (resumeData.value.Birthday != null) {
                    resumeData.value.Birthday!!
                } else {
                    ""
                }
                if (birthday.length >= 4) {
                    val birthYear = parseInt(birthday.substring(0, 4))
                    if (!isNaN(birthYear)) {
                        age = Date().getFullYear() - birthYear
                    }
                }
                return _uO("avatar" to (resumeData.value.Avatar ?: ""), "name" to (resumeData.value.UserNick ?: ""), "age" to age, "education" to (resumeData.value.Education ?: ""), "location" to city, "salary" to salary, "status" to (resumeData.value.HelpType ?: ""), "avatarStatusText" to (resumeData.value.StatusName ?: ""), "avatarStatus" to (resumeData.value.Statue ?: 0), "phone" to (resumeData.value.Mobile ?: ""), "email" to (resumeData.value.Email ?: ""))
            }
            )
            val handleStatusClick = fun(){
                selectedStatus.value = resumeStore.resumeData.HelpType ?: ""
                showStatusPopup.value = true
            }
            val handleStatusSelect = fun(value: String){
                resumeStore.updateHelpType(value)
                showStatusPopup.value = false
                resumeStore.saveBasicInfo()
            }
            val handleEditClick = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/resume/online/profile"))
            }
            val handleAdvantageEdit = fun(){
                tempAdvantage.value = resumeStore.resumeData.MySuperiority ?: ""
                showAdvantagePopup.value = true
            }
            val handleAdvantageSave = fun(){
                resumeStore.updateAdvantage(tempAdvantage.value)
                showAdvantagePopup.value = false
                resumeStore.saveBasicInfo()
            }
            val handleCustomModuleAdd = fun(mod: UTSJSONObject){
                val name = (mod["name"] as String) ?: ""
                currentModuleName.value = name
                customPopupTitle.value = name
                var content = ""
                if (name === "专业技能") {
                    content = resumeStore.resumeData.ProfessionalSkills ?: ""
                } else if (name === "社团/组织经历") {
                    content = resumeStore.resumeData.ClubActivities ?: ""
                } else if (name === "实践活动") {
                    content = resumeStore.resumeData.PracticalActivity ?: ""
                } else if (name === "所获荣耀") {
                    content = resumeStore.resumeData.MyHonor ?: ""
                }
                tempCustomContent.value = content
                showCustomPopup.value = true
            }
            val handleCustomSave = fun(){
                val name = currentModuleName.value
                val content = tempCustomContent.value
                if (name === "专业技能") {
                    resumeStore.resumeData.ProfessionalSkills = content
                } else if (name === "社团/组织经历") {
                    resumeStore.resumeData.ClubActivities = content
                } else if (name === "实践活动") {
                    resumeStore.resumeData.PracticalActivity = content
                } else if (name === "所获荣耀") {
                    resumeStore.resumeData.MyHonor = content
                }
                showCustomPopup.value = false
                resumeStore.saveBasicInfo()
            }
            val handleRefresh = fun(){
                val onlineId = authStore.userInfo?.OnlineId ?: 0
                if (onlineId > 0) {
                    resumeStore.fetchResume(onlineId)
                }
            }
            onLoad(fun(options: UTSJSONObject){
                var onlineId: Number = 0
                val idStr = options.get("id") as String?
                if (idStr != null) {
                    onlineId = parseInt(idStr)
                }
                if (onlineId === 0) {
                    onlineId = authStore.userInfo?.OnlineId ?: 0
                }
                if (onlineId > 0) {
                    resumeStore.fetchResume(onlineId)
                }
            }
            )
            onShow(fun(){
                val onlineId = authStore.userInfo?.OnlineId ?: 0
                if (onlineId > 0 && (resumeStore.resumeData.Id ?: 0) > 0) {
                    resumeStore.fetchResume(onlineId)
                }
            }
            )
            return fun(): Any? {
                return _cE(Fragment, null, _uA(
                    _cE("view", _uM("class" to "flex flex-col h-full"), _uA(
                        _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "我的在线简历")),
                        _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to ""), _uA(
                            _cE("view", _uM("class" to "flex flex-col gap-4 px-4 pb-10"), _uA(
                                _cV(unref(GenPagesApplyResumeOnlineComponentsUserCardClass), _uM("info" to userCardInfo.value, "onStatusClick" to handleStatusClick, "onEditClick" to handleEditClick), null, 8, _uA(
                                    "info"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsBasicInfoClass), _uM("list" to (resumeData.value.EducationalBackCodeData ?: _uA())), null, 8, _uA(
                                    "list"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsAdvantageClass), _uM("content" to (resumeData.value.MySuperiority ?: ""), "onEdit" to handleAdvantageEdit), null, 8, _uA(
                                    "content"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsExpectationClass), _uM("list" to (resumeData.value.PurposeCodeData ?: _uA())), null, 8, _uA(
                                    "list"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsWorkExperienceListClass), _uM("list" to (resumeData.value.WorkExperienceCodeData ?: _uA())), null, 8, _uA(
                                    "list"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsProjectExperienceListClass), _uM("list" to (resumeData.value.ProjectExperienceCodeData ?: _uA())), null, 8, _uA(
                                    "list"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsPortfolioClass), _uM("images" to unref(resumeStore).portfolioImages), null, 8, _uA(
                                    "images"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsSkillCertificateClass), _uM("certificates" to unref(resumeStore).certificates), null, 8, _uA(
                                    "certificates"
                                )),
                                _cV(unref(GenPagesApplyResumeOnlineComponentsCustomModuleListClass), _uM("list" to unref(resumeStore).customModules, "onAdd" to handleCustomModuleAdd), null, 8, _uA(
                                    "list"
                                ))
                            ))
                        )),
                        _cE("view", _uM("class" to "p-6"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "刷新简历", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleRefresh))
                        ))
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showStatusPopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showStatusPopup.value = `$event`
                    }
                    , "mode" to "bottom", "title" to "求职状态", "round" to ""), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTRadioGroupIndexClass), _uM("modelValue" to selectedStatus.value, "onUpdate:modelValue" to fun(`$event`: String){
                                    selectedStatus.value = `$event`
                                }
                                , "options" to unref(statusOptions), "direction" to "vertical", "active-color" to "#0d9488", "onChange" to handleStatusSelect), null, 8, _uA(
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
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showAdvantagePopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showAdvantagePopup.value = `$event`
                    }
                    , "mode" to "bottom", "round" to "", "show-title" to false, "height" to "80%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full p-4"), _uA(
                                _cE("text", _uM("class" to "text-lg font-bold text-gray-800 mb-2"), "我的优势"),
                                _cE("text", _uM("class" to "text-sm text-gray-500 mb-4"), "个人优势介绍，更能赢得老板的青睐"),
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to tempAdvantage.value, "onUpdate:modelValue" to fun(`$event`: String){
                                        tempAdvantage.value = `$event`
                                    }
                                    , "type" to "textarea", "placeholder" to "请输入...", "auto-height" to "", "focus-border-color" to "#0f766e"), null, 8, _uA(
                                        "modelValue",
                                        "onUpdate:modelValue"
                                    ))
                                )),
                                _cE("view", _uM("class" to "mt-4"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleAdvantageSave))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showCustomPopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showCustomPopup.value = `$event`
                    }
                    , "mode" to "bottom", "round" to "", "show-title" to false, "height" to "80%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full p-4"), _uA(
                                _cE("text", _uM("class" to "text-lg font-bold text-gray-800 mb-2"), _tD(customPopupTitle.value), 1),
                                _cE("view", _uM("class" to "flex-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to tempCustomContent.value, "onUpdate:modelValue" to fun(`$event`: String){
                                        tempCustomContent.value = `$event`
                                    }
                                    , "type" to "textarea", "placeholder" to "请输入...", "auto-height" to "", "focus-border-color" to "#0f766e"), null, 8, _uA(
                                        "modelValue",
                                        "onUpdate:modelValue"
                                    ))
                                )),
                                _cE("view", _uM("class" to "mt-4"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleCustomSave))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    ))
                ), 64)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("t-radio-group__item" to _pS(_uM("flexDirection" to "row-reverse", "justifyContent" to "space-between")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
