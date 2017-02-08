package qirui.download;

import android.app.DownloadManager;
import android.database.DatabaseUtils;
import android.net.NetworkInfo;
import android.net.Uri;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Luooh on 2017/2/8.
 */
public class ReflectUtils  {

    public static Method getMethod(String methodName, Class<?> clazz, Class<?> params) {
        Method method = null;
        try {
            method = clazz.getMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Object invokeMethod(Method method, Object reflectObj, Object... params) {
        if(method != null) {
            try {
                return method.invoke(reflectObj, params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Object invokeStaticMethod(Method method, Object... params) {
        if(method != null) {
            try {
                return method.invoke(null, params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void setAccessAllDownloads(Object reflectObj, boolean accessAllDownloads) {
        Class<?> reflectClass = reflectObj.getClass();
        Method method = getMethod("setAccessAllDownloads", reflectClass, Boolean.class);
        invokeMethod(method, accessAllDownloads);
    }

    public static String formatDuration(long millis) {
        Method method = getMethod("formatDuration", DatabaseUtils.class, Long.class);
        Object object = invokeStaticMethod(method, millis);
        if(object != null) {
            return (String) object;
        }
        return "";
    }

    public static DownloadManager.Query setOnlyIncludeVisibleInDownloadsUi(Object reflectObj, boolean value) {
        Class<?> reflectClass = reflectObj.getClass();
        Method method = getMethod("setOnlyIncludeVisibleInDownloadsUi", reflectClass, Boolean.class);
        return (DownloadManager.Query) invokeMethod(method, reflectObj, value);
    }

    public static Uri getDownloadUri(long id, Object reflectObj) {
        Class<?> reflectClass = reflectObj.getClass();
        Method method = getMethod("getDownloadUri", reflectClass, Long.class);
        return (Uri) invokeMethod(method, reflectObj, id);
    }

    public static NetworkInfo getActiveNetworkInfoForUid(Object reflectObj, long uid) {
        Class<?> reflectClass = reflectObj.getClass();
        Method method = getMethod("getActiveNetworkInfoForUid", reflectClass, Long.class);
        return (NetworkInfo) invokeMethod(method, reflectObj, uid);
    }
}
