package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.ExamsStats;

import java.util.stream.Collectors;

public class DepartmentExamsStatsReporting {

    public static ExamsStats GetReport(DataSource dataSource, String katedra) {
        var json = dataSource.getTerminyZkousek2(katedra);
        var examsList = new Gson().fromJson(json, ExamsList.class);

        long realizedCount = examsList.items.stream()
                .filter(e -> e.obsazeni != null && Integer.parseInt(e.obsazeni) >= 1)
                .count();

        var teacherIds = examsList.items.stream()
                .filter(e -> e.ucitIdno != null && e.ucitIdno != 0)
                .map(e -> e.ucitIdno)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return new ExamsStats(realizedCount, teacherIds);
    }
}
