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
open class GenPagesApplyTabbarMyIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarMyIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarMyIndex
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val userInfo = computed<UserInfo>(fun(): UserInfo {
                val info = authStore.userInfo
                val resumeVal = if (info != null) {
                    (info!!.Resume ?: 0)
                } else {
                    0
                }
                return UserInfo(id = if (info != null) {
                    "" + (info!!.Id ?: 0)
                } else {
                    "0"
                }
                , avatar = if (info != null) {
                    (info!!.Avatar ?: "")
                } else {
                    ""
                }
                , nickname = if (info != null) {
                    (info!!.NickName ?: "")
                } else {
                    ""
                }
                , resumeProgress = resumeVal, resumeTip = if (resumeVal >= 80) {
                    "简历优秀"
                } else {
                    if (resumeVal >= 50) {
                        "建议完善"
                    } else {
                        "建议优化"
                    }
                }
                )
            }
            )
            val stats = computed<UserStats>(fun(): UserStats {
                val info = authStore.userInfo
                return UserStats(chatCount = if (info != null) {
                    (info!!.UpLikeCount ?: 0)
                } else {
                    0
                }
                , interviewCount = if (info != null) {
                    (info!!.InterviewCount ?: 0)
                } else {
                    0
                }
                , viewedCount = if (info != null) {
                    (info!!.ViewrecordsCount ?: 0)
                } else {
                    0
                }
                , favoriteCount = if (info != null) {
                    (info!!.CollCount ?: 0)
                } else {
                    0
                }
                )
            }
            )
            val walletInfo = computed<WalletInfo>(fun(): WalletInfo {
                val info = authStore.userInfo
                return WalletInfo(balance = if (info != null) {
                    (info!!.Wallet ?: 0)
                } else {
                    0
                }
                , points = if (info != null) {
                    (info!!.Score ?: 0)
                } else {
                    0
                }
                )
            }
            )
            val posterUserInfo = computed<PosterUserInfo>(fun(): PosterUserInfo {
                val info = authStore.userInfo
                val nickName = if (info != null) {
                    (info!!.NickName ?: "")
                } else {
                    ""
                }
                val userName = if (info != null) {
                    (info!!.UserRName ?: "")
                } else {
                    ""
                }
                val displayName = if (nickName != "") {
                    nickName
                } else {
                    if (userName != "") {
                        userName
                    } else {
                        "新朋友"
                    }
                }
                return PosterUserInfo(avatarUrl = if (info != null) {
                    (info!!.Avatar ?: "")
                } else {
                    ""
                }
                , inviteCode = if (info != null) {
                    (info!!.ReferralCode ?: "")
                } else {
                    ""
                }
                , line1 = "我是" + displayName)
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
            val handleResumeClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/apply/resume/online/index"))
                }
                )
            }
            val handleChatClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/apply/history/communicated/index"))
                }
                )
            }
            val handleInterviewClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/interview/index"))
                }
                )
            }
            val handleViewedClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/apply/history/view/index"))
                }
                )
            }
            val handleFavoriteClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/apply/history/favorite/index"))
                }
                )
            }
            val handleWithdrawClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/withdraw/index"))
                }
                )
            }
            val handleMallClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/points/mall"))
                }
                )
            }
            val handleExchangeClick = fun(){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/points/index"))
                }
                )
            }
            val handleQuickMenuClick = fun(item: FeatureMenuItem){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = item.path))
                }
                )
            }
            val handleAllianceMenuClick = fun(item: FeatureMenuItem){
                return checkAuth(fun(){
                    uni_navigateTo(NavigateToOptions(url = item.path))
                }
                )
            }
            val handleOtherMenuClick = fun(item: FeatureMenuItem){
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
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to ""), _uA(
                        _cE("view", _uM("class" to "flex flex-col gap-2 pb-16"), _uA(
                            _cV(unref(GenPagesApplyTabbarMyComponentsUserHeaderClass), _uM("user-info" to userInfo.value, "stats" to stats.value, "is-login" to unref(authStore).isLogin, "onServiceClick" to handleServiceClick, "onSwitchRoleClick" to handleSwitchRoleClick, "onResumeClick" to handleResumeClick, "onChatClick" to handleChatClick, "onInterviewClick" to handleInterviewClick, "onViewedClick" to handleViewedClick, "onFavoriteClick" to handleFavoriteClick, "onLoginClick" to handleGoLogin), null, 8, _uA(
                                "user-info",
                                "stats",
                                "is-login"
                            )),
                            _cE("view", _uM("class" to "px-2 flex flex-col gap-2 -mt-1"), _uA(
                                _cV(unref(GenPagesApplyTabbarMyComponentsWalletCardClass), _uM("wallet-info" to walletInfo.value, "is-login" to unref(authStore).isLogin, "onWithdrawClick" to handleWithdrawClick, "onMallClick" to handleMallClick, "onExchangeClick" to handleExchangeClick), null, 8, _uA(
                                    "wallet-info",
                                    "is-login"
                                )),
                                _cV(unref(GenPagesApplyTabbarMyComponentsQuickFeaturesClass), _uM("onMenuClick" to handleQuickMenuClick)),
                                _cV(unref(GenPagesApplyTabbarMyComponentsAllianceCenterClass), _uM("poster-user-info" to posterUserInfo.value, "onMenuClick" to handleAllianceMenuClick), null, 8, _uA(
                                    "poster-user-info"
                                )),
                                _cV(unref(GenPagesApplyTabbarMyComponentsOtherFeaturesClass), _uM("onMenuClick" to handleOtherMenuClick))
                            ))
                        ))
                    )),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 4))
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
                return _uM("not-logged-in" to _pS(_uM("paddingTop" to "200rpx", "paddingBottom" to "200rpx")), "login-btn" to _pS(_uM("width" to "280rpx", "height" to "80rpx", "borderTopLeftRadius" to "40rpx", "borderTopRightRadius" to "40rpx", "borderBottomRightRadius" to "40rpx", "borderBottomLeftRadius" to "40rpx", "backgroundImage" to "linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)", "backgroundColor" to "rgba(0,0,0,0)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "login-btn-text" to _pS(_uM("color" to "#ffffff", "fontSize" to "30rpx", "fontWeight" to "bold")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
