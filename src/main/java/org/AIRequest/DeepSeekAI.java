package org.AIRequest;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class DeepSeekAI {
    private static final String API_KEY = System.getenv("myKey");
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";

    public static String sendQueryToAI(String query) throws IOException {

        OkHttpClient client = new OkHttpClient();

        //construct JSON request payload
        JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("model", "deepseek-chat"); //specify your deepseek AI model over here
        jsonPayload.put("messages", new org.json.JSONArray().put(new JSONObject().put("role", "user").put("content", query)));

        RequestBody body = RequestBody.create(jsonPayload.toString(), MediaType.get("application/json"));

        //Build HTTP request
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        //fetch response from the DeepSeek AI
        try(Response response = client.newCall(request).execute()) {
            if(response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
            } else {
                return "Error: " + response.code() + " - " + response.message();
            }
        } catch (IOException e) {
            return "Exception: " + e.getMessage();
        }
    }
}
