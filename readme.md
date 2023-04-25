
## 本项目主要用于kotlin使用探究，包含特性、骚操作，力求提升开发效率，有更多的时间enjoy life。

### 1：目录梳理：

character 特性-Kotlin
 - proxy-代理委托
 - inline-内联函数（语法糖）
 - ...
coroutine 协程-Kotlin
 - catch-错误捕获
 - suspend-挂起函数
 - ...
flow 流-Kotlin
 - operator-操作符
 - order-顺序
 - principle-原理
 - ...
thirdparty 第三方库
 -Koin 依赖注入库
 -Coil 图片加载库
   Coil 库中应用了多种设计模式，主要如下：
   Builder 模式：通过 Builder 模式生成 ImageRequest、FetchResult、Vignette 等多种对象。比如在 ImageRequest.Builder 类中，将 ImageRequest 对象的创建过程通过链式调用的方式实现，可读性更好，使其易于使用。
   工厂模式：Coil 库采用了多个工厂类来创建实例。比如 FetcherFactory 用于创建 Fetcher 对象，TransformerFactory 用于创建 Transformer 对象等。
   责任链模式：Fetcher、DiskCache、MemoryCache 等模块中均使用了责任链模式。比如在 Fetcher 中，使用了一个 FetcherChain 对象，包含了多个 Fetcher 对象，依次通过责任链来处理请求。
   策略模式：在 Predicate、Target、Scale 等模块中，应用了策略模式。比如在 Scale 模块中，使用了多种 Scaling 抽象策略，用于根据 ImageView 和 Bitmap 计算调整后的 Bitmap 尺寸。
   单例模式：Coil 库中的 ImageLoader 类是一个单例类，用于管理图片加载的内部状态，并维护了一个 MemoryCache 对象，可用来保存已经下载的 Bitmap。
   观察者模式：在 ImageLoader、MemoryCache 等模块中，使用了观察者模式。比如 MemoryCache 中设计了一个清除过期内存的机制，并通过观察者的方式通知监听器。
   综上所述，Coil 库中应用了多种设计模式，每种模式都有其独特的特点，使得开发过程更加简单和灵活。
 - OkDownload 下载库
   OkDownload是一个开源的、泛用性的下载库，通过不同的类和接口来支持多种用例。
   以下是OkDownload运用的设计模式及其应用：
   Builder模式：OkDownload中的DownloadRequest请求对象是通过Builder模式创建的，相比传统的构造函数而言，Builder模式使得代码可读性更好，避免了参数过多而导致的代码难以维护。
   装饰器模式：OkDownload中提供了CallbackDispatcher类，对IEventListener接口进行了装饰，使得IEventListener更易用且功能更加完善。
   观察者模式：OkDownload中的回调接口均实现了观察者模式，通过注册监听器实现下载状态的监听。
   状态模式：在OkDownload中的DownloadListener中，定义了多个下载状态的回调方法，使用了状态模式来管理下载状态的变化。
   策略模式：在OkDownload中的Connection、OutputStream、Filename、OkDownload的线程数量等方面均使用了策略模式，以便能够针对不同的场景采取不同的策略，从而保证OkDownload在不同情形下的良好表现。
   单例模式：OkDownload的DownloadTask类使用了单例模式，确保仅有一个DownloadTask实例在运行，从而简化并发度控制的实现，提升并发能力。
   工厂模式：OkDownload中的DownloadStrategyFactory类、DownloadTaskFactory类等，均是工厂模式的实现，用于创建Request、DownloadTask等对象。
   综上所述，通过使用多种设计模式，OkDownload实现了高可扩展性、可维护性和高可读性的下载功能。
 - ...
design 设计模式-全面
 - state machine-状态机
 - producer consumer-生产者消费者