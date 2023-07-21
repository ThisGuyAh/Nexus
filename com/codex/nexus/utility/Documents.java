package com.codex.nexus.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Documents {

    private Documents() {
    }

    public static List<String> read(String path) {
        List<String> output = new ArrayList<>();

        try {
            output = Files.readAllLines(Paths.get(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return output;
    }

    public static List<List<String>> split(String token, List<String> lines) {
        List<String> current = new ArrayList<>();
        List<List<String>> output = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (line.startsWith(token)) {
                if (found) {
                    output.add(new ArrayList<>(current));
                    current.clear();
                } else {
                    found = true;
                }
            }

            if (found) {
                current.add(line);
            }
        }

        output.add(new ArrayList<>(current));

        return output;
    }

    public static List<List<String>> collect(String startToken, String endToken, List<String> lines) {
        List<String> current = new ArrayList<>();
        List<List<String>> output = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (!line.startsWith(startToken) || !line.equalsIgnoreCase(startToken)) {
                found = true;
            }
            if (found) {
                current.add(line);
            }
            if (line.startsWith(endToken) || line.equalsIgnoreCase(endToken)) {
                output.add(new ArrayList<>(current));
                current.clear();

                found = false;
            }
        }

        return output;
    }

    public static String concatenate(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();

        for (var line : lines) {
            stringBuilder.append(line + "\n");
        }

        return stringBuilder.toString();
    }

}
