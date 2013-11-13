package application;

import javax.servlet.http.Cookie;

public class Util {
	public static boolean hasTokenCookie(Cookie[] cookies) {
		for (Cookie cookie: cookies) {
			if (cookie.getName().equals("token")) {
				return true;
			}
		}
		return false;
	}
}
