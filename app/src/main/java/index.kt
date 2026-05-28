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
import io.dcloud.uniapp.extapi.clearStorage as uni_clearStorage
import io.dcloud.uniapp.extapi.connectSocket as uni_connectSocket
import io.dcloud.uniapp.extapi.createSelectorQuery as uni_createSelectorQuery
import io.dcloud.uniapp.extapi.downloadFile as uni_downloadFile
import io.dcloud.uniapp.extapi.env as uni_env
import io.dcloud.uniapp.extapi.exit as uni_exit
import io.dcloud.uniapp.extapi.getFileSystemManager as uni_getFileSystemManager
import io.dcloud.uniapp.extapi.getLocation as uni_getLocation
import io.dcloud.uniapp.extapi.getStorage as uni_getStorage
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.previewImage as uni_previewImage
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch
import io.dcloud.uniapp.extapi.removeStorage as uni_removeStorage
import io.dcloud.uniapp.extapi.request as uni_request
import io.dcloud.uniapp.extapi.saveImageToPhotosAlbum as uni_saveImageToPhotosAlbum
import io.dcloud.uniapp.extapi.setStorage as uni_setStorage
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
import io.dcloud.uniapp.extapi.uploadFile as uni_uploadFile
val runBlock1 = run {
    __uniConfig.getAppStyles = fun(): Map<String, Map<String, Map<String, Any>>> {
        return GenApp.styles
    }
}
val storeCache: UTSJSONObject = _uO()
fun <T> defineStore(id: String, setup: () -> T): () -> T {
    return fun(): T {
        val existing = storeCache[id]
        if (existing != null) {
            return existing as T
        }
        val store = reactive(setup()) as T
        storeCache[id] = store as UTSJSONObject
        return store
    }
}
open class GetLocationResult (
    open var latitude: Number? = null,
    open var longitude: Number? = null,
) : UTSObject()
open class DistrictItem (
    @JsonNotNull
    open var code: String,
    @JsonNotNull
    open var name: String,
    open var selected: Boolean? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return DistrictItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class DistrictItemReactiveObject : DistrictItem, IUTSReactive<DistrictItem> {
    override var __v_raw: DistrictItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: DistrictItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(code = __v_raw.code, name = __v_raw.name, selected = __v_raw.selected) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): DistrictItemReactiveObject {
        return DistrictItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var code: String
        get() {
            return _tRG(__v_raw, "code", __v_raw.code, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("code")) {
                return
            }
            val oldValue = __v_raw.code
            __v_raw.code = value
            _tRS(__v_raw, "code", oldValue, value)
        }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var selected: Boolean?
        get() {
            return _tRG(__v_raw, "selected", __v_raw.selected, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("selected")) {
                return
            }
            val oldValue = __v_raw.selected
            __v_raw.selected = value
            _tRS(__v_raw, "selected", oldValue, value)
        }
}
open class LocationState (
    @JsonNotNull
    open var provinceCode: String,
    @JsonNotNull
    open var provinceName: String,
    @JsonNotNull
    open var cityCode: String,
    @JsonNotNull
    open var cityName: String,
    @JsonNotNull
    open var districts: UTSArray<DistrictItem>,
    @JsonNotNull
    open var latitude: Number,
    @JsonNotNull
    open var longitude: Number,
) : UTSObject()
open class LocationStore {
    open lateinit var provinceCode: String
    open lateinit var provinceName: String
    open lateinit var cityCode: String
    open lateinit var cityName: String
    open lateinit var districts: UTSArray<DistrictItem>
    open lateinit var latitude: Number
    open lateinit var longitude: Number
    open var initialized: Boolean by Delegates.notNull()
    open var loading: Boolean by Delegates.notNull()
    open lateinit var setLocation: (location: LocationState) -> Unit
    open lateinit var clearLocation: () -> Unit
    open lateinit var initCurrentLocation: () -> UTSPromise<Unit>
    constructor(provinceCode: Any, provinceName: Any, cityCode: Any, cityName: Any, districts: Any, latitude: Any, longitude: Any, initialized: Any, loading: Any, setLocation: (location: LocationState) -> Unit, clearLocation: () -> Unit, initCurrentLocation: () -> UTSPromise<Unit>){
        this.provinceCode = provinceCode as String
        this.provinceName = provinceName as String
        this.cityCode = cityCode as String
        this.cityName = cityName as String
        this.districts = districts as UTSArray<DistrictItem>
        this.latitude = latitude as Number
        this.longitude = longitude as Number
        this.initialized = initialized as Boolean
        this.loading = loading as Boolean
        this.setLocation = setLocation
        this.clearLocation = clearLocation
        this.initCurrentLocation = initCurrentLocation
    }
}
val useLocationStore = defineStore<LocationStore>("location", fun(): LocationStore {
    val provinceCode = ref("")
    val provinceName = ref("")
    val cityCode = ref("")
    val cityName = ref("")
    val districts = ref(_uA<DistrictItem>())
    val latitude = ref(0)
    val longitude = ref(0)
    val initialized = ref(false)
    val loading = ref(false)
    val setLocation = fun(location: LocationState){
        provinceCode.value = location.provinceCode
        provinceName.value = location.provinceName
        cityCode.value = location.cityCode
        cityName.value = location.cityName
        districts.value = location.districts
        latitude.value = location.latitude
        longitude.value = location.longitude
    }
    val clearLocation = fun(){
        provinceCode.value = ""
        provinceName.value = ""
        cityCode.value = ""
        cityName.value = ""
        districts.value = _uA()
        latitude.value = 0
        longitude.value = 0
    }
    val initCurrentLocation = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (initialized.value || loading.value) {
                    return@w1
                }
                loading.value = true
                try {
                    val result = await(UTSPromise<GetLocationResult>(fun(resolve, reject){
                        uni_getLocation(GetLocationOptions(type = "gcj02", success = fun(res){
                            resolve(res as GetLocationResult)
                        }
                        , fail = fun(err){
                            reject(err)
                        }
                        ))
                    }
                    ))
                    latitude.value = if (result.latitude != null) {
                        result.latitude!!
                    } else {
                        0
                    }
                    longitude.value = if (result.longitude != null) {
                        result.longitude!!
                    } else {
                        0
                    }
                    initialized.value = true
                }
                 catch (error: UTSError) {
                    console.error("[Location] 获取当前定位失败:", error)
                }
                 finally {
                    loading.value = false
                }
        })
    }
    return LocationStore(provinceCode, provinceName, cityCode, cityName, districts, latitude, longitude, initialized, loading, setLocation, clearLocation, initCurrentLocation)
}
)
enum class Environment__1(override val value: String) : UTSEnumString {
    DEVELOPMENT("development"),
    STAGING("staging"),
    PRODUCTION("production")
}
open class ApiConfig (
    @JsonNotNull
    open var baseUrl: String,
    @JsonNotNull
    open var signalRUrl: String,
    @JsonNotNull
    open var timeout: Number,
) : UTSObject()
val DEVELOPMENT_CONFIG = ApiConfig(baseUrl = "https://miaojieapi.wtvxin.com", signalRUrl = "https://miaojiews.wtvxin.com/chathub", timeout = 30000)
val STAGING_CONFIG = ApiConfig(baseUrl = "https://miaojieapi.wtvxin.com", signalRUrl = "https://miaojiews.wtvxin.com/chathub", timeout = 30000)
val PRODUCTION_CONFIG = ApiConfig(baseUrl = "https://miaojieapi.wtvxin.com", signalRUrl = "https://miaojiews.wtvxin.com/chathub", timeout = 30000)
fun getEnvConfig(env: Environment__1): ApiConfig {
    if (env === Environment__1.DEVELOPMENT) {
        return DEVELOPMENT_CONFIG
    }
    if (env === Environment__1.STAGING) {
        return STAGING_CONFIG
    }
    return PRODUCTION_CONFIG
}
var currentEnv: Environment__1 = Environment__1.PRODUCTION
val runBlock2 = run {
    currentEnv = Environment__1.PRODUCTION
}
fun getBaseUrl(): String {
    return getEnvConfig(currentEnv).baseUrl
}
fun getSignalRUrl(): String {
    return getEnvConfig(currentEnv).signalRUrl
}
enum class ConnectionStatus__1(override val value: String) : UTSEnumString {
    Disconnected("Disconnected"),
    Connecting("Connecting"),
    Connected("Connected"),
    Reconnecting("Reconnecting")
}
interface MessageBase {
    var MemberId: String?
    var ToMemberId: String?
    var MesId: Number?
    var MessageType: Number?
    var HireJobId: Number
    var IsGrade: Number?
    var IsRight: Number?
    var IsInterview: Number?
    var IsInterviewResult: Number?
    var IsSendInterviewResult: Number?
    var IsSendResume: Number?
    var IsSendReject: Number?
}
interface SendMessageRequest : MessageBase {
    var UserId: String?
    var Token: String?
    var InterviewId: Number?
    var SendInterviewId: Number?
    var SendChaMessId: Number?
    var SendChaInterviewId: Number?
    var SendResultStatus: Number?
    var Message: String?
    var fileBytes: String?
    var fileUrl: String?
    var fileExt: String?
    var fileSize: Number?
    var fileName: String?
    var AudioDuration: Number?
    var OnlineFileId: Number?
    var SendMethod: Number?
}
interface ReceiveMessage : MessageBase {
    var AudioDuration: Number?
    var Avatar: String?
    var Content: String?
    var DoNotDisturb: Number?
    var FileExt: String?
    var FileName: String?
    var FileSize: String?
    var InterviewId: Number?
    var InterviewResult: Number?
    var InterviewOperation: Number?
    var ResultOperation: Number?
    var IsMe: Boolean?
    var IsRead: Number?
    var NickName: String?
    var OnlineFileId: Number?
    var OperationInterview: Number?
    var SendInterview: Number?
    var SendInterviewId: Number?
    var SendMesId: Number?
    var ResumeOperation: Number?
    var Time: String?
    var FileUrl: String?
}
interface ReceiveResumeOperationMessage {
    var resumeOperation: Number
    var id: Number?
}
interface ReceiveInterviewOperationMessage {
    var InterviewOperation: Number
    var Id: Number?
}
interface ReceiveResultOperationMessage {
    var resultOperation: Number
    var id: Number?
}
open class Pagination (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PaginationReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PaginationReactiveObject : Pagination, IUTSReactive<Pagination> {
    override var __v_raw: Pagination
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: Pagination, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PaginationReactiveObject {
        return PaginationReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
}
typealias HttpMethod = String
open class HttpInitOptions (
    @JsonNotNull
    open var baseURL: String,
    open var header: UTSJSONObject? = null,
    open var showLoading: Boolean? = null,
    open var autoAuth: Boolean? = null,
    open var timeout: Number? = null,
) : UTSObject()
open class HttpRequestOptions (
    @JsonNotNull
    open var url: String,
    open var data: UTSJSONObject? = null,
    open var header: UTSJSONObject? = null,
    open var method: HttpMethod? = null,
    open var timeout: Number? = null,
    open var showLoading: Boolean? = null,
    open var loadingText: String? = null,
    open var disableAuth: Boolean? = null,
    open var customError: Boolean? = null,
    open var isTotal: Boolean? = null,
) : UTSObject()
open class HttpRequestExtraOptions (
    open var header: UTSJSONObject? = null,
    open var timeout: Number? = null,
    open var showLoading: Boolean? = null,
    open var loadingText: String? = null,
    open var disableAuth: Boolean? = null,
    open var customError: Boolean? = null,
    open var isTotal: Boolean? = null,
) : UTSObject()
open class ResponseData<T> (
    @JsonNotNull
    open var code: Number,
    @JsonNotNull
    open var msg: String,
    @JsonNotNull
    open var data: T,
    @JsonNotNull
    open var count: Number,
) : UTSObject()
open class DataResponse<T> (
    @JsonNotNull
    open var data: Any,
    open var total: Number? = null,
) : UTSObject()
open class HttpInterceptors (
    open var request: ((options: HttpRequestOptions) -> HttpRequestOptions)? = null,
    open var response: ((response: ResponseData<Any>, options: HttpRequestOptions) -> Any)? = null,
    open var error: ((err: Any, options: HttpRequestOptions) -> Unit)? = null,
) : UTSObject()
open class HttpRequest {
    private var baseURL: String
    private var header: UTSJSONObject
    private var showLoading: Boolean
    private var autoAuth: Boolean
    private var interceptors: HttpInterceptors = HttpInterceptors()
    private var timeout: Number
    constructor(options: HttpInitOptions){
        this.baseURL = options.baseURL
        this.header = _uO("Content-Type" to "application/json;charset=utf-8")
        if (options.header != null) {
            for(key in resolveUTSKeyIterator(options.header!!)){
                this.header[key] = options.header!![key]
            }
        }
        this.showLoading = options.showLoading ?: true
        this.autoAuth = options.autoAuth ?: true
        this.timeout = options.timeout ?: 50000
    }
    public open fun setInterceptors(interceptors: HttpInterceptors): Unit {
        this.interceptors = interceptors
    }
    private fun <T> coreRequest(options: HttpRequestOptions): UTSPromise<T> {
        val config = this.beforeRequest(options)
        return UTSPromise<Any>(fun(resolve, reject){
            uni_request<Any>(RequestOptions(url = config.url, data = config.data, header = config.header, method = config.method ?: "GET", timeout = config.timeout ?: 60000, success = fun(res){
                this.handleResponse(res, config).then(fun(value: Any): Any {
                    resolve(value)
                    return value
                }
                ).`catch`(fun(error: Any?): Any? {
                    reject(error)
                    return error
                }
                )
            }
            , fail = fun(err: Any){
                this.handleError(err, config)
                reject(err)
            }
            ))
        }
        ) as UTSPromise<T>
    }
    private fun beforeRequest(options: HttpRequestOptions): HttpRequestOptions {
        val config = HttpRequestOptions(url = options.url, data = options.data, method = options.method, header = if (options.header != null) {
            options.header
        } else {
            this.header
        }
        , timeout = if (options.timeout != null) {
            options.timeout
        } else {
            this.timeout
        }
        , showLoading = options.showLoading ?: this.showLoading, loadingText = options.loadingText, disableAuth = options.disableAuth ?: this.autoAuth, customError = options.customError, isTotal = options.isTotal)
        if ((if (config.showLoading != null) {
            config.showLoading!!
        } else {
            this.showLoading
        }
        ) == true) {
            uni_showLoading(ShowLoadingOptions(title = config.loadingText ?: "加载中...", mask = true))
        }
        if (!config.url.startsWith("http")) {
            config.url = this.baseURL + config.url
        }
        if (this.autoAuth == true && config.disableAuth != true) {
            val token = uni_getStorageSync("token") as String?
            if (token != null && token != "") {
                val newHeader: UTSJSONObject = _uO()
                if (config.header != null) {
                    for(key in resolveUTSKeyIterator(config.header!!)){
                        newHeader[key] = config.header!![key]
                    }
                }
                newHeader["Authorization"] = "Bearer " + token
                config.header = newHeader
            }
        }
        if (config.method == "GET") {
            val newData: UTSJSONObject = _uO()
            if (config.data != null) {
                for(key in resolveUTSKeyIterator(config.data!!)){
                    newData[key] = config.data!![key]
                }
            }
            newData["_t"] = Date.now()
            config.data = newData
        }
        if (this.interceptors.request != null) {
            return this.interceptors.request!!(config)
        }
        return config
    }
    private fun handleResponse(response: Any, options: HttpRequestOptions): UTSPromise<Any> {
        uni_hideLoading(null)
        val responseObject = response as UTSJSONObject
        val statusCode = responseObject["statusCode"] as Number
        val data = responseObject["data"]
        var res: ResponseData<Any>
        if (UTSAndroid.`typeof`(data) == "string") {
            res = JSON.parse(data as String) as ResponseData<Any>
        } else {
            res = data as ResponseData<Any>
        }
        if (this.interceptors.response != null) {
            val intercepted = this.interceptors.response!!(res, options)
            if (intercepted != null) {
                return UTSPromise.resolve(intercepted)
            }
        }
        if (statusCode < 200 || statusCode >= 300) {
            val msg = this.getHttpErrorMsg(statusCode)
            uni_showToast(ShowToastOptions(title = msg, icon = "none"))
            return UTSPromise.reject(UTSError(msg)) as UTSPromise<Any>
        }
        if (res.code != 200 && res.code != 0) {
            if (res.code == 401) {
                uni_showToast(ShowToastOptions(title = "登录已过期，请重新登录", icon = "none"))
            } else {
                uni_showToast(ShowToastOptions(title = res.msg ?: "请求失败", icon = "none"))
            }
            return UTSPromise.reject(res) as UTSPromise<Any>
        }
        return UTSPromise.resolve(res)
    }
    private fun handleError(error: Any, options: HttpRequestOptions): Unit {
        uni_hideLoading(null)
        if (this.interceptors.error != null) {
            this.interceptors.error!!(error, options)
        }
        uni_showToast(ShowToastOptions(title = "网络异常，请稍后重试", icon = "none"))
    }
    private fun getHttpErrorMsg(status: Number): String {
        if (status == 400) {
            return "请求参数错误"
        }
        if (status == 401) {
            return "未授权，请登录"
        }
        if (status == 403) {
            return "拒绝访问"
        }
        if (status == 404) {
            return "请求的资源不存在"
        }
        if (status == 405) {
            return "请求方法不允许"
        }
        if (status == 408) {
            return "请求超时"
        }
        if (status == 500) {
            return "服务器错误"
        }
        if (status == 502) {
            return "网关错误"
        }
        if (status == 503) {
            return "服务不可用"
        }
        if (status == 504) {
            return "网关超时"
        }
        return "请求失败"
    }
    private fun createRequestConfig(url: Any, data: Any?, method: HttpMethod, options: HttpRequestExtraOptions?): HttpRequestOptions {
        val requestUrl: String = url.toString()
        val requestData: UTSJSONObject? = if (data != null) {
            data as UTSJSONObject
        } else {
            null
        }
        val config = HttpRequestOptions(url = requestUrl, data = requestData, method = method)
        if (options != null) {
            config.header = options.header
            config.timeout = options.timeout
            config.showLoading = options.showLoading
            config.loadingText = options.loadingText
            config.disableAuth = options.disableAuth
            config.customError = options.customError
            config.isTotal = options.isTotal
        }
        return config
    }
    public open fun <T> get(url: Any): UTSPromise<T> {
        return this.get<T>(url, null, null)
    }
    public open fun <T> get(url: Any, data: Any?): UTSPromise<T> {
        return this.get<T>(url, data, null)
    }
    public open fun <T> get(url: Any, data: Any?, options: HttpRequestExtraOptions?): UTSPromise<T> {
        val config = this.createRequestConfig(url, data, "GET", options)
        return this.coreRequest<T>(config)
    }
    public open fun <T> post(url: Any): UTSPromise<T> {
        return this.post<T>(url, null, null)
    }
    public open fun <T> post(url: Any, data: Any?): UTSPromise<T> {
        return this.post<T>(url, data, null)
    }
    public open fun <T> post(url: Any, data: Any?, options: HttpRequestExtraOptions?): UTSPromise<T> {
        val config = this.createRequestConfig(url, data, "POST", options)
        return this.coreRequest<T>(config)
    }
    public open fun <T> put(url: Any): UTSPromise<T> {
        return this.put<T>(url, null, null)
    }
    public open fun <T> put(url: Any, data: Any?): UTSPromise<T> {
        return this.put<T>(url, data, null)
    }
    public open fun <T> put(url: Any, data: Any?, options: HttpRequestExtraOptions?): UTSPromise<T> {
        val config = this.createRequestConfig(url, data, "PUT", options)
        return this.coreRequest<T>(config)
    }
    public open fun <T> `delete`(url: Any): UTSPromise<T> {
        return this.`delete`<T>(url, null, null)
    }
    public open fun <T> `delete`(url: Any, data: Any?): UTSPromise<T> {
        return this.`delete`<T>(url, data, null)
    }
    public open fun <T> `delete`(url: Any, data: Any?, options: HttpRequestExtraOptions?): UTSPromise<T> {
        val config = this.createRequestConfig(url, data, "DELETE", options)
        return this.coreRequest<T>(config)
    }
    public open fun <T> upload(url: Any): UTSPromise<T> {
        return this.upload<T>(url, null, null)
    }
    public open fun <T> upload(url: Any, data: Any?): UTSPromise<T> {
        return this.upload<T>(url, data, null)
    }
    public open fun <T> upload(url: Any, data: Any?, options: HttpRequestExtraOptions?): UTSPromise<T> {
        val config = this.createRequestConfig(url, if (data != null) {
            data
        } else {
            null
        }
        , "POST", if (options != null) {
            options
        } else {
            null
        }
        )
        val uploadConfig = this.beforeRequest(config)
        val uploadData = if (uploadConfig.data != null) {
            uploadConfig.data!!
        } else {
            _uO()
        }
        val File = uploadData["File"] as String?
        val formData: UTSJSONObject = _uO()
        for(key in resolveUTSKeyIterator(uploadData)){
            if (key != "File") {
                formData[key] = uploadData[key]
            }
        }
        if (File == null || File == "") {
            return this.postFormData<T>(url, data, options)
        }
        return UTSPromise<Any>(fun(resolve, reject){
            uni_uploadFile(UploadFileOptions(url = uploadConfig.url, filePath = File, name = "file", formData = formData, header = uploadConfig.header, timeout = uploadConfig.timeout ?: 60000, success = fun(res){
                this.handleResponse(res, config).then(fun(value: Any): Any {
                    resolve(value)
                    return value
                }
                ).`catch`(fun(error: Any?): Any? {
                    reject(error)
                    return error
                }
                )
            }
            , fail = fun(err: Any){
                this.handleError(err, config)
                reject(err)
            }
            ))
        }
        ) as UTSPromise<T>
    }
    public open fun <T> postFormData(url: Any): UTSPromise<T> {
        return this.postFormData<T>(url, null, null)
    }
    public open fun <T> postFormData(url: Any, data: Any?): UTSPromise<T> {
        return this.postFormData<T>(url, data, null)
    }
    public open fun <T> postFormData(url: Any, data: Any?, options: HttpRequestExtraOptions?): UTSPromise<T> {
        val config = this.createRequestConfig(url, if (data != null) {
            data
        } else {
            null
        }
        , "POST", if (options != null) {
            options
        } else {
            null
        }
        )
        if (options == null || options.header == null) {
            config.header = _uO("Content-Type" to "application/x-www-form-urlencoded")
        }
        return this.coreRequest<T>(config)
    }
}
typealias UserRole = String
open class ToastOpts (
    @JsonNotNull
    open var title: String,
    open var icon: String? = null,
    open var duration: Number? = null,
    open var mask: Boolean? = null,
    open var image: String? = null,
) : UTSObject()
val normalizeToastTitle = fun(value: Any, fallback: String?): String {
    if (UTSAndroid.`typeof`(value) === "string") {
        return value as String
    }
    if (value is UTSError) {
        return (value as UTSError).message
    }
    if (value == null) {
        return if (fallback != null) {
            fallback
        } else {
            ""
        }
    }
    return "" + value
}
fun showToast(reassignedOptions: Any): Unit {
    var options = reassignedOptions
    if (UTSAndroid.`typeof`(options) === "string" || options is UTSError) {
        options = ToastOpts(title = normalizeToastTitle(options, null), icon = "none", duration = 2000, mask = false)
    }
    val config = ToastOpts(title = normalizeToastTitle((options as ToastOpts).title, null), icon = if ((options as ToastOpts).icon != null) {
        (options as ToastOpts).icon
    } else {
        "none"
    }
    , duration = if ((options as ToastOpts).duration != null) {
        (options as ToastOpts).duration
    } else {
        2000
    }
    , mask = if ((options as ToastOpts).mask != null) {
        (options as ToastOpts).mask
    } else {
        false
    }
    )
    uni_showToast(ShowToastOptions(title = config.title, icon = if (config.icon != null) {
        config.icon!!
    } else {
        "none"
    }
    , duration = if (config.duration != null) {
        config.duration
    } else {
        2000
    }
    , mask = if (config.mask != null) {
        config.mask
    } else {
        false
    }
    ))
}
fun showError(title: Any, duration: Number? = null, options: ToastOpts? = null): Unit {
    var toastIcon = "error"
    var toastMask = false
    if (options != null) {
        if (options.icon != null) {
            toastIcon = options.icon!!
        }
        if (options.mask != null) {
            toastMask = options.mask!!
        }
    }
    showToast(ToastOpts(title = normalizeToastTitle(title, "操作失败"), icon = toastIcon, duration = if (duration != null) {
        duration
    } else {
        2000
    }
    , mask = toastMask))
}
val AVATAR_SIZE: Number = 50
val AVATAR_PADDING: Number = 10
val AVATAR_TEXT_X_OFFSET: Number = 15
val AVATAR_FONT: String = "bold 14px sans-serif"
val AVATAR_LINE_HEIGHT: Number = 22
val QR_SIZE: Number = 60
val QR_PADDING: Number = 20
val QR_BG_PADDING: Number = 5
val QR_BG_COLOR: String = "#ffffff"
val QR_TEXT_X_OFFSET: Number = 15
val QR_FONT: String = "14px sans-serif"
val QR_LINE_HEIGHT: Number = 20
val CANVAS_RENDER_DELAY: Number = 300
val EXPORT_DELAY: Number = 100
val IMAGE_LOAD_DELAY: Number = 100
enum class ImageType__1(override val value: String) : UTSEnumString {
    BACKGROUND("background"),
    AVATAR("avatar"),
    QR_CODE("qrCode")
}
open class ImageInfo (
    @JsonNotNull
    open var url: String,
    @JsonNotNull
    open var type: ImageType__1,
    @JsonNotNull
    open var name: String,
) : UTSObject()
open class TextLine (
    @JsonNotNull
    open var line1: String,
    @JsonNotNull
    open var line2: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return TextLineReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class TextLineReactiveObject : TextLine, IUTSReactive<TextLine> {
    override var __v_raw: TextLine
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: TextLine, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(line1 = __v_raw.line1, line2 = __v_raw.line2) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): TextLineReactiveObject {
        return TextLineReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var line1: String
        get() {
            return _tRG(__v_raw, "line1", __v_raw.line1, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("line1")) {
                return
            }
            val oldValue = __v_raw.line1
            __v_raw.line1 = value
            _tRS(__v_raw, "line1", oldValue, value)
        }
    override var line2: String
        get() {
            return _tRG(__v_raw, "line2", __v_raw.line2, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("line2")) {
                return
            }
            val oldValue = __v_raw.line2
            __v_raw.line2 = value
            _tRS(__v_raw, "line2", oldValue, value)
        }
}
open class PosterConfig (
    @JsonNotNull
    open var canvasWidth: Number,
    @JsonNotNull
    open var canvasHeight: Number,
    @JsonNotNull
    open var backgroundUrl: String,
    @JsonNotNull
    open var avatarUrl: String,
    @JsonNotNull
    open var qrCodeUrl: String,
    @JsonNotNull
    open var inviteCode: String,
    @JsonNotNull
    open var avatarText: TextLine,
    @JsonNotNull
    open var qrCodeText: TextLine,
    open var capsuleOffset: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PosterConfigReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PosterConfigReactiveObject : PosterConfig, IUTSReactive<PosterConfig> {
    override var __v_raw: PosterConfig
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PosterConfig, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(canvasWidth = __v_raw.canvasWidth, canvasHeight = __v_raw.canvasHeight, backgroundUrl = __v_raw.backgroundUrl, avatarUrl = __v_raw.avatarUrl, qrCodeUrl = __v_raw.qrCodeUrl, inviteCode = __v_raw.inviteCode, avatarText = __v_raw.avatarText, qrCodeText = __v_raw.qrCodeText, capsuleOffset = __v_raw.capsuleOffset) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PosterConfigReactiveObject {
        return PosterConfigReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var canvasWidth: Number
        get() {
            return _tRG(__v_raw, "canvasWidth", __v_raw.canvasWidth, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("canvasWidth")) {
                return
            }
            val oldValue = __v_raw.canvasWidth
            __v_raw.canvasWidth = value
            _tRS(__v_raw, "canvasWidth", oldValue, value)
        }
    override var canvasHeight: Number
        get() {
            return _tRG(__v_raw, "canvasHeight", __v_raw.canvasHeight, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("canvasHeight")) {
                return
            }
            val oldValue = __v_raw.canvasHeight
            __v_raw.canvasHeight = value
            _tRS(__v_raw, "canvasHeight", oldValue, value)
        }
    override var backgroundUrl: String
        get() {
            return _tRG(__v_raw, "backgroundUrl", __v_raw.backgroundUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("backgroundUrl")) {
                return
            }
            val oldValue = __v_raw.backgroundUrl
            __v_raw.backgroundUrl = value
            _tRS(__v_raw, "backgroundUrl", oldValue, value)
        }
    override var avatarUrl: String
        get() {
            return _tRG(__v_raw, "avatarUrl", __v_raw.avatarUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatarUrl")) {
                return
            }
            val oldValue = __v_raw.avatarUrl
            __v_raw.avatarUrl = value
            _tRS(__v_raw, "avatarUrl", oldValue, value)
        }
    override var qrCodeUrl: String
        get() {
            return _tRG(__v_raw, "qrCodeUrl", __v_raw.qrCodeUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("qrCodeUrl")) {
                return
            }
            val oldValue = __v_raw.qrCodeUrl
            __v_raw.qrCodeUrl = value
            _tRS(__v_raw, "qrCodeUrl", oldValue, value)
        }
    override var inviteCode: String
        get() {
            return _tRG(__v_raw, "inviteCode", __v_raw.inviteCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("inviteCode")) {
                return
            }
            val oldValue = __v_raw.inviteCode
            __v_raw.inviteCode = value
            _tRS(__v_raw, "inviteCode", oldValue, value)
        }
    override var avatarText: TextLine
        get() {
            return _tRG(__v_raw, "avatarText", __v_raw.avatarText, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatarText")) {
                return
            }
            val oldValue = __v_raw.avatarText
            __v_raw.avatarText = value
            _tRS(__v_raw, "avatarText", oldValue, value)
        }
    override var qrCodeText: TextLine
        get() {
            return _tRG(__v_raw, "qrCodeText", __v_raw.qrCodeText, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("qrCodeText")) {
                return
            }
            val oldValue = __v_raw.qrCodeText
            __v_raw.qrCodeText = value
            _tRS(__v_raw, "qrCodeText", oldValue, value)
        }
    override var capsuleOffset: Number?
        get() {
            return _tRG(__v_raw, "capsuleOffset", __v_raw.capsuleOffset, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("capsuleOffset")) {
                return
            }
            val oldValue = __v_raw.capsuleOffset
            __v_raw.capsuleOffset = value
            _tRS(__v_raw, "capsuleOffset", oldValue, value)
        }
}
open class UsePosterOptions (
    @JsonNotNull
    open var canvasId: String,
    @JsonNotNull
    open var posterConfig: PosterConfig,
    open var getPixelRatio: () -> Number,
    open var queryIn: Any? = null,
) : UTSObject()
open class UsePosterReturn (
    @JsonNotNull
    open var showPosterModal: Ref<Boolean>,
    @JsonNotNull
    open var canvasReady: Ref<Boolean>,
    @JsonNotNull
    open var exportedImagePath: Ref<String>,
    @JsonNotNull
    open var backgroundPath: Ref<String>,
    @JsonNotNull
    open var avatarPath: Ref<String>,
    @JsonNotNull
    open var qrCodePath: Ref<String>,
    open var drawPoster: () -> UTSPromise<String>,
    open var generatePoster: () -> UTSPromise<String>,
    open var exportCanvasAsImage: () -> UTSPromise<Unit>,
    open var previewImage: () -> UTSPromise<Unit>,
    open var saveImage: () -> UTSPromise<Unit>,
    open var handlePosterShare: (posterPath: String) -> UTSPromise<Unit>,
    open var handleClose: () -> Unit,
) : UTSObject()
fun usePoster(options: UsePosterOptions): UsePosterReturn {
    val canvasId = options.canvasId
    val posterConfig = options.posterConfig
    val getPixelRatio = options.getPixelRatio
    val _options_queryIn = options.queryIn
    val queryIn = if (_options_queryIn == null) {
        null
    } else {
        _options_queryIn
    }
    val showPosterModal = ref(false)
    val canvasReady = ref(false)
    val exportedImagePath = ref("")
    val backgroundPath = ref("")
    val avatarPath = ref("")
    val qrCodePath = ref("")
    val createCanvasQuery = fun(): SelectorQuery {
        val query = uni_createSelectorQuery()
        if (queryIn != null) {
            query.`in`(queryIn)
        }
        return query
    }
    val getCanvasNode = fun(): UTSPromise<Any> {
        return UTSPromise(fun(resolve, reject){
            val query = createCanvasQuery()
            query.select("#" + canvasId).fields(NodeField(node = true, size = true), fun(res){
                if (res == null) {
                    reject(UTSError("Canvas 节点获取失败"))
                    return
                }
                val node = (res as UTSJSONObject)["node"]
                if (node == null) {
                    reject(UTSError("Canvas 节点获取失败"))
                    return
                }
                resolve(node)
            }
            ).exec()
        }
        )
    }
    val delay = fun(ms: Number): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, _reject){
            setTimeout(fun(){
                resolve(Unit)
            }
            , ms)
        }
        )
    }
    val showError = fun(title: String, icon: String): Unit {
        uni_showToast(ShowToastOptions(title = title, icon = icon))
    }
    val showSuccess = fun(title: String): Unit {
        uni_showToast(ShowToastOptions(title = title, icon = "success"))
    }
    val downloadImage = fun(imageInfo: ImageInfo): UTSPromise<String> {
        return UTSPromise(fun(resolve, reject){
            val url = imageInfo.url
            if (url.startsWith("/static/") || url.startsWith("./") || url.startsWith("../") || !url.startsWith("http")) {
                console.log("✅ " + imageInfo.name + "为本地资源，直接使用:", url)
                resolve(url)
                return
            }
            console.log("下载" + imageInfo.name + ":", url)
            uni_downloadFile(DownloadFileOptions(url = url, success = fun(res){
                if (res.statusCode === 200) {
                    console.log("✅ " + imageInfo.name + "下载成功:", res.tempFilePath)
                    resolve(res.tempFilePath)
                } else {
                    val error = "" + imageInfo.name + "下载失败(状态码:" + res.statusCode + ")"
                    console.error("❌ " + error)
                    reject(UTSError(error))
                }
            }
            , fail = fun(err){
                val error = "" + imageInfo.name + "下载失败"
                console.error("❌ " + error + ":", err)
                reject(UTSError(error))
            }
            ))
        }
        )
    }
    val drawTextLines = fun(ctx: CanvasRenderingContext2D, textX: Number, centerY: Number, lines: UTSArray<String>, font: String, lineHeight: Number){
        val totalTextHeight = lineHeight * lines.length
        val textStartY = centerY - totalTextHeight / 2 + lineHeight / 2
        ctx.fillStyle = "#ffffff"
        ctx.font = font
        ctx.textAlign = "left"
        ctx.textBaseline = "middle"
        ctx.strokeStyle = "rgba(0, 0, 0, 0.5)"
        ctx.lineWidth = 3
        lines.forEach(fun(line, index){
            ctx.strokeText(line, textX, textStartY + index * lineHeight)
        }
        )
        lines.forEach(fun(line, index){
            ctx.fillText(line, textX, textStartY + index * lineHeight)
        }
        )
    }
    val loadImages = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                console.log("App 平台：开始下载图片...")
                val images = _uA(
                    ImageInfo(url = posterConfig.backgroundUrl, type = ImageType__1.BACKGROUND, name = "背景图"),
                    ImageInfo(url = posterConfig.avatarUrl, type = ImageType__1.AVATAR, name = "头像"),
                    ImageInfo(url = posterConfig.qrCodeUrl, type = ImageType__1.QR_CODE, name = "二维码")
                ) as UTSArray<ImageInfo>
                try {
                    val results = await(UTSPromise.all(images.map(fun(img): UTSPromise<String> {
                        return downloadImage(img)
                    }
                    )))
                    backgroundPath.value = results[0]
                    avatarPath.value = results[1]
                    qrCodePath.value = results[2]
                    console.log("✅ 所有图片下载成功")
                    console.log("背景图:", backgroundPath.value)
                    console.log("头像:", avatarPath.value)
                    console.log("二维码:", qrCodePath.value)
                }
                 catch (err: Throwable) {
                    console.error("❌ 图片下载失败:", err)
                    throw err
                }
        })
    }
    val drawImagePlaceholder = fun(ctx: CanvasRenderingContext2D, x: Number, y: Number, width: Number, height: Number, label: String): Unit {
        ctx.fillStyle = "rgba(255, 255, 255, 0.85)"
        ctx.fillRect(x, y, width, height)
        ctx.fillStyle = "#6b7280"
        ctx.font = "12px sans-serif"
        ctx.textAlign = "center"
        ctx.textBaseline = "middle"
        ctx.fillText(label, x + width / 2, y + height / 2)
    }
    val drawBackgroundImage = fun(ctx: CanvasRenderingContext2D, canvas: UniCanvasElement): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, _reject){
            if (backgroundPath.value == "") {
                resolve(Unit)
                return
            }
            val capsuleOffset = posterConfig.capsuleOffset ?: 0
            ctx.fillStyle = "#16a34a"
            ctx.fillRect(0, capsuleOffset, posterConfig.canvasWidth, posterConfig.canvasHeight)
            resolve(Unit)
        }
        )
    }
    val drawAvatar = fun(ctx: CanvasRenderingContext2D, canvas: UniCanvasElement): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, _reject){
            if (avatarPath.value == "") {
                resolve(Unit)
                return
            }
            val capsuleOffset = posterConfig.capsuleOffset ?: 0
            val x = AVATAR_PADDING
            val y = AVATAR_PADDING + capsuleOffset
            drawImagePlaceholder(ctx, x, y, AVATAR_SIZE, AVATAR_SIZE, "头像")
            val textX = x + AVATAR_SIZE + AVATAR_TEXT_X_OFFSET
            val avatarCenterY = y + AVATAR_SIZE / 2
            drawTextLines(ctx, textX, avatarCenterY, _uA(
                posterConfig.avatarText.line1,
                posterConfig.avatarText.line2
            ), AVATAR_FONT, AVATAR_LINE_HEIGHT)
            resolve(Unit)
        }
        )
    }
    val drawQRCode = fun(ctx: CanvasRenderingContext2D, canvas: UniCanvasElement): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, _reject){
            if (qrCodePath.value == "") {
                resolve(Unit)
                return
            }
            val capsuleOffset = posterConfig.capsuleOffset ?: 0
            val x = QR_PADDING
            val actualCanvasHeight = posterConfig.canvasHeight + capsuleOffset
            val y = actualCanvasHeight - QR_SIZE - QR_PADDING
            ctx.fillStyle = QR_BG_COLOR
            ctx.fillRect(x - QR_BG_PADDING, y - QR_BG_PADDING, QR_SIZE + QR_BG_PADDING * 2, QR_SIZE + QR_BG_PADDING * 2)
            drawImagePlaceholder(ctx, x, y, QR_SIZE, QR_SIZE, "二维码")
            val textX = x + QR_SIZE + QR_TEXT_X_OFFSET
            val qrCenterY = y + QR_SIZE / 2
            drawTextLines(ctx, textX, qrCenterY, _uA(
                posterConfig.qrCodeText.line1,
                posterConfig.qrCodeText.line2
            ), QR_FONT, QR_LINE_HEIGHT)
            resolve(Unit)
        }
        )
    }
    val drawPoster = fun(): UTSPromise<String> {
        return UTSPromise(fun(resolve, reject){
            getCanvasNode().then(fun(node): UTSPromise<Unit> {
                return wrapUTSPromise(suspend w3@{
                        val canvas = node as UniCanvasElement
                        val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
                        if (ctx == null) {
                            console.error("❌ Canvas 上下文获取失败")
                            reject(UTSError("Canvas 上下文获取失败"))
                            return@w3
                        }
                        console.log("✅ Canvas 节点和上下文获取成功")
                        val capsuleOffset = posterConfig.capsuleOffset ?: 0
                        val dpr = getPixelRatio()
                        val actualCanvasHeight = posterConfig.canvasHeight + capsuleOffset
                        canvas.width = posterConfig.canvasWidth * dpr
                        canvas.height = actualCanvasHeight * dpr
                        ctx.scale(dpr, dpr)
                        console.log("Canvas 实际尺寸:", _uO("width" to posterConfig.canvasWidth, "heightWithOffset" to actualCanvasHeight, "offset" to capsuleOffset))
                        ctx.clearRect(0, 0, posterConfig.canvasWidth, actualCanvasHeight)
                        ctx.fillStyle = "transparent"
                        ctx.fillRect(0, 0, posterConfig.canvasWidth, actualCanvasHeight)
                        try {
                            console.log("开始绘制背景图...")
                            await(drawBackgroundImage(ctx, canvas))
                            console.log("✅ 背景图绘制完成")
                            console.log("开始绘制头像...")
                            await(drawAvatar(ctx, canvas))
                            console.log("✅ 头像绘制完成")
                            console.log("开始绘制二维码...")
                            await(drawQRCode(ctx, canvas))
                            console.log("✅ 二维码绘制完成")
                            console.log("等待 Canvas 渲染...")
                            await(delay(CANVAS_RENDER_DELAY))
                            console.log("✅ 海报绘制完成（未生成文件）")
                            canvasReady.value = true
                            resolve("canvas_ready")
                        }
                         catch (err: Throwable) {
                            console.error("❌ 绘制过程出错:", err)
                            reject(err)
                        }
                })
            }
            ).`catch`(fun(err: Any?){
                console.error("❌ Canvas 节点获取失败", err)
                reject(err)
            }
            )
        }
        )
    }
    val handleClose = fun(): Unit {
        showPosterModal.value = false
        canvasReady.value = false
        exportedImagePath.value = ""
        backgroundPath.value = ""
        avatarPath.value = ""
        qrCodePath.value = ""
    }
    val generatePoster = fun(): UTSPromise<String> {
        return wrapUTSPromise(suspend w1@{
                try {
                    uni_showLoading(ShowLoadingOptions(title = "生成海报中..."))
                    console.log("=== 开始生成海报 ===")
                    console.log("步骤 1: 加载图片...")
                    await(loadImages())
                    console.log("✅ 图片加载完成")
                    await(delay(IMAGE_LOAD_DELAY))
                    console.log("步骤 2: 绘制海报...")
                    val imagePath = await(drawPoster())
                    console.log("✅ 海报生成完成:", imagePath)
                    return@w1 imagePath
                }
                 catch (err: Throwable) {
                    console.error("❌ 海报生成失败:", err)
                    handleClose()
                    throw err
                }
                 finally {
                    uni_hideLoading(null)
                }
        })
    }
    val exportCanvasAsImage = fun(): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, reject){
            setTimeout(fun(){
                getCanvasNode().then(fun(node){
                    val canvas = node as UniCanvasElement
                    try {
                        console.log("正在转换 Canvas 为图片...")
                        val dataURL = canvas.toDataURL("image/png")
                        if (dataURL == "" || dataURL === "data:,") {
                            console.error("❌ Canvas 转换失败，可能还未渲染完成")
                            showError("Canvas 渲染未完成", "error")
                            reject(UTSError("Canvas 转换失败"))
                            return
                        }
                        val base64 = dataURL.split(",")[1]
                        val userDataPath = uni_env?.USER_DATA_PATH ?: ""
                        val filePath = "" + userDataPath + "/canvas_" + Date.now() + ".png"
                        console.log("正在保存文件到:", filePath)
                        uni_getFileSystemManager().writeFile(WriteFileOptions(filePath = filePath, data = base64, encoding = "base64", success = fun(_res){
                            exportedImagePath.value = filePath
                            console.log("✅ 文件保存成功:", filePath)
                            resolve(Unit)
                        }
                        , fail = fun(err){
                            console.error("❌ 文件保存失败:", err)
                            showError("导出失败", "error")
                            reject(err)
                        }
                        ))
                    }
                     catch (err: Throwable) {
                        console.error("❌ 导出过程出错:", err)
                        showError("导出失败", "error")
                        reject(err)
                    }
                }
                ).`catch`(fun(err: Any?){
                    console.error("❌ 导出时 Canvas 节点获取失败", err)
                    showError("导出失败", "error")
                    reject(err)
                }
                )
            }
            , EXPORT_DELAY)
        }
        )
    }
    val ensureImageExported = fun(): UTSPromise<Boolean> {
        return wrapUTSPromise(suspend w1@{
                if (exportedImagePath.value !== "") {
                    return@w1 true
                }
                console.log("图片未导出，正在从 Canvas 生成文件...")
                uni_showLoading(ShowLoadingOptions(title = "正在生成图片...", mask = true))
                try {
                    await(exportCanvasAsImage())
                    uni_hideLoading(null)
                    return@w1 exportedImagePath.value !== ""
                }
                 catch (err: Throwable) {
                    uni_hideLoading(null)
                    console.error("❌ 图片生成失败:", err)
                    return@w1 false
                }
        })
    }
    val previewImage = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                console.log("=== 预览图片 ===")
                try {
                    val hasImage = await(ensureImageExported())
                    if (!hasImage) {
                        showError("图片生成失败", "error")
                        return@w1
                    }
                    console.log("图片路径:", exportedImagePath.value)
                    console.log("App 平台：调用 uni.previewImage API")
                    uni_previewImage(PreviewImageOptions(urls = _uA(
                        exportedImagePath.value
                    ), current = exportedImagePath.value))
                }
                 catch (err: Throwable) {
                    console.error("❌ 预览过程出错:", err)
                    uni_hideLoading(null)
                    showError("预览失败", "error")
                }
        })
    }
    val saveImage = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                console.log("=== 保存图片 ===")
                try {
                    val hasImage = await(ensureImageExported())
                    if (!hasImage) {
                        showError("图片生成失败", "error")
                        return@w1
                    }
                    console.log("图片路径:", exportedImagePath.value)
                    console.log("App 平台：调用 uni.saveImageToPhotosAlbum API")
                    uni_saveImageToPhotosAlbum(SaveImageToPhotosAlbumOptions(filePath = exportedImagePath.value, success = fun(_){
                        console.log("✅ 保存成功")
                        showSuccess("保存成功")
                    }
                    , fail = fun(err){
                        console.error("❌ 保存失败", err)
                        showError("保存失败", "error")
                    }
                    ))
                }
                 catch (err: Throwable) {
                    console.error("❌ 保存过程出错:", err)
                    uni_hideLoading(null)
                    showError("保存失败", "error")
                }
        })
    }
    val handlePosterShare = fun(posterPath: String): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                console.log("分享海报:", posterPath)
                try {
                    val hasImage = await(ensureImageExported())
                    if (!hasImage) {
                        showError("图片生成失败", "error")
                        return@w1
                    }
                    uni_showLoading(ShowLoadingOptions(title = "正在保存...", mask = true))
                    uni_saveImageToPhotosAlbum(SaveImageToPhotosAlbumOptions(filePath = exportedImagePath.value, success = fun(_){
                        uni_hideLoading(null)
                        uni_showModal(ShowModalOptions(title = "保存成功", content = "海报已保存到相册，您可以从相册中打开图片进行分享", showCancel = false, confirmText = "好的", success = fun(_){
                            showPosterModal.value = false
                        }
                        ))
                    }
                    , fail = fun(error){
                        uni_hideLoading(null)
                        console.error("保存失败:", error)
                        uni_showModal(ShowModalOptions(title = "保存失败", content = "无法保存到相册，请检查相册权限设置", showCancel = false))
                    }
                    ))
                }
                 catch (err: Throwable) {
                    console.error("❌ 分享过程出错:", err)
                    uni_hideLoading(null)
                    showError("分享失败", "error")
                }
        })
    }
    return UsePosterReturn(showPosterModal = showPosterModal, canvasReady = canvasReady, exportedImagePath = exportedImagePath, backgroundPath = backgroundPath, avatarPath = avatarPath, qrCodePath = qrCodePath, drawPoster = drawPoster, generatePoster = generatePoster, exportCanvasAsImage = exportCanvasAsImage, previewImage = previewImage, saveImage = saveImage, handlePosterShare = handlePosterShare, handleClose = handleClose)
}
open class MenuButtonInfo (
    @JsonNotNull
    open var height: Number,
    @JsonNotNull
    open var width: Number,
    @JsonNotNull
    open var top: Number,
    @JsonNotNull
    open var right: Number,
    @JsonNotNull
    open var bottom: Number,
    @JsonNotNull
    open var left: Number,
) : UTSObject()
open class SafeAreaInfo (
    @JsonNotNull
    open var statusBarHeight: Number,
    @JsonNotNull
    open var safeAreaBottom: Number,
    @JsonNotNull
    open var navBarHeight: Number,
    open var menuButton: MenuButtonInfo? = null,
    @JsonNotNull
    open var customNavHeight: Number,
    @JsonNotNull
    open var isNotch: Boolean = false,
    @JsonNotNull
    open var screenWidth: Number,
    @JsonNotNull
    open var screenHeight: Number,
    @JsonNotNull
    open var isMP: Boolean = false,
    @JsonNotNull
    open var availableWidth: Number,
    @JsonNotNull
    open var availableHeight: Number,
    @JsonNotNull
    open var pixelRatio: Number,
) : UTSObject()
open class SafeAreaTools (
    open var getSafeAreaInfo: () -> SafeAreaInfo,
    open var getStatusBarHeightRpx: () -> Number,
    open var getSafeAreaBottomRpx: () -> Number,
    open var getNavBarHeightRpx: () -> Number,
    open var getCustomNavHeightRpx: () -> Number,
    open var getMenuButtonLeftSpace: () -> Number,
    open var getNavbarContentHeight: (customHeight: Number) -> Number,
    open var getNavbarTotalHeight: (customHeight: Number) -> Number,
    open var getAvailableWidth: () -> Number,
    open var getAvailableHeight: () -> Number,
    open var getAvailableWidthRpx: () -> Number,
    open var getAvailableHeightRpx: () -> Number,
    open var getContentHeight: (navHeight: Number) -> Number,
    open var getContentHeightRpx: (navHeight: Number) -> Number,
    open var getPixelRatio: () -> Number,
) : UTSObject()
fun useSafeArea(): SafeAreaTools {
    val checkIsMP = fun(): Boolean {
        return false
    }
    val isMP = checkIsMP()
    val pxToRpx = fun(px: Number): Number {
        val info = uni_getSystemInfoSync()
        val screenWidth = if (info.screenWidth > 0) {
            info.screenWidth
        } else {
            375
        }
        return px * 750 / screenWidth
    }
    val getSafeAreaInfo = fun(): SafeAreaInfo {
        val systemInfo = uni_getSystemInfoSync()
        val statusBarHeight = systemInfo.statusBarHeight ?: 0
        var safeAreaBottom: Number = 0
        if (systemInfo.safeAreaInsets != null) {
            safeAreaBottom = systemInfo.safeAreaInsets!!.bottom ?: 0
        } else if (systemInfo.safeArea != null) {
            safeAreaBottom = systemInfo.screenHeight - systemInfo.safeArea!!.bottom
        }
        val isNotch = safeAreaBottom > 0 || statusBarHeight > 20
        val screenWidth = systemInfo.screenWidth
        val screenHeight = systemInfo.screenHeight
        var navBarHeight = statusBarHeight + 44
        var customNavHeight = statusBarHeight + 44
        var menuButton: MenuButtonInfo? = null
        val availableWidth = screenWidth
        val availableHeight = screenHeight - statusBarHeight - safeAreaBottom
        val pixelRatio = systemInfo.pixelRatio
        return SafeAreaInfo(statusBarHeight = statusBarHeight, safeAreaBottom = safeAreaBottom, navBarHeight = navBarHeight, menuButton = menuButton, customNavHeight = customNavHeight, isNotch = isNotch, screenWidth = screenWidth, screenHeight = screenHeight, isMP = isMP, availableWidth = availableWidth, availableHeight = availableHeight, pixelRatio = pixelRatio)
    }
    val getStatusBarHeightRpx = fun(): Number {
        val info = getSafeAreaInfo()
        return pxToRpx(info.statusBarHeight)
    }
    val getSafeAreaBottomRpx = fun(): Number {
        val info = getSafeAreaInfo()
        return pxToRpx(info.safeAreaBottom)
    }
    val getNavBarHeightRpx = fun(): Number {
        val info = getSafeAreaInfo()
        return pxToRpx(info.navBarHeight)
    }
    val getCustomNavHeightRpx = fun(): Number {
        val info = getSafeAreaInfo()
        return pxToRpx(info.customNavHeight)
    }
    val getMenuButtonLeftSpace = fun(): Number {
        val info = getSafeAreaInfo()
        if (info.menuButton != null) {
            return info.menuButton!!.left
        }
        return info.screenWidth
    }
    val getNavbarContentHeight = fun(customHeight: Number): Number {
        if (customHeight > 0) {
            return customHeight
        }
        val info = getSafeAreaInfo()
        if (info.isMP && info.menuButton != null) {
            return info.menuButton!!.height * 1.5
        }
        return 44
    }
    val getNavbarTotalHeight = fun(customHeight: Number): Number {
        val info = getSafeAreaInfo()
        return info.statusBarHeight + getNavbarContentHeight(customHeight)
    }
    val getAvailableWidth = fun(): Number {
        val info = getSafeAreaInfo()
        return info.availableWidth
    }
    val getAvailableHeight = fun(): Number {
        val info = getSafeAreaInfo()
        return info.availableHeight
    }
    val getAvailableWidthRpx = fun(): Number {
        val info = getSafeAreaInfo()
        return pxToRpx(info.availableWidth)
    }
    val getAvailableHeightRpx = fun(): Number {
        val info = getSafeAreaInfo()
        return pxToRpx(info.availableHeight)
    }
    val getContentHeight = fun(navHeight: Number): Number {
        val info = getSafeAreaInfo()
        val totalNavHeight = if (navHeight > 0) {
            navHeight
        } else {
            info.navBarHeight
        }
        return info.screenHeight - totalNavHeight - info.safeAreaBottom
    }
    val getContentHeightRpx = fun(navHeight: Number): Number {
        return pxToRpx(getContentHeight(navHeight))
    }
    val getPixelRatio = fun(): Number {
        val info = getSafeAreaInfo()
        return info.pixelRatio
    }
    return SafeAreaTools(getSafeAreaInfo = getSafeAreaInfo, getStatusBarHeightRpx = getStatusBarHeightRpx, getSafeAreaBottomRpx = getSafeAreaBottomRpx, getNavBarHeightRpx = getNavBarHeightRpx, getCustomNavHeightRpx = getCustomNavHeightRpx, getMenuButtonLeftSpace = getMenuButtonLeftSpace, getNavbarContentHeight = getNavbarContentHeight, getNavbarTotalHeight = getNavbarTotalHeight, getAvailableWidth = getAvailableWidth, getAvailableHeight = getAvailableHeight, getAvailableWidthRpx = getAvailableWidthRpx, getAvailableHeightRpx = getAvailableHeightRpx, getContentHeight = getContentHeight, getContentHeightRpx = getContentHeightRpx, getPixelRatio = getPixelRatio)
}
val STORAGE_KEYS: UTSJSONObject = _uO("TOKEN" to "token", "USER_INFO" to "user_info", "APP_CONFIG" to "app_config", "CACHE_DATA" to "cache_data", "USER_ROLE" to "user_role", "THEME" to "theme", "RECRUITER_AUTH" to "recruiter_auth", "RECRUITER_AUTH_STATUS" to "recruiter_auth_status")
typealias UserRole__1 = String
val ROLES: UTSJSONObject = _uO("APPLICANT" to "applicant" as UserRole__1, "RECRUITER" to "hire" as UserRole__1)
val AUDIT_STATUS: UTSJSONObject = _uO("APPROVED" to 0, "FROZEN" to 1, "PENDING" to 2, "REJECTED" to 3)
val PAGE_PATHS: UTSJSONObject = _uO("WELCOME" to "/pages/common/welcome/index", "ROLE_SELECT" to "/pages/common/role-select/index", "LOGIN" to "/pages/auth/login", "REGISTER" to "/pages/auth/register", "MESSAGE" to "/pages/common/message/index", "APPLY_HOME" to "/pages/apply/tabbar/home/index", "APPLY_COMMUNITY" to "/pages/apply/tabbar/community/index", "APPLY_EARN" to "/pages/apply/tabbar/make/index", "APPLY_MESSAGE" to "/pages/common/message/index", "APPLY_PROFILE" to "/pages/apply/tabbar/my/index", "RECRUIT_HOME" to "/pages/hire/tabbar/home/index", "RECRUIT_JOBS" to "/pages/hire/tabbar/position/index", "RECRUIT_PUBLISH" to "/pages/hire/position/publish/index", "RECRUIT_MESSAGE" to "/pages/common/message/index", "RECRUIT_PROFILE" to "/pages/hire/tabbar/my/index", "RECRUIT_AUTH" to "/pages/hire/enterprise/certification/index", "WAIT_AUDIT" to "/pages/common/auth/wait-audit")
val pathToBase64 = fun(path: String, mimeType: String?): UTSPromise<String> {
    return UTSPromise(fun(resolve, reject){
        if (path == "") {
            reject(UTSError("文件路径不能为空"))
            return
        }
    }
    )
}
open class ValidationResult (
    @JsonNotNull
    open var isValid: Boolean = false,
    @JsonNotNull
    open var message: String,
) : UTSObject()
val hasText = fun(value: String?): Boolean {
    if (value == null) {
        return false
    }
    return value !== ""
}
fun validatePhone(phone: String): ValidationResult {
    if (!hasText(phone)) {
        return ValidationResult(isValid = false, message = "请输入手机号")
    }
    if (!UTSRegExp("^1[3-9]\\d{9}\$", "").test(phone)) {
        return ValidationResult(isValid = false, message = "请输入正确的手机号格式")
    }
    return ValidationResult(isValid = true, message = "")
}
open class SignInDay (
    @JsonNotNull
    open var day: Any,
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var points: Number,
    @JsonNotNull
    open var status: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return SignInDayReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class SignInDayReactiveObject : SignInDay, IUTSReactive<SignInDay> {
    override var __v_raw: SignInDay
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: SignInDay, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(day = __v_raw.day, label = __v_raw.label, points = __v_raw.points, status = __v_raw.status) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): SignInDayReactiveObject {
        return SignInDayReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var day: Any
        get() {
            return _tRG(__v_raw, "day", __v_raw.day, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("day")) {
                return
            }
            val oldValue = __v_raw.day
            __v_raw.day = value
            _tRS(__v_raw, "day", oldValue, value)
        }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var points: Number
        get() {
            return _tRG(__v_raw, "points", __v_raw.points, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("points")) {
                return
            }
            val oldValue = __v_raw.points
            __v_raw.points = value
            _tRS(__v_raw, "points", oldValue, value)
        }
    override var status: String
        get() {
            return _tRG(__v_raw, "status", __v_raw.status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("status")) {
                return
            }
            val oldValue = __v_raw.status
            __v_raw.status = value
            _tRS(__v_raw, "status", oldValue, value)
        }
}
open class RankUser (
    @JsonNotNull
    open var rank: Number,
    @JsonNotNull
    open var userId: String,
    @JsonNotNull
    open var userName: String,
    @JsonNotNull
    open var avatar: String,
    @JsonNotNull
    open var totalPoints: Number,
    open var monthPoints: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return RankUserReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class RankUserReactiveObject : RankUser, IUTSReactive<RankUser> {
    override var __v_raw: RankUser
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: RankUser, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(rank = __v_raw.rank, userId = __v_raw.userId, userName = __v_raw.userName, avatar = __v_raw.avatar, totalPoints = __v_raw.totalPoints, monthPoints = __v_raw.monthPoints) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): RankUserReactiveObject {
        return RankUserReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var rank: Number
        get() {
            return _tRG(__v_raw, "rank", __v_raw.rank, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("rank")) {
                return
            }
            val oldValue = __v_raw.rank
            __v_raw.rank = value
            _tRS(__v_raw, "rank", oldValue, value)
        }
    override var userId: String
        get() {
            return _tRG(__v_raw, "userId", __v_raw.userId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("userId")) {
                return
            }
            val oldValue = __v_raw.userId
            __v_raw.userId = value
            _tRS(__v_raw, "userId", oldValue, value)
        }
    override var userName: String
        get() {
            return _tRG(__v_raw, "userName", __v_raw.userName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("userName")) {
                return
            }
            val oldValue = __v_raw.userName
            __v_raw.userName = value
            _tRS(__v_raw, "userName", oldValue, value)
        }
    override var avatar: String
        get() {
            return _tRG(__v_raw, "avatar", __v_raw.avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatar")) {
                return
            }
            val oldValue = __v_raw.avatar
            __v_raw.avatar = value
            _tRS(__v_raw, "avatar", oldValue, value)
        }
    override var totalPoints: Number
        get() {
            return _tRG(__v_raw, "totalPoints", __v_raw.totalPoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("totalPoints")) {
                return
            }
            val oldValue = __v_raw.totalPoints
            __v_raw.totalPoints = value
            _tRS(__v_raw, "totalPoints", oldValue, value)
        }
    override var monthPoints: Number?
        get() {
            return _tRG(__v_raw, "monthPoints", __v_raw.monthPoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("monthPoints")) {
                return
            }
            val oldValue = __v_raw.monthPoints
            __v_raw.monthPoints = value
            _tRS(__v_raw, "monthPoints", oldValue, value)
        }
}
typealias TaskType = Number
open class TaskItem (
    @JsonNotNull
    open var id: Number,
    @JsonNotNull
    open var type: TaskType,
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var title: String,
    @JsonNotNull
    open var description: String,
    @JsonNotNull
    open var points: Number,
    @JsonNotNull
    open var status: String,
    open var progress: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return TaskItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class TaskItemReactiveObject : TaskItem, IUTSReactive<TaskItem> {
    override var __v_raw: TaskItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: TaskItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, type = __v_raw.type, icon = __v_raw.icon, title = __v_raw.title, description = __v_raw.description, points = __v_raw.points, status = __v_raw.status, progress = __v_raw.progress) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): TaskItemReactiveObject {
        return TaskItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var id: Number
        get() {
            return _tRG(__v_raw, "id", __v_raw.id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("id")) {
                return
            }
            val oldValue = __v_raw.id
            __v_raw.id = value
            _tRS(__v_raw, "id", oldValue, value)
        }
    override var type: TaskType
        get() {
            return _tRG(__v_raw, "type", __v_raw.type, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("type")) {
                return
            }
            val oldValue = __v_raw.type
            __v_raw.type = value
            _tRS(__v_raw, "type", oldValue, value)
        }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
    override var title: String
        get() {
            return _tRG(__v_raw, "title", __v_raw.title, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("title")) {
                return
            }
            val oldValue = __v_raw.title
            __v_raw.title = value
            _tRS(__v_raw, "title", oldValue, value)
        }
    override var description: String
        get() {
            return _tRG(__v_raw, "description", __v_raw.description, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("description")) {
                return
            }
            val oldValue = __v_raw.description
            __v_raw.description = value
            _tRS(__v_raw, "description", oldValue, value)
        }
    override var points: Number
        get() {
            return _tRG(__v_raw, "points", __v_raw.points, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("points")) {
                return
            }
            val oldValue = __v_raw.points
            __v_raw.points = value
            _tRS(__v_raw, "points", oldValue, value)
        }
    override var status: String
        get() {
            return _tRG(__v_raw, "status", __v_raw.status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("status")) {
                return
            }
            val oldValue = __v_raw.status
            __v_raw.status = value
            _tRS(__v_raw, "status", oldValue, value)
        }
    override var progress: String?
        get() {
            return _tRG(__v_raw, "progress", __v_raw.progress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("progress")) {
                return
            }
            val oldValue = __v_raw.progress
            __v_raw.progress = value
            _tRS(__v_raw, "progress", oldValue, value)
        }
}
open class CurrentWeekSignInState (
    @JsonNotNull
    open var calendar: UTSArray<SignInDay>,
    @JsonNotNull
    open var isTodaySigned: Boolean = false,
) : UTSObject()
val SIGN_IN_LABELS = _uA(
    "周一",
    "周二",
    "周三",
    "周四",
    "周五",
    "周六",
    "周天"
) as UTSArray<String>
val padDate = fun(value: Number): String {
    return if (value < 10) {
        "0" + value
    } else {
        "" + value
    }
}
val formatDate = fun(date: Date): String {
    return "" + date.getFullYear() + "-" + padDate(date.getMonth() + 1) + "-" + padDate(date.getDate())
}
fun getCurrentWeekDates(baseDate: Date = Date()): UTSArray<String> {
    val day = baseDate.getDay()
    val diff = if (day === 0) {
        6
    } else {
        day - 1
    }
    val monday = Date(baseDate)
    monday.setDate(baseDate.getDate() - diff)
    val dates: UTSArray<String> = _uA()
    run {
        var i: Number = 0
        while(i < 7){
            val current = Date(monday)
            current.setDate(monday.getDate() + i)
            dates.push(formatDate(current))
            i++
        }
    }
    return dates
}
fun getCurrentWeekRecordSet(signInRecord: UTSArray<String>? = null, baseDate: Date = Date()): Set<String> {
    val weekDates = getCurrentWeekDates(baseDate)
    val weekDateSet = Set<String>(weekDates)
    val recordSet = Set<String>()
    if (signInRecord == null || signInRecord.length === 0) {
        return recordSet
    }
    signInRecord.forEach(fun(dateStr){
        if (weekDateSet.has(dateStr)) {
            recordSet.add(dateStr)
        }
    }
    )
    return recordSet
}
fun buildCurrentWeekSignInState(signInRecord: UTSArray<String>? = null, baseDate: Date = Date()): CurrentWeekSignInState {
    val today = formatDate(baseDate)
    val currentWeekDates = getCurrentWeekDates(baseDate)
    val signedSet = getCurrentWeekRecordSet(signInRecord, baseDate)
    val calendar = currentWeekDates.map(fun(dateStr, index): SignInDay {
        var status: String = "future"
        if (signedSet.has(dateStr)) {
            status = "signed"
        } else if (dateStr === today) {
            status = "today"
        } else if (dateStr < today) {
            status = "missed"
        }
        return SignInDay(day = dateStr, label = SIGN_IN_LABELS[index], points = 1000, status = status)
    }
    )
    return CurrentWeekSignInState(calendar = calendar, isTodaySigned = signedSet.has(today))
}
open class StorageItemValue<T> (
    @JsonNotNull
    open var value: T,
    @JsonNotNull
    open var expireTime: Any,
) : UTSObject()
open class StorageOptions (
    open var defaultExpireTime: Number? = null,
    open var storageType: String? = null,
    open var autoCleanup: Boolean? = null,
    open var cleanupInterval: Number? = null,
    open var prefix: String? = null,
    open var compress: Boolean? = null,
    open var maxSize: Number? = null,
) : UTSObject()
open class StorageResult<T> (
    @JsonNotNull
    open var success: Boolean = false,
    open var value: T? = null,
    open var error: String? = null,
    open var expired: Boolean? = null,
) : UTSObject()
val getStoragePrefix = fun(): String {
    return "miaojie_tang"
}
val DEFAULT_STORAGE_OPTIONS = StorageOptions(defaultExpireTime = 604800000, storageType = "sync", autoCleanup = true, cleanupInterval = 3600000, prefix = getStoragePrefix(), compress = false, maxSize = 10485760)
fun getCurrentTimestamp(): Number {
    return Date.now()
}
fun safeJSONParse(jsonString: String, defaultValue: Any): Any {
    try {
        val parsed = JSON.parse(jsonString)
        return if (parsed != null) {
            parsed
        } else {
            defaultValue
        }
    }
     catch (e: Throwable) {
        return defaultValue
    }
}
fun safeJSONStringify(data: Any): String {
    try {
        return JSON.stringify(data)
    }
     catch (e: Throwable) {
        return "{}"
    }
}
val DEFAULT_EXPIRE_TIME: Number = 604800000
fun resolveExpireTime(expireTime: Number?): Number {
    if (expireTime != null) {
        return expireTime
    }
    return getCurrentTimestamp() + DEFAULT_EXPIRE_TIME
}
val isItemExpired = fun(expireTime: Number): Boolean {
    if (expireTime < 0) {
        return false
    }
    return getCurrentTimestamp() > expireTime
}
fun makeStorageSuccessResult(value: Any?): StorageResult<Any?> {
    return StorageResult(success = true, value = value)
}
fun makeStorageFailureResult(error: String, expired: Boolean?): StorageResult<Any> {
    return StorageResult(success = false, error = error, expired = expired === true)
}
fun <T> setStorage(storageKey: String, value: T, expireTime: Number? = null): UTSPromise<StorageResult<T>> {
    return UTSPromise<StorageResult<T>>(fun(resolve, _reject){
        if (storageKey == "") {
            resolve(makeStorageFailureResult("无效的存储键", null) as StorageResult<T>)
            return
        }
        val item = StorageItemValue(value = value, expireTime = resolveExpireTime(if (expireTime != null) {
            expireTime
        } else {
            null
        }
        ))
        uni_setStorage(SetStorageOptions(key = storageKey, data = safeJSONStringify(item), success = fun(_){
            resolve(makeStorageSuccessResult(value as Any) as StorageResult<T>)
        }
        , fail = fun(_){
            resolve(makeStorageFailureResult("存储失败", null) as StorageResult<T>)
        }
        ))
    }
    )
}
fun <T> getStorage(storageKey: String): UTSPromise<StorageResult<T>> {
    return UTSPromise<StorageResult<T>>(fun(resolve, _reject){
        if (storageKey == "") {
            resolve(makeStorageFailureResult("无效的存储键", null) as StorageResult<T>)
            return
        }
        uni_getStorage(GetStorageOptions(key = storageKey, success = fun(res){
            val rawData = res.data
            if (rawData == null || rawData == "") {
                resolve(makeStorageFailureResult("存储键不存在", null) as StorageResult<T>)
                return
            }
            val item = safeJSONParse(rawData as String, _uO("value" to null as T, "expireTime" to 0)) as StorageItemValue<T>
            if (isItemExpired(item.expireTime as Number)) {
                uni_removeStorage(RemoveStorageOptions(key = storageKey))
                resolve(makeStorageFailureResult("数据已过期", true) as StorageResult<T>)
                return
            }
            resolve(makeStorageSuccessResult(item.value as Any) as StorageResult<T>)
        }
        , fail = fun(_){
            resolve(makeStorageFailureResult("存储键不存在", null) as StorageResult<T>)
        }
        ))
    }
    )
}
fun removeStorage(storageKey: String?): UTSPromise<StorageResult<Boolean>> {
    return UTSPromise<StorageResult<Boolean>>(fun(resolve, _reject){
        if (storageKey == null || storageKey == "") {
            uni_clearStorage(null)
            resolve(makeStorageSuccessResult(true) as StorageResult<Boolean>)
            return
        }
        uni_removeStorage(RemoveStorageOptions(key = storageKey, success = fun(_){
            resolve(makeStorageSuccessResult(true) as StorageResult<Boolean>)
        }
        , fail = fun(_){
            resolve(makeStorageFailureResult("删除失败", null) as StorageResult<Boolean>)
        }
        ))
    }
    )
}
open class TabOption (
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var key: String,
    open var badge: String? = null,
) : UTSObject()
open class RadioOption (
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var value: Any,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return RadioOptionReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class RadioOptionReactiveObject : RadioOption, IUTSReactive<RadioOption> {
    override var __v_raw: RadioOption
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: RadioOption, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(label = __v_raw.label, value = __v_raw.value) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): RadioOptionReactiveObject {
        return RadioOptionReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var value: Any
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
}
val WORK_TYPE_OPTIONS = _uA(
    TabOption(label = "全职", key = "全职", badge = null),
    TabOption(label = "兼职", key = "兼职", badge = null)
) as UTSArray<TabOption>
val SETTLE_TYPE_OPTIONS = _uA(
    TabOption(label = "日结", key = "日结", badge = null),
    TabOption(label = "周结", key = "周结", badge = null),
    TabOption(label = "月结", key = "月结", badge = null)
) as UTSArray<TabOption>
val STATUS_OPTIONS = _uA(
    RadioOption(label = "离职-随时到岗", value = "离职-随时到岗"),
    RadioOption(label = "在职-月内到岗", value = "在职-月内到岗"),
    RadioOption(label = "在职-考虑机会", value = "在职-考虑机会"),
    RadioOption(label = "在职-暂不考虑", value = "在职-暂不考虑")
) as UTSArray<RadioOption>
val EDUCATION_OPTIONS = _uA(
    RadioOption(label = "小学", value = 1),
    RadioOption(label = "初中", value = 2),
    RadioOption(label = "高中", value = 3),
    RadioOption(label = "全日制大专", value = 4),
    RadioOption(label = "全日制本科", value = 5),
    RadioOption(label = "全日制研究生", value = 6),
    RadioOption(label = "全日制硕士", value = 7),
    RadioOption(label = "全日制博士", value = 8),
    RadioOption(label = "非全日制大专", value = 9),
    RadioOption(label = "非全日制本科", value = 10),
    RadioOption(label = "非全日制研究生", value = 11),
    RadioOption(label = "非全日制硕士", value = 12),
    RadioOption(label = "非全日制博士", value = 13)
) as UTSArray<RadioOption>
val EDUCATION_OPTIONS_CN = _uA(
    RadioOption(label = "小学", value = "小学"),
    RadioOption(label = "初中", value = "初中"),
    RadioOption(label = "高中", value = "高中"),
    RadioOption(label = "全日制大专", value = "全日制大专"),
    RadioOption(label = "全日制本科", value = "全日制本科"),
    RadioOption(label = "全日制研究生", value = "全日制研究生"),
    RadioOption(label = "全日制硕士", value = "全日制硕士"),
    RadioOption(label = "全日制博士", value = "全日制博士"),
    RadioOption(label = "非全日制大专", value = "非全日制大专"),
    RadioOption(label = "非全日制本科", value = "非全日制本科"),
    RadioOption(label = "非全日制研究生", value = "非全日制研究生"),
    RadioOption(label = "非全日制硕士", value = "非全日制硕士"),
    RadioOption(label = "非全日制博士", value = "非全日制博士")
) as UTSArray<RadioOption>
open class AppStore {
    open var webConfig: GetWebConfigurationResult? = null
    open var globalMessageListenersRegistered: Boolean by Delegates.notNull()
    open lateinit var taskRules: UTSArray<String>
    open lateinit var lotteryRules: UTSArray<String>
    open lateinit var lotteryTicketRules: UTSArray<String>
    open lateinit var fetchWebConfig: () -> UTSPromise<Unit>
    open lateinit var initApp: () -> UTSPromise<Unit>
    open lateinit var setupGlobalMessageListening: () -> Unit
    open lateinit var ensureGlobalMessageConnection: () -> UTSPromise<Unit>
    constructor(webConfig: Any, globalMessageListenersRegistered: Any, taskRules: Any, lotteryRules: Any, lotteryTicketRules: Any, fetchWebConfig: () -> UTSPromise<Unit>, initApp: () -> UTSPromise<Unit>, setupGlobalMessageListening: () -> Unit, ensureGlobalMessageConnection: () -> UTSPromise<Unit>){
        this.webConfig = webConfig as GetWebConfigurationResult?
        this.globalMessageListenersRegistered = globalMessageListenersRegistered as Boolean
        this.taskRules = taskRules as UTSArray<String>
        this.lotteryRules = lotteryRules as UTSArray<String>
        this.lotteryTicketRules = lotteryTicketRules as UTSArray<String>
        this.fetchWebConfig = fetchWebConfig
        this.initApp = initApp
        this.setupGlobalMessageListening = setupGlobalMessageListening
        this.ensureGlobalMessageConnection = ensureGlobalMessageConnection
    }
}
open class GetAddressesParams (
    open var Code: String? = null,
    @JsonNotNull
    open var Types: String,
) : UTSObject()
open class UseCommunityShareReturn (
    @JsonNotNull
    open var showSharePopup: Ref<Boolean>,
    @JsonNotNull
    open var currentPost: Ref<FindBaseResponse?>,
    open var openSharePopup: (post: FindBaseResponse) -> Unit,
    open var closeSharePopup: () -> Unit,
    open var handleShareToFriend: () -> Unit,
    open var handleShareToMoments: () -> Unit,
    open var getShareData: () -> UTSJSONObject?,
    open var reportShareSuccess: (post: FindBaseResponse) -> UTSPromise<Unit>,
) : UTSObject()
val request = HttpRequest(HttpInitOptions(baseURL = getBaseUrl(), showLoading = false, autoAuth = false, timeout = 50000))
open class RoleStore {
    open lateinit var currentRole: UserRole
    open var initialized: Boolean by Delegates.notNull()
    open var roleCheck: Boolean by Delegates.notNull()
    open lateinit var initRole: () -> UTSPromise<Unit>
    open lateinit var setRole: (role: UserRole) -> UTSPromise<Unit>
    open lateinit var toggleRole: () -> UTSPromise<Unit>
    open lateinit var resetRole: () -> Unit
    constructor(currentRole: Any, initialized: Any, roleCheck: Any, initRole: () -> UTSPromise<Unit>, setRole: (role: UserRole) -> UTSPromise<Unit>, toggleRole: () -> UTSPromise<Unit>, resetRole: () -> Unit){
        this.currentRole = currentRole as UserRole
        this.initialized = initialized as Boolean
        this.roleCheck = roleCheck as Boolean
        this.initRole = initRole
        this.setRole = setRole
        this.toggleRole = toggleRole
        this.resetRole = resetRole
    }
}
open class MessageStore {
    open lateinit var params: GetMessageListParams
    open lateinit var conversationList: UTSArray<ConversationItem>
    open var noticeData: NoticeData? = null
    open var signInReminderData: SignInReminderData? = null
    open var interviewData: InterviewData? = null
    open lateinit var unreadCount: Number
    open var isRefreshing: Boolean by Delegates.notNull()
    open var isLoadingMore: Boolean by Delegates.notNull()
    open var hasMore: Boolean by Delegates.notNull()
    open lateinit var setMessageSummary: (result: GetMessageListResult) -> Unit
    open lateinit var syncMessageSummaryMeta: (result: GetMessageListResult) -> Unit
    open lateinit var appendConversationList: (list: UTSArray<ConversationItem>) -> Unit
    open lateinit var setNoticeUnreadCount: (count: Number) -> Unit
    open lateinit var setSignInUnreadCount: (count: Number) -> Unit
    open lateinit var refreshMessageData: () -> UTSPromise<Unit>
    open lateinit var loadMoreMessageData: () -> UTSPromise<Unit>
    open lateinit var clearUnreadCount: () -> Unit
    open lateinit var clearUnreadAndRefresh: () -> UTSPromise<Unit>
    open lateinit var updateUnreadCount: () -> Unit
    constructor(params: Any, conversationList: Any, noticeData: Any, signInReminderData: Any, interviewData: Any, unreadCount: Any, isRefreshing: Any, isLoadingMore: Any, hasMore: Any, setMessageSummary: (result: GetMessageListResult) -> Unit, syncMessageSummaryMeta: (result: GetMessageListResult) -> Unit, appendConversationList: (list: UTSArray<ConversationItem>) -> Unit, setNoticeUnreadCount: (count: Number) -> Unit, setSignInUnreadCount: (count: Number) -> Unit, refreshMessageData: () -> UTSPromise<Unit>, loadMoreMessageData: () -> UTSPromise<Unit>, clearUnreadCount: () -> Unit, clearUnreadAndRefresh: () -> UTSPromise<Unit>, updateUnreadCount: () -> Unit){
        this.params = params as GetMessageListParams
        this.conversationList = conversationList as UTSArray<ConversationItem>
        this.noticeData = noticeData as NoticeData?
        this.signInReminderData = signInReminderData as SignInReminderData?
        this.interviewData = interviewData as InterviewData?
        this.unreadCount = unreadCount as Number
        this.isRefreshing = isRefreshing as Boolean
        this.isLoadingMore = isLoadingMore as Boolean
        this.hasMore = hasMore as Boolean
        this.setMessageSummary = setMessageSummary
        this.syncMessageSummaryMeta = syncMessageSummaryMeta
        this.appendConversationList = appendConversationList
        this.setNoticeUnreadCount = setNoticeUnreadCount
        this.setSignInUnreadCount = setSignInUnreadCount
        this.refreshMessageData = refreshMessageData
        this.loadMoreMessageData = loadMoreMessageData
        this.clearUnreadCount = clearUnreadCount
        this.clearUnreadAndRefresh = clearUnreadAndRefresh
        this.updateUnreadCount = updateUnreadCount
    }
}
open class AuthResultBase (
    @JsonNotNull
    open var UserId: Number,
    @JsonNotNull
    open var Token: String,
    @JsonNotNull
    open var InviteCode: String,
    @JsonNotNull
    open var IsPassWord: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return AuthResultBaseReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class AuthResultBaseReactiveObject : AuthResultBase, IUTSReactive<AuthResultBase> {
    override var __v_raw: AuthResultBase
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: AuthResultBase, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(UserId = __v_raw.UserId, Token = __v_raw.Token, InviteCode = __v_raw.InviteCode, IsPassWord = __v_raw.IsPassWord) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): AuthResultBaseReactiveObject {
        return AuthResultBaseReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var UserId: Number
        get() {
            return _tRG(__v_raw, "UserId", __v_raw.UserId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UserId")) {
                return
            }
            val oldValue = __v_raw.UserId
            __v_raw.UserId = value
            _tRS(__v_raw, "UserId", oldValue, value)
        }
    override var Token: String
        get() {
            return _tRG(__v_raw, "Token", __v_raw.Token, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Token")) {
                return
            }
            val oldValue = __v_raw.Token
            __v_raw.Token = value
            _tRS(__v_raw, "Token", oldValue, value)
        }
    override var InviteCode: String
        get() {
            return _tRG(__v_raw, "InviteCode", __v_raw.InviteCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("InviteCode")) {
                return
            }
            val oldValue = __v_raw.InviteCode
            __v_raw.InviteCode = value
            _tRS(__v_raw, "InviteCode", oldValue, value)
        }
    override var IsPassWord: Number
        get() {
            return _tRG(__v_raw, "IsPassWord", __v_raw.IsPassWord, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsPassWord")) {
                return
            }
            val oldValue = __v_raw.IsPassWord
            __v_raw.IsPassWord = value
            _tRS(__v_raw, "IsPassWord", oldValue, value)
        }
}
open class GetBenefitsParamsn (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var Name: String,
) : UTSObject()
open class GetPlusMemberListParams (
    @JsonNotNull
    open var Account: String,
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var PassWord: String,
    @JsonNotNull
    open var FranchiseLevel: Number,
    @JsonNotNull
    open var ProvinceCode: String,
    open var CityCode: String? = null,
    open var AreaCode: String? = null,
    open var ProvinceName: String? = null,
    open var CityName: String? = null,
    open var DistrictName: String? = null,
) : UTSObject()
open class GetPositionListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var IsOneself: Number,
    @JsonNotNull
    open var Lat: Number,
    @JsonNotNull
    open var Lng: Number,
    @JsonNotNull
    open var HireJobStatue: Number,
    @JsonNotNull
    open var SettleWay: String,
    @JsonNotNull
    open var HireJobCode: String,
    @JsonNotNull
    open var SalaryId: Number,
    @JsonNotNull
    open var Keywords: String,
    @JsonNotNull
    open var HireCompanyId: String,
    @JsonNotNull
    open var ToMemberId: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetPositionListParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetPositionListParamsReactiveObject : GetPositionListParams, IUTSReactive<GetPositionListParams> {
    override var __v_raw: GetPositionListParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetPositionListParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, IsOneself = __v_raw.IsOneself, Lat = __v_raw.Lat, Lng = __v_raw.Lng, HireJobStatue = __v_raw.HireJobStatue, SettleWay = __v_raw.SettleWay, HireJobCode = __v_raw.HireJobCode, SalaryId = __v_raw.SalaryId, Keywords = __v_raw.Keywords, HireCompanyId = __v_raw.HireCompanyId, ToMemberId = __v_raw.ToMemberId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetPositionListParamsReactiveObject {
        return GetPositionListParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var IsOneself: Number
        get() {
            return _tRG(__v_raw, "IsOneself", __v_raw.IsOneself, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsOneself")) {
                return
            }
            val oldValue = __v_raw.IsOneself
            __v_raw.IsOneself = value
            _tRS(__v_raw, "IsOneself", oldValue, value)
        }
    override var Lat: Number
        get() {
            return _tRG(__v_raw, "Lat", __v_raw.Lat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lat")) {
                return
            }
            val oldValue = __v_raw.Lat
            __v_raw.Lat = value
            _tRS(__v_raw, "Lat", oldValue, value)
        }
    override var Lng: Number
        get() {
            return _tRG(__v_raw, "Lng", __v_raw.Lng, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lng")) {
                return
            }
            val oldValue = __v_raw.Lng
            __v_raw.Lng = value
            _tRS(__v_raw, "Lng", oldValue, value)
        }
    override var HireJobStatue: Number
        get() {
            return _tRG(__v_raw, "HireJobStatue", __v_raw.HireJobStatue, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireJobStatue")) {
                return
            }
            val oldValue = __v_raw.HireJobStatue
            __v_raw.HireJobStatue = value
            _tRS(__v_raw, "HireJobStatue", oldValue, value)
        }
    override var SettleWay: String
        get() {
            return _tRG(__v_raw, "SettleWay", __v_raw.SettleWay, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SettleWay")) {
                return
            }
            val oldValue = __v_raw.SettleWay
            __v_raw.SettleWay = value
            _tRS(__v_raw, "SettleWay", oldValue, value)
        }
    override var HireJobCode: String
        get() {
            return _tRG(__v_raw, "HireJobCode", __v_raw.HireJobCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireJobCode")) {
                return
            }
            val oldValue = __v_raw.HireJobCode
            __v_raw.HireJobCode = value
            _tRS(__v_raw, "HireJobCode", oldValue, value)
        }
    override var SalaryId: Number
        get() {
            return _tRG(__v_raw, "SalaryId", __v_raw.SalaryId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalaryId")) {
                return
            }
            val oldValue = __v_raw.SalaryId
            __v_raw.SalaryId = value
            _tRS(__v_raw, "SalaryId", oldValue, value)
        }
    override var Keywords: String
        get() {
            return _tRG(__v_raw, "Keywords", __v_raw.Keywords, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Keywords")) {
                return
            }
            val oldValue = __v_raw.Keywords
            __v_raw.Keywords = value
            _tRS(__v_raw, "Keywords", oldValue, value)
        }
    override var HireCompanyId: String
        get() {
            return _tRG(__v_raw, "HireCompanyId", __v_raw.HireCompanyId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCompanyId")) {
                return
            }
            val oldValue = __v_raw.HireCompanyId
            __v_raw.HireCompanyId = value
            _tRS(__v_raw, "HireCompanyId", oldValue, value)
        }
    override var ToMemberId: String
        get() {
            return _tRG(__v_raw, "ToMemberId", __v_raw.ToMemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ToMemberId")) {
                return
            }
            val oldValue = __v_raw.ToMemberId
            __v_raw.ToMemberId = value
            _tRS(__v_raw, "ToMemberId", oldValue, value)
        }
}
open class GetUserInfoResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var Avatar: String,
    @JsonNotNull
    open var NickName: String,
    @JsonNotNull
    open var UserRName: String,
    @JsonNotNull
    open var Sex: Number,
    @JsonNotNull
    open var Birthday: String,
    @JsonNotNull
    open var Email: String,
    @JsonNotNull
    open var Introduction: String,
    @JsonNotNull
    open var City: String,
    @JsonNotNull
    open var Wallet: Number,
    @JsonNotNull
    open var Score: Number,
    @JsonNotNull
    open var Amount: Number,
    @JsonNotNull
    open var FrozenAmount: Number,
    @JsonNotNull
    open var SumAmount: Number,
    @JsonNotNull
    open var IsSetPayPwd: Number,
    @JsonNotNull
    open var IsPlus: Boolean = false,
    @JsonNotNull
    open var PlusEndTime: String,
    @JsonNotNull
    open var ReferralCode: String,
    @JsonNotNull
    open var IsBind: Number,
    @JsonNotNull
    open var Resume: Number,
    @JsonNotNull
    open var UpLikeCount: Number,
    @JsonNotNull
    open var InterviewCount: Number,
    @JsonNotNull
    open var ViewrecordsCount: Number,
    @JsonNotNull
    open var CollCount: Number,
    @JsonNotNull
    open var IsVipPackage: Number,
    @JsonNotNull
    open var VipPackageEndTime: String,
    @JsonNotNull
    open var VipPinnedOrder: Number,
    @JsonNotNull
    open var ApplyHireJob: Number,
    @JsonNotNull
    open var OnlineId: Number,
    @JsonNotNull
    open var FreePinnedCount: Number,
    @JsonNotNull
    open var IdentityType: Number,
    @JsonNotNull
    open var Type: Number,
    @JsonNotNull
    open var IsBindWx: Number,
    @JsonNotNull
    open var SignInRecord: UTSArray<String>,
    @JsonNotNull
    open var SignInDays: Number,
    @JsonNotNull
    open var SignInday: String,
    @JsonNotNull
    open var IsSignIn: Number,
    @JsonNotNull
    open var OnlineStatue: Number,
    @JsonNotNull
    open var AuditReason: String,
    @JsonNotNull
    open var IsHide: Number,
    @JsonNotNull
    open var IsCertification: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetUserInfoResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetUserInfoResultReactiveObject : GetUserInfoResult, IUTSReactive<GetUserInfoResult> {
    override var __v_raw: GetUserInfoResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetUserInfoResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, Mobile = __v_raw.Mobile, Avatar = __v_raw.Avatar, NickName = __v_raw.NickName, UserRName = __v_raw.UserRName, Sex = __v_raw.Sex, Birthday = __v_raw.Birthday, Email = __v_raw.Email, Introduction = __v_raw.Introduction, City = __v_raw.City, Wallet = __v_raw.Wallet, Score = __v_raw.Score, Amount = __v_raw.Amount, FrozenAmount = __v_raw.FrozenAmount, SumAmount = __v_raw.SumAmount, IsSetPayPwd = __v_raw.IsSetPayPwd, IsPlus = __v_raw.IsPlus, PlusEndTime = __v_raw.PlusEndTime, ReferralCode = __v_raw.ReferralCode, IsBind = __v_raw.IsBind, Resume = __v_raw.Resume, UpLikeCount = __v_raw.UpLikeCount, InterviewCount = __v_raw.InterviewCount, ViewrecordsCount = __v_raw.ViewrecordsCount, CollCount = __v_raw.CollCount, IsVipPackage = __v_raw.IsVipPackage, VipPackageEndTime = __v_raw.VipPackageEndTime, VipPinnedOrder = __v_raw.VipPinnedOrder, ApplyHireJob = __v_raw.ApplyHireJob, OnlineId = __v_raw.OnlineId, FreePinnedCount = __v_raw.FreePinnedCount, IdentityType = __v_raw.IdentityType, Type = __v_raw.Type, IsBindWx = __v_raw.IsBindWx, SignInRecord = __v_raw.SignInRecord, SignInDays = __v_raw.SignInDays, SignInday = __v_raw.SignInday, IsSignIn = __v_raw.IsSignIn, OnlineStatue = __v_raw.OnlineStatue, AuditReason = __v_raw.AuditReason, IsHide = __v_raw.IsHide, IsCertification = __v_raw.IsCertification) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetUserInfoResultReactiveObject {
        return GetUserInfoResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var Mobile: String
        get() {
            return _tRG(__v_raw, "Mobile", __v_raw.Mobile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Mobile")) {
                return
            }
            val oldValue = __v_raw.Mobile
            __v_raw.Mobile = value
            _tRS(__v_raw, "Mobile", oldValue, value)
        }
    override var Avatar: String
        get() {
            return _tRG(__v_raw, "Avatar", __v_raw.Avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Avatar")) {
                return
            }
            val oldValue = __v_raw.Avatar
            __v_raw.Avatar = value
            _tRS(__v_raw, "Avatar", oldValue, value)
        }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
    override var UserRName: String
        get() {
            return _tRG(__v_raw, "UserRName", __v_raw.UserRName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UserRName")) {
                return
            }
            val oldValue = __v_raw.UserRName
            __v_raw.UserRName = value
            _tRS(__v_raw, "UserRName", oldValue, value)
        }
    override var Sex: Number
        get() {
            return _tRG(__v_raw, "Sex", __v_raw.Sex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Sex")) {
                return
            }
            val oldValue = __v_raw.Sex
            __v_raw.Sex = value
            _tRS(__v_raw, "Sex", oldValue, value)
        }
    override var Birthday: String
        get() {
            return _tRG(__v_raw, "Birthday", __v_raw.Birthday, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Birthday")) {
                return
            }
            val oldValue = __v_raw.Birthday
            __v_raw.Birthday = value
            _tRS(__v_raw, "Birthday", oldValue, value)
        }
    override var Email: String
        get() {
            return _tRG(__v_raw, "Email", __v_raw.Email, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Email")) {
                return
            }
            val oldValue = __v_raw.Email
            __v_raw.Email = value
            _tRS(__v_raw, "Email", oldValue, value)
        }
    override var Introduction: String
        get() {
            return _tRG(__v_raw, "Introduction", __v_raw.Introduction, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Introduction")) {
                return
            }
            val oldValue = __v_raw.Introduction
            __v_raw.Introduction = value
            _tRS(__v_raw, "Introduction", oldValue, value)
        }
    override var City: String
        get() {
            return _tRG(__v_raw, "City", __v_raw.City, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("City")) {
                return
            }
            val oldValue = __v_raw.City
            __v_raw.City = value
            _tRS(__v_raw, "City", oldValue, value)
        }
    override var Wallet: Number
        get() {
            return _tRG(__v_raw, "Wallet", __v_raw.Wallet, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Wallet")) {
                return
            }
            val oldValue = __v_raw.Wallet
            __v_raw.Wallet = value
            _tRS(__v_raw, "Wallet", oldValue, value)
        }
    override var Score: Number
        get() {
            return _tRG(__v_raw, "Score", __v_raw.Score, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Score")) {
                return
            }
            val oldValue = __v_raw.Score
            __v_raw.Score = value
            _tRS(__v_raw, "Score", oldValue, value)
        }
    override var Amount: Number
        get() {
            return _tRG(__v_raw, "Amount", __v_raw.Amount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Amount")) {
                return
            }
            val oldValue = __v_raw.Amount
            __v_raw.Amount = value
            _tRS(__v_raw, "Amount", oldValue, value)
        }
    override var FrozenAmount: Number
        get() {
            return _tRG(__v_raw, "FrozenAmount", __v_raw.FrozenAmount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FrozenAmount")) {
                return
            }
            val oldValue = __v_raw.FrozenAmount
            __v_raw.FrozenAmount = value
            _tRS(__v_raw, "FrozenAmount", oldValue, value)
        }
    override var SumAmount: Number
        get() {
            return _tRG(__v_raw, "SumAmount", __v_raw.SumAmount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SumAmount")) {
                return
            }
            val oldValue = __v_raw.SumAmount
            __v_raw.SumAmount = value
            _tRS(__v_raw, "SumAmount", oldValue, value)
        }
    override var IsSetPayPwd: Number
        get() {
            return _tRG(__v_raw, "IsSetPayPwd", __v_raw.IsSetPayPwd, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsSetPayPwd")) {
                return
            }
            val oldValue = __v_raw.IsSetPayPwd
            __v_raw.IsSetPayPwd = value
            _tRS(__v_raw, "IsSetPayPwd", oldValue, value)
        }
    override var IsPlus: Boolean
        get() {
            return _tRG(__v_raw, "IsPlus", __v_raw.IsPlus, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsPlus")) {
                return
            }
            val oldValue = __v_raw.IsPlus
            __v_raw.IsPlus = value
            _tRS(__v_raw, "IsPlus", oldValue, value)
        }
    override var PlusEndTime: String
        get() {
            return _tRG(__v_raw, "PlusEndTime", __v_raw.PlusEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PlusEndTime")) {
                return
            }
            val oldValue = __v_raw.PlusEndTime
            __v_raw.PlusEndTime = value
            _tRS(__v_raw, "PlusEndTime", oldValue, value)
        }
    override var ReferralCode: String
        get() {
            return _tRG(__v_raw, "ReferralCode", __v_raw.ReferralCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ReferralCode")) {
                return
            }
            val oldValue = __v_raw.ReferralCode
            __v_raw.ReferralCode = value
            _tRS(__v_raw, "ReferralCode", oldValue, value)
        }
    override var IsBind: Number
        get() {
            return _tRG(__v_raw, "IsBind", __v_raw.IsBind, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsBind")) {
                return
            }
            val oldValue = __v_raw.IsBind
            __v_raw.IsBind = value
            _tRS(__v_raw, "IsBind", oldValue, value)
        }
    override var Resume: Number
        get() {
            return _tRG(__v_raw, "Resume", __v_raw.Resume, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Resume")) {
                return
            }
            val oldValue = __v_raw.Resume
            __v_raw.Resume = value
            _tRS(__v_raw, "Resume", oldValue, value)
        }
    override var UpLikeCount: Number
        get() {
            return _tRG(__v_raw, "UpLikeCount", __v_raw.UpLikeCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UpLikeCount")) {
                return
            }
            val oldValue = __v_raw.UpLikeCount
            __v_raw.UpLikeCount = value
            _tRS(__v_raw, "UpLikeCount", oldValue, value)
        }
    override var InterviewCount: Number
        get() {
            return _tRG(__v_raw, "InterviewCount", __v_raw.InterviewCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("InterviewCount")) {
                return
            }
            val oldValue = __v_raw.InterviewCount
            __v_raw.InterviewCount = value
            _tRS(__v_raw, "InterviewCount", oldValue, value)
        }
    override var ViewrecordsCount: Number
        get() {
            return _tRG(__v_raw, "ViewrecordsCount", __v_raw.ViewrecordsCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ViewrecordsCount")) {
                return
            }
            val oldValue = __v_raw.ViewrecordsCount
            __v_raw.ViewrecordsCount = value
            _tRS(__v_raw, "ViewrecordsCount", oldValue, value)
        }
    override var CollCount: Number
        get() {
            return _tRG(__v_raw, "CollCount", __v_raw.CollCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CollCount")) {
                return
            }
            val oldValue = __v_raw.CollCount
            __v_raw.CollCount = value
            _tRS(__v_raw, "CollCount", oldValue, value)
        }
    override var IsVipPackage: Number
        get() {
            return _tRG(__v_raw, "IsVipPackage", __v_raw.IsVipPackage, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsVipPackage")) {
                return
            }
            val oldValue = __v_raw.IsVipPackage
            __v_raw.IsVipPackage = value
            _tRS(__v_raw, "IsVipPackage", oldValue, value)
        }
    override var VipPackageEndTime: String
        get() {
            return _tRG(__v_raw, "VipPackageEndTime", __v_raw.VipPackageEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("VipPackageEndTime")) {
                return
            }
            val oldValue = __v_raw.VipPackageEndTime
            __v_raw.VipPackageEndTime = value
            _tRS(__v_raw, "VipPackageEndTime", oldValue, value)
        }
    override var VipPinnedOrder: Number
        get() {
            return _tRG(__v_raw, "VipPinnedOrder", __v_raw.VipPinnedOrder, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("VipPinnedOrder")) {
                return
            }
            val oldValue = __v_raw.VipPinnedOrder
            __v_raw.VipPinnedOrder = value
            _tRS(__v_raw, "VipPinnedOrder", oldValue, value)
        }
    override var ApplyHireJob: Number
        get() {
            return _tRG(__v_raw, "ApplyHireJob", __v_raw.ApplyHireJob, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ApplyHireJob")) {
                return
            }
            val oldValue = __v_raw.ApplyHireJob
            __v_raw.ApplyHireJob = value
            _tRS(__v_raw, "ApplyHireJob", oldValue, value)
        }
    override var OnlineId: Number
        get() {
            return _tRG(__v_raw, "OnlineId", __v_raw.OnlineId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("OnlineId")) {
                return
            }
            val oldValue = __v_raw.OnlineId
            __v_raw.OnlineId = value
            _tRS(__v_raw, "OnlineId", oldValue, value)
        }
    override var FreePinnedCount: Number
        get() {
            return _tRG(__v_raw, "FreePinnedCount", __v_raw.FreePinnedCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FreePinnedCount")) {
                return
            }
            val oldValue = __v_raw.FreePinnedCount
            __v_raw.FreePinnedCount = value
            _tRS(__v_raw, "FreePinnedCount", oldValue, value)
        }
    override var IdentityType: Number
        get() {
            return _tRG(__v_raw, "IdentityType", __v_raw.IdentityType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IdentityType")) {
                return
            }
            val oldValue = __v_raw.IdentityType
            __v_raw.IdentityType = value
            _tRS(__v_raw, "IdentityType", oldValue, value)
        }
    override var Type: Number
        get() {
            return _tRG(__v_raw, "Type", __v_raw.Type, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Type")) {
                return
            }
            val oldValue = __v_raw.Type
            __v_raw.Type = value
            _tRS(__v_raw, "Type", oldValue, value)
        }
    override var IsBindWx: Number
        get() {
            return _tRG(__v_raw, "IsBindWx", __v_raw.IsBindWx, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsBindWx")) {
                return
            }
            val oldValue = __v_raw.IsBindWx
            __v_raw.IsBindWx = value
            _tRS(__v_raw, "IsBindWx", oldValue, value)
        }
    override var SignInRecord: UTSArray<String>
        get() {
            return _tRG(__v_raw, "SignInRecord", __v_raw.SignInRecord, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SignInRecord")) {
                return
            }
            val oldValue = __v_raw.SignInRecord
            __v_raw.SignInRecord = value
            _tRS(__v_raw, "SignInRecord", oldValue, value)
        }
    override var SignInDays: Number
        get() {
            return _tRG(__v_raw, "SignInDays", __v_raw.SignInDays, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SignInDays")) {
                return
            }
            val oldValue = __v_raw.SignInDays
            __v_raw.SignInDays = value
            _tRS(__v_raw, "SignInDays", oldValue, value)
        }
    override var SignInday: String
        get() {
            return _tRG(__v_raw, "SignInday", __v_raw.SignInday, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SignInday")) {
                return
            }
            val oldValue = __v_raw.SignInday
            __v_raw.SignInday = value
            _tRS(__v_raw, "SignInday", oldValue, value)
        }
    override var IsSignIn: Number
        get() {
            return _tRG(__v_raw, "IsSignIn", __v_raw.IsSignIn, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsSignIn")) {
                return
            }
            val oldValue = __v_raw.IsSignIn
            __v_raw.IsSignIn = value
            _tRS(__v_raw, "IsSignIn", oldValue, value)
        }
    override var OnlineStatue: Number
        get() {
            return _tRG(__v_raw, "OnlineStatue", __v_raw.OnlineStatue, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("OnlineStatue")) {
                return
            }
            val oldValue = __v_raw.OnlineStatue
            __v_raw.OnlineStatue = value
            _tRS(__v_raw, "OnlineStatue", oldValue, value)
        }
    override var AuditReason: String
        get() {
            return _tRG(__v_raw, "AuditReason", __v_raw.AuditReason, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AuditReason")) {
                return
            }
            val oldValue = __v_raw.AuditReason
            __v_raw.AuditReason = value
            _tRS(__v_raw, "AuditReason", oldValue, value)
        }
    override var IsHide: Number
        get() {
            return _tRG(__v_raw, "IsHide", __v_raw.IsHide, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsHide")) {
                return
            }
            val oldValue = __v_raw.IsHide
            __v_raw.IsHide = value
            _tRS(__v_raw, "IsHide", oldValue, value)
        }
    override var IsCertification: Number
        get() {
            return _tRG(__v_raw, "IsCertification", __v_raw.IsCertification, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsCertification")) {
                return
            }
            val oldValue = __v_raw.IsCertification
            __v_raw.IsCertification = value
            _tRS(__v_raw, "IsCertification", oldValue, value)
        }
}
open class GetOnlineResumeParams (
    @JsonNotNull
    open var Id: Number,
) : UTSObject()
open class GetMessageListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var Type: Number,
    @JsonNotNull
    open var Keywords: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetMessageListParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetMessageListParamsReactiveObject : GetMessageListParams, IUTSReactive<GetMessageListParams> {
    override var __v_raw: GetMessageListParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetMessageListParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, Type = __v_raw.Type, Keywords = __v_raw.Keywords) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetMessageListParamsReactiveObject {
        return GetMessageListParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var Type: Number
        get() {
            return _tRG(__v_raw, "Type", __v_raw.Type, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Type")) {
                return
            }
            val oldValue = __v_raw.Type
            __v_raw.Type = value
            _tRS(__v_raw, "Type", oldValue, value)
        }
    override var Keywords: String
        get() {
            return _tRG(__v_raw, "Keywords", __v_raw.Keywords, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Keywords")) {
                return
            }
            val oldValue = __v_raw.Keywords
            __v_raw.Keywords = value
            _tRS(__v_raw, "Keywords", oldValue, value)
        }
}
open class GetWinResult (
    @JsonNotNull
    open var NickName: String,
    @JsonNotNull
    open var GetPoints: Number,
    @JsonNotNull
    open var RecordType: Number,
    @JsonNotNull
    open var PrizeName: String,
    @JsonNotNull
    open var PrizePhysicalId: Number,
    @JsonNotNull
    open var ProductName: String,
    @JsonNotNull
    open var PrizeRemark: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetWinResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetWinResultReactiveObject : GetWinResult, IUTSReactive<GetWinResult> {
    override var __v_raw: GetWinResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetWinResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(NickName = __v_raw.NickName, GetPoints = __v_raw.GetPoints, RecordType = __v_raw.RecordType, PrizeName = __v_raw.PrizeName, PrizePhysicalId = __v_raw.PrizePhysicalId, ProductName = __v_raw.ProductName, PrizeRemark = __v_raw.PrizeRemark) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetWinResultReactiveObject {
        return GetWinResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
    override var GetPoints: Number
        get() {
            return _tRG(__v_raw, "GetPoints", __v_raw.GetPoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("GetPoints")) {
                return
            }
            val oldValue = __v_raw.GetPoints
            __v_raw.GetPoints = value
            _tRS(__v_raw, "GetPoints", oldValue, value)
        }
    override var RecordType: Number
        get() {
            return _tRG(__v_raw, "RecordType", __v_raw.RecordType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("RecordType")) {
                return
            }
            val oldValue = __v_raw.RecordType
            __v_raw.RecordType = value
            _tRS(__v_raw, "RecordType", oldValue, value)
        }
    override var PrizeName: String
        get() {
            return _tRG(__v_raw, "PrizeName", __v_raw.PrizeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizeName")) {
                return
            }
            val oldValue = __v_raw.PrizeName
            __v_raw.PrizeName = value
            _tRS(__v_raw, "PrizeName", oldValue, value)
        }
    override var PrizePhysicalId: Number
        get() {
            return _tRG(__v_raw, "PrizePhysicalId", __v_raw.PrizePhysicalId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizePhysicalId")) {
                return
            }
            val oldValue = __v_raw.PrizePhysicalId
            __v_raw.PrizePhysicalId = value
            _tRS(__v_raw, "PrizePhysicalId", oldValue, value)
        }
    override var ProductName: String
        get() {
            return _tRG(__v_raw, "ProductName", __v_raw.ProductName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProductName")) {
                return
            }
            val oldValue = __v_raw.ProductName
            __v_raw.ProductName = value
            _tRS(__v_raw, "ProductName", oldValue, value)
        }
    override var PrizeRemark: String
        get() {
            return _tRG(__v_raw, "PrizeRemark", __v_raw.PrizeRemark, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizeRemark")) {
                return
            }
            val oldValue = __v_raw.PrizeRemark
            __v_raw.PrizeRemark = value
            _tRS(__v_raw, "PrizeRemark", oldValue, value)
        }
}
open class LoginParams (
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var PassWord: String,
    open var Vcode: String? = null,
    @JsonNotNull
    open var loginType: String,
) : UTSObject()
open class SignalRIncomingMessage (
    open var type: Number? = null,
    open var target: String? = null,
    open var `arguments`: Any? = null,
    open var error: String? = null,
) : UTSObject()
open class InitStore {
    open var initialized: Boolean by Delegates.notNull()
    open var loading: Boolean by Delegates.notNull()
    open lateinit var error: String
    open var isReady: Boolean by Delegates.notNull()
    open var isFailed: Boolean by Delegates.notNull()
    open lateinit var bootstrap: () -> UTSPromise<Unit>
    open lateinit var retry: () -> UTSPromise<Unit>
    open lateinit var reset: () -> Unit
    constructor(initialized: Any, loading: Any, error: Any, isReady: Any, isFailed: Any, bootstrap: () -> UTSPromise<Unit>, retry: () -> UTSPromise<Unit>, reset: () -> Unit){
        this.initialized = initialized as Boolean
        this.loading = loading as Boolean
        this.error = error as String
        this.isReady = isReady as Boolean
        this.isFailed = isFailed as Boolean
        this.bootstrap = bootstrap
        this.retry = retry
        this.reset = reset
    }
}
open class JoinFormData (
    @JsonNotNull
    open var Account: String,
    @JsonNotNull
    open var PassWord: String,
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var FranchiseLevel: Number,
    @JsonNotNull
    open var ProvinceCode: String,
    open var CityCode: String? = null,
    open var AreaCode: String? = null,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var cascadeAddressCodes: Any,
    @JsonNotNull
    open var ProvinceName: String,
    @JsonNotNull
    open var CityName: String,
    @JsonNotNull
    open var DistrictName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return JoinFormDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class JoinFormDataReactiveObject : JoinFormData, IUTSReactive<JoinFormData> {
    override var __v_raw: JoinFormData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: JoinFormData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Account = __v_raw.Account, PassWord = __v_raw.PassWord, Mobile = __v_raw.Mobile, FranchiseLevel = __v_raw.FranchiseLevel, ProvinceCode = __v_raw.ProvinceCode, CityCode = __v_raw.CityCode, AreaCode = __v_raw.AreaCode, name = __v_raw.name, cascadeAddressCodes = __v_raw.cascadeAddressCodes, ProvinceName = __v_raw.ProvinceName, CityName = __v_raw.CityName, DistrictName = __v_raw.DistrictName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): JoinFormDataReactiveObject {
        return JoinFormDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Account: String
        get() {
            return _tRG(__v_raw, "Account", __v_raw.Account, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Account")) {
                return
            }
            val oldValue = __v_raw.Account
            __v_raw.Account = value
            _tRS(__v_raw, "Account", oldValue, value)
        }
    override var PassWord: String
        get() {
            return _tRG(__v_raw, "PassWord", __v_raw.PassWord, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PassWord")) {
                return
            }
            val oldValue = __v_raw.PassWord
            __v_raw.PassWord = value
            _tRS(__v_raw, "PassWord", oldValue, value)
        }
    override var Mobile: String
        get() {
            return _tRG(__v_raw, "Mobile", __v_raw.Mobile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Mobile")) {
                return
            }
            val oldValue = __v_raw.Mobile
            __v_raw.Mobile = value
            _tRS(__v_raw, "Mobile", oldValue, value)
        }
    override var FranchiseLevel: Number
        get() {
            return _tRG(__v_raw, "FranchiseLevel", __v_raw.FranchiseLevel, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FranchiseLevel")) {
                return
            }
            val oldValue = __v_raw.FranchiseLevel
            __v_raw.FranchiseLevel = value
            _tRS(__v_raw, "FranchiseLevel", oldValue, value)
        }
    override var ProvinceCode: String
        get() {
            return _tRG(__v_raw, "ProvinceCode", __v_raw.ProvinceCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceCode")) {
                return
            }
            val oldValue = __v_raw.ProvinceCode
            __v_raw.ProvinceCode = value
            _tRS(__v_raw, "ProvinceCode", oldValue, value)
        }
    override var CityCode: String?
        get() {
            return _tRG(__v_raw, "CityCode", __v_raw.CityCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCode")) {
                return
            }
            val oldValue = __v_raw.CityCode
            __v_raw.CityCode = value
            _tRS(__v_raw, "CityCode", oldValue, value)
        }
    override var AreaCode: String?
        get() {
            return _tRG(__v_raw, "AreaCode", __v_raw.AreaCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AreaCode")) {
                return
            }
            val oldValue = __v_raw.AreaCode
            __v_raw.AreaCode = value
            _tRS(__v_raw, "AreaCode", oldValue, value)
        }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var cascadeAddressCodes: Any
        get() {
            return _tRG(__v_raw, "cascadeAddressCodes", __v_raw.cascadeAddressCodes, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("cascadeAddressCodes")) {
                return
            }
            val oldValue = __v_raw.cascadeAddressCodes
            __v_raw.cascadeAddressCodes = value
            _tRS(__v_raw, "cascadeAddressCodes", oldValue, value)
        }
    override var ProvinceName: String
        get() {
            return _tRG(__v_raw, "ProvinceName", __v_raw.ProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceName")) {
                return
            }
            val oldValue = __v_raw.ProvinceName
            __v_raw.ProvinceName = value
            _tRS(__v_raw, "ProvinceName", oldValue, value)
        }
    override var CityName: String
        get() {
            return _tRG(__v_raw, "CityName", __v_raw.CityName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityName")) {
                return
            }
            val oldValue = __v_raw.CityName
            __v_raw.CityName = value
            _tRS(__v_raw, "CityName", oldValue, value)
        }
    override var DistrictName: String
        get() {
            return _tRG(__v_raw, "DistrictName", __v_raw.DistrictName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("DistrictName")) {
                return
            }
            val oldValue = __v_raw.DistrictName
            __v_raw.DistrictName = value
            _tRS(__v_raw, "DistrictName", oldValue, value)
        }
}
open class CustomModule (
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var icon: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return CustomModuleReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class CustomModuleReactiveObject : CustomModule, IUTSReactive<CustomModule> {
    override var __v_raw: CustomModule
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: CustomModule, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(name = __v_raw.name, icon = __v_raw.icon) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): CustomModuleReactiveObject {
        return CustomModuleReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
}
open class AddressOption (
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var value: String,
    open var disabled: Boolean? = null,
    open var children: UTSArray<AddressOption>? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return AddressOptionReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class AddressOptionReactiveObject : AddressOption, IUTSReactive<AddressOption> {
    override var __v_raw: AddressOption
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: AddressOption, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(label = __v_raw.label, value = __v_raw.value, disabled = __v_raw.disabled, children = __v_raw.children) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): AddressOptionReactiveObject {
        return AddressOptionReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var value: String
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
    override var disabled: Boolean?
        get() {
            return _tRG(__v_raw, "disabled", __v_raw.disabled, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("disabled")) {
                return
            }
            val oldValue = __v_raw.disabled
            __v_raw.disabled = value
            _tRS(__v_raw, "disabled", oldValue, value)
        }
    override var children: UTSArray<AddressOption>?
        get() {
            return _tRG(__v_raw, "children", __v_raw.children, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("children")) {
                return
            }
            val oldValue = __v_raw.children
            __v_raw.children = value
            _tRS(__v_raw, "children", oldValue, value)
        }
}
open class GetWebConfigurationResult (
    @JsonNotNull
    open var SEOTitle: String,
    @JsonNotNull
    open var SEOKeywords: String,
    @JsonNotNull
    open var SEODescription: String,
    @JsonNotNull
    open var WebName: String,
    @JsonNotNull
    open var WebUrl: String,
    @JsonNotNull
    open var WebCompany: String,
    @JsonNotNull
    open var WebAddress: String,
    @JsonNotNull
    open var WebDescription: String,
    @JsonNotNull
    open var Copyright: String,
    @JsonNotNull
    open var WebStatus: Number,
    @JsonNotNull
    open var WebLogo: String,
    @JsonNotNull
    open var WebQQ: String,
    @JsonNotNull
    open var WeChat: String,
    @JsonNotNull
    open var WebTel: String,
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var SalesHotLine: String,
    @JsonNotNull
    open var WebFax: String,
    @JsonNotNull
    open var Email: String,
    @JsonNotNull
    open var BusinessHours: String,
    @JsonNotNull
    open var AddressTest: String,
    @JsonNotNull
    open var VersionNo: String,
    @JsonNotNull
    open var SwitchSeconds: Number,
    @JsonNotNull
    open var MsgIds: UTSArray<String>,
    @JsonNotNull
    open var IsOpenSms: Number,
    @JsonNotNull
    open var PrizePoints: Number,
    @JsonNotNull
    open var PrizeCouponPoints: Number,
    @JsonNotNull
    open var MaxExchangeCash: Number,
    @JsonNotNull
    open var PointsComboRule: String,
    @JsonNotNull
    open var PointsRule: String,
    @JsonNotNull
    open var TaskRule: String,
    @JsonNotNull
    open var LotteryRule: String,
    @JsonNotNull
    open var LotteryTicketRule: String,
    @JsonNotNull
    open var GetResumePoints: Number,
    @JsonNotNull
    open var GetMobilePoints: Number,
    @JsonNotNull
    open var GetWeChatPoints: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetWebConfigurationResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetWebConfigurationResultReactiveObject : GetWebConfigurationResult, IUTSReactive<GetWebConfigurationResult> {
    override var __v_raw: GetWebConfigurationResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetWebConfigurationResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(SEOTitle = __v_raw.SEOTitle, SEOKeywords = __v_raw.SEOKeywords, SEODescription = __v_raw.SEODescription, WebName = __v_raw.WebName, WebUrl = __v_raw.WebUrl, WebCompany = __v_raw.WebCompany, WebAddress = __v_raw.WebAddress, WebDescription = __v_raw.WebDescription, Copyright = __v_raw.Copyright, WebStatus = __v_raw.WebStatus, WebLogo = __v_raw.WebLogo, WebQQ = __v_raw.WebQQ, WeChat = __v_raw.WeChat, WebTel = __v_raw.WebTel, Mobile = __v_raw.Mobile, SalesHotLine = __v_raw.SalesHotLine, WebFax = __v_raw.WebFax, Email = __v_raw.Email, BusinessHours = __v_raw.BusinessHours, AddressTest = __v_raw.AddressTest, VersionNo = __v_raw.VersionNo, SwitchSeconds = __v_raw.SwitchSeconds, MsgIds = __v_raw.MsgIds, IsOpenSms = __v_raw.IsOpenSms, PrizePoints = __v_raw.PrizePoints, PrizeCouponPoints = __v_raw.PrizeCouponPoints, MaxExchangeCash = __v_raw.MaxExchangeCash, PointsComboRule = __v_raw.PointsComboRule, PointsRule = __v_raw.PointsRule, TaskRule = __v_raw.TaskRule, LotteryRule = __v_raw.LotteryRule, LotteryTicketRule = __v_raw.LotteryTicketRule, GetResumePoints = __v_raw.GetResumePoints, GetMobilePoints = __v_raw.GetMobilePoints, GetWeChatPoints = __v_raw.GetWeChatPoints) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetWebConfigurationResultReactiveObject {
        return GetWebConfigurationResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var SEOTitle: String
        get() {
            return _tRG(__v_raw, "SEOTitle", __v_raw.SEOTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SEOTitle")) {
                return
            }
            val oldValue = __v_raw.SEOTitle
            __v_raw.SEOTitle = value
            _tRS(__v_raw, "SEOTitle", oldValue, value)
        }
    override var SEOKeywords: String
        get() {
            return _tRG(__v_raw, "SEOKeywords", __v_raw.SEOKeywords, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SEOKeywords")) {
                return
            }
            val oldValue = __v_raw.SEOKeywords
            __v_raw.SEOKeywords = value
            _tRS(__v_raw, "SEOKeywords", oldValue, value)
        }
    override var SEODescription: String
        get() {
            return _tRG(__v_raw, "SEODescription", __v_raw.SEODescription, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SEODescription")) {
                return
            }
            val oldValue = __v_raw.SEODescription
            __v_raw.SEODescription = value
            _tRS(__v_raw, "SEODescription", oldValue, value)
        }
    override var WebName: String
        get() {
            return _tRG(__v_raw, "WebName", __v_raw.WebName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebName")) {
                return
            }
            val oldValue = __v_raw.WebName
            __v_raw.WebName = value
            _tRS(__v_raw, "WebName", oldValue, value)
        }
    override var WebUrl: String
        get() {
            return _tRG(__v_raw, "WebUrl", __v_raw.WebUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebUrl")) {
                return
            }
            val oldValue = __v_raw.WebUrl
            __v_raw.WebUrl = value
            _tRS(__v_raw, "WebUrl", oldValue, value)
        }
    override var WebCompany: String
        get() {
            return _tRG(__v_raw, "WebCompany", __v_raw.WebCompany, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebCompany")) {
                return
            }
            val oldValue = __v_raw.WebCompany
            __v_raw.WebCompany = value
            _tRS(__v_raw, "WebCompany", oldValue, value)
        }
    override var WebAddress: String
        get() {
            return _tRG(__v_raw, "WebAddress", __v_raw.WebAddress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebAddress")) {
                return
            }
            val oldValue = __v_raw.WebAddress
            __v_raw.WebAddress = value
            _tRS(__v_raw, "WebAddress", oldValue, value)
        }
    override var WebDescription: String
        get() {
            return _tRG(__v_raw, "WebDescription", __v_raw.WebDescription, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebDescription")) {
                return
            }
            val oldValue = __v_raw.WebDescription
            __v_raw.WebDescription = value
            _tRS(__v_raw, "WebDescription", oldValue, value)
        }
    override var Copyright: String
        get() {
            return _tRG(__v_raw, "Copyright", __v_raw.Copyright, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Copyright")) {
                return
            }
            val oldValue = __v_raw.Copyright
            __v_raw.Copyright = value
            _tRS(__v_raw, "Copyright", oldValue, value)
        }
    override var WebStatus: Number
        get() {
            return _tRG(__v_raw, "WebStatus", __v_raw.WebStatus, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebStatus")) {
                return
            }
            val oldValue = __v_raw.WebStatus
            __v_raw.WebStatus = value
            _tRS(__v_raw, "WebStatus", oldValue, value)
        }
    override var WebLogo: String
        get() {
            return _tRG(__v_raw, "WebLogo", __v_raw.WebLogo, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebLogo")) {
                return
            }
            val oldValue = __v_raw.WebLogo
            __v_raw.WebLogo = value
            _tRS(__v_raw, "WebLogo", oldValue, value)
        }
    override var WebQQ: String
        get() {
            return _tRG(__v_raw, "WebQQ", __v_raw.WebQQ, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebQQ")) {
                return
            }
            val oldValue = __v_raw.WebQQ
            __v_raw.WebQQ = value
            _tRS(__v_raw, "WebQQ", oldValue, value)
        }
    override var WeChat: String
        get() {
            return _tRG(__v_raw, "WeChat", __v_raw.WeChat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WeChat")) {
                return
            }
            val oldValue = __v_raw.WeChat
            __v_raw.WeChat = value
            _tRS(__v_raw, "WeChat", oldValue, value)
        }
    override var WebTel: String
        get() {
            return _tRG(__v_raw, "WebTel", __v_raw.WebTel, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebTel")) {
                return
            }
            val oldValue = __v_raw.WebTel
            __v_raw.WebTel = value
            _tRS(__v_raw, "WebTel", oldValue, value)
        }
    override var Mobile: String
        get() {
            return _tRG(__v_raw, "Mobile", __v_raw.Mobile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Mobile")) {
                return
            }
            val oldValue = __v_raw.Mobile
            __v_raw.Mobile = value
            _tRS(__v_raw, "Mobile", oldValue, value)
        }
    override var SalesHotLine: String
        get() {
            return _tRG(__v_raw, "SalesHotLine", __v_raw.SalesHotLine, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalesHotLine")) {
                return
            }
            val oldValue = __v_raw.SalesHotLine
            __v_raw.SalesHotLine = value
            _tRS(__v_raw, "SalesHotLine", oldValue, value)
        }
    override var WebFax: String
        get() {
            return _tRG(__v_raw, "WebFax", __v_raw.WebFax, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WebFax")) {
                return
            }
            val oldValue = __v_raw.WebFax
            __v_raw.WebFax = value
            _tRS(__v_raw, "WebFax", oldValue, value)
        }
    override var Email: String
        get() {
            return _tRG(__v_raw, "Email", __v_raw.Email, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Email")) {
                return
            }
            val oldValue = __v_raw.Email
            __v_raw.Email = value
            _tRS(__v_raw, "Email", oldValue, value)
        }
    override var BusinessHours: String
        get() {
            return _tRG(__v_raw, "BusinessHours", __v_raw.BusinessHours, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("BusinessHours")) {
                return
            }
            val oldValue = __v_raw.BusinessHours
            __v_raw.BusinessHours = value
            _tRS(__v_raw, "BusinessHours", oldValue, value)
        }
    override var AddressTest: String
        get() {
            return _tRG(__v_raw, "AddressTest", __v_raw.AddressTest, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddressTest")) {
                return
            }
            val oldValue = __v_raw.AddressTest
            __v_raw.AddressTest = value
            _tRS(__v_raw, "AddressTest", oldValue, value)
        }
    override var VersionNo: String
        get() {
            return _tRG(__v_raw, "VersionNo", __v_raw.VersionNo, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("VersionNo")) {
                return
            }
            val oldValue = __v_raw.VersionNo
            __v_raw.VersionNo = value
            _tRS(__v_raw, "VersionNo", oldValue, value)
        }
    override var SwitchSeconds: Number
        get() {
            return _tRG(__v_raw, "SwitchSeconds", __v_raw.SwitchSeconds, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SwitchSeconds")) {
                return
            }
            val oldValue = __v_raw.SwitchSeconds
            __v_raw.SwitchSeconds = value
            _tRS(__v_raw, "SwitchSeconds", oldValue, value)
        }
    override var MsgIds: UTSArray<String>
        get() {
            return _tRG(__v_raw, "MsgIds", __v_raw.MsgIds, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MsgIds")) {
                return
            }
            val oldValue = __v_raw.MsgIds
            __v_raw.MsgIds = value
            _tRS(__v_raw, "MsgIds", oldValue, value)
        }
    override var IsOpenSms: Number
        get() {
            return _tRG(__v_raw, "IsOpenSms", __v_raw.IsOpenSms, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsOpenSms")) {
                return
            }
            val oldValue = __v_raw.IsOpenSms
            __v_raw.IsOpenSms = value
            _tRS(__v_raw, "IsOpenSms", oldValue, value)
        }
    override var PrizePoints: Number
        get() {
            return _tRG(__v_raw, "PrizePoints", __v_raw.PrizePoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizePoints")) {
                return
            }
            val oldValue = __v_raw.PrizePoints
            __v_raw.PrizePoints = value
            _tRS(__v_raw, "PrizePoints", oldValue, value)
        }
    override var PrizeCouponPoints: Number
        get() {
            return _tRG(__v_raw, "PrizeCouponPoints", __v_raw.PrizeCouponPoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizeCouponPoints")) {
                return
            }
            val oldValue = __v_raw.PrizeCouponPoints
            __v_raw.PrizeCouponPoints = value
            _tRS(__v_raw, "PrizeCouponPoints", oldValue, value)
        }
    override var MaxExchangeCash: Number
        get() {
            return _tRG(__v_raw, "MaxExchangeCash", __v_raw.MaxExchangeCash, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MaxExchangeCash")) {
                return
            }
            val oldValue = __v_raw.MaxExchangeCash
            __v_raw.MaxExchangeCash = value
            _tRS(__v_raw, "MaxExchangeCash", oldValue, value)
        }
    override var PointsComboRule: String
        get() {
            return _tRG(__v_raw, "PointsComboRule", __v_raw.PointsComboRule, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PointsComboRule")) {
                return
            }
            val oldValue = __v_raw.PointsComboRule
            __v_raw.PointsComboRule = value
            _tRS(__v_raw, "PointsComboRule", oldValue, value)
        }
    override var PointsRule: String
        get() {
            return _tRG(__v_raw, "PointsRule", __v_raw.PointsRule, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PointsRule")) {
                return
            }
            val oldValue = __v_raw.PointsRule
            __v_raw.PointsRule = value
            _tRS(__v_raw, "PointsRule", oldValue, value)
        }
    override var TaskRule: String
        get() {
            return _tRG(__v_raw, "TaskRule", __v_raw.TaskRule, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("TaskRule")) {
                return
            }
            val oldValue = __v_raw.TaskRule
            __v_raw.TaskRule = value
            _tRS(__v_raw, "TaskRule", oldValue, value)
        }
    override var LotteryRule: String
        get() {
            return _tRG(__v_raw, "LotteryRule", __v_raw.LotteryRule, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LotteryRule")) {
                return
            }
            val oldValue = __v_raw.LotteryRule
            __v_raw.LotteryRule = value
            _tRS(__v_raw, "LotteryRule", oldValue, value)
        }
    override var LotteryTicketRule: String
        get() {
            return _tRG(__v_raw, "LotteryTicketRule", __v_raw.LotteryTicketRule, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LotteryTicketRule")) {
                return
            }
            val oldValue = __v_raw.LotteryTicketRule
            __v_raw.LotteryTicketRule = value
            _tRS(__v_raw, "LotteryTicketRule", oldValue, value)
        }
    override var GetResumePoints: Number
        get() {
            return _tRG(__v_raw, "GetResumePoints", __v_raw.GetResumePoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("GetResumePoints")) {
                return
            }
            val oldValue = __v_raw.GetResumePoints
            __v_raw.GetResumePoints = value
            _tRS(__v_raw, "GetResumePoints", oldValue, value)
        }
    override var GetMobilePoints: Number
        get() {
            return _tRG(__v_raw, "GetMobilePoints", __v_raw.GetMobilePoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("GetMobilePoints")) {
                return
            }
            val oldValue = __v_raw.GetMobilePoints
            __v_raw.GetMobilePoints = value
            _tRS(__v_raw, "GetMobilePoints", oldValue, value)
        }
    override var GetWeChatPoints: Number
        get() {
            return _tRG(__v_raw, "GetWeChatPoints", __v_raw.GetWeChatPoints, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("GetWeChatPoints")) {
                return
            }
            val oldValue = __v_raw.GetWeChatPoints
            __v_raw.GetWeChatPoints = value
            _tRS(__v_raw, "GetWeChatPoints", oldValue, value)
        }
}
enum class Api__1(override val value: String) : UTSEnumString {
    GET_REGISTER_PROTOCOL("/api/System/GetUserNotice"),
    GET_PRIVACY_POLICY("/api/System/GetUserAuth"),
    GET_USER_PROTOCOL("/api/System/GetServiceTerms"),
    GET_SYSTEM_CONFIG("/api/System/GetWebConfiguration"),
    GET_FEEDBACK_TYPE("/api/User/FeedBackType"),
    POST_FEEDBACK("/api/User/MemberFeedBack"),
    GET_REPORT_REASON("/api/User/ReportReason"),
    GET_REPORT("/api/User/SubmitReport"),
    GET_REPORT_LIST("/api/User/GetMyReport"),
    GET_SUBSCRIBE_TEMPLATE("/api/System/SubList"),
    GET_SUBSCRIBE_OPERATION("/api/System/SubOp")
}
val getWebConfiguration = fun(): UTSPromise<GetWebConfigurationResult> {
    return request.post<GetWebConfigurationResult>(Api__1.GET_SYSTEM_CONFIG)
}
open class ConversationItem (
    open var ToMemberId: String? = null,
    open var ToMemberAvatar: String? = null,
    open var ToMemberNickName: String? = null,
    open var UnreadCount: Number? = null,
    open var MessageType: Number? = null,
    open var Content: String? = null,
    open var AddTime: String? = null,
    open var UpdateTime: String? = null,
    open var IsTop: Number? = null,
    open var HireJobId: Number? = null,
    open var IsGrade: Number? = null,
    open var IsInterview: Number? = null,
    open var IsInterviewResult: Number? = null,
    open var InterviewId: Number? = null,
    open var IsRight: Number? = null,
    open var IsSendResume: Number? = null,
    @JsonNotNull
    open var InterviewResult: Number,
    @JsonNotNull
    open var OperationInterview: String,
    @JsonNotNull
    open var SendInterviewResult: String,
    @JsonNotNull
    open var SendMesId: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ConversationItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ConversationItemReactiveObject : ConversationItem, IUTSReactive<ConversationItem> {
    override var __v_raw: ConversationItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ConversationItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(ToMemberId = __v_raw.ToMemberId, ToMemberAvatar = __v_raw.ToMemberAvatar, ToMemberNickName = __v_raw.ToMemberNickName, UnreadCount = __v_raw.UnreadCount, MessageType = __v_raw.MessageType, Content = __v_raw.Content, AddTime = __v_raw.AddTime, UpdateTime = __v_raw.UpdateTime, IsTop = __v_raw.IsTop, HireJobId = __v_raw.HireJobId, IsGrade = __v_raw.IsGrade, IsInterview = __v_raw.IsInterview, IsInterviewResult = __v_raw.IsInterviewResult, InterviewId = __v_raw.InterviewId, IsRight = __v_raw.IsRight, IsSendResume = __v_raw.IsSendResume, InterviewResult = __v_raw.InterviewResult, OperationInterview = __v_raw.OperationInterview, SendInterviewResult = __v_raw.SendInterviewResult, SendMesId = __v_raw.SendMesId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ConversationItemReactiveObject {
        return ConversationItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var ToMemberId: String?
        get() {
            return _tRG(__v_raw, "ToMemberId", __v_raw.ToMemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ToMemberId")) {
                return
            }
            val oldValue = __v_raw.ToMemberId
            __v_raw.ToMemberId = value
            _tRS(__v_raw, "ToMemberId", oldValue, value)
        }
    override var ToMemberAvatar: String?
        get() {
            return _tRG(__v_raw, "ToMemberAvatar", __v_raw.ToMemberAvatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ToMemberAvatar")) {
                return
            }
            val oldValue = __v_raw.ToMemberAvatar
            __v_raw.ToMemberAvatar = value
            _tRS(__v_raw, "ToMemberAvatar", oldValue, value)
        }
    override var ToMemberNickName: String?
        get() {
            return _tRG(__v_raw, "ToMemberNickName", __v_raw.ToMemberNickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ToMemberNickName")) {
                return
            }
            val oldValue = __v_raw.ToMemberNickName
            __v_raw.ToMemberNickName = value
            _tRS(__v_raw, "ToMemberNickName", oldValue, value)
        }
    override var UnreadCount: Number?
        get() {
            return _tRG(__v_raw, "UnreadCount", __v_raw.UnreadCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UnreadCount")) {
                return
            }
            val oldValue = __v_raw.UnreadCount
            __v_raw.UnreadCount = value
            _tRS(__v_raw, "UnreadCount", oldValue, value)
        }
    override var MessageType: Number?
        get() {
            return _tRG(__v_raw, "MessageType", __v_raw.MessageType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MessageType")) {
                return
            }
            val oldValue = __v_raw.MessageType
            __v_raw.MessageType = value
            _tRS(__v_raw, "MessageType", oldValue, value)
        }
    override var Content: String?
        get() {
            return _tRG(__v_raw, "Content", __v_raw.Content, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Content")) {
                return
            }
            val oldValue = __v_raw.Content
            __v_raw.Content = value
            _tRS(__v_raw, "Content", oldValue, value)
        }
    override var AddTime: String?
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var UpdateTime: String?
        get() {
            return _tRG(__v_raw, "UpdateTime", __v_raw.UpdateTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UpdateTime")) {
                return
            }
            val oldValue = __v_raw.UpdateTime
            __v_raw.UpdateTime = value
            _tRS(__v_raw, "UpdateTime", oldValue, value)
        }
    override var IsTop: Number?
        get() {
            return _tRG(__v_raw, "IsTop", __v_raw.IsTop, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsTop")) {
                return
            }
            val oldValue = __v_raw.IsTop
            __v_raw.IsTop = value
            _tRS(__v_raw, "IsTop", oldValue, value)
        }
    override var HireJobId: Number?
        get() {
            return _tRG(__v_raw, "HireJobId", __v_raw.HireJobId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireJobId")) {
                return
            }
            val oldValue = __v_raw.HireJobId
            __v_raw.HireJobId = value
            _tRS(__v_raw, "HireJobId", oldValue, value)
        }
    override var IsGrade: Number?
        get() {
            return _tRG(__v_raw, "IsGrade", __v_raw.IsGrade, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsGrade")) {
                return
            }
            val oldValue = __v_raw.IsGrade
            __v_raw.IsGrade = value
            _tRS(__v_raw, "IsGrade", oldValue, value)
        }
    override var IsInterview: Number?
        get() {
            return _tRG(__v_raw, "IsInterview", __v_raw.IsInterview, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsInterview")) {
                return
            }
            val oldValue = __v_raw.IsInterview
            __v_raw.IsInterview = value
            _tRS(__v_raw, "IsInterview", oldValue, value)
        }
    override var IsInterviewResult: Number?
        get() {
            return _tRG(__v_raw, "IsInterviewResult", __v_raw.IsInterviewResult, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsInterviewResult")) {
                return
            }
            val oldValue = __v_raw.IsInterviewResult
            __v_raw.IsInterviewResult = value
            _tRS(__v_raw, "IsInterviewResult", oldValue, value)
        }
    override var InterviewId: Number?
        get() {
            return _tRG(__v_raw, "InterviewId", __v_raw.InterviewId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("InterviewId")) {
                return
            }
            val oldValue = __v_raw.InterviewId
            __v_raw.InterviewId = value
            _tRS(__v_raw, "InterviewId", oldValue, value)
        }
    override var IsRight: Number?
        get() {
            return _tRG(__v_raw, "IsRight", __v_raw.IsRight, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsRight")) {
                return
            }
            val oldValue = __v_raw.IsRight
            __v_raw.IsRight = value
            _tRS(__v_raw, "IsRight", oldValue, value)
        }
    override var IsSendResume: Number?
        get() {
            return _tRG(__v_raw, "IsSendResume", __v_raw.IsSendResume, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsSendResume")) {
                return
            }
            val oldValue = __v_raw.IsSendResume
            __v_raw.IsSendResume = value
            _tRS(__v_raw, "IsSendResume", oldValue, value)
        }
    override var InterviewResult: Number
        get() {
            return _tRG(__v_raw, "InterviewResult", __v_raw.InterviewResult, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("InterviewResult")) {
                return
            }
            val oldValue = __v_raw.InterviewResult
            __v_raw.InterviewResult = value
            _tRS(__v_raw, "InterviewResult", oldValue, value)
        }
    override var OperationInterview: String
        get() {
            return _tRG(__v_raw, "OperationInterview", __v_raw.OperationInterview, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("OperationInterview")) {
                return
            }
            val oldValue = __v_raw.OperationInterview
            __v_raw.OperationInterview = value
            _tRS(__v_raw, "OperationInterview", oldValue, value)
        }
    override var SendInterviewResult: String
        get() {
            return _tRG(__v_raw, "SendInterviewResult", __v_raw.SendInterviewResult, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SendInterviewResult")) {
                return
            }
            val oldValue = __v_raw.SendInterviewResult
            __v_raw.SendInterviewResult = value
            _tRS(__v_raw, "SendInterviewResult", oldValue, value)
        }
    override var SendMesId: Number
        get() {
            return _tRG(__v_raw, "SendMesId", __v_raw.SendMesId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SendMesId")) {
                return
            }
            val oldValue = __v_raw.SendMesId
            __v_raw.SendMesId = value
            _tRS(__v_raw, "SendMesId", oldValue, value)
        }
}
open class NoticeData (
    @JsonNotNull
    open var NoticeCountN: Number,
    @JsonNotNull
    open var PubTime: String,
    @JsonNotNull
    open var Title: String,
    open var IsRead: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return NoticeDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class NoticeDataReactiveObject : NoticeData, IUTSReactive<NoticeData> {
    override var __v_raw: NoticeData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: NoticeData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(NoticeCountN = __v_raw.NoticeCountN, PubTime = __v_raw.PubTime, Title = __v_raw.Title, IsRead = __v_raw.IsRead) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): NoticeDataReactiveObject {
        return NoticeDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var NoticeCountN: Number
        get() {
            return _tRG(__v_raw, "NoticeCountN", __v_raw.NoticeCountN, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NoticeCountN")) {
                return
            }
            val oldValue = __v_raw.NoticeCountN
            __v_raw.NoticeCountN = value
            _tRS(__v_raw, "NoticeCountN", oldValue, value)
        }
    override var PubTime: String
        get() {
            return _tRG(__v_raw, "PubTime", __v_raw.PubTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PubTime")) {
                return
            }
            val oldValue = __v_raw.PubTime
            __v_raw.PubTime = value
            _tRS(__v_raw, "PubTime", oldValue, value)
        }
    override var Title: String
        get() {
            return _tRG(__v_raw, "Title", __v_raw.Title, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Title")) {
                return
            }
            val oldValue = __v_raw.Title
            __v_raw.Title = value
            _tRS(__v_raw, "Title", oldValue, value)
        }
    override var IsRead: Number?
        get() {
            return _tRG(__v_raw, "IsRead", __v_raw.IsRead, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsRead")) {
                return
            }
            val oldValue = __v_raw.IsRead
            __v_raw.IsRead = value
            _tRS(__v_raw, "IsRead", oldValue, value)
        }
}
open class SignInReminderData (
    @JsonNotNull
    open var Count: Number,
    @JsonNotNull
    open var AddTime: String,
    @JsonNotNull
    open var ContentRemark: String,
    @JsonNotNull
    open var IsRead: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return SignInReminderDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class SignInReminderDataReactiveObject : SignInReminderData, IUTSReactive<SignInReminderData> {
    override var __v_raw: SignInReminderData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: SignInReminderData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Count = __v_raw.Count, AddTime = __v_raw.AddTime, ContentRemark = __v_raw.ContentRemark, IsRead = __v_raw.IsRead) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): SignInReminderDataReactiveObject {
        return SignInReminderDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Count: Number
        get() {
            return _tRG(__v_raw, "Count", __v_raw.Count, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Count")) {
                return
            }
            val oldValue = __v_raw.Count
            __v_raw.Count = value
            _tRS(__v_raw, "Count", oldValue, value)
        }
    override var AddTime: String
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var ContentRemark: String
        get() {
            return _tRG(__v_raw, "ContentRemark", __v_raw.ContentRemark, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentRemark")) {
                return
            }
            val oldValue = __v_raw.ContentRemark
            __v_raw.ContentRemark = value
            _tRS(__v_raw, "ContentRemark", oldValue, value)
        }
    override var IsRead: Number
        get() {
            return _tRG(__v_raw, "IsRead", __v_raw.IsRead, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsRead")) {
                return
            }
            val oldValue = __v_raw.IsRead
            __v_raw.IsRead = value
            _tRS(__v_raw, "IsRead", oldValue, value)
        }
}
open class InterviewData (
    @JsonNotNull
    open var Count: Number,
    @JsonNotNull
    open var AddTime: String,
    @JsonNotNull
    open var ContentRemark: String,
    @JsonNotNull
    open var IsRead: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return InterviewDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class InterviewDataReactiveObject : InterviewData, IUTSReactive<InterviewData> {
    override var __v_raw: InterviewData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: InterviewData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Count = __v_raw.Count, AddTime = __v_raw.AddTime, ContentRemark = __v_raw.ContentRemark, IsRead = __v_raw.IsRead) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): InterviewDataReactiveObject {
        return InterviewDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Count: Number
        get() {
            return _tRG(__v_raw, "Count", __v_raw.Count, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Count")) {
                return
            }
            val oldValue = __v_raw.Count
            __v_raw.Count = value
            _tRS(__v_raw, "Count", oldValue, value)
        }
    override var AddTime: String
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var ContentRemark: String
        get() {
            return _tRG(__v_raw, "ContentRemark", __v_raw.ContentRemark, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentRemark")) {
                return
            }
            val oldValue = __v_raw.ContentRemark
            __v_raw.ContentRemark = value
            _tRS(__v_raw, "ContentRemark", oldValue, value)
        }
    override var IsRead: Number
        get() {
            return _tRG(__v_raw, "IsRead", __v_raw.IsRead, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsRead")) {
                return
            }
            val oldValue = __v_raw.IsRead
            __v_raw.IsRead = value
            _tRS(__v_raw, "IsRead", oldValue, value)
        }
}
open class GetMessageListResult (
    @JsonNotNull
    open var list: UTSArray<ConversationItem>,
    @JsonNotNull
    open var Notice: NoticeData,
    @JsonNotNull
    open var SignInReminder: SignInReminderData,
    @JsonNotNull
    open var Interview: InterviewData,
) : UTSObject()
open class GetCommunicateListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var Type: Number,
    @JsonNotNull
    open var Lat: Number,
    @JsonNotNull
    open var Lng: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetCommunicateListParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetCommunicateListParamsReactiveObject : GetCommunicateListParams, IUTSReactive<GetCommunicateListParams> {
    override var __v_raw: GetCommunicateListParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetCommunicateListParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, Type = __v_raw.Type, Lat = __v_raw.Lat, Lng = __v_raw.Lng) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetCommunicateListParamsReactiveObject {
        return GetCommunicateListParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var Type: Number
        get() {
            return _tRG(__v_raw, "Type", __v_raw.Type, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Type")) {
                return
            }
            val oldValue = __v_raw.Type
            __v_raw.Type = value
            _tRS(__v_raw, "Type", oldValue, value)
        }
    override var Lat: Number
        get() {
            return _tRG(__v_raw, "Lat", __v_raw.Lat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lat")) {
                return
            }
            val oldValue = __v_raw.Lat
            __v_raw.Lat = value
            _tRS(__v_raw, "Lat", oldValue, value)
        }
    override var Lng: Number
        get() {
            return _tRG(__v_raw, "Lng", __v_raw.Lng, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lng")) {
                return
            }
            val oldValue = __v_raw.Lng
            __v_raw.Lng = value
            _tRS(__v_raw, "Lng", oldValue, value)
        }
}
open class GetCommunicateListResult (
    @JsonNotNull
    open var NickName: String,
    @JsonNotNull
    open var Avatar: String,
    @JsonNotNull
    open var JobName: String,
    @JsonNotNull
    open var SalaryName: String,
    @JsonNotNull
    open var ProvinceName: String,
    @JsonNotNull
    open var CityCodeName: String,
    @JsonNotNull
    open var AreaCodeName: String,
    @JsonNotNull
    open var HireAddress: String,
    @JsonNotNull
    open var ExperienceTitle: String,
    @JsonNotNull
    open var distanceCile: Number,
    @JsonNotNull
    open var MemberId: String,
    @JsonNotNull
    open var ToMemberId: String,
    @JsonNotNull
    open var HireCompanyName: String,
    @JsonNotNull
    open var HireWelfareName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetCommunicateListResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetCommunicateListResultReactiveObject : GetCommunicateListResult, IUTSReactive<GetCommunicateListResult> {
    override var __v_raw: GetCommunicateListResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetCommunicateListResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(NickName = __v_raw.NickName, Avatar = __v_raw.Avatar, JobName = __v_raw.JobName, SalaryName = __v_raw.SalaryName, ProvinceName = __v_raw.ProvinceName, CityCodeName = __v_raw.CityCodeName, AreaCodeName = __v_raw.AreaCodeName, HireAddress = __v_raw.HireAddress, ExperienceTitle = __v_raw.ExperienceTitle, distanceCile = __v_raw.distanceCile, MemberId = __v_raw.MemberId, ToMemberId = __v_raw.ToMemberId, HireCompanyName = __v_raw.HireCompanyName, HireWelfareName = __v_raw.HireWelfareName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetCommunicateListResultReactiveObject {
        return GetCommunicateListResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
    override var Avatar: String
        get() {
            return _tRG(__v_raw, "Avatar", __v_raw.Avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Avatar")) {
                return
            }
            val oldValue = __v_raw.Avatar
            __v_raw.Avatar = value
            _tRS(__v_raw, "Avatar", oldValue, value)
        }
    override var JobName: String
        get() {
            return _tRG(__v_raw, "JobName", __v_raw.JobName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobName")) {
                return
            }
            val oldValue = __v_raw.JobName
            __v_raw.JobName = value
            _tRS(__v_raw, "JobName", oldValue, value)
        }
    override var SalaryName: String
        get() {
            return _tRG(__v_raw, "SalaryName", __v_raw.SalaryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalaryName")) {
                return
            }
            val oldValue = __v_raw.SalaryName
            __v_raw.SalaryName = value
            _tRS(__v_raw, "SalaryName", oldValue, value)
        }
    override var ProvinceName: String
        get() {
            return _tRG(__v_raw, "ProvinceName", __v_raw.ProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceName")) {
                return
            }
            val oldValue = __v_raw.ProvinceName
            __v_raw.ProvinceName = value
            _tRS(__v_raw, "ProvinceName", oldValue, value)
        }
    override var CityCodeName: String
        get() {
            return _tRG(__v_raw, "CityCodeName", __v_raw.CityCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCodeName")) {
                return
            }
            val oldValue = __v_raw.CityCodeName
            __v_raw.CityCodeName = value
            _tRS(__v_raw, "CityCodeName", oldValue, value)
        }
    override var AreaCodeName: String
        get() {
            return _tRG(__v_raw, "AreaCodeName", __v_raw.AreaCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AreaCodeName")) {
                return
            }
            val oldValue = __v_raw.AreaCodeName
            __v_raw.AreaCodeName = value
            _tRS(__v_raw, "AreaCodeName", oldValue, value)
        }
    override var HireAddress: String
        get() {
            return _tRG(__v_raw, "HireAddress", __v_raw.HireAddress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireAddress")) {
                return
            }
            val oldValue = __v_raw.HireAddress
            __v_raw.HireAddress = value
            _tRS(__v_raw, "HireAddress", oldValue, value)
        }
    override var ExperienceTitle: String
        get() {
            return _tRG(__v_raw, "ExperienceTitle", __v_raw.ExperienceTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ExperienceTitle")) {
                return
            }
            val oldValue = __v_raw.ExperienceTitle
            __v_raw.ExperienceTitle = value
            _tRS(__v_raw, "ExperienceTitle", oldValue, value)
        }
    override var distanceCile: Number
        get() {
            return _tRG(__v_raw, "distanceCile", __v_raw.distanceCile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("distanceCile")) {
                return
            }
            val oldValue = __v_raw.distanceCile
            __v_raw.distanceCile = value
            _tRS(__v_raw, "distanceCile", oldValue, value)
        }
    override var MemberId: String
        get() {
            return _tRG(__v_raw, "MemberId", __v_raw.MemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberId")) {
                return
            }
            val oldValue = __v_raw.MemberId
            __v_raw.MemberId = value
            _tRS(__v_raw, "MemberId", oldValue, value)
        }
    override var ToMemberId: String
        get() {
            return _tRG(__v_raw, "ToMemberId", __v_raw.ToMemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ToMemberId")) {
                return
            }
            val oldValue = __v_raw.ToMemberId
            __v_raw.ToMemberId = value
            _tRS(__v_raw, "ToMemberId", oldValue, value)
        }
    override var HireCompanyName: String
        get() {
            return _tRG(__v_raw, "HireCompanyName", __v_raw.HireCompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCompanyName")) {
                return
            }
            val oldValue = __v_raw.HireCompanyName
            __v_raw.HireCompanyName = value
            _tRS(__v_raw, "HireCompanyName", oldValue, value)
        }
    override var HireWelfareName: String
        get() {
            return _tRG(__v_raw, "HireWelfareName", __v_raw.HireWelfareName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireWelfareName")) {
                return
            }
            val oldValue = __v_raw.HireWelfareName
            __v_raw.HireWelfareName = value
            _tRS(__v_raw, "HireWelfareName", oldValue, value)
        }
}
open class GetHaveSeenResult (
    @JsonNotNull
    open var NickName: String,
    @JsonNotNull
    open var Avatar: String,
    @JsonNotNull
    open var JobName: String,
    @JsonNotNull
    open var SalaryName: String,
    @JsonNotNull
    open var ProvinceName: String,
    @JsonNotNull
    open var CityCodeName: String,
    @JsonNotNull
    open var AreaCodeName: String,
    @JsonNotNull
    open var HireAddress: String,
    @JsonNotNull
    open var ExperienceTitle: String,
    @JsonNotNull
    open var distanceCile: Number,
    @JsonNotNull
    open var CompanyName: String,
    @JsonNotNull
    open var AddTime: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetHaveSeenResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetHaveSeenResultReactiveObject : GetHaveSeenResult, IUTSReactive<GetHaveSeenResult> {
    override var __v_raw: GetHaveSeenResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetHaveSeenResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(NickName = __v_raw.NickName, Avatar = __v_raw.Avatar, JobName = __v_raw.JobName, SalaryName = __v_raw.SalaryName, ProvinceName = __v_raw.ProvinceName, CityCodeName = __v_raw.CityCodeName, AreaCodeName = __v_raw.AreaCodeName, HireAddress = __v_raw.HireAddress, ExperienceTitle = __v_raw.ExperienceTitle, distanceCile = __v_raw.distanceCile, CompanyName = __v_raw.CompanyName, AddTime = __v_raw.AddTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetHaveSeenResultReactiveObject {
        return GetHaveSeenResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
    override var Avatar: String
        get() {
            return _tRG(__v_raw, "Avatar", __v_raw.Avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Avatar")) {
                return
            }
            val oldValue = __v_raw.Avatar
            __v_raw.Avatar = value
            _tRS(__v_raw, "Avatar", oldValue, value)
        }
    override var JobName: String
        get() {
            return _tRG(__v_raw, "JobName", __v_raw.JobName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobName")) {
                return
            }
            val oldValue = __v_raw.JobName
            __v_raw.JobName = value
            _tRS(__v_raw, "JobName", oldValue, value)
        }
    override var SalaryName: String
        get() {
            return _tRG(__v_raw, "SalaryName", __v_raw.SalaryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalaryName")) {
                return
            }
            val oldValue = __v_raw.SalaryName
            __v_raw.SalaryName = value
            _tRS(__v_raw, "SalaryName", oldValue, value)
        }
    override var ProvinceName: String
        get() {
            return _tRG(__v_raw, "ProvinceName", __v_raw.ProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceName")) {
                return
            }
            val oldValue = __v_raw.ProvinceName
            __v_raw.ProvinceName = value
            _tRS(__v_raw, "ProvinceName", oldValue, value)
        }
    override var CityCodeName: String
        get() {
            return _tRG(__v_raw, "CityCodeName", __v_raw.CityCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCodeName")) {
                return
            }
            val oldValue = __v_raw.CityCodeName
            __v_raw.CityCodeName = value
            _tRS(__v_raw, "CityCodeName", oldValue, value)
        }
    override var AreaCodeName: String
        get() {
            return _tRG(__v_raw, "AreaCodeName", __v_raw.AreaCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AreaCodeName")) {
                return
            }
            val oldValue = __v_raw.AreaCodeName
            __v_raw.AreaCodeName = value
            _tRS(__v_raw, "AreaCodeName", oldValue, value)
        }
    override var HireAddress: String
        get() {
            return _tRG(__v_raw, "HireAddress", __v_raw.HireAddress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireAddress")) {
                return
            }
            val oldValue = __v_raw.HireAddress
            __v_raw.HireAddress = value
            _tRS(__v_raw, "HireAddress", oldValue, value)
        }
    override var ExperienceTitle: String
        get() {
            return _tRG(__v_raw, "ExperienceTitle", __v_raw.ExperienceTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ExperienceTitle")) {
                return
            }
            val oldValue = __v_raw.ExperienceTitle
            __v_raw.ExperienceTitle = value
            _tRS(__v_raw, "ExperienceTitle", oldValue, value)
        }
    override var distanceCile: Number
        get() {
            return _tRG(__v_raw, "distanceCile", __v_raw.distanceCile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("distanceCile")) {
                return
            }
            val oldValue = __v_raw.distanceCile
            __v_raw.distanceCile = value
            _tRS(__v_raw, "distanceCile", oldValue, value)
        }
    override var CompanyName: String
        get() {
            return _tRG(__v_raw, "CompanyName", __v_raw.CompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyName")) {
                return
            }
            val oldValue = __v_raw.CompanyName
            __v_raw.CompanyName = value
            _tRS(__v_raw, "CompanyName", oldValue, value)
        }
    override var AddTime: String
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
}
open class GetDeliveryRecordParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var Type: Number,
    @JsonNotNull
    open var StarTime: String,
    @JsonNotNull
    open var EndTime: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetDeliveryRecordParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetDeliveryRecordParamsReactiveObject : GetDeliveryRecordParams, IUTSReactive<GetDeliveryRecordParams> {
    override var __v_raw: GetDeliveryRecordParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetDeliveryRecordParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, Type = __v_raw.Type, StarTime = __v_raw.StarTime, EndTime = __v_raw.EndTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetDeliveryRecordParamsReactiveObject {
        return GetDeliveryRecordParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var Type: Number
        get() {
            return _tRG(__v_raw, "Type", __v_raw.Type, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Type")) {
                return
            }
            val oldValue = __v_raw.Type
            __v_raw.Type = value
            _tRS(__v_raw, "Type", oldValue, value)
        }
    override var StarTime: String
        get() {
            return _tRG(__v_raw, "StarTime", __v_raw.StarTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("StarTime")) {
                return
            }
            val oldValue = __v_raw.StarTime
            __v_raw.StarTime = value
            _tRS(__v_raw, "StarTime", oldValue, value)
        }
    override var EndTime: String
        get() {
            return _tRG(__v_raw, "EndTime", __v_raw.EndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("EndTime")) {
                return
            }
            val oldValue = __v_raw.EndTime
            __v_raw.EndTime = value
            _tRS(__v_raw, "EndTime", oldValue, value)
        }
}
open class GetDeliveryRecordResult (
    @JsonNotNull
    open var AreaCodeName: String,
    @JsonNotNull
    open var CityCodeName: String,
    @JsonNotNull
    open var DeliverTime: String,
    @JsonNotNull
    open var ExperienceTitle: String,
    @JsonNotNull
    open var HireAddress: String,
    @JsonNotNull
    open var HireCompanyName: String,
    @JsonNotNull
    open var HireWelfareName: String,
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var JobName: String,
    @JsonNotNull
    open var ProvinceName: String,
    @JsonNotNull
    open var SalaryName: String,
) : UTSObject()
enum class Api__2(override val value: String) : UTSEnumString {
    GET_MESSAGE_LIST("/api/Message/MessageList"),
    GET_MESSAGE_DETAIL("/api/Message/MessageInfoList"),
    GET_SYSTEM_MESSAGE_LIST("/api/Message/GetNoticeList"),
    GET_SYSTEM_MESSAGE_READ("/api/News/ReadNoticeInfo"),
    GET_CLEAR_UNREAD("/api/Message/ClearRead"),
    GET_ADD_INTERVIEW("/api/Message/MessInterview"),
    GET_EDIT_INTERVIEW("/api/Message/EditMessInterview"),
    GET_INTERVIEW_NOTICE_LIST("/api/Message/InterviewNoticeInfoList"),
    GET_SIGN_IN_REMINDER_LIST("/api/Message/SignInInfoList"),
    GET_INTERVIEW_LIST("/api/Message/InterviewList"),
    GET_INTERVIEW_DETAIL("/api/Message/InterviewInfo"),
    GET_INTERVIEW_ACCEPT("/api/Message/OperationInterview"),
    GET_INTERVIEW_RESULT("/api/Message/SendInterview"),
    GET_OTHER_WECHAT("/api/User/GetWeChat"),
    GET_OTHER_PHONE("/api/User/GetMobile"),
    GET_GET_REMARK("/api/User/SettingsInfo"),
    GET_SET_REMARK("/api/User/EditUserSettings"),
    GET_COMMUNICATE_LIST("/api/Message/communicatedList"),
    GET_HAVE_SEEN("/api/Message/HaveSeen"),
    GET_POSITION_EVALUATE("/api/Message/MessGrade"),
    GET_DELIVERY_RECORD("/api/Message/OnlycommunicatedList"),
    GET_RECEIVE_RESULT_OPERATION_MESSAGE("/api/Message/CompleteInterview"),
    GET_END_INTERVIEW("/api/Message/EndInterview")
}
val getMessageList = fun(params: GetMessageListParams): UTSPromise<GetMessageListResult> {
    return request.post<GetMessageListResult>(Api__2.GET_MESSAGE_LIST, params)
}
val getClearUnread = fun(): UTSPromise<Any> {
    return request.post<Any>(Api__2.GET_CLEAR_UNREAD)
}
val useMessageStore = defineStore<MessageStore>("message", fun(): MessageStore {
    val params = reactive<GetMessageListParams>(GetMessageListParams(Page = 1, PageSize = 10, Keywords = "", Type = 0))
    val conversationList = ref(_uA<ConversationItem>())
    val noticeData = ref<NoticeData?>(null)
    val signInReminderData = ref<SignInReminderData?>(null)
    val interviewData = ref<InterviewData?>(null)
    val unreadCount = ref<Number>(0)
    val isRefreshing = ref(false)
    val isLoadingMore = ref(false)
    val hasMore = ref(true)
    val toNumber = fun(value: Number?): Number {
        return if (value != null) {
            value
        } else {
            0
        }
    }
    val updateUnreadCount = fun(){
        var conversationUnread: Number = 0
        conversationList.value.forEach(fun(item: ConversationItem){
            conversationUnread = conversationUnread + toNumber(if (item.UnreadCount != null) {
                item.UnreadCount
            } else {
                null
            }
            )
        }
        )
        val noticeUnread = if (noticeData.value != null) {
            noticeData.value!!.NoticeCountN
        } else {
            0
        }
        val signInUnread = if (signInReminderData.value != null) {
            signInReminderData.value!!.Count
        } else {
            0
        }
        val interviewUnread = if (interviewData.value != null) {
            interviewData.value!!.Count
        } else {
            0
        }
        unreadCount.value = conversationUnread + noticeUnread + signInUnread + interviewUnread
    }
    val setMessageSummary = fun(result: GetMessageListResult){
        conversationList.value = result.list ?: _uA()
        noticeData.value = result.Notice ?: null
        signInReminderData.value = result.SignInReminder ?: null
        interviewData.value = result.Interview ?: null
        updateUnreadCount()
    }
    val syncMessageSummaryMeta = fun(result: GetMessageListResult){
        noticeData.value = result.Notice ?: null
        signInReminderData.value = result.SignInReminder ?: null
        interviewData.value = result.Interview ?: null
    }
    val appendConversationList = fun(list: UTSArray<ConversationItem>){
        val nextList: UTSArray<ConversationItem> = _uA()
        conversationList.value.forEach(fun(item: ConversationItem){
            nextList.push(item)
        }
        )
        list.forEach(fun(item: ConversationItem){
            nextList.push(item)
        }
        )
        conversationList.value = nextList
        updateUnreadCount()
    }
    val setNoticeUnreadCount = fun(count: Number){
        val nextCount = toNumber(count)
        noticeData.value = if (noticeData.value == null) {
            NoticeData(NoticeCountN = nextCount, PubTime = "", Title = "")
        } else {
            NoticeData(NoticeCountN = nextCount, PubTime = noticeData.value!!.PubTime, Title = noticeData.value!!.Title)
        }
        updateUnreadCount()
    }
    val setSignInUnreadCount = fun(count: Number){
        val nextCount = toNumber(count)
        signInReminderData.value = if (signInReminderData.value == null) {
            SignInReminderData(Count = nextCount, AddTime = "", ContentRemark = "", IsRead = if (nextCount > 0) {
                0
            } else {
                1
            })
        } else {
            SignInReminderData(Count = nextCount, AddTime = signInReminderData.value!!.AddTime, ContentRemark = signInReminderData.value!!.ContentRemark, IsRead = if (nextCount > 0) {
                0
            } else {
                1
            }
            )
        }
        updateUnreadCount()
    }
    val refreshMessageData = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (isRefreshing.value) {
                    return@w1
                }
                isRefreshing.value = true
                params.Page = 1
                hasMore.value = true
                try {
                    val result = await(getMessageList(params))
                    setMessageSummary(result)
                    updateUnreadCount()
                    if (result.list.length < params.PageSize) {
                        hasMore.value = false
                    }
                }
                 finally {
                    isRefreshing.value = false
                }
        })
    }
    val loadMoreMessageData = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (isLoadingMore.value || !hasMore.value) {
                    return@w1
                }
                isLoadingMore.value = true
                params.Page = params.Page + 1
                try {
                    val result = await(getMessageList(params))
                    syncMessageSummaryMeta(result)
                    appendConversationList(result.list)
                    updateUnreadCount()
                    if (result.list.length < params.PageSize) {
                        hasMore.value = false
                    }
                }
                 finally {
                    isLoadingMore.value = false
                }
        })
    }
    val clearUnreadCount = fun(){
        conversationList.value.forEach(fun(item: ConversationItem){
            item.UnreadCount = 0
        }
        )
        if (noticeData.value != null) {
            noticeData.value!!.NoticeCountN = 0
        }
        if (signInReminderData.value != null) {
            signInReminderData.value!!.Count = 0
            signInReminderData.value!!.IsRead = 1
        }
        if (interviewData.value != null) {
            interviewData.value!!.Count = 0
            interviewData.value!!.IsRead = 1
        }
        updateUnreadCount()
    }
    val clearUnreadAndRefresh = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                await(getClearUnread())
                clearUnreadCount()
                await(refreshMessageData())
        })
    }
    return MessageStore(params, conversationList, noticeData, signInReminderData, interviewData, unreadCount, isRefreshing, isLoadingMore, hasMore, setMessageSummary, syncMessageSummaryMeta, appendConversationList, setNoticeUnreadCount, setSignInUnreadCount, refreshMessageData, loadMoreMessageData, clearUnreadCount, clearUnreadAndRefresh, updateUnreadCount)
}
)
val stringifySignalRValue = fun(value: Any): String {
    if (UTSAndroid.`typeof`(value) === "string") {
        return value as String
    }
    if (value is UTSError) {
        return (value as UTSError).message
    }
    if (value == null) {
        return ""
    }
    return JSON.stringify(value)
}
open class SignalRChatService {
    private var socketTask: SocketTask? = null
    private var connectionUrl: String = ""
    private var reconnectAttempts: Number = 0
    private var maxReconnectAttempts: Number = 3
    private var reconnectTimer: Number? = null
    private var currentStatus: ConnectionStatus__1 = ConnectionStatus__1.Disconnected
    private var connectionId: String = ""
    private var heartbeatTimer: Number? = null
    private var heartbeatInterval: Number = 30000
    private var invocationCounter: Number = 0
    private var isHandshakeCompleted: Boolean = false
    private var isLinkCompleted: Boolean = false
    private var initializePromise: UTSPromise<Unit>? = null
    private var onSendFailedCallback: ((error: String, errorCode: Number) -> Unit)? = null
    private var onErrorCallback: ((error: String) -> Unit)? = null
    private var onConnectionStatusChangedCallback: ((status: ConnectionStatus__1) -> Unit)? = null
    private var onMessageReceivedCallback: ((message: ReceiveMessage) -> Unit)? = null
    private var onResumeOperationCallback: ((message: ReceiveResumeOperationMessage) -> Unit)? = null
    private var onInterviewOperationCallback: ((message: ReceiveInterviewOperationMessage) -> Unit)? = null
    private var onResultOperationCallback: ((message: ReceiveResultOperationMessage) -> Unit)? = null
    private var refreshMessageListeners: UTSArray<(payload: Any) -> Unit> = _uA()
    private var userMarketingMessageCountListeners: UTSArray<(payload: Any) -> Unit> = _uA()
    private var signinMessageCountListeners: UTSArray<(payload: Any) -> Unit> = _uA()
    open fun initialize(connectionUrl: String): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w@{
                if (this.initializePromise != null) {
                    return@w this.initializePromise!!
                }
                this.initializePromise = this.doInitialize(connectionUrl)
                try {
                    await(this.initializePromise!!)
                }
                 finally {
                    this.initializePromise = null
                }
        })
    }
    private fun doInitialize(connectionUrl: String): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w@{
                if (this.socketTask != null) {
                    await(this.stop())
                }
                this.connectionUrl = connectionUrl
                this.resetConnectionReadyState()
                this.updateConnectionStatus(ConnectionStatus__1.Connecting)
                val authStore = useAuthStore()
                if (authStore.tokenValue == null) {
                    console.warn("[SignalR] 用户未登录，停止连接")
                    this.updateConnectionStatus(ConnectionStatus__1.Disconnected)
                    return@w
                }
                try {
                    val negotiateData = await(this.negotiate(connectionUrl))
                    this.connectionId = negotiateData["connectionId"] as String
                    val connectionToken = negotiateData["connectionToken"] as String
                    console.log("[SignalR] negotiate 成功, connectionId:", this.connectionId)
                    var wsUrl = connectionUrl
                    if (connectionUrl.startsWith("http://")) {
                        wsUrl = connectionUrl.replace("http://", "ws://")
                    } else if (connectionUrl.startsWith("https://")) {
                        wsUrl = connectionUrl.replace("https://", "wss://")
                    }
                    val separator = if (wsUrl.includes("?")) {
                        "&"
                    } else {
                        "?"
                    }
                    wsUrl = "" + wsUrl + separator + "id=" + encodeURIComponent(connectionToken)
                    this.socketTask = uni_connectSocket(ConnectSocketOptions(url = wsUrl, protocols = _uA(), success = fun(res){
                        console.log("[SignalR] WebSocket 连接创建成功:", res)
                    }
                    , fail = fun(error){
                        console.error("[SignalR] 连接创建失败:", error)
                        this.cleanupSocketReference()
                        this.updateConnectionStatus(ConnectionStatus__1.Disconnected)
                        this.onErrorCallback?.invoke("连接创建失败: " + JSON.stringify(error))
                    }
                    ))
                    this.setupEventListeners()
                    try {
                        await(this.waitForConnectionReady(10000))
                    }
                     catch (_error: Throwable) {
                        console.warn("[SignalR] 连接初始化未在超时内就绪")
                    }
                }
                 catch (error: Throwable) {
                    this.cleanupSocketReference()
                    this.updateConnectionStatus(ConnectionStatus__1.Disconnected)
                    this.onErrorCallback?.invoke(stringifySignalRValue(error))
                }
        })
    }
    private fun negotiate(hubUrl: String): UTSPromise<UTSJSONObject> {
        return UTSPromise(fun(resolve, reject){
            val negotiateUrl = "" + hubUrl + "/negotiate?negotiateVersion=1"
            uni_request<Any>(RequestOptions(url = negotiateUrl, method = "POST", header = _uO("Content-Type" to "application/json"), success = fun(res){
                if (res.statusCode === 200 && res.data != null) {
                    val data = res.data as UTSJSONObject
                    val connId = data["connectionId"] as String
                    if (connId != null && connId.length > 0) {
                        resolve(data)
                    } else {
                        reject(UTSError("[SignalR] negotiate 响应缺少 connectionId"))
                    }
                } else {
                    reject(UTSError("[SignalR] negotiate 请求失败, 状态码: " + res.statusCode))
                }
            }
            , fail = fun(error){
                reject(UTSError("[SignalR] negotiate 请求失败: " + JSON.stringify(error)))
            }
            ))
        }
        )
    }
    open fun getConnectionId(): String {
        return this.connectionId
    }
    private fun sendSignalRMessage(data: Any): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, reject){
            if (this.socketTask == null) {
                reject(UTSError("WebSocket 连接未初始化"))
                return
            }
            try {
                this.socketTask!!.send(SendSocketMessageOptions(data = JSON.stringify(data) + "\u001E", success = fun(_result){
                    resolve(Unit)
                }
                , fail = fun(error){
                    reject(UTSError("[SignalR] 发送消息失败: " + JSON.stringify(error)))
                }
                ))
            }
             catch (error: Throwable) {
                reject(if (error is UTSError) {
                    error
                } else {
                    UTSError(stringifySignalRValue(error))
                }
                )
            }
        }
        )
    }
    private fun setupEventListeners() {
        if (this.socketTask == null) {
            throw UTSError("WebSocket 连接未初始化")
        }
        this.socketTask!!.onOpen(fun(res){
            console.log("[SignalR] 连接已建立", res)
            this.updateConnectionStatus(ConnectionStatus__1.Connected)
            this.reconnectAttempts = 0
            this.sendHandshakeMessage()
            this.startHeartbeat()
        }
        )
        this.socketTask!!.onMessage(fun(data){
            this.handleIncomingMessage(stringifySignalRValue(data.data))
        }
        )
        this.socketTask!!.onClose(fun(_result){
            console.log("[SignalR] 连接已关闭")
            this.handleSocketDisconnected(true)
        }
        )
        this.socketTask!!.onError(fun(error){
            console.error("[SignalR] 连接错误:", error)
            this.onErrorCallback?.invoke("连接错误: " + JSON.stringify(error))
            this.handleSocketDisconnected(false)
        }
        )
    }
    private fun handleIncomingMessage(data: String): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w@{
                if (data == "" || UTSAndroid.`typeof`(data) !== "string") {
                    return@w
                }
                try {
                    val rawMessages = data.split("\u001E")
                    val messages: UTSArray<String> = _uA()
                    rawMessages.forEach(fun(msg: String){
                        if (msg.trim() !== "") {
                            messages.push(msg)
                        }
                    }
                    )
                    for(message in resolveUTSValueIterator(messages)){
                        if (message == "") {
                            continue
                        }
                        val parsed = JSON.parse(message) as SignalRIncomingMessage
                        val messageType = parsed["type"] as Number?
                        if (messageType == null) {
                            this.isHandshakeCompleted = true
                            await(this.UpdateLink())
                            continue
                        }
                        when (messageType) {
                            6 -> 
                                await(this.sendSignalRMessage(_uO("type" to 6)))
                            1 -> 
                                {
                                    val parsedTarget = parsed["target"] as String?
                                    if (parsedTarget != null && parsedTarget != "") {
                                        val args = if (parsed["arguments"] != null) {
                                            parsed["arguments"]
                                        } else {
                                            (_uA<Any>())
                                        }
                                        var receiveMessage: Any? = null
                                        if (UTSArray.isArray(args)) {
                                            val argList = args as UTSArray<Any>
                                            val firstArg = if (argList.length > 0) {
                                                argList[0]
                                            } else {
                                                null
                                            }
                                            if (UTSAndroid.`typeof`(firstArg) === "string") {
                                                val str = (firstArg as String).trim()
                                                if ((str.startsWith("{") && str.endsWith("}")) || (str.startsWith("[") && str.endsWith("]"))) {
                                                    try {
                                                        receiveMessage = JSON.parse(str)
                                                    } catch (e: Throwable) {
                                                        receiveMessage = str
                                                    }
                                                } else {
                                                    receiveMessage = str
                                                }
                                            } else {
                                                receiveMessage = firstArg
                                            }
                                        } else {
                                            receiveMessage = JSON.parse(stringifySignalRValue(args!!!!))
                                        }
                                        when (parsedTarget) {
                                            "ReceiveMessage" -> 
                                                if (receiveMessage != null) {
                                                    console.log("[SignalR] 收到消息:", receiveMessage)
                                                    this.onMessageReceivedCallback?.invoke(receiveMessage as ReceiveMessage)
                                                }
                                            "RefreshMessage" -> 
                                                {
                                                    console.log("[SignalR] 收到 列表消息:", receiveMessage)
                                                    this.emitRefreshMessage(receiveMessage as Boolean)
                                                }
                                            "UserMarketingMessageCount" -> 
                                                {
                                                    console.log("[SignalR] 收到 系统通知:", receiveMessage)
                                                    this.emitUserMarketingMessageCount(receiveMessage as Number)
                                                }
                                            "SigninMessageCount" -> 
                                                {
                                                    console.log("[SignalR] 收到 签到通知:", receiveMessage)
                                                    this.emitSigninMessageCount(receiveMessage as Number)
                                                }
                                            "SendResumeOperation" -> 
                                                if (receiveMessage != null) {
                                                    console.log("[SignalR] 收到简历操作消息:", receiveMessage)
                                                    this.onResumeOperationCallback?.invoke(receiveMessage as ReceiveResumeOperationMessage)
                                                }
                                            "SendInterviewOperation" -> 
                                                if (receiveMessage != null) {
                                                    console.log("[SignalR] 收到面试操作消息:", receiveMessage)
                                                    this.onInterviewOperationCallback?.invoke(receiveMessage as ReceiveInterviewOperationMessage)
                                                }
                                            "SendResultOperation" -> 
                                                if (receiveMessage != null) {
                                                    console.log("[SignalR] 收到面试结果消息:", receiveMessage)
                                                    this.onResultOperationCallback?.invoke(receiveMessage as ReceiveResultOperationMessage)
                                                }
                                            "SendFailed" -> 
                                                {
                                                    console.log("[SignalR] 收到失败消息:", receiveMessage)
                                                    if (receiveMessage != null) {
                                                        this.onSendFailedCallback?.invoke(stringifySignalRValue(receiveMessage), -1)
                                                    }
                                                }
                                        }
                                    }
                                }
                            2 -> 
                                {}
                            3 -> 
                                {
                                    val parsedError = parsed["error"] as String?
                                    if (parsedError != null && parsedError != "") {
                                        console.error("[SignalR] 调用失败:", parsedError)
                                        this.onSendFailedCallback?.invoke(parsedError, -1)
                                    }
                                }
                            else -> 
                                {}
                        }
                    }
                }
                 catch (error: Throwable) {
                    console.error("[SignalR] 处理消息失败:", error)
                }
        })
    }
    private fun sendHandshakeMessage(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w@{
                if (this.socketTask == null) {
                    return@w
                }
                try {
                    await(this.sendSignalRMessage(_uO("protocol" to "json", "version" to 1)))
                }
                 catch (error: Throwable) {
                    console.error("[SignalR] 发送握手消息失败:", error)
                }
        })
    }
    private fun scheduleReconnect() {
        if (this.reconnectAttempts >= this.maxReconnectAttempts) {
            console.error("[SignalR] 已达最大重连次数")
            this.updateConnectionStatus(ConnectionStatus__1.Disconnected)
            return
        }
        this.reconnectAttempts++
        this.updateConnectionStatus(ConnectionStatus__1.Reconnecting)
        if (this.reconnectTimer != null) {
            val reconnectTimer = this.reconnectTimer!!
            if (reconnectTimer != null) {
                clearTimeout(reconnectTimer)
            }
            this.reconnectTimer = null
        }
        val delay = 1000 * Math.pow(2, this.reconnectAttempts - 1)
        console.log("[SignalR] " + delay + "ms 后重连 (第" + this.reconnectAttempts + "次)")
        this.reconnectTimer = setTimeout(fun(){
            if (this.connectionUrl != "") {
                this.initialize(this.connectionUrl)
            }
        }
        , delay)
    }
    open fun UpdateLink(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w@{
                if (this.socketTask == null || this.currentStatus !== ConnectionStatus__1.Connected) {
                    console.warn("[SignalR] 连接未就绪，无法更新连接ID")
                    return@w
                }
                val authStore = useAuthStore()
                val tokenValue = authStore.tokenValue
                val token = if (tokenValue != null) {
                    tokenValue.Token
                } else {
                    ""
                }
                val userId = if (tokenValue != null) {
                    "" + tokenValue.UserId
                } else {
                    ""
                }
                try {
                    val messageData: UTSJSONObject = _uO("target" to "SetConnectionId", "arguments" to _uA(
                        userId,
                        token,
                        this.connectionId,
                        0
                    ), "invocationId" to "0", "type" to 1)
                    await(this.sendSignalRMessage(messageData))
                    this.isLinkCompleted = true
                }
                 catch (error: Throwable) {
                    this.isLinkCompleted = false
                    console.error("[SignalR] 更新连接ID失败:", error)
                    this.onSendFailedCallback?.invoke(stringifySignalRValue(error), -1)
                }
        })
    }
    private fun waitForConnectionReady(timeoutMs: Number): UTSPromise<Unit> {
        return UTSPromise(fun(resolve, reject){
            val startTime = Date.now()
            var timer: Number? = null
            timer = setInterval(fun(){
                val isReady = this.socketTask != null && this.currentStatus === ConnectionStatus__1.Connected && this.isHandshakeCompleted && this.isLinkCompleted
                if (isReady) {
                    val currentTimer = timer
                    if (currentTimer != null) {
                        clearInterval(currentTimer)
                    }
                    resolve(Unit)
                    return
                }
                if (Date.now() - startTime >= timeoutMs) {
                    val currentTimer = timer
                    if (currentTimer != null) {
                        clearInterval(currentTimer)
                    }
                    reject(UTSError("连接准备超时，消息发送失败"))
                }
            }
            , 100)
        }
        )
    }
    open fun invoke(methodName: String, args: Any): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                try {
                    val isReady = await(this.ensureConnectionReady(8000))
                    if (!isReady) {
                        throw UTSError("连接未就绪，消息发送失败")
                    }
                }
                 catch (error: Throwable) {
                    val errorMessage = stringifySignalRValue(error)
                    console.warn("[SignalR] 连接未就绪，无法调用服务端方法:", methodName)
                    this.onSendFailedCallback?.invoke(errorMessage, -1)
                    throw error
                }
                this.invocationCounter++
                val invocationId = this.invocationCounter.toString(10)
                try {
                    await(this.sendSignalRMessage(_uO("type" to 1, "invocationId" to invocationId, "target" to methodName, "arguments" to _uA(
                        args
                    ))))
                    console.log("[SignalR] invoke " + methodName + " 成功, invocationId: " + invocationId)
                }
                 catch (error: Throwable) {
                    val errorMessage = stringifySignalRValue(error)
                    console.error("[SignalR] invoke " + methodName + " 失败:", error)
                    this.onSendFailedCallback?.invoke(errorMessage, -1)
                    throw error
                }
        })
    }
    open fun sendMessage(request: SendMessageRequest): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val authStore = useAuthStore()
                val tokenData = authStore.tokenValue
                val userId = if (tokenData != null) {
                    "" + tokenData.UserId
                } else {
                    ""
                }
                val token = if (tokenData != null) {
                    tokenData.Token
                } else {
                    ""
                }
                val payload = UTSJSONObject()
                payload["UserId"] = if (request.UserId != null) {
                    request.UserId
                } else {
                    userId
                }
                payload["Token"] = if (request.Token != null) {
                    request.Token
                } else {
                    token
                }
                payload["MemberId"] = if (request.MemberId != null) {
                    request.MemberId
                } else {
                    userId
                }
                payload["ToMemberId"] = request.ToMemberId
                payload["MesId"] = request.MesId
                payload["MessageType"] = request.MessageType
                payload["HireJobId"] = request.HireJobId
                payload["IsGrade"] = request.IsGrade
                payload["IsRight"] = request.IsRight
                payload["IsInterview"] = request.IsInterview
                payload["IsInterviewResult"] = request.IsInterviewResult
                payload["IsSendInterviewResult"] = request.IsSendInterviewResult
                payload["IsSendResume"] = request.IsSendResume
                payload["IsSendReject"] = request.IsSendReject
                payload["InterviewId"] = request.InterviewId
                payload["SendInterviewId"] = request.SendInterviewId
                payload["SendChaMessId"] = request.SendChaMessId
                payload["SendChaInterviewId"] = request.SendChaInterviewId
                payload["SendResultStatus"] = request.SendResultStatus
                payload["Message"] = request.Message
                payload["fileBytes"] = request.fileBytes
                payload["fileUrl"] = request.fileUrl
                payload["fileExt"] = request.fileExt
                payload["fileSize"] = request.fileSize
                payload["fileName"] = request.fileName
                payload["AudioDuration"] = request.AudioDuration
                payload["OnlineFileId"] = request.OnlineFileId
                payload["SendMethod"] = request.SendMethod
                await(this.invoke("TalkMessages", payload))
        })
    }
    open fun getConnectionStatus(): ConnectionStatus__1 {
        if (this.socketTask == null) {
            return ConnectionStatus__1.Disconnected
        }
        return this.currentStatus
    }
    open fun isConnectionReady(): Boolean {
        return (this.socketTask != null && this.currentStatus === ConnectionStatus__1.Connected && this.isHandshakeCompleted && this.isLinkCompleted)
    }
    open fun ensureConnectionReady(timeoutMs: Number): UTSPromise<Boolean> {
        return wrapUTSPromise(suspend w@{
                if (this.isConnectionReady()) {
                    return@w true
                }
                if (this.initializePromise != null) {
                    try {
                        await(this.initializePromise!!)
                    }
                     catch (_error: Throwable) {}
                    if (this.isConnectionReady()) {
                        return@w true
                    }
                }
                if (this.socketTask != null && this.currentStatus === ConnectionStatus__1.Connected && this.isHandshakeCompleted && !this.isLinkCompleted) {
                    try {
                        await(this.UpdateLink())
                    }
                     catch (_error: Throwable) {}
                    if (this.isConnectionReady()) {
                        return@w true
                    }
                }
                var reconnectUrl = this.connectionUrl
                if (reconnectUrl == "") {
                    reconnectUrl = getSignalRUrl()
                }
                if (reconnectUrl == "") {
                    console.warn("[SignalR] connectionUrl 为空，无法重连")
                    return@w false
                }
                try {
                    await(this.stop())
                }
                 catch (_error: Throwable) {}
                try {
                    await(this.initialize(reconnectUrl))
                    return@w this.isConnectionReady()
                }
                 catch (error: Throwable) {
                    console.error("[SignalR] ensureConnectionReady 重连失败:", error)
                    return@w false
                }
        })
    }
    private fun startHeartbeat() {
        this.stopHeartbeat()
        this.heartbeatTimer = setInterval(fun(){
            if (this.socketTask != null && this.currentStatus === ConnectionStatus__1.Connected) {
                this.sendSignalRMessage(_uO("type" to 6)).`catch`(fun(error){
                    console.error("[SignalR] 心跳发送失败:", error)
                }
                )
            }
        }
        , this.heartbeatInterval)
    }
    private fun stopHeartbeat() {
        if (this.heartbeatTimer != null) {
            val heartbeatTimer = this.heartbeatTimer!!
            if (heartbeatTimer != null) {
                clearInterval(heartbeatTimer)
            }
            this.heartbeatTimer = null
        }
    }
    open fun stop(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w@{
                if (this.socketTask == null) {
                    return@w
                }
                console.log("[SignalR] 主动断开连接")
                this.stopHeartbeat()
                if (this.reconnectTimer != null) {
                    val reconnectTimer = this.reconnectTimer!!
                    if (reconnectTimer != null) {
                        clearTimeout(reconnectTimer)
                    }
                    this.reconnectTimer = null
                }
                try {
                    this.socketTask!!.close(CloseSocketOptions(code = 1000, reason = "Normal closure"))
                }
                 finally {
                    this.cleanupSocketReference()
                    this.updateConnectionStatus(ConnectionStatus__1.Disconnected)
                }
        })
    }
    open fun dispose() {
        console.log("[SignalR] 清理资源")
        this.stopHeartbeat()
        if (this.reconnectTimer != null) {
            val reconnectTimer = this.reconnectTimer!!
            if (reconnectTimer != null) {
                clearTimeout(reconnectTimer)
            }
            this.reconnectTimer = null
        }
        if (this.socketTask != null) {
            this.socketTask!!.close(CloseSocketOptions(code = 1000, reason = "Dispose"))
        }
        this.cleanupSocketReference()
        this.reconnectAttempts = 0
        this.invocationCounter = 0
        this.currentStatus = ConnectionStatus__1.Disconnected
        this.connectionUrl = ""
        this.connectionId = ""
        this.initializePromise = null
        this.onSendFailedCallback = null
        this.onErrorCallback = null
        this.onConnectionStatusChangedCallback = null
        this.onMessageReceivedCallback = null
        this.onResumeOperationCallback = null
        this.onInterviewOperationCallback = null
        this.onResultOperationCallback = null
    }
    private fun updateConnectionStatus(status: ConnectionStatus__1) {
        if (this.currentStatus !== status) {
            this.currentStatus = status
            this.onConnectionStatusChangedCallback?.invoke(status)
        }
    }
    private fun handleSocketDisconnected(needReconnect: Boolean) {
        this.stopHeartbeat()
        this.cleanupSocketReference()
        this.updateConnectionStatus(ConnectionStatus__1.Disconnected)
        if (needReconnect) {
            if (this.reconnectAttempts < this.maxReconnectAttempts) {
                this.scheduleReconnect()
            } else {
                console.error("[SignalR] 已达最大重连次数，停止重连")
            }
        }
    }
    private fun resetConnectionReadyState() {
        this.isHandshakeCompleted = false
        this.isLinkCompleted = false
    }
    private fun cleanupSocketReference() {
        this.socketTask = null
        this.resetConnectionReadyState()
    }
    private fun emitRefreshMessage(payload: Boolean) {
        val listeners = this.refreshMessageListeners.slice()
        listeners.forEach(fun(listener){
            try {
                listener(payload)
            }
             catch (error: Throwable) {
                console.error("[SignalR] 执行 RefreshMessage 监听器失败:", error)
            }
        }
        )
    }
    private fun emitUserMarketingMessageCount(payload: Number) {
        val listeners = this.userMarketingMessageCountListeners.slice()
        listeners.forEach(fun(listener){
            try {
                listener(payload)
            }
             catch (error: Throwable) {
                console.error("[SignalR] 执行 UserMarketingMessageCount 监听器失败:", error)
            }
        }
        )
    }
    private fun emitSigninMessageCount(payload: Number) {
        val listeners = this.signinMessageCountListeners.slice()
        listeners.forEach(fun(listener){
            try {
                listener(payload)
            }
             catch (error: Throwable) {
                console.error("[SignalR] 执行 SigninMessageCount 监听器失败:", error)
            }
        }
        )
    }
    open fun setOnSendFailed(callback: (error: String, errorCode: Number) -> Unit) {
        this.onSendFailedCallback = callback
    }
    open fun setOnError(callback: (error: String) -> Unit) {
        this.onErrorCallback = callback
    }
    open fun setOnConnectionStatusChanged(callback: (status: ConnectionStatus__1) -> Unit) {
        this.onConnectionStatusChangedCallback = callback
    }
    open fun setOnMessageReceived(callback: (message: ReceiveMessage) -> Unit) {
        this.onMessageReceivedCallback = callback
    }
    open fun setOnResumeOperation(callback: (message: ReceiveResumeOperationMessage) -> Unit) {
        this.onResumeOperationCallback = callback
    }
    open fun setOnInterviewOperation(callback: (message: ReceiveInterviewOperationMessage) -> Unit) {
        this.onInterviewOperationCallback = callback
    }
    open fun setOnResultOperation(callback: (message: ReceiveResultOperationMessage) -> Unit) {
        this.onResultOperationCallback = callback
    }
    open fun addRefreshMessageListener(callback: (payload: Any) -> Unit) {
        if (!this.refreshMessageListeners.includes(callback)) {
            this.refreshMessageListeners.push(callback)
        }
    }
    open fun removeRefreshMessageListener(callback: (payload: Any) -> Unit) {
        this.refreshMessageListeners = this.refreshMessageListeners.filter(fun(listener): Boolean {
            return listener !== callback
        }
        )
    }
    open fun addUserMarketingMessageCountListener(callback: (payload: Any) -> Unit) {
        if (!this.userMarketingMessageCountListeners.includes(callback)) {
            this.userMarketingMessageCountListeners.push(callback)
        }
    }
    open fun removeUserMarketingMessageCountListener(callback: (payload: Any) -> Unit) {
        this.userMarketingMessageCountListeners = this.userMarketingMessageCountListeners.filter(fun(listener): Boolean {
            return listener !== callback
        }
        )
    }
    open fun addSigninMessageCountListener(callback: (payload: Any) -> Unit) {
        if (!this.signinMessageCountListeners.includes(callback)) {
            this.signinMessageCountListeners.push(callback)
        }
    }
    open fun removeSigninMessageCountListener(callback: (payload: Any) -> Unit) {
        this.signinMessageCountListeners = this.signinMessageCountListeners.filter(fun(listener): Boolean {
            return listener !== callback
        }
        )
    }
}
val signalRChatService = SignalRChatService()
val useRoleStore = defineStore<RoleStore>("role", fun(): RoleStore {
    val currentRole = ref<UserRole>("applicant")
    val initialized = ref(false)
    val roleCheck = computed(fun(): Boolean {
        return currentRole.value === (ROLES["RECRUITER"] as String)
    }
    )
    val initRole = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (initialized.value) {
                    return@w1
                }
                try {
                    val result = await(getStorage<UserRole>(STORAGE_KEYS["USER_ROLE"] as String))
                    if (result.success && result.value != null) {
                        currentRole.value = result.value!!
                    }
                }
                 catch (error: Throwable) {
                    console.error("初始化角色失败:", error)
                }
                 finally {
                    initialized.value = true
                }
        })
    }
    val setRole = fun(role: UserRole): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                currentRole.value = role
                await(setStorage(STORAGE_KEYS["USER_ROLE"] as String, role))
        })
    }
    val toggleRole = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val newRole = if (currentRole.value === (ROLES["APPLICANT"] as String)) {
                    (ROLES["RECRUITER"] as String)
                } else {
                    (ROLES["APPLICANT"] as String)
                }
                await(setRole(newRole as UserRole))
        })
    }
    val resetRole = fun(){
        currentRole.value = ROLES["APPLICANT"] as String
        initialized.value = false
    }
    return RoleStore(currentRole, initialized, roleCheck, initRole, setRole, toggleRole, resetRole)
}
)
open class GetUserCollectParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var Type: Number,
    @JsonNotNull
    open var Lng: Number,
    @JsonNotNull
    open var Lat: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetUserCollectParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetUserCollectParamsReactiveObject : GetUserCollectParams, IUTSReactive<GetUserCollectParams> {
    override var __v_raw: GetUserCollectParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetUserCollectParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, Type = __v_raw.Type, Lng = __v_raw.Lng, Lat = __v_raw.Lat) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetUserCollectParamsReactiveObject {
        return GetUserCollectParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var Type: Number
        get() {
            return _tRG(__v_raw, "Type", __v_raw.Type, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Type")) {
                return
            }
            val oldValue = __v_raw.Type
            __v_raw.Type = value
            _tRS(__v_raw, "Type", oldValue, value)
        }
    override var Lng: Number
        get() {
            return _tRG(__v_raw, "Lng", __v_raw.Lng, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lng")) {
                return
            }
            val oldValue = __v_raw.Lng
            __v_raw.Lng = value
            _tRS(__v_raw, "Lng", oldValue, value)
        }
    override var Lat: Number
        get() {
            return _tRG(__v_raw, "Lat", __v_raw.Lat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lat")) {
                return
            }
            val oldValue = __v_raw.Lat
            __v_raw.Lat = value
            _tRS(__v_raw, "Lat", oldValue, value)
        }
}
open class GetUserCollectResult (
    open var Id: Number? = null,
    open var AddTime: String? = null,
    open var HireJobId: Number? = null,
    open var JobName: String? = null,
    open var HireCompanyName: String? = null,
    open var SalaryName: String? = null,
    open var MinimumSalary: Number? = null,
    open var HighestSalary: Number? = null,
    open var MinWorkExperience: Number? = null,
    open var MaxWorkExperience: Number? = null,
    open var ExperienceTitle: String? = null,
    open var HireWelfare: String? = null,
    open var HireWelfareName: String? = null,
    open var HireProvinceName: String? = null,
    open var HireCityCodeName: String? = null,
    open var HireAreaCodeName: String? = null,
    open var HireAddress: String? = null,
    open var HireLng: String? = null,
    open var HireLat: String? = null,
    open var row: Number? = null,
    open var distanceCile: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetUserCollectResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetUserCollectResultReactiveObject : GetUserCollectResult, IUTSReactive<GetUserCollectResult> {
    override var __v_raw: GetUserCollectResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetUserCollectResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, AddTime = __v_raw.AddTime, HireJobId = __v_raw.HireJobId, JobName = __v_raw.JobName, HireCompanyName = __v_raw.HireCompanyName, SalaryName = __v_raw.SalaryName, MinimumSalary = __v_raw.MinimumSalary, HighestSalary = __v_raw.HighestSalary, MinWorkExperience = __v_raw.MinWorkExperience, MaxWorkExperience = __v_raw.MaxWorkExperience, ExperienceTitle = __v_raw.ExperienceTitle, HireWelfare = __v_raw.HireWelfare, HireWelfareName = __v_raw.HireWelfareName, HireProvinceName = __v_raw.HireProvinceName, HireCityCodeName = __v_raw.HireCityCodeName, HireAreaCodeName = __v_raw.HireAreaCodeName, HireAddress = __v_raw.HireAddress, HireLng = __v_raw.HireLng, HireLat = __v_raw.HireLat, row = __v_raw.row, distanceCile = __v_raw.distanceCile) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetUserCollectResultReactiveObject {
        return GetUserCollectResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number?
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var AddTime: String?
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var HireJobId: Number?
        get() {
            return _tRG(__v_raw, "HireJobId", __v_raw.HireJobId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireJobId")) {
                return
            }
            val oldValue = __v_raw.HireJobId
            __v_raw.HireJobId = value
            _tRS(__v_raw, "HireJobId", oldValue, value)
        }
    override var JobName: String?
        get() {
            return _tRG(__v_raw, "JobName", __v_raw.JobName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobName")) {
                return
            }
            val oldValue = __v_raw.JobName
            __v_raw.JobName = value
            _tRS(__v_raw, "JobName", oldValue, value)
        }
    override var HireCompanyName: String?
        get() {
            return _tRG(__v_raw, "HireCompanyName", __v_raw.HireCompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCompanyName")) {
                return
            }
            val oldValue = __v_raw.HireCompanyName
            __v_raw.HireCompanyName = value
            _tRS(__v_raw, "HireCompanyName", oldValue, value)
        }
    override var SalaryName: String?
        get() {
            return _tRG(__v_raw, "SalaryName", __v_raw.SalaryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalaryName")) {
                return
            }
            val oldValue = __v_raw.SalaryName
            __v_raw.SalaryName = value
            _tRS(__v_raw, "SalaryName", oldValue, value)
        }
    override var MinimumSalary: Number?
        get() {
            return _tRG(__v_raw, "MinimumSalary", __v_raw.MinimumSalary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinimumSalary")) {
                return
            }
            val oldValue = __v_raw.MinimumSalary
            __v_raw.MinimumSalary = value
            _tRS(__v_raw, "MinimumSalary", oldValue, value)
        }
    override var HighestSalary: Number?
        get() {
            return _tRG(__v_raw, "HighestSalary", __v_raw.HighestSalary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HighestSalary")) {
                return
            }
            val oldValue = __v_raw.HighestSalary
            __v_raw.HighestSalary = value
            _tRS(__v_raw, "HighestSalary", oldValue, value)
        }
    override var MinWorkExperience: Number?
        get() {
            return _tRG(__v_raw, "MinWorkExperience", __v_raw.MinWorkExperience, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinWorkExperience")) {
                return
            }
            val oldValue = __v_raw.MinWorkExperience
            __v_raw.MinWorkExperience = value
            _tRS(__v_raw, "MinWorkExperience", oldValue, value)
        }
    override var MaxWorkExperience: Number?
        get() {
            return _tRG(__v_raw, "MaxWorkExperience", __v_raw.MaxWorkExperience, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MaxWorkExperience")) {
                return
            }
            val oldValue = __v_raw.MaxWorkExperience
            __v_raw.MaxWorkExperience = value
            _tRS(__v_raw, "MaxWorkExperience", oldValue, value)
        }
    override var ExperienceTitle: String?
        get() {
            return _tRG(__v_raw, "ExperienceTitle", __v_raw.ExperienceTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ExperienceTitle")) {
                return
            }
            val oldValue = __v_raw.ExperienceTitle
            __v_raw.ExperienceTitle = value
            _tRS(__v_raw, "ExperienceTitle", oldValue, value)
        }
    override var HireWelfare: String?
        get() {
            return _tRG(__v_raw, "HireWelfare", __v_raw.HireWelfare, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireWelfare")) {
                return
            }
            val oldValue = __v_raw.HireWelfare
            __v_raw.HireWelfare = value
            _tRS(__v_raw, "HireWelfare", oldValue, value)
        }
    override var HireWelfareName: String?
        get() {
            return _tRG(__v_raw, "HireWelfareName", __v_raw.HireWelfareName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireWelfareName")) {
                return
            }
            val oldValue = __v_raw.HireWelfareName
            __v_raw.HireWelfareName = value
            _tRS(__v_raw, "HireWelfareName", oldValue, value)
        }
    override var HireProvinceName: String?
        get() {
            return _tRG(__v_raw, "HireProvinceName", __v_raw.HireProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireProvinceName")) {
                return
            }
            val oldValue = __v_raw.HireProvinceName
            __v_raw.HireProvinceName = value
            _tRS(__v_raw, "HireProvinceName", oldValue, value)
        }
    override var HireCityCodeName: String?
        get() {
            return _tRG(__v_raw, "HireCityCodeName", __v_raw.HireCityCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCityCodeName")) {
                return
            }
            val oldValue = __v_raw.HireCityCodeName
            __v_raw.HireCityCodeName = value
            _tRS(__v_raw, "HireCityCodeName", oldValue, value)
        }
    override var HireAreaCodeName: String?
        get() {
            return _tRG(__v_raw, "HireAreaCodeName", __v_raw.HireAreaCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireAreaCodeName")) {
                return
            }
            val oldValue = __v_raw.HireAreaCodeName
            __v_raw.HireAreaCodeName = value
            _tRS(__v_raw, "HireAreaCodeName", oldValue, value)
        }
    override var HireAddress: String?
        get() {
            return _tRG(__v_raw, "HireAddress", __v_raw.HireAddress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireAddress")) {
                return
            }
            val oldValue = __v_raw.HireAddress
            __v_raw.HireAddress = value
            _tRS(__v_raw, "HireAddress", oldValue, value)
        }
    override var HireLng: String?
        get() {
            return _tRG(__v_raw, "HireLng", __v_raw.HireLng, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireLng")) {
                return
            }
            val oldValue = __v_raw.HireLng
            __v_raw.HireLng = value
            _tRS(__v_raw, "HireLng", oldValue, value)
        }
    override var HireLat: String?
        get() {
            return _tRG(__v_raw, "HireLat", __v_raw.HireLat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireLat")) {
                return
            }
            val oldValue = __v_raw.HireLat
            __v_raw.HireLat = value
            _tRS(__v_raw, "HireLat", oldValue, value)
        }
    override var row: Number?
        get() {
            return _tRG(__v_raw, "row", __v_raw.row, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("row")) {
                return
            }
            val oldValue = __v_raw.row
            __v_raw.row = value
            _tRS(__v_raw, "row", oldValue, value)
        }
    override var distanceCile: Number?
        get() {
            return _tRG(__v_raw, "distanceCile", __v_raw.distanceCile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("distanceCile")) {
                return
            }
            val oldValue = __v_raw.distanceCile
            __v_raw.distanceCile = value
            _tRS(__v_raw, "distanceCile", oldValue, value)
        }
}
open class InviteFriendsResult (
    @JsonNotNull
    open var InviteQRcode: String,
    @JsonNotNull
    open var ReferralCode: String,
) : UTSObject()
enum class Api__3(override val value: String) : UTSEnumString {
    GET_USER_INFO("/api/User/GetMemInfo"),
    GET_USER_COLLECT("/api/User/MemberCollections"),
    GET_MY_TEAM("/api/User/MyTeam"),
    GET_ACCOUNT_LIST("/api/User/GetMyAccount"),
    EDIT_USER_NICKNAME("/api/User/EditUserNick"),
    EDIT_USER_AVATAR("/api/User/UploadPhoto"),
    EDIT_USER_OTHER("/api/User/EditUserInfo"),
    EDIT_BUSINESS_INFO("/api/User/EditBusinessInfo"),
    UPDATE_PAY_PWD("/api/User/UpdatePayPwd"),
    DELETE_ACCOUNT("/api/User/LogOutMember"),
    UPDATE_PRIVATE_PROTECTION("/api/Hireworkers/SettingHide"),
    GET_QRCODE("/api/User/InviteFriends")
}
val getUserInfo = fun(): UTSPromise<GetUserInfoResult> {
    return request.post<GetUserInfoResult>(Api__3.GET_USER_INFO)
}
open class PhoneRegisterParams (
    open var IndustryId: Number? = null,
    open var UserRName: String? = null,
    open var Email: String? = null,
    open var CompanyName: String? = null,
    open var CompanyState: String? = null,
    open var CompanyRepresentative: String? = null,
    open var CompanyCapital: String? = null,
    open var CreditCode: String? = null,
    open var CompanyzcAdrr: String? = null,
    open var Tel: String? = null,
    open var ContactNumberOther: String? = null,
    open var CompanyScale: String? = null,
    open var EstablishTime: String? = null,
    open var ProvinceCode: String? = null,
    open var ProvinceName: String? = null,
    open var CityCode: String? = null,
    open var CityName: String? = null,
    open var CompanyLogo: String? = null,
    open var DistrictCode: String? = null,
    open var DistrictName: String? = null,
    open var FullAddress: String? = null,
    open var CompanyElegance: String? = null,
    open var Lng: Number? = null,
    open var Lat: Number? = null,
    open var Mobile: String? = null,
    open var PassWord: String? = null,
    open var Vcode: String? = null,
    open var UserType: Number? = null,
    open var IdentityType: Number? = null,
    open var InviteCode: String? = null,
    open var Birthday: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PhoneRegisterParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PhoneRegisterParamsReactiveObject : PhoneRegisterParams, IUTSReactive<PhoneRegisterParams> {
    override var __v_raw: PhoneRegisterParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PhoneRegisterParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(IndustryId = __v_raw.IndustryId, UserRName = __v_raw.UserRName, Email = __v_raw.Email, CompanyName = __v_raw.CompanyName, CompanyState = __v_raw.CompanyState, CompanyRepresentative = __v_raw.CompanyRepresentative, CompanyCapital = __v_raw.CompanyCapital, CreditCode = __v_raw.CreditCode, CompanyzcAdrr = __v_raw.CompanyzcAdrr, Tel = __v_raw.Tel, ContactNumberOther = __v_raw.ContactNumberOther, CompanyScale = __v_raw.CompanyScale, EstablishTime = __v_raw.EstablishTime, ProvinceCode = __v_raw.ProvinceCode, ProvinceName = __v_raw.ProvinceName, CityCode = __v_raw.CityCode, CityName = __v_raw.CityName, CompanyLogo = __v_raw.CompanyLogo, DistrictCode = __v_raw.DistrictCode, DistrictName = __v_raw.DistrictName, FullAddress = __v_raw.FullAddress, CompanyElegance = __v_raw.CompanyElegance, Lng = __v_raw.Lng, Lat = __v_raw.Lat, Mobile = __v_raw.Mobile, PassWord = __v_raw.PassWord, Vcode = __v_raw.Vcode, UserType = __v_raw.UserType, IdentityType = __v_raw.IdentityType, InviteCode = __v_raw.InviteCode, Birthday = __v_raw.Birthday) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PhoneRegisterParamsReactiveObject {
        return PhoneRegisterParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var IndustryId: Number?
        get() {
            return _tRG(__v_raw, "IndustryId", __v_raw.IndustryId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IndustryId")) {
                return
            }
            val oldValue = __v_raw.IndustryId
            __v_raw.IndustryId = value
            _tRS(__v_raw, "IndustryId", oldValue, value)
        }
    override var UserRName: String?
        get() {
            return _tRG(__v_raw, "UserRName", __v_raw.UserRName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UserRName")) {
                return
            }
            val oldValue = __v_raw.UserRName
            __v_raw.UserRName = value
            _tRS(__v_raw, "UserRName", oldValue, value)
        }
    override var Email: String?
        get() {
            return _tRG(__v_raw, "Email", __v_raw.Email, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Email")) {
                return
            }
            val oldValue = __v_raw.Email
            __v_raw.Email = value
            _tRS(__v_raw, "Email", oldValue, value)
        }
    override var CompanyName: String?
        get() {
            return _tRG(__v_raw, "CompanyName", __v_raw.CompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyName")) {
                return
            }
            val oldValue = __v_raw.CompanyName
            __v_raw.CompanyName = value
            _tRS(__v_raw, "CompanyName", oldValue, value)
        }
    override var CompanyState: String?
        get() {
            return _tRG(__v_raw, "CompanyState", __v_raw.CompanyState, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyState")) {
                return
            }
            val oldValue = __v_raw.CompanyState
            __v_raw.CompanyState = value
            _tRS(__v_raw, "CompanyState", oldValue, value)
        }
    override var CompanyRepresentative: String?
        get() {
            return _tRG(__v_raw, "CompanyRepresentative", __v_raw.CompanyRepresentative, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyRepresentative")) {
                return
            }
            val oldValue = __v_raw.CompanyRepresentative
            __v_raw.CompanyRepresentative = value
            _tRS(__v_raw, "CompanyRepresentative", oldValue, value)
        }
    override var CompanyCapital: String?
        get() {
            return _tRG(__v_raw, "CompanyCapital", __v_raw.CompanyCapital, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyCapital")) {
                return
            }
            val oldValue = __v_raw.CompanyCapital
            __v_raw.CompanyCapital = value
            _tRS(__v_raw, "CompanyCapital", oldValue, value)
        }
    override var CreditCode: String?
        get() {
            return _tRG(__v_raw, "CreditCode", __v_raw.CreditCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CreditCode")) {
                return
            }
            val oldValue = __v_raw.CreditCode
            __v_raw.CreditCode = value
            _tRS(__v_raw, "CreditCode", oldValue, value)
        }
    override var CompanyzcAdrr: String?
        get() {
            return _tRG(__v_raw, "CompanyzcAdrr", __v_raw.CompanyzcAdrr, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyzcAdrr")) {
                return
            }
            val oldValue = __v_raw.CompanyzcAdrr
            __v_raw.CompanyzcAdrr = value
            _tRS(__v_raw, "CompanyzcAdrr", oldValue, value)
        }
    override var Tel: String?
        get() {
            return _tRG(__v_raw, "Tel", __v_raw.Tel, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Tel")) {
                return
            }
            val oldValue = __v_raw.Tel
            __v_raw.Tel = value
            _tRS(__v_raw, "Tel", oldValue, value)
        }
    override var ContactNumberOther: String?
        get() {
            return _tRG(__v_raw, "ContactNumberOther", __v_raw.ContactNumberOther, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContactNumberOther")) {
                return
            }
            val oldValue = __v_raw.ContactNumberOther
            __v_raw.ContactNumberOther = value
            _tRS(__v_raw, "ContactNumberOther", oldValue, value)
        }
    override var CompanyScale: String?
        get() {
            return _tRG(__v_raw, "CompanyScale", __v_raw.CompanyScale, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyScale")) {
                return
            }
            val oldValue = __v_raw.CompanyScale
            __v_raw.CompanyScale = value
            _tRS(__v_raw, "CompanyScale", oldValue, value)
        }
    override var EstablishTime: String?
        get() {
            return _tRG(__v_raw, "EstablishTime", __v_raw.EstablishTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("EstablishTime")) {
                return
            }
            val oldValue = __v_raw.EstablishTime
            __v_raw.EstablishTime = value
            _tRS(__v_raw, "EstablishTime", oldValue, value)
        }
    override var ProvinceCode: String?
        get() {
            return _tRG(__v_raw, "ProvinceCode", __v_raw.ProvinceCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceCode")) {
                return
            }
            val oldValue = __v_raw.ProvinceCode
            __v_raw.ProvinceCode = value
            _tRS(__v_raw, "ProvinceCode", oldValue, value)
        }
    override var ProvinceName: String?
        get() {
            return _tRG(__v_raw, "ProvinceName", __v_raw.ProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceName")) {
                return
            }
            val oldValue = __v_raw.ProvinceName
            __v_raw.ProvinceName = value
            _tRS(__v_raw, "ProvinceName", oldValue, value)
        }
    override var CityCode: String?
        get() {
            return _tRG(__v_raw, "CityCode", __v_raw.CityCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCode")) {
                return
            }
            val oldValue = __v_raw.CityCode
            __v_raw.CityCode = value
            _tRS(__v_raw, "CityCode", oldValue, value)
        }
    override var CityName: String?
        get() {
            return _tRG(__v_raw, "CityName", __v_raw.CityName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityName")) {
                return
            }
            val oldValue = __v_raw.CityName
            __v_raw.CityName = value
            _tRS(__v_raw, "CityName", oldValue, value)
        }
    override var CompanyLogo: String?
        get() {
            return _tRG(__v_raw, "CompanyLogo", __v_raw.CompanyLogo, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyLogo")) {
                return
            }
            val oldValue = __v_raw.CompanyLogo
            __v_raw.CompanyLogo = value
            _tRS(__v_raw, "CompanyLogo", oldValue, value)
        }
    override var DistrictCode: String?
        get() {
            return _tRG(__v_raw, "DistrictCode", __v_raw.DistrictCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("DistrictCode")) {
                return
            }
            val oldValue = __v_raw.DistrictCode
            __v_raw.DistrictCode = value
            _tRS(__v_raw, "DistrictCode", oldValue, value)
        }
    override var DistrictName: String?
        get() {
            return _tRG(__v_raw, "DistrictName", __v_raw.DistrictName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("DistrictName")) {
                return
            }
            val oldValue = __v_raw.DistrictName
            __v_raw.DistrictName = value
            _tRS(__v_raw, "DistrictName", oldValue, value)
        }
    override var FullAddress: String?
        get() {
            return _tRG(__v_raw, "FullAddress", __v_raw.FullAddress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FullAddress")) {
                return
            }
            val oldValue = __v_raw.FullAddress
            __v_raw.FullAddress = value
            _tRS(__v_raw, "FullAddress", oldValue, value)
        }
    override var CompanyElegance: String?
        get() {
            return _tRG(__v_raw, "CompanyElegance", __v_raw.CompanyElegance, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyElegance")) {
                return
            }
            val oldValue = __v_raw.CompanyElegance
            __v_raw.CompanyElegance = value
            _tRS(__v_raw, "CompanyElegance", oldValue, value)
        }
    override var Lng: Number?
        get() {
            return _tRG(__v_raw, "Lng", __v_raw.Lng, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lng")) {
                return
            }
            val oldValue = __v_raw.Lng
            __v_raw.Lng = value
            _tRS(__v_raw, "Lng", oldValue, value)
        }
    override var Lat: Number?
        get() {
            return _tRG(__v_raw, "Lat", __v_raw.Lat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Lat")) {
                return
            }
            val oldValue = __v_raw.Lat
            __v_raw.Lat = value
            _tRS(__v_raw, "Lat", oldValue, value)
        }
    override var Mobile: String?
        get() {
            return _tRG(__v_raw, "Mobile", __v_raw.Mobile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Mobile")) {
                return
            }
            val oldValue = __v_raw.Mobile
            __v_raw.Mobile = value
            _tRS(__v_raw, "Mobile", oldValue, value)
        }
    override var PassWord: String?
        get() {
            return _tRG(__v_raw, "PassWord", __v_raw.PassWord, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PassWord")) {
                return
            }
            val oldValue = __v_raw.PassWord
            __v_raw.PassWord = value
            _tRS(__v_raw, "PassWord", oldValue, value)
        }
    override var Vcode: String?
        get() {
            return _tRG(__v_raw, "Vcode", __v_raw.Vcode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Vcode")) {
                return
            }
            val oldValue = __v_raw.Vcode
            __v_raw.Vcode = value
            _tRS(__v_raw, "Vcode", oldValue, value)
        }
    override var UserType: Number?
        get() {
            return _tRG(__v_raw, "UserType", __v_raw.UserType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UserType")) {
                return
            }
            val oldValue = __v_raw.UserType
            __v_raw.UserType = value
            _tRS(__v_raw, "UserType", oldValue, value)
        }
    override var IdentityType: Number?
        get() {
            return _tRG(__v_raw, "IdentityType", __v_raw.IdentityType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IdentityType")) {
                return
            }
            val oldValue = __v_raw.IdentityType
            __v_raw.IdentityType = value
            _tRS(__v_raw, "IdentityType", oldValue, value)
        }
    override var InviteCode: String?
        get() {
            return _tRG(__v_raw, "InviteCode", __v_raw.InviteCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("InviteCode")) {
                return
            }
            val oldValue = __v_raw.InviteCode
            __v_raw.InviteCode = value
            _tRS(__v_raw, "InviteCode", oldValue, value)
        }
    override var Birthday: String?
        get() {
            return _tRG(__v_raw, "Birthday", __v_raw.Birthday, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Birthday")) {
                return
            }
            val oldValue = __v_raw.Birthday
            __v_raw.Birthday = value
            _tRS(__v_raw, "Birthday", oldValue, value)
        }
}
typealias PhoneRegisterResult = AuthResultBase
open class PhoneLoginParams (
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var PassWord: String,
) : UTSObject()
open class PhoneLoginResult (
    @JsonNotNull
    open var UserId: Number,
    @JsonNotNull
    open var Token: String,
    @JsonNotNull
    open var InviteCode: String,
    @JsonNotNull
    open var IsPassWord: Number,
    open var IndustryId: Number? = null,
    open var UserRName: String? = null,
    open var Email: String? = null,
    open var CompanyName: String? = null,
    open var CompanyState: String? = null,
    open var CompanyRepresentative: String? = null,
    open var CompanyCapital: String? = null,
    open var CreditCode: String? = null,
    open var CompanyzcAdrr: String? = null,
    open var Tel: String? = null,
    open var ContactNumberOther: String? = null,
    open var CompanyScale: String? = null,
    open var EstablishTime: String? = null,
    open var ProvinceCode: String? = null,
    open var ProvinceName: String? = null,
    open var CityCode: String? = null,
    open var CityName: String? = null,
    open var CompanyLogo: String? = null,
    open var AreaCode: String? = null,
    open var AreaName: String? = null,
    open var Address: String? = null,
    open var CompanyelegancePicNo: String? = null,
    open var StatusName: String? = null,
    open var Remark: String? = null,
    @JsonNotNull
    open var UserType: Number,
    @JsonNotNull
    open var IdentityType: Number,
    @JsonNotNull
    open var Birthday: String,
) : UTSObject()
open class WechatLoginParams (
    @JsonNotNull
    open var code: String,
    open var iv: String? = null,
    open var encryptedData: String? = null,
) : UTSObject()
open class MiniProgramRegisterParams (
    open var IndustryId: Number? = null,
    open var UserRName: String? = null,
    open var Email: String? = null,
    open var CompanyName: String? = null,
    open var CompanyState: String? = null,
    open var CompanyRepresentative: String? = null,
    open var CompanyCapital: String? = null,
    open var CreditCode: String? = null,
    open var CompanyzcAdrr: String? = null,
    open var Tel: String? = null,
    open var ContactNumberOther: String? = null,
    open var CompanyScale: String? = null,
    open var EstablishTime: String? = null,
    open var ProvinceCode: String? = null,
    open var ProvinceName: String? = null,
    open var CityCode: String? = null,
    open var CityName: String? = null,
    open var CompanyLogo: String? = null,
    open var DistrictCode: String? = null,
    open var DistrictName: String? = null,
    open var FullAddress: String? = null,
    open var CompanyElegance: String? = null,
    open var Lng: Number? = null,
    open var Lat: Number? = null,
    open var Mobile: String? = null,
    open var PassWord: String? = null,
    open var Vcode: String? = null,
    open var UserType: Number? = null,
    open var IdentityType: Number? = null,
    open var InviteCode: String? = null,
    open var Birthday: String? = null,
    @JsonNotNull
    open var code: String,
    open var iv: String? = null,
    open var encryptedData: String? = null,
) : UTSObject()
open class GetAuthStatusResult (
    open var IndustryId: Number? = null,
    open var UserRName: String? = null,
    open var Email: String? = null,
    open var CompanyName: String? = null,
    open var CompanyState: String? = null,
    open var CompanyRepresentative: String? = null,
    open var CompanyCapital: String? = null,
    open var CreditCode: String? = null,
    open var CompanyzcAdrr: String? = null,
    open var Tel: String? = null,
    open var ContactNumberOther: String? = null,
    open var CompanyScale: String? = null,
    open var EstablishTime: String? = null,
    open var ProvinceCode: String? = null,
    open var ProvinceName: String? = null,
    open var CityCode: String? = null,
    open var CityName: String? = null,
    open var CompanyLogo: String? = null,
    open var DistrictCode: String? = null,
    open var DistrictName: String? = null,
    open var FullAddress: String? = null,
    open var CompanyElegance: String? = null,
    open var Lng: Number? = null,
    open var Lat: Number? = null,
    open var Mobile: String? = null,
    open var PassWord: String? = null,
    open var Vcode: String? = null,
    open var UserType: Number? = null,
    open var IdentityType: Number? = null,
    open var InviteCode: String? = null,
    open var Birthday: String? = null,
    @JsonNotNull
    open var Status: Number,
    open var Remark: String? = null,
) : UTSObject()
enum class Api__4(override val value: String) : UTSEnumString {
    PHONE_REGISTER("/api/Login/SmsMobileRegister"),
    PHONE_LOGIN("/api/Login/LoginByMobile"),
    RE_SUBMIT("/api/Login/MobileResubmit"),
    WECHAT_LOGIN("/api/Login/SilentAutoLogin"),
    MINI_PROGRAM_REGISTER("/api/Login/BindOrSignInPhone"),
    GET_AUTH_STATUS("/api/Login/AttestationInfo")
}
fun phoneRegister(params: PhoneRegisterParams): UTSPromise<PhoneRegisterResult> {
    return request.post<PhoneRegisterResult>(Api__4.PHONE_REGISTER, params)
}
fun phoneLogin(params: PhoneLoginParams): UTSPromise<PhoneLoginResult> {
    return request.post<PhoneLoginResult>(Api__4.PHONE_LOGIN, params)
}
fun reSubmit(params: PhoneRegisterParams): UTSPromise<PhoneRegisterResult> {
    return request.post<PhoneRegisterResult>(Api__4.RE_SUBMIT, params)
}
fun wechatLoginApi(params: WechatLoginParams): UTSPromise<PhoneLoginResult> {
    return request.post<PhoneLoginResult>(Api__4.WECHAT_LOGIN, params, HttpRequestExtraOptions(customError = true))
}
fun miniProgramRegister(params: MiniProgramRegisterParams): UTSPromise<PhoneLoginResult> {
    return request.post<PhoneLoginResult>(Api__4.MINI_PROGRAM_REGISTER, params)
}
val getAuthStatus = fun(): UTSPromise<GetAuthStatusResult> {
    return request.post<GetAuthStatusResult>(Api__4.GET_AUTH_STATUS)
}
open class AuthStore {
    open var loading: Boolean by Delegates.notNull()
    open lateinit var error: String
    open lateinit var registerForm: PhoneRegisterParams
    open var userInfo: GetUserInfoResult? = null
    open var tokenValue: AuthResultBase? = null
    open lateinit var auditStatus: Number
    open lateinit var auditRemark: String
    open var isLogin: Boolean by Delegates.notNull()
    open lateinit var avatar: String
    open lateinit var username: String
    open lateinit var restoreLocalUser: () -> UTSPromise<Unit>
    open lateinit var initUser: () -> UTSPromise<Unit>
    open lateinit var setToken: (`val`: AuthResultBase, skipFetch: Boolean?, forceFetch: Boolean?) -> UTSPromise<Unit>
    open var token: AuthResultBase? = null
    open lateinit var fetchUserInfo: (force: Boolean?) -> UTSPromise<Unit>
    open lateinit var updateUserInfo: (info: GetUserInfoResult) -> UTSPromise<Unit>
    open lateinit var fetchAuthStatus: () -> UTSPromise<GetAuthStatusResult?>
    open lateinit var loginByPassword: (params: LoginParams) -> UTSPromise<PhoneLoginResult?>
    open lateinit var register: (params: PhoneRegisterParams) -> UTSPromise<PhoneLoginResult?>
    open lateinit var resubmit: (params: PhoneRegisterParams) -> UTSPromise<PhoneLoginResult?>
    open lateinit var logout: () -> UTSPromise<Unit>
    open lateinit var resetState: () -> Unit
    open lateinit var clearError: () -> Unit
    open lateinit var performStartupRouting: () -> UTSPromise<Unit>
    open lateinit var wechatLogin: () -> UTSPromise<PhoneLoginResult>
    open lateinit var getWechatLoginCode: () -> UTSPromise<String>
    open lateinit var getWechatPhoneNumber: (e: UTSJSONObject) -> UTSPromise<Unit>
    open lateinit var miniProgramRegisterAction: (identityType: Number, extraParams: UTSJSONObject?) -> UTSPromise<PhoneLoginResult?>
    constructor(loading: Any, error: Any, registerForm: PhoneRegisterParams, userInfo: Any, tokenValue: Any, auditStatus: Any, auditRemark: Any, isLogin: Any, avatar: Any, username: Any, restoreLocalUser: () -> UTSPromise<Unit>, initUser: () -> UTSPromise<Unit>, setToken: (`val`: AuthResultBase, skipFetch: Boolean?, forceFetch: Boolean?) -> UTSPromise<Unit>, token: Any, fetchUserInfo: (force: Boolean?) -> UTSPromise<Unit>, updateUserInfo: (info: GetUserInfoResult) -> UTSPromise<Unit>, fetchAuthStatus: () -> UTSPromise<GetAuthStatusResult?>, loginByPassword: (params: LoginParams) -> UTSPromise<PhoneLoginResult?>, register: (params: PhoneRegisterParams) -> UTSPromise<PhoneLoginResult?>, resubmit: (params: PhoneRegisterParams) -> UTSPromise<PhoneLoginResult?>, logout: () -> UTSPromise<Unit>, resetState: () -> Unit, clearError: () -> Unit, performStartupRouting: () -> UTSPromise<Unit>, wechatLogin: () -> UTSPromise<PhoneLoginResult>, getWechatLoginCode: () -> UTSPromise<String>, getWechatPhoneNumber: (e: UTSJSONObject) -> UTSPromise<Unit>, miniProgramRegisterAction: (identityType: Number, extraParams: UTSJSONObject?) -> UTSPromise<PhoneLoginResult?>){
        this.loading = loading as Boolean
        this.error = error as String
        this.registerForm = registerForm
        this.userInfo = userInfo as GetUserInfoResult?
        this.tokenValue = tokenValue as AuthResultBase?
        this.auditStatus = auditStatus as Number
        this.auditRemark = auditRemark as String
        this.isLogin = isLogin as Boolean
        this.avatar = avatar as String
        this.username = username as String
        this.restoreLocalUser = restoreLocalUser
        this.initUser = initUser
        this.setToken = setToken
        this.token = token as AuthResultBase?
        this.fetchUserInfo = fetchUserInfo
        this.updateUserInfo = updateUserInfo
        this.fetchAuthStatus = fetchAuthStatus
        this.loginByPassword = loginByPassword
        this.register = register
        this.resubmit = resubmit
        this.logout = logout
        this.resetState = resetState
        this.clearError = clearError
        this.performStartupRouting = performStartupRouting
        this.wechatLogin = wechatLogin
        this.getWechatLoginCode = getWechatLoginCode
        this.getWechatPhoneNumber = getWechatPhoneNumber
        this.miniProgramRegisterAction = miniProgramRegisterAction
    }
}
val useAuthStore = defineStore<AuthStore>("auth", fun(): AuthStore {
    val loading = ref(false)
    val error = ref("")
    var registerForm = reactive<PhoneRegisterParams>(PhoneRegisterParams())
    val userInfo = ref<GetUserInfoResult?>(null)
    val tokenValue = ref<AuthResultBase?>(null)
    val initialized = ref(false)
    open class PhoneAuthInfo (
        @JsonNotNull
        open var code: String,
        @JsonNotNull
        open var iv: String,
        @JsonNotNull
        open var encryptedData: String,
    ) : UTSObject()
    val phoneNumberAuthorization = reactive<PhoneAuthInfo>(PhoneAuthInfo(code = "", iv = "", encryptedData = ""))
    val auditStatus = ref<Number>(AUDIT_STATUS["PENDING"] as Number)
    val auditRemark = ref("")
    val isLogin = computed(fun(): Boolean {
        return tokenValue.value != null && userInfo.value != null
    }
    )
    val avatar = computed(fun(): String {
        return if (userInfo.value != null && userInfo.value!!.Avatar != "") {
            userInfo.value!!.Avatar
        } else {
            "/static/default-avatar.png"
        }
    }
    )
    val username = computed(fun(): String {
        return if (userInfo.value != null && userInfo.value!!.NickName != "") {
            userInfo.value!!.NickName
        } else {
            "未登录"
        }
    }
    )
    val clearUserData = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                userInfo.value = null
                tokenValue.value = null
                await(UTSPromise.all(_uA(
                    removeStorage(STORAGE_KEYS["USER_INFO"] as String),
                    removeStorage(STORAGE_KEYS["TOKEN"] as String),
                    removeStorage(STORAGE_KEYS["USER_ROLE"] as String),
                    removeStorage(STORAGE_KEYS["RECRUITER_AUTH"] as String),
                    removeStorage(STORAGE_KEYS["RECRUITER_AUTH_STATUS"] as String)
                )))
                auditStatus.value = AUDIT_STATUS["PENDING"] as Number
                auditRemark.value = ""
                signalRChatService.dispose()
                val roleStore = useRoleStore()
                roleStore.currentRole = ROLES["APPLICANT"] as String
        })
    }
    val resetState = fun(){
        loading.value = false
        error.value = ""
        registerForm = reactive<PhoneRegisterParams>(PhoneRegisterParams())
    }
    val clearError = fun(){
        error.value = ""
    }
    val restoreLocalUser = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val tokenResult = await(getStorage<AuthResultBase>(STORAGE_KEYS["TOKEN"] as String))
                if (tokenResult.success && tokenResult.value != null) {
                    tokenValue.value = tokenResult.value!!
                }
                val userInfoResult = await(getStorage<GetUserInfoResult>(STORAGE_KEYS["USER_INFO"] as String))
                if (userInfoResult.success && userInfoResult.value != null) {
                    userInfo.value = userInfoResult.value!!
                }
                val statusResult = await(getStorage<Number>(STORAGE_KEYS["RECRUITER_AUTH_STATUS"] as String))
                if (statusResult.success && statusResult.value != null) {
                    auditStatus.value = statusResult.value!!
                }
        })
    }
    val fetchUserInfo = fun(force: Boolean?): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                val _force = if (force != null) {
                    force
                } else {
                    false
                }
                if (tokenValue.value == null) {
                    return@w1
                }
                val roleStore = useRoleStore()
                if (!_force && roleStore.currentRole === (ROLES["RECRUITER"] as String) && auditStatus.value !== (AUDIT_STATUS["APPROVED"] as Number)) {
                    console.log("[Auth] 招聘者审核未通过，跳过用户信息获取")
                    return@w1
                }
                try {
                    val res = await(getUserInfo())
                    if (res != null) {
                        userInfo.value = res
                        await(setStorage(STORAGE_KEYS["USER_INFO"] as String, res))
                    }
                }
                 catch (err: Throwable) {
                    console.error("获取用户信息失败:", err)
                }
        })
    }
    val setToken = fun(kVal: AuthResultBase, skipFetch: Boolean?, forceFetch: Boolean?): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val _skipFetch = if (skipFetch != null) {
                    skipFetch
                } else {
                    false
                }
                val _forceFetch = if (forceFetch != null) {
                    forceFetch
                } else {
                    false
                }
                tokenValue.value = kVal
                await(setStorage(STORAGE_KEYS["TOKEN"] as String, kVal))
                if (!_skipFetch) {
                    await(fetchUserInfo(_forceFetch))
                }
        })
    }
    val token = computed(fun(): AuthResultBase? {
        return tokenValue.value
    }
    )
    val fetchAuthStatus = fun(): UTSPromise<GetAuthStatusResult?> {
        return wrapUTSPromise(suspend w1@{
                try {
                    val res = await(getAuthStatus())
                    if (res != null) {
                        auditStatus.value = res.Status
                        auditRemark.value = res.Remark ?: ""
                        await(setStorage(STORAGE_KEYS["RECRUITER_AUTH_STATUS"] as String, res.Status))
                        if (res.Status === (AUDIT_STATUS["REJECTED"] as Number)) {
                            registerForm.IndustryId = res.IndustryId ?: registerForm.IndustryId
                            registerForm.UserRName = res.UserRName ?: registerForm.UserRName
                            registerForm.Email = res.Email ?: registerForm.Email
                            registerForm.CompanyName = res.CompanyName ?: registerForm.CompanyName
                            registerForm.CompanyState = res.CompanyState ?: registerForm.CompanyState
                            registerForm.CompanyRepresentative = res.CompanyRepresentative ?: registerForm.CompanyRepresentative
                            registerForm.CompanyCapital = res.CompanyCapital ?: registerForm.CompanyCapital
                            registerForm.CreditCode = res.CreditCode ?: registerForm.CreditCode
                            registerForm.CompanyzcAdrr = res.CompanyzcAdrr ?: registerForm.CompanyzcAdrr
                            registerForm.Tel = res.Tel ?: registerForm.Tel
                            registerForm.ContactNumberOther = res.ContactNumberOther ?: registerForm.ContactNumberOther
                            registerForm.CompanyScale = res.CompanyScale ?: registerForm.CompanyScale
                            registerForm.EstablishTime = res.EstablishTime ?: registerForm.EstablishTime
                            registerForm.ProvinceCode = res.ProvinceCode ?: registerForm.ProvinceCode
                            registerForm.ProvinceName = res.ProvinceName ?: registerForm.ProvinceName
                            registerForm.CityCode = res.CityCode ?: registerForm.CityCode
                            registerForm.CityName = res.CityName ?: registerForm.CityName
                            registerForm.CompanyLogo = res.CompanyLogo ?: registerForm.CompanyLogo
                            registerForm.DistrictCode = res.DistrictCode ?: registerForm.DistrictCode
                            registerForm.DistrictName = res.DistrictName ?: registerForm.DistrictName
                            registerForm.FullAddress = res.FullAddress ?: registerForm.FullAddress
                            registerForm.CompanyElegance = res.CompanyElegance ?: registerForm.CompanyElegance
                            registerForm.Lng = res.Lng ?: registerForm.Lng
                            registerForm.Lat = res.Lat ?: registerForm.Lat
                            registerForm.Mobile = res.Mobile ?: registerForm.Mobile
                            registerForm.PassWord = res.PassWord ?: registerForm.PassWord
                            registerForm.Vcode = res.Vcode ?: registerForm.Vcode
                            registerForm.UserType = res.UserType ?: registerForm.UserType
                            registerForm.IdentityType = res.IdentityType ?: registerForm.IdentityType
                            registerForm.InviteCode = res.InviteCode ?: registerForm.InviteCode
                            registerForm.Birthday = res.Birthday ?: registerForm.Birthday
                        }
                        return@w1 res
                    }
                }
                 catch (err: Throwable) {
                    console.error("获取认证状态失败:", err)
                }
                return@w1 null
        })
    }
    val updateUserInfo = fun(info: GetUserInfoResult): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                userInfo.value = info
                await(setStorage(STORAGE_KEYS["USER_INFO"] as String, info))
        })
    }
    val initUser = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (initialized.value) {
                    return@w1
                }
                try {
                    await(restoreLocalUser())
                    if (tokenValue.value == null) {
                        initialized.value = true
                        return@w1
                    }
                    val roleStore = useRoleStore()
                    if (!roleStore.initialized) {
                        await(roleStore.initRole())
                    }
                    val isRecruiter = roleStore.currentRole === (ROLES["RECRUITER"] as String)
                    val statusResult = await(getStorage<Number>(STORAGE_KEYS["RECRUITER_AUTH_STATUS"] as String))
                    if (statusResult.success && statusResult.value != null) {
                        auditStatus.value = statusResult.value!!
                    }
                    if (isRecruiter) {
                        if (auditStatus.value !== (AUDIT_STATUS["APPROVED"] as Number)) {
                            initialized.value = true
                            return@w1
                        }
                    }
                    val res = await(getUserInfo())
                    signalRChatService.initialize(getSignalRUrl())
                    if (res != null) {
                        userInfo.value = res
                        await(setStorage(STORAGE_KEYS["USER_INFO"] as String, res))
                    } else {
                        console.warn("获取用户信息失败，Token 可能已过期")
                        await(clearUserData())
                    }
                }
                 catch (err: Throwable) {
                    console.error("初始化用户信息失败，执行退出登录:", err)
                    await(clearUserData())
                }
                 finally {
                    initialized.value = true
                }
        })
    }
    val handleAuthSuccess = fun(res: PhoneLoginResult, identityTypeParam: Number?): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                val tokenData = AuthResultBase(Token = res.Token, UserId = res.UserId, InviteCode = res.InviteCode, IsPassWord = res.IsPassWord)
                val shouldForceFetchUserInfo = identityTypeParam == null
                val isRecruiterRegister = identityTypeParam === 1
                await(setToken(tokenData, isRecruiterRegister, shouldForceFetchUserInfo))
                val identityType = if (identityTypeParam != null) {
                    identityTypeParam
                } else {
                    if (userInfo.value != null) {
                        userInfo.value!!.IdentityType
                    } else {
                        0
                    }
                }
                val roleStore = useRoleStore()
                val role = if (identityType === 1) {
                    (ROLES["RECRUITER"] as String)
                } else {
                    (ROLES["APPLICANT"] as String)
                }
                await(roleStore.setRole(role))
                if (identityType === 1) {
                    val statusRes = await(fetchAuthStatus())
                    val status = if (statusRes != null) {
                        statusRes.Status
                    } else {
                        auditStatus.value
                    }
                    if (status === (AUDIT_STATUS["APPROVED"] as Number)) {
                        await(fetchUserInfo(true))
                        uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["RECRUIT_HOME"] as String))
                        return@w1
                    }
                    uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["WAIT_AUDIT"] as String))
                    return@w1
                }
                uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["APPLY_HOME"] as String))
        })
    }
    val loginByPassword = fun(params: LoginParams): UTSPromise<PhoneLoginResult?> {
        return wrapUTSPromise(suspend w1@{
                loading.value = true
                error.value = ""
                var result: PhoneLoginResult? = null
                try {
                    val loginParams: PhoneLoginParams = PhoneLoginParams(Mobile = params.Mobile, PassWord = params.PassWord)
                    val res = await(phoneLogin(loginParams))
                    if (res != null) {
                        await(handleAuthSuccess(res, null))
                        result = res
                    }
                }
                 catch (err: Throwable) {
                    val errorMsg = if ((err as UTSError).message != null) {
                        (err as UTSError).message
                    } else {
                        "登录失败，请稍后重试"
                    }
                    error.value = errorMsg
                    uni_showToast(ShowToastOptions(title = errorMsg, icon = "none"))
                    throw UTSError(errorMsg)
                }
                 finally {
                    loading.value = false
                }
                return@w1 result
        })
    }
    val performStartupRouting = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (tokenValue.value == null) {
                    return@w1
                }
                val roleStore = useRoleStore()
                val identityType = if (userInfo.value != null) {
                    userInfo.value!!.IdentityType
                } else {
                    if (roleStore.currentRole === (ROLES["RECRUITER"] as String)) {
                        1
                    } else {
                        0
                    }
                }
                if (identityType === 1) {
                    val statusResult = await(getStorage<Number>(STORAGE_KEYS["RECRUITER_AUTH_STATUS"] as String))
                    val status = if (statusResult.success && statusResult.value != null) {
                        statusResult.value!!
                    } else {
                        (AUDIT_STATUS["PENDING"] as Number)
                    }
                    if (status === (AUDIT_STATUS["APPROVED"] as Number)) {} else {
                        uni_reLaunch(ReLaunchOptions(url = PAGE_PATHS["WAIT_AUDIT"] as String))
                    }
                }
        })
    }
    val register = fun(params: PhoneRegisterParams): UTSPromise<PhoneLoginResult?> {
        return wrapUTSPromise(suspend w1@{
                loading.value = true
                error.value = ""
                try {
                    val res = await(phoneRegister(params))
                    if (res != null) {
                        await(handleAuthSuccess(res as PhoneLoginResult, if (params.IdentityType != null) {
                            params.IdentityType!!
                        } else {
                            0
                        }
                        ))
                        return@w1 res as PhoneLoginResult
                    }
                }
                 catch (err: Throwable) {
                    val errorMsg = if ((err as UTSError).message != null) {
                        (err as UTSError).message
                    } else {
                        "注册失败，请稍后重试"
                    }
                    error.value = errorMsg
                    showError(errorMsg)
                }
                 finally {
                    loading.value = false
                }
                return@w1 null
        })
    }
    val resubmit = fun(params: PhoneRegisterParams): UTSPromise<PhoneLoginResult?> {
        return wrapUTSPromise(suspend w1@{
                loading.value = true
                error.value = ""
                try {
                    val res = await(reSubmit(params))
                    if (res != null) {
                        console.log("重新提交成功")
                        return@w1 res as PhoneLoginResult
                    }
                }
                 catch (err: Throwable) {
                    val errorMsg = if ((err as UTSError).message != null) {
                        (err as UTSError).message
                    } else {
                        "重新提交失败，请稍后重试"
                    }
                    error.value = errorMsg
                    console.error("重新提交失败:", err)
                    uni_showToast(ShowToastOptions(title = errorMsg, icon = "none"))
                    throw err
                }
                 finally {
                    loading.value = false
                }
                return@w1 null
        })
    }
    val getWechatLoginCode = fun(): UTSPromise<String> {
        return wrapUTSPromise(suspend {
                showError("当前平台不支持微信登录")
                throw UTSError("当前平台不支持微信登录")
        })
    }
    val getWechatPhoneNumber = fun(e: UTSJSONObject): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val detail = e["detail"] as UTSJSONObject
                val errMsg = detail["errMsg"] as String?
                val encryptedData = detail["encryptedData"] as String?
                val iv = detail["iv"] as String?
                if (errMsg == null || errMsg.indexOf("ok") < 0) {
                    showError("授权失败")
                    throw UTSError("授权失败")
                }
                phoneNumberAuthorization.encryptedData = if (encryptedData != null) {
                    encryptedData
                } else {
                    ""
                }
                phoneNumberAuthorization.iv = if (iv != null) {
                    iv
                } else {
                    ""
                }
        })
    }
    val resolveMiniProgramUserType = fun(identityType: Number, extraParams: UTSJSONObject): Number {
        if (identityType !== 1) {
            return 0
        }
        val rawUserType = extraParams["UserType"] as Number?
        return if (rawUserType != null && rawUserType > 0) {
            rawUserType
        } else {
            1
        }
    }
    val miniProgramRegisterAction = fun(identityType: Number, extraParams: UTSJSONObject?): UTSPromise<PhoneLoginResult?> {
        return wrapUTSPromise(suspend w1@{
                val nextParams = if (extraParams != null) {
                    extraParams
                } else {
                    (_uO())
                }
                loading.value = true
                error.value = ""
                try {
                    val loginCode = await(getWechatLoginCode())
                    val registerParams = MiniProgramRegisterParams(code = loginCode, iv = phoneNumberAuthorization.iv, encryptedData = phoneNumberAuthorization.encryptedData, IdentityType = identityType, UserType = resolveMiniProgramUserType(identityType, nextParams))
                    val paramsObj = registerParams as UTSJSONObject
                    for(key in resolveUTSKeyIterator(nextParams)){
                        paramsObj[key] = nextParams[key]
                    }
                    val res = await(miniProgramRegister(registerParams))
                    if (res != null) {
                        await(handleAuthSuccess(res, identityType))
                        return@w1 res
                    }
                }
                 catch (err: Throwable) {
                    val errorMsg = if ((err as UTSError).message != null) {
                        (err as UTSError).message
                    } else {
                        "小程序注册失败"
                    }
                    error.value = errorMsg
                    showError(errorMsg)
                }
                 finally {
                    loading.value = false
                }
                return@w1 null
        })
    }
    val wechatLogin = fun(): UTSPromise<PhoneLoginResult> {
        return wrapUTSPromise(suspend w1@{
                loading.value = true
                var loginResult: PhoneLoginResult? = null
                try {
                    val code = await(getWechatLoginCode())
                    val result = await(wechatLoginApi(WechatLoginParams(code = code)))
                    if (result != null) {
                        await(handleAuthSuccess(result, null))
                        loginResult = result
                    }
                }
                 finally {
                    loading.value = false
                }
                if (loginResult == null) {
                    throw UTSError("登录异常")
                }
                return@w1 loginResult!!
        })
    }
    val logout = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                try {
                    await(clearUserData())
                    resetState()
                    console.log("退出登录成功")
                    uni_reLaunch(ReLaunchOptions(url = "/pages/auth/login"))
                }
                 catch (err: Throwable) {
                    console.error("退出登录失败:", err)
                }
        })
    }
    return AuthStore(loading, error, registerForm, userInfo, tokenValue, auditStatus, auditRemark, isLogin, avatar, username, restoreLocalUser, initUser, setToken, token, fetchUserInfo, updateUserInfo, fetchAuthStatus, loginByPassword, register, resubmit, logout, resetState, clearError, performStartupRouting, wechatLogin, getWechatLoginCode, getWechatPhoneNumber, miniProgramRegisterAction)
}
)
val useAppStore = defineStore<AppStore>("app", fun(): AppStore {
    val webConfig = ref<GetWebConfigurationResult?>(null)
    val globalMessageListenersRegistered = ref(false)
    val fetchWebConfig = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                try {
                    val cacheResult = await(getStorage<GetWebConfigurationResult>(STORAGE_KEYS["APP_CONFIG"] as String))
                    if (cacheResult.success && cacheResult.value != null) {
                        webConfig.value = cacheResult.value!!
                        console.log("网站配置已从本地缓存恢复")
                        return@w1
                    }
                    val res = await(getWebConfiguration())
                    if (res != null) {
                        webConfig.value = res
                        await(setStorage(STORAGE_KEYS["APP_CONFIG"] as String, res))
                        console.log("网站配置请求成功并已缓存")
                    }
                }
                 catch (err: Throwable) {
                    console.error("获取网站配置失败:", err)
                }
        })
    }
    val taskRules = computed(fun(): UTSArray<String> {
        val rule = webConfig.value?.TaskRule
        console.log("taskRules", rule)
        return if (rule != null && rule != "") {
            rule.split("\n")
        } else {
            _uA()
        }
    }
    )
    val lotteryRules = computed(fun(): UTSArray<String> {
        val rule = webConfig.value?.LotteryRule
        return if (rule != null && rule != "") {
            rule.split("\n")
        } else {
            _uA()
        }
    }
    )
    val lotteryTicketRules = computed(fun(): UTSArray<String> {
        val rule = webConfig.value?.LotteryTicketRule
        return if (rule != null && rule != "") {
            rule.split("\n")
        } else {
            _uA()
        }
    }
    )
    val initApp = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                await(fetchWebConfig())
                console.log("应用基础配置初始化完成")
        })
    }
    val handleRefreshMessage = fun(payload: Any){
        console.log("[GlobalMessage] 收到 列表消息:", payload)
        if (payload === true) {
            val messageStore = useMessageStore()
            messageStore.refreshMessageData()
        }
    }
    val handleUserMarketingMessageCount = fun(payload: Any){
        console.log("[GlobalMessage] 收到 系统通知:", payload)
        val messageStore = useMessageStore()
        messageStore.setNoticeUnreadCount(if (payload != null) {
            (payload as Number)
        } else {
            0
        }
        )
    }
    val handleSigninMessageCount = fun(payload: Any){
        console.log("[GlobalMessage] 收到 签到通知:", payload)
        val messageStore = useMessageStore()
        messageStore.setSignInUnreadCount(if (payload != null) {
            (payload as Number)
        } else {
            0
        }
        )
    }
    val setupGlobalMessageListening = fun(){
        if (globalMessageListenersRegistered.value) {
            return
        }
        signalRChatService.addRefreshMessageListener(handleRefreshMessage)
        signalRChatService.addUserMarketingMessageCountListener(handleUserMarketingMessageCount)
        signalRChatService.addSigninMessageCountListener(handleSigninMessageCount)
        globalMessageListenersRegistered.value = true
    }
    val ensureGlobalMessageConnection = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                val authStore = useAuthStore()
                if (!authStore.isLogin) {
                    return@w1
                }
                try {
                    await(signalRChatService.ensureConnectionReady(8000))
                }
                 catch (error: Throwable) {
                    console.error("[GlobalMessage] 前台恢复消息连接失败:", error)
                }
        })
    }
    return AppStore(webConfig, globalMessageListenersRegistered, taskRules, lotteryRules, lotteryTicketRules, fetchWebConfig, initApp, setupGlobalMessageListening, ensureGlobalMessageConnection)
}
)
open class GetAddressesResult (
    open var Code: String? = null,
    open var Name: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetAddressesResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetAddressesResultReactiveObject : GetAddressesResult, IUTSReactive<GetAddressesResult> {
    override var __v_raw: GetAddressesResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetAddressesResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Code = __v_raw.Code, Name = __v_raw.Name) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetAddressesResultReactiveObject {
        return GetAddressesResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Code: String?
        get() {
            return _tRG(__v_raw, "Code", __v_raw.Code, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Code")) {
                return
            }
            val oldValue = __v_raw.Code
            __v_raw.Code = value
            _tRS(__v_raw, "Code", oldValue, value)
        }
    override var Name: String?
        get() {
            return _tRG(__v_raw, "Name", __v_raw.Name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Name")) {
                return
            }
            val oldValue = __v_raw.Name
            __v_raw.Name = value
            _tRS(__v_raw, "Name", oldValue, value)
        }
}
enum class Api__5(override val value: String) : UTSEnumString {
    GET_ADDRESSES("/api/Area/GetArea"),
    ADD_ADDRESS("/api/Address/AddAddress"),
    EDIT_ADDRESS("/api/Address/UpdateAddress"),
    DELETE_ADDRESS("/api/Address/DeleteAddress"),
    GET_ADDRESS_DETAIL("/api/Address/GetInfo"),
    SET_DEFAULT_ADDRESS("/api/Address/SetDefaultaddress"),
    GET_ADDRESS_LIST("/api/Address/AddressList")
}
fun getAddresses(params: GetAddressesParams): UTSPromise<UTSArray<GetAddressesResult>> {
    return request.post<UTSArray<GetAddressesResult>>(Api__5.GET_ADDRESSES, params)
}
open class PublishFindResponse (
    @JsonNotNull
    open var FindId: Number,
) : UTSObject()
open class GetFindListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var IsFollow: Number,
    @JsonNotNull
    open var Keywords: String,
    open var IsMy: Number? = null,
    open var myType: Number? = null,
    open var IsHot: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetFindListParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetFindListParamsReactiveObject : GetFindListParams, IUTSReactive<GetFindListParams> {
    override var __v_raw: GetFindListParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetFindListParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, IsFollow = __v_raw.IsFollow, Keywords = __v_raw.Keywords, IsMy = __v_raw.IsMy, myType = __v_raw.myType, IsHot = __v_raw.IsHot) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetFindListParamsReactiveObject {
        return GetFindListParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var IsFollow: Number
        get() {
            return _tRG(__v_raw, "IsFollow", __v_raw.IsFollow, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsFollow")) {
                return
            }
            val oldValue = __v_raw.IsFollow
            __v_raw.IsFollow = value
            _tRS(__v_raw, "IsFollow", oldValue, value)
        }
    override var Keywords: String
        get() {
            return _tRG(__v_raw, "Keywords", __v_raw.Keywords, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Keywords")) {
                return
            }
            val oldValue = __v_raw.Keywords
            __v_raw.Keywords = value
            _tRS(__v_raw, "Keywords", oldValue, value)
        }
    override var IsMy: Number?
        get() {
            return _tRG(__v_raw, "IsMy", __v_raw.IsMy, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsMy")) {
                return
            }
            val oldValue = __v_raw.IsMy
            __v_raw.IsMy = value
            _tRS(__v_raw, "IsMy", oldValue, value)
        }
    override var myType: Number?
        get() {
            return _tRG(__v_raw, "myType", __v_raw.myType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("myType")) {
                return
            }
            val oldValue = __v_raw.myType
            __v_raw.myType = value
            _tRS(__v_raw, "myType", oldValue, value)
        }
    override var IsHot: Number?
        get() {
            return _tRG(__v_raw, "IsHot", __v_raw.IsHot, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsHot")) {
                return
            }
            val oldValue = __v_raw.IsHot
            __v_raw.IsHot = value
            _tRS(__v_raw, "IsHot", oldValue, value)
        }
}
open class FindBaseResponse (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var ContentDetails: String,
    @JsonNotNull
    open var Avatar: String,
    @JsonNotNull
    open var LikeNum: Number,
    @JsonNotNull
    open var CommtentNum: Number,
    @JsonNotNull
    open var NickName: String,
    @JsonNotNull
    open var IsLike: Number,
    @JsonNotNull
    open var AddTime: String,
    @JsonNotNull
    open var ImgList: UTSArray<String>,
    @JsonNotNull
    open var IsFollow: Number,
    @JsonNotNull
    open var IsMy: Number,
    @JsonNotNull
    open var Age: String,
    @JsonNotNull
    open var InCity: String,
    @JsonNotNull
    open var ShareNum: Number,
    @JsonNotNull
    open var Status: Number,
    @JsonNotNull
    open var Reason: String,
    @JsonNotNull
    open var MemberId: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return FindBaseResponseReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class FindBaseResponseReactiveObject : FindBaseResponse, IUTSReactive<FindBaseResponse> {
    override var __v_raw: FindBaseResponse
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: FindBaseResponse, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, ContentDetails = __v_raw.ContentDetails, Avatar = __v_raw.Avatar, LikeNum = __v_raw.LikeNum, CommtentNum = __v_raw.CommtentNum, NickName = __v_raw.NickName, IsLike = __v_raw.IsLike, AddTime = __v_raw.AddTime, ImgList = __v_raw.ImgList, IsFollow = __v_raw.IsFollow, IsMy = __v_raw.IsMy, Age = __v_raw.Age, InCity = __v_raw.InCity, ShareNum = __v_raw.ShareNum, Status = __v_raw.Status, Reason = __v_raw.Reason, MemberId = __v_raw.MemberId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): FindBaseResponseReactiveObject {
        return FindBaseResponseReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var ContentDetails: String
        get() {
            return _tRG(__v_raw, "ContentDetails", __v_raw.ContentDetails, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetails")) {
                return
            }
            val oldValue = __v_raw.ContentDetails
            __v_raw.ContentDetails = value
            _tRS(__v_raw, "ContentDetails", oldValue, value)
        }
    override var Avatar: String
        get() {
            return _tRG(__v_raw, "Avatar", __v_raw.Avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Avatar")) {
                return
            }
            val oldValue = __v_raw.Avatar
            __v_raw.Avatar = value
            _tRS(__v_raw, "Avatar", oldValue, value)
        }
    override var LikeNum: Number
        get() {
            return _tRG(__v_raw, "LikeNum", __v_raw.LikeNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LikeNum")) {
                return
            }
            val oldValue = __v_raw.LikeNum
            __v_raw.LikeNum = value
            _tRS(__v_raw, "LikeNum", oldValue, value)
        }
    override var CommtentNum: Number
        get() {
            return _tRG(__v_raw, "CommtentNum", __v_raw.CommtentNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CommtentNum")) {
                return
            }
            val oldValue = __v_raw.CommtentNum
            __v_raw.CommtentNum = value
            _tRS(__v_raw, "CommtentNum", oldValue, value)
        }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
    override var IsLike: Number
        get() {
            return _tRG(__v_raw, "IsLike", __v_raw.IsLike, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsLike")) {
                return
            }
            val oldValue = __v_raw.IsLike
            __v_raw.IsLike = value
            _tRS(__v_raw, "IsLike", oldValue, value)
        }
    override var AddTime: String
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var ImgList: UTSArray<String>
        get() {
            return _tRG(__v_raw, "ImgList", __v_raw.ImgList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ImgList")) {
                return
            }
            val oldValue = __v_raw.ImgList
            __v_raw.ImgList = value
            _tRS(__v_raw, "ImgList", oldValue, value)
        }
    override var IsFollow: Number
        get() {
            return _tRG(__v_raw, "IsFollow", __v_raw.IsFollow, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsFollow")) {
                return
            }
            val oldValue = __v_raw.IsFollow
            __v_raw.IsFollow = value
            _tRS(__v_raw, "IsFollow", oldValue, value)
        }
    override var IsMy: Number
        get() {
            return _tRG(__v_raw, "IsMy", __v_raw.IsMy, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsMy")) {
                return
            }
            val oldValue = __v_raw.IsMy
            __v_raw.IsMy = value
            _tRS(__v_raw, "IsMy", oldValue, value)
        }
    override var Age: String
        get() {
            return _tRG(__v_raw, "Age", __v_raw.Age, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Age")) {
                return
            }
            val oldValue = __v_raw.Age
            __v_raw.Age = value
            _tRS(__v_raw, "Age", oldValue, value)
        }
    override var InCity: String
        get() {
            return _tRG(__v_raw, "InCity", __v_raw.InCity, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("InCity")) {
                return
            }
            val oldValue = __v_raw.InCity
            __v_raw.InCity = value
            _tRS(__v_raw, "InCity", oldValue, value)
        }
    override var ShareNum: Number
        get() {
            return _tRG(__v_raw, "ShareNum", __v_raw.ShareNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ShareNum")) {
                return
            }
            val oldValue = __v_raw.ShareNum
            __v_raw.ShareNum = value
            _tRS(__v_raw, "ShareNum", oldValue, value)
        }
    override var Status: Number
        get() {
            return _tRG(__v_raw, "Status", __v_raw.Status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Status")) {
                return
            }
            val oldValue = __v_raw.Status
            __v_raw.Status = value
            _tRS(__v_raw, "Status", oldValue, value)
        }
    override var Reason: String
        get() {
            return _tRG(__v_raw, "Reason", __v_raw.Reason, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Reason")) {
                return
            }
            val oldValue = __v_raw.Reason
            __v_raw.Reason = value
            _tRS(__v_raw, "Reason", oldValue, value)
        }
    override var MemberId: Number
        get() {
            return _tRG(__v_raw, "MemberId", __v_raw.MemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberId")) {
                return
            }
            val oldValue = __v_raw.MemberId
            __v_raw.MemberId = value
            _tRS(__v_raw, "MemberId", oldValue, value)
        }
}
typealias GetFindXqParams = PublishFindResponse
typealias GetFindXqResponse = FindBaseResponse
open class GetCommentListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var FindId: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetCommentListParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetCommentListParamsReactiveObject : GetCommentListParams, IUTSReactive<GetCommentListParams> {
    override var __v_raw: GetCommentListParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetCommentListParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, FindId = __v_raw.FindId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetCommentListParamsReactiveObject {
        return GetCommentListParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var FindId: Number
        get() {
            return _tRG(__v_raw, "FindId", __v_raw.FindId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FindId")) {
                return
            }
            val oldValue = __v_raw.FindId
            __v_raw.FindId = value
            _tRS(__v_raw, "FindId", oldValue, value)
        }
}
open class CommentReply (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var FindFristId: Number,
    @JsonNotNull
    open var FindId: Number,
    @JsonNotNull
    open var ParentCommentId: Number,
    @JsonNotNull
    open var Comment: String,
    @JsonNotNull
    open var PicList: String,
    @JsonNotNull
    open var AddTime: String,
    @JsonNotNull
    open var MemberName: String,
    @JsonNotNull
    open var MemberHead: String,
    @JsonNotNull
    open var IsLike: Number,
    @JsonNotNull
    open var IsMy: Number,
    @JsonNotNull
    open var MemberId: String,
    @JsonNotNull
    open var IsLzRead: Number,
    @JsonNotNull
    open var LikeNum: Number,
    @JsonNotNull
    open var LzId: Number,
    @JsonNotNull
    open var Reason: String,
    @JsonNotNull
    open var Status: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return CommentReplyReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class CommentReplyReactiveObject : CommentReply, IUTSReactive<CommentReply> {
    override var __v_raw: CommentReply
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: CommentReply, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, FindFristId = __v_raw.FindFristId, FindId = __v_raw.FindId, ParentCommentId = __v_raw.ParentCommentId, Comment = __v_raw.Comment, PicList = __v_raw.PicList, AddTime = __v_raw.AddTime, MemberName = __v_raw.MemberName, MemberHead = __v_raw.MemberHead, IsLike = __v_raw.IsLike, IsMy = __v_raw.IsMy, MemberId = __v_raw.MemberId, IsLzRead = __v_raw.IsLzRead, LikeNum = __v_raw.LikeNum, LzId = __v_raw.LzId, Reason = __v_raw.Reason, Status = __v_raw.Status) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): CommentReplyReactiveObject {
        return CommentReplyReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var FindFristId: Number
        get() {
            return _tRG(__v_raw, "FindFristId", __v_raw.FindFristId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FindFristId")) {
                return
            }
            val oldValue = __v_raw.FindFristId
            __v_raw.FindFristId = value
            _tRS(__v_raw, "FindFristId", oldValue, value)
        }
    override var FindId: Number
        get() {
            return _tRG(__v_raw, "FindId", __v_raw.FindId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FindId")) {
                return
            }
            val oldValue = __v_raw.FindId
            __v_raw.FindId = value
            _tRS(__v_raw, "FindId", oldValue, value)
        }
    override var ParentCommentId: Number
        get() {
            return _tRG(__v_raw, "ParentCommentId", __v_raw.ParentCommentId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ParentCommentId")) {
                return
            }
            val oldValue = __v_raw.ParentCommentId
            __v_raw.ParentCommentId = value
            _tRS(__v_raw, "ParentCommentId", oldValue, value)
        }
    override var Comment: String
        get() {
            return _tRG(__v_raw, "Comment", __v_raw.Comment, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Comment")) {
                return
            }
            val oldValue = __v_raw.Comment
            __v_raw.Comment = value
            _tRS(__v_raw, "Comment", oldValue, value)
        }
    override var PicList: String
        get() {
            return _tRG(__v_raw, "PicList", __v_raw.PicList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PicList")) {
                return
            }
            val oldValue = __v_raw.PicList
            __v_raw.PicList = value
            _tRS(__v_raw, "PicList", oldValue, value)
        }
    override var AddTime: String
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var MemberName: String
        get() {
            return _tRG(__v_raw, "MemberName", __v_raw.MemberName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberName")) {
                return
            }
            val oldValue = __v_raw.MemberName
            __v_raw.MemberName = value
            _tRS(__v_raw, "MemberName", oldValue, value)
        }
    override var MemberHead: String
        get() {
            return _tRG(__v_raw, "MemberHead", __v_raw.MemberHead, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberHead")) {
                return
            }
            val oldValue = __v_raw.MemberHead
            __v_raw.MemberHead = value
            _tRS(__v_raw, "MemberHead", oldValue, value)
        }
    override var IsLike: Number
        get() {
            return _tRG(__v_raw, "IsLike", __v_raw.IsLike, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsLike")) {
                return
            }
            val oldValue = __v_raw.IsLike
            __v_raw.IsLike = value
            _tRS(__v_raw, "IsLike", oldValue, value)
        }
    override var IsMy: Number
        get() {
            return _tRG(__v_raw, "IsMy", __v_raw.IsMy, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsMy")) {
                return
            }
            val oldValue = __v_raw.IsMy
            __v_raw.IsMy = value
            _tRS(__v_raw, "IsMy", oldValue, value)
        }
    override var MemberId: String
        get() {
            return _tRG(__v_raw, "MemberId", __v_raw.MemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberId")) {
                return
            }
            val oldValue = __v_raw.MemberId
            __v_raw.MemberId = value
            _tRS(__v_raw, "MemberId", oldValue, value)
        }
    override var IsLzRead: Number
        get() {
            return _tRG(__v_raw, "IsLzRead", __v_raw.IsLzRead, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsLzRead")) {
                return
            }
            val oldValue = __v_raw.IsLzRead
            __v_raw.IsLzRead = value
            _tRS(__v_raw, "IsLzRead", oldValue, value)
        }
    override var LikeNum: Number
        get() {
            return _tRG(__v_raw, "LikeNum", __v_raw.LikeNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LikeNum")) {
                return
            }
            val oldValue = __v_raw.LikeNum
            __v_raw.LikeNum = value
            _tRS(__v_raw, "LikeNum", oldValue, value)
        }
    override var LzId: Number
        get() {
            return _tRG(__v_raw, "LzId", __v_raw.LzId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LzId")) {
                return
            }
            val oldValue = __v_raw.LzId
            __v_raw.LzId = value
            _tRS(__v_raw, "LzId", oldValue, value)
        }
    override var Reason: String
        get() {
            return _tRG(__v_raw, "Reason", __v_raw.Reason, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Reason")) {
                return
            }
            val oldValue = __v_raw.Reason
            __v_raw.Reason = value
            _tRS(__v_raw, "Reason", oldValue, value)
        }
    override var Status: Number
        get() {
            return _tRG(__v_raw, "Status", __v_raw.Status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Status")) {
                return
            }
            val oldValue = __v_raw.Status
            __v_raw.Status = value
            _tRS(__v_raw, "Status", oldValue, value)
        }
}
open class GetCommentListResponse (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var MemberName: String,
    @JsonNotNull
    open var MemberHead: String,
    @JsonNotNull
    open var FinId: Number,
    @JsonNotNull
    open var Comment: String,
    @JsonNotNull
    open var ParentCommentId: Number,
    @JsonNotNull
    open var AddTime: String,
    @JsonNotNull
    open var ImgList: UTSArray<String>,
    @JsonNotNull
    open var IsMy: Number,
    @JsonNotNull
    open var ReplyNum: Number,
    @JsonNotNull
    open var MyCommnetList: UTSArray<CommentReply>,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetCommentListResponseReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetCommentListResponseReactiveObject : GetCommentListResponse, IUTSReactive<GetCommentListResponse> {
    override var __v_raw: GetCommentListResponse
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetCommentListResponse, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, MemberName = __v_raw.MemberName, MemberHead = __v_raw.MemberHead, FinId = __v_raw.FinId, Comment = __v_raw.Comment, ParentCommentId = __v_raw.ParentCommentId, AddTime = __v_raw.AddTime, ImgList = __v_raw.ImgList, IsMy = __v_raw.IsMy, ReplyNum = __v_raw.ReplyNum, MyCommnetList = __v_raw.MyCommnetList) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetCommentListResponseReactiveObject {
        return GetCommentListResponseReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var MemberName: String
        get() {
            return _tRG(__v_raw, "MemberName", __v_raw.MemberName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberName")) {
                return
            }
            val oldValue = __v_raw.MemberName
            __v_raw.MemberName = value
            _tRS(__v_raw, "MemberName", oldValue, value)
        }
    override var MemberHead: String
        get() {
            return _tRG(__v_raw, "MemberHead", __v_raw.MemberHead, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberHead")) {
                return
            }
            val oldValue = __v_raw.MemberHead
            __v_raw.MemberHead = value
            _tRS(__v_raw, "MemberHead", oldValue, value)
        }
    override var FinId: Number
        get() {
            return _tRG(__v_raw, "FinId", __v_raw.FinId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FinId")) {
                return
            }
            val oldValue = __v_raw.FinId
            __v_raw.FinId = value
            _tRS(__v_raw, "FinId", oldValue, value)
        }
    override var Comment: String
        get() {
            return _tRG(__v_raw, "Comment", __v_raw.Comment, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Comment")) {
                return
            }
            val oldValue = __v_raw.Comment
            __v_raw.Comment = value
            _tRS(__v_raw, "Comment", oldValue, value)
        }
    override var ParentCommentId: Number
        get() {
            return _tRG(__v_raw, "ParentCommentId", __v_raw.ParentCommentId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ParentCommentId")) {
                return
            }
            val oldValue = __v_raw.ParentCommentId
            __v_raw.ParentCommentId = value
            _tRS(__v_raw, "ParentCommentId", oldValue, value)
        }
    override var AddTime: String
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var ImgList: UTSArray<String>
        get() {
            return _tRG(__v_raw, "ImgList", __v_raw.ImgList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ImgList")) {
                return
            }
            val oldValue = __v_raw.ImgList
            __v_raw.ImgList = value
            _tRS(__v_raw, "ImgList", oldValue, value)
        }
    override var IsMy: Number
        get() {
            return _tRG(__v_raw, "IsMy", __v_raw.IsMy, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsMy")) {
                return
            }
            val oldValue = __v_raw.IsMy
            __v_raw.IsMy = value
            _tRS(__v_raw, "IsMy", oldValue, value)
        }
    override var ReplyNum: Number
        get() {
            return _tRG(__v_raw, "ReplyNum", __v_raw.ReplyNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ReplyNum")) {
                return
            }
            val oldValue = __v_raw.ReplyNum
            __v_raw.ReplyNum = value
            _tRS(__v_raw, "ReplyNum", oldValue, value)
        }
    override var MyCommnetList: UTSArray<CommentReply>
        get() {
            return _tRG(__v_raw, "MyCommnetList", __v_raw.MyCommnetList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MyCommnetList")) {
                return
            }
            val oldValue = __v_raw.MyCommnetList
            __v_raw.MyCommnetList = value
            _tRS(__v_raw, "MyCommnetList", oldValue, value)
        }
}
open class UserCommentParams (
    @JsonNotNull
    open var FindId: Number,
    @JsonNotNull
    open var Comment: String,
    open var CommentId: Number? = null,
    open var ParentCommentId: Number? = null,
    open var Picids: UTSArray<Number>? = null,
) : UTSObject()
open class DeleteCommentParams (
    @JsonNotNull
    open var CommentId: Number,
) : UTSObject()
open class UserFollowParams (
    @JsonNotNull
    open var ToMemberId: Number,
) : UTSObject()
open class UserLikeParams (
    @JsonNotNull
    open var FindId: Number,
    @JsonNotNull
    open var Type: Number,
) : UTSObject()
open class DeleteFindParams (
    @JsonNotNull
    open var IdArr: String,
    @JsonNotNull
    open var IsClear: Number,
) : UTSObject()
enum class Api__6(override val value: String) : UTSEnumString {
    UPLOAD_IMAGE("/api/Find2025/UpPic"),
    DELETED_IMAGE("/api/Find2025/DelPic"),
    PUBLISH_AND_EDIT("/api/Find2025/PublishFind"),
    GET_EDIT_DATA("/api/Find2025/GetFind"),
    GET_FIND_LIST("/api/Find2025/FindList"),
    GET_FIND_XQ("/api/Find2025/FindXq"),
    GET_COMMENT_LIST("/api/Find2025/CommentList"),
    USER_COMMENT("/api/Find2025/CommentOperation"),
    DELETED_COMMENT("/api/Find2025/CommentDel"),
    USER_FOLLOW("/api/Find2025/FollowOperation"),
    USER_LIKE("/api/Find2025/FindlikeOperation"),
    DELETE_FIND("/api/Find2025/DelMyFind"),
    INCREASE_SHARE("/api/Find2025/ShareAdd")
}
fun getFindList(params: GetFindListParams): UTSPromise<Any> {
    return request.post(Api__6.GET_FIND_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
fun getFindXq(params: GetFindXqParams): UTSPromise<GetFindXqResponse> {
    return request.post<GetFindXqResponse>(Api__6.GET_FIND_XQ, params)
}
fun getCommentList(params: GetCommentListParams): UTSPromise<Any> {
    return request.post(Api__6.GET_COMMENT_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
fun userComment(params: UserCommentParams): UTSPromise<Any> {
    return request.post(Api__6.USER_COMMENT, params)
}
fun deletedComment(params: DeleteCommentParams): UTSPromise<Any> {
    return request.post(Api__6.DELETED_COMMENT, params)
}
fun userFollow(params: UserFollowParams): UTSPromise<Any> {
    return request.post(Api__6.USER_FOLLOW, params)
}
fun userLike(params: UserLikeParams): UTSPromise<Any> {
    return request.post(Api__6.USER_LIKE, params)
}
fun deleteFind(params: DeleteFindParams): UTSPromise<Any> {
    return request.post(Api__6.DELETE_FIND, params)
}
open class IncreaseShareParams (
    @JsonNotNull
    open var FindId: Number,
) : UTSObject()
fun increaseShare(params: IncreaseShareParams): UTSPromise<Any> {
    return request.post(Api__6.INCREASE_SHARE, params)
}
fun useCommunityShare(onShared: ((findId: Number) -> Unit)? = null): UseCommunityShareReturn {
    val showSharePopup = ref(false)
    val currentPost = ref<FindBaseResponse?>(null)
    val closeSharePopup = fun(){
        showSharePopup.value = false
    }
    val openSharePopup = fun(post: FindBaseResponse){
        currentPost.value = post
        showSharePopup.value = true
    }
    val buildImageUrl = fun(post: FindBaseResponse): String {
        return if (post.ImgList.length > 0) {
            post.ImgList[0]
        } else {
            post.Avatar
        }
    }
    val reportShareSuccess = fun(post: FindBaseResponse): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                try {
                    await(increaseShare(IncreaseShareParams(FindId = post.Id)))
                }
                 catch (err: Throwable) {
                    console.error("记录分享失败:", err)
                }
                if (onShared != null) {
                    onShared(post.Id)
                }
                uni_showToast(ShowToastOptions(title = "分享成功", icon = "success"))
        })
    }
    val getShareData = fun(): UTSJSONObject? {
        val post = currentPost.value
        if (post == null) {
            return null
        }
        return _uO("title" to ("" + post.NickName + "的动态"), "summary" to post.ContentDetails, "imageUrl" to buildImageUrl(post))
    }
    val shareToScene = fun(scene: String){
        val post = currentPost.value
        if (post == null) {
            return
        }
        closeSharePopup()
    }
    val handleShareToFriend = fun(){
        shareToScene("WXSceneSession")
    }
    val handleShareToMoments = fun(){
        shareToScene("WXSceneTimeline")
    }
    return UseCommunityShareReturn(showSharePopup = showSharePopup, currentPost = currentPost, openSharePopup = openSharePopup, closeSharePopup = closeSharePopup, handleShareToFriend = handleShareToFriend, handleShareToMoments = handleShareToMoments, getShareData = getShareData, reportShareSuccess = reportShareSuccess)
}
val runBlock3 = run {
    request.setInterceptors(HttpInterceptors(request = fun(options): HttpRequestOptions {
        val header: UTSJSONObject = _uO()
        if (options.header != null) {
            for(key in resolveUTSKeyIterator(options.header!!)){
                header[key] = options.header!![key]
            }
        }
        header["Custom-Header"] = "custom-value"
        val userStore = useAuthStore()
        val tokenValue = userStore.tokenValue
        val data = UTSJSONObject()
        if (options.data != null) {
            for(key in resolveUTSKeyIterator(options.data!!)){
                data[key] = options.data!![key]
            }
        }
        data["UserId"] = if (tokenValue != null) {
            "" + tokenValue.UserId
        } else {
            ""
        }
        data["Token"] = if (tokenValue != null) {
            tokenValue.Token
        } else {
            ""
        }
        options.header = header
        options.data = data
        return options
    }
    , response = fun(response, options): Any {
        val code = response.code
        val msg = response.msg
        if (code === 2 || code === 401 || code === 403) {
            if (options.customError == true) {
                return UTSPromise.reject(_uO("code" to code, "msg" to msg))
            }
            val authStore = useAuthStore()
            authStore.logout()
            val authErrorMsg = msg ?: "登录已过期，请重新登录"
            showError(authErrorMsg)
            return UTSPromise.reject(UTSError(authErrorMsg))
        }
        if (code === 0) {
            if (options.isTotal == true) {
                return _uO("data" to if (UTSArray.isArray(response.data)) {
                    response.data as UTSArray<Any>
                } else {
                    _uA()
                }, "total" to (response.count ?: 0))
            } else {
                return response.data
            }
        }
        val serverErrorMsg = msg ?: "服务器异常，请稍后重试"
        showError(serverErrorMsg)
        return UTSPromise.reject(UTSError(msg ?: "服务器异常"))
    }
    , error = fun(error, options){
        console.log("错误拦截器", error, options)
        val errorObj = error as UTSJSONObject
        if (options.customError == true && errorObj["response"] != null) {
            val response = errorObj["response"] as UTSJSONObject
            val resData = response["data"] as UTSJSONObject
            val code = resData["code"] ?: response["statusCode"]
            val msg = resData["msg"] ?: errorObj["message"]
            console.error("自定义错误:", code, msg)
        }
    }
    ))
}
val HttpsRequest = HttpRequest(HttpInitOptions(baseURL = getBaseUrl()))
open class DictItem (
    @JsonNotNull
    open var code: Number,
    @JsonNotNull
    open var message: String,
) : UTSObject()
open class GetIndustryInfoResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var Name: String,
    @JsonNotNull
    open var FatherId: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetIndustryInfoResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetIndustryInfoResultReactiveObject : GetIndustryInfoResult, IUTSReactive<GetIndustryInfoResult> {
    override var __v_raw: GetIndustryInfoResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetIndustryInfoResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, Name = __v_raw.Name, FatherId = __v_raw.FatherId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetIndustryInfoResultReactiveObject {
        return GetIndustryInfoResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var Name: String
        get() {
            return _tRG(__v_raw, "Name", __v_raw.Name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Name")) {
                return
            }
            val oldValue = __v_raw.Name
            __v_raw.Name = value
            _tRS(__v_raw, "Name", oldValue, value)
        }
    override var FatherId: Number
        get() {
            return _tRG(__v_raw, "FatherId", __v_raw.FatherId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FatherId")) {
                return
            }
            val oldValue = __v_raw.FatherId
            __v_raw.FatherId = value
            _tRS(__v_raw, "FatherId", oldValue, value)
        }
}
open class GetNationalityResult (
    @JsonNotNull
    open var ChineseName: String,
    @JsonNotNull
    open var Id: Number,
) : UTSObject()
open class GetUniversityResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var SchoolName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetUniversityResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetUniversityResultReactiveObject : GetUniversityResult, IUTSReactive<GetUniversityResult> {
    override var __v_raw: GetUniversityResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetUniversityResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, SchoolName = __v_raw.SchoolName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetUniversityResultReactiveObject {
        return GetUniversityResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var SchoolName: String
        get() {
            return _tRG(__v_raw, "SchoolName", __v_raw.SchoolName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolName")) {
                return
            }
            val oldValue = __v_raw.SchoolName
            __v_raw.SchoolName = value
            _tRS(__v_raw, "SchoolName", oldValue, value)
        }
}
open class GetAgeOrExpParams (
    @JsonNotNull
    open var DemandType: Number,
) : UTSObject()
open class GetAgeOrExpResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var Name: String,
    @JsonNotNull
    open var MaxSize: Number,
    @JsonNotNull
    open var MinSize: Number,
) : UTSObject()
open class GetNationalityParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
) : UTSObject()
open class GetUniversityParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var KeyWord: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetUniversityParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetUniversityParamsReactiveObject : GetUniversityParams, IUTSReactive<GetUniversityParams> {
    override var __v_raw: GetUniversityParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetUniversityParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, KeyWord = __v_raw.KeyWord) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetUniversityParamsReactiveObject {
        return GetUniversityParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var KeyWord: String
        get() {
            return _tRG(__v_raw, "KeyWord", __v_raw.KeyWord, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("KeyWord")) {
                return
            }
            val oldValue = __v_raw.KeyWord
            __v_raw.KeyWord = value
            _tRS(__v_raw, "KeyWord", oldValue, value)
        }
}
open class GetQualificationCertificateParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var IsRecommend: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetQualificationCertificateParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetQualificationCertificateParamsReactiveObject : GetQualificationCertificateParams, IUTSReactive<GetQualificationCertificateParams> {
    override var __v_raw: GetQualificationCertificateParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetQualificationCertificateParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, Id = __v_raw.Id, IsRecommend = __v_raw.IsRecommend) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetQualificationCertificateParamsReactiveObject {
        return GetQualificationCertificateParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var IsRecommend: Number
        get() {
            return _tRG(__v_raw, "IsRecommend", __v_raw.IsRecommend, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsRecommend")) {
                return
            }
            val oldValue = __v_raw.IsRecommend
            __v_raw.IsRecommend = value
            _tRS(__v_raw, "IsRecommend", oldValue, value)
        }
}
open class GetSalaryResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var Name: String,
    @JsonNotNull
    open var MaxSalary: Number,
    @JsonNotNull
    open var MinSalary: Number,
) : UTSObject()
open class GetAllPositionParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var IndustryType: Number,
    @JsonNotNull
    open var Id: Number,
    open var Keywords: String? = null,
    @JsonNotNull
    open var TwoPage: Number,
    @JsonNotNull
    open var TwoPageSize: Number,
    @JsonNotNull
    open var ThreePage: Number,
    @JsonNotNull
    open var ThreePageSize: Number,
) : UTSObject()
open class GetAllPositionResponse (
    @JsonNotNull
    open var Fatherdata: UTSArray<GetAllPositionResult>,
    @JsonNotNull
    open var MaxCount: Number,
) : UTSObject()
open class GetAllPositionResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var PositionName: String,
    @JsonNotNull
    open var FatherId: Number,
    @JsonNotNull
    open var ThreePageSize: Number,
    @JsonNotNull
    open var LevelType: Number,
    @JsonNotNull
    open var MinCount: Number,
    open var MidChildren: UTSArray<GetAllPositionResult>? = null,
    open var MinChildren: UTSArray<GetPositionResult>? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetAllPositionResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetAllPositionResultReactiveObject : GetAllPositionResult, IUTSReactive<GetAllPositionResult> {
    override var __v_raw: GetAllPositionResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetAllPositionResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, PositionName = __v_raw.PositionName, FatherId = __v_raw.FatherId, ThreePageSize = __v_raw.ThreePageSize, LevelType = __v_raw.LevelType, MinCount = __v_raw.MinCount, MidChildren = __v_raw.MidChildren, MinChildren = __v_raw.MinChildren) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetAllPositionResultReactiveObject {
        return GetAllPositionResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var PositionName: String
        get() {
            return _tRG(__v_raw, "PositionName", __v_raw.PositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PositionName")) {
                return
            }
            val oldValue = __v_raw.PositionName
            __v_raw.PositionName = value
            _tRS(__v_raw, "PositionName", oldValue, value)
        }
    override var FatherId: Number
        get() {
            return _tRG(__v_raw, "FatherId", __v_raw.FatherId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FatherId")) {
                return
            }
            val oldValue = __v_raw.FatherId
            __v_raw.FatherId = value
            _tRS(__v_raw, "FatherId", oldValue, value)
        }
    override var ThreePageSize: Number
        get() {
            return _tRG(__v_raw, "ThreePageSize", __v_raw.ThreePageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ThreePageSize")) {
                return
            }
            val oldValue = __v_raw.ThreePageSize
            __v_raw.ThreePageSize = value
            _tRS(__v_raw, "ThreePageSize", oldValue, value)
        }
    override var LevelType: Number
        get() {
            return _tRG(__v_raw, "LevelType", __v_raw.LevelType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LevelType")) {
                return
            }
            val oldValue = __v_raw.LevelType
            __v_raw.LevelType = value
            _tRS(__v_raw, "LevelType", oldValue, value)
        }
    override var MinCount: Number
        get() {
            return _tRG(__v_raw, "MinCount", __v_raw.MinCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinCount")) {
                return
            }
            val oldValue = __v_raw.MinCount
            __v_raw.MinCount = value
            _tRS(__v_raw, "MinCount", oldValue, value)
        }
    override var MidChildren: UTSArray<GetAllPositionResult>?
        get() {
            return _tRG(__v_raw, "MidChildren", __v_raw.MidChildren, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MidChildren")) {
                return
            }
            val oldValue = __v_raw.MidChildren
            __v_raw.MidChildren = value
            _tRS(__v_raw, "MidChildren", oldValue, value)
        }
    override var MinChildren: UTSArray<GetPositionResult>?
        get() {
            return _tRG(__v_raw, "MinChildren", __v_raw.MinChildren, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinChildren")) {
                return
            }
            val oldValue = __v_raw.MinChildren
            __v_raw.MinChildren = value
            _tRS(__v_raw, "MinChildren", oldValue, value)
        }
}
open class GetPositionResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var PositionName: String,
    @JsonNotNull
    open var FatherId: Number,
    @JsonNotNull
    open var LevelType: Number,
    @JsonNotNull
    open var RePositionName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetPositionResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetPositionResultReactiveObject : GetPositionResult, IUTSReactive<GetPositionResult> {
    override var __v_raw: GetPositionResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetPositionResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, PositionName = __v_raw.PositionName, FatherId = __v_raw.FatherId, LevelType = __v_raw.LevelType, RePositionName = __v_raw.RePositionName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetPositionResultReactiveObject {
        return GetPositionResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var PositionName: String
        get() {
            return _tRG(__v_raw, "PositionName", __v_raw.PositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PositionName")) {
                return
            }
            val oldValue = __v_raw.PositionName
            __v_raw.PositionName = value
            _tRS(__v_raw, "PositionName", oldValue, value)
        }
    override var FatherId: Number
        get() {
            return _tRG(__v_raw, "FatherId", __v_raw.FatherId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FatherId")) {
                return
            }
            val oldValue = __v_raw.FatherId
            __v_raw.FatherId = value
            _tRS(__v_raw, "FatherId", oldValue, value)
        }
    override var LevelType: Number
        get() {
            return _tRG(__v_raw, "LevelType", __v_raw.LevelType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("LevelType")) {
                return
            }
            val oldValue = __v_raw.LevelType
            __v_raw.LevelType = value
            _tRS(__v_raw, "LevelType", oldValue, value)
        }
    override var RePositionName: String
        get() {
            return _tRG(__v_raw, "RePositionName", __v_raw.RePositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("RePositionName")) {
                return
            }
            val oldValue = __v_raw.RePositionName
            __v_raw.RePositionName = value
            _tRS(__v_raw, "RePositionName", oldValue, value)
        }
}
enum class Api__7(override val value: String) : UTSEnumString {
    GET_INDUSTRY_INFO("/api/Goods/IndustryList"),
    GET_ENTERPRISE_SCALE("/api/Goods/GetCompanyScale"),
    GET_ENTERPRISE_REGISTRATION_STATUS("/api/Goods/GetCompanyState"),
    GET_AGE_OR_EXP("/api/Hireworkers/DemandList"),
    GET_JOB_STATUS("/api/Hireworkers/GetJobState"),
    GET_EXPECTED_INDUSTRY_AND_POSITION("/api/Hireworkers/CupationList"),
    GET_SALARY("/api/Hireworkers/SalaryList"),
    GET_BENEFITS("/api/Hireworkers/WelfareList"),
    GET_NATIONALITY("/api/Hireworkers/NationalityList"),
    GET_UNIVERSITY("/api/Hireworkers/SchoolList"),
    GET_ALL_POSITION("/api/Hireworkers/AllPositionList"),
    GET_POSITION("/api/Hireworkers/QueryPositionList"),
    GET_QUALIFICATION_CERTIFICATE("/api/Hireworkers/CertificateList")
}
fun getAgeOrExp(params: GetAgeOrExpParams): UTSPromise<UTSArray<GetAgeOrExpResult>> {
    return request.post<UTSArray<GetAgeOrExpResult>>(Api__7.GET_AGE_OR_EXP, params)
}
fun getJobStatus(): UTSPromise<UTSArray<DictItem>> {
    return request.post<UTSArray<DictItem>>(Api__7.GET_JOB_STATUS)
}
fun getNationality(params: GetNationalityParams): UTSPromise<Any> {
    return request.post<Any>(Api__7.GET_NATIONALITY, params, HttpRequestExtraOptions(isTotal = true))
}
fun getUniversity(params: GetUniversityParams): UTSPromise<Any> {
    return request.post<Any>(Api__7.GET_UNIVERSITY, params, HttpRequestExtraOptions(isTotal = true))
}
fun getQualificationCertificate(params: GetQualificationCertificateParams): UTSPromise<Any> {
    return request.post<Any>(Api__7.GET_QUALIFICATION_CERTIFICATE, params, HttpRequestExtraOptions(isTotal = true))
}
fun getSalary(): UTSPromise<UTSArray<GetSalaryResult>> {
    return request.post<UTSArray<GetSalaryResult>>(Api__7.GET_SALARY)
}
fun getBenefits(): UTSPromise<UTSArray<GetBenefitsParamsn>> {
    return request.post<UTSArray<GetBenefitsParamsn>>(Api__7.GET_BENEFITS)
}
fun getAllPosition(params: GetAllPositionParams): UTSPromise<GetAllPositionResponse> {
    return request.post<GetAllPositionResponse>(Api__7.GET_ALL_POSITION, params)
}
fun getPosition(params: GetAllPositionParams): UTSPromise<Any> {
    return request.post<Any>(Api__7.GET_POSITION, params, HttpRequestExtraOptions(isTotal = true))
}
open class GetPlusMemberListResult (
    open var Id: Number? = null,
    open var PlusTitle: String? = null,
    open var PlusPrice: Number? = null,
    open var Effective: Number? = null,
    open var PlusIntroduce: String? = null,
    open var Status: Number? = null,
    open var AddTime: String? = null,
    open var MarketPrice: Number? = null,
    open var ProvinceCode: String? = null,
    open var CityCode: String? = null,
    open var AreaCode: String? = null,
    open var FranchiseLevel: Number? = null,
    open var ProvinceName: String? = null,
    open var CityCodeName: String? = null,
    open var AreaCodeName: String? = null,
    open var FranchiseBind: Number? = null,
    open var FranchiseRate: Number? = null,
    open var IsFirstOpen: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetPlusMemberListResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetPlusMemberListResultReactiveObject : GetPlusMemberListResult, IUTSReactive<GetPlusMemberListResult> {
    override var __v_raw: GetPlusMemberListResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetPlusMemberListResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, PlusTitle = __v_raw.PlusTitle, PlusPrice = __v_raw.PlusPrice, Effective = __v_raw.Effective, PlusIntroduce = __v_raw.PlusIntroduce, Status = __v_raw.Status, AddTime = __v_raw.AddTime, MarketPrice = __v_raw.MarketPrice, ProvinceCode = __v_raw.ProvinceCode, CityCode = __v_raw.CityCode, AreaCode = __v_raw.AreaCode, FranchiseLevel = __v_raw.FranchiseLevel, ProvinceName = __v_raw.ProvinceName, CityCodeName = __v_raw.CityCodeName, AreaCodeName = __v_raw.AreaCodeName, FranchiseBind = __v_raw.FranchiseBind, FranchiseRate = __v_raw.FranchiseRate, IsFirstOpen = __v_raw.IsFirstOpen) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetPlusMemberListResultReactiveObject {
        return GetPlusMemberListResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number?
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var PlusTitle: String?
        get() {
            return _tRG(__v_raw, "PlusTitle", __v_raw.PlusTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PlusTitle")) {
                return
            }
            val oldValue = __v_raw.PlusTitle
            __v_raw.PlusTitle = value
            _tRS(__v_raw, "PlusTitle", oldValue, value)
        }
    override var PlusPrice: Number?
        get() {
            return _tRG(__v_raw, "PlusPrice", __v_raw.PlusPrice, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PlusPrice")) {
                return
            }
            val oldValue = __v_raw.PlusPrice
            __v_raw.PlusPrice = value
            _tRS(__v_raw, "PlusPrice", oldValue, value)
        }
    override var Effective: Number?
        get() {
            return _tRG(__v_raw, "Effective", __v_raw.Effective, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Effective")) {
                return
            }
            val oldValue = __v_raw.Effective
            __v_raw.Effective = value
            _tRS(__v_raw, "Effective", oldValue, value)
        }
    override var PlusIntroduce: String?
        get() {
            return _tRG(__v_raw, "PlusIntroduce", __v_raw.PlusIntroduce, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PlusIntroduce")) {
                return
            }
            val oldValue = __v_raw.PlusIntroduce
            __v_raw.PlusIntroduce = value
            _tRS(__v_raw, "PlusIntroduce", oldValue, value)
        }
    override var Status: Number?
        get() {
            return _tRG(__v_raw, "Status", __v_raw.Status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Status")) {
                return
            }
            val oldValue = __v_raw.Status
            __v_raw.Status = value
            _tRS(__v_raw, "Status", oldValue, value)
        }
    override var AddTime: String?
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var MarketPrice: Number?
        get() {
            return _tRG(__v_raw, "MarketPrice", __v_raw.MarketPrice, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MarketPrice")) {
                return
            }
            val oldValue = __v_raw.MarketPrice
            __v_raw.MarketPrice = value
            _tRS(__v_raw, "MarketPrice", oldValue, value)
        }
    override var ProvinceCode: String?
        get() {
            return _tRG(__v_raw, "ProvinceCode", __v_raw.ProvinceCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceCode")) {
                return
            }
            val oldValue = __v_raw.ProvinceCode
            __v_raw.ProvinceCode = value
            _tRS(__v_raw, "ProvinceCode", oldValue, value)
        }
    override var CityCode: String?
        get() {
            return _tRG(__v_raw, "CityCode", __v_raw.CityCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCode")) {
                return
            }
            val oldValue = __v_raw.CityCode
            __v_raw.CityCode = value
            _tRS(__v_raw, "CityCode", oldValue, value)
        }
    override var AreaCode: String?
        get() {
            return _tRG(__v_raw, "AreaCode", __v_raw.AreaCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AreaCode")) {
                return
            }
            val oldValue = __v_raw.AreaCode
            __v_raw.AreaCode = value
            _tRS(__v_raw, "AreaCode", oldValue, value)
        }
    override var FranchiseLevel: Number?
        get() {
            return _tRG(__v_raw, "FranchiseLevel", __v_raw.FranchiseLevel, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FranchiseLevel")) {
                return
            }
            val oldValue = __v_raw.FranchiseLevel
            __v_raw.FranchiseLevel = value
            _tRS(__v_raw, "FranchiseLevel", oldValue, value)
        }
    override var ProvinceName: String?
        get() {
            return _tRG(__v_raw, "ProvinceName", __v_raw.ProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProvinceName")) {
                return
            }
            val oldValue = __v_raw.ProvinceName
            __v_raw.ProvinceName = value
            _tRS(__v_raw, "ProvinceName", oldValue, value)
        }
    override var CityCodeName: String?
        get() {
            return _tRG(__v_raw, "CityCodeName", __v_raw.CityCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCodeName")) {
                return
            }
            val oldValue = __v_raw.CityCodeName
            __v_raw.CityCodeName = value
            _tRS(__v_raw, "CityCodeName", oldValue, value)
        }
    override var AreaCodeName: String?
        get() {
            return _tRG(__v_raw, "AreaCodeName", __v_raw.AreaCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AreaCodeName")) {
                return
            }
            val oldValue = __v_raw.AreaCodeName
            __v_raw.AreaCodeName = value
            _tRS(__v_raw, "AreaCodeName", oldValue, value)
        }
    override var FranchiseBind: Number?
        get() {
            return _tRG(__v_raw, "FranchiseBind", __v_raw.FranchiseBind, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FranchiseBind")) {
                return
            }
            val oldValue = __v_raw.FranchiseBind
            __v_raw.FranchiseBind = value
            _tRS(__v_raw, "FranchiseBind", oldValue, value)
        }
    override var FranchiseRate: Number?
        get() {
            return _tRG(__v_raw, "FranchiseRate", __v_raw.FranchiseRate, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FranchiseRate")) {
                return
            }
            val oldValue = __v_raw.FranchiseRate
            __v_raw.FranchiseRate = value
            _tRS(__v_raw, "FranchiseRate", oldValue, value)
        }
    override var IsFirstOpen: Number?
        get() {
            return _tRG(__v_raw, "IsFirstOpen", __v_raw.IsFirstOpen, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsFirstOpen")) {
                return
            }
            val oldValue = __v_raw.IsFirstOpen
            __v_raw.IsFirstOpen = value
            _tRS(__v_raw, "IsFirstOpen", oldValue, value)
        }
}
open class CreatePlusMemberOrderParams (
    @JsonNotNull
    open var PlusId: Number,
    @JsonNotNull
    open var Account: String,
    @JsonNotNull
    open var PassWord: String,
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var FranchiseLevel: Number,
    @JsonNotNull
    open var ProvinceCode: String,
    open var CityCode: String? = null,
    open var AreaCode: String? = null,
) : UTSObject()
open class CreatePlusMemberOrderResult (
    @JsonNotNull
    open var plusNo: String,
) : UTSObject()
enum class Api__8(override val value: String) : UTSEnumString {
    GET_PLUS_MEMBER_LIST("/api/User/GetPlusMemberList"),
    CREATE_PLUS_MEMBER_ORDER("/api/User/PlusBuyOrder"),
    GET_FRANCHISE_BIND("/api/User/GetBindPlusList"),
    BIND_PLUS("/api/User/BindId"),
    GET_VIP_PACKAGE_LIST("/api/User/GetVipPackageList")
}
fun getPlusMemberList(params: GetPlusMemberListParams): UTSPromise<UTSArray<GetPlusMemberListResult>> {
    return request.post<UTSArray<GetPlusMemberListResult>>(Api__8.GET_PLUS_MEMBER_LIST, params)
}
open class GetPositionListResult (
    open var Id: Number? = null,
    open var MemberId: String? = null,
    open var Avatar: String? = null,
    open var UserNick: String? = null,
    open var PicNo: String? = null,
    open var SettleWay: String? = null,
    open var PublTypeName: String? = null,
    open var JobName: String? = null,
    open var HireCompanyName: String? = null,
    open var HireProvinceCode: String? = null,
    open var HireCityCode: String? = null,
    open var HireAreaCode: String? = null,
    open var HireProvinceName: String? = null,
    open var HireCityCodeName: String? = null,
    open var HireAreaCodeName: String? = null,
    open var Distance: Number? = null,
    open var HireWelfare: String? = null,
    open var HireWelfareName: String? = null,
    open var ExperienceId: Number? = null,
    open var ExperienceTitle: String? = null,
    open var SalaryName: String? = null,
    open var SalaryId: Number? = null,
    open var Education: String? = null,
    open var JobPositionId: Number? = null,
    open var JobPositionName: String? = null,
    open var HireDescription: String? = null,
    open var IsPinned: Number? = null,
    open var SalesStatus: Number? = null,
    open var AddTime: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetPositionListResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetPositionListResultReactiveObject : GetPositionListResult, IUTSReactive<GetPositionListResult> {
    override var __v_raw: GetPositionListResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetPositionListResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, MemberId = __v_raw.MemberId, Avatar = __v_raw.Avatar, UserNick = __v_raw.UserNick, PicNo = __v_raw.PicNo, SettleWay = __v_raw.SettleWay, PublTypeName = __v_raw.PublTypeName, JobName = __v_raw.JobName, HireCompanyName = __v_raw.HireCompanyName, HireProvinceCode = __v_raw.HireProvinceCode, HireCityCode = __v_raw.HireCityCode, HireAreaCode = __v_raw.HireAreaCode, HireProvinceName = __v_raw.HireProvinceName, HireCityCodeName = __v_raw.HireCityCodeName, HireAreaCodeName = __v_raw.HireAreaCodeName, Distance = __v_raw.Distance, HireWelfare = __v_raw.HireWelfare, HireWelfareName = __v_raw.HireWelfareName, ExperienceId = __v_raw.ExperienceId, ExperienceTitle = __v_raw.ExperienceTitle, SalaryName = __v_raw.SalaryName, SalaryId = __v_raw.SalaryId, Education = __v_raw.Education, JobPositionId = __v_raw.JobPositionId, JobPositionName = __v_raw.JobPositionName, HireDescription = __v_raw.HireDescription, IsPinned = __v_raw.IsPinned, SalesStatus = __v_raw.SalesStatus, AddTime = __v_raw.AddTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetPositionListResultReactiveObject {
        return GetPositionListResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number?
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var MemberId: String?
        get() {
            return _tRG(__v_raw, "MemberId", __v_raw.MemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberId")) {
                return
            }
            val oldValue = __v_raw.MemberId
            __v_raw.MemberId = value
            _tRS(__v_raw, "MemberId", oldValue, value)
        }
    override var Avatar: String?
        get() {
            return _tRG(__v_raw, "Avatar", __v_raw.Avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Avatar")) {
                return
            }
            val oldValue = __v_raw.Avatar
            __v_raw.Avatar = value
            _tRS(__v_raw, "Avatar", oldValue, value)
        }
    override var UserNick: String?
        get() {
            return _tRG(__v_raw, "UserNick", __v_raw.UserNick, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UserNick")) {
                return
            }
            val oldValue = __v_raw.UserNick
            __v_raw.UserNick = value
            _tRS(__v_raw, "UserNick", oldValue, value)
        }
    override var PicNo: String?
        get() {
            return _tRG(__v_raw, "PicNo", __v_raw.PicNo, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PicNo")) {
                return
            }
            val oldValue = __v_raw.PicNo
            __v_raw.PicNo = value
            _tRS(__v_raw, "PicNo", oldValue, value)
        }
    override var SettleWay: String?
        get() {
            return _tRG(__v_raw, "SettleWay", __v_raw.SettleWay, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SettleWay")) {
                return
            }
            val oldValue = __v_raw.SettleWay
            __v_raw.SettleWay = value
            _tRS(__v_raw, "SettleWay", oldValue, value)
        }
    override var PublTypeName: String?
        get() {
            return _tRG(__v_raw, "PublTypeName", __v_raw.PublTypeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PublTypeName")) {
                return
            }
            val oldValue = __v_raw.PublTypeName
            __v_raw.PublTypeName = value
            _tRS(__v_raw, "PublTypeName", oldValue, value)
        }
    override var JobName: String?
        get() {
            return _tRG(__v_raw, "JobName", __v_raw.JobName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobName")) {
                return
            }
            val oldValue = __v_raw.JobName
            __v_raw.JobName = value
            _tRS(__v_raw, "JobName", oldValue, value)
        }
    override var HireCompanyName: String?
        get() {
            return _tRG(__v_raw, "HireCompanyName", __v_raw.HireCompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCompanyName")) {
                return
            }
            val oldValue = __v_raw.HireCompanyName
            __v_raw.HireCompanyName = value
            _tRS(__v_raw, "HireCompanyName", oldValue, value)
        }
    override var HireProvinceCode: String?
        get() {
            return _tRG(__v_raw, "HireProvinceCode", __v_raw.HireProvinceCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireProvinceCode")) {
                return
            }
            val oldValue = __v_raw.HireProvinceCode
            __v_raw.HireProvinceCode = value
            _tRS(__v_raw, "HireProvinceCode", oldValue, value)
        }
    override var HireCityCode: String?
        get() {
            return _tRG(__v_raw, "HireCityCode", __v_raw.HireCityCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCityCode")) {
                return
            }
            val oldValue = __v_raw.HireCityCode
            __v_raw.HireCityCode = value
            _tRS(__v_raw, "HireCityCode", oldValue, value)
        }
    override var HireAreaCode: String?
        get() {
            return _tRG(__v_raw, "HireAreaCode", __v_raw.HireAreaCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireAreaCode")) {
                return
            }
            val oldValue = __v_raw.HireAreaCode
            __v_raw.HireAreaCode = value
            _tRS(__v_raw, "HireAreaCode", oldValue, value)
        }
    override var HireProvinceName: String?
        get() {
            return _tRG(__v_raw, "HireProvinceName", __v_raw.HireProvinceName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireProvinceName")) {
                return
            }
            val oldValue = __v_raw.HireProvinceName
            __v_raw.HireProvinceName = value
            _tRS(__v_raw, "HireProvinceName", oldValue, value)
        }
    override var HireCityCodeName: String?
        get() {
            return _tRG(__v_raw, "HireCityCodeName", __v_raw.HireCityCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireCityCodeName")) {
                return
            }
            val oldValue = __v_raw.HireCityCodeName
            __v_raw.HireCityCodeName = value
            _tRS(__v_raw, "HireCityCodeName", oldValue, value)
        }
    override var HireAreaCodeName: String?
        get() {
            return _tRG(__v_raw, "HireAreaCodeName", __v_raw.HireAreaCodeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireAreaCodeName")) {
                return
            }
            val oldValue = __v_raw.HireAreaCodeName
            __v_raw.HireAreaCodeName = value
            _tRS(__v_raw, "HireAreaCodeName", oldValue, value)
        }
    override var Distance: Number?
        get() {
            return _tRG(__v_raw, "Distance", __v_raw.Distance, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Distance")) {
                return
            }
            val oldValue = __v_raw.Distance
            __v_raw.Distance = value
            _tRS(__v_raw, "Distance", oldValue, value)
        }
    override var HireWelfare: String?
        get() {
            return _tRG(__v_raw, "HireWelfare", __v_raw.HireWelfare, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireWelfare")) {
                return
            }
            val oldValue = __v_raw.HireWelfare
            __v_raw.HireWelfare = value
            _tRS(__v_raw, "HireWelfare", oldValue, value)
        }
    override var HireWelfareName: String?
        get() {
            return _tRG(__v_raw, "HireWelfareName", __v_raw.HireWelfareName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireWelfareName")) {
                return
            }
            val oldValue = __v_raw.HireWelfareName
            __v_raw.HireWelfareName = value
            _tRS(__v_raw, "HireWelfareName", oldValue, value)
        }
    override var ExperienceId: Number?
        get() {
            return _tRG(__v_raw, "ExperienceId", __v_raw.ExperienceId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ExperienceId")) {
                return
            }
            val oldValue = __v_raw.ExperienceId
            __v_raw.ExperienceId = value
            _tRS(__v_raw, "ExperienceId", oldValue, value)
        }
    override var ExperienceTitle: String?
        get() {
            return _tRG(__v_raw, "ExperienceTitle", __v_raw.ExperienceTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ExperienceTitle")) {
                return
            }
            val oldValue = __v_raw.ExperienceTitle
            __v_raw.ExperienceTitle = value
            _tRS(__v_raw, "ExperienceTitle", oldValue, value)
        }
    override var SalaryName: String?
        get() {
            return _tRG(__v_raw, "SalaryName", __v_raw.SalaryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalaryName")) {
                return
            }
            val oldValue = __v_raw.SalaryName
            __v_raw.SalaryName = value
            _tRS(__v_raw, "SalaryName", oldValue, value)
        }
    override var SalaryId: Number?
        get() {
            return _tRG(__v_raw, "SalaryId", __v_raw.SalaryId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalaryId")) {
                return
            }
            val oldValue = __v_raw.SalaryId
            __v_raw.SalaryId = value
            _tRS(__v_raw, "SalaryId", oldValue, value)
        }
    override var Education: String?
        get() {
            return _tRG(__v_raw, "Education", __v_raw.Education, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Education")) {
                return
            }
            val oldValue = __v_raw.Education
            __v_raw.Education = value
            _tRS(__v_raw, "Education", oldValue, value)
        }
    override var JobPositionId: Number?
        get() {
            return _tRG(__v_raw, "JobPositionId", __v_raw.JobPositionId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionId")) {
                return
            }
            val oldValue = __v_raw.JobPositionId
            __v_raw.JobPositionId = value
            _tRS(__v_raw, "JobPositionId", oldValue, value)
        }
    override var JobPositionName: String?
        get() {
            return _tRG(__v_raw, "JobPositionName", __v_raw.JobPositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionName")) {
                return
            }
            val oldValue = __v_raw.JobPositionName
            __v_raw.JobPositionName = value
            _tRS(__v_raw, "JobPositionName", oldValue, value)
        }
    override var HireDescription: String?
        get() {
            return _tRG(__v_raw, "HireDescription", __v_raw.HireDescription, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HireDescription")) {
                return
            }
            val oldValue = __v_raw.HireDescription
            __v_raw.HireDescription = value
            _tRS(__v_raw, "HireDescription", oldValue, value)
        }
    override var IsPinned: Number?
        get() {
            return _tRG(__v_raw, "IsPinned", __v_raw.IsPinned, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsPinned")) {
                return
            }
            val oldValue = __v_raw.IsPinned
            __v_raw.IsPinned = value
            _tRS(__v_raw, "IsPinned", oldValue, value)
        }
    override var SalesStatus: Number?
        get() {
            return _tRG(__v_raw, "SalesStatus", __v_raw.SalesStatus, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SalesStatus")) {
                return
            }
            val oldValue = __v_raw.SalesStatus
            __v_raw.SalesStatus = value
            _tRS(__v_raw, "SalesStatus", oldValue, value)
        }
    override var AddTime: String?
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
}
open class GetGroupQrCodeParams (
    @JsonNotNull
    open var Lat: Number,
    @JsonNotNull
    open var Lng: Number,
) : UTSObject()
open class AddFavoriteParams (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var Type: String,
) : UTSObject()
open class FavoriteResult (
    open var Success: Boolean? = null,
) : UTSObject()
open class BuyPinnedParams (
    @JsonNotNull
    open var HireJobId: Number,
    open var PinnedId: Number? = null,
) : UTSObject()
open class SalesHireJobParams (
    @JsonNotNull
    open var HireJobId: Number,
) : UTSObject()
enum class Api__9(override val value: String) : UTSEnumString {
    GET_POSITION_LIST("/api/Hireworkers/HireJobList"),
    GET_POSITION_DETAIL("/api/Hireworkers/HireJobInfo"),
    GET_GROUP_QR_CODE("/api/Hireworkers/GetQRCode"),
    GET_HOT_CITY("/api/Area/HotCity"),
    POST_FAVORITE_POSITION("/api/User/AddCollections"),
    DELETE_FAVORITE_POSITION("/api/User/ReCollections"),
    GET_POSITION_COMMENT_LIST("/api/Message/GradeList"),
    GET_COMANY_DETAIL("/api/Hireworkers/HireCompanyInfo"),
    POSITON_SHARE("/api/User/FindShareOperation"),
    POST_BLOCK_COMPANY("/api/Hireworkers/SettingScreen"),
    GET_BLOCK_LIST("/api/Hireworkers/ScreenList"),
    POST_POSITION_PUBLISH("/api/Hireworkers/AddEidtHireJob"),
    POST_MARK_UNSUITABLE("/api/Hireworkers/MarkSettings"),
    GET_COMPANY_LIST("/api/Hireworkers/HireCompanyList"),
    GET_PINNED_LIST("/api/User/GetPinnedList"),
    POST_FREE_PINNED("/api/Hireworkers/ConfirmPinned"),
    POST_SALES_HIRE_JOB("/api/Hireworkers/SalesHireJob")
}
fun getPositionList(params: GetPositionListParams): UTSPromise<Any> {
    return request.post(Api__9.GET_POSITION_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
fun getGroupQrCode(params: GetGroupQrCodeParams): UTSPromise<String> {
    return request.post<String>(Api__9.GET_GROUP_QR_CODE, params)
}
fun deleteFavoritePosition(params: AddFavoriteParams): UTSPromise<FavoriteResult> {
    return request.post<FavoriteResult>(Api__9.DELETE_FAVORITE_POSITION, params)
}
fun postFreePinned(params: BuyPinnedParams): UTSPromise<Any> {
    return request.post(Api__9.POST_FREE_PINNED, params)
}
fun salesHireJob(params: SalesHireJobParams): UTSPromise<Any> {
    return request.post(Api__9.POST_SALES_HIRE_JOB, params)
}
val getUserCollect = fun(params: GetUserCollectParams): UTSPromise<Any> {
    return request.post(Api__3.GET_USER_COLLECT, params, HttpRequestExtraOptions(isTotal = true))
}
val updatePrivateProtection = fun(): UTSPromise<Any> {
    return request.post(Api__3.UPDATE_PRIVATE_PROTECTION)
}
val getQrcode = fun(): UTSPromise<InviteFriendsResult> {
    return request.post<InviteFriendsResult>(Api__3.GET_QRCODE)
}
open class AddEditOnlineResumeParams (
    @JsonNotNull
    open var Mobile: String,
    @JsonNotNull
    open var UserNick: String,
    @JsonNotNull
    open var Sex: Number,
    @JsonNotNull
    open var OnlineResumeId: Number,
    @JsonNotNull
    open var HelpType: String,
    open var Email: String? = null,
    @JsonNotNull
    open var Nation: String,
    open var MyHonor: String? = null,
    open var PracticalActivity: String? = null,
    open var ClubActivities: String? = null,
    @JsonNotNull
    open var StartWorking: String,
    open var ProfessionalSkills: String? = null,
    open var CertificateId: String? = null,
    open var OpusDescription: String? = null,
    open var WeChat: String? = null,
    @JsonNotNull
    open var Avatar: String,
    open var MySuperiority: String? = null,
    @JsonNotNull
    open var Birthday: String,
    open var OpusPicNo: String? = null,
    @JsonNotNull
    open var Education: Number,
) : UTSObject()
open class AddEditEducationParams (
    @JsonNotNull
    open var Degree: String,
    @JsonNotNull
    open var SchoolMajor: String,
    @JsonNotNull
    open var SchoolId: Number,
    open var ContentDetail: String? = null,
    @JsonNotNull
    open var SchoolBeginTime: String,
    @JsonNotNull
    open var SchoolEndTime: String,
    open var EducationalId: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return AddEditEducationParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class AddEditEducationParamsReactiveObject : AddEditEducationParams, IUTSReactive<AddEditEducationParams> {
    override var __v_raw: AddEditEducationParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: AddEditEducationParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Degree = __v_raw.Degree, SchoolMajor = __v_raw.SchoolMajor, SchoolId = __v_raw.SchoolId, ContentDetail = __v_raw.ContentDetail, SchoolBeginTime = __v_raw.SchoolBeginTime, SchoolEndTime = __v_raw.SchoolEndTime, EducationalId = __v_raw.EducationalId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): AddEditEducationParamsReactiveObject {
        return AddEditEducationParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Degree: String
        get() {
            return _tRG(__v_raw, "Degree", __v_raw.Degree, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Degree")) {
                return
            }
            val oldValue = __v_raw.Degree
            __v_raw.Degree = value
            _tRS(__v_raw, "Degree", oldValue, value)
        }
    override var SchoolMajor: String
        get() {
            return _tRG(__v_raw, "SchoolMajor", __v_raw.SchoolMajor, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolMajor")) {
                return
            }
            val oldValue = __v_raw.SchoolMajor
            __v_raw.SchoolMajor = value
            _tRS(__v_raw, "SchoolMajor", oldValue, value)
        }
    override var SchoolId: Number
        get() {
            return _tRG(__v_raw, "SchoolId", __v_raw.SchoolId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolId")) {
                return
            }
            val oldValue = __v_raw.SchoolId
            __v_raw.SchoolId = value
            _tRS(__v_raw, "SchoolId", oldValue, value)
        }
    override var ContentDetail: String?
        get() {
            return _tRG(__v_raw, "ContentDetail", __v_raw.ContentDetail, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetail")) {
                return
            }
            val oldValue = __v_raw.ContentDetail
            __v_raw.ContentDetail = value
            _tRS(__v_raw, "ContentDetail", oldValue, value)
        }
    override var SchoolBeginTime: String
        get() {
            return _tRG(__v_raw, "SchoolBeginTime", __v_raw.SchoolBeginTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolBeginTime")) {
                return
            }
            val oldValue = __v_raw.SchoolBeginTime
            __v_raw.SchoolBeginTime = value
            _tRS(__v_raw, "SchoolBeginTime", oldValue, value)
        }
    override var SchoolEndTime: String
        get() {
            return _tRG(__v_raw, "SchoolEndTime", __v_raw.SchoolEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolEndTime")) {
                return
            }
            val oldValue = __v_raw.SchoolEndTime
            __v_raw.SchoolEndTime = value
            _tRS(__v_raw, "SchoolEndTime", oldValue, value)
        }
    override var EducationalId: Number?
        get() {
            return _tRG(__v_raw, "EducationalId", __v_raw.EducationalId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("EducationalId")) {
                return
            }
            val oldValue = __v_raw.EducationalId
            __v_raw.EducationalId = value
            _tRS(__v_raw, "EducationalId", oldValue, value)
        }
}
open class AddEditProjectExperienceParams (
    @JsonNotNull
    open var ProjectName: String,
    @JsonNotNull
    open var ProjectPositionName: String,
    @JsonNotNull
    open var ContentDetail: String,
    @JsonNotNull
    open var JobBeginTime: String,
    @JsonNotNull
    open var JobEndTime: String,
    open var WorkExperienceId: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return AddEditProjectExperienceParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class AddEditProjectExperienceParamsReactiveObject : AddEditProjectExperienceParams, IUTSReactive<AddEditProjectExperienceParams> {
    override var __v_raw: AddEditProjectExperienceParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: AddEditProjectExperienceParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(ProjectName = __v_raw.ProjectName, ProjectPositionName = __v_raw.ProjectPositionName, ContentDetail = __v_raw.ContentDetail, JobBeginTime = __v_raw.JobBeginTime, JobEndTime = __v_raw.JobEndTime, WorkExperienceId = __v_raw.WorkExperienceId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): AddEditProjectExperienceParamsReactiveObject {
        return AddEditProjectExperienceParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var ProjectName: String
        get() {
            return _tRG(__v_raw, "ProjectName", __v_raw.ProjectName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProjectName")) {
                return
            }
            val oldValue = __v_raw.ProjectName
            __v_raw.ProjectName = value
            _tRS(__v_raw, "ProjectName", oldValue, value)
        }
    override var ProjectPositionName: String
        get() {
            return _tRG(__v_raw, "ProjectPositionName", __v_raw.ProjectPositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProjectPositionName")) {
                return
            }
            val oldValue = __v_raw.ProjectPositionName
            __v_raw.ProjectPositionName = value
            _tRS(__v_raw, "ProjectPositionName", oldValue, value)
        }
    override var ContentDetail: String
        get() {
            return _tRG(__v_raw, "ContentDetail", __v_raw.ContentDetail, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetail")) {
                return
            }
            val oldValue = __v_raw.ContentDetail
            __v_raw.ContentDetail = value
            _tRS(__v_raw, "ContentDetail", oldValue, value)
        }
    override var JobBeginTime: String
        get() {
            return _tRG(__v_raw, "JobBeginTime", __v_raw.JobBeginTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobBeginTime")) {
                return
            }
            val oldValue = __v_raw.JobBeginTime
            __v_raw.JobBeginTime = value
            _tRS(__v_raw, "JobBeginTime", oldValue, value)
        }
    override var JobEndTime: String
        get() {
            return _tRG(__v_raw, "JobEndTime", __v_raw.JobEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobEndTime")) {
                return
            }
            val oldValue = __v_raw.JobEndTime
            __v_raw.JobEndTime = value
            _tRS(__v_raw, "JobEndTime", oldValue, value)
        }
    override var WorkExperienceId: Number?
        get() {
            return _tRG(__v_raw, "WorkExperienceId", __v_raw.WorkExperienceId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WorkExperienceId")) {
                return
            }
            val oldValue = __v_raw.WorkExperienceId
            __v_raw.WorkExperienceId = value
            _tRS(__v_raw, "WorkExperienceId", oldValue, value)
        }
}
open class AddEditJobIntentionParams (
    @JsonNotNull
    open var JobPositionId: Number,
    @JsonNotNull
    open var CityCode: String,
    @JsonNotNull
    open var CityName: String,
    @JsonNotNull
    open var HighestSalary: String,
    @JsonNotNull
    open var MinimumSalary: String,
    @JsonNotNull
    open var JobPositionIndustryId: Number,
    open var PurposeId: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return AddEditJobIntentionParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class AddEditJobIntentionParamsReactiveObject : AddEditJobIntentionParams, IUTSReactive<AddEditJobIntentionParams> {
    override var __v_raw: AddEditJobIntentionParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: AddEditJobIntentionParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(JobPositionId = __v_raw.JobPositionId, CityCode = __v_raw.CityCode, CityName = __v_raw.CityName, HighestSalary = __v_raw.HighestSalary, MinimumSalary = __v_raw.MinimumSalary, JobPositionIndustryId = __v_raw.JobPositionIndustryId, PurposeId = __v_raw.PurposeId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): AddEditJobIntentionParamsReactiveObject {
        return AddEditJobIntentionParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var JobPositionId: Number
        get() {
            return _tRG(__v_raw, "JobPositionId", __v_raw.JobPositionId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionId")) {
                return
            }
            val oldValue = __v_raw.JobPositionId
            __v_raw.JobPositionId = value
            _tRS(__v_raw, "JobPositionId", oldValue, value)
        }
    override var CityCode: String
        get() {
            return _tRG(__v_raw, "CityCode", __v_raw.CityCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCode")) {
                return
            }
            val oldValue = __v_raw.CityCode
            __v_raw.CityCode = value
            _tRS(__v_raw, "CityCode", oldValue, value)
        }
    override var CityName: String
        get() {
            return _tRG(__v_raw, "CityName", __v_raw.CityName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityName")) {
                return
            }
            val oldValue = __v_raw.CityName
            __v_raw.CityName = value
            _tRS(__v_raw, "CityName", oldValue, value)
        }
    override var HighestSalary: String
        get() {
            return _tRG(__v_raw, "HighestSalary", __v_raw.HighestSalary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HighestSalary")) {
                return
            }
            val oldValue = __v_raw.HighestSalary
            __v_raw.HighestSalary = value
            _tRS(__v_raw, "HighestSalary", oldValue, value)
        }
    override var MinimumSalary: String
        get() {
            return _tRG(__v_raw, "MinimumSalary", __v_raw.MinimumSalary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinimumSalary")) {
                return
            }
            val oldValue = __v_raw.MinimumSalary
            __v_raw.MinimumSalary = value
            _tRS(__v_raw, "MinimumSalary", oldValue, value)
        }
    override var JobPositionIndustryId: Number
        get() {
            return _tRG(__v_raw, "JobPositionIndustryId", __v_raw.JobPositionIndustryId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionIndustryId")) {
                return
            }
            val oldValue = __v_raw.JobPositionIndustryId
            __v_raw.JobPositionIndustryId = value
            _tRS(__v_raw, "JobPositionIndustryId", oldValue, value)
        }
    override var PurposeId: Number?
        get() {
            return _tRG(__v_raw, "PurposeId", __v_raw.PurposeId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PurposeId")) {
                return
            }
            val oldValue = __v_raw.PurposeId
            __v_raw.PurposeId = value
            _tRS(__v_raw, "PurposeId", oldValue, value)
        }
}
open class AddEditWorkExperienceParams (
    open var WorkExperienceId: Number? = null,
    @JsonNotNull
    open var CompanyName: String,
    @JsonNotNull
    open var JobName: String,
    @JsonNotNull
    open var ContentDetail: String,
    @JsonNotNull
    open var JobBeginTime: String,
    @JsonNotNull
    open var JobEndTime: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return AddEditWorkExperienceParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class AddEditWorkExperienceParamsReactiveObject : AddEditWorkExperienceParams, IUTSReactive<AddEditWorkExperienceParams> {
    override var __v_raw: AddEditWorkExperienceParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: AddEditWorkExperienceParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(WorkExperienceId = __v_raw.WorkExperienceId, CompanyName = __v_raw.CompanyName, JobName = __v_raw.JobName, ContentDetail = __v_raw.ContentDetail, JobBeginTime = __v_raw.JobBeginTime, JobEndTime = __v_raw.JobEndTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): AddEditWorkExperienceParamsReactiveObject {
        return AddEditWorkExperienceParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var WorkExperienceId: Number?
        get() {
            return _tRG(__v_raw, "WorkExperienceId", __v_raw.WorkExperienceId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WorkExperienceId")) {
                return
            }
            val oldValue = __v_raw.WorkExperienceId
            __v_raw.WorkExperienceId = value
            _tRS(__v_raw, "WorkExperienceId", oldValue, value)
        }
    override var CompanyName: String
        get() {
            return _tRG(__v_raw, "CompanyName", __v_raw.CompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyName")) {
                return
            }
            val oldValue = __v_raw.CompanyName
            __v_raw.CompanyName = value
            _tRS(__v_raw, "CompanyName", oldValue, value)
        }
    override var JobName: String
        get() {
            return _tRG(__v_raw, "JobName", __v_raw.JobName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobName")) {
                return
            }
            val oldValue = __v_raw.JobName
            __v_raw.JobName = value
            _tRS(__v_raw, "JobName", oldValue, value)
        }
    override var ContentDetail: String
        get() {
            return _tRG(__v_raw, "ContentDetail", __v_raw.ContentDetail, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetail")) {
                return
            }
            val oldValue = __v_raw.ContentDetail
            __v_raw.ContentDetail = value
            _tRS(__v_raw, "ContentDetail", oldValue, value)
        }
    override var JobBeginTime: String
        get() {
            return _tRG(__v_raw, "JobBeginTime", __v_raw.JobBeginTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobBeginTime")) {
                return
            }
            val oldValue = __v_raw.JobBeginTime
            __v_raw.JobBeginTime = value
            _tRS(__v_raw, "JobBeginTime", oldValue, value)
        }
    override var JobEndTime: String
        get() {
            return _tRG(__v_raw, "JobEndTime", __v_raw.JobEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobEndTime")) {
                return
            }
            val oldValue = __v_raw.JobEndTime
            __v_raw.JobEndTime = value
            _tRS(__v_raw, "JobEndTime", oldValue, value)
        }
}
open class NearbyListRults (
    open var Id: Number? = null,
    open var FilePath: String? = null,
    open var PicName: String? = null,
    open var Status: Number? = null,
    open var AddTime: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return NearbyListRultsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class NearbyListRultsReactiveObject : NearbyListRults, IUTSReactive<NearbyListRults> {
    override var __v_raw: NearbyListRults
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: NearbyListRults, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, FilePath = __v_raw.FilePath, PicName = __v_raw.PicName, Status = __v_raw.Status, AddTime = __v_raw.AddTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): NearbyListRultsReactiveObject {
        return NearbyListRultsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number?
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var FilePath: String?
        get() {
            return _tRG(__v_raw, "FilePath", __v_raw.FilePath, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("FilePath")) {
                return
            }
            val oldValue = __v_raw.FilePath
            __v_raw.FilePath = value
            _tRS(__v_raw, "FilePath", oldValue, value)
        }
    override var PicName: String?
        get() {
            return _tRG(__v_raw, "PicName", __v_raw.PicName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PicName")) {
                return
            }
            val oldValue = __v_raw.PicName
            __v_raw.PicName = value
            _tRS(__v_raw, "PicName", oldValue, value)
        }
    override var Status: Number?
        get() {
            return _tRG(__v_raw, "Status", __v_raw.Status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Status")) {
                return
            }
            val oldValue = __v_raw.Status
            __v_raw.Status = value
            _tRS(__v_raw, "Status", oldValue, value)
        }
    override var AddTime: String?
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
}
open class RenameNearbyParams (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var FileName: String,
) : UTSObject()
open class DeleteNearbyParams (
    @JsonNotNull
    open var IdArr: String,
    @JsonNotNull
    open var IsClear: Number,
) : UTSObject()
open class DeleteEducationParams (
    @JsonNotNull
    open var EducationalId: Number,
) : UTSObject()
open class DeleteJobIntentionParams (
    @JsonNotNull
    open var PurposeId: Number,
) : UTSObject()
open class DeleteWorkExperienceParams (
    @JsonNotNull
    open var WorkExperienceId: Number,
) : UTSObject()
open class DeleteProjectExperienceParams (
    @JsonNotNull
    open var WorkExperienceId: Number,
) : UTSObject()
open class EducationItem (
    @JsonNotNull
    open var EducationalBackId: Number,
    @JsonNotNull
    open var SchoolName: String,
    @JsonNotNull
    open var SchoolId: Number,
    @JsonNotNull
    open var DegreeId: Number,
    open var Degree: String? = null,
    @JsonNotNull
    open var SchoolMajor: String,
    @JsonNotNull
    open var SchoolBeginTime: String,
    @JsonNotNull
    open var SchoolEndTime: String,
    @JsonNotNull
    open var ContentDetail: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return EducationItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class EducationItemReactiveObject : EducationItem, IUTSReactive<EducationItem> {
    override var __v_raw: EducationItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: EducationItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(EducationalBackId = __v_raw.EducationalBackId, SchoolName = __v_raw.SchoolName, SchoolId = __v_raw.SchoolId, DegreeId = __v_raw.DegreeId, Degree = __v_raw.Degree, SchoolMajor = __v_raw.SchoolMajor, SchoolBeginTime = __v_raw.SchoolBeginTime, SchoolEndTime = __v_raw.SchoolEndTime, ContentDetail = __v_raw.ContentDetail) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): EducationItemReactiveObject {
        return EducationItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var EducationalBackId: Number
        get() {
            return _tRG(__v_raw, "EducationalBackId", __v_raw.EducationalBackId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("EducationalBackId")) {
                return
            }
            val oldValue = __v_raw.EducationalBackId
            __v_raw.EducationalBackId = value
            _tRS(__v_raw, "EducationalBackId", oldValue, value)
        }
    override var SchoolName: String
        get() {
            return _tRG(__v_raw, "SchoolName", __v_raw.SchoolName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolName")) {
                return
            }
            val oldValue = __v_raw.SchoolName
            __v_raw.SchoolName = value
            _tRS(__v_raw, "SchoolName", oldValue, value)
        }
    override var SchoolId: Number
        get() {
            return _tRG(__v_raw, "SchoolId", __v_raw.SchoolId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolId")) {
                return
            }
            val oldValue = __v_raw.SchoolId
            __v_raw.SchoolId = value
            _tRS(__v_raw, "SchoolId", oldValue, value)
        }
    override var DegreeId: Number
        get() {
            return _tRG(__v_raw, "DegreeId", __v_raw.DegreeId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("DegreeId")) {
                return
            }
            val oldValue = __v_raw.DegreeId
            __v_raw.DegreeId = value
            _tRS(__v_raw, "DegreeId", oldValue, value)
        }
    override var Degree: String?
        get() {
            return _tRG(__v_raw, "Degree", __v_raw.Degree, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Degree")) {
                return
            }
            val oldValue = __v_raw.Degree
            __v_raw.Degree = value
            _tRS(__v_raw, "Degree", oldValue, value)
        }
    override var SchoolMajor: String
        get() {
            return _tRG(__v_raw, "SchoolMajor", __v_raw.SchoolMajor, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolMajor")) {
                return
            }
            val oldValue = __v_raw.SchoolMajor
            __v_raw.SchoolMajor = value
            _tRS(__v_raw, "SchoolMajor", oldValue, value)
        }
    override var SchoolBeginTime: String
        get() {
            return _tRG(__v_raw, "SchoolBeginTime", __v_raw.SchoolBeginTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolBeginTime")) {
                return
            }
            val oldValue = __v_raw.SchoolBeginTime
            __v_raw.SchoolBeginTime = value
            _tRS(__v_raw, "SchoolBeginTime", oldValue, value)
        }
    override var SchoolEndTime: String
        get() {
            return _tRG(__v_raw, "SchoolEndTime", __v_raw.SchoolEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("SchoolEndTime")) {
                return
            }
            val oldValue = __v_raw.SchoolEndTime
            __v_raw.SchoolEndTime = value
            _tRS(__v_raw, "SchoolEndTime", oldValue, value)
        }
    override var ContentDetail: String
        get() {
            return _tRG(__v_raw, "ContentDetail", __v_raw.ContentDetail, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetail")) {
                return
            }
            val oldValue = __v_raw.ContentDetail
            __v_raw.ContentDetail = value
            _tRS(__v_raw, "ContentDetail", oldValue, value)
        }
}
open class WorkExperienceItem (
    open var WorkExperienceId: Number? = null,
    open var CompanyName: String? = null,
    open var JobName: String? = null,
    open var ContentDetail: String? = null,
    open var JobBeginTime: String? = null,
    open var JobEndTime: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return WorkExperienceItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class WorkExperienceItemReactiveObject : WorkExperienceItem, IUTSReactive<WorkExperienceItem> {
    override var __v_raw: WorkExperienceItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: WorkExperienceItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(WorkExperienceId = __v_raw.WorkExperienceId, CompanyName = __v_raw.CompanyName, JobName = __v_raw.JobName, ContentDetail = __v_raw.ContentDetail, JobBeginTime = __v_raw.JobBeginTime, JobEndTime = __v_raw.JobEndTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): WorkExperienceItemReactiveObject {
        return WorkExperienceItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var WorkExperienceId: Number?
        get() {
            return _tRG(__v_raw, "WorkExperienceId", __v_raw.WorkExperienceId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WorkExperienceId")) {
                return
            }
            val oldValue = __v_raw.WorkExperienceId
            __v_raw.WorkExperienceId = value
            _tRS(__v_raw, "WorkExperienceId", oldValue, value)
        }
    override var CompanyName: String?
        get() {
            return _tRG(__v_raw, "CompanyName", __v_raw.CompanyName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CompanyName")) {
                return
            }
            val oldValue = __v_raw.CompanyName
            __v_raw.CompanyName = value
            _tRS(__v_raw, "CompanyName", oldValue, value)
        }
    override var JobName: String?
        get() {
            return _tRG(__v_raw, "JobName", __v_raw.JobName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobName")) {
                return
            }
            val oldValue = __v_raw.JobName
            __v_raw.JobName = value
            _tRS(__v_raw, "JobName", oldValue, value)
        }
    override var ContentDetail: String?
        get() {
            return _tRG(__v_raw, "ContentDetail", __v_raw.ContentDetail, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetail")) {
                return
            }
            val oldValue = __v_raw.ContentDetail
            __v_raw.ContentDetail = value
            _tRS(__v_raw, "ContentDetail", oldValue, value)
        }
    override var JobBeginTime: String?
        get() {
            return _tRG(__v_raw, "JobBeginTime", __v_raw.JobBeginTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobBeginTime")) {
                return
            }
            val oldValue = __v_raw.JobBeginTime
            __v_raw.JobBeginTime = value
            _tRS(__v_raw, "JobBeginTime", oldValue, value)
        }
    override var JobEndTime: String?
        get() {
            return _tRG(__v_raw, "JobEndTime", __v_raw.JobEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobEndTime")) {
                return
            }
            val oldValue = __v_raw.JobEndTime
            __v_raw.JobEndTime = value
            _tRS(__v_raw, "JobEndTime", oldValue, value)
        }
}
open class ProjectExperienceItem (
    open var ProjectExperienceId: Number? = null,
    open var ProjectName: String? = null,
    open var ProjectPositionName: String? = null,
    open var ContentDetail: String? = null,
    open var JobBeginTime: String? = null,
    open var JobEndTime: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ProjectExperienceItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ProjectExperienceItemReactiveObject : ProjectExperienceItem, IUTSReactive<ProjectExperienceItem> {
    override var __v_raw: ProjectExperienceItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ProjectExperienceItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(ProjectExperienceId = __v_raw.ProjectExperienceId, ProjectName = __v_raw.ProjectName, ProjectPositionName = __v_raw.ProjectPositionName, ContentDetail = __v_raw.ContentDetail, JobBeginTime = __v_raw.JobBeginTime, JobEndTime = __v_raw.JobEndTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ProjectExperienceItemReactiveObject {
        return ProjectExperienceItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var ProjectExperienceId: Number?
        get() {
            return _tRG(__v_raw, "ProjectExperienceId", __v_raw.ProjectExperienceId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProjectExperienceId")) {
                return
            }
            val oldValue = __v_raw.ProjectExperienceId
            __v_raw.ProjectExperienceId = value
            _tRS(__v_raw, "ProjectExperienceId", oldValue, value)
        }
    override var ProjectName: String?
        get() {
            return _tRG(__v_raw, "ProjectName", __v_raw.ProjectName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProjectName")) {
                return
            }
            val oldValue = __v_raw.ProjectName
            __v_raw.ProjectName = value
            _tRS(__v_raw, "ProjectName", oldValue, value)
        }
    override var ProjectPositionName: String?
        get() {
            return _tRG(__v_raw, "ProjectPositionName", __v_raw.ProjectPositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProjectPositionName")) {
                return
            }
            val oldValue = __v_raw.ProjectPositionName
            __v_raw.ProjectPositionName = value
            _tRS(__v_raw, "ProjectPositionName", oldValue, value)
        }
    override var ContentDetail: String?
        get() {
            return _tRG(__v_raw, "ContentDetail", __v_raw.ContentDetail, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ContentDetail")) {
                return
            }
            val oldValue = __v_raw.ContentDetail
            __v_raw.ContentDetail = value
            _tRS(__v_raw, "ContentDetail", oldValue, value)
        }
    override var JobBeginTime: String?
        get() {
            return _tRG(__v_raw, "JobBeginTime", __v_raw.JobBeginTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobBeginTime")) {
                return
            }
            val oldValue = __v_raw.JobBeginTime
            __v_raw.JobBeginTime = value
            _tRS(__v_raw, "JobBeginTime", oldValue, value)
        }
    override var JobEndTime: String?
        get() {
            return _tRG(__v_raw, "JobEndTime", __v_raw.JobEndTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobEndTime")) {
                return
            }
            val oldValue = __v_raw.JobEndTime
            __v_raw.JobEndTime = value
            _tRS(__v_raw, "JobEndTime", oldValue, value)
        }
}
open class JobIntentionItem (
    open var PurposeId: Number? = null,
    open var JobPositionName: String? = null,
    open var JobPositionId: Number? = null,
    open var CityName: String? = null,
    open var CityCode: String? = null,
    open var MinimumSalary: String? = null,
    open var HighestSalary: String? = null,
    open var JobPositionIndustryName: String? = null,
    open var JobPositionIndustryId: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return JobIntentionItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class JobIntentionItemReactiveObject : JobIntentionItem, IUTSReactive<JobIntentionItem> {
    override var __v_raw: JobIntentionItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: JobIntentionItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(PurposeId = __v_raw.PurposeId, JobPositionName = __v_raw.JobPositionName, JobPositionId = __v_raw.JobPositionId, CityName = __v_raw.CityName, CityCode = __v_raw.CityCode, MinimumSalary = __v_raw.MinimumSalary, HighestSalary = __v_raw.HighestSalary, JobPositionIndustryName = __v_raw.JobPositionIndustryName, JobPositionIndustryId = __v_raw.JobPositionIndustryId) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): JobIntentionItemReactiveObject {
        return JobIntentionItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var PurposeId: Number?
        get() {
            return _tRG(__v_raw, "PurposeId", __v_raw.PurposeId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PurposeId")) {
                return
            }
            val oldValue = __v_raw.PurposeId
            __v_raw.PurposeId = value
            _tRS(__v_raw, "PurposeId", oldValue, value)
        }
    override var JobPositionName: String?
        get() {
            return _tRG(__v_raw, "JobPositionName", __v_raw.JobPositionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionName")) {
                return
            }
            val oldValue = __v_raw.JobPositionName
            __v_raw.JobPositionName = value
            _tRS(__v_raw, "JobPositionName", oldValue, value)
        }
    override var JobPositionId: Number?
        get() {
            return _tRG(__v_raw, "JobPositionId", __v_raw.JobPositionId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionId")) {
                return
            }
            val oldValue = __v_raw.JobPositionId
            __v_raw.JobPositionId = value
            _tRS(__v_raw, "JobPositionId", oldValue, value)
        }
    override var CityName: String?
        get() {
            return _tRG(__v_raw, "CityName", __v_raw.CityName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityName")) {
                return
            }
            val oldValue = __v_raw.CityName
            __v_raw.CityName = value
            _tRS(__v_raw, "CityName", oldValue, value)
        }
    override var CityCode: String?
        get() {
            return _tRG(__v_raw, "CityCode", __v_raw.CityCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CityCode")) {
                return
            }
            val oldValue = __v_raw.CityCode
            __v_raw.CityCode = value
            _tRS(__v_raw, "CityCode", oldValue, value)
        }
    override var MinimumSalary: String?
        get() {
            return _tRG(__v_raw, "MinimumSalary", __v_raw.MinimumSalary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinimumSalary")) {
                return
            }
            val oldValue = __v_raw.MinimumSalary
            __v_raw.MinimumSalary = value
            _tRS(__v_raw, "MinimumSalary", oldValue, value)
        }
    override var HighestSalary: String?
        get() {
            return _tRG(__v_raw, "HighestSalary", __v_raw.HighestSalary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HighestSalary")) {
                return
            }
            val oldValue = __v_raw.HighestSalary
            __v_raw.HighestSalary = value
            _tRS(__v_raw, "HighestSalary", oldValue, value)
        }
    override var JobPositionIndustryName: String?
        get() {
            return _tRG(__v_raw, "JobPositionIndustryName", __v_raw.JobPositionIndustryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionIndustryName")) {
                return
            }
            val oldValue = __v_raw.JobPositionIndustryName
            __v_raw.JobPositionIndustryName = value
            _tRS(__v_raw, "JobPositionIndustryName", oldValue, value)
        }
    override var JobPositionIndustryId: Number?
        get() {
            return _tRG(__v_raw, "JobPositionIndustryId", __v_raw.JobPositionIndustryId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobPositionIndustryId")) {
                return
            }
            val oldValue = __v_raw.JobPositionIndustryId
            __v_raw.JobPositionIndustryId = value
            _tRS(__v_raw, "JobPositionIndustryId", oldValue, value)
        }
}
open class OpusPicItem (
    open var PicUrl: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return OpusPicItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class OpusPicItemReactiveObject : OpusPicItem, IUTSReactive<OpusPicItem> {
    override var __v_raw: OpusPicItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: OpusPicItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(PicUrl = __v_raw.PicUrl) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): OpusPicItemReactiveObject {
        return OpusPicItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var PicUrl: String?
        get() {
            return _tRG(__v_raw, "PicUrl", __v_raw.PicUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PicUrl")) {
                return
            }
            val oldValue = __v_raw.PicUrl
            __v_raw.PicUrl = value
            _tRS(__v_raw, "PicUrl", oldValue, value)
        }
}
open class GetOnlineResumeResult (
    open var Id: Number? = null,
    open var AddTime: String? = null,
    open var AuditReason: String? = null,
    open var AuditTime: String? = null,
    open var Birthday: String? = null,
    open var CertificateId: String? = null,
    open var ClubActivities: String? = null,
    open var Education: String? = null,
    open var EducationalBackCodeData: UTSArray<EducationItem>? = null,
    open var Email: String? = null,
    open var WorkExperienceCodeData: UTSArray<WorkExperienceItem>? = null,
    open var HelpType: String? = null,
    open var IsHide: Number? = null,
    open var Ismark: Number? = null,
    open var Ismy: Number? = null,
    open var JobExperienced: String? = null,
    open var MemberId: String? = null,
    open var Mobile: String? = null,
    open var MyFileId: Number? = null,
    open var MyHonor: String? = null,
    open var MySuperiority: String? = null,
    open var Nation: String? = null,
    open var OpusDescription: String? = null,
    open var OrderPicData: UTSArray<OpusPicItem>? = null,
    open var PracticalActivity: String? = null,
    open var ProfessionalSkills: String? = null,
    open var ProjectExperienceCodeData: UTSArray<ProjectExperienceItem>? = null,
    open var PurposeCodeData: UTSArray<JobIntentionItem>? = null,
    open var School: UTSArray<EducationItem>? = null,
    open var ServiceCategoryName: String? = null,
    open var Sex: Number? = null,
    open var StartWorking: String? = null,
    open var Statue: Number? = null,
    open var StatusName: String? = null,
    open var UserNick: String? = null,
    open var WeChat: String? = null,
    open var Avatar: String? = null,
    open var NickName: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetOnlineResumeResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetOnlineResumeResultReactiveObject : GetOnlineResumeResult, IUTSReactive<GetOnlineResumeResult> {
    override var __v_raw: GetOnlineResumeResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetOnlineResumeResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, AddTime = __v_raw.AddTime, AuditReason = __v_raw.AuditReason, AuditTime = __v_raw.AuditTime, Birthday = __v_raw.Birthday, CertificateId = __v_raw.CertificateId, ClubActivities = __v_raw.ClubActivities, Education = __v_raw.Education, EducationalBackCodeData = __v_raw.EducationalBackCodeData, Email = __v_raw.Email, WorkExperienceCodeData = __v_raw.WorkExperienceCodeData, HelpType = __v_raw.HelpType, IsHide = __v_raw.IsHide, Ismark = __v_raw.Ismark, Ismy = __v_raw.Ismy, JobExperienced = __v_raw.JobExperienced, MemberId = __v_raw.MemberId, Mobile = __v_raw.Mobile, MyFileId = __v_raw.MyFileId, MyHonor = __v_raw.MyHonor, MySuperiority = __v_raw.MySuperiority, Nation = __v_raw.Nation, OpusDescription = __v_raw.OpusDescription, OrderPicData = __v_raw.OrderPicData, PracticalActivity = __v_raw.PracticalActivity, ProfessionalSkills = __v_raw.ProfessionalSkills, ProjectExperienceCodeData = __v_raw.ProjectExperienceCodeData, PurposeCodeData = __v_raw.PurposeCodeData, School = __v_raw.School, ServiceCategoryName = __v_raw.ServiceCategoryName, Sex = __v_raw.Sex, StartWorking = __v_raw.StartWorking, Statue = __v_raw.Statue, StatusName = __v_raw.StatusName, UserNick = __v_raw.UserNick, WeChat = __v_raw.WeChat, Avatar = __v_raw.Avatar, NickName = __v_raw.NickName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetOnlineResumeResultReactiveObject {
        return GetOnlineResumeResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number?
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var AddTime: String?
        get() {
            return _tRG(__v_raw, "AddTime", __v_raw.AddTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AddTime")) {
                return
            }
            val oldValue = __v_raw.AddTime
            __v_raw.AddTime = value
            _tRS(__v_raw, "AddTime", oldValue, value)
        }
    override var AuditReason: String?
        get() {
            return _tRG(__v_raw, "AuditReason", __v_raw.AuditReason, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AuditReason")) {
                return
            }
            val oldValue = __v_raw.AuditReason
            __v_raw.AuditReason = value
            _tRS(__v_raw, "AuditReason", oldValue, value)
        }
    override var AuditTime: String?
        get() {
            return _tRG(__v_raw, "AuditTime", __v_raw.AuditTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("AuditTime")) {
                return
            }
            val oldValue = __v_raw.AuditTime
            __v_raw.AuditTime = value
            _tRS(__v_raw, "AuditTime", oldValue, value)
        }
    override var Birthday: String?
        get() {
            return _tRG(__v_raw, "Birthday", __v_raw.Birthday, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Birthday")) {
                return
            }
            val oldValue = __v_raw.Birthday
            __v_raw.Birthday = value
            _tRS(__v_raw, "Birthday", oldValue, value)
        }
    override var CertificateId: String?
        get() {
            return _tRG(__v_raw, "CertificateId", __v_raw.CertificateId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CertificateId")) {
                return
            }
            val oldValue = __v_raw.CertificateId
            __v_raw.CertificateId = value
            _tRS(__v_raw, "CertificateId", oldValue, value)
        }
    override var ClubActivities: String?
        get() {
            return _tRG(__v_raw, "ClubActivities", __v_raw.ClubActivities, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ClubActivities")) {
                return
            }
            val oldValue = __v_raw.ClubActivities
            __v_raw.ClubActivities = value
            _tRS(__v_raw, "ClubActivities", oldValue, value)
        }
    override var Education: String?
        get() {
            return _tRG(__v_raw, "Education", __v_raw.Education, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Education")) {
                return
            }
            val oldValue = __v_raw.Education
            __v_raw.Education = value
            _tRS(__v_raw, "Education", oldValue, value)
        }
    override var EducationalBackCodeData: UTSArray<EducationItem>?
        get() {
            return _tRG(__v_raw, "EducationalBackCodeData", __v_raw.EducationalBackCodeData, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("EducationalBackCodeData")) {
                return
            }
            val oldValue = __v_raw.EducationalBackCodeData
            __v_raw.EducationalBackCodeData = value
            _tRS(__v_raw, "EducationalBackCodeData", oldValue, value)
        }
    override var Email: String?
        get() {
            return _tRG(__v_raw, "Email", __v_raw.Email, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Email")) {
                return
            }
            val oldValue = __v_raw.Email
            __v_raw.Email = value
            _tRS(__v_raw, "Email", oldValue, value)
        }
    override var WorkExperienceCodeData: UTSArray<WorkExperienceItem>?
        get() {
            return _tRG(__v_raw, "WorkExperienceCodeData", __v_raw.WorkExperienceCodeData, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WorkExperienceCodeData")) {
                return
            }
            val oldValue = __v_raw.WorkExperienceCodeData
            __v_raw.WorkExperienceCodeData = value
            _tRS(__v_raw, "WorkExperienceCodeData", oldValue, value)
        }
    override var HelpType: String?
        get() {
            return _tRG(__v_raw, "HelpType", __v_raw.HelpType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HelpType")) {
                return
            }
            val oldValue = __v_raw.HelpType
            __v_raw.HelpType = value
            _tRS(__v_raw, "HelpType", oldValue, value)
        }
    override var IsHide: Number?
        get() {
            return _tRG(__v_raw, "IsHide", __v_raw.IsHide, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("IsHide")) {
                return
            }
            val oldValue = __v_raw.IsHide
            __v_raw.IsHide = value
            _tRS(__v_raw, "IsHide", oldValue, value)
        }
    override var Ismark: Number?
        get() {
            return _tRG(__v_raw, "Ismark", __v_raw.Ismark, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Ismark")) {
                return
            }
            val oldValue = __v_raw.Ismark
            __v_raw.Ismark = value
            _tRS(__v_raw, "Ismark", oldValue, value)
        }
    override var Ismy: Number?
        get() {
            return _tRG(__v_raw, "Ismy", __v_raw.Ismy, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Ismy")) {
                return
            }
            val oldValue = __v_raw.Ismy
            __v_raw.Ismy = value
            _tRS(__v_raw, "Ismy", oldValue, value)
        }
    override var JobExperienced: String?
        get() {
            return _tRG(__v_raw, "JobExperienced", __v_raw.JobExperienced, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("JobExperienced")) {
                return
            }
            val oldValue = __v_raw.JobExperienced
            __v_raw.JobExperienced = value
            _tRS(__v_raw, "JobExperienced", oldValue, value)
        }
    override var MemberId: String?
        get() {
            return _tRG(__v_raw, "MemberId", __v_raw.MemberId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MemberId")) {
                return
            }
            val oldValue = __v_raw.MemberId
            __v_raw.MemberId = value
            _tRS(__v_raw, "MemberId", oldValue, value)
        }
    override var Mobile: String?
        get() {
            return _tRG(__v_raw, "Mobile", __v_raw.Mobile, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Mobile")) {
                return
            }
            val oldValue = __v_raw.Mobile
            __v_raw.Mobile = value
            _tRS(__v_raw, "Mobile", oldValue, value)
        }
    override var MyFileId: Number?
        get() {
            return _tRG(__v_raw, "MyFileId", __v_raw.MyFileId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MyFileId")) {
                return
            }
            val oldValue = __v_raw.MyFileId
            __v_raw.MyFileId = value
            _tRS(__v_raw, "MyFileId", oldValue, value)
        }
    override var MyHonor: String?
        get() {
            return _tRG(__v_raw, "MyHonor", __v_raw.MyHonor, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MyHonor")) {
                return
            }
            val oldValue = __v_raw.MyHonor
            __v_raw.MyHonor = value
            _tRS(__v_raw, "MyHonor", oldValue, value)
        }
    override var MySuperiority: String?
        get() {
            return _tRG(__v_raw, "MySuperiority", __v_raw.MySuperiority, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MySuperiority")) {
                return
            }
            val oldValue = __v_raw.MySuperiority
            __v_raw.MySuperiority = value
            _tRS(__v_raw, "MySuperiority", oldValue, value)
        }
    override var Nation: String?
        get() {
            return _tRG(__v_raw, "Nation", __v_raw.Nation, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Nation")) {
                return
            }
            val oldValue = __v_raw.Nation
            __v_raw.Nation = value
            _tRS(__v_raw, "Nation", oldValue, value)
        }
    override var OpusDescription: String?
        get() {
            return _tRG(__v_raw, "OpusDescription", __v_raw.OpusDescription, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("OpusDescription")) {
                return
            }
            val oldValue = __v_raw.OpusDescription
            __v_raw.OpusDescription = value
            _tRS(__v_raw, "OpusDescription", oldValue, value)
        }
    override var OrderPicData: UTSArray<OpusPicItem>?
        get() {
            return _tRG(__v_raw, "OrderPicData", __v_raw.OrderPicData, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("OrderPicData")) {
                return
            }
            val oldValue = __v_raw.OrderPicData
            __v_raw.OrderPicData = value
            _tRS(__v_raw, "OrderPicData", oldValue, value)
        }
    override var PracticalActivity: String?
        get() {
            return _tRG(__v_raw, "PracticalActivity", __v_raw.PracticalActivity, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PracticalActivity")) {
                return
            }
            val oldValue = __v_raw.PracticalActivity
            __v_raw.PracticalActivity = value
            _tRS(__v_raw, "PracticalActivity", oldValue, value)
        }
    override var ProfessionalSkills: String?
        get() {
            return _tRG(__v_raw, "ProfessionalSkills", __v_raw.ProfessionalSkills, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProfessionalSkills")) {
                return
            }
            val oldValue = __v_raw.ProfessionalSkills
            __v_raw.ProfessionalSkills = value
            _tRS(__v_raw, "ProfessionalSkills", oldValue, value)
        }
    override var ProjectExperienceCodeData: UTSArray<ProjectExperienceItem>?
        get() {
            return _tRG(__v_raw, "ProjectExperienceCodeData", __v_raw.ProjectExperienceCodeData, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ProjectExperienceCodeData")) {
                return
            }
            val oldValue = __v_raw.ProjectExperienceCodeData
            __v_raw.ProjectExperienceCodeData = value
            _tRS(__v_raw, "ProjectExperienceCodeData", oldValue, value)
        }
    override var PurposeCodeData: UTSArray<JobIntentionItem>?
        get() {
            return _tRG(__v_raw, "PurposeCodeData", __v_raw.PurposeCodeData, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PurposeCodeData")) {
                return
            }
            val oldValue = __v_raw.PurposeCodeData
            __v_raw.PurposeCodeData = value
            _tRS(__v_raw, "PurposeCodeData", oldValue, value)
        }
    override var School: UTSArray<EducationItem>?
        get() {
            return _tRG(__v_raw, "School", __v_raw.School, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("School")) {
                return
            }
            val oldValue = __v_raw.School
            __v_raw.School = value
            _tRS(__v_raw, "School", oldValue, value)
        }
    override var ServiceCategoryName: String?
        get() {
            return _tRG(__v_raw, "ServiceCategoryName", __v_raw.ServiceCategoryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("ServiceCategoryName")) {
                return
            }
            val oldValue = __v_raw.ServiceCategoryName
            __v_raw.ServiceCategoryName = value
            _tRS(__v_raw, "ServiceCategoryName", oldValue, value)
        }
    override var Sex: Number?
        get() {
            return _tRG(__v_raw, "Sex", __v_raw.Sex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Sex")) {
                return
            }
            val oldValue = __v_raw.Sex
            __v_raw.Sex = value
            _tRS(__v_raw, "Sex", oldValue, value)
        }
    override var StartWorking: String?
        get() {
            return _tRG(__v_raw, "StartWorking", __v_raw.StartWorking, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("StartWorking")) {
                return
            }
            val oldValue = __v_raw.StartWorking
            __v_raw.StartWorking = value
            _tRS(__v_raw, "StartWorking", oldValue, value)
        }
    override var Statue: Number?
        get() {
            return _tRG(__v_raw, "Statue", __v_raw.Statue, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Statue")) {
                return
            }
            val oldValue = __v_raw.Statue
            __v_raw.Statue = value
            _tRS(__v_raw, "Statue", oldValue, value)
        }
    override var StatusName: String?
        get() {
            return _tRG(__v_raw, "StatusName", __v_raw.StatusName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("StatusName")) {
                return
            }
            val oldValue = __v_raw.StatusName
            __v_raw.StatusName = value
            _tRS(__v_raw, "StatusName", oldValue, value)
        }
    override var UserNick: String?
        get() {
            return _tRG(__v_raw, "UserNick", __v_raw.UserNick, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("UserNick")) {
                return
            }
            val oldValue = __v_raw.UserNick
            __v_raw.UserNick = value
            _tRS(__v_raw, "UserNick", oldValue, value)
        }
    override var WeChat: String?
        get() {
            return _tRG(__v_raw, "WeChat", __v_raw.WeChat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("WeChat")) {
                return
            }
            val oldValue = __v_raw.WeChat
            __v_raw.WeChat = value
            _tRS(__v_raw, "WeChat", oldValue, value)
        }
    override var Avatar: String?
        get() {
            return _tRG(__v_raw, "Avatar", __v_raw.Avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Avatar")) {
                return
            }
            val oldValue = __v_raw.Avatar
            __v_raw.Avatar = value
            _tRS(__v_raw, "Avatar", oldValue, value)
        }
    override var NickName: String?
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
}
open class GetResumeListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    open var Keywords: String? = null,
    @JsonNotNull
    open var myType: Number,
    open var HelpType: String? = null,
    open var Sex: Number? = null,
    @JsonNotNull
    open var Education: String,
    @JsonNotNull
    open var MaxAge: Number,
    @JsonNotNull
    open var MinAge: Number,
    @JsonNotNull
    open var MaxExperience: Number,
    @JsonNotNull
    open var MinExperience: Number,
) : UTSObject()
enum class Api__10(override val value: String) : UTSEnumString {
    GET_ONLINE_RESUME("/api/Hireworkers/OnlineResumeInfo"),
    EDIT_OR_ADD_ONLINE_RESUME("/api/Hireworkers/AddEidtOnlineResume"),
    ADD_EDIT_EDUCATION("/api/Hireworkers/AddEidtEducationalBack"),
    ADD_EDIT_PROJECT_EXPERIENCE("/api/Hireworkers/AddEidtProjectExperience"),
    ADD_EDIT_JOB_INTENTION("/api/Hireworkers/AddEidtPurpose"),
    ADD_EDIT_WORK_EXPERIENCE("/api/Hireworkers/AddEidtWorkExperience"),
    UPLOAD_NEARBY("/api/Hireworkers/UserPublishFile"),
    GET_NEARBY_LIST("/api/Hireworkers/WorkFileList"),
    RENAME_NEARBY("/api/Hireworkers/RenameFile"),
    DELETE_NEARBY("/api/Hireworkers/DelMyWorkFile"),
    DELETE_EDUCATION("/api/Hireworkers/DelEducationalBack"),
    DELETE_JOB_INTENTION("/api/Hireworkers/DelPurpose"),
    DELETE_WORK_EXPERIENCE("/api/Hireworkers/DelWorkExperience"),
    DELETE_PROJECT_EXPERIENCE("/api/Hireworkers/DelProjectExperience"),
    GET_RESUME_LIST("/api/Hireworkers/OnlineResumeList")
}
fun getOnlineResume(params: GetOnlineResumeParams): UTSPromise<GetOnlineResumeResult> {
    return request.post<GetOnlineResumeResult>(Api__10.GET_ONLINE_RESUME, params)
}
fun editOrAddOnlineResume(params: AddEditOnlineResumeParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.EDIT_OR_ADD_ONLINE_RESUME, params)
}
fun addEditEducation(params: AddEditEducationParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.ADD_EDIT_EDUCATION, params)
}
fun addEditProjectExperience(params: AddEditProjectExperienceParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.ADD_EDIT_PROJECT_EXPERIENCE, params)
}
fun addEditJobIntention(params: AddEditJobIntentionParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.ADD_EDIT_JOB_INTENTION, params)
}
fun addEditWorkExperience(params: AddEditWorkExperienceParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.ADD_EDIT_WORK_EXPERIENCE, params)
}
fun getNearbyList(params: Pagination): UTSPromise<Any> {
    return request.post<Any>(Api__10.GET_NEARBY_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
fun renameNearby(params: RenameNearbyParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.RENAME_NEARBY, params)
}
fun deleteNearby(params: DeleteNearbyParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.DELETE_NEARBY, params)
}
fun deleteEducation(params: DeleteEducationParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.DELETE_EDUCATION, params)
}
fun deleteJobIntention(params: DeleteJobIntentionParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.DELETE_JOB_INTENTION, params)
}
fun deleteWorkExperience(params: DeleteWorkExperienceParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.DELETE_WORK_EXPERIENCE, params)
}
fun deleteProjectExperience(params: DeleteProjectExperienceParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.DELETE_PROJECT_EXPERIENCE, params)
}
fun getResumeList(params: GetResumeListParams): UTSPromise<Any> {
    return request.post<Any>(Api__10.GET_RESUME_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
val getCommunicateList = fun(params: GetCommunicateListParams): UTSPromise<Any> {
    return request.post(Api__2.GET_COMMUNICATE_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
val getHaveSeen = fun(params: GetCommunicateListParams): UTSPromise<Any> {
    return request.post(Api__2.GET_HAVE_SEEN, params, HttpRequestExtraOptions(isTotal = true))
}
val getDeliveryRecord = fun(params: GetDeliveryRecordParams): UTSPromise<Any> {
    return request.post(Api__2.GET_DELIVERY_RECORD, params, HttpRequestExtraOptions(isTotal = true))
}
enum class Api__11(override val value: String) : UTSEnumString {
    GET_ORDER_LIST("/api/Order/VipPackageOrderList"),
    GET_ORDER_DETAIL("/api/Order/VipPackageOrderDetails"),
    GET_ORDER_CANCEL("/api/Order/CancelVipPackageOrders"),
    GET_ORDER_DELETE("/api/Order/DeleteVipPackageOrders"),
    CREATE_VIP_ORDER("/api/User/VipPackageBuyOrder"),
    CREATE_POINTS_ORDER("/api/User/PointsBuyOrder"),
    CREATE_PINNED_ORDER("/api/User/PinnedBuyOrder"),
    CREATE_PLUS_MEMBER_ORDER("/api/User/PlusBuyOrder")
}
fun createPlusMemberOrder(params: CreatePlusMemberOrderParams): UTSPromise<CreatePlusMemberOrderResult> {
    return request.post<CreatePlusMemberOrderResult>(Api__11.CREATE_PLUS_MEMBER_ORDER, params)
}
open class Dic (
    @JsonNotNull
    open var Avatar: String,
    @JsonNotNull
    open var IsMy: Number,
    @JsonNotNull
    open var MemberId: String,
    @JsonNotNull
    open var NickName: String,
    @JsonNotNull
    open var Score: Number,
    @JsonNotNull
    open var Sort: Number,
    @JsonNotNull
    open var SumScore: Number,
) : UTSObject()
open class GetIntegralRankingResult (
    @JsonNotNull
    open var dic: UTSArray<Dic>,
    @JsonNotNull
    open var Score: Number,
    @JsonNotNull
    open var SumScore: Number,
) : UTSObject()
open class GetTaskListParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var MissionType: Number,
) : UTSObject()
open class GetTaskListResult (
    @JsonNotNull
    open var AlreadyCount: Number,
    @JsonNotNull
    open var Claim: Number,
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var MissionGenre: Number,
    @JsonNotNull
    open var MissionGenreName: String,
    @JsonNotNull
    open var Name: String,
    @JsonNotNull
    open var PicUrl: String,
    @JsonNotNull
    open var Points: Number,
    @JsonNotNull
    open var Quantity: Any,
    @JsonNotNull
    open var Synopsis: String,
    @JsonNotNull
    open var Type: Number,
) : UTSObject()
open class PostClaimTaskParams (
    @JsonNotNull
    open var MissionId: Number,
) : UTSObject()
open class GetLotteryInfoResult (
    @JsonNotNull
    open var Id: Number,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var PicUrl: String,
    @JsonNotNull
    open var PrizeType: Number,
    @JsonNotNull
    open var PrizeTypeName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return GetLotteryInfoResultReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class GetLotteryInfoResultReactiveObject : GetLotteryInfoResult, IUTSReactive<GetLotteryInfoResult> {
    override var __v_raw: GetLotteryInfoResult
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: GetLotteryInfoResult, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Id = __v_raw.Id, name = __v_raw.name, PicUrl = __v_raw.PicUrl, PrizeType = __v_raw.PrizeType, PrizeTypeName = __v_raw.PrizeTypeName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): GetLotteryInfoResultReactiveObject {
        return GetLotteryInfoResultReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Id: Number
        get() {
            return _tRG(__v_raw, "Id", __v_raw.Id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Id")) {
                return
            }
            val oldValue = __v_raw.Id
            __v_raw.Id = value
            _tRS(__v_raw, "Id", oldValue, value)
        }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var PicUrl: String
        get() {
            return _tRG(__v_raw, "PicUrl", __v_raw.PicUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PicUrl")) {
                return
            }
            val oldValue = __v_raw.PicUrl
            __v_raw.PicUrl = value
            _tRS(__v_raw, "PicUrl", oldValue, value)
        }
    override var PrizeType: Number
        get() {
            return _tRG(__v_raw, "PrizeType", __v_raw.PrizeType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizeType")) {
                return
            }
            val oldValue = __v_raw.PrizeType
            __v_raw.PrizeType = value
            _tRS(__v_raw, "PrizeType", oldValue, value)
        }
    override var PrizeTypeName: String
        get() {
            return _tRG(__v_raw, "PrizeTypeName", __v_raw.PrizeTypeName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PrizeTypeName")) {
                return
            }
            val oldValue = __v_raw.PrizeTypeName
            __v_raw.PrizeTypeName = value
            _tRS(__v_raw, "PrizeTypeName", oldValue, value)
        }
}
open class PostLotteryResult (
    @JsonNotNull
    open var PrizeName: String,
    @JsonNotNull
    open var PrizeOrderNo: String,
    @JsonNotNull
    open var PrizeType: Number,
    @JsonNotNull
    open var Id: Number,
) : UTSObject()
open class PostLotteryCouponResult (
    @JsonNotNull
    open var draw: UTSArray<String>,
    @JsonNotNull
    open var WinPrize: Number,
) : UTSObject()
enum class Api__12(override val value: String) : UTSEnumString {
    POST_SIGN("/api/User/SingInSuccess"),
    POST_SIGN_CASH("/api/User/EditUserReminder"),
    GET_WIN("/api/User/PrizeNotice"),
    GET_INTEGRAL_RANKING("/api/User/PointsRankingList"),
    GET_TASK_LIST("/api/User/MissionList"),
    POST_CLAIM_TASK("/api/User/ClaimMission"),
    GET_LOTTERY_INFO("/api/User/PrizeList"),
    POST_LOTTERY("/api/User/DrawPrize"),
    GET_LOTTERY_COUPON("/api/User/PrizeRaffleTicket"),
    POST_LOTTERY_COUPON("/api/User/RaffleTicketPrize"),
    GET_EXCHANGE_LIST("/api/User/PrizeRecordList"),
    GET_VIDEO_INFO("/api/User/VideoList"),
    POST_VIDEO_WATCH("/api/User/VideoRecordSuccess"),
    POST_CLAIM_VIDEO_REWARD("/api/User/VideoClaim"),
    POST_SHARE_MINI_PROGRAM("/api/User/ShareAPP"),
    POST_GENERATE_PUBLIC_NUMBER("/api/WxPay/ShowQrcode")
}
val postSign = fun(): UTSPromise<Any> {
    return request.post(Api__12.POST_SIGN)
}
val postSignCash = fun(): UTSPromise<Any> {
    return request.post(Api__12.POST_SIGN_CASH)
}
val getWin = fun(): UTSPromise<UTSArray<GetWinResult>> {
    return request.post<UTSArray<GetWinResult>>(Api__12.GET_WIN)
}
val getIntegralRanking = fun(params: Pagination): UTSPromise<GetIntegralRankingResult> {
    return request.post<GetIntegralRankingResult>(Api__12.GET_INTEGRAL_RANKING, params)
}
val getTaskList = fun(params: GetTaskListParams): UTSPromise<Any> {
    return request.post(Api__12.GET_TASK_LIST, params, HttpRequestExtraOptions(isTotal = true))
}
val postClaimTask = fun(params: PostClaimTaskParams): UTSPromise<Any> {
    return request.post(Api__12.POST_CLAIM_TASK, params)
}
val getLotteryInfo = fun(params: Pagination): UTSPromise<UTSArray<GetLotteryInfoResult>> {
    return request.post<UTSArray<GetLotteryInfoResult>>(Api__12.GET_LOTTERY_INFO, params)
}
val postLottery = fun(): UTSPromise<PostLotteryResult> {
    return request.post<PostLotteryResult>(Api__12.POST_LOTTERY)
}
val getLotteryCoupon = fun(): UTSPromise<UTSArray<String>> {
    return request.post<UTSArray<String>>(Api__12.GET_LOTTERY_COUPON)
}
val postLotteryCoupon = fun(): UTSPromise<PostLotteryCouponResult> {
    return request.post<PostLotteryCouponResult>(Api__12.POST_LOTTERY_COUPON)
}
val useInitStore = defineStore<InitStore>("init", fun(): InitStore {
    val initialized = ref(false)
    val loading = ref(false)
    val error = ref("")
    val isReady = computed(fun(): Boolean {
        return initialized.value && error.value === ""
    }
    )
    val isFailed = computed(fun(): Boolean {
        return initialized.value && error.value !== ""
    }
    )
    val bootstrap = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (initialized.value || loading.value) {
                    return@w1
                }
                loading.value = true
                error.value = ""
                try {
                    val appStore = useAppStore()
                    val userStore = useAuthStore()
                    val locationStore = useLocationStore()
                    val roleStore = useRoleStore()
                    await(UTSPromise.all(_uA(
                        roleStore.initRole(),
                        userStore.restoreLocalUser()
                    )))
                    console.log("[Init] 本地用户/角色数据恢复完成")
                    await(appStore.initApp())
                    appStore.setupGlobalMessageListening()
                    console.log("[Init] 应用基础配置初始化完成")
                    await(userStore.initUser())
                    await(locationStore.initCurrentLocation())
                    console.log("[Init] 用户/角色数据初始化完成")
                    await(userStore.performStartupRouting())
                }
                 catch (err: Throwable) {
                    val errorMsg = if (err is UTSError) {
                        (err as UTSError).message
                    } else {
                        "应用初始化失败"
                    }
                    error.value = errorMsg
                    console.error("[Init] ❌ 应用初始化失败:", err)
                }
                 finally {
                    loading.value = false
                    initialized.value = true
                }
        })
    }
    val retry = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                initialized.value = false
                loading.value = false
                error.value = ""
                await(bootstrap())
        })
    }
    val reset = fun(){
        initialized.value = false
        loading.value = false
        error.value = ""
    }
    return InitStore(initialized, loading, error, isReady, isFailed, bootstrap, retry, reset)
}
)
open class JoinStore {
    open var formData: JoinFormData? = null
    open lateinit var packageList: UTSArray<GetPlusMemberListResult>
    open var selectedPackageId: Number? = null
    open var loading: Boolean by Delegates.notNull()
    open var submitting: Boolean by Delegates.notNull()
    open lateinit var setFormData: (data: JoinFormData) -> Unit
    open lateinit var fetchPackageList: () -> UTSPromise<Boolean>
    open lateinit var selectPackage: (id: Number) -> Unit
    open lateinit var submitOrder: () -> UTSPromise<CreatePlusMemberOrderResult?>
    open lateinit var reset: () -> Unit
    constructor(formData: Any, packageList: Any, selectedPackageId: Any, loading: Any, submitting: Any, setFormData: (data: JoinFormData) -> Unit, fetchPackageList: () -> UTSPromise<Boolean>, selectPackage: (id: Number) -> Unit, submitOrder: () -> UTSPromise<CreatePlusMemberOrderResult?>, reset: () -> Unit){
        this.formData = formData as JoinFormData?
        this.packageList = packageList as UTSArray<GetPlusMemberListResult>
        this.selectedPackageId = selectedPackageId as Number?
        this.loading = loading as Boolean
        this.submitting = submitting as Boolean
        this.setFormData = setFormData
        this.fetchPackageList = fetchPackageList
        this.selectPackage = selectPackage
        this.submitOrder = submitOrder
        this.reset = reset
    }
}
val useJoinStore = defineStore<JoinStore>("join", fun(): JoinStore {
    val formData = ref<JoinFormData?>(null)
    val packageList = ref(_uA<GetPlusMemberListResult>())
    val selectedPackageId = ref<Number?>(null)
    val loading = ref(false)
    val submitting = ref(false)
    val setFormData = fun(data: JoinFormData){
        formData.value = data
    }
    val fetchPackageList = fun(): UTSPromise<Boolean> {
        return wrapUTSPromise(suspend w1@{
                if (formData.value == null) {
                    console.warn("表单数据为空，无法获取套餐列表")
                    return@w1 false
                }
                val data = formData.value!!
                val params = GetPlusMemberListParams(Account = data.Account, Mobile = data.Mobile, PassWord = data.PassWord, FranchiseLevel = data.FranchiseLevel, ProvinceCode = data.ProvinceCode, CityCode = data.CityCode ?: "", AreaCode = data.AreaCode ?: "", ProvinceName = data.ProvinceName, CityName = data.CityName, DistrictName = data.DistrictName)
                console.log(data.cascadeAddressCodes)
                loading.value = true
                try {
                    val res = await(getPlusMemberList(params))
                    console.log(res)
                    if (res != null && res.length > 0) {
                        packageList.value = res
                        selectedPackageId.value = res[0].Id ?: null
                        return@w1 true
                    } else {
                        packageList.value = _uA()
                        selectedPackageId.value = null
                        showToast("暂无可用套餐")
                        return@w1 false
                    }
                }
                 catch (err: Throwable) {
                    val errorMsg = if (err is UTSError) {
                        (err as UTSError).message
                    } else {
                        "获取套餐列表失败，请稍后重试"
                    }
                    console.error("获取套餐列表失败:", err)
                    packageList.value = _uA()
                    selectedPackageId.value = null
                    showError(errorMsg)
                    return@w1 false
                }
                 finally {
                    loading.value = false
                }
        })
    }
    val selectPackage = fun(id: Number){
        selectedPackageId.value = id
    }
    val submitOrder = fun(): UTSPromise<CreatePlusMemberOrderResult?> {
        return wrapUTSPromise(suspend w1@{
                if (formData.value == null) {
                    showToast("请先填写加盟信息")
                    return@w1 null
                }
                if (selectedPackageId.value == null) {
                    showToast("请选择套餐")
                    return@w1 null
                }
                val data = formData.value!!
                val params = CreatePlusMemberOrderParams(PlusId = selectedPackageId.value!! as Number, Account = data.Account, PassWord = data.PassWord, Mobile = data.Mobile, FranchiseLevel = data.FranchiseLevel, ProvinceCode = data.ProvinceCode, CityCode = if ((data.FranchiseLevel === 2 || data.FranchiseLevel === 3)) {
                    data.CityCode
                } else {
                    ""
                }
                , AreaCode = if (data.FranchiseLevel === 3) {
                    data.AreaCode
                } else {
                    ""
                }
                )
                submitting.value = true
                try {
                    val res = await(createPlusMemberOrder(params))
                    if (res != null) {
                        return@w1 res
                    }
                    return@w1 null
                }
                 catch (err: Throwable) {
                    val errorMsg = if (err is UTSError) {
                        (err as UTSError).message
                    } else {
                        "提交订单失败，请稍后重试"
                    }
                    console.error("提交订单失败:", err)
                    showToast(errorMsg)
                    return@w1 null
                }
                 finally {
                    submitting.value = false
                }
        })
    }
    val reset = fun(){
        formData.value = null
        packageList.value = _uA()
        selectedPackageId.value = null
        loading.value = false
        submitting.value = false
    }
    return JoinStore(formData, packageList, selectedPackageId, loading, submitting, setFormData, fetchPackageList, selectPackage, submitOrder, reset)
}
)
open class ProfileFormData (
    @JsonNotNull
    open var avatar: String,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var gender: String,
    @JsonNotNull
    open var nation: String,
    @JsonNotNull
    open var workStartTime: String,
    @JsonNotNull
    open var phone: String,
    @JsonNotNull
    open var wechat: String,
    @JsonNotNull
    open var birthday: String,
    @JsonNotNull
    open var email: String,
    @JsonNotNull
    open var status: String,
    @JsonNotNull
    open var education: String,
) : UTSObject()
open class ResumeStore {
    open var loading: Boolean by Delegates.notNull()
    open var saving: Boolean by Delegates.notNull()
    open lateinit var resumeData: GetOnlineResumeResult
    open lateinit var certificates: UTSArray<String>
    open lateinit var portfolioImages: UTSArray<String>
    open lateinit var customModules: UTSArray<CustomModule>
    open lateinit var editingEducation: EducationItem
    open lateinit var editingWorkExperience: WorkExperienceItem
    open lateinit var editingProjectExperience: ProjectExperienceItem
    open lateinit var editingJobIntention: JobIntentionItem
    open lateinit var genderText: String
    open lateinit var fetchResume: (onlineId: Number) -> UTSPromise<Unit>
    open lateinit var updateBasicInfo: (data: ProfileFormData) -> Unit
    open lateinit var saveBasicInfo: () -> UTSPromise<Boolean>
    open lateinit var updateHelpType: (status: String) -> Unit
    open lateinit var updateAdvantage: (content: String) -> Unit
    open lateinit var updateCertificates: (ids: String, names: UTSArray<String>) -> Unit
    open lateinit var updatePortfolio: (images: UTSArray<String>, description: String) -> Unit
    open lateinit var updateProfessionalSkills: (content: String) -> Unit
    open lateinit var updateClubActivities: (content: String) -> Unit
    open lateinit var updatePracticalActivity: (content: String) -> Unit
    open lateinit var updateMyHonor: (content: String) -> Unit
    open lateinit var setEditingEducation: (item: EducationItem) -> Unit
    open lateinit var setEditingWorkExperience: (item: WorkExperienceItem) -> Unit
    open lateinit var setEditingProjectExperience: (item: ProjectExperienceItem) -> Unit
    open lateinit var setEditingJobIntention: (item: JobIntentionItem) -> Unit
    open lateinit var clearEditing: () -> Unit
    open lateinit var reset: () -> Unit
    constructor(loading: Any, saving: Any, resumeData: GetOnlineResumeResult, certificates: Any, portfolioImages: Any, customModules: Any, editingEducation: EducationItem, editingWorkExperience: WorkExperienceItem, editingProjectExperience: ProjectExperienceItem, editingJobIntention: JobIntentionItem, genderText: Any, fetchResume: (onlineId: Number) -> UTSPromise<Unit>, updateBasicInfo: (data: ProfileFormData) -> Unit, saveBasicInfo: () -> UTSPromise<Boolean>, updateHelpType: (status: String) -> Unit, updateAdvantage: (content: String) -> Unit, updateCertificates: (ids: String, names: UTSArray<String>) -> Unit, updatePortfolio: (images: UTSArray<String>, description: String) -> Unit, updateProfessionalSkills: (content: String) -> Unit, updateClubActivities: (content: String) -> Unit, updatePracticalActivity: (content: String) -> Unit, updateMyHonor: (content: String) -> Unit, setEditingEducation: (item: EducationItem) -> Unit, setEditingWorkExperience: (item: WorkExperienceItem) -> Unit, setEditingProjectExperience: (item: ProjectExperienceItem) -> Unit, setEditingJobIntention: (item: JobIntentionItem) -> Unit, clearEditing: () -> Unit, reset: () -> Unit){
        this.loading = loading as Boolean
        this.saving = saving as Boolean
        this.resumeData = resumeData
        this.certificates = certificates as UTSArray<String>
        this.portfolioImages = portfolioImages as UTSArray<String>
        this.customModules = customModules as UTSArray<CustomModule>
        this.editingEducation = editingEducation
        this.editingWorkExperience = editingWorkExperience
        this.editingProjectExperience = editingProjectExperience
        this.editingJobIntention = editingJobIntention
        this.genderText = genderText as String
        this.fetchResume = fetchResume
        this.updateBasicInfo = updateBasicInfo
        this.saveBasicInfo = saveBasicInfo
        this.updateHelpType = updateHelpType
        this.updateAdvantage = updateAdvantage
        this.updateCertificates = updateCertificates
        this.updatePortfolio = updatePortfolio
        this.updateProfessionalSkills = updateProfessionalSkills
        this.updateClubActivities = updateClubActivities
        this.updatePracticalActivity = updatePracticalActivity
        this.updateMyHonor = updateMyHonor
        this.setEditingEducation = setEditingEducation
        this.setEditingWorkExperience = setEditingWorkExperience
        this.setEditingProjectExperience = setEditingProjectExperience
        this.setEditingJobIntention = setEditingJobIntention
        this.clearEditing = clearEditing
        this.reset = reset
    }
}
val useResumeStore = defineStore<ResumeStore>("resume", fun(): ResumeStore {
    val loading = ref(false)
    val saving = ref(false)
    val resumeData = reactive<GetOnlineResumeResult>(GetOnlineResumeResult(EducationalBackCodeData = _uA<EducationItem>(), WorkExperienceCodeData = _uA<WorkExperienceItem>(), ProjectExperienceCodeData = _uA<ProjectExperienceItem>(), PurposeCodeData = _uA<JobIntentionItem>()))
    val certificates = ref(_uA<String>())
    val portfolioImages = ref(_uA<String>())
    val customModules = ref(_uA<CustomModule>(CustomModule(name = "专业技能", icon = "skill"), CustomModule(name = "社团/组织经历", icon = "group"), CustomModule(name = "实践活动", icon = "activity"), CustomModule(name = "所获荣耀", icon = "honor")))
    val editingEducation = reactive<EducationItem>(EducationItem(EducationalBackId = 0, SchoolName = "", SchoolId = 0, DegreeId = 0, SchoolMajor = "", SchoolBeginTime = "", SchoolEndTime = "", ContentDetail = ""))
    val editingWorkExperience = reactive<WorkExperienceItem>(WorkExperienceItem(WorkExperienceId = 0, CompanyName = "", JobName = "", ContentDetail = "", JobBeginTime = "", JobEndTime = ""))
    val editingProjectExperience = reactive<ProjectExperienceItem>(ProjectExperienceItem(ProjectExperienceId = 0, ProjectName = "", ProjectPositionName = "", ContentDetail = "", JobBeginTime = "", JobEndTime = ""))
    val editingJobIntention = reactive<JobIntentionItem>(JobIntentionItem(PurposeId = 0, JobPositionId = 0, JobPositionName = "", CityName = "", CityCode = "", MinimumSalary = "", HighestSalary = "", JobPositionIndustryId = 0, JobPositionIndustryName = ""))
    val genderText = computed(fun(): String {
        if (resumeData.Sex === 1) {
            return "男"
        }
        if (resumeData.Sex === 2) {
            return "女"
        }
        return "保密"
    }
    )
    val parsePortfolioImages = fun(picList: UTSArray<OpusPicItem>){
        if (picList.length === 0) {
            portfolioImages.value = _uA()
            return
        }
        portfolioImages.value = picList.map(fun(item): String {
            return item.PicUrl ?: ""
        }
        ).filter(fun(url): Boolean {
            return url !== ""
        }
        )
    }
    val mapResponseToState = fun(res: GetOnlineResumeResult){
        if (res.EducationalBackCodeData == null || !UTSArray.isArray(res.EducationalBackCodeData)) {
            res.EducationalBackCodeData = _uA()
        }
        if (res.WorkExperienceCodeData == null || !UTSArray.isArray(res.WorkExperienceCodeData)) {
            res.WorkExperienceCodeData = _uA()
        }
        if (res.ProjectExperienceCodeData == null || !UTSArray.isArray(res.ProjectExperienceCodeData)) {
            res.ProjectExperienceCodeData = _uA()
        }
        if (res.PurposeCodeData == null || !UTSArray.isArray(res.PurposeCodeData)) {
            res.PurposeCodeData = _uA()
        }
        UTSJSONObject.assign(resumeData, res)
        parsePortfolioImages(res.OrderPicData ?: _uA())
        console.log("parsePortfolioImages", res)
    }
    val fetchResume = fun(onlineId: Number): UTSPromise<Unit> {
        return wrapUTSPromise(suspend w1@{
                if (loading.value) {
                    return@w1
                }
                loading.value = true
                try {
                    val res = await(getOnlineResume(GetOnlineResumeParams(Id = onlineId)))
                    if (res != null) {
                        mapResponseToState(res)
                    }
                }
                 catch (err: Throwable) {
                    console.error("获取在线简历失败:", err)
                    uni_showToast(ShowToastOptions(title = "获取简历失败", icon = "none"))
                }
                 finally {
                    loading.value = false
                }
        })
    }
    val updateBasicInfo = fun(data: ProfileFormData){
        UTSJSONObject.assign(resumeData, GetOnlineResumeResult(Avatar = data.avatar, UserNick = data.name, Sex = if (data.gender === "男") {
            1
        } else {
            if (data.gender === "女") {
                2
            } else {
                0
            }
        }
        , Nation = data.nation, Mobile = data.phone, WeChat = data.wechat, Birthday = data.birthday, Email = data.email, StartWorking = data.workStartTime, HelpType = data.status, Education = data.education))
    }
    val buildPortfolioJson = fun(): String {
        if (portfolioImages.value.length === 0) {
            return ""
        }
        val picArr = portfolioImages.value.map(fun(url): UTSJSONObject {
            val obj = UTSJSONObject()
            obj["PicUrl"] = url
            return obj
        }
        )
        return JSON.stringify(picArr)
    }
    val saveBasicInfo = fun(): UTSPromise<Boolean> {
        return wrapUTSPromise(suspend w1@{
                if (saving.value) {
                    return@w1 false
                }
                saving.value = true
                try {
                    val params = AddEditOnlineResumeParams(Mobile = resumeData.Mobile ?: "", UserNick = resumeData.UserNick ?: "", Sex = resumeData.Sex ?: 1, OnlineResumeId = resumeData.Id ?: 0, HelpType = resumeData.HelpType ?: "", Email = resumeData.Email ?: "", Nation = resumeData.Nation ?: "", MyHonor = resumeData.MyHonor ?: "", PracticalActivity = resumeData.PracticalActivity ?: "", ClubActivities = resumeData.ClubActivities ?: "", StartWorking = resumeData.StartWorking ?: "", ProfessionalSkills = resumeData.ProfessionalSkills ?: "", CertificateId = resumeData.CertificateId ?: "", OpusDescription = resumeData.OpusDescription ?: "", WeChat = resumeData.WeChat ?: "", Avatar = resumeData.Avatar ?: "", MySuperiority = resumeData.MySuperiority ?: "", Education = parseInt(resumeData.Education ?: "0") as Number, Birthday = resumeData.Birthday ?: "", OpusPicNo = buildPortfolioJson())
                    await(editOrAddOnlineResume(params))
                    uni_showToast(ShowToastOptions(title = "保存成功", icon = "success"))
                    return@w1 true
                }
                 catch (err: Throwable) {
                    val errorMsg = if ((err as UTSError).message != null) {
                        (err as UTSError).message
                    } else {
                        "保存失败，请稍后重试"
                    }
                    console.error("保存简历失败:", err)
                    uni_showToast(ShowToastOptions(title = errorMsg, icon = "none"))
                    return@w1 false
                }
                 finally {
                    saving.value = false
                }
        })
    }
    val updateHelpType = fun(status: String){
        resumeData.HelpType = status
    }
    val updateAdvantage = fun(content: String){
        resumeData.MySuperiority = content
    }
    val updateCertificates = fun(ids: String, names: UTSArray<String>){
        resumeData.CertificateId = ids
        certificates.value = names
    }
    val updatePortfolio = fun(images: UTSArray<String>, description: String){
        portfolioImages.value = images
        resumeData.OpusDescription = description
    }
    val updateProfessionalSkills = fun(content: String){
        resumeData.ProfessionalSkills = content
    }
    val updateClubActivities = fun(content: String){
        resumeData.ClubActivities = content
    }
    val updatePracticalActivity = fun(content: String){
        resumeData.PracticalActivity = content
    }
    val updateMyHonor = fun(content: String){
        resumeData.MyHonor = content
    }
    val setEditingEducation = fun(item: EducationItem){
        UTSJSONObject.assign(editingEducation, item)
    }
    val setEditingWorkExperience = fun(item: WorkExperienceItem){
        UTSJSONObject.assign(editingWorkExperience, item)
    }
    val setEditingProjectExperience = fun(item: ProjectExperienceItem){
        UTSJSONObject.assign(editingProjectExperience, item)
    }
    val setEditingJobIntention = fun(item: JobIntentionItem){
        UTSJSONObject.assign(editingJobIntention, item)
    }
    val clearEditing = fun(){
        UTSJSONObject.assign(editingEducation, EducationItem(EducationalBackId = 0, SchoolName = "", SchoolId = 0, DegreeId = 0, Degree = "", SchoolMajor = "", SchoolBeginTime = "", SchoolEndTime = "", ContentDetail = ""))
        UTSJSONObject.assign(editingWorkExperience, WorkExperienceItem(WorkExperienceId = 0, CompanyName = "", JobName = "", ContentDetail = "", JobBeginTime = "", JobEndTime = ""))
        UTSJSONObject.assign(editingProjectExperience, ProjectExperienceItem(ProjectExperienceId = 0, ProjectName = "", ProjectPositionName = "", ContentDetail = "", JobBeginTime = "", JobEndTime = ""))
        UTSJSONObject.assign(editingJobIntention, JobIntentionItem(PurposeId = 0, JobPositionId = 0, JobPositionName = "", CityName = "", CityCode = "", MinimumSalary = "", HighestSalary = "", JobPositionIndustryId = 0, JobPositionIndustryName = ""))
    }
    val reset = fun(){
        loading.value = false
        saving.value = false
        val resumeObj = resumeData as UTSJSONObject
        for(key in resolveUTSKeyIterator(resumeObj)){
            resumeObj[key] = null
        }
        UTSJSONObject.assign(resumeData, GetOnlineResumeResult(EducationalBackCodeData = _uA(), WorkExperienceCodeData = _uA(), ProjectExperienceCodeData = _uA(), PurposeCodeData = _uA()))
        certificates.value = _uA()
        portfolioImages.value = _uA()
        clearEditing()
    }
    return ResumeStore(loading, saving, resumeData, certificates, portfolioImages, customModules, editingEducation, editingWorkExperience, editingProjectExperience, editingJobIntention, genderText, fetchResume, updateBasicInfo, saveBasicInfo, updateHelpType, updateAdvantage, updateCertificates, updatePortfolio, updateProfessionalSkills, updateClubActivities, updatePracticalActivity, updateMyHonor, setEditingEducation, setEditingWorkExperience, setEditingProjectExperience, setEditingJobIntention, clearEditing, reset)
}
)
val LOADING_PLACEHOLDER_VALUE = "__loading__"
val STORAGE_KEY = "MIAOJIE_ADDRESS_TREE"
val NEVER_EXPIRE = Date.now() + 3153600000000
open class AddressStore {
    open lateinit var addressTree: UTSArray<AddressOption>
    open lateinit var getDistrictOptions: (type: String, parentCode: String?) -> UTSPromise<UTSArray<AddressOption>>
    constructor(addressTree: Any, getDistrictOptions: (type: String, parentCode: String?) -> UTSPromise<UTSArray<AddressOption>>){
        this.addressTree = addressTree as UTSArray<AddressOption>
        this.getDistrictOptions = getDistrictOptions
    }
}
val useAddressStore = defineStore<AddressStore>("address", fun(): AddressStore {
    val addressTree = ref(_uA<AddressOption>())
    val createPlaceholder = fun(): UTSArray<AddressOption> {
        return _uA(
            AddressOption(label = "加载中...", value = LOADING_PLACEHOLDER_VALUE, disabled = true)
        )
    }
    fun stripPlaceholders(opts: UTSArray<AddressOption>): UTSArray<AddressOption> {
        return opts.map(fun(item: AddressOption): AddressOption {
            val children = item.children
            if (children == null || children!!.length === 0) {
                return AddressOption(label = item.label, value = item.value, children = null)
            }
            if (children!!.length === 1 && children!![0].value === LOADING_PLACEHOLDER_VALUE) {
                return AddressOption(label = item.label, value = item.value, children = null)
            }
            return AddressOption(label = item.label, value = item.value, children = stripPlaceholders(children!!))
        }
        )
    }
    val saveToStorage = fun(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                console.log("[AddressStore] 同步树结构到本地存储")
                val cleanTree = stripPlaceholders(addressTree.value)
                await(setStorage(STORAGE_KEY, cleanTree, NEVER_EXPIRE))
        })
    }
    fun findOption(opts: UTSArray<AddressOption>, targetValue: String): AddressOption? {
        run {
            var i: Number = 0
            while(i < opts.length){
                if (opts[i].value === targetValue) {
                    return opts[i]
                }
                val children = opts[i].children
                if (children != null && children!!.length > 0) {
                    val found = findOption(children!!, targetValue)
                    if (found != null) {
                        return found
                    }
                }
                i++
            }
        }
        return null
    }
    val getDistrictOptions = fun(type: String, parentCode: String?): UTSPromise<UTSArray<AddressOption>> {
        return wrapUTSPromise(suspend w1@{
                val _parentCode = if (parentCode != null) {
                    parentCode
                } else {
                    ""
                }
                if (addressTree.value.length === 0) {
                    val storageRes = await(getStorage<UTSArray<AddressOption>>(STORAGE_KEY))
                    if (storageRes.success && storageRes.value != null && storageRes.value!!.length > 0) {
                        console.log("[AddressStore] 从本地存储恢复地址树")
                        addressTree.value = storageRes.value!!
                    }
                }
                if (type === "Province" && addressTree.value.length > 0) {
                    run {
                        var i: Number = 0
                        while(i < addressTree.value.length){
                            if (addressTree.value[i].children == null) {
                                addressTree.value[i].children = createPlaceholder()
                            }
                            i++
                        }
                    }
                    return@w1 addressTree.value
                }
                if (type === "Province") {
                    try {
                        val res = await(getAddresses(GetAddressesParams(Types = "Province")))
                        if (res != null) {
                            addressTree.value = res.map(fun(item: GetAddressesResult): AddressOption {
                                return (AddressOption(label = item.Name ?: "", value = item.Code ?: "", children = createPlaceholder()))
                            }
                            )
                            await(saveToStorage())
                        }
                    }
                     catch (e: Throwable) {
                        console.error("[AddressStore] 获取省份数据失败:", e)
                    }
                    return@w1 addressTree.value
                }
                if (_parentCode == "") {
                    return@w1 _uA()
                }
                val targetNode = findOption(addressTree.value, _parentCode)
                if (targetNode == null) {
                    console.warn("[AddressStore] 未找到父级节点: " + _parentCode)
                    return@w1 _uA()
                }
                val children = targetNode.children
                val hasRealChildren = children != null && children!!.length > 0 && children!![0].value !== LOADING_PLACEHOLDER_VALUE
                if (hasRealChildren) {
                    console.log("[AddressStore] 命中缓存: " + _parentCode + " 的子级")
                    return@w1 children!!
                }
                try {
                    console.log("[AddressStore] 请求网络数据: " + type + " - " + parentCode)
                    val res = await(getAddresses(GetAddressesParams(Types = type, Code = _parentCode)))
                    if (res != null) {
                        val childrenResult = res.map(fun(item: GetAddressesResult): AddressOption {
                            return (AddressOption(label = item.Name ?: "", value = item.Code ?: "", children = if (type === "City") {
                                createPlaceholder()
                            } else {
                                null
                            }
                            ))
                        }
                        )
                        val existingChildren = targetNode.children
                        if (existingChildren != null) {
                            existingChildren!!.splice(0, existingChildren!!.length)
                            run {
                                var i: Number = 0
                                while(i < childrenResult.length){
                                    existingChildren!!.push(childrenResult[i])
                                    i++
                                }
                            }
                        } else {
                            targetNode.children = childrenResult
                        }
                        addressTree.value = addressTree.value.slice()
                        saveToStorage()
                        return@w1 if (existingChildren != null) {
                            existingChildren!!
                        } else {
                            childrenResult
                        }
                    }
                }
                 catch (e: Throwable) {
                    console.error("[AddressStore] 获取 " + type + " 数据失败:", e)
                }
                return@w1 _uA()
        })
    }
    return AddressStore(addressTree, getDistrictOptions)
}
)
open class GenApp : BaseApp {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        setCurrentInstance(__ins)
        __ins.proxy = this
        GenApp.setup(this, SetupContext(__ins))
    }
    open var onLastPageBackPress: () -> Unit
        get() {
            return unref(this.`$exposed`["onLastPageBackPress"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "onLastPageBackPress", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenApp, __setupCtx: SetupContext) -> Any? = fun(__props, __setupCtx): Any? {
            val __expose = __setupCtx.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenApp
            val _cache = __ins.renderCache
            var firstBackTime: Number = 0
            val initStore = useInitStore()
            val appStore = useAppStore()
            onLaunch(fun(_options){
                console.log("App Launch")
                initStore.bootstrap()
            }
            )
            onShow(fun(){
                console.log("App Show")
                appStore.ensureGlobalMessageConnection()
            }
            )
            onHide(fun(){
                console.log("App Hide")
            }
            )
            val onLastPageBackPress = fun(){
                console.log("App LastPageBackPress")
                if (firstBackTime == 0) {
                    uni_showToast(ShowToastOptions(title = "再按一次退出应用", position = "bottom"))
                    firstBackTime = Date.now()
                    setTimeout(fun(){
                        firstBackTime = 0
                    }, 2000)
                } else if (Date.now() - firstBackTime < 2000) {
                    firstBackTime = Date.now()
                    uni_exit(null)
                }
            }
            __expose(_uM("onLastPageBackPress" to onLastPageBackPress))
            return fun(): Any? {
                return null
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            _nCS(_uA(
                styles0,
                styles1,
                styles2,
                styles3,
                styles4,
                styles5
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("iconfont" to _pS(_uM("!fontFamily" to "iconfont", "fontSize" to 16, "fontStyle" to "normal", "WebkitFontSmoothing" to "antialiased", "MozOsxFontSmoothing" to "grayscale")), "icon-zhifubaozhifu" to _pS(_uM("content:before" to "'\\e634'")), "icon-weixinhaoyou" to _pS(_uM("content:before" to "'\\e615'")), "icon-fenxiangtu" to _pS(_uM("content:before" to "'\\e665'")), "icon-weixinpengyouquan" to _pS(_uM("content:before" to "'\\e603'")), "icon-qiandao1" to _pS(_uM("content:before" to "'\\e641'")), "icon-yuyuemianshi" to _pS(_uM("content:before" to "'\\e6fe'")), "icon-shangjia" to _pS(_uM("content:before" to "'\\e6e6'")), "icon-paixing" to _pS(_uM("content:before" to "'\\e601'")), "icon-choujiang" to _pS(_uM("content:before" to "'\\e659'")), "icon-qiandao" to _pS(_uM("content:before" to "'\\e693'")), "icon-duihuanchenggong" to _pS(_uM("content:before" to "'\\e66d'")), "icon-cuowuwenjian" to _pS(_uM("content:before" to "'\\e715'")), "icon-gengduo" to _pS(_uM("content:before" to "'\\e612'")), "icon-pdf" to _pS(_uM("content:before" to "'\\e8e8'")), "icon-zhifushibai" to _pS(_uM("content:before" to "'\\e6bf'")), "icon-ic_paid" to _pS(_uM("content:before" to "'\\e60f'")), "icon-nan" to _pS(_uM("content:before" to "'\\e8b4'")), "icon-jiaoseqiehuan" to _pS(_uM("content:before" to "'\\e661'")), "icon-a-kefukefu" to _pS(_uM("content:before" to "'\\e63a'")), "icon-toudijilu2" to _pS(_uM("content:before" to "'\\e64b'")), "icon-lajitong" to _pS(_uM("content:before" to "'\\e658'")), "icon-weixin" to _pS(_uM("content:before" to "'\\e607'")), "icon-arrow-left" to _pS(_uM("content:before" to "'\\e646'")), "icon-shangjiantou" to _pS(_uM("content:before" to "'\\e655'")), "icon-xiajiantou" to _pS(_uM("content:before" to "'\\e656'")), "icon-youjiantou" to _pS(_uM("content:before" to "'\\e60a'")), "icon-shanchu" to _pS(_uM("content:before" to "'\\e61a'")), "icon-sousuo" to _pS(_uM("content:before" to "'\\e602'")), "icon-shangcheng" to _pS(_uM("content:before" to "'\\e637'")), "icon-jifen" to _pS(_uM("content:before" to "'\\e617'")), "icon-ziyuan" to _pS(_uM("content:before" to "'\\e7cd'")), "icon-jubao" to _pS(_uM("content:before" to "'\\e652'")), "icon-48" to _pS(_uM("content:before" to "'\\e62c'")), "icon-shouji" to _pS(_uM("content:before" to "'\\e692'")), "icon-tixing" to _pS(_uM("content:before" to "'\\e6cc'")), "icon-guanyu" to _pS(_uM("content:before" to "'\\e6a1'")), "icon-jujue" to _pS(_uM("content:before" to "'\\e657'")), "icon-jingjiren" to _pS(_uM("content:before" to "'\\e6e0'")), "icon-xitong" to _pS(_uM("content:before" to "'\\e67c'")), "icon-clean" to _pS(_uM("content:before" to "'\\e616'")), "icon-xiajia" to _pS(_uM("content:before" to "'\\e6e4'")), "icon-dizhi" to _pS(_uM("content:before" to "'\\e63e'")), "icon-tupian" to _pS(_uM("content:before" to "'\\e889'")), "icon-guanzhu" to _pS(_uM("content:before" to "'\\e60d'")), "icon-shuaxin" to _pS(_uM("content:before" to "'\\e62b'")), "icon-zaixianjianli" to _pS(_uM("content:before" to "'\\e627'")), "icon-fujianjianli" to _pS(_uM("content:before" to "'\\e631'")), "icon-youxiang" to _pS(_uM("content:before" to "'\\e908'")), "icon-zhifuwancheng" to _pS(_uM("content:before" to "'\\e669'")), "icon-yunduanxiazai" to _pS(_uM("content:before" to "'\\ec1d'")), "icon-qianbao" to _pS(_uM("content:before" to "'\\e613'")), "icon-shoucang" to _pS(_uM("content:before" to "'\\e614'")), "icon-dingzhi" to _pS(_uM("content:before" to "'\\e620'")), "icon-gongjuxiang" to _pS(_uM("content:before" to "'\\e639'")), "icon-resume-line" to _pS(_uM("content:before" to "'\\e64a'")), "icon-fahuo" to _pS(_uM("content:before" to "'\\e643'")), "icon-tuandui" to _pS(_uM("content:before" to "'\\e604'")), "icon-daizhifu" to _pS(_uM("content:before" to "'\\e709'")), "icon-lock" to _pS(_uM("content:before" to "'\\e9d4'")), "icon-huiyuantaocan" to _pS(_uM("content:before" to "'\\e69f'")), "icon-qianbao1" to _pS(_uM("content:before" to "'\\e851'")), "icon-jinzhi" to _pS(_uM("content:before" to "'\\e608'")), "icon-tianjia" to _pS(_uM("content:before" to "'\\e62d'")), "icon-plus" to _pS(_uM("content:before" to "'\\e705'")), "icon-geren" to _pS(_uM("content:before" to "'\\e654'")), "icon-fasong" to _pS(_uM("content:before" to "'\\e65e'")), "icon-bangzhu" to _pS(_uM("content:before" to "'\\e8ac'")), "icon-hongbao" to _pS(_uM("content:before" to "'\\e8b0'")), "icon-nv" to _pS(_uM("content:before" to "'\\e8b3'")), "icon-tuandui1" to _pS(_uM("content:before" to "'\\e66b'")), "icon-dianzan" to _pS(_uM("content:before" to "'\\e600'")), "icon-shaixuan" to _pS(_uM("content:before" to "'\\e60b'")), "icon-dianhua" to _pS(_uM("content:before" to "'\\e61b'")), "icon-hongbao1" to _pS(_uM("content:before" to "'\\e60e'")), "icon-pengyouquan" to _pS(_uM("content:before" to "'\\e635'")), "icon-jiaohuan" to _pS(_uM("content:before" to "'\\e653'")), "icon-toudijilu1" to _pS(_uM("content:before" to "'\\e651'")), "icon-fenxiang" to _pS(_uM("content:before" to "'\\e644'")), "icon-bianji" to _pS(_uM("content:before" to "'\\e645'")), "icon-daochu1024-26" to _pS(_uM("content:before" to "'\\e617'")), "icon-daochu1024-27" to _pS(_uM("content:before" to "'\\e618'")), "icon-daochu1024-28" to _pS(_uM("content:before" to "'\\e619'")), "icon-yinsibaohu" to _pS(_uM("content:before" to "'\\e6af'")), "icon-qiuzhiyixiang" to _pS(_uM("content:before" to "'\\e6a6'")), "icon-fuzhi" to _pS(_uM("content:before" to "'\\e65f'")), "icon-xiaoxi" to _pS(_uM("content:before" to "'\\e781'")), "icon-diannao" to _pS(_uM("content:before" to "'\\e660'")), "icon-jiamengshang" to _pS(_uM("content:before" to "'\\e739'")), "icon-qiye" to _pS(_uM("content:before" to "'\\e61f'")), "icon-quxiaoshoucang" to _pS(_uM("content:before" to "'\\e6bd'")), "icon-men-line" to _pS(_uM("content:before" to "'\\e7a1'")), "icon-yuyue" to _pS(_uM("content:before" to "'\\e6de'")), "icon-yuebuzu" to _pS(_uM("content:before" to "'\\e71f'")), "pointer-events-none" to _pS(_uM("pointerEvents" to "none")), "visible" to _pS(_uM("visibility" to "visible")), "fixed" to _pS(_uM("position" to "fixed")), "absolute" to _pS(_uM("position" to "absolute")), "relative" to _pS(_uM("position" to "relative")), "inset-0" to _pS(_uM("inset" to "0px")))
            }
        val styles1: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("-right-1" to _pS(_uM("right" to -4)), "-right-2" to _pS(_uM("right" to -8)), "-top-1" to _pS(_uM("top" to -4)), "-top-2" to _pS(_uM("top" to -8)), "-top-3" to _pS(_uM("top" to -12)), "bottom-0" to _pS(_uM("bottom" to 0)), "bottom-1" to _pS(_uM("bottom" to 4)), "bottom-3" to _pS(_uM("bottom" to 12)), "bottom-4" to _pS(_uM("bottom" to 16)), "left-0" to _pS(_uM("left" to 0)), "left-3" to _pS(_uM("left" to 12)), "left-4" to _pS(_uM("left" to 16)), "right-0" to _pS(_uM("right" to 0)), "right-1" to _pS(_uM("right" to 4)), "right-2" to _pS(_uM("right" to 8)), "right-3" to _pS(_uM("right" to 12)), "right-4" to _pS(_uM("right" to 16)), "right-5" to _pS(_uM("right" to 20)), "top-0" to _pS(_uM("top" to 0)), "top-1" to _pS(_uM("top" to 4)), "top-3" to _pS(_uM("top" to 12)), "top-4" to _pS(_uM("top" to 16)), "z-10" to _pS(_uM("zIndex" to 10)), "z-40" to _pS(_uM("zIndex" to 40)), "z-50" to _pS(_uM("zIndex" to 50)), "m-0" to _pS(_uM("marginTop" to 0, "marginRight" to 0, "marginBottom" to 0, "marginLeft" to 0)), "m-2" to _pS(_uM("marginTop" to 8, "marginRight" to 8, "marginBottom" to 8, "marginLeft" to 8)), "m-4" to _pS(_uM("marginTop" to 16, "marginRight" to 16, "marginBottom" to 16, "marginLeft" to 16)), "m-6" to _pS(_uM("marginTop" to 24, "marginRight" to 24, "marginBottom" to 24, "marginLeft" to 24)), "mx-1" to _pS(_uM("marginLeft" to 4, "marginRight" to 4)), "mx-2" to _pS(_uM("marginLeft" to 8, "marginRight" to 8)), "mx-3" to _pS(_uM("marginLeft" to 12, "marginRight" to 12)), "mx-4" to _pS(_uM("marginLeft" to 16, "marginRight" to 16)), "mx-6" to _pS(_uM("marginLeft" to 24, "marginRight" to 24)), "mx-8" to _pS(_uM("marginLeft" to 32, "marginRight" to 32)), "mx-auto" to _pS(_uM("marginLeft" to "auto", "marginRight" to "auto")), "my-0" to _pS(_uM("marginTop" to 0, "marginBottom" to 0)), "my-2" to _pS(_uM("marginTop" to 8, "marginBottom" to 8)), "my-4" to _pS(_uM("marginTop" to 16, "marginBottom" to 16)), "-mt-1" to _pS(_uM("marginTop" to -4)), "-mt-10" to _pS(_uM("marginTop" to -40)), "-mt-12" to _pS(_uM("marginTop" to -48)), "-mt-6" to _pS(_uM("marginTop" to -24)), "mb-1" to _pS(_uM("marginBottom" to 4)), "mb-10" to _pS(_uM("marginBottom" to 40)), "mb-12" to _pS(_uM("marginBottom" to 48)), "mb-2" to _pS(_uM("marginBottom" to 8)), "mb-3" to _pS(_uM("marginBottom" to 12)), "mb-4" to _pS(_uM("marginBottom" to 16)), "mb-5" to _pS(_uM("marginBottom" to 20)), "mb-6" to _pS(_uM("marginBottom" to 24)), "mb-8" to _pS(_uM("marginBottom" to 32)), "mb-80" to _pS(_uM("marginBottom" to 320)), "ml-1" to _pS(_uM("marginLeft" to 4)), "ml-2" to _pS(_uM("marginLeft" to 8)), "ml-3" to _pS(_uM("marginLeft" to 12)), "ml-4" to _pS(_uM("marginLeft" to 16)), "ml-6" to _pS(_uM("marginLeft" to 24)), "ml-auto" to _pS(_uM("marginLeft" to "auto")), "mr-0" to _pS(_uM("marginRight" to 0)), "mr-1" to _pS(_uM("marginRight" to 4)), "mr-2" to _pS(_uM("marginRight" to 8)), "mr-3" to _pS(_uM("marginRight" to 12)), "mr-4" to _pS(_uM("marginRight" to 16)), "mr-6" to _pS(_uM("marginRight" to 24)), "mr-8" to _pS(_uM("marginRight" to 32)), "mt-1" to _pS(_uM("marginTop" to 4)), "mt-10" to _pS(_uM("marginTop" to 40)), "mt-12" to _pS(_uM("marginTop" to 48)), "mt-16" to _pS(_uM("marginTop" to 64)), "mt-2" to _pS(_uM("marginTop" to 8)), "mt-20" to _pS(_uM("marginTop" to 80)), "mt-3" to _pS(_uM("marginTop" to 12)), "mt-4" to _pS(_uM("marginTop" to 16)), "mt-40" to _pS(_uM("marginTop" to 160)), "mt-5" to _pS(_uM("marginTop" to 20)), "mt-6" to _pS(_uM("marginTop" to 24)), "mt-8" to _pS(_uM("marginTop" to 32)), "mt-auto" to _pS(_uM("marginTop" to "auto")), "mt-xs" to _pS(_uM("marginTop" to 4)), "box-border" to _pS(_uM("boxSizing" to "border-box")), "line-clamp-1" to _pS(_uM("overflow" to "hidden", "WebkitBoxOrient" to "vertical", "WebkitLineClamp" to 1)), "line-clamp-2" to _pS(_uM("overflow" to "hidden", "WebkitBoxOrient" to "vertical", "WebkitLineClamp" to 2)), "line-clamp-3" to _pS(_uM("overflow" to "hidden", "WebkitBoxOrient" to "vertical", "WebkitLineClamp" to 3)), "flex" to _pS(_uM("display" to "flex")), "hidden" to _pS(_uM("display" to "none")), "h-1" to _pS(_uM("height" to 4)), "h-10" to _pS(_uM("height" to 40)), "h-11" to _pS(_uM("height" to 44)), "h-12" to _pS(_uM("height" to 48)), "h-14" to _pS(_uM("height" to 56)), "h-16" to _pS(_uM("height" to 64)), "h-2" to _pS(_uM("height" to 8)), "h-20" to _pS(_uM("height" to 80)), "h-24" to _pS(_uM("height" to 96)), "h-28" to _pS(_uM("height" to 112)), "h-3" to _pS(_uM("height" to 12)), "h-30" to _pS(_uM("height" to 120)), "h-32" to _pS(_uM("height" to 128)), "h-36" to _pS(_uM("height" to 144)))
            }
        val styles2: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("h-4" to _pS(_uM("height" to 16)), "h-40" to _pS(_uM("height" to 160)), "h-44" to _pS(_uM("height" to 176)), "h-5" to _pS(_uM("height" to 20)), "h-52" to _pS(_uM("height" to 208)), "h-6" to _pS(_uM("height" to 24)), "h-60" to _pS(_uM("height" to 240)), "h-7" to _pS(_uM("height" to 28)), "h-72" to _pS(_uM("height" to 288)), "h-8" to _pS(_uM("height" to 32)), "h-80" to _pS(_uM("height" to 320)), "h-9" to _pS(_uM("height" to 36)), "h-auto" to _pS(_uM("height" to "auto")), "h-full" to _pS(_uM("height" to "100%")), "h-px" to _pS(_uM("height" to 1)), "min-h-0" to _pS(_uM("minHeight" to 0)), "min-h-12" to _pS(_uM("minHeight" to 48)), "min-h-40" to _pS(_uM("minHeight" to 160)), "w-0" to _pS(_uM("width" to 0)), "w-1" to _pS(_uM("width" to 4)), "w-10" to _pS(_uM("width" to 40)), "w-11" to _pS(_uM("width" to 44)), "w-12" to _pS(_uM("width" to 48)), "w-14" to _pS(_uM("width" to 56)), "w-16" to _pS(_uM("width" to 64)), "w-2" to _pS(_uM("width" to 8)), "w-20" to _pS(_uM("width" to 80)), "w-24" to _pS(_uM("width" to 96)), "w-28" to _pS(_uM("width" to 112)), "w-3" to _pS(_uM("width" to 12)), "w-30" to _pS(_uM("width" to 120)), "w-32" to _pS(_uM("width" to 128)), "w-4" to _pS(_uM("width" to 16)), "w-40" to _pS(_uM("width" to 160)), "w-5" to _pS(_uM("width" to 20)), "w-6" to _pS(_uM("width" to 24)), "w-60" to _pS(_uM("width" to 240)), "w-7" to _pS(_uM("width" to 28)), "w-72" to _pS(_uM("width" to 288)), "w-8" to _pS(_uM("width" to 32)), "w-80" to _pS(_uM("width" to 320)), "w-9" to _pS(_uM("width" to 36)), "w-full" to _pS(_uM("width" to "100%")), "w-px" to _pS(_uM("width" to 1)), "min-w-0" to _pS(_uM("minWidth" to 0)), "min-w-10" to _pS(_uM("minWidth" to 40)), "min-w-12" to _pS(_uM("minWidth" to 48)), "min-w-2" to _pS(_uM("minWidth" to 8)), "min-w-24" to _pS(_uM("minWidth" to 96)), "min-w-4" to _pS(_uM("minWidth" to 16)), "min-w-80" to _pS(_uM("minWidth" to 320)), "max-w-24" to _pS(_uM("maxWidth" to 96)), "max-w-48" to _pS(_uM("maxWidth" to 192)), "flex-1" to _pS(_uM("flexGrow" to 1, "flexShrink" to 1, "flexBasis" to "0%")), "flex-shrink" to _pS(_uM("flexShrink" to 1)), "flex-shrink-0" to _pS(_uM("flexShrink" to 0)), "shrink-0" to _pS(_uM("flexShrink" to 0)), "flex-grow" to _pS(_uM("flexGrow" to 1)), "grow-0" to _pS(_uM("flexGrow" to 0)), "basis-auto" to _pS(_uM("flexBasis" to "auto")), "origin-center" to _pS(_uM("transformOrigin" to "center")), "-rotate-90" to _pS(_uM("--tw-rotate" to "-90deg", "transform" to "translate(var(--tw-translate-x), var(--tw-translate-y))\n    rotate(var(--tw-rotate)) skewX(var(--tw-skew-x)) skewY(var(--tw-skew-y))\n    scaleX(var(--tw-scale-x)) scaleY(var(--tw-scale-y))")), "rotate-180" to _pS(_uM("--tw-rotate" to "180deg", "transform" to "translate(var(--tw-translate-x), var(--tw-translate-y))\n    rotate(var(--tw-rotate)) skewX(var(--tw-skew-x)) skewY(var(--tw-skew-y))\n    scaleX(var(--tw-scale-x)) scaleY(var(--tw-scale-y))")), "rotate-90" to _pS(_uM("--tw-rotate" to "90deg", "transform" to "translate(var(--tw-translate-x), var(--tw-translate-y))\n    rotate(var(--tw-rotate)) skewX(var(--tw-skew-x)) skewY(var(--tw-skew-y))\n    scaleX(var(--tw-scale-x)) scaleY(var(--tw-scale-y))")), "scale-105" to _pS(_uM("--tw-scale-x" to "1.05", "--tw-scale-y" to "1.05", "transform" to "translate(var(--tw-translate-x), var(--tw-translate-y))\n    rotate(var(--tw-rotate)) skewX(var(--tw-skew-x)) skewY(var(--tw-skew-y))\n    scaleX(var(--tw-scale-x)) scaleY(var(--tw-scale-y))")), "transform" to _pS(_uM("transform" to "translate(var(--tw-translate-x), var(--tw-translate-y))\n    rotate(var(--tw-rotate)) skewX(var(--tw-skew-x)) skewY(var(--tw-skew-y))\n    scaleX(var(--tw-scale-x)) scaleY(var(--tw-scale-y))")), "animate-spin" to _pS(_uM("animation" to "spin 1s linear infinite")), "cursor-default" to _pS(_uM("cursor" to "default")), "cursor-not-allowed" to _pS(_uM("cursor" to "not-allowed")), "cursor-pointer" to _pS(_uM("cursor" to "pointer")), "touch-none" to _pS(_uM("touchAction" to "none")), "select-none" to _pS(_uM("WebkitUserSelect" to "none", "MozUserSelect" to "none", "userSelect" to "none")), "select-text" to _pS(_uM("WebkitUserSelect" to "text", "MozUserSelect" to "text", "userSelect" to "text")), "resize-none" to _pS(_uM("resize" to "none")), "appearance-none" to _pS(_uM("WebkitAppearance" to "none", "MozAppearance" to "none", "appearance" to "none")), "grid-cols-2" to _pS(_uM("gridTemplateColumns" to "repeat(2, minmax(0, 1fr))")), "grid-cols-3" to _pS(_uM("gridTemplateColumns" to "repeat(3, minmax(0, 1fr))")), "flex-row" to _pS(_uM("flexDirection" to "row")), "flex-col" to _pS(_uM("flexDirection" to "column")), "flex-wrap" to _pS(_uM("flexWrap" to "wrap")), "items-start" to _pS(_uM("alignItems" to "flex-start")), "items-end" to _pS(_uM("alignItems" to "flex-end")), "items-center" to _pS(_uM("alignItems" to "center")), "justify-start" to _pS(_uM("justifyContent" to "flex-start")), "justify-end" to _pS(_uM("justifyContent" to "flex-end")), "justify-center" to _pS(_uM("justifyContent" to "center")), "justify-between" to _pS(_uM("justifyContent" to "space-between")), "justify-around" to _pS(_uM("justifyContent" to "space-around")), "gap-1" to _pS(_uM("gap" to "4px")), "gap-10" to _pS(_uM("gap" to "40px")), "gap-12" to _pS(_uM("gap" to "48px")), "gap-16" to _pS(_uM("gap" to "64px")), "gap-2" to _pS(_uM("gap" to "8px")), "gap-3" to _pS(_uM("gap" to "12px")), "gap-4" to _pS(_uM("gap" to "16px")), "gap-6" to _pS(_uM("gap" to "24px")), "gap-7" to _pS(_uM("gap" to "28px")), "gap-8" to _pS(_uM("gap" to "32px")), "gap-x-1" to _pS(_uM("MozColumnGap" to "4px", "columnGap" to "4px")), "gap-x-10" to _pS(_uM("MozColumnGap" to "40px", "columnGap" to "40px")))
            }
        val styles3: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("gap-y-10" to _pS(_uM("rowGap" to "40px")), "gap-y-4" to _pS(_uM("rowGap" to "16px")), "overflow-hidden" to _pS(_uM("overflow" to "hidden")), "overflow-visible" to _pS(_uM("overflow" to "visible")), "overflow-x-auto" to _pS(_uM("overflowX" to "auto")), "overflow-y-auto" to _pS(_uM("overflowY" to "auto")), "overflow-y-hidden" to _pS(_uM("overflowY" to "hidden")), "truncate" to _pS(_uM("overflow" to "hidden", "textOverflow" to "ellipsis", "whiteSpace" to "nowrap")), "text-ellipsis" to _pS(_uM("textOverflow" to "ellipsis")), "whitespace-normal" to _pS(_uM("whiteSpace" to "normal")), "whitespace-nowrap" to _pS(_uM("whiteSpace" to "nowrap")), "break-words" to _pS(_uM("overflowWrap" to "break-word")), "break-all" to _pS(_uM("wordBreak" to "break-all")), "rounded" to _pS(_uM("borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4)), "rounded-2xl" to _pS(_uM("borderTopLeftRadius" to 16, "borderTopRightRadius" to 16, "borderBottomRightRadius" to 16, "borderBottomLeftRadius" to 16)), "rounded-3xl" to _pS(_uM("borderTopLeftRadius" to 24, "borderTopRightRadius" to 24, "borderBottomRightRadius" to 24, "borderBottomLeftRadius" to 24)), "rounded-full" to _pS(_uM("borderTopLeftRadius" to 9999, "borderTopRightRadius" to 9999, "borderBottomRightRadius" to 9999, "borderBottomLeftRadius" to 9999)), "rounded-lg" to _pS(_uM("borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8)), "rounded-md" to _pS(_uM("borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)), "rounded-none" to _pS(_uM("borderTopLeftRadius" to 0, "borderTopRightRadius" to 0, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0)), "rounded-sm" to _pS(_uM("borderTopLeftRadius" to 2, "borderTopRightRadius" to 2, "borderBottomRightRadius" to 2, "borderBottomLeftRadius" to 2)), "rounded-xl" to _pS(_uM("borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12)), "rounded-b-none" to _pS(_uM("borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0)), "rounded-r" to _pS(_uM("borderTopRightRadius" to 4, "borderBottomRightRadius" to 4)), "rounded-t-2xl" to _pS(_uM("borderTopLeftRadius" to 16, "borderTopRightRadius" to 16)), "rounded-t-3xl" to _pS(_uM("borderTopLeftRadius" to 24, "borderTopRightRadius" to 24)), "rounded-t-xl" to _pS(_uM("borderTopLeftRadius" to 12, "borderTopRightRadius" to 12)), "border" to _pS(_uM("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1)), "border-0" to _pS(_uM("borderTopWidth" to 0, "borderRightWidth" to 0, "borderBottomWidth" to 0, "borderLeftWidth" to 0)), "border-2" to _pS(_uM("borderTopWidth" to 2, "borderRightWidth" to 2, "borderBottomWidth" to 2, "borderLeftWidth" to 2)), "border-y" to _pS(_uM("borderTopWidth" to 1, "borderBottomWidth" to 1)), "border-b" to _pS(_uM("borderBottomWidth" to 1)), "border-b-0" to _pS(_uM("borderBottomWidth" to 0)), "border-l" to _pS(_uM("borderLeftWidth" to 1)), "border-t" to _pS(_uM("borderTopWidth" to 1)), "border-t-8" to _pS(_uM("borderTopWidth" to 8)), "border-solid" to _pS(_uM("borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid")), "border-dashed" to _pS(_uM("borderTopStyle" to "dashed", "borderRightStyle" to "dashed", "borderBottomStyle" to "dashed", "borderLeftStyle" to "dashed")), "border-none" to _pS(_uM("borderTopStyle" to "none", "borderRightStyle" to "none", "borderBottomStyle" to "none", "borderLeftStyle" to "none")), "border-blue-200" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(191 219 254 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(191 219 254 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(191 219 254 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(191 219 254 / var(--tw-border-opacity, 1))")), "border-blue-500" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(59 130 246 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(59 130 246 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(59 130 246 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(59 130 246 / var(--tw-border-opacity, 1))")), "border-border" to _pS(_uM("borderTopColor" to "var(--tui-border)", "borderRightColor" to "var(--tui-border)", "borderBottomColor" to "var(--tui-border)", "borderLeftColor" to "var(--tui-border)")), "border-border-soft" to _pS(_uM("borderTopColor" to "var(--tui-border-soft)", "borderRightColor" to "var(--tui-border-soft)", "borderBottomColor" to "var(--tui-border-soft)", "borderLeftColor" to "var(--tui-border-soft)")), "border-gray-100" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(243 244 246 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(243 244 246 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(243 244 246 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(243 244 246 / var(--tw-border-opacity, 1))")), "border-gray-200" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(229 231 235 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(229 231 235 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(229 231 235 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(229 231 235 / var(--tw-border-opacity, 1))")), "border-gray-300" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(209 213 219 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(209 213 219 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(209 213 219 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(209 213 219 / var(--tw-border-opacity, 1))")), "border-gray-50" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(249 250 251 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(249 250 251 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(249 250 251 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(249 250 251 / var(--tw-border-opacity, 1))")), "border-green-200" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(187 247 208 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(187 247 208 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(187 247 208 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(187 247 208 / var(--tw-border-opacity, 1))")), "border-green-500" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(34 197 94 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(34 197 94 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(34 197 94 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(34 197 94 / var(--tw-border-opacity, 1))")), "border-green-700" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(21 128 61 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(21 128 61 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(21 128 61 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(21 128 61 / var(--tw-border-opacity, 1))")), "border-orange-200" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(254 215 170 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(254 215 170 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(254 215 170 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(254 215 170 / var(--tw-border-opacity, 1))")), "border-orange-400" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(251 146 60 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(251 146 60 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(251 146 60 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(251 146 60 / var(--tw-border-opacity, 1))")), "border-orange-500" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(249 115 22 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(249 115 22 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(249 115 22 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(249 115 22 / var(--tw-border-opacity, 1))")), "border-red-200" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(254 202 202 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(254 202 202 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(254 202 202 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(254 202 202 / var(--tw-border-opacity, 1))")), "border-red-400" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(248 113 113 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(248 113 113 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(248 113 113 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(248 113 113 / var(--tw-border-opacity, 1))")), "border-slate-100" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(241 245 249 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(241 245 249 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(241 245 249 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(241 245 249 / var(--tw-border-opacity, 1))")), "border-teal-500" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(20 184 166 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(20 184 166 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(20 184 166 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(20 184 166 / var(--tw-border-opacity, 1))")), "border-teal-600" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(13 148 136 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(13 148 136 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(13 148 136 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(13 148 136 / var(--tw-border-opacity, 1))")), "border-teal-700" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(15 118 110 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(15 118 110 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(15 118 110 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(15 118 110 / var(--tw-border-opacity, 1))")), "border-transparent" to _pS(_uM("borderTopColor" to "rgba(0,0,0,0)", "borderRightColor" to "rgba(0,0,0,0)", "borderBottomColor" to "rgba(0,0,0,0)", "borderLeftColor" to "rgba(0,0,0,0)")), "border-white" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(255 255 255 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(255 255 255 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(255 255 255 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(255 255 255 / var(--tw-border-opacity, 1))")), "border-zinc-300" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(212 212 216 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(212 212 216 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(212 212 216 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(212 212 216 / var(--tw-border-opacity, 1))")), "border-zinc-400" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(161 161 170 / var(--tw-border-opacity, 1))", "borderRightColor" to "rgb(161 161 170 / var(--tw-border-opacity, 1))", "borderBottomColor" to "rgb(161 161 170 / var(--tw-border-opacity, 1))", "borderLeftColor" to "rgb(161 161 170 / var(--tw-border-opacity, 1))")), "border-t-blue-500" to _pS(_uM("--tw-border-opacity" to "1", "borderTopColor" to "rgb(59 130 246 / var(--tw-border-opacity, 1))")), "bg-black" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(0 0 0 / var(--tw-bg-opacity, 1))")), "bg-blue-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(219 234 254 / var(--tw-bg-opacity, 1))")), "bg-blue-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(239 246 255 / var(--tw-bg-opacity, 1))")), "bg-blue-500" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(59 130 246 / var(--tw-bg-opacity, 1))")), "bg-cyan-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(207 250 254 / var(--tw-bg-opacity, 1))")), "bg-gray-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(243 244 246 / var(--tw-bg-opacity, 1))")), "bg-gray-200" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(229 231 235 / var(--tw-bg-opacity, 1))")), "bg-gray-300" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(209 213 219 / var(--tw-bg-opacity, 1))")), "bg-gray-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(249 250 251 / var(--tw-bg-opacity, 1))")), "bg-green-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(220 252 231 / var(--tw-bg-opacity, 1))")), "bg-green-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(240 253 244 / var(--tw-bg-opacity, 1))")), "bg-green-500" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(34 197 94 / var(--tw-bg-opacity, 1))")), "bg-green-600" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(22 163 74 / var(--tw-bg-opacity, 1))")), "bg-green-700" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(21 128 61 / var(--tw-bg-opacity, 1))")), "bg-orange-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(255 237 213 / var(--tw-bg-opacity, 1))")), "bg-orange-200" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(254 215 170 / var(--tw-bg-opacity, 1))")), "bg-orange-300" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(253 186 116 / var(--tw-bg-opacity, 1))")), "bg-orange-400" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(251 146 60 / var(--tw-bg-opacity, 1))")), "bg-orange-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(255 247 237 / var(--tw-bg-opacity, 1))")), "bg-orange-500" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(249 115 22 / var(--tw-bg-opacity, 1))")), "bg-page" to _pS(_uM("backgroundColor" to "var(--tui-page-bg)")), "bg-pink-500" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(236 72 153 / var(--tw-bg-opacity, 1))")), "bg-purple-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(250 245 255 / var(--tw-bg-opacity, 1))")), "bg-red-400" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(248 113 113 / var(--tw-bg-opacity, 1))")), "bg-red-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(254 242 242 / var(--tw-bg-opacity, 1))")), "bg-red-500" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(239 68 68 / var(--tw-bg-opacity, 1))")), "bg-rose-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(255 228 230 / var(--tw-bg-opacity, 1))")), "bg-slate-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(248 250 252 / var(--tw-bg-opacity, 1))")), "bg-surface" to _pS(_uM("backgroundColor" to "var(--tui-surface)")), "bg-teal-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(204 251 241 / var(--tw-bg-opacity, 1))")), "bg-teal-50" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(240 253 250 / var(--tw-bg-opacity, 1))")), "bg-teal-500" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(20 184 166 / var(--tw-bg-opacity, 1))")), "bg-teal-600" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(13 148 136 / var(--tw-bg-opacity, 1))")), "bg-teal-700" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(15 118 110 / var(--tw-bg-opacity, 1))")), "bg-transparent" to _pS(_uM("backgroundColor" to "rgba(0,0,0,0)")), "bg-white" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(255 255 255 / var(--tw-bg-opacity, 1))")))
            }
        val styles4: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("bg-yellow-400" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(250 204 21 / var(--tw-bg-opacity, 1))")), "bg-zinc-100" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(244 244 245 / var(--tw-bg-opacity, 1))")), "bg-zinc-400" to _pS(_uM("--tw-bg-opacity" to "1", "backgroundColor" to "rgb(161 161 170 / var(--tw-bg-opacity, 1))")), "bg-opacity-50" to _pS(_uM("--tw-bg-opacity" to "0.5")), "bg-opacity-95" to _pS(_uM("--tw-bg-opacity" to "0.95")), "bg-gradient-to-b" to _pS(_uM("backgroundImage" to "linear-gradient(to bottom, var(--from-color, transparent), var(--to-color, transparent))")), "bg-gradient-to-r" to _pS(_uM("backgroundImage" to "linear-gradient(to right, var(--tw-gradient-stops))")), "bg-none" to _pS(_uM("backgroundImage" to "none")), "from-green-100" to _pS(_uM("--tw-gradient-from" to "#dcfce7 var(--tw-gradient-from-position)", "--tw-gradient-to" to "rgb(220 252 231 / 0) var(--tw-gradient-to-position)", "--tw-gradient-stops" to "var(--tw-gradient-from), var(--tw-gradient-to)")), "from-green-200" to _pS(_uM("--tw-gradient-from" to "#bbf7d0 var(--tw-gradient-from-position)", "--tw-gradient-to" to "rgb(187 247 208 / 0) var(--tw-gradient-to-position)", "--tw-gradient-stops" to "var(--tw-gradient-from), var(--tw-gradient-to)", "--from-color" to "#bbf7d0")), "from-green-50" to _pS(_uM("--tw-gradient-from" to "#f0fdf4 var(--tw-gradient-from-position)", "--tw-gradient-to" to "rgb(240 253 244 / 0) var(--tw-gradient-to-position)", "--tw-gradient-stops" to "var(--tw-gradient-from), var(--tw-gradient-to)")), "from-red-400" to _pS(_uM("--tw-gradient-from" to "#f87171 var(--tw-gradient-from-position)", "--tw-gradient-to" to "rgb(248 113 113 / 0) var(--tw-gradient-to-position)", "--tw-gradient-stops" to "var(--tw-gradient-from), var(--tw-gradient-to)")), "from-teal-500" to _pS(_uM("--tw-gradient-from" to "#14b8a6 var(--tw-gradient-from-position)", "--tw-gradient-to" to "rgb(20 184 166 / 0) var(--tw-gradient-to-position)", "--tw-gradient-stops" to "var(--tw-gradient-from), var(--tw-gradient-to)")), "to-teal-400" to _pS(_uM("--tw-gradient-to" to "#2dd4bf var(--tw-gradient-to-position)")), "to-white" to _pS(_uM("--tw-gradient-to" to "#fff var(--tw-gradient-to-position)")), "to-yellow-400" to _pS(_uM("--tw-gradient-to" to "#facc15 var(--tw-gradient-to-position)")), "p-0" to _pS(_uM("paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0)), "p-1" to _pS(_uM("paddingTop" to 4, "paddingRight" to 4, "paddingBottom" to 4, "paddingLeft" to 4)), "p-12" to _pS(_uM("paddingTop" to 48, "paddingRight" to 48, "paddingBottom" to 48, "paddingLeft" to 48)), "p-2" to _pS(_uM("paddingTop" to 8, "paddingRight" to 8, "paddingBottom" to 8, "paddingLeft" to 8)), "p-3" to _pS(_uM("paddingTop" to 12, "paddingRight" to 12, "paddingBottom" to 12, "paddingLeft" to 12)), "p-4" to _pS(_uM("paddingTop" to 16, "paddingRight" to 16, "paddingBottom" to 16, "paddingLeft" to 16)), "p-5" to _pS(_uM("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "p-6" to _pS(_uM("paddingTop" to 24, "paddingRight" to 24, "paddingBottom" to 24, "paddingLeft" to 24)), "p-7" to _pS(_uM("paddingTop" to 28, "paddingRight" to 28, "paddingBottom" to 28, "paddingLeft" to 28)), "p-8" to _pS(_uM("paddingTop" to 32, "paddingRight" to 32, "paddingBottom" to 32, "paddingLeft" to 32)), "px-1" to _pS(_uM("paddingLeft" to 4, "paddingRight" to 4)), "px-10" to _pS(_uM("paddingLeft" to 40, "paddingRight" to 40)), "px-12" to _pS(_uM("paddingLeft" to 48, "paddingRight" to 48)), "px-16" to _pS(_uM("paddingLeft" to 64, "paddingRight" to 64)), "px-2" to _pS(_uM("paddingLeft" to 8, "paddingRight" to 8)), "px-24" to _pS(_uM("paddingLeft" to 96, "paddingRight" to 96)), "px-3" to _pS(_uM("paddingLeft" to 12, "paddingRight" to 12)), "px-4" to _pS(_uM("paddingLeft" to 16, "paddingRight" to 16)), "px-5" to _pS(_uM("paddingLeft" to 20, "paddingRight" to 20)), "px-6" to _pS(_uM("paddingLeft" to 24, "paddingRight" to 24)), "px-8" to _pS(_uM("paddingLeft" to 32, "paddingRight" to 32)), "px-lg" to _pS(_uM("paddingLeft" to 16, "paddingRight" to 16)), "py-1" to _pS(_uM("paddingTop" to 4, "paddingBottom" to 4)), "py-10" to _pS(_uM("paddingTop" to 40, "paddingBottom" to 40)), "py-12" to _pS(_uM("paddingTop" to 48, "paddingBottom" to 48)), "py-16" to _pS(_uM("paddingTop" to 64, "paddingBottom" to 64)), "py-2" to _pS(_uM("paddingTop" to 8, "paddingBottom" to 8)), "py-20" to _pS(_uM("paddingTop" to 80, "paddingBottom" to 80)), "py-24" to _pS(_uM("paddingTop" to 96, "paddingBottom" to 96)), "py-3" to _pS(_uM("paddingTop" to 12, "paddingBottom" to 12)), "py-4" to _pS(_uM("paddingTop" to 16, "paddingBottom" to 16)), "py-5" to _pS(_uM("paddingTop" to 20, "paddingBottom" to 20)), "py-6" to _pS(_uM("paddingTop" to 24, "paddingBottom" to 24)), "py-8" to _pS(_uM("paddingTop" to 32, "paddingBottom" to 32)), "py-lg" to _pS(_uM("paddingTop" to 16, "paddingBottom" to 16)), "py-px" to _pS(_uM("paddingTop" to 1, "paddingBottom" to 1)), "pb-1" to _pS(_uM("paddingBottom" to 4)), "pb-10" to _pS(_uM("paddingBottom" to 40)), "pb-12" to _pS(_uM("paddingBottom" to 48)), "pb-16" to _pS(_uM("paddingBottom" to 64)), "pb-2" to _pS(_uM("paddingBottom" to 8)), "pb-20" to _pS(_uM("paddingBottom" to 80)), "pb-24" to _pS(_uM("paddingBottom" to 96)), "pb-32" to _pS(_uM("paddingBottom" to 128)), "pb-4" to _pS(_uM("paddingBottom" to 16)), "pb-5" to _pS(_uM("paddingBottom" to 20)), "pb-6" to _pS(_uM("paddingBottom" to 24)), "pb-8" to _pS(_uM("paddingBottom" to 32)), "pl-0" to _pS(_uM("paddingLeft" to 0)), "pl-1" to _pS(_uM("paddingLeft" to 4)), "pl-2" to _pS(_uM("paddingLeft" to 8)), "pl-4" to _pS(_uM("paddingLeft" to 16)), "pr-0" to _pS(_uM("paddingRight" to 0)), "pr-10" to _pS(_uM("paddingRight" to 40)), "pr-2" to _pS(_uM("paddingRight" to 8)), "pr-4" to _pS(_uM("paddingRight" to 16)), "pr-60" to _pS(_uM("paddingRight" to 240)), "pt-1" to _pS(_uM("paddingTop" to 4)), "pt-10" to _pS(_uM("paddingTop" to 40)), "pt-16" to _pS(_uM("paddingTop" to 64)), "pt-2" to _pS(_uM("paddingTop" to 8)), "pt-20" to _pS(_uM("paddingTop" to 80)), "pt-28" to _pS(_uM("paddingTop" to 112)), "pt-3" to _pS(_uM("paddingTop" to 12)), "pt-36" to _pS(_uM("paddingTop" to 144)), "pt-4" to _pS(_uM("paddingTop" to 16)), "pt-40" to _pS(_uM("paddingTop" to 160)), "pt-6" to _pS(_uM("paddingTop" to 24)), "pt-8" to _pS(_uM("paddingTop" to 32)), "text-left" to _pS(_uM("textAlign" to "left")), "text-center" to _pS(_uM("textAlign" to "center")), "text-right" to _pS(_uM("textAlign" to "right")), "font-mono" to _pS(_uM("fontFamily" to "ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, Liberation Mono,\n    Courier New, monospace")), "text-2xl" to _pS(_uM("fontSize" to 24, "lineHeight" to "32px")), "text-3xl" to _pS(_uM("fontSize" to 30, "lineHeight" to "36px")), "text-7xl" to _pS(_uM("fontSize" to 72, "lineHeight" to 1)), "text-base" to _pS(_uM("fontSize" to 16, "lineHeight" to "24px")), "text-lg" to _pS(_uM("fontSize" to 18, "lineHeight" to "28px")), "text-sm" to _pS(_uM("fontSize" to 14, "lineHeight" to "20px")), "text-xl" to _pS(_uM("fontSize" to 20, "lineHeight" to "28px")), "text-xs" to _pS(_uM("fontSize" to 12, "lineHeight" to "16px")), "font-bold" to _pS(_uM("fontWeight" to "700")), "font-medium" to _pS(_uM("fontWeight" to "500")), "font-normal" to _pS(_uM("fontWeight" to "400")))
            }
        val styles5: Map<String, Map<String, Map<String, Any>>>
            get() {
                return _uM("font-semibold" to _pS(_uM("fontWeight" to "600")), "italic" to _pS(_uM("fontStyle" to "italic")), "leading-5" to _pS(_uM("lineHeight" to "20px")), "leading-6" to _pS(_uM("lineHeight" to "24px")), "leading-none" to _pS(_uM("lineHeight" to 1)), "leading-normal" to _pS(_uM("lineHeight" to 1.5)), "leading-relaxed" to _pS(_uM("lineHeight" to 1.625)), "leading-snug" to _pS(_uM("lineHeight" to 1.375)), "text-blue-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(59 130 246 / var(--tw-text-opacity, 1))")), "text-cyan-600" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(8 145 178 / var(--tw-text-opacity, 1))")), "text-disabled" to _pS(_uM("color" to "var(--tui-text-disabled)")), "text-gray-300" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(209 213 219 / var(--tw-text-opacity, 1))")), "text-gray-400" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(156 163 175 / var(--tw-text-opacity, 1))")), "text-gray-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(107 114 128 / var(--tw-text-opacity, 1))")), "text-gray-600" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(75 85 99 / var(--tw-text-opacity, 1))")), "text-gray-700" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(55 65 81 / var(--tw-text-opacity, 1))")), "text-gray-800" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(31 41 55 / var(--tw-text-opacity, 1))")), "text-gray-900" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(17 24 39 / var(--tw-text-opacity, 1))")), "text-green-200" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(187 247 208 / var(--tw-text-opacity, 1))")), "text-green-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(34 197 94 / var(--tw-text-opacity, 1))")), "text-green-600" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(22 163 74 / var(--tw-text-opacity, 1))")), "text-green-700" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(21 128 61 / var(--tw-text-opacity, 1))")), "text-green-900" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(20 83 45 / var(--tw-text-opacity, 1))")), "text-orange-400" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(251 146 60 / var(--tw-text-opacity, 1))")), "text-orange-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(249 115 22 / var(--tw-text-opacity, 1))")), "text-orange-600" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(234 88 12 / var(--tw-text-opacity, 1))")), "text-primary" to _pS(_uM("color" to "var(--tui-text-primary)")), "text-red-400" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(248 113 113 / var(--tw-text-opacity, 1))")), "text-red-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(239 68 68 / var(--tw-text-opacity, 1))")), "text-rose-400" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(251 113 133 / var(--tw-text-opacity, 1))")), "text-rose-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(244 63 94 / var(--tw-text-opacity, 1))")), "text-secondary" to _pS(_uM("color" to "var(--tui-text-secondary)")), "text-slate-300" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(203 213 225 / var(--tw-text-opacity, 1))")), "text-slate-400" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(148 163 184 / var(--tw-text-opacity, 1))")), "text-teal-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(20 184 166 / var(--tw-text-opacity, 1))")), "text-teal-600" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(13 148 136 / var(--tw-text-opacity, 1))")), "text-teal-700" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(15 118 110 / var(--tw-text-opacity, 1))")), "text-teal-800" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(17 94 89 / var(--tw-text-opacity, 1))")), "text-tertiary" to _pS(_uM("color" to "var(--tui-text-tertiary)")), "text-white" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(255 255 255 / var(--tw-text-opacity, 1))")), "text-yellow-300" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(253 224 71 / var(--tw-text-opacity, 1))")), "text-yellow-500" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(234 179 8 / var(--tw-text-opacity, 1))")), "text-zinc-400" to _pS(_uM("--tw-text-opacity" to "1", "color" to "rgb(161 161 170 / var(--tw-text-opacity, 1))")), "text-opacity-70" to _pS(_uM("--tw-text-opacity" to "0.7")), "underline" to _pS(_uM("textDecorationLine" to "underline")), "line-through" to _pS(_uM("textDecorationLine" to "line-through")), "opacity-0" to _pS(_uM("opacity" to 0)), "opacity-100" to _pS(_uM("opacity" to 1)), "opacity-40" to _pS(_uM("opacity" to 0.4)), "opacity-50" to _pS(_uM("opacity" to 0.5)), "opacity-60" to _pS(_uM("opacity" to 0.6)), "opacity-65" to _pS(_uM("opacity" to 0.65)), "opacity-80" to _pS(_uM("opacity" to 0.8)), "opacity-90" to _pS(_uM("opacity" to 0.9)), "shadow" to _pS(_uM("--tw-shadow" to "0 1px 3px 0 rgb(0 0 0 / 0.1), 0 1px 2px -1px rgb(0 0 0 / 0.1)", "--tw-shadow-colored" to "0 1px 3px 0 var(--tw-shadow-color), 0 1px 2px -1px var(--tw-shadow-color)", "boxShadow" to "var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000),\n    var(--tw-shadow)")), "shadow-inner" to _pS(_uM("--tw-shadow" to "inset 0 2px 4px 0 rgb(0 0 0 / 0.05)", "--tw-shadow-colored" to "inset 0 2px 4px 0 var(--tw-shadow-color)", "boxShadow" to "var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000),\n    var(--tw-shadow)")), "shadow-lg" to _pS(_uM("--tw-shadow" to "0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1)", "--tw-shadow-colored" to "0 10px 15px -3px var(--tw-shadow-color),\n    0 4px 6px -4px var(--tw-shadow-color)", "boxShadow" to "var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000),\n    var(--tw-shadow)")), "shadow-sm" to _pS(_uM("--tw-shadow" to "0 1px 2px 0 rgb(0 0 0 / 0.05)", "--tw-shadow-colored" to "0 1px 2px 0 var(--tw-shadow-color)", "boxShadow" to "var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000),\n    var(--tw-shadow)")), "outline-none" to _pS(_uM("outline" to "2px solid transparent", "outlineOffset" to "2px")), "blur" to _pS(_uM("--tw-blur" to "blur(8px)", "filter" to "var(--tw-blur) var(--tw-brightness) var(--tw-contrast)\n    var(--tw-grayscale) var(--tw-hue-rotate) var(--tw-invert) var(--tw-saturate)\n    var(--tw-sepia) var(--tw-drop-shadow)")), "drop-shadow" to _pS(_uM("--tw-drop-shadow" to "drop-shadow(0 1px 2px rgb(0 0 0 / 0.1))\n    drop-shadow(0 1px 1px rgb(0 0 0 / 0.06))", "filter" to "var(--tw-blur) var(--tw-brightness) var(--tw-contrast)\n    var(--tw-grayscale) var(--tw-hue-rotate) var(--tw-invert) var(--tw-saturate)\n    var(--tw-sepia) var(--tw-drop-shadow)")), "filter" to _pS(_uM("filter" to "var(--tw-blur) var(--tw-brightness) var(--tw-contrast)\n    var(--tw-grayscale) var(--tw-hue-rotate) var(--tw-invert) var(--tw-saturate)\n    var(--tw-sepia) var(--tw-drop-shadow)")), "backdrop-blur-sm" to _pS(_uM("--tw-backdrop-blur" to "blur(4px)", "backdropFilter" to "var(--tw-backdrop-blur) var(--tw-backdrop-brightness)\n    var(--tw-backdrop-contrast) var(--tw-backdrop-grayscale)\n    var(--tw-backdrop-hue-rotate) var(--tw-backdrop-invert)\n    var(--tw-backdrop-opacity) var(--tw-backdrop-saturate)\n    var(--tw-backdrop-sepia)")), "transition" to _pS(_uM("transitionTimingFunction" to "cubic-bezier(0.4,0,0.2,1)", "transitionDuration" to "150ms")), "transition-all" to _pS(_uM("transitionProperty" to "all", "transitionTimingFunction" to "cubic-bezier(0.4,0,0.2,1)", "transitionDuration" to "150ms")), "transition-colors" to _pS(_uM("transitionTimingFunction" to "cubic-bezier(0.4,0,0.2,1)", "transitionDuration" to "150ms")), "transition-opacity" to _pS(_uM("transitionProperty" to "opacity", "transitionTimingFunction" to "cubic-bezier(0.4,0,0.2,1)", "transitionDuration" to "150ms")), "transition-transform" to _pS(_uM("transitionProperty" to "transform", "transitionTimingFunction" to "cubic-bezier(0.4,0,0.2,1)", "transitionDuration" to "150ms")), "duration-100" to _pS(_uM("transitionDuration" to "100ms")), "duration-150" to _pS(_uM("transitionDuration" to "150ms")), "duration-200" to _pS(_uM("transitionDuration" to "200ms")), "duration-300" to _pS(_uM("transitionDuration" to "300ms")), "duration-500" to _pS(_uM("transitionDuration" to "500ms")), "ease-in" to _pS(_uM("transitionTimingFunction" to "cubic-bezier(0.4,0,1,1)")), "ease-out" to _pS(_uM("transitionTimingFunction" to "cubic-bezier(0,0,0.2,1)")), "auth-container" to _pS(_uM("backgroundImage" to "linear-gradient(135deg, #14b8a6 0%, #2dd4bf 50%, #5eead4 100%)", "backgroundColor" to "rgba(0,0,0,0)")), "logo-bg" to _pS(_uM("backgroundImage" to "linear-gradient(45deg, #14b8a6, #2dd4bf)", "backgroundColor" to "rgba(0,0,0,0)", "position" to "relative", "content::before" to "\"\"", "position::before" to "absolute", "inset::before" to -50, "width::before" to "200%", "height::before" to "200%", "backgroundImage::before" to "linear-gradient(45deg, transparent, rgba(255, 255, 255, 0.3), transparent)", "backgroundColor::before" to "rgba(0,0,0,0)", "animation::before" to "shine 3s ease-in-out infinite")), "logo-shadow" to _pS(_uM("bottom" to "-10rpx", "left" to "50%", "transform" to "translateX(-50%)", "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.2)", "filter" to "blur(8rpx)")), "form-card" to _uM(".loading" to _uM("opacity" to 0.8, "transform" to "scale(0.98)")), "tab-item" to _pS(_uM("transform:active" to "scale(0.95)")), "tab-text" to _uM(".tab-item.active " to _uM("color" to "#14b8a6", "fontWeight" to "600")), "tab-indicator" to _uM("" to _uM("width" to "50%", "backgroundImage" to "linear-gradient(90deg, #14b8a6, #2dd4bf)", "backgroundColor" to "rgba(0,0,0,0)", "transitionProperty" to "all", "transitionDuration" to "0.3s", "transitionTimingFunction" to "cubic-bezier(0.4,0,0.2,1)"), ".sms" to _uM("transform" to "translateX(100%)")), "code-input" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "rgba(255,255,255,0.8)", "borderTopWidth" to "1rpx", "borderRightWidth" to "1rpx", "borderBottomWidth" to "1rpx", "borderLeftWidth" to "1rpx", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(0,0,0,0.05)", "borderRightColor" to "rgba(0,0,0,0.05)", "borderBottomColor" to "rgba(0,0,0,0.05)", "borderLeftColor" to "rgba(0,0,0,0.05)", "color" to "#333333", "transitionProperty" to "all", "transitionDuration" to "0.3s", "transitionTimingFunction" to "ease", "backgroundImage:focus" to "none", "backgroundColor:focus" to "#ffffff", "borderTopColor:focus" to "#14b8a6", "borderRightColor:focus" to "#14b8a6", "borderBottomColor:focus" to "#14b8a6", "borderLeftColor:focus" to "#14b8a6", "boxShadow:focus" to "0 0 0 4rpx rgba(20, 184, 166, 0.1)")), "code-btn" to _uM("" to _uM("backgroundImage" to "linear-gradient(45deg, #14b8a6, #2dd4bf)", "backgroundColor" to "rgba(0,0,0,0)", "transitionProperty" to "transform", "transitionDuration" to "0.2s"), ".disabled" to _uM("backgroundImage" to "none", "backgroundColor" to "#e0e0e0", "color" to "#999999", "cursor" to "not-allowed"), ".countdown" to _uM("backgroundImage" to "linear-gradient(45deg, #ff6b6b, #feca57)", "backgroundColor" to "rgba(0,0,0,0)")), "code-btn-disabled" to _pS(_uM("backgroundImage" to "linear-gradient(45deg, #cfd8dc, #b0bec5)", "backgroundColor" to "rgba(0,0,0,0)", "color" to "#999999", "cursor" to "not-allowed", "opacity" to 0.7)), "submit-btn" to _uM("" to _uM("backgroundImage" to "linear-gradient(45deg, #14b8a6, #2dd4bf)", "backgroundColor" to "rgba(0,0,0,0)"), ".loading" to _uM("opacity" to 0.8, "cursor" to "not-allowed")), "secondary-btn" to _pS(_uM("color" to "#333333", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#e0e0e0", "borderRightColor" to "#e0e0e0", "borderBottomColor" to "#e0e0e0", "borderLeftColor" to "#e0e0e0")), "deleted-btn" to _uM("" to _uM("backgroundImage" to "linear-gradient(45deg, #ff6b6b, #ef6868)", "backgroundColor" to "rgba(0,0,0,0)"), ".loading" to _uM("opacity" to 0.8, "cursor" to "not-allowed")), "loading-icon" to _pS(_uM("animation" to "spin 1s linear infinite")), "social-btn" to _pS(_uM("transform:active" to "scale(0.95)", "backgroundImage:active" to "none", "backgroundColor:active" to "rgba(255,255,255,0.3)")), "wechat-btn" to _pS(_uM("backgroundImage" to "none", "backgroundColor" to "rgba(7,193,96,0.9)")), "t-default" to _pS(_uM("!backgroundColor" to "#0f766e", "!borderTopColor" to "#0f766e", "!borderRightColor" to "#0f766e", "!borderBottomColor" to "#0f766e", "!borderLeftColor" to "#0f766e")), "t-light" to _pS(_uM("--primary-color" to "#007aff", "--success-color" to "#4cd964", "--warning-color" to "#f0ad4e", "--danger-color" to "#dd524d", "--info-color" to "#909399", "--text-color" to "#303133", "--border-color" to "#dcdfe6", "--bg-color" to "#f5f7fa", "--border-radius" to "8rpx", "--font-size" to "28rpx", "--tui-page-bg" to "#f5f7fa", "--tui-page-bg-elevated" to "#ffffff", "--tui-surface" to "#ffffff", "--tui-surface-muted" to "#f5f7fa", "--tui-surface-soft" to "#fafafa", "--tui-surface-overlay" to "rgba(255, 255, 255, 0.92)", "--tui-surface-primary-soft" to "#ecf5ff", "--tui-surface-success-soft" to "#f0f9ff", "--tui-surface-warning-soft" to "#fdf6ec", "--tui-surface-danger-soft" to "#fef0f0", "--tui-text-primary" to "#303133", "--tui-text-secondary" to "#606266", "--tui-text-tertiary" to "#909399", "--tui-text-disabled" to "#c0c4cc", "--tui-text-inverse" to "#ffffff", "--tui-border" to "#dcdfe6", "--tui-border-soft" to "#ebeef5", "--tui-border-strong" to "#c8c9cc", "--tui-shadow-color" to "rgba(0, 0, 0, 0.12)", "--tui-mask" to "rgba(0, 0, 0, 0.5)", "--tui-nav-bg" to "#667eea", "--tui-nav-text" to "#ffffff", "--tui-page-gradient-start" to "#667eea", "--tui-page-gradient-end" to "#764ba2")), "t-dark" to _pS(_uM("--primary-color" to "#409eff", "--success-color" to "#67c23a", "--warning-color" to "#e6a23c", "--danger-color" to "#f56c6c", "--info-color" to "#909399", "--text-color" to "#f0f0f0", "--border-color" to "#4c4c4c", "--bg-color" to "#1a1a1a", "--border-radius" to "8rpx", "--font-size" to "28rpx", "--tui-page-bg" to "#111827", "--tui-page-bg-elevated" to "#1f2937", "--tui-surface" to "#1f2937", "--tui-surface-muted" to "#243041", "--tui-surface-soft" to "#374151", "--tui-surface-overlay" to "rgba(17, 24, 39, 0.82)", "--tui-surface-primary-soft" to "#1e3a5f", "--tui-surface-success-soft" to "#123524", "--tui-surface-warning-soft" to "#3f2e12", "--tui-surface-danger-soft" to "#3f1d1d", "--tui-text-primary" to "#f3f4f6", "--tui-text-secondary" to "#d1d5db", "--tui-text-tertiary" to "#9ca3af", "--tui-text-disabled" to "#6b7280", "--tui-text-inverse" to "#111827", "--tui-border" to "#374151", "--tui-border-soft" to "#4b5563", "--tui-border-strong" to "#6b7280", "--tui-shadow-color" to "rgba(0, 0, 0, 0.45)", "--tui-mask" to "rgba(0, 0, 0, 0.65)", "--tui-nav-bg" to "#111827", "--tui-nav-text" to "#f9fafb", "--tui-page-gradient-start" to "#1f2937", "--tui-page-gradient-end" to "#111827")), "@FONT-FACE" to _uM("0" to _uM("fontFamily" to "iconfont", "src" to "url('/static/font/iconfont.woff2?t=1778047602466') format('woff2'),\n    url('/static/font/iconfont.woff?t=1778047602466') format('woff'),\n    url('/static/font/iconfont.ttf?t=1778047602466') format('truetype')")), "@TRANSITION" to _uM("transition" to _uM("timingFunction" to "cubic-bezier(0.4,0,0.2,1)", "duration" to "150ms"), "transition-all" to _uM("property" to "all", "timingFunction" to "cubic-bezier(0.4,0,0.2,1)", "duration" to "150ms"), "transition-colors" to _uM("timingFunction" to "cubic-bezier(0.4,0,0.2,1)", "duration" to "150ms"), "transition-opacity" to _uM("property" to "opacity", "timingFunction" to "cubic-bezier(0.4,0,0.2,1)", "duration" to "150ms"), "transition-transform" to _uM("property" to "transform", "timingFunction" to "cubic-bezier(0.4,0,0.2,1)", "duration" to "150ms"), "duration-100" to _uM("duration" to "100ms"), "duration-150" to _uM("duration" to "150ms"), "duration-200" to _uM("duration" to "200ms"), "duration-300" to _uM("duration" to "300ms"), "duration-500" to _uM("duration" to "500ms"), "ease-in" to _uM("timingFunction" to "cubic-bezier(0.4,0,1,1)"), "ease-out" to _uM("timingFunction" to "cubic-bezier(0,0,0.2,1)"), "tab-indicator" to _uM("property" to "all", "duration" to "0.3s", "timingFunction" to "cubic-bezier(0.4,0,0.2,1)"), "code-input" to _uM("property" to "all", "duration" to "0.3s", "timingFunction" to "ease"), "code-btn" to _uM("property" to "transform", "duration" to "0.2s")))
            }
    }
}
val GenAppClass = CreateVueAppComponent(GenApp::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "app", name = "", inheritAttrs = true, inject = Map(), props = Map(), propsNeedCastKeys = _uA(), emits = Map(), components = Map(), styles = GenApp.styles, setup = fun(props: ComponentPublicInstance, ctx: SetupContext): Any? {
        return GenApp.setup(props as GenApp, ctx)
    }
    )
}
, fun(instance): GenApp {
    return GenApp(instance)
}
)
typealias ModularLocaleMessages = UTSJSONObject
typealias TranslateParams = UTSJSONObject
val cancelText = "取消"
val `default`: UTSJSONObject = _uO("cancelText" to cancelText)
val confirm = "确定"
val cancel = "取消"
val ok = "好的"
val close = "关闭"
val save = "保存"
val edit = "编辑"
val submit = "提交"
val reset = "重置"
val search = "搜索"
val loading = "加载中..."
val noData = "暂无数据"
val error = "出错了"
val language = "语言"
val languageSwitch = "切换语言"
val default__1: UTSJSONObject = _uO("confirm" to confirm, "cancel" to cancel, "ok" to ok, "close" to close, "save" to save, "delete" to "删除", "edit" to edit, "submit" to submit, "reset" to reset, "search" to search, "loading" to loading, "noData" to noData, "error" to error, "language" to language, "languageSwitch" to languageSwitch)
val title = "提示"
val confirmText = "确定"
val cancelText__1 = "取消"
val default__2: UTSJSONObject = _uO("title" to title, "confirmText" to confirmText, "cancelText" to cancelText__1)
val title__1 = "暂无数据"
val noData__1 = "暂无数据"
val description = "当前没有相关内容"
val actionText = "刷新试试"
val default__3: UTSJSONObject = _uO("title" to title__1, "noData" to noData__1, "description" to description, "actionText" to actionText)
val title__2 = "出错了"
val description__1 = "页面加载失败，请稍后重试"
val retryText = "重新加载"
val default__4: UTSJSONObject = _uO("title" to title__2, "description" to description__1, "retryText" to retryText)
val groups: UTSJSONObject = _uO("basic" to "基础组件", "layout" to "布局组件", "form" to "表单组件", "display" to "数据展示", "feedback" to "反馈组件", "navigation" to "导航组件", "tools" to "测试工具")
val components: UTSJSONObject = _uO("TButton" to _uO("name" to "按钮", "enName" to "Button", "description" to "按钮用于触发操作"), "TIcon" to _uO("name" to "图标", "enName" to "Icon", "description" to "语义化图标"), "TText" to _uO("name" to "文本", "enName" to "Text", "description" to "文本展示组件"), "TImage" to _uO("name" to "图片", "enName" to "Image", "description" to "增强的图片组件"), "TDivider" to _uO("name" to "分割线", "enName" to "Divider", "description" to "内容分割线"), "TCard" to _uO("name" to "卡片", "enName" to "Card", "description" to "卡片容器"), "TList" to _uO("name" to "列表", "enName" to "List", "description" to "列表展示"), "TCell" to _uO("name" to "单元格", "enName" to "Cell", "description" to "单元格组件"), "TGrid" to _uO("name" to "宫格", "enName" to "Grid", "description" to "宫格布局"), "TRow/TCol" to _uO("name" to "栅格", "enName" to "Layout", "description" to "24栅格布局"), "TInput" to _uO("name" to "输入框", "enName" to "Input", "description" to "文本输入"), "TTextarea" to _uO("name" to "多行输入", "enName" to "Textarea", "description" to "多行文本输入"), "TSearchBar" to _uO("name" to "搜索框", "enName" to "SearchBar", "description" to "搜索输入框"), "TNumberInput" to _uO("name" to "数字输入", "enName" to "NumberInput", "description" to "数字输入框"), "TSwitch" to _uO("name" to "开关", "enName" to "Switch", "description" to "开关选择器"), "TCheckbox" to _uO("name" to "复选框", "enName" to "Checkbox", "description" to "多选框"), "TCheckboxGroup" to _uO("name" to "复选组", "enName" to "CheckboxGroup", "description" to "复选框组"), "TRadioButton" to _uO("name" to "单选", "enName" to "Radio", "description" to "单选按钮"), "TRadioGroup" to _uO("name" to "单选组", "enName" to "RadioGroup", "description" to "单选框组"), "TSelect" to _uO("name" to "选择器", "enName" to "Select", "description" to "下拉选择"), "TPicker" to _uO("name" to "通用选择器", "enName" to "Picker", "description" to "多列数据选择"), "TDateTimePicker" to _uO("name" to "时间选择器", "enName" to "DateTimePicker", "description" to "日期时间选择"), "TSlider" to _uO("name" to "滑块", "enName" to "Slider", "description" to "滑动选择器"), "TRate" to _uO("name" to "评分", "enName" to "Rate", "description" to "评分组件"), "TForm" to _uO("name" to "表单", "enName" to "Form", "description" to "表单组件"), "Tags" to _uO("name" to "标签", "enName" to "Tags", "description" to "标记分类"), "TBadge" to _uO("name" to "徽标", "enName" to "Badge", "description" to "数字角标"), "TAvatar" to _uO("name" to "头像", "enName" to "Avatar", "description" to "用户头像"), "TProgress" to _uO("name" to "进度条", "enName" to "Progress", "description" to "进度指示器"), "TNoticeBar" to _uO("name" to "通知栏", "enName" to "NoticeBar", "description" to "滚动通知"), "TCollapse" to _uO("name" to "折叠面板", "enName" to "Collapse", "description" to "可折叠内容区域"), "TSwiper" to _uO("name" to "轮播图", "enName" to "Swiper", "description" to "图片/内容轮播"), "TEmpty" to _uO("name" to "空状态", "enName" to "Empty", "description" to "空数据展示"), "TErrorState" to _uO("name" to "错误状态", "enName" to "Error", "description" to "错误提示"), "TLoading" to _uO("name" to "加载", "enName" to "Loading", "description" to "加载状态"), "TToast" to _uO("name" to "轻提示", "enName" to "Toast", "description" to "轻量级提示"), "TDialog" to _uO("name" to "对话框", "enName" to "Dialog", "description" to "模态对话框"), "TPopup" to _uO("name" to "弹出层", "enName" to "Popup", "description" to "弹出层容器"), "TActionSheet" to _uO("name" to "动作面板", "enName" to "ActionSheet", "description" to "底部动作面板"), "Tabs" to _uO("name" to "标签页", "enName" to "Tabs", "description" to "选项卡切换"), "TNavBar" to _uO("name" to "导航栏", "enName" to "NavBar", "description" to "顶部导航栏"), "I18nDemo" to _uO("name" to "多语言测试", "enName" to "I18n", "description" to "国际化多语言功能演示"), "Request" to _uO("name" to "请求封装", "enName" to "Request", "description" to "HttpRequest 请求封装、拦截器与响应处理示例"), "Utils" to _uO("name" to "工具方法", "enName" to "Utils", "description" to "常用工具函数与 storage 封装示例"))
val default__5: UTSJSONObject = _uO("groups" to groups, "components" to components)
val button: UTSJSONObject = _uO("title" to "TButton 按钮组件", "description" to "按钮用于触发操作", "sections" to _uO("basic" to "基础用法", "plain" to "朴素按钮", "size" to "按钮尺寸", "disabled" to "禁用状态", "loading" to "加载状态", "round" to "圆角按钮", "block" to "块级按钮"), "buttons" to _uO("primary" to "主要按钮", "success" to "成功按钮", "warning" to "警告按钮", "danger" to "危险按钮", "info" to "信息按钮", "plain" to "朴素按钮", "large" to "大型按钮", "medium" to "中等按钮", "small" to "小型按钮", "disabled" to "禁用按钮", "loading" to "加载中", "clickToLoad" to "点击加载", "round" to "圆角按钮", "block" to "块级按钮"), "messages" to _uO("clicked" to "按钮被点击"))
val icon: UTSJSONObject = _uO("title" to "TIcon 图标组件", "description" to "语义化图标", "sections" to _uO("basic" to "基础用法", "size" to "图标尺寸", "color" to "图标颜色"))
val text: UTSJSONObject = _uO("title" to "TText 文本组件", "description" to "文本展示组件", "sections" to _uO("basic" to "基础用法", "type" to "文本类型", "size" to "文本尺寸", "weight" to "文本粗细", "align" to "文本对齐", "ellipsis" to "文本省略", "customColor" to "自定义颜色"), "content" to _uO("normal" to "这是一段普通文本", "primary" to "主要文本 Primary", "success" to "成功文本 Success", "warning" to "警告文本 Warning", "danger" to "危险文本 Danger", "info" to "信息文本 Info", "extraSmall" to "超小号文本 Extra Small", "small" to "小号文本 Small", "medium" to "中号文本 Medium", "large" to "大号文本 Large", "extraLarge" to "超大号文本 Extra Large", "bold" to "加粗文本", "italic" to "斜体文本", "underline" to "下划线文本", "lineThrough" to "删除线文本", "combined" to "组合修饰文本", "alignLeft" to "左对齐文本", "alignCenter" to "居中对齐文本", "alignRight" to "右对齐文本", "ellipsisOneLine" to "这是一段很长的文本，当超过一行时会显示省略号。这是一段很长的文本，当超过一行时会显示省略号。", "ellipsisTwoLines" to "这是一段很长的文本，当超过两行时会显示省略号。这是一段很长的文本，当超过两行时会显示省略号。这是一段很长的文本，当超过两行时会显示省略号。这是一段很长的文本，当超过两行时会显示省略号。", "customColor" to "自定义颜色文本"))
val image: UTSJSONObject = _uO("title" to "TImage 图片组件", "description" to "增强的图片组件", "sections" to _uO("basic" to "基础用法", "shape" to "图片形状", "mode" to "裁剪模式", "radius" to "自定义圆角", "error" to "加载失败", "preview" to "图片预览", "lazy" to "懒加载", "customText" to "自定义提示文本"), "labels" to _uO("square" to "方形", "round" to "圆角", "circle" to "圆形", "clickToPreview" to "点击图片可预览", "lazyLoadDesc" to "向下滚动时才会加载图片", "customLoading" to "自定义加载文本", "customError" to "自定义错误文本"), "messages" to _uO("loadComplete" to "图片加载完成", "loadFailed" to "图片加载失败", "errorText" to "图片加载失败", "loadingText" to "请稍候...", "errorCustomText" to "无法加载", "lazyLoading" to "懒加载中..."))
val divider: UTSJSONObject = _uO("title" to "TDivider 分割线", "description" to "内容分割线", "sections" to _uO("basic" to "基础用法", "dashed" to "虚线样式", "customColor" to "自定义颜色", "customThickness" to "自定义粗细", "withText" to "文字分割线", "customTextStyle" to "自定义文字样式", "customMargin" to "自定义间距", "vertical" to "垂直分割线", "verticalStyle" to "垂直分割线样式", "slot" to "插槽用法"), "labels" to _uO("default" to "默认分割线", "secondPart" to "第二部分内容", "dashedLine" to "虚线分割", "dottedLine" to "点线分割", "solidLine" to "实线分割", "blueLine" to "蓝色分割线", "redLine" to "红色分割线", "greenLine" to "绿色分割线", "hairline" to "细线 (hairline)", "defaultCenter" to "默认居中", "leftAlign" to "左对齐", "rightAlign" to "右对齐", "topContent" to "上方内容", "middleContent" to "中间内容", "bottomContent" to "下方内容", "blueText" to "蓝色文字", "largeText" to "大号文字", "smallMargin" to "小间距", "defaultMargin" to "默认间距", "largeMargin" to "大间距", "option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "option4" to "选项四", "solid" to "实线", "dashed" to "虚线", "dotted" to "点线", "colorful" to "彩色", "end" to "结束", "customSlot" to "自定义插槽内容"))
val card: UTSJSONObject = _uO("title" to "TCard 卡片组件", "description" to "卡片容器，用于组织内容", "sections" to _uO("basic" to "基础卡片", "withSubtitle" to "带副标题的卡片", "noShadow" to "无阴影卡片", "withBorder" to "带边框卡片", "padding" to "不同内边距", "largePadding" to "大内边距卡片", "radius" to "不同圆角", "largeRadius" to "大圆角卡片", "clickable" to "可点击卡片", "customSlot" to "自定义插槽"), "content" to _uO("basic" to "这是卡片的内容区域", "subtitle" to "这是副标题", "anyContent" to "卡片内容可以是任何元素", "noShadow" to "这个卡片没有阴影效果", "withBorder" to "这个卡片使用边框而不是阴影", "smallPadding" to "小内边距卡片", "largePadding" to "大内边距卡片", "noRadius" to "无圆角卡片", "largeRadius" to "大圆角卡片", "clickable" to "点击这个卡片会触发事件", "customHeader" to "自定义头部", "customSlot" to "这个卡片使用了自定义头部插槽"), "buttons" to _uO("action" to "操作", "cancel" to "取消", "confirm" to "确定"), "messages" to _uO("clicked" to "卡片被点击"))
val input: UTSJSONObject = _uO("title" to "TInput 输入框", "description" to "文本输入", "sections" to _uO("basic" to "基础用法", "type" to "输入类型", "size" to "不同大小", "disabled" to "禁用状态", "readonly" to "只读状态", "clearable" to "可清空", "affix" to "带前后缀", "events" to "事件处理", "maxlength" to "字数限制"), "placeholder" to "请输入内容", "labels" to _uO("inputValue" to "输入值", "textInput" to "文本输入", "passwordInput" to "密码输入", "numberInput" to "数字输入", "phoneInput" to "电话号码", "emailInput" to "邮箱地址", "smallSize" to "小尺寸输入框", "mediumSize" to "中等尺寸输入框", "largeSize" to "大尺寸输入框", "disabled" to "禁用的输入框", "readonly" to "只读的输入框", "clearable" to "可清除内容", "amount" to "请输入金额", "website" to "请输入网址", "domain" to "请输入域名", "checkConsole" to "查看控制台"))
val form: UTSJSONObject = _uO("title" to "TForm 表单组件", "description" to "动态表单，支持多种表单控件", "sections" to _uO("basic" to "基础用法", "full" to "完整表单（所有组件类型）", "customButtons" to "自定义按钮", "customSlot" to "自定义插槽"), "labels" to _uO("username" to "用户名", "password" to "密码", "name" to "姓名", "age" to "年龄", "bio" to "简介", "city" to "城市", "birthday" to "生日", "gender" to "性别", "hobbies" to "爱好", "notify" to "通知", "rating" to "评分", "volume" to "音量", "email" to "邮箱", "phone" to "手机号", "nickname" to "昵称", "avatar" to "头像"), "placeholders" to _uO("username" to "请输入用户名", "password" to "请输入密码", "name" to "请输入姓名", "age" to "请输入年龄", "bio" to "请输入个人简介", "email" to "请输入邮箱", "phone" to "请输入手机号", "nickname" to "请输入昵称"), "options" to _uO("beijing" to "北京", "shanghai" to "上海", "guangzhou" to "广州", "shenzhen" to "深圳", "male" to "男", "female" to "女", "reading" to "阅读", "sports" to "运动", "music" to "音乐"), "buttons" to _uO("submit" to "提交", "reset" to "重置", "validate" to "验证", "getData" to "获取数据", "uploadAvatar" to "上传头像"), "messages" to _uO("submitSuccess" to "提交成功", "reset" to "已重置", "validateSuccess" to "验证通过", "validateFail" to "验证失败", "uploadSuccess" to "上传成功", "formData" to "表单数据"))
val list: UTSJSONObject = _uO("title" to "TList 列表组件", "description" to "用于展示列表数据", "sections" to _uO("basic" to "基础列表", "withDescription" to "带描述的列表", "withIcon" to "带图标的列表", "withExtra" to "带右侧内容", "clickable" to "可点击列表", "customSpacing" to "自定义间距", "withTitle" to "带标题的列表"), "items" to _uO("item1" to "列表项 1", "item2" to "列表项 2", "item3" to "列表项 3", "titleText" to "标题文本", "description1" to "这是第一项的描述信息", "anotherTitle" to "另一个标题", "description2" to "这是第二项的描述信息", "settings" to "设置", "notification" to "通知", "privacy" to "隐私", "about" to "关于", "balance" to "余额", "coupon" to "优惠券", "points" to "积分", "clickable1" to "可点击项 1", "clickable2" to "可点击项 2", "disabled" to "禁用项", "noSpacing1" to "无间距项 1", "noSpacing2" to "无间距项 2", "largeSpacing1" to "大间距项 1", "largeSpacing2" to "大间距项 2", "systemSettings" to "系统设置", "general" to "通用", "accountSecurity" to "账号与安全"), "extra" to _uO("balance" to "¥1,234.56", "couponCount" to "3 张", "pointsCount" to "1,234"))
val dialog: UTSJSONObject = _uO("title" to "TDialog 对话框", "description" to "模态对话框", "sections" to _uO("basic" to "基础用法", "confirm" to "确认对话框", "custom" to "自定义内容"), "buttons" to _uO("show" to "显示对话框", "confirm" to "确认", "cancel" to "取消"), "content" to _uO("title" to "提示", "message" to "这是一条消息"))
val toast: UTSJSONObject = _uO("title" to "TToast 轻提示", "description" to "轻量级提示", "sections" to _uO("basic" to "基础用法", "type" to "提示类型", "duration" to "显示时长"), "buttons" to _uO("show" to "显示提示", "success" to "成功提示", "error" to "错误提示", "warning" to "警告提示", "loading" to "加载提示"), "messages" to _uO("text" to "这是一条文本提示", "success" to "操作成功", "fail" to "操作失败", "loading" to "加载中...", "loadComplete" to "加载完成"))
val loading__1: UTSJSONObject = _uO("title" to "TLoading 加载", "description" to "加载状态", "sections" to _uO("basic" to "基础用法", "type" to "加载类型", "text" to "加载文案"), "text" to _uO("loading" to "加载中..."))
val empty: UTSJSONObject = _uO("title" to "TEmpty 空状态", "description" to "空数据展示", "sections" to _uO("basic" to "基础用法", "custom" to "自定义内容"), "text" to _uO("noData" to "暂无数据", "noResult" to "暂无搜索结果"))
val picker: UTSJSONObject = _uO("title" to "TPicker 选择器", "description" to "多列数据选择", "sections" to _uO("basic" to "基础用法", "multiColumn" to "多列选择", "cascade" to "级联选择"), "buttons" to _uO("confirm" to "确认", "cancel" to "取消"))
val actionSheet: UTSJSONObject = _uO("title" to "TActionSheet 动作面板", "description" to "底部动作面板", "sections" to _uO("basic" to "基础用法", "withTitle" to "带标题描述", "customColor" to "自定义颜色"), "buttons" to _uO("show" to "打开操作面板", "cancel" to "取消"), "actions" to _uO("option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "shareToFriend" to "分享给朋友", "shareToMoments" to "分享到朋友圈", "copyLink" to "复制链接", "delete" to "删除"), "dialog" to _uO("title" to "操作选择", "description" to "请选择您要进行的操作"), "messages" to _uO("selected" to "选择了"))
val checkbox: UTSJSONObject = _uO("title" to "TCheckbox 复选框组件", "description" to "多选框组件", "sections" to _uO("basic" to "基础用法", "defaultChecked" to "默认选中", "group" to "复选框组", "disabled" to "禁用状态", "size" to "不同大小", "customColor" to "自定义颜色", "indeterminate" to "中间状态", "events" to "事件处理"), "labels" to _uO("checkbox" to "复选框", "defaultChecked" to "默认选中的复选框", "option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "disabledUnchecked" to "禁用未选中", "disabledChecked" to "禁用已选中", "small" to "小尺寸", "medium" to "中等尺寸", "large" to "大尺寸", "red" to "红色复选框", "green" to "绿色复选框", "orange" to "橙色复选框", "indeterminate" to "半选状态", "clickToConsole" to "点击查看控制台"), "status" to _uO("checked" to "选中状态", "selected" to "选中值", "notSelected" to "未选择", "indeterminateDesc" to "用于表示部分选中状态", "checkConsole" to "切换复选框查看控制台"))
val cell: UTSJSONObject = _uO("title" to "TCell 单元格", "description" to "单元格组件", "sections" to _uO("basic" to "基础用法", "withIcon" to "带图标", "withValue" to "带值", "link" to "链接单元格"), "groups" to _uO("showArrow" to "显示箭头", "clickableCell" to "可点击单元格", "customSize" to "自定义尺寸", "customContent" to "自定义内容", "group" to "单元格组", "required" to "必填项"), "labels" to _uO("cell" to "单元格", "content" to "内容", "description" to "描述信息", "settings" to "设置", "message" to "消息", "notification" to "通知", "enabled" to "已启用", "personalInfo" to "个人信息", "systemSettings" to "系统设置", "enter" to "进入", "aboutUs" to "关于我们", "clickable" to "可点击", "notClickable" to "不可点击", "largeCell" to "大尺寸单元格", "defaultCell" to "默认单元格", "smallCell" to "小尺寸单元格", "customTitle" to "自定义标题", "accountManagement" to "账号管理", "privacySettings" to "隐私设置", "notificationSettings" to "通知设置", "basicSettings" to "基础设置", "clearCache" to "清除缓存", "logout" to "退出登录", "other" to "其他", "username" to "用户名", "phone" to "手机号", "pleaseEnter" to "请输入", "email" to "邮箱", "optional" to "选填"))
val grid: UTSJSONObject = _uO("title" to "TGrid 宫格", "description" to "宫格布局", "sections" to _uO("basic" to "基础用法", "custom" to "自定义列数", "customGap" to "自定义间距", "border" to "显示边框", "square" to "正方形格子", "clickable" to "可点击", "customColor" to "自定义图标颜色", "badge" to "徽标提示", "image" to "使用图片", "customContent" to "自定义内容"), "labels" to _uO("home" to "首页", "mail" to "邮件", "favorite" to "收藏", "settings" to "设置", "phone" to "电话", "bulb" to "灯泡", "palette" to "调色板", "camera" to "相机", "red" to "红色", "blue" to "蓝色", "green" to "绿色", "yellow" to "黄色", "image1" to "图片1", "image2" to "图片2", "image3" to "图片3", "custom" to "自定义", "customDesc" to "自定义内容", "normalGrid" to "普通宫格", "blueColor" to "蓝色", "background" to "背景色", "fast" to "快速"))
val layout: UTSJSONObject = _uO("title" to "TRow/TCol 栅格", "description" to "24栅格布局", "sections" to _uO("basic" to "基础用法", "gutter" to "区块间隔", "offset" to "列偏移", "align" to "水平对齐", "verticalAlign" to "垂直对齐", "responsive" to "响应式"), "labels" to _uO("leftAlign" to "左对齐", "centerAlign" to "居中对齐", "rightAlign" to "右对齐", "spaceBetween" to "两端对齐", "spaceAround" to "分散对齐", "topAlign" to "顶部对齐", "middleAlign" to "居中对齐", "bottomAlign" to "底部对齐", "height" to "高度"))
val textarea: UTSJSONObject = _uO("title" to "TTextarea 多行输入", "description" to "多行文本输入", "sections" to _uO("basic" to "基础用法", "disabled" to "禁用状态", "maxlength" to "字数统计", "autoHeight" to "自动高度", "clearable" to "显示清空按钮", "customRows" to "自定义行数", "complete" to "完整示例"), "placeholder" to "请输入内容", "labels" to _uO("disabled" to "禁用状态", "limit100" to "最多输入100个字符", "autoGrow" to "自动增长高度", "clearable" to "可清空内容", "rows3" to "3行", "rows3Height" to "固定3行高度", "rows5" to "5行", "rows5Height" to "固定5行高度", "feedback" to "请输入您的反馈意见"))
val error__1: UTSJSONObject = _uO("title" to "TErrorState 错误状态", "description" to "错误提示", "sections" to _uO("404" to "404 错误", "500" to "500 错误", "basic" to "基础用法", "custom" to "自定义内容", "network" to "网络错误", "withButton" to "带按钮", "customIcon" to "自定义图标", "customSlot" to "自定义插槽"), "text" to _uO("error" to "发生错误", "retry" to "重试", "networkError" to "网络连接失败", "404Title" to "页面未找到", "404Message" to "抱歉，您访问的页面不存在", "500Title" to "服务器错误", "500Message" to "抱歉，服务器出现了一些问题", "networkTitle" to "网络异常", "networkMessage" to "请检查您的网络连接", "loadFailed" to "加载失败", "loadFailedMessage" to "数据加载失败，请重试", "reload" to "重新加载", "operationFailed" to "操作失败", "tryAgain" to "请稍后再试", "errorOccurred" to "出错了", "customError" to "这是自定义的错误提示内容", "back" to "返回"))
val radio: UTSJSONObject = _uO("title" to "TRadioButton 单选组件", "description" to "单选按钮组件", "sections" to _uO("basic" to "基础用法", "group" to "单选组", "disabled" to "禁用状态", "size" to "不同大小", "customColor" to "自定义颜色", "events" to "事件处理"), "labels" to _uO("option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "optionA" to "选项 A", "optionB" to "选项 B", "optionC" to "选项 C", "optionD" to "选项 D", "disabledUnchecked" to "禁用未选中", "disabledChecked" to "禁用已选中", "small" to "小尺寸", "medium" to "中等尺寸", "large" to "大尺寸", "red" to "红色", "green" to "绿色", "orange" to "橙色"), "status" to _uO("selected" to "选中值", "notSelected" to "未选择", "checkConsole" to "切换选项查看控制台"))
val searchBar: UTSJSONObject = _uO("title" to "TSearchBar 搜索框", "description" to "搜索输入框", "sections" to _uO("basic" to "基础用法", "round" to "圆角样式", "showCancel" to "显示取消按钮", "customBackground" to "自定义背景色", "disabled" to "禁用状态", "customIcon" to "自定义图标", "maxlength" to "限制长度"), "placeholder" to _uO("default" to "请输入搜索内容", "round" to "圆角搜索框", "background" to "浅蓝色背景", "disabled" to "禁用状态", "customIcon" to "自定义搜索图标", "maxlength" to "最多输入20个字符"), "buttons" to _uO("cancel" to "取消"), "messages" to _uO("search" to "搜索", "cancelled" to "已取消"))
val numberInput: UTSJSONObject = _uO("title" to "TNumberInput 数字输入组件", "description" to "用于输入数字的增强组件", "sections" to _uO("basic" to "基础用法", "range" to "设置范围", "step" to "步长设置", "precision" to "精度控制", "size" to "不同大小", "disabled" to "禁用状态", "events" to "事件处理"), "labels" to _uO("currentValue" to "当前值", "range" to "范围", "step" to "步长", "integer" to "整数", "decimal" to "保留2位小数", "checkConsole" to "查看控制台了解数值变化"))
val select: UTSJSONObject = _uO("title" to "TSelect 选择器组件", "description" to "下拉选择器组件", "sections" to _uO("basic" to "基础用法", "upward" to "向上展开", "default" to "默认值", "disabled" to "禁用状态", "disabledOptions" to "禁用选项", "clearable" to "可清空", "filterable" to "可搜索", "multiple" to "多选", "size" to "不同大小", "events" to "事件处理"), "labels" to _uO("option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "option4" to "选项四", "option5" to "选项五", "item1" to "第一项", "item2" to "第二项", "item3" to "第三项", "available1" to "可选项一", "disabled" to "禁用项", "available2" to "可选项二", "available3" to "可选项三", "small" to "小尺寸", "medium" to "中等尺寸", "large" to "大尺寸"), "placeholder" to _uO("default" to "请选择", "upward" to "向上展开", "disabled" to "禁用状态", "partialDisabled" to "部分选项禁用", "clearable" to "可清空选择", "filterable" to "可搜索选项", "multiple" to "请选择多个选项", "checkConsole" to "选择查看控制台"), "messages" to _uO("selected" to "选中值", "notSelected" to "未选择", "upwardDesc" to "设置 placement=\"top\" 可以让下拉列表向上展开"))
val slider: UTSJSONObject = _uO("title" to "TSlider 滑块", "description" to "滑动选择器", "sections" to _uO("basic" to "基础用法", "showValue" to "显示当前值", "customColor" to "自定义颜色", "step" to "设置步长", "disabled" to "禁用状态"))
val rate: UTSJSONObject = _uO("title" to "TRate 评分", "description" to "评分组件", "sections" to _uO("basic" to "基础用法", "halfStar" to "半星", "readonly" to "只读", "customIcon" to "自定义图标"))
val tags: UTSJSONObject = _uO("title" to "Tags 标签组件", "description" to "用于标记和分类的标签组件", "sections" to _uO("basic" to "基础用法", "card" to "卡片类型", "line" to "横线类型", "size" to "不同尺寸", "badge" to "带徽章", "disabled" to "禁用状态", "bottom" to "底部标签栏", "customColor" to "自定义颜色", "centered" to "居中显示", "noAnimation" to "禁用动画", "usage" to "使用说明"), "labels" to _uO("home" to "首页", "product" to "产品", "service" to "服务", "about" to "关于", "all" to "全部", "ongoing" to "进行中", "finished" to "已完成", "tab1" to "选项1", "tab2" to "选项2", "tab3" to "选项3", "message" to "消息", "notification" to "通知", "todo" to "待办", "enabled1" to "可用选项1", "disabled" to "禁用选项", "enabled2" to "可用选项2", "discover" to "发现", "profile" to "我的", "redTheme" to "红色主题", "greenTheme" to "绿色主题", "blueTheme" to "蓝色主题"), "content" to _uO("currentKey" to "当前激活的 Key", "home" to "首页内容区域", "product" to "产品展示区域", "service" to "服务介绍区域", "about" to "关于我们区域", "currentSelected" to "当前选中", "allTasks" to "全部任务", "allTasksDesc" to "显示所有任务列表", "ongoing" to "进行中", "ongoingDesc" to "显示正在进行的任务", "finished" to "已完成", "finishedDesc" to "显示已完成的任务", "current" to "当前", "messageCenter" to "消息中心", "unreadMessages" to "您有 5 条未读消息", "notifications" to "通知", "notificationCount" to "您有 99+ 条通知", "todoList" to "待办事项", "newTodo" to "您有新的待办事项", "disabledDesc" to "中间的选项已被禁用，无法点击", "homeContent" to "首页", "bottomNavDesc" to "标签栏显示在底部，适合底部导航", "discoverContent" to "发现页面内容", "profileContent" to "个人中心内容", "customColorTheme" to "自定义颜色主题", "redTheme" to "激活颜色为红色 (#e74c3c)", "centered" to "居中显示", "noAnimation" to "无动画效果"), "usage" to _uO("props" to "主要属性", "vModel" to "• v-model: 绑定当前激活的标签", "items" to "• items: 标签数据数组", "type" to "• type: 标签类型 (line | card)", "size" to "• size: 标签大小 (small | medium | large)", "tabPosition" to "• tabPosition: 标签位置 (top | bottom)", "scrollable" to "• scrollable: 是否可滚动", "centered" to "• centered: 是否居中显示", "activeColor" to "• activeColor: 激活颜色", "inactiveColor" to "• inactiveColor: 非激活颜色", "animated" to "• animated: 是否显示动画", "events" to "主要事件", "change" to "• @change: 标签切换时触发", "tabClick" to "• @tab-click: 点击标签时触发", "slots" to "插槽", "default" to "• default: 内容区域,接收 activeKey 参数", "tabItem" to "TabItem 类型", "key" to "• key: 唯一标识 (必填)", "label" to "• label: 标签文本 (必填)", "disabled" to "• disabled: 是否禁用", "badge" to "• badge: 徽章内容", "icon" to "• icon: 图标路径"))
val badge: UTSJSONObject = _uO("title" to "TBadge 徽标组件", "description" to "用于显示数字或状态标记", "sections" to _uO("basic" to "基础用法", "max" to "最大值", "dot" to "小圆点", "type" to "不同类型", "customColor" to "自定义颜色", "offset" to "偏移量", "dynamic" to "动态控制"), "labels" to _uO("comment" to "评论", "reply" to "回复", "message" to "消息", "warning" to "警告", "online" to "在线", "offline" to "离线", "primary" to "主要", "danger" to "危险", "success" to "成功", "info" to "信息", "bgColor" to "背景色", "blue" to "蓝色", "green" to "绿色", "offset" to "偏移", "dynamic" to "动态"), "buttons" to _uO("increase" to "增加", "decrease" to "减少", "show" to "显示", "hide" to "隐藏"))
val avatar: UTSJSONObject = _uO("title" to "TAvatar 头像组件", "description" to "用于展示用户头像或图标", "sections" to _uO("basic" to "基础用法", "size" to "不同尺寸", "shape" to "不同形状", "customColor" to "自定义颜色", "fallback" to "加载失败兜底图", "fit" to "图片填充模式", "events" to "带事件", "customContent" to "自定义内容"), "messages" to _uO("clicked" to "头像被点击", "error" to "图片加载失败", "clickOrConsole" to "点击头像或查看控制台日志"))
val progress: UTSJSONObject = _uO("title" to "TProgress 进度条", "description" to "进度指示器", "sections" to _uO("basic" to "基础用法", "type" to "进度条类型", "status" to "进度条状态", "custom" to "自定义颜色"))
val noticeBar: UTSJSONObject = _uO("title" to "TNoticeBar 通知栏", "description" to "滚动通知", "sections" to _uO("basic" to "基础用法", "closable" to "可关闭", "link" to "链接模式", "scrollable" to "滚动播放", "customColor" to "自定义颜色"), "text" to _uO("notice" to "这是一条通知消息", "closable" to "这是一条可关闭的通知消息", "link" to "点击查看详情", "scrollable" to "这是一条很长的通知消息，会自动滚动播放以便用户查看完整内容", "customColor" to "自定义颜色的通知栏", "danger" to "危险提示信息"))
val collapse: UTSJSONObject = _uO("title" to "Collapse 折叠面板", "description" to "可以折叠/展开的内容区域", "sections" to _uO("basic" to "基础用法", "accordion" to "手风琴模式", "disabled" to "禁用状态", "customTitle" to "自定义标题", "noBorder" to "无边框", "currentState" to "当前状态"), "titles" to _uO("consistency" to "一致性 Consistency", "feedback" to "反馈 Feedback", "efficiency" to "效率 Efficiency", "designPrinciples" to "设计原则", "navigation" to "导航", "feedbackTitle" to "反馈", "normalItem" to "正常项", "disabledItem" to "禁用项", "mobileComponents" to "移动端组件", "richComponents" to "丰富的组件", "highPerformance" to "高性能", "item1" to "第一项", "item2" to "第二项"), "content" to _uO("consistency" to "与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念。", "feedback" to "控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作。", "efficiency" to "简化流程：设计简洁直观的操作流程，让用户快速完成目标。", "designPrinciples" to "一致、反馈、效率、可控是我们的设计原则。", "navigation" to "导航可以解决用户在访问页面时，在哪里、去哪里、怎么去的问题。", "feedbackContent" to "反馈是对用户行为的一种回应，给用户一个交代。", "normalItem" to "这是一个正常的折叠面板项，可以正常展开收起。", "disabledItem" to "这是一个禁用的折叠面板项，无法展开。", "mobileComponents" to "专为移动端设计的轻量级 UI 组件库。", "richComponents" to "提供丰富的基础组件，覆盖移动端各类场景。", "highPerformance" to "性能优异，轻量级实现，保证流畅体验。", "item1Content" to "这是第一项的内容。", "item2Content" to "这是第二项的内容。"), "labels" to _uO("expandedItems" to "基础用法展开项", "accordionExpanded" to "手风琴展开项", "none" to "无"))
val swiper: UTSJSONObject = _uO("title" to "Swiper 轮播图", "description" to "用于循环播放图片或内容", "sections" to _uO("basic" to "基础用法", "customIndicator" to "自定义指示器颜色", "numberIndicator" to "数字指示器", "topIndicator" to "顶部指示器", "vertical" to "垂直轮播", "noAutoplay" to "禁用自动播放", "noIndicator" to "无指示器", "customHeight" to "自定义高度和间隔"), "labels" to _uO("carousel1" to "轮播图 1", "carousel2" to "轮播图 2", "carousel3" to "轮播图 3", "carousel4" to "轮播图 4", "currentIndex" to "当前索引", "manualSwipe" to "手动滑动切换", "heightDesc" to "高度 150px，间隔 5秒，动画 500ms"))
val navbar: UTSJSONObject = _uO("title" to "TNavBar 导航栏", "description" to "顶部导航栏", "sections" to _uO("basic" to "基础用法", "leftBack" to "左侧返回", "rightButton" to "右侧按钮", "bothButtons" to "左右按钮"), "labels" to _uO("title" to "标题", "back" to "返回", "button" to "按钮", "more" to "更多"), "messages" to _uO("clickLeft" to "点击左侧", "clickRight" to "点击右侧"))
val tabs: UTSJSONObject = _uO("title" to "Tabs 标签页组件", "description" to "选项卡切换组件,支持多种样式和配置", "sections" to _uO("basic" to "基础用法", "card" to "卡片类型", "line" to "横线类型", "size" to "不同尺寸", "badge" to "带徽章", "disabled" to "禁用状态", "bottom" to "底部标签栏", "customColor" to "自定义颜色", "centered" to "居中显示", "noAnimation" to "禁用动画", "usage" to "使用说明"), "labels" to _uO("home" to "首页", "profile" to "个人中心", "settings" to "设置", "about" to "关于", "user" to "用户信息", "order" to "订单管理", "wallet" to "我的钱包", "option1" to "选项1", "option2" to "选项2", "option3" to "选项3", "message" to "消息", "notification" to "通知", "todo" to "待办", "enabled1" to "可用选项1", "disabled" to "禁用选项", "enabled2" to "可用选项2", "discover" to "发现", "me" to "我的"), "content" to _uO("currentKey" to "当前激活的 Key", "home" to "这是首页的内容区域", "profile" to "这是个人中心的内容区域", "settings" to "这是设置页面的内容区域", "about" to "这是关于页面的内容区域", "user" to "用户信息", "userPanel" to "卡片样式的用户信息面板", "order" to "订单管理", "orderPanel" to "卡片样式的订单管理面板", "wallet" to "我的钱包", "walletPanel" to "卡片样式的钱包面板", "currentSelected" to "当前选中", "messageCenter" to "消息中心", "unreadMessages" to "您有 5 条未读消息", "notifications" to "通知", "notificationCount" to "您有 99+ 条通知", "todoList" to "待办事项", "newTodo" to "您有新的待办事项", "disabledDesc" to "中间的选项已被禁用，无法点击", "homeContent" to "首页", "bottomNavDesc" to "标签栏显示在底部，适合作为底部导航", "discoverContent" to "发现页内容", "meContent" to "个人中心内容", "customColorTheme" to "自定义颜色", "redTheme" to "激活颜色为红色 (#ff6b6b)", "centered" to "居中显示的标签", "noAnimation" to "无动画效果"), "usage" to _uO("props" to "主要属性", "vModel" to "• v-model: 绑定当前激活的标签", "type" to "• type: 标签类型 (line | card)", "size" to "• size: 标签大小 (small | medium | large)", "tabPosition" to "• tabPosition: 标签位置 (top | bottom)", "scrollable" to "• scrollable: 是否可滚动", "centered" to "• centered: 是否居中显示", "activeColor" to "• activeColor: 激活颜色", "inactiveColor" to "• inactiveColor: 非激活颜色", "animated" to "• animated: 是否显示动画", "events" to "主要事件", "change" to "• @change: 标签切换时触发", "slots" to "插槽", "default" to "• default: 内容区域,接收 activePane 和 activeIndex"))
val popup: UTSJSONObject = _uO("title" to "TPopup 弹出层组件", "description" to "弹出层容器，用于展示弹出内容", "sections" to _uO("basic" to "基础用法", "position" to "不同方向"), "buttons" to _uO("bottom" to "底部弹出", "top" to "顶部弹出", "left" to "左侧弹出", "right" to "右侧弹出", "center" to "中心弹出"), "content" to _uO("bottom" to "这是弹出层内容", "top" to "顶部弹出内容", "left" to "左侧弹出内容", "right" to "右侧弹出内容", "center" to "中心弹出", "centerDesc" to "这是一个中心弹出的内容区域"))
val checkboxGroup: UTSJSONObject = _uO("title" to "TCheckboxGroup 复选组", "description" to "复选框组", "sections" to _uO("basic" to "基础用法", "horizontal" to "水平排列", "disabled" to "禁用选项", "max" to "限制最多选择", "disabledAll" to "禁用所有"), "labels" to _uO("option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "option2Disabled" to "选项二（禁用）"))
val radioGroup: UTSJSONObject = _uO("title" to "TRadioGroup 单选组", "description" to "单选框组", "sections" to _uO("basic" to "基础用法", "horizontal" to "水平排列", "disabled" to "禁用选项", "disabledAll" to "禁用所有"), "labels" to _uO("option1" to "选项一", "option2" to "选项二", "option3" to "选项三", "option2Disabled" to "选项二（禁用）"))
val default__6: UTSJSONObject = _uO("button" to button, "icon" to icon, "text" to text, "image" to image, "divider" to divider, "card" to card, "input" to input, "form" to form, "list" to list, "dialog" to dialog, "toast" to toast, "loading" to loading__1, "empty" to empty, "picker" to picker, "actionSheet" to actionSheet, "checkbox" to checkbox, "cell" to cell, "grid" to grid, "layout" to layout, "textarea" to textarea, "error" to error__1, "radio" to radio, "switch" to _uO("title" to "TSwitch 开关组件", "description" to "表示两种状态之间的切换", "sections" to _uO("basic" to "基础用法", "disabled" to "禁用状态", "size" to "不同尺寸", "customColor" to "自定义颜色", "withText" to "带文字提示", "events" to "change 事件"), "labels" to _uO("status" to "状态", "on" to "开", "off" to "关", "disabledOff" to "禁用状态（关）", "disabledOn" to "禁用状态（开）", "small" to "小尺寸", "medium" to "中尺寸", "large" to "大尺寸", "customColor" to "自定义颜色", "openConsole" to "打开控制台查看事件"), "text" to _uO("active" to "开启", "inactive" to "关闭")), "searchBar" to searchBar, "numberInput" to numberInput, "select" to select, "slider" to slider, "rate" to rate, "tags" to tags, "badge" to badge, "avatar" to avatar, "progress" to progress, "noticeBar" to noticeBar, "collapse" to collapse, "swiper" to swiper, "navbar" to navbar, "tabs" to tabs, "popup" to popup, "checkboxGroup" to checkboxGroup, "radioGroup" to radioGroup)
val inputPlaceholder = "请输入{label}"
val selectPlaceholder = "请选择"
val datePlaceholder = "请选择日期"
val timePlaceholder = "请选择时间"
val required = "此项为必填项"
val invalid = "格式不正确"
val minLength = "长度不能少于{min}个字符"
val maxLength = "长度不能超过{max}个字符"
val email = "请输入有效的邮箱地址"
val phone = "请输入有效的手机号码"
val url = "请输入有效的网址"
val requiredError = "请输入{label}"
val submitButton = "提交"
val resetButton = "重置"
val validationFailed = "表单验证失败"
val submitFailed = "表单提交失败"
val noOptions = "暂无选项"
val selectAll = "全选"
val clear = "清空"
val default__7: UTSJSONObject = _uO("inputPlaceholder" to inputPlaceholder, "selectPlaceholder" to selectPlaceholder, "datePlaceholder" to datePlaceholder, "timePlaceholder" to timePlaceholder, "required" to required, "invalid" to invalid, "minLength" to minLength, "maxLength" to maxLength, "email" to email, "phone" to phone, "url" to url, "requiredError" to requiredError, "submitButton" to submitButton, "resetButton" to resetButton, "validationFailed" to validationFailed, "submitFailed" to submitFailed, "noOptions" to noOptions, "selectAll" to selectAll, "clear" to clear)
val placeholder = "请输入"
val default__8: UTSJSONObject = _uO("placeholder" to placeholder)
val loadingText = "加载中..."
val finishedText = "没有更多了"
val errorText = "加载失败，点击重试"
val default__9: UTSJSONObject = _uO("loadingText" to loadingText, "finishedText" to finishedText, "errorText" to errorText)
val text__1 = "加载中..."
val default__10: UTSJSONObject = _uO("text" to text__1)
val backText = "返回"
val title__3 = "标题"
val default__11: UTSJSONObject = _uO("backText" to backText, "title" to title__3)
val closeText = "关闭"
val default__12: UTSJSONObject = _uO("closeText" to closeText)
val title__4 = "请选择"
val confirmText__1 = "确定"
val cancelText__2 = "取消"
val default__13: UTSJSONObject = _uO("title" to title__4, "confirmText" to confirmText__1, "cancelText" to cancelText__2)
val placeholder__1 = "请输入搜索关键词"
val cancelText__3 = "取消"
val default__14: UTSJSONObject = _uO("placeholder" to placeholder__1, "cancelText" to cancelText__3)
val placeholder__2 = "请输入内容"
val default__15: UTSJSONObject = _uO("placeholder" to placeholder__2)
val loading__2 = "加载中..."
val success = "操作成功"
val fail = "操作失败"
val info = "提示"
val default__16: UTSJSONObject = _uO("loading" to loading__2, "success" to success, "fail" to fail, "info" to info)
val title__5 = "选择时间"
val confirmText__2 = "确定"
val cancelText__4 = "取消"
val year = "年"
val month = "月"
val day = "日"
val hour = "时"
val minute = "分"
val default__17: UTSJSONObject = _uO("title" to title__5, "confirmText" to confirmText__2, "cancelText" to cancelText__4, "year" to year, "month" to month, "day" to day, "hour" to hour, "minute" to minute)
val cancelText__5 = "Cancel"
val default__18: UTSJSONObject = _uO("cancelText" to cancelText__5)
val confirm__1 = "Confirm"
val cancel__1 = "Cancel"
val ok__1 = "OK"
val close__1 = "Close"
val save__1 = "Save"
val edit__1 = "Edit"
val submit__1 = "Submit"
val reset__1 = "Reset"
val search__1 = "Search"
val loading__3 = "Loading..."
val noData__2 = "No Data"
val error__2 = "Error"
val language__1 = "Language"
val languageSwitch__1 = "Switch Language"
val default__19: UTSJSONObject = _uO("confirm" to confirm__1, "cancel" to cancel__1, "ok" to ok__1, "close" to close__1, "save" to save__1, "delete" to "Delete", "edit" to edit__1, "submit" to submit__1, "reset" to reset__1, "search" to search__1, "loading" to loading__3, "noData" to noData__2, "error" to error__2, "language" to language__1, "languageSwitch" to languageSwitch__1)
val title__6 = "Notice"
val confirmText__3 = "Confirm"
val cancelText__6 = "Cancel"
val default__20: UTSJSONObject = _uO("title" to title__6, "confirmText" to confirmText__3, "cancelText" to cancelText__6)
val title__7 = "No Data"
val noData__3 = "No Data"
val description__2 = "No relevant content available"
val actionText__1 = "Try Refresh"
val default__21: UTSJSONObject = _uO("title" to title__7, "noData" to noData__3, "description" to description__2, "actionText" to actionText__1)
val title__8 = "Error"
val description__3 = "Page load failed, please try again later"
val retryText__1 = "Retry"
val default__22: UTSJSONObject = _uO("title" to title__8, "description" to description__3, "retryText" to retryText__1)
val groups__1: UTSJSONObject = _uO("basic" to "Basic", "layout" to "Layout", "form" to "Form", "display" to "Display", "feedback" to "Feedback", "navigation" to "Navigation", "tools" to "Tools")
val components__1: UTSJSONObject = _uO("TButton" to _uO("name" to "Button", "enName" to "Button", "description" to "Trigger an action"), "TIcon" to _uO("name" to "Icon", "enName" to "Icon", "description" to "Semantic icons"), "TText" to _uO("name" to "Text", "enName" to "Text", "description" to "Text display component"), "TImage" to _uO("name" to "Image", "enName" to "Image", "description" to "Enhanced image component"), "TDivider" to _uO("name" to "Divider", "enName" to "Divider", "description" to "Content divider"), "TCard" to _uO("name" to "Card", "enName" to "Card", "description" to "Card container"), "TList" to _uO("name" to "List", "enName" to "List", "description" to "List display"), "TCell" to _uO("name" to "Cell", "enName" to "Cell", "description" to "Cell component"), "TGrid" to _uO("name" to "Grid", "enName" to "Grid", "description" to "Grid layout"), "TRow/TCol" to _uO("name" to "Layout", "enName" to "Layout", "description" to "24-column grid layout"), "TInput" to _uO("name" to "Input", "enName" to "Input", "description" to "Text input"), "TTextarea" to _uO("name" to "Textarea", "enName" to "Textarea", "description" to "Multi-line text input"), "TSearchBar" to _uO("name" to "SearchBar", "enName" to "SearchBar", "description" to "Search input box"), "TNumberInput" to _uO("name" to "NumberInput", "enName" to "NumberInput", "description" to "Number input box"), "TSwitch" to _uO("name" to "Switch", "enName" to "Switch", "description" to "Switch selector"), "TCheckbox" to _uO("name" to "Checkbox", "enName" to "Checkbox", "description" to "Checkbox"), "TCheckboxGroup" to _uO("name" to "CheckboxGroup", "enName" to "CheckboxGroup", "description" to "Checkbox group"), "TRadioButton" to _uO("name" to "Radio", "enName" to "Radio", "description" to "Radio button"), "TRadioGroup" to _uO("name" to "RadioGroup", "enName" to "RadioGroup", "description" to "Radio group"), "TSelect" to _uO("name" to "Select", "enName" to "Select", "description" to "Dropdown selector"), "TPicker" to _uO("name" to "Picker", "enName" to "Picker", "description" to "Multi-column data picker"), "TDateTimePicker" to _uO("name" to "DateTimePicker", "enName" to "DateTimePicker", "description" to "Date and time picker"), "TSlider" to _uO("name" to "Slider", "enName" to "Slider", "description" to "Slider selector"), "TRate" to _uO("name" to "Rate", "enName" to "Rate", "description" to "Rating component"), "TForm" to _uO("name" to "Form", "enName" to "Form", "description" to "Form component"), "Tags" to _uO("name" to "Tags", "enName" to "Tags", "description" to "Tag classification"), "TBadge" to _uO("name" to "Badge", "enName" to "Badge", "description" to "Number badge"), "TAvatar" to _uO("name" to "Avatar", "enName" to "Avatar", "description" to "User avatar"), "TProgress" to _uO("name" to "Progress", "enName" to "Progress", "description" to "Progress indicator"), "TNoticeBar" to _uO("name" to "NoticeBar", "enName" to "NoticeBar", "description" to "Scrolling notice"), "TCollapse" to _uO("name" to "Collapse", "enName" to "Collapse", "description" to "Collapsible content area"), "TSwiper" to _uO("name" to "Swiper", "enName" to "Swiper", "description" to "Image/content carousel"), "TEmpty" to _uO("name" to "Empty", "enName" to "Empty", "description" to "Empty state display"), "TErrorState" to _uO("name" to "Error", "enName" to "Error", "description" to "Error prompt"), "TLoading" to _uO("name" to "Loading", "enName" to "Loading", "description" to "Loading state"), "TToast" to _uO("name" to "Toast", "enName" to "Toast", "description" to "Lightweight prompt"), "TDialog" to _uO("name" to "Dialog", "enName" to "Dialog", "description" to "Modal dialog"), "TPopup" to _uO("name" to "Popup", "enName" to "Popup", "description" to "Popup container"), "TActionSheet" to _uO("name" to "ActionSheet", "enName" to "ActionSheet", "description" to "Bottom action sheet"), "Tabs" to _uO("name" to "Tabs", "enName" to "Tabs", "description" to "Tab switching"), "TNavBar" to _uO("name" to "NavBar", "enName" to "NavBar", "description" to "Top navigation bar"), "I18nDemo" to _uO("name" to "I18n Demo", "enName" to "I18n", "description" to "Internationalization demo"), "Request" to _uO("name" to "Request", "enName" to "Request", "description" to "HttpRequest wrapper, interceptor, and response handling demo"), "Utils" to _uO("name" to "Utils", "enName" to "Utils", "description" to "Common utility functions and storage wrapper demo"))
val default__23: UTSJSONObject = _uO("groups" to groups__1, "components" to components__1)
val button__1: UTSJSONObject = _uO("title" to "TButton Component", "description" to "Trigger an action", "sections" to _uO("basic" to "Basic Usage", "plain" to "Plain Button", "size" to "Button Size", "disabled" to "Disabled State", "loading" to "Loading State", "round" to "Round Button", "block" to "Block Button"), "buttons" to _uO("primary" to "Primary", "success" to "Success", "warning" to "Warning", "danger" to "Danger", "info" to "Info", "plain" to "Plain", "large" to "Large", "medium" to "Medium", "small" to "Small", "disabled" to "Disabled", "loading" to "Loading", "clickToLoad" to "Click to Load", "round" to "Round", "block" to "Block"), "messages" to _uO("clicked" to "Button clicked"))
val icon__1: UTSJSONObject = _uO("title" to "TIcon Component", "description" to "Semantic icons", "sections" to _uO("basic" to "Basic Usage", "size" to "Icon Size", "color" to "Icon Color"))
val text__2: UTSJSONObject = _uO("title" to "TText Component", "description" to "Text display component", "sections" to _uO("basic" to "Basic Usage", "type" to "Text Type", "size" to "Text Size", "weight" to "Text Weight", "align" to "Text Alignment", "ellipsis" to "Text Ellipsis", "customColor" to "Custom Color"), "content" to _uO("normal" to "This is a normal text", "primary" to "Primary Text", "success" to "Success Text", "warning" to "Warning Text", "danger" to "Danger Text", "info" to "Info Text", "extraSmall" to "Extra Small Text", "small" to "Small Text", "medium" to "Medium Text", "large" to "Large Text", "extraLarge" to "Extra Large Text", "bold" to "Bold Text", "italic" to "Italic Text", "underline" to "Underline Text", "lineThrough" to "Line Through Text", "combined" to "Combined Style Text", "alignLeft" to "Left Aligned Text", "alignCenter" to "Center Aligned Text", "alignRight" to "Right Aligned Text", "ellipsisOneLine" to "This is a very long text that will show ellipsis when it exceeds one line. This is a very long text that will show ellipsis when it exceeds one line.", "ellipsisTwoLines" to "This is a very long text that will show ellipsis when it exceeds two lines. This is a very long text that will show ellipsis when it exceeds two lines. This is a very long text that will show ellipsis when it exceeds two lines. This is a very long text that will show ellipsis when it exceeds two lines.", "customColor" to "Custom Color Text"))
val image__1: UTSJSONObject = _uO("title" to "TImage Component", "description" to "Enhanced image component", "sections" to _uO("basic" to "Basic Usage", "shape" to "Image Shape", "mode" to "Crop Mode", "radius" to "Custom Radius", "error" to "Load Error", "preview" to "Image Preview", "lazy" to "Lazy Load", "customText" to "Custom Text"), "labels" to _uO("square" to "Square", "round" to "Round", "circle" to "Circle", "clickToPreview" to "Click to preview", "lazyLoadDesc" to "Image loads when scrolling down", "customLoading" to "Custom loading text", "customError" to "Custom error text"), "messages" to _uO("loadComplete" to "Image loaded", "loadFailed" to "Image load failed", "errorText" to "Image load failed", "loadingText" to "Please wait...", "errorCustomText" to "Cannot load", "lazyLoading" to "Lazy loading..."))
val divider__1: UTSJSONObject = _uO("title" to "TDivider Component", "description" to "Content divider", "sections" to _uO("basic" to "Basic Usage", "dashed" to "Dashed Style", "customColor" to "Custom Color", "customThickness" to "Custom Thickness", "withText" to "With Text", "customTextStyle" to "Custom Text Style", "customMargin" to "Custom Margin", "vertical" to "Vertical Divider", "verticalStyle" to "Vertical Divider Style", "slot" to "Slot Usage"), "labels" to _uO("default" to "Default divider", "secondPart" to "Second part content", "dashedLine" to "Dashed line", "dottedLine" to "Dotted line", "solidLine" to "Solid line", "blueLine" to "Blue divider", "redLine" to "Red divider", "greenLine" to "Green divider", "hairline" to "Hairline", "defaultCenter" to "Default center", "leftAlign" to "Left align", "rightAlign" to "Right align", "topContent" to "Top content", "middleContent" to "Middle content", "bottomContent" to "Bottom content", "blueText" to "Blue text", "largeText" to "Large text", "smallMargin" to "Small margin", "defaultMargin" to "Default margin", "largeMargin" to "Large margin", "option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "option4" to "Option 4", "solid" to "Solid", "dashed" to "Dashed", "dotted" to "Dotted", "colorful" to "Colorful", "end" to "End", "customSlot" to "Custom slot content"))
val card__1: UTSJSONObject = _uO("title" to "TCard Component", "description" to "Card container for organizing content", "sections" to _uO("basic" to "Basic Card", "withSubtitle" to "Card with Subtitle", "noShadow" to "No Shadow Card", "withBorder" to "Card with Border", "padding" to "Different Padding", "largePadding" to "Large Padding Card", "radius" to "Different Radius", "largeRadius" to "Large Radius Card", "clickable" to "Clickable Card", "customSlot" to "Custom Slot"), "content" to _uO("basic" to "This is the content area of the card", "subtitle" to "This is subtitle", "anyContent" to "Card content can be any element", "noShadow" to "This card has no shadow effect", "withBorder" to "This card uses border instead of shadow", "smallPadding" to "Small padding card", "largePadding" to "Large padding card", "noRadius" to "No radius card", "largeRadius" to "Large radius card", "clickable" to "Clicking this card will trigger an event", "customHeader" to "Custom Header", "customSlot" to "This card uses custom header slot"), "buttons" to _uO("action" to "Action", "cancel" to "Cancel", "confirm" to "Confirm"), "messages" to _uO("clicked" to "Card clicked"))
val input__1: UTSJSONObject = _uO("title" to "TInput Component", "description" to "Text input", "sections" to _uO("basic" to "Basic Usage", "type" to "Input Type", "size" to "Different Sizes", "disabled" to "Disabled State", "readonly" to "Readonly State", "clearable" to "Clearable", "affix" to "With Affix", "events" to "Event Handling", "maxlength" to "Max Length"), "placeholder" to "Please enter content", "labels" to _uO("inputValue" to "Input value", "textInput" to "Text input", "passwordInput" to "Password input", "numberInput" to "Number input", "phoneInput" to "Phone number", "emailInput" to "Email address", "smallSize" to "Small size input", "mediumSize" to "Medium size input", "largeSize" to "Large size input", "disabled" to "Disabled input", "readonly" to "Readonly input", "clearable" to "Clearable content", "amount" to "Please enter amount", "website" to "Please enter website", "domain" to "Please enter domain", "checkConsole" to "Check console"))
val form__1: UTSJSONObject = _uO("title" to "TForm Component", "description" to "Dynamic form with multiple form controls", "sections" to _uO("basic" to "Basic Usage", "full" to "Full Form (All Component Types)", "customButtons" to "Custom Buttons", "customSlot" to "Custom Slot"), "labels" to _uO("username" to "Username", "password" to "Password", "name" to "Name", "age" to "Age", "bio" to "Bio", "city" to "City", "birthday" to "Birthday", "gender" to "Gender", "hobbies" to "Hobbies", "notify" to "Notification", "rating" to "Rating", "volume" to "Volume", "email" to "Email", "phone" to "Phone", "nickname" to "Nickname", "avatar" to "Avatar"), "placeholders" to _uO("username" to "Please enter username", "password" to "Please enter password", "name" to "Please enter name", "age" to "Please enter age", "bio" to "Please enter bio", "email" to "Please enter email", "phone" to "Please enter phone", "nickname" to "Please enter nickname"), "options" to _uO("beijing" to "Beijing", "shanghai" to "Shanghai", "guangzhou" to "Guangzhou", "shenzhen" to "Shenzhen", "male" to "Male", "female" to "Female", "reading" to "Reading", "sports" to "Sports", "music" to "Music"), "buttons" to _uO("submit" to "Submit", "reset" to "Reset", "validate" to "Validate", "getData" to "Get Data", "uploadAvatar" to "Upload Avatar"), "messages" to _uO("submitSuccess" to "Submit successful", "reset" to "Reset", "validateSuccess" to "Validation passed", "validateFail" to "Validation failed", "uploadSuccess" to "Upload successful", "formData" to "Form Data"))
val list__1: UTSJSONObject = _uO("title" to "TList Component", "description" to "Display list data", "sections" to _uO("basic" to "Basic List", "withDescription" to "List with Description", "withIcon" to "List with Icon", "withExtra" to "With Extra Content", "clickable" to "Clickable List", "customSpacing" to "Custom Spacing", "withTitle" to "List with Title"), "items" to _uO("item1" to "List Item 1", "item2" to "List Item 2", "item3" to "List Item 3", "titleText" to "Title Text", "description1" to "This is the description for the first item", "anotherTitle" to "Another Title", "description2" to "This is the description for the second item", "settings" to "Settings", "notification" to "Notification", "privacy" to "Privacy", "about" to "About", "balance" to "Balance", "coupon" to "Coupon", "points" to "Points", "clickable1" to "Clickable Item 1", "clickable2" to "Clickable Item 2", "disabled" to "Disabled Item", "noSpacing1" to "No Spacing Item 1", "noSpacing2" to "No Spacing Item 2", "largeSpacing1" to "Large Spacing Item 1", "largeSpacing2" to "Large Spacing Item 2", "systemSettings" to "System Settings", "general" to "General", "accountSecurity" to "Account & Security"), "extra" to _uO("balance" to "¥1,234.56", "couponCount" to "3 coupons", "pointsCount" to "1,234"))
val dialog__1: UTSJSONObject = _uO("title" to "TDialog Component", "description" to "Modal dialog", "sections" to _uO("basic" to "Basic Usage", "confirm" to "Confirm Dialog", "custom" to "Custom Content"), "buttons" to _uO("show" to "Show Dialog", "confirm" to "Confirm", "cancel" to "Cancel"), "content" to _uO("title" to "Tip", "message" to "This is a message"))
val toast__1: UTSJSONObject = _uO("title" to "TToast Component", "description" to "Lightweight prompt", "sections" to _uO("basic" to "Basic Usage", "type" to "Toast Type", "duration" to "Duration"), "buttons" to _uO("show" to "Show Toast", "success" to "Success", "error" to "Error", "warning" to "Warning", "loading" to "Loading"), "messages" to _uO("text" to "This is a text message", "success" to "Operation successful", "fail" to "Operation failed", "loading" to "Loading...", "loadComplete" to "Load complete"))
val loading__4: UTSJSONObject = _uO("title" to "TLoading Component", "description" to "Loading state", "sections" to _uO("basic" to "Basic Usage", "type" to "Loading Type", "text" to "Loading Text"), "text" to _uO("loading" to "Loading..."))
val empty__1: UTSJSONObject = _uO("title" to "TEmpty Component", "description" to "Empty state display", "sections" to _uO("basic" to "Basic Usage", "custom" to "Custom Content"), "text" to _uO("noData" to "No Data", "noResult" to "No Search Results"))
val picker__1: UTSJSONObject = _uO("title" to "TPicker Component", "description" to "Multi-column data picker", "sections" to _uO("basic" to "Basic Usage", "multiColumn" to "Multi Column", "cascade" to "Cascade"), "buttons" to _uO("confirm" to "Confirm", "cancel" to "Cancel"))
val actionSheet__1: UTSJSONObject = _uO("title" to "TActionSheet Component", "description" to "Bottom action sheet", "sections" to _uO("basic" to "Basic Usage", "withTitle" to "With Title & Description", "customColor" to "Custom Color"), "buttons" to _uO("show" to "Open Action Sheet", "cancel" to "Cancel"), "actions" to _uO("option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "shareToFriend" to "Share to Friend", "shareToMoments" to "Share to Moments", "copyLink" to "Copy Link", "delete" to "Delete"), "dialog" to _uO("title" to "Action Selection", "description" to "Please select the action you want to perform"), "messages" to _uO("selected" to "Selected"))
val checkbox__1: UTSJSONObject = _uO("title" to "TCheckbox Component", "description" to "Checkbox component", "sections" to _uO("basic" to "Basic Usage", "defaultChecked" to "Default Checked", "group" to "Checkbox Group", "disabled" to "Disabled State", "size" to "Different Sizes", "customColor" to "Custom Color", "indeterminate" to "Indeterminate State", "events" to "Event Handling"), "labels" to _uO("checkbox" to "Checkbox", "defaultChecked" to "Default checked checkbox", "option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "disabledUnchecked" to "Disabled unchecked", "disabledChecked" to "Disabled checked", "small" to "Small size", "medium" to "Medium size", "large" to "Large size", "red" to "Red checkbox", "green" to "Green checkbox", "orange" to "Orange checkbox", "indeterminate" to "Indeterminate state", "clickToConsole" to "Click to view console"), "status" to _uO("checked" to "Checked status", "selected" to "Selected", "notSelected" to "Not selected", "indeterminateDesc" to "Used to indicate partial selection", "checkConsole" to "Toggle checkbox to view console"))
val cell__1: UTSJSONObject = _uO("title" to "TCell Component", "description" to "Cell component", "sections" to _uO("basic" to "Basic Usage", "withIcon" to "With Icon", "withValue" to "With Value", "link" to "Link Cell"), "groups" to _uO("showArrow" to "Show Arrow", "clickableCell" to "Clickable Cell", "customSize" to "Custom Size", "customContent" to "Custom Content", "group" to "Cell Group", "required" to "Required Field"), "labels" to _uO("cell" to "Cell", "content" to "Content", "description" to "Description", "settings" to "Settings", "message" to "Message", "notification" to "Notification", "enabled" to "Enabled", "personalInfo" to "Personal Info", "systemSettings" to "System Settings", "enter" to "Enter", "aboutUs" to "About Us", "clickable" to "Clickable", "notClickable" to "Not Clickable", "largeCell" to "Large Cell", "defaultCell" to "Default Cell", "smallCell" to "Small Cell", "customTitle" to "Custom Title", "accountManagement" to "Account Management", "privacySettings" to "Privacy Settings", "notificationSettings" to "Notification Settings", "basicSettings" to "Basic Settings", "clearCache" to "Clear Cache", "logout" to "Logout", "other" to "Other", "username" to "Username", "phone" to "Phone", "pleaseEnter" to "Please Enter", "email" to "Email", "optional" to "Optional"))
val grid__1: UTSJSONObject = _uO("title" to "TGrid Component", "description" to "Grid layout", "sections" to _uO("basic" to "Basic Usage", "custom" to "Custom Columns", "customGap" to "Custom Gap", "border" to "With Border", "square" to "Square Grid", "clickable" to "Clickable", "customColor" to "Custom Icon Color", "badge" to "With Badge", "image" to "With Image", "customContent" to "Custom Content"), "labels" to _uO("home" to "Home", "mail" to "Mail", "favorite" to "Favorite", "settings" to "Settings", "phone" to "Phone", "bulb" to "Bulb", "palette" to "Palette", "camera" to "Camera", "red" to "Red", "blue" to "Blue", "green" to "Green", "yellow" to "Yellow", "image1" to "Image 1", "image2" to "Image 2", "image3" to "Image 3", "custom" to "Custom", "customDesc" to "Custom Content", "normalGrid" to "Normal Grid", "blueColor" to "Blue", "background" to "Background", "fast" to "Fast"))
val layout__1: UTSJSONObject = _uO("title" to "TRow/TCol Component", "description" to "24-column grid layout", "sections" to _uO("basic" to "Basic Usage", "gutter" to "With Gutter", "offset" to "Column Offset", "align" to "Horizontal Alignment", "verticalAlign" to "Vertical Alignment", "responsive" to "Responsive"), "labels" to _uO("leftAlign" to "Left Align", "centerAlign" to "Center Align", "rightAlign" to "Right Align", "spaceBetween" to "Space Between", "spaceAround" to "Space Around", "topAlign" to "Top Align", "middleAlign" to "Middle Align", "bottomAlign" to "Bottom Align", "height" to "Height"))
val textarea__1: UTSJSONObject = _uO("title" to "TTextarea Component", "description" to "Multi-line text input", "sections" to _uO("basic" to "Basic Usage", "disabled" to "Disabled State", "maxlength" to "Character Count", "autoHeight" to "Auto Height", "clearable" to "Clearable", "customRows" to "Custom Rows", "complete" to "Complete Example"), "placeholder" to "Please enter content", "labels" to _uO("disabled" to "Disabled state", "limit100" to "Max 100 characters", "autoGrow" to "Auto grow height", "clearable" to "Clearable content", "rows3" to "3 Rows", "rows3Height" to "Fixed 3 rows height", "rows5" to "5 Rows", "rows5Height" to "Fixed 5 rows height", "feedback" to "Please enter your feedback"))
val error__3: UTSJSONObject = _uO("title" to "TErrorState Component", "description" to "Error prompt", "sections" to _uO("404" to "404 Error", "500" to "500 Error", "basic" to "Basic Usage", "custom" to "Custom Content", "network" to "Network Error", "withButton" to "With Button", "customIcon" to "Custom Icon", "customSlot" to "Custom Slot"), "text" to _uO("error" to "Error occurred", "retry" to "Retry", "networkError" to "Network connection failed", "404Title" to "Page Not Found", "404Message" to "Sorry, the page you are looking for does not exist", "500Title" to "Server Error", "500Message" to "Sorry, the server encountered an error", "networkTitle" to "Network Error", "networkMessage" to "Please check your network connection", "loadFailed" to "Load Failed", "loadFailedMessage" to "Failed to load data, please try again", "reload" to "Reload", "operationFailed" to "Operation Failed", "tryAgain" to "Please try again later", "errorOccurred" to "An Error Occurred", "customError" to "This is a custom error message", "back" to "Back"))
val radio__1: UTSJSONObject = _uO("title" to "TRadioButton Component", "description" to "Radio button component", "sections" to _uO("basic" to "Basic Usage", "group" to "Radio Group", "disabled" to "Disabled State", "size" to "Different Sizes", "customColor" to "Custom Color", "events" to "Event Handling"), "labels" to _uO("option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "optionA" to "Option A", "optionB" to "Option B", "optionC" to "Option C", "optionD" to "Option D", "disabledUnchecked" to "Disabled unchecked", "disabledChecked" to "Disabled checked", "small" to "Small size", "medium" to "Medium size", "large" to "Large size", "red" to "Red", "green" to "Green", "orange" to "Orange"), "status" to _uO("selected" to "Selected", "notSelected" to "Not selected", "checkConsole" to "Toggle option to view console"))
val searchBar__1: UTSJSONObject = _uO("title" to "TSearchBar Component", "description" to "Search input box", "sections" to _uO("basic" to "Basic Usage", "round" to "Round Style", "showCancel" to "Show Cancel Button", "customBackground" to "Custom Background", "disabled" to "Disabled State", "customIcon" to "Custom Icon", "maxlength" to "Max Length"), "placeholder" to _uO("default" to "Please enter search content", "round" to "Round search box", "background" to "Light blue background", "disabled" to "Disabled state", "customIcon" to "Custom search icon", "maxlength" to "Max 20 characters"), "buttons" to _uO("cancel" to "Cancel"), "messages" to _uO("search" to "Search", "cancelled" to "Cancelled"))
val numberInput__1: UTSJSONObject = _uO("title" to "TNumberInput Component", "description" to "Enhanced component for number input", "sections" to _uO("basic" to "Basic Usage", "range" to "Set Range", "step" to "Step Setting", "precision" to "Precision Control", "size" to "Different Sizes", "disabled" to "Disabled State", "events" to "Event Handling"), "labels" to _uO("currentValue" to "Current value", "range" to "Range", "step" to "Step", "integer" to "Integer", "decimal" to "2 decimal places", "checkConsole" to "Check console for value changes"))
val select__1: UTSJSONObject = _uO("title" to "TSelect Component", "description" to "Dropdown selector component", "sections" to _uO("basic" to "Basic Usage", "upward" to "Upward Expand", "default" to "Default Value", "disabled" to "Disabled State", "disabledOptions" to "Disabled Options", "clearable" to "Clearable", "filterable" to "Filterable", "multiple" to "Multiple Selection", "size" to "Different Sizes", "events" to "Event Handling"), "labels" to _uO("option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "option4" to "Option 4", "option5" to "Option 5", "item1" to "Item 1", "item2" to "Item 2", "item3" to "Item 3", "available1" to "Available Option 1", "disabled" to "Disabled Option", "available2" to "Available Option 2", "available3" to "Available Option 3", "small" to "Small Size", "medium" to "Medium Size", "large" to "Large Size"), "placeholder" to _uO("default" to "Please select", "upward" to "Upward expand", "disabled" to "Disabled state", "partialDisabled" to "Partial options disabled", "clearable" to "Clearable selection", "filterable" to "Filterable options", "multiple" to "Please select multiple options", "checkConsole" to "Select to view console"), "messages" to _uO("selected" to "Selected value", "notSelected" to "Not selected", "upwardDesc" to "Set placement=\"top\" to expand dropdown upward"))
val slider__1: UTSJSONObject = _uO("title" to "TSlider Component", "description" to "Slider selector", "sections" to _uO("basic" to "Basic Usage", "showValue" to "Show Current Value", "customColor" to "Custom Color", "step" to "Set Step", "disabled" to "Disabled State"))
val rate__1: UTSJSONObject = _uO("title" to "TRate Component", "description" to "Rating component", "sections" to _uO("basic" to "Basic Usage", "halfStar" to "Half Star", "readonly" to "Read Only", "customIcon" to "Custom Icon"))
val tags__1: UTSJSONObject = _uO("title" to "Tags Component", "description" to "Tag component for marking and classification", "sections" to _uO("basic" to "Basic Usage", "card" to "Card Type", "line" to "Line Type", "size" to "Different Sizes", "badge" to "With Badge", "disabled" to "Disabled State", "bottom" to "Bottom Tag Bar", "customColor" to "Custom Color", "centered" to "Centered Display", "noAnimation" to "Disable Animation", "usage" to "Usage Instructions"), "labels" to _uO("home" to "Home", "product" to "Product", "service" to "Service", "about" to "About", "all" to "All", "ongoing" to "Ongoing", "finished" to "Finished", "tab1" to "Option 1", "tab2" to "Option 2", "tab3" to "Option 3", "message" to "Message", "notification" to "Notification", "todo" to "Todo", "enabled1" to "Enabled Option 1", "disabled" to "Disabled Option", "enabled2" to "Enabled Option 2", "discover" to "Discover", "profile" to "Profile", "redTheme" to "Red Theme", "greenTheme" to "Green Theme", "blueTheme" to "Blue Theme"), "content" to _uO("currentKey" to "Current active Key", "home" to "Home content area", "product" to "Product display area", "service" to "Service introduction area", "about" to "About us area", "currentSelected" to "Current selected", "allTasks" to "All Tasks", "allTasksDesc" to "Display all task list", "ongoing" to "Ongoing", "ongoingDesc" to "Display ongoing tasks", "finished" to "Finished", "finishedDesc" to "Display finished tasks", "current" to "Current", "messageCenter" to "Message Center", "unreadMessages" to "You have 5 unread messages", "notifications" to "Notifications", "notificationCount" to "You have 99+ notifications", "todoList" to "Todo List", "newTodo" to "You have new todo items", "disabledDesc" to "The middle option is disabled and cannot be clicked", "homeContent" to "Home", "bottomNavDesc" to "Tab bar displayed at bottom, suitable for bottom navigation", "discoverContent" to "Discover page content", "profileContent" to "Profile content", "customColorTheme" to "Custom color theme", "redTheme" to "Active color is red (#e74c3c)", "centered" to "Centered display", "noAnimation" to "No animation effect"), "usage" to _uO("props" to "Main Properties", "vModel" to "• v-model: Bind current active tag", "items" to "• items: Tag data array", "type" to "• type: Tag type (line | card)", "size" to "• size: Tag size (small | medium | large)", "tabPosition" to "• tabPosition: Tag position (top | bottom)", "scrollable" to "• scrollable: Whether scrollable", "centered" to "• centered: Whether centered", "activeColor" to "• activeColor: Active color", "inactiveColor" to "• inactiveColor: Inactive color", "animated" to "• animated: Whether to show animation", "events" to "Main Events", "change" to "• @change: Triggered when tag switches", "tabClick" to "• @tab-click: Triggered when tag is clicked", "slots" to "Slots", "default" to "• default: Content area, receives activeKey parameter", "tabItem" to "TabItem Type", "key" to "• key: Unique identifier (required)", "label" to "• label: Tag text (required)", "disabled" to "• disabled: Whether disabled", "badge" to "• badge: Badge content", "icon" to "• icon: Icon path"))
val badge__1: UTSJSONObject = _uO("title" to "TBadge Component", "description" to "Display number or status badge", "sections" to _uO("basic" to "Basic Usage", "max" to "Max Value", "dot" to "Dot Badge", "type" to "Different Types", "customColor" to "Custom Color", "offset" to "Offset", "dynamic" to "Dynamic Control"), "labels" to _uO("comment" to "Comment", "reply" to "Reply", "message" to "Message", "warning" to "Warning", "online" to "Online", "offline" to "Offline", "primary" to "Primary", "danger" to "Danger", "success" to "Success", "info" to "Info", "bgColor" to "BgColor", "blue" to "Blue", "green" to "Green", "offset" to "Offset", "dynamic" to "Dynamic"), "buttons" to _uO("increase" to "Increase", "decrease" to "Decrease", "show" to "Show", "hide" to "Hide"))
val avatar__1: UTSJSONObject = _uO("title" to "TAvatar Component", "description" to "Display user avatar or icon", "sections" to _uO("basic" to "Basic Usage", "size" to "Different Sizes", "shape" to "Different Shapes", "customColor" to "Custom Color", "fallback" to "Fallback Image", "fit" to "Image Fit Mode", "events" to "With Events", "customContent" to "Custom Content"), "messages" to _uO("clicked" to "Avatar clicked", "error" to "Image load failed", "clickOrConsole" to "Click avatar or check console log"))
val progress__1: UTSJSONObject = _uO("title" to "TProgress Component", "description" to "Progress indicator", "sections" to _uO("basic" to "Basic Usage", "type" to "Progress Type", "status" to "Progress Status", "custom" to "Custom Color"))
val noticeBar__1: UTSJSONObject = _uO("title" to "TNoticeBar Component", "description" to "Scrolling notice", "sections" to _uO("basic" to "Basic Usage", "closable" to "Closable", "link" to "Link Mode", "scrollable" to "Scrollable", "customColor" to "Custom Color"), "text" to _uO("notice" to "This is a notice message", "closable" to "This is a closable notice message", "link" to "Click for details", "scrollable" to "This is a long notice message that will automatically scroll for users to view the complete content", "customColor" to "Custom color notice bar", "danger" to "Danger alert message"))
val collapse__1: UTSJSONObject = _uO("title" to "Collapse Component", "description" to "Collapsible content area", "sections" to _uO("basic" to "Basic Usage", "accordion" to "Accordion Mode", "disabled" to "Disabled State", "customTitle" to "Custom Title", "noBorder" to "No Border", "currentState" to "Current State"), "titles" to _uO("consistency" to "Consistency", "feedback" to "Feedback", "efficiency" to "Efficiency", "designPrinciples" to "Design Principles", "navigation" to "Navigation", "feedbackTitle" to "Feedback", "normalItem" to "Normal Item", "disabledItem" to "Disabled Item", "mobileComponents" to "Mobile Components", "richComponents" to "Rich Components", "highPerformance" to "High Performance", "item1" to "Item 1", "item2" to "Item 2"), "content" to _uO("consistency" to "Consistent with real life: Keep consistent with real-life processes and logic, follow user habits in language and concepts.", "feedback" to "Control feedback: Let users clearly perceive their operations through interface styles and interactive effects.", "efficiency" to "Simplify process: Design simple and intuitive operation processes to help users complete goals quickly.", "designPrinciples" to "Consistency, feedback, efficiency, and controllability are our design principles.", "navigation" to "Navigation can solve the problems of where users are, where to go, and how to get there when visiting pages.", "feedbackContent" to "Feedback is a response to user behavior, giving users an explanation.", "normalItem" to "This is a normal collapse item that can be expanded and collapsed normally.", "disabledItem" to "This is a disabled collapse item that cannot be expanded.", "mobileComponents" to "Lightweight UI component library designed for mobile.", "richComponents" to "Provides rich basic components covering various mobile scenarios.", "highPerformance" to "Excellent performance, lightweight implementation, ensuring smooth experience.", "item1Content" to "This is the content of item 1.", "item2Content" to "This is the content of item 2."), "labels" to _uO("expandedItems" to "Expanded items", "accordionExpanded" to "Accordion expanded", "none" to "None"))
val swiper__1: UTSJSONObject = _uO("title" to "Swiper Component", "description" to "For looping images or content", "sections" to _uO("basic" to "Basic Usage", "customIndicator" to "Custom Indicator Color", "numberIndicator" to "Number Indicator", "topIndicator" to "Top Indicator", "vertical" to "Vertical Swiper", "noAutoplay" to "Disable Autoplay", "noIndicator" to "No Indicator", "customHeight" to "Custom Height and Interval"), "labels" to _uO("carousel1" to "Carousel 1", "carousel2" to "Carousel 2", "carousel3" to "Carousel 3", "carousel4" to "Carousel 4", "currentIndex" to "Current Index", "manualSwipe" to "Manual swipe to switch", "heightDesc" to "Height 150px, interval 5s, animation 500ms"))
val navbar__1: UTSJSONObject = _uO("title" to "TNavBar Component", "description" to "Top navigation bar", "sections" to _uO("basic" to "Basic Usage", "leftBack" to "Left Back Button", "rightButton" to "Right Button", "bothButtons" to "Both Buttons"), "labels" to _uO("title" to "Title", "back" to "Back", "button" to "Button", "more" to "More"), "messages" to _uO("clickLeft" to "Click left", "clickRight" to "Click right"))
val tabs__1: UTSJSONObject = _uO("title" to "Tabs Component", "description" to "Tab switching component with multiple styles and configurations", "sections" to _uO("basic" to "Basic Usage", "card" to "Card Type", "line" to "Line Type", "size" to "Different Sizes", "badge" to "With Badge", "disabled" to "Disabled State", "bottom" to "Bottom Tab Bar", "customColor" to "Custom Color", "centered" to "Centered Display", "noAnimation" to "Disable Animation", "usage" to "Usage Instructions"), "labels" to _uO("home" to "Home", "profile" to "Profile", "settings" to "Settings", "about" to "About", "user" to "User Info", "order" to "Order Management", "wallet" to "My Wallet", "option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "message" to "Message", "notification" to "Notification", "todo" to "Todo", "enabled1" to "Enabled Option 1", "disabled" to "Disabled Option", "enabled2" to "Enabled Option 2", "discover" to "Discover", "me" to "Me"), "content" to _uO("currentKey" to "Current active Key", "home" to "This is the home content area", "profile" to "This is the profile content area", "settings" to "This is the settings content area", "about" to "This is the about content area", "user" to "User Info", "userPanel" to "Card style user info panel", "order" to "Order Management", "orderPanel" to "Card style order management panel", "wallet" to "My Wallet", "walletPanel" to "Card style wallet panel", "currentSelected" to "Current selected", "messageCenter" to "Message Center", "unreadMessages" to "You have 5 unread messages", "notifications" to "Notifications", "notificationCount" to "You have 99+ notifications", "todoList" to "Todo List", "newTodo" to "You have new todo items", "disabledDesc" to "The middle option is disabled and cannot be clicked", "homeContent" to "Home", "bottomNavDesc" to "Tab bar displayed at bottom, suitable for bottom navigation", "discoverContent" to "Discover page content", "meContent" to "Profile content", "customColorTheme" to "Custom color", "redTheme" to "Active color is red (#ff6b6b)", "centered" to "Centered tabs", "noAnimation" to "No animation effect"), "usage" to _uO("props" to "Main Properties", "vModel" to "• v-model: Bind current active tab", "type" to "• type: Tab type (line | card)", "size" to "• size: Tab size (small | medium | large)", "tabPosition" to "• tabPosition: Tab position (top | bottom)", "scrollable" to "• scrollable: Whether scrollable", "centered" to "• centered: Whether centered", "activeColor" to "• activeColor: Active color", "inactiveColor" to "• inactiveColor: Inactive color", "animated" to "• animated: Whether to show animation", "events" to "Main Events", "change" to "• @change: Triggered when tab switches", "slots" to "Slots", "default" to "• default: Content area, receives activePane and activeIndex"))
val popup__1: UTSJSONObject = _uO("title" to "TPopup Component", "description" to "Popup container for displaying popup content", "sections" to _uO("basic" to "Basic Usage", "position" to "Different Positions"), "buttons" to _uO("bottom" to "Bottom Popup", "top" to "Top Popup", "left" to "Left Popup", "right" to "Right Popup", "center" to "Center Popup"), "content" to _uO("bottom" to "This is popup content", "top" to "Top popup content", "left" to "Left popup content", "right" to "Right popup content", "center" to "Center Popup", "centerDesc" to "This is a center popup content area"))
val checkboxGroup__1: UTSJSONObject = _uO("title" to "TCheckboxGroup Component", "description" to "Checkbox group", "sections" to _uO("basic" to "Basic Usage", "horizontal" to "Horizontal Layout", "disabled" to "Disabled Options", "max" to "Max Selection Limit", "disabledAll" to "Disabled All"), "labels" to _uO("option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "option2Disabled" to "Option 2 (Disabled)"))
val radioGroup__1: UTSJSONObject = _uO("title" to "TRadioGroup Component", "description" to "Radio group", "sections" to _uO("basic" to "Basic Usage", "horizontal" to "Horizontal Layout", "disabled" to "Disabled Options", "disabledAll" to "Disabled All"), "labels" to _uO("option1" to "Option 1", "option2" to "Option 2", "option3" to "Option 3", "option2Disabled" to "Option 2 (Disabled)"))
val default__24: UTSJSONObject = _uO("button" to button__1, "icon" to icon__1, "text" to text__2, "image" to image__1, "divider" to divider__1, "card" to card__1, "input" to input__1, "form" to form__1, "list" to list__1, "dialog" to dialog__1, "toast" to toast__1, "loading" to loading__4, "empty" to empty__1, "picker" to picker__1, "actionSheet" to actionSheet__1, "checkbox" to checkbox__1, "cell" to cell__1, "grid" to grid__1, "layout" to layout__1, "textarea" to textarea__1, "error" to error__3, "radio" to radio__1, "switch" to _uO("title" to "TSwitch Component", "description" to "Toggle between two states", "sections" to _uO("basic" to "Basic Usage", "disabled" to "Disabled State", "size" to "Different Sizes", "customColor" to "Custom Color", "withText" to "With Text", "events" to "Change Event"), "labels" to _uO("status" to "Status", "on" to "On", "off" to "Off", "disabledOff" to "Disabled (Off)", "disabledOn" to "Disabled (On)", "small" to "Small size", "medium" to "Medium size", "large" to "Large size", "customColor" to "Custom color", "openConsole" to "Open console to view event"), "text" to _uO("active" to "On", "inactive" to "Off")), "searchBar" to searchBar__1, "numberInput" to numberInput__1, "select" to select__1, "slider" to slider__1, "rate" to rate__1, "tags" to tags__1, "badge" to badge__1, "avatar" to avatar__1, "progress" to progress__1, "noticeBar" to noticeBar__1, "collapse" to collapse__1, "swiper" to swiper__1, "navbar" to navbar__1, "tabs" to tabs__1, "popup" to popup__1, "checkboxGroup" to checkboxGroup__1, "radioGroup" to radioGroup__1)
val inputPlaceholder__1 = "Please enter {label}"
val selectPlaceholder__1 = "Please select"
val datePlaceholder__1 = "Select date"
val timePlaceholder__1 = "Select time"
val required__1 = "This field is required"
val invalid__1 = "Invalid format"
val minLength__1 = "Length must be at least {min} characters"
val maxLength__1 = "Length must not exceed {max} characters"
val email__1 = "Please enter a valid email address"
val phone__1 = "Please enter a valid phone number"
val url__1 = "Please enter a valid URL"
val requiredError__1 = "Please enter {label}"
val submitButton__1 = "Submit"
val resetButton__1 = "Reset"
val validationFailed__1 = "Form validation failed"
val submitFailed__1 = "Form submission failed"
val noOptions__1 = "No options"
val selectAll__1 = "Select all"
val clear__1 = "Clear"
val default__25: UTSJSONObject = _uO("inputPlaceholder" to inputPlaceholder__1, "selectPlaceholder" to selectPlaceholder__1, "datePlaceholder" to datePlaceholder__1, "timePlaceholder" to timePlaceholder__1, "required" to required__1, "invalid" to invalid__1, "minLength" to minLength__1, "maxLength" to maxLength__1, "email" to email__1, "phone" to phone__1, "url" to url__1, "requiredError" to requiredError__1, "submitButton" to submitButton__1, "resetButton" to resetButton__1, "validationFailed" to validationFailed__1, "submitFailed" to submitFailed__1, "noOptions" to noOptions__1, "selectAll" to selectAll__1, "clear" to clear__1)
val placeholder__3 = "Please enter"
val default__26: UTSJSONObject = _uO("placeholder" to placeholder__3)
val loadingText__1 = "Loading..."
val finishedText__1 = "No more data"
val errorText__1 = "Load failed, click to retry"
val default__27: UTSJSONObject = _uO("loadingText" to loadingText__1, "finishedText" to finishedText__1, "errorText" to errorText__1)
val text__3 = "Loading..."
val default__28: UTSJSONObject = _uO("text" to text__3)
val backText__1 = "Back"
val title__9 = "Title"
val default__29: UTSJSONObject = _uO("backText" to backText__1, "title" to title__9)
val closeText__1 = "Close"
val default__30: UTSJSONObject = _uO("closeText" to closeText__1)
val title__10 = "Please Select"
val confirmText__4 = "Confirm"
val cancelText__7 = "Cancel"
val default__31: UTSJSONObject = _uO("title" to title__10, "confirmText" to confirmText__4, "cancelText" to cancelText__7)
val placeholder__4 = "Enter search keywords"
val cancelText__8 = "Cancel"
val default__32: UTSJSONObject = _uO("placeholder" to placeholder__4, "cancelText" to cancelText__8)
val placeholder__5 = "Please enter content"
val default__33: UTSJSONObject = _uO("placeholder" to placeholder__5)
val loading__5 = "Loading..."
val success__1 = "Success"
val fail__1 = "Failed"
val info__1 = "Info"
val default__34: UTSJSONObject = _uO("loading" to loading__5, "success" to success__1, "fail" to fail__1, "info" to info__1)
val title__11 = "Select Time"
val confirmText__5 = "Confirm"
val cancelText__9 = "Cancel"
val year__1 = ""
val month__1 = ""
val day__1 = ""
val hour__1 = ""
val minute__1 = ""
val default__35: UTSJSONObject = _uO("title" to title__11, "confirmText" to confirmText__5, "cancelText" to cancelText__9, "year" to year__1, "month" to month__1, "day" to day__1, "hour" to hour__1, "minute" to minute__1)
val cancelText__10 = "取消"
val default__36: UTSJSONObject = _uO("cancelText" to cancelText__10)
val confirm__2 = "確定"
val cancel__2 = "取消"
val ok__2 = "好的"
val close__2 = "關閉"
val save__2 = "儲存"
val edit__2 = "編輯"
val submit__2 = "提交"
val reset__2 = "重置"
val search__2 = "搜尋"
val loading__6 = "載入中..."
val noData__4 = "暫無資料"
val error__4 = "出錯了"
val default__37: UTSJSONObject = _uO("confirm" to confirm__2, "cancel" to cancel__2, "ok" to ok__2, "close" to close__2, "save" to save__2, "delete" to "刪除", "edit" to edit__2, "submit" to submit__2, "reset" to reset__2, "search" to search__2, "loading" to loading__6, "noData" to noData__4, "error" to error__4)
val title__12 = "提示"
val confirmText__6 = "確定"
val cancelText__11 = "取消"
val default__38: UTSJSONObject = _uO("title" to title__12, "confirmText" to confirmText__6, "cancelText" to cancelText__11)
val title__13 = "暫無資料"
val noData__5 = "暫無資料"
val description__4 = "目前沒有相關內容"
val actionText__2 = "重新整理"
val default__39: UTSJSONObject = _uO("title" to title__13, "noData" to noData__5, "description" to description__4, "actionText" to actionText__2)
val title__14 = "出錯了"
val description__5 = "頁面載入失敗，請稍後重試"
val retryText__2 = "重新載入"
val default__40: UTSJSONObject = _uO("title" to title__14, "description" to description__5, "retryText" to retryText__2)
val groups__2: UTSJSONObject = _uO("basic" to "基礎組件", "layout" to "佈局組件", "form" to "表單組件", "display" to "數據展示", "feedback" to "反饋組件", "navigation" to "導航組件", "tools" to "測試工具")
val components__2: UTSJSONObject = _uO("TButton" to _uO("name" to "按鈕", "enName" to "Button", "description" to "按鈕用於觸發操作"), "TIcon" to _uO("name" to "圖標", "enName" to "Icon", "description" to "語義化圖標"), "TText" to _uO("name" to "文本", "enName" to "Text", "description" to "文本展示組件"), "TImage" to _uO("name" to "圖片", "enName" to "Image", "description" to "增強的圖片組件"), "TDivider" to _uO("name" to "分割線", "enName" to "Divider", "description" to "內容分割線"), "TCard" to _uO("name" to "卡片", "enName" to "Card", "description" to "卡片容器"), "TList" to _uO("name" to "列表", "enName" to "List", "description" to "列表展示"), "TCell" to _uO("name" to "單元格", "enName" to "Cell", "description" to "單元格組件"), "TGrid" to _uO("name" to "宮格", "enName" to "Grid", "description" to "宮格佈局"), "TRow/TCol" to _uO("name" to "柵格", "enName" to "Layout", "description" to "24柵格佈局"), "TInput" to _uO("name" to "輸入框", "enName" to "Input", "description" to "文本輸入"), "TTextarea" to _uO("name" to "多行輸入", "enName" to "Textarea", "description" to "多行文本輸入"), "TSearchBar" to _uO("name" to "搜索框", "enName" to "SearchBar", "description" to "搜索輸入框"), "TNumberInput" to _uO("name" to "數字輸入", "enName" to "NumberInput", "description" to "數字輸入框"), "TSwitch" to _uO("name" to "開關", "enName" to "Switch", "description" to "開關選擇器"), "TCheckbox" to _uO("name" to "複選框", "enName" to "Checkbox", "description" to "多選框"), "TCheckboxGroup" to _uO("name" to "複選組", "enName" to "CheckboxGroup", "description" to "複選框組"), "TRadioButton" to _uO("name" to "單選", "enName" to "Radio", "description" to "單選按鈕"), "TRadioGroup" to _uO("name" to "單選組", "enName" to "RadioGroup", "description" to "單選框組"), "TSelect" to _uO("name" to "選擇器", "enName" to "Select", "description" to "下拉選擇"), "TPicker" to _uO("name" to "通用選擇器", "enName" to "Picker", "description" to "多列數據選擇"), "TDateTimePicker" to _uO("name" to "時間選擇器", "enName" to "DateTimePicker", "description" to "日期時間選擇"), "TSlider" to _uO("name" to "滑塊", "enName" to "Slider", "description" to "滑動選擇器"), "TRate" to _uO("name" to "評分", "enName" to "Rate", "description" to "評分組件"), "TForm" to _uO("name" to "表單", "enName" to "Form", "description" to "表單組件"), "Tags" to _uO("name" to "標籤", "enName" to "Tags", "description" to "標記分類"), "TBadge" to _uO("name" to "徽標", "enName" to "Badge", "description" to "數字角標"), "TAvatar" to _uO("name" to "頭像", "enName" to "Avatar", "description" to "用戶頭像"), "TProgress" to _uO("name" to "進度條", "enName" to "Progress", "description" to "進度指示器"), "TNoticeBar" to _uO("name" to "通知欄", "enName" to "NoticeBar", "description" to "滾動通知"), "TCollapse" to _uO("name" to "折疊面板", "enName" to "Collapse", "description" to "可折疊內容區域"), "TSwiper" to _uO("name" to "輪播圖", "enName" to "Swiper", "description" to "圖片/內容輪播"), "TEmpty" to _uO("name" to "空狀態", "enName" to "Empty", "description" to "空數據展示"), "TErrorState" to _uO("name" to "錯誤狀態", "enName" to "Error", "description" to "錯誤提示"), "TLoading" to _uO("name" to "加載", "enName" to "Loading", "description" to "加載狀態"), "TToast" to _uO("name" to "輕提示", "enName" to "Toast", "description" to "輕量級提示"), "TDialog" to _uO("name" to "對話框", "enName" to "Dialog", "description" to "模態對話框"), "TPopup" to _uO("name" to "彈出層", "enName" to "Popup", "description" to "彈出層容器"), "TActionSheet" to _uO("name" to "動作面板", "enName" to "ActionSheet", "description" to "底部動作面板"), "Tabs" to _uO("name" to "標籤頁", "enName" to "Tabs", "description" to "選項卡切換"), "TNavBar" to _uO("name" to "導航欄", "enName" to "NavBar", "description" to "頂部導航欄"), "I18nDemo" to _uO("name" to "多語言測試", "enName" to "I18n", "description" to "國際化多語言功能演示"), "Request" to _uO("name" to "請求封裝", "enName" to "Request", "description" to "HttpRequest 請求封裝、攔截器與響應處理示例"), "Utils" to _uO("name" to "工具方法", "enName" to "Utils", "description" to "常用工具函數與 storage 封裝示例"))
val default__41: UTSJSONObject = _uO("groups" to groups__2, "components" to components__2)
val button__2: UTSJSONObject = _uO("title" to "TButton 按鈕組件", "description" to "按鈕用於觸發操作", "sections" to _uO("basic" to "基礎用法", "plain" to "樸素按鈕", "size" to "按鈕尺寸", "disabled" to "禁用狀態", "loading" to "加載狀態", "round" to "圓角按鈕", "block" to "塊級按鈕"), "buttons" to _uO("primary" to "主要按鈕", "success" to "成功按鈕", "warning" to "警告按鈕", "danger" to "危險按鈕", "info" to "信息按鈕", "plain" to "樸素按鈕", "large" to "大型按鈕", "medium" to "中等按鈕", "small" to "小型按鈕", "disabled" to "禁用按鈕", "loading" to "加載中", "clickToLoad" to "點擊加載", "round" to "圓角按鈕", "block" to "塊級按鈕"), "messages" to _uO("clicked" to "按鈕被點擊"))
val icon__2: UTSJSONObject = _uO("title" to "TIcon 圖標組件", "description" to "語義化圖標", "sections" to _uO("basic" to "基礎用法", "size" to "圖標尺寸", "color" to "圖標顏色"))
val text__4: UTSJSONObject = _uO("title" to "TText 文本組件", "description" to "文本展示組件", "sections" to _uO("basic" to "基礎用法", "type" to "文本類型", "size" to "文本尺寸", "weight" to "文本粗細", "ellipsis" to "文本省略"))
val image__2: UTSJSONObject = _uO("title" to "TImage 圖片組件", "description" to "增強的圖片組件", "sections" to _uO("basic" to "基礎用法", "mode" to "裁剪模式", "loading" to "加載狀態", "error" to "加載失敗"))
val divider__2: UTSJSONObject = _uO("title" to "TDivider 分割線", "description" to "內容分割線", "sections" to _uO("basic" to "基礎用法", "withText" to "帶文字", "dashed" to "虛線", "vertical" to "垂直分割線"))
val card__2: UTSJSONObject = _uO("title" to "TCard 卡片組件", "description" to "卡片容器，用於組織內容", "sections" to _uO("basic" to "基礎卡片", "withSubtitle" to "帶副標題的卡片", "noShadow" to "無陰影卡片", "withBorder" to "帶邊框卡片", "padding" to "不同內邊距", "largePadding" to "大內邊距卡片", "radius" to "不同圓角", "largeRadius" to "大圓角卡片", "clickable" to "可點擊卡片", "customSlot" to "自定義插槽"), "content" to _uO("basic" to "這是卡片的內容區域", "subtitle" to "這是副標題", "anyContent" to "卡片內容可以是任何元素", "noShadow" to "這個卡片沒有陰影效果", "withBorder" to "這個卡片使用邊框而不是陰影", "smallPadding" to "小內邊距卡片", "largePadding" to "大內邊距卡片", "noRadius" to "無圓角卡片", "largeRadius" to "大圓角卡片", "clickable" to "點擊這個卡片會觸發事件", "customHeader" to "自定義頭部", "customSlot" to "這個卡片使用了自定義頭部插槽"), "buttons" to _uO("action" to "操作", "cancel" to "取消", "confirm" to "確定"), "messages" to _uO("clicked" to "卡片被點擊"))
val input__2: UTSJSONObject = _uO("title" to "TInput 輸入框", "description" to "文本輸入", "sections" to _uO("basic" to "基礎用法", "type" to "輸入類型", "clearable" to "可清空", "disabled" to "禁用狀態", "maxlength" to "字數限制"), "placeholder" to "請輸入內容")
val form__2: UTSJSONObject = _uO("title" to "TForm 表單", "description" to "表單組件", "sections" to _uO("basic" to "基礎用法", "validation" to "表單驗證", "layout" to "表單佈局"), "labels" to _uO("username" to "用戶名", "password" to "密碼", "email" to "郵箱", "phone" to "手機號"), "buttons" to _uO("submit" to "提交", "reset" to "重置"))
val list__2: UTSJSONObject = _uO("title" to "TList 列表組件", "description" to "用於展示列表數據", "sections" to _uO("basic" to "基礎列表", "withDescription" to "帶描述的列表", "withIcon" to "帶圖標的列表", "withExtra" to "帶右側內容", "clickable" to "可點擊列表", "customSpacing" to "自定義間距", "withTitle" to "帶標題的列表"), "items" to _uO("item1" to "列表項 1", "item2" to "列表項 2", "item3" to "列表項 3", "titleText" to "標題文字", "anotherTitle" to "另一個標題", "description1" to "這裡是描述信息，可以是多行文字內容", "description2" to "描述信息支持較長的文本內容顯示", "settings" to "設置", "notification" to "通知", "privacy" to "隱私", "about" to "關於", "balance" to "賬戶餘額", "coupon" to "優惠券", "points" to "積分", "clickable1" to "可點擊項 1", "clickable2" to "可點擊項 2", "disabled" to "禁用的項目", "noSpacing1" to "無間距列表項 1", "noSpacing2" to "無間距列表項 2", "largeSpacing1" to "大間距列表項 1", "largeSpacing2" to "大間距列表項 2", "systemSettings" to "系統設置", "general" to "通用", "accountSecurity" to "賬號與安全"), "extra" to _uO("balance" to "¥100.00", "couponCount" to "5張", "pointsCount" to "1000"))
val dialog__2: UTSJSONObject = _uO("title" to "TDialog 對話框", "description" to "模態對話框", "sections" to _uO("basic" to "基礎用法", "confirm" to "確認對話框", "custom" to "自定義內容"), "buttons" to _uO("show" to "顯示對話框", "confirm" to "確認", "cancel" to "取消"), "content" to _uO("title" to "提示", "message" to "這是一條消息"))
val toast__2: UTSJSONObject = _uO("title" to "TToast 輕提示", "description" to "輕量級提示", "sections" to _uO("basic" to "基礎用法", "type" to "提示類型", "duration" to "顯示時長"), "buttons" to _uO("show" to "顯示提示", "success" to "成功提示", "error" to "錯誤提示", "warning" to "警告提示", "loading" to "加載提示"))
val loading__7: UTSJSONObject = _uO("title" to "TLoading 加載", "description" to "加載狀態", "sections" to _uO("basic" to "基礎用法", "type" to "加載類型", "text" to "加載文案"), "text" to _uO("loading" to "加載中..."))
val empty__2: UTSJSONObject = _uO("title" to "TEmpty 空狀態", "description" to "空數據展示", "sections" to _uO("basic" to "基礎用法", "custom" to "自定義內容"), "text" to _uO("noData" to "暫無數據", "noResult" to "暫無搜索結果"))
val picker__2: UTSJSONObject = _uO("title" to "TPicker 選擇器", "description" to "多列數據選擇", "sections" to _uO("basic" to "基礎用法", "multiColumn" to "多列選擇", "cascade" to "級聯選擇"), "buttons" to _uO("confirm" to "確認", "cancel" to "取消"))
val actionSheet__2: UTSJSONObject = _uO("title" to "TActionSheet 動作面板", "description" to "底部動作面板", "sections" to _uO("basic" to "基礎用法", "withTitle" to "帶標題描述", "customColor" to "自定義顏色"), "buttons" to _uO("show" to "打開操作面板", "cancel" to "取消"), "actions" to _uO("option1" to "選項一", "option2" to "選項二", "option3" to "選項三", "shareToFriend" to "分享給朋友", "shareToMoments" to "分享到朋友圈", "copyLink" to "複製鏈接", "delete" to "刪除"), "dialog" to _uO("title" to "操作選擇", "description" to "請選擇您要進行的操作"), "messages" to _uO("selected" to "選擇了"))
val checkbox__2: UTSJSONObject = _uO("title" to "TCheckbox 複選框組件", "description" to "多選框組件", "sections" to _uO("basic" to "基礎用法", "defaultChecked" to "默認選中", "group" to "複選框組", "disabled" to "禁用狀態", "size" to "不同大小", "customColor" to "自定義顏色", "indeterminate" to "中間狀態", "events" to "事件處理"), "labels" to _uO("checkbox" to "複選框", "defaultChecked" to "默認選中的複選框", "option1" to "選項一", "option2" to "選項二", "option3" to "選項三", "disabledUnchecked" to "禁用未選中", "disabledChecked" to "禁用已選中", "small" to "小尺寸", "medium" to "中等尺寸", "large" to "大尺寸", "red" to "紅色複選框", "green" to "綠色複選框", "orange" to "橙色複選框", "indeterminate" to "半選狀態", "clickToConsole" to "點擊查看控制台"), "status" to _uO("checked" to "選中狀態", "selected" to "選中值", "notSelected" to "未選擇", "indeterminateDesc" to "用於表示部分選中狀態", "checkConsole" to "切換複選框查看控制台"))
val radio__2: UTSJSONObject = _uO("title" to "TRadioButton 單選組件", "description" to "單選按鈕組件", "sections" to _uO("basic" to "基礎用法", "group" to "單選組", "disabled" to "禁用狀態", "size" to "不同大小", "customColor" to "自定義顏色", "events" to "事件處理"), "labels" to _uO("option1" to "選項一", "option2" to "選項二", "option3" to "選項三", "optionA" to "選項 A", "optionB" to "選項 B", "optionC" to "選項 C", "optionD" to "選項 D", "disabledUnchecked" to "禁用未選中", "disabledChecked" to "禁用已選中", "small" to "小尺寸", "medium" to "中等尺寸", "large" to "大尺寸", "red" to "紅色", "green" to "綠色", "orange" to "橙色"), "status" to _uO("selected" to "選中值", "notSelected" to "未選擇", "checkConsole" to "切換選項查看控制台"))
val textarea__2: UTSJSONObject = _uO("title" to "TTextarea 多行輸入", "description" to "多行文本輸入", "sections" to _uO("basic" to "基礎用法", "disabled" to "禁用狀態", "maxlength" to "字數統計", "autoHeight" to "自動高度", "clearable" to "顯示清空按鈕", "customRows" to "自定義行數", "complete" to "完整示例"), "placeholder" to "請輸入內容", "labels" to _uO("disabled" to "禁用狀態", "limit100" to "最多輸入100個字符", "autoGrow" to "自動增長高度", "clearable" to "可清空內容", "rows3" to "3行", "rows3Height" to "固定3行高度", "rows5" to "5行", "rows5Height" to "固定5行高度", "feedback" to "請輸入您的反饋意見"))
val searchBar__2: UTSJSONObject = _uO("title" to "TSearchBar 搜索框", "description" to "搜索輸入框", "sections" to _uO("basic" to "基礎用法", "withButton" to "帶按鈕", "custom" to "自定義樣式"), "placeholder" to "搜索", "buttons" to _uO("search" to "搜索", "cancel" to "取消"))
val numberInput__2: UTSJSONObject = _uO("title" to "TNumberInput 數字輸入", "description" to "數字輸入框", "sections" to _uO("basic" to "基礎用法", "minMax" to "最小/最大值", "step" to "步長", "disabled" to "禁用狀態"))
val select__2: UTSJSONObject = _uO("title" to "TSelect 選擇器", "description" to "下拉選擇", "sections" to _uO("basic" to "基礎用法", "multiple" to "多選", "disabled" to "禁用狀態"), "placeholder" to "請選擇")
val slider__2: UTSJSONObject = _uO("title" to "TSlider 滑塊", "description" to "滑動選擇器", "sections" to _uO("basic" to "基礎用法", "range" to "範圍選擇", "step" to "步長", "disabled" to "禁用狀態"))
val rate__2: UTSJSONObject = _uO("title" to "TRate 評分", "description" to "評分組件", "sections" to _uO("basic" to "基礎用法", "halfStar" to "半星", "readonly" to "只讀", "customIcon" to "自定義圖標"))
val tags__2: UTSJSONObject = _uO("title" to "Tags 標籤", "description" to "標記分類", "sections" to _uO("basic" to "基礎用法", "type" to "標籤類型", "size" to "標籤尺寸", "closable" to "可關閉"))
val badge__2: UTSJSONObject = _uO("title" to "TBadge 徽標組件", "description" to "用於顯示數字或狀態標記", "sections" to _uO("basic" to "基礎用法", "max" to "最大值", "dot" to "小圓點", "type" to "不同類型", "customColor" to "自定義顏色", "offset" to "偏移量", "dynamic" to "動態控制"), "labels" to _uO("comment" to "評論", "reply" to "回覆", "message" to "消息", "warning" to "警告", "online" to "在線", "offline" to "離線", "primary" to "主要", "danger" to "危險", "success" to "成功", "info" to "信息", "bgColor" to "背景色", "blue" to "藍色", "green" to "綠色", "offset" to "偏移", "dynamic" to "動態"), "buttons" to _uO("increase" to "增加", "decrease" to "減少", "show" to "顯示", "hide" to "隱藏"))
val avatar__2: UTSJSONObject = _uO("title" to "TAvatar 頭像組件", "description" to "用於展示用戶頭像或圖標", "sections" to _uO("basic" to "基礎用法", "size" to "不同尺寸", "shape" to "不同形狀", "customColor" to "自定義顏色", "fallback" to "加載失敗兜底圖", "fit" to "圖片填充模式", "events" to "帶事件", "customContent" to "自定義內容"), "messages" to _uO("clicked" to "頭像被點擊", "error" to "圖片加載失敗", "clickOrConsole" to "點擊頭像或查看控制台日誌"))
val progress__2: UTSJSONObject = _uO("title" to "TProgress 進度條", "description" to "進度指示器", "sections" to _uO("basic" to "基礎用法", "type" to "進度條類型", "status" to "進度條狀態", "custom" to "自定義顏色"))
val noticeBar__2: UTSJSONObject = _uO("title" to "TNoticeBar 通知欄", "description" to "滾動通知", "sections" to _uO("basic" to "基礎用法", "scrollable" to "滾動播放", "closable" to "可關閉", "withIcon" to "帶圖標"))
val collapse__2: UTSJSONObject = _uO("title" to "TCollapse 折疊面板", "description" to "可折疊內容區域", "sections" to _uO("basic" to "基礎用法", "accordion" to "手風琴模式", "custom" to "自定義內容"))
val swiper__2: UTSJSONObject = _uO("title" to "TSwiper 輪播圖", "description" to "圖片/內容輪播", "sections" to _uO("basic" to "基礎用法", "autoplay" to "自動播放", "vertical" to "垂直方向", "custom" to "自定義指示器"))
val error__5: UTSJSONObject = _uO("title" to "TErrorState 錯誤狀態", "description" to "錯誤提示", "sections" to _uO("basic" to "基礎用法", "custom" to "自定義內容"), "text" to _uO("error" to "發生錯誤", "retry" to "重試"))
val navbar__2: UTSJSONObject = _uO("title" to "TNavBar 導航欄", "description" to "頂部導航欄", "sections" to _uO("basic" to "基礎用法", "withIcon" to "帶圖標", "custom" to "自定義內容"), "text" to _uO("title" to "標題", "back" to "返回"))
val tabs__2: UTSJSONObject = _uO("title" to "Tabs 標籤頁", "description" to "選項卡切換", "sections" to _uO("basic" to "基礎用法", "card" to "卡片風格", "line" to "橫線風格", "scrollable" to "可滾動"))
val popup__2: UTSJSONObject = _uO("title" to "TPopup 彈出層", "description" to "彈出層容器", "sections" to _uO("basic" to "基礎用法", "position" to "彈出位置", "custom" to "自定義內容"), "buttons" to _uO("show" to "顯示彈出層"))
val cell__2: UTSJSONObject = _uO("title" to "TCell 單元格", "description" to "單元格組件", "sections" to _uO("basic" to "基礎用法", "withIcon" to "帶圖標", "withValue" to "帶值", "link" to "鏈接單元格"))
val grid__2: UTSJSONObject = _uO("title" to "TGrid 宮格", "description" to "宮格佈局", "sections" to _uO("basic" to "基礎用法", "custom" to "自定義列數", "customGap" to "自定義間距", "border" to "顯示邊框", "square" to "正方形格子", "clickable" to "可點擊", "customColor" to "自定義圖標顏色", "badge" to "徽標提示", "image" to "使用圖片", "customContent" to "自定義內容"), "labels" to _uO("home" to "首頁", "mail" to "郵件", "favorite" to "收藏", "settings" to "設置", "phone" to "電話", "bulb" to "燈泡", "palette" to "調色板", "camera" to "相機", "red" to "紅色", "blue" to "藍色", "green" to "綠色", "yellow" to "黃色", "image1" to "圖片1", "image2" to "圖片2", "image3" to "圖片3", "custom" to "自定義", "customDesc" to "自定義內容", "normalGrid" to "普通宮格", "blueColor" to "藍色", "background" to "背景色", "fast" to "快速"))
val layout__2: UTSJSONObject = _uO("title" to "TRow/TCol 柵格", "description" to "24柵格佈局", "sections" to _uO("basic" to "基礎用法", "gutter" to "區塊間隔", "offset" to "列偏移", "align" to "水平對齊", "verticalAlign" to "垂直對齊", "responsive" to "響應式"), "labels" to _uO("leftAlign" to "左對齊", "centerAlign" to "居中對齊", "rightAlign" to "右對齊", "spaceBetween" to "兩端對齊", "spaceAround" to "分散對齊", "topAlign" to "頂部對齊", "middleAlign" to "居中對齊", "bottomAlign" to "底部對齊", "height" to "高度"))
val checkboxGroup__2: UTSJSONObject = _uO("title" to "TCheckboxGroup 複選組", "description" to "複選框組", "sections" to _uO("basic" to "基礎用法", "disabled" to "禁用狀態", "max" to "最大選擇數"))
val radioGroup__2: UTSJSONObject = _uO("title" to "TRadioGroup 單選組", "description" to "單選框組", "sections" to _uO("basic" to "基礎用法", "disabled" to "禁用狀態", "button" to "按鈕樣式"))
val default__42: UTSJSONObject = _uO("button" to button__2, "icon" to icon__2, "text" to text__4, "image" to image__2, "divider" to divider__2, "card" to card__2, "input" to input__2, "form" to form__2, "list" to list__2, "dialog" to dialog__2, "toast" to toast__2, "loading" to loading__7, "empty" to empty__2, "picker" to picker__2, "actionSheet" to actionSheet__2, "checkbox" to checkbox__2, "radio" to radio__2, "switch" to _uO("title" to "TSwitch 開關組件", "description" to "表示兩種狀態之間的切換", "sections" to _uO("basic" to "基礎用法", "disabled" to "禁用狀態", "size" to "不同尺寸", "customColor" to "自定義顏色", "withText" to "帶文字提示", "events" to "change 事件"), "labels" to _uO("status" to "狀態", "on" to "開", "off" to "關", "disabledOff" to "禁用狀態（關）", "disabledOn" to "禁用狀態（開）", "small" to "小尺寸", "medium" to "中尺寸", "large" to "大尺寸", "customColor" to "自定義顏色", "openConsole" to "打開控制台查看事件"), "text" to _uO("active" to "開啟", "inactive" to "關閉")), "textarea" to textarea__2, "searchBar" to searchBar__2, "numberInput" to numberInput__2, "select" to select__2, "slider" to slider__2, "rate" to rate__2, "tags" to tags__2, "badge" to badge__2, "avatar" to avatar__2, "progress" to progress__2, "noticeBar" to noticeBar__2, "collapse" to collapse__2, "swiper" to swiper__2, "error" to error__5, "navbar" to navbar__2, "tabs" to tabs__2, "popup" to popup__2, "cell" to cell__2, "grid" to grid__2, "layout" to layout__2, "checkboxGroup" to checkboxGroup__2, "radioGroup" to radioGroup__2)
val inputPlaceholder__2 = "請輸入{label}"
val selectPlaceholder__2 = "請選擇"
val datePlaceholder__2 = "請選擇日期"
val timePlaceholder__2 = "請選擇時間"
val required__2 = "此項為必填項"
val invalid__2 = "格式不正確"
val minLength__2 = "長度不能少於{min}個字符"
val maxLength__2 = "長度不能超過{max}個字符"
val email__2 = "請輸入有效的郵箱地址"
val phone__2 = "請輸入有效的手機號碼"
val url__2 = "請輸入有效的網址"
val requiredError__2 = "請輸入{label}"
val submitButton__2 = "提交"
val resetButton__2 = "重設"
val validationFailed__2 = "表單驗證失敗"
val submitFailed__2 = "表單提交失敗"
val default__43: UTSJSONObject = _uO("inputPlaceholder" to inputPlaceholder__2, "selectPlaceholder" to selectPlaceholder__2, "datePlaceholder" to datePlaceholder__2, "timePlaceholder" to timePlaceholder__2, "required" to required__2, "invalid" to invalid__2, "minLength" to minLength__2, "maxLength" to maxLength__2, "email" to email__2, "phone" to phone__2, "url" to url__2, "requiredError" to requiredError__2, "submitButton" to submitButton__2, "resetButton" to resetButton__2, "validationFailed" to validationFailed__2, "submitFailed" to submitFailed__2)
val placeholder__6 = "請輸入"
val default__44: UTSJSONObject = _uO("placeholder" to placeholder__6)
val loadingText__2 = "載入中..."
val finishedText__2 = "沒有更多了"
val errorText__2 = "載入失敗，點擊重試"
val default__45: UTSJSONObject = _uO("loadingText" to loadingText__2, "finishedText" to finishedText__2, "errorText" to errorText__2)
val text__5 = "載入中..."
val default__46: UTSJSONObject = _uO("text" to text__5)
val backText__2 = "返回"
val title__15 = "標題"
val default__47: UTSJSONObject = _uO("backText" to backText__2, "title" to title__15)
val closeText__2 = "關閉"
val default__48: UTSJSONObject = _uO("closeText" to closeText__2)
val title__16 = "請選擇"
val confirmText__7 = "確定"
val cancelText__12 = "取消"
val default__49: UTSJSONObject = _uO("title" to title__16, "confirmText" to confirmText__7, "cancelText" to cancelText__12)
val placeholder__7 = "請輸入搜尋關鍵字"
val cancelText__13 = "取消"
val default__50: UTSJSONObject = _uO("placeholder" to placeholder__7, "cancelText" to cancelText__13)
val placeholder__8 = "請輸入內容"
val default__51: UTSJSONObject = _uO("placeholder" to placeholder__8)
val loading__8 = "載入中..."
val success__2 = "操作成功"
val fail__2 = "操作失敗"
val info__2 = "提示"
val default__52: UTSJSONObject = _uO("loading" to loading__8, "success" to success__2, "fail" to fail__2, "info" to info__2)
val title__17 = "選擇時間"
val confirmText__8 = "確定"
val cancelText__14 = "取消"
val year__2 = "年"
val month__2 = "月"
val day__2 = "日"
val hour__2 = "時"
val minute__2 = "分"
val default__53: UTSJSONObject = _uO("title" to title__17, "confirmText" to confirmText__8, "cancelText" to cancelText__14, "year" to year__2, "month" to month__2, "day" to day__2, "hour" to hour__2, "minute" to minute__2)
val zhCNModules = ModularLocaleMessages()
val runBlock4 = run {
    zhCNModules["actionSheet"] = `default`
    zhCNModules["common"] = default__1
    zhCNModules["dialog"] = default__2
    zhCNModules["empty"] = default__3
    zhCNModules["errorState"] = default__4
    zhCNModules["examples"] = default__5
    zhCNModules["examplePages"] = default__6
    zhCNModules["form"] = default__7
    zhCNModules["input"] = default__8
    zhCNModules["list"] = default__9
    zhCNModules["loading"] = default__10
    zhCNModules["navBar"] = default__11
    zhCNModules["noticeBar"] = default__12
    zhCNModules["picker"] = default__13
    zhCNModules["searchBar"] = default__14
    zhCNModules["textarea"] = default__15
    zhCNModules["toast"] = default__16
    zhCNModules["datetimePicker"] = default__17
}
val enUSModules = ModularLocaleMessages()
val runBlock5 = run {
    enUSModules["actionSheet"] = default__18
    enUSModules["common"] = default__19
    enUSModules["dialog"] = default__20
    enUSModules["empty"] = default__21
    enUSModules["errorState"] = default__22
    enUSModules["examples"] = default__23
    enUSModules["examplePages"] = default__24
    enUSModules["form"] = default__25
    enUSModules["input"] = default__26
    enUSModules["list"] = default__27
    enUSModules["loading"] = default__28
    enUSModules["navBar"] = default__29
    enUSModules["noticeBar"] = default__30
    enUSModules["picker"] = default__31
    enUSModules["searchBar"] = default__32
    enUSModules["textarea"] = default__33
    enUSModules["toast"] = default__34
    enUSModules["datetimePicker"] = default__35
}
val zhTWModules = ModularLocaleMessages()
val runBlock6 = run {
    zhTWModules["actionSheet"] = default__36
    zhTWModules["common"] = default__37
    zhTWModules["dialog"] = default__38
    zhTWModules["empty"] = default__39
    zhTWModules["errorState"] = default__40
    zhTWModules["examples"] = default__41
    zhTWModules["examplePages"] = default__42
    zhTWModules["form"] = default__43
    zhTWModules["input"] = default__44
    zhTWModules["list"] = default__45
    zhTWModules["loading"] = default__46
    zhTWModules["navBar"] = default__47
    zhTWModules["noticeBar"] = default__48
    zhTWModules["picker"] = default__49
    zhTWModules["searchBar"] = default__50
    zhTWModules["textarea"] = default__51
    zhTWModules["toast"] = default__52
    zhTWModules["datetimePicker"] = default__53
}
open class LocaleLoader {
    companion object {
        private val MODULES = _uA(
            "actionSheet",
            "common",
            "dialog",
            "empty",
            "errorState",
            "examples",
            "examplePages",
            "form",
            "input",
            "list",
            "loading",
            "navBar",
            "noticeBar",
            "picker",
            "searchBar",
            "textarea",
            "toast",
            "datetimePicker"
        ) as UTSArray<String>
        fun loadLocale(locale: String): ModularLocaleMessages {
            val modules = ModularLocaleMessages()
            val localeImports = this.getLocaleImports(locale)
            if (localeImports == null) {
                console.warn("[Tang UI I18n] Locale \"" + locale + "\" not found")
                return modules
            }
            for(moduleName in resolveUTSValueIterator(this.MODULES)){
                try {
                    val content = localeImports[moduleName]
                    if (content != null) {
                        modules[moduleName] = content
                    } else {
                        console.warn("[Tang UI I18n] Module " + moduleName + " not found for locale " + locale)
                        modules[moduleName] = _uO()
                    }
                }
                 catch (error: Throwable) {
                    console.warn("[Tang UI I18n] Failed to load module " + moduleName + " for locale " + locale, error)
                    modules[moduleName] = _uO()
                }
            }
            return modules
        }
        private fun getLocaleImports(locale: String): ModularLocaleMessages? {
            when (locale) {
                "zh-CN" -> 
                    return zhCNModules
                "en-US" -> 
                    return enUSModules
                "zh-TW" -> 
                    return zhTWModules
                else -> 
                    return null
            }
        }
        fun preloadBuiltinLocales(): Map<String, ModularLocaleMessages> {
            val builtinLocales = _uA(
                "zh-CN",
                "en-US",
                "zh-TW"
            ) as UTSArray<String>
            val localesMap = Map<String, ModularLocaleMessages>()
            for(locale in resolveUTSValueIterator(builtinLocales)){
                try {
                    val messages = this.loadLocale(locale)
                    localesMap.set(locale, messages)
                }
                 catch (error: Throwable) {
                    console.error("[Tang UI I18n] Failed to preload locale " + locale, error)
                }
            }
            return localesMap
        }
    }
}
open class I18nError {
    companion object {
        fun warnLocaleNotFound(locale: String): Unit {
            console.warn("[Tang UI I18n] Locale \"" + locale + "\" not found, using fallback locale")
        }
        fun warnKeyNotFound(key: String, locale: String): Unit {}
        fun errorInvalidMessages(locale: String): Unit {
            console.error("[Tang UI I18n] Invalid messages format for locale \"" + locale + "\"")
        }
        fun warnMissingParam(key: String, param: String): Unit {}
        fun warnInvalidKey(key: String): Unit {}
        fun errorMessagesNotObject(locale: String): Unit {
            console.error("[Tang UI I18n] Messages for locale \"" + locale + "\" must be a valid object")
        }
        fun errorModuleNotObject(locale: String, moduleName: String): Unit {
            console.error("[Tang UI I18n] Module \"" + moduleName + "\" in locale \"" + locale + "\" must be a valid object")
        }
        fun errorValueNotString(locale: String, moduleName: String, key: String): Unit {
            console.error("[Tang UI I18n] Value for key \"" + key + "\" in module \"" + moduleName + "\" of locale \"" + locale + "\" must be a string")
        }
    }
}
fun isObject(value: Any?): Boolean {
    return value != null && UTSAndroid.`typeof`(value) === "object" && !UTSArray.isArray(value)
}
fun cloneModules(source: ModularLocaleMessages): ModularLocaleMessages {
    val result = ModularLocaleMessages()
    for(moduleName in resolveUTSKeyIterator(source)){
        result[moduleName] = source[moduleName]
    }
    return result
}
open class I18nManager {
    open lateinit var currentLocale: Ref<String>
    open lateinit var fallbackLocale: String
    private var messages: Map<String, ModularLocaleMessages>
    private var availableLocaleCodes: UTSArray<String>
    private constructor(){
        this.currentLocale = ref("zh-CN")
        this.fallbackLocale = "zh-CN"
        this.messages = Map<String, ModularLocaleMessages>()
        this.availableLocaleCodes = _uA()
    }
    open fun translate(key: String, params: TranslateParams?): String {
        val parts = key.split(".")
        if (parts.length < 2) {
            I18nError.warnInvalidKey(key)
            return key
        }
        val moduleName = parts[0]
        val messageKey = parts.slice(1).join(".")
        if (moduleName == "" || messageKey == "") {
            I18nError.warnInvalidKey(key)
            return key
        }
        var message = this.getModuleMessage(this.currentLocale.value, moduleName, messageKey)
        if (message == null) {
            message = this.getModuleMessage(this.fallbackLocale, moduleName, messageKey)
            if (message == null) {
                I18nError.warnKeyNotFound(key, this.currentLocale.value)
                return key
            }
        }
        if (params != null && UTSAndroid.`typeof`(message) === "string") {
            return this.interpolate(message, params)
        }
        return message as String
    }
    private fun getModuleMessage(locale: String, moduleName: String, key: String): String? {
        val localeMessages = this.messages.get(locale)
        if (localeMessages == null) {
            return null
        }
        val moduleValue = localeMessages[moduleName]
        if (!isObject(moduleValue)) {
            return null
        }
        val moduleObject = moduleValue as UTSJSONObject
        val keys = key.split(".")
        var current: Any? = moduleObject
        for(item in resolveUTSValueIterator(keys)){
            if (!isObject(current)) {
                return null
            }
            val currentObject = current as UTSJSONObject
            if (!resolveInOperator(currentObject, item)) {
                return null
            }
            current = currentObject[item]
        }
        return if (UTSAndroid.`typeof`(current) === "string") {
            current as String
        } else {
            null
        }
    }
    private fun interpolate(template: String, params: TranslateParams): String {
        var result = template
        for(key in resolveUTSKeyIterator(params)){
            val value = params[key]
            if (value != null) {
                result = result.split("{" + key + "}").join("" + value)
            }
        }
        return result
    }
    open fun setLocale(locale: String): Boolean {
        if (!this.hasLocale(locale)) {
            I18nError.warnLocaleNotFound(locale)
            return false
        }
        this.currentLocale.value = locale
        return true
    }
    open fun hasLocale(locale: String): Boolean {
        return this.messages.has(locale)
    }
    private fun validateMessages(locale: String, messages: ModularLocaleMessages): Boolean {
        if (messages == null || UTSAndroid.`typeof`(messages) !== "object" || UTSArray.isArray(messages)) {
            I18nError.errorMessagesNotObject(locale)
            return false
        }
        for(moduleName in resolveUTSKeyIterator(messages)){
            val moduleValue = messages[moduleName]
            if (!isObject(moduleValue)) {
                I18nError.errorModuleNotObject(locale, moduleName)
                return false
            }
            if (!this.validateModuleContent(locale, moduleName, moduleValue as UTSJSONObject)) {
                return false
            }
        }
        return true
    }
    private fun validateModuleContent(locale: String, moduleName: String, obj: UTSJSONObject): Boolean {
        for(key in resolveUTSKeyIterator(obj)){
            val value = obj[key]
            if (UTSAndroid.`typeof`(value) === "string") {
                continue
            } else if (isObject(value)) {
                if (!this.validateModuleContent(locale, moduleName, value as UTSJSONObject)) {
                    return false
                }
            } else {
                I18nError.errorValueNotString(locale, moduleName, key)
                return false
            }
        }
        return true
    }
    open fun registerMessages(locale: String, messages: Any): Unit {
        if (UTSAndroid.`typeof`(messages) === "string") {
            this.registerMessagesFromPath(locale, messages as String)
        } else {
            this.registerMessagesWithMode(locale, messages as UTSJSONObject, "merge")
        }
    }
    open fun registerMessagesAsync(locale: String, messages: Any): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                if (UTSAndroid.`typeof`(messages) === "string") {
                    await(this.registerMessagesFromPathAsync(locale, messages as String))
                } else {
                    this.registerMessagesWithMode(locale, messages as UTSJSONObject, "merge")
                }
        })
    }
    private fun registerMessagesFromPathAsync(locale: String, basePath: String): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val loadedMessages = LocaleLoader.loadLocale(locale)
                if (this.hasMessages(loadedMessages)) {
                    this.registerMessagesWithMode(locale, loadedMessages, "merge")
                } else {
                    console.warn("[I18n] Failed to load locale " + locale + " from path " + basePath + ". UniApp X App builds require static locale imports.")
                    I18nError.warnLocaleNotFound(locale)
                }
        })
    }
    private fun registerMessagesFromPath(locale: String, basePath: String): Unit {
        this.registerMessagesFromPathAsync(locale, basePath).`catch`(fun(e){
            console.error("[I18n] Failed to load locale " + locale + " from path " + basePath + ":", e)
            I18nError.warnLocaleNotFound(locale)
        }
        )
    }
    open fun registerMessagesWithMode(locale: String, messages: ModularLocaleMessages, mode: String): Unit {
        if (!this.validateMessages(locale, messages)) {
            return
        }
        if (mode === "replace") {
            this.messages.set(locale, messages)
        } else {
            val existingMessages = this.messages.get(locale)
            if (existingMessages != null) {
                val merged = this.mergeModularMessages(existingMessages, messages)
                this.messages.set(locale, merged)
            } else {
                this.messages.set(locale, messages)
            }
        }
        this.ensureLocaleRegistered(locale)
    }
    open fun replaceMessages(locale: String, messages: ModularLocaleMessages): Unit {
        if (!this.validateMessages(locale, messages)) {
            return
        }
        this.messages.set(locale, messages)
        this.ensureLocaleRegistered(locale)
    }
    private fun mergeModularMessages(target: ModularLocaleMessages, source: ModularLocaleMessages): ModularLocaleMessages {
        val result: ModularLocaleMessages = cloneModules(target)
        for(moduleName in resolveUTSKeyIterator(source)){
            val sourceModule = source[moduleName]
            val targetModule = result[moduleName]
            if (isObject(targetModule) && isObject(sourceModule)) {
                val mergedModule: UTSJSONObject = _uO()
                val targetObject = targetModule as UTSJSONObject
                val sourceObject = sourceModule as UTSJSONObject
                for(key in resolveUTSKeyIterator(targetObject)){
                    mergedModule[key] = targetObject[key]
                }
                for(key in resolveUTSKeyIterator(sourceObject)){
                    mergedModule[key] = sourceObject[key]
                }
                result[moduleName] = mergedModule
            } else {
                result[moduleName] = sourceModule
            }
        }
        return result
    }
    open fun getAvailableLocales(): ComputedRef<UTSArray<String>> {
        return computed<UTSArray<String>>(fun(): UTSArray<String> {
            return this.availableLocaleCodes
        }
        )
    }
    open fun getCurrentLocale(): String {
        return this.currentLocale.value
    }
    open fun registerLocale(locale: String, messagesOrPath: Any): Unit {
        this.registerMessages(locale, messagesOrPath)
    }
    open fun registerLocaleAsync(locale: String, messagesOrPath: Any): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                await(this.registerMessagesAsync(locale, messagesOrPath))
        })
    }
    open fun extendLocale(locale: String, messages: ModularLocaleMessages): Unit {
        this.registerMessagesWithMode(locale, messages, "merge")
    }
    open fun getLocale(): String {
        return this.currentLocale.value
    }
    open fun t(key: String, params: TranslateParams?, defaultValue: String?): String {
        val result = this.translate(key, params)
        if (result === key && defaultValue != null) {
            return defaultValue
        }
        return result
    }
    private fun hasMessages(messages: ModularLocaleMessages): Boolean {
        for(key in resolveUTSKeyIterator(messages)){
            return true
        }
        return false
    }
    private fun ensureLocaleRegistered(locale: String): Unit {
        for(existingLocale in resolveUTSValueIterator(this.availableLocaleCodes)){
            if (existingLocale === locale) {
                return
            }
        }
        this.availableLocaleCodes.push(locale)
    }
    companion object {
        private var instance: I18nManager? = null
        fun getInstance(): I18nManager {
            if (I18nManager.instance == null) {
                I18nManager.instance = I18nManager()
            }
            return I18nManager.instance!!
        }
    }
}
var initialized = false
fun initI18n(): Unit {
    if (initialized) {
        console.log("[Tang UI I18n] Already initialized, skipping...")
        return
    }
    try {
        console.log("[Tang UI I18n] Initializing i18n system...")
        val manager = I18nManager.getInstance()
        manager.registerMessages("zh-CN", LocaleLoader.loadLocale("zh-CN"))
        console.log("[Tang UI I18n] Registered locale: zh-CN")
        manager.registerMessages("en-US", LocaleLoader.loadLocale("en-US"))
        console.log("[Tang UI I18n] Registered locale: en-US")
        manager.registerMessages("zh-TW", LocaleLoader.loadLocale("zh-TW"))
        console.log("[Tang UI I18n] Registered locale: zh-TW")
        val success = manager.setLocale("zh-CN")
        if (success) {
            console.log("[Tang UI I18n] Default locale set to zh-CN")
        } else {
            console.warn("[Tang UI I18n] Failed to set default locale to zh-CN")
        }
        initialized = true
        console.log("[Tang UI I18n] Initialization complete")
    }
     catch (error: Throwable) {
        console.error("[Tang UI I18n] Failed to initialize i18n system:", error)
        throw error
    }
}
val runBlock7 = run {
    initI18n()
}
val GenUniModulesTangUiXComponentsTIconIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTIconIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTIconIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTIconIndex.inject, props = GenUniModulesTangUiXComponentsTIconIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTIconIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTIconIndex.emits, components = GenUniModulesTangUiXComponentsTIconIndex.components, styles = GenUniModulesTangUiXComponentsTIconIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTIconIndex.setup(props as GenUniModulesTangUiXComponentsTIconIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTIconIndex {
    return GenUniModulesTangUiXComponentsTIconIndex(instance)
}
)
val GenUniModulesTangUiXComponentsTPopupIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTPopupIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTPopupIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTPopupIndex.inject, props = GenUniModulesTangUiXComponentsTPopupIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTPopupIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTPopupIndex.emits, components = GenUniModulesTangUiXComponentsTPopupIndex.components, styles = GenUniModulesTangUiXComponentsTPopupIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTPopupIndex.setup(props as GenUniModulesTangUiXComponentsTPopupIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTPopupIndex {
    return GenUniModulesTangUiXComponentsTPopupIndex(instance)
}
)
val GenUniModulesTangUiXComponentsTButtonIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTButtonIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTButtonIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTButtonIndex.inject, props = GenUniModulesTangUiXComponentsTButtonIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTButtonIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTButtonIndex.emits, components = GenUniModulesTangUiXComponentsTButtonIndex.components, styles = GenUniModulesTangUiXComponentsTButtonIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTButtonIndex.setup(props as GenUniModulesTangUiXComponentsTButtonIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTButtonIndex {
    return GenUniModulesTangUiXComponentsTButtonIndex(instance)
}
)
open class UseI18nReturn (
    open var `$t`: (key: String, params: TranslateParams?) -> String,
    open var t: (key: String) -> String,
    @JsonNotNull
    open var locale: ComputedRef<String>,
    @JsonNotNull
    open var availableLocales: ComputedRef<UTSArray<String>>,
    open var setLocale: (locale: String) -> Boolean,
) : UTSObject()
fun useI18n(): UseI18nReturn {
    val manager = I18nManager.getInstance()
    val `$t` = fun(key: String, params: TranslateParams?): String {
        return manager.translate(key, params)
    }
    val t = fun(key: String): String {
        return `$t`(key, null)
    }
    val locale = computed<String>(fun(): String {
        return manager.currentLocale.value
    }
    )
    val availableLocales = manager.getAvailableLocales()
    val setLocale = fun(locale: String): Boolean {
        return manager.setLocale(locale)
    }
    return UseI18nReturn(`$t` = `$t`, t = t, locale = locale, availableLocales = availableLocales, setLocale = setLocale)
}
val GenUniModulesTangUiXComponentsTEmptyIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTEmptyIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTEmptyIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTEmptyIndex.inject, props = GenUniModulesTangUiXComponentsTEmptyIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTEmptyIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTEmptyIndex.emits, components = GenUniModulesTangUiXComponentsTEmptyIndex.components, styles = GenUniModulesTangUiXComponentsTEmptyIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTEmptyIndex.setup(props as GenUniModulesTangUiXComponentsTEmptyIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTEmptyIndex {
    return GenUniModulesTangUiXComponentsTEmptyIndex(instance)
}
)
open class TabItem (
    @JsonNotNull
    open var key: Any,
    @JsonNotNull
    open var label: String,
    open var disabled: Boolean? = null,
    open var badge: Any? = null,
    open var icon: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return TabItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class TabItemReactiveObject : TabItem, IUTSReactive<TabItem> {
    override var __v_raw: TabItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: TabItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(key = __v_raw.key, label = __v_raw.label, disabled = __v_raw.disabled, badge = __v_raw.badge, icon = __v_raw.icon) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): TabItemReactiveObject {
        return TabItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var key: Any
        get() {
            return _tRG(__v_raw, "key", __v_raw.key, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("key")) {
                return
            }
            val oldValue = __v_raw.key
            __v_raw.key = value
            _tRS(__v_raw, "key", oldValue, value)
        }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var disabled: Boolean?
        get() {
            return _tRG(__v_raw, "disabled", __v_raw.disabled, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("disabled")) {
                return
            }
            val oldValue = __v_raw.disabled
            __v_raw.disabled = value
            _tRS(__v_raw, "disabled", oldValue, value)
        }
    override var badge: Any?
        get() {
            return _tRG(__v_raw, "badge", __v_raw.badge, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("badge")) {
                return
            }
            val oldValue = __v_raw.badge
            __v_raw.badge = value
            _tRS(__v_raw, "badge", oldValue, value)
        }
    override var icon: String?
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
}
typealias TabsModelValue = Any?
val GenUniModulesTangUiXComponentsTabsIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTabsIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTabsIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTabsIndex.inject, props = GenUniModulesTangUiXComponentsTabsIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTabsIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTabsIndex.emits, components = GenUniModulesTangUiXComponentsTabsIndex.components, styles = GenUniModulesTangUiXComponentsTabsIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTabsIndex.setup(props as GenUniModulesTangUiXComponentsTabsIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTabsIndex {
    return GenUniModulesTangUiXComponentsTabsIndex(instance)
}
)
open class TabBarItem (
    @JsonNotNull
    open var pagePath: String,
    @JsonNotNull
    open var text: String,
    @JsonNotNull
    open var iconPath: String,
    @JsonNotNull
    open var selectedIconPath: String,
    open var isRaised: Boolean? = null,
) : UTSObject()
val GenComponentsCustomTabbarIndexClass = CreateVueComponent(GenComponentsCustomTabbarIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsCustomTabbarIndex.inheritAttrs, inject = GenComponentsCustomTabbarIndex.inject, props = GenComponentsCustomTabbarIndex.props, propsNeedCastKeys = GenComponentsCustomTabbarIndex.propsNeedCastKeys, emits = GenComponentsCustomTabbarIndex.emits, components = GenComponentsCustomTabbarIndex.components, styles = GenComponentsCustomTabbarIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsCustomTabbarIndex.setup(props as GenComponentsCustomTabbarIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsCustomTabbarIndex {
    return GenComponentsCustomTabbarIndex(instance)
}
)
val GenUniModulesTangUiXComponentsTImageIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTImageIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTImageIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTImageIndex.inject, props = GenUniModulesTangUiXComponentsTImageIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTImageIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTImageIndex.emits, components = GenUniModulesTangUiXComponentsTImageIndex.components, styles = GenUniModulesTangUiXComponentsTImageIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTImageIndex.setup(props as GenUniModulesTangUiXComponentsTImageIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTImageIndex {
    return GenUniModulesTangUiXComponentsTImageIndex(instance)
}
)
open class JobInfo (
    @JsonNotNull
    open var id: Number,
    @JsonNotNull
    open var title: String,
    @JsonNotNull
    open var company: String,
    @JsonNotNull
    open var area: String,
    @JsonNotNull
    open var address: String,
    @JsonNotNull
    open var benefitTags: UTSArray<String>,
    @JsonNotNull
    open var salaryName: String,
    open var salaryMin: Number? = null,
    open var salaryMax: Number? = null,
    open var salaryUnit: String? = null,
    @JsonNotNull
    open var distance: Number,
    @JsonNotNull
    open var settleType: String,
    open var image: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return JobInfoReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class JobInfoReactiveObject : JobInfo, IUTSReactive<JobInfo> {
    override var __v_raw: JobInfo
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: JobInfo, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, title = __v_raw.title, company = __v_raw.company, area = __v_raw.area, address = __v_raw.address, benefitTags = __v_raw.benefitTags, salaryName = __v_raw.salaryName, salaryMin = __v_raw.salaryMin, salaryMax = __v_raw.salaryMax, salaryUnit = __v_raw.salaryUnit, distance = __v_raw.distance, settleType = __v_raw.settleType, image = __v_raw.image) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): JobInfoReactiveObject {
        return JobInfoReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var id: Number
        get() {
            return _tRG(__v_raw, "id", __v_raw.id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("id")) {
                return
            }
            val oldValue = __v_raw.id
            __v_raw.id = value
            _tRS(__v_raw, "id", oldValue, value)
        }
    override var title: String
        get() {
            return _tRG(__v_raw, "title", __v_raw.title, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("title")) {
                return
            }
            val oldValue = __v_raw.title
            __v_raw.title = value
            _tRS(__v_raw, "title", oldValue, value)
        }
    override var company: String
        get() {
            return _tRG(__v_raw, "company", __v_raw.company, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("company")) {
                return
            }
            val oldValue = __v_raw.company
            __v_raw.company = value
            _tRS(__v_raw, "company", oldValue, value)
        }
    override var area: String
        get() {
            return _tRG(__v_raw, "area", __v_raw.area, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("area")) {
                return
            }
            val oldValue = __v_raw.area
            __v_raw.area = value
            _tRS(__v_raw, "area", oldValue, value)
        }
    override var address: String
        get() {
            return _tRG(__v_raw, "address", __v_raw.address, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("address")) {
                return
            }
            val oldValue = __v_raw.address
            __v_raw.address = value
            _tRS(__v_raw, "address", oldValue, value)
        }
    override var benefitTags: UTSArray<String>
        get() {
            return _tRG(__v_raw, "benefitTags", __v_raw.benefitTags, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("benefitTags")) {
                return
            }
            val oldValue = __v_raw.benefitTags
            __v_raw.benefitTags = value
            _tRS(__v_raw, "benefitTags", oldValue, value)
        }
    override var salaryName: String
        get() {
            return _tRG(__v_raw, "salaryName", __v_raw.salaryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("salaryName")) {
                return
            }
            val oldValue = __v_raw.salaryName
            __v_raw.salaryName = value
            _tRS(__v_raw, "salaryName", oldValue, value)
        }
    override var salaryMin: Number?
        get() {
            return _tRG(__v_raw, "salaryMin", __v_raw.salaryMin, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("salaryMin")) {
                return
            }
            val oldValue = __v_raw.salaryMin
            __v_raw.salaryMin = value
            _tRS(__v_raw, "salaryMin", oldValue, value)
        }
    override var salaryMax: Number?
        get() {
            return _tRG(__v_raw, "salaryMax", __v_raw.salaryMax, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("salaryMax")) {
                return
            }
            val oldValue = __v_raw.salaryMax
            __v_raw.salaryMax = value
            _tRS(__v_raw, "salaryMax", oldValue, value)
        }
    override var salaryUnit: String?
        get() {
            return _tRG(__v_raw, "salaryUnit", __v_raw.salaryUnit, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("salaryUnit")) {
                return
            }
            val oldValue = __v_raw.salaryUnit
            __v_raw.salaryUnit = value
            _tRS(__v_raw, "salaryUnit", oldValue, value)
        }
    override var distance: Number
        get() {
            return _tRG(__v_raw, "distance", __v_raw.distance, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("distance")) {
                return
            }
            val oldValue = __v_raw.distance
            __v_raw.distance = value
            _tRS(__v_raw, "distance", oldValue, value)
        }
    override var settleType: String
        get() {
            return _tRG(__v_raw, "settleType", __v_raw.settleType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("settleType")) {
                return
            }
            val oldValue = __v_raw.settleType
            __v_raw.settleType = value
            _tRS(__v_raw, "settleType", oldValue, value)
        }
    override var image: String?
        get() {
            return _tRG(__v_raw, "image", __v_raw.image, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("image")) {
                return
            }
            val oldValue = __v_raw.image
            __v_raw.image = value
            _tRS(__v_raw, "image", oldValue, value)
        }
}
val GenComponentsJobCardIndexClass = CreateVueComponent(GenComponentsJobCardIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsJobCardIndex.inheritAttrs, inject = GenComponentsJobCardIndex.inject, props = GenComponentsJobCardIndex.props, propsNeedCastKeys = GenComponentsJobCardIndex.propsNeedCastKeys, emits = GenComponentsJobCardIndex.emits, components = GenComponentsJobCardIndex.components, styles = GenComponentsJobCardIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsJobCardIndex.setup(props as GenComponentsJobCardIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsJobCardIndex {
    return GenComponentsJobCardIndex(instance)
}
)
val GenPagesApplyTabbarHomeComponentsGroupBannerIndexClass = CreateVueComponent(GenPagesApplyTabbarHomeComponentsGroupBannerIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.inheritAttrs, inject = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.inject, props = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.props, propsNeedCastKeys = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.propsNeedCastKeys, emits = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.emits, components = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.components, styles = GenPagesApplyTabbarHomeComponentsGroupBannerIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarHomeComponentsGroupBannerIndex.setup(props as GenPagesApplyTabbarHomeComponentsGroupBannerIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarHomeComponentsGroupBannerIndex {
    return GenPagesApplyTabbarHomeComponentsGroupBannerIndex(instance)
}
)
open class SearchBarInputEventDetail (
    @JsonNotNull
    open var value: String,
) : UTSObject()
open class SearchBarInputEvent (
    @JsonNotNull
    open var detail: SearchBarInputEventDetail,
) : UTSObject()
val GenUniModulesTangUiXComponentsTSearchBarIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTSearchBarIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTSearchBarIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTSearchBarIndex.inject, props = GenUniModulesTangUiXComponentsTSearchBarIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTSearchBarIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTSearchBarIndex.emits, components = GenUniModulesTangUiXComponentsTSearchBarIndex.components, styles = GenUniModulesTangUiXComponentsTSearchBarIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTSearchBarIndex.setup(props as GenUniModulesTangUiXComponentsTSearchBarIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTSearchBarIndex {
    return GenUniModulesTangUiXComponentsTSearchBarIndex(instance)
}
)
val GenComponentsCommonSearchBarIndexClass = CreateVueComponent(GenComponentsCommonSearchBarIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsCommonSearchBarIndex.inheritAttrs, inject = GenComponentsCommonSearchBarIndex.inject, props = GenComponentsCommonSearchBarIndex.props, propsNeedCastKeys = GenComponentsCommonSearchBarIndex.propsNeedCastKeys, emits = GenComponentsCommonSearchBarIndex.emits, components = GenComponentsCommonSearchBarIndex.components, styles = GenComponentsCommonSearchBarIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsCommonSearchBarIndex.setup(props as GenComponentsCommonSearchBarIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsCommonSearchBarIndex {
    return GenComponentsCommonSearchBarIndex(instance)
}
)
val GenUniModulesTangUiXComponentsTCheckboxIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTCheckboxIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTCheckboxIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTCheckboxIndex.inject, props = GenUniModulesTangUiXComponentsTCheckboxIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTCheckboxIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTCheckboxIndex.emits, components = GenUniModulesTangUiXComponentsTCheckboxIndex.components, styles = GenUniModulesTangUiXComponentsTCheckboxIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTCheckboxIndex.setup(props as GenUniModulesTangUiXComponentsTCheckboxIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTCheckboxIndex {
    return GenUniModulesTangUiXComponentsTCheckboxIndex(instance)
}
)
open class FilterOption (
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var value: Any,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return FilterOptionReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class FilterOptionReactiveObject : FilterOption, IUTSReactive<FilterOption> {
    override var __v_raw: FilterOption
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: FilterOption, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(label = __v_raw.label, value = __v_raw.value) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): FilterOptionReactiveObject {
        return FilterOptionReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var value: Any
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
}
open class FilterItem (
    @JsonNotNull
    open var key: String,
    @JsonNotNull
    open var title: String,
    @JsonNotNull
    open var value: Any,
    @JsonNotNull
    open var options: UTSArray<FilterOption>,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return FilterItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class FilterItemReactiveObject : FilterItem, IUTSReactive<FilterItem> {
    override var __v_raw: FilterItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: FilterItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(key = __v_raw.key, title = __v_raw.title, value = __v_raw.value, options = __v_raw.options) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): FilterItemReactiveObject {
        return FilterItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var key: String
        get() {
            return _tRG(__v_raw, "key", __v_raw.key, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("key")) {
                return
            }
            val oldValue = __v_raw.key
            __v_raw.key = value
            _tRS(__v_raw, "key", oldValue, value)
        }
    override var title: String
        get() {
            return _tRG(__v_raw, "title", __v_raw.title, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("title")) {
                return
            }
            val oldValue = __v_raw.title
            __v_raw.title = value
            _tRS(__v_raw, "title", oldValue, value)
        }
    override var value: Any
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
    override var options: UTSArray<FilterOption>
        get() {
            return _tRG(__v_raw, "options", __v_raw.options, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("options")) {
                return
            }
            val oldValue = __v_raw.options
            __v_raw.options = value
            _tRS(__v_raw, "options", oldValue, value)
        }
}
val GenPagesApplyTabbarHomeIndexClass = CreateVueComponent(GenPagesApplyTabbarHomeIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyTabbarHomeIndex.inheritAttrs, inject = GenPagesApplyTabbarHomeIndex.inject, props = GenPagesApplyTabbarHomeIndex.props, propsNeedCastKeys = GenPagesApplyTabbarHomeIndex.propsNeedCastKeys, emits = GenPagesApplyTabbarHomeIndex.emits, components = GenPagesApplyTabbarHomeIndex.components, styles = GenPagesApplyTabbarHomeIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarHomeIndex.setup(props as GenPagesApplyTabbarHomeIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarHomeIndex {
    return GenPagesApplyTabbarHomeIndex(instance, renderer)
}
)
open class NavbarStyle (
    @JsonNotNull
    open var height: String,
    @JsonNotNull
    open var backgroundColor: String,
    @JsonNotNull
    open var color: String,
    @JsonNotNull
    open var borderBottomWidth: String,
    open var paddingRight: String? = null,
) : UTSObject()
open class NavbarBlockStyle (
    @JsonNotNull
    open var height: String,
    open var maxWidth: String? = null,
) : UTSObject()
val GenComponentsNavbarIndexClass = CreateVueComponent(GenComponentsNavbarIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsNavbarIndex.inheritAttrs, inject = GenComponentsNavbarIndex.inject, props = GenComponentsNavbarIndex.props, propsNeedCastKeys = GenComponentsNavbarIndex.propsNeedCastKeys, emits = GenComponentsNavbarIndex.emits, components = GenComponentsNavbarIndex.components, styles = GenComponentsNavbarIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsNavbarIndex.setup(props as GenComponentsNavbarIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsNavbarIndex {
    return GenComponentsNavbarIndex(instance)
}
)
val GenUniModulesTangUiXComponentsTAvatarIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTAvatarIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTAvatarIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTAvatarIndex.inject, props = GenUniModulesTangUiXComponentsTAvatarIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTAvatarIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTAvatarIndex.emits, components = GenUniModulesTangUiXComponentsTAvatarIndex.components, styles = GenUniModulesTangUiXComponentsTAvatarIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTAvatarIndex.setup(props as GenUniModulesTangUiXComponentsTAvatarIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTAvatarIndex {
    return GenUniModulesTangUiXComponentsTAvatarIndex(instance)
}
)
val GenUniModulesTangUiXComponentsTCardIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTCardIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTCardIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTCardIndex.inject, props = GenUniModulesTangUiXComponentsTCardIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTCardIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTCardIndex.emits, components = GenUniModulesTangUiXComponentsTCardIndex.components, styles = GenUniModulesTangUiXComponentsTCardIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTCardIndex.setup(props as GenUniModulesTangUiXComponentsTCardIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTCardIndex {
    return GenUniModulesTangUiXComponentsTCardIndex(instance)
}
)
val GenPagesHireTabbarHomeComponentsPositionCardClass = CreateVueComponent(GenPagesHireTabbarHomeComponentsPositionCard::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesHireTabbarHomeComponentsPositionCard.inheritAttrs, inject = GenPagesHireTabbarHomeComponentsPositionCard.inject, props = GenPagesHireTabbarHomeComponentsPositionCard.props, propsNeedCastKeys = GenPagesHireTabbarHomeComponentsPositionCard.propsNeedCastKeys, emits = GenPagesHireTabbarHomeComponentsPositionCard.emits, components = GenPagesHireTabbarHomeComponentsPositionCard.components, styles = GenPagesHireTabbarHomeComponentsPositionCard.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarHomeComponentsPositionCard.setup(props as GenPagesHireTabbarHomeComponentsPositionCard)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarHomeComponentsPositionCard {
    return GenPagesHireTabbarHomeComponentsPositionCard(instance)
}
)
open class FilterItem__1 (
    @JsonNotNull
    open var key: String,
    @JsonNotNull
    open var title: String,
    @JsonNotNull
    open var value: Any,
    @JsonNotNull
    open var options: UTSArray<RadioOption>,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return FilterItem__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class FilterItem__1ReactiveObject : FilterItem__1, IUTSReactive<FilterItem__1> {
    override var __v_raw: FilterItem__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: FilterItem__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(key = __v_raw.key, title = __v_raw.title, value = __v_raw.value, options = __v_raw.options) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): FilterItem__1ReactiveObject {
        return FilterItem__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var key: String
        get() {
            return _tRG(__v_raw, "key", __v_raw.key, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("key")) {
                return
            }
            val oldValue = __v_raw.key
            __v_raw.key = value
            _tRS(__v_raw, "key", oldValue, value)
        }
    override var title: String
        get() {
            return _tRG(__v_raw, "title", __v_raw.title, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("title")) {
                return
            }
            val oldValue = __v_raw.title
            __v_raw.title = value
            _tRS(__v_raw, "title", oldValue, value)
        }
    override var value: Any
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
    override var options: UTSArray<RadioOption>
        get() {
            return _tRG(__v_raw, "options", __v_raw.options, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("options")) {
                return
            }
            val oldValue = __v_raw.options
            __v_raw.options = value
            _tRS(__v_raw, "options", oldValue, value)
        }
}
open class PageParams (
    @JsonNotNull
    open var Page: Number,
    @JsonNotNull
    open var PageSize: Number,
    @JsonNotNull
    open var myType: Number,
    @JsonNotNull
    open var Keywords: String,
    @JsonNotNull
    open var Education: String,
    @JsonNotNull
    open var HelpType: String,
    @JsonNotNull
    open var Sex: Number,
    @JsonNotNull
    open var MinAge: Number,
    @JsonNotNull
    open var MaxAge: Number,
    @JsonNotNull
    open var MinExperience: Number,
    @JsonNotNull
    open var MaxExperience: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PageParamsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PageParamsReactiveObject : PageParams, IUTSReactive<PageParams> {
    override var __v_raw: PageParams
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PageParams, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(Page = __v_raw.Page, PageSize = __v_raw.PageSize, myType = __v_raw.myType, Keywords = __v_raw.Keywords, Education = __v_raw.Education, HelpType = __v_raw.HelpType, Sex = __v_raw.Sex, MinAge = __v_raw.MinAge, MaxAge = __v_raw.MaxAge, MinExperience = __v_raw.MinExperience, MaxExperience = __v_raw.MaxExperience) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PageParamsReactiveObject {
        return PageParamsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var Page: Number
        get() {
            return _tRG(__v_raw, "Page", __v_raw.Page, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Page")) {
                return
            }
            val oldValue = __v_raw.Page
            __v_raw.Page = value
            _tRS(__v_raw, "Page", oldValue, value)
        }
    override var PageSize: Number
        get() {
            return _tRG(__v_raw, "PageSize", __v_raw.PageSize, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("PageSize")) {
                return
            }
            val oldValue = __v_raw.PageSize
            __v_raw.PageSize = value
            _tRS(__v_raw, "PageSize", oldValue, value)
        }
    override var myType: Number
        get() {
            return _tRG(__v_raw, "myType", __v_raw.myType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("myType")) {
                return
            }
            val oldValue = __v_raw.myType
            __v_raw.myType = value
            _tRS(__v_raw, "myType", oldValue, value)
        }
    override var Keywords: String
        get() {
            return _tRG(__v_raw, "Keywords", __v_raw.Keywords, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Keywords")) {
                return
            }
            val oldValue = __v_raw.Keywords
            __v_raw.Keywords = value
            _tRS(__v_raw, "Keywords", oldValue, value)
        }
    override var Education: String
        get() {
            return _tRG(__v_raw, "Education", __v_raw.Education, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Education")) {
                return
            }
            val oldValue = __v_raw.Education
            __v_raw.Education = value
            _tRS(__v_raw, "Education", oldValue, value)
        }
    override var HelpType: String
        get() {
            return _tRG(__v_raw, "HelpType", __v_raw.HelpType, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("HelpType")) {
                return
            }
            val oldValue = __v_raw.HelpType
            __v_raw.HelpType = value
            _tRS(__v_raw, "HelpType", oldValue, value)
        }
    override var Sex: Number
        get() {
            return _tRG(__v_raw, "Sex", __v_raw.Sex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("Sex")) {
                return
            }
            val oldValue = __v_raw.Sex
            __v_raw.Sex = value
            _tRS(__v_raw, "Sex", oldValue, value)
        }
    override var MinAge: Number
        get() {
            return _tRG(__v_raw, "MinAge", __v_raw.MinAge, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinAge")) {
                return
            }
            val oldValue = __v_raw.MinAge
            __v_raw.MinAge = value
            _tRS(__v_raw, "MinAge", oldValue, value)
        }
    override var MaxAge: Number
        get() {
            return _tRG(__v_raw, "MaxAge", __v_raw.MaxAge, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MaxAge")) {
                return
            }
            val oldValue = __v_raw.MaxAge
            __v_raw.MaxAge = value
            _tRS(__v_raw, "MaxAge", oldValue, value)
        }
    override var MinExperience: Number
        get() {
            return _tRG(__v_raw, "MinExperience", __v_raw.MinExperience, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MinExperience")) {
                return
            }
            val oldValue = __v_raw.MinExperience
            __v_raw.MinExperience = value
            _tRS(__v_raw, "MinExperience", oldValue, value)
        }
    override var MaxExperience: Number
        get() {
            return _tRG(__v_raw, "MaxExperience", __v_raw.MaxExperience, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("MaxExperience")) {
                return
            }
            val oldValue = __v_raw.MaxExperience
            __v_raw.MaxExperience = value
            _tRS(__v_raw, "MaxExperience", oldValue, value)
        }
}
val GenPagesHireTabbarHomeIndexClass = CreateVueComponent(GenPagesHireTabbarHomeIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesHireTabbarHomeIndex.inheritAttrs, inject = GenPagesHireTabbarHomeIndex.inject, props = GenPagesHireTabbarHomeIndex.props, propsNeedCastKeys = GenPagesHireTabbarHomeIndex.propsNeedCastKeys, emits = GenPagesHireTabbarHomeIndex.emits, components = GenPagesHireTabbarHomeIndex.components, styles = GenPagesHireTabbarHomeIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarHomeIndex.setup(props as GenPagesHireTabbarHomeIndex)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarHomeIndex {
    return GenPagesHireTabbarHomeIndex(instance, renderer)
}
)
typealias InputModelValue = Any
open class InputChangeEventDetail (
    @JsonNotNull
    open var value: Any,
) : UTSObject()
open class InputChangeEvent (
    @JsonNotNull
    open var detail: InputChangeEventDetail,
) : UTSObject()
val GenUniModulesTangUiXComponentsTInputIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTInputIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTInputIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTInputIndex.inject, props = GenUniModulesTangUiXComponentsTInputIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTInputIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTInputIndex.emits, components = GenUniModulesTangUiXComponentsTInputIndex.components, styles = GenUniModulesTangUiXComponentsTInputIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTInputIndex.setup(props as GenUniModulesTangUiXComponentsTInputIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTInputIndex {
    return GenUniModulesTangUiXComponentsTInputIndex(instance)
}
)
open class FormData (
    @JsonNotNull
    open var phoneNumber: String,
    @JsonNotNull
    open var password: String,
    @JsonNotNull
    open var verifyCode: String,
    @JsonNotNull
    open var isChecked: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return FormDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class FormDataReactiveObject : FormData, IUTSReactive<FormData> {
    override var __v_raw: FormData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: FormData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(phoneNumber = __v_raw.phoneNumber, password = __v_raw.password, verifyCode = __v_raw.verifyCode, isChecked = __v_raw.isChecked) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): FormDataReactiveObject {
        return FormDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var phoneNumber: String
        get() {
            return _tRG(__v_raw, "phoneNumber", __v_raw.phoneNumber, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("phoneNumber")) {
                return
            }
            val oldValue = __v_raw.phoneNumber
            __v_raw.phoneNumber = value
            _tRS(__v_raw, "phoneNumber", oldValue, value)
        }
    override var password: String
        get() {
            return _tRG(__v_raw, "password", __v_raw.password, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("password")) {
                return
            }
            val oldValue = __v_raw.password
            __v_raw.password = value
            _tRS(__v_raw, "password", oldValue, value)
        }
    override var verifyCode: String
        get() {
            return _tRG(__v_raw, "verifyCode", __v_raw.verifyCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("verifyCode")) {
                return
            }
            val oldValue = __v_raw.verifyCode
            __v_raw.verifyCode = value
            _tRS(__v_raw, "verifyCode", oldValue, value)
        }
    override var isChecked: Boolean
        get() {
            return _tRG(__v_raw, "isChecked", __v_raw.isChecked, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("isChecked")) {
                return
            }
            val oldValue = __v_raw.isChecked
            __v_raw.isChecked = value
            _tRS(__v_raw, "isChecked", oldValue, value)
        }
}
open class LoginMethodItem (
    @JsonNotNull
    open var title: String,
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var color: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return LoginMethodItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class LoginMethodItemReactiveObject : LoginMethodItem, IUTSReactive<LoginMethodItem> {
    override var __v_raw: LoginMethodItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: LoginMethodItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(title = __v_raw.title, icon = __v_raw.icon, color = __v_raw.color) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): LoginMethodItemReactiveObject {
        return LoginMethodItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var title: String
        get() {
            return _tRG(__v_raw, "title", __v_raw.title, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("title")) {
                return
            }
            val oldValue = __v_raw.title
            __v_raw.title = value
            _tRS(__v_raw, "title", oldValue, value)
        }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
    override var color: String
        get() {
            return _tRG(__v_raw, "color", __v_raw.color, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("color")) {
                return
            }
            val oldValue = __v_raw.color
            __v_raw.color = value
            _tRS(__v_raw, "color", oldValue, value)
        }
}
val GenPagesAuthLoginClass = CreateVueComponent(GenPagesAuthLogin::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesAuthLogin.inheritAttrs, inject = GenPagesAuthLogin.inject, props = GenPagesAuthLogin.props, propsNeedCastKeys = GenPagesAuthLogin.propsNeedCastKeys, emits = GenPagesAuthLogin.emits, components = GenPagesAuthLogin.components, styles = GenPagesAuthLogin.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesAuthLogin.setup(props as GenPagesAuthLogin)
    }
    )
}
, fun(instance, renderer): GenPagesAuthLogin {
    return GenPagesAuthLogin(instance, renderer)
}
)
open class RegisterFormData (
    @JsonNotNull
    open var phoneNumber: String,
    @JsonNotNull
    open var password: String,
    @JsonNotNull
    open var confirmPassword: String,
    @JsonNotNull
    open var verifyCode: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return RegisterFormDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class RegisterFormDataReactiveObject : RegisterFormData, IUTSReactive<RegisterFormData> {
    override var __v_raw: RegisterFormData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: RegisterFormData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(phoneNumber = __v_raw.phoneNumber, password = __v_raw.password, confirmPassword = __v_raw.confirmPassword, verifyCode = __v_raw.verifyCode) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): RegisterFormDataReactiveObject {
        return RegisterFormDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var phoneNumber: String
        get() {
            return _tRG(__v_raw, "phoneNumber", __v_raw.phoneNumber, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("phoneNumber")) {
                return
            }
            val oldValue = __v_raw.phoneNumber
            __v_raw.phoneNumber = value
            _tRS(__v_raw, "phoneNumber", oldValue, value)
        }
    override var password: String
        get() {
            return _tRG(__v_raw, "password", __v_raw.password, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("password")) {
                return
            }
            val oldValue = __v_raw.password
            __v_raw.password = value
            _tRS(__v_raw, "password", oldValue, value)
        }
    override var confirmPassword: String
        get() {
            return _tRG(__v_raw, "confirmPassword", __v_raw.confirmPassword, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("confirmPassword")) {
                return
            }
            val oldValue = __v_raw.confirmPassword
            __v_raw.confirmPassword = value
            _tRS(__v_raw, "confirmPassword", oldValue, value)
        }
    override var verifyCode: String
        get() {
            return _tRG(__v_raw, "verifyCode", __v_raw.verifyCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("verifyCode")) {
                return
            }
            val oldValue = __v_raw.verifyCode
            __v_raw.verifyCode = value
            _tRS(__v_raw, "verifyCode", oldValue, value)
        }
}
val GenPagesAuthRegisterClass = CreateVueComponent(GenPagesAuthRegister::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesAuthRegister.inheritAttrs, inject = GenPagesAuthRegister.inject, props = GenPagesAuthRegister.props, propsNeedCastKeys = GenPagesAuthRegister.propsNeedCastKeys, emits = GenPagesAuthRegister.emits, components = GenPagesAuthRegister.components, styles = GenPagesAuthRegister.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesAuthRegister.setup(props as GenPagesAuthRegister)
    }
    )
}
, fun(instance, renderer): GenPagesAuthRegister {
    return GenPagesAuthRegister(instance, renderer)
}
)
val GenPagesCommonAuthWaitAuditClass = CreateVueComponent(GenPagesCommonAuthWaitAudit::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesCommonAuthWaitAudit.inheritAttrs, inject = GenPagesCommonAuthWaitAudit.inject, props = GenPagesCommonAuthWaitAudit.props, propsNeedCastKeys = GenPagesCommonAuthWaitAudit.propsNeedCastKeys, emits = GenPagesCommonAuthWaitAudit.emits, components = GenPagesCommonAuthWaitAudit.components, styles = GenPagesCommonAuthWaitAudit.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesCommonAuthWaitAudit.setup(props as GenPagesCommonAuthWaitAudit)
    }
    )
}
, fun(instance, renderer): GenPagesCommonAuthWaitAudit {
    return GenPagesCommonAuthWaitAudit(instance, renderer)
}
)
val GenPagesCommonRoleSelectIndexClass = CreateVueComponent(GenPagesCommonRoleSelectIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesCommonRoleSelectIndex.inheritAttrs, inject = GenPagesCommonRoleSelectIndex.inject, props = GenPagesCommonRoleSelectIndex.props, propsNeedCastKeys = GenPagesCommonRoleSelectIndex.propsNeedCastKeys, emits = GenPagesCommonRoleSelectIndex.emits, components = GenPagesCommonRoleSelectIndex.components, styles = GenPagesCommonRoleSelectIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesCommonRoleSelectIndex.setup(props as GenPagesCommonRoleSelectIndex)
    }
    )
}
, fun(instance, renderer): GenPagesCommonRoleSelectIndex {
    return GenPagesCommonRoleSelectIndex(instance, renderer)
}
)
val GenComponentsPostCardIndexClass = CreateVueComponent(GenComponentsPostCardIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPostCardIndex.inheritAttrs, inject = GenComponentsPostCardIndex.inject, props = GenComponentsPostCardIndex.props, propsNeedCastKeys = GenComponentsPostCardIndex.propsNeedCastKeys, emits = GenComponentsPostCardIndex.emits, components = GenComponentsPostCardIndex.components, styles = GenComponentsPostCardIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPostCardIndex.setup(props as GenComponentsPostCardIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsPostCardIndex {
    return GenComponentsPostCardIndex(instance)
}
)
val GenComponentsSharePopupIndexClass = CreateVueComponent(GenComponentsSharePopupIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsSharePopupIndex.inheritAttrs, inject = GenComponentsSharePopupIndex.inject, props = GenComponentsSharePopupIndex.props, propsNeedCastKeys = GenComponentsSharePopupIndex.propsNeedCastKeys, emits = GenComponentsSharePopupIndex.emits, components = GenComponentsSharePopupIndex.components, styles = GenComponentsSharePopupIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsSharePopupIndex.setup(props as GenComponentsSharePopupIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsSharePopupIndex {
    return GenComponentsSharePopupIndex(instance)
}
)
typealias TabKey = String
val GenPagesApplyTabbarCommunityComponentsHeaderClass = CreateVueComponent(GenPagesApplyTabbarCommunityComponentsHeader::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarCommunityComponentsHeader.inheritAttrs, inject = GenPagesApplyTabbarCommunityComponentsHeader.inject, props = GenPagesApplyTabbarCommunityComponentsHeader.props, propsNeedCastKeys = GenPagesApplyTabbarCommunityComponentsHeader.propsNeedCastKeys, emits = GenPagesApplyTabbarCommunityComponentsHeader.emits, components = GenPagesApplyTabbarCommunityComponentsHeader.components, styles = GenPagesApplyTabbarCommunityComponentsHeader.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarCommunityComponentsHeader.setup(props as GenPagesApplyTabbarCommunityComponentsHeader)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarCommunityComponentsHeader {
    return GenPagesApplyTabbarCommunityComponentsHeader(instance)
}
)
val GenPagesApplyTabbarCommunityComponentsPublishClass = CreateVueComponent(GenPagesApplyTabbarCommunityComponentsPublish::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarCommunityComponentsPublish.inheritAttrs, inject = GenPagesApplyTabbarCommunityComponentsPublish.inject, props = GenPagesApplyTabbarCommunityComponentsPublish.props, propsNeedCastKeys = GenPagesApplyTabbarCommunityComponentsPublish.propsNeedCastKeys, emits = GenPagesApplyTabbarCommunityComponentsPublish.emits, components = GenPagesApplyTabbarCommunityComponentsPublish.components, styles = GenPagesApplyTabbarCommunityComponentsPublish.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarCommunityComponentsPublish.setup(props as GenPagesApplyTabbarCommunityComponentsPublish)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarCommunityComponentsPublish {
    return GenPagesApplyTabbarCommunityComponentsPublish(instance)
}
)
val GenPagesApplyTabbarCommunityIndexClass = CreateVueComponent(GenPagesApplyTabbarCommunityIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyTabbarCommunityIndex.inheritAttrs, inject = GenPagesApplyTabbarCommunityIndex.inject, props = GenPagesApplyTabbarCommunityIndex.props, propsNeedCastKeys = GenPagesApplyTabbarCommunityIndex.propsNeedCastKeys, emits = GenPagesApplyTabbarCommunityIndex.emits, components = GenPagesApplyTabbarCommunityIndex.components, styles = GenPagesApplyTabbarCommunityIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarCommunityIndex.setup(props as GenPagesApplyTabbarCommunityIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarCommunityIndex {
    return GenPagesApplyTabbarCommunityIndex(instance, renderer)
}
)
val GenComponentsPointsPointsHeaderClass = CreateVueComponent(GenComponentsPointsPointsHeader::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsPointsHeader.inheritAttrs, inject = GenComponentsPointsPointsHeader.inject, props = GenComponentsPointsPointsHeader.props, propsNeedCastKeys = GenComponentsPointsPointsHeader.propsNeedCastKeys, emits = GenComponentsPointsPointsHeader.emits, components = GenComponentsPointsPointsHeader.components, styles = GenComponentsPointsPointsHeader.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsPointsHeader.setup(props as GenComponentsPointsPointsHeader)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsPointsHeader {
    return GenComponentsPointsPointsHeader(instance)
}
)
val GenUniModulesTangUiXComponentsTSwitchIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTSwitchIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTSwitchIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTSwitchIndex.inject, props = GenUniModulesTangUiXComponentsTSwitchIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTSwitchIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTSwitchIndex.emits, components = GenUniModulesTangUiXComponentsTSwitchIndex.components, styles = GenUniModulesTangUiXComponentsTSwitchIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTSwitchIndex.setup(props as GenUniModulesTangUiXComponentsTSwitchIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTSwitchIndex {
    return GenUniModulesTangUiXComponentsTSwitchIndex(instance)
}
)
val GenComponentsPointsSignInCardClass = CreateVueComponent(GenComponentsPointsSignInCard::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsSignInCard.inheritAttrs, inject = GenComponentsPointsSignInCard.inject, props = GenComponentsPointsSignInCard.props, propsNeedCastKeys = GenComponentsPointsSignInCard.propsNeedCastKeys, emits = GenComponentsPointsSignInCard.emits, components = GenComponentsPointsSignInCard.components, styles = GenComponentsPointsSignInCard.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsSignInCard.setup(props as GenComponentsPointsSignInCard)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsSignInCard {
    return GenComponentsPointsSignInCard(instance)
}
)
val GenComponentsPointsWinnerCarouselClass = CreateVueComponent(GenComponentsPointsWinnerCarousel::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsWinnerCarousel.inheritAttrs, inject = GenComponentsPointsWinnerCarousel.inject, props = GenComponentsPointsWinnerCarousel.props, propsNeedCastKeys = GenComponentsPointsWinnerCarousel.propsNeedCastKeys, emits = GenComponentsPointsWinnerCarousel.emits, components = GenComponentsPointsWinnerCarousel.components, styles = GenComponentsPointsWinnerCarousel.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsWinnerCarousel.setup(props as GenComponentsPointsWinnerCarousel)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsWinnerCarousel {
    return GenComponentsPointsWinnerCarousel(instance)
}
)
val GenComponentsPointsRankListClass = CreateVueComponent(GenComponentsPointsRankList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsRankList.inheritAttrs, inject = GenComponentsPointsRankList.inject, props = GenComponentsPointsRankList.props, propsNeedCastKeys = GenComponentsPointsRankList.propsNeedCastKeys, emits = GenComponentsPointsRankList.emits, components = GenComponentsPointsRankList.components, styles = GenComponentsPointsRankList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsRankList.setup(props as GenComponentsPointsRankList)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsRankList {
    return GenComponentsPointsRankList(instance)
}
)
val GenComponentsPointsRuleTitleClass = CreateVueComponent(GenComponentsPointsRuleTitle::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsRuleTitle.inheritAttrs, inject = GenComponentsPointsRuleTitle.inject, props = GenComponentsPointsRuleTitle.props, propsNeedCastKeys = GenComponentsPointsRuleTitle.propsNeedCastKeys, emits = GenComponentsPointsRuleTitle.emits, components = GenComponentsPointsRuleTitle.components, styles = GenComponentsPointsRuleTitle.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsRuleTitle.setup(props as GenComponentsPointsRuleTitle)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsRuleTitle {
    return GenComponentsPointsRuleTitle(instance)
}
)
val GenComponentsPointsTaskListClass = CreateVueComponent(GenComponentsPointsTaskList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsTaskList.inheritAttrs, inject = GenComponentsPointsTaskList.inject, props = GenComponentsPointsTaskList.props, propsNeedCastKeys = GenComponentsPointsTaskList.propsNeedCastKeys, emits = GenComponentsPointsTaskList.emits, components = GenComponentsPointsTaskList.components, styles = GenComponentsPointsTaskList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsTaskList.setup(props as GenComponentsPointsTaskList)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsTaskList {
    return GenComponentsPointsTaskList(instance)
}
)
open class RewardItem (
    @JsonNotNull
    open var points: Number,
    @JsonNotNull
    open var times: Number,
) : UTSObject()
val GenComponentsPointsVideoRewardsClass = CreateVueComponent(GenComponentsPointsVideoRewards::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsVideoRewards.inheritAttrs, inject = GenComponentsPointsVideoRewards.inject, props = GenComponentsPointsVideoRewards.props, propsNeedCastKeys = GenComponentsPointsVideoRewards.propsNeedCastKeys, emits = GenComponentsPointsVideoRewards.emits, components = GenComponentsPointsVideoRewards.components, styles = GenComponentsPointsVideoRewards.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsPointsVideoRewards.setup(props as GenComponentsPointsVideoRewards)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsVideoRewards {
    return GenComponentsPointsVideoRewards(instance)
}
)
open class Prize (
    @JsonNotNull
    open var id: Number,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var icon: String,
    open var image: String? = null,
    open var probability: Number? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PrizeReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PrizeReactiveObject : Prize, IUTSReactive<Prize> {
    override var __v_raw: Prize
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: Prize, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, name = __v_raw.name, icon = __v_raw.icon, image = __v_raw.image, probability = __v_raw.probability) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PrizeReactiveObject {
        return PrizeReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var id: Number
        get() {
            return _tRG(__v_raw, "id", __v_raw.id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("id")) {
                return
            }
            val oldValue = __v_raw.id
            __v_raw.id = value
            _tRS(__v_raw, "id", oldValue, value)
        }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
    override var image: String?
        get() {
            return _tRG(__v_raw, "image", __v_raw.image, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("image")) {
                return
            }
            val oldValue = __v_raw.image
            __v_raw.image = value
            _tRS(__v_raw, "image", oldValue, value)
        }
    override var probability: Number?
        get() {
            return _tRG(__v_raw, "probability", __v_raw.probability, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("probability")) {
                return
            }
            val oldValue = __v_raw.probability
            __v_raw.probability = value
            _tRS(__v_raw, "probability", oldValue, value)
        }
}
val GenComponentsPointsLuckyWheelClass = CreateVueComponent(GenComponentsPointsLuckyWheel::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsLuckyWheel.inheritAttrs, inject = GenComponentsPointsLuckyWheel.inject, props = GenComponentsPointsLuckyWheel.props, propsNeedCastKeys = GenComponentsPointsLuckyWheel.propsNeedCastKeys, emits = GenComponentsPointsLuckyWheel.emits, components = GenComponentsPointsLuckyWheel.components, styles = GenComponentsPointsLuckyWheel.styles, setup = fun(props: ComponentPublicInstance, ctx: SetupContext): Any? {
        return GenComponentsPointsLuckyWheel.setup(props as GenComponentsPointsLuckyWheel, ctx)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsLuckyWheel {
    return GenComponentsPointsLuckyWheel(instance)
}
)
val GenComponentsPointsLotteryTicketClass = CreateVueComponent(GenComponentsPointsLotteryTicket::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsPointsLotteryTicket.inheritAttrs, inject = GenComponentsPointsLotteryTicket.inject, props = GenComponentsPointsLotteryTicket.props, propsNeedCastKeys = GenComponentsPointsLotteryTicket.propsNeedCastKeys, emits = GenComponentsPointsLotteryTicket.emits, components = GenComponentsPointsLotteryTicket.components, styles = GenComponentsPointsLotteryTicket.styles, setup = fun(props: ComponentPublicInstance, ctx: SetupContext): Any? {
        return GenComponentsPointsLotteryTicket.setup(props as GenComponentsPointsLotteryTicket, ctx)
    }
    )
}
, fun(instance, renderer): GenComponentsPointsLotteryTicket {
    return GenComponentsPointsLotteryTicket(instance)
}
)
open class BenefitItem (
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var desc: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return BenefitItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class BenefitItemReactiveObject : BenefitItem, IUTSReactive<BenefitItem> {
    override var __v_raw: BenefitItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: BenefitItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(icon = __v_raw.icon, label = __v_raw.label, desc = __v_raw.desc) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): BenefitItemReactiveObject {
        return BenefitItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var desc: String
        get() {
            return _tRG(__v_raw, "desc", __v_raw.desc, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("desc")) {
                return
            }
            val oldValue = __v_raw.desc
            __v_raw.desc = value
            _tRS(__v_raw, "desc", oldValue, value)
        }
}
val GenComponentsAuthGuideIndexClass = CreateVueComponent(GenComponentsAuthGuideIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsAuthGuideIndex.inheritAttrs, inject = GenComponentsAuthGuideIndex.inject, props = GenComponentsAuthGuideIndex.props, propsNeedCastKeys = GenComponentsAuthGuideIndex.propsNeedCastKeys, emits = GenComponentsAuthGuideIndex.emits, components = GenComponentsAuthGuideIndex.components, styles = GenComponentsAuthGuideIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsAuthGuideIndex.setup(props as GenComponentsAuthGuideIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsAuthGuideIndex {
    return GenComponentsAuthGuideIndex(instance)
}
)
interface LuckyWheelInstance {
    var play: (_id: Number) -> Unit
}
interface LotteryTicketInstance {
    var play: (_targetNumbers: UTSArray<String>, _isWin: Boolean) -> Unit
}
val GenPagesApplyTabbarMakeIndexClass = CreateVueComponent(GenPagesApplyTabbarMakeIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyTabbarMakeIndex.inheritAttrs, inject = GenPagesApplyTabbarMakeIndex.inject, props = GenPagesApplyTabbarMakeIndex.props, propsNeedCastKeys = GenPagesApplyTabbarMakeIndex.propsNeedCastKeys, emits = GenPagesApplyTabbarMakeIndex.emits, components = GenPagesApplyTabbarMakeIndex.components, styles = GenPagesApplyTabbarMakeIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMakeIndex.setup(props as GenPagesApplyTabbarMakeIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMakeIndex {
    return GenPagesApplyTabbarMakeIndex(instance, renderer)
}
)
val GenPagesCommonMessageComponentsItemClass = CreateVueComponent(GenPagesCommonMessageComponentsItem::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesCommonMessageComponentsItem.inheritAttrs, inject = GenPagesCommonMessageComponentsItem.inject, props = GenPagesCommonMessageComponentsItem.props, propsNeedCastKeys = GenPagesCommonMessageComponentsItem.propsNeedCastKeys, emits = GenPagesCommonMessageComponentsItem.emits, components = GenPagesCommonMessageComponentsItem.components, styles = GenPagesCommonMessageComponentsItem.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesCommonMessageComponentsItem.setup(props as GenPagesCommonMessageComponentsItem)
    }
    )
}
, fun(instance, renderer): GenPagesCommonMessageComponentsItem {
    return GenPagesCommonMessageComponentsItem(instance)
}
)
val GenPagesCommonMessageIndexClass = CreateVueComponent(GenPagesCommonMessageIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesCommonMessageIndex.inheritAttrs, inject = GenPagesCommonMessageIndex.inject, props = GenPagesCommonMessageIndex.props, propsNeedCastKeys = GenPagesCommonMessageIndex.propsNeedCastKeys, emits = GenPagesCommonMessageIndex.emits, components = GenPagesCommonMessageIndex.components, styles = GenPagesCommonMessageIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesCommonMessageIndex.setup(props as GenPagesCommonMessageIndex)
    }
    )
}
, fun(instance, renderer): GenPagesCommonMessageIndex {
    return GenPagesCommonMessageIndex(instance, renderer)
}
)
open class UserInfo (
    @JsonNotNull
    open var id: String,
    @JsonNotNull
    open var avatar: String,
    @JsonNotNull
    open var nickname: String,
    @JsonNotNull
    open var resumeProgress: Number,
    @JsonNotNull
    open var resumeTip: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UserInfoReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class UserInfoReactiveObject : UserInfo, IUTSReactive<UserInfo> {
    override var __v_raw: UserInfo
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: UserInfo, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, avatar = __v_raw.avatar, nickname = __v_raw.nickname, resumeProgress = __v_raw.resumeProgress, resumeTip = __v_raw.resumeTip) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UserInfoReactiveObject {
        return UserInfoReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var id: String
        get() {
            return _tRG(__v_raw, "id", __v_raw.id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("id")) {
                return
            }
            val oldValue = __v_raw.id
            __v_raw.id = value
            _tRS(__v_raw, "id", oldValue, value)
        }
    override var avatar: String
        get() {
            return _tRG(__v_raw, "avatar", __v_raw.avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatar")) {
                return
            }
            val oldValue = __v_raw.avatar
            __v_raw.avatar = value
            _tRS(__v_raw, "avatar", oldValue, value)
        }
    override var nickname: String
        get() {
            return _tRG(__v_raw, "nickname", __v_raw.nickname, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("nickname")) {
                return
            }
            val oldValue = __v_raw.nickname
            __v_raw.nickname = value
            _tRS(__v_raw, "nickname", oldValue, value)
        }
    override var resumeProgress: Number
        get() {
            return _tRG(__v_raw, "resumeProgress", __v_raw.resumeProgress, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("resumeProgress")) {
                return
            }
            val oldValue = __v_raw.resumeProgress
            __v_raw.resumeProgress = value
            _tRS(__v_raw, "resumeProgress", oldValue, value)
        }
    override var resumeTip: String
        get() {
            return _tRG(__v_raw, "resumeTip", __v_raw.resumeTip, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("resumeTip")) {
                return
            }
            val oldValue = __v_raw.resumeTip
            __v_raw.resumeTip = value
            _tRS(__v_raw, "resumeTip", oldValue, value)
        }
}
open class UserStats (
    @JsonNotNull
    open var chatCount: Number,
    @JsonNotNull
    open var interviewCount: Number,
    @JsonNotNull
    open var viewedCount: Number,
    @JsonNotNull
    open var favoriteCount: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UserStatsReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class UserStatsReactiveObject : UserStats, IUTSReactive<UserStats> {
    override var __v_raw: UserStats
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: UserStats, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(chatCount = __v_raw.chatCount, interviewCount = __v_raw.interviewCount, viewedCount = __v_raw.viewedCount, favoriteCount = __v_raw.favoriteCount) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UserStatsReactiveObject {
        return UserStatsReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var chatCount: Number
        get() {
            return _tRG(__v_raw, "chatCount", __v_raw.chatCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("chatCount")) {
                return
            }
            val oldValue = __v_raw.chatCount
            __v_raw.chatCount = value
            _tRS(__v_raw, "chatCount", oldValue, value)
        }
    override var interviewCount: Number
        get() {
            return _tRG(__v_raw, "interviewCount", __v_raw.interviewCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("interviewCount")) {
                return
            }
            val oldValue = __v_raw.interviewCount
            __v_raw.interviewCount = value
            _tRS(__v_raw, "interviewCount", oldValue, value)
        }
    override var viewedCount: Number
        get() {
            return _tRG(__v_raw, "viewedCount", __v_raw.viewedCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("viewedCount")) {
                return
            }
            val oldValue = __v_raw.viewedCount
            __v_raw.viewedCount = value
            _tRS(__v_raw, "viewedCount", oldValue, value)
        }
    override var favoriteCount: Number
        get() {
            return _tRG(__v_raw, "favoriteCount", __v_raw.favoriteCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("favoriteCount")) {
                return
            }
            val oldValue = __v_raw.favoriteCount
            __v_raw.favoriteCount = value
            _tRS(__v_raw, "favoriteCount", oldValue, value)
        }
}
open class WalletInfo (
    @JsonNotNull
    open var balance: Number,
    @JsonNotNull
    open var points: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return WalletInfoReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class WalletInfoReactiveObject : WalletInfo, IUTSReactive<WalletInfo> {
    override var __v_raw: WalletInfo
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: WalletInfo, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(balance = __v_raw.balance, points = __v_raw.points) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): WalletInfoReactiveObject {
        return WalletInfoReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var balance: Number
        get() {
            return _tRG(__v_raw, "balance", __v_raw.balance, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("balance")) {
                return
            }
            val oldValue = __v_raw.balance
            __v_raw.balance = value
            _tRS(__v_raw, "balance", oldValue, value)
        }
    override var points: Number
        get() {
            return _tRG(__v_raw, "points", __v_raw.points, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("points")) {
                return
            }
            val oldValue = __v_raw.points
            __v_raw.points = value
            _tRS(__v_raw, "points", oldValue, value)
        }
}
open class FeatureMenuItem (
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var path: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return FeatureMenuItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class FeatureMenuItemReactiveObject : FeatureMenuItem, IUTSReactive<FeatureMenuItem> {
    override var __v_raw: FeatureMenuItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: FeatureMenuItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(icon = __v_raw.icon, label = __v_raw.label, path = __v_raw.path) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): FeatureMenuItemReactiveObject {
        return FeatureMenuItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var path: String
        get() {
            return _tRG(__v_raw, "path", __v_raw.path, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("path")) {
                return
            }
            val oldValue = __v_raw.path
            __v_raw.path = value
            _tRS(__v_raw, "path", oldValue, value)
        }
}
open class PosterUserInfo (
    @JsonNotNull
    open var avatarUrl: String,
    @JsonNotNull
    open var inviteCode: String,
    @JsonNotNull
    open var line1: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PosterUserInfoReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PosterUserInfoReactiveObject : PosterUserInfo, IUTSReactive<PosterUserInfo> {
    override var __v_raw: PosterUserInfo
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PosterUserInfo, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(avatarUrl = __v_raw.avatarUrl, inviteCode = __v_raw.inviteCode, line1 = __v_raw.line1) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PosterUserInfoReactiveObject {
        return PosterUserInfoReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var avatarUrl: String
        get() {
            return _tRG(__v_raw, "avatarUrl", __v_raw.avatarUrl, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatarUrl")) {
                return
            }
            val oldValue = __v_raw.avatarUrl
            __v_raw.avatarUrl = value
            _tRS(__v_raw, "avatarUrl", oldValue, value)
        }
    override var inviteCode: String
        get() {
            return _tRG(__v_raw, "inviteCode", __v_raw.inviteCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("inviteCode")) {
                return
            }
            val oldValue = __v_raw.inviteCode
            __v_raw.inviteCode = value
            _tRS(__v_raw, "inviteCode", oldValue, value)
        }
    override var line1: String
        get() {
            return _tRG(__v_raw, "line1", __v_raw.line1, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("line1")) {
                return
            }
            val oldValue = __v_raw.line1
            __v_raw.line1 = value
            _tRS(__v_raw, "line1", oldValue, value)
        }
}
val GenPagesApplyTabbarMyComponentsUserHeaderClass = CreateVueComponent(GenPagesApplyTabbarMyComponentsUserHeader::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarMyComponentsUserHeader.inheritAttrs, inject = GenPagesApplyTabbarMyComponentsUserHeader.inject, props = GenPagesApplyTabbarMyComponentsUserHeader.props, propsNeedCastKeys = GenPagesApplyTabbarMyComponentsUserHeader.propsNeedCastKeys, emits = GenPagesApplyTabbarMyComponentsUserHeader.emits, components = GenPagesApplyTabbarMyComponentsUserHeader.components, styles = GenPagesApplyTabbarMyComponentsUserHeader.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMyComponentsUserHeader.setup(props as GenPagesApplyTabbarMyComponentsUserHeader)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMyComponentsUserHeader {
    return GenPagesApplyTabbarMyComponentsUserHeader(instance)
}
)
val GenPagesApplyTabbarMyComponentsWalletCardClass = CreateVueComponent(GenPagesApplyTabbarMyComponentsWalletCard::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarMyComponentsWalletCard.inheritAttrs, inject = GenPagesApplyTabbarMyComponentsWalletCard.inject, props = GenPagesApplyTabbarMyComponentsWalletCard.props, propsNeedCastKeys = GenPagesApplyTabbarMyComponentsWalletCard.propsNeedCastKeys, emits = GenPagesApplyTabbarMyComponentsWalletCard.emits, components = GenPagesApplyTabbarMyComponentsWalletCard.components, styles = GenPagesApplyTabbarMyComponentsWalletCard.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMyComponentsWalletCard.setup(props as GenPagesApplyTabbarMyComponentsWalletCard)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMyComponentsWalletCard {
    return GenPagesApplyTabbarMyComponentsWalletCard(instance)
}
)
val GenComponentsCommonFeatureGridIndexClass = CreateVueComponent(GenComponentsCommonFeatureGridIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsCommonFeatureGridIndex.inheritAttrs, inject = GenComponentsCommonFeatureGridIndex.inject, props = GenComponentsCommonFeatureGridIndex.props, propsNeedCastKeys = GenComponentsCommonFeatureGridIndex.propsNeedCastKeys, emits = GenComponentsCommonFeatureGridIndex.emits, components = GenComponentsCommonFeatureGridIndex.components, styles = GenComponentsCommonFeatureGridIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenComponentsCommonFeatureGridIndex.setup(props as GenComponentsCommonFeatureGridIndex)
    }
    )
}
, fun(instance, renderer): GenComponentsCommonFeatureGridIndex {
    return GenComponentsCommonFeatureGridIndex(instance)
}
)
val GenPagesApplyTabbarMyComponentsQuickFeaturesClass = CreateVueComponent(GenPagesApplyTabbarMyComponentsQuickFeatures::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarMyComponentsQuickFeatures.inheritAttrs, inject = GenPagesApplyTabbarMyComponentsQuickFeatures.inject, props = GenPagesApplyTabbarMyComponentsQuickFeatures.props, propsNeedCastKeys = GenPagesApplyTabbarMyComponentsQuickFeatures.propsNeedCastKeys, emits = GenPagesApplyTabbarMyComponentsQuickFeatures.emits, components = GenPagesApplyTabbarMyComponentsQuickFeatures.components, styles = GenPagesApplyTabbarMyComponentsQuickFeatures.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMyComponentsQuickFeatures.setup(props as GenPagesApplyTabbarMyComponentsQuickFeatures)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMyComponentsQuickFeatures {
    return GenPagesApplyTabbarMyComponentsQuickFeatures(instance)
}
)
val GenPagesApplyTabbarMyComponentsAllianceCenterClass = CreateVueComponent(GenPagesApplyTabbarMyComponentsAllianceCenter::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarMyComponentsAllianceCenter.inheritAttrs, inject = GenPagesApplyTabbarMyComponentsAllianceCenter.inject, props = GenPagesApplyTabbarMyComponentsAllianceCenter.props, propsNeedCastKeys = GenPagesApplyTabbarMyComponentsAllianceCenter.propsNeedCastKeys, emits = GenPagesApplyTabbarMyComponentsAllianceCenter.emits, components = GenPagesApplyTabbarMyComponentsAllianceCenter.components, styles = GenPagesApplyTabbarMyComponentsAllianceCenter.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMyComponentsAllianceCenter.setup(props as GenPagesApplyTabbarMyComponentsAllianceCenter)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMyComponentsAllianceCenter {
    return GenPagesApplyTabbarMyComponentsAllianceCenter(instance)
}
)
val GenPagesApplyTabbarMyComponentsOtherFeaturesClass = CreateVueComponent(GenPagesApplyTabbarMyComponentsOtherFeatures::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyTabbarMyComponentsOtherFeatures.inheritAttrs, inject = GenPagesApplyTabbarMyComponentsOtherFeatures.inject, props = GenPagesApplyTabbarMyComponentsOtherFeatures.props, propsNeedCastKeys = GenPagesApplyTabbarMyComponentsOtherFeatures.propsNeedCastKeys, emits = GenPagesApplyTabbarMyComponentsOtherFeatures.emits, components = GenPagesApplyTabbarMyComponentsOtherFeatures.components, styles = GenPagesApplyTabbarMyComponentsOtherFeatures.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMyComponentsOtherFeatures.setup(props as GenPagesApplyTabbarMyComponentsOtherFeatures)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMyComponentsOtherFeatures {
    return GenPagesApplyTabbarMyComponentsOtherFeatures(instance)
}
)
val GenPagesApplyTabbarMyIndexClass = CreateVueComponent(GenPagesApplyTabbarMyIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyTabbarMyIndex.inheritAttrs, inject = GenPagesApplyTabbarMyIndex.inject, props = GenPagesApplyTabbarMyIndex.props, propsNeedCastKeys = GenPagesApplyTabbarMyIndex.propsNeedCastKeys, emits = GenPagesApplyTabbarMyIndex.emits, components = GenPagesApplyTabbarMyIndex.components, styles = GenPagesApplyTabbarMyIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyTabbarMyIndex.setup(props as GenPagesApplyTabbarMyIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyTabbarMyIndex {
    return GenPagesApplyTabbarMyIndex(instance, renderer)
}
)
val GenPagesHireTabbarPositionComponentsPositionCardClass = CreateVueComponent(GenPagesHireTabbarPositionComponentsPositionCard::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesHireTabbarPositionComponentsPositionCard.inheritAttrs, inject = GenPagesHireTabbarPositionComponentsPositionCard.inject, props = GenPagesHireTabbarPositionComponentsPositionCard.props, propsNeedCastKeys = GenPagesHireTabbarPositionComponentsPositionCard.propsNeedCastKeys, emits = GenPagesHireTabbarPositionComponentsPositionCard.emits, components = GenPagesHireTabbarPositionComponentsPositionCard.components, styles = GenPagesHireTabbarPositionComponentsPositionCard.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarPositionComponentsPositionCard.setup(props as GenPagesHireTabbarPositionComponentsPositionCard)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarPositionComponentsPositionCard {
    return GenPagesHireTabbarPositionComponentsPositionCard(instance)
}
)
open class PositionListPageResult (
    @JsonNotNull
    open var data: UTSArray<GetPositionListResult>,
    @JsonNotNull
    open var total: Number,
) : UTSObject()
val GenPagesHireTabbarPositionIndexClass = CreateVueComponent(GenPagesHireTabbarPositionIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesHireTabbarPositionIndex.inheritAttrs, inject = GenPagesHireTabbarPositionIndex.inject, props = GenPagesHireTabbarPositionIndex.props, propsNeedCastKeys = GenPagesHireTabbarPositionIndex.propsNeedCastKeys, emits = GenPagesHireTabbarPositionIndex.emits, components = GenPagesHireTabbarPositionIndex.components, styles = GenPagesHireTabbarPositionIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarPositionIndex.setup(props as GenPagesHireTabbarPositionIndex)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarPositionIndex {
    return GenPagesHireTabbarPositionIndex(instance, renderer)
}
)
open class UserInfo__1 (
    @JsonNotNull
    open var id: Number,
    @JsonNotNull
    open var avatar: String,
    @JsonNotNull
    open var nickname: String,
    @JsonNotNull
    open var phone: String,
    @JsonNotNull
    open var isVerified: Boolean = false,
    @JsonNotNull
    open var isVip: Boolean = false,
    open var vipExpireTime: String? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UserInfo__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class UserInfo__1ReactiveObject : UserInfo__1, IUTSReactive<UserInfo__1> {
    override var __v_raw: UserInfo__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: UserInfo__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, avatar = __v_raw.avatar, nickname = __v_raw.nickname, phone = __v_raw.phone, isVerified = __v_raw.isVerified, isVip = __v_raw.isVip, vipExpireTime = __v_raw.vipExpireTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UserInfo__1ReactiveObject {
        return UserInfo__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var id: Number
        get() {
            return _tRG(__v_raw, "id", __v_raw.id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("id")) {
                return
            }
            val oldValue = __v_raw.id
            __v_raw.id = value
            _tRS(__v_raw, "id", oldValue, value)
        }
    override var avatar: String
        get() {
            return _tRG(__v_raw, "avatar", __v_raw.avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatar")) {
                return
            }
            val oldValue = __v_raw.avatar
            __v_raw.avatar = value
            _tRS(__v_raw, "avatar", oldValue, value)
        }
    override var nickname: String
        get() {
            return _tRG(__v_raw, "nickname", __v_raw.nickname, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("nickname")) {
                return
            }
            val oldValue = __v_raw.nickname
            __v_raw.nickname = value
            _tRS(__v_raw, "nickname", oldValue, value)
        }
    override var phone: String
        get() {
            return _tRG(__v_raw, "phone", __v_raw.phone, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("phone")) {
                return
            }
            val oldValue = __v_raw.phone
            __v_raw.phone = value
            _tRS(__v_raw, "phone", oldValue, value)
        }
    override var isVerified: Boolean
        get() {
            return _tRG(__v_raw, "isVerified", __v_raw.isVerified, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("isVerified")) {
                return
            }
            val oldValue = __v_raw.isVerified
            __v_raw.isVerified = value
            _tRS(__v_raw, "isVerified", oldValue, value)
        }
    override var isVip: Boolean
        get() {
            return _tRG(__v_raw, "isVip", __v_raw.isVip, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("isVip")) {
                return
            }
            val oldValue = __v_raw.isVip
            __v_raw.isVip = value
            _tRS(__v_raw, "isVip", oldValue, value)
        }
    override var vipExpireTime: String?
        get() {
            return _tRG(__v_raw, "vipExpireTime", __v_raw.vipExpireTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("vipExpireTime")) {
                return
            }
            val oldValue = __v_raw.vipExpireTime
            __v_raw.vipExpireTime = value
            _tRS(__v_raw, "vipExpireTime", oldValue, value)
        }
}
open class UserStats__1 (
    @JsonNotNull
    open var applicationCount: Number,
    @JsonNotNull
    open var interviewCount: Number,
    @JsonNotNull
    open var favoriteCount: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UserStats__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class UserStats__1ReactiveObject : UserStats__1, IUTSReactive<UserStats__1> {
    override var __v_raw: UserStats__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: UserStats__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(applicationCount = __v_raw.applicationCount, interviewCount = __v_raw.interviewCount, favoriteCount = __v_raw.favoriteCount) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UserStats__1ReactiveObject {
        return UserStats__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var applicationCount: Number
        get() {
            return _tRG(__v_raw, "applicationCount", __v_raw.applicationCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("applicationCount")) {
                return
            }
            val oldValue = __v_raw.applicationCount
            __v_raw.applicationCount = value
            _tRS(__v_raw, "applicationCount", oldValue, value)
        }
    override var interviewCount: Number
        get() {
            return _tRG(__v_raw, "interviewCount", __v_raw.interviewCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("interviewCount")) {
                return
            }
            val oldValue = __v_raw.interviewCount
            __v_raw.interviewCount = value
            _tRS(__v_raw, "interviewCount", oldValue, value)
        }
    override var favoriteCount: Number
        get() {
            return _tRG(__v_raw, "favoriteCount", __v_raw.favoriteCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("favoriteCount")) {
                return
            }
            val oldValue = __v_raw.favoriteCount
            __v_raw.favoriteCount = value
            _tRS(__v_raw, "favoriteCount", oldValue, value)
        }
}
open class CommonFeatureData (
    @JsonNotNull
    open var orderCount: Number,
    @JsonNotNull
    open var pointsCount: Number,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return CommonFeatureDataReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class CommonFeatureDataReactiveObject : CommonFeatureData, IUTSReactive<CommonFeatureData> {
    override var __v_raw: CommonFeatureData
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: CommonFeatureData, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(orderCount = __v_raw.orderCount, pointsCount = __v_raw.pointsCount) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): CommonFeatureDataReactiveObject {
        return CommonFeatureDataReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var orderCount: Number
        get() {
            return _tRG(__v_raw, "orderCount", __v_raw.orderCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("orderCount")) {
                return
            }
            val oldValue = __v_raw.orderCount
            __v_raw.orderCount = value
            _tRS(__v_raw, "orderCount", oldValue, value)
        }
    override var pointsCount: Number
        get() {
            return _tRG(__v_raw, "pointsCount", __v_raw.pointsCount, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("pointsCount")) {
                return
            }
            val oldValue = __v_raw.pointsCount
            __v_raw.pointsCount = value
            _tRS(__v_raw, "pointsCount", oldValue, value)
        }
}
open class FeatureMenuItem__1 (
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var path: String,
) : UTSObject()
val GenPagesHireTabbarMyComponentsUserHeaderClass = CreateVueComponent(GenPagesHireTabbarMyComponentsUserHeader::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesHireTabbarMyComponentsUserHeader.inheritAttrs, inject = GenPagesHireTabbarMyComponentsUserHeader.inject, props = GenPagesHireTabbarMyComponentsUserHeader.props, propsNeedCastKeys = GenPagesHireTabbarMyComponentsUserHeader.propsNeedCastKeys, emits = GenPagesHireTabbarMyComponentsUserHeader.emits, components = GenPagesHireTabbarMyComponentsUserHeader.components, styles = GenPagesHireTabbarMyComponentsUserHeader.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarMyComponentsUserHeader.setup(props as GenPagesHireTabbarMyComponentsUserHeader)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarMyComponentsUserHeader {
    return GenPagesHireTabbarMyComponentsUserHeader(instance)
}
)
val GenPagesHireTabbarMyComponentsCommonFeaturesClass = CreateVueComponent(GenPagesHireTabbarMyComponentsCommonFeatures::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesHireTabbarMyComponentsCommonFeatures.inheritAttrs, inject = GenPagesHireTabbarMyComponentsCommonFeatures.inject, props = GenPagesHireTabbarMyComponentsCommonFeatures.props, propsNeedCastKeys = GenPagesHireTabbarMyComponentsCommonFeatures.propsNeedCastKeys, emits = GenPagesHireTabbarMyComponentsCommonFeatures.emits, components = GenPagesHireTabbarMyComponentsCommonFeatures.components, styles = GenPagesHireTabbarMyComponentsCommonFeatures.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarMyComponentsCommonFeatures.setup(props as GenPagesHireTabbarMyComponentsCommonFeatures)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarMyComponentsCommonFeatures {
    return GenPagesHireTabbarMyComponentsCommonFeatures(instance)
}
)
val GenPagesHireTabbarMyComponentsOtherFeaturesClass = CreateVueComponent(GenPagesHireTabbarMyComponentsOtherFeatures::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesHireTabbarMyComponentsOtherFeatures.inheritAttrs, inject = GenPagesHireTabbarMyComponentsOtherFeatures.inject, props = GenPagesHireTabbarMyComponentsOtherFeatures.props, propsNeedCastKeys = GenPagesHireTabbarMyComponentsOtherFeatures.propsNeedCastKeys, emits = GenPagesHireTabbarMyComponentsOtherFeatures.emits, components = GenPagesHireTabbarMyComponentsOtherFeatures.components, styles = GenPagesHireTabbarMyComponentsOtherFeatures.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarMyComponentsOtherFeatures.setup(props as GenPagesHireTabbarMyComponentsOtherFeatures)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarMyComponentsOtherFeatures {
    return GenPagesHireTabbarMyComponentsOtherFeatures(instance)
}
)
val GenPagesHireTabbarMyIndexClass = CreateVueComponent(GenPagesHireTabbarMyIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesHireTabbarMyIndex.inheritAttrs, inject = GenPagesHireTabbarMyIndex.inject, props = GenPagesHireTabbarMyIndex.props, propsNeedCastKeys = GenPagesHireTabbarMyIndex.propsNeedCastKeys, emits = GenPagesHireTabbarMyIndex.emits, components = GenPagesHireTabbarMyIndex.components, styles = GenPagesHireTabbarMyIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesHireTabbarMyIndex.setup(props as GenPagesHireTabbarMyIndex)
    }
    )
}
, fun(instance, renderer): GenPagesHireTabbarMyIndex {
    return GenPagesHireTabbarMyIndex(instance, renderer)
}
)
open class ReplyTarget (
    @JsonNotNull
    open var CommentId: Number,
    @JsonNotNull
    open var NickName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ReplyTargetReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ReplyTargetReactiveObject : ReplyTarget, IUTSReactive<ReplyTarget> {
    override var __v_raw: ReplyTarget
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ReplyTarget, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(CommentId = __v_raw.CommentId, NickName = __v_raw.NickName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ReplyTargetReactiveObject {
        return ReplyTargetReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var CommentId: Number
        get() {
            return _tRG(__v_raw, "CommentId", __v_raw.CommentId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CommentId")) {
                return
            }
            val oldValue = __v_raw.CommentId
            __v_raw.CommentId = value
            _tRS(__v_raw, "CommentId", oldValue, value)
        }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
}
val GenPagesApplyCommunityDetailClass = CreateVueComponent(GenPagesApplyCommunityDetail::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyCommunityDetail.inheritAttrs, inject = GenPagesApplyCommunityDetail.inject, props = GenPagesApplyCommunityDetail.props, propsNeedCastKeys = GenPagesApplyCommunityDetail.propsNeedCastKeys, emits = GenPagesApplyCommunityDetail.emits, components = GenPagesApplyCommunityDetail.components, styles = GenPagesApplyCommunityDetail.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyCommunityDetail.setup(props as GenPagesApplyCommunityDetail)
    }
    )
}
, fun(instance, renderer): GenPagesApplyCommunityDetail {
    return GenPagesApplyCommunityDetail(instance, renderer)
}
)
open class ReplyTarget__1 (
    @JsonNotNull
    open var CommentId: Number,
    @JsonNotNull
    open var NickName: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ReplyTarget__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ReplyTarget__1ReactiveObject : ReplyTarget__1, IUTSReactive<ReplyTarget__1> {
    override var __v_raw: ReplyTarget__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ReplyTarget__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(CommentId = __v_raw.CommentId, NickName = __v_raw.NickName) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ReplyTarget__1ReactiveObject {
        return ReplyTarget__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var CommentId: Number
        get() {
            return _tRG(__v_raw, "CommentId", __v_raw.CommentId, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("CommentId")) {
                return
            }
            val oldValue = __v_raw.CommentId
            __v_raw.CommentId = value
            _tRS(__v_raw, "CommentId", oldValue, value)
        }
    override var NickName: String
        get() {
            return _tRG(__v_raw, "NickName", __v_raw.NickName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("NickName")) {
                return
            }
            val oldValue = __v_raw.NickName
            __v_raw.NickName = value
            _tRS(__v_raw, "NickName", oldValue, value)
        }
}
val GenPagesApplyCommunityPublishClass = CreateVueComponent(GenPagesApplyCommunityPublish::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyCommunityPublish.inheritAttrs, inject = GenPagesApplyCommunityPublish.inject, props = GenPagesApplyCommunityPublish.props, propsNeedCastKeys = GenPagesApplyCommunityPublish.propsNeedCastKeys, emits = GenPagesApplyCommunityPublish.emits, components = GenPagesApplyCommunityPublish.components, styles = GenPagesApplyCommunityPublish.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyCommunityPublish.setup(props as GenPagesApplyCommunityPublish)
    }
    )
}
, fun(instance, renderer): GenPagesApplyCommunityPublish {
    return GenPagesApplyCommunityPublish(instance, renderer)
}
)
val GenPagesApplyCommunityMyPostsClass = CreateVueComponent(GenPagesApplyCommunityMyPosts::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyCommunityMyPosts.inheritAttrs, inject = GenPagesApplyCommunityMyPosts.inject, props = GenPagesApplyCommunityMyPosts.props, propsNeedCastKeys = GenPagesApplyCommunityMyPosts.propsNeedCastKeys, emits = GenPagesApplyCommunityMyPosts.emits, components = GenPagesApplyCommunityMyPosts.components, styles = GenPagesApplyCommunityMyPosts.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyCommunityMyPosts.setup(props as GenPagesApplyCommunityMyPosts)
    }
    )
}
, fun(instance, renderer): GenPagesApplyCommunityMyPosts {
    return GenPagesApplyCommunityMyPosts(instance, renderer)
}
)
val GenPagesApplyResumeAttachmentClass = CreateVueComponent(GenPagesApplyResumeAttachment::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeAttachment.inheritAttrs, inject = GenPagesApplyResumeAttachment.inject, props = GenPagesApplyResumeAttachment.props, propsNeedCastKeys = GenPagesApplyResumeAttachment.propsNeedCastKeys, emits = GenPagesApplyResumeAttachment.emits, components = GenPagesApplyResumeAttachment.components, styles = GenPagesApplyResumeAttachment.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeAttachment.setup(props as GenPagesApplyResumeAttachment)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeAttachment {
    return GenPagesApplyResumeAttachment(instance, renderer)
}
)
open class UploadMethod (
    @JsonNotNull
    open var id: String,
    @JsonNotNull
    open var icon: String,
    @JsonNotNull
    open var title: String,
    open var desc: String? = null,
    @JsonNotNull
    open var color: String,
    open var recommend: Boolean? = null,
) : UTSObject()
val GenPagesApplyResumeUploadAttachmentClass = CreateVueComponent(GenPagesApplyResumeUploadAttachment::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeUploadAttachment.inheritAttrs, inject = GenPagesApplyResumeUploadAttachment.inject, props = GenPagesApplyResumeUploadAttachment.props, propsNeedCastKeys = GenPagesApplyResumeUploadAttachment.propsNeedCastKeys, emits = GenPagesApplyResumeUploadAttachment.emits, components = GenPagesApplyResumeUploadAttachment.components, styles = GenPagesApplyResumeUploadAttachment.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeUploadAttachment.setup(props as GenPagesApplyResumeUploadAttachment)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeUploadAttachment {
    return GenPagesApplyResumeUploadAttachment(instance, renderer)
}
)
open class RadioOption__1 (
    @JsonNotNull
    open var label: String,
    @JsonNotNull
    open var value: Any,
    open var disabled: Boolean? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return RadioOption__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class RadioOption__1ReactiveObject : RadioOption__1, IUTSReactive<RadioOption__1> {
    override var __v_raw: RadioOption__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: RadioOption__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(label = __v_raw.label, value = __v_raw.value, disabled = __v_raw.disabled) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): RadioOption__1ReactiveObject {
        return RadioOption__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var label: String
        get() {
            return _tRG(__v_raw, "label", __v_raw.label, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("label")) {
                return
            }
            val oldValue = __v_raw.label
            __v_raw.label = value
            _tRS(__v_raw, "label", oldValue, value)
        }
    override var value: Any
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
    override var disabled: Boolean?
        get() {
            return _tRG(__v_raw, "disabled", __v_raw.disabled, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("disabled")) {
                return
            }
            val oldValue = __v_raw.disabled
            __v_raw.disabled = value
            _tRS(__v_raw, "disabled", oldValue, value)
        }
}
typealias RadioGroupModelValue = Any
open class RadioSizeConfig (
    @JsonNotNull
    open var icon: Number,
    @JsonNotNull
    open var dot: Number,
    @JsonNotNull
    open var fontSize: Number,
) : UTSObject()
val GenUniModulesTangUiXComponentsTRadioGroupIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTRadioGroupIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTRadioGroupIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTRadioGroupIndex.inject, props = GenUniModulesTangUiXComponentsTRadioGroupIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTRadioGroupIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTRadioGroupIndex.emits, components = GenUniModulesTangUiXComponentsTRadioGroupIndex.components, styles = GenUniModulesTangUiXComponentsTRadioGroupIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTRadioGroupIndex.setup(props as GenUniModulesTangUiXComponentsTRadioGroupIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTRadioGroupIndex {
    return GenUniModulesTangUiXComponentsTRadioGroupIndex(instance)
}
)
open class UserCardInfo (
    @JsonNotNull
    open var avatar: String,
    open var avatarStatusText: String? = null,
    open var avatarStatus: Number? = null,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var age: Number,
    @JsonNotNull
    open var education: String,
    @JsonNotNull
    open var location: String,
    @JsonNotNull
    open var salary: String,
    @JsonNotNull
    open var status: String,
    @JsonNotNull
    open var phone: String,
    @JsonNotNull
    open var email: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return UserCardInfoReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class UserCardInfoReactiveObject : UserCardInfo, IUTSReactive<UserCardInfo> {
    override var __v_raw: UserCardInfo
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: UserCardInfo, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(avatar = __v_raw.avatar, avatarStatusText = __v_raw.avatarStatusText, avatarStatus = __v_raw.avatarStatus, name = __v_raw.name, age = __v_raw.age, education = __v_raw.education, location = __v_raw.location, salary = __v_raw.salary, status = __v_raw.status, phone = __v_raw.phone, email = __v_raw.email) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UserCardInfoReactiveObject {
        return UserCardInfoReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var avatar: String
        get() {
            return _tRG(__v_raw, "avatar", __v_raw.avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatar")) {
                return
            }
            val oldValue = __v_raw.avatar
            __v_raw.avatar = value
            _tRS(__v_raw, "avatar", oldValue, value)
        }
    override var avatarStatusText: String?
        get() {
            return _tRG(__v_raw, "avatarStatusText", __v_raw.avatarStatusText, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatarStatusText")) {
                return
            }
            val oldValue = __v_raw.avatarStatusText
            __v_raw.avatarStatusText = value
            _tRS(__v_raw, "avatarStatusText", oldValue, value)
        }
    override var avatarStatus: Number?
        get() {
            return _tRG(__v_raw, "avatarStatus", __v_raw.avatarStatus, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatarStatus")) {
                return
            }
            val oldValue = __v_raw.avatarStatus
            __v_raw.avatarStatus = value
            _tRS(__v_raw, "avatarStatus", oldValue, value)
        }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var age: Number
        get() {
            return _tRG(__v_raw, "age", __v_raw.age, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("age")) {
                return
            }
            val oldValue = __v_raw.age
            __v_raw.age = value
            _tRS(__v_raw, "age", oldValue, value)
        }
    override var education: String
        get() {
            return _tRG(__v_raw, "education", __v_raw.education, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("education")) {
                return
            }
            val oldValue = __v_raw.education
            __v_raw.education = value
            _tRS(__v_raw, "education", oldValue, value)
        }
    override var location: String
        get() {
            return _tRG(__v_raw, "location", __v_raw.location, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("location")) {
                return
            }
            val oldValue = __v_raw.location
            __v_raw.location = value
            _tRS(__v_raw, "location", oldValue, value)
        }
    override var salary: String
        get() {
            return _tRG(__v_raw, "salary", __v_raw.salary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("salary")) {
                return
            }
            val oldValue = __v_raw.salary
            __v_raw.salary = value
            _tRS(__v_raw, "salary", oldValue, value)
        }
    override var status: String
        get() {
            return _tRG(__v_raw, "status", __v_raw.status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("status")) {
                return
            }
            val oldValue = __v_raw.status
            __v_raw.status = value
            _tRS(__v_raw, "status", oldValue, value)
        }
    override var phone: String
        get() {
            return _tRG(__v_raw, "phone", __v_raw.phone, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("phone")) {
                return
            }
            val oldValue = __v_raw.phone
            __v_raw.phone = value
            _tRS(__v_raw, "phone", oldValue, value)
        }
    override var email: String
        get() {
            return _tRG(__v_raw, "email", __v_raw.email, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("email")) {
                return
            }
            val oldValue = __v_raw.email
            __v_raw.email = value
            _tRS(__v_raw, "email", oldValue, value)
        }
}
val GenPagesApplyResumeOnlineComponentsUserCardClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsUserCard::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsUserCard.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsUserCard.inject, props = GenPagesApplyResumeOnlineComponentsUserCard.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsUserCard.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsUserCard.emits, components = GenPagesApplyResumeOnlineComponentsUserCard.components, styles = GenPagesApplyResumeOnlineComponentsUserCard.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsUserCard.setup(props as GenPagesApplyResumeOnlineComponentsUserCard)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsUserCard {
    return GenPagesApplyResumeOnlineComponentsUserCard(instance)
}
)
val GenPagesApplyResumeOnlineComponentsBasicInfoClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsBasicInfo::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsBasicInfo.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsBasicInfo.inject, props = GenPagesApplyResumeOnlineComponentsBasicInfo.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsBasicInfo.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsBasicInfo.emits, components = GenPagesApplyResumeOnlineComponentsBasicInfo.components, styles = GenPagesApplyResumeOnlineComponentsBasicInfo.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsBasicInfo.setup(props as GenPagesApplyResumeOnlineComponentsBasicInfo)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsBasicInfo {
    return GenPagesApplyResumeOnlineComponentsBasicInfo(instance)
}
)
val GenPagesApplyResumeOnlineComponentsAdvantageClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsAdvantage::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsAdvantage.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsAdvantage.inject, props = GenPagesApplyResumeOnlineComponentsAdvantage.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsAdvantage.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsAdvantage.emits, components = GenPagesApplyResumeOnlineComponentsAdvantage.components, styles = GenPagesApplyResumeOnlineComponentsAdvantage.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsAdvantage.setup(props as GenPagesApplyResumeOnlineComponentsAdvantage)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsAdvantage {
    return GenPagesApplyResumeOnlineComponentsAdvantage(instance)
}
)
val GenPagesApplyResumeOnlineComponentsExpectationClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsExpectation::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsExpectation.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsExpectation.inject, props = GenPagesApplyResumeOnlineComponentsExpectation.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsExpectation.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsExpectation.emits, components = GenPagesApplyResumeOnlineComponentsExpectation.components, styles = GenPagesApplyResumeOnlineComponentsExpectation.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsExpectation.setup(props as GenPagesApplyResumeOnlineComponentsExpectation)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsExpectation {
    return GenPagesApplyResumeOnlineComponentsExpectation(instance)
}
)
val GenPagesApplyResumeOnlineComponentsWorkExperienceListClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsWorkExperienceList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsWorkExperienceList.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsWorkExperienceList.inject, props = GenPagesApplyResumeOnlineComponentsWorkExperienceList.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsWorkExperienceList.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsWorkExperienceList.emits, components = GenPagesApplyResumeOnlineComponentsWorkExperienceList.components, styles = GenPagesApplyResumeOnlineComponentsWorkExperienceList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsWorkExperienceList.setup(props as GenPagesApplyResumeOnlineComponentsWorkExperienceList)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsWorkExperienceList {
    return GenPagesApplyResumeOnlineComponentsWorkExperienceList(instance)
}
)
val GenPagesApplyResumeOnlineComponentsProjectExperienceListClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsProjectExperienceList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsProjectExperienceList.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsProjectExperienceList.inject, props = GenPagesApplyResumeOnlineComponentsProjectExperienceList.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsProjectExperienceList.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsProjectExperienceList.emits, components = GenPagesApplyResumeOnlineComponentsProjectExperienceList.components, styles = GenPagesApplyResumeOnlineComponentsProjectExperienceList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsProjectExperienceList.setup(props as GenPagesApplyResumeOnlineComponentsProjectExperienceList)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsProjectExperienceList {
    return GenPagesApplyResumeOnlineComponentsProjectExperienceList(instance)
}
)
open class PortfolioState (
    @JsonNotNull
    open var tempImages: UTSArray<String>,
    @JsonNotNull
    open var tempDescription: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PortfolioStateReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PortfolioStateReactiveObject : PortfolioState, IUTSReactive<PortfolioState> {
    override var __v_raw: PortfolioState
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PortfolioState, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(tempImages = __v_raw.tempImages, tempDescription = __v_raw.tempDescription) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PortfolioStateReactiveObject {
        return PortfolioStateReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var tempImages: UTSArray<String>
        get() {
            return _tRG(__v_raw, "tempImages", __v_raw.tempImages, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("tempImages")) {
                return
            }
            val oldValue = __v_raw.tempImages
            __v_raw.tempImages = value
            _tRS(__v_raw, "tempImages", oldValue, value)
        }
    override var tempDescription: String
        get() {
            return _tRG(__v_raw, "tempDescription", __v_raw.tempDescription, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("tempDescription")) {
                return
            }
            val oldValue = __v_raw.tempDescription
            __v_raw.tempDescription = value
            _tRS(__v_raw, "tempDescription", oldValue, value)
        }
}
val GenPagesApplyResumeOnlineComponentsPortfolioClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsPortfolio::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsPortfolio.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsPortfolio.inject, props = GenPagesApplyResumeOnlineComponentsPortfolio.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsPortfolio.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsPortfolio.emits, components = GenPagesApplyResumeOnlineComponentsPortfolio.components, styles = GenPagesApplyResumeOnlineComponentsPortfolio.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsPortfolio.setup(props as GenPagesApplyResumeOnlineComponentsPortfolio)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsPortfolio {
    return GenPagesApplyResumeOnlineComponentsPortfolio(instance)
}
)
val GenPagesApplyResumeOnlineComponentsSkillCertificateClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsSkillCertificate::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsSkillCertificate.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsSkillCertificate.inject, props = GenPagesApplyResumeOnlineComponentsSkillCertificate.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsSkillCertificate.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsSkillCertificate.emits, components = GenPagesApplyResumeOnlineComponentsSkillCertificate.components, styles = GenPagesApplyResumeOnlineComponentsSkillCertificate.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsSkillCertificate.setup(props as GenPagesApplyResumeOnlineComponentsSkillCertificate)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsSkillCertificate {
    return GenPagesApplyResumeOnlineComponentsSkillCertificate(instance)
}
)
open class CustomModule__1 (
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var icon: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return CustomModule__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class CustomModule__1ReactiveObject : CustomModule__1, IUTSReactive<CustomModule__1> {
    override var __v_raw: CustomModule__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: CustomModule__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(name = __v_raw.name, icon = __v_raw.icon) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): CustomModule__1ReactiveObject {
        return CustomModule__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var icon: String
        get() {
            return _tRG(__v_raw, "icon", __v_raw.icon, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("icon")) {
                return
            }
            val oldValue = __v_raw.icon
            __v_raw.icon = value
            _tRS(__v_raw, "icon", oldValue, value)
        }
}
val GenPagesApplyResumeOnlineComponentsCustomModuleListClass = CreateVueComponent(GenPagesApplyResumeOnlineComponentsCustomModuleList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyResumeOnlineComponentsCustomModuleList.inheritAttrs, inject = GenPagesApplyResumeOnlineComponentsCustomModuleList.inject, props = GenPagesApplyResumeOnlineComponentsCustomModuleList.props, propsNeedCastKeys = GenPagesApplyResumeOnlineComponentsCustomModuleList.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineComponentsCustomModuleList.emits, components = GenPagesApplyResumeOnlineComponentsCustomModuleList.components, styles = GenPagesApplyResumeOnlineComponentsCustomModuleList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineComponentsCustomModuleList.setup(props as GenPagesApplyResumeOnlineComponentsCustomModuleList)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineComponentsCustomModuleList {
    return GenPagesApplyResumeOnlineComponentsCustomModuleList(instance)
}
)
val GenPagesApplyResumeOnlineIndexClass = CreateVueComponent(GenPagesApplyResumeOnlineIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeOnlineIndex.inheritAttrs, inject = GenPagesApplyResumeOnlineIndex.inject, props = GenPagesApplyResumeOnlineIndex.props, propsNeedCastKeys = GenPagesApplyResumeOnlineIndex.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineIndex.emits, components = GenPagesApplyResumeOnlineIndex.components, styles = GenPagesApplyResumeOnlineIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineIndex.setup(props as GenPagesApplyResumeOnlineIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineIndex {
    return GenPagesApplyResumeOnlineIndex(instance, renderer)
}
)
open class ActionSheetAction (
    @JsonNotNull
    open var name: String,
    open var color: String? = null,
    open var disabled: Boolean? = null,
    open var loading: Boolean? = null,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ActionSheetActionReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ActionSheetActionReactiveObject : ActionSheetAction, IUTSReactive<ActionSheetAction> {
    override var __v_raw: ActionSheetAction
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ActionSheetAction, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(name = __v_raw.name, color = __v_raw.color, disabled = __v_raw.disabled, loading = __v_raw.loading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ActionSheetActionReactiveObject {
        return ActionSheetActionReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var color: String?
        get() {
            return _tRG(__v_raw, "color", __v_raw.color, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("color")) {
                return
            }
            val oldValue = __v_raw.color
            __v_raw.color = value
            _tRS(__v_raw, "color", oldValue, value)
        }
    override var disabled: Boolean?
        get() {
            return _tRG(__v_raw, "disabled", __v_raw.disabled, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("disabled")) {
                return
            }
            val oldValue = __v_raw.disabled
            __v_raw.disabled = value
            _tRS(__v_raw, "disabled", oldValue, value)
        }
    override var loading: Boolean?
        get() {
            return _tRG(__v_raw, "loading", __v_raw.loading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("loading")) {
                return
            }
            val oldValue = __v_raw.loading
            __v_raw.loading = value
            _tRS(__v_raw, "loading", oldValue, value)
        }
}
val GenUniModulesTangUiXComponentsTActionSheetIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTActionSheetIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTActionSheetIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTActionSheetIndex.inject, props = GenUniModulesTangUiXComponentsTActionSheetIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTActionSheetIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTActionSheetIndex.emits, components = GenUniModulesTangUiXComponentsTActionSheetIndex.components, styles = GenUniModulesTangUiXComponentsTActionSheetIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTActionSheetIndex.setup(props as GenUniModulesTangUiXComponentsTActionSheetIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTActionSheetIndex {
    return GenUniModulesTangUiXComponentsTActionSheetIndex(instance)
}
)
open class TDateTimePickerDayItem (
    @JsonNotNull
    open var day: Number,
    @JsonNotNull
    open var month: Number,
    @JsonNotNull
    open var year: Number,
    @JsonNotNull
    open var isCurrentMonth: Boolean = false,
    @JsonNotNull
    open var isToday: Boolean = false,
    @JsonNotNull
    open var isSelected: Boolean = false,
    @JsonNotNull
    open var isDisabled: Boolean = false,
    open var isRangeStart: Boolean? = null,
    open var isRangeEnd: Boolean? = null,
    open var isInRange: Boolean? = null,
) : UTSObject()
open class TDateTimePickerRangeNumberObject (
    @JsonNotNull
    open var start: Number,
    @JsonNotNull
    open var end: Number,
) : UTSObject()
open class TDateTimePickerRangeStringObject (
    @JsonNotNull
    open var start: String,
    @JsonNotNull
    open var end: String,
) : UTSObject()
typealias TDateTimePickerRangeNumberArray = UTSArray<Number>
typealias TDateTimePickerRangeStringArray = UTSArray<String>
typealias TDateTimePickerRangeValue = Any
typealias DayItem = TDateTimePickerDayItem
open class RangeParseResult (
    @JsonNotNull
    open var start: Number,
    @JsonNotNull
    open var end: Number,
) : UTSObject()
val GenUniModulesTangUiXComponentsTDateTimePickerIndexClass = CreateVueComponent(GenUniModulesTangUiXComponentsTDateTimePickerIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenUniModulesTangUiXComponentsTDateTimePickerIndex.inheritAttrs, inject = GenUniModulesTangUiXComponentsTDateTimePickerIndex.inject, props = GenUniModulesTangUiXComponentsTDateTimePickerIndex.props, propsNeedCastKeys = GenUniModulesTangUiXComponentsTDateTimePickerIndex.propsNeedCastKeys, emits = GenUniModulesTangUiXComponentsTDateTimePickerIndex.emits, components = GenUniModulesTangUiXComponentsTDateTimePickerIndex.components, styles = GenUniModulesTangUiXComponentsTDateTimePickerIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenUniModulesTangUiXComponentsTDateTimePickerIndex.setup(props as GenUniModulesTangUiXComponentsTDateTimePickerIndex)
    }
    )
}
, fun(instance, renderer): GenUniModulesTangUiXComponentsTDateTimePickerIndex {
    return GenUniModulesTangUiXComponentsTDateTimePickerIndex(instance)
}
)
typealias InputField = String
typealias DateField = String
open class ProfileFormState (
    @JsonNotNull
    open var avatar: String,
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var gender: String,
    @JsonNotNull
    open var nation: String,
    @JsonNotNull
    open var workStartTime: String,
    @JsonNotNull
    open var phone: String,
    @JsonNotNull
    open var wechat: String,
    @JsonNotNull
    open var birthday: String,
    @JsonNotNull
    open var email: String,
    @JsonNotNull
    open var status: String,
    @JsonNotNull
    open var education: String,
    @JsonNotNull
    open var registerTime: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ProfileFormStateReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ProfileFormStateReactiveObject : ProfileFormState, IUTSReactive<ProfileFormState> {
    override var __v_raw: ProfileFormState
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ProfileFormState, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(avatar = __v_raw.avatar, name = __v_raw.name, gender = __v_raw.gender, nation = __v_raw.nation, workStartTime = __v_raw.workStartTime, phone = __v_raw.phone, wechat = __v_raw.wechat, birthday = __v_raw.birthday, email = __v_raw.email, status = __v_raw.status, education = __v_raw.education, registerTime = __v_raw.registerTime) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ProfileFormStateReactiveObject {
        return ProfileFormStateReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var avatar: String
        get() {
            return _tRG(__v_raw, "avatar", __v_raw.avatar, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("avatar")) {
                return
            }
            val oldValue = __v_raw.avatar
            __v_raw.avatar = value
            _tRS(__v_raw, "avatar", oldValue, value)
        }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var gender: String
        get() {
            return _tRG(__v_raw, "gender", __v_raw.gender, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("gender")) {
                return
            }
            val oldValue = __v_raw.gender
            __v_raw.gender = value
            _tRS(__v_raw, "gender", oldValue, value)
        }
    override var nation: String
        get() {
            return _tRG(__v_raw, "nation", __v_raw.nation, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("nation")) {
                return
            }
            val oldValue = __v_raw.nation
            __v_raw.nation = value
            _tRS(__v_raw, "nation", oldValue, value)
        }
    override var workStartTime: String
        get() {
            return _tRG(__v_raw, "workStartTime", __v_raw.workStartTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("workStartTime")) {
                return
            }
            val oldValue = __v_raw.workStartTime
            __v_raw.workStartTime = value
            _tRS(__v_raw, "workStartTime", oldValue, value)
        }
    override var phone: String
        get() {
            return _tRG(__v_raw, "phone", __v_raw.phone, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("phone")) {
                return
            }
            val oldValue = __v_raw.phone
            __v_raw.phone = value
            _tRS(__v_raw, "phone", oldValue, value)
        }
    override var wechat: String
        get() {
            return _tRG(__v_raw, "wechat", __v_raw.wechat, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("wechat")) {
                return
            }
            val oldValue = __v_raw.wechat
            __v_raw.wechat = value
            _tRS(__v_raw, "wechat", oldValue, value)
        }
    override var birthday: String
        get() {
            return _tRG(__v_raw, "birthday", __v_raw.birthday, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("birthday")) {
                return
            }
            val oldValue = __v_raw.birthday
            __v_raw.birthday = value
            _tRS(__v_raw, "birthday", oldValue, value)
        }
    override var email: String
        get() {
            return _tRG(__v_raw, "email", __v_raw.email, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("email")) {
                return
            }
            val oldValue = __v_raw.email
            __v_raw.email = value
            _tRS(__v_raw, "email", oldValue, value)
        }
    override var status: String
        get() {
            return _tRG(__v_raw, "status", __v_raw.status, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("status")) {
                return
            }
            val oldValue = __v_raw.status
            __v_raw.status = value
            _tRS(__v_raw, "status", oldValue, value)
        }
    override var education: String
        get() {
            return _tRG(__v_raw, "education", __v_raw.education, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("education")) {
                return
            }
            val oldValue = __v_raw.education
            __v_raw.education = value
            _tRS(__v_raw, "education", oldValue, value)
        }
    override var registerTime: String
        get() {
            return _tRG(__v_raw, "registerTime", __v_raw.registerTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("registerTime")) {
                return
            }
            val oldValue = __v_raw.registerTime
            __v_raw.registerTime = value
            _tRS(__v_raw, "registerTime", oldValue, value)
        }
}
open class ActionItem (
    @JsonNotNull
    open var name: String,
    @JsonNotNull
    open var value: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return ActionItemReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class ActionItemReactiveObject : ActionItem, IUTSReactive<ActionItem> {
    override var __v_raw: ActionItem
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: ActionItem, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(name = __v_raw.name, value = __v_raw.value) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): ActionItemReactiveObject {
        return ActionItemReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var name: String
        get() {
            return _tRG(__v_raw, "name", __v_raw.name, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("name")) {
                return
            }
            val oldValue = __v_raw.name
            __v_raw.name = value
            _tRS(__v_raw, "name", oldValue, value)
        }
    override var value: String
        get() {
            return _tRG(__v_raw, "value", __v_raw.value, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("value")) {
                return
            }
            val oldValue = __v_raw.value
            __v_raw.value = value
            _tRS(__v_raw, "value", oldValue, value)
        }
}
val GenPagesApplyResumeOnlineProfileClass = CreateVueComponent(GenPagesApplyResumeOnlineProfile::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeOnlineProfile.inheritAttrs, inject = GenPagesApplyResumeOnlineProfile.inject, props = GenPagesApplyResumeOnlineProfile.props, propsNeedCastKeys = GenPagesApplyResumeOnlineProfile.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineProfile.emits, components = GenPagesApplyResumeOnlineProfile.components, styles = GenPagesApplyResumeOnlineProfile.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineProfile.setup(props as GenPagesApplyResumeOnlineProfile)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineProfile {
    return GenPagesApplyResumeOnlineProfile(instance, renderer)
}
)
val GenPagesApplyResumeOnlineAddEducationClass = CreateVueComponent(GenPagesApplyResumeOnlineAddEducation::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeOnlineAddEducation.inheritAttrs, inject = GenPagesApplyResumeOnlineAddEducation.inject, props = GenPagesApplyResumeOnlineAddEducation.props, propsNeedCastKeys = GenPagesApplyResumeOnlineAddEducation.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineAddEducation.emits, components = GenPagesApplyResumeOnlineAddEducation.components, styles = GenPagesApplyResumeOnlineAddEducation.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineAddEducation.setup(props as GenPagesApplyResumeOnlineAddEducation)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineAddEducation {
    return GenPagesApplyResumeOnlineAddEducation(instance, renderer)
}
)
open class PageState (
    @JsonNotNull
    open var isEditMode: Boolean = false,
    @JsonNotNull
    open var submitting: Boolean = false,
    @JsonNotNull
    open var showPositionPicker: Boolean = false,
    @JsonNotNull
    open var showCityPicker: Boolean = false,
    @JsonNotNull
    open var showIndustryPicker: Boolean = false,
    @JsonNotNull
    open var positionName: String,
    @JsonNotNull
    open var industryName: String,
    @JsonNotNull
    open var selectedIndustryCode: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PageStateReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PageStateReactiveObject : PageState, IUTSReactive<PageState> {
    override var __v_raw: PageState
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PageState, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(isEditMode = __v_raw.isEditMode, submitting = __v_raw.submitting, showPositionPicker = __v_raw.showPositionPicker, showCityPicker = __v_raw.showCityPicker, showIndustryPicker = __v_raw.showIndustryPicker, positionName = __v_raw.positionName, industryName = __v_raw.industryName, selectedIndustryCode = __v_raw.selectedIndustryCode) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PageStateReactiveObject {
        return PageStateReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var isEditMode: Boolean
        get() {
            return _tRG(__v_raw, "isEditMode", __v_raw.isEditMode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("isEditMode")) {
                return
            }
            val oldValue = __v_raw.isEditMode
            __v_raw.isEditMode = value
            _tRS(__v_raw, "isEditMode", oldValue, value)
        }
    override var submitting: Boolean
        get() {
            return _tRG(__v_raw, "submitting", __v_raw.submitting, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("submitting")) {
                return
            }
            val oldValue = __v_raw.submitting
            __v_raw.submitting = value
            _tRS(__v_raw, "submitting", oldValue, value)
        }
    override var showPositionPicker: Boolean
        get() {
            return _tRG(__v_raw, "showPositionPicker", __v_raw.showPositionPicker, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("showPositionPicker")) {
                return
            }
            val oldValue = __v_raw.showPositionPicker
            __v_raw.showPositionPicker = value
            _tRS(__v_raw, "showPositionPicker", oldValue, value)
        }
    override var showCityPicker: Boolean
        get() {
            return _tRG(__v_raw, "showCityPicker", __v_raw.showCityPicker, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("showCityPicker")) {
                return
            }
            val oldValue = __v_raw.showCityPicker
            __v_raw.showCityPicker = value
            _tRS(__v_raw, "showCityPicker", oldValue, value)
        }
    override var showIndustryPicker: Boolean
        get() {
            return _tRG(__v_raw, "showIndustryPicker", __v_raw.showIndustryPicker, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("showIndustryPicker")) {
                return
            }
            val oldValue = __v_raw.showIndustryPicker
            __v_raw.showIndustryPicker = value
            _tRS(__v_raw, "showIndustryPicker", oldValue, value)
        }
    override var positionName: String
        get() {
            return _tRG(__v_raw, "positionName", __v_raw.positionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("positionName")) {
                return
            }
            val oldValue = __v_raw.positionName
            __v_raw.positionName = value
            _tRS(__v_raw, "positionName", oldValue, value)
        }
    override var industryName: String
        get() {
            return _tRG(__v_raw, "industryName", __v_raw.industryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("industryName")) {
                return
            }
            val oldValue = __v_raw.industryName
            __v_raw.industryName = value
            _tRS(__v_raw, "industryName", oldValue, value)
        }
    override var selectedIndustryCode: String
        get() {
            return _tRG(__v_raw, "selectedIndustryCode", __v_raw.selectedIndustryCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("selectedIndustryCode")) {
                return
            }
            val oldValue = __v_raw.selectedIndustryCode
            __v_raw.selectedIndustryCode = value
            _tRS(__v_raw, "selectedIndustryCode", oldValue, value)
        }
}
open class PositionState (
    @JsonNotNull
    open var categoryList: UTSArray<GetAllPositionResult>,
    @JsonNotNull
    open var categoryLoading: Boolean = false,
    @JsonNotNull
    open var currentCategoryIndex: Number,
    @JsonNotNull
    open var searchKeyword: String,
    @JsonNotNull
    open var searchResultList: UTSArray<GetPositionResult>,
    @JsonNotNull
    open var searchPageNum: Number,
    @JsonNotNull
    open var searchHasMore: Boolean = false,
    @JsonNotNull
    open var searchLoading: Boolean = false,
    @JsonNotNull
    open var childLoading: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PositionStateReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PositionStateReactiveObject : PositionState, IUTSReactive<PositionState> {
    override var __v_raw: PositionState
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PositionState, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(categoryList = __v_raw.categoryList, categoryLoading = __v_raw.categoryLoading, currentCategoryIndex = __v_raw.currentCategoryIndex, searchKeyword = __v_raw.searchKeyword, searchResultList = __v_raw.searchResultList, searchPageNum = __v_raw.searchPageNum, searchHasMore = __v_raw.searchHasMore, searchLoading = __v_raw.searchLoading, childLoading = __v_raw.childLoading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PositionStateReactiveObject {
        return PositionStateReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var categoryList: UTSArray<GetAllPositionResult>
        get() {
            return _tRG(__v_raw, "categoryList", __v_raw.categoryList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("categoryList")) {
                return
            }
            val oldValue = __v_raw.categoryList
            __v_raw.categoryList = value
            _tRS(__v_raw, "categoryList", oldValue, value)
        }
    override var categoryLoading: Boolean
        get() {
            return _tRG(__v_raw, "categoryLoading", __v_raw.categoryLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("categoryLoading")) {
                return
            }
            val oldValue = __v_raw.categoryLoading
            __v_raw.categoryLoading = value
            _tRS(__v_raw, "categoryLoading", oldValue, value)
        }
    override var currentCategoryIndex: Number
        get() {
            return _tRG(__v_raw, "currentCategoryIndex", __v_raw.currentCategoryIndex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("currentCategoryIndex")) {
                return
            }
            val oldValue = __v_raw.currentCategoryIndex
            __v_raw.currentCategoryIndex = value
            _tRS(__v_raw, "currentCategoryIndex", oldValue, value)
        }
    override var searchKeyword: String
        get() {
            return _tRG(__v_raw, "searchKeyword", __v_raw.searchKeyword, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchKeyword")) {
                return
            }
            val oldValue = __v_raw.searchKeyword
            __v_raw.searchKeyword = value
            _tRS(__v_raw, "searchKeyword", oldValue, value)
        }
    override var searchResultList: UTSArray<GetPositionResult>
        get() {
            return _tRG(__v_raw, "searchResultList", __v_raw.searchResultList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchResultList")) {
                return
            }
            val oldValue = __v_raw.searchResultList
            __v_raw.searchResultList = value
            _tRS(__v_raw, "searchResultList", oldValue, value)
        }
    override var searchPageNum: Number
        get() {
            return _tRG(__v_raw, "searchPageNum", __v_raw.searchPageNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchPageNum")) {
                return
            }
            val oldValue = __v_raw.searchPageNum
            __v_raw.searchPageNum = value
            _tRS(__v_raw, "searchPageNum", oldValue, value)
        }
    override var searchHasMore: Boolean
        get() {
            return _tRG(__v_raw, "searchHasMore", __v_raw.searchHasMore, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchHasMore")) {
                return
            }
            val oldValue = __v_raw.searchHasMore
            __v_raw.searchHasMore = value
            _tRS(__v_raw, "searchHasMore", oldValue, value)
        }
    override var searchLoading: Boolean
        get() {
            return _tRG(__v_raw, "searchLoading", __v_raw.searchLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchLoading")) {
                return
            }
            val oldValue = __v_raw.searchLoading
            __v_raw.searchLoading = value
            _tRS(__v_raw, "searchLoading", oldValue, value)
        }
    override var childLoading: Boolean
        get() {
            return _tRG(__v_raw, "childLoading", __v_raw.childLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("childLoading")) {
                return
            }
            val oldValue = __v_raw.childLoading
            __v_raw.childLoading = value
            _tRS(__v_raw, "childLoading", oldValue, value)
        }
}
open class CityState (
    @JsonNotNull
    open var provinceList: UTSArray<GetAddressesResult>,
    @JsonNotNull
    open var cityList: UTSArray<GetAddressesResult>,
    @JsonNotNull
    open var currentProvinceIndex: Number,
    @JsonNotNull
    open var provinceLoading: Boolean = false,
    @JsonNotNull
    open var cityLoading: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return CityStateReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class CityStateReactiveObject : CityState, IUTSReactive<CityState> {
    override var __v_raw: CityState
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: CityState, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(provinceList = __v_raw.provinceList, cityList = __v_raw.cityList, currentProvinceIndex = __v_raw.currentProvinceIndex, provinceLoading = __v_raw.provinceLoading, cityLoading = __v_raw.cityLoading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): CityStateReactiveObject {
        return CityStateReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var provinceList: UTSArray<GetAddressesResult>
        get() {
            return _tRG(__v_raw, "provinceList", __v_raw.provinceList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("provinceList")) {
                return
            }
            val oldValue = __v_raw.provinceList
            __v_raw.provinceList = value
            _tRS(__v_raw, "provinceList", oldValue, value)
        }
    override var cityList: UTSArray<GetAddressesResult>
        get() {
            return _tRG(__v_raw, "cityList", __v_raw.cityList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("cityList")) {
                return
            }
            val oldValue = __v_raw.cityList
            __v_raw.cityList = value
            _tRS(__v_raw, "cityList", oldValue, value)
        }
    override var currentProvinceIndex: Number
        get() {
            return _tRG(__v_raw, "currentProvinceIndex", __v_raw.currentProvinceIndex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("currentProvinceIndex")) {
                return
            }
            val oldValue = __v_raw.currentProvinceIndex
            __v_raw.currentProvinceIndex = value
            _tRS(__v_raw, "currentProvinceIndex", oldValue, value)
        }
    override var provinceLoading: Boolean
        get() {
            return _tRG(__v_raw, "provinceLoading", __v_raw.provinceLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("provinceLoading")) {
                return
            }
            val oldValue = __v_raw.provinceLoading
            __v_raw.provinceLoading = value
            _tRS(__v_raw, "provinceLoading", oldValue, value)
        }
    override var cityLoading: Boolean
        get() {
            return _tRG(__v_raw, "cityLoading", __v_raw.cityLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("cityLoading")) {
                return
            }
            val oldValue = __v_raw.cityLoading
            __v_raw.cityLoading = value
            _tRS(__v_raw, "cityLoading", oldValue, value)
        }
}
open class IndustryState (
    @JsonNotNull
    open var list: UTSArray<GetAllPositionResult>,
    @JsonNotNull
    open var currentParentIndex: Number,
    @JsonNotNull
    open var loading: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return IndustryStateReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class IndustryStateReactiveObject : IndustryState, IUTSReactive<IndustryState> {
    override var __v_raw: IndustryState
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: IndustryState, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(list = __v_raw.list, currentParentIndex = __v_raw.currentParentIndex, loading = __v_raw.loading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): IndustryStateReactiveObject {
        return IndustryStateReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var list: UTSArray<GetAllPositionResult>
        get() {
            return _tRG(__v_raw, "list", __v_raw.list, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("list")) {
                return
            }
            val oldValue = __v_raw.list
            __v_raw.list = value
            _tRS(__v_raw, "list", oldValue, value)
        }
    override var currentParentIndex: Number
        get() {
            return _tRG(__v_raw, "currentParentIndex", __v_raw.currentParentIndex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("currentParentIndex")) {
                return
            }
            val oldValue = __v_raw.currentParentIndex
            __v_raw.currentParentIndex = value
            _tRS(__v_raw, "currentParentIndex", oldValue, value)
        }
    override var loading: Boolean
        get() {
            return _tRG(__v_raw, "loading", __v_raw.loading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("loading")) {
                return
            }
            val oldValue = __v_raw.loading
            __v_raw.loading = value
            _tRS(__v_raw, "loading", oldValue, value)
        }
}
val GenPagesApplyResumeOnlineAddJobIntentionClass = CreateVueComponent(GenPagesApplyResumeOnlineAddJobIntention::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeOnlineAddJobIntention.inheritAttrs, inject = GenPagesApplyResumeOnlineAddJobIntention.inject, props = GenPagesApplyResumeOnlineAddJobIntention.props, propsNeedCastKeys = GenPagesApplyResumeOnlineAddJobIntention.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineAddJobIntention.emits, components = GenPagesApplyResumeOnlineAddJobIntention.components, styles = GenPagesApplyResumeOnlineAddJobIntention.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineAddJobIntention.setup(props as GenPagesApplyResumeOnlineAddJobIntention)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineAddJobIntention {
    return GenPagesApplyResumeOnlineAddJobIntention(instance, renderer)
}
)
typealias InputType = String
val GenPagesApplyResumeOnlineAddWorkExperienceClass = CreateVueComponent(GenPagesApplyResumeOnlineAddWorkExperience::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeOnlineAddWorkExperience.inheritAttrs, inject = GenPagesApplyResumeOnlineAddWorkExperience.inject, props = GenPagesApplyResumeOnlineAddWorkExperience.props, propsNeedCastKeys = GenPagesApplyResumeOnlineAddWorkExperience.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineAddWorkExperience.emits, components = GenPagesApplyResumeOnlineAddWorkExperience.components, styles = GenPagesApplyResumeOnlineAddWorkExperience.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineAddWorkExperience.setup(props as GenPagesApplyResumeOnlineAddWorkExperience)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineAddWorkExperience {
    return GenPagesApplyResumeOnlineAddWorkExperience(instance, renderer)
}
)
typealias InputType__1 = String
val GenPagesApplyResumeOnlineAddProjectExperienceClass = CreateVueComponent(GenPagesApplyResumeOnlineAddProjectExperience::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeOnlineAddProjectExperience.inheritAttrs, inject = GenPagesApplyResumeOnlineAddProjectExperience.inject, props = GenPagesApplyResumeOnlineAddProjectExperience.props, propsNeedCastKeys = GenPagesApplyResumeOnlineAddProjectExperience.propsNeedCastKeys, emits = GenPagesApplyResumeOnlineAddProjectExperience.emits, components = GenPagesApplyResumeOnlineAddProjectExperience.components, styles = GenPagesApplyResumeOnlineAddProjectExperience.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeOnlineAddProjectExperience.setup(props as GenPagesApplyResumeOnlineAddProjectExperience)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeOnlineAddProjectExperience {
    return GenPagesApplyResumeOnlineAddProjectExperience(instance, renderer)
}
)
open class PageState__1 (
    @JsonNotNull
    open var isEditMode: Boolean = false,
    @JsonNotNull
    open var submitting: Boolean = false,
    @JsonNotNull
    open var showPositionPicker: Boolean = false,
    @JsonNotNull
    open var showCityPicker: Boolean = false,
    @JsonNotNull
    open var showIndustryPicker: Boolean = false,
    @JsonNotNull
    open var positionName: String,
    @JsonNotNull
    open var industryName: String,
    @JsonNotNull
    open var selectedIndustryCode: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PageState__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PageState__1ReactiveObject : PageState__1, IUTSReactive<PageState__1> {
    override var __v_raw: PageState__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PageState__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(isEditMode = __v_raw.isEditMode, submitting = __v_raw.submitting, showPositionPicker = __v_raw.showPositionPicker, showCityPicker = __v_raw.showCityPicker, showIndustryPicker = __v_raw.showIndustryPicker, positionName = __v_raw.positionName, industryName = __v_raw.industryName, selectedIndustryCode = __v_raw.selectedIndustryCode) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PageState__1ReactiveObject {
        return PageState__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var isEditMode: Boolean
        get() {
            return _tRG(__v_raw, "isEditMode", __v_raw.isEditMode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("isEditMode")) {
                return
            }
            val oldValue = __v_raw.isEditMode
            __v_raw.isEditMode = value
            _tRS(__v_raw, "isEditMode", oldValue, value)
        }
    override var submitting: Boolean
        get() {
            return _tRG(__v_raw, "submitting", __v_raw.submitting, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("submitting")) {
                return
            }
            val oldValue = __v_raw.submitting
            __v_raw.submitting = value
            _tRS(__v_raw, "submitting", oldValue, value)
        }
    override var showPositionPicker: Boolean
        get() {
            return _tRG(__v_raw, "showPositionPicker", __v_raw.showPositionPicker, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("showPositionPicker")) {
                return
            }
            val oldValue = __v_raw.showPositionPicker
            __v_raw.showPositionPicker = value
            _tRS(__v_raw, "showPositionPicker", oldValue, value)
        }
    override var showCityPicker: Boolean
        get() {
            return _tRG(__v_raw, "showCityPicker", __v_raw.showCityPicker, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("showCityPicker")) {
                return
            }
            val oldValue = __v_raw.showCityPicker
            __v_raw.showCityPicker = value
            _tRS(__v_raw, "showCityPicker", oldValue, value)
        }
    override var showIndustryPicker: Boolean
        get() {
            return _tRG(__v_raw, "showIndustryPicker", __v_raw.showIndustryPicker, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("showIndustryPicker")) {
                return
            }
            val oldValue = __v_raw.showIndustryPicker
            __v_raw.showIndustryPicker = value
            _tRS(__v_raw, "showIndustryPicker", oldValue, value)
        }
    override var positionName: String
        get() {
            return _tRG(__v_raw, "positionName", __v_raw.positionName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("positionName")) {
                return
            }
            val oldValue = __v_raw.positionName
            __v_raw.positionName = value
            _tRS(__v_raw, "positionName", oldValue, value)
        }
    override var industryName: String
        get() {
            return _tRG(__v_raw, "industryName", __v_raw.industryName, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("industryName")) {
                return
            }
            val oldValue = __v_raw.industryName
            __v_raw.industryName = value
            _tRS(__v_raw, "industryName", oldValue, value)
        }
    override var selectedIndustryCode: String
        get() {
            return _tRG(__v_raw, "selectedIndustryCode", __v_raw.selectedIndustryCode, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("selectedIndustryCode")) {
                return
            }
            val oldValue = __v_raw.selectedIndustryCode
            __v_raw.selectedIndustryCode = value
            _tRS(__v_raw, "selectedIndustryCode", oldValue, value)
        }
}
open class PositionState__1 (
    @JsonNotNull
    open var categoryList: UTSArray<GetAllPositionResult>,
    @JsonNotNull
    open var categoryLoading: Boolean = false,
    @JsonNotNull
    open var currentCategoryIndex: Number,
    @JsonNotNull
    open var searchKeyword: String,
    @JsonNotNull
    open var searchResultList: UTSArray<GetPositionResult>,
    @JsonNotNull
    open var searchPageNum: Number,
    @JsonNotNull
    open var searchHasMore: Boolean = false,
    @JsonNotNull
    open var searchLoading: Boolean = false,
    @JsonNotNull
    open var childLoading: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return PositionState__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class PositionState__1ReactiveObject : PositionState__1, IUTSReactive<PositionState__1> {
    override var __v_raw: PositionState__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: PositionState__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(categoryList = __v_raw.categoryList, categoryLoading = __v_raw.categoryLoading, currentCategoryIndex = __v_raw.currentCategoryIndex, searchKeyword = __v_raw.searchKeyword, searchResultList = __v_raw.searchResultList, searchPageNum = __v_raw.searchPageNum, searchHasMore = __v_raw.searchHasMore, searchLoading = __v_raw.searchLoading, childLoading = __v_raw.childLoading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): PositionState__1ReactiveObject {
        return PositionState__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var categoryList: UTSArray<GetAllPositionResult>
        get() {
            return _tRG(__v_raw, "categoryList", __v_raw.categoryList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("categoryList")) {
                return
            }
            val oldValue = __v_raw.categoryList
            __v_raw.categoryList = value
            _tRS(__v_raw, "categoryList", oldValue, value)
        }
    override var categoryLoading: Boolean
        get() {
            return _tRG(__v_raw, "categoryLoading", __v_raw.categoryLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("categoryLoading")) {
                return
            }
            val oldValue = __v_raw.categoryLoading
            __v_raw.categoryLoading = value
            _tRS(__v_raw, "categoryLoading", oldValue, value)
        }
    override var currentCategoryIndex: Number
        get() {
            return _tRG(__v_raw, "currentCategoryIndex", __v_raw.currentCategoryIndex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("currentCategoryIndex")) {
                return
            }
            val oldValue = __v_raw.currentCategoryIndex
            __v_raw.currentCategoryIndex = value
            _tRS(__v_raw, "currentCategoryIndex", oldValue, value)
        }
    override var searchKeyword: String
        get() {
            return _tRG(__v_raw, "searchKeyword", __v_raw.searchKeyword, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchKeyword")) {
                return
            }
            val oldValue = __v_raw.searchKeyword
            __v_raw.searchKeyword = value
            _tRS(__v_raw, "searchKeyword", oldValue, value)
        }
    override var searchResultList: UTSArray<GetPositionResult>
        get() {
            return _tRG(__v_raw, "searchResultList", __v_raw.searchResultList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchResultList")) {
                return
            }
            val oldValue = __v_raw.searchResultList
            __v_raw.searchResultList = value
            _tRS(__v_raw, "searchResultList", oldValue, value)
        }
    override var searchPageNum: Number
        get() {
            return _tRG(__v_raw, "searchPageNum", __v_raw.searchPageNum, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchPageNum")) {
                return
            }
            val oldValue = __v_raw.searchPageNum
            __v_raw.searchPageNum = value
            _tRS(__v_raw, "searchPageNum", oldValue, value)
        }
    override var searchHasMore: Boolean
        get() {
            return _tRG(__v_raw, "searchHasMore", __v_raw.searchHasMore, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchHasMore")) {
                return
            }
            val oldValue = __v_raw.searchHasMore
            __v_raw.searchHasMore = value
            _tRS(__v_raw, "searchHasMore", oldValue, value)
        }
    override var searchLoading: Boolean
        get() {
            return _tRG(__v_raw, "searchLoading", __v_raw.searchLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("searchLoading")) {
                return
            }
            val oldValue = __v_raw.searchLoading
            __v_raw.searchLoading = value
            _tRS(__v_raw, "searchLoading", oldValue, value)
        }
    override var childLoading: Boolean
        get() {
            return _tRG(__v_raw, "childLoading", __v_raw.childLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("childLoading")) {
                return
            }
            val oldValue = __v_raw.childLoading
            __v_raw.childLoading = value
            _tRS(__v_raw, "childLoading", oldValue, value)
        }
}
open class CityState__1 (
    @JsonNotNull
    open var provinceList: UTSArray<GetAddressesResult>,
    @JsonNotNull
    open var cityList: UTSArray<GetAddressesResult>,
    @JsonNotNull
    open var currentProvinceIndex: Number,
    @JsonNotNull
    open var provinceLoading: Boolean = false,
    @JsonNotNull
    open var cityLoading: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return CityState__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class CityState__1ReactiveObject : CityState__1, IUTSReactive<CityState__1> {
    override var __v_raw: CityState__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: CityState__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(provinceList = __v_raw.provinceList, cityList = __v_raw.cityList, currentProvinceIndex = __v_raw.currentProvinceIndex, provinceLoading = __v_raw.provinceLoading, cityLoading = __v_raw.cityLoading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): CityState__1ReactiveObject {
        return CityState__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var provinceList: UTSArray<GetAddressesResult>
        get() {
            return _tRG(__v_raw, "provinceList", __v_raw.provinceList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("provinceList")) {
                return
            }
            val oldValue = __v_raw.provinceList
            __v_raw.provinceList = value
            _tRS(__v_raw, "provinceList", oldValue, value)
        }
    override var cityList: UTSArray<GetAddressesResult>
        get() {
            return _tRG(__v_raw, "cityList", __v_raw.cityList, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("cityList")) {
                return
            }
            val oldValue = __v_raw.cityList
            __v_raw.cityList = value
            _tRS(__v_raw, "cityList", oldValue, value)
        }
    override var currentProvinceIndex: Number
        get() {
            return _tRG(__v_raw, "currentProvinceIndex", __v_raw.currentProvinceIndex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("currentProvinceIndex")) {
                return
            }
            val oldValue = __v_raw.currentProvinceIndex
            __v_raw.currentProvinceIndex = value
            _tRS(__v_raw, "currentProvinceIndex", oldValue, value)
        }
    override var provinceLoading: Boolean
        get() {
            return _tRG(__v_raw, "provinceLoading", __v_raw.provinceLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("provinceLoading")) {
                return
            }
            val oldValue = __v_raw.provinceLoading
            __v_raw.provinceLoading = value
            _tRS(__v_raw, "provinceLoading", oldValue, value)
        }
    override var cityLoading: Boolean
        get() {
            return _tRG(__v_raw, "cityLoading", __v_raw.cityLoading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("cityLoading")) {
                return
            }
            val oldValue = __v_raw.cityLoading
            __v_raw.cityLoading = value
            _tRS(__v_raw, "cityLoading", oldValue, value)
        }
}
open class IndustryState__1 (
    @JsonNotNull
    open var list: UTSArray<GetAllPositionResult>,
    @JsonNotNull
    open var currentParentIndex: Number,
    @JsonNotNull
    open var loading: Boolean = false,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return IndustryState__1ReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class IndustryState__1ReactiveObject : IndustryState__1, IUTSReactive<IndustryState__1> {
    override var __v_raw: IndustryState__1
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: IndustryState__1, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(list = __v_raw.list, currentParentIndex = __v_raw.currentParentIndex, loading = __v_raw.loading) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): IndustryState__1ReactiveObject {
        return IndustryState__1ReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var list: UTSArray<GetAllPositionResult>
        get() {
            return _tRG(__v_raw, "list", __v_raw.list, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("list")) {
                return
            }
            val oldValue = __v_raw.list
            __v_raw.list = value
            _tRS(__v_raw, "list", oldValue, value)
        }
    override var currentParentIndex: Number
        get() {
            return _tRG(__v_raw, "currentParentIndex", __v_raw.currentParentIndex, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("currentParentIndex")) {
                return
            }
            val oldValue = __v_raw.currentParentIndex
            __v_raw.currentParentIndex = value
            _tRS(__v_raw, "currentParentIndex", oldValue, value)
        }
    override var loading: Boolean
        get() {
            return _tRG(__v_raw, "loading", __v_raw.loading, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("loading")) {
                return
            }
            val oldValue = __v_raw.loading
            __v_raw.loading = value
            _tRS(__v_raw, "loading", oldValue, value)
        }
}
val GenPagesApplyResumeExpectationClass = CreateVueComponent(GenPagesApplyResumeExpectation::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeExpectation.inheritAttrs, inject = GenPagesApplyResumeExpectation.inject, props = GenPagesApplyResumeExpectation.props, propsNeedCastKeys = GenPagesApplyResumeExpectation.propsNeedCastKeys, emits = GenPagesApplyResumeExpectation.emits, components = GenPagesApplyResumeExpectation.components, styles = GenPagesApplyResumeExpectation.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeExpectation.setup(props as GenPagesApplyResumeExpectation)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeExpectation {
    return GenPagesApplyResumeExpectation(instance, renderer)
}
)
val GenPagesApplyResumeHideClass = CreateVueComponent(GenPagesApplyResumeHide::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyResumeHide.inheritAttrs, inject = GenPagesApplyResumeHide.inject, props = GenPagesApplyResumeHide.props, propsNeedCastKeys = GenPagesApplyResumeHide.propsNeedCastKeys, emits = GenPagesApplyResumeHide.emits, components = GenPagesApplyResumeHide.components, styles = GenPagesApplyResumeHide.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyResumeHide.setup(props as GenPagesApplyResumeHide)
    }
    )
}
, fun(instance, renderer): GenPagesApplyResumeHide {
    return GenPagesApplyResumeHide(instance, renderer)
}
)
open class DeliveryRecord (
    @JsonNotNull
    open var id: String,
    @JsonNotNull
    open var jobTitle: String,
    @JsonNotNull
    open var salary: String,
    @JsonNotNull
    open var company: String,
    @JsonNotNull
    open var tags: UTSArray<String>,
    @JsonNotNull
    open var areaText: String,
    @JsonNotNull
    open var deliverTime: String,
    @JsonNotNull
    open var deliverDate: String,
    @JsonNotNull
    open var displayDate: String,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return DeliveryRecordReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class DeliveryRecordReactiveObject : DeliveryRecord, IUTSReactive<DeliveryRecord> {
    override var __v_raw: DeliveryRecord
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: DeliveryRecord, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(id = __v_raw.id, jobTitle = __v_raw.jobTitle, salary = __v_raw.salary, company = __v_raw.company, tags = __v_raw.tags, areaText = __v_raw.areaText, deliverTime = __v_raw.deliverTime, deliverDate = __v_raw.deliverDate, displayDate = __v_raw.displayDate) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): DeliveryRecordReactiveObject {
        return DeliveryRecordReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var id: String
        get() {
            return _tRG(__v_raw, "id", __v_raw.id, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("id")) {
                return
            }
            val oldValue = __v_raw.id
            __v_raw.id = value
            _tRS(__v_raw, "id", oldValue, value)
        }
    override var jobTitle: String
        get() {
            return _tRG(__v_raw, "jobTitle", __v_raw.jobTitle, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("jobTitle")) {
                return
            }
            val oldValue = __v_raw.jobTitle
            __v_raw.jobTitle = value
            _tRS(__v_raw, "jobTitle", oldValue, value)
        }
    override var salary: String
        get() {
            return _tRG(__v_raw, "salary", __v_raw.salary, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("salary")) {
                return
            }
            val oldValue = __v_raw.salary
            __v_raw.salary = value
            _tRS(__v_raw, "salary", oldValue, value)
        }
    override var company: String
        get() {
            return _tRG(__v_raw, "company", __v_raw.company, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("company")) {
                return
            }
            val oldValue = __v_raw.company
            __v_raw.company = value
            _tRS(__v_raw, "company", oldValue, value)
        }
    override var tags: UTSArray<String>
        get() {
            return _tRG(__v_raw, "tags", __v_raw.tags, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("tags")) {
                return
            }
            val oldValue = __v_raw.tags
            __v_raw.tags = value
            _tRS(__v_raw, "tags", oldValue, value)
        }
    override var areaText: String
        get() {
            return _tRG(__v_raw, "areaText", __v_raw.areaText, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("areaText")) {
                return
            }
            val oldValue = __v_raw.areaText
            __v_raw.areaText = value
            _tRS(__v_raw, "areaText", oldValue, value)
        }
    override var deliverTime: String
        get() {
            return _tRG(__v_raw, "deliverTime", __v_raw.deliverTime, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("deliverTime")) {
                return
            }
            val oldValue = __v_raw.deliverTime
            __v_raw.deliverTime = value
            _tRS(__v_raw, "deliverTime", oldValue, value)
        }
    override var deliverDate: String
        get() {
            return _tRG(__v_raw, "deliverDate", __v_raw.deliverDate, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("deliverDate")) {
                return
            }
            val oldValue = __v_raw.deliverDate
            __v_raw.deliverDate = value
            _tRS(__v_raw, "deliverDate", oldValue, value)
        }
    override var displayDate: String
        get() {
            return _tRG(__v_raw, "displayDate", __v_raw.displayDate, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("displayDate")) {
                return
            }
            val oldValue = __v_raw.displayDate
            __v_raw.displayDate = value
            _tRS(__v_raw, "displayDate", oldValue, value)
        }
}
open class DeliveryGroup (
    @JsonNotNull
    open var date: String,
    @JsonNotNull
    open var items: UTSArray<DeliveryRecord>,
) : UTSReactiveObject() {
    override fun __v_create(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): UTSReactiveObject {
        return DeliveryGroupReactiveObject(this, __v_isReadonly, __v_isShallow, __v_skip)
    }
}
class DeliveryGroupReactiveObject : DeliveryGroup, IUTSReactive<DeliveryGroup> {
    override var __v_raw: DeliveryGroup
    override var __v_isReadonly: Boolean
    override var __v_isShallow: Boolean
    override var __v_skip: Boolean
    constructor(__v_raw: DeliveryGroup, __v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean) : super(date = __v_raw.date, items = __v_raw.items) {
        this.__v_raw = __v_raw
        this.__v_isReadonly = __v_isReadonly
        this.__v_isShallow = __v_isShallow
        this.__v_skip = __v_skip
    }
    override fun __v_clone(__v_isReadonly: Boolean, __v_isShallow: Boolean, __v_skip: Boolean): DeliveryGroupReactiveObject {
        return DeliveryGroupReactiveObject(this.__v_raw, __v_isReadonly, __v_isShallow, __v_skip)
    }
    override var date: String
        get() {
            return _tRG(__v_raw, "date", __v_raw.date, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("date")) {
                return
            }
            val oldValue = __v_raw.date
            __v_raw.date = value
            _tRS(__v_raw, "date", oldValue, value)
        }
    override var items: UTSArray<DeliveryRecord>
        get() {
            return _tRG(__v_raw, "items", __v_raw.items, __v_isReadonly, __v_isShallow)
        }
        set(value) {
            if (!__v_canSet("items")) {
                return
            }
            val oldValue = __v_raw.items
            __v_raw.items = value
            _tRS(__v_raw, "items", oldValue, value)
        }
}
val GenPagesApplyHistoryDeliveryClass = CreateVueComponent(GenPagesApplyHistoryDelivery::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyHistoryDelivery.inheritAttrs, inject = GenPagesApplyHistoryDelivery.inject, props = GenPagesApplyHistoryDelivery.props, propsNeedCastKeys = GenPagesApplyHistoryDelivery.propsNeedCastKeys, emits = GenPagesApplyHistoryDelivery.emits, components = GenPagesApplyHistoryDelivery.components, styles = GenPagesApplyHistoryDelivery.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyHistoryDelivery.setup(props as GenPagesApplyHistoryDelivery)
    }
    )
}
, fun(instance, renderer): GenPagesApplyHistoryDelivery {
    return GenPagesApplyHistoryDelivery(instance, renderer)
}
)
val GenPagesApplyHistoryCommunicatedIndexClass = CreateVueComponent(GenPagesApplyHistoryCommunicatedIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyHistoryCommunicatedIndex.inheritAttrs, inject = GenPagesApplyHistoryCommunicatedIndex.inject, props = GenPagesApplyHistoryCommunicatedIndex.props, propsNeedCastKeys = GenPagesApplyHistoryCommunicatedIndex.propsNeedCastKeys, emits = GenPagesApplyHistoryCommunicatedIndex.emits, components = GenPagesApplyHistoryCommunicatedIndex.components, styles = GenPagesApplyHistoryCommunicatedIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyHistoryCommunicatedIndex.setup(props as GenPagesApplyHistoryCommunicatedIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyHistoryCommunicatedIndex {
    return GenPagesApplyHistoryCommunicatedIndex(instance, renderer)
}
)
val GenPagesApplyHistoryViewComponentsViewerListClass = CreateVueComponent(GenPagesApplyHistoryViewComponentsViewerList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyHistoryViewComponentsViewerList.inheritAttrs, inject = GenPagesApplyHistoryViewComponentsViewerList.inject, props = GenPagesApplyHistoryViewComponentsViewerList.props, propsNeedCastKeys = GenPagesApplyHistoryViewComponentsViewerList.propsNeedCastKeys, emits = GenPagesApplyHistoryViewComponentsViewerList.emits, components = GenPagesApplyHistoryViewComponentsViewerList.components, styles = GenPagesApplyHistoryViewComponentsViewerList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyHistoryViewComponentsViewerList.setup(props as GenPagesApplyHistoryViewComponentsViewerList)
    }
    )
}
, fun(instance, renderer): GenPagesApplyHistoryViewComponentsViewerList {
    return GenPagesApplyHistoryViewComponentsViewerList(instance)
}
)
val GenPagesApplyHistoryViewComponentsViewedListClass = CreateVueComponent(GenPagesApplyHistoryViewComponentsViewedList::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenPagesApplyHistoryViewComponentsViewedList.inheritAttrs, inject = GenPagesApplyHistoryViewComponentsViewedList.inject, props = GenPagesApplyHistoryViewComponentsViewedList.props, propsNeedCastKeys = GenPagesApplyHistoryViewComponentsViewedList.propsNeedCastKeys, emits = GenPagesApplyHistoryViewComponentsViewedList.emits, components = GenPagesApplyHistoryViewComponentsViewedList.components, styles = GenPagesApplyHistoryViewComponentsViewedList.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyHistoryViewComponentsViewedList.setup(props as GenPagesApplyHistoryViewComponentsViewedList)
    }
    )
}
, fun(instance, renderer): GenPagesApplyHistoryViewComponentsViewedList {
    return GenPagesApplyHistoryViewComponentsViewedList(instance)
}
)
val GenPagesApplyHistoryViewIndexClass = CreateVueComponent(GenPagesApplyHistoryViewIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyHistoryViewIndex.inheritAttrs, inject = GenPagesApplyHistoryViewIndex.inject, props = GenPagesApplyHistoryViewIndex.props, propsNeedCastKeys = GenPagesApplyHistoryViewIndex.propsNeedCastKeys, emits = GenPagesApplyHistoryViewIndex.emits, components = GenPagesApplyHistoryViewIndex.components, styles = GenPagesApplyHistoryViewIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyHistoryViewIndex.setup(props as GenPagesApplyHistoryViewIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyHistoryViewIndex {
    return GenPagesApplyHistoryViewIndex(instance, renderer)
}
)
val GenPagesApplyHistoryFavoriteIndexClass = CreateVueComponent(GenPagesApplyHistoryFavoriteIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesApplyHistoryFavoriteIndex.inheritAttrs, inject = GenPagesApplyHistoryFavoriteIndex.inject, props = GenPagesApplyHistoryFavoriteIndex.props, propsNeedCastKeys = GenPagesApplyHistoryFavoriteIndex.propsNeedCastKeys, emits = GenPagesApplyHistoryFavoriteIndex.emits, components = GenPagesApplyHistoryFavoriteIndex.components, styles = GenPagesApplyHistoryFavoriteIndex.styles, setup = fun(props: ComponentPublicInstance): Any? {
        return GenPagesApplyHistoryFavoriteIndex.setup(props as GenPagesApplyHistoryFavoriteIndex)
    }
    )
}
, fun(instance, renderer): GenPagesApplyHistoryFavoriteIndex {
    return GenPagesApplyHistoryFavoriteIndex(instance, renderer)
}
)
fun createApp(): UTSJSONObject {
    val app = createSSRApp(GenAppClass)
    return _uO("app" to app)
}
fun main(app: IApp) {
    definePageRoutes()
    defineAppConfig()
    (createApp()["app"] as VueApp).mount(app, GenUniApp())
}
open class UniAppConfig : io.dcloud.uniapp.appframe.AppConfig {
    override var name: String = "miaojie"
    override var appid: String = "__UNI__4828FFC"
    override var versionName: String = "1.0.0"
    override var versionCode: String = "100"
    override var uniCompilerVersion: String = "5.07"
    constructor() : super() {}
}
fun definePageRoutes() {
    __uniRoutes.push(UniPageRoute(path = "pages/apply/tabbar/home/index", component = GenPagesApplyTabbarHomeIndexClass, meta = UniPageMeta(isQuit = true), style = _uM("navigationBarTitleText" to "首页", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/hire/tabbar/home/index", component = GenPagesHireTabbarHomeIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "首页", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/auth/login", component = GenPagesAuthLoginClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "登录", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/auth/register", component = GenPagesAuthRegisterClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "注册", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/common/auth/wait-audit", component = GenPagesCommonAuthWaitAuditClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "审核状态", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/common/role-select/index", component = GenPagesCommonRoleSelectIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "选择角色", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/tabbar/community/index", component = GenPagesApplyTabbarCommunityIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "社区")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/tabbar/make/index", component = GenPagesApplyTabbarMakeIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "赚钱", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/common/message/index", component = GenPagesCommonMessageIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "消息", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/tabbar/my/index", component = GenPagesApplyTabbarMyIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "我的", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/hire/tabbar/position/index", component = GenPagesHireTabbarPositionIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "职位管理")))
    __uniRoutes.push(UniPageRoute(path = "pages/hire/tabbar/my/index", component = GenPagesHireTabbarMyIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "我的", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/community/detail", component = GenPagesApplyCommunityDetailClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "详情", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/community/publish", component = GenPagesApplyCommunityPublishClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "发布", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/community/my-posts", component = GenPagesApplyCommunityMyPostsClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "我的发布", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/attachment", component = GenPagesApplyResumeAttachmentClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "附件简历", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/upload-attachment", component = GenPagesApplyResumeUploadAttachmentClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "上传附件", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/online/index", component = GenPagesApplyResumeOnlineIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "我的在线简历", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/online/profile", component = GenPagesApplyResumeOnlineProfileClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "个人信息", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/online/add-education", component = GenPagesApplyResumeOnlineAddEducationClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "添加教育经历", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/online/add-job-intention", component = GenPagesApplyResumeOnlineAddJobIntentionClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "添加求职意向", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/online/add-work-experience", component = GenPagesApplyResumeOnlineAddWorkExperienceClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "添加工作经历", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/online/add-project-experience", component = GenPagesApplyResumeOnlineAddProjectExperienceClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "添加项目经历", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/expectation", component = GenPagesApplyResumeExpectationClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "添加求职期望", "navigationStyle" to "custom", "navigationBarBackgroundColor" to "#f0fdf4", "navigationBarTextStyle" to "black")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/resume/hide", component = GenPagesApplyResumeHideClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "隐藏简历", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/history/delivery", component = GenPagesApplyHistoryDeliveryClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "投递记录", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/history/communicated/index", component = GenPagesApplyHistoryCommunicatedIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "沟通过", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/history/view/index", component = GenPagesApplyHistoryViewIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "查看记录", "navigationStyle" to "custom")))
    __uniRoutes.push(UniPageRoute(path = "pages/apply/history/favorite/index", component = GenPagesApplyHistoryFavoriteIndexClass, meta = UniPageMeta(isQuit = false), style = _uM("navigationBarTitleText" to "收藏职位", "navigationStyle" to "custom")))
}
val __uniTabBar: Map<String, Any?>? = _uM("custom" to true, "color" to "#7A7E83", "selectedColor" to "#3B82F6", "borderStyle" to "black", "backgroundColor" to "#FFFFFF", "list" to _uA(
    _uM("pagePath" to "pages/apply/tabbar/home/index", "text" to "首页"),
    _uM("pagePath" to "pages/apply/tabbar/community/index", "text" to "社区"),
    _uM("pagePath" to "pages/apply/tabbar/make/index", "text" to "赚钱"),
    _uM("pagePath" to "pages/common/message/index", "text" to "消息"),
    _uM("pagePath" to "pages/apply/tabbar/my/index", "text" to "我的"),
    _uM("pagePath" to "pages/hire/tabbar/home/index", "text" to "首页"),
    _uM("pagePath" to "pages/hire/tabbar/position/index", "text" to "职位"),
    _uM("pagePath" to "pages/hire/tabbar/my/index", "text" to "我的")
))
val __uniLaunchPage: Map<String, Any?> = _uM("url" to "pages/apply/tabbar/home/index", "style" to _uM("navigationBarTitleText" to "首页", "navigationStyle" to "custom"))
fun defineAppConfig() {
    __uniConfig.entryPagePath = "/pages/apply/tabbar/home/index"
    __uniConfig.globalStyle = _uM("navigationBarTextStyle" to "black", "navigationBarTitleText" to "秒介招聘", "navigationBarBackgroundColor" to "#FFFFFF", "backgroundColor" to "#F8F8F8")
    __uniConfig.getTabBarConfig = fun(): Map<String, Any>? {
        return _uM("custom" to true, "color" to "#7A7E83", "selectedColor" to "#3B82F6", "borderStyle" to "black", "backgroundColor" to "#FFFFFF", "list" to _uA(
            _uM("pagePath" to "pages/apply/tabbar/home/index", "text" to "首页"),
            _uM("pagePath" to "pages/apply/tabbar/community/index", "text" to "社区"),
            _uM("pagePath" to "pages/apply/tabbar/make/index", "text" to "赚钱"),
            _uM("pagePath" to "pages/common/message/index", "text" to "消息"),
            _uM("pagePath" to "pages/apply/tabbar/my/index", "text" to "我的"),
            _uM("pagePath" to "pages/hire/tabbar/home/index", "text" to "首页"),
            _uM("pagePath" to "pages/hire/tabbar/position/index", "text" to "职位"),
            _uM("pagePath" to "pages/hire/tabbar/my/index", "text" to "我的")
        ))
    }
    __uniConfig.tabBar = __uniConfig.getTabBarConfig()
    __uniConfig.conditionUrl = ""
    __uniConfig.uniIdRouter = _uM()
    __uniConfig.ready = true
}
open class GenUniApp : UniAppImpl() {
    open val vm: GenApp?
        get() {
            return getAppVm() as GenApp?
        }
    open val `$vm`: GenApp?
        get() {
            return getAppVm() as GenApp?
        }
}
fun getApp(): GenUniApp {
    return getUniApp() as GenUniApp
}
