package com.example.svaan1.userservice.dto;

import com.example.svaan1.userservice.model.User;
import com.example.svaan1.userservice.repository.spec.UserSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryDTO {
    private String name;
    private String email;

    public Specification<User> buildSpecification() {
        Specification<User> specification = null;
        List<Specification<User>> specificationList = new ArrayList<>();

        if (this.name != null) {
            specificationList.add(UserSpecification.nameIs(this.name));
        }

        if (this.email != null) {
            specificationList.add(UserSpecification.emailIs(this.email));
        }

        for (int index = 0; index < specificationList.size(); index++) {
            if (index == 0) {
                specification = Specification.where(specificationList.get(index));
            } else {
                specification = specification.and(specificationList.get(index));
            }
        }

        return specification;
    }
}
