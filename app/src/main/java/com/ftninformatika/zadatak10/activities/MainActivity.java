package com.ftninformatika.zadatak10.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ftninformatika.zadatak10.R;
import com.ftninformatika.zadatak10.adapters.DrawerListViewAdapter;
import com.ftninformatika.zadatak10.dialogs.Dialog;
import com.ftninformatika.zadatak10.fragments.DetailsFragment;
import com.ftninformatika.zadatak10.fragments.SearchFragment;
import com.ftninformatika.zadatak10.fragments.SettingsFragment;
import com.ftninformatika.zadatak10.fragments.WatchedListFragment;
import com.ftninformatika.zadatak10.model.Movie;
import com.ftninformatika.zadatak10.model.NavigationItem;
import com.ftninformatika.zadatak10.net.ormlight.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchFragment.onItemClickListener, WatchedListFragment.onItemClickListener {


    public static final int NOTIF_ID = 10;
    public static final String NOTIF_CHANNEL_ID = "Notification Channel";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence drawerTitle;
    private CharSequence title;
    private final List<NavigationItem> navigationItems = new ArrayList<>();

    private DatabaseHelper databaseHelper;

    private boolean searchShown = false;
    private boolean detailsShown = false;
    private boolean watchedListShown = false;
    private boolean settingsShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createNotificationChannel();

        setupDrawer();
        showSearchFragment();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL_ID, "Nas Notif Kanal", importance);
            channel.setDescription("Opis naseg kanala");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void showNotification(String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIF_CHANNEL_ID);
        builder.setContentTitle("WatchedFilms")
                .setContentText(text)
                .setSmallIcon(R.drawable.star_icon);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIF_ID, builder.build());
    }

    private void setupDrawer() {
        setupDrawerNavigationItems();
        title = drawerTitle = getTitle();
        setupDrawerItems();

    }

    private void setupDrawerNavigationItems() {
        //Moji filmovi, Pretraga, Podešavanja, Obriši sve
        navigationItems.add(new NavigationItem("Moji Filmovi", "Pogledajte listu odgledanih filmova", R.drawable.star_icon));
        navigationItems.add(new NavigationItem("Pretraga", "Potrazite film koji ste odgledali", R.drawable.search_icon));
        navigationItems.add(new NavigationItem("Podesavanja", "Izmenite podesavanja aplikacije", R.drawable.settings_icon));
        navigationItems.add(new NavigationItem("Obrisi sve", "Obrisite sve odgledane filmove", R.drawable.delete_icon));
    }

    private void setupDrawerItems() {
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.leftDrawer);

        DrawerListViewAdapter adapter = new DrawerListViewAdapter(navigationItems, this);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showWatchedListFragment();
                        break;
                    case 1:
                        showSearchFragment();
                        break;
                    case 2:
                        showSettingsFragment();
                        break;
                    case 3:
                        deleteAll();
                        break;
                }
                drawerLayout.closeDrawer(drawerList);
            }
        });
    }

    private void deleteAll() {
        AlertDialog dialog = new Dialog(this).prepareDialog();
        dialog.show();

        if (watchedListShown) {
            showWatchedListFragment();
        }
     }


    private void showWatchedListFragment() {
        try {
            List<Movie> watched = getDatabaseHelper().getMovieDao().queryForAll();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            WatchedListFragment fragment = new WatchedListFragment();
            fragment.setWatched(watched);
            transaction.replace(R.id.root, fragment);
            transaction.commit();

            searchShown = false;
            detailsShown = false;
            watchedListShown = true;
            settingsShown = false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void showSearchFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SearchFragment fragment = new SearchFragment();
        transaction.replace(R.id.root, fragment);
        transaction.commit();

        searchShown = true;
        detailsShown = false;
        watchedListShown = false;
        settingsShown = false;
    }

    private void showDetailsFragment(Movie movie) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetailsFragment fragment = new DetailsFragment();
        fragment.setMovie(movie);
        transaction.replace(R.id.root, fragment);
        transaction.commit();

        searchShown = false;
        detailsShown = true;
        watchedListShown = false;
        settingsShown = false;
    }

    private void showSettingsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SettingsFragment fragment = new SettingsFragment();
        transaction.replace(R.id.root, fragment);
        transaction.commit();

        searchShown = false;
        detailsShown = false;
        watchedListShown = false;
        settingsShown = true;
    }


    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onSearchItemClicked(Movie movie) {

    }

    @Override
    public void onWatchedItemClicked(Movie movie) {

    }
}



