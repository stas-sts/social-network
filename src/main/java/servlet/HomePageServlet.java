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
 * Created by misha on 07.06.17.
 */

@WebServlet(name="homePage", urlPatterns="/homePage")
public class HomePageServlet extends HttpServlet {

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

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String city = user.getCity();
        String country = user.getCountry();
        String photo = user.getPath_to_photo();

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

        try{
            String countOfMusic = userDao.getCountOfUserSongs(user);
            if(countOfMusic == null || countOfMusic.isEmpty()){
                request.setAttribute("countOfMusic", "0");
            }else {
                request.setAttribute("countOfMusic", countOfMusic);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("countOfMusic", "0");
        }

        try{
            String countOfRegisteredUsers = userDao.getCountOfRegisteredUsers();
            if(countOfRegisteredUsers == null || countOfRegisteredUsers.isEmpty()){
                request.setAttribute("countOfRegisteredUsers", "0");
            }else {
                request.setAttribute("countOfRegisteredUsers", countOfRegisteredUsers);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("countOfRegisteredUsers", "0");
        }

        if(photo == null){
            request.setAttribute("photo", "photo/default.jpg");
        }else {
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

        getServletContext().getRequestDispatcher("/home.jsp").forward(
                request, response);

    }
}
