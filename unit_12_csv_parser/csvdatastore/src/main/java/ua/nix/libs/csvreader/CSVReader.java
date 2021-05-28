package ua.nix.libs.csvreader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private final List<String> dataList = new ArrayList<>();
    private final ClassLoader classLoader;
    private final String fileNameFromResources;

    public CSVReader(Class<?> objectReference, String fileNameFromResources) {
        classLoader = objectReference.getClassLoader();
        this.fileNameFromResources = fileNameFromResources;
    }

    public void readFile() throws IOException{
        String temp;
        try (InputStream inputStream = classLoader.getResourceAsStream(fileNameFromResources)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((temp = bufferedReader.readLine()) != null){
                dataList.add(temp);
            }
        } catch (IOException ioException){
            throw new IOException(ioException.getMessage());
        }
    }

    public List<String> getDataList() {
        return dataList;
    }
}
