package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.modules.SerialModule
import kotlinx.serialization.modules.SerializersModule

val PoetModule: SerialModule = SerializersModule {
    // TODO set classDiscriminator to "kind" instead of default "type" when available:
    // https://github.com/Kotlin/kotlinx.serialization/issues/546
    polymorphic<TypeName> {
        ClassName::class with ClassNameSerializer
        ParameterizedTypeName::class with ParameterizedTypeNameSerializer
        LambdaTypeName::class with LambdaTypeNameSerializer
        TypeVariableName::class with TypeVariableNameSerializer
        WildcardTypeName::class with WildcardTypeNameSerializer
    }
}

val TypeNameSerializer: KSerializer<TypeName> = PolymorphicSerializer(TypeName::class)

@Serializer(forClass = ClassName::class)
object ClassNameSerializer: KSerializer<ClassName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("class") {
        init {
            addElement("canonicalName")
            addElement("isNullable")
        }
    }

    override fun serialize(encoder: Encoder, obj: ClassName) {
        encoder.beginStructure(descriptor).let { composite ->
            composite.encodeStringElement(descriptor, 0, obj.canonicalName)
            composite.encodeBooleanElement(descriptor, 1, obj.isNullable)
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): ClassName {
        var canonicalName: String? = null
        var isNullable: Boolean? = null
        var annotations: List<AnnotationSpec> = emptyList()

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> canonicalName = dec.decodeStringElement(descriptor, i)
                1 -> isNullable = dec.decodeBooleanElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        canonicalName ?: throw MissingFieldException("canonicalName")
        isNullable ?: throw MissingFieldException("isNullable")
        return ClassName.bestGuess(canonicalName)
            .copy(nullable = isNullable, annotations = annotations)
    }
}

@Serializer(forClass = ParameterizedTypeName::class)
object ParameterizedTypeNameSerializer: KSerializer<ParameterizedTypeName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("parameterized") {
        init {
            addElement("rawType")
            addElement("typeArguments")
            addElement("isNullable")
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
                TypeNameSerializer.list,
                obj.typeArguments
            )
            composite.encodeBooleanElement(descriptor, 2, obj.isNullable)
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): ParameterizedTypeName {
        var rawType: ClassName? = null
        var typeArguments: List<TypeName>? = null
        var isNullable: Boolean? = null
        var annotations: List<AnnotationSpec> = emptyList()

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> rawType = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    ClassNameSerializer
                )
                1 -> typeArguments = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer.list
                )
                2 -> isNullable = dec.decodeBooleanElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        rawType ?: throw MissingFieldException("rawType")
        typeArguments ?: throw MissingFieldException("typeArguments")
        isNullable ?: throw MissingFieldException("isNullable")
        return rawType.parameterizedBy(*typeArguments.toTypedArray())
            .copy(nullable = isNullable, annotations = annotations)
    }
}

