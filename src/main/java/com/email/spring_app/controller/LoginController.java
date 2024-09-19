package com.email.spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.email.spring_app.dto.HttpResponseDTO;
import com.email.spring_app.dto.LoginDTO;
import com.email.spring_app.models.controller.ILoginController;
import com.email.spring_app.repository.UserRepository;
import com.email.spring_app.security.CustomAuthenticationManager;
import com.email.spring_app.services.UserRepositoryService;
import com.email.spring_app.utils.HttpResponseUtil;

@Controller
@ResponseBody
public class LoginController implements ILoginController {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    /**
     * The function returns an unauthorized HTTP response for a login request.
     * 
     * @param loginDTO The `loginDTO` parameter is an object of type `LoginDTO` which typically
     * contains the user's login credentials such as username and password. In the given code snippet,
     * the `loginDTO` object is passed as a parameter to the `login` method, which returns a
     * `ResponseEntity`
     * @return A `ResponseEntity` containing an `HttpResponseDTO` object is being returned.
     */
    @Override
    public ResponseEntity<HttpResponseDTO> login(LoginDTO loginDTO) {
        Authentication usernamePasswordAuthenticationToken = getAuthentication(loginDTO);
        Authentication authentication = customAuthenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return HttpResponseUtil.ok(authentication);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final LoginDTO loginDTO) {
        return new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(),
            loginDTO.getPassword()
        );
    }

}
