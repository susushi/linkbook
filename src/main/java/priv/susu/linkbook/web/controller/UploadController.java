package priv.susu.linkbook.web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import priv.susu.linkbook.web.Response;

@Controller
public class UploadController extends AbstractController {

	@RequestMapping("/upload")
	@ResponseBody
	public Response<String> iconUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
		Response<String> response = new Response<String>();
		try {
			HttpSession session = request.getSession();
			long uid = getCurrentUid(request);
			if (uid == -1) {
				response.setCode(403);
				response.setMessage("请登录");
				return response;
			}
			String path = session.getServletContext().getRealPath("resources/timeline/" + uid + "/");
			// 获得文件的后缀
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String fileName = System.currentTimeMillis() + "." + extension;
			FileUtils.writeByteArrayToFile(new File(path, fileName), file.getBytes());
			response.setData("timeline/" + uid + "/" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(500);
			response.setMessage("内部服务器错误！");
		}
		return response;
	}

}
