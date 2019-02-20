package br.com.caelum.twittelumapp.repository

import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.modelo.Tweet

class TweetRepository(val tweetDao: TweetDao) {
    fun insere(tweet: Tweet) = tweetDao.salva(tweet)

    fun busca() = tweetDao.lista()
}