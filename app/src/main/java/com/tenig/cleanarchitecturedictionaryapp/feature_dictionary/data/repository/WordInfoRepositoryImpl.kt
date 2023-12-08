package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.repository

import com.tenig.cleanarchitecturedictionaryapp.core.util.Resource
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.RemoteApi
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.WordInfo
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val dao: WordInfoDao,
    private val api: RemoteApi
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {

        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })

        } catch (httpException: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, Something Went wrong!!",
                    data = wordInfos
                )
            )
        } catch (ioException: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, Please check your internet connection!",
                    data = wordInfos
                )
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(data = newWordInfos))
    }

}