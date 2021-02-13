package z.dev.gaodi.fileserver

object Database: IFileserverDB {
    override fun generateUploadUrl(tags: List<String>, user: UserBean): String {
        TODO("Not yet implemented")
    }

    override fun searchFile(uuid: String): FileBean? {
        TODO("Not yet implemented")
    }

    override fun updateFile(file: FileBean) {
        TODO("Not yet implemented")
    }
}