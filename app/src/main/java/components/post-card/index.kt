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
open class GenComponentsPostCardIndex : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var post: FindBaseResponse by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPostCardIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPostCardIndex
            val _cache = __ins.renderCache
            val props = __props
            fun emit(event: String, vararg do_not_transform_spread: Any?) {
                __ins.emit(event, *do_not_transform_spread)
            }
            val handleLike = fun(){
                emit("like", props.post)
            }
            val handleComment = fun(){
                emit("comment", props.post)
            }
            val handleShare = fun(){
                emit("share", props.post)
            }
            val handleFollow = fun(){
                emit("follow", props.post)
            }
            val handleClick = fun(){
                emit("click", props.post)
            }
            val handleClickImage = fun(index: Number){
                emit("clickImage", props.post.ImgList, index)
            }
            val formatCount = fun(count: Number): String {
                if (count >= 10000) {
                    return (count / 10000).toFixed(1) + "w"
                }
                if (count >= 1000) {
                    return (count / 1000).toFixed(1) + "k"
                }
                return count.toString(10)
            }
            return fun(): Any? {
                return _cV(unref(GenUniModulesTangUiXComponentsTCardIndexClass), _uM("class" to "post-card bg-white", "onClick" to handleClick), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return _uA(
                        _cE("view", _uM("class" to "flex flex-col gap-2"), _uA(
                            _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                _cE("view", _uM("class" to "relative"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to _ctx.post.Avatar), null, 8, _uA(
                                        "src"
                                    )),
                                    _cE("view", _uM("class" to "absolute bottom-0 right-0 rounded-full"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-rencai", "size" to 24, "color" to "#3b82f6"))
                                    ))
                                )),
                                _cE("view", _uM("class" to "flex-1 flex flex-col gap-1"), _uA(
                                    _cE("text", _uM("class" to "text-base font-medium text-gray-800"), _tD(_ctx.post.NickName), 1),
                                    _cE("view", _uM("class" to "flex flex-row items-center gap-1"), _uA(
                                        _cE("text", _uM("class" to "text-xs text-gray-500"), _tD(_ctx.post.Age) + "岁", 1),
                                        _cE("text", _uM("class" to "text-xs text-gray-400"), "|"),
                                        _cE("text", _uM("class" to "text-xs text-gray-500"), _tD(_ctx.post.InCity), 1)
                                    ))
                                )),
                                if (_ctx.post.IsMy !== 1) {
                                    _cE("view", _uM("key" to 0, "class" to _nC(_uA(
                                        "follow-btn flex flex-row items-center gap-2 px-2 py-1 rounded-md border border-solid",
                                        if (_ctx.post.IsFollow === 1) {
                                            "border-gray-300 bg-white"
                                        } else {
                                            "border-green-500 bg-white"
                                        }
                                    )), "onClick" to withModifiers(handleFollow, _uA(
                                        "stop"
                                    ))), _uA(
                                        if (_ctx.post.IsFollow === 0) {
                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("key" to 0, "name" to "icon-jiahao", "size" to 24, "color" to "#10b981"))
                                        } else {
                                            _cC("v-if", true)
                                        },
                                        _cE("text", _uM("class" to _nC(_uA(
                                            "text-sm",
                                            if (_ctx.post.IsFollow === 1) {
                                                "text-gray-500"
                                            } else {
                                                "text-green-500"
                                            }
                                        ))), _tD(if (_ctx.post.IsFollow === 1) {
                                            "已关注"
                                        } else {
                                            "关注"
                                        }), 3)
                                    ), 2)
                                } else {
                                    _cC("v-if", true)
                                }
                            )),
                            _cE("view", _uM("class" to "px-2 py-2"), _uA(
                                _cE("text", _uM("class" to "text-base text-gray-700 leading-relaxed"), _tD(_ctx.post.ContentDetails), 1)
                            )),
                            if (_ctx.post.ImgList.length > 0) {
                                _cE("view", _uM("key" to 0), _uA(
                                    _cE("view", _uM("class" to _nC(_uA(
                                        "flex flex-row flex-wrap gap-1",
                                        if (_ctx.post.ImgList.length === 1) {
                                            "single-image"
                                        } else {
                                            ""
                                        }
                                    ))), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(_ctx.post.ImgList.slice(0, 9), fun(image, index, __index, _cached): Any {
                                            return _cE("view", _uM("key" to index, "class" to _nC(_uA(
                                                "image-item rounded-lg overflow-hidden",
                                                _uA(
                                                    if (_ctx.post.ImgList.length === 1) {
                                                        "single"
                                                    } else {
                                                        ""
                                                    },
                                                    if (_ctx.post.ImgList.length === 2) {
                                                        "double"
                                                    } else {
                                                        ""
                                                    },
                                                    if (_ctx.post.ImgList.length >= 4) {
                                                        "triple"
                                                    } else {
                                                        ""
                                                    }
                                                )
                                            )), "onClick" to withModifiers(fun(){
                                                handleClickImage(index)
                                            }, _uA(
                                                "stop"
                                            ))), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to image, "width" to "100%", "height" to "100%", "mode" to "aspectFill"), null, 8, _uA(
                                                    "src"
                                                ))
                                            ), 10, _uA(
                                                "onClick"
                                            ))
                                        }), 128)
                                    ), 2)
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                            ,
                            _cE("view", _uM("class" to "flex flex-row items-center border-t border-gray-100"), _uA(
                                _cE("view", _uM("class" to "flex-1 flex flex-row items-center justify-center gap-1", "onClick" to withModifiers(handleLike, _uA(
                                    "stop"
                                ))), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-dianzan", "size" to 36, "color" to if (_ctx.post.IsLike === 1) {
                                        "#10b981"
                                    } else {
                                        "#9ca3af"
                                    }
                                    ), null, 8, _uA(
                                        "color"
                                    )),
                                    _cE("text", _uM("class" to _nC(_uA(
                                        "text-sm",
                                        if (_ctx.post.IsLike === 1) {
                                            "text-green-500"
                                        } else {
                                            "text-gray-400"
                                        }
                                    ))), _tD(formatCount(_ctx.post.LikeNum)), 3)
                                )),
                                _cE("view", _uM("class" to "w-px h-2 bg-gray-200")),
                                _cE("view", _uM("class" to "flex-1 flex flex-row items-center justify-center gap-2", "onClick" to withModifiers(handleComment, _uA(
                                    "stop"
                                ))), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiaoxi", "size" to 36, "color" to "#9ca3af")),
                                    _cE("text", _uM("class" to "text-sm text-gray-400"), _tD(formatCount(_ctx.post.CommtentNum)), 1)
                                )),
                                _cE("view", _uM("class" to "w-px h-4 bg-gray-200")),
                                _cE("view", _uM("class" to "flex-1 flex flex-row items-center justify-center gap-2", "onClick" to withModifiers(handleShare, _uA(
                                    "stop"
                                ))), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-fenxiang", "size" to 36, "color" to "#9ca3af")),
                                    _cE("text", _uM("class" to "text-sm text-gray-400"), _tD(formatCount(_ctx.post.ShareNum)), 1)
                                ))
                            ))
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
                return _uM("post-card" to _pS(_uM("marginBottom" to "20rpx")), "image-item" to _uM("" to _uM("backgroundColor" to "#f3f4f6"), ".single" to _uM("width" to "60%", "height" to 100), ".double" to _uM("height" to 80), ".triple" to _uM("height" to 55)), "follow-btn" to _pS(_uM("transitionProperty" to "all", "transitionDuration" to "0.2s", "transitionTimingFunction" to "ease", "opacity:active" to 0.7)), "@TRANSITION" to _uM("follow-btn" to _uM("property" to "all", "duration" to "0.2s", "timingFunction" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM("like" to null, "comment" to null, "share" to null, "follow" to null, "click" to null, "clickImage" to null)
        var props = _nP(_uM("post" to _uM("type" to "Object", "required" to true)))
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
