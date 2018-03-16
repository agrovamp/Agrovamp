package com.agrovamp.agrovamp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ResourceBundle;

public class UserMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FarmFragment.OnFragmentInteractionListener,
        FAQFragment.OnFragmentInteractionListener,
        HelpFragment.OnFragmentInteractionListener,
        MarketplaceFragment.OnFragmentInteractionListener,
        AgrobookFragment.OnFragmentInteractionListener{

    public static final String TAG = UserMainActivity.class.getSimpleName();

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private Fragment fragment;
    private Bundle bundle;

    private PreferenceManager preferenceManager;

    private String qrId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        preferenceManager = new PreferenceManager(getApplicationContext());

        if (preferenceManager.isStored()) {
            Log.d(TAG, "Lang code: " + preferenceManager.getLanguageCode());
        }

        if (preferenceManager.isQRStored()) {
            qrId = preferenceManager.getQRCode();
        } else {
            qrId = intent.getStringExtra(QRCodeActivity.KEY_QR);
        }

        Log.d(TAG, "QR: " + qrId);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        Log.d(TAG, "QR: " + qrId);

        bundle = new Bundle();
        bundle.putString(QRCodeActivity.KEY_QR, qrId);

        FarmFragment farmFragment = new FarmFragment();
        farmFragment.setArguments(bundle);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        final TextView userNameTextView = (TextView) headerView.findViewById(R.id.name_text_view);
        final TextView mobileNumberTextView = (TextView) headerView.findViewById(R.id.phone_text_view);

        reference.child(qrId).child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    userNameTextView.setText(name);
                    mobileNumberTextView.setText(phone);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            }
        });

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, new FarmFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            startActivity(new Intent(UserMainActivity.this, QRCodeActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
            ));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getQrId() {
        return qrId;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        fragment = new FarmFragment();
        fragment.setArguments(bundle);

        if (id == R.id.nav_your_farm) {
            // Handle the camera action
            fragment = new FarmFragment();
            fragment.setArguments(bundle);
        } else if (id == R.id.nav_faq) {
            fragment = new FAQFragment();
        } else if (id == R.id.nav_marketplace) {
            fragment = new MarketplaceFragment();
        } else if (id == R.id.nav_help) {
            fragment = new HelpFragment();
        } else if (id == R.id.nav_agrobook) {
            fragment = new AgrobookFragment();
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame_layout, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }
}
