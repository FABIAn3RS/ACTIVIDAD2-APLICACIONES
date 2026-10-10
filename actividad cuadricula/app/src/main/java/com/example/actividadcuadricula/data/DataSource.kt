package com.example.actividadcuadricula.data

import com.example.actividadcuadricula.R
import com.example.actividadcuadricula.model.Topic

/**
 * Fuente de datos con la lista de temas del curso disponibles.
 */
object DataSource {
    val topics: List<Topic> = listOf(
        Topic(R.string.architecture, 58, R.drawable.ic_architecture),
        Topic(R.string.crafts, 121, R.drawable.ic_crafts),
        Topic(R.string.business, 78, R.drawable.ic_business),
        Topic(R.string.culinary, 118, R.drawable.ic_culinary),
        Topic(R.string.design, 423, R.drawable.ic_design),
        Topic(R.string.fashion, 92, R.drawable.ic_fashion),
        Topic(R.string.film, 165, R.drawable.ic_film),
        Topic(R.string.gaming, 164, R.drawable.ic_gaming),
        Topic(R.string.drawing, 326, R.drawable.ic_drawing),
        Topic(R.string.lifestyle, 305, R.drawable.ic_lifestyle),
        Topic(R.string.music, 212, R.drawable.ic_music),
        Topic(R.string.painting, 172, R.drawable.ic_painting),
        Topic(R.string.photography, 321, R.drawable.ic_photography),
        Topic(R.string.tech, 118, R.drawable.ic_tech),
        Topic(R.string.history, 85, R.drawable.ic_history),
        Topic(R.string.ecology, 64, R.drawable.ic_ecology)
    )
}
