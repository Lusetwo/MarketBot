package com.market.bot.common.genarator;

import com.market.bot.dto.common.ColumnInfo;
import com.market.bot.dto.common.TableInfo;
import org.hibernate.tool.schema.extract.spi.TableInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseMetadataExtractor {

    @Autowired
    private DataSource dataSource;

    public List<TableInfo> extractTableInfo() throws SQLException {
        List<TableInfo> tables = new ArrayList<TableInfo>();

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(connection.getCatalog(), null, "%", new String[]{"TABLE"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                TableInfo tableInfo = new TableInfo(tableName);

                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, "%");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    tableInfo.addColumn(new ColumnInfo(columnName, columnType));
                }
                tables.add(tableInfo);
            }
        }
        return tables;
    }
}
