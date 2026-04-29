package pro1.reports.report1;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.BooksList;
import pro1.apiDataModel.TeacherCourse;
import pro1.apiDataModel.TeacherCoursesList;
import pro1.reports.report1.reportDataModel.CourseBook;

import java.util.ArrayList;
import java.util.List;

public class TeacherBooksReporting {

    public static List<CourseBook> GetReport(DataSource dataSource, String rok, int ucitIdno, String katedra){
        var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra  );

        var courses = new Gson().fromJson(coursesJson, TeacherCoursesList.class);

        var reportItems = new ArrayList<CourseBook>();

        // TODO 1.3: Pro každý předmět získej z dataSource ještě seznam knih. Pro každou z nich přidej prvek do reportItems.
        for(var course : courses.items){
            var booksJson = dataSource.getLiteraturaPredmetu(course.code,katedra);
            var books = new Gson().fromJson(booksJson, BooksList.class);
            for(var book : books.items){
                reportItems.add(new CourseBook(book.title, book.author,course.code));
            }
        }
        return reportItems;
    }
}
