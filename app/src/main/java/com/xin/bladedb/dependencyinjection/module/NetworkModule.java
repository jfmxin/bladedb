package com.xin.bladedb.dependencyinjection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/**
 * Created by Xin on 7/17/2016.
 */
@Module
public class NetworkModule {
//Doing some firebase research... this might not be necessary. May continue later...
    @Provides
    HttpUrl providesURL(){
        return new HttpUrl.Builder()
                .host("https://bladedb-44d63.firebaseio.com/")
                .build();
    }
    @Provides
    @Singleton
    Retrofit providesRetrofit( HttpUrl baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
    }
}
