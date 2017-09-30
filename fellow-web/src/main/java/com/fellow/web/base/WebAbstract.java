package com.fellow.web.base;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.db.able.IncrementAble;
import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.session.LocalSession;
import com.fellow.domain.web.Response;
import com.fellow.service.EmailService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/8/30.
 */
public abstract class WebAbstract<S> {
    public Log log = LogFactory.getLog(this.getClass());
    //模块名称
    protected String module;
    @Autowired
    public S service;
    @Resource
    protected VelocityConfig velocityConfig;
    @Resource
    protected EmailService emailService;


    @ModelAttribute
    public void constant(Model model) {
        model.addAttribute("COMMENT_GROUP_NUM", SystemConstant.COMMENT_GROUP_NUM);
        model.addAttribute("lostPostType", PostEnum.LOST_POST.getKey());
        model.addAttribute("lostPostType", PostEnum.LOST_POST.getKey());
        model.addAttribute("account", getAccount());
        model.addAttribute("username", getUserName());
        model.addAttribute("user",LocalSession.getUserInfo() );
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yy-MM-dd"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy"), true));
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
    }

    /**
     * 获取模块名称
     *
     * @return 模块
     */
    protected String getModule() {
        if (module == null) {
            module = (getClass().getAnnotation(RequestMapping.class)).value()[0];
        }
        return module;
    }

    @RequestMapping(value = {"", "/testIndex"})
    public String baseIndex() {
        String viewPath = getModule();
        return "index";
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
                imgMap.put(SystemConstant.IMAGE_UPLOAD_ATTR_ROOT_NAME, SystemConstant.IMAGE_UPLOAD_PROTOCOL + SystemConstant.IMAGE_UPLOAD_ATTR_ROOT_URL);
                imgMap.put(SystemConstant.IMAGE_UPLOAD_ATTR_RELATIVE_NAME,"/" + SystemConstant.IMAGE_UPLOAD_DIR +"/" + imageMonthDirectory + "/" + imageName + "." + imageExtend);
                response.setObject(imgMap);
                response.setSuccess(true);
            } catch (Exception e) {
                response.setMessage("不支持的图片类型");
                log.error("uploadImge--图片上传异常--e=", e);
            }
        }
        return response;
    }


    /**
     * 赞
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/upIncrement", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response upIncrement(Long id) {
        Response response = Response.newResponse();
        ((IncrementAble) service).upIncrement(id);
        response.setSuccess(true);
        return response;
    }

    /**
     * 踩
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/downIncrement", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response downIncrement(Long id) {
        Response response = Response.newResponse();
        ((IncrementAble) service).downIncrement(id);
        response.setSuccess(true);
        return response;
    }

    /**
     * 爱心
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/loveIncrement", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response loveIncrement(Long id) {
        Response response = Response.newResponse();
        ((IncrementAble) service).loveIncrement(id);
        response.setSuccess(true);
        return response;
    }


    public String getAccount() {
        return LocalSession.getSession().getUserAccount();
    }

    public String getUserName() {
        return LocalSession.getSession().getUserName();
    }
}
