package services;

import dao.UsuarioDAO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateo
 */
public class LogIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            boolean estado = UsuarioDAO.validar(request.getParameter("username"), request.getParameter("password"));
            if (estado) {
                //out.print("YES AUTORIZED");
                String KEY = "123pormi";
                long time = System.currentTimeMillis();
                String jwt = Jwts.builder()
                        .signWith(SignatureAlgorithm.HS256, KEY)
                        .setSubject("User Data")
                        .setIssuedAt(new Date(time))
                        .setExpiration(new Date(time + 900000))
                        .claim("email", "email1@gmail.com")
                        .compact();
                out.print(jwt);
            } else {
                out.print("NOT AUTORIZED");
            }
        }

    }

}
