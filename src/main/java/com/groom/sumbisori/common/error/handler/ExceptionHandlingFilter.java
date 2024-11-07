package com.groom.sumbisori.common.error.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.ErrorResponse;
import com.groom.sumbisori.common.error.GlobalErrorCode;
import com.groom.sumbisori.common.error.GlobalException;
import com.groom.sumbisori.common.error.exception.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    private static final String LOG_FORMAT = "\nException Class = {}\nResponse Code = {}\nMessage = {}";

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            handleErrorResponse(e, response, e.getErrorCode());
        } catch (GlobalException e) {
            handleErrorResponse(e, response, e.getErrorCode());
        } catch (Exception e) {
            log.error("Unexpected error occurred while processing request: {}", e.getMessage(), e);
            handleErrorResponse(e, response, GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void handleErrorResponse(Exception e, HttpServletResponse response, ErrorCode errorCode)
            throws IOException {
        logException(e, errorCode);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());
        ErrorResponse errorResponse = ErrorResponse.from(errorCode);
        String apiResponseJson = objectMapper.writeValueAsString(errorResponse);
        PrintWriter writer = response.getWriter();
        writer.print(apiResponseJson);
        writer.flush();
    }

    private void logException(final Exception e, final ErrorCode errorCode) {
        log.error(LOG_FORMAT, e.getClass(), errorCode.getHttpStatus().value(), errorCode.getMessage());
    }
}
