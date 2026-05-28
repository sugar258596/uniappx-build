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
open class GenPagesApplyUserPrivacy : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyUserPrivacy) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyUserPrivacy
            val _cache = __ins.renderCache
            val privacyItems = _uA(
                PrivacyItem(id = "block_company", icon = "icon-shouji", title = "屏蔽公司", desc = "设置指定公司不可看见你的简历，屏蔽后双向不被推荐", iconBg = "#f0fdf4"),
                PrivacyItem(id = "hide_resume", icon = "icon-shouji", title = "隐藏简历", desc = "设置简历查看范围，对老板隐藏简历", iconBg = "#f0fdf4")
            ) as UTSArray<PrivacyItem>
            val handleItemClick = fun(item: PrivacyItem){
                if (item.id === "block_company") {
                    uni_navigateTo(NavigateToOptions(url = "/pages/apply/user/block"))
                } else if (item.id === "hide_resume") {
                    uni_navigateTo(NavigateToOptions(url = "/pages/apply/resume/hide"))
                }
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cE("view", _uM("class" to "bg h-130"), _uA(
                        _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "", "background" to "transparent")),
                        _cE("view", _uM("class" to "px-4 pt-30"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                _cE("view", _uM("class" to "flex-1 pr-60"), _uA(
                                    _cE("text", _uM("class" to "text-xl font-bold text-green-900"), "隐藏保护"),
                                    _cE("text", _uM("class" to "text-sm text-gray-500 block mt-4"), "查看管理你的已提供的权限，使用的你的信息，以及如何保证你的信息安全")
                                ))
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "px-6 -mt-10 flex flex-col gap-6"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(privacyItems, fun(item, __key, __index, _cached): Any {
                            return _cE("view", _uM("key" to item.id, "class" to "bg-white rounded-2xl p-6 flex flex-row items-center justify-between shadow-sm tap-active", "onClick" to fun(){
                                handleItemClick(item)
                            }
                            ), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-4 flex-1"), _uA(
                                    _cE("view", _uM("class" to "w-12 h-12 shrink-0 rounded-2xl flex items-center justify-center", "style" to _nS(_uM("backgroundColor" to item.iconBg))), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to if (item.id === "block_company") {
                                            "icon-jiesuo"
                                        } else {
                                            "icon-jinzhichakan"
                                        }
                                        , "color" to "#10b981", "size" to 32), null, 8, _uA(
                                            "name"
                                        ))
                                    ), 4),
                                    _cE("view", _uM("class" to "flex flex-col gap-2 flex-1 pr-30"), _uA(
                                        _cE("text", _uM("class" to "text-lg font-bold text-gray-800"), _tD(item.title), 1),
                                        _cE("text", _uM("class" to "text-xs text-gray-400 leading-snug"), _tD(item.desc), 1)
                                    ))
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 20, "color" to "#d1d5db"))
                            ), 8, _uA(
                                "onClick"
                            ))
                        }
                        ), 64)
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
                return _uM("header-bg" to _pS(_uM("backgroundImage" to "linear-gradient(to bottom, #ecfdf5, #f9fafb)", "backgroundColor" to "rgba(0,0,0,0)")), "tap-active" to _pS(_uM("opacity:active" to 0.9, "transform:active" to "scale(0.99)", "transitionProperty:active" to "all", "transitionDuration:active" to "0.1s", "transitionTimingFunction:active" to "ease")), "@TRANSITION" to _uM("tap-active" to _uM("property:active" to "all", "duration:active" to "0.1s", "timingFunction:active" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
