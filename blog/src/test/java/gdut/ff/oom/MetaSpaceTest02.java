package gdut.ff.oom;

/**
 * @Author bluesnail95
 * @Date 2020/10/18 18:02
 * @Description
 */
public class MetaSpaceTest02 {

    public static void main(String[] args) {
        for (int i = 0; i < 128; i++) {
            MetaSpaceVo metaSpaceVo = new MetaSpaceVo("name" + i);
            System.out.println(metaSpaceVo);
        }
    }

}
