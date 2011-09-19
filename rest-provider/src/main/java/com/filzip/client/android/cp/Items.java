package com.filzip.client.android.cp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public class Items implements BaseColumns {
    public static final String AUTHORITY = "com.mobileunity.client.android.cp.Items";

    private Items() {}

    public static final String TABLENAME = "items";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/items");
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.app.items";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.app.item";
    public static final String _ID = "_id";
    public static final String CREATED_AT = "created_at";
    public static final String CONTENT_STRING = "content_string";

    public static final String SYNC_STATE = "sync_state";



    public static final String DEFAULT_SORT_ORDER = CREATED_AT + " ASC";
}
