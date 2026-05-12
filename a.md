## 工程配置

## 当前工程路径对照

本工程是单模块 Android 工程，当前只有 `:app` 模块，没有单独的 `:uniappx` 模块。下面文档里提到的 `uniappx` 模块，在当前工程中按 `app` 模块处理。

### 参考导出资源

参考 uni-app x 导出资源根目录：

```text
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android
```

该目录当前包含：

```text
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android\__UNI__679904D
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android\uni_modules
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android\uniappx
```

参考资源的 appid 来自：

```text
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android\__UNI__679904D\www\manifest.json
```

其中：

```json
{
  "id": "__UNI__679904D",
  "name": "dom"
}
```

### 当前 Android 工程路径

当前工程根目录：

```text
F:\web\AndroidStudioProjects\uniappx
```

当前 Android 主模块：

```text
F:\web\AndroidStudioProjects\uniappx\app
```

当前主模块依赖库目录：

```text
F:\web\AndroidStudioProjects\uniappx\app\libs
```

当前主模块打包资源目录：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps
```

当前主模块 Kotlin/Java 源码目录：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\java
```

### 打包资源拷贝路径

将参考导出的 appid 目录整体拷贝到 Android 主模块的 `assets/apps` 下。

源目录：

```text
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android\__UNI__679904D
```

目标目录：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps\__UNI__679904D
```

拷贝后目标目录结构应类似：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps\__UNI__679904D\www\manifest.json
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps\__UNI__679904D\www\assets
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps\__UNI__679904D\www\static
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps\__UNI__679904D\www\uni_modules
```

注意：当前工程中已经存在旧资源：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\assets\apps\__UNI__4828FFC
```

如果要使用参考工程 `dom` 的资源，`AndroidManifest.xml` 中的 `DCLOUD_UNI_APPID` 必须配置为 `__UNI__679904D`，并且 `assets/apps` 下也必须存在同名目录 `__UNI__679904D`。appid 不一致会导致运行时找不到对应应用资源。

### Kotlin 源码拷贝路径

参考工程生成的 Kotlin 源码位于：

```text
E:\ProgramData\Vue\The_tennis_world\dom\unpackage\resources\app-android\uniappx\app-android\src
```

需要将该目录下的所有内容拷贝到当前主模块源码目录：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\java
```

