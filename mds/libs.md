# Libs.version.toml

# 依赖库的组成

```kotlin
dependencies {
  val nav_version = "2.8.0"
  // Views/Fragments integration
  implementation("androidx.navigation:navigation-fragment:$nav_version")
  implementation("androidx.navigation:navigation-ui:$nav_version")
}
```
以androidx.navigation:navigation-fragment:$nav_version为例子，他的结构可以是如下两种形式

###### 1、group:name:version

- group androidx.navigation
- name navigation-fragment
- version nav_version

###### 2、module:version

- module 可以当做group:name如androidx.navigation:navigation-fragment
- version nav_version

那么上面这个在Libs.version.toml是怎样配置的呢？我们有必要先了解下toml文件

# toml文件

在 Android Gradle 项目中，libs.versions.toml 文件是用于管理依赖项版本的文件。它遵循的是 TOML（Tom's Obvious, Minimal Language） 语法，TOML 是一种简洁的配置文件格式，在 Gradle 7.0 之后被引入用于集中管理依赖版本。

为了方便管理项目中的所有依赖，我们通常会创建buildSrc module，然后这里提供管理依赖的文件。libs.versions.toml文件的功能就与buildSrc module中定义的文件一样，你可以在这个文件中定义所有依赖的版本号，并在不同的模块中引用这些版本。

###### 1、libs.versions.toml 语法结构

这个文件包含以下几部分：

- 版本定义（[versions]）
- 依赖定义（[libraries]）
- 插件定义（[plugins]）

```toml
# 定义所有的版本
[versions]
kotlin = "1.5.31"
retrofit = "2.9.0"
coroutines = "1.5.2"

# 定义库的依赖
[libraries]
kotlin-stdlib = { module = "org.jetbrains.kotlin:kotlin-stdlib", version.ref = "kotlin" }
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
coroutines-core = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "coroutines" }

# 定义插件
[plugins]
android-application = { id = "com.android.application", version = "7.0.0" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version = "1.5.31" }

```

主要语法规则：

- 版本号定义： version.ref 用来引用在 [versions] 部分中定义的版本号。

- 依赖管理： 每个依赖项使用 module = "group:name" 的格式来指定 Maven 坐标。依赖项的版本可以是硬编码的，也可以使用 version.ref 引用已定义的版本。

- 插件定义： 插件使用 id 来指定插件的名称，并使用 version 定义其版本。

###### 2、gradle.kts 中使用

implementation(libs.变量名)如上的kotlin-stdlib

```kotlin
implementation(libs.kotlin-stdlib)
```

# 官方文档

libs.versions.toml 的主要用途是通过 Version Catalogs 功能来声明和统一管理依赖。

[Gradle Version Catalogs ](https://docs.gradle.org/current/userguide/platforms.html#sec)

[TOML](https://toml.io/en/)
