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
open class GenComponentsAuthGuideIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var icon: String by `$props`
    open var title: String by `$props`
    open var subtitle: String by `$props`
    open var benefits: UTSArray<BenefitItem> by `$props`
    open var buttonText: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsAuthGuideIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsAuthGuideIndex
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleLogin = fun(){
                emit("login")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "auth-guide-wrapper flex flex-col items-center"), _uA(
                    _cE("view", _uM("class" to "hero-section flex flex-col items-center justify-center pt-20"), _uA(
                        _cE("view", _uM("class" to "logo-outer bg-white rounded-full flex items-center justify-center shadow-sm"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to _ctx.icon, "size" to 70, "color" to "#10b981"), null, 8, _uA(
                                "name"
                            ))
                        )),
                        _cE("text", _uM("class" to "title-text text-xl font-bold text-gray-800 mt-8"), _tD(_ctx.title), 1),
                        _cE("text", _uM("class" to "subtitle-text text-sm text-gray-500 mt-2"), _tD(_ctx.subtitle), 1)
                    )),
                    _cE("view", _uM("class" to "benefit-card bg-white mx-8 rounded-2xl shadow-sm -mt-12"), _uA(
                        _cE("view", _uM("class" to "flex flex-row flex-wrap justify-between gap-4"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(_ctx.benefits, fun(item, index, __index, _cached): Any {
                                return _cE("view", _uM("key" to index, "class" to "benefit-item flex flex-col items-center flex-1"), _uA(
                                    _cE("view", _uM("class" to "icon-wrapper bg-green-50 rounded-full p-6"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to item.icon, "size" to 40, "color" to "#10b981"), null, 8, _uA(
                                            "name"
                                        ))
                                    )),
                                    _cE("text", _uM("class" to "item-label text-sm font-bold text-gray-800 mt-4"), _tD(item.label), 1),
                                    _cE("text", _uM("class" to "item-desc text-xs text-gray-400 mt-2"), _tD(item.desc), 1)
                                ))
                            }
                            ), 128)
                        )),
                        _cE("view", _uM("class" to "action-box mt-20 px-10"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("text" to _ctx.buttonText, "type" to "warning", "block" to "", "shape" to "round", "size" to "large", "shadow" to "", "onClick" to handleLogin), null, 8, _uA(
                                "text"
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
                return _uM("auth-guide-wrapper" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "backgroundColor" to "#ffffff")), "hero-section" to _pS(_uM("width" to "100%", "height" to 360, "backgroundImage" to "linear-gradient(180deg, #d1fae5 0%, #ffffff 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "logo-outer" to _pS(_uM("width" to 100, "height" to 100)), "benefit-card" to _pS(_uM("width" to "90%", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#f1f5f9", "borderRightColor" to "#f1f5f9", "borderBottomColor" to "#f1f5f9", "borderLeftColor" to "#f1f5f9")), "icon-wrapper" to _pS(_uM("width" to 60, "height" to 60, "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "benefit-item" to _pS(_uM("transitionProperty" to "transform", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease", "transform:active" to "scale(0.95)")), "@TRANSITION" to _uM("benefit-item" to _uM("property" to "transform", "duration" to "0.3s", "timingFunction" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("login" to null)
        var props = _nP(_uM("icon" to _uM("type" to "String", "required" to false, "default" to "icon-jifen"), "title" to _uM("type" to "String", "required" to true), "subtitle" to _uM("type" to "String", "required" to true), "benefits" to _uM("type" to "Array", "required" to true), "buttonText" to _uM("type" to "String", "required" to false, "default" to "立即登录")))
        var propsNeedCastKeys = _uA(
            "icon",
            "buttonText"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
