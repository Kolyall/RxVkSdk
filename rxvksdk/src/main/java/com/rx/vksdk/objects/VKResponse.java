package com.rx.vksdk.objects;

import lombok.Getter;

/**
 * Created by Nick Unuchek (skype: kolyall) on 29.07.2017.
 */

@Getter
public class VKResponse<T> {
    T response;
}
