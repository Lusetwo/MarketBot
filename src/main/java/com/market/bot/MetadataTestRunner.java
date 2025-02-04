package com.market.bot;

import com.market.bot.common.genarator.DatabaseMetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MetadataTestRunner implements CommandLineRunner {

    @Autowired
    private DatabaseMetadataExtractor extractor;

    @Override
    public void run(String... args) throws Exception {
        extractor.extractTableInfo().forEach(table -> {
            System.out.println("Table: " + table.getTableName());
            table.getColumns().forEach(column -> {
                System.out.println(" - Column: " + column.getName() + " (" + column.getType() + ")");
            });
        });
    }
}
