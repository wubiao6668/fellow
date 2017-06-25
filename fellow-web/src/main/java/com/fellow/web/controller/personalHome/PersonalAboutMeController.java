package com.fellow.web.controller.personalHome;

import com.alibaba.fastjson.JSON;
import com.fellow.common.constant.SystemConstant;
import com.fellow.domain.model.PhotoImage;
import com.fellow.domain.model.UserDetail;
import com.fellow.domain.model.UserSkill;
import com.fellow.domain.query.UserDetailQuery;
import com.fellow.domain.query.UserSkillQuery;
import com.fellow.domain.web.array.UserSkillArray;
import com.fellow.domain.web.Response;
import com.fellow.service.PhotoImageService;
import com.fellow.service.UserDetailService;
import com.fellow.service.UserSkillService;
import com.fellow.service.cache.SkillCacheService;
import com.fellow.web.base.WebAbstract;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wubiao on 2016/10/30.
 */
@Controller
@RequestMapping(PersonalAboutMeController.VIEW_PATH)
public class PersonalAboutMeController extends WebAbstract<UserDetailService> {
    public static final String VIEW_PATH = "/personal/home/aboutMe";

    @Resource
    private UserSkillService userSkillService;
    @Resource
    private UserDetailService userDetailService;
    @Resource
    private SkillCacheService skillCacheService;
    @Resource
    private PhotoImageService photoImageService;

    @ModelAttribute

