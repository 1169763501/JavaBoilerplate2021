package com.nbnfsoft.admin.utils.sail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface IHandleTimestamp {

    default String toTimestamp(Date date) {
        if (date == null) return null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String output = String.format("TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS')", df.format(date));
        return output;
    }
}
