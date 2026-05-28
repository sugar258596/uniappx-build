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
open class GenPagesAuthRegister : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAuthRegister) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAuthRegister
            val _cache = __ins.renderCache
            val appStore = useAppStore()
            val authStore = useAuthStore()
            val getSafeAreaInfo = useSafeArea().getSafeAreaInfo
            val statusBarHeight = computed(fun(): Number {
                val info = getSafeAreaInfo()
                return info.statusBarHeight
            }
            )
            val isOpenSms = computed(fun(): Boolean {
                return if (appStore.webConfig != null) {
                    (appStore.webConfig!!.IsOpenSms as Number) === 1
                } else {
                    false
                }
            }
            )
            val isChecked = ref(true)
            val formData = reactive<RegisterFormData>(RegisterFormData(phoneNumber = "13333333331", password = "13333333333", confirmPassword = "13333333333", verifyCode = ""))
            val countdown = ref(0)
            val isCounting = ref(false)
            var timer: Number? = null
            val handleRegister = fun(){
                if (isChecked.value !== true) {
                    uni_showToast(ShowToastOptions(title = "请先阅读并同意用户协议", icon = "none"))
                    return
                }
                val phoneValidation = validatePhone(formData.phoneNumber)
                if (phoneValidation.isValid !== true) {
                    uni_showToast(ShowToastOptions(title = phoneValidation.message, icon = "none"))
                    return
                }
                if (isOpenSms.value !== true) {
                    if (formData.password === "") {
                        uni_showToast(ShowToastOptions(title = "请输入密码", icon = "none"))
                        return
                    }
                    if (formData.password.length < 6) {
                        uni_showToast(ShowToastOptions(title = "密码不能少于6位", icon = "none"))
                        return
                    }
                    if (formData.confirmPassword === "") {
                        uni_showToast(ShowToastOptions(title = "请确认密码", icon = "none"))
                        return
                    }
                    if (formData.password !== formData.confirmPassword) {
                        uni_showToast(ShowToastOptions(title = "两次密码输入不一致", icon = "none"))
                        return
                    }
                }
                if (isOpenSms.value && formData.verifyCode === "") {
                    uni_showToast(ShowToastOptions(title = "请输入验证码", icon = "none"))
                    return
                }
                authStore.registerForm.Mobile = formData.phoneNumber
                authStore.registerForm.PassWord = formData.password
                authStore.registerForm.Vcode = formData.verifyCode
                uni_navigateTo(NavigateToOptions(url = PAGE_PATHS["ROLE_SELECT"] as String))
            }
            val getVerifyCode = fun(){
                if (isCounting.value) {
                    return
                }
                val phoneValidation = validatePhone(formData.phoneNumber)
                if (phoneValidation.isValid !== true) {
                    uni_showToast(ShowToastOptions(title = phoneValidation.message, icon = "none"))
                    return
                }
                countdown.value = 60
                isCounting.value = true
                uni_showToast(ShowToastOptions(title = "验证码已发送", icon = "none"))
                timer = setInterval(fun(){
                    countdown.value--
                    if (countdown.value <= 0) {
                        val currentTimer = timer
                        if (currentTimer != null) {
                            clearInterval(currentTimer)
                            timer = null
                        }
                        isCounting.value = false
                        countdown.value = 0
                    }
                }
                , 1000)
            }
            val goToLogin = fun(){
                uni_navigateTo(NavigateToOptions(url = PAGE_PATHS["LOGIN"] as String))
            }
            onUnmounted(fun(){
                val currentTimer = timer
                if (currentTimer != null) {
                    clearInterval(currentTimer)
                    timer = null
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "flex flex-col h-screen relative"), _uA(
                    _cE("view", _uM("class" to "flex flex-col bg-gradient-to-b from-green-200 to-white overflow-visible", "style" to _nS(_uM("paddingTop" to (statusBarHeight.value + "px"), "paddingBottom" to "20px"))), _uA(
                        _cE("view", _uM("class" to "flex justify-center items-center pt-8 pb-2"), _uA(
                            _cE("text", _uM("class" to "text-lg font-bold text-gray-800"), "注册账号")
                        )),
                        _cE("view", _uM("class" to "flex-row flex justify-between items-center p-3"), _uA(
                            _cE("view", _uM("class" to "flex-1"), _uA(
                                _cE("text", _uM("class" to "text-base font-bold text-teal-700 mb-1 block"), "加入秒介 · 开启职场新旅程"),
                                _cE("text", _uM("class" to "text-sm text-gray-500 block"), "快速注册，轻松找到理想工作")
                            ))
                        ))
                    ), 4),
                    _cE("view", _uM("class" to "flex-1 h-full bg-white rounded-t-2xl px-3 py-5 flex flex-col items-center mt-5 shadow-lg overflow-visible pt-28"), _uA(
                        _cE("view", _uM("class" to "w-30 h-25 rounded-md absolute top-0 right-0"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to "/static/login-log.png", "custom-class" to "w-full h-full bg-transparent"))
                        )),
                        _cE("view", _uM("class" to "w-full flex flex-col gap-3 mb-5"), _uA(
                            _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-2 px-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shouji", "size" to 30, "color" to "#666")),
                                    _cE("text", _uM("class" to "text-sm text-gray-600 font-medium"), "手机号")
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to formData.phoneNumber, "onUpdate:modelValue" to fun(`$event`: String){
                                    formData.phoneNumber = `$event`
                                }
                                , "placeholder" to "请输入手机号", "type" to "number", "max-length" to 11, "custom-class" to "bg-gray-200 rounded-xl p-1 border border-gray-100", "focus-border-color" to "#006635"), null, 8, _uA(
                                    "modelValue",
                                    "onUpdate:modelValue"
                                ))
                            )),
                            _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-2 px-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shouji", "size" to 30, "color" to "#666")),
                                    _cE("text", _uM("class" to "text-sm text-gray-600 font-medium"), "密码")
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to formData.password, "onUpdate:modelValue" to fun(`$event`: String){
                                    formData.password = `$event`
                                }
                                , "placeholder" to "请输入密码（至少6位）", "type" to "password", "custom-class" to "bg-gray-200 rounded-xl p-1 border border-gray-100", "focus-border-color" to "#006635"), null, 8, _uA(
                                    "modelValue",
                                    "onUpdate:modelValue"
                                ))
                            )),
                            _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-2 px-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shouji", "size" to 30, "color" to "#666")),
                                    _cE("text", _uM("class" to "text-sm text-gray-600 font-medium"), "确认密码")
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to formData.confirmPassword, "onUpdate:modelValue" to fun(`$event`: String){
                                    formData.confirmPassword = `$event`
                                }
                                , "placeholder" to "请再次输入密码", "type" to "password", "custom-class" to "bg-gray-200 rounded-xl p-1 border border-gray-100", "focus-border-color" to "#006635"), null, 8, _uA(
                                    "modelValue",
                                    "onUpdate:modelValue"
                                ))
                            )),
                            if (isTrue(isOpenSms.value)) {
                                _cE("view", _uM("key" to 0, "class" to "flex flex-col gap-4"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-2 px-1"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youxiang", "size" to 30, "color" to "#666")),
                                        _cE("text", _uM("class" to "text-sm text-gray-600 font-medium"), "验证码")
                                    )),
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                        _cE("view", _uM("class" to "flex-1"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to formData.verifyCode, "onUpdate:modelValue" to fun(`$event`: String){
                                                formData.verifyCode = `$event`
                                            }, "placeholder" to "请输入验证码", "type" to "number", "max-length" to 6, "custom-class" to "bg-gray-200 rounded-xl p-1 flex-1 border border-gray-100", "focus-border-color" to "#006635"), null, 8, _uA(
                                                "modelValue",
                                                "onUpdate:modelValue"
                                            ))
                                        )),
                                        _cE("view", _uM("class" to "active:opacity-70 pl-4 min-w-24 flex justify-center items-center bg-gray-200 rounded-xl border border-gray-100 h-full", "onClick" to getVerifyCode), _uA(
                                            _cE("text", _uM("class" to "text-sm text-teal-700 font-medium"), _tD(if (isCounting.value) {
                                                "" + countdown.value + "s后重新获取"
                                            } else {
                                                "获取验证码"
                                            }), 1)
                                        ))
                                    ))
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                        )),
                        _cE("view", _uM("class" to "w-full flex flex-col items-center gap-2 mb-4"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center justify-center mb-a2"), _uA(
                                _cE("view", _uM("class" to "mr-2"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("modelValue" to isChecked.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                                        isChecked.value = `$event`
                                    }
                                    , "active-color" to "#006635"), null, 8, _uA(
                                        "modelValue",
                                        "onUpdate:modelValue"
                                    ))
                                )),
                                _cE("text", _uM("class" to "text-xs text-gray-400"), _uA(
                                    "我已阅读并同意 ",
                                    _cE("text", _uM("class" to "text-teal-700"), "《用户协议》")
                                ))
                            )),
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("text" to "立即注册", "type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleRegister))
                        )),
                        _cE("view", _uM("class" to "flex flex-row items-center justify-center", "onClick" to goToLogin), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-500"), "已有账号？"),
                            _cE("text", _uM("class" to "text-sm text-teal-700 font-medium ml-1"), "去登录")
                        ))
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
                return _uM("t-image" to _pS(_uM("backgroundColor" to "rgba(0,0,0,0)")), "top-0" to _pS(_uM("top" to -60)), "right-0" to _pS(_uM("right" to 10)), "border-l" to _uM(".t-input- " to _uM("borderLeftStyle" to "solid", "!borderTopColor" to "#006635", "!borderRightColor" to "#006635", "!borderBottomColor" to "#006635", "!borderLeftColor" to "#006635")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
