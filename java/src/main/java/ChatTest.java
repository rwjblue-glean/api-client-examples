import java.util.ArrayList;
import java.util.List;

import com.glean.api_client.glean_api_client.Glean;
import com.glean.api_client.glean_api_client.models.components.Author;
import com.glean.api_client.glean_api_client.models.components.ChatMessage;
import com.glean.api_client.glean_api_client.models.components.ChatMessageFragment;
import com.glean.api_client.glean_api_client.models.components.ChatRequest;
import com.glean.api_client.glean_api_client.models.operations.ChatResponse;
import com.glean.api_client.glean_api_client.utils.SpeakeasyHTTPClient;

public class ChatTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting ChatTest...");
        long startTime = System.currentTimeMillis();
        
        SpeakeasyHTTPClient.setDebugLogging(true);

        String apiToken = System.getenv("GLEAN_API_TOKEN");
        String instance = System.getenv("GLEAN_INSTANCE");
        
        if (apiToken == null || instance == null) {
            System.err.println("Please set GLEAN_API_TOKEN and GLEAN_INSTANCE environment variables");
            System.exit(1);
        }
        
        System.out.println("Environment variables loaded. Instance: " + instance);
        System.out.println("Building Glean API client...");
        
        Glean apiClient = Glean.builder()
            .apiToken(apiToken)
            .instance(instance)
            .build();
            
        System.out.println("API client built in " + (System.currentTimeMillis() - startTime) + "ms");
       
        ChatMessageFragment chatMessageFragment = ChatMessageFragment.builder()
            .text("What are the company holidays this year?")
            .build();
       
        List<ChatMessageFragment> chatMessageFragmentList = new ArrayList<>();
        chatMessageFragmentList.add(chatMessageFragment);

        ChatMessage chatMessage = ChatMessage.builder()
            .fragments(chatMessageFragmentList)
//            .author(Author.USER)
            .build();
       
        List<ChatMessage> chatMessageList = new ArrayList<>();
        chatMessageList.add(chatMessage);
        
        System.out.println("Request objects built in " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("Making API call...");
        
        long apiCallStart = System.currentTimeMillis();
        ChatResponse res = apiClient.client().chat().create()
                .chatRequest(ChatRequest.builder()
                    .messages(chatMessageList)
                    .build())
                .call();
        
        long apiCallEnd = System.currentTimeMillis();
        System.out.println("API call completed in " + (apiCallEnd - apiCallStart) + "ms");

        if (res.chatResponse().isPresent()) {
            System.out.println("Response received successfully!");
            System.out.println("Total execution time: " + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println("Response: " + res.chatResponse().get());
        } else {
            System.out.println("No response received");
        }
    }
}
