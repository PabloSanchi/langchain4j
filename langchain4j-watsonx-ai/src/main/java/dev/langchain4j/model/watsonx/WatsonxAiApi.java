package dev.langchain4j.model.watsonx;

import retrofit2.Call;
import retrofit2.http.*;

public interface WatsonxAiApi {

    @POST("/ml/v1/text/generation")
    @Headers({"Content-Type: application/json"})
    Call<WatsonxAiChatResponse> completion(@Header("Authorization") String authorization, @Query("version") String version, @Body WatsonxAiChatRequest request);

    @POST("/ml/v1/text/generation_stream")
    @Headers({"Content-Type: application/json"})
    @Streaming
    Call<WatsonxAiChatResponse> streamingCompletion(@Header("Authorization") String authorization, @Query("version") String version, @Body WatsonxAiChatRequest request);

    @POST("ml/v1/text/embeddings")
    Call<WatsonxAiEmbeddingResponse> embed(@Header("Authorization") String authorization, @Query("version") String version, @Body WatsonxAiEmbeddingRequest request);

}
