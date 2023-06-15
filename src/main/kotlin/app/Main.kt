@file:OptIn(ExperimentalCli::class)

package app

import kotlinx.cli.*
import java.io.IOError
import java.nio.file.Path
import java.nio.file.Paths

fun main(args: Array<String>) {
    val parser = ArgParser("chat-gpt-tools")

    class DumpFiles : Subcommand("dump-files", "Dump files") {
        val path by argument(ArgType.String, "path", description = "Path to directory")

        override fun execute() {
            dumpFiles(
                path = Paths.get(path)
            )
        }
    }

    parser.subcommands(
        DumpFiles(),
    )

    parser.parse(args)
}

private fun dumpFiles(path: Path) {
    val files = path.toFile().listFiles() ?: throw Error("There was a problem when listing $path")

    println("In all the provided files, the backtick character is escaped, so nested Markdown is not ambiguous")
    println()

    files.forEach {
        println("`${it.name}`:")
        println("```")
        print(escape(it.readText()))
        println("```")
        println()
    }
}

private fun escape(text: String): String {
    return text.replace("`", "\\`")
}
