package tiniakovdev.com.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import tiniakovdev.com.R
import tiniakovdev.com.data.entity.Film
import tiniakovdev.com.databinding.ActivityMainBinding
import tiniakovdev.com.view.fragments.*
import tiniakovdev.com.viewmodul.HomeFragmentViewModel


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

        homeFragmentViewModel.getLoadData().observe(this) {
            Snackbar.make(binding.fragmentPlaceholder, "Loading error", Snackbar.LENGTH_SHORT).show()
        }

        appNavigation()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    fun launchDetailsFragment(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable("film", film)

        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun appNavigation() {

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: FavoritesFragment(), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: WatchLaterFragment(), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: SelectionsFragment(), tag)
                    true
                }
                R.id.settings -> {
                    val tag = "settings"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: SettingFragment(), tag)
                    true
                }
                else -> false
            }
        }
    }

    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            AlertDialog.Builder(this, R.style.AlertDialog)
                .setTitle(R.string.dialog_title)
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setPositiveButton(R.string.dialog_PositiveButton) { _, _ ->
                    finish()
                }
                .setNegativeButton(R.string.dialog_NegativeButton) { _, _ ->

                }
                .setNeutralButton(R.string.dialog_NeutralButton) { _, _ ->

                }
                .show()
        }
    }
}

