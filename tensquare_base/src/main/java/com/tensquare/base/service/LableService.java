package com.tensquare.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.base.mapper.LableMapper;
import com.tensquare.base.pojo.Lable;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LableService {


    @Value("page.size")
    private String size;
    @Resource
    private LableMapper lableMapper;

    public List<Lable> findAll() {
        return lableMapper.findAll();
    }

    public Lable findById(String lableId) {
        return lableMapper.findById(lableId);
    }

    public void add(Lable lable) {
        lable.setId(new IdWorker(1, 1).nextId() + "");
        lableMapper.save(lable);
    }

    public void update(Lable lable) {
        lableMapper.update1(lable);
    }


    public void delete(String lableId) {
        lableMapper.deleteById(lableId);
    }

    public PageResult<Lable> searchPage(Lable lable, int page, int size) {
        PageHelper.startPage(page,size);
        PageResult<Lable> pageResult = new PageResult<>();
        try {
            List<Lable> list = lableMapper.searchPage(lable);
            pageResult.setRows(list);
            //利用pagehelper自带的pageinfo对象获取总数
            pageResult.setTotal(new PageInfo<>(list).getTotal());
            return pageResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResult;
    }


    /**
     * spring data jpa分页查询，条件查询
     */
   /* public List<Lable> findSearch(Lable lable) {
        return lableDao.findAll(new Specification<Lable>() {

            List<Predicate> list = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("lablename").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(predicate);
                }
                if (lable.getFans() != null && !"".equals(lable.getFans())) {
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
        Pageable pageable = PageRequest.of(page - 1, size);
        return lableDao.findAll(new Specification<Lable>() {
            List<Predicate> list = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("lablename").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(predicate);
                }
                if (lable.getFans() != null && !"".equals(lable.getFans())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("fans").as(String.class), "='" + lable.getFans());
                    list.add(predicate);
                }
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                return criteriaBuilder.and(arr);
            }
        }, pageable);

    }*/
}
