package com.cn.hnust.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.MovieMapper;
import com.cn.hnust.pojo.Movie;
import com.cn.hnust.pojo.MovieExample;
import com.cn.hnust.pojo.MovieExample.Criteria;
import com.cn.hnust.service.MovieService;
import com.cn.hnust.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
/**
 * @ClassName: MovieServiceImpl
 * @title: MovieServiceImpl.class
 * @author: liulangzhizi
 * @Data: 2016��-11��-07�� 10:23:26 
 * @Version: V1.0.0
 * @description:  
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Resource
	private MovieMapper moviemapper;

	//��ҳ��ѯ
	public PageBean findPage(int currentPage , int pageSize,Movie movie){
		PageBean pageBean = new PageBean();
		MovieExample example=new MovieExample();
		example.setOrderByClause("Id");
		Criteria criteria= example.createCriteria();
		System.out.println("movieService获取到movie.type="+movie.getTypeid());
		if(movie.getTypeid()!=null&&movie.getTypeid()>0){
			criteria.andTypeidEqualTo(movie.getTypeid());
		}
		PageHelper.startPage(currentPage, pageSize);
		List<Movie> list = moviemapper.selectByExample(example);
		PageInfo<Movie> info = new PageInfo<Movie>(list);
		long total = info.getTotal();
		int totalPage = (int) ((total + pageSize - 1) / pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotal(total);
		pageBean.setRows(list);
		pageBean.setCurrentPage(currentPage);
		return pageBean;
	}
	
	//����id��ѯ
	public Movie findById(int id){
		return moviemapper.selectByPrimaryKey(id);
	}
	
	//��ѯ����
	public List<Movie> findList(){
		MovieExample example=new MovieExample();
		return moviemapper.selectByExample(example);
	}
	
	public List<Movie> findListByCounts(){
		return moviemapper.selectByCounts();
	}
	public List<Movie> findListByrank(){
		return moviemapper.selectByRank();
	}
	
	
	//���
	public int insertMovie(Movie entity){
		return moviemapper.insert(entity);
	}
	
	//�޸�
	public int updateMovie(Movie entity){
		MovieExample example=new MovieExample();
		return moviemapper.updateByPrimaryKeySelective(entity);
	}
	
	//ɾ��
	public int deleteById(int id){
		return moviemapper.deleteByPrimaryKey(id);
	}
	
	//����ɾ��
	public void deletes(String ids){
		String[] str=ids.split(",");
		for(String id : str){
			try {
				moviemapper.deleteByPrimaryKey(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public List<Movie> findCollectionList(List<String> act1List,List<String> act2List,List<String> directorList) {
		MovieExample example=new MovieExample();
		Criteria  criteria=example.createCriteria();
		criteria.andDirectorIn(directorList);
		criteria.andMainact1In(act1List);
		criteria.andMainact2In(act2List);
		example.setLimitCounts(15);
		return moviemapper.selectByExampleCollection(example);
	}

	@Override
	public List<Movie> findHobyList(int countryId,int typeId,int counts) {
		MovieExample example=new MovieExample();
		Criteria  criteria=example.createCriteria();
		criteria.andCountryidEqualTo(countryId);
		criteria.andTypeidEqualTo(typeId);
		example.setLimitCounts(counts);
		example.setOrderByClause("Id");
		return moviemapper.selectByExample(example);
	}

	@Override
	public List<Movie> findListNewMovie() {
		MovieExample example=new MovieExample();
		Criteria  criteria=example.createCriteria();
		example.setLimitCounts(15);
		example.setOrderByClause("startDate");
		return moviemapper.selectByExample(example);
	}

	@Override
	public PageBean findPageQuery(int currentPage, int pageSize, Movie movie) {
		PageBean pageBean = new PageBean();
		MovieExample example=new MovieExample();
		example.setOrderByClause("Id");
		Criteria criteria= example.createCriteria();
		if(!StringUtils.isNullOrEmpty(movie.getName())){
			criteria.andNameLike(movie.getName());
		}
		if(!StringUtils.isNullOrEmpty(movie.getDirector())){
			criteria.andDirectorLike(movie.getDirector());
		}
		if(!StringUtils.isNullOrEmpty(movie.getMainact1())){
			criteria.andMainact1Like(movie.getMainact1());
		}
		if(!StringUtils.isNullOrEmpty(movie.getMainact2())){
			criteria.andMainact2Like(movie.getMainact2());
		}
	    if(!StringUtils.isNullOrEmpty(movie.getIntroduce())){
	    	criteria.andIntroduce(movie.getIntroduce());
		}
		PageHelper.startPage(currentPage, pageSize);
		List<Movie> list = moviemapper.selectByExampleQuery(example);
		PageInfo<Movie> info = new PageInfo<Movie>(list);
		long total = info.getTotal();
		int totalPage = (int) ((total + pageSize - 1) / pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotal(total);
		pageBean.setRows(list);
		pageBean.setCurrentPage(currentPage);
		return pageBean;
	}

}
