package android.igorkostenko.applicasterapp.di

import android.igorkostenko.applicasterapp.BuildConfig
import android.igorkostenko.applicasterapp.network.ApplicasterClient
import android.igorkostenko.applicasterapp.network.ApplicasterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("http://assets-production.applicaster.com/applicaster-employees/israel_team/Elad/assignment/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApplicasterService::class.java) }

    single { ApplicasterClient(get()) }
}
