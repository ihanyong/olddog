package old.dog.cature.yasige;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * DownListRequester
 *
 * @author yong.han
 * 2018/9/20
 */
@Data @Builder
public class DownListRequester {
//public class DownListRequester implements Iterator<DownListRequester.Resp> {

    private final static String remote_url = "http://www.ieltsbro.com/api/web_download/download_list";

    private int category_id;


    public static void main(String[] args) {
        DownListRequester requester = DownListRequester.builder().category_id(1).build();

        System.out.println(requester.invoke());


    }

    public Resp invoke() {
        CloseableHttpClient client = HttpClients.createDefault();

        Request request = Request.builder().category_id(category_id).page(1).per(50).build();
        CloseableHttpResponse  response = null;
        try{
            response = client.execute(request.asHttpPost());

            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            return Resp.ofJson(content);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }




    //{"page":1,"per":10,"category_id":1}
    @Data @Builder
    public static class Request {
        private int page;
        private int per;
        private int category_id;

        public HttpPost asHttpPost() {
            HttpPost post = new HttpPost(remote_url);

            StringBuilder builder = new StringBuilder();
            // {"page":1,"per":20,"category_id":2}
            builder.append("{\"page\":").append(page);
            builder.append(",\"per\":").append(per);
            builder.append(",\"category_id\":").append(category_id);
            builder.append("}");

            HttpEntity entity = new StringEntity(builder.toString(), ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            return post;
        }

    }

    // {"status":0,"content":{"content":[{"id":2,"title":"2018年1-4月雅思口语题库打印版【雅思哥出品】","fileSize":"64.22 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/2/0478243d0c4e4a5fc9260a45d285cfa1.pdf","downloadCount":20131,"extension":".pdf"},{"id":43,"title":"雅思口语中最实用的10个精华句型","fileSize":"9.17 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/43/fb3b68fa0f11ed600a8668ed78b83865.pdf","downloadCount":16023,"extension":".pdf"},{"id":37,"title":"高分口语替换词","fileSize":"2.50 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/37/2f66a13e169878fd6e38d6ac33ee31e8.pdf","downloadCount":14492,"extension":".pdf"},{"id":41,"title":"雅思口语亮点词汇","fileSize":"3.27 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/41/7a18383cc392ccae58388c0916f98fa0.pdf","downloadCount":13651,"extension":".pdf"},{"id":38,"title":"口语考官最爱的170个问题(上)","fileSize":"2.04 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/38/aa422ff474cfbe9e9e7108d645a702f7.pdf","downloadCount":13611,"extension":".pdf"},{"id":40,"title":"雅思口语必备词汇","fileSize":"2.23 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/40/ce6e1ffc08688532b743ddce2c36acd3.pdf","downloadCount":13169,"extension":".pdf"},{"id":34,"title":"153句高频日常口语（上）","fileSize":"2.51 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/34/dff30765838dc2308627fef30429c044.pdf","downloadCount":13044,"extension":".pdf"},{"id":42,"title":"雅思口语提分词汇","fileSize":"4.00 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/42/3c696d69050081cc82d9878470b8bb27.pdf","downloadCount":12731,"extension":".pdf"},{"id":39,"title":"口语考官最爱的170个问题（下）","fileSize":"2.03 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/39/466accb69da79329b7c848d965d69698.pdf","downloadCount":12611,"extension":".pdf"},{"id":44,"title":"雅思口语中最易混淆的介词用法（上）","fileSize":"2.82 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/44/cf66295abb38a8fd6e8c765f8f5c12b8.pdf","downloadCount":12381,"extension":".pdf"},{"id":46,"title":"雅思提分地道英语习语（上）","fileSize":"3.24 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/46/ba7308845e63a3ebb061a05ddd06ba6e.pdf","downloadCount":11996,"extension":".pdf"},{"id":35,"title":"153句高频日常口语（下）","fileSize":"2.26 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/35/cb8b7cb47bc08cb0e8b6a70dd0a03ed9.pdf","downloadCount":11784,"extension":".pdf"},{"id":36,"title":"地道英语常用短语汇总","fileSize":"3.84 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/36/cce378b399684d45497c0ff6d3fa9d02.pdf","downloadCount":11624,"extension":".pdf"},{"id":47,"title":"雅思提分地道英语习语（下）","fileSize":"2.74 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/47/cfeea50a79a766e99975e2b164826705.pdf","downloadCount":11617,"extension":".pdf"},{"id":48,"title":"英语日常口语100句","fileSize":"2.84 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/48/8b0e484b99c932c1d776f0472714b5af.pdf","downloadCount":11582,"extension":".pdf"},{"id":45,"title":"雅思口语中最易混淆的介词用法（下）","fileSize":"3.03 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/45/f642ab0b13e1e2889234c7c2619ee0a1.pdf","downloadCount":11476,"extension":".pdf"},{"id":55,"title":"2018年5-8月雅思口语题库打印版【雅思哥出品】","fileSize":"31.29 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/55/6fc0a08d082c95d3da8bd09a4748ce8f.pdf","downloadCount":8663,"extension":".pdf"},{"id":66,"title":"100个口语提分的tips （上）","fileSize":"2.71 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/66/f4ef8fb2c2822afa39ac6aea2ed3229a.pdf","downloadCount":1211,"extension":".pdf"},{"id":70,"title":"口语练习必备100个经典句子 （上）","fileSize":"3.51 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/70/522481aa811cca338bb2be7d530fd9df.pdf","downloadCount":1192,"extension":".pdf"},{"id":67,"title":"100个口语提分的tips （下）","fileSize":"2.72 MB","fileUrl":"https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/67/deb9cab3b043932e0bff8f9b042a1683.pdf","downloadCount":1034,"extension":".pdf"}],"totalCount":26},"message":""}
    @Data
    public static class Resp {
        // 0 is ok
        private int status;
        private Rc content;
        private String message;


        private static Resp ofJson(String json) {
            return JSON.parseObject(json, Resp.class);
        }
    }

    @Data
    public static class Rc {
        private long totalCount;
        private List<Content> content;
    }



//    id=3, title=35个雅思写作超有用句型（上）, fileSize=4.03 MB, fileUrl=https://hcp-official-site.oss-cn-shenzhen.aliyuncs.com/uploads/web_download/file/3/eeef9eb6bc35876180e6d602ea5b40fa.pdf, downloadCount=17432, extension=.pdf
    @Data
    public static class Content {
        private int id;
        private String title;
        private String fileSize;
        private String fileUrl;
        private String downloadCount;
        private String extension;

    }

}
