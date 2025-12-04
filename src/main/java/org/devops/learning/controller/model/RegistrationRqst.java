package org.devops.learning.controller.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRqst {

    private String name;
    private String email;
}
