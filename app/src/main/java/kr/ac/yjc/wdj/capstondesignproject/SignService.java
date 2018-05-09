package kr.ac.yjc.wdj.capstondesignproject;

/**
 * Created by 박주용 on 2018-04-30.
 */

import android.content.Context;

import kr.ac.yjc.wdj.capstondesignproject.APIAdapter;
import kr.ac.yjc.wdj.capstondesignproject.APIUrl;
import kr.ac.yjc.wdj.capstondesignproject.ResData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class SignService extends APIAdapter {
    /**
     * Retrofit 객체를 가져오는 메소드
     *
     * @param context
     * @return
     */
    public static SignAPI getRetrofit(Context context) {
        // 현재 서비스객체의 이름으로 Retrofit 객체를 초기화 하고 반환
        return (SignAPI) retrofit(context, SignAPI.class);
    }

    // SignAPI 인터페이스
    public interface SignAPI {
        /**
         * 회원가입 메소드
         *
         * @param email
         * @param pw
         * @param name
         * @return
         */
        @FormUrlEncoded
        @POST(APIUrl.SIGN_UP_URL)
        Call<ResData> up(
                @Field("email") String email,
                @Field("pw") String pw,
                @Field("name") String name
        );

        /**
         * 로그인 메소드
         *
         * @param id
         * @param password
         * @return
         */
        @FormUrlEncoded
        @POST(APIUrl.LOGIN_URL)
        Call<ResData> in(
                @Field("id") String id,
                @Field("password") String password
        );
    }
}