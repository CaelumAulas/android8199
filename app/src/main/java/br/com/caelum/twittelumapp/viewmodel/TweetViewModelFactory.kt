package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumapp.application.TwittelumApplication
import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.repository.TweetRepository

object TweetViewModelFactory : ViewModelProvider.Factory {

    val database = TwittelumDatabase.pegaInstancia(TwittelumApplication.getInstancia())
    private val tweetDao: TweetDao = database.getTweetDao()
    private val repository: TweetRepository = TweetRepository(tweetDao)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TweetViewModel(repository) as T
    }
}