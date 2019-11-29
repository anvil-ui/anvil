package dev.inkremental.gradle

import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName

// TODO avoid kotlinpoet types in model

data class ModuleModel(
    val name: String,
    val views: List<ViewModel>
)

data class ViewModel(
    val name: String,
    val qualifiedClassName: String,
    val hasTypeParameters: Boolean,
    val plainType: TypeName,
    val parametrizedType: TypeName,
    val starProjectedType: TypeName,
    val attrs: List<AttrModel>,
    var superType: ViewModel? = null
)

fun ViewModel.isAssignableFrom(other: ViewModel): Boolean {
    var vm: ViewModel? = this
    while(vm != null && vm != other) {
        vm = vm.superType
    }
    return vm != null
}

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

data class TypeModel(
    val name: String,
    val isSamLike: Boolean,
    val isInterface: Boolean,
    val plainType: TypeName,
    val argType: TypeName,              // is lambda type for SAM
    val parametrizedType: TypeName,     // has <T> form for generics (e.g. RecyclerView.Adapter<RecyclerView.ViewHolder>)
    val starProjectedType: TypeName,    // has <*, *, ...> form of plain for generics (e.g. RecyclerView.Adapter<*>) and <*> for kotlin.Array
    val functions: List<FunctionModel>
) {
    override fun equals(other: Any?): Boolean = other is TypeModel && parametrizedType == other.parametrizedType
    override fun hashCode(): Int = parametrizedType.hashCode()
}

data class FunctionModel(
    val name: String,
    val parameters: List<ParameterModel>,
    val returnType: TypeName
)

data class ParameterModel(
    val name: String,
    val type: TypeName
)

val FunctionModel.argsString: String
    get() = parameters.joinToString { it.name }
