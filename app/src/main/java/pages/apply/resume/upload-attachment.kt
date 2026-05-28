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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyResumeUploadAttachment : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeUploadAttachment) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeUploadAttachment
            val _cache = __ins.renderCache
            val methods = _uA(
                UploadMethod(id = "wechat", icon = "icon-weixin", title = "微信上传", color = "#22c55e", recommend = true),
                UploadMethod(id = "mobile", icon = "icon-shouji", title = "手机文件上传", desc = "从手机文件管理中上传", color = "#3b82f6")
            ) as UTSArray<UploadMethod>
            val isUploading = ref(false)
            val handleWechatUpload = fun(){
                uni_showToast(ShowToastOptions(title = "当前环境不支持微信上传", icon = "none"))
            }
            val handleMobileUpload = fun(){}
            val handleMethodClick = fun(item: UploadMethod){
                if (item.id === "wechat") {
                    handleWechatUpload()
                } else if (item.id === "mobile") {
                    handleMobileUpload()
                }
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-gray-50"), _uA(
                    _cE("view", _uM("class" to "bg h-116"), _uA(
                        _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "管理附件", "background" to "transparent")),
                        _cE("view", _uM("class" to "px-4 pt-30"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                _cE("view", _uM("class" to "flex-1 pr-50"), _uA(
                                    _cE("text", _uM("class" to "text-xl font-bold text-green-900"), "请选择上传方式"),
                                    _cE("text", _uM("class" to "text-sm text-gray-500 block mt-4"), "简历建议使用PDF文件，也支持JPG、PNG格式，大小不超过20M")
                                ))
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "px-4 flex flex-col gap-12"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(methods, fun(item, index, __index, _cached): Any {
                            return _cE("view", _uM("key" to index, "class" to "bg-white rounded-2xl p-6 flex flex-row items-center justify-between shadow-sm tap-active", "onClick" to fun(){
                                handleMethodClick(item)
                            }
                            ), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-2 flex-1"), _uA(
                                    _cE("view", _uM("class" to "flex items-center justify-center shrink-0 rounded-full"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to item.icon, "color" to item.color), null, 8, _uA(
                                            "name",
                                            "color"
                                        ))
                                    )),
                                    _cE("view", _uM("class" to "flex flex-col gap-2 justify-center flex-1 px-4"), _uA(
                                        _cE("view", _uM("class" to "flex flex-row justify-between items-center gap-2"), _uA(
                                            _cE("text", _uM("class" to "text-lg font-medium text-gray-800"), _tD(item.title), 1),
                                            if (isTrue(item.recommend)) {
                                                _cE("view", _uM("key" to 0, "class" to "bg-cyan-100 px-4 py-2 rounded-sm"), _uA(
                                                    _cE("text", _uM("class" to "text-xs text-cyan-600 font-medium"), "推荐")
                                                ))
                                            } else {
                                                _cC("v-if", true)
                                            }
                                        )),
                                        if (isTrue(item.desc)) {
                                            _cE("text", _uM("key" to 0, "class" to "text-xs text-gray-400"), _tD(item.desc), 1)
                                        } else {
                                            _cC("v-if", true)
                                        }
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
                return _uM("tap-active" to _pS(_uM("opacity:active" to 0.9, "transform:active" to "scale(0.995)", "transitionProperty:active" to "all", "transitionDuration:active" to "0.1s", "transitionTimingFunction:active" to "ease")), "@TRANSITION" to _uM("tap-active" to _uM("property:active" to "all", "duration:active" to "0.1s", "timingFunction:active" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
