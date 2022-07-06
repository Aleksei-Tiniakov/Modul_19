package tiniakovdev.com

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun menuToast(view: View) {
        val toastMenu = Toast.makeText(this,"menu",Toast.LENGTH_SHORT)
        toastMenu.show()
    }
    fun favoritesToast(view: View) {
        val toastFavorites = Toast.makeText(this,"favorites", Toast.LENGTH_SHORT)
        toastFavorites.show()
    }
    fun watchLaterToast(view: View) {
        val toastWatchLater = Toast.makeText(this,"watch later", Toast.LENGTH_SHORT)
        toastWatchLater.show()
    }
    fun selectionsToast(view: View) {
        val toastSelection = Toast.makeText(this,"selections", Toast.LENGTH_SHORT)
        toastSelection.show()
    }
    fun settingsToast(view: View) {
        val toastSettings = Toast.makeText(this,"settings", Toast.LENGTH_SHORT)
        toastSettings.show()
    }
}