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
open class GenUniModulesTangUiXComponentsTEmptyIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String? by `$props`
    open var description: String? by `$props`
    open var image: String by `$props`
    open var showAction: Boolean by `$props`
    open var actionText: String? by `$props`
    open var compact: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTEmptyIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTEmptyIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emits(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val t = useI18n().t
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val displayTitle = computed<String>(fun(): String {
                if (hasTextValue(props.title)) {
                    return props.title as String
                }
                return t("empty.title")
            }
            )
            val displayDescription = computed<String>(fun(): String {
                if (hasTextValue(props.description)) {
                    return props.description as String
                }
                return t("empty.description")
            }
            )
            val displayActionText = computed<String>(fun(): String {
                if (hasTextValue(props.actionText)) {
                    return props.actionText as String
                }
                return t("empty.actionText")
            }
            )
            val handleActionClick = fun(){
                emits("actionClick")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(_uA(
                    "flex min-h-40 flex-col items-center justify-center",
                    if (_ctx.compact) {
                        "py-12"
                    } else {
                        "py-20"
                    }
                ))), _uA(
                    renderSlot(_ctx.`$slots`, "default", _uO(), fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to _nC(_uA(
                                "animate-fade-in-soft opacity-65",
                                if (_ctx.compact) {
                                    "mb-3 h-24 w-24"
                                } else {
                                    "mb-4 h-32 w-32"
                                }
                            ))), _uA(
                                _cE("image", _uM("src" to _ctx.image, "class" to "w-full h-full", "mode" to "aspectFit", "show-loading" to false), null, 8, _uA(
                                    "src"
                                ))
                            ), 2)
                        )
                    }
                    ),
                    _cE("view", _uM("class" to "w-full"), _uA(
                        _cE("text", _uM("class" to _nC(_uA(
                            "block text-center text-gray-700 twx-leading-14",
                            if (_ctx.compact) {
                                "text-base font-medium"
                            } else {
                                "text-lg font-medium"
                            }
                        ))), _tD(displayTitle.value), 3),
                        if (displayDescription.value !== "") {
                            _cE("text", _uM("key" to 0, "class" to _nC(_uA(
                                "mx-auto mt-2 block twx-maxw-80vw text-center text-gray-500 twx-leading-15",
                                if (_ctx.compact) {
                                    "text-xs"
                                } else {
                                    "text-sm"
                                }
                            ))), _tD(displayDescription.value), 3)
                        } else {
                            _cC("v-if", true)
                        }
                    )),
                    if (isTrue(_ctx.showAction)) {
                        _cE("view", _uM("key" to 0, "class" to "mt-6 w-full max-w-48"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "size" to "medium", "onClick" to handleActionClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return _uA(
                                    _tD(displayActionText.value)
                                )
                            }), "_" to 1))
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                ), 2)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("twx-leading-14" to _pS(_uM("lineHeight" to 1.4)), "twx-leading-15" to _pS(_uM("lineHeight" to 1.5)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("actionClick" to null)
        var props = _nP(_uM("title" to _uM("type" to "String", "required" to false), "description" to _uM("type" to "String", "required" to false), "image" to _uM("type" to "String", "required" to false, "default" to "/static/images/empty.png"), "showAction" to _uM("type" to "Boolean", "required" to false, "default" to false), "actionText" to _uM("type" to "String", "required" to false), "compact" to _uM("type" to "Boolean", "required" to false, "default" to false)))
        var propsNeedCastKeys = _uA(
            "image",
            "showAction",
            "compact"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
