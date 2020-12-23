package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Tutors: IntIdTable()  {
    val name = varchar("name", length = 50)
    val post = varchar("post", length = 50)

    override val primaryKey =
        PrimaryKey(id, name = "PK_TUTOR_ID")
}