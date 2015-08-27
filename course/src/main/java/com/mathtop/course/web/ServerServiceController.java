package com.mathtop.course.web;



import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/serverservice")
public class ServerServiceController extends BaseController {

	//得到所有学院
		@RequestMapping(value = "/now", method = RequestMethod.GET)
		@ResponseBody
		public Date getDate(){
			return new Date();
		}

}
