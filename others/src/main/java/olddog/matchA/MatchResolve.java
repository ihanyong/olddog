package olddog.matchA;

import javafx.util.Pair;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MatchResolve
 *
 * @author yong.han
 * 2018/10/15
 */
@Data
public class MatchResolve {


    public static void main(String[] args) {
        MatchResolve m4 = new MatchResolve(4L, 41L, 4, 10, 7, 6);
        MatchResolve m3 = new MatchResolve(3L, 31L, 3, 10, 0, 1);
        MatchResolve m2 = new MatchResolve(2L, 21L, 2, 10, 1, 0);
        MatchResolve m1 = new MatchResolve(1L, 11L, 1, 10, 0, 0);


        m4.setSmallerItem(m3);
        m3.setSmallerItem(m2);
        m2.setSmallerItem(m1);


        long current = System.currentTimeMillis();
        MatchResult result = m4.match();
        System.out.println("cost time is " + (System.currentTimeMillis() - current));
        System.out.println(result.result);
        System.out.println(result.unpack);

    }





    public MatchResolve(Long itemId, Long subItemId, long level, long coefficient, long availableQty, long targetQty) {
        this.itemId = itemId;
        this.subItemId = subItemId;
        this.level = level;
        this.coefficient = coefficient;
        this.availableQty = availableQty;
        this.targetQty = targetQty;
    }


    /** 对应物的Id */
    private Long itemId;
    private Long subItemId;
    /** 所属层级 */
    private long level;
    /** 转换系数 */
    private long coefficient;

    private long availableQty;

    private long targetQty;


    /** 对应的小规格物，level为1时为null*/
    private MatchResolve smallerItem;


    /**
     * 破坏性操作， 会改变 available
     * @return
     */
    public MatchResult  match() {
        List<ItemWithQty> result = new ArrayList<>();
        List<ItemWithQty> unpack = new ArrayList<>();

        if (!enough()) {
            throw new RuntimeException("not enough");
        }
        match(result, unpack, 0);

        return new MatchResult(result, unpack);
    }

    public void match(List<ItemWithQty> result, List<ItemWithQty> unpack, long parentOutstanding) {
        long currentTarget = targetQty + parentOutstanding * coefficient;
        long outstanding = 0;
        if (availableQty >= currentTarget) {
            if (currentTarget > 0) {
                result.add(new ItemWithQty(this.itemId, this.subItemId, currentTarget));
            }
        } else {
            if (availableQty > 0) {
                result.add(new ItemWithQty(this.itemId, this.subItemId, availableQty));
            }
            outstanding = currentTarget - availableQty;
        }

        if (null != smallerItem) {
            if (!smallerItem.enough() && availableQty > currentTarget) {
                this.availableQty--;
                this.smallerItem.availableQty += this.smallerItem.coefficient;
                unpack.add((new ItemWithQty(this.itemId, this.subItemId, 1)));
            }
            smallerItem.match(result, unpack, outstanding);
        }
    }


    private boolean enough() {
        return availableSmallerQty(0) >= endTargetQty(0);
    }

    private long availableSmallerQty(long parentQty) {
        long current = availableQty + parentQty * coefficient;

        if (smallerItem != null) {
            current = this.smallerItem.availableSmallerQty(current);
        }
        return current;
    }

    private long endTargetQty(long parentQty) {
        long current = targetQty + parentQty * coefficient;
        if (smallerItem != null) {
            current = this.smallerItem.endTargetQty(current);
        }
        return current;
    }


    public static class MatchResult {
        public MatchResult(List<ItemWithQty> result, List<ItemWithQty> unpack) {
            this.result = result;
            this.unpack = unpack;
        }

        private List<ItemWithQty> result;
        private List<ItemWithQty> unpack;



    }

    public static class ItemWithQty {

        public ItemWithQty(Long itemId, Long subItemId, long qty) {
            this.itemId = itemId;
            this.subItemId = subItemId;
            this.qty = qty;
        }

        private Long itemId;
        private Long subItemId;
        private long qty;

        @Override
        public String toString() {
            return "ItemWithQty{" +
                    "itemId=" + itemId +
                    ", subItemId=" + subItemId +
                    ", qty=" + qty +
                    '}';
        }
    }



}
