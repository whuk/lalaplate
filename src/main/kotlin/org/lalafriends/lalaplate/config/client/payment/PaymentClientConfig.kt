package org.lalafriends.lalaplate.config.client.payment

import org.lalafriends.lalaplate.config.AppEnv
import org.lalafriends.lalaplate.util.RestClientUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentClientConfig(
    private val appEnv: AppEnv,
) {
    @Bean
    fun paymentClient(): PaymentClient {
        val env = appEnv.client.payment
        return if (env.useDummy) {
            PaymentDummyClient()
        } else {
            PaymentStableClient(env, RestClientUtil.new(env))
        }
    }
}
