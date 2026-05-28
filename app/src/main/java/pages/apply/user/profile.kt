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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyUserProfile : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyUserProfile) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyUserProfile
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val asText = fun(value: String?): String {
                if (value == null) {
                    return ""
                }
                return value
            }
            val formData = reactive(_uO("Avatar" to asText(authStore.userInfo?.Avatar), "NickName" to asText(authStore.userInfo?.NickName), "Mobile" to asText(authStore.userInfo?.Mobile), "Sex" to (authStore.userInfo?.Sex ?: 0), "Birthday" to asText(authStore.userInfo?.Birthday)))
            val syncFormDataFromUserInfo = fun(){
                formData["Avatar"] = asText(authStore.userInfo?.Avatar)
                formData["NickName"] = asText(authStore.userInfo?.NickName)
                formData["Mobile"] = asText(authStore.userInfo?.Mobile)
                formData["Sex"] = authStore.userInfo?.Sex ?: 0
                formData["Birthday"] = asText(authStore.userInfo?.Birthday)
            }
            val sexText = computed(fun(): String {
                if (formData["Sex"] == 1) {
                    return "男"
                }
                if (formData["Sex"] == 2) {
                    return "女"
                }
                return "未选择"
            }
            )
            val showBirthdayPicker = ref(false)
            val showSexSheet = ref(false)
            val sexActions = _uA<UTSJSONObject>(_uO("name" to "男", "value" to 1), _uO("name" to "女", "value" to 2))
            val handleChooseAvatar = fun(){
                uni_chooseImage(ChooseImageOptions(count = 1, success = fun(res){
                    val filePath = res.tempFilePaths[0] as String
                    formData["Avatar"] = filePath
                    uni_showToast(ShowToastOptions(title = "图片已选择", icon = "none"))
                }
                ))
            }
            val handleEditNickname = fun(){
                uni_showModal(ShowModalOptions(title = "修改昵称", content = formData["NickName"] as String, success = fun(res){
                    if (res.confirm) {
                        val newNickName = (res as UTSJSONObject)["content"] as String
                        if (newNickName != null && newNickName != "") {
                            formData["NickName"] = newNickName
                        }
                    }
                }
                ))
            }
            val onSexSelect = fun(action: Any){
                val sexValue = (action as UTSJSONObject)["value"] as Number
                formData["Sex"] = sexValue
            }
            val onBirthdayConfirm = fun(value: Number, formatted: String){
                formData["Birthday"] = formatted
                showBirthdayPicker.value = false
            }
            val handleSave = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        uni_showLoading(ShowLoadingOptions(title = "保存中..."))
                        try {
                            if (formData["NickName"] != authStore.userInfo?.NickName) {
                                await(editUserNickName(EditUserNickNameParams(UserNick = formData["NickName"] as String)))
                            }
                            val avatarStr = formData["Avatar"] as String
                            if (avatarStr != authStore.userInfo?.Avatar && (avatarStr.startsWith("blob:") || avatarStr.startsWith("file:") || avatarStr.includes("_doc/") || avatarStr.startsWith("http://tmp") || avatarStr.startsWith("wxfile://"))) {
                                val base64 = await(pathToBase64(avatarStr, null))
                                await(editUserAvatar(EditUserAvatarParams(Avatar = base64)))
                            }
                            if (formData["Sex"] != authStore.userInfo?.Sex || formData["Birthday"] != authStore.userInfo?.Birthday) {
                                await(editUserOther(EditUserOtherParams(Sex = formData["Sex"] as Number, Birthday = formData["Birthday"] as String)))
                            }
                            uni_hideLoading(null)
                            await(authStore.fetchUserInfo(true))
                            syncFormDataFromUserInfo()
                            uni_showToast(ShowToastOptions(title = "保存成功", icon = "success"))
                        }
                         catch (err: Throwable) {
                            uni_hideLoading(null)
                            uni_showToast(ShowToastOptions(title = "保存失败", icon = "none"))
                            console.error("保存用户信息失败:", err)
                        }
                })
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-screen flex flex-col bg-white"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "编辑信息")),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to ""), _uA(
                        _cE("view", _uM("class" to "py-2"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTCellIndexClass), _uM("title" to "头像", "onCell" to handleChooseAvatar), _uM("content" to withSlotCtx(fun(): UTSArray<Any> {
                                return _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to formData["Avatar"], "shape" to "square", "size" to "small"), null, 8, _uA(
                                        "src"
                                    ))
                                )
                            }
                            ), "_" to 1)),
                            _cV(unref(GenUniModulesTangUiXComponentsTCellIndexClass), _uM("title" to "昵称", "content" to formData["NickName"], "onCell" to handleEditNickname), null, 8, _uA(
                                "content"
                            )),
                            _cV(unref(GenUniModulesTangUiXComponentsTCellIndexClass), _uM("title" to "手机号", "content" to formData["Mobile"], "show-icon" to false), null, 8, _uA(
                                "content"
                            )),
                            _cV(unref(GenUniModulesTangUiXComponentsTCellIndexClass), _uM("title" to "性别", "content" to sexText.value, "onCell" to fun(){
                                showSexSheet.value = true
                            }
                            ), null, 8, _uA(
                                "content",
                                "onCell"
                            )),
                            _cV(unref(GenUniModulesTangUiXComponentsTCellIndexClass), _uM("title" to "出生年月", "content" to if (formData["Birthday"] != "") {
                                formData["Birthday"]
                            } else {
                                "未选择"
                            }
                            , "onCell" to fun(){
                                showBirthdayPicker.value = true
                            }
                            ), null, 8, _uA(
                                "content",
                                "onCell"
                            ))
                        )),
                        _cE("view", _uM("class" to "mt-20 px-6"), _uA(
                            _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("text" to "保 存", "type" to "success", "size" to "large", "shape" to "round", "block" to "", "custom-class" to "t-default", "onClick" to handleSave))
                        ))
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTActionSheetIndexClass), _uM("modelValue" to showSexSheet.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showSexSheet.value = `$event`
                    }
                    , "title" to "请选择性别", "actions" to sexActions, "onSelect" to onSexSelect), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue"
                    )),
                    _cV(unref(GenUniModulesTangUiXComponentsTDateTimePickerIndexClass), _uM("modelValue" to showBirthdayPicker.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showBirthdayPicker.value = `$event`
                    }
                    , "mode" to "date", "title" to "选择出生年月", "active-color" to "#00897b", "confirm-color" to "#00897b", "onConfirm" to onBirthdayConfirm, "onCancel" to fun(){
                        showBirthdayPicker.value = false
                    }
                    ), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "onCancel"
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
                return _uM("font-bold" to _uM(".py-2 " to _uM("fontWeight" to "500")), "t-action-sheet__title" to _pS(_uM("textAlign" to "center")), "t-icon" to _pS(_uM("display" to "flex", "alignContent" to "center", "alignItems" to "center")), "t-cell" to _pS(_uM("borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f5f5f5")), "avatar-btn" to _pS(_uM("backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0, "marginTop" to 0, "marginRight" to 0, "marginBottom" to 0, "marginLeft" to 0, "lineHeight" to "normal", "borderTopLeftRadius" to 0, "borderTopRightRadius" to 0, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0, "width" to "100%", "borderTopWidth::after" to "medium", "borderRightWidth::after" to "medium", "borderBottomWidth::after" to "medium", "borderLeftWidth::after" to "medium", "borderTopStyle::after" to "none", "borderRightStyle::after" to "none", "borderBottomStyle::after" to "none", "borderLeftStyle::after" to "none", "borderTopColor::after" to "#000000", "borderRightColor::after" to "#000000", "borderBottomColor::after" to "#000000", "borderLeftColor::after" to "#000000")), "cell-row" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to "24rpx", "paddingRight" to "32rpx", "paddingBottom" to "24rpx", "paddingLeft" to "32rpx", "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f5f5f5")), "cell-title" to _pS(_uM("fontSize" to "30rpx", "color" to "#333333")), "cell-right" to _pS(_uM("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "gap" to "8rpx")), "cell-arrow" to _pS(_uM("fontSize" to "36rpx", "color" to "#999999")), "nickname-cell" to _pS(_uM("backgroundColor" to "#ffffff")), "nickname-input" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%", "textAlign" to "right", "fontSize" to "30rpx", "color" to "#333333", "borderTopWidth" to "medium", "borderRightWidth" to "medium", "borderBottomWidth" to "medium", "borderLeftWidth" to "medium", "borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
