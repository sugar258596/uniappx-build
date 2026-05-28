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
open class GenPagesCommonMessageIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesCommonMessageIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesCommonMessageIndex
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val messageStore = useMessageStore()
            val isLogin = computed(fun(): Boolean {
                return authStore.isLogin
            }
            )
            val tabItems = _uA<UTSJSONObject>(_uO("label" to "全部消息", "key" to 0), _uO("label" to "仅沟通", "key" to 1), _uO("label" to "有交换", "key" to 2))
            val messageBenefits = _uA(
                BenefitItem(icon = "icon-xiaoxi", label = "即时聊聊", desc = "实时在线互动"),
                BenefitItem(icon = "icon-tixing", label = "面试通知", desc = "机会准时送达"),
                BenefitItem(icon = "icon-clean", label = "高效沟通", desc = "一键建立联系")
            ) as UTSArray<BenefitItem>
            val handleClearUnread = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        await(messageStore.clearUnreadAndRefresh())
                })
            }
            val handleSystemClick = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/message/system/index"))
            }
            val handleInterviewClick = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/message/interview/notice"))
            }
            val handleSignInClick = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/message/signin/index"))
            }
            val handleToLogin = fun(){
                uni_navigateTo(NavigateToOptions(url = PAGE_PATHS["LOGIN"] as String))
            }
            val handleItemClick = fun(item: ConversationItem){
                if (item.ToMemberId != null && item.HireJobId != null) {
                    uni_navigateTo(NavigateToOptions(url = "/pages/common/message/chat/index?memberId=" + item.ToMemberId!! + "&hireJobId=" + item.HireJobId!!))
                } else {
                    uni_showToast(ShowToastOptions(title = "信息不完整", icon = "none"))
                }
            }
            val handleTabChange = fun(key: Any){
                messageStore.params.Type = parseInt("" + key) as Number
                messageStore.refreshMessageData()
            }
            onShow(fun(){
                if (isLogin.value) {
                    messageStore.refreshMessageData()
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-header-tab flex flex-col"), _uA(
                    if (isTrue(isLogin.value)) {
                        _cE(Fragment, _uM("key" to 0), _uA(
                            _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "消息", "show-back" to false)),
                            _cE("view", _uM("class" to "px-4"), _uA(
                                _cE("view", _uM("class" to "bg-white rounded-full flex flex-row w-full"), _uA(
                                    _cV(unref(GenComponentsCommonSearchBarIndexClass), _uM("modelValue" to unref(messageStore).params.Keywords, "onUpdate:modelValue" to fun(`$event`: String){
                                        unref(messageStore).params.Keywords = `$event`
                                    }, "onSearch" to unref(messageStore).refreshMessageData), null, 8, _uA(
                                        "modelValue",
                                        "onUpdate:modelValue",
                                        "onSearch"
                                    ))
                                ))
                            )),
                            _cE("view", _uM("class" to "flex-1 py-2 gap-2"), _uA(
                                _cE("view", _uM("class" to "flex flex-row justify-between"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTabsIndexClass), _uM("model-value" to unref(messageStore).params.Type, "default-active-key" to 0, "items" to tabItems, "active-color" to "#006633", "onChange" to handleTabChange), null, 8, _uA(
                                        "model-value"
                                    )),
                                    _cE("view", _uM("class" to "flex flex-row justify-center items-center gap-2 pr-2 tap-active", "onClick" to handleClearUnread), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-clean", "size" to 32, "color" to "#9ca3af")),
                                        _cE("text", _uM("class" to "text-sm text-gray-400"), "清除未读")
                                    ))
                                )),
                                _cE("scroll-view", _uM("class" to "flex-1", "refresher-enabled" to true, "refresher-triggered" to unref(messageStore).isRefreshing, "lower-threshold" to 100, "onRefresherrefresh" to unref(messageStore).refreshMessageData, "onScrolltolower" to unref(messageStore).loadMoreMessageData), _uA(
                                    _cE("view", _uM("class" to "px-2 flex flex-col gap-1"), _uA(
                                        _cV(unref(GenPagesCommonMessageComponentsItemClass), _uM("list" to unref(messageStore).conversationList, "notice-data" to unref(messageStore).noticeData, "sign-in-reminder-data" to unref(messageStore).signInReminderData, "interview-data" to unref(messageStore).interviewData, "show-notice" to (unref(messageStore).params.Type === 0), "onSystemClick" to handleSystemClick, "onInterviewClick" to handleInterviewClick, "onSignInClick" to handleSignInClick, "onItemClick" to handleItemClick), null, 8, _uA(
                                            "list",
                                            "notice-data",
                                            "sign-in-reminder-data",
                                            "interview-data",
                                            "show-notice"
                                        )),
                                        _cE("view", _uM("class" to "py-4 flex justify-center items-center"), _uA(
                                            if (isTrue(unref(messageStore).isLoadingMore)) {
                                                _cE("text", _uM("key" to 0, "class" to "text-gray-400 text-sm"), "加载中...")
                                            } else {
                                                if (isTrue(!unref(messageStore).hasMore && unref(messageStore).conversationList.length > 0)) {
                                                    _cE("text", _uM("key" to 1, "class" to "text-gray-400 text-sm"), "没有更多数据了")
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                            }
                                        ))
                                    ))
                                ), 40, _uA(
                                    "refresher-triggered",
                                    "onRefresherrefresh",
                                    "onScrolltolower"
                                ))
                            ))
                        ), 64)
                    } else {
                        _cV(unref(GenComponentsAuthGuideIndexClass), _uM("key" to 1, "icon" to "icon-xiaoxi", "title" to "消息沟通 · 实时掌握", "subtitle" to "登录后即可查看消息并与他人实时互动", "benefits" to messageBenefits, "onLogin" to handleToLogin))
                    }
                    ,
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 3))
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
                return _uM("nav-tabs" to _pS(_uM("width" to "auto")), "nav-label" to _uM(".nav-tabs " to _uM("paddingBottom" to "8rpx")), "nav-item" to _uM(".nav-tabs " to _uM("content::after" to "\"\"", "height::after" to 2, "width::after" to 0, "backgroundColor::after" to "#006633", "transitionProperty::after" to "width", "transitionDuration::after" to "0.3s", "transitionTimingFunction::after" to "ease")), "active" to _uM(".nav-tabs " to _uM("width::after" to "100%")), "select-box" to _pS(_uM("borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000")), "@TRANSITION" to _uM("nav-item" to _uM("property::after" to "width", "duration::after" to "0.3s", "timingFunction::after" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
