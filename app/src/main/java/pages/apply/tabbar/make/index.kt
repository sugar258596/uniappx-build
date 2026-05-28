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
open class GenPagesApplyTabbarMakeIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {}
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesApplyTabbarMakeIndex) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesApplyTabbarMakeIndex
            val _cache = __ins.renderCache
            val authStore = useAuthStore()
            val appStore = useAppStore()
            val winList = ref(_uA<GetWinResult>())
            val wheelPrizes = ref(_uA<GetLotteryInfoResult>())
            val luckyWheelRef = ref<LuckyWheelInstance?>(null)
            val lotteryTicketRef = ref<LotteryTicketInstance?>(null)
            val couponNumbers = ref(_uA<String>())
            val signInReminder = ref(false)
            val rankList = ref(_uA<RankUser>())
            val currentTaskType = ref<TaskType>(0)
            val taskTabItems = _uA(
                TabItem(key = 0, label = "每日任务"),
                TabItem(key = 1, label = "长期任务")
            ) as UTSArray<TabItem>
            val dailyTasks = ref(_uA<TaskItem>())
            val longTermTasks = ref(_uA<TaskItem>())
            val makeBenefits = _uA(
                BenefitItem(icon = "icon-qiandao", label = "每日签到", desc = "金币领不停"),
                BenefitItem(icon = "icon-choujiang", label = "幸运抽奖", desc = "大奖带回家"),
                BenefitItem(icon = "icon-paixing", label = "积分排行", desc = "荣耀竞技场")
            ) as UTSArray<BenefitItem>
            val totalPoints = computed(fun(): Number {
                return authStore.userInfo?.Score ?: 0
            }
            )
            val signInDays = computed(fun(): Number {
                return authStore.userInfo?.SignInDays ?: 0
            }
            )
            val isLogin = computed(fun(): Boolean {
                return authStore.isLogin
            }
            )
            val signInState = computed(fun(): CurrentWeekSignInState {
                return buildCurrentWeekSignInState(authStore.userInfo?.SignInRecord ?: _uA())
            }
            )
            val isTodaySigned = computed(fun(): Boolean {
                return signInState.value.isTodaySigned
            }
            )
            val signInCalendar = computed(fun(): UTSArray<SignInDay> {
                return signInState.value.calendar
            }
            )
            val currentTasks = computed(fun(): UTSArray<TaskItem> {
                return if (currentTaskType.value === 0) {
                    dailyTasks.value
                } else {
                    longTermTasks.value
                }
            }
            )
            val handleTaskTabChange = fun(key: Any){
                currentTaskType.value = key as TaskType
            }
            val fetchWinList = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(getWin())
                            if (res != null) {
                                winList.value = res
                            }
                        }
                         catch (e: Throwable) {
                            console.error(e)
                        }
                })
            }
            val fetchRanking = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(getIntegralRanking(Pagination(Page = 1, PageSize = 20)))
                            if (res.dic != null && res.dic.length > 0) {
                                rankList.value = res.dic.map(fun(item: Dic): RankUser {
                                    return (RankUser(rank = item.Sort, userId = item.MemberId, userName = item.NickName, avatar = item.Avatar, totalPoints = item.SumScore, monthPoints = item.Score))
                                }
                                )
                            }
                        }
                         catch (e: Throwable) {
                            console.error(e)
                        }
                })
            }
            val fetchTasks = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val dailyRes = await(getTaskList(GetTaskListParams(Page = 1, PageSize = 100, MissionType = 0))) as DataResponse<UTSArray<GetTaskListResult>>
                            if (dailyRes != null && dailyRes.data != null && (dailyRes.data as UTSArray<GetTaskListResult>).length > 0) {
                                val dataArr = dailyRes.data as UTSArray<GetTaskListResult>
                                dailyTasks.value = dataArr.map(fun(item: GetTaskListResult): TaskItem {
                                    return (TaskItem(id = item.Id, type = 0, icon = item.PicUrl, title = item.Name, description = item.Synopsis, points = item.Points, status = if (item.Claim === 2) {
                                        "completed"
                                    } else {
                                        "pending"
                                    }
                                    , progress = "" + item.AlreadyCount + "/" + item.Quantity))
                                }
                                )
                            }
                            val longRes = await(getTaskList(GetTaskListParams(Page = 1, PageSize = 100, MissionType = 1))) as DataResponse<UTSArray<GetTaskListResult>>
                            if (longRes != null && longRes.data != null && (longRes.data as UTSArray<GetTaskListResult>).length > 0) {
                                val longDataArr = longRes.data as UTSArray<GetTaskListResult>
                                longTermTasks.value = longDataArr.map(fun(item: GetTaskListResult): TaskItem {
                                    return (TaskItem(id = item.Id, type = 1, icon = item.PicUrl, title = item.Name, description = item.Synopsis, points = item.Points, status = if (item.Claim === 2) {
                                        "completed"
                                    } else {
                                        "pending"
                                    }
                                    , progress = "" + item.AlreadyCount + "/" + item.Quantity))
                                }
                                )
                            }
                        }
                         catch (e: Throwable) {
                            console.error(e)
                        }
                })
            }
            val fetchLottery = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(getLotteryInfo(Pagination(Page = 1, PageSize = 20)))
                            if (res != null && res.length > 0) {
                                wheelPrizes.value = res.map(fun(item: GetLotteryInfoResult): Any {
                                    return (_uO("id" to item.Id, "name" to item.name as String, "icon" to item.PicUrl, "probability" to ((1 as Number) / res.length)))
                                }
                                ) as UTSArray<GetLotteryInfoResult>
                            }
                        }
                         catch (e: Throwable) {
                            console.error(e)
                        }
                })
            }
            val fetchCoupon = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(getLotteryCoupon())
                            if (res != null) {
                                couponNumbers.value = res
                            }
                        }
                         catch (e: Throwable) {
                            console.error(e)
                        }
                })
            }
            val handlePointsDetail = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/points/detail"))
            }
            val handlePointsMall = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/points/mall"))
            }
            val handleSignIn = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (isTodaySigned.value) {
                            showError("今日已签到")
                            return@w1
                        }
                        try {
                            await(postSign())
                            authStore.fetchUserInfo(null)
                        }
                         catch (e: Throwable) {
                            showError(e)
                        }
                })
            }
            val handleReminderChange = fun(value: Boolean): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            await(postSignCash())
                            signInReminder.value = value
                            showToast(if (value) {
                                "已开启签到提醒"
                            } else {
                                "已关闭签到提醒"
                            }
                            )
                        }
                         catch (e: Throwable) {
                            console.error("设置签到提醒失败:", e)
                        }
                })
            }
            val handleViewMoreRank = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/common/points/ranking"))
            }
            val handleDoTask = fun(task: TaskItem): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w1@{
                        if (task.status === "completed") {
                            showError("任务已完成")
                            return@w1
                        }
                        try {
                            await(postClaimTask(PostClaimTaskParams(MissionId = task.id)))
                            showToast("任务 " + task.title + " 领取成功")
                            fetchTasks()
                        }
                         catch (_e: Throwable) {
                            showError("执行任务: " + task.title)
                        }
                })
            }
            val handleLuckyWheelSpin = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(postLottery())
                            console.log(res)
                            if (res != null && luckyWheelRef.value != null) {
                                luckyWheelRef.value!!.play(res.Id)
                            }
                        }
                         catch (e: Throwable) {
                            showError(e)
                        }
                })
            }
            val handleLotteryDraw = fun(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        try {
                            val res = await(postLotteryCoupon())
                            if (res != null && lotteryTicketRef.value != null) {
                                lotteryTicketRef.value!!.play(res.draw, res.WinPrize === 1)
                            }
                        }
                         catch (e: Throwable) {
                            showError(e)
                        }
                })
            }
            val handleSpinComplete = fun(prize: Prize){
                console.log("抽中奖品:", prize.name)
            }
            val handleToLogin = fun(){
                uni_navigateTo(NavigateToOptions(url = PAGE_PATHS["LOGIN"] as String))
            }
            onShow(fun(){
                if (isLogin.value) {
                    authStore.fetchUserInfo(null)
                    fetchWinList()
                    fetchRanking()
                    fetchLottery()
                    fetchTasks()
                    fetchCoupon()
                }
            }
            )
            return fun(): Any? {
                return _cE("view", _uM("class" to "h-header-tab"), _uA(
                    _cE("scroll-view", _uM("class" to "flex-1", "scroll-y" to ""), _uA(
                        if (isTrue(isLogin.value)) {
                            _cE(Fragment, _uM("key" to 0), _uA(
                                _cV(unref(GenComponentsPointsPointsHeaderClass), _uM("total-points" to totalPoints.value, "show-navbar" to false, "onDetailClick" to handlePointsDetail, "onMallClick" to handlePointsMall), null, 8, _uA(
                                    "total-points"
                                )),
                                _cE("view", _uM("class" to "flex flex-col gap-4 p-4"), _uA(
                                    _cV(unref(GenComponentsPointsSignInCardClass), _uM("sign-in-days" to signInDays.value, "sign-in-reminder" to signInReminder.value, "is-today-signed" to isTodaySigned.value, "sign-in-calendar" to signInCalendar.value, "manual" to true, "onSignIn" to handleSignIn, "onReminderChange" to handleReminderChange), null, 8, _uA(
                                        "sign-in-days",
                                        "sign-in-reminder",
                                        "is-today-signed",
                                        "sign-in-calendar"
                                    )),
                                    _cV(unref(GenComponentsPointsWinnerCarouselClass), _uM("winner-list" to winList.value), null, 8, _uA(
                                        "winner-list"
                                    )),
                                    _cV(unref(GenComponentsPointsRankListClass), _uM("rank-list" to rankList.value, "show-month-points" to true, "onViewMore" to handleViewMoreRank), null, 8, _uA(
                                        "rank-list"
                                    )),
                                    _cV(unref(GenComponentsPointsTaskListClass), _uM("current-task-type" to currentTaskType.value, "task-tab-items" to taskTabItems, "current-tasks" to currentTasks.value, "rules-content" to unref(appStore).taskRules, "onTabChange" to handleTaskTabChange, "onDoTask" to handleDoTask), null, 8, _uA(
                                        "current-task-type",
                                        "current-tasks",
                                        "rules-content"
                                    )),
                                    _cV(unref(GenComponentsPointsVideoRewardsClass)),
                                    _cV(unref(GenComponentsPointsLuckyWheelClass), _uM("ref_key" to "luckyWheelRef", "ref" to luckyWheelRef, "mode" to "server", "prizes" to if (wheelPrizes.value.length > 0) {
                                        wheelPrizes.value
                                    } else {
                                        null
                                    }, "rules-content" to unref(appStore).lotteryRules, "onClickSpin" to handleLuckyWheelSpin, "onSpinComplete" to handleSpinComplete), null, 8, _uA(
                                        "prizes",
                                        "rules-content"
                                    )),
                                    _cV(unref(GenComponentsPointsLotteryTicketClass), _uM("ref_key" to "lotteryTicketRef", "ref" to lotteryTicketRef, "defmodel" to couponNumbers.value, "onUpdate:defmodel" to fun(`$event`: UTSArray<String>){
                                        couponNumbers.value = `$event`
                                    }, "rules-content" to unref(appStore).lotteryTicketRules, "onClickDraw" to handleLotteryDraw), null, 8, _uA(
                                        "defmodel",
                                        "onUpdate:defmodel",
                                        "rules-content"
                                    ))
                                ))
                            ), 64)
                        } else {
                            _cV(unref(GenComponentsAuthGuideIndexClass), _uM("key" to 1, "title" to "开启积分之旅", "subtitle" to "登录后即可享受多种积分获取权益", "benefits" to makeBenefits, "onLogin" to handleToLogin))
                        }
                    )),
                    _cV(unref(GenComponentsCustomTabbarIndexClass), _uM("active-index" to 2))
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
