package com.tensquare.qa.service;

import com.tensquare.qa.mapper.ProblemMapper;
import com.tensquare.qa.pojo.Problem;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;
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

	@Autowired
	private HttpServletRequest request;

    public void addProblem(Problem problem) {
    	problem.setId(idWorker.nextId()+"");
    	problemMapper.addProblem(problem);
    }

    public List<Problem> findAll() {
		Claims claims = (Claims) request.getAttribute("claims_user");
		if (claims == null) {
			throw new RuntimeException("权限不足，请先登陆后再查询！");
		}
		String roles = (String) claims.get("roles");
		if (!"user".equals(roles)) {
			throw new RuntimeException("权限不足，请先登陆后再查询！");
		}
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
