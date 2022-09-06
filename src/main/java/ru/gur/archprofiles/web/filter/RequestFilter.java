package ru.gur.archprofiles.web.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final RequestScopedDataProvider requestScopedDataProvider;
    private final SessionScopedDataProvider sessionScopedDataProvider;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        sessionScopedDataProvider.setValue(sessionScopedDataProvider.getValue() + 1);
        requestScopedDataProvider.setValue(requestScopedDataProvider.getValue() + 1);
        System.out.println("!fs " + sessionScopedDataProvider.getValue());
        System.out.println("!fr " + requestScopedDataProvider.getValue());

        filterChain.doFilter(request, response);
    }
}
