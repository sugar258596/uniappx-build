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
open class GenPagesHireTabbarMyComponentsUserHeader : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var userInfo: UserInfo__1 by `$props`
    open var stats: UserStats__1 by `$props`
    open var isLogin: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarMyComponentsUserHeader) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarMyComponentsUserHeader
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleServiceClick = fun(){
                emit("serviceClick")
            }
            val handleSwitchRoleClick = fun(){
                emit("switchRoleClick")
            }
            val handleVipClick = fun(){
                emit("vipClick")
            }
            val handleApplicationClick = fun(){
                emit("applicationClick")
            }
            val handleInterviewClick = fun(){
                emit("interviewClick")
            }
            val handleFavoriteClick = fun(){
                emit("favoriteClick")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "user-header"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), null, _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-row justify-between items-center px-4 py-2"), _uA(
                                _cE("view", _uM("class" to "flex flex-row gap-3"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-1 tap-active", "onClick" to handleServiceClick), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-a-kefukefu", "size" to 36, "color" to "#666")),
                                        _cE("text", _uM("class" to "text-sm text-gray-700"), "客服")
                                    )),
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-1 tap-active", "onClick" to handleSwitchRoleClick), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-jiaoseqiehuan", "size" to 36, "color" to "#666")),
                                        _cE("text", _uM("class" to "text-sm text-gray-700"), "我要求职")
                                    ))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1)),
                    _cE("view", _uM("class" to "user-info flex flex-row items-center justify-between px-4 py-3"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                            _cE("view", _uM("class" to "avatar-wrap w-14 h-14 rounded-full overflow-hidden bg-gray-200"), _uA(
                                if (isTrue(props.isLogin)) {
                                    _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("key" to 0, "src" to props.userInfo.avatar, "width" to "100%", "height" to "100%", "error-text" to ""), null, 8, _uA(
                                        "src"
                                    ))
                                } else {
                                    _cE("view", _uM("key" to 1, "class" to "w-full h-full flex items-center justify-center bg-gray-50"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-yonghu", "size" to 70, "color" to "#ccc"))
                                    ))
                                }
                            )),
                            if (isTrue(props.isLogin)) {
                                _cE("view", _uM("key" to 0, "class" to "flex flex-col gap-1"), _uA(
                                    _cE("text", _uM("class" to "text-lg font-bold text-white"), _tD(props.userInfo.nickname), 1),
                                    _cE("text", _uM("class" to "text-sm text-white", "style" to _nS(_uM("opacity" to "0.8"))), _tD(props.userInfo.phone), 5)
                                ))
                            } else {
                                _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-1 tap-active", "onClick" to fun(){
                                    emit("loginClick")
                                }
                                ), _uA(
                                    _cE("text", _uM("class" to "text-lg font-bold text-white"), "去登录/注册"),
                                    _cE("text", _uM("class" to "text-sm text-white", "style" to _nS(_uM("opacity" to "0.8"))), "登录后体验更多牛逼功能", 4)
                                ), 8, _uA(
                                    "onClick"
                                ))
                            }
                        )),
                        if (isTrue(props.userInfo.isVerified)) {
                            _cE("view", _uM("key" to 0, "class" to "flex flex-row items-center gap-1 bg-white rounded-full px-2 py-1"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-qiye", "size" to 28, "color" to "#22c55e")),
                                _cE("text", _uM("class" to "text-xs text-green-500"), "通过")
                            ))
                        } else {
                            _cE("view", _uM("key" to 1, "class" to "flex flex-row items-center gap-1 bg-white rounded-full px-2 py-1"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-qiye", "size" to 28, "color" to "#eab308")),
                                _cE("text", _uM("class" to "text-xs text-yellow-500"), "没有通过")
                            ))
                        }
                    )),
                    _cE("view", _uM("class" to "px-4"), _uA(
                        _cE("view", _uM("class" to "pb-2"), _uA(
                            _cE("view", _uM("class" to "bg rounded-md flex flex-col gap-1 h-44"), _uA(
                                _cE("view", _uM("class" to "flex flex-row py-1"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-1 px-1"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-tongzhi", "color" to "#02054fff")),
                                        _cE("text", _uM("class" to "text-md text-white font-bold"), "我的会员")
                                    ))
                                )),
                                _cE("view", _uM("class" to "flex flex-row justify-between items-center px-3 py-1"), _uA(
                                    _cE("view", _uM("class" to "flex flex-col"), _uA(
                                        if (isTrue(!_ctx.userInfo.isVip)) {
                                            _cE("text", _uM("key" to 0, "class" to "text-sm text-white", "style" to _nS(_uM("opacity" to "0.8"))), "该账号暂未开通会员", 4)
                                        } else {
                                            _cE("text", _uM("key" to 1, "class" to "text-sm text-white", "style" to _nS(_uM("opacity" to "0.8"))), "会员有效期至 " + _tD(props.userInfo.vipExpireTime), 5)
                                        }
                                    )),
                                    if (isTrue(!_ctx.userInfo.isVip)) {
                                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("key" to 0, "size" to "small", "custom-class" to "vip-btn", "onClick" to handleVipClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                            return _uA(
                                                " 立即开通 "
                                            )
                                        }), "_" to 1))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "py-3"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return _uA(
                                        _cE("view", _uM("class" to "flex flex-row"), _uA(
                                            _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 tap-active", "onClick" to handleApplicationClick), _uA(
                                                _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                                    props.stats.applicationCount
                                                } else {
                                                    "-"
                                                }
                                                ), 1),
                                                _cE("text", _uM("class" to "text-sm text-gray-500"), "职位申请")
                                            )),
                                            _cE("view", _uM("class" to "divider w-px bg-gray-200")),
                                            _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-2 tap-active", "onClick" to handleInterviewClick), _uA(
                                                _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                                    props.stats.interviewCount
                                                } else {
                                                    "-"
                                                }
                                                ), 1),
                                                _cE("text", _uM("class" to "text-sm text-gray-500"), "面试日程")
                                            )),
                                            _cE("view", _uM("class" to "divider w-px bg-gray-200")),
                                            _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-2 tap-active", "onClick" to handleFavoriteClick), _uA(
                                                _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                                    props.stats.favoriteCount
                                                } else {
                                                    "-"
                                                }
                                                ), 1),
                                                _cE("text", _uM("class" to "text-sm text-gray-500"), "我的收藏")
                                            ))
                                        ))
                                    )
                                }
                                ), "_" to 1))
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
                return _uM("user-header" to _pS(_uM("backgroundImage" to "linear-gradient(to bottom, #a6efc0ff 0%, #fff 60%, #fff 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "vip-btn" to _pS(_uM("!backgroundColor" to "#facc15", "!color" to "#422006", "!borderTopWidth" to "medium", "!borderRightWidth" to "medium", "!borderBottomWidth" to "medium", "!borderLeftWidth" to "medium", "!borderTopStyle" to "none", "!borderRightStyle" to "none", "!borderBottomStyle" to "none", "!borderLeftStyle" to "none", "!borderTopColor" to "#000000", "!borderRightColor" to "#000000", "!borderBottomColor" to "#000000", "!borderLeftColor" to "#000000", "!borderTopLeftRadius" to "9999rpx", "!borderTopRightRadius" to "9999rpx", "!borderBottomRightRadius" to "9999rpx", "!borderBottomLeftRadius" to "9999rpx", "!fontSize" to "24rpx")), "divider" to _pS(_uM("height" to "60rpx")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("serviceClick" to null, "switchRoleClick" to null, "vipClick" to null, "applicationClick" to null, "interviewClick" to null, "favoriteClick" to null, "loginClick" to null)
        var props = _nP(_uM("userInfo" to _uM("type" to "Object", "required" to true), "stats" to _uM("type" to "Object", "required" to true), "isLogin" to _uM("type" to "Boolean", "required" to true)))
        var propsNeedCastKeys = _uA(
            "isLogin"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
