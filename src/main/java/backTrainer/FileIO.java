package backTrainer;

import org.json.JSONObject;

import java.io.*;
import java.util.stream.Collectors;

public class FileIO {
    public static final String FILEPATH = ".backTrainerConfig";
    private static final String TIME_KEY = "time";

    public static int readTime() {
        try {
            return readJSONFromFile(FILEPATH).getInt(TIME_KEY);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static void writeTime(int time) {
        writeJSONToFile(new JSONObject().put(TIME_KEY, time));
    }

    private static void writeJSONToFile(JSONObject jsonObject) {
        String jsonToWrite = jsonObject.toString();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILEPATH))) {

            bw.write(jsonToWrite);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject readJSONFromFile(String pathToFile) {

        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {

            String fileContent;

//            sCurrentLine = br.readLine();
            fileContent =  br.lines().collect(Collectors.joining());

            return new JSONObject(fileContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
