package tiniakovdev.com

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import tiniakovdev.com.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


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

    fun selections(item: MenuItem) {
        val intent = Intent(this@MainActivity, Selections::class.java)
        startActivity(intent)
    }

    fun favorites(item: MenuItem) {
        val intent = Intent(this@MainActivity, Favorites::class.java)
        startActivity(intent)
    }

    fun watchLater(item: MenuItem) {
        val intent = Intent(this@MainActivity, WatchLater::class.java)
        startActivity(intent)
    }

    fun settings(item: MenuItem) {
        val toastSettings = Toast.makeText(this, "settings", Toast.LENGTH_SHORT)
        toastSettings.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.settings -> {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            AlertDialog.Builder(this, R.style.AlertDialog)
                .setTitle(R.string.dialog_title)
                .setIcon(R.drawable.ic_exit_to_app)
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