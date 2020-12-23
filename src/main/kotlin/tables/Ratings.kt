package tables

import org.jetbrains.exposed.dao.id.IntIdTable


object Ratings: IntIdTable() {
    val ratings = float("ratings")
    val weight = float("weight")
    val name = varchar("name", 50)


    val course = reference("Course", Courses)
    val student = reference("Student", Students)

    override val primaryKey =
        PrimaryKey(id, name = "PK_RATING_ID")
}