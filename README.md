# WanAndroid

基于WanAndroid网站开放api以及自己所学的知识点练习练习~

# 架构设计

基于Google Android [官方推荐架构](https://developer.android.google.cn/topic/architecture?hl=zh-cn)

+---------------------------+
|        App Module          |  ---> 应用模块
+---------------------------+
|
+---------------------------+
|   Presentation Layer (UI)  |  ---> 表示层 (ViewModel, Fragment, Activity)
+---------------------------+
|
+---------------------------+
|      Domain Layer          |  ---> 领域层 (UseCases, Repository Interface)
+---------------------------+
|
+---------------------------+
|        Data Layer          |  ---> 数据层 (Repository Impl, Data Sources)
+---------------------------+
|
+---------------------------+
|        Core Module         |  ---> 核心模块 (网络、数据库、依赖注入)
+---------------------------+
|
+---------------------------+
|        BaseLib Module      |  ---> 基础模块 (通用工具类、常量等)
+---------------------------+

###### 1、Presentation Layer

Presentation Layer 依赖于 Domain Layer，通过 UseCase 访问业务逻辑，不直接访问数据层。

###### 2、Domain Layer

Domain Layer 定义了业务逻辑和数据操作的接口 (Repository)，不会与具体的数据层交互。

###### 3、Data Layer

Data Layer 实现了 Domain Layer 中的 Repository 接口，直接与数据库、API 等数据源进行交互。

###### 4、Core Module

功能: 包含应用的核心功能和逻辑，如网络、数据库、依赖注入等，负责跨模块的基础设施搭建。

典型内容:

- 网络模块 (Retrofit、OkHttp 配置)
- 数据库模块 (Room 数据库配置)
- 依赖注入 (Dagger/Hilt 配置)
- 网络状态监测 (NetworkUtil)

依赖关系: 

Core 模块依赖于 BaseLib，用来获取基础的工具类。数据层和领域层都依赖 Core 模块来访问网络和数据库等核心功能。

###### 5、BaseLib Module

功能: 

包含所有通用的工具类、常量、扩展函数等，不依赖其他模块。

典型内容:

- 日志工具 (LogUtils)
- 设备信息获取 (DeviceUtils)
- 时间、日期工具 (DateUtils)
- 扩展函数 (StringExtensions, ViewExtensions)
- SharedPreferences 工具类 (SpUtil)

依赖关系:

不依赖任何其他模块，独立存在。所有其他模块（包括 Core、Domain、Data 层等）都可以依赖它。
