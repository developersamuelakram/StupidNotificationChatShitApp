package com.example.mychatapp.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APISERVICESHIT {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAArDBnszc:APA91bFyuROwfb_RuVkHKxLqdtAEprBtt52Rx1-wWy4Aa2ZK4O-97hioMpGEmb2JNbQ_qEfwtXp_MPzBhNA5SBB5afGtNhElgvMYGcqQb7VAhmj9CNznbeWops5MCweW8EDSDH4hhL8Z"
    })

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);


}
