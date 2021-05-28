package ua.nix.libs.csvdatastore;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ua.nix.libs.csvreader.CSVReader;
import ua.nix.libs.csvparser.CSVParser;

public class CSVDataStore {

    private final Map<Integer, List<String>> data = new TreeMap<>();
    private final CSVReader csvReader;
    private List<String> nonParsedDataFromFile;
    private List<String> header;

    public CSVDataStore(Class<?> objectRef) {
        this.csvReader = new CSVReader(objectRef);
    }

    public void readFile(String fileName) throws IOException {
        try {
            this.csvReader.readFile(fileName);
        } catch (IOException ioException){
            ioException.printStackTrace();
            throw new IOException(ioException.getMessage());
        }
        this.nonParsedDataFromFile = this.csvReader.getDataList();
    }

    public void parseFile(String parsePattern) {
        CSVParser csvParser = new CSVParser(this.nonParsedDataFromFile, parsePattern);
        for (int indexFrom = 0, indexTo = -1; indexFrom < csvParser.getRowsNums(); indexFrom++, indexTo++) {
            if (indexFrom == 0) {
                this.header = csvParser.parse(indexFrom);
            } else {
                this.data.put(indexTo, csvParser.parse(indexFrom));
            }
        }
    }

    public String getData(int row, int column){
       return this.data.get(row).get(column);
    }

    public String getData(int row, String columnName) {
        String result = "";
        for(int index = 0; index < this.header.size(); index++){
            if(columnName.equals(this.header.get(index))){
                result = getData(row, index);
                break;
            }
        }
        return result;
    }

    public Map<Integer, List<String>> getAllData() {
        return this.data;
    }

    public List<String> getHeader(){
        return header;
    }
}