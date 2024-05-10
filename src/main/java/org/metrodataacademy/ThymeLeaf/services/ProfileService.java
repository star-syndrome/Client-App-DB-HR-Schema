package org.metrodataacademy.Thymeleaf.services;

import org.metrodataacademy.Thymeleaf.models.dtos.request.ChangePasswordRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateUserRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getUser();

    ProfileResponse updateUser(UpdateUserRequest userRequest);

    ProfileResponse changePassword(ChangePasswordRequest passwordRequest);
}