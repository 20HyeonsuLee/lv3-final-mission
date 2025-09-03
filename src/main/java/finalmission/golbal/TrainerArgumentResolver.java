package finalmission.golbal;

import finalmission.golbal.exception.AuthenticationException;
import finalmission.member.model.Customer;
import finalmission.member.model.Trainer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class TrainerArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(Trainer.class);
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new AuthenticationException("로그인이 필요합니다.");
        }
         Trainer trainer = (Trainer) session.getAttribute("LOGIN_MEMBER");
        if (trainer == null) {
            throw new AuthenticationException("로그인이 필요합니다.");
        }
        return trainer;
    }
}
