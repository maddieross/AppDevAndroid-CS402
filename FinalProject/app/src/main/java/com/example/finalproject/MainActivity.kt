package com.example.finalproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.TextView
import com.example.finalproject.Fragments.AddChoreFragment
import com.example.finalproject.Fragments.HomeFragment
import com.example.finalproject.Fragments.SendAlertFragment
import com.example.finalproject.Fragments.SettingsFragment
import io.objectbox.kotlin.query

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    AddChoreFragment.OnFragmentInteractionListener, SendAlertFragment.OnFragmentInteractionListener,
    SettingsFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener
{
    lateinit var homeFragment: HomeFragment
    lateinit var addChoreFragment: AddChoreFragment
    lateinit var sendFragment: SendAlertFragment
    lateinit var settingsFragment: SettingsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        } */
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment.newInstance()
        addChoreFragment = AddChoreFragment.newInstance()
        sendFragment =  SendAlertFragment.newInstance()
        settingsFragment = SettingsFragment.newInstance()

        val displayNameTextView: TextView = findViewById(R.id.display_name_textview)
        val signIn:SignIn = intent.getSerializableExtra("email") as SignIn
        val userBox = ObjectBox.boxStore.boxFor(User::class.java)

        val query= userBox.query {
            equal(User_.email, signIn.email.toString())
        }.property(User_.displayName).nullValue("unknown")
        val results = query.findString()

        //TODO: How to get only the display name from query?
        displayNameTextView.text = results.toString()

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, homeFragment)
                    .addToBackStack(homeFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_add_chore -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, addChoreFragment)
                    .addToBackStack(addChoreFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_settings -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, settingsFragment)
                    .addToBackStack(settingsFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_send -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, sendFragment)
                    .addToBackStack(sendFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
