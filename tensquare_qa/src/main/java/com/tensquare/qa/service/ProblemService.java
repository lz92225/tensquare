package com.tensquare.qa.service;

import com.tensquare.qa.mapper.ProblemMapper;
import com.tensquare.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class ProblemService {

	@Autowired
	private RedisTemplate redisTemplate;

	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private ProblemMapper problemMapper;

    public void addProblem(Problem problem) {
    	problem.setId(idWorker.nextId()+"");
    	problemMapper.addProblem(problem);
    }

    public List<Problem> findAll() {
    	return problemMapper.findAll();
    }


    /**
	 * 查询全部列表
	 * @return
	 */

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */

	/**
	 * 增加
	 * @param problem
	 */

	/**
	 * 修改
	 * @param problem
	 */

	/**
	 * 删除
	 * @param id
	 */

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */

}
