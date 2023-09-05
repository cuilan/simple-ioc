package cn.cuilan.dao;

import cn.cuilan.annotation.Component;

/**
 * @author zhang.yan
 * @since 2020/8/16
 */
@Component("indexDao")
public class IndexDaoImpl implements IndexDao {

    @Override
    public void index() {
        System.out.println("dao index");
    }
}
