package cn.cuilan.service;

import cn.cuilan.dao.IndexDao;

/**
 * @author zhang.yan
 * @date 2020/8/16
 */
public class IndexService {

    private IndexDao indexDao;

    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    public void index() {
        System.out.println("service index");
        indexDao.index();
    }

}
