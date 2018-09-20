package old.dog.cature.yasige;

import lombok.Builder;
import lombok.Data;

/**
 * DownLoadFileRequester
 *
 * @author yong.han
 * 2018/9/20
 */
@Data @Builder
public class DownLoadFileRequester {

    private DownListRequester.Content content;


    public void invoke() {

    }
}
