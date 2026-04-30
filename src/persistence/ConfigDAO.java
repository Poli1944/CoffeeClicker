package persistence;

import business.entities.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ConfigDAO {

    private static final String CONFIG_PATH = "config.json";


    public Config readConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(CONFIG_PATH));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
            br.close();

            String json = sb.toString();


            String ip       = extractString(json, "ip");
            String database = extractString(json, "database");
            String username = extractString(json, "username");
            String password = extractString(json, "password");
            int port        = Integer.parseInt(extractString(json, "port"));

            return new Config(port, ip, database, username, password);

        } catch (IOException e) {
            System.out.println("Error reading config.json: " + e.getMessage());
            return null;
        }
    }


    private String extractString(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);

        if (keyIndex == -1) return "";

        int colonIndex = json.indexOf(":", keyIndex);

        int valueStart = colonIndex + 1;
        while (valueStart < json.length() && json.charAt(valueStart) == ' ') {
            valueStart++;
        }


        if (json.charAt(valueStart) == '"') {

            int endIndex = json.indexOf("\"", valueStart + 1);
            return json.substring(valueStart + 1, endIndex);
        } else {
            int endIndex = valueStart;
            while (endIndex < json.length() &&
                    json.charAt(endIndex) != ',' &&
                    json.charAt(endIndex) != '}') {
                endIndex++;
            }
            return json.substring(valueStart, endIndex).trim();
        }
    }
}