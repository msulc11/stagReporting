package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.WeekdayStats;

import java.util.Arrays;

public class DepartmentWeekdaysReporting {

    public static WeekdayStats[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        var json = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(json, ActionsList.class);

        return Arrays.stream(days)
                .map(day -> {
                    long count = actionsList.items.stream()
                            .filter(a -> day.equals(a.denZkr))
                            .count();
                    return new WeekdayStats(day, count);
                })
                .toArray(WeekdayStats[]::new);
    }
}
