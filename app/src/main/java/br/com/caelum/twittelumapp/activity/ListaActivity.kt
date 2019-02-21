package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.adapter.TweetAdapter
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


        viewModel.busca().observe(this, Observer {tweets ->
            tweets?.let {
                if (tweets.isNotEmpty()) {
                    val adapter = TweetAdapter(tweets)
                    lista_tweets.adapter = adapter
                }
            }
        })


        val listenerDeCliqueNoItem = AdapterView.OnItemClickListener { parent, view, position, idView ->
            val tweet = lista_tweets.adapter.getItem(position) as Tweet
            perguntaEDeletaTweet(tweet)
        }

        lista_tweets.onItemClickListener = listenerDeCliqueNoItem
    }

    private fun perguntaEDeletaTweet(tweet: Tweet) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Quer mesmo deletar o tweet?")
        dialogBuilder.setPositiveButton("Sim") { dialog, which ->
            viewModel.deleta(tweet)
        }
        dialogBuilder.setNegativeButton("Não", null)
        dialogBuilder.show()
    }

}