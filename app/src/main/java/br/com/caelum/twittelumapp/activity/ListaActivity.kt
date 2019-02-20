package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.TweetViewModelFactory
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, TweetViewModelFactory).get(TweetViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        botao_novo.setOnClickListener {
            val intent = Intent(this,TweetActivity::class.java)
            startActivity(intent)
        }
        val adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1)
        lista_tweets.adapter = adapter


        viewModel.busca().observe(this, Observer {
            adapter.clear()
            adapter.addAll(it)
        })
    }

    //    override fun onResume() {
//        super.onResume()
//        populaLista()
//    }

//    private fun populaLista() {
//       val tweets = viewModel.busca()

//        lista_tweets.adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)
//    }
}