package com.market.bot.dto.common;

import java.util.ArrayList;
import java.util.List;

public class TableInfo {

    private String tableName;

    private List<ColumnInfo> columns;

    public TableInfo(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
    }

    public void addColumn(ColumnInfo columnInfo) {
        this.columns.add(columnInfo);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
