package bj.delivery.storeadmin.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    private static final String[] whiteList = { "text/event-stream" }; // sse 는 패스하도록

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("---->>>> init uri : {}", req.getRequestURI());

        if(PatternMatchUtils.simpleMatch(whiteList, req.getHeader("Accept"))){
            chain.doFilter(req, response);
        }
        else{
            chain.doFilter(req, res);
        }

        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();
        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);
            headerValues.append("[ ").append(headerKey).append(" : ").append(headerValue).append(" ] , ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info("---->>>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);

        // response 정보
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues.append("[ ").append(headerKey).append(" : ").append(headerValue).append(" ] , ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<---- uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);
        log.info("<<<<---- end uri");

        res.copyBodyToResponse(); // 반드시 있어야지 데이터 담긴 상태로 응답됨.
    }
}
