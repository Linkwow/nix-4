package ua.nix.pathroute.service.fileinteractions;

import java.io.*;

public class Reader {
    private final File file = new File("graphapp\\src\\main\\resources\\input.txt");
    private final File outputFile = new File("graphapp\\src\\main\\resources\\output.txt");
    private String[] outputArray;
    private static Reader instance;

    public String[] readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                outputArray = new String[Integer.parseInt(br.readLine()) + 1];
                for (int index = 0; index < outputArray.length - 1; index++) {
                    outputArray[index] = br.readLine();
                    if (outputArray[index].matches("[a-zA-z�-��-�].*")) {
                        int fieldsCount = Integer.parseInt(br.readLine());
                        outputArray[index] += "/" + fieldsCount;
                        for (int dataIndex = 0; dataIndex < fieldsCount; dataIndex++) {
                            String temp = br.readLine();
                            if (temp.matches("\\d+\\s\\d+")) {
                                outputArray[index] += "/" + temp;
                            } else {
                                throw new IOException("�� ����� ������������ ������ �� ������� ����, ��������� ����");
                            }
                        }
                    } else {
                        throw new IOException("�������� ������ �������� ������������ �������, ��������� ������������ �����");
                    }
                }
                int routePath = Integer.parseInt(br.readLine());
                outputArray[outputArray.length - 1] = routePath + "/";
                for (int i = 0; i < routePath; i++) {
                    outputArray[outputArray.length - 1] += br.readLine() + "/";
                }
            } catch (NumberFormatException numberFormatException) {
                System.err.println("�� �� ����� ���������� �������, ��������� ������������ �����.");
            } catch (IOException ioException) {
                System.err.println(ioException.getMessage());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("��������� �� ����� ��������� ����. ��������� ������������ ���������� ����");
        }
        return outputArray;
    }

    public void readInput() {
        read(file);
    }
    public void readOutput() {
      read(outputFile);
    }

    private void read(File file){
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("��������� �� ����� ��������� ����. ��������� ������������ ���������� ����");
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }

    public static Reader getInstance() {
        if(instance == null){
            instance = new Reader();
        }
        return instance;
    }
}
