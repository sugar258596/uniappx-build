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
open class GenPagesApplyResumeOnlineComponentsBasicInfo : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var list: UTSArray<EducationItem> by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineComponentsBasicInfo) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineComponentsBasicInfo
            val _cache = __ins.renderCache
            val resumeStore = useResumeStore()
            val handleAdd = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/resume/online/add-education"))
            }
            val handleEdit = fun(_edu: EducationItem, index: Number){
                val raw = (resumeStore.resumeData.EducationalBackCodeData ?: _uA())[index]
                if (raw != null) {
                    resumeStore.setEditingEducation(raw)
                }
                val id = raw?.EducationalBackId ?: 0
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/resume/online/add-education?id=" + id))
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg p-6"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between mb-4"), _uA(
                        _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "教育经历"),
                        _cE("view", _uM("class" to "flex items-center justify-center", "onClick" to handleAdd), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-tianjia", "size" to 40, "color" to "#999"))
                        ))
                    )),
                    _cE("view", _uM("class" to "flex flex-col"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(edu, index, __index, _cached): Any {
                            return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                "flex flex-row items-start py-4 border-b border-gray-100",
                                _uM("border-b-0" to (index === _ctx.list.length - 1))
                            )), "onClick" to fun(){
                                handleEdit(edu, index)
                            }
                            ), _uA(
                                _cE("view", _uM("class" to "w-3 h-3 rounded-full bg-teal-600 mt-2 mr-4")),
                                _cE("view", _uM("class" to "flex-1 flex flex-col gap-2"), _uA(
                                    _cE("text", _uM("class" to "text-base text-gray-800"), _tD(edu.SchoolName), 1),
                                    _cE("text", _uM("class" to "text-sm text-gray-500"), _tD(edu.SchoolBeginTime) + "-" + _tD(edu.SchoolEndTime) + " " + _tD(edu.SchoolMajor) + " " + _tD(edu.Degree), 1)
                                )),
                                _cE("view", _uM("class" to "flex items-center justify-center h-12"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-youjiantou", "size" to 28, "color" to "#ccc"))
                                ))
                            ), 10, _uA(
                                "onClick"
                            ))
                        }
                        ), 128)
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
                return _uM("border-b-0" to _pS(_uM("borderBottomWidth" to 0)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM("list" to _uM("type" to "Array", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
