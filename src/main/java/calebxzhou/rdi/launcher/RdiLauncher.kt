package calebxzhou.rdi.launcher

import org.apache.commons.io.FileUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*


/**
 * Created  on 2023-02-22,21:58.
 */
const val LauncherDebug = true
const val DebugPath = "C:\\Users\\liberatorch\\Documents\\RDIGame - Copy\\RDI40StartTest\\minecraft_files\\MOD"
fun main(args:Array<String>) {
    println("启动中。。。。")
    val command = ""
    val currDir = if(LauncherDebug) DebugPath else System.getProperty("user.dir")
    val mcPath = Path.of(currDir).parent
    println("mc目录：$mcPath")
    if(LauncherDebug)
        System.setProperty("user.dir", mcPath.toString())
    val javaPath = mcPath.resolve("jre").resolve("win").resolve("bin").resolve("java.exe")
    println("Java目录: $javaPath")
    val libPath = mcPath.resolve("lib")
    println("运行库目录：$libPath")
    val resPath = mcPath.resolve("res")
    println("资源目录：$resPath")

    println("读取运行库中。")
    var libCmdArg = ""
    Files.walk(libPath).filter(Files::isRegularFile).forEach{path ->
        libCmdArg+= "${path.absolutePathString()};"
    }
    val cmd = """
            "$javaPath"
             -cp "$libCmdArg"
             -Duser.dir="$mcPath"
             -Xmx3072M
              org.quiltmc.loader.impl.launch.knot.KnotClient
        """
        .trimIndent().replace("\n","")
    println(cmd)
    val builder = ProcessBuilder(
        "cmd.exe", "/c", cmd
    )
    builder.redirectErrorStream(true)
    val p = builder.start()
    val r = BufferedReader(InputStreamReader(p.inputStream))
    var line: String?
    while (true) {
        line = r.readLine()
        if (line == null) {
            break
        }
        println(line)
    }
}
class RdiLauncher {

}
internal class RdiLauncherBatch{
    //移动libraries 把mc原版lib目录里所有jar文件拿出来平行放置
    @OptIn(ExperimentalPathApi::class)
    fun vanillaLib2RdiLib(){
        val path = Path.of("C:\\Users\\liberatorch\\Documents\\RDIGame - Copy\\RDI40StartTest\\minecraft_files\\lib")
        val path2 = Path.of("C:\\Users\\liberatorch\\Documents\\RDIGame - Copy\\RDI40StartTest\\minecraft_files\\lib2")
        path.visitFileTree {
            this.onVisitFile { file, attributes ->
                println(file.name)
                if(file.name.endsWith(".jar")){
                    FileUtils.copyToDirectory(file.toFile(),path2.toFile())
                }
                FileVisitResult.CONTINUE
            }
        }
    }
}
