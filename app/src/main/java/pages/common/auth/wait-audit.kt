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
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesCommonAuthWaitAudit : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesCommonAuthWaitAudit) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesCommonAuthWaitAudit
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val isRefreshing = ref(false)
            val status = computed(fun(): Number {
                return (authStore.auditStatus ?: (AUDIT_STATUS["PENDING"] as Number)) as Number
            }
            )
            val statusIcon = computed(fun(): String {
                when (status.value) {
                    AUDIT_STATUS["APPROVED"] as Number -> 
                        return "✅"
                    AUDIT_STATUS["FROZEN"] as Number -> 
                        return "❄️"
                    AUDIT_STATUS["REJECTED"] as Number -> 
                        return "❌"
                    AUDIT_STATUS["PENDING"] as Number -> 
                        return "⏳"
                    else -> 
                        return "⏳"
                }
            }
            )
            val statusTitle = computed(fun(): String {
                when (status.value) {
                    AUDIT_STATUS["APPROVED"] as Number -> 
                        return "审核已通过"
                    AUDIT_STATUS["FROZEN"] as Number -> 
                        return "账号已冻结"
                    AUDIT_STATUS["REJECTED"] as Number -> 
                        return "审核未通过"
                    AUDIT_STATUS["PENDING"] as Number -> 
                        return "审核处理中"
                    else -> 
                        return "处理中"
                }
            }
            )
            val statusDesc = computed(fun(): String {
                when (status.value) {
                    AUDIT_STATUS["APPROVED"] as Number -> 
                        return "您的招聘者身份已激活，欢迎回来！"
                    AUDIT_STATUS["FROZEN"] as Number -> 
                        return "您的账号因违反平台规范已被冻结，请联系客服。"
                    AUDIT_STATUS["REJECTED"] as Number -> 
                        return "抱歉，您的认证申请未能通过审核。"
                    AUDIT_STATUS["PENDING"] as Number -> 
                        return "官方人员正在快马加鞭审核您的资料，请稍后。"
                    else -> 
                        return "请耐心等待..."
                }
            }
            )
            val showEditBtn = computed(fun(): Boolean {
                return status.value === (AUDIT_STATUS["REJECTED"] as Number)
            }
            )
            val isApproved = computed(fun(): Boolean {
                return status.value === (AUDIT_STATUS["APPROVED"] as Number)
            }
            )
            val handleRefresh = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isRefreshing.value) {
                            return@w1
                        }
                        isRefreshing.value = true
                        try {
                            val res = await(authStore.fetchAuthStatus()) as GetAuthStatusResult?
                            if (res != null && res!!.Status === (AUDIT_STATUS["APPROVED"] as Number)) {
                                uni_showToast(ShowToastOptions(title = "审核已通过", icon = "success"))
                                setTimeout(fun(){
                                    uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["RECRUIT_HOME"] as String))
                                }, 1000)
                            } else {
                                uni_showToast(ShowToastOptions(title = "状态已刷新", icon = "none"))
                            }
                        }
                         finally {
                            isRefreshing.value = false
                        }
                })
            }
            val handleGoEdit = fun(){
                uni_navigateTo(NavigateToOptions(url = PAGE_PATHS["RECRUIT_AUTH"] as String))
            }
            val handleGoHome = fun(){
                uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["RECRUIT_HOME"] as String))
            }
            val handleLogout = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        await(authStore.logout())
                })
            }
            val loadPage = fun(_options: OnLoadOptions): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        val res = await(authStore.fetchAuthStatus()) as GetAuthStatusResult?
                        if (res != null && res!!.Status === (AUDIT_STATUS["APPROVED"] as Number)) {
                            uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["RECRUIT_HOME"] as String))
                        }
                })
            }
            onLoad(fun(options: OnLoadOptions){
                loadPage(options)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "page-container h-screen"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "审核状态", "show-back" to false)),
                    _cE("view", _uM("class" to "content-box flex-1 flex flex-col items-center justify-center px-10 pb-20"), _uA(
                        _cE("view", _uM("class" to "status-icon-wrapper mb-8"), _uA(
                            _cE("text", _uM("class" to "text-7xl"), _tD(statusIcon.value), 1)
                        )),
                        _cE("text", _uM("class" to "text-2xl font-bold text-gray-900 mb-4"), _tD(statusTitle.value), 1),
                        _cE("view", _uM("class" to "desc-box px-4 py-2 bg-white/50 rounded-xl mb-12"), _uA(
                            _cE("text", _uM("class" to "text-sm text-gray-500 text-center leading-relaxed"), _tD(statusDesc.value), 1),
                            if (isTrue(status.value === (unref(AUDIT_STATUS)["REJECTED"] as Number) && ((unref(authStore).auditRemark ?: "") as String) != "")) {
                                _cE("text", _uM("key" to 0, "class" to "text-red-400 text-xs mt-3 block text-center italic"), " 原因：" + _tD((unref(authStore).auditRemark ?: "") as String), 1)
                            } else {
                                _cC("v-if", true)
                            }
                        )),
                        _cE("view", _uM("class" to "btn-group w-full gap-4"), _uA(
                            if (isTrue(showEditBtn.value)) {
                                _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 0, "block" to "", "type" to "primary", "shape" to "round", "size" to "large", "text" to "修改认证资料", "onClick" to handleGoEdit))
                            } else {
                                _cC("v-if", true)
                            }
                            ,
                            if (isTrue(isApproved.value)) {
                                _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 1, "block" to "", "type" to "primary", "shape" to "round", "size" to "large", "text" to "进入招聘中心", "onClick" to handleGoHome))
                            } else {
                                _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 2, "block" to "", "type" to "success", "size" to "large", "shape" to "round", "custom-class" to "t-default", "loading" to isRefreshing.value, "text" to "刷新审核状态", "onClick" to handleRefresh), null, 8, _uA(
                                    "loading"
                                ))
                            }
                            ,
                            _cE("view", _uM("class" to "mt-8 flex flex-row items-center justify-center", "onClick" to handleLogout), _uA(
                                _cE("text", _uM("class" to "text-sm text-gray-400"), "切换账号 / 退出登录")
                            ))
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
                return _uM("page-container" to _pS(_uM("display" to "flex", "flexDirection" to "column", "backgroundImage" to "linear-gradient(180deg, #d1fae5 0%, #ffffff 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "status-icon-wrapper" to _pS(_uM("width" to "160rpx", "height" to "160rpx", "borderTopLeftRadius" to "80rpx", "borderTopRightRadius" to "80rpx", "borderBottomRightRadius" to "80rpx", "borderBottomLeftRadius" to "80rpx", "backgroundColor" to "#ffffff", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "boxShadow" to "0 10rpx 30rpx rgba(0, 0, 0, 0.05)")), "desc-box" to _pS(_uM("width" to "100%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
