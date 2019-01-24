package com.example.isur.typeracer.Model

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Interface.IGameInteractor
import com.example.isur.typeracer.Model.Repository.TypeRacerApi
import com.example.isur.typeracer.Model.Utils.Network.ConnectionInfo
import com.example.isur.typeracer.Model.Utils.Network.NoConnectivityException
import com.example.isur.typeracer.TypeRacerApplication
import com.example.isur.typeracer.Views.Interface.IGameBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class GameInteractor : IGameInteractor {
    private val apiService: TypeRacerApi = TypeRacerApi(TypeRacerApplication.provideConnectivityInterceptor())
    override fun getWord(): String {
        return try {
            runBlocking(Dispatchers.IO) {
                apiService.getWord().await()
            }
        } catch (ex: NoConnectivityException) {
            ConnectionInfo.sendNoConnection(TypeRacerApplication.applicationContext())
            return ""
        }
    }

    override fun getWords(): Array<String> {
        val words = try {
            runBlocking(Dispatchers.IO) {
                apiService.getWords().await()
            }
        } catch (ex: NoConnectivityException) {
            ConnectionInfo.sendNoConnection(TypeRacerApplication.applicationContext())
            return arrayOf("")
        }
        val modifiedWords = mutableListOf<String>()
        words.forEach { modifiedWords.add(modifyWordsRandom(it)) }
        return modifiedWords.toTypedArray()
    }

    override fun postScore(nickname: String, score: Int) {
        try {
            val response = runBlocking(Dispatchers.IO) {
                apiService.postScore(ScoreList.Score(nickname, score))
            }
        } catch (ex: NoConnectivityException) {
            ConnectionInfo.sendNoConnection(TypeRacerApplication.applicationContext())
        }

    }

    override fun getGame(time: Int, context: IGameBoard): Game {
        return Game(time, context)
    }

    private fun modifyWords(word: String): String {
        val newWord = mutableListOf<Char>()
        word.forEachIndexed { index, char ->
            if (index % 2 == 0) {
                newWord.add(char.toUpperCase())
            } else {
                newWord.add(char.toLowerCase())
            }
        }
        return String(newWord.toCharArray())

    }

    private fun modifyWordsRandom(word: String): String {
        val newWord = mutableListOf<Char>()
        val rand = Random.nextInt(1, until = word.length)
        word.forEachIndexed { index, char ->
            if (index % rand == 0) {
                newWord.add(char.toUpperCase())
            } else {
                newWord.add(char.toLowerCase())
            }
        }
        return String(newWord.toCharArray())
    }
}