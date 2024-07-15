package dev.langchain4j.model.watsonx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatsonxAiEmbeddingResponse {

    private String modelId;

    private Date createdAt;

    private List<WatsonxAiEmbeddingResults> results;

    private Integer inputTokenCount;

}
