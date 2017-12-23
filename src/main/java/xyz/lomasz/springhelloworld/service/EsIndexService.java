package xyz.lomasz.springhelloworld.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.apachecommons.CommonsLog;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lomasz.springhelloworld.model.Airline;

@Service
@CommonsLog
public class EsIndexService {

  private RestHighLevelClient client;
  private String indexName;

  @Autowired
  public EsIndexService(RestHighLevelClient client, String indexName) {
    this.client = client;
    this.indexName = indexName;
  }

  public void index(Airline airline) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(airline);

    IndexRequest indexRequest = new IndexRequest(indexName, indexName, airline.getIcao());
    indexRequest = indexRequest.source(jsonString, XContentType.JSON);
    client.index(indexRequest);
  }

  public void delete(Airline airline) throws IOException {
    DeleteRequest deleteRequest = new DeleteRequest(indexName, indexName, airline.getIcao());
    client.delete(deleteRequest);
  }

}