package com.soso_server.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.soso_server.utils.AES256;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KakaoServiceImpl implements KakaoService {

    KakaoRAO rao;
    @Autowired
    AES256 aes256;

    private final String HTTP_REQUEST = "https://kapi.kakao.com/v2/user/me";

    public void setRao(KakaoRAO rao) {
        this.rao = rao;
    }

    @Override
    public String getService(String authorize_code) {
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");

            sb.append("&client_id=a42a6c91f7b1bb0d3f8e3daef2b6f24b"); //본인이 발급받은 key
//            sb.append("&redirect_uri=https://plater.kr/web/kakaologin/index.html"); // 본인이 설정한 주소
//            sb.append("&redirect_uri=https://plater.kr/web/redirect"); // 본인이 설정한 주소
            sb.append("&redirect_uri=https://angelo-s-library-2.netlify.app/redirect"); // 본인이 설정한 주소
            sb.append("&" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            bw.close();

            System.out.println("response body : " + result);

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            KakaoDTO kakaoDTO = getUserData(access_Token, refresh_Token);
            System.out.println("DB에 입력된 데이터 = " + kakaoDTO);

            System.out.println("aes256.encrypt(String.valueOf(kakaoDTO.getId())) = " + aes256.encrypt(String.valueOf(kakaoDTO.getId())));
            System.out.println("KakaoServiceImpl.getService");
            return aes256.encrypt(String.valueOf(kakaoDTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return "카카오 테이블 id를 찾을 수 없음";
        }
    }

    @Override
    public KakaoDTO getUserData(String access_Token, String refresh_Token) {
        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        KakaoDTO kakaoDTO = new KakaoDTO();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            // 필수 NOT NULL
            kakaoDTO.setKakaoId(element.getAsJsonObject().get("id").getAsString());
            kakaoDTO.setKakaoAccessToken(access_Token);
            kakaoDTO.setKakaoRefreshToken(refresh_Token);

            if (kakao_account.getAsJsonObject().has("email")) {
                kakaoDTO.setKakaoEmail(kakao_account.getAsJsonObject().get("email").getAsString());
            }
            if (properties.getAsJsonObject().has("nickname")) {
                kakaoDTO.setKakaoNickName(properties.getAsJsonObject().get("nickname").getAsString());
            }
            if (kakao_account.getAsJsonObject().has("gender")) {
                kakaoDTO.setKakaoGender(kakao_account.getAsJsonObject().get("gender").getAsString());
            }
            if (kakao_account.getAsJsonObject().has("birthday")) {
                kakaoDTO.setKakaoBirthday(kakao_account.getAsJsonObject().get("birthday").getAsString());
            }
            System.out.println("kakaoDTO = " + kakaoDTO.toString());

            // 이미 등록됐는지 체크
            KakaoDTO checkkakaoDTO = rao.findOneKakao(kakaoDTO.getKakaoId());

            if (checkkakaoDTO != null) {
                System.out.println("already register");
                rao.refreshKakao(kakaoDTO);
                return checkkakaoDTO;
            } else {
                rao.registerKakao(kakaoDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rao.findOneKakao(kakaoDTO.getKakaoId());
    }
}
