package com.mobgen.mybooks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by feantury on 20/11/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;
    private BookListener listener;

    public BookAdapter(List<Book> books, BookListener listener) {
        this.books = books;
        this.listener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(this.books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView author;
        private TextView isbn;
        private ImageView cover;
        private TextView date;

        public BookViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBookClicked(books.get(getAdapterPosition()));
                }
            });
        }

        public void bind(Book book) {
            title.setText(book.getTitle());
        }
    }

    interface BookListener {
        public void onBookClicked(Book book);
    }
}
