# 开发日志

## 2026-05-27：修复 JNI ContextProto 崩溃

### 问题
LocalType APK 安装后，启用输入法服务（IME）时 native crash：
```
JNI DETECTED ERROR: FindClass called with pending exception
java.lang.NoSuchMethodError: ContextProto.<init>(CompositionProto, MenuProto, String, int)
```

### 排查过程

#### 第一轮：JNI 签名拼写错误
- 文件：`app/src/main/jni/librime_jni/jni-utils.h`
- 问题：`ContextProtoInit` 的 JNI 方法签名中包名重复
  - 错误：`Lcom/localtype/app/localtype/app/core/MenuProto`
  - 正确：`Lcom/localtype/app/core/MenuProto`
- 结果：修复后仍崩溃（签名正确但 `GetMethodID` 仍返回 NULL）

#### 第二轮：多 dex 类加载时序
- 怀疑 `JNI_OnLoad` 时 ClassLoader 只加载主 dex，`ContextProto` 在 `classes6.dex` 中不可见
- 尝试 1：在 `Rime.kt` 中预加载 proto 类（无效）
- 尝试 2：添加 `multidex-config.txt` 强制 proto 类入主 dex（无效）

#### 第三轮：绕过构造函数（最终方案）
- 完全放弃通过 JNI 调用 Kotlin data class 构造函数
- 改用 `AllocObject()` + `SetField()` 逐个设置字段
- 文件修改：
  - `jni-utils.h`：移除 `ContextProtoInit`（method ID），新增 4 个 field ID
  - `objconv.h`：`rimeContextToJObject()` 改为 AllocObject + SetField

### 修复文件清单

| 文件 | 变更 |
|------|------|
| `app/src/main/jni/librime_jni/jni-utils.h` | ContextProto 构造函数 → 4 个 field ID |
| `app/src/main/jni/librime_jni/objconv.h` | NewObject → AllocObject + SetField |
| `app/src/main/java/com/localtype/app/core/Rime.kt` | 添加 proto 类预加载（防御性） |
| `app/build.gradle.kts` | 添加 multiDexKeepFile |
| `app/multidex-config.txt` | 新增，proto 类主 dex 保留规则 |

### 编译环境
- Gradle 9.4.1 / AGP / SDK 35 / NDK 28 / JDK 17
- 国内网络需配置镜像仓库（aliyun / tencent）
- 编译命令：`./gradlew assembleDebug`
- 输出：`app/build/outputs/apk/debug/`

### 遗留问题
- 首次安装后需先启动 App 初始化主题数据库（`ThemeManager`），再启用 IME
- 否则报 `No valid theme available`
