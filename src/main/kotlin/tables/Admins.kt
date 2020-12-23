package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Admins : IntIdTable() {
    val name = varchar("name", length = 50)

    override val primaryKey =
        PrimaryKey(id, name = "PK_ADMIN_ID")
}