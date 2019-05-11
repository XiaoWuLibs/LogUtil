


#用于Android端的日志输出以及程序崩溃全局监测插件<br>


 ![image](https://github.com/ButBueatiful/dotvim/raw/master/screenshots/vim-screenshot.jpg)
 

####要将Git项目放入您的构建中,使用步骤：<br>

#####步骤1.将JitPack存储库添加到构建文件中


将其添加到Project的build.gradle中：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
#####步骤2.在app的build.gradle中添加依赖项

	dependencies {
	        implementation 'com.github.XiaoWuLibs:LogUtil:1.1.1'
	}

####使用规则：<br>

     * 使用LogUtils时，需要进行初始化。<br>
     * 初始化分为两种：<br>
     * 1、在您项目Application的onCreate中加入  LogUtils.init(this, true)。<br>
     * 可以看到 LogUtils.init(Context context, boolean useCrashCatch)
       中有两个参数，context:上下文、useCrashCatch：是否需要输出崩溃日志到日志文件。根据自己需要进行配置；<br>
     * 2、使您项目的Application继承LogUtils的LogUtilApplication，
       并在您项目Application的onCreate中加入LogUtils.launchCrashCatch()；<br>
     * 在方法2中，如果您的项目中不需要打印崩溃日志到本地文件，则不需要在Application的onCreate中加入LogUtils.launchCrashCatch()；
     *


