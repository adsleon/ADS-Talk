package com.mobgen.mybooks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by feantury on 22/11/2017.
 */

public class BookDetailActivity extends AppCompatActivity {

    public static Intent getIntent(Context context, Book book){
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("book", book);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Book book = intent.getParcelableExtra("book");

    }
}
