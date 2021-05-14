package ua.nix.datestandart.entity.generator;

import ua.nix.datestandart.entity.RandomDate;

public class Generator {
    private static Generator instance;

    public String generateRandomData(int quantity) {
        StringBuilder sb = new StringBuilder();
        RandomDate randomDate;
        for (int index = 0; index < quantity; index++) {
            randomDate = new RandomDate();
            sb.append(randomDate.getInfo());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        System.out.println("—генерированы следующие случайные даты : "+ "\n" + sb);
        return sb.toString();
    }

    public static Generator getInstance() {
        if(instance == null){
            instance = new Generator();
        }
        return instance;
    }
}
