package dev.langchain4j.model.watsonx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  WatsonxAiChatResults {

    String generatedText;

    Integer generatedTokenCount;

    Integer inputTokenCount;

    String stopReason;

}
