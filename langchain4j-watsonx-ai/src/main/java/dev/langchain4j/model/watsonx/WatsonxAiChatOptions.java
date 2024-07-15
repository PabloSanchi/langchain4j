package dev.langchain4j.model.watsonx;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatsonxAiChatOptions {

    private Float temperature;

    private Float topP;

    private Integer topK;

    private String decodingMethod;

    private Integer maxNewTokens;

    private Integer minNewTokens;

    private List<String> stopSequences;

    private Float repetitionPenalty;

    private Integer randomSeed;

}
