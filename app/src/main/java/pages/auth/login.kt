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
open class GenPagesAuthLogin : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAuthLogin) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAuthLogin
            val _cache = __ins.renderCache
            val appStore = useAppStore()
            val authStore = useAuthStore()
            val getSafeAreaInfo = useSafeArea().getSafeAreaInfo
            val loginType = ref("quick")
            val countdown = ref(0)
            val isCounting = ref(false)
            var timer: Number? = null
            val showRegisterPopup = ref(false)
            val formData = reactive<FormData>(FormData(phoneNumber = "13333333333", password = "123456", verifyCode = "", isChecked = true))
            val LoginMethod = reactive(_uA<LoginMethodItem>(LoginMethodItem(title = "微信登录", icon = "icon-weixin", color = "#006635")))
            val isMiniProgram = computed(fun(): Boolean {
                return false
            }
            )
            val statusBarHeight = computed(fun(): Number {
                val info = getSafeAreaInfo()
                return info.statusBarHeight
            }
            )
            val isOpenSms = computed(fun(): Boolean {
                return if (appStore.webConfig != null) {
                    appStore.webConfig!!.IsOpenSms === 1
                } else {
                    false
                }
            }
            )
            val handleWeChetQuickLogin = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (formData.isChecked !== true) {
                            uni_showToast(ShowToastOptions(title = "请先阅读并同意用户协议", icon = "none"))
                            return@w1
                        }
                        try {
                            await(authStore.wechatLogin())
                        }
                         catch (_err: Throwable) {
                            showRegisterPopup.value = true
                        }
                })
            }
            val onGetPhoneNumber = fun(e: UTSJSONObject): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(authStore.getWechatPhoneNumber(e))
                            showRegisterPopup.value = false
                            uni_navigateTo(NavigateToOptions(url = "" + PAGE_PATHS["ROLE_SELECT"] as String + "?type=mini_program_register"))
                        }
                         catch (_err: Throwable) {}
                })
            }
            val handleQuickLogin = fun(){
                if (formData.isChecked !== true) {
                    uni_showToast(ShowToastOptions(title = "请先阅读并同意用户协议", icon = "none"))
                    return
                }
                showRegisterPopup.value = true
                uni_showToast(ShowToastOptions(title = "手机号快捷登录暂未开放", icon = "none"))
            }
            val handleOtherPhoneLogin = fun(){
                loginType.value = "form"
                console.log("切换到其它手机号登录")
            }
            val handleFormLogin = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (formData.isChecked !== true) {
                            uni_showToast(ShowToastOptions(title = "请先阅读并同意用户协议", icon = "none"))
                            return@w1
                        }
                        if (formData.phoneNumber === "") {
                            uni_showToast(ShowToastOptions(title = "请输入手机号", icon = "none"))
                            return@w1
                        }
                        if (isOpenSms.value === true && formData.verifyCode === "") {
                            uni_showToast(ShowToastOptions(title = "请输入验证码", icon = "none"))
                            return@w1
                        }
                        if (isOpenSms.value !== true && formData.password === "") {
                            uni_showToast(ShowToastOptions(title = "请输入密码", icon = "none"))
                            return@w1
                        }
                        if (isOpenSms.value === true) {
                            try {
                                await(authStore.loginByPassword(LoginParams(Mobile = formData.phoneNumber, PassWord = "", Vcode = formData.verifyCode, loginType = "code")))
                            } catch (err: Throwable) {
                                console.log(err)
                            }
                        } else {
                            await(authStore.loginByPassword(LoginParams(Mobile = formData.phoneNumber, PassWord = formData.password, loginType = "password")))
                        }
                })
            }
            val handleWechatLogin = fun(){
                uni_showToast(ShowToastOptions(title = "微信授权登录暂未开放", icon = "none"))
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
            val goToRegister = fun(){
                uni_navigateTo(NavigateToOptions(url = PAGE_PATHS["REGISTER"] as String))
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
                        _cE("view", _uM("class" to "flex justify-center items-center pt-4 pb-2"), _uA(
                            _cE("text", _uM("class" to "text-lg font-bold text-gray-800"), "授权登录")
                        )),
                        _cE("view", _uM("class" to "flex-row flex justify-between items-center px-3 py-3"), _uA(
                            _cE("view", _uM("class" to "flex-1"), _uA(
                                _cE("text", _uM("class" to "text-base font-bold text-teal-700 mb-2 block"), "精准匹配 · 求职招聘快人一步"),
                                _cE("text", _uM("class" to "text-sm text-gray-500 block"), "求职者精准搜岗，企业高效筛才")
                            ))
                        ))
                    ), 4),
                    _cE("view", _uM("class" to "flex-1 h-full bg-white rounded-t-2xl px-3 flex flex-col items-center mt-2 shadow-lg overflow-visible pt-28"), _uA(
                        _cE("view", _uM("class" to "w-30 h-30 rounded-md absolute top-0 right-0"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to "/static/login-log.png", "custom-class" to "w-full h-full bg-transparent"))
                        )),
                        if (loginType.value === "quick") {
                            _cE(Fragment, _uM("key" to 0), _uA(
                                _cE("view", _uM("class" to "flex flex-col items-center mb-8"), _uA(
                                    _cE("text", _uM("class" to "text-3xl text-teal-700 font-mono mb-4"), "秒介"),
                                    _cE("text", _uM("class" to "text-sm text-gray-700"), "秒介招聘")
                                )),
                                if (isMiniProgram.value !== true) {
                                    _cE("view", _uM("key" to 0, "class" to "mb-4"), _uA(
                                        _cE("text", _uM("class" to "text-2xl font-bold text-gray-800"), "132****1234")
                                    ))
                                } else {
                                    _cC("v-if", true)
                                },
                                _cE("view", _uM("class" to "w-full flex flex-col items-center gap-2 mb-4"), _uA(
                                    if (isTrue(isMiniProgram.value)) {
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 0, "text" to "快捷登录", "type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleWeChetQuickLogin))
                                    } else {
                                        _cC("v-if", true)
                                    },
                                    if (isMiniProgram.value !== true) {
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 1, "text" to "手机号快捷登录", "type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleQuickLogin))
                                    } else {
                                        _cC("v-if", true)
                                    },
                                    if (isMiniProgram.value !== true) {
                                        _cE("view", _uM("key" to 2, "class" to "p-1", "onClick" to handleOtherPhoneLogin), _uA(
                                            _cE("text", _uM("class" to "text-sm text-gray-500"), "其它手机号登录")
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            ), 64)
                        } else {
                            _cE(Fragment, _uM("key" to 1), _uA(
                                _cE("view", _uM("class" to "w-full flex flex-col gap-3 mb-5"), _uA(
                                    _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                                        _cE("view", _uM("class" to "flex flex-row items-center gap-1 px-1"), _uA(
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
                                    if (isOpenSms.value !== true) {
                                        _cE("view", _uM("key" to 0, "class" to "flex flex-col gap-2"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row items-center gap-1 px-1"), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shouji", "size" to 30, "color" to "#666")),
                                                _cE("text", _uM("class" to "text-sm text-gray-600 font-medium"), "密码")
                                            )),
                                            _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to formData.password, "onUpdate:modelValue" to fun(`$event`: String){
                                                formData.password = `$event`
                                            }, "placeholder" to "请输入密码", "type" to "password", "max-length" to 11, "custom-class" to "bg-gray-200 rounded-xl p-1 border border-gray-100", "focus-border-color" to "#006635"), null, 8, _uA(
                                                "modelValue",
                                                "onUpdate:modelValue"
                                            ))
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                    ,
                                    if (isTrue(isOpenSms.value)) {
                                        _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-2"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row items-center gap-2 px-1"), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youxiang", "size" to 30, "color" to "#666")),
                                                _cE("text", _uM("class" to "text-sm text-gray-600 font-medium"), "验证码")
                                            )),
                                            _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                                _cE("view", _uM("class" to "flex-1"), _uA(
                                                    _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to formData.verifyCode, "onUpdate:modelValue" to fun(`$event`: String){
                                                        formData.verifyCode = `$event`
                                                    }, "placeholder" to "请输入验证码", "type" to "number", "max-length" to 6, "custom-class" to "bg-gray-200 rounded-xl px-2 py-2 flex-1 border border-gray-100", "focus-border-color" to "#006635"), null, 8, _uA(
                                                        "modelValue",
                                                        "onUpdate:modelValue"
                                                    ))
                                                )),
                                                _cE("view", _uM("class" to "active:opacity-70 px-2 min-w-12 flex justify-center items-center bg-gray-200 rounded-xl border border-gray-100 h-full", "onClick" to getVerifyCode), _uA(
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
                                    _cE("view", _uM("class" to "flex flex-row items-center justify-center mb-2"), _uA(
                                        _cE("view", _uM("class" to "mr-2"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("modelValue" to formData.isChecked, "onUpdate:modelValue" to fun(`$event`: Boolean){
                                                formData.isChecked = `$event`
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
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("text" to "立即登录", "type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleFormLogin)),
                                    _cE("view", _uM("class" to "flex flex-row items-center justify-center mt-2", "onClick" to goToRegister), _uA(
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "没有账号？"),
                                        _cE("text", _uM("class" to "text-sm text-teal-700 font-medium ml-1"), "去注册")
                                    ))
                                ))
                            ), 64)
                        }
                        ,
                        if (loginType.value === "quick") {
                            _cE("view", _uM("key" to 2, "class" to "flex flex-row items-center mb-5"), _uA(
                                _cE("view", _uM("class" to "mr-2"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTCheckboxIndexClass), _uM("modelValue" to formData.isChecked, "onUpdate:modelValue" to fun(`$event`: Boolean){
                                        formData.isChecked = `$event`
                                    }, "active-color" to "#006635"), null, 8, _uA(
                                        "modelValue",
                                        "onUpdate:modelValue"
                                    ))
                                )),
                                _cE("text", _uM("class" to "text-xs text-gray-400"), _uA(
                                    "我已阅读并同意 ",
                                    _cE("text", _uM("class" to "text-teal-700"), "《用户协议》")
                                ))
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                        ,
                        if (isTrue(loginType.value === "quick" && isMiniProgram.value !== true)) {
                            _cE("view", _uM("key" to 3, "class" to "w-full flex flex-col gap-2 items-center"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center w-4-5 mb-2"), _uA(
                                    _cE("view", _uM("class" to "flex-1 h-px bg-gray-200")),
                                    _cE("text", _uM("class" to "mx-2 text-xs text-gray-400"), "其它登录方式"),
                                    _cE("view", _uM("class" to "flex-1 h-px bg-gray-200"))
                                )),
                                _cE(Fragment, null, RenderHelpers.renderList(LoginMethod, fun(v, i, __index, _cached): Any {
                                    return _cE("view", _uM("key" to i, "class" to "flex flex-col items-center gap-2", "onClick" to handleWechatLogin), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to v.icon, "color" to v.color), null, 8, _uA(
                                            "name",
                                            "color"
                                        )),
                                        _cE("text", _uM("class" to "text-xs text-gray-500"), _tD(v.title), 1)
                                    ))
                                }), 128)
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                        ,
                        _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showRegisterPopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                            showRegisterPopup.value = `$event`
                        }
                        , "title" to "授权手机号", "position" to "center", "width" to "600rpx"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-col items-center py-4 px-2"), _uA(
                                    _cE("view", _uM("class" to "w-16 h-16 rounded-full bg-green-50 flex justify-center items-center mb-6"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shouji", "size" to 40, "color" to "#006635"))
                                    )),
                                    _cE("text", _uM("class" to "text-lg font-bold text-gray-800 mb-2"), "获取手机号授权"),
                                    _cE("text", _uM("class" to "text-sm text-gray-500 text-center mb-10 px-4 leading-6"), " 为了向您提供精准的职位推荐、邀约通知等核心服务，我们需要获取您的手机号以完成账号注册。 "),
                                    _cE("button", _uM("class" to "w-full bg-teal-700 text-white rounded-full h-24 flex items-center justify-center border-none tap-active", "open-type" to "getPhoneNumber", "onGetphonenumber" to onGetPhoneNumber), _uA(
                                        _cE("text", _uM("class" to "text-base font-bold"), "微信一键授权")
                                    ), 32),
                                    _cE("view", _uM("class" to "mt-4 p-2", "onClick" to fun(){
                                        showRegisterPopup.value = false
                                    }
                                    ), _uA(
                                        _cE("text", _uM("class" to "text-sm text-gray-400"), "暂不授权")
                                    ), 8, _uA(
                                        "onClick"
                                    ))
                                ))
                            )
                        }
                        ), "_" to 1), 8, _uA(
                            "modelValue",
                            "onUpdate:modelValue"
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
                return _uM("t-image" to _pS(_uM("backgroundColor" to "rgba(0,0,0,0)")), "top-0" to _pS(_uM("top" to -60)), "right-0" to _pS(_uM("right" to 10)), "popup-position-center" to _pS(_uM("!backgroundImage" to "linear-gradient(to bottom, #f0fdf4 0%, #ffffff 30%, #ffffff 100%)", "!backgroundColor" to "rgba(0,0,0,0)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
