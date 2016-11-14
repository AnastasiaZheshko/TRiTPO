package com.example.anastasia.gid;

import anastasiya.gid.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int[] tabIcons = {android.support.design.R.drawable.abc_btn_radio_material,android.support.design.R.drawable.abc_btn_radio_material,
            android.support.design.R.drawable.abc_btn_radio_material,android.support.design.R.drawable.abc_btn_radio_material
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(AccessToken.getCurrentAccessToken()==null) {
            goLoginScreen();
        }
        setContentView(R.layout.activity_main);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if(viewPager==null) System.out.println("Null viewpager");
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        if(tabLayout==null) System.out.println("Null tablayout");


        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager() {
        adapter.addFragment(new NewsFragment());
        adapter.addFragment(new FriendsFragment());
        adapter.addFragment(new WishesFragment());
        adapter.addFragment(new QuitFragment());
        viewPager.setAdapter(adapter);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, AuthorizationActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}

