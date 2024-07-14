package dev.langchain4j.model.watsonx;

import retrofit2.Call;
import retrofit2.http.*;

public interface WatsonxAiApi {

    @POST("/ml/v1/text/generation?version=2023-05-29")
    @Headers({"Content-Type: application/json"})
    Call<WatsonxAiChatResponse> completion(@Header("Authorization") String authorization, @Body WatsonxAiChatRequest request);

    @POST("/ml/v1/text/generation_stream?version=2023-05-29")
    @Headers({"Content-Type: application/json"})
    @Streaming
    Call<WatsonxAiChatResponse> streamingCompletion(@Header("Authorization") String authorization, @Body WatsonxAiChatRequest request);

    @POST("ml/v1/text/embeddings?version=2023-05-29")
    Call<WatsonxAiEmbeddingResponse> embed(@Header("Authorization") String authorization, @Body WatsonxAiEmbeddingRequest request);

}