拷贝后至少应包含：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\java\index.kt
F:\web\AndroidStudioProjects\uniappx\app\src\main\java\components
F:\web\AndroidStudioProjects\uniappx\app\src\main\java\pages
F:\web\AndroidStudioProjects\uniappx\app\src\main\java\uni_modules
```

当前工程目前只看到：

```text
F:\web\AndroidStudioProjects\uniappx\app\src\main\java\com\example\myapplication\MainActivity.kt
```

说明参考导出的 `index.kt`、`pages`、`components` 等 Kotlin 产物还没有拷贝进当前工程。

### 当前工程配置核对

已存在：

- `app\libs` 已放入大量 uni-app x 运行库和内置模块 AAR/JAR。
- `app\build.gradle` 已配置 `implementation fileTree(include: ['*.aar'], dir: './libs')`。
- `app\build.gradle` 已配置 `aaptOptions`。
- 根目录已存在 `plugins\uts-kotlin-compiler-plugin-0.0.1.jar` 和 `plugins\uts-kotlin-gradle-plugin-0.0.1.jar`。

仍需核对或补齐：

- `settings.gradle` 当前只配置了 `google()` 和 `mavenCentral()`，还需要按下文加入 `maven { url = uri("https://jitpack.io") }` 和 `flatDir { dirs('./plugins/') }`。
- 根目录 `build.gradle` 当前只有 `plugins` 块，还需要按下文加入 `buildscript.dependencies.classpath(files(...))`。
- `gradle.properties` 当前缺少 `android.useAndroidX=true` 和 `android.enableJetifier=true`。
- `app\src\main\AndroidManifest.xml` 当前还没有将 `application` 改为 `io.dcloud.uniapp.UniApplication`，也没有配置 `UniAppActivity`、`DCLOUD_UNI_APPID`、`DCLOUD_CHANNEL`。
- 当前 `assets/apps` 里已有的 appid 是 `__UNI__4828FFC`，与参考工程 appid `__UNI__679904D` 不一致，需要按实际要运行的资源统一。

### 基础库配置

将uts-runtime-release.aar，android-gif-drawable-1.2.28.aar，app-common-release.aar，app-runtime-release.aar，breakpad-build-release.aar，dcloud-layout-release.aar，
framework-release.aar，uni-exit-release.aar，uni-getAccessibilityInfo-release.aar，uni-getAppAuthorizeSetting-release.aar，uni-getAppBaseInfo-release.aar，
uni-getSystemSetting-release.aar，uni-openAppAuthorizeSetting-release.aar，uni-prompt-release.aar，uni-storage-release.aar，uni-getDeviceInfo-release.aar，
uni-getSystemInfo-release.aar，uni-rpx2px-release.aar，uni-theme-release.aar共19个aar拷贝到uni-app x模块的libs下，如果没有libs需要手动创建，参考下图：

![](https://web-ext-storage.dcloud.net.cn/native/doc/android/main_libs_2.jpg)

### 修改build.gradle

修改uniappx模块下的build.gradle

- 添加依赖
	
	将下面的依赖信息添加到build.gradle中
	
	::: preview
	
	> build.gradle
	
	```groovy
	dependencies {
		implementation fileTree(include: ['*.aar'], dir: './libs') // 复制到主模块中需要将`./libs`替换为`../uniappx/libs`
		implementation "androidx.core:core-ktx:1.10.1"
		implementation "androidx.recyclerview:recyclerview:1.3.2"
		implementation "androidx.appcompat:appcompat:1.0.0"
		implementation "androidx.exifinterface:exifinterface:1.3.6"
		implementation "androidx.localbroadcastmanager:localbroadcastmanager:1.0.0@aar"
		implementation "androidx.constraintlayout:constraintlayout:2.1.4"
		implementation "androidx.webkit:webkit:1.6.0"
		implementation "com.google.android.material:material:1.4.0"
		implementation "androidx.viewpager2:viewpager2:1.1.0-beta02"
		implementation "com.alibaba:fastjson:1.2.83"
		implementation "com.facebook.fresco:fresco:3.4.0"
		implementation "com.facebook.fresco:middleware:3.4.0"
		implementation "com.facebook.fresco:animated-gif:3.4.0"
		implementation "com.facebook.fresco:webpsupport:3.4.0"
		implementation "com.facebook.fresco:animated-webp:3.4.0"
		implementation "com.caverock:androidsvg:1.4"
		implementation "com.github.bumptech.glide:glide:4.9.0"
		implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
		implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
		implementation "org.jetbrains.kotlin:kotlin-stdlib:2.2.0"
		implementation "org.jetbrains.kotlin:kotlin-reflect:2.2.0"
		implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
		implementation "com.squareup.okhttp3:okhttp:3.12.12"
		implementation "com.github.getActivity:XXPermissions:18.63"
		implementation "net.lingala.zip4j:zip4j:2.11.5"
	}
	```
	
	> build.gradle.kts

	```groovy
	dependencies {
	    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar")))) // 复制到主模块中需要将`./libs`替换为`../uniappx/libs`
	    implementation("androidx.core:core-ktx:1.10.1")
	    implementation("androidx.recyclerview:recyclerview:1.3.2")
	    implementation("androidx.appcompat:appcompat:1.0.0")
	    implementation("androidx.exifinterface:exifinterface:1.3.6")
	    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.0.0")
	    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	    implementation("androidx.webkit:webkit:1.6.0")
	    implementation("com.google.android.material:material:1.4.0")
	    implementation("androidx.viewpager2:viewpager2:1.1.0-beta02")
	    implementation("com.alibaba:fastjson:1.2.83")
	    implementation("com.facebook.fresco:fresco:3.4.0")
	    implementation("com.facebook.fresco:middleware:3.4.0")
	    implementation("com.facebook.fresco:animated-gif:3.4.0")
	    implementation("com.facebook.fresco:webpsupport:3.4.0")
	    implementation("com.facebook.fresco:animated-webp:3.4.0")
		implementation("com.caverock:androidsvg:1.4")
	    implementation("com.github.bumptech.glide:glide:4.9.0")
	    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
	    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.0")
	    implementation("org.jetbrains.kotlin:kotlin-reflect:2.2.0")
	    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
	    implementation("com.squareup.okhttp3:okhttp:3.12.12")
	    implementation("com.github.getActivity:XXPermissions:18.63")
	    implementation("net.lingala.zip4j:zip4j:2.11.5")
	}
	```
	
	:::
	
- 添加aaptOptions配置

	将aaptOptions配置添加到android节点下
	
	::: preview
	
	> build.gradle
	
	```groovy
    aaptOptions {
        additionalParameters '--auto-add-overlay'
        ignoreAssetsPattern '!.svn:!.git:.*:!CVS:!thumbs.db:!picasa.ini:!*.scc:*~'
    }
	```
	
	> build.gradle.kts

	```groovy
    androidResources {
        additionalParameters += listOf("--auto-add-overlay")
        ignoreAssetsPattern = "!.svn:!.git:.*:!CVS:!thumbs.db:!picasa.ini:!*.scc:*~"
    }
	```
	
	:::
	
	***注意：上面的配置需要同时设置到主模块中。***
	
### 配置gradle插件 @gradleplugin

在项目根目录的build.gradle的顶部添加gradle插件的依赖。参考：

```groovy
buildscript {
    dependencies {
		...
        classpath(files('plugins/uts-kotlin-compiler-plugin-0.0.1.jar'))
        classpath(files('plugins/uts-kotlin-gradle-plugin-0.0.1.jar'))
    }
}
```

**注意：文件uts-kotlin-compiler-plugin-0.0.1.jar和uts-kotlin-gradle-plugin-0.0.1.jar位于原生SDK中，示例中放到了项目根目录的`plugins`文件夹下。参考：**

![](https://web-ext-storage.dcloud.net.cn/native/doc/android/gradle_plugins.png)

然后在`uniappx`模块的build.gradle下添加插件`io.dcloud.uts.kotlin`的依赖。参考：

::: preview

> build.gradle

```groovy
plugins {
	...
    id 'io.dcloud.uts.kotlin'
}
```

> build.gradle.kts

```groovy
plugins {
	...
    id("io.dcloud.uts.kotlin")
}
```

:::

**注意：`io.dcloud.uts.kotlin`仅需要配置到uniappx模块和android uts插件模块中。原有的主项目不需要配置。**

### 修改项目的settings.gradle

在项目根路径下的settings.gradle中添加`jitpack`的maven的仓库地址和本地gradle插件的路径配置。参考如下：

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
		flatDir { dirs('./plugins/') }
    }
}
```

