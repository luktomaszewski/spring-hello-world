package xyz.lomasz.springhelloworld.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

  @Value("${spring.data.elasticsearch.host}")
  private String esHost;

  @Value("${spring.data.elasticsearch.port}")
  private int esPort;

  @Value("${spring.data.elasticsearch.clustername}")
  private String esClusterName;

  @Value("${spring.data.elasticsearch.username}")
  private String esUserName;

  @Value("${spring.data.elasticsearch.password}")
  private String esPassword;

  @Value("${spring.data.elasticsearch.index}")
  private String indexName;

  @Bean
  public RestHighLevelClient client() {

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
        new UsernamePasswordCredentials(esUserName, esPassword));

    return new RestHighLevelClient(
        RestClient.builder(
            new HttpHost(esHost, esPort, "http"))
            .setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
  }

  @Bean
  public String indexName() {
    return indexName;
  }
}



