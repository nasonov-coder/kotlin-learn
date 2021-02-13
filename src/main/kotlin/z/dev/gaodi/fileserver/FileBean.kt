package z.dev.gaodi.fileserver



data class FileBean(
    val jsonType: String = "file",
    val uuid: String,
    val user: UserBean,
    val tags: List<String>,
    val uploadRequestTimestamp: Long,
    val uploadTimestamp: Long? = null,
    val filename: String? = null,
    val hash: String? = null,
    val size: Long? = null,
    val fileMeta: Map<String, String>?,
) {
    fun makeKey() = "$jsonType@$uuid"
}