package tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.date

object Grades: IntIdTable()  {
    val value = integer("value")
    val date = date("date")

    val student = reference("Student", Students)
    val task = reference("Task", Tasks)

    override val primaryKey =
        PrimaryKey(id, name = "PK_GRADE_ID")
}