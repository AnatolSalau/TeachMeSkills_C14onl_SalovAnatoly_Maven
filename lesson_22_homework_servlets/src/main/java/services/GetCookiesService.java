package services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCookiesService {
    public static Cookie[] getCookies(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        return cookies;
    }

    public static Map<String,String> getMapCookies (HttpServletRequest req) {
        Map<String,String> result = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            result = Arrays.stream(cookies)
                    .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        }
        return result;
    }
    public static void printOnPage (PrintWriter printWriter, Map<String, String> cookies) {
        printWriter.println("Cookies: ");
        if (cookies == null) {
            printWriter.println("null");
        } else {
            cookies.entrySet().stream()
                    .peek(entry -> {
                        printWriter.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                    });
        }
    }
}
