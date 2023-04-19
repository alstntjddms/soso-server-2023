package com.soso_server.service;

import com.google.gson.*;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.utils.AES256;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.service.itf.KakaoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class KakaoServiceImpl implements KakaoService {

    KakaoRAO rao;

    @Autowired
    MemberRAO memberRAO;

    @Autowired
    AES256 aes256;

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

            // 이미 등록됐는지 체크
            KakaoDTO checkkakaoDTO = rao.findOneKakao(kakaoDTO.getKakaoId());

            if (checkkakaoDTO != null) {
                System.out.println("already register");

                kakaoDTO.setKakaoMsgYn(checkkakaoDTO.isKakaoMsgYn());
                // 동의항목 체크
                if(checkkakaoDTO.isKakaoScopeCheck()){
                    kakaoDTO.setKakaoMsgYn(checkScopes(access_Token));
                    kakaoDTO.setKakaoScopeCheck(false);
                }

                rao.refreshKakao(kakaoDTO);
            } else {
//                // 카카오 동의항목 메세지 체크 확인
                kakaoDTO.setKakaoMsgYn(checkScopes(access_Token));
                kakaoDTO.setKakaoDefaultNickName(kakaoDTO.getKakaoNickName());
                rao.registerKakao(kakaoDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rao.findOneKakao(kakaoDTO.getKakaoId());
    }

    @Override
    public boolean checkScopes(String accessToken) {
        try {
            URL url = new URL("https://kapi.kakao.com/v2/user/scopes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonObject json = new Gson().fromJson(response.toString(), JsonObject.class);
                JsonArray scopes = json.getAsJsonArray("scopes");

                System.out.println("scopes = " + scopes);
                for (JsonElement scope : scopes) {
                    JsonObject scopeObj = scope.getAsJsonObject();
                    String id = scopeObj.get("id").getAsString();
                    boolean agreed = scopeObj.get("agreed").getAsBoolean();
                    if ("talk_message".equals(id)) {
                        return agreed;
                    }
                }
                return false;
            } else {
                System.out.println("HTTP request failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String revokeByUserId(String userId) {
        try {
            KakaoDTO kakaoDTO = rao.findOneKakaoById(
                    memberRAO.findMemberByUserId(
                            Integer.parseInt(aes256.replaceDecodeDecryt(userId))).getId());

            URL url = new URL("https://kapi.kakao.com/v2/user/revoke/scopes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Bearer " + kakaoDTO.getKakaoAccessToken());
            String body = "scopes=[\"talk_message\"]";
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(body.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JsonObject json = new Gson().fromJson(response.toString(), JsonObject.class);
                System.out.println(json);
                System.out.println("동의 항목(메시지 보내기) 철회가 완료되었습니다.");
                return "동의 항목(메시지 보내기) 철회가 완료되었습니다.";
            } else {
                System.out.println("HTTP request failed: " + responseCode);
                return "동의 항목(메시지 보내기) 철회가 실패";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }


    @Override
    public String refreshAccessToken(String refreshToken) throws IOException {
        String clientId = "a42a6c91f7b1bb0d3f8e3daef2b6f24b"; // 카카오 디벨로퍼스에서 발급받은 REST API 키
        String grantType = "refresh_token";
        String apiUrl = "https://kauth.kakao.com/oauth/token";

        System.out.println("KakaoServiceImpl.refreshAccessToken");
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // Request Body에 필요한 파라미터를 입력합니다.
        String params = "grant_type=" + grantType + "&client_id=" + clientId + "&refresh_token=" + refreshToken;

        // Request Body를 서버에 전송합니다.
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(params.getBytes("UTF-8"));
        os.flush();
        os.close();

        // Response Code를 확인합니다.
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // JSON 응답 본문에서 Access Token을 파싱합니다.
            JSONObject jsonObject = new JSONObject(response.toString());
            String accessToken = jsonObject.getString("access_token");

            // refreshtoken을 return한 경우
            String newRefreshToken = "";
            if (jsonObject.has("refresh_token")) {
                newRefreshToken = jsonObject.getString("refresh_token");
                getUserData(accessToken, newRefreshToken);
            }else {
                getUserData(accessToken, refreshToken);
            }
            return accessToken;
        } else {
            System.out.println("HTTP error code : " + responseCode);
            return "";
        }
    }

    @Override
    public String withdraw(String userId) throws Exception {
        KakaoDTO kakaoDTO = rao.findOneKakaoById(
                memberRAO.findMemberByUserId(
                        Integer.parseInt(aes256.replaceDecodeDecryt(userId))).getId());

        String apiUrl = "https://kapi.kakao.com/v1/user/unlink";

        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + kakaoDTO.getKakaoAccessToken());

        // Response Code를 확인합니다.
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("withdraw success");
            return "탈퇴성공";
        } else {
            System.out.println("withdraw failed");
        }
        return "";
    }

    @Override
    public String updateScopeCheck(String userId) throws Exception {
        KakaoDTO kakaoDTO = rao.findOneKakaoById(
                        memberRAO.findMemberByUserId(
                        Integer.parseInt(aes256.replaceDecodeDecryt(userId))).getId());
        kakaoDTO.setKakaoScopeCheck(true);
        rao.refreshKakao(kakaoDTO);
        return "동의항목 누름";
    }

    @Override
    public Boolean selectKakaoMsgYnByUserId(String userId) throws Exception {
        KakaoDTO kakaoDTO = rao.findOneKakaoById(
                memberRAO.findMemberByUserId(
                        Integer.parseInt(aes256.replaceDecodeDecryt(userId))).getId());
        return kakaoDTO.isKakaoMsgYn();
    }

    @Override
    public List<KakaoDTO> findKakaoAll() {
        return rao.findKakaoAll();
    }

}
