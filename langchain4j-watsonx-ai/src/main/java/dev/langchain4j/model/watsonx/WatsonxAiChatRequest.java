package dev.langchain4j.model.watsonx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatsonxAiChatRequest {

    private String input;

    private WatsonxAiChatOptions parameters;

    private String modelId;

    private String projectId;

}
