package xyz.lomasz.springhelloworld.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.apachecommons.CommonsLog;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lomasz.springhelloworld.model.Airline;

@Service
@CommonsLog
public class EsIndexService {

  private RestHighLevelClient client;

  private String indexName;

  @Autowired
  public void setRestClient(RestHighLevelClient client) {
    this.client = client;
  }

  @Autowired
  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public RestStatus index(Airline airline) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(airline);

    IndexRequest indexRequest = new IndexRequest(indexName, indexName, airline.getIcao());
    indexRequest = indexRequest.source(jsonString, XContentType.JSON);
    IndexResponse indexResponse = client.index(indexRequest);

    return indexResponse.status();
  }

  public RestStatus deleteIndex(Airline airline) throws IOException {
    DeleteRequest deleteRequest = new DeleteRequest(indexName, indexName, airline.getIcao());
    DeleteResponse deleteResponse = client.delete(deleteRequest);

    return deleteResponse.status();
  }

}