package com.demo.library.log;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.demo.library.application.LogUtilApplication;
import com.demo.library.handler.CrashHandler;
import com.demo.library.utils.FileUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by  wsl
 * on 2019/4/30 13:38
 * <p>
 * 如果使用缓存文件，文件保存在主存贮路径下，以项目名称来命名的文件
 * <p>
 * 规则：使用LogUtils时，需要进行初始化。
 * 初始化分为两种：
 * 1、在您项目Application的onCreate中加入  LogUtils.init(this, true)。
 * 可以看到 LogUtils.init(Context context, boolean useCrashCatch)
 * 中有两个参数，context:上下文、useCrashCatch：是否需要输出崩溃日志到日志文件。根据自己需要进行配置；
 * 2、使您项目的Application继承LogUtils的LogUtilApplication，
 * 并在您项目Application的onCreate中加入LogUtils.launchCrashCatch()；
 * 在方法2中，如果您的项目中不需要打印崩溃日志到本地文件，则不需要在Application的onCreate中加入LogUtils.launchCrashCatch()；
 */
public class LogUtils {
    /**
     * isWrite:用于开关是否吧日志写入txt文件中</p>
     */
    private static boolean isWrite = false;
    /**
     * isDebug :是用来控制，是否打印日志
     */
    private static final boolean isDeBug = true;
    /**
     * 存放日志文件的所在路径
     */
//    private static final String DIRPATH = AppConfig.LOG_DIRPATH;
    private static final String DIRPATH = "log/";
    /**
     * 存放日志的文本名
     */
//    private static final String LOGNAME = AppConfig.LOG_FILENAME;
    private static final String LOGNAME = "log.log";
    /**
     * 设置时间的格式
     */
    private static final String INFORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * VERBOSE日志形式的标识符
     */
    public static final int VERBOSE = 5;
    /**
     * DEBUG日志形式的标识符
     */
    public static final int DEBUG = 4;
    /**
     * INFO日志形式的标识符
     */
    public static final int INFO = 3;
    /**
     * WARN日志形式的标识符
     */
    public static final int WARN = 2;
    /**
     * ERROR日志形式的标识符
     */
    public static final int ERROR = 1;

    /**
     * 初始化LogUtils工具
     * 获取所需的context以及是否启动全局异常捕获
     * 规则：使用LogUtils时，需要进行初始化。
     * 初始化分为两种：
     * 1、在您项目Application的onCreate中加入  LogUtils.init(this, true)。
     * 可以看到 LogUtils.init(Context context, boolean useCrashCatch)
     * 中有两个参数，context:上下文、useCrashCatch：是否需要输出崩溃日志到日志文件。根据自己需要进行配置；
     * 2、使您项目的Application继承LogUtils的LogUtilApplication，
     * 并在您项目Application的onCreate中加入LogUtils.launchCrashCatch()；
     * 在方法2中，如果您的项目中不需要打印崩溃日志到本地文件，则不需要在Application的onCreate中加入LogUtils.launchCrashCatch()；
     *
     * @param context       上下文
     * @param useCrashCatch 是否启动全局异常捕获
     */
    public static void init(Context context, boolean useCrashCatch) {
        LogUtilApplication.init(context, useCrashCatch);
    }

    /**
     * 启动全局异常捕获
     */
    public static void launchCrashCatch() {
        LogUtilApplication.launchCrashCatch();
    }

    /**
     * 设置是否打印exception日志
     *
     * @param printExceptionLog 是否打印崩溃日志.true:打印；false：不打印
     */
    public static void setPrintExceptionLog(boolean printExceptionLog) {
        CrashHandler.setPrintExceptionLog(printExceptionLog);
    }

