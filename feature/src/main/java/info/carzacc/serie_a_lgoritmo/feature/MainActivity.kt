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
import org.json.JSONObject
import java.lang.Package.getPackages
import android.widget.ArrayAdapter


/*
        Cosa vogliamo:
        ListView con
        nomesquadra: punti
        nomesquadra: punti
        ecc.
 */


/*Tipi di dati nell' array
        nomesquadra: String,
        punti: Short,
        puntiTrad: Short,
        golfatti: Short,
        golsubiti: Short,
        vittorie: Short,
        sconfitte: Short,
        pareggi: Short
*/

class MainActivity : AppCompatActivity() {
    private var risultato = "WAIT";
    private var classifica = "Alternativa"
    private var listanormale: ArrayList<String> = ArrayList<String>()
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
              //  message.setText(R.string.esempio_testo_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classifica -> {
                var squadre = JSONArray(risultato)
                creaArray(squadre)

                val arrayAdapter = ArrayAdapter<String>(this@MainActivity, android.R.id.message, listanormale)

              //  message.setText(R.string.esempio_testo_classifica)
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
    fun creaArray(phpoutput: JSONArray) {
        var i=0;
        while (i<20)    {
            var oggetto = phpoutput.getJSONObject(i)
            var nomesquadra = oggetto.getString("nomesquadra")
            var punti = "a"
            if(classifica == "Alternativa") {
                punti = oggetto.getInt("punti").toString()
            }
            listanormale[i] = nomesquadra + ": " + punti
            i++
        }
    }
}
