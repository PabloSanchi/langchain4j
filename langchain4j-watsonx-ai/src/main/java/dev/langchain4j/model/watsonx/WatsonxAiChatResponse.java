package dev.langchain4j.model.watsonx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatsonxAiChatResponse {

        String modelId;

        Date createdAt;

        WatsonxAiChatResults results;

        Map<String, Object> system;

}
