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
open class GenPagesApplyTabbarMyComponentsWalletCard : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var walletInfo: WalletInfo by `$props`
    open var isLogin: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarMyComponentsWalletCard) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarMyComponentsWalletCard
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleWithdrawClick = fun(){
                emit("withdrawClick")
            }
            val handleMallClick = fun(){
                emit("mallClick")
            }
            val handleExchangeClick = fun(){
                emit("exchangeClick")
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "mx-3", "padding" to "medium"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "flex flex-row"), _uA(
                            _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 py-1"), _uA(
                                _cE("text", _uM("class" to "text-xl font-bold text-orange-500"), "¥" + _tD(if (props.isLogin) {
                                    props.walletInfo.balance.toFixed(2)
                                } else {
                                    "-"
                                }
                                ), 1),
                                _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "wallet", "size" to 28, "color" to "#9ca3af")),
                                    _cE("text", _uM("class" to "text-sm text-gray-500"), "我的钱包")
                                )),
                                _cE("view", _uM("class" to "withdraw-btn flex flex-row items-center px-2 py-1 rounded-full tap-active", "onClick" to handleWithdrawClick), _uA(
                                    _cE("text", _uM("class" to "text-xs text-orange-500"), "提现"),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "right", "size" to 20, "color" to "#f97316"))
                                ))
                            )),
                            _cE("view", _uM("class" to "w-px bg-gray-200 my-2")),
                            _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 py-1"), _uA(
                                _cE("text", _uM("class" to "text-xl font-bold text-orange-500"), "积分好物"),
                                _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "gift", "size" to 28, "color" to "#9ca3af")),
                                    _cE("text", _uM("class" to "text-sm text-gray-500"), "我的商城")
                                )),
                                _cE("view", _uM("class" to "enter-btn flex flex-row items-center px-2 py-1 rounded-full tap-active", "onClick" to handleMallClick), _uA(
                                    _cE("text", _uM("class" to "text-xs text-green-600"), "进入"),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "right", "size" to 20, "color" to "#16a34a"))
                                ))
                            )),
                            _cE("view", _uM("class" to "w-px bg-gray-200 my-2")),
                            _cE("view", _uM("class" to "flex-1 flex flex-col items-center gap-1 py-1"), _uA(
                                _cE("text", _uM("class" to "text-xl font-bold text-orange-500"), _tD(if (props.isLogin) {
                                    props.walletInfo.points
                                } else {
                                    "-"
                                }
                                ), 1),
                                _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "star", "size" to 28, "color" to "#9ca3af")),
                                    _cE("text", _uM("class" to "text-sm text-gray-500"), "我的积分")
                                )),
                                _cE("view", _uM("class" to "exchange-btn flex flex-row items-center px-2 py-1 rounded-full tap-active", "onClick" to handleExchangeClick), _uA(
                                    _cE("text", _uM("class" to "text-xs text-orange-500"), "兑换"),
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "right", "size" to 20, "color" to "#f97316"))
                                ))
                            ))
                        ))
                    )
                }
                ), "_" to 1))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("withdraw-btn" to _pS(_uM("backgroundColor" to "#fff7ed")), "enter-btn" to _pS(_uM("backgroundColor" to "#dcfce7")), "exchange-btn" to _pS(_uM("backgroundColor" to "#fff7ed")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("withdrawClick" to null, "mallClick" to null, "exchangeClick" to null)
        var props = _nP(_uM("walletInfo" to _uM("type" to "Object", "required" to true), "isLogin" to _uM("type" to "Boolean", "required" to true)))
        var propsNeedCastKeys = _uA(
            "isLogin"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
