package appbackend.back.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    private final RequestMetricsService requestMetricsService;

    @Autowired
    public RequestInterceptor(RequestMetricsService requestMetricsService) {
        this.requestMetricsService = requestMetricsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Ghi lại thời điểm bắt đầu xử lý yêu cầu
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Xác định xem yêu cầu có thành công hay không và ghi lại thông tin
        boolean isSuccess = (response.getStatus() >= 200 && response.getStatus() < 300);
        requestMetricsService.processRequest(isSuccess);
        // Đo thời gian phản hồi của yêu cầu và cập nhật metrics
        long startTime = (Long) request.getAttribute("startTime");
        requestMetricsService.recordRequestTime(startTime);
    }
}

