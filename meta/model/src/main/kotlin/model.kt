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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerialModule
import kotlinx.serialization.modules.SerializersModule

val ModelModule: SerialModule = SerializersModule {
    include(PoetModule)
}

fun ModelJson(): Json = Json(JsonConfiguration.Stable.copy(encodeDefaults = false), context = ModelModule)

@Serializable
data class ModuleModel(
    val name: String,
    val javadocContains: String,
    val srcPackage: String,
    val modulePackage: String,
    val manualSetter: MemberName? = null,
    val views: List<ViewModel> = listOf()
)

fun ModuleModel.backlink(): ModuleModel = apply {
    views.forEach {
        it.owner = this
        it.backlink()
    }
}

@Serializable
data class ViewModel(
    val name: String,
    val plainType: ClassName,
    val parametrizedType: ParameterizedTypeName? = null,
    val attrs: List<AttrModel> = listOf(),
    var superType: ViewModelSupertype? = null
) {
    @Transient lateinit var owner: ModuleModel
}

val ViewModel.isRoot: Boolean
    get() = superType == null

val ViewModel.scopeType: ClassName
    get() {
        val srcPackage = owner.srcPackage
        val modulePackage = owner.modulePackage
        val viewSrcPackage = plainType.packageName
        val viewModulePackage = if(!viewSrcPackage.startsWith(srcPackage)) {
            println("$plainType does not belong to source package $srcPackage")
            viewSrcPackage
        } else {
            modulePackage + viewSrcPackage.substring(srcPackage.length)
        }
        return ClassName(viewModulePackage, "${name}Scope")
    }

@Serializable
sealed class ViewModelSupertype {
    @Serializable
    class Resolved(val type: ViewModel) : ViewModelSupertype()
    @Serializable
    class Unresolved(val references: List<String>) : ViewModelSupertype()
}

fun ViewModel.backlink(): ViewModel = apply {
    attrs.forEach { it.owner = this }
}

val ViewModel.starProjectedType: TypeName
    get() = parametrizedType?.starProjected ?: plainType

fun ViewModel.isAssignableFrom(other: ViewModel): Boolean {
    var vm: ViewModel? = this
    while(vm != null && vm != other) {
        when(val superType = vm.superType) {
            null -> return false
            is ViewModelSupertype.Unresolved -> return false
            is ViewModelSupertype.Resolved ->  {
                vm = superType.type
            }
        }

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
    val isNullable: Boolean = false,
    val transformers: List<DslTransformer>? = null
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
