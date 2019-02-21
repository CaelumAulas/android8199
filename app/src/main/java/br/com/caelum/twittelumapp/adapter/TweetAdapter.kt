package br.com.caelum.twittelumapp.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.application.TwittelumApplication
import br.com.caelum.twittelumapp.extensions.CarregadorDeImagem
import br.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(val tweets: List<Tweet>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.item_tweet, parent, false)

        val tweet = tweets[position]

        view.item_conteudo.text = tweet.mensagem

        tweet.foto?.let {fotoNaBase64 ->
            view.item_foto.setImageBitmap(CarregadorDeImagem.decodifica(fotoNaBase64))
        }
        return view
    }

    override fun getItem(position: Int) = tweets.get(position)

    override fun getItemId(position: Int) = tweets[position].id.toLong()

    override fun getCount() = tweets.size

}
