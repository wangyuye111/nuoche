package com.nuoche.redirect.resolverB.interface1.mA;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.coobird.thumbnailator.Thumbnails;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.nuoche.classroot.interface1.NuocheInOutFace;
import com.nuoche.classroot.interface4.JsonUtil;
import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;
@WebServlet("/uiface/JyServletInOut")
public class JyServletInOut extends HttpServlet {

	

	public JyServletInOut() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String[] arg = null;
		NuocheInOutFace inOutFace;
		JyLogDetect log = new JyLogDetect(request);
		
		

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		arg = JsonUtil.jsonReceive(request);
		for(int i = 0;i<arg.length;i++) {
			System.out.println(arg[i]);
		}
		if(arg[1].equals("ArticleAdd")||arg[1].equals("ArticleMove")){
			//arg[9]=request.getParameter("descs");
			//log.send(DataType.specialType, "01115", "头信息:   ", request.getParameter("descs"));
			
			
			log.send(DataType.specialType, "01115", "头信息:   ", arg);
			
			try {
				arg=upfile(arg,request,response);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		log.send(DataType.specialType, "01115", "头信息:   ", arg);
		
		
		
		try {
			switch (arg[0]) {
		
			case "A-boss-add":
				inOutFace = new JyInOutBoss(arg, request, response);
				inOutFace.addface();
				break;
			case "A-boss-delete":
				inOutFace = new JyInOutBoss(arg, request, response);
				inOutFace.deleteface();
				break;
			case "A-boss-mod":
				inOutFace = new JyInOutBoss(arg, request, response);
				inOutFace.modface();
				break;
			case "A-boss-search":
				inOutFace = new JyInOutBoss(arg, request, response);
				inOutFace.searchface();
				break;
			
			case "A-user-add":
				inOutFace = new JyInOutUser(arg, request, response);
				inOutFace.addface();
				break;
			case "A-user-delete":
				inOutFace = new JyInOutUser(arg, request, response);
				inOutFace.deleteface();
				break;
			case "A-user-mod":
				inOutFace = new JyInOutUser(arg, request, response);
				inOutFace.modface();
				break;
			case "A-user-search":
				inOutFace = new JyInOutUser(arg, request, response);
				inOutFace.searchface();
				break;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.send(DataType.exceptionType, "01107", "Exception: ", e);
		}
	}

	// 含有图片内容提交
		public String[] upfile(String[] arg,HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException, SmartUploadException {
			JyLogDetect log = new JyLogDetect(request);
			int argSize = 0;
			String path = "img/article/";
			String loadpath = this.getServletConfig().getServletContext().getRealPath("/");
			String fullPath = loadpath + path;
			File fullDir = new File(fullPath);
			if (!fullDir.exists()) {
				fullDir.mkdirs();
			}

			SmartUpload smartUpload = new SmartUpload();
			long maxSize = 10 * 1024 * 1024;//
			String allowFileExtList = "jar,exe,doc,docx,txt,html,xml,xls,pdf,jpg,png,PNG,gif,GIF,jpeg,JPEG,JPG,BMP,bmp";
			smartUpload.initialize(this.getServletConfig(), request, response);
			smartUpload.setMaxFileSize(maxSize);
			smartUpload.setAllowedFilesList(allowFileExtList);
			smartUpload.upload();
			
			
			switch (arg[1]) {
			case "ArticleAdd": {
				String[] goodinfo = new String[6];

				goodinfo[0] = arg[0];
				goodinfo[1] = arg[1];
				goodinfo[2] = smartUpload.getRequest().getParameter("articlename");
				goodinfo[3] = smartUpload.getRequest().getParameter("imgname");
				goodinfo[4] = smartUpload.getRequest().getParameter("articletype");
				goodinfo[5] = smartUpload.getRequest().getParameter("descs");
				/*arttype
				
				descs
				
				vidname
				goodinfo[7] = "";
				for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {
					com.jspsmart.upload.File smartFile = smartUpload.getFiles()
							.getFile(i);
					if (!smartFile.isMissing()) {
						String fileName = smartFile.getFileName();
						String type2 = fileName
								.substring(fileName.lastIndexOf("."));
						Random rnd = new Random();
						int r = rnd.nextInt(100);
						Date date2 = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyyMMddHHmmss");
						String strDate2 = formatter.format(date2);
						fileName = strDate2 + r + type2;
						smartFile.saveAs(fullPath + "//" + fileName,
								com.jspsmart.upload.File.SAVEAS_PHYSICAL);
	
						// names[i] = path + fileName;
						// thumbimage.thumbnailImage(fullPath + fileName, 300, 240);
	
						File imgFile = new File(fullPath + "/" + fileName);
						Image img = ImageIO.read(imgFile);
						int width = img.getWidth(null);
						int height = img.getHeight(null);
						Thumbnails
								.of(fullPath + "/" + fileName)
								.size(width, height)
								.outputQuality(0.9f)
								.outputFormat("jpg")
								.toFile(fullPath
										+ "/thumb_"
										+ fileName.substring(0,
												fileName.lastIndexOf(".")));
	
						goodinfo[7 + i] = path + "thumb_"
								+ fileName.substring(0, fileName.lastIndexOf("."))
								+ ".jpg";
					}
	
				}*/
				return goodinfo;
			}
			
			default :{
					String[] goodinfo = new String[7];
					goodinfo[0] = arg[0];
					goodinfo[1] = arg[1];
					goodinfo[2] = smartUpload.getRequest().getParameter("articlename");
					goodinfo[3] = smartUpload.getRequest().getParameter("imgname");
					goodinfo[4] = smartUpload.getRequest().getParameter("articletype");
					goodinfo[5] = smartUpload.getRequest().getParameter("descs");
					goodinfo[6] = smartUpload.getRequest().getParameter("id");
					return goodinfo;
				}
				
			}
		}

		
		public boolean JudgeIsMoblie(HttpServletRequest request) {  
	        boolean isMoblie = false;  
	        String[] mobileAgents = { "iphone", "android", "phone", "mobile",  
	                "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",  
	                "windows ce", "symbian", "series", "webos", "sony",  
	                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",  
	                "pieplus", "meizu", "midp", "cldc", "motorola", "foma",  
	                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",  
	                "huawei", "novarra", "coolpad", "webos", "techfaith",  
	                "palmsource", "alcatel", "amoi", "ktouch", "nexian",  
	                "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",  
	                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",  
	                "pantech", "gionee", "portalmmm", "jig browser", "hiptop",  
	                "benq", "haier", "^lct", "320x320", "240x320", "176x220",  
	                "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",  
	                "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",  
	                "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",  
	                "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",  
	                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",  
	                "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",  
	                "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",  
	                "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",  
	                "sony", "sph-", "symb", "t-mo", "teli", "tim-", /*"tosh",*/ "tsm-",  
	                "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",  
	                "wapr", "webc", "winw", "winw", "xda", "xda-",  
	                "Googlebot-Mobile" };  
	        if (request.getHeader("User-Agent") != null) {  
	            for (String mobileAgent : mobileAgents) {  

	                if (request.getHeader("User-Agent").toLowerCase()  
	                        .indexOf(mobileAgent) >= 0) {  
	                    isMoblie = true;  
	                    break;  
	                }  
	            }  
	        }  
	        return isMoblie;  
	    } 
		
}
