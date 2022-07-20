package com.example.fragmentexample1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

const val STATE_FRAGMENT = "state_of_fragment"

class MainActivity : AppCompatActivity() {

    lateinit var mButton: Button
    var isFragmentDisplayed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButton = findViewById(R.id.open_button)
        if (savedInstanceState != null) {
            isFragmentDisplayed =
                savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }
        mButton.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment();
            } else {
                closeFragment();
            }
        }
    }

    fun displayFragment() {
        val simpleFragment: SimpleFragment = SimpleFragment.newInstance
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container,
            simpleFragment
        ).addToBackStack(null).commit();
// Update the Button text.
        mButton.setText(R.string.close);
// Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }

    fun closeFragment() {
        // Get the FragmentManager.
        val fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        val simpleFragment = fragmentManager
            .findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            // Create and commit the transaction to remove the fragment.
            val fragmentTransaction =
                fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        // Update the Button text.
        mButton.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_FRAGMENT,isFragmentDisplayed)
        super.onSaveInstanceState(outState)

    }
}