    @RequestMapping("/index")
    public String index(Model model) {
        UserDetailQuery userDetailQuery = new UserDetailQuery();
        userDetailQuery.setAccount(super.getAccount());
        UserDetail userDetail = service.selectAboutMe(userDetailQuery);
        List<PhotoImage> photoImageList = photoImageService.selectPersonalImages(super.getAccount());
        UserSkillQuery userSkillQuery = new UserSkillQuery();
        userSkillQuery.setAccount(super.getAccount());
        List<UserSkill> userSkillList = userSkillService.selectPersonalSkill(userSkillQuery);
        if (CollectionUtils.isNotEmpty(userSkillList)) {
            for (UserSkill userSkillTemp : userSkillList) {
                userSkillTemp.setSkillName(skillCacheService.getSkillNameById(userSkillTemp.getSkillId()));
                if (StringUtils.isNotBlank(userSkillTemp.getSkillPath())) {
                    userSkillTemp.setSkillFirst(userSkillTemp.getSkillPath().split(SystemConstant.CONNECT_SYMBOL)[0]);
                }
            }
        }
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("photoImageList", photoImageList);
        model.addAttribute("userSkillList", userSkillList);
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/updateAboutMe", method = RequestMethod.POST)
    @ResponseBody
    public Response updateAboutMe(UserDetail userDetail) {
        Response response = Response.newResponse();
        userDetail.setAccount(super.getAccount());
        userDetail.setUpdateAccount(super.getAccount());
        userDetail.setUpdateName(super.getUserName());
        userDetailService.updateAboutMe(userDetail);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/addSkill")
    @ResponseBody
    public Response addSkill(UserSkill userSkill) {
        Response response = Response.newResponse();
        userSkill.setAccount(super.getAccount());
        userSkill.setCreateAccount(super.getAccount());
        userSkill.setCreateName(super.getUserName());
        if (StringUtils.isNotBlank(userSkill.getSkillFirst())) {
            userSkill.setSkillPath(userSkill.getSkillFirst() + SystemConstant.CONNECT_SYMBOL);
        }
        userSkillService.insertSelective(userSkill);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/updateSkill", method = RequestMethod.POST)
    @ResponseBody
    public Response updateSkill(UserSkillArray userSkillArray) {
        Response response = Response.newResponse();
        UserSkill[] userSkills = userSkillArray.getUserSkills();
        List<Long> idList = new ArrayList<Long>();
        if (ArrayUtils.isNotEmpty(userSkills)) {
            for (UserSkill userSkill : userSkills) {
                idList.add(userSkill.getId());
                userSkill.setAccount(super.getAccount());
                userSkill.setUpdateAccount(super.getAccount());
                userSkill.setUpdateName(super.getUserName());
            }
        }
        userSkillService.updateAfterDeletePersonalSkills(userSkills, idList);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/uploadImge", method = RequestMethod.POST)
    @ResponseBody
    public Response uploadImge(MultipartFile file, HttpServletResponse servletResponse) {
        Response response = Response.newResponse();
        if (null != file && !file.isEmpty()) {
            try {
                BufferedImage bufreader = ImageIO.read(file.getInputStream());
                int width = bufreader.getWidth();
                int height = bufreader.getHeight();
                if (width <= 0 || height <= 0) {
                    response.setMessage("不支持的图片类型");
                    return response;
                }

                if (width > SystemConstant.IMAGE_MAX_WIDTH) {
                    width = SystemConstant.IMAGE_MAX_WIDTH;
                }
                if (height > SystemConstant.IMAGE_MAX_HEIGHT) {
                    height = SystemConstant.IMAGE_MAX_HEIGHT;
                }
                long size = file.getSize();
                double scale = 1;
                if (size > SystemConstant.IMAGE_MAX_SIZE) {
                    scale = BigDecimal.valueOf(SystemConstant.IMAGE_MAX_SIZE).divide(BigDecimal.valueOf(size), 2, BigDecimal.ROUND_DOWN).doubleValue();
                }

                String imageName = System.nanoTime() + RandomStringUtils.randomAlphabetic(4);
                String imageMonthDirectory = DateFormatUtils.format(new Date(), "yyyyMM");
                String absoultName = SystemConstant.IMAGE_ROOT_PATH + File.separator + imageMonthDirectory + File.separator + imageName;
                File parentDirectory = new File(SystemConstant.IMAGE_ROOT_PATH + File.separator + imageMonthDirectory);
                if (!parentDirectory.exists()) {
                    parentDirectory.mkdirs();
                }
                String imageExtend = "jpg";
                Thumbnails.of(file.getInputStream())
                        .outputQuality(scale)
                        .forceSize(width, height)
                        .outputFormat(imageExtend)
                        .toFile(absoultName);
                Map<String, String> imgMap = new HashMap<String, String>();
                String rootPath = "/";
                String relationPath = "image/" + imageMonthDirectory + "/" + imageName + "." + imageExtend;
                imgMap.put(SystemConstant.IMAGE_UPLOAD_ATTR_ROOT_NAME, rootPath);
                imgMap.put(SystemConstant.IMAGE_UPLOAD_ATTR_RELATIVE_NAME, relationPath);
                PhotoImage photoImage = new PhotoImage();
                photoImage.setAccount(super.getAccount());
                photoImage.setImgRootPath(rootPath);
                photoImage.setImgRelativePath(relationPath);
                photoImage.setImgName(file.getName());
                photoImage.setCreateAccount(super.getAccount());
                photoImage.setCreateName(super.getUserName());
                photoImageService.insertSelective(photoImage);
                imgMap.put("id",photoImage.getId().toString());
                response.setObject(JSON.toJSONString(imgMap));
                response.setSuccess(true);
            } catch (Exception e) {
                response.setMessage("不支持的图片类型");
                log.error("uploadImge--图片上传异常--e=", e);
            }
        }
        return response;
    }

    @RequestMapping("/deletePersonalPhoto")
    @ResponseBody
    public Response deletePersonalPhoto(Long id) {
        Response response = Response.newResponse();
        PhotoImage photoImage = new PhotoImage();
        photoImage.setAccount(super.getAccount());
        photoImage.setId(id);
        photoImage.setUpdateAccount(super.getAccount());
        photoImage.setUpdateName(super.getUserName());
        photoImageService.deletePersonalImage(photoImage);
        response.setSuccess(true);
        return response;
    }
}
