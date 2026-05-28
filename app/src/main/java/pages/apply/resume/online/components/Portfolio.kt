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
import io.dcloud.uniapp.extapi.chooseImage as uni_chooseImage
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyResumeOnlineComponentsPortfolio : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var images: UTSArray<String> by `$props`
    open var description: String by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyResumeOnlineComponentsPortfolio) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyResumeOnlineComponentsPortfolio
            val _cache = __ins.renderCache
            val props = __props
            val resumeStore = useResumeStore()
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val showPopup = ref(false)
            val saving = ref(false)
            val state = reactive(PortfolioState(tempImages = _uA<String>(), tempDescription = ""))
            val maxImageCount: Number = 5
            val maxDisplay: Number = 3
            val displayImages = computed(fun(): UTSArray<String> {
                return props.images.slice(0, maxDisplay)
            }
            )
            val remainingCount = computed(fun(): Number {
                return props.images.length - maxDisplay
            }
            )
            val hasMore = computed(fun(): Boolean {
                return props.images.length > maxDisplay
            }
            )
            val handleEdit = fun(){
                state.tempImages = props.images.slice()
                state.tempDescription = props.description ?: ""
                showPopup.value = true
            }
            watch(showPopup, fun(kVal: Boolean){
                emit("popup-change", kVal)
            }
            )
            val handleChooseImage = fun(){
                if (state.tempImages.length >= maxImageCount) {
                    uni_showToast(ShowToastOptions(title = "最多上传" + maxImageCount + "张图片", icon = "none"))
                    return
                }
                uni_chooseImage(ChooseImageOptions(count = maxImageCount - state.tempImages.length, sizeType = _uA(
                    "compressed"
                ), sourceType = _uA(
                    "album",
                    "camera"
                ), success = fun(res){
                    state.tempImages = state.tempImages.concat(res.tempFilePaths)
                }
                ))
            }
            val handleRemoveImage = fun(index: Number){
                state.tempImages.splice(index, 1)
            }
            val handlePreviewImage = fun(index: Number){
                uni_previewImage(PreviewImageOptions(current = index, urls = state.tempImages))
            }
            val handlePreviewDisplayImage = fun(index: Number){
                uni_previewImage(PreviewImageOptions(current = index, urls = props.images))
            }
            val handleSave = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        console.log("Portfolio handleSave triggered")
                        if (saving.value) {
                            return@w1
                        }
                        uni_showToast(ShowToastOptions(title = "正在保存...", icon = "loading", duration = 10000))
                        saving.value = true
                        try {
                            val base64Images: UTSArray<String> = _uA()
                            for(path in resolveUTSValueIterator(state.tempImages)){
                                if (path.startsWith("data:image")) {
                                    base64Images.push(path)
                                } else {
                                    val base64 = await(pathToBase64(path, null))
                                    base64Images.push(base64)
                                }
                            }
                            resumeStore.updatePortfolio(base64Images, state.tempDescription)
                            val success = await(resumeStore.saveBasicInfo())
                            if (success) {
                                uni_showToast(ShowToastOptions(title = "保存成功", icon = "success"))
                                showPopup.value = false
                            }
                        }
                         catch (err: Throwable) {
                            console.error("保存作品失败:", err)
                            uni_showToast(ShowToastOptions(title = "保存失败", icon = "none"))
                        }
                         finally {
                            saving.value = false
                        }
                })
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "bg-white rounded-lg p-6"), _uA(
                    _cE("view", _uM("class" to "flex flex-row items-center justify-between mb-4"), _uA(
                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                            _cE("view", _uM("class" to "w-1 h-4 bg-teal-600 rounded-full")),
                            _cE("text", _uM("class" to "text-base font-bold text-gray-800"), "图片作品")
                        )),
                        _cE("view", _uM("class" to "flex items-center justify-center p-1 active:opacity-60", "onClick" to handleEdit), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-bianji", "size" to 40, "color" to "#999"))
                        ))
                    )),
                    if (isTrue(_ctx.description)) {
                        _cE("text", _uM("key" to 0, "class" to "text-sm text-gray-500 mb-4"), _tD(_ctx.description), 1)
                    } else {
                        _cE("view", _uM("key" to 1, "class" to "py-4 mb-2"), _uA(
                            _cE("text", _uM("class" to "text-xs text-gray-400 italic"), "介绍一下你的作品...")
                        ))
                    }
                    ,
                    if (_ctx.images.length > 0) {
                        _cE("view", _uM("key" to 2, "class" to "flex flex-row gap-4"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(displayImages.value, fun(img, index, __index, _cached): Any {
                                return _cE("view", _uM("key" to index, "class" to "relative flex-1 h-60 rounded-lg overflow-hidden", "onClick" to fun(){
                                    handlePreviewDisplayImage(index)
                                }), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to img, "width" to "100%", "height" to "100%", "mode" to "aspectFit", "error-text" to ""), null, 8, _uA(
                                        "src"
                                    )),
                                    if (isTrue(index === maxDisplay - 1 && hasMore.value)) {
                                        _cE("view", _uM("key" to 0, "class" to "more-overlay"), _uA(
                                            _cE("text", _uM("class" to "more-text"), "+" + _tD(remainingCount.value), 1)
                                        ))
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ), 8, _uA(
                                    "onClick"
                                ))
                            }), 128)
                        ))
                    } else {
                        _cE("view", _uM("key" to 3, "class" to "py-10 flex flex-col items-center justify-center bg-gray-50 rounded-lg border border-dashed border-gray-200"), _uA(
                            _cE("text", _uM("class" to "text-xs text-gray-400"), "目前还没有添加任何图片作品")
                        ))
                    }
                    ,
                    _cV(unref(GenUniModulesTangUiXComponentsTPopupIndexClass), _uM("modelValue" to showPopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showPopup.value = `$event`
                    }
                    , "mode" to "bottom", "title" to "编辑图片作品", "round" to "", "height" to "85%"), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            _cE("view", _uM("class" to "flex flex-col h-full bg-slate-50"), _uA(
                                _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to ""), _uA(
                                    _cE("view", _uM("class" to "p-4 flex flex-col gap-6"), _uA(
                                        _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                            _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                                _cE("text", _uM("class" to "text-sm font-bold text-gray-700"), "作品图片 (" + _tD(state.tempImages.length) + "/" + _tD(maxImageCount) + ")", 1),
                                                _cE("text", _uM("class" to "text-xs text-gray-400"), "建议上传清晰的作品展示图")
                                            )),
                                            _cE("view", _uM("class" to "flex flex-row flex-wrap gap-4"), _uA(
                                                _cE(Fragment, null, RenderHelpers.renderList(state.tempImages, fun(image, index, __index, _cached): Any {
                                                    return _cE("view", _uM("key" to index, "class" to "relative w-40 h-40"), _uA(
                                                        _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to image, "class" to "w-full h-full rounded-lg", "mode" to "aspectFill", "onClick" to fun(){
                                                            handlePreviewImage(index)
                                                        }
                                                        ), null, 8, _uA(
                                                            "src",
                                                            "onClick"
                                                        )),
                                                        _cE("view", _uM("class" to "absolute -top-2 -right-2 w-10 h-10 bg-black/50 rounded-full flex items-center justify-center", "onClick" to fun(){
                                                            handleRemoveImage(index)
                                                        }
                                                        ), _uA(
                                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-plus", "size" to 24, "color" to "#ffffff", "custom-style" to "transform: rotate(45deg)"))
                                                        ), 8, _uA(
                                                            "onClick"
                                                        ))
                                                    ))
                                                }
                                                ), 128),
                                                if (state.tempImages.length < maxImageCount) {
                                                    _cE("view", _uM("key" to 0, "class" to "flex justify-center items-center w-40 h-40 bg-white rounded-lg border border-solid border-gray-200 active:bg-gray-50", "onClick" to handleChooseImage), _uA(
                                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-plus", "size" to 48, "color" to "#9ca3af"))
                                                    ))
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                            ))
                                        )),
                                        _cE("view", _uM("class" to "flex flex-col gap-4"), _uA(
                                            _cE("text", _uM("class" to "text-sm font-bold text-gray-700"), "作品描述"),
                                            _cE("view", _uM("class" to "bg-white rounded-lg p-2 border border-solid border-gray-100"), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("modelValue" to state.tempDescription, "onUpdate:modelValue" to fun(`$event`: String){
                                                    state.tempDescription = `$event`
                                                }
                                                , "type" to "textarea", "placeholder" to "简单介绍一下你的作品，让老板更了解你...", "maxlength" to 500, "height" to 300, "show-count" to "", "focus-border-color" to "#0f766e"), null, 8, _uA(
                                                    "modelValue",
                                                    "onUpdate:modelValue"
                                                ))
                                            ))
                                        ))
                                    ))
                                )),
                                _cE("view", _uM("class" to "p-6 pb-10 bg-white border-t border-slate-100"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "primary", "text" to "保存作品", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "loading" to saving.value, "onClick" to handleSave), null, 8, _uA(
                                        "loading"
                                    ))
                                ))
                            ))
                        )
                    }
                    ), "_" to 1), 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
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
                return _uM("more-overlay" to _pS(_uM("position" to "absolute", "top" to 0, "left" to 0, "right" to 0, "bottom" to 0, "backgroundColor" to "rgba(0,0,0,0.5)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "more-text" to _pS(_uM("fontSize" to "40rpx", "fontWeight" to "bold", "color" to "#ffffff")), "t-popup__container" to _pS(_uM("backgroundColor" to "#f8fafc")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("popup-change" to null)
        var props = _nP(_uM("images" to _uM("type" to "Array", "required" to true), "description" to _uM("type" to "String", "required" to false, "default" to "")))
        var propsNeedCastKeys = _uA(
            "description"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
