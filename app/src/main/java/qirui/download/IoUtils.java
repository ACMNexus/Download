package qirui.download;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by Luooh on 2017/2/8.
 */
public class IoUtils {

    /**
     * Closes 'closeable', ignoring any checked exceptions. Does nothing if 'closeable' is null.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }
}
