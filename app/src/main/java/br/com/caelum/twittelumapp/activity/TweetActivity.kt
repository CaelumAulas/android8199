package br.com.caelum.twittelumapp.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.OutraTweetViewModel
import br.com.caelum.twittelumapp.viewmodel.OutraTweetViewModelFactory
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.TweetViewModelFactory
import kotlinx.android.synthetic.main.activity_tweet.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    var caminho: String? = null

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

            R.id.item_foto -> {
                tiraFoto()
            }
        }
        return false
    }

    private fun tiraFoto() {
        val intencao = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = escolheCaminhoDaFoto()
        intencao.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivityForResult(intencao,123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 123) {
            if(resultCode == Activity.RESULT_OK) {
//                if (caminho != null)
//                    carregaFoto()
                caminho?.let {
                    carregaFoto()
                }
            }
        }
    }

    private fun escolheCaminhoDaFoto(): Uri {
        caminho = "${getExternalFilesDir(null)}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(caminho)
        //val uriFoto = Uri.fromFile(arquivo)
        val uriFoto = FileProvider.getUriForFile(this, "MeuProvider", arquivo)
        return uriFoto
    }
//
//    override fun onResume() {
//        super.onResume()
//        if (caminho != null)
//            carregaFoto()
//    }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(caminho)

        imagem_tweet.setImageBitmap(bitmap)
        imagem_tweet.scaleType = ImageView.ScaleType.FIT_XY
    }


}
