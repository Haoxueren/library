package com.haoxueren.library.log;

import android.util.Log;

import java.util.Arrays;


/**
 * 一款小巧强大的日志输出工具；
 *
 * @author Haoxueren
 */
public class MyLog {
    /**
     * 默认打开日志；
     */
    public static boolean debug = true;

    public static String tag = "Haoxueren";

    /**
     * true表示打开日志，false表示关闭日志
     */
    public static void setDebug(boolean debug) {
        MyLog.debug = debug;
    }

    /**
     * 设置日志的TAG；
     */
    public static void setTag(String tag) {
        MyLog.tag = tag;
    }

    /**
     * 输出普通日志信息
     */
    public static void info(Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            String header = thread.getClassName() + "." + thread.getMethodName() + "()";
            Log.d(tag, header);
            Log.i(tag, "#" + thread.getLineNumber() + " --> " + Arrays.toString(message));
        }
    }

    /**
     * 输出捕获异常信息
     */
    public static void error(Object... message) {
        if (debug) {
            ThreadInfo thread = new ThreadInfo();
            String header = thread.getClassName() + "." + thread.getMethodName() + "()";
            Log.d(tag, header);
            Log.e(tag, "#" + thread.getLineNumber() + " --> " + Arrays.toString(message));
        }
    }

    /**
     * 输出捕获异常信息
     */
    public static void error(Throwable throwable) {
        if (debug) {
            StackTraceElement[] elements = throwable.getStackTrace();
            Log.e(tag, throwable.getClass().getSimpleName() + "：" + throwable.getMessage());
            for (StackTraceElement element : elements) {
                Log.w(tag, element.getClassName() + "." + element.getMethodName() + "(Line: " + element.getLineNumber
                        () + ")");
            }
        }
    }

    /**
     * System.out.println()
     */
    public static void println(Object... message) {
        if (debug) {
            System.out.println(Arrays.toString(message));
        }
    }

}
