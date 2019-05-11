


#用于Android端的日志输出以及程序崩溃全局监测插件<br>

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
中文 | English
功能强大，UI简洁，交互优雅的通用弹窗！可以替代Dialog，PopupWindow，PopupMenu，BottomSheet，DrawerLayout，Spinner等组件，自带十几种效果良好的动画， 支持完全的UI和动画自定义！它有这样几个特点：

功能强大，内部封装了常用的弹窗，内置十几种良好的动画，将弹窗和动画的自定义设计的极其简单
UI和动画简洁，遵循Material Design，在设计动画的时候考虑了很多细节，过渡，层级的变化；或者说是模拟系统组件的动画，具体可以从Demo中感受
交互优雅，实现了优雅的手势交互以及智能的嵌套滚动，具体看Demo
适配全面屏，目前适配了小米，华为，谷歌，OPPO，VIVO，三星，魅族，一加全系全面屏手机
通用性，项目需求复杂多变，产品经理天马行空，虽然很难做到UI的通用，但是你可以看到交互和动画完全可以通用；至于弹窗的UI和逻辑可能需要你自定义
易用性，所有的自定义弹窗只需继承对应的类，实现你的布局，然后在onCreate方法写逻辑即可
编写本库的初衷有以下几点：

项目有这样常见需求：中间和底部弹出甚至可拖拽的对话框，指定位置的PopupMenu或者PopupWindow，指定区域阴影的弹出层效果
市面上已有的类库要么功能不足够，要么交互效果不完美，有着普遍的缺点，就像BottomSheet存在的问题一样。比如：窗体消失的动画和背景渐变动画不一致，窗体消失后半透明背景仍然停留一会儿
设计思路： 综合常见的弹窗场景，我将其分为几类：

Center类型，就是在中间弹出的弹窗，比如确认和取消弹窗，Loading弹窗
Bottom类型，就是从页面底部弹出，比如从底部弹出的分享窗体，知乎的从底部弹出的评论列表，我内部会处理好手势拖拽和嵌套滚动
Attach类型，就是弹窗的位置需要依附于某个View或者某个触摸点，就像系统的PopupMenu效果一样，但PopupMenu的自定义性很差，淘宝的商品列表筛选的下拉弹窗也属于这种，微信的朋友圈点赞弹窗也是这种。
DrawerLayout类型，就是从窗体的坐边或者右边弹出，并支持手势拖拽；好处是与界面解耦，可以在任何界面显示DrawerLayout
大图浏览类型，就像掘金那样的图片浏览弹窗，带有良好的拖拽交互体验，内部嵌入了改良的PhotoView
全屏弹窗，弹窗是全屏的，就像Activity那样，可以设置任意的动画器；适合用来实现登录，选择性的界面效果。
ScreenShot
