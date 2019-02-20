package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.OutraTweetViewModel
import br.com.caelum.twittelumapp.viewmodel.OutraTweetViewModelFactory
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.TweetViewModelFactory

class TweetActivity : AppCompatActivity() {

    val viewModel: OutraTweetViewModel by lazy {
        ViewModelProviders.of(this, OutraTweetViewModelFactory).get(OutraTweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun publicaTweet() {
        val campoTexto = findViewById<EditText>(R.id.campo_tweet)
        val texto = campoTexto.text.toString()
        val tweet = Tweet(texto)

//        val database = TwittelumDatabase.pegaInstancia(this)
//        val tweetDao = database.getTweetDao()
//        tweetDao.salva(tweet)

        viewModel.adiciona(tweet)

        Log.i("CLIQUE", "clicou!")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tweet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId)  {
            R.id.item_salvar -> {
                publicaTweet()
                finish()
            }
            android.R.id.home -> finish()
        }
        return false
    }


}
