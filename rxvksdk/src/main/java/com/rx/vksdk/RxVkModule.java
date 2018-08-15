package com.rx.vksdk;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rx.vksdk.service.RxApiVkRepository;
import com.rx.vksdk.service.RxVkRepository;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nick Unuchek on 01.11.2017.
 */
@Module
public class RxVkModule {

    public static final String KEY_VKAPISERVICE_OKHTTPCLIENT = "key_vkapiservice_okhttpclient";
    public static final String KEY_VKAPISERVICE_GSON = "key_vkapiservice_gson";

    private HashMap<String, VkApiService> mServices = new HashMap<>();

    @Provides
    @Named(KEY_VKAPISERVICE_GSON)
    @Singleton
    public Gson provideDefaultLowerCaseGson() {
        return new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Named(KEY_VKAPISERVICE_OKHTTPCLIENT)
    @NonNull
    @Singleton
    OkHttpClient providesOkHttpClient(Interceptor loggingInterceptor) {

        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @NonNull
    VkApiService providesVkApiService(@Named(KEY_VKAPISERVICE_GSON) Gson gson,
                                      @Named(KEY_VKAPISERVICE_OKHTTPCLIENT) OkHttpClient okHttpClient) {

        VkApiService service = mServices.get(VkApiService.BASE_API_URL);
        if (service == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(VkApiService.BASE_API_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            service = retrofit.create(VkApiService.class);
            mServices.put(VkApiService.BASE_API_URL, service);
        }

        return service;
    }

    @Provides
    @NonNull
    @Singleton
    RxVkRepository providesRxVkRepository(VkApiService vkApiService) {
        return new RxApiVkRepository(vkApiService);
    }

}
