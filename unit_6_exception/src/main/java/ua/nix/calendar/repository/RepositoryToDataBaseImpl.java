package ua.nix.calendar.repository;

import ua.nix.calendar.dao.impl.DaoImpl;
import ua.nix.calendar.exceptions.DateException;

import static ua.nix.calendar.util.UtilClass.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoryToDataBaseImpl {

    public static void create(String[] arrayOfParameters) throws DateException {
        List<Long> parameters = new ArrayList<>();
        Boolean isYearLeap = false;
        try {
            isYearLeap = correctValueCheck(arrayOfParameters);
            Collections.addAll(parameters, stringArrayConvertToLongArray(arrayOfParameters));
            long dayCount = countAllPassedDay(parameters, isYearLeap);
            parameters.add(convertAllToMs(dayCount, parameters));
        } catch (DateException d) {
            throw new DateException(d.getMessage());
        }
        DaoImpl.getInstance().create(parameters);
    }

    private static Long countAllPassedDay(List<Long> parameters, Boolean isYearLeap) {
        long dayCount = (parameters.get(2) - 1) * DAY_IN_YEAR + leapYearCount(parameters.get(2)) + dayPassedSinceYearStart(parameters.get(1)) + (parameters.get(0));
        if (isYearLeap && parameters.get(1) > 2) {
            dayCount++;
        }
        return dayCount;
    }

    private static Long convertAllToMs(long dayCount, List<Long> parameters) {
        return dayCount * DAY_TO_MS_VALUE +
                parameters.get(3) * HOUR_TO_MS_VALUE +
                parameters.get(4) * MINUTE_TO_MS_VALUE +
                parameters.get(5) * SECONDS_TO_MS_VALUE +
                parameters.get(6);
    }
}