package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PReader {
    File file = new File("src/main/resources/Env.properties");

    FileInputStream fis = new FileInputStream(file);
    public Properties prop = null;

    public PReader() throws IOException {
        prop = new Properties();
        prop.load(fis);
    }

    public String getUrl() { return prop.getProperty("url"); }

    public String readFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        String file = new String(Files.readAllBytes(path));
        return file;
    }

    public List<String> getVehicleReg(String pathToFile) throws IOException {
        String file = readFromFile(pathToFile);
        String pattern = "\\b[A-Z]{2}\\d{2,}\\s?[A-Z]{3,}\\b";
        ArrayList<String> regData = new ArrayList<>();
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(file);
        while (matcher.find()) {
            regData.add(matcher.group());
        }
        return regData;
    }
}
