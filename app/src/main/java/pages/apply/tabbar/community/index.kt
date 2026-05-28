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
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyTabbarCommunityIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarCommunityIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarCommunityIndex
            val _cache = __ins.renderCache
            val keyword = ref("")
            val activeTab = ref<TabKey>("hot")
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val postList = ref(_uA<FindBaseResponse>())
            val params = reactive(GetFindListParams(Page = 1 as Number, PageSize = 10 as Number, IsFollow = 0 as Number, Keywords = "" as String, myType = 2 as Number, IsHot = 0 as Number))
            val shareHelper = useCommunityShare(fun(findId: Number){
                val index = postList.value.findIndex(fun(p: FindBaseResponse): Boolean {
                    return p.Id === findId
                }
                )
                if (index !== -1) {
                    postList.value[index].ShareNum = postList.value[index].ShareNum + 1
                }
            }
            )
            val showSharePopup = computed<Boolean>(_uO("get" to fun(): Boolean {
                return shareHelper.showSharePopup.value
            }
            , "set" to fun(kVal: Boolean){
                shareHelper.showSharePopup.value = kVal
            }
            ))
            fun gen_fetchList_fn(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                params.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getFindList(params))
                            if (res != null) {
                                val resData = (res as UTSJSONObject)["data"] as UTSArray<FindBaseResponse>
                                val resTotal = (res as UTSJSONObject)["total"] as Number
                                if (isRefresh) {
                                    postList.value = resData
                                } else {
                                    postList.value = postList.value.concat(resData)
                                }
                                hasMore.value = postList.value.length < resTotal
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取动态列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取数据失败", icon = "none"))
                        }
                })
            }
            val fetchList = ::gen_fetchList_fn
            val handleSearch = fun(searchKeyword: String){
                params.Keywords = searchKeyword
                fetchList(true)
            }
            val handleTabChange = fun(tab: TabKey){
                params.IsFollow = if (tab === "follow") {
                    1
                } else {
                    0
                }
                fetchList(true)
            }
            val handleLike = fun(post: FindBaseResponse): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        val index = postList.value.findIndex(fun(p: FindBaseResponse): Boolean {
                            return p.Id === post.Id
                        }
                        )
                        if (index === -1) {
                            return@w1
                        }
                        try {
                            await(userLike(UserLikeParams(FindId = post.Id, Type = 0)))
                            postList.value[index].IsLike = if (postList.value[index].IsLike === 1) {
                                0
                            } else {
                                1
                            }
                            postList.value[index].LikeNum = postList.value[index].LikeNum + (if (postList.value[index].IsLike === 1) {
                                1
                            } else {
                                -1
                            }
                            )
                        }
                         catch (_err: Throwable) {
                            uni_showToast(ShowToastOptions(title = "操作失败", icon = "none"))
                        }
                })
            }
            val handleComment = fun(post: FindBaseResponse){
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/community/detail?id=" + post.Id + "&focus=comment"))
            }
            val handleShare = fun(post: FindBaseResponse){
                shareHelper.openSharePopup(post)
            }
            val handleFollow = fun(post: FindBaseResponse): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        val index = postList.value.findIndex(fun(p: FindBaseResponse): Boolean {
                            return p.Id === post.Id
                        }
                        )
                        if (index === -1) {
                            return@w1
                        }
                        try {
                            await(userFollow(UserFollowParams(ToMemberId = post.MemberId)))
                            postList.value[index].IsFollow = if (postList.value[index].IsFollow === 1) {
                                0
                            } else {
                                1
                            }
                            uni_showToast(ShowToastOptions(title = if (postList.value[index].IsFollow === 1) {
                                "关注成功"
                            } else {
                                "已取消关注"
                            }
                            , icon = "none"))
                        }
                         catch (_err: Throwable) {
                            uni_showToast(ShowToastOptions(title = "操作失败", icon = "none"))
                        }
                })
            }
            val handleClickPost = fun(post: FindBaseResponse){
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/community/detail?id=" + post.Id))
            }
            val handleClickImage = fun(images: UTSArray<String>, index: Number){
                uni_previewImage(PreviewImageOptions(urls = images, current = index))
            }
            val handlePublish = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/apply/community/publish"))
            }
            val handleRefresh = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        isRefreshing.value = true
                        await(fetchList(true))
                        isRefreshing.value = false
                })
            }
            val handleLoadMore = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoadingMore.value || !hasMore.value) {
                            return@w1
                        }
                        isLoadingMore.value = true
                        params.Page = params.Page + 1
                        await(fetchList(false))
                        isLoadingMore.value = false
                })
            }
            onShow(fun(){
                fetchList(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-full flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenPagesApplyTabbarCommunityComponentsHeaderClass), _uM("keyword" to keyword.value, "onUpdate:keyword" to fun(`$event`: String){
                        keyword.value = `$event`
                    }
                    , "active-tab" to activeTab.value, "onUpdate:activeTab" to fun(`$event`: TabKey){
                        activeTab.value = `$event`
                    }
                    , "onSearch" to handleSearch, "onTabChange" to handleTabChange), null, 8, _uA(
                        "keyword",
                        "onUpdate:keyword",
                        "active-tab",
                        "onUpdate:activeTab"
                    )),
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to "", "show-scrollbar" to false, "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to handleRefresh, "onScrolltolower" to handleLoadMore), _uA(
                        if (isTrue(postList.value.length === 0 && !isRefreshing.value)) {
                            _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "title" to "暂无动态", "description" to "快来发布你的第一条动态吧"))
                        } else {
                            _cE(Fragment, _uM("key" to 1), _uA(
                                _cE("view", _uM("class" to "py-4"), _uA(
                                    _cE(Fragment, null, RenderHelpers.renderList(postList.value, fun(post, __key, __index, _cached): Any {
                                        return _cV(unref(GenComponentsPostCardIndexClass), _uM("key" to post.Id, "post" to post, "onLike" to handleLike, "onComment" to handleComment, "onShare" to handleShare, "onFollow" to handleFollow, "onClick" to handleClickPost, "onClickImage" to handleClickImage), null, 8, _uA(
                                            "post"
                                        ))
                                    }
                                    ), 128)
                                )),
                                _cE("view", _uM("class" to "flex justify-center items-center py-4"), _uA(
                                    if (isTrue(isLoadingMore.value)) {
                                        _cE("text", _uM("key" to 0, "class" to "text-xs text-gray-400"), "加载中...")
                                    } else {
                                        if (isTrue(!hasMore.value && postList.value.length > 0)) {
                                            _cE("text", _uM("key" to 1, "class" to "text-xs text-gray-400"), "没有更多了")
                                        } else {
                                            _cC("v-if", true)
                                        }
                                    }
                                ))
                            ), 64)
                        }
                    ), 40, _uA(
                        "refresher-triggered"
                    )),
                    _cV(unref(GenPagesApplyTabbarCommunityComponentsPublishClass), _uM("onClick" to handlePublish)),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 1)),
                    _cV(unref(GenComponentsSharePopupIndexClass), _uM("modelValue" to showSharePopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showSharePopup.value = `$event`
                    }
                    , "onShareToFriend" to unref(shareHelper).handleShareToFriend, "onShareToMoments" to unref(shareHelper).handleShareToMoments), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "onShareToFriend",
                        "onShareToMoments"
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
