package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class PoetSerializerTest {
    private val json = Json {
        prettyPrint = false
        serialModule = PoetModule
    }

    @Test fun className() {
        val stringRepr =
            """{""" +
            """"type":"class",""" +
            """"canonicalName":"dev.inkremental.meta.model.PoetSerializerTest"""" +
            """}"""
        val input = PoetSerializerTest::class.asClassName()
        val serialized = json.toJson(TypeNameSerializer, input)
        assertEquals(stringRepr, serialized.toString())
        val output = json.fromJson(TypeNameSerializer, serialized)
        assertEquals(input, output)
    }

    @Test fun parametrizedName() {
        val stringRepr =
            """{""" +
            """"type":"parameterized",""" +
            """"rawType":{"canonicalName":"dev.inkremental.meta.model.PoetSerializerTest"},""" +
            """"typeArguments":[{"type":"class","canonicalName":"kotlin.Int"}]""" +
            """}"""
        val input = PoetSerializerTest::class.asClassName().parameterizedBy(Int::class.asTypeName())
        val serialized = json.toJson(TypeNameSerializer, input)
        assertEquals(stringRepr, serialized.toString())
        val output = json.fromJson(TypeNameSerializer, serialized)
        assertEquals(input, output)
    }
}
