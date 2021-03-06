package com.cn.hnust.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.hnust.pojo.Country;
import com.cn.hnust.service.CountryService;
/**
 * @ClassName: CountryController
 * @title: CountryController.class
 * @author: liulangzhizi 
 * @Data: 2016��-11��-07�� 10:23:26 
 * @Version: V1.0.0
 * @description:  
 */
@Controller
@RequestMapping("/country")
public class CountryController{

	@Autowired
	private CountryService countryService;
	
	//��ҳ��ѯ
	@RequestMapping("/pageList")
	public String findPage(@RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "5") int pageSize, Model model) {
		model.addAttribute("page", countryService.findPage( currentPage , pageSize ));
		return "countryList";
	}
	
	//��ѯ����
	@RequestMapping("/list")
	public String findList(@RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(defaultValue = "5") int pageSize, Model model) {
		model.addAttribute("pageBean", countryService.findPage(currentPage, pageSize));
		return "countryList";
	}
	
	//����id��ѯ    idType
	@RequestMapping("/findById")
	public String findById(int id , Model model) {
		model.addAttribute("country", countryService.findById(id));
		return "country";
	}
	
	//�޸�
	@RequestMapping("/edit")
	public String edit(Country country , Model model) {
		countryService.updateCountry(country);
		return "redirect:/country/list";
	}
	
	//��ת���޸�ҳ��
	@RequestMapping("/showEdit")
	public String showEdit(int id , Model model) {
		model.addAttribute("country", countryService.findById(id));
		return "countryEdit";
	}

	//��ת�����ҳ��
	@RequestMapping("/showAdd")
	public String showAdd() {
		return "countryAdd";
	}
	
	//���
	@RequestMapping("/save")
	public String insert(Country country , Model model) {
		countryService.insertCountry(country);
		return "redirect:/country/list";
	}
	
	//ɾ��
	@RequestMapping("/delete")
	public String deleteById(int id , Model model) {
		countryService.deleteById(id);
		return "redirect:/country/list";
	}
	
	//����ɾ��
	@RequestMapping("/deletes")
	public String deletes(String ids , Model model) {
		countryService.deletes(ids);
		return "redirect:/country/list";
	}

}
