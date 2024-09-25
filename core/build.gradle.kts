plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.carry.wanandroid.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    /**
     * ui layer 也需要navigation这个依赖，这里api替换implementation后只能上层使用，上上层就不能用了。
     *
     * 若想上上层也能使用那么就要依赖module是也api，然后层层上传，这不可取。还是在其他层也依赖一份即可。
     *
     * 栗子：
     * 1、core layer 定义的 classA
     *    上层模块   moduleA  implementation core时，moduleA能直接使用classA。
     *    上上层模块 moduleAA implementation moduleA时，moduleAA不能使用classA
     *    上上层模块 moduleAA api moduleA时，moduleAA能使用classA
     * 2、 core layer implementation okhttp 库,上层模块 moduleA 无论是implementation，还是api都不能使用okhttp库
     *
     * 收获：
     * api 能传递到上层
     * implementation 只能当前使用
     *
     * 换句话说
     *
     * 当前模块A implementation 三方库 只有当前模块能使用三方库中的类，
     * 当前模块A api            三方库 当前模块上一模块也能使用三方库中的类，
     *
     * 这个规则对模块之间的类同样适用。我们可以把其中一个模块当做三方库。
     *
     * todo 写几个test 类整理下这个对则。
     *
     * */

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    androidTestImplementation(libs.androidx.navigation.testing)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(project(":baselib"))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation (libs.retrofit2.converter.gson)
    implementation (libs.jetbrains.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
}