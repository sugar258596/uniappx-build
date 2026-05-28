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
open class GenPagesCommonMessageComponentsItem : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var list: UTSArray<ConversationItem> by `$props`
    open var noticeData: Any? by `$props`
    open var signInReminderData: Any? by `$props`
    open var interviewData: Any? by `$props`
    open var showNotice: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesCommonMessageComponentsItem) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesCommonMessageComponentsItem
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val noticeInfo = computed(fun(): NoticeData? {
                return (props.noticeData ?: null) as NoticeData?
            }
            )
            val interviewInfo = computed(fun(): InterviewData? {
                return (props.interviewData ?: null) as InterviewData?
            }
            )
            val signInInfo = computed(fun(): SignInReminderData? {
                return (props.signInReminderData ?: null) as SignInReminderData?
            }
            )
            val handleSystemClick = fun(){
                emit("systemClick")
            }
            val handleInterviewClick = fun(){
                emit("interviewClick")
            }
            val handleSignInClick = fun(){
                emit("signInClick")
            }
            val handleItemClick = fun(item: ConversationItem){
                emit("itemClick", item)
            }
            return fun(): Any? {
                return _cE(Fragment, null, _uA(
                    if (isTrue(_ctx.showNotice && noticeInfo.value != null)) {
                        _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("key" to 0, "class" to "w-full gap-2 mb-2", "padding" to "small", "custom-class" to "tap-active", "onClick" to handleSystemClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row gap-4 flex-1 items-center"), _uA(
                                        _cE("view", _uM("class" to "w-12 h-12 bg-blue-100 rounded-full flex justify-center items-center"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xitong", "size" to 48, "color" to "#3b82f6"))
                                        )),
                                        _cE("view", _uM("class" to "flex flex-col gap-2 flex-1"), _uA(
                                            _cE("view", _uM("class" to "text-base font-bold text-zice-800"), "系统消息"),
                                            _cE("view", _uM("class" to "text-sm text-zice-600 line-clamp-1"), _tD(noticeInfo.value?.Title), 1)
                                        ))
                                    )),
                                    if (isTrue(noticeInfo.value?.IsRead != 1 && noticeInfo.value?.NoticeCountN !== 0)) {
                                        _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center"), _uA(
                                            _cE("text", _uM("class" to "px-2 min-w-4 h-4 bg-red-500 rounded-full text-white text-xs flex justify-center items-center"), _tD(noticeInfo.value?.NoticeCountN), 1)
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            )
                        }), "_" to 1))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    if (isTrue(_ctx.showNotice && interviewInfo.value != null)) {
                        _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("key" to 1, "class" to "w-full gap-2 mb-2", "padding" to "small", "custom-class" to "tap-active", "onClick" to handleInterviewClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row gap-4 flex-1 items-center"), _uA(
                                        _cE("view", _uM("class" to "w-12 h-12 bg-orange-100 rounded-full flex justify-center items-center"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-yuyuemianshi", "size" to 48, "color" to "#f97316"))
                                        )),
                                        _cE("view", _uM("class" to "flex flex-col gap-2 flex-1"), _uA(
                                            _cE("view", _uM("class" to "text-base font-bold text-zice-800"), "面试通知"),
                                            if (isTrue(interviewInfo.value?.ContentRemark)) {
                                                _cE("view", _uM("key" to 0, "class" to "text-sm text-zice-600 line-clamp-1"), _tD(interviewInfo.value?.ContentRemark), 1)
                                            } else {
                                                _cC("v-if", true)
                                            }
                                        ))
                                    )),
                                    if (isTrue(interviewInfo.value?.IsRead != 1 && interviewInfo.value?.Count !== 0)) {
                                        _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center"), _uA(
                                            _cE("text", _uM("class" to "px-2 min-w-4 h-4 bg-red-500 rounded-full text-white text-xs flex justify-center items-center"), _tD(interviewInfo.value?.Count), 1)
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            )
                        }), "_" to 1))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    if (isTrue(_ctx.showNotice && signInInfo.value != null)) {
                        _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("key" to 2, "class" to "w-full gap-2 mb-2", "padding" to "small", "custom-class" to "tap-active", "onClick" to handleSignInClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row gap-4 flex-1 items-center"), _uA(
                                        _cE("view", _uM("class" to "w-12 h-12 bg-green-100 rounded-full flex justify-center items-center"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-qiandao1", "size" to 48, "color" to "#10b981"))
                                        )),
                                        _cE("view", _uM("class" to "flex flex-col gap-2 flex-1"), _uA(
                                            _cE("view", _uM("class" to "text-base font-bold text-zice-800"), "签到通知"),
                                            if (isTrue(signInInfo.value?.ContentRemark)) {
                                                _cE("view", _uM("key" to 0, "class" to "text-sm text-zice-600 line-clamp-1"), _tD(signInInfo.value?.ContentRemark), 1)
                                            } else {
                                                _cC("v-if", true)
                                            }
                                        ))
                                    )),
                                    if (isTrue(signInInfo.value?.IsRead != 1 && signInInfo.value?.Count !== 0)) {
                                        _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center"), _uA(
                                            _cE("text", _uM("class" to "px-2 min-w-4 h-4 bg-red-500 rounded-full text-white text-xs flex justify-center items-center"), _tD(signInInfo.value?.Count), 1)
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            )
                        }), "_" to 1))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    _cE(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, index, __index, _cached): Any {
                        return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("key" to index, "class" to "w-full gap-2 mb-2", "padding" to "small", "custom-class" to "tap-active", "onClick" to fun(){
                            handleItemClick(item)
                        }
                        ), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row gap-4 flex-1 items-center"), _uA(
                                        _cE("view", _uM("class" to "rounded-full overflow-hidden"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to item.ToMemberAvatar, "size" to 50), null, 8, _uA(
                                                "src"
                                            ))
                                        )),
                                        _cE("view", _uM("class" to "flex flex-col gap-2 flex-1"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row justify-between items-center"), _uA(
                                                _cE("view", _uM("class" to "text-base font-bold text-zice-800"), _tD(item.ToMemberNickName), 1),
                                                _cE("view", _uM("class" to "text-xs text-gray-400"), _tD(item.AddTime?.split(" ")?.get(0)), 1)
                                            )),
                                            _cE("view", _uM("class" to "text-sm text-zice-600 line-clamp-1"), _tD(item.Content), 1)
                                        ))
                                    )),
                                    if (isTrue(item.UnreadCount)) {
                                        _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center ml-2"), _uA(
                                            _cE("text", _uM("class" to "px-2 min-w-4 h-4 bg-red-500 rounded-full text-white text-xs flex justify-center items-center"), _tD(item.UnreadCount), 1)
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            )
                        }
                        ), "_" to 2), 1032, _uA(
                            "onClick"
                        ))
                    }
                    ), 128),
                    if (_ctx.list.length === 0) {
                        _cE("view", _uM("key" to 3, "class" to "mt-20"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("text" to "暂无消息会话"))
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                ), 64)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("min-w-10" to _pS(_uM("minWidth" to 20.8)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("systemClick" to null, "interviewClick" to null, "signInClick" to null, "itemClick" to null)
        var props = _nP(_uM("list" to _uM("type" to "Array", "required" to true), "noticeData" to _uM("required" to false), "signInReminderData" to _uM("required" to false), "interviewData" to _uM("required" to false), "showNotice" to _uM("type" to "Boolean", "required" to true)))
        var propsNeedCastKeys = _uA(
            "showNotice"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
