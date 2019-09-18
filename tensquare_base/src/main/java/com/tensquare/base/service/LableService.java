package com.tensquare.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.base.mapper.LableMapper;
import com.tensquare.base.pojo.Lable;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LableService {


    @Value("page.size")
    private String size;
    @Resource
    private LableMapper lableMapper;

    @Autowired
    private IdWorker idWorker;

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
        PageHelper.startPage(page, size);
        PageResult<Lable> pageResult = new PageResult<>();
        List<Lable> list = lableMapper.searchPage(lable);
        pageResult.setRows(list);
        //利用pagehelper自带的pageinfo对象获取总数
        pageResult.setTotal(new PageInfo<>(list).getTotal());
        return pageResult;
    }

    public List<Lable> test_propagation() {
        Lable lable = new Lable();
        lable.setId(idWorker.nextId()+"");
        lable.setLabelname("test_propagation");
        lable.setCount(22L);
        lable.setState("2");
        lable.setFans(22L);
        lable.setRecommend("2");
        lableMapper.save(lable);
        List<Lable> list = lableMapper.findAll();
        return list;
    }


    public void batchInsert(){
        Lable lable = new Lable();
        ArrayList<Lable> list = new ArrayList<>();
        lable.setState("2");
        lable.setRecommend("2");
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 3000000; i++) {
            System.out.println("第"+(i+1)+"条数据！");
            lable.setId(System.currentTimeMillis()+""+i);
            lable.setLabelname("test_propagation"+i);
            lable.setCount(22L+i);
            lable.setFans(22L+i);
            list.add(lable);
            if((i+1)%30==0){
                lableMapper.batchAdd(list);
                list.clear();
            }
        }
//        lableMapper.batchAdd(list);
        long endtime = System.currentTimeMillis();
        System.out.println("花费时间："+(endtime-starttime));
//        try {
//            new Thread().sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Lable lable1 = new Lable();
//        ArrayList<Lable> list1 = new ArrayList<>();
//        lable1.setState("2");
//        lable1.setRecommend("2");
//        long starttime1 = System.currentTimeMillis();
//        for (int i = 0; i < 300; i++) {
//            System.out.println("第"+(i+1)+"条数据！");
//            lable1.setId(System.currentTimeMillis()+""+i);
//            lable1.setLabelname("test_propagation"+i);
//            lable1.setCount(22L+i);
//            lable1.setFans(22L+i);
//            list.add(lable1);
//            lableMapper.save(lable1);
//        }
//        long endtime1 = System.currentTimeMillis();
//        System.out.println("花费时间："+(endtime1-starttime1));
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
