package com.johan_apps.exelsa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.johan_apps.exelsa.fragments.CourseFragment;
import com.johan_apps.exelsa.fragments.NotificationFragment;
import com.johan_apps.exelsa.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showCourseFragment();
                    return true;
                case R.id.navigation_dashboard:
                    showNotificationFragent();
                    return true;
                case R.id.navigation_notifications:
                    showProfileFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CourseFragment()).commit();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void showCourseFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CourseFragment()).commit();
    }

    private void showNotificationFragent() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new NotificationFragment()).commit();
    }

    private void showProfileFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new ProfileFragment()).commit();
    }

}
