package com.tjeit.a20191228_01_apipractice.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonResponseHandler {
        fun onResponse(json:JSONObject)
    }

    companion object {

//        어느 서버에 연결할지 서버의 주소 (도메인-www.naver.com / IP-192.168.0.10) 를 변수로 저장.
        val BASE_URL = "http://192.168.0.10:5000"

//        [로그인 요청 기능] 함수로 구현

        fun postRequestLogin(context: Context, inputId:String, inputPw:String, handler:JsonResponseHandler?) {

//            클라이언트 역할을 수행해주는 변수 (서버에 요청을 날려주는 역할)
            val client = OkHttpClient()

//            기본주소(BASE_URL)+기능주소(/auth) 의 조합으로 서버에 찾아갈 주소를 명시.
            val url = "${BASE_URL}/auth"

//            서버에 찾아갈때 들고갈 데이터를 추가.
            val formBody = FormBody.Builder()
                .add("login_id", inputId)
                .add("password", inputPw)
                .build() // 모두 첨부 완료했으면 build()로 마무리. 완성.

            val request = Request.Builder()
                .url(url)
//                    헤더(header)로 담아야 하는 데이터가 있다면 여기에 .header("이름", 값) 으로 추가해줘야함
//                .header("X-Http-Token", inputId)
                .post(formBody)
                .build()


            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버연결실패", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string() // 서버에서 내려준 응답을 string으로 저장.
                    val json = JSONObject(body) // string으로 저장된 응답을 JSON양식으로 가공.
//                    json변수의 내용을 분석해서 상황에 따른 처리를 할 수 있게됨.

//                    JSON 상세 분석 or 결과에 따른 처리를 화면(Activity)로 이관.
                    handler?.onResponse(json)
                }

            })

        }


    }

}









