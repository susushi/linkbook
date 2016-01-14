package priv.susu.linkbook.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import priv.susu.linkbook.web.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UploadController extends AbstractController {

	@RequestMapping("/upload")
//	@ResponseBody
	public void iconUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse res) {
		Response<String> response = new Response<String>();
		try {
			HttpSession session = request.getSession();
			long uid = getCurrentUid(request);
			if (uid == -1) {
				response.setCode(403);
				response.setMessage("请登录");
			} else {
				String path = session.getServletContext().getRealPath("resources/timeline/" + uid + "/");
				// 获得文件的后缀
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String fileName = System.currentTimeMillis() + "." + extension;
				FileUtils.writeByteArrayToFile(new File(path, fileName), file.getBytes());
				response.setData("resources/timeline/" + uid + "/" + fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(500);
			response.setMessage("内部服务器错误！");
		}
		ObjectMapper objMapper = new ObjectMapper();
		Writer writer;
		try {
			writer = res.getWriter();
			writer.write(objMapper.writeValueAsString(response));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
