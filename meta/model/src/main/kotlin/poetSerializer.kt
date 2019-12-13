package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.modules.*

fun poetModule(): SerialModule = SerializersModule {
    polymorphic<TypeName> {
        addSubclass(ClassNameSerializer)
        addSubclass(ParameterizedTypeNameSerializer)
    }
}

@Serializer(forClass = ClassName::class)
object ClassNameSerializer: KSerializer<ClassName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("class") {
        init {
            addElement("canonicalName")
        }
    }

    override fun serialize(encoder: Encoder, obj: ClassName) {
        encoder.beginStructure(descriptor).let { composite ->
            composite.encodeStringElement(descriptor, 0, obj.canonicalName)
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): ClassName {
        val dec = decoder.beginStructure(descriptor)
        var canonicalName: String? = null
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> canonicalName = dec.decodeStringElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)
        canonicalName ?: throw MissingFieldException("canonicalName")
        return ClassName.bestGuess(canonicalName)
    }
}

@Serializer(forClass = ParameterizedTypeName::class)
object ParameterizedTypeNameSerializer: KSerializer<ParameterizedTypeName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("parameterized") {
        init {
            addElement("rawType")
            addElement("typeArguments")
        }
    }

    override fun serialize(encoder: Encoder, obj: ParameterizedTypeName) {
        encoder.beginStructure(descriptor).let { composite ->
            composite.encodeSerializableElement(
                descriptor,
                0,
                ClassNameSerializer,
                obj.rawType
            )
            composite.encodeSerializableElement(
                descriptor,
                1,
                PolymorphicSerializer(TypeName::class).list,
                obj.typeArguments
            )
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): ParameterizedTypeName {
        val dec = decoder.beginStructure(descriptor)
        var rawType: ClassName? = null
        var typeArguments: List<TypeName>? = null
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> rawType = dec.decodeSerializableElement(
                    descriptor, i,
                    ClassNameSerializer
                )
                1 -> typeArguments = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    PolymorphicSerializer(TypeName::class).list
                )
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)
        rawType ?: throw MissingFieldException("rawType")
        typeArguments ?: throw MissingFieldException("typeArguments")
        return rawType.parameterizedBy(*typeArguments.toTypedArray())
    }
}
