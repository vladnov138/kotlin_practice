import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun parseCsvData(filePath: String): MutableList<AppInfoCsv>? {
    val csvMapper = CsvMapper().apply {
        registerKotlinModule()
    }
    // Вручную задаю, т.к. Jackson по каким-то причинам неправильно парсит схему
    val schema = CsvSchema.builder()
        .addColumn("name")
        .addColumn("category")
        .addColumn("rating")
        .addColumn("reviews")
        .addColumn("size")
        .addColumn("installs")
        .addColumn("type")
        .addColumn("price")
        .addColumn("contentRating")
        .addArrayColumn("genres") // Учитывая, что genres это список
        .addColumn("lastUpdated")
        .addColumn("currentVer")
        .addColumn("androidVer")
        .build()
        .withHeader()

    val file = File(filePath)
    val readValues: MappingIterator<AppInfoCsv> = csvMapper.readerFor(AppInfoCsv::class.java)
        .with(schema)
        .readValues(file)
    return readValues.readAll()
}

fun androidVersionToApi(version: Float): Int {
    return when (version) {
        in 1.0..1.5 -> 1
        in 1.5..1.6 -> 3
        in 1.6..2.0 -> 4
        in 2.0..2.1 -> 5
        in 2.1..2.2 -> 7
        in 2.2..2.3 -> 8
        in 2.3..3.0 -> 9
        in 3.0..3.1 -> 11
        in 3.1..3.2 -> 12
        in 3.2..4.0 -> 13
        in 4.0..4.1 -> 14
        in 4.1..4.2 -> 16
        in 4.2..4.3 -> 17
        in 4.3..4.4 -> 18
        in 4.4..5.0 -> 19
        in 5.0..5.1 -> 21
        in 5.1..6.0 -> 22
        in 6.0..7.0 -> 23
        in 7.0..7.1 -> 24
        in 7.1..8.0 -> 25
        in 8.0..8.1 -> 26
        in 8.1..9.0 -> 27
        in 9.0..10.0 -> 28
        in 10.0..11.0 -> 29
        in 11.0..12.0 -> 30
        else -> -1 // Неизвестная версия
    }
}

fun convertDate(inputDate: String): String {
    val inFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    val outFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val date = inFormat.parse(inputDate)
    return outFormat.format(date)
}

fun main() {
    val csvData = parseCsvData("./src/main/resources/googleplaystore.csv")
    val objectMapper = jacksonObjectMapper()
    val groupedData = mutableMapOf<String, MutableList<AppInfoJson>>()
    for (data in csvData!!) {
        val androidVer = try {
            val index = data.androidVer?.indexOf(" ") ?: -1
            val version = if (index != -1) {
                data.androidVer?.substring(0, data.androidVer.indexOf(" "))
            } else {
                data.androidVer
            }
            version?.toFloat() ?: -1.0f
        } catch (e: NumberFormatException) {
            -1.0f
        }
        val apiVer = androidVersionToApi(androidVer)

        val installs = try {
            data.installs
                ?.replace(Regex("[+,]"), "")
                ?.toInt() ?: -1
        } catch (e: NumberFormatException) {
            -1
        }

        val price = try {
            (data.price?.replace("$", "")?.toFloat() ?: -1.0f) > 0
        } catch (e: NumberFormatException) {
            false
        }

        val date = try {
            convertDate(data.lastUpdated ?: "")
        } catch (e: ParseException) {
            "1970-01-01T00:00:00"
        }

        val category = data.category ?: "n/d"
        groupedData.computeIfAbsent(category) { mutableListOf() }.add(
            // Отдельная структура т.к. installs должен быть сохранен как число
            // по заданию
            AppInfoJson(
                name = data.name,
                category = category,
                rating = data.rating,
                reviews = data.reviews,
                size = data.size,
                installs = installs,
                type = data.type,
                price = price,
                contentRating = data.contentRating,
                genres = data.genres,
                lastUpdated = date,
                currentVer = data.currentVer,
                androidVer = apiVer
            )
        )
    }
    val resultFile = "./src/main/resources/googleplaystore.json"
    objectMapper.writeValue(File(resultFile),
        groupedData.mapValues { (_, list) ->
            list.sortedBy { it.installs }
        })
    println("Result saved in $resultFile")
}