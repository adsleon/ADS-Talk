package com.mobgen.mybooks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListActivity extends AppCompatActivity implements BookAdapter.BookListener, LoadBooksTask.BookLoadListener {

    private BookAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        progressBar = findViewById(R.id.pb_load);
        list = findViewById(R.id.rv_list);
        list.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://5f4069cb.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        new LoadBooksTask(retrofit.create(BookService.class), progressBar, this).execute();
    }

    @Override
    public void onBookClicked(Book book) {
        startActivity(BookDetailActivity.getIntent(this, book));
    }

    @Override
    public void onBooksLoaded(List<Book> books) {
        adapter = new BookAdapter(books, this);
        list.setAdapter(adapter);
    }
}
