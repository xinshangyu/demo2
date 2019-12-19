package com.vincent.filepicker.filter.loader;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import static android.provider.MediaStore.MediaColumns.MIME_TYPE;

/**
 * Created by Vincent Woo
 * Date: 2016/10/12
 * Time: 14:48
 */

public class FileLoader extends CursorLoader {
    private static final String[] FILE_PROJECTION = {
            //Base File
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATE_ADDED,

            //Normal File
            MediaStore.Files.FileColumns.MIME_TYPE
    };

    private FileLoader(Context context, Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    public FileLoader(Context context, String filter) {
        super(context);
        setProjection(FILE_PROJECTION);
        setUri(MediaStore.Files.getContentUri("external"));
        setSortOrder(MediaStore.Files.FileColumns.DATE_ADDED + " DESC");

        //文件过滤
//        String selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlsx'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xls'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppt'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.pptx'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.pdf'" +
//                " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.epub'" + ")";
        setSelection(filter);

//        String[] selectionArgs;
//        selectionArgs = new String[] { "text/txt", "text/plain" };
//        setSelectionArgs(selectionArgs);
    }
}
