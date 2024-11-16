package org.example.fileHandling;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Writer {

    public static void write(String fileName, OutputData output){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), output);
        } catch (IOException e) {
            System.err.println("Error during writing JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
