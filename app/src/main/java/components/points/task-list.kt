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
open class GenComponentsPointsTaskList : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var currentTaskType: Number by `$props`
    open var taskTabItems: UTSArray<TabItem> by `$props`
    open var currentTasks: UTSArray<TaskItem> by `$props`
    open var rulesContent: UTSArray<String>? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsTaskList) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsTaskList
            val _cache = __ins.renderCache
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleTabChange = fun(_key: Any){
                emit("tab-change", _key)
            }
            val handleDoTask = fun(task: TaskItem){
                emit("do-task", task)
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg pb-10"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between relative overflow-visible"), _uA(
                        _cV(unref(GenUniModulesTangUiXComponentsTabsIndexClass), _uM("model-value" to _ctx.currentTaskType, "items" to _ctx.taskTabItems, "centered" to "", "active-color" to "#f97316", "onChange" to handleTabChange), null, 8, _uA(
                            "model-value",
                            "items"
                        )),
                        _cE("view", _uM("class" to "absolute top right-0 flex-row items-center gap-1 pb-1 overflow-visible"), _uA(
                            _cV(unref(GenComponentsPointsRuleTitleClass), _uM("rule-btn-text" to "规则", "rules-content" to _ctx.rulesContent), null, 8, _uA(
                                "rules-content"
                            ))
                        ))
                    )),
                    _cE("view", _uM("class" to "flex flex-col gap-6 px-2"), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(_ctx.currentTasks, fun(task, __key, __index, _cached): Any {
                            return _cE("view", _uM("key" to task.id, "class" to "flex flex-row items-center gap-4"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-earn-active")),
                                _cE("view", _uM("class" to "flex-1 flex flex-col gap-2"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                        _cE("text", _uM("class" to "text-sm font-bold text-zice-800"), _tD(task.title), 1),
                                        if (isTrue(task.progress)) {
                                            _cE("text", _uM("key" to 0, "class" to "text-sm text-zice-800"), "(" + _tD(task.progress) + ")", 1)
                                        } else {
                                            _cC("v-if", true)
                                        }
                                        ,
                                        _cE("view", _uM("class" to "flex flex-row items-cente justify-center gap-2 rounded-full"), _uA(
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-earn-active", "size" to 30)),
                                            _cE("view", _uM("class" to "text-xs text-orange-500"), _tD(task.points), 1)
                                        ))
                                    )),
                                    _cE("text", _uM("class" to "text-xs text-zice-400"), _tD(task.description), 1)
                                )),
                                _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to if (task.status === "completed") {
                                    "default"
                                } else {
                                    "warning"
                                }
                                , "size" to "small", "shape" to "round", "plain" to (task.status !== "completed"), "disabled" to (task.status === "completed"), "onClick" to fun(){
                                    handleDoTask(task)
                                }
                                ), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return _uA(
                                        _tD(if (task.status === "completed") {
                                            "已完成"
                                        } else {
                                            "去完成"
                                        }
                                        )
                                    )
                                }
                                ), "_" to 2), 1032, _uA(
                                    "type",
                                    "plain",
                                    "disabled",
                                    "onClick"
                                ))
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
                return _uM("nav-label" to _uM(".nav-tabs " to _uM("paddingBottom" to "8rpx")), "nav-item" to _uM(".nav-tabs " to _uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "content::after" to "\"\"", "height::after" to 2, "width::after" to 0, "backgroundColor::after" to "#f97316", "transitionProperty::after" to "width", "transitionDuration::after" to "0.3s", "transitionTimingFunction::after" to "ease")), "active" to _uM(".nav-tabs " to _uM("width::after" to "30%")), "top" to _pS(_uM("top" to "50%", "transform" to "translateY(-50%)")), "@TRANSITION" to _uM("nav-item" to _uM("property::after" to "width", "duration::after" to "0.3s", "timingFunction::after" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("tab-change" to null, "view-rules" to null, "do-task" to null)
        var props = _nP(_uM("currentTaskType" to _uM("type" to "Number", "required" to true), "taskTabItems" to _uM("type" to "Array", "required" to true), "currentTasks" to _uM("type" to "Array", "required" to true), "rulesContent" to _uM("type" to "Array", "required" to false)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
