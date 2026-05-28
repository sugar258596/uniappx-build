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
open class GenComponentsSharePopupIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var modelValue: Boolean by `$props`
    open var height: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsSharePopupIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsSharePopupIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleModelValueChange = fun(value: Boolean){
                emit("update:modelValue", value)
            }
            val handleClose = fun(){
                handleModelValueChange(false)
            }
            val handleShareToFriend = fun(){
                emit("share-to-friend")
            }
            val handleShareToMoments = fun(){
                emit("share-to-moments")
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("model-value" to _ctx.modelValue, "position" to "bottom", "round" to true, "title" to "分享到", "height" to props.height, "onUpdate:modelValue" to handleModelValueChange), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "share-popup-content"), _uA(
                            _cE("view", _uM("class" to "flex flex-row justify-center gap-16 py-12"), _uA(
                                _cE("view", _uM("class" to "flex flex-col items-center active:opacity-70", "onClick" to handleShareToFriend), _uA(
                                    _cE("view", _uM("class" to "w-30 h-30 rounded-full bg-green-500 flex items-center justify-center"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-weixin", "size" to 56, "color" to "#ffffff"))
                                    )),
                                    _cE("text", _uM("class" to "text-sm text-gray-700 mt-2"), "微信好友")
                                )),
                                _cE("view", _uM("class" to "flex flex-col items-center active:opacity-70", "onClick" to handleShareToMoments), _uA(
                                    _cE("view", _uM("class" to "w-30 h-30 rounded-full bg-green-600 flex items-center justify-center"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-pengyouquan", "size" to 56, "color" to "#ffffff"))
                                    )),
                                    _cE("text", _uM("class" to "text-sm text-gray-700 mt-2"), "朋友圈")
                                ))
                            )),
                            _cE("view", _uM("class" to "py-6 border-t border-gray-100 active:opacity-70", "onClick" to handleClose), _uA(
                                _cE("text", _uM("class" to "text-base text-gray-600 text-center"), "取消")
                            ))
                        ))
                    )
                }
                ), "_" to 1), 8, _uA(
                    "model-value",
                    "height"
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
                return _uM("share-popup-content" to _pS(_uM("backgroundColor" to "#ffffff", "paddingTop" to "32rpx", "paddingRight" to "32rpx", "paddingBottom" to "32rpx", "paddingLeft" to "32rpx")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("update:modelValue" to null, "share-to-friend" to null, "share-to-moments" to null)
        var props = _nP(_uM("modelValue" to _uM("type" to "Boolean", "default" to false), "height" to _uM("type" to "String", "default" to "45%")))
        var propsNeedCastKeys = _uA(
            "modelValue",
            "height"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