@Serializer(forClass = LambdaTypeName::class)
object LambdaTypeNameSerializer: KSerializer<LambdaTypeName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("lambda") {
        init {
            addElement("receiver", isOptional = true)
            addElement("parameters", isOptional = true)
            addElement("returnType")
            addElement("isNullable")
            addElement("isSuspending")
            //addElement("annotations", isOptional = true)
            // TODO implement AnnotationSpecSerializer
        }
    }

    override fun serialize(encoder: Encoder, obj: LambdaTypeName) {
        encoder.beginStructure(descriptor).let { composite ->
            obj.receiver?.let {
                composite.encodeSerializableElement(
                    descriptor,
                    0,
                    TypeNameSerializer,
                    it
                )
            }
            if(obj.parameters.isNotEmpty()) {
                composite.encodeSerializableElement(
                    descriptor,
                    1,
                    ParameterSpecSerializer.list,
                    obj.parameters
                )
            }
            composite.encodeSerializableElement(
                descriptor,
                2,
                TypeNameSerializer,
                obj.returnType
            )
            composite.encodeBooleanElement(
                descriptor,
                3,
                obj.isNullable
            )
            composite.encodeBooleanElement(
                descriptor,
                4,
                obj.isSuspending
            )
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): LambdaTypeName {
        var receiver: TypeName? = null
        var parameters: List<ParameterSpec> = emptyList()
        var returnType: TypeName? = null
        var isNullable: Boolean? = null
        var isSuspending: Boolean? = null
        var annotations: List<AnnotationSpec> = emptyList()

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> receiver = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer
                )
                1 -> parameters = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    ParameterSpecSerializer.list
                )
                2 -> returnType = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer
                )
                3 -> isNullable = dec.decodeBooleanElement(descriptor, i)
                4 -> isSuspending = dec.decodeBooleanElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        returnType ?: throw MissingFieldException("returnType")
        isNullable ?: throw MissingFieldException("isNullable")
        isSuspending ?: throw MissingFieldException("isSuspending")
        return LambdaTypeName
            .get(receiver, parameters, returnType)
            .copy(isNullable, annotations, isSuspending)
    }
}

@Serializer(forClass = ParameterSpec::class)
object ParameterSpecSerializer: KSerializer<ParameterSpec> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("parameter") {
        init {
            addElement("name")
            addElement("type")
        }
    }

    override fun serialize(encoder: Encoder, obj: ParameterSpec) {
        encoder.beginStructure(descriptor).let { composite ->
            composite.encodeStringElement(
                descriptor,
                0,
                obj.name
            )
            composite.encodeSerializableElement(
                descriptor,
                1,
                TypeNameSerializer,
                obj.type
            )
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): ParameterSpec {
        var name: String? = null
        var type: TypeName? = null

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> name = dec.decodeStringElement(descriptor, i)
                1 -> type = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer
                )
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        name ?: throw MissingFieldException("name")
        type ?: throw MissingFieldException("type")
        return ParameterSpec.builder(name, type).build()
    }
}

@Serializer(forClass = TypeVariableName::class)
object TypeVariableNameSerializer: KSerializer<TypeVariableName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("typeVar") {
        init {
            addElement("name")
            addElement("bounds", isOptional = true)
            addElement("variance", isOptional = true)
            addElement("isReified")
            addElement("isNullable")
            //addElement("annotations", isOptional = true)
            // TODO implement AnnotationSpecSerializer
        }
    }

    override fun serialize(encoder: Encoder, obj: TypeVariableName) {
        encoder.beginStructure(descriptor).let { composite ->
            composite.encodeStringElement(
                descriptor,
                0,
                obj.name
            )
            if(obj.bounds.isNotEmpty()) {
                composite.encodeSerializableElement(
                    descriptor,
                    1,
                    TypeNameSerializer.list,
                    obj.bounds
                )
            }
            obj.variance?.let {
                composite.encodeStringElement(
                    descriptor,
                    2,
                    it.name
                )
            }
            composite.encodeBooleanElement(
                descriptor,
                3,
                obj.isReified
            )
            composite.encodeBooleanElement(
                descriptor,
                4,
                obj.isNullable
            )
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): TypeVariableName {
        var name: String? = null
        var bounds: List<TypeName> = emptyList()

        /** Either [KModifier.IN], [KModifier.OUT], or null. */
        var variance: KModifier? = null
        var isReified: Boolean? = null
        var isNullable: Boolean? = null
        var annotations: List<AnnotationSpec> = emptyList()

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> name = dec.decodeStringElement(descriptor, i)
                1 -> bounds = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer.list
                )
                2 -> variance = KModifier.valueOf(dec.decodeStringElement(descriptor, i))
                3 -> isReified = dec.decodeBooleanElement(descriptor, i)
                4 -> isNullable = dec.decodeBooleanElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        name ?: throw MissingFieldException("name")
        isReified ?: throw MissingFieldException("isReified")
        isNullable ?: throw MissingFieldException("isNullable")
        return TypeVariableName(name = name, bounds = *bounds.toTypedArray(), variance = variance)
            .copy(isNullable, annotations, bounds, isReified)
    }
}

