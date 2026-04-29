package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {
    @SerializedName("datumZadani")
    public StDate datumZadani;

    @SerializedName("datumOdevzdani")
    public StDate datumOdevzdani;
}
