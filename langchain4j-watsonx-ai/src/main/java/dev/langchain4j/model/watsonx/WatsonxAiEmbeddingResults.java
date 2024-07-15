package dev.langchain4j.model.watsonx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatsonxAiEmbeddingResults {

    private List<Double> embeddings;

}
