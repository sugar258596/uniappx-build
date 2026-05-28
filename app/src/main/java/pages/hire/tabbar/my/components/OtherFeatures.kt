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
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesHireTabbarMyComponentsOtherFeatures : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarMyComponentsOtherFeatures) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarMyComponentsOtherFeatures
            val _cache = __ins.renderCache
            val menuItems = _uA(
                FeatureMenuItem__1(icon = "icon-dizhi", label = "我的地址", path = "/pages/common/user-info/address/index"),
                FeatureMenuItem__1(icon = "icon-jingjiren", label = "经纪人中心", path = ""),
                FeatureMenuItem__1(icon = "icon-tuandui", label = "我的团队", path = "/pages/common/team/index"),
                FeatureMenuItem__1(icon = "icon-qiye", label = "企业信息", path = "/pages/hire/enterprise/company"),
                FeatureMenuItem__1(icon = "icon-jiamengshang", label = "加盟", path = "/pages/common/join/index"),
                FeatureMenuItem__1(icon = "icon-48", label = "充值中心", path = "/pages/common/recharge/index"),
                FeatureMenuItem__1(icon = "icon-guanyu", label = "关于我们", path = "/pages/common/system/about/index"),
                FeatureMenuItem__1(icon = "icon-jiaohuan", label = "更换账号", path = "/pages/common/system/switch-account/index"),
                FeatureMenuItem__1(icon = "icon-bianji", label = "投诉建议", path = "/pages/common/feedback/index"),
                FeatureMenuItem__1(icon = "icon-xitong", label = "系统设置", path = "/pages/common/system/settings/index")
            ) as UTSArray<FeatureMenuItem__1>
            val authStore = useAuthStore()
            val currentInstance = getCurrentInstance()
            val POSTER_BACKGROUND_URL = "/static/images/yaoqi.png"
            val displayName = computed(fun(): String {
                val info = authStore.userInfo
                return if (info != null) {
                    (info!!.NickName ?: "用户")
                } else {
                    "用户"
                }
            }
            )
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val posterConfig = reactive<PosterConfig>(PosterConfig(canvasWidth = 300, canvasHeight = 450, backgroundUrl = POSTER_BACKGROUND_URL, avatarUrl = "", qrCodeUrl = "", inviteCode = "", avatarText = TextLine(line1 = "", line2 = "邀请您加入"), qrCodeText = TextLine(line1 = "长按识别二维码", line2 = "加入我们的团队"), capsuleOffset = 0))
            val _usePoster = usePoster(UsePosterOptions(canvasId = "other-features-poster-canvas", posterConfig = posterConfig, getPixelRatio = fun(): Number {
                return uni_getSystemInfoSync().pixelRatio
            }
            , queryIn = currentInstance))
            val generatePoster = _usePoster.generatePoster
            val showPosterModal = _usePoster.showPosterModal
            val exportedImagePath = _usePoster.exportedImagePath
            val saveImage = _usePoster.saveImage
            val handlePosterShare = _usePoster.handlePosterShare
            val exportCanvasAsImage = _usePoster.exportCanvasAsImage
            val syncPosterQrcode = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        val res = await(getQrcode())
                        posterConfig.qrCodeUrl = if (res != null) {
                            (res!!.InviteQRcode ?: "")
                        } else {
                            ""
                        }
                        if (res != null && (res!!.ReferralCode ?: "") != "") {
                            posterConfig.inviteCode = res!!.ReferralCode
                        }
                })
            }
            val handleGeneratePoster = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (!authStore.isLogin) {
                            uni_showToast(ShowToastOptions(title = "请先登录", icon = "none"))
                            setTimeout(fun(){
                                uni_navigateTo(NavigateToOptions(url = "/pages/auth/login"))
                            }
                            , 1000)
                            return@w1
                        }
                        try {
                            await(syncPosterQrcode())
                            await(generatePoster())
                            await(exportCanvasAsImage())
                            showPosterModal.value = true
                        }
                         catch (error: Throwable) {
                            uni_showToast(ShowToastOptions(title = "生成失败", icon = "none"))
                            console.error(error)
                        }
                })
            }
            watch(fun(): GetUserInfoResult? {
                return authStore.userInfo
            }
            , fun(value: GetUserInfoResult?){
                posterConfig.inviteCode = if (value != null) {
                    (value!!.ReferralCode ?: "")
                } else {
                    ""
                }
                posterConfig.avatarUrl = if (value != null) {
                    (value!!.Avatar ?: "")
                } else {
                    ""
                }
                posterConfig.avatarText.line1 = "我是" + displayName.value
            }
            , WatchOptions(immediate = true))
            val handleMenuClick = fun(item: FeatureMenuItem__1){
                if (item.label === "经纪人中心") {
                    handleGeneratePoster()
                } else {
                    emit("menuClick", item)
                }
            }
            val handleClosePoster = fun(){
                showPosterModal.value = false
            }
            val handleSavePoster = fun(){
                saveImage()
            }
            val handleSharePoster = fun(){
                handlePosterShare(exportedImagePath.value)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "other-features"), _uA(
                    _cV(unref(GenComponentsCommonFeatureGridIndexClass), _uM("title" to "其它功能", "items" to menuItems, "columns" to 4, "onMenuClick" to handleMenuClick)),
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to unref(showPosterModal), "onUpdate:modelValue" to fun(`$event`: Boolean){
                        trySetRefValue(showPosterModal, `$event`)
                    }
                    , "position" to "center", "show-title" to false, "background-color" to "transparent", "width" to "90%", "height" to "70%", "mask-close-able" to false), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col items-center justify-center gap-2"), _uA(
                                if (isTrue(unref(exportedImagePath))) {
                                    _cE("image", _uM("key" to 0, "src" to unref(exportedImagePath), "mode" to "widthFix", "class" to "rounded-lg shadow-lg"), null, 8, _uA(
                                        "src"
                                    ))
                                } else {
                                    _cC("v-if", true)
                                }
                                ,
                                _cE("view", _uM("class" to "flex flex-row justify-center gap-2 w-full"), _uA(
                                    _cE("view", _uM("class" to "flex-1"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("size" to "medium", "block" to "", "custom-class" to "bg-white border-0 shadow-sm", "onClick" to handleSavePoster), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                            return _uA(
                                                _cE("view", _uM("class" to "flex flex-row items-center justify-center gap-1"), _uA(
                                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiazai1", "size" to 20, "color" to "#333333")),
                                                    _cE("text", _uM("class" to "text-gray-800 text-sm font-bold"), "保存到本地")
                                                ))
                                            )
                                        }
                                        ), "_" to 1))
                                    )),
                                    _cE("view", _uM("class" to "flex-1"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("size" to "medium", "type" to "primary", "block" to "", "custom-class" to "bg-green-500 border-0 shadow-sm", "onClick" to handleSharePoster), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                            return _uA(
                                                _cE("view", _uM("class" to "flex flex-row items-center justify-center gap-2"), _uA(
                                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-fenxiang", "size" to 20, "color" to "#ffffff")),
                                                    _cE("text", _uM("class" to "text-white text-sm font-bold"), "分享到...")
                                                ))
                                            )
                                        }
                                        ), "_" to 1))
                                    ))
                                )),
                                _cE("view", _uM("class" to "p-2 bg-white-50 rounded-full active:opacity-80 backdrop-blur-sm", "onClick" to handleClosePoster), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-shanchu"))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue"
                    )),
                    _cE("canvas", _uM("id" to "other-features-poster-canvas", "canvas-id" to "other-features-poster-canvas", "class" to "poster-canvas"))
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
                return _uM("logout-btn" to _pS(_uM("!backgroundColor" to "#f3f4f6", "!color" to "#22c55e", "!borderTopWidth" to "medium", "!borderRightWidth" to "medium", "!borderBottomWidth" to "medium", "!borderLeftWidth" to "medium", "!borderTopStyle" to "none", "!borderRightStyle" to "none", "!borderBottomStyle" to "none", "!borderLeftStyle" to "none", "!borderTopColor" to "#000000", "!borderRightColor" to "#000000", "!borderBottomColor" to "#000000", "!borderLeftColor" to "#000000")), "poster-canvas" to _pS(_uM("position" to "fixed", "left" to -9999, "width" to 300, "height" to 450)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("menuClick" to null, "logoutClick" to null)
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
