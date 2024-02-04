package com.dataprev.abono.errors;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class ReadErrorListener {

    @OnSkipInRead
    public void skipInRead(Throwable th) {
        if(th instanceof JsonParseException) {
            createFile("src/main/resources/PagamentoAbonoReportJobErrors/ReadErrors/errorsLine.txt", ((JsonParseException) th).getLocation().getLineNr());
        }
    }

    private void createFile(String filePath, Integer line) {
        try(FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(line + "\n");
        }
        catch (Exception e) { }
    }
}
