package de.ait.ec.controllers.api;

import de.ait.ec.dto.StandardResponseDto;
import de.ait.ec.dto.UserDto;
import de.ait.ec.handler.RestException;
import de.ait.ec.security.details.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 8/16/2023
 * EducationCenter
 *
 * @author Marsel Sidikov (AIT TR)
 */
@RequestMapping("/api/users/")
@Tags(value =
        @Tag(name = "Users")
)
public interface UsersApi {

    @Operation(summary = "Получение профиля", description = "Доступно аутентифицированным пользователям. Позволяет получить текущего пользователя на основе сессии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Профиль пользователя",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Пользователь не аутентифицирован",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/me")
    ResponseEntity<UserDto> getMyProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);
}
