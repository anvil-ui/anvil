package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.MemberName
import dev.inkremental.meta.model.*
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

abstract class GenerateModelTask : DefaultTask() {

    @get:Input abstract var quirks: InkrementalQuirks
    @get:Input abstract var camelCaseName: String
    @get:Input abstract var javadocContains: String
    @get:Input abstract var packageName: String
    @get:Input @get:Optional abstract var manualSetterName: String?
    @get:OutputFile abstract var outputFile: File

    abstract fun createIntrospector(): Introspector

    @TaskAction
    fun run() {
        val introspector = createIntrospector()
        val model = createModel(introspector)
        dumpModel(model)
        val json = Json {
            encodeDefaults = false
            prettyPrint = false
            serialModule = ModelModule
        }
        val outputStr = json.stringify(ModuleModel.serializer(), model)
        outputFile.writeText(outputStr)
    }

    private fun createModel(introspector: Introspector): ModuleModel {
        val superRequests: MutableList<Pair<ViewModel, List<String>>> = mutableListOf()
        val superViews: MutableMap<String, ViewModel> = mutableMapOf()

        val views = introspector.viewModelSequence(quirks)
            .onEach { model ->
                superViews[model.first.plainType.toString()] = model.first
                superRequests += model
            }
            .map { it.first }
            .toList()

        superRequests.forEach { (view, supers) ->
            view.superType = supers.mapNotNull { superViews[it] }.firstOrNull()
        }

        return ModuleModel(
            name = camelCaseName,
            javadocContains = javadocContains,
            packageName = packageName,
            manualSetter = manualSetterName?.let { MemberName(packageName, it) },
            views = views
        )
    }

    private fun dumpModel(model: ModuleModel) {
        model.views.forEach { view ->
            logger.warn("[gen] ${view.plainType}")
            view.attrs.forEach {
                val t = it.type

                val flag = when {
                    it.isListener -> "L"
                    it.isNullable -> "F?"
                    else -> "F"
                }
                logger.warn("[gen]   ${it.name} | ${t.argType} | $flag")
                logger.warn("[gen]       plain   ${t.plainType}")
                t.lambdaType?.let { lambdaType ->
                    logger.warn("[gen]       lmbd    $lambdaType")
                }
                t.parametrizedTypeUnsafe?.let { parametrizedType ->
                    logger.warn("[gen]       prmtrzd $parametrizedType")
                }
            }
            logger.warn("[gen]")
        }
    }
}
