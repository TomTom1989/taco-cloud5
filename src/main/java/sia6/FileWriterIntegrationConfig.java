package sia6;
import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

//There is also another way like using Java configuration to define an integration flow

@Configuration
public class FileWriterIntegrationConfig {
 @Bean
 public IntegrationFlow fileWriterFlow() {
 return IntegrationFlows
 .from(MessageChannels.direct("textInChannel")) 
 .<String, String>transform(t -> t.toUpperCase()) 
 .handle(Files 
 .outboundAdapter(new File("/tmp/sia6/files"))
 .fileExistsMode(FileExistsMode.APPEND)
 .appendNewLine(true))
 .get();
 }
}




