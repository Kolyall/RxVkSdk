package com.rx.vksdk;


import com.rx.vksdk.objects.VKResponse;
import com.rx.vksdk.objects.VKUser;
import com.vk.sdk.VKScope;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Nick Unuchek (skype: kolyall) on 04.10.2016.
 */

public interface VkApiService {
    String BASE_API_URL = "https://api.vk.com/";
    String VERSION_NUMBER = "5.63";
    String[] VK_SCOPE = new String[]{
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.PHOTOS,
//            VKScope.NOHTTPS,
            VKScope.EMAIL,
            VKScope.OFFLINE,
            VKScope.MESSAGES
    };
    String FILTER = "filter";
    String NEED_SYSTEM = "need_system";
    String SERVER = "server";
    String PHOTOS_LIST = "photos_list";
    String PHOTO = "photo";
    String HASH = "hash";
    String TITLE = "title";
    String DESCRIPTION = "description";
    String UPLOAD_BY_ADMINS_ONLY = "upload_by_admins_only";
    String COMMENTS_DISABLED = "comments_disabled";

    @FormUrlEncoded
    @POST("/method/users.get")
    Observable<VKResponse<List<VKUser>>> getUsers(@FieldMap Map<String, String> params);

}
