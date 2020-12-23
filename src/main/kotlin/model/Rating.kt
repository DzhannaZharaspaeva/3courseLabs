package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.Ratings

class Rating(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Rating>(Ratings)

    var ratings by Ratings.ratings
    var weights by Ratings.weight
    var course by Course referencedOn Ratings.course
    var student by Student referencedOn Ratings.student


}