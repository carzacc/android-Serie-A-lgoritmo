package info.carzacc.serie_a_lgoritmo.feature

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import kotlin.io.readText
import org.json.*
import java.net.URL

data class Squadra(
        val nomesquadra: String,
        val punti: Short,
        val puntiTrad: Short,
        val golfatti: Short,
        val golsubiti: Short,
        val vittorie: Short,
        val sconfitte: Short,
        val pareggi: Short)

class MainActivity : AppCompatActivity() {
    private var risultato = "WAIT";

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.esempio_testo_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classifica -> {
                val squadre = JSONArray(risultato)  
                message.setText(R.string.esempio_testo_classifica)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doAsync {
            risultato = URL("https://algoritmo-php.herokuapp.com/?g=15").readText()
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
