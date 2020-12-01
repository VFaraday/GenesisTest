package com.bobryshev.genesistest.data.repository.domain

import com.bobryshev.genesistest.data.db.enteties.GitRepoEntity
import com.google.gson.annotations.SerializedName

data class GitRepo(
    val id: String,
    @SerializedName("html_url")
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

        fun build() = GitRepo(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun GitRepo.toGitEntity(): GitRepoEntity {
    val gitRepo = this
    return GitRepoEntity.build {
        id = gitRepo.id
        htmlUrl = gitRepo.htmlUrl
        name = gitRepo.name
        description = gitRepo.description
    }
}