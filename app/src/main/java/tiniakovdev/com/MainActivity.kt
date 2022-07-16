package tiniakovdev.com

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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


//
//    fun action(view: View) {
//        view.findViewById<Notification.Action>()
}
//    fun menuToast(view: View) {
//        val toastMenu = Toast.makeText(this,"menu",Toast.LENGTH_SHORT)
//        toastMenu.show()
//    }
//    fun favoritesToast(view: View) {
//        val toastFavorites = Toast.makeText(this,"favorites", Toast.LENGTH_SHORT)
//        toastFavorites.show()
//    }
//    fun watchLaterToast(view: View) {
//        val toastWatchLater = Toast.makeText(this,"watch later", Toast.LENGTH_SHORT)
//        toastWatchLater.show()
//    }
//    fun selectionsToast(view: View) {
//        val toastSelection = Toast.makeText(this,"selections", Toast.LENGTH_SHORT)
//        toastSelection.show()
//    }
//    fun settingsToast(view: View) {
//        val toastSettings = Toast.makeText(this,"settings", Toast.LENGTH_SHORT)
//        toastSettings.show()
//    }





