package com.laputa.foundation.elasticsearch.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.repository.config.ElasticsearchRepositoryConfigExtension;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

import java.util.Properties;

/**
 * Created by jiangdongping on 2018/3/8 0008.
 */
@Configuration
@EnableElasticsearchRepositories(elasticsearchTemplateRef = "laputaElasticsearchTemplate", basePackages = "com.laputa.*.elasticsearch.repositories")
public class LaputaElasticsearchConfiguration {

    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

    @Value("${elasticsearch.cluster.nodes:127.0.0.1:9300}")
    private String clusterNodes;

    /**
     * 你可以设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，<br/>
     * 把集群中其它机器的ip地址加到客户端中，<br/>
     * 这样做的好处是一般你不用手动设置集群里所有集群的ip到连接客户端，它会自动帮你添加，并且自动发现新加入集群的机器。<br/>
     * <p>
     * 注意：当ES服务器监听使用内网服务器IP而访问使用外网IP时，不要使用client.transport.sniff为true，<br/>
     * 在自动发现时会使用内网IP进行通信，导致无法连接到ES服务器， <br/>
     * 应该直接使用addTransportAddress方法<即配置 clusterNodes 用逗号分割></>进行指定ES服务器。 <br/>
     */
    @Value("${elasticsearch.client.transport.sniff:true}")
    private Boolean clientTransportSniff;

    /**
     * Set to true to ignore cluster name validation of connected nodes. (since 0.19.4)
     */
    @Value("${elasticsearch.client.transport.ignore_cluster_name:false}")
    private Boolean clientIgnoreClusterName;

    /**
     * The time to wait for a ping response from a node. Defaults to 5s.
     */
    @Value("${elasticsearch.client.transport.ping_timeout:5s}")
    private String clientPingTimeout;

    /**
     * 轮询集群节点连接的频率
     */
    @Value("${elasticsearch.client.transport.nodes_sampler_interval:5s}")
    private String clientNodesSamplerInterval;

    @Bean
    public TransportClient elasticsearchClient() throws Exception {
        TransportClientFactoryBean factory = new TransportClientFactoryBean();
        factory.setClusterNodes(clusterNodes);
        factory.setProperties(createProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    private Properties createProperties() {
        Properties properties = new Properties();
        properties.put("cluster.name", clusterName);
        properties.put("client.transport.sniff", clientTransportSniff);
        properties.put("client.transport.ignore_cluster_name", clientIgnoreClusterName);
        properties.put("client.transport.ping_timeout", clientPingTimeout);
        properties.put("client.transport.nodes_sampler_interval", clientNodesSamplerInterval);
        //properties.putAll(this.properties.getProperties());
        return properties;
    }


    @Bean
    public ElasticsearchConverter elasticsearchConverter(
            SimpleElasticsearchMappingContext mappingContext) {
        return new MappingElasticsearchConverter(mappingContext);
    }

    @Bean
    public SimpleElasticsearchMappingContext mappingContext() {
        return new SimpleElasticsearchMappingContext();
    }

    @Bean
    public ElasticsearchTemplate laputaElasticsearchTemplate(Client elasticsearchClient,
                                                             ElasticsearchConverter converter) {
        try {
            return new ElasticsearchTemplate(elasticsearchClient, converter);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
