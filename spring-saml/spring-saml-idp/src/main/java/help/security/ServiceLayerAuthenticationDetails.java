package help.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class ServiceLayerAuthenticationDetails extends WebAuthenticationDetails 
{
    private static final long serialVersionUID = 6975601077710753878L;
    protected final String company;

    public ServiceLayerAuthenticationDetails(HttpServletRequest request) 
    {
        super(request);
        company = request.getParameter("company");
    }

    public String getCompany() {
        return company;
    }
}