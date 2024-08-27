package org.metrodataacademy.ClientApp.services;

import org.metrodataacademy.ClientApp.models.dtos.request.ChangePasswordRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateUserRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getUser();

    ProfileResponse updateUser(UpdateUserRequest userRequest);

    ProfileResponse changePassword(ChangePasswordRequest passwordRequest);
}