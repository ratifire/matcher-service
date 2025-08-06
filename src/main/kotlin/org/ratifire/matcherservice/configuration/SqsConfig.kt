package org.ratifire.matcherservice.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.context.annotation.Profile
import java.net.URI

@Configuration
class SqsConfig {

    @Value("\${aws.sqs.accessKey}")
    private lateinit var accessKey: String

    @Value("\${aws.sqs.secretKey}")
    private lateinit var secretKey: String

    @Value("\${aws.sqs.region}")
    private lateinit var region: String

    @Bean
    @Profile("!local")
    fun sqsAsyncClient(): SqsAsyncClient {
        return SqsAsyncClient.builder()
            .region(Region.of(region))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey)
                )
            )
            .build()
    }

    @Bean
    @Profile("local")
    fun amazonSQSAsync(): SqsAsyncClient {
        return SqsAsyncClient.builder().endpointOverride(URI.create("http://elasticmq:9324")).credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create("accessKey", "secretKey")
            )
        ).region(Region.US_EAST_1).build()
    }

    @Bean
    fun sqsTemplate(sqsAsyncClient: SqsAsyncClient): SqsTemplate {
        return SqsTemplate.builder()
            .sqsAsyncClient(sqsAsyncClient)
            .build()
    }
}
