package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.Converter
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.util.GsonConverter
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.RemoteApi
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.usecase.GetWordInfoUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWorkInfoUsecase(
        repository: WordInfoRepository
    ): GetWordInfoUsecase {
        return GetWordInfoUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: RemoteApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(db.wordInfoDao, api)
    }

    @Provides
    @Singleton
    fun provideRemoteApi(httpClient: OkHttpClient): RemoteApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(RemoteApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        return  retrofit.create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application, converter: Converter): WordInfoDatabase {
        return Room.databaseBuilder(app, WordInfoDatabase::class.java, "Dictionary_db")
            .addTypeConverter(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverter() : Converter {
        return Converter(GsonConverter(Gson()))
    }

}