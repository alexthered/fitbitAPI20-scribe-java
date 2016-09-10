import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.services.Base64Encoder;

import java.nio.charset.Charset;

/**
 * Created by hd on 10/09/16.
 */
public class Fitbit20ServiceImpl extends OAuth20Service {

    public Fitbit20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    /**
     * ref: https://dev.fitbit.com/docs/oauth2/#access-token-request
     * @param code
     * @param request
     * @param <T>
     * @return
     */
    @Override
    protected <T extends AbstractRequest> T createAccessTokenRequest(String code, T request) {
        OAuthConfig config = this.getConfig();
        request.addParameter("client_id", config.getApiKey());
        request.addParameter("client_secret", config.getApiSecret());
        request.addParameter("code", code);
        request.addParameter("redirect_uri", config.getCallback());
        String scope = config.getScope();
        if(scope != null) {
            request.addParameter("scope", scope);
        }

        //this is non-OAuth2 standard, but Fitbit requires it
        request.addHeader("Authorization", "Basic " + Base64Encoder.getInstance().encode(String.format("%s:%s", new Object[]{config.getApiKey(), config.getApiSecret()}).getBytes(Charset.forName("UTF-8"))));

        request.addParameter("grant_type", "authorization_code");
        return request;
    }


    /**
     * ref: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
     * @param refreshToken
     * @param request
     * @param <T>
     * @return
     */
    @Override
    protected <T extends AbstractRequest> T createRefreshTokenRequest(String refreshToken, T request) {
        if(refreshToken != null && !refreshToken.isEmpty()) {
            OAuthConfig config = this.getConfig();
            request.addParameter("client_id", config.getApiKey());
            request.addParameter("client_secret", config.getApiSecret());
            request.addParameter("refresh_token", refreshToken);
            request.addParameter("grant_type", "refresh_token");

            //this is non-OAuth2 standard, but Fitbit requires it
            request.addHeader("Authorization", "Basic " + Base64Encoder.getInstance().encode(String.format("%s:%s", new Object[]{config.getApiKey(), config.getApiSecret()}).getBytes(Charset.forName("UTF-8"))));

            return request;
        } else {
            throw new IllegalArgumentException("The refreshToken cannot be null or empty");
        }
    }

}
