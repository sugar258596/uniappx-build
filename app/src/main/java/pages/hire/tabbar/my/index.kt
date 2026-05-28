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
open class GenPagesHireTabbarMyIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarMyIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarMyIndex
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val userInfo = computed<UserInfo__1>(fun(): UserInfo__1 {
                val user = authStore.userInfo
                return UserInfo__1(id = if (user != null) {
                    (user!!.Id ?: 0)
                } else {
                    0
                }
                , avatar = if (user != null) {
                    (user!!.Avatar ?: "")
                } else {
                    ""
                }
                , nickname = if (user != null) {
                    (user!!.NickName ?: "")
                } else {
                    ""
                }
                , phone = if (user != null) {
                    (user!!.Mobile ?: "")
                } else {
                    ""
                }
                , isVerified = if (user != null) {
                    ((user!!.IsCertification ?: 0) === 1)
                } else {
                    false
                }
                , isVip = if (user != null) {
                    (user!!.IsVipPackage === 1)
                } else {
                    false
                }
                , vipExpireTime = if (user != null) {
                    (user!!.VipPackageEndTime ?: "")
                } else {
                    ""
                }
                )
            }
            )
            val stats = computed<UserStats__1>(fun(): UserStats__1 {
                val user = authStore.userInfo
                return UserStats__1(applicationCount = if (user != null) {
                    (user!!.ApplyHireJob ?: 0)
                } else {
                    0
                }
                , interviewCount = if (user != null) {
                    (user!!.InterviewCount ?: 0)
                } else {
                    0
                }
                , favoriteCount = if (user != null) {
                    (user!!.CollCount ?: 0)
                } else {
                    0
                }
                )
            }
            )
            val commonFeatureData = computed<CommonFeatureData>(fun(): CommonFeatureData {
                val user = authStore.userInfo
                return CommonFeatureData(orderCount = if (user != null) {
                    (user!!.VipPinnedOrder ?: 0)
                } else {
                    0
                }
                , pointsCount = if (user != null) {
                    (user!!.Score ?: 0)
                } else {
                    0
                }
                )
            }
            )
            val handleServiceClick = fun(){
                uni_showToast(ShowToastOptions(title = "客服功能开发中", icon = "none"))
            }
            val checkAuth = fun(callback: () -> Unit){
                if (!authStore.isLogin) {
                    uni_showToast(ShowToastOptions(title = "请先登录", icon = "none"))
                    return
                }
                callback()
            }
            val handleSwitchRoleClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/role-select/index"))
                }
                )
            }
            val handleVipClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/vip/index"))
                }
                )
            }
            val handleApplicationClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/hire/process/application/index"))
                }
                )
            }
            val handleInterviewClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/interview/index"))
                }
                )
            }
            val handleFavoriteClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/hire/history/favorite/index"))
                }
                )
            }
            val handleOrderClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/order/index"))
                }
                )
            }
            val handlePointsClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/points/index"))
                }
                )
            }
            val handleMenuClick = fun(item: FeatureMenuItem__1){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = item.path))
                }
                )
            }
            val handleGoLogin = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/auth/login"))
            }
            onShow(fun(){
                authStore.fetchUserInfo(null)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-tab bg-gray-50"), _uA(
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "show-scrollbar" to false), _uA(
                        _cE("view", _uM("class" to "flex flex-col gap-2 pb-16"), _uA(
                            _cV(unref(GenPagesHireTabbarMyComponentsUserHeaderClass), _uM("user-info" to userInfo.value, "stats" to stats.value, "is-login" to unref(authStore).isLogin, "onServiceClick" to handleServiceClick, "onSwitchRoleClick" to handleSwitchRoleClick, "onVipClick" to handleVipClick, "onApplicationClick" to handleApplicationClick, "onInterviewClick" to handleInterviewClick, "onFavoriteClick" to handleFavoriteClick, "onLoginClick" to handleGoLogin), null, 8, _uA(
                                "user-info",
                                "stats",
                                "is-login"
                            )),
                            _cV(unref(GenPagesHireTabbarMyComponentsCommonFeaturesClass), _uM("data" to commonFeatureData.value, "is-login" to unref(authStore).isLogin, "onOrderClick" to handleOrderClick, "onPointsClick" to handlePointsClick), null, 8, _uA(
                                "data",
                                "is-login"
                            )),
                            _cV(unref(GenPagesHireTabbarMyComponentsOtherFeaturesClass), _uM("onMenuClick" to handleMenuClick))
                        ))
                    )),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 4))
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
