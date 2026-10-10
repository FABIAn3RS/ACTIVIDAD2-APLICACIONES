package com.example.actividadcuadricula.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Clase de datos que representa un tema del curso.
 *
 * @property name Identificador del recurso de texto del nombre del tema.
 * @property availableCourses Cantidad de cursos asociados a este tema.
 * @property imageRes Identificador del recurso de imagen para este tema.
 */
data class Topic(
    @param:StringRes val name: Int,
    val availableCourses: Int,
    @param:DrawableRes val imageRes: Int
)
