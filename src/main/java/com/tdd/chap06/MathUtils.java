package com.tdd.chap06;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class MathUtils {
    public static long sum(File dataFile) {
        try {
            return Files.readAllLines(dataFile.toPath()).stream()
                .mapToLong(Long::parseLong)
                .sum();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
