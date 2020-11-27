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
    }

    @Override
    public void onSearchItemClicked(Movie movie) {

    }

    @Override
    public void onWatchedItemClicked(Movie movie) {

    }
}



