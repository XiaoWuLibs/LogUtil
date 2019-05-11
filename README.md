# LogUtil

[！[]（https://jitpack.io/v/XiaoWuLibs/LogUtil.svg）（https://jitpack.io/#XiaoWuLibs/LogUtil）

用于Android端的日志输出以及程序崩溃全局监测插件
要将Git项目放入您的构建中：

步骤1.将JitPack存储库添加到构建文件中

gradle这个
行家
SBT
leiningen
将其添加到存储库末尾的根build.gradle中：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
步骤2.添加依赖项

	dependencies {
	        implementation 'com.github.XiaoWuLibs:LogUtil:1.1.1'
	}
分享此版本：
