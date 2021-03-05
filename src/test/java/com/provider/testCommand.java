package com.provider;

import com.provider.controller.Servlet;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class testCommand {
//    public final static String path = "/internet_provider/index";
//
//    @Test
//    public void whenCallDoGetThenReturnIndexPage() throws ServletException, IOException {
//        final Servlet servlet = new Servlet();
//
//        final HttpServletRequest request = mock(HttpServletRequest.class);
//        final HttpServletResponse response = mock(HttpServletResponse.class);
//        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//
//        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
//
//        servlet.doGet(request, response);
//
//        verify(request, times(1)).getRequestDispatcher(path);
//        verify(request, never()).getSession();
//        verify(dispatcher).forward(request, response);
//
//    }
}
