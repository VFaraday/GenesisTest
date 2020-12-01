package com.bobryshev.genesistest.data.db.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bobryshev.genesistest.data.repository.domain.GitRepo

@Entity
data class GitRepoEntity(
    @PrimaryKey
    val id: String,
    val htmlUrl: String?,
    val name: String?,
    val description: String?,
) {

    private constructor(builder: Builder): this(builder.id, builder.htmlUrl, builder.name, builder.description)

    class Builder {
        var id: String = ""
        var htmlUrl: String? = null
        var name: String? = null
        var description: String? = null

        fun build() = GitRepoEntity(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun GitRepoEntity.toGitRepo(): GitRepo {
    val entity = this
    return GitRepo.build {
        id = entity.id
        htmlUrl = entity.htmlUrl
        name = entity.name
        description = entity.description
    }
}
