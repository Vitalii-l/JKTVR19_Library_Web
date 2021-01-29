/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import tools.EncryptPassword;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1,  urlPatterns = {
    "/listBooks",
    "/loginForm", 
    "/login",
    "/logout",
    "/registrationForm",
    "/registration",
})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB
    private ReaderFacade readerFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private BookFacade bookFacade;
    EncryptPassword encryptPassword = new EncryptPassword();

    public static final ResourceBundle pathToJsp = ResourceBundle.getBundle("property.pathTojsp");
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        if(userFacade.findAll().size() > 0) return;
        Reader reader = new Reader("Ivan", "Ivanov", "565456565");
        readerFacade.create(reader);
        String salt = encryptPassword.createSalt();
        String passwordHash = encryptPassword.createHash("12345", salt);
        User user = new User("admin", passwordHash, salt, reader);
        userFacade.create(user);
        
        Role role = new Role("ADMIN");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);
        
        role = new Role("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);
        
        role = new Role("READER");
        roleFacade.create(role);
        userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);
        
    }
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/listBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("listBooks")).forward(request, response);
                break;
            case "/loginForm":
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("login")).forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                String passwordHash = encryptPassword.createHash(password, user.getSalt());
                
                if(!passwordHash.equals(user.getPassword())){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли как " + user.getLogin());
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
                break;
            case "/logout":
                httpSession = request.getSession(false);
                if(httpSession != null){
                    httpSession.invalidate();
                    request.setAttribute("info", "Вы вышли!");
                }
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
                break;
            case "/registrationForm":
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("registration")).forward(request, response);
                break;
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if("".equals(firstname) || firstname == null
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/registrationForm").forward(request, response);
                    break;
                }
                Reader reader = new Reader(firstname, lastname, phone);
                readerFacade.create(reader);
                String salt = encryptPassword.createSalt();
                passwordHash = encryptPassword.createHash(password, salt);
                user = new User(login, passwordHash, salt, reader);
                userFacade.create(user);
                Role role = roleFacade.findByName("READER");
                UserRoles userRoles = new UserRoles(role, user);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", "Читатель \"" + reader.getFirstname() +" "+ reader.getLastname()+ "\" добавлен");
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
                break;        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
