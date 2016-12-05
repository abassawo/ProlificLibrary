package com.abasscodes.prolificlibrary.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.ui.edit_book.EditActivity;
import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.ui.show_book_detail.DetailActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/15/16.
 */

public abstract class BasePresenterActivity extends AppCompatActivity{

   private static final String TAG = BasePresenterActivity.class.getSimpleName() ;
   public static final int DELETED_ITEM_CODE = 999;



   @Override
   public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
      super.onCreate(savedInstanceState, persistentState);
   }


   public void showBookDetail(Book book) {
      Intent intent = DetailActivity.makeIntent(this, book);
      startActivity(intent);
   }


   public void editBook(int id, Book book) {
      Intent intent = EditActivity.createEditIntent(this, id, book);
      startActivity(intent);
   }


   public void fillOutNewBookForm() {
      Intent intent = EditActivity.fillOutNewBook(this, null);
      startActivity(intent);
   }

   public void fillOutNewBookForm(Book book){
      Intent intent = EditActivity.fillOutNewBook(this, book);
      startActivity(intent);
   }


   public void showNetworkSettings(){
      startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
   }


   public void deleteBook(Book book) {
      final String title = book.getTitle();
      Call<Book> call = APIClient.getInstance().deleteBook(book.getId());
      call.enqueue(new Callback<Book>() {
         @Override
         public void onResponse(Call<Book> call, Response<Book> response) {
            Toast.makeText(BasePresenterActivity.this, "1 item deleted : " + title, Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onFailure(Call<Book> call, Throwable t) {
            Log.d(TAG, "failure " + t);
         }
      });
      Intent homeIntent = new Intent(this, MainTabsActivity.class);
      homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivityForResult(homeIntent, DELETED_ITEM_CODE);
   }

}