@Serializer(forClass = WildcardTypeName::class)
object WildcardTypeNameSerializer: KSerializer<WildcardTypeName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("wildcard") {
        init {
            addElement("outTypes", isOptional = true)
            addElement("inTypes", isOptional = true)
            addElement("isNullable")
            //addElement("annotations", isOptional = true)
            // TODO implement AnnotationSpecSerializer
        }
    }

    override fun serialize(encoder: Encoder, obj: WildcardTypeName) {
        encoder.beginStructure(descriptor).let { composite ->
            if(obj.outTypes.isNotEmpty()) {
                composite.encodeSerializableElement(
                    descriptor,
                    0,
                    TypeNameSerializer.list,
                    obj.outTypes
                )
            }
            if(obj.inTypes.isNotEmpty()) {
                composite.encodeSerializableElement(
                    descriptor,
                    1,
                    TypeNameSerializer.list,
                    obj.inTypes
                )
            }
            composite.encodeBooleanElement(
                descriptor,
                2,
                obj.isNullable
            )
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): WildcardTypeName {
        var outTypes: List<TypeName> = emptyList()
        var inTypes: List<TypeName> = emptyList()
        var isNullable: Boolean? = null
        var annotations: List<AnnotationSpec> = emptyList()

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> outTypes = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer.list
                )
                1 -> inTypes = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    TypeNameSerializer.list
                )
                2 -> isNullable = dec.decodeBooleanElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        isNullable ?: throw MissingFieldException("isNullable")
        return when {
            outTypes.size != 1 -> throw SerializationException("outTypes should have exactly one element")
            inTypes.isEmpty() -> WildcardTypeName.producerOf(outTypes[0])
            inTypes.size != 1 -> throw SerializationException("inTypes should have zero or one element")
            else -> WildcardTypeName.consumerOf(inTypes[0])
        }.copy(isNullable, annotations)
    }
}

@Serializer(forClass = MemberName::class)
object MemberNameSerializer: KSerializer<MemberName> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("member") {
        init {
            addElement("packageName", isOptional = true)
            addElement("enclosingClassName", isOptional = true)
            addElement("simpleName")
        }
    }

    override fun serialize(encoder: Encoder, obj: MemberName) {
        encoder.beginStructure(descriptor).let { composite ->
            if(obj.enclosingClassName != null && obj.packageName.isNotEmpty()) {
                TODO("Both packageName and enclosingClassName are present")
            }
            if(obj.packageName.isNotEmpty()) {
                composite.encodeStringElement(descriptor, 0, obj.packageName)
            }
            obj.enclosingClassName?.let {
                composite.encodeSerializableElement(
                    descriptor,
                    1,
                    ClassNameSerializer,
                    it
                )
            }
            composite.encodeStringElement(descriptor, 2, obj.simpleName)
            composite.endStructure(descriptor)
        }
    }

    override fun deserialize(decoder: Decoder): MemberName {
        var packageName: String? = null
        var enclosingClassName: ClassName? = null
        var simpleName: String? = null

        val dec = decoder.beginStructure(descriptor)
        loop@ while (true) {
            when (val i = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> packageName = dec.decodeStringElement(descriptor, i)
                1 -> enclosingClassName = dec.decodeSerializableElement(
                    descriptor,
                    i,
                    ClassNameSerializer
                )
                2 -> simpleName = dec.decodeStringElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        dec.endStructure(descriptor)

        simpleName ?: throw MissingFieldException("simpleName")
        return when {
            packageName != null -> MemberName(packageName, simpleName)
            enclosingClassName != null -> MemberName(enclosingClassName, simpleName)
            else -> throw SerializationException("Either packageName or enclosingClassName should be present")
        }
    }
}
