package dev.inkremental.meta.model

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.INT
import kotlin.test.Test
import kotlin.test.assertEquals

class ModelSerializerTest {
    private val json = ModelJson()

    @Test fun simpleModule() {
        val input = ModuleModel(
            name = "test-module",
            javadocContains = "",
            srcPackage = "dev.inkremental.test.src",
            modulePackage = "dev.inkremental.test.dsl",
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
            srcPackage = "dev.inkremental.test.src",
            modulePackage = "dev.inkremental.test.dsl",
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
