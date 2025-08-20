package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
                @NotBlank(message = "El login es obligatorio") String login,

                @NotBlank(message = "La contraseña es obligatoria") String clave) {
}