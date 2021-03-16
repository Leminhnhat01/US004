package com.project.motorspringmvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.motorspringmvc.model.MotorCurrencyModel;
import com.project.motorspringmvc.model.MotorModel;
import com.project.motorspringmvc.model.MotorReceiptModel;

import com.project.motorspringmvc.dao.MotorReceiptDao;



@Controller
public class MotorReceiptController {
	private static Logger logger=Logger.getLogger(MotorReceiptController.class);
	
	@RequestMapping(value="/search", method =RequestMethod.GET)
	@ResponseBody
	public String searchlostcard(@RequestParam String policyNo) { 
		MotorReceiptDao dao=new MotorReceiptDao();
		MotorModel listMotor=dao.findByPolicyNo(policyNo);
		return listMotor.getCurrency();
	}
	
	@RequestMapping("/main")
    public String formMain(Model model) {
		MotorReceiptDao dao=new MotorReceiptDao();
		List<MotorCurrencyModel> listCurrency=dao.list();
		model.addAttribute("listCurrency",listCurrency);
		
        return "main";   
    }
	
	@RequestMapping("/find")
    public String findNew() {
        return "find";   
    }
	
	@RequestMapping("/create")
	public String motorCreate(@ModelAttribute("motorReceipt") MotorReceiptModel m,Model model) {
		model.addAttribute("receipt",m);
		MotorReceiptDao dao=new MotorReceiptDao();
		
		List<MotorCurrencyModel> listCurrency=dao.list();
		model.addAttribute("listCurrency",listCurrency);

		//generates random receipt no and check it not exist in database
		List<MotorReceiptModel> checkItemReceiptNo=new ArrayList<MotorReceiptModel>();
		String randomR=null;
		do {
			String randomReceiptNo=RandomStringUtils.randomAlphanumeric(8).toUpperCase();
			checkItemReceiptNo=dao.findAllMotorReceipt().stream().filter(item->item.getReceipt_no().equals(randomReceiptNo)).collect(Collectors.toList());
			if(checkItemReceiptNo.isEmpty()) {
				randomR=randomReceiptNo;
				break;
			}
		}while(checkItemReceiptNo.isEmpty()==false);
		m.setReceipt_no(randomR);
		
		MotorModel m2=null;
		try {
			m2=dao.findByPolicyNo(m.getPolicy_no());
			System.out.print(m2.getAmount());
		}catch(Exception e) {
			logger.error("Policy No not found!1");
		}
		
		if(m2!=null) {
			if(m2.getAmount().equals(m.getAmount())) {
				dao.createMotor(m);
				dao.updatePaymentStatus(m.getPolicy_no(), "Paid");
				model.addAttribute("success", "Successfully Receipted");
				logger.info("Success");
			}
			else {
				model.addAttribute("errorAmount", "Your input amount not match with Posted Premium of policy transaction");
			}
		}
		else {
			model.addAttribute("error", "Your Policy_No Not Found, Please Check Again");
		}
		return "main";
	}
	
	@RequestMapping(value="/findItem",method =RequestMethod.GET,headers = "Accept=application/json")
	public String getReceiptMotorByPolicy(@RequestParam String policy_no,Model model) {
		model.addAttribute("policyNO",policy_no);
		MotorReceiptDao dao=new MotorReceiptDao();
		if(policy_no!=null) {
			MotorReceiptModel m3=dao.findReceiptByPolicyNo(policy_no);
			if(m3!=null)
			{
				model.addAttribute("receiptModel",m3);
				logger.info(m3.getPolicy_no());
			}
			else {
				model.addAttribute("error", "Your Policy_No Not Found, Please Check Again");
			}
		}
		return "find";
	}
	
//	@RequestMapping(value="/findAllItem",method = RequestMethod.GET)
//	@ResponseBody
//	public List<MotorReceiptModel> findAllMotor(){
//		List<MotorReceiptModel> list=new ArrayList<MotorReceiptModel>();
//		MotorReceiptDao dao=new MotorReceiptDao();
//		list =dao.findAllMotor();
//		return list;
//	}
	
//	@RequestMapping(value="/find",method =RequestMethod.GET,headers = "Accept=application/json")
//	@ResponseBody
//	public String getMotorByPolicy(@RequestParam String policy_no) {
//		ModelAndView mv=new ModelAndView("showinfo");
//		MotorReceiptDao dao=new MotorReceiptDao();
//		MotorModel m3=dao.findByPolicyNo(policy_no);
//		mv.addObject(m3);
//		return m3.getPolicy_no()+m3.getPayment_status();
//	}
	  
}
