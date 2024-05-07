package zong.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Getter
@Setter
public class Role   implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = -8478114427891717226L;

    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
