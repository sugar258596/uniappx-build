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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesApplyCommunityPublish : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyCommunityPublish) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyCommunityPublish
            val _cache = __ins.renderCache
            val isLoaded = ref(false)
            val commentList = ref(_uA<GetCommentListResponse>())
            val commentInputRef = ref<Any?>(null)
            val commentContent = ref("")
            val hasCommentContent = computed(fun(): Boolean {
                return commentContent.value.trim() != ""
            }
            )
            val commentInputFocused = ref(false)
            val focusCommentInput = fun(): Unit {
                commentInputFocused.value = true
            }
            val commentCount = ref(0)
            val findId = ref(0)
            val isRefreshing = ref(false)
            val isLoadingMore = ref(false)
            val hasMore = ref(true)
            val postDetail = reactive<GetFindXqResponse>(FindBaseResponse(Id = 0, ContentDetails = "", Avatar = "", LikeNum = 0, CommtentNum = 0, NickName = "", IsLike = 0, AddTime = "", ImgList = _uA<String>(), IsFollow = 0, IsMy = 0, Age = "", InCity = "", ShareNum = 0, Status = 0, Reason = "", MemberId = 0 as Number))
            val replyTo = reactive<ReplyTarget__1>(ReplyTarget__1(CommentId = 0 as Number, NickName = ""))
            val commentParams = reactive<GetCommentListParams>(GetCommentListParams(Page = 1 as Number, PageSize = 10 as Number, FindId = 0 as Number))
            val shareHelper = useCommunityShare(fun(_findId: Number){
                postDetail.ShareNum = postDetail.ShareNum + 1
            }
            )
            val showSharePopup = computed<Boolean>(_uO("get" to fun(): Boolean {
                return shareHelper.showSharePopup.value
            }
            , "set" to fun(kVal: Boolean){
                shareHelper.showSharePopup.value = kVal
            }
            ))
            val fetchDetail = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(getFindXq(PublishFindResponse(FindId = findId.value)))
                            if (res != null) {
                                UTSJSONObject.assign(postDetail, res)
                                commentCount.value = res.CommtentNum
                                isLoaded.value = true
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取动态详情失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取详情失败", icon = "none"))
                        }
                })
            }
            fun gen_fetchCommentList_fn(isRefresh: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            if (isRefresh) {
                                commentParams.Page = 1
                                hasMore.value = true
                            }
                            val res = await(getCommentList(commentParams))
                            if (res != null) {
                                val rawData = (res as UTSJSONObject)["data"] as UTSArray<GetCommentListResponse>?
                                val rawTotal = (res as UTSJSONObject)["total"] as Number?
                                val resData = if (rawData != null) {
                                    rawData
                                } else {
                                    _uA<GetCommentListResponse>()
                                }
                                val resTotal = if (rawTotal != null) {
                                    rawTotal
                                } else {
                                    0
                                }
                                if (isRefresh) {
                                    commentList.value = resData
                                } else {
                                    commentList.value = commentList.value.concat(resData)
                                }
                                commentCount.value = resTotal
                                hasMore.value = commentList.value.length < resTotal
                            }
                        }
                         catch (err: Throwable) {
                            console.error("获取评论列表失败:", err)
                            uni_showToast(ShowToastOptions(title = "获取评论失败", icon = "none"))
                        }
                })
            }
            val fetchCommentList = ::gen_fetchCommentList_fn
            val handleRefresh = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        isRefreshing.value = true
                        await(fetchDetail())
                        await(fetchCommentList(true))
                        isRefreshing.value = false
                })
            }
            val handleLoadMore = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isLoadingMore.value || !hasMore.value) {
                            return@w1
                        }
                        isLoadingMore.value = true
                        commentParams.Page = commentParams.Page + 1
                        await(fetchCommentList(false))
                        isLoadingMore.value = false
                })
            }
            val handleFollow = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        if (isLoaded.value) {
                            try {
                                await(userFollow(UserFollowParams(ToMemberId = postDetail.MemberId)))
                                postDetail.IsFollow = if (postDetail.IsFollow === 1) {
                                    0
                                } else {
                                    1
                                }
                                uni_showToast(ShowToastOptions(title = if (postDetail.IsFollow === 1) {
                                    "关注成功"
                                } else {
                                    "已取消关注"
                                }
                                , icon = "none"))
                            }
                             catch (e: Throwable) {
                                uni_showToast(ShowToastOptions(title = "操作失败", icon = "none"))
                            }
                        }
                })
            }
            val confirmDeletePost = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(deleteFind(DeleteFindParams(IdArr = postDetail.Id.toString(10), IsClear = 0)))
                            uni_showToast(ShowToastOptions(title = "删除成功", icon = "none"))
                            setTimeout(fun(){
                                uni_navigateBack(null)
                            }
                            , 1500)
                        }
                         catch (e: Throwable) {
                            uni_showToast(ShowToastOptions(title = "删除失败", icon = "none"))
                        }
                })
            }
            val handleDeletePost = fun(){
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要删除这条动态吗？", success = fun(res){
                    if (res.confirm) {
                        confirmDeletePost()
                    }
                }
                ))
            }
            val confirmDeleteComment = fun(commentId: Number): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(deletedComment(DeleteCommentParams(CommentId = commentId)))
                            uni_showToast(ShowToastOptions(title = "删除成功", icon = "none"))
                            fetchCommentList(true)
                        }
                         catch (e: Throwable) {
                            uni_showToast(ShowToastOptions(title = "删除失败", icon = "none"))
                        }
                })
            }
            val handleDeleteComment = fun(comment: GetCommentListResponse){
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要删除这条评论吗？", success = fun(res){
                    if (res.confirm) {
                        confirmDeleteComment(comment.Id)
                    }
                }
                ))
            }
            val handleDeleteReply = fun(reply: CommentReply){
                uni_showModal(ShowModalOptions(title = "提示", content = "确定要删除这条回复吗？", success = fun(res){
                    if (res.confirm) {
                        confirmDeleteComment(reply.Id)
                    }
                }
                ))
            }
            val handleClickImage = fun(index: Number){
                if (isLoaded.value) {
                    uni_previewImage(PreviewImageOptions(urls = postDetail.ImgList, current = index))
                }
            }
            val handleLike = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        if (isLoaded.value) {
                            try {
                                await(userLike(UserLikeParams(FindId = findId.value, Type = 0)))
                                postDetail.IsLike = if (postDetail.IsLike === 1) {
                                    0
                                } else {
                                    1
                                }
                                postDetail.LikeNum = postDetail.LikeNum + (if (postDetail.IsLike === 1) {
                                    1
                                } else {
                                    -1
                                }
                                )
                            }
                             catch (e: Throwable) {
                                uni_showToast(ShowToastOptions(title = "操作失败", icon = "none"))
                            }
                        }
                })
            }
            val handleCancelReply = fun(){
                replyTo.CommentId = 0
                replyTo.NickName = ""
            }
            val handleSendComment = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        val trimmedComment = commentContent.value.trim()
                        if (trimmedComment == "") {
                            uni_showToast(ShowToastOptions(title = "请输入评论内容", icon = "none"))
                            return@w1
                        }
                        try {
                            val commentPayload = UserCommentParams(FindId = findId.value, Comment = trimmedComment)
                            if (replyTo.CommentId > 0) {
                                commentPayload.ParentCommentId = replyTo.CommentId
                            }
                            await(userComment(commentPayload))
                            uni_showToast(ShowToastOptions(title = "评论成功", icon = "none"))
                            commentContent.value = ""
                            handleCancelReply()
                            fetchCommentList(true)
                        }
                         catch (e: Throwable) {
                            uni_showToast(ShowToastOptions(title = "评论失败", icon = "none"))
                        }
                })
            }
            val handleCommentIconClick = fun(){
                handleCancelReply()
                focusCommentInput()
            }
            val handleShare = fun(){
                if (!isLoaded.value) {
                    return
                }
                shareHelper.openSharePopup(postDetail)
            }
            val handleReply = fun(comment: GetCommentListResponse){
                replyTo.CommentId = comment.Id
                replyTo.NickName = comment.MemberName
                focusCommentInput()
            }
            val handleReplyToReply = fun(comment: GetCommentListResponse, reply: CommentReply){
                replyTo.CommentId = comment.Id
                replyTo.NickName = reply.MemberName
                focusCommentInput()
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
            onLoad(fun(options: Any){
                val pageOptions = if (options != null) {
                    (options as UTSJSONObject)
                } else {
                    null
                }
                val postIdText = if (pageOptions != null) {
                    ((pageOptions["id"] as String?) ?: "0")
                } else {
                    "0"
                }
                val parsedPostId = parseInt(postIdText)
                findId.value = if (isNaN(parsedPostId)) {
                    0
                } else {
                    parsedPostId
                }
                commentParams.FindId = findId.value
                fetchDetail()
                fetchCommentList(true)
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "detail-page h-full flex flex-col bg-gray-50"), _uA(
                    _cV(unref(GenComponentsNavbarIndexClass), _uM("title" to "详情"), _uM("right" to withSlotCtx(fun(): UTSArray<Any> {
                        return _uA(
                            if (postDetail.IsMy === 1) {
                                _cE("view", _uM("key" to 0, "class" to "more-btn", "onClick" to handleDeletePost), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-lajitong", "size" to 40, "color" to "#ef4444"))
                                ))
                            } else {
                                _cC("v-if", true)
                            }
                        )
                    }
                    ), "_" to 1)),
                    if (isTrue(isLoaded.value)) {
                        _cE("scroll-view", _uM("key" to 0, "class" to "flex-1", "scroll-y" to "", "show-scrollbar" to false, "refresher-enabled" to "", "refresher-triggered" to isRefreshing.value, "onRefresherrefresh" to handleRefresh, "onScrolltolower" to handleLoadMore), _uA(
                            _cE("view", _uM("class" to "bg-white px-4 py-4"), _uA(
                                _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                    _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to postDetail.Avatar), null, 8, _uA(
                                        "src"
                                    )),
                                    _cE("view", _uM("class" to "flex-1 flex flex-col gap-2"), _uA(
                                        _cE("text", _uM("class" to "text-base font-medium text-gray-800"), _tD(postDetail.NickName), 1),
                                        _cE("view", _uM("class" to "flex flex-row items-center gap-2"), _uA(
                                            _cE("text", _uM("class" to "text-xs text-gray-500"), _tD(postDetail.Age) + "岁", 1),
                                            _cE("text", _uM("class" to "text-xs text-gray-400"), "|"),
                                            _cE("text", _uM("class" to "text-xs text-gray-500"), _tD(postDetail.InCity), 1)
                                        ))
                                    )),
                                    if (postDetail.IsMy !== 1) {
                                        _cE("view", _uM("key" to 0, "class" to _nC(_uA(
                                            "follow-btn px-4 py-2 rounded-md",
                                            if (postDetail.IsFollow === 1) {
                                                "bg-gray-50"
                                            } else {
                                                "bg-white border border-solid border-green-500"
                                            }
                                        )), "onClick" to handleFollow), _uA(
                                            _cE("text", _uM("class" to _nC(_uA(
                                                "text-sm",
                                                if (postDetail.IsFollow === 1) {
                                                    "text-gray-500"
                                                } else {
                                                    "text-green-500"
                                                }
                                            ))), _tD(if (postDetail.IsFollow === 1) {
                                                "已关注"
                                            } else {
                                                "关注"
                                            }), 3)
                                        ), 2)
                                    } else {
                                        _cC("v-if", true)
                                    }
                                ))
                            )),
                            _cE("view", _uM("class" to "bg-white px-4 py-4 mt-2"), _uA(
                                _cE("text", _uM("class" to "text-base text-gray-700 leading-relaxed"), _tD(postDetail.ContentDetails), 1)
                            )),
                            if (postDetail.ImgList.length > 0) {
                                _cE("view", _uM("key" to 0, "class" to "bg-white px-4 py-2"), _uA(
                                    _cE("view", _uM("class" to "flex flex-row flex-wrap gap-2"), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(postDetail.ImgList, fun(image, index, __index, _cached): Any {
                                            return _cE("view", _uM("key" to index, "class" to "image-item rounded-lg overflow-hidden", "onClick" to fun(){
                                                handleClickImage(index)
                                            }), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTImageIndexClass), _uM("src" to image, "width" to "100%", "height" to "100%", "mode" to "aspectFill"), null, 8, _uA(
                                                    "src"
                                                ))
                                            ), 8, _uA(
                                                "onClick"
                                            ))
                                        }), 128)
                                    ))
                                ))
                            } else {
                                _cC("v-if", true)
                            },
                            _cE("view", _uM("class" to "bg-white px-4 py-4"), _uA(
                                _cE("text", _uM("class" to "text-sm text-gray-400"), _tD(postDetail.AddTime), 1)
                            )),
                            _cE("view", _uM("class" to "comment-count bg-white px-4 py-4 mt-2"), _uA(
                                _cE("text", _uM("class" to "text-base font-medium text-gray-800"), "共" + _tD(commentCount.value) + "条评论", 1)
                            )),
                            _cE("view", _uM("class" to "bg-white"), _uA(
                                if (isTrue(commentList.value.length === 0 && !isRefreshing.value)) {
                                    _cV(unref(GenUniModulesTangUiXComponentsTEmptyIndexClass), _uM("key" to 0, "title" to "暂无评论", "description" to "快来发表你的第一条评论吧"))
                                } else {
                                    _cE(Fragment, _uM("key" to 1), _uA(
                                        _cE(Fragment, null, RenderHelpers.renderList(commentList.value, fun(comment, __key, __index, _cached): Any {
                                            return _cE("view", _uM("key" to comment.Id, "class" to "comment-item flex flex-row gap-4 px-4 py-4 border-b border-gray-100"), _uA(
                                                _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to comment.MemberHead, "size" to 40), null, 8, _uA(
                                                    "src"
                                                )),
                                                _cE("view", _uM("class" to "flex-1 flex flex-col gap-2"), _uA(
                                                    _cE("view", _uM("class" to "flex flex-row items-center justify-between"), _uA(
                                                        _cE("text", _uM("class" to "text-sm font-medium text-gray-800"), _tD(comment.MemberName), 1),
                                                        _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(comment.AddTime), 1)
                                                    )),
                                                    _cE("text", _uM("class" to "text-sm text-gray-600"), _tD(comment.Comment), 1),
                                                    if (comment.MyCommnetList.length > 0) {
                                                        _cE("view", _uM("key" to 0, "class" to "mt-2 ml-1 pl-2"), _uA(
                                                            _cE(Fragment, null, RenderHelpers.renderList(comment.MyCommnetList, fun(reply, replyIndex, __index, _cached): Any {
                                                                return _cE("view", _uM("key" to reply.Id, "class" to _nC(_uA(
                                                                    "flex flex-row items-start gap-2",
                                                                    if (replyIndex > 0) {
                                                                        "mt-3"
                                                                    } else {
                                                                        ""
                                                                    }
                                                                ))), _uA(
                                                                    _cE("view", _uM("class" to "flex flex-col items-center flex-shrink-0"), _uA(
                                                                        _cV(unref(GenUniModulesTangUiXComponentsTAvatarIndexClass), _uM("src" to reply.MemberHead, "size" to 40), null, 8, _uA(
                                                                            "src"
                                                                        ))
                                                                    )),
                                                                    _cE("view", _uM("class" to "gap-2"), _uA(
                                                                        _cE("view", _uM("class" to "flex-wrap"), _uA(
                                                                            _cE("text", _uM("class" to "text-xs font-medium text-gray-500"), "回复"),
                                                                            _cE("text", _uM("class" to "text-sm text-gray-600"), _tD(reply.Comment), 1)
                                                                        )),
                                                                        _cE("view", _uM("class" to "mt-1 flex flex-row items-center gap-2 pl-1"), _uA(
                                                                            _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(reply.AddTime), 1),
                                                                            _cE("text", _uM("class" to "text-xs text-gray-300"), "·"),
                                                                            _cE("text", _uM("class" to "text-xs text-gray-400 active:opacity-70", "onClick" to fun(){
                                                                                handleReplyToReply(comment, reply)
                                                                            }), "回复", 8, _uA(
                                                                                "onClick"
                                                                            )),
                                                                            if (reply.IsMy === 1) {
                                                                                _cE(Fragment, _uM("key" to 0), _uA(
                                                                                    _cE("text", _uM("class" to "text-xs text-gray-300"), "·"),
                                                                                    _cE("text", _uM("class" to "text-xs text-red-400 active:opacity-70", "onClick" to fun(){
                                                                                        handleDeleteReply(reply)
                                                                                    }), "删除", 8, _uA(
                                                                                        "onClick"
                                                                                    ))
                                                                                ), 64)
                                                                            } else {
                                                                                _cC("v-if", true)
                                                                            }
                                                                        ))
                                                                    ))
                                                                ), 2)
                                                            }), 128),
                                                            if (comment.ReplyNum > comment.MyCommnetList.length) {
                                                                _cE("view", _uM("key" to 0, "class" to "mt-4 ml-6 inline-flex items-center gap-2"), _uA(
                                                                    _cE("view", _uM("class" to "h-px w-6 bg-gray-300")),
                                                                    _cE("text", _uM("class" to "text-xs text-gray-500"), "查看全部" + _tD(comment.ReplyNum) + "条回复", 1),
                                                                    _cE("text", _uM("class" to "text-xs text-gray-500"), ">")
                                                                ))
                                                            } else {
                                                                _cC("v-if", true)
                                                            }
                                                        ))
                                                    } else {
                                                        _cC("v-if", true)
                                                    },
                                                    _cE("view", _uM("class" to "flex flex-row items-center gap-4"), _uA(
                                                        _cE("view", _uM("class" to "reply-btn flex-row items-center gap-2", "onClick" to fun(){
                                                            handleReply(comment)
                                                        }), _uA(
                                                            _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiaoxi", "size" to 24, "color" to "#9ca3af")),
                                                            _cE("text", _uM("class" to "text-xs text-gray-400"), "回复")
                                                        ), 8, _uA(
                                                            "onClick"
                                                        )),
                                                        if (comment.IsMy === 1) {
                                                            _cE("view", _uM("key" to 0, "class" to "reply-btn flex-row items-center gap-2", "onClick" to fun(){
                                                                handleDeleteComment(comment)
                                                            }), _uA(
                                                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-lajitong", "size" to 30, "color" to "#ec4899")),
                                                                _cE("text", _uM("class" to "text-xs text-red-400"), "删除")
                                                            ), 8, _uA(
                                                                "onClick"
                                                            ))
                                                        } else {
                                                            _cC("v-if", true)
                                                        }
                                                    ))
                                                ))
                                            ))
                                        }), 128),
                                        _cE("view", _uM("class" to "flex justify-center items-center py-4"), _uA(
                                            if (isTrue(isLoadingMore.value)) {
                                                _cE("text", _uM("key" to 0, "class" to "text-xs text-gray-400"), "加载中...")
                                            } else {
                                                if (isTrue(!hasMore.value && commentList.value.length > 0)) {
                                                    _cE("text", _uM("key" to 1, "class" to "text-xs text-gray-400"), "没有更多评论了")
                                                } else {
                                                    _cC("v-if", true)
                                                }
                                            }
                                        ))
                                    ), 64)
                                }
                            ))
                        ), 40, _uA(
                            "refresher-triggered"
                        ))
                    } else {
                        _cC("v-if", true)
                    }
                    ,
                    _cE("view", _uM("class" to "bottom-bar bg-white border-t border-gray-100"), _uA(
                        if (replyTo.CommentId > 0) {
                            _cE("view", _uM("key" to 0, "class" to "flex flex-row items-center justify-between px-4 py-2 bg-gray-50 border-b border-gray-100"), _uA(
                                _cE("text", _uM("class" to "text-xs text-gray-500"), "回复 @" + _tD(replyTo.NickName), 1),
                                _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-lajitong", "size" to 32, "color" to "#9ca3af", "onClick" to handleCancelReply))
                            ))
                        } else {
                            _cC("v-if", true)
                        }
                        ,
                        _cE("view", _uM("class" to "flex flex-row items-center gap-4 px-4 py-4"), _uA(
                            _cE("view", _uM("class" to "flex-1 input-wrapper"), _uA(
                                _cV(unref(GenUniModulesTangUiXComponentsTInputIndexClass), _uM("ref_key" to "commentInputRef", "ref" to commentInputRef, "modelValue" to commentContent.value, "onUpdate:modelValue" to fun(`$event`: String){
                                    commentContent.value = `$event`
                                }
                                , "placeholder" to "说点什么...", "border" to false, "class" to "comment-input", "confirm-type" to "send", "focus-border-color" to "#0f766e", "auto-focus" to commentInputFocused.value, "onConfirm" to handleSendComment, "onFocus" to fun(){
                                    commentInputFocused.value = true
                                }
                                , "onBlur" to fun(){
                                    commentInputFocused.value = false
                                }
                                ), null, 8, _uA(
                                    "modelValue",
                                    "onUpdate:modelValue",
                                    "auto-focus",
                                    "onFocus",
                                    "onBlur"
                                ))
                            )),
                            if (isTrue(hasCommentContent.value)) {
                                _cE("view", _uM("key" to 0, "class" to "send-btn", "onClick" to handleSendComment), _uA(
                                    _cE("text", _uM("class" to "text-sm text-green-500 font-medium"), "发送")
                                ))
                            } else {
                                _cE("view", _uM("key" to 1, "class" to "flex flex-row items-center gap-4"), _uA(
                                    _cE("view", _uM("class" to "icon-btn", "onClick" to handleLike), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-dianzan", "size" to 48, "color" to if (postDetail.IsLike === 1) {
                                            "#10b981"
                                        } else {
                                            "#9ca3af"
                                        }
                                        ), null, 8, _uA(
                                            "color"
                                        )),
                                        _cE("text", _uM("class" to _nC(_uA(
                                            "text-xs",
                                            if (postDetail.IsLike === 1) {
                                                "text-green-500"
                                            } else {
                                                "text-gray-400"
                                            }
                                        ))), _tD(formatCount(postDetail.LikeNum)), 3)
                                    )),
                                    _cE("view", _uM("class" to "icon-btn", "onClick" to handleCommentIconClick), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-xiaoxi", "size" to 48, "color" to "#9ca3af")),
                                        _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(formatCount(postDetail.CommtentNum)), 1)
                                    )),
                                    _cE("view", _uM("class" to "icon-btn", "onClick" to handleShare), _uA(
                                        _cV(unref(GenUniModulesTangUiXComponentsTIconIndexClass), _uM("name" to "icon-fenxiang", "size" to 48, "color" to "#9ca3af")),
                                        _cE("text", _uM("class" to "text-xs text-gray-400"), _tD(formatCount(postDetail.ShareNum)), 1)
                                    ))
                                ))
                            }
                        ))
                    )),
                    _cV(unref(GenComponentsSharePopupIndexClass), _uM("modelValue" to showSharePopup.value, "onUpdate:modelValue" to fun(`$event`: Boolean){
                        showSharePopup.value = `$event`
                    }
                    , "height" to "40%", "onShareToFriend" to unref(shareHelper).handleShareToFriend, "onShareToMoments" to unref(shareHelper).handleShareToMoments), null, 8, _uA(
                        "modelValue",
                        "onUpdate:modelValue",
                        "onShareToFriend",
                        "onShareToMoments"
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
                return _uM("detail-page" to _pS(_uM("backgroundColor" to "#f9fafb")), "nav-bar" to _pS(_uM("height" to 44, "paddingTop" to CSS_VAR_STATUS_BAR_HEIGHT, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f3f4f6")), "back-btn" to _pS(_uM("width" to 40, "height" to 40, "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "more-btn" to _pS(_uM("width" to 40, "height" to 40, "display" to "flex", "alignItems" to "center", "justifyContent" to "center")), "follow-btn" to _pS(_uM("transitionProperty" to "all", "transitionDuration" to "0.2s", "transitionTimingFunction" to "ease", "opacity:active" to 0.7)), "image-item" to _pS(_uM("height" to "320rpx", "backgroundColor" to "#f3f4f6")), "comment-item" to _pS(_uM("borderBottomWidth:last-child" to "medium", "borderBottomStyle:last-child" to "none", "borderBottomColor:last-child" to "#000000")), "reply-btn" to _pS(_uM("alignSelf" to "flex-start", "paddingTop" to "4rpx", "paddingRight" to 0, "paddingBottom" to "4rpx", "paddingLeft" to 0, "opacity:active" to 0.7)), "bottom-bar" to _pS(_uM("paddingBottom" to "env(safe-area-inset-bottom)", "boxShadow" to "0 -2px 8px rgba(0, 0, 0, 0.05)")), "input-wrapper" to _pS(_uM("backgroundColor" to "#f3f4f6", "borderTopLeftRadius" to 20, "borderTopRightRadius" to 20, "borderBottomRightRadius" to 20, "borderBottomLeftRadius" to 20, "paddingTop" to "8rpx", "paddingRight" to "16rpx", "paddingBottom" to "8rpx", "paddingLeft" to "16rpx")), "comment-input" to _pS(_uM("fontSize" to 14)), "icon-btn" to _pS(_uM("display" to "flex", "flexDirection" to "column", "alignItems" to "center", "gap" to "2rpx", "opacity:active" to 0.7)), "@TRANSITION" to _uM("follow-btn" to _uM("property" to "all", "duration" to "0.2s", "timingFunction" to "ease")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = _uM()
        var emits: Map<String, Any?> = _uM()
        var props = _nP(_uM())
        var propsNeedCastKeys: UTSArray<String> = _uA()
        var components: Map<String, CreateVueComponent> = _uM()
    }
}
