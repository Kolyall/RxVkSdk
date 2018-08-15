package com.rx.vksdk.service;

import com.rx.vksdk.VkApiService;
import com.rx.vksdk.objects.VKResponse;
import com.rx.vksdk.objects.VKUser;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.rx.vksdk.objects.VKUser.PHOTO_400_ORIG;


/**
 * Created by Nick Unuchek (skype: kolyall) on 30.09.2016.
 */

public class RxApiVkRepository implements RxVkRepository {
    private static final String TAG = RxApiVkRepository.class.getSimpleName();
    private VkApiService mVkApiService;

    public RxApiVkRepository(VkApiService vkApiService) {
        this.mVkApiService = vkApiService;
    }

    public Observable<VKUser> getUsersObservable(final VKAccessToken vkAccessToken) {
        HashMap<String, String> params = new HashMap<>();
        params.put(VKApiConst.VERSION, VkApiService.VERSION_NUMBER);
        params.put(VKApiConst.ACCESS_TOKEN, vkAccessToken.accessToken);
        params.put(VKApiConst.FIELDS, PHOTO_400_ORIG+",first_name,last_name");
        return mVkApiService.getUsers(params).map(new Func1<VKResponse<List<VKUser>>, VKUser>() {
                    @Override
                    public VKUser call(VKResponse<List<VKUser>> apiResponse) {
                        List<VKUser> VKUsers = apiResponse.getResponse();
                        if (VKUsers == null || VKUsers.size() == 0) {
                            return null;
                        }
                        VKUser vkUser = VKUsers.get(0);
                        vkUser.setEmail(vkAccessToken.email);
                        return vkUser;
                    }
                })
                .subscribeOn(Schedulers.io());
    }

}
