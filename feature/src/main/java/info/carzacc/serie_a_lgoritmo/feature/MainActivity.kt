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
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.Arrays.asList




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
    private var risultato = "RITENTA SARAI PIU' FORTUNATO"
    private var listanormale: Array<String> = Array<String>(20,{ " " })
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_classifica_alternativa -> {
                if(risultato == "RITENTA SARAI PIU' FORTUNATO") {
                    toast("Riprova FRA UN ATTIMO a premere Classifica")
                } else {
                    var squadre = JSONArray(risultato)
                    creaArray(squadre, "Alternativa")
                    val lista: ListView = findViewById(R.id.message)
                    val arraylistsquadre = listanormale.asList()

                    val arrayAdapter = ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            arraylistsquadre
                    )
                    lista.setAdapter(arrayAdapter)
                }
              //  message.setText(R.string.esempio_testo_classifica)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classifica_tradizionale -> {
                if(risultato == "RITENTA SARAI PIU' FORTUNATO") {
                    toast("Riprova FRA UN ATTIMO a premere Classifica")
                } else {
                    var squadre = JSONArray(risultato)
                    creaArray(squadre, "Tradizionale")
                    val lista: ListView = findViewById(R.id.message)
                    val arraylistsquadre = listanormale.asList()

                    val arrayAdapter = ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            arraylistsquadre
                    )
                    lista.setAdapter(arrayAdapter)
                }
                //  message.setText(R.string.esempio_testo_classifica)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classifica_somma -> {
                if(risultato == "RITENTA SARAI PIU' FORTUNATO") {
                    toast("Riprova FRA UN ATTIMO a premere Classifica")
                } else {
                    var squadre = JSONArray(risultato)
                    creaArray(squadre, "Somma")
                    val lista: ListView = findViewById(R.id.message)
                    val arraylistsquadre = listanormale.asList()

                    val arrayAdapter = ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            arraylistsquadre
                    )
                    lista.setAdapter(arrayAdapter)
                }
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
            risultato = URL("https://algoritmo-php.herokuapp.com/").readText()
            uiThread    {
                toast("Tutto caricato, scegli che classifica vuoi vedere")
            }
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    fun creaArray(phpoutput: JSONArray, tipoclassifica: String) {
        for (i in listanormale.indices)    {
            var oggetto = phpoutput.getJSONObject(i)
            var nomesquadra = oggetto.getString("nomesquadra")
            var punti = "a"
            when (tipoclassifica) {
                "Alternativa" -> {
                    punti = oggetto.getDouble("punti").toString()
                }
                "Tradizionale" ->   {
                    punti = oggetto.getInt("puntiTrad").toString()
                }
                "Somma" ->   {
                    val puntint = oggetto.getDouble("punti") + + oggetto.getInt("puntiTrad")
                    punti = puntint.toString()
                }
            }
            listanormale[i] = nomesquadra + ": " + punti
        }
    }
}
