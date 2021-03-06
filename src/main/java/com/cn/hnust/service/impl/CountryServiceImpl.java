package com.cn.hnust.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.CountryMapper;
import com.cn.hnust.pojo.Country;
import com.cn.hnust.pojo.CountryExample;
import com.cn.hnust.service.CountryService;
import com.cn.hnust.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @ClassName: CountryServiceImpl
 * @title: CountryServiceImpl.class
 * @author: liulangzhizi
 * @Data: 2016��-11��-07�� 10:23:26 
 * @Version: V1.0.0
 * @description:  
 */
@Service
public class CountryServiceImpl implements CountryService {

	@Resource
	private CountryMapper countrymapper;

	//��ҳ��ѯ
	public PageBean findPage(int currentPage , int pageSize){
		PageBean pageBean = new PageBean();
		CountryExample example=new CountryExample();
		PageHelper.startPage(currentPage, pageSize);
		List<Country> list = countrymapper.selectByExample(example);
		PageInfo<Country> info = new PageInfo<Country>(list);
		long total = info.getTotal();
		int totalPage = (int) ((total + pageSize - 1) / pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotal(total);
		pageBean.setRows(list);
		pageBean.setCurrentPage(currentPage);
		return pageBean;
	}
	
	//����id��ѯ
	public Country findById(int id){
		return countrymapper.selectByPrimaryKey(id);
	}
	
	//��ѯ����
	public List<Country> findList(){
		CountryExample example=new CountryExample();
		return countrymapper.selectByExample(example);
	}
	
	//���
	public int insertCountry(Country entity){
		return countrymapper.insert(entity);
	}
	
	//�޸�
	public int updateCountry(Country entity){
		return countrymapper.updateByPrimaryKey(entity);
	}
	
	//ɾ��
	public int deleteById(int id){
		return countrymapper.deleteByPrimaryKey(id);
	}
	
	//����ɾ��
	public void deletes(String ids){
		String[] str=ids.split(",");
		for(String id : str){
			try {
				countrymapper.deleteByPrimaryKey(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
