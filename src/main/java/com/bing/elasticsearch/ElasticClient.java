package com.bing.elasticsearch;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.query.QuerySearchRequest;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import sun.rmi.transport.Transport;

import java.net.InetAddress;

/**
 *
 */
public class ElasticClient {
    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        /*IndexRequest indexRequest = new IndexRequest("car_shop","cars","1");
        indexRequest.source(XContentFactory.jsonBuilder().startObject()
                .field("brand","宝马")
                .field("name","宝马321").field("price",310000)
                .field("product_date","2017-01-01").endObject()
        );*/

       /*ActionFuture<IndexResponse> future =  transportClient.index(indexRequest2);
       IndexResponse indexResponse = future.get();
        System.out.println(indexResponse.toString());

        UpdateRequest updateRequest = new UpdateRequest("car_shop", "cars", "1");
        updateRequest.doc(XContentFactory.jsonBuilder().startObject().field("price",310000).endObject()).upsert(indexRequest);

        UpdateResponse updateResponse = transportClient.update(updateRequest).get();*/


        /*IndexRequest indexRequest2 = new IndexRequest("car_shop","cars","2");
        indexRequest.source(XContentFactory.jsonBuilder().startObject()
                .field("brand","奔驰")
                .field("name","奔驰250").field("price",350000)
                .field("product_date","2017-02-01").endObject());

        ActionFuture<IndexResponse> future =  client.index(indexRequest2);
        IndexResponse indexResponse = future.get();
        System.out.println(indexResponse.toString());*/

        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        IndexRequestBuilder indexRequestBuilder = client.prepareIndex("car_shop", "sales", "3")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("brand", "奔驰")
                        .field("name", "奔驰C200")
                        .field("price", 350000)
                        .field("produce_date", "2017-01-20")
                        .field("sale_price", 320000)
                        .field("sale_date", "2017-01-25")
                        .endObject());
        bulkRequestBuilder.add(indexRequestBuilder);

        UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate("car_shop", "sales", "1")
                .setDoc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("sale_price", 290000)
                        .endObject());
        bulkRequestBuilder.add(updateRequestBuilder);

        DeleteRequestBuilder deleteReqeustBuilder = client.prepareDelete("car_shop", "sales", "2");
        bulkRequestBuilder.add(deleteReqeustBuilder);

        BulkResponse bulkResponse = bulkRequestBuilder.get();

        for (BulkItemResponse bulkItemResponse : bulkResponse.getItems()) {
            System.out.println("version: " + bulkItemResponse.getVersion());
        }

        client.close();


    }
}
