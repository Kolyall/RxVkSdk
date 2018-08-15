package com.rx.vksdk.service;

import com.rx.vksdk.objects.VKUser;
import com.vk.sdk.VKAccessToken;

import rx.Observable;


/**
 * Created by Nick Unuchek (skype: kolyall) on 30.09.2016.
 */

public interface RxVkRepository {

    Observable<VKUser> getUsersObservable(VKAccessToken vkAccessToken);

}
