package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Courses: IntIdTable() {
    var name = varchar("name", length = 50)

    override val primaryKey =
            PrimaryKey(id, name = "PK_COURSE_ID")
}
