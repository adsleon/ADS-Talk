package com.mobgen.mybooks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by feantury on 22/11/2017.
 */

public class LoadBooksTask extends AsyncTask<Void, Void, List<Book>> {

    private ProgressBar progress;
    private BookLoadListener listener;
    private BookService bookService;

    public LoadBooksTask(BookService bookService, ProgressBar progress, BookLoadListener listener) {
        this.progress = progress;
        this.listener = listener;
        this.bookService = bookService;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        progress.setVisibility(View.GONE);
        listener.onBooksLoaded(books);
    }

    @Override
    protected List<Book> doInBackground(Void... voids) {
        try {
            return bookService.getBooks().execute().body();
        } catch (IOException e) {
            Log.e("Error", "Error while loading books", e);
            return null;
        }
    }

    public interface BookLoadListener {
        public void onBooksLoaded(List<Book> books);
    }
}
