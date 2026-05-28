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
open class GenPagesApplyTabbarMyComponentsUserHeader : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var userInfo: UserInfo by `$props`
    open var stats: UserStats by `$props`
    open var isLogin: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarMyComponentsUserHeader) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarMyComponentsUserHeader
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
            val handleResumeClick = fun(){
                emit("resumeClick")
            }
            val handleChatClick = fun(){
                emit("chatClick")
            }
            val handleInterviewClick = fun(){
                emit("interviewClick")
            }
            val handleViewedClick = fun(){
                emit("viewedClick")
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
                                        _cE("text", _uM("class" to "text-sm text-gray-700"), "我要招人")
                                    ))
                                )),
                                _cE("view", _uM("class" to "flex flex-row gap-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "scan", "size" to 40, "color" to "#666")),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "setting", "size" to 40, "color" to "#666"))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1)),
                    _cE("view", _uM("class" to "user-info flex flex-row items-center justify-between px-4 py-3"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                            _cE("view", _uM("class" to "avatar-wrap overflow-hidden"), _uA(
                                if (isTrue(props.isLogin)) {
                                    _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("key" to 0, "src" to props.userInfo.avatar, "size" to 50), null, 8, _uA(
                                        "src"
                                    ))
                                } else {
                                    _cE("view", _uM("key" to 1, "class" to "w-full h-full flex items-center justify-center bg-gray-50"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-yonghu", "size" to 70, "color" to "#ccc"))
                                    ))
                                }
                            )),
                            if (isTrue(props.isLogin)) {
                                _cE("view", _uM("key" to 0, "class" to "flex flex-col gap-2"), _uA(
                                    _cE("text", _uM("class" to "text-lg font-bold text-gray-800"), _tD(props.userInfo.nickname), 1),
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-2 tap-active", "onClick" to handleResumeClick), _uA(
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), " 简历完善" + _tD(props.userInfo.resumeProgress) + "%，" + _tD(props.userInfo.resumeTip), 1),
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "right", "size" to 24, "color" to "#9ca3af"))
                                    ))
                                ))
                            } else {
                                _cE("view", _uM("key" to 1, "class" to "flex flex-col gap-1 tap-active", "onClick" to fun(){
                                    emit("loginClick")
                                }
                                ), _uA(
                                    _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), "去登录/注册"),
                                    _cE("text", _uM("class" to "text-sm text-gray-500"), "登录后体验更多牛逼功能")
                                ), 8, _uA(
                                    "onClick"
                                ))
                            }
                        ))
                    )),
                    _cE("view", _uM("class" to "px-2 pb-2"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "mx-2", "padding" to "medium"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-row"), _uA(
                                    _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 tap-active", "onClick" to handleChatClick), _uA(
                                        _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                            props.stats.chatCount
                                        } else {
                                            "-"
                                        }
                                        ), 1),
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "沟通过")
                                    )),
                                    _cE("view", _uM("class" to "divider w-px bg-gray-200")),
                                    _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-2 tap-active", "onClick" to handleInterviewClick), _uA(
                                        _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                            props.stats.interviewCount
                                        } else {
                                            "-"
                                        }
                                        ), 1),
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "我的面试")
                                    )),
                                    _cE("view", _uM("class" to "divider w-px bg-gray-200")),
                                    _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-2 tap-active", "onClick" to handleViewedClick), _uA(
                                        _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                            props.stats.viewedCount
                                        } else {
                                            "-"
                                        }
                                        ), 1),
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "谁看过我")
                                    )),
                                    _cE("view", _uM("class" to "divider w-px bg-gray-200")),
                                    _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-2 tap-active", "onClick" to handleFavoriteClick), _uA(
                                        _cE("text", _uM("class" to "text-xl font-bold text-gray-800"), _tD(if (props.isLogin) {
                                            props.stats.favoriteCount
                                        } else {
                                            "-"
                                        }
                                        ), 1),
                                        _cE("text", _uM("class" to "text-sm text-gray-500"), "收藏")
                                    ))
                                ))
                            )
                        }
                        ), "_" to 1))
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
                return _uM("user-header" to _pS(_uM("backgroundImage" to "linear-gradient(to bottom, #a6efc0ff 0%, #fff 60%, #fff 100%)", "backgroundColor" to "rgba(0,0,0,0)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("serviceClick" to null, "switchRoleClick" to null, "resumeClick" to null, "chatClick" to null, "interviewClick" to null, "viewedClick" to null, "favoriteClick" to null, "loginClick" to null)
        var props = _nP(_uM("userInfo" to _uM("type" to "Object", "required" to true), "stats" to _uM("type" to "Object", "required" to true), "isLogin" to _uM("type" to "Boolean", "required" to true)))
        var propsNeedCastKeys = _uA(
            "isLogin"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
