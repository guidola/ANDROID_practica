package domel.ecampus;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by jorgemt94 on 13/5/16.
 */
public class Tools{

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean fileExists(Context context, String fname){
        File file = context.getFileStreamPath(fname);
        return file.exists();
    }

}
