package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ModelSerializerTest {
    private val json = Json {
        prettyPrint = true
        serialModule = ModelModule
    }

    @Test fun simpleModule() {
        val input = ModuleModel(
            name = "test-module",
            javadocContains = "",
            packageName = "dev.inkremental",
            views = listOf(
                ViewModel(
                    name = "TestView",
                    plainType = ClassName.bestGuess("dev.inkremental.TestView")
                )
            )
        )
        val serialized = json.toJson(ModuleModel.serializer(), input)
        println("Model:\n${json.stringify(ModuleModel.serializer(), input)}")
        val output = json.fromJson(ModuleModel.serializer(), serialized)
        assertEquals(input, output)
    }

    @Test fun oneAttrModule() {
        val input = ModuleModel(
            name = "test-module",
            javadocContains = "",
            packageName = "dev.inkremental",
            views = listOf(
                ViewModel(
                    name = "TestView",
                    plainType = ClassName.bestGuess("dev.inkremental.TestView"),
                    attrs = listOf(
                        AttrModel(
                            name = "testAttr",
                            setterName = "setTestAttr",
                            type = TypeModel(
                                name = "TestAttrType",
                                plainType = INT
                            )
                        )
                    )
                )
            )
        )
        val serialized = json.toJson(ModuleModel.serializer(), input)
        println("Model:\n${json.stringify(ModuleModel.serializer(), input)}")
        val output = json.fromJson(ModuleModel.serializer(), serialized)
        assertEquals(input, output)
    }
}
