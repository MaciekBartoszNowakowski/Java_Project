package org.example.fileHandling;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Reader {

    public static InputData read(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(fileName), InputData.class);
        } catch (IOException e) {
            System.err.println("Error during reading JSON: " + e.getMessage());

            throw new RuntimeException(e);
        }

    }

}
