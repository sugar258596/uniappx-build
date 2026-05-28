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
open class GenPagesApplyResumeHide : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeHide) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeHide
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val isHidden = ref(false)
            val isSubmitting = ref(false)
            val syncHiddenStatus = fun(){
                isHidden.value = (authStore.userInfo?.IsHide ?: 0) === 1
            }
            val handleSwitchChange = fun(kVal: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isSubmitting.value) {
                            return@w1
                        }
                        isSubmitting.value = true
                        try {
                            await(updatePrivateProtection())
                            await(authStore.fetchUserInfo(true))
                            syncHiddenStatus()
                            uni_showToast(ShowToastOptions(title = if (kVal) {
                                "已开启隐藏简历"
                            } else {
                                "已关闭隐藏简历"
                            }
                            , icon = "none"))
                        }
                         catch (error: Throwable) {
                            console.error("设置隐私保护失败:", error)
                            uni_showToast(ShowToastOptions(title = "设置失败，请稍后重试", icon = "none"))
                        }
                         finally {
                            isSubmitting.value = false
                        }
                })
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "隐藏简历")),
                    _cE("view", _uM("class" to "px-4 pt-4"), _uA(
                        _cE("view", _uM("class" to "bg-white rounded-xl p-6 flex flex-col gap-4"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                _cE("view", _uM("class" to "flex-1 pr-10"), _uA(
                                    _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "隐藏简历"),
                                    _cE("text", _uM("class" to "text-xs text-gray-400 mt-2"), "设置简历查看范围，对老板隐藏简历")
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTSwitchIndexClass), _uM("model-value" to isHidden.value, "active-color" to "#059669", "disabled" to isSubmitting.value, "onChange" to handleSwitchChange), null, 8, _uA(
                                    "model-value",
                                    "disabled"
                                ))
                            ))
                        ))
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
