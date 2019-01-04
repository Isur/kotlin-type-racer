package com.example.isur.typeracer.Model.Utils

import com.example.isur.typeracer.Model.DataModels.ScoreList
import com.example.isur.typeracer.Model.Repository.MockAPI

object JSONParser {
    fun jsonToScoreList(jsonString: String): ScoreList {
        // TODO("Not implemented")
        return MockAPI.getScoresMock()
    }

    fun scoreToJson(nickname: String, score: Int): String {
//        TODO("Not implemented")
        return "{nickname: $nickname, score: $score}"
    }

    fun jsonToArray(jsonString: String): Array<String> {
        // TODO("Not implemented")
        return arrayOf("word1", "word2", "word3", "word4")
    }
}