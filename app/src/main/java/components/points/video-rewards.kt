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
open class GenComponentsPointsVideoRewards : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsPointsVideoRewards) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsPointsVideoRewards
            val _cache = __ins.renderCache
            val rewards = _uA(
                RewardItem(points = 200, times = 1),
                RewardItem(points = 300, times = 3),
                RewardItem(points = 500, times = 5),
                RewardItem(points = 1000, times = 10),
                RewardItem(points = 1500, times = 15),
                RewardItem(points = 2000, times = 20)
            ) as UTSArray<RewardItem>
            val currentTimes = ref(0)
            val progressPercent = computed(fun(): Number {
                val maxTimes = rewards[rewards.length - 1].times
                return Math.min((currentTimes.value / maxTimes) * 100, 100)
            }
            )
            val isRewardClaimed = fun(reward: RewardItem): Boolean {
                return currentTimes.value >= reward.times
            }
            val handleWatchVideo = fun(){
                uni_showToast(ShowToastOptions(title = "观看视频中...", icon = "none"))
            }
            return fun(): Any? {
                return _cE("view", _uM("class" to "video-rewards"), _uA(
                    _cE("view", _uM("class" to "header"), _uA(
                        _cE("text", _uM("class" to "title"), "看视频赚积分"),
                        _cV(unref(GenUniModulesTangUiXComponentsTButtonIndexClass), _uM("type" to "warning", "size" to "small", "shape" to "round", "onClick" to handleWatchVideo), _uM("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return _uA(
                                " 看视频 "
                            )
                        }
                        ), "_" to 1))
                    )),
                    _cE("view", _uM("class" to "progress-container"), _uA(
                        _cE("view", _uM("class" to "pacman-container"), _uA(
                            _cE("view", _uM("class" to "pacman"))
                        )),
                        _cE("view", _uM("class" to "progress-track"), _uA(
                            _cE("view", _uM("class" to "progress-bar", "style" to _nS(_uM("width" to (progressPercent.value + "%")))), null, 4)
                        )),
                        _cE("view", _uM("class" to "rewards-list"), _uA(
                            _cE(Fragment, null, RenderHelpers.renderList(rewards, fun(reward, index, __index, _cached): Any {
                                return _cE("view", _uM("key" to index, "class" to "reward-item"), _uA(
                                    _cE("view", _uM("class" to _nC(_uA(
                                        "red-envelope",
                                        _uM("claimed" to isRewardClaimed(reward))
                                    ))), _uA(
                                        _cE("text", _uM("class" to "points"), _tD(reward.points), 1)
                                    ), 2),
                                    _cE("text", _uM("class" to "times"), _tD(reward.times) + "次", 1)
                                ))
                            }
                            ), 64)
                        ))
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
                return _uM("video-rewards" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "#ffffff", "borderTopLeftRadius" to "12rpx", "borderTopRightRadius" to "12rpx", "borderBottomRightRadius" to "12rpx", "borderBottomLeftRadius" to "12rpx", "paddingTop" to "24rpx", "paddingRight" to "24rpx", "paddingBottom" to "24rpx", "paddingLeft" to "24rpx")), "header" to _pS(_uM("display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between", "alignItems" to "center", "marginBottom" to "30rpx")), "title" to _pS(_uM("fontSize" to "32rpx", "fontWeight" to "bold", "color" to "#333333")), "progress-container" to _pS(_uM("position" to "relative", "paddingTop" to "20rpx")), "pacman-container" to _pS(_uM("position" to "absolute", "left" to 0, "top" to "50rpx", "zIndex" to 10)), "pacman" to _pS(_uM("width" to "40rpx", "height" to "40rpx", "backgroundImage" to "none", "backgroundColor" to "#ffc107", "borderTopLeftRadius" to "50%", "borderTopRightRadius" to "50%", "borderBottomRightRadius" to "50%", "borderBottomLeftRadius" to "50%", "position" to "relative", "content::before" to "\"\"", "position::before" to "absolute", "right::before" to "2rpx", "top::before" to 0, "width::before" to 0, "height::before" to 0, "borderLeftWidth::before" to "20rpx", "borderLeftStyle::before" to "solid", "borderLeftColor::before" to "#ffffff", "borderTopWidth::before" to "20rpx", "borderTopStyle::before" to "solid", "borderTopColor::before" to "rgba(0,0,0,0)", "borderBottomWidth::before" to "20rpx", "borderBottomStyle::before" to "solid", "borderBottomColor::before" to "rgba(0,0,0,0)")), "progress-track" to _pS(_uM("position" to "absolute", "left" to "50rpx", "right" to "20rpx", "top" to "60rpx", "height" to "8rpx", "backgroundImage" to "none", "backgroundColor" to "#f5f5f5", "borderTopLeftRadius" to "4rpx", "borderTopRightRadius" to "4rpx", "borderBottomRightRadius" to "4rpx", "borderBottomLeftRadius" to "4rpx")), "progress-bar" to _pS(_uM("height" to "100%", "backgroundImage" to "linear-gradient(90deg, #ffc107 0%, #ff9800 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopLeftRadius" to "4rpx", "borderTopRightRadius" to "4rpx", "borderBottomRightRadius" to "4rpx", "borderBottomLeftRadius" to "4rpx", "transitionProperty" to "width", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease")), "rewards-list" to _pS(_uM("display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between", "paddingLeft" to "40rpx", "paddingRight" to "10rpx", "marginTop" to "10rpx")), "reward-item" to _pS(_uM("display" to "flex", "flexDirection" to "column", "alignItems" to "center", "gap" to "16rpx")), "red-envelope" to _uM("" to _uM("width" to "80rpx", "height" to "100rpx", "backgroundImage" to "linear-gradient(180deg, #ff5252 0%, #e53935 50%, #c62828 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopLeftRadius" to "8rpx", "borderTopRightRadius" to "8rpx", "borderBottomRightRadius" to "8rpx", "borderBottomLeftRadius" to "8rpx", "display" to "flex", "justifyContent" to "center", "alignItems" to "center", "position" to "relative", "boxShadow" to "0 4rpx 8rpx rgba(198, 40, 40, 0.3)", "content::before" to "\"\"", "position::before" to "absolute", "top::before" to "30rpx", "left::before" to "50%", "transform::before" to "translateX(-50%)", "width::before" to "24rpx", "height::before" to "24rpx", "backgroundImage::before" to "none", "backgroundColor::before" to "#ffc107", "borderTopLeftRadius::before" to "50%", "borderTopRightRadius::before" to "50%", "borderBottomRightRadius::before" to "50%", "borderBottomLeftRadius::before" to "50%", "content::after" to "\"\"", "position::after" to "absolute", "top::after" to "40rpx", "left::after" to 0, "right::after" to 0, "height::after" to "4rpx", "backgroundImage::after" to "none", "backgroundColor::after" to "#d32f2f"), ".claimed" to _uM("opacity" to 0.6)), "points" to _pS(_uM("fontSize" to "24rpx", "fontWeight" to "bold", "color" to "#ffffff", "marginTop" to "30rpx")), "times" to _pS(_uM("fontSize" to "24rpx", "color" to "#999999")), "@TRANSITION" to _uM("progress-bar" to _uM("property" to "width", "duration" to "0.3s", "timingFunction" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
