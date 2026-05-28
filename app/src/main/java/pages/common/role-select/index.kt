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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.redirectTo as uni_redirectTo
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.switchTab as uni_switchTab
open class GenPagesCommonRoleSelectIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesCommonRoleSelectIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesCommonRoleSelectIndex
            val _cache = __ins.renderCache
            val roleStore = useRoleStore()
            val authStore = useAuthStore()
            val selectedRole = ref<UserRole>(roleStore.currentRole as UserRole)
            val registerType = ref("form")
            open class RoleOption {
                open lateinit var value: UserRole
                open lateinit var label: String
                open lateinit var description: String
                open lateinit var icon: String
                constructor(value: UserRole, label: String, description: String, icon: String){
                    this.value = value
                    this.label = label
                    this.description = description
                    this.icon = icon
                }
            }
            val roleOptions = ref(_uA<RoleOption>(RoleOption(ROLES["APPLICANT"] as String, "求职者", "精准找岗，海量工作任你选~", "/static/tabbar/jobs-active.png"), RoleOption(ROLES["RECRUITER"] as String, "招聘者", "高效招才，海量人才等你挑~", "/static/tabbar/profile-active.png")))
            fun gen_selectRole_fn(role: UserRole) {
                selectedRole.value = role
            }
            val selectRole = ::gen_selectRole_fn
            fun gen_confirmRole_fn(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        await(roleStore.setRole(selectedRole.value))
                        if (!authStore.isLogin) {
                            if (selectedRole.value === ROLES["APPLICANT"]) {
                                if (registerType.value === "mini_program_register") {
                                    val res = await(authStore.miniProgramRegisterAction(0, null))
                                    if (res != null) {
                                        uni_showToast(ShowToastOptions(title = "注册成功", icon = "success"))
                                        setTimeout(fun() {
                                            uni_switchTab(SwitchTabOptions(url = PAGE_PATHS["APPLY_HOME"] as String))
                                        }
                                        , 1500)
                                    }
                                } else {
                                    Object.assign(authStore.registerForm, _uO("UserType" to 0, "IdentityType" to 0))
                                    val res = await(authStore.register(authStore.registerForm))
                                    if (res != null) {
                                        uni_showToast(ShowToastOptions(title = "注册成功", icon = "success"))
                                        setTimeout(fun() {
                                            uni_switchTab(SwitchTabOptions(url = PAGE_PATHS["APPLY_HOME"] as String))
                                        }
                                        , 1500)
                                    }
                                }
                            } else {
                                val url = if (registerType.value === "mini_program_register") {
                                    "" + PAGE_PATHS["RECRUIT_AUTH"] as String + "?type=mini_program_register"
                                } else {
                                    PAGE_PATHS["RECRUIT_AUTH"] as String
                                }
                                uni_navigateTo(NavigateToOptions(url = url))
                            }
                            return@w1
                        }
                        if (selectedRole.value === ROLES["APPLICANT"]) {
                            uni_switchTab(SwitchTabOptions(url = PAGE_PATHS["APPLY_HOME"] as String))
                        } else {
                            uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                            val authResult = await(authStore.fetchAuthStatus())
                            uni_hideLoading(null)
                            if (authResult?.Status === (AUDIT_STATUS["APPROVED"] as Number)) {
                                uni_switchTab(SwitchTabOptions(url = PAGE_PATHS["RECRUIT_HOME"] as String))
                                return@w1
                            }
                            if (authResult?.Status === -1) {
                                uni_redirectTo(RedirectToOptions(url = PAGE_PATHS["RECRUIT_AUTH"] as String))
                                return@w1
                            }
                            uni_redirectTo(RedirectToOptions(url = PAGE_PATHS["WAIT_AUDIT"] as String))
                        }
                })
            }
            val confirmRole = ::gen_confirmRole_fn
            onLoad(fun(options: UTSJSONObject){
                if ((options["type"] as String?) == "mini_program_register") {
                    registerType.value = "mini_program_register"
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "min-h-screen bg-white flex flex-col"), _uA(
                    _cE("view", _uM("class" to "h-52 flex justify-between bg-gradient-to-b from-green-200 to-white overflow-visible"), _uA(
                        _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "身份选项")),
                        _cE("view", _uM("class" to "flex-row flex justify-between items-center p-3"), _uA(
                            _cE("view", _uM("class" to "mb-10"), _uA(
                                _cE("text", _uM("class" to "text-base font-bold text-gray-900 mb-2"), "您的身份是"),
                                _cE("text", _uM("class" to "text-sm text-gray-500"), "求职者or招聘者?")
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "w-full space-y-6 mb-6 px-3 gap-7"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(roleOptions.value, fun(option, __key, __index, _cached): Any {
                            return _cE("view", _uM("key" to option.value, "class" to "bg-gray-50 rounded-2xl p-3 flex flex-row items-center justify-between", "onClick" to fun(){
                                selectRole(option.value)
                            }
                            ), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center flex-1 gap-1"), _uA(
                                    _cE("view", _uM("class" to "w-7 h-7 mr-2"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to option.icon, "height" to "100%"), null, 8, _uA(
                                            "src"
                                        ))
                                    )),
                                    _cE("view", _uM("class" to "gap-1"), _uA(
                                        _cE("text", _uM("class" to "text-lg font-bold text-gray-900 block mb-1"), _tD(option.label), 1),
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), _tD(option.description), 1)
                                    ))
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("model-value" to (selectedRole.value === option.value), "active-color" to "#00703C"), null, 8, _uA(
                                    "model-value"
                                ))
                            ), 8, _uA(
                                "onClick"
                            ))
                        }
                        ), 128),
                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("text" to "下一步", "type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default mt-20", "onClick" to confirmRole))
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
