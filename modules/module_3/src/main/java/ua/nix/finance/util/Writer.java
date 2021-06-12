package ua.nix.finance.util;

import com.opencsv.CSVWriter;
import ua.nix.finance.exceptions.CSVFileException;
import ua.nix.finance.persistence.entity.DischargeDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {

    public static void writeBalanceToFile(List<DischargeDto> dischargeDtoList, Integer debet, Integer balance) throws CSVFileException {
        try {
            File file = new File("src/main/resources/discharge.csv");
            if(file.exists()){
                file.delete();
            }
            List<String[]> dataList = new ArrayList<>();
            String[] header = new String[]{"id", "funds", "date_time", "type", "category"};
            dataList.add(header);
            for (DischargeDto dischargeDto : dischargeDtoList) {
                String[] data = new String[5];
                data[0] = dischargeDto.getId();
                data[1] = dischargeDto.getFunds();
                data[2] = dischargeDto.getDateTime();
                data[3] = dischargeDto.getType();
                data[4] = dischargeDto.getName();
                dataList.add(data);
            }
            dataList.add(new String[]{debet.toString()});
            dataList.add(new String[]{balance.toString()});
            try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/discharge.csv", true))) {
                writer.writeAll(dataList);
            } catch (IOException ioException) {
                throw new IOException(ioException.getMessage(), ioException);
            }
        } catch (IOException ioException) {
            throw new CSVFileException(ioException.getMessage(), ioException);
        }
    }
}
