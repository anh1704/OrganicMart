package vn.hoidanit.laptopshop.service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private final UserService userService;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        if (!user.getPassword().equals(user.getConfirmPassword())){
            context.buildConstraintViolationWithTemplate("Password nhap khong chinh xac")  // thong bao loi gi
                    .addPropertyNode("confirmPassword")// truong thong tin co loi
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // check email
        if (this.userService.checkEmailExist(user.getEmail())){
            context.buildConstraintViolationWithTemplate("Email da ton tai")  // thong bao loi gi
                    .addPropertyNode("email")// truong thong tin co loi
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
