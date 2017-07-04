package olddog.elasticsearchclient;/**
 * Created by hanyong on 2017/3/31.
 */


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hanyong
 * @Date 2017/3/31
 */
public class ElasticSearchMain {
    public static void main(String[] args) throws UnknownHostException {

        final TransportClient client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000; i++) {
                    long tookMilns = System.currentTimeMillis();
                    String[] hitIds = doGeoDistanceQuery(client,"hanyong", "rider", 30.270765, 120.215807, 5);
                    tookMilns = System.currentTimeMillis()-tookMilns;
                    if (null != hitIds) {
                        System.out.println(" tookMilns:" + tookMilns + ", thread:" +Thread.currentThread().getId() + ":" + Arrays.toString(hitIds) );
                    }
//                }
//
//            }
//        }).start();


//        for (int i = 0; i < 10; i++) {
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String threadId = String.valueOf(Thread.currentThread().getId());
//                    for (int i = 0; i < 500; i++) {
//                        String id = threadId + i;
//                        double random = Math.random();
//
//                        doGeoDocumentPut(client, "hanyong", "rider", id, 30.270565+random, 120.215807+random);
//                    }
//
//                }
//            }).start();
//        }

//
//        doGeoDocumentPut(client, "hanyong", "rider", "103", 30.270565, 120.215807);
//        doGeoDocumentDelete(client, "hanyong", "rider", "103");

//        client.close();
    }



    private static final String FIELD_LOCATION = "location";

    private static String[] doGeoDistanceQuery(TransportClient client,String index, String type, double lat, double lon, double distance) {
        QueryBuilder qb = new GeoDistanceQueryBuilder(FIELD_LOCATION).point(lat, lon).distance(distance, DistanceUnit.KILOMETERS);

        List<String> hitIdList = new LinkedList<>();

        SearchResponse response = client.prepareSearch(index).setTypes(type).setScroll(new TimeValue(60000)).setQuery(qb).setSize(100).get();

        while (true) {
            for (SearchHit hit : response.getHits()) {
                hitIdList.add(hit.getId());
            }

            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).get();

            if (0 == response.getHits().getHits().length) {
                break;
            }
        }

        return hitIdList.toArray(new String[hitIdList.size()]);


    }

    private static void doGeoDocumentPut(TransportClient client,String index, String type, String id, double lat, double lon) {
        client.prepareIndex(index, type).setSource(FIELD_LOCATION, new double[]{lon, lat}).setId(id).get();
    }

    private static void doGeoDocumentDelete(TransportClient client,String index, String type, String id) {
        client.prepareDelete(index, type, id).get();
    }
}