### 修改项目的gradle.properties

在项目根路径下的gradle.properties中追加如下内容

```groovy
android.useAndroidX=true
android.enableJetifier=true
```

### 修改uniappx模块下的AndroidManifest.xml @androidmanifest  

- 添加activity
	
	将下面内容拷贝到application节点下

	```xml
        <activity
            android:name="io.dcloud.uniapp.UniAppActivity"
            android:configChanges="orientation|keyboard|keyboardHidden|smallestScreenSize|screenLayout|screenSize|mcc|mnc|fontScale|navigation|uiMode"
            android:exported="true"
            android:label="@string/app_name"
            android:screenOrientation="portrait"
            android:theme="@style/UniAppX.Activity.DefaultTheme"
            android:windowSoftInputMode="adjustResize"
            tools:replace="android:label,android:exported,android:theme,android:configChanges,android:windowSoftInputMode,android:screenOrientation">
        </activity>
	```

- 添加appid

	在application节点下添加`DCLOUD_UNI_APPID`节点
	```xml
	<meta-data
		android:name="DCLOUD_UNI_APPID" android:value="替换成应用的appid" />
	```
	
- 修改application

	将`application`节点的`android:name`修改为`io.dcloud.uniapp.UniApplication`

	**注意：如果需要自定义application，必须继承自UniApplication**
	
