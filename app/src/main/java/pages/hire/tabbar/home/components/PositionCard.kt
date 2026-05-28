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
open class GenPagesHireTabbarHomeComponentsPositionCard : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var item: Any by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesHireTabbarHomeComponentsPositionCard) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesHireTabbarHomeComponentsPositionCard
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val dataObj = computed(fun(): UTSJSONObject {
                return props.item as UTSJSONObject
            }
            )
            val handleClick = fun(){
                val id = dataObj.value["Id"] as Number?
                emit("click", id ?: 0)
            }
            val avatarUrl = computed(fun(): String {
                return (dataObj.value["Avatar"] as String?) ?: ""
            }
            )
            val sexCode = computed(fun(): Number {
                return ((dataObj.value["Sex"] as Number?) ?: 0) as Number
            }
            )
            val sexIcon = computed(fun(): String {
                return if (sexCode.value === 1) {
                    "icon-nv"
                } else {
                    "icon-nan"
                }
            }
            )
            val sexColor = computed(fun(): String {
                return if (sexCode.value === 1) {
                    "red"
                } else {
                    "blue"
                }
            }
            )
            val userNick = computed(fun(): String {
                return (dataObj.value["UserNick"] as String?) ?: ""
            }
            )
            val education = computed(fun(): String {
                return (dataObj.value["Education"] as String?) ?: ""
            }
            )
            val helpType = computed(fun(): String {
                return (dataObj.value["HelpType"] as String?) ?: ""
            }
            )
            val hasPurpose = computed(fun(): Boolean {
                val arr = dataObj.value["PurposeCodeData"] as UTSArray<Any>?
                return arr != null && arr!!.length > 0
            }
            )
            val firstPurpose = computed(fun(): UTSJSONObject? {
                if (!hasPurpose.value) {
                    return null
                }
                val arr = dataObj.value["PurposeCodeData"] as UTSArray<Any>
                return arr[0] as UTSJSONObject
            }
            )
            val minSalary = computed(fun(): String {
                val p = firstPurpose.value
                return if (p != null) {
                    ((p!!["MinimumSalary"] as String?) ?: "")
                } else {
                    ""
                }
            }
            )
            val maxSalary = computed(fun(): String {
                val p = firstPurpose.value
                return if (p != null) {
                    ((p!!["HighestSalary"] as String?) ?: "")
                } else {
                    ""
                }
            }
            )
            val hasWorkExp = computed(fun(): Boolean {
                val arr = dataObj.value["WorkExperienceCodeData"] as UTSArray<Any>?
                return arr != null && arr!!.length > 0
            }
            )
            val firstWorkExp = computed(fun(): UTSJSONObject? {
                if (!hasWorkExp.value) {
                    return null
                }
                val arr = dataObj.value["WorkExperienceCodeData"] as UTSArray<Any>
                return arr[0] as UTSJSONObject
            }
            )
            val companyName = computed(fun(): String {
                val w = firstWorkExp.value
                return if (w != null) {
                    ((w!!["CompanyName"] as String?) ?: "")
                } else {
                    ""
                }
            }
            )
            val addTime = computed(fun(): String {
                val w = firstWorkExp.value
                return if (w != null) {
                    ((w!!["JobBeginTime"] as String?) ?: "")
                } else {
                    ""
                }
            }
            )
            val jobEndTime = computed(fun(): String {
                val w = firstWorkExp.value
                return if (w != null) {
                    ((w!!["JobEndTime"] as String?) ?: "")
                } else {
                    ""
                }
            }
            )
            val descriptionText = computed(fun(): String {
                val superiority = dataObj.value["MySuperiority"] as String?
                val experienced = dataObj.value["JobExperienced"] as String?
                if (superiority != null && superiority != "") {
                    return superiority!!
                }
                if (experienced != null && experienced != "") {
                    return experienced!!
                }
                return "暂无描述"
            }
            )
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "w-full gap-4", "padding" to "small", "custom-class" to "shadow-lg tap-active", "onClick" to handleClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "flex flex-row gap-4"), _uA(
                            _cE("view", _uM("class" to "relative"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to avatarUrl.value, "size" to 50), null, 8, _uA(
                                    "src"
                                )),
                                _cE("view", _uM("class" to "absolute bottom-0 right-0 bg-white rounded-full"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to sexIcon.value, "size" to 30, "color" to sexColor.value), null, 8, _uA(
                                        "name",
                                        "color"
                                    ))
                                ))
                            )),
                            _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                                _cE("view", _uM("class" to "text-base text-zice-800 font-bold"), _tD(userNick.value), 1),
                                _cE("view", _uM("class" to "flex flex-row gap-2"), _uA(
                                    _cE("text", _uM("class" to "text-sm text-zice-600"), _tD(education.value), 1),
                                    _cE("text", _uM("class" to "text-sm text-zice-600"), " | " + _tD(helpType.value), 1),
                                    if (isTrue(hasPurpose.value)) {
                                        _cE("text", _uM("key" to 0, "class" to "text-sm text-zice-600"), " | " + _tD(minSalary.value) + " - " + _tD(maxSalary.value), 1)
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            ))
                        )),
                        _cE("view", _uM("class" to "flex flex-col gap-2 mt-2"), _uA(
                            if (isTrue(hasWorkExp.value)) {
                                _cE("view", _uM("key" to 0, "class" to "flex flex-row justify-between items-start"), _uA(
                                    _cE("view", _uM("class" to "w-80 text-sm font-bold text-zice-800 line-clamp-1"), _tD(companyName.value), 1),
                                    _cE("view", _uM("class" to "flex flex-row items-end shrink-0"), _uA(
                                        _cE("text", _uM("class" to "text-sm text-zice-600"), _tD(addTime.value), 1),
                                        _cE("text", _uM("class" to "text-sm text-zice-600"), "- " + _tD(jobEndTime.value), 1)
                                    ))
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                            ,
                            _cE("view", _uM("class" to "text-sm text-zice-600 line-clamp-2"), _tD(descriptionText.value), 1)
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
                return _uM("shadow-lg" to _pS(_uM("!boxShadow" to "0 2px 12px 3px rgba(0, 0, 0, 0.1)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null)
        var props = _nP(_uM("item" to _uM("required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
