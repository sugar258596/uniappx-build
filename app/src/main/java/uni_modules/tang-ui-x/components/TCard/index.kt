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
open class GenUniModulesTangUiXComponentsTCardIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String by `$props`
    open var subtitle: String by `$props`
    open var shadow: Boolean by `$props`
    open var border: Boolean by `$props`
    open var padding: String by `$props`
    open var radius: String by `$props`
    open var customClass: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenUniModulesTangUiXComponentsTCardIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenUniModulesTangUiXComponentsTCardIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val cardClass = computed(fun(): String {
                val classes = _uA(
                    "t-card",
                    "relative overflow-visible bg-surface shrink-0 grow-0 basis-auto min-h-0 min-w-0"
                ) as UTSArray<String>
                if (props.shadow) {
                    classes.push("shadow-card")
                }
                if (props.border) {
                    classes.push("border border-solid border-border")
                }
                when (props.padding) {
                    "small" -> 
                        classes.push("p-3")
                    "large" -> 
                        classes.push("p-7")
                    else -> 
                        classes.push("p-5")
                }
                when (props.radius) {
                    "none" -> 
                        classes.push("rounded-none")
                    "small" -> 
                        classes.push("rounded")
                    "large" -> 
                        classes.push("rounded-xl")
                    else -> 
                        classes.push("rounded-lg")
                }
                if (props.customClass !== "") {
                    classes.push(props.customClass)
                }
                return classes.join(" ")
            }
            )
            val cardStyle = computed(fun(): String {
                val styles: UTSArray<String> = _uA()
                if (props.shadow) {
                    styles.push("box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.12)")
                }
                if (props.border) {
                    styles.push("border-width: 1px")
                    styles.push("border-style: solid")
                    styles.push("border-color: #dcdfe6")
                }
                return styles.join("; ")
            }
            )
            val handleClick = fun(): Unit {
                emit("click")
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to _nC(cardClass.value), "style" to _nS(cardStyle.value), "onClick" to handleClick), _uA(
                    if (isTrue(_ctx.title !== "" || _ctx.subtitle !== "")) {
                        _cE("view", _uM("key" to 0, "class" to "t-card__header mb-4 flex flex-row items-center justify-between border-b border-border-soft pb-4"), _uA(
                            renderSlot(_ctx.`$slots`, "header", _uO(), fun(): UTSArray<Any> {
                                return _uA(
                                    _cE("view", _uM("class" to "t-card__header-content min-w-0 flex-1"), _uA(
                                        if (_ctx.title !== "") {
                                            _cE("text", _uM("key" to 0, "class" to "t-card__title text-lg font-semibold leading-6 text-primary"), _tD(_ctx.title), 1)
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        if (_ctx.subtitle !== "") {
                                            _cE("text", _uM("key" to 1, "class" to "t-card__subtitle mt-xs text-sm leading-5 text-tertiary"), _tD(_ctx.subtitle), 1)
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    ))
                                )
                            }),
                            renderSlot(_ctx.`$slots`, "extra")
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    _cE("view", _uM("class" to "t-card__body overflow-visible text-sm leading-6 text-secondary"), _uA(
                        renderSlot(_ctx.`$slots`, "default")
                    )),
                    renderSlot(_ctx.`$slots`, "footer")
                ), 6)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("click" to null)
        var props = _nP(_uM("title" to _uM("type" to "String", "required" to false, "default" to ""), "subtitle" to _uM("type" to "String", "required" to false, "default" to ""), "shadow" to _uM("type" to "Boolean", "required" to false, "default" to true), "border" to _uM("type" to "Boolean", "required" to false, "default" to false), "padding" to _uM("type" to "String", "required" to false, "default" to "medium"), "radius" to _uM("type" to "String", "required" to false, "default" to "medium"), "customClass" to _uM("type" to "String", "required" to false, "default" to "")))
        var propsNeedCastKeys = _uA(
            "title",
            "subtitle",
            "shadow",
            "border",
            "padding",
            "radius",
            "customClass"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
