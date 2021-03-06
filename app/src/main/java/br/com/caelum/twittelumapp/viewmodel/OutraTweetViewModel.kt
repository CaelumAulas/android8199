package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.repository.TweetRepository

class OutraTweetViewModel(val tweetRepository: TweetRepository) : ViewModel() {
    fun adiciona(tweet: Tweet) = tweetRepository.insere(tweet)

    fun busca() = tweetRepository.busca()
}