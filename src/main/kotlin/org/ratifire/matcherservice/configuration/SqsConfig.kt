package org.ratifire.matcherservice.configuration

import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI


@Configuration
class SqsConfig { // need to be reconfigured for prod env

    @Bean
    @Profile("!prod")
    fun amazonSQSAsync(): SqsAsyncClient {
        return SqsAsyncClient.builder().endpointOverride(URI.create("http://localhost:9324")).credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("accessKey", "secretKey")
                )
            ).region(Region.US_EAST_1).build()
    }

    @Bean
    fun sqsTemplate(sqsAsyncClient: SqsAsyncClient): SqsTemplate {
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build()
    }


    @Bean
    fun chatClient(builder: ChatClient.Builder): ChatClient {
        return builder.defaultSystem("You are a friendly chat bot that answers question")
            .build()
    }
}