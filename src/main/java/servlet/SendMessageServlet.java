package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by misha on 10.06.17.
 */

@WebServlet(name="sendMessage", urlPatterns="/sendMessage")
public class SendMessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();

        String photo = user.getPath_to_photo();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String city = user.getCity();
        String country = user.getCountry();
        try {
            String musicSize = userDao.getMusicsSize(user);
            if(musicSize == null || musicSize.isEmpty()){
                request.setAttribute("count", "0");
            }else {
                request.setAttribute("count", musicSize);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("count", "0");
        }

        if (photo == null) {
            request.setAttribute("photo", "photo/default.jpg");
        } else {
            request.setAttribute("photo", photo);
        }

        if(firstName == null || firstName.isEmpty()){
            request.setAttribute("name",
                    "no information");
        }else {
            request.setAttribute("name",
                    firstName);
        }

        if(lastName == null || lastName.isEmpty()){
            request.setAttribute("lastName",
                    "no information");
        }else{
            request.setAttribute("lastName",
                    lastName);
        }

        if(country == null || country.isEmpty()){
            request.setAttribute("country",
                    "no information");
        }else {
            request.setAttribute("country",
                    country);
        }

        if(city == null || city.isEmpty()){
            request.setAttribute("city",
                    "no information");
        }else{
            request.setAttribute("city",
                    city);
        }

        getServletContext().getRequestDispatcher("/sendMessage.jsp").forward(
                request, response);
    }
}