- 配置应用分发渠道
	
	在application节点下添加`DCLOUD_CHANNEL`节点
	```xml
	<meta-data
		android:name="DCLOUD_CHANNEL" android:value="替换成需要发布的应用分发渠道" />
	```
	
	获取配置的应用分发渠道，可以通过[uni.getAppBaseInfo()](../../api/get-app-base-info.md)。

- 合并AndroidManifest.xml

	如果uni-app x项目根目录下有AndroidManifest.xml文件，你需要按照xml文件的结构将内容拷贝到`uniappx`模块的AndroidManifest.xml中。

### 拷贝资源文件
1. 导出uni-app x项目的资源文件

	选择项目，然后点击：发行 -> 原生App-本地打包 -> 生成本地打包App资源

	![](https://web-ext-storage.dcloud.net.cn/native/doc/android/export.png)

	导出成功之后会在项目的unpackage/resources目录下生成资源文件

	![](https://web-ext-storage.dcloud.net.cn/native/doc/android/resources.png)
	
2. 将app-android目录下与appid对应的目录拷贝到uniappx项目的`assets/apps`目录下
	
	![](https://web-ext-storage.dcloud.net.cn/native/doc/android/app_assets_2.png)
	
	**注意：apps下的appid必须与AndroidManifest.xml的`DCLOUD_UNI_APPID`保持一致。**
	
### 拷贝kt文件
需要将`unkackage/resource/app-android/uniappx/app-android/src/`目录下的所有文件拷贝到uniappx项目的`src/main/java`下

![](https://web-ext-storage.dcloud.net.cn/native/doc/android/copykt.png)

注意：不要破坏原有src下的目录结构。

### 添加到主模块

将uni-app x模块添加到主模块中。

::: preview
	
> build.gradle

```groovy
	dependencies {
		implementation project(':uniappx')
		implementation fileTree(include: ['*.aar'], dir: '../uniappx/libs')
	}
```

> build.gradle.kts
	
```groovy
	dependencies {
		implementation(project(":uniappx"))
		implementation(fileTree(mapOf("dir" to "../uniappx/libs", "include" to listOf("*.aar"))))
	}
```

:::

## 配置内置模块@configmodules

根据`unpackage/resource/{appid}/manifest.json`的配置，添加[内置模块的配置](../modules/android/others.md)。

## uts插件配置

uts插件资源位于`unpackage/resource/app-android/uni_modules`下。如果uni_modules下存在uts插件，需要按照[uts插件配置文档](./androiduts.md)将插件集成到项目中。

以下模块可以忽略配置：
- uni-exit
- uni-getAccessibilityInfo
- uni-getAppAuthorizeSetting
- uni-getAppBaseInfo
- uni-getDeviceInfo
- uni-getSystemInfo
- uni-getSystemSetting
- uni-openAppAuthorizeSetting
- uni-prompt
- uni-storage
- uni-rpx2px
- uni-theme

## 启动

至此，uni-app x 导入原生项目的所有配置已经完成。uni-app x的启动、退出及运行期间通讯可以参考[文档](androidcomm.md)。

 
工资都难要 这里 你要转行做什么，不清楚能干什么干什么    做那种亚马逊的网站，shipoy? 