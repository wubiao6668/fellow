package com.fellow.common.util;

import com.fellow.common.emotion.EmoticonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by wubiao on 2016/11/23.
 */
public class VelocityUtil {

    public static String mergeTemplate(Context context, VelocityEngine velocityEngine, String template) {
        Writer writer = new StringWriter();
        velocityEngine.mergeTemplate(template, "UTF-8", context, writer);
        return writer.toString();
    }

    public static String mergeTextTemplate(Context context, VelocityEngine velocityEngine, String template) {
        if (null == velocityEngine) {
            velocityEngine = new VelocityEngine();
        }
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, "", template);
        return writer.toString();
    }

    public static String mergeEmoticon(VelocityEngine velocityEngine, String template) {
        if (null == velocityEngine) {
            velocityEngine = new VelocityEngine();
        }
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(EmoticonUtil.context, writer, "", template);
        return writer.toString();
    }

    public static String mergeEmoticon(String template) {
        return mergeEmoticon(null, template);
    }
}
