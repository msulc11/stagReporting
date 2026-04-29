package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDurationStats;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class ThesisDurationReporting {

    public static ThesisDurationStats[] GetReport(DataSource dataSource, String katedra, String[] years) {
        return Arrays.stream(years)
                .map(year -> {
                    var json = dataSource.getKvalifikacniPrace(year, katedra);
                    var thesisList = new Gson().fromJson(json, ThesisList.class);

                    var avg = thesisList.items.stream()
                            .filter(t -> t.datumZadani != null && t.datumZadani.isValid()
                                    && t.datumOdevzdani != null && t.datumOdevzdani.isValid())
                            .mapToLong(t -> ChronoUnit.DAYS.between(
                                    t.datumZadani.toLocalDate(),
                                    t.datumOdevzdani.toLocalDate()))
                            .average()
                            .orElse(0);

                    return new ThesisDurationStats(year, Math.round(avg));
                })
                .toArray(ThesisDurationStats[]::new);
    }
}
