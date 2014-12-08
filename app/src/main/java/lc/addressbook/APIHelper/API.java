package lc.addressbook.APIHelper;

import lc.addressbook.Models.Results;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by QA on 14-12-08.
 */
public interface API {


    @GET("/")
    public void getContacts(@Query("results") int result, Callback<Results> response);
}
