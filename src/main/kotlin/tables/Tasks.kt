package tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.date

object Tasks: IntIdTable()  {
    var name = varchar("name", length = 50)
    val shortName = varchar("shortName", length = 50)
    var description = varchar("description", length = 50)
    var maxValue = integer("maxValue")
    var deadline = date("deadline")

    var type = reference("Task", Types)
    var course = reference("Course", Courses)

    override val primaryKey =
        PrimaryKey(id, name = "PK_TASK_ID")
}