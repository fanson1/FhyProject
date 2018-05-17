# FhyProject使用手册
```
主要包括两类moudle：
Android项目demo --- mvvm，kotlin-mvvm等
Android项目基础工具 --- baselibs

后续从1.0.3开始区分：精简版和完整版
dev-base：精简版，只包括baselibs工具库
dev-aall：完整版，包含baselibs工具库和其他demo
```

# github地址
```
https://github.com/fanson1/FhyProject
```
# 提交规范

* **[func] do sth**：功能开发
* **[fixbug] #xx# do sth**：Bug 修改，#号内为 issue 编号
* **[docs] do sth**：文档
* **[chore] do sth**：build相关的修改
* **[test] do sth**：添加测试代码
* **[optimize] do sth**: 优化部分代码
* **[style] do sth**: 格式化上的格式化、删除空白行等，无关功能


# 注释文档
doc

# android studio中gradle引用方式：
## Step 1. Add it in your root build.gradle at the end of repositories:
### 	allprojects {
### 		repositories {
### 			...
### 			maven { url 'https://jitpack.io' }
### 		}
### 	}
## Step 2. Add the dependency
### 	dependencies {
### 	        compile 'com.github.fanson1:FhyProject:1.0.3-base'
### 	}
## 其他方式参照：
https://jitpack.io/#fanson1/FhyProject/

# 目录
## 一、application：
Application抽取
## 二、bean：
实体类抽取
## 三、net：网络请求封装
### 1.ApiService：
接口定义
### 2.ApiServiceFactory：
okHttpClient，Retrofit，ApiService初始化
## 四、ui：BaseUI封装
### 1.BaseActivity：
主要处理Activity的视图数据初始化流程，键盘显示隐藏，沉浸式状态栏及Activity统一的栈管理
### 2.BaseFragment：
主要处理Fragment的视图数据初始化流程，点击事件绑定
## 五、util：工具类封装
### 1.app：
应用管理，界面跳转等
### 2.bezier：
贝塞尔曲线动画
### 3.bitmap：
位图工具
### 4.hint：
日志和吐司
### 5.img：
图片加载
### 6.layoutmanager：
自定义线性管理器
### 7.pinyin：
汉字转拼音工具封装
### 8.refresh：
列表刷新和加载更多封装
### 9.common：
通用工具
## 六、view：自定义View封装

# 附录：依赖库版本控制
## 1.通用的部分在Project下的config.gradle中进行统一配置
```
def supportVersion = "26.0.2"

ext {
    android = [compileSdkVersion: 26,
               buildToolsVersion: "${supportVersion}",
               minSdkVersion    : 15,
               targetSdkVersion : 26]
    dependencies = [
            // Support
            "support-v4"          : "com.android.support:support-v4:${supportVersion}",
            "appcompat-v7"        : "com.android.support:appcompat-v7:${supportVersion}",
            "design"              : "com.android.support:design:${supportVersion}",
            "recyclerview-v7"     : "com.android.support:recyclerview-v7:${supportVersion}",
            "cardview-v7"         : "com.android.support:cardview-v7:${supportVersion}",

            // UI
            "brvadapter"          : "com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.34",
            "tkrefreshlayout"     : "com.lcodecorex:tkrefreshlayout:1.0.6",
            "srfreshlayout"       : "com.scwang.smartrefresh:SmartRefreshLayout:1.0.4",
            "srrefreshheader"     : "com.scwang.smartrefresh:SmartRefreshHeader:1.0.4",
            "flowlayout"          : "com.hyman:flowlayout-lib:1.1.2",
            "constraint"          : "com.android.support.constraint:constraint-layout:1.0.2",
            "banner"              : "com.youth.banner:banner:1.4.10",
            "mddialog-commons"    : "com.afollestad.material-dialogs:commons:0.9.4.3",
            "mddialog-core"       : "com.afollestad.material-dialogs:core:0.9.4.3",

            // Test
            "junit"               : "junit:junit:4.12",
            "espresso"            : "com.android.support.test.espresso:espresso-core:2.2.2",

            // Json
            "gson"                : "com.google.code.gson:gson:2.4",
            "fastjson"            : "com.alibaba:fastjson:1.2.24",


            "clog"                : "com.dreamlive.cn.clog:ClogLibrary:0.0.1",
            "eventbus"            : "org.greenrobot:eventbus:3.0.0",

            // Image
            "glide"               : "com.github.bumptech.glide:glide:3.7.0",
            "uimgloader"          : "com.nostra13.universalimageloader:universal-image-loader:1.9.3",
            "picasso"             : "com.squareup.picasso:picasso:2.5.2",

            // Rx...
            "rxjava"              : "io.reactivex.rxjava2:rxjava:2.0.1",
            "rxandroid"           : "io.reactivex.rxjava2:rxandroid:2.0.1",
            "rxbinding"           : "com.jakewharton.rxbinding2:rxbinding:2.0.0",
            "retrofit"            : "com.squareup.retrofit2:retrofit:2.1.0",
            "rradapter"           : "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0",
            "converter-gson"      : "com.squareup.retrofit2:converter-gson:2.1.0",
            "rxpermissions"       : "com.tbruyelle.rxpermissions:rxpermissions:0.9.4",

            // Greendao
            "greendao"            : "org.greenrobot:greendao:3.0.1",
            "greendao-generator"  : "org.greenrobot:greendao-generator:3.0.0",

            // MD lib
            "md-dialog-commons"  : "com.afollestad.material-dialogs:commons:0.9.4.3",
            "md-dialog-core"  : "com.afollestad.material-dialogs:core:0.9.4.3",

            // Other
            "nineoldandroids"     : "com.nineoldandroids:library:2.4.0",
            "photoview"           : "com.github.chrisbanes.photoview:library:1.2.3",
            "numberprogressbar"   : "com.daimajia.numberprogressbar:library:1.2@aar",
            "umeng-analytics"     : "com.umeng.analytics:analytics:latest.integration",
            "okhttp-urlconnection": "com.squareup.okhttp:okhttp-urlconnection:2.0.0",
            "okhttp"              : "com.squareup.okhttp:okhttp:2.0.0",
            "butterknife"         : "com.jakewharton:butterknife:7.0.1",
            "otto"                : "com.squareup:otto:1.3.8"]
}

```
## 2.在Project的build.gradle启用：
```
apply from: "config.gradle"
```
## 3.在moudle的build.gradlez中引用

```
    android {
        compileSdkVersion rootProject.ext.android.compileSdkVersion
        ...
    }
    
    dependencies {
        compile rootProject.ext.dependencies["junit"]
        ...
    }
```
