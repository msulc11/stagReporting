package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("ucitIdno")
    public Long ucitIdno;

    @SerializedName("obsazeni")
    public String obsazeni;
}
