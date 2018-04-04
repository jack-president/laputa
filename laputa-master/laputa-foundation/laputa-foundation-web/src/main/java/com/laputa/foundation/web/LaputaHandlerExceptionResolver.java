package com.laputa.foundation.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.laputa.foundation.common.exception.LaputaBaseException;
import com.laputa.foundation.common.json.GsonUtil;
import com.laputa.foundation.logging.mdc.MdcContext;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangDongPing on 2016/11/9.
 */
@Order(-1000)
public class LaputaHandlerExceptionResolver implements HandlerExceptionResolver {

    private final static ModelAndView nullModelAndView = new ModelAndView();

    /**
     * Try to resolve the given exception that got thrown during on handler execution,
     * returning a ModelAndView that represents a specific error page if appropriate.
     * <p>The returned ModelAndView may be {@linkplain ModelAndView#isEmpty() empty}
     * to indicate that the exception has been resolved successfully but that no view
     * should be rendered, for instance by setting a status code.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or {@code null} if none chosen at the
     *                 time of the exception (for example, if multipart resolution failed)
     * @param ex       the exception that got thrown during handler execution
     * @return a corresponding ModelAndView to forward to,
     * or {@code null} for default processing
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        if (ex instanceof LaputaBaseException) {
            try {
                LaputaBaseException laputaBaseException = (LaputaBaseException) ex;
                response.setStatus(501);
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                Map<String, Object> result = new HashMap<>();
                result.put("transid", MdcContext.getTransId());
                result.put("code", laputaBaseException.getCode());
                result.put("message", laputaBaseException.getMessage());
                response.getWriter().write(GsonUtil.toJsonString(result));
                return nullModelAndView;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
