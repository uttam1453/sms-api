package com.myschool.sms.api.auth.service.social;


import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.myschool.sms.api.common.AppException;
import com.myschool.sms.api.common.constants.SocialSignInType;
import com.myschool.sms.api.common.excecption.FacebookTokenValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by lokesh on 25/01/17.
 */
@Component
public class FacebookLoginSocialLoginTokenValidator implements SocialLoginTokenValidator {
    public static final Logger LOGGER = LoggerFactory.getLogger(FacebookLoginSocialLoginTokenValidator.class);
    @Autowired
    private Environment env;


    private String debugTokenURL = "https://graph.facebook.com/debug_token?";
    private String meUserURL = "https://graph.facebook.com/me";

    @Override
    public ValidatedToken validatedDetails(AuthTokenValidatable authTokenValidatable) {

        return validatedToken(authTokenValidatable.tokenToValidate());

    }

    private String getEmailFromAccessToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(meUserURL)
                    .queryParam("access_token", token)
                    .queryParam("fields", "email");
            HttpEntity<String> entity = new HttpEntity<String>(request.toString());
            ResponseEntity<String> response = restTemplate
                    .exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject receiptJson = new JSONObject(response.getBody());
                //check if there is an error key throw exception
                if (receiptJson.has("error")) {
                    receiptJson = (JSONObject) receiptJson.get("error");
                    throw new FacebookTokenValidationException("INVALID_TOKEN", receiptJson.getInt("code"), receiptJson.getString("message"), token);
                }
                return receiptJson.getString("email");
            }

        } catch (JSONException ex) {
            LOGGER.error("Error parsing facebook token validation response ", ex);
        }
        return null;
    }

    private ValidatedToken validatedToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        TurtleValidatedToken validatedToken = new TurtleValidatedToken();
        validatedToken.setSrc(SocialSignInType.FACEBOOK.toString());
        // create request body
        JSONObject request = new JSONObject();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(debugTokenURL)
                    .queryParam("input_token", token)
                    .queryParam("access_token", env.getProperty("FB.APP_ID") + "|" + env.getProperty("FB.APP_SECRET"));

            HttpEntity<String> entity = new HttpEntity<String>(request.toString());
            ResponseEntity<String> response = restTemplate
                    .exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject receiptJson = new JSONObject(response.getBody());
                receiptJson = (JSONObject) receiptJson.get("data");
                //check if there is an error key throw exception
                if (receiptJson.has("error")) {
                    receiptJson = (JSONObject) receiptJson.get("error");
                    throw new FacebookTokenValidationException("INVALID_TOKEN", receiptJson.getInt("code"), receiptJson.getString("message"), token);
                }
                //Check if scopes has email permission or not
                boolean hasEmailScope = false;
                JSONArray jsonArray = receiptJson.getJSONArray("scopes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    if ("email".equalsIgnoreCase(jsonArray.getString(i))) {
                        hasEmailScope = true;
                        break;
                    }
                }
                if (!hasEmailScope)
                    throw new AppException("EMAIL_PERMISSION");

                validatedToken.setValid(receiptJson.getBoolean("is_valid"));
                validatedToken.setExpiresAt(receiptJson.getLong("expires_at"));
                validatedToken.setSocialId(receiptJson.getString("user_id"));
                validatedToken.setEmail(getEmailFromAccessToken(token));
            }
        } catch (JSONException ex) {
            LOGGER.error("Error parsing facebook token validation response ", ex);
        }

        return validatedToken;
    }
}
