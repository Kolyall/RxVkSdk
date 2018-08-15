package com.rx.vksdk.objects;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Nick Unuchek (skype: kolyall) on 29.07.2017.
 */
@Getter
@Setter
public class VKUser {
    public static final String PHOTO_400_ORIG = "photo_400_orig";
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    @SerializedName(PHOTO_400_ORIG)
    private String photo;
}
