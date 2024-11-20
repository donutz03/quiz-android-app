package eu.ase.ro.demo2app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import eu.ase.ro.demo2app.fragments.HistoryOfTestScoresFragment;
import eu.ase.ro.demo2app.fragments.LearnEthicsAndMoralityFragment;

public class MainActivity extends AppCompatActivity {


    private ActivityResultLauncher<Intent> launcher;
    private Fragment currentFragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private FloatingActionButton takeTestFAB;

    private ArrayList<Integer> scoresList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        navigationView = findViewById(R.id.hodoroaga_ionut_main_nav_view);
        navigationView.setNavigationItemSelectedListener(getItemSelectedEvent());
        takeTestFAB = findViewById(R.id.hodoroaga_ionut_main_fab_add);
        takeTestFAB.setOnClickListener(getAddEvent());
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                TakeTestCallback()
        );

        if (savedInstanceState == null) {
            currentFragment = HistoryOfTestScoresFragment.getInstance(scoresList);
            openFragment();
            navigationView.setCheckedItem(R.id.hodoroaga_ionut_nav_history_of_tests);
        }

    }

    private ActivityResultCallback<ActivityResult> TakeTestCallback() {
        return result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                int latestScore = result.getData().getIntExtra(QuizEthicsMoralityActivity.LATEST_SCORE, 0);

                if (currentFragment instanceof HistoryOfTestScoresFragment) {
                    ((HistoryOfTestScoresFragment) currentFragment).addScore(latestScore);
                }
            }
        };
    }

    private View.OnClickListener getAddEvent() {
        return v -> {
            Intent intent = new Intent(getApplicationContext(),
                    QuizEthicsMoralityActivity.class);
            launcher.launch(intent);
        };
    }

    private NavigationView.OnNavigationItemSelectedListener getItemSelectedEvent() {
        return item -> {
            if (item.getItemId() == R.id.hodoroaga_ionut_nav_learn) {
                Toast.makeText(getApplicationContext(), R.string.learn_clicked,
                        Toast.LENGTH_LONG).show();
                currentFragment = new LearnEthicsAndMoralityFragment();
                takeTestFAB.hide();
            } else {
                takeTestFAB.show();
                Toast.makeText(getApplicationContext(), R.string.test_history_clicked,
                        Toast.LENGTH_LONG).show();
                currentFragment = HistoryOfTestScoresFragment.getInstance(scoresList);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            openFragment();
            return true;
        };
    }

    private void openFragment() {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.hodoroaga_ionut_main_fl_container, currentFragment).
                commit();
    }

    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.hodoroaga_ionut_main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.hodoroaga_ionut_main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}