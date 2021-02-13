package z.dev.gaodi.fileserver

/**
 * Интерфейс для взаимодействия с БД файлсервера
 *
 * Необходимо создать объект реализующий этот интерфейс
 *
 * ключ в базе в формате: "file@$uuid"
 */
interface IFileserverDB {
    /**
     * Создается первичная запись
     *
     * - uuid генерируется.
     *
     * - tags берем из запроса(пока заглушка).
     *
     * - user из equal(тоже пока заглушка).
     *
     * - делаем новую запись в БД:
     * {
     * "user": UserBean
     * "tags": List<String>
     * "uploadRequestTimestamp": Int
     * "uuid": String
     * }
     *
     */
    fun generateUploadUrl(tags: List<String>, user: UserBean): String

    /**
     * Выполняется поиск в БД и возвращается запись, если существует.
     *
     * В нашей задаче ВНЕ этой фунции должна проводится проверка:
     * существует ли запись и если да, то создана ли она <24 часов назад?
     */
    fun searchFile(uuid: String): FileBean?

    /**
     * Обновляется запись в БД после загрузки файла.
     *
     * Необходимо заполнить все свойства file:FileBean.
     *
     */
    fun updateFile(file: FileBean)

}