package ua.nix.libs.csvparser;

import java.util.*;

public class CSVParser {

    private List<String> dataList;
    private String parsePattern;

    public CSVParser(List<String> dataList, String parsePattern) {
        this.dataList = dataList;
        this.parsePattern = parsePattern;
    }

    public List<String> parse(int index) {
        return Arrays.asList(dataList.get(index).split(parsePattern));
    }

    public int getRowsNums() {
        return dataList.size();
    }
}