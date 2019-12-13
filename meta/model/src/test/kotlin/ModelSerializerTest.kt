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
        serialModule = poetModule()
    }

    @Test fun simpleModule() {
        val input = ModuleModel(
            name = "test-module",
            views = listOf(
                ViewModel(
                    name = "TestView",
                    plainType = ClassName.bestGuess("dev.inkremental.TestView"),
                    parametrizedType = null,
                    attrs = listOf(),
                    superType = null
                )
            )
        )
        val serialized = json.toJson(ModuleModel.serializer(), input)
        println("Model:\n${json.stringify(ModuleModel.serializer(), input)}")
        val output = json.fromJson(ModuleModel.serializer(), serialized)
        assertEquals(input, output)
    }
}
