package kr.co.mz.main.testmain;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import kr.co.mz.comm.utils.excel.ExcelDownloadService;
import kr.co.mz.comm.utils.excel.ExcelUploadService;
import kr.co.mz.config.Constants;
import kr.co.mz.config.Constants.UPLOADS;

@Controller(value = "TestController")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private ExcelDownloadService excelDownloadService;
	@Autowired
	private ExcelUploadService exceluploadService;
	
	@RequestMapping(value="/testmain", method=RequestMethod.GET)
	public ModelAndView testmain(HttpServletRequest req) throws Exception {
		logger.debug("/testmain");
		ModelAndView mav = new ModelAndView("main/testmain/testmain");
		return mav;
	}
	
	@RequestMapping(value="/jsontest1", method=RequestMethod.GET)
	@ResponseBody
	public Object jsontest1(HttpServletRequest req) throws Exception {
		logger.debug("/jsontest1");
		
		BoardVO vo = new BoardVO();
		vo.setId("20200304");
		vo.setTitle("board title");
		vo.setContent("board Content");
		
		return vo;
	}
	
	@RequestMapping(value="/jsonviewtest2", method=RequestMethod.GET)
	public ModelAndView jsonviewtest2(HttpServletRequest req) throws Exception {
		logger.debug("/jsonviewtest2");
		ModelAndView mav = new ModelAndView();
		
		BoardVO vo = new BoardVO();
		vo.setId("2020030415");
		vo.setTitle("board title jsvonView");
		vo.setContent("board Content jsonView");
		
		ObjectMapper objecMapper = new ObjectMapper();
		String jsonString = objecMapper.writeValueAsString(vo);
		
		mav.addObject("res", jsonString.toString());
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/gsontest3", method=RequestMethod.GET)
	public ModelAndView gsontest3(HttpServletRequest req) throws Exception {
		logger.debug("/gsontest3");
		ModelAndView mav = new ModelAndView();
		
		BoardVO vo = new BoardVO();
		vo.setId("2020030416");
		vo.setTitle("board title gson");
		vo.setContent("board Content gson");
//		JsonObject vo = new JsonObject();
//		vo.addProperty("id", "2020030416");
//		vo.addProperty("title", "board title gson");
//		vo.addProperty("content", "board Content gson");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		String jsonString = gson.toJson(vo);
		
		mav.addObject("res", jsonString.toString());
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/sendJsonUsingJackson", method=RequestMethod.POST)
	public ModelAndView sendJsonUsingJackson(HttpServletRequest req, @RequestBody BoardVO para) throws Exception {
		logger.debug("/sendJsonUsingJackson");
		ModelAndView mav = new ModelAndView();
		
		ObjectMapper objecMapper = new ObjectMapper();
		String jsonString = objecMapper.writeValueAsString(para);
		
		mav.addObject("res", jsonString.toString());
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/sendJsonUsingGson", method=RequestMethod.POST)
	public ModelAndView sendJsonUsingGson(HttpServletRequest req) throws Exception {
		logger.debug("/sendJsonUsingGson");
		ModelAndView mav = new ModelAndView();
		
		Enumeration params = req.getParameterNames();
		logger.debug("----------------------------");
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    logger.debug(name + " : " +req.getParameter(name));
		}
		logger.debug("----------------------------");
		
		String regJsonString = req.getParameter("jsonstr");
		logger.debug("regJsonString="+regJsonString);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		BoardVO vo = (BoardVO)gson.fromJson(regJsonString, BoardVO.class);
		
		String jsonString = gson.toJson(vo);
		
		mav.addObject("res", jsonString.toString());
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/testExcelDown1.ajax", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public void testExcelDown1_ajax(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("/testExcelDown1_ajax");
		
		XSSFWorkbook wb = excelDownloadService.testDownExcel();
		
		String fileName = "sampleExcel.xlsx";
		
		OutputStream out = res.getOutputStream();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/x-msexcel");
		res.setHeader("Set-Cookie", "fileDownload=true; path=/");
		res.setHeader("Content-Disposition", "attachement; filename=\""+fileName+"\"");
	
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@RequestMapping(value = "/testExcelUpload1.ajax", method = RequestMethod.POST)
	public ModelAndView testExcelUpload1_ajax(MultipartHttpServletRequest request)  throws Exception{
		logger.debug("/testExcelUpload1.ajax");
		
		MultipartFile excelFile = request.getFile("excelFile");
		
		String webroot = Constants.getPATH(UPLOADS.ROOT);
		logger.debug("webroot="+webroot);
		String path = Constants.getPATH(UPLOADS.UPLOAD_ROOT);
		logger.debug("path="+path);
		String fileName = excelFile.getOriginalFilename();
		logger.debug("fileName="+fileName);
		logger.debug("fullpath="+path+fileName);
		
		if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
		
		File destFile = new File(path+fileName);
        try{
            excelFile.transferTo(destFile);
        } catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        
        if( excelFile != null ) {
        	exceluploadService.testUploadExcel(destFile);
        }
        
		ModelAndView view = new ModelAndView();
        view.setViewName("jsonView");
        return view;
	}
	
}
