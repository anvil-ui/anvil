@file:UseSerializers(
    MemberNameSerializer::class,
    ClassNameSerializer::class,
    ParameterizedTypeNameSerializer::class,
    LambdaTypeNameSerializer::class
)

package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.*
import kotlinx.serialization.modules.SerialModule
import kotlinx.serialization.modules.SerializersModule

val ModelModule: SerialModule = SerializersModule {
    include(PoetModule)
}

@Serializable
data class ModuleModel(
    val name: String,
    val javadocContains: String,
    val packageName: String,
    val manualSetter: MemberName? = null,
    val views: List<ViewModel> = listOf()
)

@Serializable
data class ViewModel(
    val name: String,
    val plainType: @Polymorphic TypeName,
    val parametrizedType: ParameterizedTypeName? = null,
    val attrs: List<AttrModel> = listOf(),
    var superType: ViewModel? = null
)

fun ViewModel.backlinkAttrs() {
    attrs.forEach { it.owner = this }
}

val ViewModel.starProjectedType: TypeName
    get() = parametrizedType?.starProjected ?: plainType

fun ViewModel.isAssignableFrom(other: ViewModel): Boolean {
    var vm: ViewModel? = this
    while(vm != null && vm != other) {
        vm = vm.superType
    }
    return vm != null
}

@Serializable
data class AttrModel(
    val name: String,
    val setterName: String,
    val type: TypeModel,
    val isArray: Boolean = false,
    val isVarArg: Boolean = false,
    val isListener: Boolean = false,
    val isNullable: Boolean = false
) {
    @Transient lateinit var owner: ViewModel
}

@Serializable
data class TypeModel(
    val name: String,
    val isSamLike: Boolean = false,
    val isInterface: Boolean = false,
    val plainType: ClassName,
    val lambdaType: LambdaTypeName? = null,
    val parametrizedTypeUnsafe: ParameterizedTypeName? = null,
    val functions: List<FunctionModel> = listOf()
) {
    override fun equals(other: Any?): Boolean = other is TypeModel && parametrizedType == other.parametrizedType
    override fun hashCode(): Int = parametrizedType.hashCode()
}

val TypeModel.parametrizedType: TypeName
    get() = parametrizedTypeUnsafe ?: plainType

val TypeModel.starProjectedType: TypeName
    get() = parametrizedTypeUnsafe?.starProjected ?: plainType

val TypeModel.argType: TypeName
    get() = lambdaType ?: parametrizedType

@Serializable
data class FunctionModel(
    val name: String,
    val parameters: List<ParameterModel>,
    val returnType: @Polymorphic TypeName
)

@Serializable
data class ParameterModel(
    val name: String,
    val type: @Polymorphic TypeName
)

val FunctionModel.argsString: String
    get() = parameters.joinToString { it.name }

val ParameterizedTypeName.starProjected: TypeName
    get() = rawType.parameterizedBy(*typeArguments.map { STAR }.toTypedArray())
