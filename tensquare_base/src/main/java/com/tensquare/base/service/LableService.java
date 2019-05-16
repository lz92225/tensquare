package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class LableService {

    @Autowired
    private LableDao lableDao;

    public List<Lable> findAll() {
        return lableDao.findAll();
    }

    public Lable findById(String lableId) {
        return lableDao.findById(lableId).get();
    }

    public void add(Lable lable) {
        lable.setId(new IdWorker(1,1).nextId()+"");
        lableDao.save(lable);
    }

    public void update(Lable lable) {
        lableDao.save(lable);
    }


    public void delete(String lableId) {
        lableDao.deleteById(lableId);
    }
}
