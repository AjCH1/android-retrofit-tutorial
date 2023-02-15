package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The TopRatedResponse class will store a JSON array of the top 20 rated movies. This data
 * will be stored in a List<Movie> Object.
 *
 * @SerializedName("results") will help parse the array which then can be parsed further in
 * the Movie class.
 */
public class TopRatedResponse {
    @SerializedName("results")
    private List<Movie> results;

    //get a JSON array for results
    public TopRatedResponse(List<Movie> results){
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
