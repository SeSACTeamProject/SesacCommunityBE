package com.everysesac.backend.global.converter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;

public class PrettySqlFormatter implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connId, String now, long elapsed,
                                String category, String prepared, String sql, String url) {
        // DDL/DML 구분 처리 추가
        String formattedSql = sql.trim().isEmpty() ? sql :
                sql.trim().toLowerCase().startsWith("create") ||
                        sql.trim().toLowerCase().startsWith("alter") ?
                        FormatStyle.DDL.getFormatter().format(sql) :
                        FormatStyle.BASIC.getFormatter().format(sql);

        return String.format("[%dms] %s\n%s", elapsed, category, formattedSql);
    }

}
