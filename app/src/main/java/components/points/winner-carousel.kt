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
open class GenComponentsPointsWinnerCarousel : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var winnerList: UTSArray<GetWinResult> by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsWinnerCarousel) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsWinnerCarousel
            val _cache = __ins.renderCache
            return fun(): Any? {
                return _cE("view", _uM("class" to "winner-container rounded-xl flex flex-row items-center border overflow-visible"), _uA(
                    _cE("view", _uM("class" to "badge flex flex-row items-center px-4 relative"), _uA(
                        _cE("view", _uM("class" to "emoji-box rounded-full flex justify-center items-center shadow-sm"), _uA(
                            _cE("text", _uM("class" to "text-sm"), "😜")
                        )),
                        _cE("text", _uM("class" to "text-white text-xs font-bold whitespace-nowrap ml-6"), "中奖公告")
                    )),
                    _cE("swiper", _uM("class" to "winner-swiper flex-1 px-8", "vertical" to "", "circular" to "", "autoplay" to "", "interval" to 3000, "duration" to 300, "indicator-dots" to false), _uA(
                        _cE(Fragment, null, RenderHelpers.renderList(_ctx.winnerList, fun(winner, index, __index, _cached): Any {
                            return _cE("swiper-item", _uM("key" to index), _uA(
                                _cE("view", _uM("class" to "winner-swiper-item h-full flex flex-row items-center gap-4"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row items-center"), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-ziyuan", "size" to 20, "color" to "#f97316")),
                                        _cE("text", _uM("class" to "text-lg font-bold text-orange-500 ml-2"), "x" + _tD(winner.GetPoints ?: 0), 1)
                                    )),
                                    _cE("view", _uM("class" to "text-xs text-gray-700 flex flex-row justify-center items-center gap-2"), _uA(
                                        " 恭喜 ",
                                        _cE("text", _uM("class" to "w-24 line-clamp-1 text-orange-500 font-bold"), _tD(winner.NickName), 1),
                                        " 获得",
                                        _cE("text", _uM("class" to "text-orange-500 font-bold"), _tD(winner.GetPoints), 1),
                                        " 积分 "
                                    ))
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
                return _uM("winner-container" to _pS(_uM("backgroundColor" to "#fffbf6", "borderTopColor" to "#ffe4cc", "borderRightColor" to "#ffe4cc", "borderBottomColor" to "#ffe4cc", "borderLeftColor" to "#ffe4cc", "height" to "64rpx", "position" to "relative", "marginTop" to "10rpx")), "badge" to _pS(_uM("backgroundImage" to "linear-gradient(90deg, #ff6b00 0%, #ff9d4d 100%)", "backgroundColor" to "rgba(0,0,0,0)", "height" to "100%", "borderTopLeftRadius" to "20rpx", "borderBottomLeftRadius" to "20rpx", "borderTopRightRadius" to "40rpx", "borderBottomRightRadius" to 0)), "emoji-box" to _pS(_uM("position" to "absolute", "left" to "-10rpx", "top" to "-10rpx", "width" to "44rpx", "height" to "44rpx", "backgroundColor" to "#ffffff", "borderTopWidth" to "4rpx", "borderRightWidth" to "4rpx", "borderBottomWidth" to "4rpx", "borderLeftWidth" to "4rpx", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#ff9d4d", "borderRightColor" to "#ff9d4d", "borderBottomColor" to "#ff9d4d", "borderLeftColor" to "#ff9d4d")), "line-1" to _pS(_uM("overflow" to "hidden", "textOverflow" to "ellipsis")), "winner-swiper" to _pS(_uM("height" to "100%")), "winner-swiper-item" to _pS(_uM("height" to "100%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM("winnerList" to _uM("type" to "Array", "default" to fun(): UTSArray<GetWinResult> {
            return _uA<GetWinResult>()
        }
        )))
        var propsNeedCastKeys = _uA(
            "winnerList"
        )
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
