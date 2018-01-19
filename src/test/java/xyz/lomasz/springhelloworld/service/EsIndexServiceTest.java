package xyz.lomasz.springhelloworld.service;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class EsIndexServiceTest {

    @Mock
    private IndexRequest indexRequest;

    @Mock
    private IndexResponse indexResponse;

    @InjectMocks
    private EsIndexService esIndexService = new EsIndexService();

    private RestHighLevelClient client = mock(RestHighLevelClient.class);

    @Before
    public void setUp() throws Exception {
        esIndexService.setRestClient(client);
        String indexName = "indexName";
        FieldUtils.writeDeclaredField(esIndexService, "indexName", indexName, true);
    }

    @Test
    public void indexAirlineShouldReturnCreatedStatus() throws IOException {
        // TODO: Unit Test: index(airline) -> RestStatus: CREATED
        // given


        // when


        // then


    }

    @Test
    public void indexAirlineShouldReturnUpdatedStatus() throws IOException {
        // TODO: Unit Test: index(airline) -> RestStatus: UPDATED
        // given


        // when


        // then


    }

    @Test
    public void indexAirlineShouldReturnIOException() throws IOException {
        // TODO: Unit Test: index(airline) -> IOException
        // given


        // when


        // then


    }

    @Test
    public void deleteAirlineShouldReturnNotFoundStatus() throws IOException {
        // TODO: Unit Test: deleteIndex(airline) -> RestStatus: NOT FOUND
        // given


        // when


        // then


    }


    @Test
    public void deleteAirlineShouldReturnDeletedStatus() throws IOException {
        // TODO: Unit Test: deleteIndex(airline) -> RestStatus: DELETED
        // given


        // when


        // then


    }

    @Test
    public void deleteAirlineShouldReturnIOException() throws IOException {
        // TODO: Unit Test: deleteIndex(airline) -> IOException
        // given


        // when


        // then


    }

}