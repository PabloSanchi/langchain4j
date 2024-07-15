package dev.langchain4j.model.watsonx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;


@Slf4j
public class WatsonxAiClient {

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private final WatsonxAiApi watsonxAiApi;
    private final boolean logStreamingResponses;
    private final String version;
    private final IamAuthenticator iamAuthenticator;

    @Builder
    public WatsonxAiClient(String baseUrl,
                           String iamToken,
                           String version,
                           Duration timeout,
                           Boolean logRequests, Boolean logResponses, Boolean logStreamingResponses,
                           Map<String, String> customHeaders) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .callTimeout(timeout)
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout);

        if(logRequests != null && logRequests) {
            okHttpClientBuilder.addInterceptor(new WatsonxAiRequestLoggingInterceptor());
        }
        if (logResponses != null && logResponses) {
            okHttpClientBuilder.addInterceptor(new WatsonxAiResponseLoggingInterceptor());
        }
        this.logStreamingResponses = logStreamingResponses != null && logStreamingResponses;

        if (customHeaders != null && !customHeaders.isEmpty()) {
            okHttpClientBuilder.addInterceptor(new GenericHeadersInterceptor(customHeaders));
        }
        OkHttpClient okHttpClient = okHttpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GSON))
                .build();

        this.version = version;
        this.iamAuthenticator = IamAuthenticator.fromConfiguration(new HashMap<String, String>() {{
            put("APIKEY", iamToken);
        }});
        this.watsonxAiApi = retrofit.create(WatsonxAiApi.class);
    }

    // TODO: implement completion
    // TODO: implement stream completion

    public WatsonxAiEmbeddingResponse embed(WatsonxAiEmbeddingRequest request) {
        try {
            retrofit2.Response<WatsonxAiEmbeddingResponse> retrofitResponse = watsonxAiApi.embed("", "", request).execute();
            if (retrofitResponse.isSuccessful()) {
                return retrofitResponse.body();
            } else {
                throw toException(retrofitResponse);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RuntimeException toException(retrofit2.Response<?> response) throws IOException {
        int code = response.code();
        String body = response.errorBody().string();

        String errorMessage = String.format("status code: %s; body: %s", code, body);
        return new RuntimeException(errorMessage);
    }

    static class GenericHeadersInterceptor implements Interceptor {

        private final Map<String, String> headers = new HashMap<>();

        GenericHeadersInterceptor(Map<String, String> headers) {
            Optional.ofNullable(headers)
                    .ifPresent(this.headers::putAll);
        }

        @NotNull
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();

            // Add headers
            this.headers.forEach(builder::addHeader);

            return chain.proceed(builder.build());
        }
    }
}
