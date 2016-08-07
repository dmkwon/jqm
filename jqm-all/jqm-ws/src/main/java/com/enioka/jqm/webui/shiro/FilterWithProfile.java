package com.enioka.jqm.webui.shiro;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;

public class FilterWithProfile extends HttpMethodPermissionFilter
{
    @Override
    protected String[] buildPermissions(HttpServletRequest request, String[] configuredPerms, String action)
    {
        String[] path = request.getRequestURI().split("/");
        String profileId = null;
        for (int i = 0; i < path.length; i++)
        {
            String segment = path[i];
            if (segment.equals("profile") && i + 1 < path.length)
            {
                profileId = path[i + 1];
                break;
            }
        }
        if (profileId == null)
        {
            throw new InvalidParameterException("using profile filter on URL without profile ID - " + request.getContextPath());
        }

        String[] tmpPerms = new String[configuredPerms.length];
        for (int i = 0; i < configuredPerms.length; i++)
        {
            tmpPerms[i] = profileId + ":" + configuredPerms[i];
        }

        return buildPermissions(tmpPerms, action);
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException
    {
        String[] perms = (String[]) mappedValue;
        // append the http action to the end of the permissions and then back to super
        String action = getHttpMethodAction(request);
        String[] resolvedPerms = buildPermissions((HttpServletRequest) request, perms, action);
        return super.isAccessAllowed(request, response, resolvedPerms);
    }
}