    /**
     * 把异常用来输出日志的综合方法
     *
     * @param @param tag 日志标识
     * @param @param throwable 抛出的异常
     * @param @param type 日志类型(VERBOSE,DEBUG,INFO,WARN,ERROR)
     * @param @param write 是否写入缓存文件(true:写入缓存文件；false：不写入缓存文件 )
     * @return void 返回类型
     * @throws
     */
    public static void log(String tag, Throwable throwable, int type, boolean write) {
        log(tag, exToString(throwable), type, write);
    }

    /**
     * 用来输出日志的综合方法（文本内容）
     *
     * @param @param tag 日志标识
     * @param @param msg 要输出的内容
     * @param @param type 日志类型(VERBOSE,DEBUG,INFO,WARN,ERROR)
     * @param @param write 是否写入缓存文件(true:写入缓存文件；false：不写入缓存文件 )
     * @return void 返回类型
     * @throws
     */
    public static void log(String tag, String msg, int type, boolean write) {
        isWrite = write;
        switch (type) {
            case VERBOSE:
                logV(tag, msg);// verbose等级
                break;
            case DEBUG:
                logD(tag, msg);// debug等级
                break;
            case INFO:
                logI(tag, msg);// info等级
                break;
            case WARN:
                logW(tag, msg);// warn等级
                break;
            case ERROR:
                logE(tag, msg);// error等级
                break;
            default:
                logV(tag, msg);// verbose等级
                break;
        }
    }

    /**
     * verbose等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    private static void logV(String tag, String msg) {
        // 是否开启日志输出
        if (isDeBug) {
            Log.v(tag, msg);
        }
        // 是否将日志写入文件
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * debug等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    private static void logD(String tag, String msg) {
        if (isDeBug) {
            Log.d(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * info等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    private static void logI(String tag, String msg) {
        if (isDeBug) {
            Log.i(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * warn等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    private static void logW(String tag, String msg) {
        if (isDeBug) {
            Log.w(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * error等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     */
    private static void logE(String tag, String msg) {
        if (isDeBug) {
            Log.e(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * verbose等级的日志输出
     *
     * @param tag tag标记
     * @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    /**
     * debug等级的日志输出
     *
     * @param tag tag标记
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    /**
     * info等级的日志输出
     *
     * @param tag tag标记
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    /**
     * warn等级的日志输出
     *
     * @param tag tag标记
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    /**
     * error等级的日志输出
     *
     * @param tag tag标记
     * @param msg 内容
     * @return void 返回类型
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     * verbose等级的日志输出
     *
     * @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    public static void v(String msg) {
        v("VerboseTag", msg);
    }

    /**
     * debug等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void d(String msg) {
        d("DebugTag", msg);
    }

    /**
     * info等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void i(String msg) {
        i("InfoTag", msg);
    }

    /**
     * warn等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void w(String msg) {
        w("WarnTag", msg);
    }

    /**
     * error等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     */
    public static void e(String msg) {
        e("ErrorTag", msg);
    }

    /**
     * 用于把日志内容写入制定的文件
     *
     * @param @param tag 标识
     * @param @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    private static void write(String tag, String msg) {
        String path = FileUtils.getLocalSavePath_log(LogUtilApplication.getContext(), LOGNAME);
        if (TextUtils.isEmpty(path)) {
            return;
        }
        String log = DateFormat.format(INFORMAT, System.currentTimeMillis())
                + tag
                + "========>>"
                + msg
                + "\r\n=================================分割线=================================\r\n";
        FileUtils.write2File(path, log, true);
    }

    /**
     * 用于把崩溃日志内容写入制定的文件
     *
     * @param ex 异常
     */
    public static void write(Throwable ex) {
        write("", exToString(ex));
    }

    /**
     * 用于把自定义日志内容写入制定的文件
     *
     * @param content 自定义日志内容
     */
    public static void write(String content) {
        write("", content);
    }

    /**
     * 把异常信息转化为字符串
     *
     * @param ex 异常信息
     * @return 异常信息字符串
     */
    private static String exToString(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();
        return result;
    }
}
