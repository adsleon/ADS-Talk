package com.mobgen.mybooks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by feantury on 29/11/2017.
 */

public interface BookService {

    @GET("/")
    Call<List<Book>> getBooks();
}
