package org.ratifire.matcherservice.configuration

import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI


@Configuration
class SqsConfig {

    @Bean
    fun amazonSQSAsync(): SqsAsyncClient {
        return SqsAsyncClient.builder()
            .endpointOverride(URI.create("http://localhost:9324"))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("accessKey", "secretKey")
                )
            )
            .region(Region.US_EAST_1)
            .build()
    }

    @Bean
    fun sqsTemplate(sqsAsyncClient: SqsAsyncClient): SqsTemplate {
        return SqsTemplate.builder()
            .sqsAsyncClient(sqsAsyncClient)
            .build()
    }
}