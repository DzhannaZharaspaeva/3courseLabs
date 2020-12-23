package tables

import org.jetbrains.exposed.dao.id.IntIdTable


object Students: IntIdTable()  {
    val name = varchar("name", length = 50)
    val group = varchar("group", length = 50)

    override val primaryKey =
        PrimaryKey(id, name = "PK_STUDENT_ID")
}