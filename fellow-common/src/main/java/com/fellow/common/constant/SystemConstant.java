package com.fellow.common.constant;

/**
 * Created by wubiao on 2016/8/31.
 */
public class SystemConstant {

    public static final String TOKEN = "token";
    public static final int COOKIES_EXPIRY = 60 * 60 * 3 * 1000;
    public static final String DESC_CODER_KEY = "vCWn2uWDJj0=";

    public static final int IMAGE_MAX_HEIGHT = 4000;
    public static final int IMAGE_MAX_WIDTH = 4000;
    public static final long IMAGE_MAX_SIZE = 1024 * 6;

    public static final int IMAGE_UPLOAD_MAX_NUM = 8;

    public static final String IMAGE_ROOT_PATH = System.getProperty("root.path") + SystemConstant.IMAGE_UPLOAD_DIR;

    public static final String IMAGE_UPLOAD_PREFIX = "/image/";
    public static final String IMAGE_UPLOAD_RELATIVE_PATH = "image";
    public static final String EMOTICON_PREFIX = "/plugins/emoji/";

    public static final String IMAGE_UPLOAD_ATTR_ROOT_NAME = "root";
    public static final String IMAGE_UPLOAD_ATTR_RELATIVE_NAME = "relative";

    public static final String IMAGE_UPLOAD_ATTR_ROOT_URL = "localhost:8020";

    public static final String IMAGE_UPLOAD_DIR = "uploadImage";

    public static final String IMAGE_UPLOAD_PROTOCOL = "http://";

    public static final String IMAGE_UPLOAD_USER_SELECTOR = "img[src~=.*" + IMAGE_UPLOAD_ATTR_ROOT_URL + "/" + SystemConstant.IMAGE_UPLOAD_DIR + ".*]";

    public static final int POST_BRIEF_TEXT_LENGTH = 120;
    public static final int POST_BRIEF_INCLUDE_IMAGE_LENGTH = 200;

    public static final String IMG_ROOT_PATH_LOCALHOST = "/";

    public static final int page = 1;
    public static final int limit = 10;

    public static final int DEFAULT_SLIDERS_COUNT = 5;

    public static final int COMMENT_GROUP_NUM = 3;

    public static final String EMOTICON_PATH = "/plugins/emoji/emoji/";

    public static final String CONNECT_SYMBOL = "_";

    public static final String SYSTEM_MESSAGE_TITLE_FRIEND_FROM = "对方请求添加您为好友";
    public static final String SYSTEM_MESSAGE_TITLE_FRIEND_TO = "我请求添加对方为好友";
    public static final String SYSTEM_MESSAGE_TITLE_NOTICE_FROM = "给您发送消息";

    public static final int DEFAULT_PAGESIZE = 10;

    public static final String CAPTCHA_NAME = "captcha";

    public static final int FORGET_PASSWORD_TEIM = 5;

    public static final String DEFAULT_USER_AVATAR = "/static/assets/avatars/avatar5.png";

    public static final String REGISTER_CAPTCHA = "register_captcha";

}
