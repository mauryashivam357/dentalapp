package com.example.navigation_drawer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.navigation_drawer.utils.SessionManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
         private DrawerLayout drawerLayout;
         SessionManager session;
         @Override
         protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         session = new SessionManager(this);
         String user_token = session.getAuthToken();
//         Log.d("TAG", "onCreate: "+user_token);
//         Toast.makeText(this,user_token, Toast.LENGTH_SHORT).show();
         if(user_token!=""){
         }
         else {
//             Intent intent = new Intent(this, LoginAcount.class);
//             startActivity(intent);
//             finish();
         }
        if (Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.green));

        }

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
             toolbar.setOutlineProvider(null);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Display DesboardFragment by default


             displaySelectedFragment(new DesboardFragment(),"Dental Care"); //getString(R.string.title_home));
             navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                 @Override
                 public boolean onNavigationItemSelected(MenuItem item) {
                     int id = item.getItemId();
                     switch (id) {
                         case R.id.nav_home:
                             displaySelectedFragment(new DesboardFragment(), "Dental Care ");//getString(R.string.title_home)
                             break;

                         case R.id.appointments:
                             displaySelectedFragment(new Appointments(), getString(R.string.title_appointments));

                             break;
                         case R.id.services:
                             displaySelectedFragment(new ServicesFragment(), getString(R.string.services));
                             break;

                         case R.id.team:
                             displaySelectedFragment(new Team(), getString(R.string.title_team));
                             break;

                         case R.id.feedback:
                             displaySelectedFragment(new  FeedbackFragment(), getString(R.string.title_feedback));
                             break;

                         case R.id.about:
                             displaySelectedFragment(new About(), getString(R.string.title_about));
                             break;

                         case R.id.faq:
                             displaySelectedFragment(new FAQ(), getString(R.string.title_faq));
                             break;

                         case R.id.help_center:
                             displaySelectedFragment(new HelpCenter(), getString(R.string.help_center));
                             break;

                         case R.id.setting:
                             displaySelectedFragment(new Setting(), getString(R.string.title_setting));
                             break;
                         default:
                             return false;
                     }
                     drawerLayout.closeDrawer(GravityCompat.START);
                     return true;
                 }
             });
         }
    private void displaySelectedFragment(Fragment fragment, String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
