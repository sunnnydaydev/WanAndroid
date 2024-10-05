# implementation & api

在 Android 或 Java 项目中，implementation 和 api 是用于依赖管理的关键字，特别是在使用 Gradle 构建工具时。它们的主要区别体现在依赖的可见性和传播性上：

###### 1. implementation
- 依赖的可见性: 当你使用 implementation 来声明依赖时，该依赖只对当前模块可见，其他模块无法直接访问此依赖。
- 编译时间优化: 使用 implementation 声明依赖可以减少编译时间，因为 Gradle 不需要将该依赖传播给其他模块，编译器只关心当前模块所需的依赖。
- 适用场景: 适用于当前模块需要使用某个库，但不希望其他模块直接访问它。可以帮助模块化开发时减少耦合。

示例:

```groovy
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
}
```
在这种情况下，只有当前模块可以使用 Retrofit，其他依赖于该模块的模块无法直接访问 Retrofit。

###### 2. api

- 依赖的可见性: 当你使用 api 声明依赖时，该依赖会对所有依赖于此模块的其他模块也可见。这意味着不仅当前模块可以使用该依赖，其他模块也可以直接使用它。
- 传播性: 依赖使用 api 声明后，它会自动传播到其他模块。依赖于当前模块的所有模块都可以访问该依赖。
- 适用场景: 当一个模块需要提供某个库的接口给其他模块使用时，可以使用 api，比如一个共享的库模块。

示例:

```groovy
dependencies {
    api 'com.google.guava:guava:31.0.1-android'
}
```

在这种情况下，所有依赖于该模块的其他模块都可以使用 Guava 库。


api 只能将依赖传递给直接依赖它的模块，无法自动传递给上上层或更远的模块。换句话说：

- 直接依赖：当模块 A 使用 api 声明了某个依赖库，模块 B 依赖于模块 A，模块 B 也可以直接使用这个依赖。
- 传递性限制：如果还有一个模块 C 依赖于模块 B，那么模块 C 并不能直接访问模块 A 中通过 api 声明的依赖，除非模块 B 也使用 api 来声明对模块 A 的依赖。

示例

假设有以下三个模块：模块 A、模块 B 和模块 C。

- 模块 A 有一个依赖 Guava，并且它使用 api 声明了依赖：

```groovy
// Module A
dependencies {
    api 'com.google.guava:guava:31.0.1-android'
}
```

- 模块 B 依赖于模块 A，假设它用的是 implementation，那么模块 B 可以直接使用 Guava，但模块 C 无法使用，因为模块 B 选择了 implementation：

```groovy
// Module B
dependencies {
    implementation project(':moduleA')
}
```

- 如果模块 C 想要使用 Guava，那么模块 B 必须使用 api 来声明对模块 A 的依赖：

```groovy
// Module B
dependencies {
    api project(':moduleA')
}
```

这样，模块 C 依赖于模块 B 的时候，Guava 才能被传递到模块 C：

```groovy
// Module C
dependencies {
    implementation project(':moduleB')
}
```

关键点
- implementation：限制传递，只在当前模块使用。
- api：允许传递，但只能传递给直接依赖的模块。
- 多层传递：如果想要在多个层次的模块中都使用某个依赖，每一层都需要使用 api 声明依赖。
这种机制可以控制依赖的可见性，从而避免不必要的依赖传播，提升模块的隔离性。