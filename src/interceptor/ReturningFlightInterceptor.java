package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by QQZhao on 3/4/17.
 */
public class ReturningFlightInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        Map<String, Object> map = actionInvocation.getInvocationContext().getSession();


        if(null == map.get("returningDate")){
            return "JumpToCart";
        }

        return actionInvocation.invoke();
    }

}