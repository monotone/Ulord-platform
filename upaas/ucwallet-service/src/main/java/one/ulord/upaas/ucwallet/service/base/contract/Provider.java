/**
 * Copyright(c) 2018
 * Ulord core developers
 */
package one.ulord.upaas.ucwallet.service.base.contract;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ulord.side.provider config info
 *
 * @author chenxin
 * @since 2018-08-10
 */
@Component
public class Provider {

    @Value("${ulord.side.provider.ulordProvider}")
    private String ulordProvider;
    @Value("${ulord.side.provider.tokenAddress}")
    private String  tokenAddress;
    @Value("${ulord.side.provider.candyAddress}")
    private String  candyAddress;
    @Value("${ulord.side.provider.publishAddress}")
    private String  publishAddress;
    @Value("${ulord.side.provider.keystoreFile}")
    private String  keystoreFile;
    @Value("${ulord.side.provider.keystorePassword}")
    private String  keystorePassword;


    @Value("${query.transaction.sleep1}")
    private String  querySleep1;
    @Value("${query.transaction.sleep2}")
    private String  querySleep2;

    @Value("${executor.corePoolSize}")
    private String  corePoolSize;
    @Value("${executor.maxPoolSize}")
    private String  maxPoolSize;
    @Value("${executor.keepAliveSeconds}")
    private String  keepAliveSeconds;
    @Value("${executor.queueCapacity}")
    private String  queueCapacity;
    @Value("${executor.threadNamePrefix}")
    private String  threadNamePrefix;
    @Value("${executor.awaitTerminationSeconds}")
    private String  awaitTerminationSeconds;


    public String getUlordProvider() {
        return ulordProvider;
    }
    public String getTokenAddress() {
        return tokenAddress;
    }
    public String getCandyAddress() {
        return candyAddress;
    }
    public String getPublishAddress() {
        return publishAddress;
    }
    public String getKeystoreFile() {
        return keystoreFile;
    }
    public String getKeystorePassword() {
        return keystorePassword;
    }
    public String getQuerySleep1() {  return querySleep1; }
    public String getQuerySleep2() {
        return querySleep2;
    }
    public String getCorePoolSize() {
        return corePoolSize;
    }
    public String getMaxPoolSize() {  return maxPoolSize; }
    public String getKeepAliveSeconds() {
        return keepAliveSeconds;
    }
    public String getQueueCapacity() {
        return queueCapacity;
    }
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }
    public String getAwaitTerminationSeconds() {
        return awaitTerminationSeconds;
    }
}
