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
open class GenUniModulesTangUiXComponentsTActionSheetIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var actions: UTSArray<ActionSheetAction> by `$props`
    open var title: String by `$props`
    open var description: String by `$props`
    open var cancelText: String? by `$props`
    open var closeOnClickAction: Boolean by `$props`
    open var closeOnClickOverlay: Boolean by `$props`
    open var customClass: String by `$props`
    open var customStyle: String by `$props`
    open var modelValue: Any? by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTActionSheetIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTActionSheetIndex
            val _cache = __ins.renderCache
            val props = __props
            val visible = useModel<Any>(__ins.props, "modelValue") as Ref<Boolean>
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val `$t` = useI18n().`$t`
            val hasTextValue = fun(value: String?): Boolean {
                return value != null && value !== ""
            }
            val hasTitle = computed(fun(): Boolean {
                return hasTextValue(props.title)
            }
            )
            val hasDescription = computed(fun(): Boolean {
                return hasTextValue(props.description)
            }
            )
            val showHeader = computed(fun(): Boolean {
                return hasTitle.value || hasDescription.value
            }
            )
            val displayCancelText = computed(fun(): String {
                return if (props.cancelText != null && props.cancelText !== "") {
                    props.cancelText as String
                } else {
                    `$t`("actionSheet.cancelText", _uO())
                }
            }
            )
            val canCloseOnClickAction = computed(fun(): Boolean {
                return props.closeOnClickAction == true
            }
            )
            val getActionClass = fun(action: ActionSheetAction): String {
                var className = "t-action-sheet__action flex items-center justify-center border-b border-border-soft p-4 text-center"
                if (action.disabled == true || action.loading == true) {
                    className = className + " opacity-50"
                }
                return className
            }
            val getActionTextStyle = fun(action: ActionSheetAction): String {
                if (action.color != null && action.color !== "") {
                    return "color: " + action.color!!
                }
                return ""
            }
            val handleSelect = fun(action: ActionSheetAction, index: Number): Unit {
                if (action.disabled == true || action.loading == true) {
                    return
                }
                emit("select", action, index)
                if (canCloseOnClickAction.value) {
                    visible.value = false
                }
            }
            val handleCancel = fun(): Unit {
                emit("cancel")
                visible.value = false
            }
            val handleClose = fun(): Unit {
                emit("close")
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to visible.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                    visible.value = `$event`
                }
                , "position" to "bottom", "show-title" to false, "show-close" to false, "close-on-click-overlay" to _ctx.closeOnClickOverlay, "border-radius" to 16, "height" to "auto", "onClose" to handleClose), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to _nC(_uA(
                            "t-action-sheet bg-transparent p-0",
                            _ctx.customClass
                        )), "style" to _nS(_ctx.customStyle)), _uA(
                            if (isTrue(showHeader.value)) {
                                _cE("view", _uM("key" to 0, "class" to "t-action-sheet__header border-b border-border-soft p-4 text-center"), _uA(
                                    if (isTrue(hasTitle.value)) {
                                        _cE("text", _uM("key" to 0, "class" to "t-action-sheet__title mb-2 text-base font-medium text-title"), _tD(_ctx.title), 1)
                                    } else {
                                        _cC("v-if", true)
                                    },
                                    if (isTrue(hasDescription.value)) {
                                        _cE("text", _uM("key" to 1, "class" to "t-action-sheet__description text-sm text-desc"), _tD(_ctx.description), 1)
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                            ,
                            _cE("view", _uM("class" to "t-action-sheet__actions max-h-actions overflow-y-auto"), _uA(
                                _cE(Fragment, null, RenderHelpers.renderList(_ctx.actions, fun(action, index, __index, _cached): Any {
                                    return _cE("view", _uM("key" to index, "class" to _nC(getActionClass(action)), "onClick" to fun(){
                                        handleSelect(action, index)
                                    }
                                    ), _uA(
                                        _cE("text", _uM("class" to "t-action-sheet__action-text text-base text-title", "style" to _nS(getActionTextStyle(action))), _tD(action.name), 5)
                                    ), 10, _uA(
                                        "onClick"
                                    ))
                                }
                                ), 128)
                            )),
                            _cE("view", _uM("class" to "t-action-sheet__cancel flex items-center justify-center border-t-8 border-action-divider p-4 text-center", "onClick" to handleCancel), _uA(
                                _cE("text", _uM("class" to "t-action-sheet__cancel-text text-base text-title"), _tD(displayCancelText.value), 1)
                            ))
                        ), 6)
                    )
                }
                ), "_" to 1), 8, _uA(
                    "modelValue",
                    "onUpdate:modelValue",
                    "close-on-click-overlay"
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("select" to null, "cancel" to null, "close" to null, "update:modelValue" to null)
        var props = _nP(_uM("actions" to _uM("type" to "Array", "required" to false, "default" to fun(): UTSArray<ActionSheetAction> {
            return _uA<ActionSheetAction>()
        }
        ), "title" to _uM("type" to "String", "required" to false, "default" to ""), "description" to _uM("type" to "String", "required" to false, "default" to ""), "cancelText" to _uM("type" to "String", "required" to false), "closeOnClickAction" to _uM("type" to "Boolean", "required" to false, "default" to true), "closeOnClickOverlay" to _uM("type" to "Boolean", "required" to false, "default" to true), "customClass" to _uM("type" to "String", "required" to false, "default" to ""), "customStyle" to _uM("type" to "String", "required" to false, "default" to ""), "modelValue" to _uM("default" to false)))
        var propsNeedCastKeys = _uA(
            "actions",
            "title",
            "description",
            "closeOnClickAction",
            "closeOnClickOverlay",
            "customClass",
            "customStyle",
            "modelValue"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
