package com.abasscodes.prolificlibrary.presenter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.interactions.edit_book.EditActivity;
import com.abasscodes.prolificlibrary.interactions.show_all_books.MainTabsActivity;
import com.abasscodes.prolificlibrary.interactions.show_book_detail.DetailActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.model.api.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/15/16.
 */

public abstract class BasePresenterActivity<P extends Mvp.Presenter> extends AppCompatActivity{

   private static final String TAG = BasePresenterActivity.class.getSimpleName() ;
   public static final int DELETED_ITEM_CODE = 999;
   public Fragment currentFragment;
   private BookRepository bookRepo;


   public abstract P getPresenter();

   @Override
   public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
      super.onCreate(savedInstanceState, persistentState);
   }

//   @Override
//   public void onConfigurationChanged(Configuration newConfig) {
//      super.onConfigurationChanged(newConfig);
//      getPresenter().updateUI(this);
//   }
//
//   @Override
//   protected void onResume() {
//      super.onResume();
//      getPresenter().updateUI(this);
//   }


   public void showBookDetail(Book book) {
      Intent intent = DetailActivity.makeIntent(this, book);
      startActivity(intent);
   }




   public void editBook(int id, Book book) {
      Intent intent = EditActivity.createEditIntent(this, id, book);
      startActivity(intent);
   }


   public void fillOutNewBookForm() {
      Intent intent = EditActivity.fillOutNewBook(this);
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
