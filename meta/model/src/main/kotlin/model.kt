package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.*

@Serializable
data class ModuleModel(
    val name: String,
    val views: List<ViewModel>
)

@Serializable
data class ViewModel(
    val name: String,
    val plainType: @Polymorphic TypeName,
    val parametrizedType: @Polymorphic ParameterizedTypeName?,
    val attrs: List<AttrModel>,
    var superType: ViewModel? = null
)

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
    val isArray: Boolean,
    val isVarArg: Boolean,
    val isListener: Boolean,
    val isNullable: Boolean
) {
    lateinit var owner: ViewModel
}

@Serializable
data class TypeModel(
    val name: String,
    val isSamLike: Boolean,
    val isInterface: Boolean,
    val plainType: @Polymorphic ClassName,
    val lambdaType: @Polymorphic LambdaTypeName?,
    val parametrizedTypeUnsafe: @Polymorphic ParameterizedTypeName?,
    val functions: List<FunctionModel>
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
