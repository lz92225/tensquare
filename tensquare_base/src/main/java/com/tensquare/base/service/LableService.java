package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        lable.setId(new IdWorker(1, 1).nextId() + "");
        lableDao.save(lable);
    }

    public void update(Lable lable) {
        lableDao.save(lable);
    }


    public void delete(String lableId) {
        lableDao.deleteById(lableId);
    }

    public List<Lable> findSearch(Lable lable) {
        return lableDao.findAll(new Specification<Lable>() {

            List<Predicate> list = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("lablename").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(predicate);
                }
                if(lable.getFans()!=null && !"".equals(lable.getFans())){
                    Predicate predicate = criteriaBuilder.equal(root.get("fans").as(String.class), "='" + lable.getFans());
                    list.add(predicate);
                }
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                return criteriaBuilder.and(arr);
            }
        });
    }

    public Page<Lable> pageSearch(int page, int size, Lable lable) {
        Pageable pageable = PageRequest.of(page-1,size);
        return lableDao.findAll(new Specification<Lable>() {
            List<Predicate> list = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("lablename").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(predicate);
                }
                if(lable.getFans()!=null && !"".equals(lable.getFans())){
                    Predicate predicate = criteriaBuilder.equal(root.get("fans").as(String.class), "='" + lable.getFans());
                    list.add(predicate);
                }
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                return criteriaBuilder.and(arr);
            }
        },pageable);

    }
}
